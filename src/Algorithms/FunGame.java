package Algorithms;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FunGame {

    // Complete the funGame function below.
    static String funGame(int[] a, int[] b) {

        // create a List marking all indices that have not been guessed yet
        final List<Integer> remainingIndices = new ArrayList<>();
        for (int i=0; i<a.length; i++) {
            remainingIndices.add(i);
        }

        int[] aMinusB = new int[a.length];
        int[] bMinusA = new int[a.length];
        for (int i=0; i<a.length; i++) {
            aMinusB[i] = a[i] - b[i];
            bMinusA[i] = b[i] - a[i];
        }

        int p1Score = 0, p2Score = 0;
        for (int i=0; i<a.length; i++) {
            final int bestMoveForP1 = findIndexOfMaxRemainingAvailable(remainingIndices, aMinusB);
            final int bestMoveForP2 = findIndexOfMaxRemainingAvailable(remainingIndices, bMinusA);
            if (i % 2 == 0) {
                int indexOfP1Move = -1;
                if (aMinusB[bestMoveForP1] > bMinusA[bestMoveForP2]) {
                    indexOfP1Move = bestMoveForP1;
                }
                else {
                    indexOfP1Move = bestMoveForP2;
                }
                p1Score += a[indexOfP1Move];
                remainingIndices.remove((Integer)indexOfP1Move);
            }
            else {
                int indexOfP2Move = -1;
                if (bMinusA[bestMoveForP2] > aMinusB[bestMoveForP1]) {
                    indexOfP2Move = bestMoveForP2;
                }
                else {
                    indexOfP2Move = bestMoveForP1;
                }
                p2Score += b[indexOfP2Move];
                remainingIndices.remove((Integer)indexOfP2Move);
            }
        }

        if (p1Score > p2Score) {
            return "First";
        }
        else if (p2Score > p1Score) {
            return "Second";
        }
        else {
            return "Tie";
        }
    }

    /**
     * Unoptimized method to find the index with the max value in arr among
     * the available indices in availableIndices
     */
    static int findIndexOfMaxRemainingAvailable(List<Integer> availableIndices, int[] arr) {
        int maxValue = Integer.MIN_VALUE;
        int indexOfMaxValue = -1;
        for (int index : availableIndices) {
            int value = arr[index];
            if (value > maxValue) {
                maxValue = value;
                indexOfMaxValue = index;
            }
        }
        return indexOfMaxValue;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] a = new int[n];

            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int aItem = Integer.parseInt(aItems[i]);
                a[i] = aItem;
            }

            int[] b = new int[n];

            String[] bItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int bItem = Integer.parseInt(bItems[i]);
                b[i] = bItem;
            }

            String result = funGame(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
