import java.awt.*;

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
            return this.getArea() == rectangle.getArea() && this.getColor() == rectangle.getColor() && sameDimensions;
        }
        return false;
    }
}
