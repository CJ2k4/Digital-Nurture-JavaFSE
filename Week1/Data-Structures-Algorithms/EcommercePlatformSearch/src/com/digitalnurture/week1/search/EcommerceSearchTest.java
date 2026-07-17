package com.digitalnurture.week1.search;

import java.util.Arrays;

public class EcommerceSearchTest {

    public static void main(String[] args) {
        Product[] products = {
                new Product(101, "Laptop", "Electronics"),
                new Product(102, "Headphones", "Electronics"),
                new Product(103, "Coffee Maker", "Kitchen"),
                new Product(104, "Running Shoes", "Sportswear"),
                new Product(105, "Backpack", "Accessories"),
                new Product(106, "Monitor", "Electronics"),
                new Product(107, "Desk Lamp", "Furniture"),
                new Product(108, "Yoga Mat", "Sportswear")
        };

        SearchEngine engine = new SearchEngine();

        System.out.println("=== Linear search (unsorted array) ===");
        search(engine, products, "Yoga Mat", false);
        search(engine, products, "Laptop", false);
        search(engine, products, "Guitar", false);

        Product[] sorted = Arrays.copyOf(products, products.length);
        Arrays.sort(sorted);

        System.out.println("\n=== Sorted array (required for binary search) ===");
        for (Product product : sorted) {
            System.out.println("  " + product.getProductName());
        }

        System.out.println("\n=== Binary search (sorted array) ===");
        search(engine, sorted, "Yoga Mat", true);
        search(engine, sorted, "Laptop", true);
        search(engine, sorted, "Guitar", true);

        System.out.println("\n=== Comparison count: worst case (last element) ===");
        engine.linearSearch(products, "Yoga Mat");
        int linear = engine.getLastComparisonCount();
        engine.binarySearch(sorted, "Yoga Mat");
        int binary = engine.getLastComparisonCount();
        System.out.printf("n = %d%n", products.length);
        System.out.printf("Linear search comparisons: %d   -> O(n)%n", linear);
        System.out.printf("Binary search comparisons: %d   -> O(log n)%n", binary);
    }

    private static void search(SearchEngine engine, Product[] products, String name, boolean binary) {
        Product found = binary ? engine.binarySearch(products, name) : engine.linearSearch(products, name);
        String result = found == null ? "not found" : found.toString();
        System.out.printf("Search '%s' -> %s (%d comparisons)%n", name, result, engine.getLastComparisonCount());
    }
}
