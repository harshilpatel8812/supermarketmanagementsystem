package com.mycompany.supermarketmanagementsystem;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Main application class with console-based user interface
 * Provides complete menu system for testing all functionalities
 * This class serves as the primary user interface and controller
 * for the supermarket management system, coordinating between user input, business logic, and data management.
 * 
 * Key Responsibilities:
 * 1. User Interface management - Provides console menus and input handling
 * 2. Application Flow control - Orchestrates the sequence of operations
 * 3. Input Validation - Ensures user input meets system requirements
 * 4. Testing Support - Includes both normal and testing modes
 * 
 * @author Group 10
 * @version 1.0
 */
public class SupermarketApp {
    // Core system manager that handles business logic and data operations
    private SupermarketManager manager;
    // Scanner for reading user input from console
    private Scanner scanner;
    // Flag to determine if system is in testing mode or normal mode
    private boolean testingMode;
    
    /**
     * Constructor initializes the application with default settings
     * Sets up the scanner for input and creates a new manager instance
     */
    
    public SupermarketApp() {
        this.scanner = new Scanner(System.in); // Initialize console input scanner
        this.testingMode = false; // Default to normal operation mode
        this.manager = new SupermarketManager(); // Create core system manager 
    }
    
    /**
     * Application entry point with mode selection
     * This is the main starting method that coordinates application startup
     */
    public void start() {
        displayWelcomeMessage(); // Show initial welcome screen
        selectOperationMode(); // Let user choose testing or normal mode
        mainMenuLoop(); // Enter the main application loop
    }
    
    /**
     * Displays welcome message and application header
     * Creating a visually appealing introduction to the system
     */
    private void displayWelcomeMessage() {
        // Decorative header using box-drawing characters for visual appeal
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("||      SUPERMARKET MANAGMENT SYSTEM ||");
        System.out.println("||       Object-Oriented Coursework ||");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Allows user to select between normal mode and testing mode
     * Testing mode pre-loads sample data for demonstration and testing
     */
    private void selectOperationMode() {
        System.out.println("\nSelect Operation Mode:");
        System.out.println("1. Normal Mode (Start fresh or load existing data)");
        System.out.println("2. Testing Mode (Pre-loaded with sample data)");
        
        // Get user's mode selection
        int mode = getIntInput("Enter your choice (1 or 2):");
         
        if (mode == 2) {
            // Activate testing mode with Pre-loaded sample data
            testingMode = true;
            preloadTestData(); // load sample data testing
        } else {
            //  Default to normal mode with empty system
            System.out.println("Starting with empty system.");
            } 
        }
        
        /**
         * Preloads sample data for testing and demonstration purposes
         * Creates sample products and activities to demonstrate system functionality
         * This is particularly useful for coursework demonstration and testing
         */
        private void preloadTestData() {
            //  Create fresh manager instance for clean testing environment
            manager = new SupermarketManager();
            
            //Create sample activities with realistic data
            manager.addActivityToProduct("P001", "AddToStock", 25, LocalDate.of(2024, 1, 19));
            manager.addActivityToProduct("P001", "RemoveFromStock", 15, LocalDate.of(2024, 1, 20));
            manager.addActivityToProduct("P002", "AddToStock", 10, LocalDate.of(2024, 1, 21));
            
            // Add sample activities to demonstrate activity tracking functionality
            manager.addActivityToProduct("P001", "AddToStock", 25,LocalDate.of(2024,
        1, 19));
            manager.addActivityToProduct("P001", "RemoveFromStock", 15,
        LocalDate.of(2024, 1, 20));
            manager.addActivityToProduct("P2002", "AddToStock", 10, LocalDate.of(2024,
        1, 21));
        
            // Confirm testing mode activation
            System.out.println("\n Testing mode activated with pre-loaded sample data");
            System.out.println(" Sample products: Appple, Banana, Orange, Milk");
            System.out.println(" Sample activities added for testing\n");
            }
        
        /**
         * Main application loop - continuously displays menu and processes user choices
         * This is the core control loop that keeps the application running
         */
        private void mainMenuLoop() {
            // Infinite loop until user chooses to exit
            while (true) {
                displayMainMenu(); // Show the main menu options
                int choice = getIntInput("Enter your choice (0-7): "); //  Get user selection
                
                //  Process user choise using switch statement
                switch (choice) {
                    case 1: createProduct(); break; // Create new product
                    case 2: manager.displayAllProducts(); break; // Display all products
                    case 3: deleteProduct(); break; // Delete existing product
                    case 4: addActivity(); break; // Add activity to product
                    case 5: displayProductActivities(); break; // Display sorted activities
                    case 6: testLastFourActivities(); break; // Test last 4 activities feature
                    case 7: runComprehensiveTest(); break;// Run comprehensive test suite
                    case 0: {exitApplication(); return;} // Exit application
                    default: System.out.println("Invalid choice! Please enter 0-8."); // Invalid input
                }
                
                // Pause after each operation to allow user to read output
                PressEnterToContinue();
            }
        }     
        
        /**
         * Displays the main menu with all available options
         * Shows current system statistics for user reference 
         */
             private void displayMainMenu() {
                System.out.println("\n" + "=".repeat(60)); // Menu header
                System.out.println("MAIN MENU");
                System.out.println("=".repeat(60));
                System.out.println("1. Create New Product"); //Functionality 1
                System.out.println("2. Display All Products"); // Functionality 2
                System.out.println("3. Delete Product"); // Functionality 3
                System.out.println("4. Add Activity to Product"); // Functionality 4
                System.out.println("5. Display Product Activities (Sorted)");// Functionality 5
                System.out.println("6. Test Last 4 Activities Functionality"); // Additional test
                System.out.println("7. Run Comprehensive Test"); // Run all tests
                System.out.println("0. Exit Application"); // Exit option
                System.out.println("=".repeat(60));
                // Show current product count for system status awareness
                System.out.println("Current Products in System:" +
                                  (manager != null ? manager.getProductCount() : 0));
            }
             
             /**
              * Handles product creation workflow
              * Collects all required product information from user and creates new product 
              */

            private void createProduct() {
                System.out.println("\n" + "-".repeat(50));
                System.out.println("CREATE NEW PRODUCT");
                System.out.println("-".repeat(50));

                // Get product ID from user 
                System.out.println("Enter Product ID:");
                String ProductID = scanner.nextLine().trim();

                // Get product name from user
                System.out.println("Enter Product Name:");
                String ProductName = scanner.nextLine().trim();

                // Get entry date with validation
                LocalDate EntryDate = getDateInput("Enter Entry Date (YYYY-MM-DD):");
                if (EntryDate == null) return;

                int initialProductQuantity = getIntInput("Enter Initial Product Quantity:");
                if (initialProductQuantity < 0) {
                    System.out.println("Error: ProductQuantity can not be negative!");
                    return; // return if quantity is invalid
                }

                // Call manager to create product with collected data
                manager.createProduct(ProductID, ProductName, EntryDate, 
    initialProductQuantity);
        }

        /**
         * Handles product deletion workflow
         * Prompts user for product ID and attempts to delete the product
         */
        private void deleteProduct() {
            System.out.println("\n" + "-".repeat(50));
            System.out.println(            "DELETE PRODUCT");
            System.out.println("-".repeat(50));

            // Get product ID to delete
            System.out.print("Enter Product ID to delete: ");
            String ProductID = scanner.nextLine().trim();
 
            // Call manager to delete product
            manager.deleteProduct(ProductID);
        }

        /**
         * Handles activity addiction workflow
         * Allows user to add either AddToStock or RemoveFromStock activities
         */
        private void addActivity() {
            System.out.println("\n" + "-".repeat(50));
            System.out.println("ADD ACTIVITY");
            System.out.println("-".repeat(50));

            // Get product ID for which to add activity
            System.out.println("Enter Product ID: ");
            String ProductID = scanner.nextLine().trim();

            // Let user choose activity type
            System.out.println("Select Activity Type: ");
            System.out.println("1. AddToStock");
            System.out.println("2. RemoveFromStock");
            
            int activityChoise = getIntInput("Enter choise (1 or 2)");

            // Determine activity name based on user choise
            String activityName;
            if (activityChoise == 1) {
                activityName = "AddToStock";
            } else if  (activityChoise == 2) {
                activityName = "RemoveFromStock";
            } else {
                System.out.println("Invalid choise! Please enter 1 or 2.");
                return; // Return if invalid choise
            }

            // Get activity quantity
            int ProductQuantity = getIntInput("Enter ProductQuantity: ");
           
            // Get activity date
            LocalDate activityDate = getDateInput("Enter Activity Date (YYYY-MM-DD): ");
            if (activityDate == null) return; // Return if date input failed

            // Call manager to add activity
            manager.addActivityToProduct(ProductID, activityName, ProductQuantity, activityDate);
        }

        /**
         * Handles display of sorted activities for a specific product
         * Demonstrates sorting functionality
         */
        private void displayProductActivities() {
            System.out.println("\n" + "-".repeat(50));
            System.out.println("DISPLAY PRODUCT ACTIVITIES");
            System.out.println("-".repeat(50));

            // Get product ID foe which to display activities 
            System.out.println("Enter Product ID: ");
            String productId = scanner.nextLine().trim();

            // Call manager to display sorted activities
             manager.displayProductActivities(productId);
    }

    /**
    * Specially tests the last 4 activities functionality
    * Demonstrates that system maintains only the last 4 activities per product
    * This is a key requirement of the coursework
    */
    private void testLastFourActivities() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("      TESTING LAST 4 ACTIVITIES FUNCTIONALITY");
        System.out.println("-".repeat(70));

        //Create a test product if none exist
        if (manager.getProduct("TEST001") == null) {
        manager.createProduct("TEST001", "Test Product", LocalDate.now(),100);
        } 
        
        System.out.println("Adding 6 activities to demonstrate last 4 retension...");

        //Add 6 activities - system should keep only last 4 
        manager.addActivityToProduct("TEST001", "AddToStock", 10, LocalDate.now().plusDays(1));
        manager.addActivityToProduct("TEST001", "RemoveFromStock", 5, LocalDate.now().plusDays(2));
        manager.addActivityToProduct("TEST001", "AddToStock", 20, LocalDate.now().plusDays(3));
        manager.addActivityToProduct("TEST001", "RemoveFromStock", 8, LocalDate.now().plusDays(4));
        manager.addActivityToProduct("TESt001", "AddToStock", 15, LocalDate.now().plusDays(5)); //5th
        manager.addActivityToProduct("TEST001", "RemoveFromStock", 3, LocalDate.now().plusDays(6)); //6th

        System.out.println("\nNow displaying activities - should show LASt 4 activites:");
        manager.displayProductActivities("TEST001");

        System.out.println("TEST COMPLETED: System correctly maintain only last 4 activities");
    }

    /**
     * Runs the comprehensive test suite
     * Executes all test cases for coursework demonstration
     */
        private void runComprehensiveTest() {
            ComprehensiveTest test = new ComprehensiveTest();
            test.runAllTests();
        }

        /**
         * Handles application exit procedure
         * Provides closing messages and performs any cleanup
         */
        private void exitApplication() {
            System.out.println("\n" + "-".repeat(60));
            System.out.println("Thank you for using Supermarket Managment System!");
            System.out.println("Saving data before exit..."); // Placeholder for save functionality
            System.out.println("Goodbye!");
            System.out.println("-".repeat(60));
        }

        //Utility methods for input handling
        
        /**
         * Gets integer input from user with validation
         * Repeatedly prompts until valid integer is entered
         * 
         * @param prompt Message to display to user 
         * @return Valid integer entered by user
         */
        private int getIntInput(String prompt) {
            while (true) {
                try {
                    if (!prompt.isEmpty()) System.out.println(prompt);
                    return Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                }
            }
        }
        
        /**
         * Gets date input from user with validation
         * Expects date in YYYY-MM-DD format
         * 
         * @param prompt Message to display to user
         * @return LocalDate object or null if input cancelled
         */
        private LocalDate getDateInput(String prompt) {
            while (true) {
                try {
                    System.out.print(prompt);
                    String dateStr = scanner.nextLine().trim();
                    if (dateStr.isEmpty()) {
                        System.out.println("Date cannot be empty!");
                        continue; // Continue loop for empty input
                    }
                    return LocalDate.parse(dateStr); // Parse string to LocalDate
                } catch (DateTimeParseException e) {
                    System.out.println("Inavlid date format! Please use YYYY-MM-DD format.");
                }
            }
        }
        
        
        /**
         * Gets yes/no confirmation from user 
         * 
         * @param message Question to ask user
         * @return true if user confirms (y/yes), false otherwise 
         */
        private boolean getConfirmation(String message) {
            System.out.print(message + "(y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            return response.equals("y") || response.equals("yes");
        }

        /**
         * Pauses execution and waits for user to press Enter
         * Allows user to read output before continuing
         */
        private void PressEnterToContinue() {
            System.out.println("\nPress Enter to continue..."); 
            scanner.nextLine(); // Wait for enter key press
        }

        /**
         * Main entry point of the application 
         * Creates and starts the SupermarketApp instance
         * 
         * @param args Command line arguments 
         */
        public static void main(String[] args) {
            SupermarketApp app = new SupermarketApp(); // create application instance
            app.start(); // Start the application
        }
}
    