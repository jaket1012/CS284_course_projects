package pa1;

/**
 * PUT YOUR INFORMATION BELOW PRIOR TO SUBMISSION
 * Name:Jake Talvy
 * Pledge:I pledge my honor that I have abided by the Stevens Honor System.
 */

public class LinkedArray {
    public static class ArrayNode {
        public String[] array;
        public ArrayNode next;

        public ArrayNode(int size) {
            this.array = new String[size];
        }
    }

    public ArrayNode head;
    public int size;
    public ArrayNode tail;

    public LinkedArray() {
        size = 1;
        head = new ArrayNode(1);
        head.next = null;
        tail = head;
    }

    /**
     * This function takes in the location of the element and returns that element
     * @param idx the location of the desired element
     * @return the element at that location
     */
    public String get(int idx) {
        if (idx < 0 || idx > size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ArrayNode current = head;
        int nodeNum = 1;
        while (current.next != null){
            if (idx < (Math.pow(2, nodeNum) - 1.0)){
                break;
            }
            current = current.next;
            nodeNum++;
        }
        int num = 1;
        for (int i = 1; i < nodeNum; i++){
            num = num * 2;
        }
        return current.array[idx - (num - 1)];
    }

    /**
     * This function takes in the location of an element in the linked array and changes it to the given value.
     * @param idx the location of the element
     * @param value the new value the location in the linked array is being changed to
     */
    public void set(int idx, String value) {
        if (idx < 0 || idx > size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ArrayNode current = head;
        int nodeNum = 1;
        while (current.next != null){
            if (idx < (Math.pow(2, nodeNum) - 1.0)){
                break;
            }
            current = current.next;
            nodeNum++;
        }
        int num = 1;
        for (int i = 1; i < nodeNum; i++){
            num = num * 2;
        }
        current.array[idx - (num - 1)] = value;
    }

    /**
     * appends the inputted element to the end of the linked array
     * @param value the value that is added to the linked array
     */
    public void append(String value) {
        if (head == null){
            head = new ArrayNode(1);
            tail = head;
            tail.array[0] = value;
            size++;
            return;
        }
        if (tail.array[tail.array.length -1] != null){
            ArrayNode newNode = new ArrayNode(tail.array.length * 2);
            newNode.array[0] = value;
            tail = newNode;
            size++;
            return;
        }
        for (int i = 0; i < tail.array.length; i++){
            if (tail.array[i] == null) {
                tail.array[i] = value;
                break;
            }
        }
    }

    /**
     * returns the element at the end of the linked array and removes it
     * @return returns the element at the end of the linked array
     */
    public String pop() {
        ArrayNode current = head;
        while (current.next.next != null){
            current = current.next;
        }
        String toReturn = null;
        for (int i = 0; i < tail.array.length-1; i++){
            if (tail.array[i+1] == null){
                toReturn = tail.array[i];
                tail.array[i] = null;
                break;
            }
        }
        if (toReturn == null){
            toReturn = tail.array[tail.array.length-1];
            tail.array[tail.array.length-1] = null;
        }
        if (tail.array[0] == null){
            current.next = null;
            tail = current;
            size--;
        }
        return toReturn;
    }

    /**
     * This returns the amount of elements in the linked array
     * @return the amount of elements in the linked array
     */
    public int size() {
        if (head == null){
            throw new RuntimeException("list is empty");
        }
        int num = 1;
        for (int i = 1; i < size; i++){
            num = num * 2;
        }
        return num - 1 + tail.array.length;
    }

    public void insert(int idx, String value) {
        throw new UnsupportedOperationException("Method Not Implemented");
        // Delete the above line, and write your implementation here.
        // This method is OPTIONAL and not for credit.
    }

    public String remove(int idx) {
        throw new UnsupportedOperationException("Method Not Implemented");
        // Delete the above line, and write your implementation here.
        // This method is OPTIONAL and not for credit.
    }

    public String toString() {
        throw new UnsupportedOperationException("Method Not Implemented");
        // Delete the above line, and write your implementation here.
        // This method is OPTIONAL and not for credit.
    }
}
