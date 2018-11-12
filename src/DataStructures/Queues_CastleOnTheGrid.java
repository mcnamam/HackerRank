package DataStructures;

import java.io.*;
import java.util.*;

public class Queues_CastleOnTheGrid {

    /**
     * BFS solution got all messed up once I realized the problem's X and Y are reversed...
     *  Works for some test cases but not all since they have an inconsistent concept of X and Y
     */
    // Complete the minimumMoves function below.
    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
        final Queue queue = new Queue();
        final Map<String, Node> posToNodeMap = createNodeMap(grid);
        final Node startNode = posToNodeMap.get(getMapKey(startX,startY));
        startNode.visited = true;
        queue.enqueue(startNode);

        int shortestDistance = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            final Node node = queue.dequeue();
            if (node.x == goalX && node.y == goalY) {
                if (node.numMovesToHere < shortestDistance) {
                    shortestDistance = node.numMovesToHere;
                }
            }
            for (Node neighbor : node.neighbors) {
                if (neighbor == null) continue;
                if (!neighbor.visited) {
                    neighbor.visited = true;
                    neighbor.numMovesToHere = node.numMovesToHere+1;
                    queue.enqueue(neighbor);
                }
            }
        }

        return shortestDistance;
    }


    static Map<String, Node> createNodeMap(String[] grid) {
        Map<String, Node> posToNodeMap = new HashMap<>();

        // create nodes for all indices with '.'
        for (int x=0; x < grid.length; x++) {
            final String row = grid[x];
            for (int y=0; y<row.length(); y++) {
                if (row.charAt(y) == 'X') {
                    continue;
                }
                final String mapKey = getMapKey(x,y);
                posToNodeMap.put(mapKey, new Node(x,y,0,false));
            }
        }

        // iterate through all of the '.' nodes,
        // then walk up, down, left, right from each node to find
        // which other nodes are directly accessible from the node
        for (Node node : posToNodeMap.values()) {
            final List<Node> neighbors = new ArrayList<>();
            char[] rowChars = grid[node.x].toCharArray();
            char[] colChars = getColChars(grid, node.y);

            // look left
            if (node.y > 0) {
                for (int j=node.y-1; j>=0; j--) {
                    if (rowChars[j] == 'X') {
                        break;
                    }
                    neighbors.add(posToNodeMap.get(getMapKey(node.x,j)));
                }
            }
            // look right
            if (node.y < 2) {
                for (int j=node.y+1; j<=2; j++) {
                    if (rowChars[j] == 'X') {
                        break;
                    }
                    neighbors.add(posToNodeMap.get(getMapKey(node.x,j)));
                }
            }
            // look up
            if (node.x > 0) {
                for (int i=node.x-1; i>=0; i--) {
                    if (colChars[i] == 'X') {
                        break;
                    }
                    neighbors.add(posToNodeMap.get(getMapKey(i, node.y)));
                }
            }
            // look down
            if (node.x < 2) {
                for (int i=node.x+1; i<=2; i++) {
                    if (colChars[i] == 'X') {
                        break;
                    }
                    neighbors.add(posToNodeMap.get(getMapKey(i,node.y)));
                }
            }

            node.neighbors = neighbors;
        }

        return posToNodeMap;
    }

    static String getMapKey(int y, int x) {
        return "" + y + "," + x;
    }

    static char[] getColChars(String[] grid, int col) {
        final char[] colChars = new char[grid.length];
        for (int i=0; i<grid.length; i++) {
            colChars[i] = grid[i].charAt(col);
        }
        return colChars;
    }

    static class Node {
        public int x = 0, y = 0;
        public int numMovesToHere = 0;
        public boolean visited = false;
        public List<Node> neighbors;
        public Node next;

        public Node(int x, int y, int numMovesToHere, boolean visited) {
            this.x = x;
            this.y = y;
            this.numMovesToHere = numMovesToHere;
            this.visited = visited;
        }

        public String toString() {
            String s = "Position (" + y + "," + x + "). Neighbors: ";
            for (Node neighbor : neighbors) {
                if (neighbor == null) continue;
                s += "(" + neighbor.y + "," + neighbor.x + ")";
            }
            return s;
        }
    }

    static class Queue {
        private Node head;
        private Node tail;

        public Queue() {}

        public void enqueue(Node node) {
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }

        public Node dequeue() {
            if (head == null) {
                return null;
            }
            final Node dequeuedHead = head;
            this.head = head.next;
            return dequeuedHead;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] startXStartY = scanner.nextLine().split(" ");

        int startX = Integer.parseInt(startXStartY[0]);

        int startY = Integer.parseInt(startXStartY[1]);

        int goalX = Integer.parseInt(startXStartY[2]);

        int goalY = Integer.parseInt(startXStartY[3]);

        int result = minimumMoves(grid, startX, startY, goalX, goalY);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
        System.out.println("Rsult is " + result);

        scanner.close();
    }
}
