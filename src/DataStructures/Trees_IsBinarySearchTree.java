package DataStructures;

public class Trees_IsBinarySearchTree {

    /* Hidden stub code will pass a root argument to the function below.
    Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.
    */

    // The Node class is defined as follows:
    class Node {
        int data;
        Node left;
        Node right;
    }


    boolean checkBST(Node root) {
        if (root == null) {
            return true;
        }
        int leftMax = maxChild(root.left);
        int rightMin = minChild(root.right);
        if (leftMax >= root.data || rightMin <= root.data) {
            return false;
        }
        return checkBST(root.left) && checkBST(root.right);
    }

    int maxChild(Node root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int leftMax = root.left == null ? Integer.MIN_VALUE : maxChild(root.left);
        int rightMax = root.right == null ? Integer.MIN_VALUE : maxChild(root.right);
        int max = root.data;
        if (leftMax > max) {
            max = leftMax;
        }
        if (rightMax > max) {
            max = rightMax;
        }
        return max;
    }

    int minChild(Node root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int leftMin = root.left == null ? Integer.MAX_VALUE : minChild(root.left);
        int rightMin = root.right == null ? Integer.MAX_VALUE : minChild(root.right);
        int min = root.data;
        if (leftMin < min) {
            min = leftMin;
        }
        if (rightMin < min) {
            min = rightMin;
        }
        return min;
    }
}
