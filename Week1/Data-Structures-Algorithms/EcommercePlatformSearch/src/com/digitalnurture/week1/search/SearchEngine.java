package com.digitalnurture.week1.search;

public class SearchEngine {

    private int lastComparisonCount;

    public Product linearSearch(Product[] products, String productName) {
        lastComparisonCount = 0;
        for (Product product : products) {
            lastComparisonCount++;
            if (product.getProductName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    public Product binarySearch(Product[] sortedProducts, String productName) {
        lastComparisonCount = 0;
        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            lastComparisonCount++;
            int comparison = sortedProducts[mid].getProductName().compareToIgnoreCase(productName);

            if (comparison == 0) {
                return sortedProducts[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public int getLastComparisonCount() {
        return lastComparisonCount;
    }
}
