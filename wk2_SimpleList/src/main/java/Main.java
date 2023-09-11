import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> ints = new LinkedList<>();
        ints.add(1);
        DESLSimpleList<Integer> list = new DESLSimpleList<>();
        list.add(10);
        list.add(12);
        list.add(123);
        list.add(11);
        list.add(3);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        Integer removed = list.remove(2);
        System.out.println(removed);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}