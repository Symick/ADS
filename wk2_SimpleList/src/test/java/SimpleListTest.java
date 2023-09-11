import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleListTest {
    ASimpleList<Integer> ints = new ASimpleList<>();

    @BeforeEach
    void setUp() {
        ints.add(4);
        ints.add(5);
        ints.add(3);
    }

    @Test
    void checkSize() {
        assertEquals(3, ints.size());
    }

    @Test
    void newItemShouldBeAtEnd() {
        ints.add(10);
        assertEquals(10, ints.get(3));
    }

    @Test
    void wrongIndexThrowsException() {
       assertThrows(IndexOutOfBoundsException.class, () -> {ints.get(-1);});
        assertThrows(IndexOutOfBoundsException.class, () -> {ints.get(5);});
        assertThrows(IndexOutOfBoundsException.class, () -> {ints.remove(-1);});
        assertThrows(IndexOutOfBoundsException.class, () -> {ints.remove(5);});
    }

    @Test
    void removeShouldReturnItem() {
        assertEquals(4, ints.remove(0));
        //previous last index should become null.
        assertNull(ints.remove(2));
    }

}
