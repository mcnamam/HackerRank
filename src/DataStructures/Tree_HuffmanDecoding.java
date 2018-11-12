package DataStructures;

public class Tree_HuffmanDecoding {

   // Node class provided by HackerRank
   class Node {
       public int frequency; // the frequency of this tree
       public char data;
       public Node left, right;
   }

    private int index=0;
    void decode(String s, Node root)
    {
        final StringBuffer sb = new StringBuffer();
        if (root == null || s.length() == 0) {
            return;
        }
        while (index < s.length()) {
            sb.append(getChar(s, root));
        }
        System.out.println(sb.toString());
    }

    char getChar(String s, Node root) {
        if (root == null || s.length() == 0) {
            return ' ';
        }
        if (root.data != '\0') {
            return root.data;
        }
        if (s.charAt(index) == '0') {
            index++;
            return getChar(s, root.left);
        } else {
            index++;
            return getChar(s, root.right);
        }
    }

}
