public class FunnyTreeWalkDemo {
    static int counter = 0;
    
    public static void main(String[] args) {
        System.out.println("=== FUNNY TREE WALK DEMONSTRATION ===\n");
        System.out.println("From COEN352 Fall 2024 Final Exam Question 5");
        System.out.println();
        
        // Build tree from array [9, 16, 4, 18, 10]
        TreeNode root = buildExampleTree();
        
        System.out.println("Tree structure (array representation: [9, 16, 4, 18, 10]):");
        printTree(root, "", true);
        
        System.out.println("\n--- ALGORITHM ---");
        System.out.println("int counter = 0;");
        System.out.println("public void funnyTreeWalk(Node x) {");
        System.out.println("    if (x != null) {");
        System.out.println("        funnyTreeWalk(x.left);   // Go LEFT");
        System.out.println("        funnyTreeWalk(x.left);   // Go LEFT again!");
        System.out.println("        System.out.print(x.key + \" \");");
        System.out.println("        funnyTreeWalk(x.right);  // Go RIGHT");
        System.out.println("    } else {");
        System.out.println("        counter = counter + 1;");
        System.out.println("    }");
        System.out.println("}");
        
        System.out.println("\n--- KEY INSIGHT ---");
        System.out.println("Compared to in-order tree walk, funnyTreeWalk goes to the LEFT TWICE!");
        System.out.println("This means the left subtree is fully traversed TWICE before printing the node.");
        System.out.println();
        
        // Run funnyTreeWalk
        System.out.println("--- EXECUTION ---");
        System.out.print("Output: ");
        funnyTreeWalk(root);
        System.out.println();
        
        System.out.println("\n--- RESULTS ---");
        System.out.println("(a) Printed result: 18 18 16 10 18 18 16 10 9 4");
        System.out.println("(b) Counter at end: " + counter);
        System.out.println();
        
        System.out.println("--- EXPLANATION ---");
        System.out.println("Counter explanation:");
        System.out.println("- Tree has 5 nodes, so it has 5+1 = 6 null pointers in a binary tree");
        System.out.println("- Node 18: 2 null children");
        System.out.println("- Node 10: 2 null children");
        System.out.println("- Node 4: 2 null children");
        System.out.println("- Plus 0 additional nulls = 6 total null positions");
        System.out.println();
        System.out.println("But wait! The answer is " + counter + ", not 6!");
        System.out.println();
        System.out.println("This is because we call funnyTreeWalk(x.left) TWICE.");
        System.out.println("Each time we visit a node, we traverse its left subtree twice.");
        System.out.println("This means some null nodes are visited multiple times!");
        System.out.println();
        System.out.println("Formula: 3 * 7 = 21");
        System.out.println("(Each of the 7 null positions is visited 3 times on average)");
        
        System.out.println("\n=== DEMONSTRATION COMPLETE ===");
    }
    
    // Build tree from exam: [9, 16, 4, 18, 10]
    //        9
    //       / \
    //      16  4
    //     / \
    //    18  10
    private static TreeNode buildExampleTree() {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(16);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(18);
        root.left.right = new TreeNode(10);
        return root;
    }
    
    // FunnyTreeWalk implementation from the exam
    public static void funnyTreeWalk(TreeNode x) {
        if (x != null) {
            funnyTreeWalk(x.left);    // Go LEFT first
            funnyTreeWalk(x.left);    // Go LEFT again!
            System.out.print(x.key + " ");
            funnyTreeWalk(x.right);   // Go RIGHT
        } else {
            counter = counter + 1;     // Count each null visit
        }
    }
    
    // Helper method to print tree structure
    private static void printTree(TreeNode node, String prefix, boolean isTail) {
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

// Simple tree node class
class TreeNode {
    int key;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }
}
