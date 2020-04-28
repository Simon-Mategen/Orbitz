package View;

import Model.Song;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class MediaBar extends HBox
    {
        private Slider volumeSlider = new Slider(); // Slider for volume
        private Button btnPlay = new Button("||"); // For pausing the player
        private Label lblVolume = new Label("Volume: ");
        private MediaPlayer player;
        private ComboBox<Song> songComboBox;
        private Media song;
        private Song[] songs;

        public MediaBar()
        {
            songs = initSongs();
            songComboBox = new ComboBox<Song>(FXCollections.observableArrayList(songs));
            String path = songs[0].getPath();
            song = new Media(new File(path).toURI().toString());
            player = new MediaPlayer(song);
            setAlignment(Pos.CENTER);
            setPadding(new Insets(5, 10, 5, 10));
            // Set the preference for volume bar
            volumeSlider.setPrefWidth(70);
            volumeSlider.setMinWidth(30);
            volumeSlider.setValue(100);
            btnPlay.setPrefWidth(30);


            getChildren().add(btnPlay);
            getChildren().add(lblVolume);
            getChildren().add(volumeSlider);
            getChildren().add(songComboBox);

            // Adding Functionality
            // to play the media player

            EventHandler<ActionEvent> event =
                    new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e)
                        {
                            player.pause();
                            song = new Media(new File(songComboBox.getSelectionModel().getSelectedItem().getPath()).toURI().toString()); // scary code
                            player = new MediaPlayer(song);
                            player.play();
                        }
                    };

            // Set on action
            songComboBox.setOnAction(event);

            btnPlay.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    MediaPlayer.Status status = player.getStatus();
                    if (status == MediaPlayer.Status.PLAYING) {

                        if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {


                            player.seek(player.getStartTime());
                            player.play();
                        }
                        else {

                            player.pause();

                            btnPlay.setText(">");
                        }
                    }
                    if (status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.PAUSED) {
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
            player.play();
        }

        private Song[] initSongs()
        {
            int songs = 3;
            Song[] tempSongs = new Song[songs];
            tempSongs[0] = new Song("Emil Rottmayer","Descend", "sound/Emil Rottmayer - Descend.mp3");
            tempSongs[1] = new Song("Mike Noise","Low Earth Orbit", "sound/Mike Noise Low Earth Orbit.mp3");
            tempSongs[2] = new Song("Daniel Rosenfeld","Stranger Things  Theme Songx","sound/Stranger Things Theme Songx.mp3");
            return tempSongs;
        }

    }