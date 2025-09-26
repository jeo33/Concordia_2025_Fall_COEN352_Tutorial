package Tutorial4;

import java.util.Arrays;

public class InsertionSortComparison {

    public static void main(String[] args) {
        int[] arr = {5,1,4,2,3};

        int[] regularArr = arr.clone();
        int[] binaryArr = arr.clone();

        System.out.println("Original array: " + Arrays.toString(arr));

        // Regular Insertion Sort
        int regularComparisons = insertionSort(regularArr);
        System.out.println("\nRegular Insertion Sort result: " + Arrays.toString(regularArr));
        System.out.println("Number of comparisons (regular): " + regularComparisons);

        // Binary Insertion Sort
        int binaryComparisons = binaryInsertionSort(binaryArr);
        System.out.println("\nBinary Insertion Sort result: " + Arrays.toString(binaryArr));
        System.out.println("Number of comparisons (binary): " + binaryComparisons);
    }

    // Regular Insertion Sort
    public static int insertionSort(int[] arr) {
        int comparisons = 0;

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            // Compare key with sorted portion
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }

        return comparisons;
    }

    // Binary Insertion Sort
    public static int binaryInsertionSort(int[] arr) {
        int comparisons = 0;

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];

            // Find location to insert using binary search
            int left = 0;
            int right = i - 1;
            int mid = 0;
            while (left <= right) {
                mid = (left + right) / 2;
                comparisons++;
                if (arr[mid] > key) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            // Shift elements to make room for key
            for (int j = i - 1; j >= left; j--) {
                arr[j + 1] = arr[j];
            }
            arr[left] = key;
        }

        return comparisons;
    }
}
