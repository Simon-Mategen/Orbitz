package Controller;

import Controller.Calculators.OrbitCalculator;
import Controller.Calculators.PlanetCalculator;
import Controller.Calculators.PositionCalculator;
import Model.Sun;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Model.Planet;
import Enum.*;
import View.MainFrame;
import View.SoundThread;
import javafx.util.Duration;

import javax.sound.sampled.*;
import javax.swing.*;

public class Controller
{
    private APIReader reader;
    private OrbitCalculator orbitCalculator;
    private PlanetCalculator planetCalculator;
    private PositionCalculator positionCalculator;
    private MainFrame mainframe;
    private SoundThread soundThread;

    private Sun sun;

    private ArrayList<Planet> planetArrayList;

    public Controller()
    {
        reader = new APIReader();
        orbitCalculator = new OrbitCalculator();
        planetCalculator = new PlanetCalculator();
        positionCalculator = new PositionCalculator();


        this.sun = new Sun(reader.readBodyFromAPI(Stars.soleil.toString()));
        sun.setYCord(0);
        sun.setXCord(0);

        planetArrayList = createPlanetArray(1); //No duration modifier should be added here.

        mainframe = new MainFrame(this);
        
    }

    public ArrayList<Planet> getPlanetArrayList()
    {
        return planetArrayList;
    }

    /**
     * Creates an ArrayList with planets and their orbits generated.
     * @return An ArrayList filled with newly generated planet objects
     */
    public ArrayList<Planet> createPlanetArray(double durationModifier)
    {
        ArrayList<Planet> newPlanets = new ArrayList<>();

        //Reads the planets from the API
        for(Planets p : Planets.values())
        {
            newPlanets.add(new Planet(reader.readBodyFromAPI(p.toString())));
        }

        //Add orbits to the planets
        for (Planet p : newPlanets)
        {
            p.setPlanetOrbit(orbitCalculator.getPlanetSunOrbit(sun, p));//Create orbit
        }

        //Sets planet duration
        for (Planet planet: newPlanets)
        {
            planet.setDuration(new Duration((planetCalculator.calculatePlanetSunOrbitTime(sun, planet)*10)*durationModifier)); //*1000 is to make it into seconds instead of milliseconds
        }

        //Set the PathTransition
        for (int i = 0; i < newPlanets.size() ; i++)
        {
            newPlanets.get(i).createPathTransition();
        }

        return newPlanets;
    }



    /**
     * @Author Manna Manojlovic
     * version 1.0
     *
     * user clicks the soundOn-button, then the thread starts,which calls playSound()
     */
    public void startSoundThread()
    {
        if (soundThread == null)
        {
            soundThread = new SoundThread(this);

            Thread t1 = new Thread (soundThread);
            t1.start();

        }

        //if thread is already running, no new thread will be created
        if (soundThread.isRunning())
        {
            System.out.println("Already running");

            return;
        }

        resumeSound();      //when user clicks the soundOn-button after pausing the sound, the sound resumes
                            //pause == false
    }


    /**
     * @Author Manna Manojlovic
     * version 1.0
     *
     * Pauses the thread on buttonclick mute
     */
    public void togglePauseSound()
    {
        soundThread.paused = true;

        System.out.println("Thread is now " + (soundThread.paused ? "paused" : "running") + ".");
    }

    /**
     * @Author Manna Manojlovic
     * version 1.0
     * Continues playing the sound after soundOn-button is clicked on
     */

    public void resumeSound()
    {
        soundThread.paused = false;
    }

    /**
     * @Author: Manna Manojlovic
     * @version 1.0
     *
     * When user clicks play button, a thread that calls this method starts.
     *
     * Method for playing a .wav-file. Reads the .wav through AudioInputStream an plays it via Clip.
     * Clip class also has a built in loop for continuously playing the sound until user stops it manually.
     */
    public void playSound()
    {
        File file = new File("sound/Jupiter2001.wav");
        AudioInputStream ais = null;
        Clip clip = null;

        try
        {
            clip = AudioSystem.getClip();
        }
        catch (LineUnavailableException e)
        {
            e.printStackTrace();
        }

        try
        {
            ais = AudioSystem.getAudioInputStream(file);
        }
        catch (UnsupportedAudioFileException | IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            clip.open(ais);
        }
        catch (LineUnavailableException | IOException e)
        {
            e.printStackTrace();
        }

        //play the sound until user stops it
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        SwingUtilities.invokeLater(() ->
        {

        });
    }
    
    public static void main(String[] args)
{
    Controller controller = new Controller();
}
}


