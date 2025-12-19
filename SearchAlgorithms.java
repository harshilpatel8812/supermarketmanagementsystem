package com.mycompany.supermarketmanagementsystem;

import java.util.List;
import java.util.ArrayList;

/**
 * Custom search algorithms implementation
 * Provides linear search implementation as required by coursework specifications
 * This class demonstrates understanding of search algorithms and their practical application
 * in a supermarket inventory management context
 * 
 * Key Implementations:
 * 1. Linear Search - Primary algorithm used for product lookup (Required functionality)
 * 2. Binary Search - Additional demonstration of alternative search algorithm knowledge
 * 
 * @author Group 10
 * @version 1.0
 */
public class SearchAlgorithms {
     
    /**
     * Linear search implementation to find product by ID
     * This satisfies the custom searching algorithm requirement
     * Linear search is chosen because:
     * 1. Product lists are typically small in this supermarket system
     * 2. Products may not be sorted by ID
     * 3. Simple implementation meets functional requirements
     * 
     * Algorithm steps:
     * 1. Validate input parameters
     * 2. Iterate through each product in the list
     * 3. Compare each product's ID with the target ID
     * 4. Return product if found, null if not found
     * 
     * Time Complexity: 0(n) where n is the number of products
     * Space Complexity: 0(1) - no additional data structures used
     * 
     * @param products List of Products to search through
     * @param ProductID target Product ID to find
     * @return Product if found, null otherwise
     */
    public static Product linearSearchProduct(List<Product> products, String
            ProductID) {
        // Input validation - defensive programming approach
        // Check for null products list, null ProductID, or Empty/Ehitespace - only ID
        
        if (products == null || ProductID == null || ProductID.trim().isEmpty()) {
            return null; // Early return for invalid input
        }
        
        // Linear search implementation
        for (Product product : products) {
            // Compare current product's ID with target ID using equals() for exact match
            // This uses string equality comparison
            if (product.getProductID().equals(ProductID)) {
                return product; // Found match - return product immediately
            }
        }
        
        // If loop completes without finding match. product doesn't exist in list
        return null;
    }
    
    /**
     * Alternative binary search implementation (not used in main functionality)
     * Demonstrates knowledge of multiple search algorithms
     * Binary search is more efficient but requires sorted data
     * 
     * Important notes:
     * - This implementation requires copying and sorting the product list first
     * - The sorting step adds 0(n log n) time complexity before search
     * - Binary search itself has 0(log n) time complexity
     * -Used here primarily to demonstrate algorithm knowledge
     * 
     * Algorithm steps:
     * 1. Validate input parameters
     * 2. Create sorted copy of product list
     * 3. Implement binary search on sorted list
     * 4. Return product if found, null is not found
     * 
     * @param products List of products to search through 
     * @param productID target Product ID to find
     * @return Product if found with matching ID, null if not found or invalid input
     */
    public static Product binarySearchProduct(List<Product> products, String 
            productID) {
        // Input validation - same as linear search for consistency
        if (products == null || productID == null) { 
            return null; // Early return for invalid input
        }
        
        // Binary search requires sorted data
        // First create a copy to avoid modifying original list
        List<Product> sortedProducts = new java.util.ArrayList<>(products);
        
        // Sort the copy by product ID in ascending order using lambda comparat or
        // Comparator compares two products based on their ID strings
        sortedProducts.sort((p1, p2) -> p1.getProductID().compareTo(p2.getProductID()));
        
        // Binary search algorithm implementation
        int left = 0; // Left boundary of search range
        int right = sortedProducts.size() - 1;  // Right boundary of search range
        
        // Continue searching while search range is valid 
        while (left <= right) {
            // Calculate middle index - prevents integer overflow compared to(left+right)/2
            int mid = left + (right - left) / 2;
            
            // Get product at middle position
            Product midproduct = sortedProducts.get(mid);
            
            // Compare middle product's ID with target ID
            int comparison = midproduct.getProductID().compareTo(productID);
            
            // Check comparison result
            if (comparison == 0) {
                // Exact match found - return the product
                return midproduct;
            } else if (comparison < 0) {
                // Target ID is greater than middle ID
                // Search in the left half of the currnt range
                left = mid +1;
            } else {
                // Tarfet ID is less than middlke ID 
                // Search in the left half of the current range
                right = mid - 1;
            } 
        }
        
        // If loop exits without returning, product was not found
            return null;
    }
    
    // Note: Potential enhancements for future development
    // 1. Add case-insensitive search option for product IDs
    // 2. Implement search by product name with partial matching
    // 3. Add search by quantity range
    // 4. Implement recuesive binary search for educational comparison
    // 5. Add method to return all products matching a search pattern
    // 6. Consider using java's Collections.binarySearch() for production use
    
} 

 