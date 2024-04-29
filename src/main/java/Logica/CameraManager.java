package Logica;

import Guardado.SaveConfiguraciones;
import Guardado.SaveAreas;
import Ventanas.Menu;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoWriter;

public class CameraManager {
    private static List<VideoCapture> cameras = new ArrayList<>();
    private static List<JLabel> cameraLabels = new ArrayList<>();
    
    private static volatile boolean deteccionActiva = false;
    private static Thread movimientoThread;
    
    public static double sensibilidadCamara;
    public static int duracionVideo;
    private static long ultimoTiempoGrabacion = 0;
    
    static SaveAreas guardadoAreas = new SaveAreas();
    static SistemNotificaciones notifi = new SistemNotificaciones();
    static Menu menu = new Menu();
    
    static SaveConfiguraciones guardadoConfig = new SaveConfiguraciones();

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void cargarCamaras() {
        List<String> datos = guardadoConfig.CargarDatos();
        sensibilidadCamara = Integer.parseInt(datos.get(1));
        duracionVideo = Integer.parseInt(datos.get(2));
        System.out.println("Sensibilidad: " + sensibilidadCamara);
        System.out.println("Duración: " + duracionVideo);
        
        int i = 0;
        while (true) {
            VideoCapture camera = new VideoCapture(i);
            if (camera.isOpened()) {
                cameras.add(camera);
                System.out.println("Cámara " + i + " cargada correctamente.");
                i++;
            } else {
                camera.release();
                break; // Detener el bucle cuando no se pueda abrir más cámaras
            }
        }
    }
    
    public static void cargarCamarasComboBox(JComboBox listaCamaras) {
        int i = 0;
        while (true) {
            VideoCapture camera = new VideoCapture(i);
            if (camera.isOpened()) {
                cameras.add(camera);
                listaCamaras.addItem("Camara" + i);
                System.out.println("Cámara " + i + " cargada correctamente.");
                i++;
            } else {
                camera.release();
                break; // Detener el bucle cuando no se pueda abrir más cámaras
            }
        }
    }
    
    public static void eliminarCamaraDelPanel(JPanel panel) {
        if (!cameraLabels.isEmpty()) {
            JLabel cameraLabel = cameraLabels.remove(cameraLabels.size() - 1); // Obtener el último JLabel agregado
            panel.remove(cameraLabel); // Eliminar el JLabel del panel
            panel.revalidate(); // Actualizar el panel
            panel.repaint();
        } else {
            System.err.println("No hay cámaras para eliminar del panel.");
        }
    }

    public static void mostrarCamaraEnPanel(int cameraIndex, JPanel panel) {
        if (cameraIndex < 0 || cameraIndex >= cameras.size()) {
            System.err.println("Índice de cámara inválido.");
            return;
        }

        VideoCapture camera = cameras.get(cameraIndex);
        JLabel cameraLabel = new JLabel();

        Thread videoThread = new Thread(() -> {
            Mat frameMat = new Mat();
            while (true) {
                if (camera.read(frameMat)) {
                    ImageIcon image = new ImageIcon(matToBufferedImage(frameMat));
                    SwingUtilities.invokeLater(() -> {
                        cameraLabel.setIcon(image);
                        cameraLabel.repaint();
                    });
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        videoThread.start();
        
        // Establecer posición y tamaño del cameraLabel
        cameraLabel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        panel.add(cameraLabel);
        cameraLabels.add(cameraLabel);
    }

    public static void cerrarCamaras() {
        for (VideoCapture camera : cameras) {
            if (camera.isOpened()) {
                camera.release();
            }
        }
        cameras.clear();
        cameraLabels.clear();
        System.out.println("Cámaras cerradas correctamente.");
    }

    private static BufferedImage matToBufferedImage(Mat frame) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (frame.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = frame.channels() * frame.cols() * frame.rows();
        byte[] buffer = new byte[bufferSize];
        frame.get(0, 0, buffer);
        BufferedImage image = new BufferedImage(frame.cols(), frame.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
    }
    
    public static void detectarMovimiento(boolean activarDeteccion, String rutaGuardarVideos) {
        if (activarDeteccion && !deteccionActiva) {
            deteccionActiva = true;
            movimientoThread = new Thread(() -> {
                List<Mat> framesAnteriores = new ArrayList<>();
                boolean[] enPausa = new boolean[cameras.size()]; // Array para mantener el estado de pausa para cada cámara

                while (deteccionActiva) {
                    for (int i = 0; i < cameras.size(); i++) {
                        VideoCapture camera = cameras.get(i);
                        Mat frameMat = new Mat();
                        if (camera.read(frameMat)) {
                            if (framesAnteriores.size() <= i) {
                                framesAnteriores.add(i, frameMat.clone());
                            }
                            if (!enPausa[i] && hayMovimiento(framesAnteriores.get(i), frameMat)) {
                                enPausa[i] = true; // Activar pausa para esta cámara
                                final int camaraIndex = i;
                                new Thread(() -> {
                                    try {
                                        // Crear la carpeta si no existe
                                        File directorio = new File(rutaGuardarVideos + "/Camara " + camaraIndex);
                                        if (!directorio.exists()) {
                                            directorio.mkdirs();
                                        }

                                        LocalDateTime fechaHoraActual = LocalDateTime.now();
                                        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH-mm   dd-MM-yyyy"); // Patrón de formato de fecha y hora
                                        String fechaFormateada = fechaHoraActual.format(formateador);
                                        String nombreArchivo = rutaGuardarVideos + "/Camara " + camaraIndex + "/Camara#" + camaraIndex + "  " + fechaFormateada + ".mp4";

                                        // Verificar si ha pasado al menos 1 minuto desde la última grabación
                                        if (System.currentTimeMillis() - ultimoTiempoGrabacion >= 60000) {
                                            System.out.println("Movimiento detectado en la cámara " + camaraIndex);
                                            notifi.guardarNotifi(camaraIndex);
                                            menu.btnNotifi.revalidate();
                                            menu.btnNotifi.repaint();
                                            
                                            // Cambiar el códec de compresión a H.264 para MP4
                                            VideoWriter videoWriter = new VideoWriter(nombreArchivo, VideoWriter.fourcc('H', '2', '6', '4'), 10, new Size(frameMat.width(), frameMat.height()));

                                            long startTime = System.currentTimeMillis();
                                            while (System.currentTimeMillis() - startTime < duracionVideo) { // Grabar durante 10 segundos
                                                if (camera.read(frameMat)) {
                                                    videoWriter.write(frameMat);
                                                }
                                            }
                                            videoWriter.release();
                                            ultimoTiempoGrabacion = System.currentTimeMillis(); // Actualizar el último tiempo de grabación
                                        }
                                        enPausa[camaraIndex] = false; // Desactivar pausa después de grabar el video
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }).start();
                            }
                            framesAnteriores.set(i, frameMat.clone());
                        }
                    }
                }
            });
            movimientoThread.start();
        } else {
            deteccionActiva = false;
        }
    }

    private static boolean hayMovimiento(Mat frameAnterior, Mat frameActual) {
        // Calcula la diferencia absoluta entre los fotogramas anterior y actual
        Mat diferencia = new Mat();
        Core.absdiff(frameAnterior, frameActual, diferencia);

        // Convierte la diferencia a escala de grises
        Mat diferenciaEscalaGrises = new Mat();
        Imgproc.cvtColor(diferencia, diferenciaEscalaGrises, Imgproc.COLOR_BGR2GRAY);

        // Aplica un umbral para detectar cambios significativos
        Mat umbral = new Mat();
        double umbralValor = sensibilidadCamara; // Ajusta este valor según tus necesidades
        Imgproc.threshold(diferenciaEscalaGrises, umbral, umbralValor, 255, Imgproc.THRESH_BINARY);

        // Aplicar operaciones morfológicas para filtrar el ruido
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5)); // Tamaño del kernel
        Imgproc.dilate(umbral, umbral, kernel); // Dilatar para unir regiones blancas cercanas
        Imgproc.erode(umbral, umbral, kernel);  // Erosionar para eliminar pequeños puntos blancos

        // Cuenta los píxeles blancos en el umbral
        int pixelesBlancos = Core.countNonZero(umbral);

        // Si la cantidad de píxeles blancos supera un umbral, consideramos que hay movimiento
        int umbralMovimiento = 100; // Ajusta este valor según tus necesidades
        return pixelesBlancos > umbralMovimiento;
    }
    }