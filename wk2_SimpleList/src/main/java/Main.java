import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        ASimpleList<String> list = new ASimpleList<>();

        //testing
        list.add("hallo");
        list.add("test");
        list.add("Banaan");
        list.add("Pol");
        list.add("a");
        list.remove(2);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println(list.size());
    }
}