package pruebas;

import javafx.application.Platform;
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

public class reproductor extends JFrame {

    private final JFXPanel fxPanel = new JFXPanel();

    public reproductor() {
        setTitle("Reproductor de Video");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el panel de contenido
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Agregar el panel de JavaFX al panel de contenido
        contentPane.add(fxPanel, BorderLayout.CENTER);

        // Inicializar JavaFX
        Platform.runLater(() -> {
            initFX(fxPanel);
        });
    }

    private void initFX(JFXPanel fxPanel) {
        // Crear un reproductor de medios para reproducir el video
        MediaPlayer mediaPlayer = createMediaPlayer();

        // Crear un reproductor de medios de JavaFX para mostrar el video
        MediaView mediaView = new MediaView(mediaPlayer);

        // Crear un Pane de JavaFX para envolver el JFXPanel
        StackPane pane = new StackPane();
        pane.getChildren().add(mediaView);

        // Crear la escena de JavaFX y configurarla en el JFXPanel
        Scene scene = new Scene(pane);
        fxPanel.setScene(scene);

        // Reproducir el video
        mediaPlayer.play();
    }

    private MediaPlayer createMediaPlayer() {
        // Ruta del archivo de video .avi
        String videoFile = "Videos/Camara 0/Camara#0  12-35   27-03-2024.mp4"; // Cambia la ruta al archivo de tu video

        // Crear un objeto Media con la URL del archivo de video
        Media media = new Media(new File(videoFile).toURI().toString());

        // Crear un reproductor de medios con la media
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Repetir el video
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        return mediaPlayer;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            reproductor player = new reproductor();
            player.setVisible(true);
        });
    }
}

