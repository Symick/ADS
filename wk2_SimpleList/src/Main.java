import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ASimpleList<String> list = new ASimpleList<>();

        //testing
        list.add("hallo");
        list.add("test");
        System.out.println(list.size());
        System.out.println(list.get(1));
        list.add("Banaan");
        System.out.println(list.size());
        list.add("Pol");
        list.add("a");
        System.out.println(list.size());
    }
}