package DataStructures;

import java.util.*;

public class Stacks_SimpleTextEditor {

    /**
     * Creates a custom Stack object using a linked list to reduce time/space complexity.
     * Still encountering timeouts for a couple tests though :/
     */
    static class Node {
        public int operation = 0;
        public String operand;
        public Node next;

        public Node(int i, String s) {
            this.operation = i;
            this.operand = s;
        }
    }
    static class Stack {
        private Node head;
        private String currentText = "";

        public void push(int i, String s) {
            final Node newNode = new Node(i, s);
            if (head != null) {
                newNode.next=head;
            }
            head = newNode;

            if (i == 1) {
                this.currentText += s;
            }
            else if (i == 2) {
                this.currentText = this.currentText.substring(0, this.currentText.length()-s.length());
            }
        }
        public Node pop() {
            if (head == null) {
                return null;
            }
            final Node poppedNode = head;
            head = head.next;
            if (poppedNode.operation == 1) {
                currentText = currentText.substring(0, currentText.length() - poppedNode.operand.length());
            } else {
                currentText += poppedNode.operand;
            }
            return poppedNode;
        }

        public String getCurrentText() {
            return currentText;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public Stack(){}
    }

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        final int q = Integer.parseInt(scanner.nextLine());
        final Stack operationStack = new Stack();

        for (int i=0; i<q && scanner.hasNext(); i++) {
            //System.out.println("stack currentText is " + operationStack.getCurrentText());
            final String operation = scanner.nextLine();
            final String[] splitOperation = operation.split(" ");
            final int operationType = Integer.parseInt(splitOperation[0]);

            if (operationType == 1) {
                operationStack.push(operationType, splitOperation[1]);
            }
            else if (operationType == 2) {
                final int k = Integer.parseInt(splitOperation[1]);
                final String removedText = operationStack.getCurrentText().substring(operationStack.getCurrentText().length()-k);
                operationStack.push(operationType, removedText);
            } else if (operationType == 3) {
                final int k = Integer.parseInt(splitOperation[1]);
                System.out.println(operationStack.getCurrentText().charAt(k-1));
            } else if (operationType == 4) {
                operationStack.pop();
            }
        }
    }

    /**
     * Alternate solution that uses a stack of all stages of text S
     */
//    static class Node {
//        public String s;
//        public Node next;
//
//        public Node(String string) {
//            this.s = string;
//        }
//    }
//    static class Stack {
//        Node head;
//        public void push(String string) {
//            Node newNode = new Node(string);
//            if (head != null) {
//                newNode.next=head;
//            }
//            head = newNode;
//        }
//        public String pop() {
//            if (head == null) {
//                return null;
//            }
//            final String headString = head.s;
//            head = head.next;
//            return headString;
//        }
//        public String peek() {
//            return head == null ? null : head.s;
//        }
//
//        public boolean isEmpty() {
//            return head == null;
//        }
//
//        public Stack(){}
//    }
//
//    public static void main(String[] args) {
//        final Scanner scanner = new Scanner(System.in);
//        final int q = Integer.parseInt(scanner.nextLine());
//        final Stack s_stack = new Stack();
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
