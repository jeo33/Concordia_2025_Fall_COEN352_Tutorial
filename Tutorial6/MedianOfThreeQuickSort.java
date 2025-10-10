import java.util.Random;
import java.util.Arrays;

public class MedianOfThreeQuickSort {
    private static Random rand = new Random();
    
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        // Find median of three: first, middle, last
        int mid = left + (right - left) / 2;
        
        // Get the median index
        int medianIndex = getMedianOfThree(arr, left, mid, right);
        
        // Swap median with last element (pivot position)
        int temp = arr[medianIndex];
        arr[medianIndex] = arr[right];
        arr[right] = temp;
        
        // Standard partition with median as pivot
        int pivot = arr[right];
        int i = left - 1;
        
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;
        
        return i + 1;
    }
    
    // Find index of median among three elements
    public static int getMedianOfThree(int[] arr, int left, int mid, int right) {
        int a = arr[left];
        int b = arr[mid];
        int c = arr[right];
        
        // Find median value and return its index
        if ((a <= b && b <= c) || (c <= b && b <= a)) {
            return mid;  // b is median
        } else if ((b <= a && a <= c) || (c <= a && a <= b)) {
            return left; // a is median
        } else {
            return right; // c is median
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("Original array:");
        printArray(arr);
        
        quickSort(arr, 0, arr.length - 1);
        
        System.out.println("Sorted array:");
        printArray(arr);
    }
    
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}