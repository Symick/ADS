import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
class ListPerformanceTest {

    private final int TEST_COUNT = 10000;

    private List<String> list1;
    private List<String> list2;

    @BeforeEach
    void setup() {
//        list1 = new ArrayList<>(); list2 = new ArrayList<>();
//        list1 = new Vector<>(); list2 = new Vector<>();
//        list1 = new LinkedList<>(); list2 = new LinkedList<>();
        list1 = new models.DELinkedList<>(); list2 = new models.DELinkedList<>();
    }

    @Test
    void p01_appendPerfomanceTest() {
        for (int i = 0; i < 10*TEST_COUNT; i++) {
            list1.add("kjhkhjkhj" + i);
        }
    }

    @Test
    void p02_prependPerfomanceTest() {
        for (int i = 0; i < 10*TEST_COUNT; i++) {
            list1.add(0, "kjhkhjkhj" + i);
        }
    }

    @Test
    void p03_FIFOPerfomanceTest() {
        for (int i = 0; i < 2*TEST_COUNT; i++) {
            list1.add("aaaaa" + i);
            list1.add("bbbbb" + i);
            list1.add("ccccc" + i);
            list1.remove(0);
        }
        for (int i = 0; i < 2*TEST_COUNT; i++) {
            list1.add("ddddd" + i);
            list1.remove(0);
            list1.remove(0);
            list1.remove(0);
        }
        assertTrue(list1.isEmpty());
    }

    @Test
    void p04_randomAddRemovePerformanceTest() {
        for (int i = 0; i < TEST_COUNT; i++) {
            int idx;
            idx = (int)(list1.size() * Math.random());
            list1.add(idx, "kjhkhjkhj" + i);
            idx = (int)(list1.size() * Math.random());
            list1.add(idx, "aaaaa" + i);
            idx = (int)(list1.size() * Math.random());
            list1.remove(idx);
        }
    }

    @Test
    void p05_randomGetSetPerformanceTest() {
        for (int i = 0; i < TEST_COUNT; i++) {
            int idx;
            list1.add("kjhkhjkhj" + i);
            idx = (int)(list1.size() * Math.random());
            list1.get(idx);
            idx = (int)(list1.size() * Math.random());
            list1.set(idx, "jghsdjjgds" + i);
        }
    }
}