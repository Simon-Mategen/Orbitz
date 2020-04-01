package Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Orbit extends Ellipse
{
    Celestialbody celestialbody; // planet or moon

    public Orbit()
    {

    }

    public Orbit(Celestialbody celestialbody, double centerX, double centerY, double radiusX, double radiusY) {
        super(centerX, centerY, radiusX, radiusY);
        this.celestialbody = celestialbody;
        setStroke(Color.LIGHTGRAY);
        setFill(Color.TRANSPARENT);
    }

    public Celestialbody getCelestialbody() {
        return celestialbody;
    }
}
