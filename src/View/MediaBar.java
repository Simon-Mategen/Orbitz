package View;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;

    public class MediaBar extends HBox
    {

        private Slider timeSlider = new Slider(); // Slider for time
        private Slider volumeSlider = new Slider(); // Slider for volume
        private Button btnPlay = new Button("||"); // For pausing the player
        private Label lblVolume = new Label("Volume: ");
        private MediaPlayer player;

        public MediaBar(MediaPlayer play)
        { // Default constructor taking
            // the MediaPlayer object
            player = play;

            setAlignment(Pos.CENTER); // setting the HBox to center
            setPadding(new Insets(5, 10, 5, 10));
            // Set the preference for volume bar
            volumeSlider.setPrefWidth(70);
            volumeSlider.setMinWidth(30);
            volumeSlider.setValue(100);
            HBox.setHgrow(timeSlider, Priority.ALWAYS);
            btnPlay.setPrefWidth(30);

            // Adding the components to the bottom

            getChildren().add(btnPlay); // Playbutton
            getChildren().add(timeSlider); // time slider
            getChildren().add(lblVolume); // volume slider
            getChildren().add(volumeSlider);

            // Adding Functionality
            // to play the media player
            btnPlay.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    MediaPlayer.Status status = player.getStatus(); // To get the status of Player
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
                    if (status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.PAUSED) {
                        player.play(); // Start the video
                        btnPlay.setText("||");
                    }
                }
            });


            player.currentTimeProperty().addListener(new InvalidationListener()
            {
                public void invalidated(Observable ov)
                {
                    updatesValues();
                }
            });

            // jump by clicking
            timeSlider.valueProperty().addListener(new InvalidationListener() {
                public void invalidated(Observable ov)
                {
                    if (timeSlider.isPressed())
                    { // It would set the time
                        // as specified by user by pressing
                        player.seek(player.getMedia().getDuration().multiply(timeSlider.getValue() / 100));
                    }
                }
            });

            // volume control
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                public void invalidated(Observable ov)
                {
                    if (volumeSlider.isPressed()) {
                        player.setVolume(volumeSlider.getValue() / 100); // It would set the volume
                        // as specified by user by pressing
                    }
                }
            });
        }

        // Outside the constructor
        protected void updatesValues()
        {
            Platform.runLater(new Runnable() {
                public void run()
                {
                    // Updating to the new time value
                    // This will move the slider while running your video
                    timeSlider.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100);
                }
            });
        }
    }