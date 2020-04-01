package Model;

import javafx.animation.PathTransition;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;

public abstract class Celestialbody extends Circle
{
    double speed;
    ImageView imageView;
    String mass;
    String size;
    String distanceFromSun;
    String lengthOfYear;

    enum PlanetType
    {
        Gas,
        Giant,
        Rock,
    }

    PathTransition pathTransition;
    Path path;
    Orbit orbit;

    public Celestialbody()
    {

    }

    public Celestialbody(double centerX, double centerY, double radius, Paint fill)
    {
        setCenterX(centerX);
        setCenterY(centerY);
        setRadius(radius);
        setFill(fill);
    }


    public Celestialbody(double radius, Paint fill, double speed, double radius1, ImageView imageView, String mass, String size, String distanceFromSun, String lengthOfYear, PathTransition pathTransition, Path path, Orbit orbit) {
        super(radius, fill);
        this.speed = speed;
        this.imageView = imageView;
        this.mass = mass;
        this.size = size;
        this.distanceFromSun = distanceFromSun;
        this.lengthOfYear = lengthOfYear;
        this.pathTransition = pathTransition;
        this.path = path;
        this.orbit = orbit;
    }

    public Celestialbody(double speed, double radius, ImageView imageView, String mass, String size, String distanceFromSun, String lengthOfYear, PathTransition pathTransition, Path path, Orbit orbit) {
        this.speed = speed;
        this.imageView = imageView;
        this.mass = mass;
        this.size = size;
        this.distanceFromSun = distanceFromSun;
        this.lengthOfYear = lengthOfYear;
        this.pathTransition = pathTransition;
        this.path = path;
        this.orbit = orbit;
    }

    public Celestialbody(double centerX, double centerY, double radius, double speed, double radius1, ImageView imageView, String mass, String size, String distanceFromSun, String lengthOfYear, PathTransition pathTransition, Path path, Orbit orbit) {
        super(centerX, centerY, radius);
        this.speed = speed;
        this.imageView = imageView;
        this.mass = mass;
        this.size = size;
        this.distanceFromSun = distanceFromSun;
        this.lengthOfYear = lengthOfYear;
        this.pathTransition = pathTransition;
        this.path = path;
        this.orbit = orbit;
    }

    public Celestialbody(double centerX, double centerY, double radius, Paint fill, double speed, double radius1, ImageView imageView, String mass, String size, String distanceFromSun, String lengthOfYear, PathTransition pathTransition, Path path, Orbit orbit) {
        super(centerX, centerY, radius, fill);
        this.speed = speed;
        this.imageView = imageView;
        this.mass = mass;
        this.size = size;
        this.distanceFromSun = distanceFromSun;
        this.lengthOfYear = lengthOfYear;
        this.pathTransition = pathTransition;
        this.path = path;
        this.orbit = orbit;
    }

    public double getSpeed() {
        return speed;
    }

    public Orbit getOrbit() {
        return orbit;
    }

    public Path getPath() {
        return path;
    }

    public String getDistanceFromSun() {
        return distanceFromSun;
    }

    public PathTransition getPathTransition() {
        return pathTransition;
    }

    public String getMass() {
        return mass;
    }

    public String getSize() {
        return size;
    }

    public String getLengthOfYear() {
        return lengthOfYear;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setDistanceFromSun(String distanceFromSun) {
        this.distanceFromSun = distanceFromSun;
    }

    public void setLengthOfYear(String lengthOfYear) {
        this.lengthOfYear = lengthOfYear;
    }

    public void setPathTransition(PathTransition pathTransition) {
        this.pathTransition = pathTransition;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public void setOrbit(Orbit orbit) {
        this.orbit = orbit;
    }
}
