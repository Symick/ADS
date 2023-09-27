package models;

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

        // TODO convert the information in line to a new Product instance

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

    // TODO provide additional helper methods

    @Override
    public String toString() {
        // TODO represent the product in the format: title, price, stock
        return null;
    }
}
