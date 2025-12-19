package com.mycompany.supermarketmanagementsystem;

import java.time.LocalDate;
import java.util.*;

/**
 * Main management class for supermarket operations
 * Uses ArrayList from Java collections Framework as required
 * Implements all core business logic and functionality
 * 
 * This class serves as the central controller and business logic layer of the system,
 * coordinating all supermarket operations including product management, activity tracking,
 * and inventory control.
 * 
 * Key Responsibilities:
 * 1. Product Management - Creation, deletion, and retrieval of products
 * 2. Activity Management - Handling stock addictions and removals
 * 3. Data validation - Ensuring data integrity through input validation
 * 4. System Coordination - Integrating custom algorithms and data structures
 * 
 * Design Patterns : Follows the manager/Controller pattern for business logic encapsulation
 * 
 * @author Group 10
 * @version 1.0
 */
public class SupermarketManager {
    // JCF Data Structure: Arraylist for storing products
    // ArrayList is chosen beacuse: 
    // 1. Provides 0(1) access time for random access
    // 2. Maintains insertion order for predictable interation
    // 3. Easy to use and integrates well with custom algorithms
    // 4. Meets the requirement to use java collections Framework
    private List<Product> products;
    
    /**
     * Constructor initializes the products list
     * Creates an empty ArrayList to store Product objects
     * This represents the main product inventory database
     */
    public SupermarketManager() {
        // Initialize products list as an empty ArrayList
        // Using diamond operator <> for type interface
        this.products = new ArrayList<>(); 
    }
    
    /**
     * FUNCTIONALITY #1: Create a new product and add it to the system 
     * Implements product creation with validation, duplicate checking, and initial activity creation
     * This is the first core functionality required by the coursework
     * 
     * Business Rules:
     * 1. Product ID must be unique
     * 2. Product Name cannot be empty
     * 3. Initial quantity must be non- negative
     * 4. Automatically creates an initial stock activity
     * 
     * @param ProductID Unique product identifier
     * @param ProductName Name of the product
     * @param ProductEntryDate Date when product was added
     * @param initialProductQuantity Starting stock quantity
     * @return true if product created successfully, false otherwise
     */
    public boolean createProduct(String ProductID, String ProductName, LocalDate ProductEntryDate, int initialProductQuantity) {
        // Input validation
        
        // Validate ProductID : Cannot be null or empty
        if (ProductID == null || ProductID.trim().isEmpty()) {
            System.out.println("Error: Product ID cannot be empty!");
            return false; // Early return on validation failure
        }
        
        // Validate Product Name: cannot be null or empty
        if (ProductName == null || ProductName.trim().isEmpty()) {
            System.out.println("Error: Product Name cannot be empty!");
            return false; // Early return on validation failure
        }
        
        // Validate Initial Quantity : must be non-negative
        if (initialProductQuantity < 0) {
            System.out.println("Error: Initial Product Quantity cannot be negative!");
            return false; // Early return on validation failure
        }
        
        // Check for duplicate product ID using custom search algorithm
        // This demonstrates integration of custom algorithms with business logic
        
        if (SearchAlgorithms.linearSearchProduct(products, ProductID) != null) {
        System.out.println("Error: Productwith ID" + ProductID + "already exists!");
        return false; // Early return on duplicate detection
    }
        
        // Create and add new product
        Product product = new Product(ProductID, ProductName, ProductEntryDate, initialProductQuantity);
        
        // Add product to the products list 
        products.add(product);
        
        // Create initial stock activity to record the starting quantity
        // This ensures activities history starts with the initial stock addition
        Activity initialActivity = new Activity(
                generateActivityID(), // Generate unique activity ID
                "AddToStock",  // Activity type for initial quantity
                initialProductQuantity, // Same as initial quantity
                ProductEntryDate  // same as product entry date
        );
        
        // Add initial activity to product's activity history
        product.addActivity(initialActivity);
        
        System.out.println("Product created successfully: " + ProductName);
        return true; // Successfully created product
    }
    
    /**
     * FUNCTIONALITY #2: Display all products with their general details
     * Provides formatted output of all products in the system
     * This is the second core functionality required by the coursework
     * 
     * Display format:
     * - Header with system information
     * - Table format with columns: Product ID, Name, Entry Date, Quantity
     * - Footer with total product count
     * 
     * Note: Uses printf for formatted output with fixed column widths
     */
    public void displayAllProducts() {
        //Check if products list is empty
        if (products.isEmpty()) {
            System.out.println("No products available in the system.");
            return; // Early Return for empty system
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ALL PRODUCTS IN SYSTEM");
        System.out.println("=".repeat(80));
        System.out.printf("%-12s %-20s %-15s %-10s %n", "Product ID", "Product Name", "Product Entry Date", "Product Quantity");
        System.out.println("-".repeat(80));
        
        // Interate through all product using enhanced for loop
        for (Product product : products) {
            // Print each product in formatted table row
            System.out.printf("%-12s %-20s %-15s %-10d%n",
                    product.getProductID(),
                    product.getProductName(),
                    product.getProductEntryDate(),
                    product.getProductQuantity());
        }
        System.out.println("=".repeat(80));
        System.out.println("Total products:" + products.size());
    }
    
    /**
     * FUNCTIONALITY #3: Delete a product from the system using product ID
     * Uses custom linear search algorithm
     * This is the third core functionality required by the coursework
     * 
     * Process:
     * 1. Search for product using linear search algorithm
     * 2. If found, remove from products list
     * 3. Provide success/error feedback to user
     * 
     * @param ProductID ID of the Product to delete
     * @return true if product deleted successfully, false otherwise
     */
    public boolean deleteProduct(String ProductID) {
        // Search for product using custom linear search algorithm
        Product productToDelete = SearchAlgorithms.linearSearchProduct(products, ProductID);
        if (productToDelete != null) {
            // Product found - remove from ArrayList
        products.remove(productToDelete);
        
        // Confirm deletion with product name for user feedback
        System.out.println("Product deleted successfully:" + productToDelete.getProductName());
        return true; // Successful deletion
    } else {
            // Product not found - provide error message
            System.out.println("Error: Product with ID '" + ProductID + "' not found!");
            return false; // Failed deletion
            }
    }
    
    /**
     * FUNCTIONALITY #4: Update system with new activities
     * Handles both AddToStock and RemoveFromStock activities with validation
     * This is the fourth core functionality required by the coursework
     * 
     * Business Rules:
     * 1. Activity quantity must be non-negative
     * 2. Product must exist in system
     * 3. For RemoveFromStock, must have sufficient stock available
     * 4. Updates product quantity and maintains activity history
     * 
     * @param ProductID ID of the product to update
     * @param activityName Type of activity (AddToStock/RemoveFromStock)
     * @param ProductQuantity Number of items to add/remove
     * @param activityDate Date of the activity
     * @return true if activity added successfully, false otherwise
     */
    public boolean addActivityToProduct(String ProductID, String activityName, int  ProductQuantity, LocalDate activityDate) {
        // Input validation
        
        // Validate activity quantity : cannot be negative
        if (ProductQuantity < 0) {
            System.out.println("Error: Activity Product Quantity cannot be negative!");
            return false; // Early return on validation failure
        }
        
        // find product using custom linear search algorithm
        Product product = SearchAlgorithms.linearSearchProduct(products, ProductID);
        
        // ckeck if product exists
        if (product == null) {
            System.out.println("Error: Product with ID '" + ProductID + "'not found!");
            return false; // Early return if product not found
        }
        
        // Additional validation for RemoveFromStock
        if ("RemoveFromStock".equals(activityName) && product.getProductQuantity() < ProductQuantity) {
            System.out.println("Error: Insufficient stock! Available:" + product.getProductQuantity() + ", Requested:" + ProductQuantity);
            return false; // Early return if insufficient stock
        }
        
        // Create and add activity
        Activity activity = new Activity(
                generateActivityID(), // Generate unique activity ID
                activityName, // Activity type
                ProductQuantity, //  Quantity to add/remove
                activityDate // Date of activity
        );
        
        // Add activity to product
        product.addActivity(activity);
        
        System.out.println("Activity added successfully:" + activityName + " " + ProductQuantity + "items");
        System.out.println("Updated quantity for " + product.getProductName() + " : " + product.getProductQuantity());
        return true; // Successfully added activity
    }
    
    /**
     * FUNCTIONALITY #5: Display product's last four activities sorted by quantity
     * Uses custom bubble sort algorithm
     * This is the fifth core functionality required 
     * 
     * Features:
     * - Shows only last 4 activities 
     * -Sorts activities by quantity using custom bubble sort
     * - Displays in formatted table with current stock information
     * 
     * @param ProductID ID of the product to display activities for
     */
    public void displayProductActivities(String ProductID) {
    // Search for product using custom linear search algorithm
        Product product = SearchAlgorithms.linearSearchProduct(products, ProductID);
        
        // Check if product exists
        if (product == null) {
            System.out.println(ProductID + "Error: Product with ID " + " not found!");
            return; // Early return if product not found
        }
        
        // Get sorted activities from product
        List<Activity> sortedActivities =
                product.getActivitiesSortedByProductQuantity();
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ACTIVITIES FOR PRODUCT: " + product.getProductName().toUpperCase());
        System.out.println("Current Stock:" + product.getProductQuantity());
        System.out.println("=".repeat(70));
        
        // check if activities exist for this product
        if (sortedActivities.isEmpty()) {
            System.out.println("No activities found for this product.");
        } else {
            // Show number of activities
            System.out.println("Last " + sortedActivities.size() + " activities sorted by activityProductQuantity:");
            // Column headers with fixed widths for alignment
            System.out.printf("%-10s %-15s %-10s %-12s%n", "activityID", "activityType", "activityProductQuantity", "activityDate");
            System.out.println("-".repeat(70));
            
            
            // Iterate through sorted activities
            for (Activity activity : sortedActivities) {
                
                // Print each activity in formatted table row
                // Note: Truncates activity ID for display purposes
                System.out.printf("%-10s %-15s %-10d %-12s%n",
                        activity.getActivityID().substring(0,8) + "...",
                        activity.getActivityName(),
                        activity.getActivityProductQuantity(),
                        activity.getActivityDate());
            }
    }
    System.out.println("=".repeat(70));
    }
    /**
     * Generates unique activity IDs using timestamp and random number
     * Ensures each activity has a unique identifier for tracking
     * 
     * Format: "ACT" + timestamp + "_" + random number
     * Example: "ACT1674829401234_456"
     * 
     * @return Unique activity ID string
     */
    private String generateActivityID() {
        // Generate ID using:
        // 1. Prefix "ACT" to identify as activity ID
        // 2. Current time in milliseconds for uniqueness
        // 3. Random number 0-999 for additional uniqueness
        return "ACT" + System.currentTimeMillis() + "_" + new Random().nextInt(1000); 
    }
    // Additional utility methods
    
    /**
     * Retrieves a product by ID using custom linear search algorithm
     * Helper method for external access to specific products
     * 
     * @param ProductID of the product to retrieve
     * @return Product object if found, null if not found
     */
    public Product getProduct(String ProductID) {
        return SearchAlgorithms.linearSearchProduct(products, ProductID);
    }
    
    /**
     * Returns a copy of all products in the system
     * Returns a defensive copy to prevent external modification of internal list
     * 
     * @return New ArrayList containing all products 
     */
    public List<Product> getAllProducts() {
        // return defensive copy to prevent external modification
      return new ArrayList<>(products);
    }
    
    /**
     * Returns the current number of products in the system
     * Useful for displaying system statistics
     * 
     * @return Number of products in the produsts list 
     */

    public int getProductCount() {
       return products.size();
    } 
}
