package View;

import Controller.Controller;

public class SoundThread implements Runnable
{
    private Controller controller;
    private boolean running = false;
    public boolean paused = false;

    //boolean for a thread that is already running
    public boolean isRunning()
    {
        return running == true;
    }

    /**
     * constructor
     * @param controller
     */
    public SoundThread(Controller controller)
    {
        this.controller = controller;

    }

    /**
     * when user hits "Stop" button, the thread pauses with sleep(), resumes again when user hits "start" button
     * when start button is pressed the thread starts it's run() by moving the text
     * pauses for 200 ms before next position
     */
    @Override
    public void run()
    {

        try
        {
            while (true)
            {

                while (paused)
                {
                    Thread.sleep(500);
                }

                //the thread runs the method in the controller
                controller.playSound();
                Thread.sleep(200);

            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Finished running.");
        }

        paused = false;
        running = false;

    }
}
