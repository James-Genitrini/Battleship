package boardifier.model;

public class Coord2D {
    private double x;
    private double y;

    public Coord2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coord2D() {
        this(0.0, 0.0);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void relativeMove(Coord2D pos) {
        this.x += pos.x;
        this.y += pos.y;
    }

    public void relativeMove(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public Coord2D add(double x, double y) {
        return new Coord2D(this.x+x, this.y+y);
    }

    public Coord2D add(Coord2D pos) {
        return new Coord2D(this.x+pos.x, this.y+pos.y);
    }

    public Coord2D subtract(double x, double y) {
        return new Coord2D(this.x-x, this.y-y);
    }
    public Coord2D subtract(Coord2D pos) {
        return new Coord2D(this.x-pos.x, this.y-pos.y);
    }
}
