package View;

import Model.Song;
import Model.Theme;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;

import java.io.File;


/**
 * A Media Bar that contains the ability to play music, pause it and change the volume
 * Also features a Combo Box for selection of tracks.
 * @author Albin Ahlbeck
 */
public class MediaBar extends HBox
    {
        private Slider volumeSlider = new Slider(); // Slider for volume
        private Button btnPlay = new Button("||"); // For pausing the player
        private Label lblVolume = new Label("Volume: ");
        private MediaPlayer player;
        private ComboBox<Song> songComboBox;
        private Media song;
        private Song[] songs;

        public MediaBar(Theme theme)
        {
            System.out.println(theme.getName());
            songs = initSongs();
            songComboBox = new ComboBox<Song>(FXCollections.observableArrayList(songs));
            songComboBox.getSelectionModel().select(0);
            String path = songs[0].getPath();
            song = new Media(new File(path).toURI().toString());
            player = new MediaPlayer(song);
            player.play();
            setAlignment(Pos.CENTER);
            setPadding(new Insets(5, 10, 5, 10));
            setSpacing(8);
            // Set the preference for volume bar
            volumeSlider.setPrefWidth(70);
            volumeSlider.setMinWidth(30);
            volumeSlider.setValue(100);
            btnPlay.setPrefWidth(30);
           addTheme(theme);


            getChildren().add(btnPlay);
            getChildren().add(lblVolume);
            getChildren().add(volumeSlider);
            getChildren().add(songComboBox);
            setBackground(Background.EMPTY);

            EventHandler<ActionEvent> event =
                    new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e)
                        {
                            System.out.println("Pausing");
                            if (player.getStatus() == Status.PLAYING) {
                                player.pause();
                            }
                            btnPlay.setText("||");
                            song = new Media(new File(songComboBox.getSelectionModel().getSelectedItem().getPath()).toURI().toString()); // scary code
                            player = new MediaPlayer(song);
                            player.play();
                        }
                    };

            songComboBox.setOnAction(event);

            btnPlay.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    Status status = player.getStatus(); // To get the status of Player
                    if (status == status.PLAYING) {

                        // If the status is Video playing
                        if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {

                            // If the player is at the end of video
                            player.seek(player.getStartTime()); // Restart the video
                            player.play();
                        }
                        else {
                            // Pausing the player
                            player.pause();

                            btnPlay.setText(">");
                        }
                    } // If the video is stopped, halted or paused
                    if (status == Status.HALTED || status == Status.STOPPED || status == Status.PAUSED) {
                        player.play(); // Start the video
                       btnPlay.setText("||");
                    }
                }
            });

            // volume control
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                public void invalidated(Observable ov)
                {
                    if (volumeSlider.isPressed()) {
                        player.setVolume(volumeSlider.getValue() / 100);

                    }
                }
            });
        }

        private Song[] initSongs()
        {
            int songs = 4;
            Song[] tempSongs = new Song[songs];
            tempSongs[0] = new Song("Emil Rottmayer","Descend", "sound/Emil Rottmayer - Descend.mp3");
            tempSongs[1] = new Song("Mike Noise","Low Earth Orbit", "sound/Mike Noise Low Earth Orbit.mp3");
            tempSongs[2] = new Song("Daniel Rosenfeld","Stranger Things  Theme Songx","sound/Stranger Things Theme Songx.mp3");
            tempSongs[3] = new Song("John Williams", "Star Wars Main Theme", "sound/starwars.mp3");
            return tempSongs;
        }

        public void addTheme(Theme theme)
        {
            btnPlay.setTextFill(theme.getMainPaint());
            btnPlay.setBackground(new Background(new BackgroundFill(theme.getSecondaryPaint(),
                    CornerRadii.EMPTY, Insets.EMPTY)));

            lblVolume.setTextFill(theme.getSecondaryPaint());
            songComboBox.setBackground(new Background(new BackgroundFill(theme.getSecondaryPaint(),
                    CornerRadii.EMPTY, Insets.EMPTY)));

        }

        public void changeSong(int index)
        {
            songComboBox.getSelectionModel().select(index);
        }

    }