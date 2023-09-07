import java.awt.*;

public class Circle extends Shape {
    private double radius;

    public Circle(Color color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.pow(radius, 2) * Math.PI;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Circle circle) {
            return circle.getArea()== this.getArea() && this.getColor() == circle.getColor();
        }

        return false;
    }
}
