import java.awt.*;
import java.util.Objects;

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
            System.out.println(this + " " + circle);
            return circle.getArea()== this.getArea() && super.equals(circle);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius);
    }
}
