import java.util.Random;
import java.util.Arrays;

public class QuickSortComparison {
    private static Random rand = new Random();
    private static int regularComparisons = 0;
    private static int regularSwaps = 0;
    private static int randomizedComparisons = 0;
    private static int randomizedSwaps = 0;
    private static int regularRecursionDepth = 0;
    private static int randomizedRecursionDepth = 0;
    private static int regularMaxDepth = 0;
    private static int randomizedMaxDepth = 0;
    
    // Regular QuickSort
    public static void regularQuickSort(int[] arr, int left, int right, int depth) {
        regularRecursionDepth = depth;
        if (depth > regularMaxDepth) {
            regularMaxDepth = depth;
        }
        
        if (left < right) {
            System.out.println("\n--- Regular QuickSort (Depth: " + depth + ") ---");
            System.out.println("Sorting range [" + left + ", " + right + "]");
            System.out.print("Before partition: ");
            printRange(arr, left, right);
            
            int pivotIndex = regularPartition(arr, left, right);
            
            System.out.print("After partition:  ");
            printRange(arr, left, right);
            System.out.println("Pivot index: " + pivotIndex + ", Pivot value: " + arr[pivotIndex]);
            
            regularQuickSort(arr, left, pivotIndex - 1, depth + 1);
            regularQuickSort(arr, pivotIndex + 1, right, depth + 1);
        }
    }

    public static int regularPartition(int[] arr, int left, int right) {
        int pivot = arr[right];
        System.out.println("Pivot (last element): " + pivot);
        int i = left - 1;
        
        for (int j = left; j < right; j++) {
            regularComparisons++;
            if (arr[j] < pivot) {
                i++;
                regularSwaps++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        regularSwaps++;
        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;
        
        return i + 1;
    }
    
    // Randomized QuickSort
    public static void randomizedQuickSort(int[] arr, int left, int right, int depth) {
        randomizedRecursionDepth = depth;
        if (depth > randomizedMaxDepth) {
            randomizedMaxDepth = depth;
        }
        
        if (left < right) {
            System.out.println("\n--- Randomized QuickSort (Depth: " + depth + ") ---");
            System.out.println("Sorting range [" + left + ", " + right + "]");
            System.out.print("Before partition: ");
            printRange(arr, left, right);
            
            int pivotIndex = randomizedPartition(arr, left, right);
            
            System.out.print("After partition:  ");
            printRange(arr, left, right);
            System.out.println("Pivot index: " + pivotIndex + ", Pivot value: " + arr[pivotIndex]);
            
            randomizedQuickSort(arr, left, pivotIndex - 1, depth + 1);
            randomizedQuickSort(arr, pivotIndex + 1, right, depth + 1);
        }
    }

    public static int randomizedPartition(int[] arr, int left, int right) {
        int randomIndex = left + rand.nextInt(right - left + 1);
        System.out.println("Random pivot index: " + randomIndex + ", value: " + arr[randomIndex]);
        
        randomizedSwaps++;
        int temp = arr[randomIndex];
        arr[randomIndex] = arr[right];
        arr[right] = temp;
        
        int pivot = arr[right];
        int i = left - 1;
        
        for (int j = left; j < right; j++) {
            randomizedComparisons++;
            if (arr[j] < pivot) {
                i++;
                randomizedSwaps++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        randomizedSwaps++;
        temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;
        
        return i + 1;
    }
    
    public static void printRange(int[] arr, int left, int right) {
        System.out.print("[");
        for (int i = left; i <= right; i++) {
            System.out.print(arr[i]);
            if (i < right) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    public static void main(String[] args) {
        int[] originalArray = {64, 34, 25, 12, 22, 11, 90, 88, 45, 50};
        
        System.out.println("=====================================================");
        System.out.println("           QUICKSORT ALGORITHM COMPARISON            ");
        System.out.println("=====================================================");
        System.out.println("\nOriginal array: " + Arrays.toString(originalArray));
        System.out.println("Array size: " + originalArray.length);
        
        // Test Regular QuickSort
        int[] arr1 = originalArray.clone();
        System.out.println("\n\n████████████████████████████████████████████████████");
        System.out.println("        REGULAR QUICKSORT (Last Element Pivot)       ");
        System.out.println("████████████████████████████████████████████████████");
        
        long startTime = System.nanoTime();
        regularQuickSort(arr1, 0, arr1.length - 1, 0);
        long endTime = System.nanoTime();
        long regularTime = endTime - startTime;
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Final sorted array: " + Arrays.toString(arr1));
        System.out.println("=".repeat(50));
        
        // Test Randomized QuickSort
        int[] arr2 = originalArray.clone();
        System.out.println("\n\n████████████████████████████████████████████████████");
        System.out.println("       RANDOMIZED QUICKSORT (Random Pivot)          ");
        System.out.println("████████████████████████████████████████████████████");
        
        startTime = System.nanoTime();
        randomizedQuickSort(arr2, 0, arr2.length - 1, 0);
        endTime = System.nanoTime();
        long randomizedTime = endTime - startTime;
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Final sorted array: " + Arrays.toString(arr2));
        System.out.println("=".repeat(50));
        
        // Print Statistics
        System.out.println("\n\n╔════════════════════════════════════════════════════╗");
        System.out.println("║              PERFORMANCE STATISTICS                ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        
        System.out.println("\n┌─────────────────────────────────────────────────┐");
        System.out.println("│ Regular QuickSort (Last Element Pivot)          │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ Comparisons:        %-28d│%n", regularComparisons);
        System.out.printf("│ Swaps:              %-28d│%n", regularSwaps);
        System.out.printf("│ Max Recursion Depth: %-27d│%n", regularMaxDepth);
        System.out.printf("│ Execution Time:     %-20d ns    │%n", regularTime);
        System.out.println("└─────────────────────────────────────────────────┘");
        
        System.out.println("\n┌─────────────────────────────────────────────────┐");
        System.out.println("│ Randomized QuickSort (Random Pivot)             │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ Comparisons:        %-28d│%n", randomizedComparisons);
        System.out.printf("│ Swaps:              %-28d│%n", randomizedSwaps);
        System.out.printf("│ Max Recursion Depth: %-27d│%n", randomizedMaxDepth);
        System.out.printf("│ Execution Time:     %-20d ns    │%n", randomizedTime);
        System.out.println("└─────────────────────────────────────────────────┘");
        
        System.out.println("\n┌─────────────────────────────────────────────────┐");
        System.out.println("│ Comparison Summary                              │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ Comparison Difference: %-25d│%n", 
            Math.abs(regularComparisons - randomizedComparisons));
        System.out.printf("│ Swap Difference:       %-25d│%n", 
            Math.abs(regularSwaps - randomizedSwaps));
        System.out.printf("│ Depth Difference:      %-25d│%n", 
            Math.abs(regularMaxDepth - randomizedMaxDepth));
        
        String faster = regularTime < randomizedTime ? "Regular" : "Randomized";
        long timeDiff = Math.abs(regularTime - randomizedTime);
        System.out.printf("│ Faster Algorithm:      %-25s│%n", faster);
        System.out.printf("│ Time Difference:       %-20d ns │%n", timeDiff);
        System.out.println("└─────────────────────────────────────────────────┘");
        
        System.out.println("\n" + "=".repeat(53));
        System.out.println("Both arrays sorted correctly: " + 
            Arrays.equals(arr1, arr2));
        System.out.println("=".repeat(53));
    }
}