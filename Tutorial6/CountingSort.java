public class CountingSort {
    
    public static void countingSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        
        // Find the range of input values
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }
        
        int range = max - min + 1;
        
        // Create counting array
        int[] count = new int[range];
        
        // Store count of each element
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }
        
        // Modify count array to store actual positions
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        
        // Build output array
        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
        
        // Copy output array back to original array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        
        System.out.println("Original array:");
        printArray(arr);
        
        countingSort(arr);
        
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