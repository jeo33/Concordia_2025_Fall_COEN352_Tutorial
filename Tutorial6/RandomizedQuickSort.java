import java.util.Random;

public class RandomizedQuickSort {
    private static Random rand = new Random();
    
    public static int randomizedPartition(int[] A, int p, int r) {
        int i = p + rand.nextInt(r - p + 1);
        
        int temp = A[r];
        A[r] = A[i];
        A[i] = temp;
        
        return partition(A, p, r);
    }
    
    public static void randomizedQuickSort(int[] A, int p, int r) {
        if (p < r) {
            int q = randomizedPartition(A, p, r);
            randomizedQuickSort(A, p, q - 1);
            randomizedQuickSort(A, q + 1, r);
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
        
        randomizedQuickSort(arr, 0, arr.length - 1);
        
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