package com.mycompany.supermarketmanagementsystem;

import java.time.LocalDate;

/**
 * Represents an activity performed on a product in the supermarket management system. (AddToStock or RemoveFromStock)
 * This class models stock movements including both additions (AddToStock) and
 * removals (RemoveFromStock) from inventory.
 * It tracks essential details about each stock transaction including the product
 * quantity involved and the date of the transaction.
 * 
 * @author Group 10
 * @version 1.0
 */
public class Activity {

    // Unique identifier for the activity - typically auto-generated or manually assigned
    private String activityID;
    
    // Type of activity - should be either "AddToStock" or "RemoveFromStock"
    private String activityName;
    
    // The quantity of products involved in this activity (positive integer)
    private int activityProductQuantity;
    
    // The date when the activity/transaction occured 
    private LocalDate activityDate;
    
    /**
     * Constructor to initialize activity with all required properties
     * This is used when creating a complete activity record from existing data.
     * 
     * @param activityID Unique identifier for the activity
     * @param activityName Type of activity (AddToStock/RemoveFromStock)
     * @param activityProductQuantity Number of items added/removed (must be positive)
     * @param activityDate The date when activity occurred (cannot be null)
     */
    public Activity(String activityID, String activityName, int activityProductQuantity,
            LocalDate activityDate) {
        this.activityID = activityID;
        this.activityName = activityName;
        this.activityProductQuantity = activityProductQuantity;
        this.activityDate = activityDate;
     }

    // Getters and setters with documentation
    
    /**
     * Retrieves the unique identifier for this activity.
     * 
     * @return The activity ID as a String
     */
    
    public String getActivityID() {
        return activityID;
    }
    
    /**
     * Updates the unique identifier for this activity.
     * 
     * @param activityID The new activity ID to set
     */
    
    public void setActivityID(String activityID) { 
        this.activityID = activityID;
    }
    
    /**
     * Retrieves the type/name of this activity.
     * 
     * @return The activity name (e.g., "AddToStock", "RemoveFromStock") 
     */
    
    public String getActivityName() {
        return activityName;
    }
    
    /**
     * Updates the type/name of this activity.
     * 
     * @param activityName The new activity name to set 
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    
    /**
     * Retrieves the quantity of products involved in this activity.
     * 
     * @return The product quantity as an integer 
     */
    public int getActivityProductQuantity() { 
        return activityProductQuantity; 
    }
    
    /**
     * Updates the quantity of products involved in this activity.
     * 
     * @param activityProductQuantity The new product quantity (must be positive) 
     */
    public void setActivityProductQuantity(int activityProductQuantity) {
    this.activityProductQuantity = activityProductQuantity; 
    }
    
    /**
     * Retrieves the date when this activity occurred.
     * 
     * @return The activity date as a LocalDate object
     */
    public LocalDate getActivityDate() {
        return activityDate; 
    }
    
    /**
     * Updates the date when this activity occurred.
     * 
     * @param activityDate The new activity date to set 
     */
    public void setActivityDate(LocalDate activityDate) { 
        this.activityDate = activityDate; 
    }
    
    /**
     * Returns a formatted string representation of activity for display purposes
     * This is useful for displaying activity information in logs, reports, or UI.
     * The format is: "Activity ID: XXX | Type: XXX | ProductQuantity: XXX | Date: XXX"
     * 
     * @return A formatted string containing all activity details
     */
    @Override
    public String toString() {
        return String.format("Activity ID: %s | Type: %s | ProductQuantity: %d | Date: %s",
                         activityID, activityName, activityProductQuantity, activityDate);
    }
    
    // Note: Consider adding the following methods in future enhancements:
    // 1. equals() and hashCode() methods for proper object comparison
    // 2. Input validation in setters to ensure data integrity
    // 3. Constants for activity names: public static final String ADD_TO_STOCK = "AddToStock";
    // 4. A constructor that automatically generates an activity ID
    // 5. Method to check if activity is valid (e.g., positive quantity, valid date)
}
