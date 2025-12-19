package com.mycompany.supermarketmanagementsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom implementation of a linked list for storing activities.
 * This satisfies the requirement for a custom data structure implementation.
 * Maintains only the last N elements (4 in our case) using FIFO principle to manage memory efficiency.
 * 
 * Key Features: 
 * - Fixed maximum capacity(configurable via constructor)
 * - Automatically removes oldest elements when capacity is exceeded
 * - provides methods for adding, removing, and retrieving elements
 * - Can be converted to standard Java collections for sorting
 * 
 * @author Group 10
 * @version 1.0
 * @param <T> The type of elements stored in the list (typically Activity objects)
 */
public class CustomLinkedList<T> {
    // Reference to the first node in the linked List (head of the list)
    private Node<T> head;
    
    // Current number of elements in the list
    private int size;
    
    //Maximum number of elements allowed in the list (4 for activity tracking)
    private final int maxSize;
            
    /**
     * Static inner class representing individual nodes in the linked list.
     * Each node contains the data element and a reference to the next node.
     * This is the fundamental building block of our liked list.
     * 
     * @param <T> The type of data stored in the node
     */

private static class Node<T> {
    // The actual data element stored in this node
        T data;
        
        // Reference to the next node in linked list
        // null indicates this is the last node in the list
        Node<T> next;
        
        
        /**
         * Constructs a new node with the specified data.
         * The next reference is initialized to null.
         * 
         * @param data The data elements
         */
        Node(T data) {
            this.data = data;
            this.next = null; // New nodes are initially not connected to any other node
        }
    }
     
     /**
      * Constructor initializes an empty linked list with specified maximum size.
      * 
      * @param maxSize Maximum number of elements to maintain in the list.
      *                 For our activity tracking requirement, this should be 4.
      * @throws IllegalArgumentException if maxSize is less than or equal to 0
      */
    public CustomLinkedList(int maxSize) {
        // Validate the maxSize parameter
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Maximum size must be positive:" + maxSize);
        }
        
        this.head = null;  // Empty list has no head node
        this.size = 0;    // Empty list has size 0
        this.maxSize = maxSize;  // Set the capacity constraint
    }
    
    /**
     * Adds a new element to the beginning of the list (maintains recent activities first).
     * Implements FIFO behavior - removes oldest element when max size is exceeded
     * (Last in the list) is automatically removed.
     * 
     * Time Complexity: 0(1) for adding, 0(n) for maintaining size constraint
     * 
     * @param data The element to add to the beginning of the list
     * @throws NullPointerException if data is null (depending on requirements)
     */
   public void addFirst(T data) {
       // Create a new node with the provided data
       Node<T> newNode = new Node<> (data);
       
       // Insert the new node at the beggining of the list
       newNode.next = head;  // New node points to current head
       head = newNode;      // Update head to the point to new node
       
       // Increment the size counter
       size++;
       
       // CRITICAL REQIREMENT: Maintain only the last maxSize elements
       // If we have exceeded the maximum capacity, remove the oldest element
       
   }
   
   /**
    * Removes the last element from the list (oldest activity).
    * Used when maintaining the last 4 activities requirement.
    * 
    * Time Complexity: 0(n) where n is the number of elements in the liist
    * 
    * Edge cases handled:
    * - Empty list: does nothing
    * - Single element list: removes the only element
    * - Multiple elements: removes the last one
    */
   public void removeLast() {
       // If list is empty, there's nothing to remove
       if (head == null) {
           return; // or could throw IllegalStateException depending on requirements
       }
       
       // Special case: list has only one element
       if (head.next == null) {
           head = null;  // Remove the only element
       } else {
           // General case: traverse to find the second-to-last node
           Node<T> current = head;
           
           // Traverse untill current.next.next is null
           // This means current is thesecond-to-last node
           while (current.next.next != null){
           current = current.next;
       }
           
           // Remove the last node by setting second-to-last's next to null
           current.next = null;
       }
       
       // Update the size counter
       size--;
   }
   
   /**
    * Converts the linked list to ArrayList for sorting operations
    * This is useful because standard java collections have built-in sorting methods.
    * The conversion maintains the order of elements as they appear in the linked list.
    * 
    * Time Complexity: 0(n) where n is the number of elements
    * 
    * @return ArrayList containing all elements from the linked list
    */
   public ArrayList<T> toArrayList() {
       // Create a new ArrayList with initial capacity equal to list size
       ArrayList<T> list = new ArrayList<>();
       
       // Start traversal from the head of the linked list
       Node<T> current = head;
       
       // Traverse through all nodesand add data to ArrayList
       while (current != null) {
           list.add(current.data);
           current = current.next;  // Move to next node
       }
       return list;
   }
   
   /**
    * Returns all elements as a List for external processing.
    * This provides more flexibility than returning ArrayList specifically.
    * 
    * @return List containing all elements from the linked list in order
    */
   public List<T> getAllElements() {
       // Create a List (interface) that will contain all elements
       List<T> elements = new ArrayList<>();
       
       // Traverse the linked list
       Node<T> current = head;
       while (current != null) {
           elements.add(current.data);
           current = current.next;
       }
       return elements;
   }
   
   // Utility methods for list management
   
   /**
    * Returns the current number of elements in the list.
    * 
    * @return The size of the list (number of elements)
    */
   public int size() { return size; }
   
   /**
    * Checks if the list is empty (contains no elements).
    * 
    * @return true if the list is empty, false otherwise 
    */
   public boolean isEmpty() { return size == 0; }
   
   /**
    * Clears all elements from the list, resetting it to empty state.
    * Useful for refusing the list or freeing memory.
    */
   
   // Note for future enhancements:
   // 1. Consider adding an iterator implementatio for enhanced for-loop support
   // 2. Add getFirst() and getLast() methods for convenient access
   // 3. Implement equals() and hashCode() for proper object comparison
   // 4. Add validatiion for null elements if needed
   // 5. Consider making the list thread-safe for concurrent access
}