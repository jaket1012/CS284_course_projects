package pa4;
//Jake Talvy
//I pledge my honor that I have abided by the Stevens Honor System.

import java.util.Random;
import java.util.Stack;


public class Treap<E extends Comparable<E>> {
    /**
     * class representing a single node in the pa4.Treap
     * @param <E> generic data type
     */
    private static class Node<E>{
        public E data; // key for the search
        public int priority; // random heap priority
        public Node <E > left;
        public Node <E > right;

        /**
         * creates a new node object
         * @param data the data the node stores
         * @param priority the nodes priority value
         */
        public Node (E data, int priority){
            if (data == null){
                throw new IllegalArgumentException("data cannot be null");
            }
            this.data = data;
            this.priority = priority;
            left = null;
            right = null;
        }

        /**
         * performs a right rotation on the node
         *
         * @return returns the new root node after the rotation
         */
        Node <E> rotateRight (){
            Node<E> newRoot = left;
            this.left = left.right;
            newRoot.right = this;
            return newRoot;
        }

        /**
         * performs a left rotation on the node
         *
         * @return returns the new root node after the rotation
         */
        Node <E> rotateLeft () {
            Node<E> newRoot = right;
            this.right = right.left;
            newRoot.left = this;
            return newRoot;
        }

        public String toString(){
            return "(key =" + data + " , priority =" + priority + ")";
        }
    }
    private Random priorityGenerator;
    private Node<E> root;

    /**
     * creates a new instance of the Treap class with a null root and a priority generator with no seed.
     */
    public Treap(){
        root = null;
        priorityGenerator = new Random();
    }

    /**
     * creates a new instance of the Treap class with a null root and a priority generator with a given seed
     * @param seed the seed of the priority generator
     */
    public Treap(long seed){
        root = null;
        priorityGenerator = new Random(seed);
    }

    /**
     * adds a new node to the Treap with a random priority and a given key
     * @param key the key for the added node
     * @return true if its able to add the key successfully, false otherwise
     */
    public boolean add(E key){
        if (key == null){
            throw new IllegalArgumentException("key cannot be null");
        }
        return add(key, priorityGenerator.nextInt());
    }

    /**
     * this function is called by add. reheaps the Treap so that the Treap maintains the properties of a heap as well as a BST
     * @param path the path the added item took to reach where it is inserted
     */
    private void reheap(Stack<Node<E>> path){
        Node<E> child = path.pop();
        while (!path.isEmpty()){
            Node<E> newParent;
            Node<E> parent = path.pop();
            if (child.priority < parent.priority){
                return;
            }
            if (parent.right == child){
                newParent = parent.rotateLeft();
            }
            else{
                newParent = parent.rotateRight();
            }
            if(path.isEmpty()){
                root = newParent;
                return;
            }
            Node<E>grandParent = path.peek();
            if (grandParent.right == parent){
                grandParent.right = newParent;
                parent = grandParent;
            }
            else{
                grandParent.left = newParent;
                parent = grandParent;
            }

        }
    }

    /**
     * usually called by the regular add function, adds a new node to the Treap with a given priority and a given key
     * @param key the given key for the added item
     * @param priority the items priority
     * @return true if its able to add the key successfully, false otherwise
     */
    public boolean add(E key, int priority){
        if (key == null){
            throw new IllegalArgumentException("key cannot be null");
        }
        if (root == null){
            root = new Node<E>(key, priority);
            return true;
        }
        Node<E> current = root;
        Stack<Node<E>> path = new Stack<>();
        while(true){
            int comparison = key.compareTo(current.data);
            if (comparison == 0){
                return false;
            }

            path.push(current);

            if (comparison < 0) {
                if (current.left == null) {
                    current.left = new Node<>(key, priority);
                    path.push(current.left);
                    break;
                }
                current = current.left;
            }else {
                if (current.right == null) {
                    current.right = new Node<>(key, priority);
                    path.push(current.right);
                    break;
                }
                current = current.right;
            }
        }
        reheap(path);
        return true;
    }

    /**
     * deletes the node of the given key from the Treap.
     * @param key the key of the desired node to be deleted
     * @return true if the node is in the Treap and is able to be removed and false otherwise
     */
    boolean delete(E key){
        if (find(key)){
            Node<E> current = root;
            Node<E> parent = root;
            while(true){
                int comparison = key.compareTo(current.data);
                if (comparison == 0){
                    break;
                }
                if (comparison < 0){
                    parent = current;
                    current = current.left;
                }
                else{
                    parent = current;
                    current = current.right;
                }
            }
            boolean rightChild;
            if (parent.right == current){
                rightChild = true;
            }else{
                rightChild = false;
            }
            while(true){
                if (current.right != null && current.left != null){
                    if (current.right.priority > current.left.priority){
                        if (root == current){
                            root = current.rotateLeft();
                            parent = root;
                        } else {
                            if(rightChild) {
                                parent.right = parent.right.rotateLeft();
                                parent = parent.right;
                            }
                            else{
                                parent.left = parent.left.rotateLeft();
                                parent = parent.left;
                            }
                        }
                        rightChild = false;
                        continue;
                    }else{
                        if (root == current) {
                            root = current.rotateRight();
                            parent = root;
                        } else {
                            if(rightChild) {
                                parent.right = parent.right.rotateRight();
                                parent = parent.right;
                            }
                            else{
                                parent.left = parent.left.rotateRight();
                                parent = parent.left;
                            }
                        }
                        rightChild = true;
                        continue;
                    }
                }
                if(current.left != null){
                    if (root == current) {
                        root = current.rotateRight();
                        parent = root;
                    }
                    else {
                        if(rightChild) {
                            parent.right = parent.right.rotateRight();
                            parent = parent.right;
                        }
                        else{
                            parent.left = parent.left.rotateRight();
                            parent = parent.left;
                        }
                    }
                    rightChild = true;
                    continue;
                }if (current.right != null){
                    if (root == current) {
                        root = current.rotateLeft();
                        parent = root;
                    } else {
                        if(rightChild) {
                            parent.right = parent.right.rotateLeft();
                            parent = parent.right;
                        }
                        else{
                            parent.left = parent.left.rotateLeft();
                            parent = parent.left;
                        }
                    }
                    rightChild = false;
                    continue;
                }else {
                    if (parent == root){
                        if (parent.right == current){
                            parent.right = null;
                        } if(parent.left == current){
                            parent.left = null;
                        } else{
                            root = null;
                        }
                    }
                    if (rightChild){
                        parent.right = null;
                    }
                    else{
                        parent.left = null;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * called by find, finds the node with the given key starting from the given root
     * @param root the root of the Treap that is ideally an ancestor to the given key
     * @param key the key of the node to be found
     * @return true if the node with the given key is in the Treap, false if not
     */
    private boolean find(Node<E> root, E key){
        if (key == null){
            throw new IllegalArgumentException("key cannot be null");
        }
        if (root == null){
            return false;
        }
        int comparison = key.compareTo(root.data);
        if (comparison == 0){
            return true;
        }
        if (comparison < 0){
            return find(root.left, key);
        }
        else{
            return find(root.right, key);
        }
    }

    public boolean find(E key){
        return find(root, key);
    }

    /**
     * SOURCE CODE FROM CANVAS BINARYTREE.JAVA
     * @return the Treap formatted into a string
     */
    public String toString(){
        return toString(root, 0);
    }

    /**
     * called by toString SOURCE CODE FROM CANVAS BINARYTREE.JAVA
     * @param current the current node
     * @param level the level of the node
     * @return the Treap formatted into a string
     */
    private String toString(Node current, int level){
        StringBuilder s = new StringBuilder();
        for(int i = 0 ; i < level; i++)
            s.append(" ");
        if(current == null)
            s.append("null\n");
        else{
            s.append(current.toString()+"\n");
            s.append(toString(current.left, level + 1));
            s.append(toString(current.right, level + 1));
        }
        return s.toString();
    }
}
