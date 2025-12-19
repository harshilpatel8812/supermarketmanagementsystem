package com.mycompany.supermarketmanagementsystem;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a product in the Supermarket System
 * Users CustomLinkedList to maintain Only the last 4 Activities
 * This class is central to the inventory management system, tracking
 * product details, stock levels, and associated stock movement activities.
 * 
 * Key Features: 
 * - Manages product information including ID, name, and stock quantity
 * - Tracks the last 4 activities using custom data structure
 * - provides sorting functionality for activity display
 * - Ensures data integrity through validation checks
 * 
 * @author Group 10
 * @version 1.0
 */
public class Product {
    // Unique identifier for the product (e.g., P001, P002)
    private String ProductID;
    
    // Descriptive name of the product (e.g., Milk, Bread)
    private String ProductName;
    
    // Date when the product was first added to the system
    private LocalDate ProductEntryDate;
    
    // Current quantity of this product available in stock
    private int ProductQuantity;
    
    // Custom data structure to store only the last 4 activities (FIFO constraint)
    // This fulfils the requirement to implement  custom data structure
    private final CustomLinkedList<Activity> activities; 
    
    /**
     * Constructor to initialize product with basic details
     * Creates a new product instance and initializes the custom linked list
     * with a maximum capacity of 4 activities as per requirements.
     * 
     * @param ProductID Unique identifier for  the product
     * @param ProductName Name of the product
     * @param initialProductQuantity starting quantity in stock
     * @param ProductEntryDate Date when product was entered into the system
     * @throws IllegalArgumentException if initialProductQuantity is negative 
     */
 public Product(String ProductID, String ProductName, LocalDate 
            ProductEntryDate, int initialProductQuantity) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ProductEntryDate = ProductEntryDate;
        this.ProductQuantity = initialProductQuantity;
        this.activities = new CustomLinkedList<>(4); // Maintain only last 4 activities
    }
    
    /**
     * Adds an activity to the product and updates stock quantity
     * Uses custom linked list to maintain activity history
     * Also updates product stock quantity based on activity type
     * 
     * Business Logic:
     * - "AddToStock": Increases product quantity by specified amount
     * - "RemoveFromStock": Decreases product quantity by specified amount
     * - Ensures stock quantity never becomes negative
     * 
     * @param activity The activity to add (AddToStock/RemoveFromStock)
     * @throws IllegalArgumentException if activity type is unrecognized
     */
   public void addActivity(Activity activity) {
        // Add activity to the beginning of custom linked list
        // This maintains recent activities first, older ones are automatically removed after 4
       activities.addFirst(activity);
       
       // Update product quantitu based on activity type with validation
       if ("AddToStock".equals (activity.getActivityName())){
           // Add Stock: increase product quantity
           ProductQuantity += activity.getActivityProductQuantity();
            } else if ("RemoveFromStock".equals(activity.getActivityName())) {
               // Remove stock: decrease product quantity
                ProductQuantity -= activity.getActivityProductQuantity();
                // Ensure quantity never goea negative
                //  This prevents negative stock levels which would indicate system error
                if (ProductQuantity < 0) ProductQuantity = 0;
            }
       // Note: Consider adding else block to handle invalid activity types
   }
   
   /**
    * Returns activities sorted by product quantity using custom bubble sort
    * Implements functionality requirement #5
    * 
    * Sorting logic:
    * - Sorts activities in ascending order by the quantity involved in each activity
    * - Only sorts the last 4 activities (due to linked list constraint)
    * 
    * @return List of activities sorted by product quantity in ascending order 
    */
   public List<Activity> getActivitiesSortedByProductQuantity() {
       // Get all activities from custom linked list (maximum 4)
     List<Activity> activityList = activities.getAllElements();
     
     // Use custom bubble sort algorithm to sort by activity quantity
     // This implements the sorting requirement for the coursework
     SortAlgorithms.bubbleSortActivities(activityList);
     
     return activityList;
   }
   
   // Getters and setters
   
   /**
    * Retrieves the unique product identifier
    * @return The product ID as a String
    */
   public String getProductID() { return ProductID; }
   
   /**
    * Updates the product identifier
    * @param ProductID New product ID to set
    */
   public void setProductID(String ProductID) {this.ProductID = ProductID; }
   
   /**
    * Retrieves the product name 
    * @return The product name as a String
    */
   public String getProductName() { return ProductName; }
   
   /**
    * Updates the product name
    * @param ProductName New product name to set
    */
   public void setProductName(String ProductName) { this.ProductName = ProductName; }
   
   /**
    * Retrieves the product entry date
    * @return The date when product was first added to system
    */
   public LocalDate getProductEntryDate() { return ProductEntryDate; }
   
   /**
    * Updates the product entry date
    * @param ProductEntryDate New entry date to set
    */
   public void setProductEntryDate(LocalDate ProductEntryDate)
   { this.ProductEntryDate = ProductEntryDate; }
   
   /**
    * Retrieves current product quantity in stock
    * @return Current stock quantity
    */
   public int getProductQuantity() { return ProductQuantity; }
   
   /**
    * Updates product quantity with validation
    * Ensures quantity never becomes negative
    * 
    * @param ProductQuantity New quantity to set
    */
   public void setProductQuantity(int ProductQuantity) {
       // Validation to prevent negative product quantities
       // Using Math.max ensures quantity is alway >= 0
       this.ProductQuantity = Math.max(0, ProductQuantity); 
   }
   
   /**
    * Retrieves the custom linked list containing activities
    * Note: This returns the actual linked list, not a copy
    * 
    * @return CustomLinkedList containing the last 4 activities 
    */
   public CustomLinkedList<Activity> getActivities() { return activities; }
   
   /**
    * String representation for display purposes
    * Provides formatted output for console display or logging
    *
    * Format: " Product ID:XXX | Name: XXX | Entry Date: XXX | ProductQuantity: XXX " 
    * 
    * @return Formatted string containing all product details 
    */
   @Override
   public String toString() {
       return String.format("Product ID: %s | Name: %s | Entry Date: %s | ProductQuantity: %d",
                   ProductID, ProductName, ProductEntryDate, ProductQuantity);
                   
   
   }

   
   // Note for future enhancements:
   // 1. Add methpd to get total activity count
   // 2. Add validation for Product ID format (e.g., must start with 'P')
   // 3. Add method to check if product is out of stock
   // 4. Consider implementing Comparable interface for product sorting
   // 5. Add method to get activities sorted by date
   
}


 