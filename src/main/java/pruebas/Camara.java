package pruebas;

import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Camara extends JFrame {

    private JLabel visualizadorCamara;
    private JLabel alertaTextual;
    private VideoCapture capturadorVideo;
    private MatOfRect deteccion;

    public Camara() {
        super("Camera Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 520);
        setLayout(new BorderLayout());

        visualizadorCamara = new JLabel();
        visualizadorCamara.setHorizontalAlignment(SwingConstants.CENTER);
        add(visualizadorCamara, BorderLayout.CENTER);

        alertaTextual = new JLabel("Sin movimiento");
        alertaTextual.setHorizontalAlignment(SwingConstants.CENTER);
        add(alertaTextual, BorderLayout.SOUTH);

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        capturadorVideo = new VideoCapture();
        deteccion = new MatOfRect();

        List<String> cameraList = getCameraList();
        JComboBox<String> cameraComboBox = new JComboBox<>(cameraList.toArray(new String[0]));
        cameraComboBox.addActionListener(e -> {
            JComboBox<String> combo = (JComboBox<String>) e.getSource();
            int selectedCameraIndex = combo.getSelectedIndex();
            if (selectedCameraIndex == 0) {
                stopCapture();
                visualizadorCamara.setIcon(null);
                visualizadorCamara.setText("No has seleccionado una cámara");
            } else {
                startCapture(selectedCameraIndex - 1);
            }
        });
        add(cameraComboBox, BorderLayout.NORTH);
    }

    private void startCapture(int cameraIndex) {
        if (capturadorVideo.isOpened()) {
            capturadorVideo.release();
        }
        capturadorVideo.open(cameraIndex);
        if (capturadorVideo.isOpened()) {
            new Thread(this::runCapture).start();
            visualizadorCamara.setText("");
        }
    }

    private void stopCapture() {
        capturadorVideo.release();
    }

    private List<String> getCameraList() {
        List<String> cameras = new ArrayList<>();
        cameras.add("Seleccione una cámara");
        int maxIndex = 10;
        for (int i = 0; i < maxIndex; i++) {
            VideoCapture tempCapture = new VideoCapture(i);
            if (tempCapture.isOpened()) {
                String cameraName = "Cámara " + i;
                cameras.add(cameraName);
                tempCapture.release();
                break;
            }
            tempCapture.release();
        }
        return cameras;
    }

    private boolean detectMovement(Mat frame) {
        Mat grayFrame = new Mat();
        Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

        if (deteccion.empty()) {
            deteccion = new MatOfRect();
            grayFrame.copyTo(deteccion);
            return false;
        }

        Mat absoluteDiff = new Mat();
        Core.absdiff(grayFrame, deteccion, absoluteDiff);

        Mat threshold = new Mat();
        Imgproc.threshold(absoluteDiff, threshold, 30, 255, Imgproc.THRESH_BINARY);

        int whitePixels = Core.countNonZero(threshold);
        int thresholdPixels = (int) (frame.cols() * frame.rows() * 0.01);

        grayFrame.copyTo(deteccion);

        return whitePixels > thresholdPixels;
    }

    private void runCapture() {
        Mat frame = new Mat();
        while (true) {
            if (capturadorVideo.read(frame)) {
                boolean isMovementDetected = detectMovement(frame);
                if (isMovementDetected) {
                    alertaTextual.setText("Movimiento detectado");
                } else {
                    alertaTextual.setText("Sin movimiento");
                }
                ImageIcon image = new ImageIcon(Mat2BufferedImage(frame));
                visualizadorCamara.setIcon(image);
                visualizadorCamara.repaint();
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private BufferedImage Mat2BufferedImage(Mat frame) {
        Mat tempMat = new Mat();
        Imgproc.cvtColor(frame, tempMat, Imgproc.COLOR_BGR2RGB); // Convertir a RGB
        byte[] data = new byte[tempMat.rows() * tempMat.cols() * (int) (tempMat.elemSize())];
        tempMat.get(0, 0, data);
        BufferedImage image = new BufferedImage(tempMat.cols(), tempMat.rows(), BufferedImage.TYPE_3BYTE_BGR);
        image.getRaster().setDataElements(0, 0, tempMat.cols(), tempMat.rows(), data);
        return image;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Camara cameraViewer = new Camara();
            cameraViewer.setVisible(true);
        });
    }
}