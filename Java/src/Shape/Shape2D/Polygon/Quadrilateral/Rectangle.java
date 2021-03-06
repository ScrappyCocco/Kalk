package Shape.Shape2D.Polygon.Quadrilateral;

import Color.ColorRGB;
import Shape.Point2D;
import Shape.Shape2D.Shape2D;

import java.util.ArrayList;

public class Rectangle extends Quadrilateral {

    public Rectangle(ArrayList<Point2D> p) {
        super(p, "Rectangle");
        if (!canBuild(p)) {
            points = buildRectangle(1, 2);
        }
    }

    public Rectangle(ArrayList<Point2D> p, ColorRGB color) {
        super(p, "Rectangle", color);
        if (!canBuild(p)) {
            points = buildRectangle(1, 2);
        }
    }

    public Rectangle(double base, double height) {
        super(null, "Rectangle");
        points = buildRectangle(base, height);
    }

    public Rectangle(double base, double height, ColorRGB color) {
        super(null, "Rectangle", color);
        points = buildRectangle(base, height);
    }

    public Rectangle(Rectangle r) {
        super(r);
    }

    public boolean canBuild(double base, double height) {
        return base > 0 && height > 0 && base != height;
    }

    private boolean canBuild(ArrayList<Point2D> vp2d) {
        if (vp2d.size() == 4) {
            double base = Point2D.distance(vp2d.get(1), vp2d.get(0));
            double height = Point2D.distance(vp2d.get(2), vp2d.get(1));
            return base > 0 && height > 0 && base != height;
        }
        return false;
    }

    public ArrayList<Point2D> buildRectangle(double base, double height) {
        if (!canBuild(base, height)) {
            base = 2;
            height = 1;
        }
        ArrayList<Point2D> points = new ArrayList<>();
        points.add(new Point2D());
        points.add(new Point2D(base, 0));
        points.add(new Point2D(base, height));
        points.add(new Point2D(0, height));

        return points;
    }

    public double base() {
        return Point2D.distance(points.get(1), points.get(0));
    }

    public double height() {
        return Point2D.distance(points.get(2), points.get(1));
    }

    public double diagonal() {
        return Math.sqrt(Math.pow(base(), 2) + Math.pow(height(), 2));
    }

    public double sideToOperator() {
        return base() + height();
    }

    public double area() {
        return base() * height();
    }

    public Rectangle sum(Shape2D other) {
        double newBase;
        double newHeight;
        Rectangle newRectangle;
        if (getClass() == other.getClass()) {
            Rectangle s = (Rectangle) other;
            newBase = base() + s.base();
            newHeight = height() + s.height();
            newRectangle = new Rectangle(buildRectangle(newBase, newHeight), getColor().sum(other.getColor()));
        } else {
            newBase = base() + other.sideToOperator();
            newHeight = height() + other.sideToOperator();
            newRectangle = new Rectangle(buildRectangle(newBase, newHeight), getColor().sum(other.getColor()));
        }
        return newRectangle;
    }

    public Rectangle sub(Shape2D other) {
        double newBase;
        double newHeight;
        Rectangle newRectangle;
        if (getClass() == other.getClass()) {
            Rectangle s = (Rectangle) other;
            newBase = base() - s.base();
            newHeight = height() - s.height();
            newRectangle = new Rectangle(buildRectangle(newBase, newHeight), getColor().sub(other.getColor()));
        } else {
            newBase = base() - other.sideToOperator();
            newHeight = height() - other.sideToOperator();
            newRectangle = new Rectangle(buildRectangle(newBase, newHeight), getColor().sub(other.getColor()));
        }
        return newRectangle;
    }

    public Rectangle mul(Shape2D other) {
        double newBase;
        double newHeight;
        Rectangle newRectangle;
        if (getClass() == other.getClass()) {
            Rectangle s = (Rectangle) other;
            newBase = base() * s.base();
            newHeight = height() * s.height();
            newRectangle = new Rectangle(buildRectangle(newBase, newHeight), getColor().mul(other.getColor()));
        } else {
            newBase = base() * other.sideToOperator();
            newHeight = height() * other.sideToOperator();
            newRectangle = new Rectangle(buildRectangle(newBase, newHeight), getColor().div(other.getColor()));
        }
        return newRectangle;
    }

    public Rectangle div(Shape2D other) {
        double newBase;
        double newHeight;
        Rectangle newRectangle;
        if (getClass() == other.getClass()) {
            Rectangle s = (Rectangle) other;
            newBase = base() / s.base();
            newHeight = height() / s.height();
            newRectangle = new Rectangle(buildRectangle(newBase, newHeight), getColor().div(other.getColor()));
        } else {
            newBase = base() / other.sideToOperator();
            newHeight = height() / other.sideToOperator();
            newRectangle = new Rectangle(buildRectangle(newBase, newHeight), getColor().div(other.getColor()));
        }
        return newRectangle;
    }

    public String toString() {
        return super.toString() + "Base: " + base() + "\nHeight: " + height() +
                "\nArea: " + area() + "\nPerimeter: " + perimeter();
    }
}
