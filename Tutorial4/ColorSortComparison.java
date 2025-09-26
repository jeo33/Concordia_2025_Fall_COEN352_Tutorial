package Tutorial4;

import java.util.Arrays;

public class ColorSortComparison {

    public static void main(String[] args) {
        String[] colors1 = {" blue ", "red", " blue ", " green ", " green ", "red"};

        System.out.println("Original colors1: " + Arrays.toString(colors1));

        // Method 1: Dutch National Flag (3-color) sort
        String[] dutchSorted = colors1.clone();
        dutchNationalFlagSort(dutchSorted);
        System.out.println("\nSorted with Dutch National Flag: " + Arrays.toString(dutchSorted));

        // Method 2: Bubble Sort
        String[] bubbleSorted = colors1.clone();
        bubbleSort(bubbleSorted);
        System.out.println("\nSorted with Bubble Sort: " + Arrays.toString(bubbleSorted));
    }

    // Dutch National Flag sort for "red", " green ", " blue "
    public static void dutchNationalFlagSort(String[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;

        while (mid <= high) {
            String color = arr[mid];
            if (color.equals("red")) {
                swap(arr, low, mid);
                low++;
                mid++;
            } else if (color.equals(" green ")) {
                mid++;
            } else if (color.equals(" blue ")) {
                swap(arr, mid, high);
                high--;
            }
        }
    }

    // Bubble sort for Strings
    public static void bubbleSort(String[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) < 0) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break; // Optimization: stop if already sorted
        }
    }

    // Helper swap method
    public static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
