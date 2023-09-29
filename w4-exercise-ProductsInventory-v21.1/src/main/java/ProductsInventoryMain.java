import models.Product;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class ProductsInventoryMain {

    public static void main(String[] args) {
        System.out.println("Welcome to the HvA Products Inventory");

        InputStream fileStream = ProductsInventoryMain.class.getResourceAsStream("/products.txt");
        assert fileStream != null;
        Scanner scanner = new Scanner(fileStream);

        List<Product> products = new ArrayList<>();
        // load all products from the text file
        importFromScanner(scanner, products, Product::fromLine);

        System.out.printf("\nImported products:\n%s\n", products);

        // TODO sort all products alfabetically by title
        //  using List.sort and a Product instance helper method
        products.sort(Product::compareTitle);

        System.out.printf("\nProducts by title:\n%s\n", products);

        // TODO sort all products by increasing price
        //  using List.sort and a Product instance helper method
        products.sort(Product::comparePrice);

        System.out.printf("\nProducts by increasing price:\n%s\n", products);

        // TODO sort all products by decreasing price
        //  using List.sort and Comparator.comparingDouble
        products.sort(Comparator.comparingDouble(Product::getPrice).reversed());

        System.out.printf("\nProducts by decreasing price:\n%s\n", products);

        // TODO sort all products by increasing inventory value ( = stock * price)
        //  using List.sort and a Product instance helper method

        products.sort(Product::compareValue);

        System.out.printf("\nProducts by increasing inventory value:\n%s\n", products);

        // TODO reduce stocks of all products by half
        //  using .forEach and a lambda expression

        products.forEach((product -> product.setStock(product.getStock() / 2)));

        System.out.printf("\nProducts inventory reduced by half:\n%s\n", products);

        // TODO reduce stocks of all products by 10 items
        //  using .forEach and a lambda expression

        products.forEach(product -> product.setStock(product.getStock() - 10));

        System.out.printf("\nProducts inventory reduced by 10 items:\n%s\n", products);

        // TODO remove all products with a zero or negative stock
        //  using .removeIf and a lambda expression
        products.removeIf(product -> product.getStock() <= 0);

        System.out.printf("\nProducts with positive inventory:\n%s\n", products);

        // TODO sort all products by decreasing inventory value ( = stock * price)
        //  using List.sort and a lambda expression
        products.sort((product1, product2) -> {
            return Double.compare((product1.getPrice() * product1.getStock()), (product2.getPrice() * product2.getStock()));
        });

        System.out.printf("\nProducts by increasing inventory value:\n%s\n", products);
    }

    public static <E> void importFromScanner(Scanner scanner, List<E> items,
                                            Function<String,E> converter) {
        while (scanner.hasNext()) {
            // input another line with author information
            String line = scanner.nextLine();
            try {
                E item = converter.apply(line);
                items.add(item);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
