package DataStructures;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Stacks_LargestRectangle {

    /** This function will be used recursively to partition the array around
     *   the minimum height and compute the largest rectangles for three situations:
     *    a) minimum height * width of entire array h
     *    b) largest rect formed for sub-array to the left of the min height
     *    c) largest rect formed for sub-array to the right of the min height
     *   then picks the largest of these three
     */
    static long largestRectangle(int[] h) {
        // base case, empty array has rectangle of size 0
        if (h.length == 0) {
            return 0;
        }
        // secondary base case, single-cell array has rectangle
        // with size = cell'currentText value
        if (h.length == 1) {
            return h[0];
        }
        // find the index of the lowest height in the array
        int indexOfMin = findIndexOfMin(h);
        // sanity check before proceeding, should not occur
        if (indexOfMin == -1) {
            return 0;
        }
        // case a) minimum height * width of entire array
        int minHeight = h[indexOfMin];
        int largestRectangle = minHeight * h.length;

        // cases b) and c)
        // partition the array into left and right of the minimum
        // and calculate the largest rectangles in each partition
        int[] leftArr = Arrays.copyOfRange(h,0,indexOfMin);
        int[] rightArr = Arrays.copyOfRange(h,indexOfMin+1,h.length);
        int leftLargestRectangle = (int)largestRectangle(leftArr);
        int rightLargestRectangle = (int)largestRectangle(rightArr);

        // if either left or right partition'currentText largest rectangle
        // is larger than case a), update the value
        if (leftLargestRectangle > largestRectangle) {
            largestRectangle = leftLargestRectangle;
        }
        if (rightLargestRectangle > largestRectangle) {
            largestRectangle = rightLargestRectangle;
        }

        return (long) largestRectangle;
    }

    // iterative function to find the index of the smallest value in arr
    // could be replaced by a stream
    private static int findIndexOfMin(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }
        int min = arr[0];
        int indexOfMin = 0;
        for (int i =1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                indexOfMin = i;
            }
        }
        return indexOfMin;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] h = new int[n];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        long result = largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
