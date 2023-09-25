import models.DELinkedList;

import java.util.List;

public class DELinkedListMain {
    public static void main(String[] args) {
        List<String> list = new DELinkedList<>();

        list.add("Leentje");
        list.addAll(List.of("Langs", "de", "Lange", "Lindelaan"));
        list.add(1,"Leerde");
        list.addAll(2, List.of("Lotje", "Lopen"));
        System.out.println("list has " + list.size() + " words.");
        System.out.println(list);
        for( String w : list) {
            System.out.print(w + " ");
        }
        System.out.println();

        System.out.println("Word[2]=" + list.get(2));
        System.out.println("Lopen is at position " + list.indexOf("Lopen"));

        list.remove("Lopen");
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
        list.remove("Over");
        System.out.println(list);
        list.remove("Leerde");
        System.out.println(list);
        list.remove(0);
        System.out.println(list);
        list.remove("Lange");
        System.out.println(list);
        System.out.println(list.size());
    }
}
