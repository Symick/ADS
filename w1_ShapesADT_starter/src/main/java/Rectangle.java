import java.awt.*;
import java.util.Objects;

public class Rectangle extends Shape{
    private double width;
    private double length;


    public Rectangle(Color color, double width, double length) {
        super(color);
        this.width = width;
        this.length = length;
    }

    @Override
    public double getArea() {
        return width * length;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Rectangle rectangle) {
            boolean sameDimensions = width == rectangle.width && length == rectangle.length;
            return this.getArea() == rectangle.getArea() && super.equals(rectangle) && sameDimensions;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), width, length);
    }
}
