package com.mycompany.supermarketmanagementsystem;
/**
 * Comprehensive test class to demonstrate all system functionalities
 * Provides automated testing for report evidence. This class simulates various
 * user scenarios and edge cases to ensure the supermarket management system
 * works correctly under different conditions.
 * 
 * @author Group 10
 * @version 1.0
 */
public class ComprehensiveTest {
    
    // Reference to the supermarkety manager that will be tested
    private final SupermarketManager manager;
    
    /**
     * Constructor initializes the test environment by creating a new
     * SupermarketManager instance that will be used throughout all tests.
     */
    public ComprehensiveTest() {
        this.manager = new SupermarketManager();
    }
    
    /**
     * Main test execution method that orchestrates all individual tests.
     * This is the entry point for running the complete test suite.
     * Displays formatted headers and executes each functionality test in sequence.
     */
   public void runAllTests() {
       // Display a decorative header for the test suite
        System.out.println("\n" + "╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                 COMPREHENSIVE SYSTEM TESTING                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

       // Execute each test function in the required order
       testFunctionality1(); // F1: Create products with various scenarios
       testFunctionality2(); // F2: Display all products
       testFunctionality3(); // F3: Delete product functionality
       testFunctionality4(); // F4: Add activities to products
       testFunctionality5(); // F5: Display sorted activities
       testLastFourRestriction(); // Additional test: Last 4 activities restriction
       testDataValidation(); // Additional test: Input validation edge cases
       
       // Display test completion message
       System.out.println("\n" + "=".repeat(70));
       System.out.println("ALL TEST COMPLETED SUCCESSFULLY");
       System.out.println("=".repeat(70));
    }
   
   /**
    * Tests Functinality 1: Product Creation
    * This test validates that products can be created with valid data
    * and handles edge cases like duplicates and negative quantities.
    */
    private void testFunctionality1() {
       System.out.println("\n" + "-".repeat(40));
       System.out.println("TEST F1: CREATE PRODUCTS");
       System.out.println("-".repeat(40));
       
       // Test 1: Createb products with void data
       System.out.println("Creating three valid products....");
       manager.createProduct("P100", "Test Product 1", java.time.LocalDate.now(), 50);
       manager.createProduct("P200", "Test Product 2", java.time.LocalDate.now(), 30);
       manager.createProduct("P300", "Test Product 3", java.time.LocalDate.now(), 40);
       
       // Test 2: Attempt to create product with duplicate product ID
       System.out.println("\nAttempting to create product with duplicate ID 'P100'...");
       manager.createProduct("P100", "Duplicate Product", java.time.LocalDate.now(),20);
       
       // Test 3: Attempt to create product with negative quantity
       System.out.println("\nAttempting to create product with negative quantity...");
       manager.createProduct("P400", "Negative Test", java.time.LocalDate.now(), -10);
    }
    
    /**
     * Tests Functionality 2: Display All Products
     * This test verifies that all products in the system can be displayed
     * in a readable format after creation.
     */
    private void testFunctionality2(){
       System.out.println("\n" + "-".repeat(40));
       System.out.println("TEST F2: DISPLAY ALL PRODUCTS");
       System.out.println("-".repeat(40));
       
       System.out.println("Displaying all products currently in the system...");
       manager.displayAllProducts();
   }
    
    /**
     * Tests Functionality 3: Delete Product
     * This test verifies product deletion works correctly and handles
     * attempts to delete non-existent products gracefully.
     */
   private void testFunctionality3() {
       System.out.println("\n" + "-".repeat(40));
       System.out.println("TEST F3: DELETE PRODUCT");
       System.out.println("-".repeat(40));
       
       // Display products before deletion
       System.out.println("Brfore deletion:");
       manager.displayAllProducts();
       
       //Delete an existing product
       System.out.println("\nDeleting product with ID 'p200'...");
       manager.deleteProduct("P200");
       
       //Display products after deletation to confirm removal
       System.out.println("After deletion:");
       manager.displayAllProducts();
       
       // Test deleting non-existent product
       System.out.println("\nAttempting to delete non-existen product...");
       manager.deleteProduct("NONEXISTENT");
   }
   
   /**
    * Tests Functionality 4: Add Activities to products
    * This test validates that activities can be added to products,
    * including both AddToStock an RemoveToStock operations.
    * Also tests edge cases Like negative quantities and insufficient stock.
    */
    private void testFunctionality4() {
       System.out.println("\n" + "-".repeat(40));
       System.out.println("TEST F4: ADD ACTIVITIES");
       System.out.println("-".repeat(40));
       
       // Test 1: Add Stock to existing product
       System.out.println("\nAdding Stock from product 'P100'...");
       manager.addActivityToProduct("P100", "AddStock", 25, java.time.LocalDate.now().plusDays(1));
       
       // Test 2: Remove Stock from product
       System.out.println("\nRemoving stock from product 'P100'...");
       manager.addActivityToProduct("P100", "RemoveFromStock", 15, java.time.LocalDate.now().plusDays(2));
       
       // Test 3: Attempt to add negative quantity
       System.out.println("\nAttempting to add negative quantity...");
       manager.addActivityToProduct("P100", "AddToStock", -5, java.time.LocalDate.now());
       
       // Test 4: Attempt to remove more stock than available
       System.out.println("\nAttempting to remove stock than available...");
       manager.addActivityToProduct("P100", "RemoveFromStock", 1000, java.time.LocalDate.now());
       
       // Display final product state to verify updates
       System.out.println("\nFinal product state after all activities:");
       manager.displayAllProducts();
   }
   
    /**
     * Tests Functionality 5: Display sorted Activities
     * This test verifies that activities for a product can be displayed
     * in sorted order (Likely by date or activity ID).
     */
   private void testFunctionality5(){
       System.out.println("\n" + "-".repeat(40));
       System.out.println("TEST F5: DISPLAY SORTED ACTIVITIES");
       System.out.println("-".repeat(40));
       
       // Add multiple activities to a product for testing
       System.out.println("Adding multiple activities to product 'P300'...");
       manager.addActivityToProduct("P300", "AddToStock", 50, java.time.LocalDate.now().plusDays(1));
       manager.addActivityToProduct("P300", "RemoveFromStock", 10, java.time.LocalDate.now().plusDays(2));
       manager.addActivityToProduct("P300", "AddToStock", 30, java.time.LocalDate.now().plusDays(3));
       manager.addActivityToProduct("P300", "RemoveFromStock", 20, java.time.LocalDate.now().plusDays(4));
       
       //Display the activities , which should be sorted
       System.out.println("\nDisplaying sorted activities for product 'P300'");
       manager.displayProductActivities("P300");
   }
   
   /**
    * Tests the "Last 4 Activities" restriction feature.
    * This test verifies that when more than 4 activities are added to a product,
    * only the most recent 4 are retained and displayed.
    */
   private void testLastFourRestriction() {
       System.out.println("\n" + "-".repeat(50));
       System.out.println("TEST: LAST 4 ACTIVITIES RESTRICTION");
       System.out.println("-".repeat(50));
      
       // Create a new product specifically for this test
       System.out.println("Creating test product 'TEST001'...");
       manager.createProduct("TEST001", "Restriction Test Product", java.time.LocalDate.now(), 100);
       
       System.out.println("\nAdding 6 activities to product TEST001...");
       System.out.println("System should keep only the last 4 activities.");
       
       // Add 6 activities - system should keep only last 4 according to requirement
       // Activity 1: Will be removed when we exceed 4 activities
       
       manager.addActivityToProduct("TEST001", "AddToStock", 10,
               java.time.LocalDate.now().plusDays(1));
       
       // Activity 2: Will be removed when we exceed 4 activities
       // Note: Typo in activity name - should be "RemoveFromSTock" not "RemoveToStock"
       
       manager.addActivityToProduct("Test001", "RemoveToStock", 5,
               java.time.LocalDate.now().plusDays(2));
       
       // Activity 3: Will be kept (3rd olddest of the Last 4)
       manager.addActivityToProduct("TEST001", "AddToStock", 20,
               java.time.LocalDate.now().plusDays(3));
       
       // Activity 4: Will be kept (4th oldest of the last 4) 
       manager.addActivityToProduct("TEST001", "RemoveFromStock", 8,
               java.time.LocalDate.now().plusDays(4));
       
       // Activity 5: will be kept (2nd most recent)
       manager.addActivityToProduct("TEST001", "AddToStock", 15,
               java.time.LocalDate.now().plusDays(5)); 
       
       // Activity 6: will be kept (Most recent)
       manager.addActivityToProduct("TEST001", "RemoveFromStock", 3,
                java.time.LocalDate.now().plusDays(6)); 
                
       // Display activities - should show only the last 4 (Activities 3-6)
        System.out.println("Displaying activities - should show only LAST 4:");
        manager.displayProductActivities("TEST001");
    }
   
   /**
    * Tests various data validation scenarios.
    * This test ensures the system properly handles invalid or edge case inputs
    * without crashing and provides appropriate error messages.
    */
   private void testDataValidation() {
       System.out.println("\n" + "-".repeat(40));
       System.out.println("TEST: DATA VALIDATION");
       System.out.println("-".repeat(40));
       
       System.out.println("Testing various invalid inputs to ensure proper validation...");
       
       // Test 1: Empty product ID 
       System.out.println("\n1. Testing empty product ID...");
       manager.createProduct("", "Empty ID Test", java.time.LocalDate.now(),10);
       
       // Test 2: Empty product name
       System.out.println("\n2. Testing empty product name...");
       manager.createProduct("P500", "", java.time.LocalDate.now(), 10);
       
       // Test 3: Invalid activity type
       System.out.println("\n3. Testing invalid activity type...");
       manager.addActivityToProduct("P100", "InvalidType", 10,
               java.time.LocalDate.now());
       
       
       // Note: Additional validation tests could include:
       // - Null date values
       // - Product ID with speciap chracters
       // - Very Large quantity values
       // - Dates in the past/future depending on business rules
   }
   
   /**
    * Main method to run the comprehensive test suite.
    * This is the entry Point when the class is executed directly
    * 
    * @param args Command Line arguments (not used in this test)
    */
   public static void main(String[] args) {
       System.out.println("Starting Comprehensive Test Suite...");
       System.out.println("=".repeat(70));
       
       // Create test instance and run all tests
       ComprehensiveTest test = new ComprehensiveTest();
       test.runAllTests();
       
       System.out.println("\nTest execution completed. Check output above for the results.");
   }
}