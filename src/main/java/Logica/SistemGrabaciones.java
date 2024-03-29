package Logica;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SistemGrabaciones {

    private JFXPanel fxPanel = new JFXPanel();
    private MediaPlayer mediaPlayerActual; // Referencia al MediaPlayer actual

    public void crearReproductor(JPanel contentPane) {
        // Agregar el JFXPanel al contentPane
        contentPane.add(fxPanel, BorderLayout.CENTER);
    }

    public void reproducirVideo(JPanel contentPane, String nombreVideo, int index) {
        // Detiene y libera el MediaPlayer anterior si existe
        if (mediaPlayerActual != null) {
            mediaPlayerActual.stop();
            mediaPlayerActual.dispose();
        }

        // Crear un nuevo MediaPlayer para reproducir el video
        mediaPlayerActual = createMediaPlayer(nombreVideo, index);

        // Crear un reproductor de medios de JavaFX para mostrar el video
        MediaView mediaView = new MediaView(mediaPlayerActual);

        // Crear un Pane de JavaFX para envolver el JFXPanel
        StackPane pane = new StackPane();
        pane.getChildren().add(mediaView);

        // Obtener el ancho y alto del contenedor
        double width = contentPane.getWidth();
        double height = contentPane.getHeight();

        // Establecer el tamaño del MediaView
        mediaView.setFitWidth(width);
        mediaView.setFitHeight(height);

        // Crear la escena de JavaFX y configurarla en el JFXPanel
        Scene scene = new Scene(pane, width, height);
        fxPanel.setScene(scene);

        // Reproducir el video
        mediaPlayerActual.play();
    }

    private MediaPlayer createMediaPlayer(String nombreVideo, int index) {
        // Ruta del archivo de video .mp4
        String videoFile = "Videos/Camara " + index + "/" + nombreVideo;

        // Crear un objeto Media con la URL del archivo de video
        Media media = new Media(new File(videoFile).toURI().toString());

        // Crear un reproductor de medios con la media
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Repetir el video
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        return mediaPlayer;
    }

    public void cerrarReproductor() {
        // Detener la reproducción del video actual y liberar recursos del reproductor
        if (mediaPlayerActual != null) {
            mediaPlayerActual.stop();
            mediaPlayerActual.dispose();
            System.out.println("Se han detenido las grabaciones correctamente.");
        }

        // Limpiar el JFXPanel
        fxPanel.setScene(null);
        mediaPlayerActual = null; // Resetear la referencia al MediaPlayer
    }

    // Nuevo método para detener el video que se está reproduciendo actualmente
    public void detenerVideo() {
        if (mediaPlayerActual != null) {
            mediaPlayerActual.stop();
            System.out.println("Video detenido correctamente.");
        }
    }
}