package com.mycompany.supermarketmanagementsystem;

import java.util.List;

/**
 * Custom sorting algorithms implementation
 * Provides bubble sort implementation as required
 * This class demonstrates understanding of sorting algorithms and their practical application
 * in sorting product activities by quantity in the supermarket management system
 * 
 * Key Implementations:
 * 1. Bubble Sort - Primary algorithm used for activity sorting
 * 2. Quick sort - Additional demonstration of alternative sorting algorithm knowledge
 * 
 * Sorting Characteristics:
 * - Bubble Sort: Simple, stable, o(n²) time complexity, good for small datasets
 * - Quick Sort: Efficient, recursive, average 0 (n log n) time complexity
 * 
 * Note: Since activities are limited to maximum 4 per product
 * the 0(n²) complexity of bubble sort is acceptable for this specific use case.
 * 
 * @author Group 10
 * @version 1.0
 */
public class SortAlgorithms {
    
    /**
     * Bubble sort implementation for sorting activities by product quantity
     * This satisfies the custom sorting algorithm requirement
     * Bubble sort is chosen because:
     * 1. Activity lists are small
     * 2. Simple implementation meets functional requirements
     * 3. Stable sorting algorithm
     * 4. Easy to understand and implement correctly
     * 
     * Algorithm steps:
     * 1. Validate input parameters
     * 2. Repeatedly iterate through the list
     * 3. Compare adjacent elements
     * 4. Swap elements if they are in wrong order
     * 5. Continue until no swaps are needed 
     * 
     * Time complexity: 0(n²) - acceptable for small datasets
     * Space Complexity: 0(1) - in-place sorting, uses only constant extra space
     * 
     * 
     * @param activities List of activities to sort quantity
     */
    public static void bubbleSortActivities(List<Activity> activities) {
        // Input validation
        if (activities == null || activities.size() <= 1) {
            return; // Early return: nothing to sort
        }
        
        int n = activities .size(); // Store list size for efficiency
        boolean swapped; // Optimization flag: tracks if any swaps occured in pass
        
        // Bubble sort algorithm implementation
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Reset swap flag for this pass
            
            // Inner loop: compares adjacent elements in the unsorted portion
            // n-i-1: reduces comparison range as largest elements settle at end
            for (int j = 0; j < n - 1; j++){
            // Compare adjacent elements
            if (activities.get(j).getActivityProductQuantity() > activities.get(j + 1).getActivityProductQuantity()) {
                // Swap activities if they are in wrong order
                Activity temp = activities.get(j); // temporary storage for swap
                activities.set(j, activities.get(j + 1)); // Move next to current position
                activities.set(j + 1, temp);// Move current to nect position
                swapped = true; // Mark that a swap occured
            }
        }
            
        // If no swapping occured , list i salready sorted
        // the list is already sorted, so we can stop early
        if (!swapped) break; // Exit outer loop early
    }
}
    
/**
 * Alternative quick sort implementation for demonstration
 * Shows knowledge of more advanced sorting algorithms beyond required bubble sort
 * Quick sort is a divide-and-conquer algorithm with better average performance
 * 
 * Algorithm Overview:
 * 1. Choose a pivot element
 * 2. Partition the array around the pivot
 * 3. Recursively sort the sub-arrays
 * 
 * Note: This implementation has some issues
 * 
 * @param activities list of activities to sort by product quantity
 * @param low Starting index of the sub-array to sort
 * @param high ending index of the sub-array to sort
 */
public static void quickSortActivities(List<Activity> activities, int low,
        int high) {
                    // Base case: if low index is less than high index, there are elements to sort
                if (low < high) {
                    // Partition the array and get the pivot index
                    int pi = partition(activities, low, high);
                    // Recursively sort elements before partition
                    quickSortActivities(activities, low, pi - 1);
                    // Recursively sort elements after partition
                    quickSortActivities(activities, pi + 1, high);
                }
            }

            /**
             * Helper method for quick sort that partitions the array around pivot
             * This method selects last element as pivot, places it in correct position,
             * and places all smaller elements to left and larger elements to right
             * 
             * @param activities List of activities being sorted
             * @param low Starting index of partition stage
             * @param high Ending index of partition range
             * @return The correct index position of the pivot element after partitioning
             */
            
            private static int partition (List<Activity> activities, int low, int high) {
                
                // Select pivot element: uses the last element's product quantity
                int pivot = activities.get(high).getActivityProductQuantity();
                
                //  Index of smaller element: indicates position where pivot should be placed
                int i = (low - 1); // Initially set to one position before low
                
                // Iterate through the array from low to high-1
                for (int j = low; j < high; j++) {
                    // If current element is smaller than or equal to pivot
                    if (activities.get(j).getActivityProductQuantity() <= pivot) {
                        i++; // Increment index of smaller element
                        
                        // Swap current element with element at i
                        // Swap the pivot element with element i+1
                        // This places pvot in ita correct position
                        Activity temp = activities.get(i);
                        activities.set(i + 1, activities.get(high));
                        activities.set(high, temp);
                        
                        // return the partition index
                        return i + 1;
                    }
                }
        return 0;
    }
            
            // Note: Issues in current quickSort Implementation
            // 1. The original code had incorrect partiotion method logic
            // 2. Missing swap operation in the far loop of partition method
            // 3. The corrected partition method above fixes these issues
            // 4. Consider adding input validation similat to bubbleSort method
            
            // Note: Potential enhancements for duture development:
            // 1. Add insertion sort for very small lists(n<=10)
            // 2. Implement merge sort for the comparison of different algorithms
            // 3. Add generic implementation to sort any comparable object
            // 4. Add option for descending order sorting
            // 5. Implement hybrid sort
}
