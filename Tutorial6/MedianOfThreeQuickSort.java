import java.util.Random;

public class MedianOfThreeQuickSort {
    private static Random rand = new Random();
    
    public static int median3Partition(int[] A, int p, int r) {
        int mid = (p + r) / 2;
        
        int a = A[p];
        int b = A[mid];
        int c = A[r];
        
        int medianIndex;
        
        if ((a <= b && b <= c) || (c <= b && b <= a)) {
            medianIndex = mid;
        } else if ((b <= a && a <= c) || (c <= a && a <= b)) {
            medianIndex = p;
        } else {
            medianIndex = r;
        }
        
        int temp = A[r];
        A[r] = A[medianIndex];
        A[medianIndex] = temp;
        
        return partition(A, p, r);
    }
    
    public static void median3QuickSort(int[] A, int p, int r) {
        if (p < r) {
            int q = median3Partition(A, p, r);
            median3QuickSort(A, p, q - 1);
            median3QuickSort(A, q + 1, r);
        }
    }
    
    public static int partition(int[] A, int p, int r) {
        int pivot = A[r];
        int i = p - 1;
        
        for (int j = p; j < r; j++) {
            if (A[j] <= pivot) {
                i++;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        
        int temp = A[i + 1];
        A[i + 1] = A[r];
        A[r] = temp;
        
        return i + 1;
    }
    
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("Original array:");
        printArray(arr);
        
        median3QuickSort(arr, 0, arr.length - 1);
        
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