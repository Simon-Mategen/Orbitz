package View;

import Model.Planet;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainLunarPanel extends JPanel implements ActionListener
{
    private JButton returnBtn = new JButton("< Previous");
    private JPanel btnPanel;
    private JFXPanel lunarModel = new JFXPanel();
    private MediaPlayer player;
    private Sphere moon = new Sphere();

    private MainInfoFrame mainInfoFrame;
    private LunarTextPanel lunarTextPanel;

//    private Planet planet;

    public MainLunarPanel()
    {
        setupPanel();

    }

    public void setupPanel()
    {
        lunarTextPanel = new LunarTextPanel();
        setBackground(Color.black);

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setHgap(70);

        btnPanel = new JPanel(flowLayout);
        btnPanel.setBackground(Color.black);

        JLabel headline = new JLabel("THE MOON");
        headline.setFont(new Font("Earth Orbiter", Font.BOLD, 55));
        headline.setForeground(Color.YELLOW);

        JLabel gifLabel = new JLabel();
        gifLabel.setIcon(new ImageIcon("funfacts/moonLanding.gif"));

        returnBtn.setPreferredSize(new Dimension(120,25));

        btnPanel.add(returnBtn);
        btnPanel.add(headline);

        lunarModel.setPreferredSize(new Dimension(80, 80));

        add(btnPanel,BorderLayout.NORTH);
        add(gifLabel,BorderLayout.CENTER);
        add(lunarModel,BorderLayout.WEST);
        add(lunarTextPanel,BorderLayout.EAST);

        Platform.runLater(new Runnable()
        {
            public void run(){
                initFX(lunarModel);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == returnBtn) {
            mainInfoFrame.setVisible(true);
        }
    }

    public void playSound(String filePath) {
        String file = filePath;
        Media media = new Media(new File(file).toURI().toString());
        player = new MediaPlayer(media);
        player.setCycleCount(1);
        player.play();
    }

    /*public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == moon) {
            playSound("sound/earthMoon.mp3");
        }else if(e.getSource() == deimos) {
            playSound("sound/marsDeimos.mp3");
        }else if(e.getSource() == phobos) {
            playSound("sound/marsPhobos.mp3");
        }
    }*/

    public void initFX(JFXPanel lunarModel){
        Group moonRoot = new Group();
        Scene moonScene = new Scene(moonRoot);
        moonScene.setFill(javafx.scene.paint.Color.BLACK);
        moon.setTranslateX(40);
        moon.setTranslateY(40);
        moon.setRadius(40);
        PhongMaterial moonMaterial = new PhongMaterial();
        moonMaterial.setDiffuseMap(new Image("Images/moon.jpg"));
        moon.setMaterial(moonMaterial);
        moonRoot.getChildren().add(moon);
        lunarModel.setScene(moonScene);
    }
}
