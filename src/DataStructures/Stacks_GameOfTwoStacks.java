package DataStructures;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Stacks_GameOfTwoStacks {

    /*
     * Complete the twoStacks function below.
     */
    static int twoStacks(int x, int[] a, int[] b) {
        /*
         * Third implementation that computes the sum up to each index in both
         * A and B arrays, then iterates through the A_SUM array and counts number of
         * moves into B_SUM array that it could use before exceeding the max
         */

        // sanity check on input stacks
        if (a == null || b == null || a.length == 0 || b.length == 0
                || (a[0] > x && b[0] > x)) {
            return 0;
        }

        // fill the aSum and BSum arrays with the sum up to each index
        // e.g. aSums[i] = a[0] + a[1] + ... + a[i]
        final int[] aSums = new int[a.length];
        final int[] bSums = new int[b.length];
        for (int i=0; i<a.length; i++) {
            final int aSum = a[i] + (i == 0 ? 0 : aSums[i-1]);
            aSums[i] = aSum;
            if (aSum > x) {
                break;
            }
        }
        for (int i=0; i<b.length; i++) {
            final int bSum = b[i] + (i == 0 ? 0 : bSums[i-1]);
            bSums[i] = bSum;
            if (bSum > x) {
                break;
            }
        }

        int maxMoves = 0;
        for (int i=0; i<=a.length; i++) {
            final int aSum = i == 0 ? 0 : aSums[i-1];
            int numMoves = i;
            if (aSum > x) {
                break;
            }
            else if (aSum != x) {
                for (int j = 0; j < b.length; j++) {
                    final int bSum = bSums[j];
                    if (aSum + bSum  <= x) {
                        numMoves++;
                    } else {
                        break;
                    }
                }
            }
            if (numMoves >= maxMoves) {
                maxMoves = numMoves;
            }
        }

        return maxMoves;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int g = Integer.parseInt(scanner.nextLine().trim());

        for (int gItr = 0; gItr < g; gItr++) {
            String[] nmx = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmx[0].trim());

            int m = Integer.parseInt(nmx[1].trim());

            int x = Integer.parseInt(nmx[2].trim());

            int[] a = new int[n];

            String[] aItems = scanner.nextLine().split(" ");

            for (int aItr = 0; aItr < n; aItr++) {
                int aItem = Integer.parseInt(aItems[aItr].trim());
                a[aItr] = aItem;
            }

            int[] b = new int[m];

            String[] bItems = scanner.nextLine().split(" ");

            for (int bItr = 0; bItr < m; bItr++) {
                int bItem = Integer.parseInt(bItems[bItr].trim());
                b[bItr] = bItem;
            }

            int result = twoStacks(x, a, b);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
