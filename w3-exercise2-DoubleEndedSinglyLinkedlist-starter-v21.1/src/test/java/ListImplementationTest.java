import models.DELinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
class ListImplementationTest {

    private List<String> list1;
    private List<String> list2;

    @BeforeEach
    void setup() {
//        list1 = new ArrayList<>(); list2 = new ArrayList<>();
//        list1 = new LinkedList<>(); list2 = new LinkedList<>();
//        list1 = new Vector<>(); list2 = new Vector<>();
        list1 = new DELinkedList<>(); list2 = new DELinkedList<>();
    }

    @Test
    void t01_addAndSizeAndIsEmptyAndEqualsTest() {
        assertEquals(0, list1.size());
        assertTrue(list1.isEmpty());
        assertEquals(list1, list2);

        assertTrue(list1.add("Leentje"));
        assertEquals(1, list1.size());
        assertNotEquals(list1, list2);
        assertFalse(list1.isEmpty());

        assertTrue(list2.add("Leentje"));
        assertEquals(list1, list2);

        assertTrue(list1.add("Leerde"));
        assertEquals(2, list1.size());
        assertNotEquals(list1, list2);

        assertTrue(list2.add("Leerde"));
        assertEquals(list1, list2);

        assertTrue(list1.add("Lotje"));
        assertEquals(3, list1.size());
        assertNotEquals(list1, list2);

        assertTrue(list2.add("Lotje"));
        assertEquals(list1, list2);

        assertTrue(list1.add("Lotje"));
        assertEquals(4, list1.size());
    }

    @Test
    void t02_getAndAddIdxTest() {
        assertEquals(0, list1.size());
        assertEquals(list1, list2);

        list1.add(0, "Lotje");
        list1.add(0, "Leentje");
        assertEquals(2, list1.size());
        list1.add(2, "Lopen");
        assertEquals(3, list1.size());
        list1.add(1, "Leerde");
        assertEquals(4, list1.size());

        assertThrows(IndexOutOfBoundsException.class, () -> { list1.add(5, "."); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list1.add(6, "."); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list1.add(99, "."); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list1.add(-1, "."); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list1.add(-99, "."); });

        assertEquals("Leentje", list1.get(0));
        assertEquals("Leerde", list1.get(1));
        assertEquals("Lotje", list1.get(2));
        assertEquals("Lopen", list1.get(3));

        assertThrows(IndexOutOfBoundsException.class, () -> { list1.get(4); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list1.get(5); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list1.get(99); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list1.get(-1); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list1.get(-99); });
    }

    @Test
    void t03_addAllAndClearTest() {
        assertTrue(list1.addAll(List.of("Leentje", "Leerde", "Lotje", "Lopen")));
        assertTrue(list1.addAll(List.of("Langs", "de", "Lange", "Lindenlaan")));
        assertEquals(8, list1.size());

        assertTrue(list2.addAll(List.of("Lopen")));
        assertTrue(list2.addAll(1, List.of("Langs", "de", "Lange")));
        assertTrue(list2.addAll(0, List.of("Leentje", "Leerde")));
        assertTrue(list2.addAll(2, List.of("Lotje")));
        assertTrue(list2.add("Lindenlaan"));
        assertEquals(list1,list2);

        list1.clear();
        assertEquals(0, list1.size());
        assertTrue(list1.isEmpty());
        list2.clear();
        assertEquals(list1, list2);
        assertTrue(list1.add("Leentje"));
        assertTrue(list1.addAll(1,List.of("Lotje", "Lopen")));
        list1.add(1,"Leerde");
        assertEquals(list1, List.of("Leentje", "Leerde", "Lotje", "Lopen"));

        assertThrows(IndexOutOfBoundsException.class, () -> { list2.addAll(1, List.of("Leentje", "Leerde")); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list2.addAll(2, List.of("Leentje", "Leerde")); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list2.addAll(99, List.of("Leentje", "Leerde")); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list2.addAll(-1, List.of("Leentje", "Leerde")); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list2.addAll(-2, List.of("Leentje", "Leerde")); });
    }

    @Test
    void t04_removeAndRemoveAllTest() {
        assertTrue(list1.addAll(List.of("Leentje", "Leerde", "Lotje", "Lopen")));
        assertTrue(list1.addAll(List.of("Langs", "de", "Lange", "Lindenlaan")));

        assertTrue(list1.remove("Leentje"));
        assertTrue(list1.remove("Lindenlaan"));
        assertTrue(list1.remove("Langs"));
        assertEquals(5, list1.size());
        assertFalse(list1.isEmpty());
        assertTrue(list1.removeAll(List.of("Lotje", "Lopen")));
        assertEquals(3, list1.size());
        assertFalse(list1.removeAll(List.of("Lotje", "Lopen")));
        assertEquals(3, list1.size());
        assertTrue(list1.removeAll(List.of("Leerde", "Lotje", "Lopen")));
        assertEquals(2, list1.size());
        assertTrue(list1.removeAll(List.of("de", "Lange")));
        assertEquals(0, list1.size());
        assertTrue(list1.isEmpty());

        assertFalse(list1.remove("Leentje"));
        assertEquals(list1, list2);
    }

    @Test
    void t05_setAndRemoveIdxTest() {
        assertTrue(list1.addAll(List.of("Leentje", "Leerde", "Lotje", "Lopen")));
        assertEquals("Leentje", list1.set(0, "Jantje"));
        assertEquals("Lotje", list1.set(2, "Pietje"));
        assertEquals("Lopen",list1.remove(3));
        assertEquals("Pietje", list1.set(2, "Klaasje"));
        assertEquals("Jantje",list1.remove(0));
        assertEquals(2, list1.size());

        assertThrows(IndexOutOfBoundsException.class, () -> { list1.set(2, "."); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list1.set(3, "."); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list1.set(99, "."); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list1.set(-1, "."); });
        assertThrows(IndexOutOfBoundsException.class, () -> { list1.set(-99, "."); });

        assertEquals("Klaasje",list1.remove(1));
        assertEquals("Leerde",list1.remove(0));
        assertEquals(0, list1.size());
        assertEquals(list1, list2);

    }

    @Test
    void t06_containsAndContainsAllAndRetainAllTest() {
        assertFalse(list1.contains("Leentje"));
        assertFalse(list1.containsAll(List.of("Leentje", "Leerde")));
        assertTrue(list1.containsAll(List.of()));
        assertFalse(list1.retainAll(List.of("Leentje", "Leerde")));
        // returns whether the collection has changed
        assertFalse(list1.retainAll(List.of()));

        assertTrue(list1.addAll(List.of("Leentje", "Leerde", "Lotje", "Lopen")));
        assertTrue(list1.contains("Leentje"));
        assertTrue(list1.contains("Leerde"));
        assertTrue(list1.contains("Lopen"));
        assertFalse(list1.contains(""));
        assertFalse(list1.contains(null));
        assertTrue(list1.containsAll(List.of("Leentje", "Leerde")));
        assertTrue(list1.containsAll(List.of("Lotje", "Lotje")));
        assertTrue(list1.containsAll(List.of()));
        assertFalse(list1.containsAll(List.of("Lotje", "")));
        for (String w : list1) {
            assertNotEquals("",w);
        }

        assertFalse(list1.retainAll(List.of("Leentje", "Leerde", "Lotje", "Lopen")));
        assertEquals(4, list1.size());
        assertTrue(list1.retainAll(List.of("Leentje", "Lotje", "Lopen")));
        assertEquals(3, list1.size());
        assertTrue(list1.retainAll(List.of("Leentje", "Leerde", "Lopen")));
        assertEquals(2, list1.size());
        assertTrue(list1.retainAll(List.of()));
        assertEquals(0, list1.size());
    }

    @Test
    void t07_lastIndexOfTest() {
        assertEquals(-1, list1.indexOf("Leentje"));
        assertEquals(-1, list1.lastIndexOf("Lopen"));

        assertTrue(list1.addAll(List.of("Leentje", "Leerde", "Lotje", "Lopen", "Lopen")));
        assertEquals(0, list1.indexOf("Leentje"));
        assertEquals(1, list1.indexOf("Leerde"));
        assertEquals(3, list1.indexOf("Lopen"));
        assertEquals(-1, list1.indexOf(""));
        assertEquals(4, list1.lastIndexOf("Lopen"));
    }

    @Test
    void t08_subListTest() {
        assertTrue(list1.addAll(List.of("Leentje", "Leerde", "Lotje", "Lopen")));
        assertTrue(list1.addAll(List.of("Langs", "de", "Lange", "Lindenlaan")));
        assertTrue(list2.addAll(list1.subList(0,3)));
        assertTrue(list2.addAll(list1.subList(3,5)));
        assertEquals(5,list2.size());
        assertFalse(list2.addAll(list1.subList(5,5)));
        assertEquals(5,list2.size());
        assertThrows(IllegalArgumentException.class, () -> { list2.addAll(list1.subList(5,4)); } );

        assertTrue(list2.addAll(list1.subList(5,8)));
        assertEquals(8,list2.size());
    }

    @Test
    void t09_toArrayTest() {
        assertTrue(list1.addAll(List.of("Leentje", "Leerde", "Lotje", "Lopen")));
        String[] array4 = new String[4];
        String[] llllS = list1.toArray(array4);
        Object[] llllO = list1.toArray();
        assertEquals(4, llllS.length);
        assertEquals(4, llllO.length);
        for (int i = 0; i < 4; i++) {
            assertEquals(llllS[i], (String)llllO[i]);
        }
        assertTrue(list2.addAll(List.of(llllS)));
        assertEquals(list1, list2);
    }
}