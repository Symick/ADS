import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class IterableShapesTest {
    Circle circle1, circle2, circle3;
    Rectangle rectangle1, rectangle2, rectangle3;
    Board<Rectangle> rectangleBoard;
    Board<Circle> circleBoard;
    final int boardSize = 9;
    final double rectanglesTotalArea = 1740.0;

    @BeforeEach
    void setUp() {
        circle1 = new Circle(Color.BLACK,10);
        circle2 = new Circle(Color.BLACK,10);
        circle3 = new Circle(Color.BLACK,5);
        rectangle1 = new Rectangle(Color.BLUE, 4,5);
        rectangle2 = new Rectangle(Color.BLUE, 4,5);
        rectangle3 = new Rectangle(Color.BLUE,2,10);

        rectangleBoard = new Board<>(boardSize);
        for (int i = 0; i < boardSize; i++ ){
            for (int j = 0; j < i; j++) {
                rectangleBoard.add(new Rectangle(Color.BLUE, i+1, j+1), i, j);
                rectangleBoard.add(new Rectangle(Color.BLACK, j+1, i+1), j, i);
            }
        }
        circleBoard = new Board<>(1);
    }

    @Test
    void getAreaShouldFollowShapeFormula() {
        assertEquals(314.1592653589793, circle1.getArea());
        assertEquals(20, rectangle1.getArea());
    }

    @Test
    void getShapesAreaShouldAccumulateAllShapes() {
        assertEquals(rectanglesTotalArea, rectangleBoard.getShapesArea());
        assertEquals(0, circleBoard.getShapesArea());
        // add and remove an item
        assertTrue(rectangleBoard.add(rectangle1,3,3));
        assertNotNull(rectangleBoard.remove(3,3));
        // check total shapes area invariant
        assertEquals(rectanglesTotalArea, rectangleBoard.getShapesArea());
    }

    @Test
    void equalsShouldFollowShapeRules() {
        assertEquals(circle1, circle2);
        assertEquals(rectangle1, rectangle2);
        assertNotEquals(circle1, circle3);
        assertNotEquals(rectangle2, rectangle3);
    }

    @Test
    void hashCodeShouldFollowEquals() {
        assertEquals(circle1.hashCode(), circle2.hashCode());
        assertEquals(rectangle1.hashCode(), rectangle2.hashCode());
        assertNotEquals(circle1.hashCode(), circle3.hashCode());
    }

    @Test
    void addRemoveShouldPreserveBoardIntegrity(){
        //when a shape is added it returns true
        assertTrue(rectangleBoard.add(rectangle1, 2, 2));
        //when a position is occupied it returns false
        assertFalse(rectangleBoard.add(rectangle1, 2, 2));
        // remove returns the shape that has been removed
        assertEquals(rectangle1, rectangleBoard.remove(2,2));
        // check shape has been removed
        assertNull(rectangleBoard.remove(2, 2));
    }

    @Test
    void addRemoveShouldCheckOutOfBounds() {
        Throwable t;
        t = assertThrows(IndexOutOfBoundsException.class,
                () -> {rectangleBoard.remove(-1,-1);}
        );
        assertEquals("Position -1,-1 is not available on a board of size "+boardSize, t.getMessage());

        t = assertThrows(IndexOutOfBoundsException.class,
                () -> {rectangleBoard.add(rectangle2,-1,0);}
        );
        assertEquals("Position -1,0 is not available on a board of size "+boardSize, t.getMessage());

        t = assertThrows(IndexOutOfBoundsException.class,
                () -> {circleBoard.add(circle2,0,1);}
        );
        assertEquals("Position 0,1 is not available on a board of size 1", t.getMessage());

        t = assertThrows(IndexOutOfBoundsException.class,
                () -> {circleBoard.remove(1,1);}
        );
        assertEquals("Position 1,1 is not available on a board of size 1", t.getMessage());
    }

    @Test
    void getGridShouldExposeAllShapes(){
        //The getGrid method returns a 2D array of Shape
        Shape[][] shapes = rectangleBoard.getGrid();
        for (int x = 0; x < shapes.length; x++){
            for(int y = 0; y < shapes.length; y++){
                Shape s = shapes[x][y];
                assertTrue(s == null || s instanceof Rectangle);
            }
        }
    }

    @Test
    void iteratorShouldEnumerateAllNonNullShapesForPartlyPopulatedBoard() {
        testIterator(rectangleBoard, boardSize*boardSize-boardSize, rectanglesTotalArea);
    }

    @Test
    void iteratorShouldEnumerateAllNonNullShapesForEmptyBoard() {
        testIterator(circleBoard, 0, 0.0);
    }

    private <S extends Shape> void testIterator(Board<S> board,
                                                int expectedNumShapes, double expectedTotalArea) {

        int count = 0; int nullCount = 0;  double totalArea = 0;

        Iterator<S> iterator = board.iterator();
        while (iterator.hasNext()){
            S shape = iterator.next();
            count++;
            if (shape == null) {
                nullCount++;
            } else {
                totalArea += shape.getArea();
            }
        }
        assertEquals(expectedNumShapes, count - nullCount);
        assertEquals(expectedTotalArea, totalArea);
        // iterator should skip null shapes:
//        assertEquals(0, nullCount);
    }
}
