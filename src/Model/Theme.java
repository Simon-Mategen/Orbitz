package Model;

import java.awt.*;

public class Theme
{
    private String name;
    private Color mainColor;
    private Color secondaryColor;
    private javafx.scene.paint.Paint mainPaint;
    private javafx.scene.paint.Paint secondaryPaint;

    public Theme(String name, Color mainColor, Color secondaryColor, javafx.scene.paint.Paint mainPaint, javafx.scene.paint.Paint secondaryPaint)
    {
        this.name = name;
        this.mainColor = mainColor;
        this.secondaryColor = secondaryColor;
        this.mainPaint = mainPaint;
        this.secondaryPaint = secondaryPaint;
    }

    public Color getMainColor() {
        return mainColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public javafx.scene.paint.Paint getMainPaint() {
        return mainPaint;
    }

    public javafx.scene.paint.Paint getSecondaryPaint() {
        return secondaryPaint;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
