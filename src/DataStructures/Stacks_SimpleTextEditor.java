package DataStructures;

import java.util.*;

public class Stacks_SimpleTextEditor {

    /**
     * Creates a Stack object using a linked list to optimize performance.
     * Still encountering timeouts for a couple tests though :/
     */
    static class Node {
        public String s;
        public Node next;

        public Node(String string) {
            this.s = string;
        }
    }
    static class Stack {
        Node head;
        public void push(String string) {
            Node newNode = new Node(string);
            if (head != null) {
                newNode.next=head;
            }
            head = newNode;
        }
        public String pop() {
            if (head == null) {
                return null;
            }
            final String headString = head.s;
            head = head.next;
            return headString;
        }
        public String peek() {
            return head == null ? null : head.s;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public Stack(){}
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int q = Integer.parseInt(scanner.nextLine());
        final Stack s_stack = new Stack();
        for (int i=0; i<q && scanner.hasNext(); i++) {
            String s = s_stack.isEmpty() ? "" : s_stack.peek();
            final String operation = scanner.nextLine();
            final String[] splitOperation = operation.split(" ");
            final int operationType = Integer.parseInt(splitOperation[0]);
            if (operationType == 1) {
                s_stack.push(s + splitOperation[1]);
            } else if (operationType == 2) {
                final int k = Integer.parseInt(splitOperation[1]);
                s_stack.push(s.substring(0, s.length()-k));
            } else if (operationType == 3) {
                final int k = Integer.parseInt(splitOperation[1]);
                System.out.println(s.charAt(k-1));
            } else if (operationType == 4) {
                s_stack.pop();
            }
        }
    }

    // original solution using Java's built-in Stack object
//    public static void main(String[] args) {
//        final Scanner scanner = new Scanner(System.in);
//        final int q = Integer.parseInt(scanner.nextLine());
//        final Stack<String> s_stack = new Stack<>();
//        for (int i=0; i<q && scanner.hasNext(); i++) {
//            String s = s_stack.isEmpty() ? "" : s_stack.peek();
//            final String operation = scanner.nextLine();
//            final String[] splitOperation = operation.split(" ");
//            final int operationType = Integer.parseInt(splitOperation[0]);
//            if (operationType == 1) {
//                s_stack.push(s + splitOperation[1]);
//            } else if (operationType == 2) {
//                final int k = Integer.parseInt(splitOperation[1]);
//                s_stack.push(s.substring(0, s.length()-k));
//            } else if (operationType == 3) {
//                final int k = Integer.parseInt(splitOperation[1]);
//                System.out.println(s.charAt(k-1));
//            } else if (operationType == 4) {
//                s_stack.pop();
//            }
//        }
//    }
}
