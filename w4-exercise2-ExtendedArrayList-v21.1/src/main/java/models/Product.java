package models;

import java.util.IllegalFormatException;
import java.util.Locale;

public class Product {
    private String title;
    private double price;
    private int stock;

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
        this.stock = 0;
    }
    public Product(String title, double price, int stock) {
        this(title, price);
        this.stock = stock;
    }

    /**
     * parses product information from a textLine
     * @param textLine
     * @return  a new Product instance with the provided information
     *          or null if the textLine is corrupt or incomplete
     */
    public static Product fromLine(String textLine) {
        Product newProduct = null;

        if (textLine == null) return null;

        String[] productInfo = textLine.split(",");
        //string should contain three pieces of information
        if (productInfo.length != 3) {
            throw new IllegalStateException();
        }
        try {

            String title = productInfo[0].trim();
            double price = Double.parseDouble(productInfo[1].trim());
            int stock = Integer.parseInt(productInfo[2].trim());
            newProduct = new Product(title, price, stock);

        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
        }

        return newProduct;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        // TODO represent the product in the format: title, price, stock
        return String.format("%s/%.2f/%d", title, price, stock);
    }
}
