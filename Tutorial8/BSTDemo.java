import java.util.Arrays;

public class BSTDemo {
    
    public static void main(String[] args) {
        System.out.println("=== BINARY SEARCH TREE DEMONSTRATION ===\n");
        
        BinarySearchTree bst = new BinarySearchTree();
        
        // Array of size 9
        int[] arr = {15, 10, 20, 8, 12, 17, 25, 6, 11};
        
        System.out.println("Inserting array: " + Arrays.toString(arr));
        for (int val : arr) {
            bst.insert(val);
        }
        
        // Display tree structure
        System.out.println("\nTree structure:");
        bst.printTree();
        
        // In-order traversal (sorted order)
        bst.inOrderTraversal();
        
        // Operation 1: Find Minimum
        System.out.println("\n--- Operation 1: MINIMUM ---");
        System.out.println("Minimum value: " + bst.findMinimum());
        
        // Operation 2: Find Maximum
        System.out.println("\n--- Operation 2: MAXIMUM ---");
        System.out.println("Maximum value: " + bst.findMaximum());
        
        // Operation 3: Find Successor
        System.out.println("\n--- Operation 3: FIND SUCCESSOR ---");
        int key1 = 10;
        System.out.println("Successor of " + key1 + ": " + bst.findSuccessor(key1));
        
        int key2 = 12;
        System.out.println("Successor of " + key2 + ": " + bst.findSuccessor(key2));
        
        // Operation 4: Delete
        System.out.println("\n--- Operation 4: DELETE ---");
        
        System.out.println("\nDeleting 6 (leaf node)...");
        bst.delete(6);
        bst.inOrderTraversal();
        
        System.out.println("\nDeleting 8 (one child)...");
        bst.delete(8);
        bst.inOrderTraversal();
        
        System.out.println("\nDeleting 10 (two children)...");
        bst.delete(10);
        bst.inOrderTraversal();
        
        System.out.println("\nFinal tree structure:");
        bst.printTree();
        
        System.out.println("\n=== DEMONSTRATION COMPLETE ===");
    }
}

// Tree Node class
class BSTNode {
    int key;
    BSTNode left;
    BSTNode right;
    BSTNode parent;
    
    public BSTNode(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

// Binary Search Tree class
class BinarySearchTree {
    private BSTNode root;
    
    public BinarySearchTree() {
        this.root = null;
    }
    
    // Insert a new key into the tree
    public void insert(int key) {
        BSTNode newNode = new BSTNode(key);
        BSTNode parent = null;
        BSTNode current = root;
        
        // Find the position to insert
        while (current != null) {
            parent = current;
            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        
        // Set parent
        newNode.parent = parent;
        
        // Insert as root or child
        if (parent == null) {
            root = newNode;  // Tree was empty
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }
    
    // Find minimum node in subtree rooted at node
    private BSTNode minimum(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    // Find maximum node in subtree rooted at node
    private BSTNode maximum(BSTNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    
    // Find minimum value in entire tree
    public int findMinimum() {
        if (root == null) {
            throw new RuntimeException("Tree is empty");
        }
        return minimum(root).key;
    }
    
    // Find maximum value in entire tree
    public int findMaximum() {
        if (root == null) {
            throw new RuntimeException("Tree is empty");
        }
        return maximum(root).key;
    }
    
    // Find successor of a node
    private BSTNode successor(BSTNode node) {
        // Case 1: Node has right subtree
        // Successor is minimum of right subtree
        if (node.right != null) {
            return minimum(node.right);
        }
        
        // Case 2: Node has no right subtree
        // Go up until we find a node that is a left child
        BSTNode parent = node.parent;
        while (parent != null && node == parent.right) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }
    
    // Find successor of a key
    public int findSuccessor(int key) {
        BSTNode node = search(root, key);
        if (node == null) {
            throw new RuntimeException("Key " + key + " not found in tree");
        }
        BSTNode succ = successor(node);
        if (succ == null) {
            throw new RuntimeException("No successor exists for " + key);
        }
        return succ.key;
    }
    
    // Search for a node with given key
    private BSTNode search(BSTNode node, int key) {
        if (node == null || node.key == key) {
            return node;
        }
        
        if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }
    
    // Check if tree contains a key
    public boolean contains(int key) {
        return search(root, key) != null;
    }
    
    // Delete a node with given key
    public void delete(int key) {
        root = deleteNode(root, key);
    }
    
    // Helper method to delete a node
    private BSTNode deleteNode(BSTNode node, int key) {
        if (node == null) {
            return null;
        }
        
        // Find the node to delete
        if (key < node.key) {
            node.left = deleteNode(node.left, key);
            if (node.left != null) node.left.parent = node;
        } else if (key > node.key) {
            node.right = deleteNode(node.right, key);
            if (node.right != null) node.right.parent = node;
        } else {
            // Node found - handle three cases
            
            // Case 1: No children (leaf node)
            if (node.left == null && node.right == null) {
                return null;
            }
            
            // Case 2a: Only right child
            if (node.left == null) {
                node.right.parent = node.parent;
                return node.right;
            }
            
            // Case 2b: Only left child
            if (node.right == null) {
                node.left.parent = node.parent;
                return node.left;
            }
            
            // Case 3: Two children
            // Find successor (minimum in right subtree)
            BSTNode successor = minimum(node.right);
            
            // Copy successor's value to current node
            node.key = successor.key;
            
            // Delete the successor (which has at most one child)
            node.right = deleteNode(node.right, successor.key);
            if (node.right != null) node.right.parent = node;
        }
        
        return node;
    }
    
    // In-order traversal (prints sorted order)
    public void inOrderTraversal() {
        System.out.print("In-order: ");
        inOrder(root);
        System.out.println();
    }
    
    private void inOrder(BSTNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }
    
    // Print tree structure visually
    public void printTree() {
        if (root == null) {
            System.out.println("(empty tree)");
            return;
        }
        printTree(root, "", true);
    }
    
    private void printTree(BSTNode node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node.key);
            
            if (node.left != null || node.right != null) {
                if (node.right != null) {
                    printTree(node.right, prefix + (isTail ? "    " : "│   "), false);
                } else {
                    System.out.println(prefix + (isTail ? "    " : "│   ") + "├── (null)");
                }
                
                if (node.left != null) {
                    printTree(node.left, prefix + (isTail ? "    " : "│   "), true);
                } else {
                    System.out.println(prefix + (isTail ? "    " : "│   ") + "└── (null)");
                }
            }
        }
    }
}
