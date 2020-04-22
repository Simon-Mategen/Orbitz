package View;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

import java.util.Random;
import java.util.Timer;

public class Star extends Circle
{
    private int x;
    private int y;
    private int z;

    private Random rng = new Random();
    private int min = 1;
    private int max = 4;

    public Star(double radius, int x, int y , int z)
    {
        super(radius);
        setVisible(true);
        setTranslateZ(z);
        setTranslateX(x);
        setTranslateY(y);

        setFill(Color.LIGHTGOLDENRODYELLOW);
/*

        // not sure if we want stars in other colors
        if (rng.nextInt((max - min + 1) + min) == 1)
        {
            setFill(Color.WHITE);
        }

        if (rng.nextInt((max - min + 1) + min) == 2)
        {
            setFill(Color.YELLOW);
        }

        if (rng.nextInt((max - min + 1) + min) == 3)
        {
            setFill(Color.CYAN);
        }




        if (rng.nextInt((max - min + 1) + min) == 4)
        {
            setFill(Color.ORANGE);
        }

 */

    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }


}
