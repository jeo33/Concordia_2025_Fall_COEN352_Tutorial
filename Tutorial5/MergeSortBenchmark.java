import java.util.Arrays;
import java.util.Random;

public class MergeSortBenchmark {

    // Global counters
    static long naturalComparisons = 0;
    static long regularComparisons = 0;

    // ---------------- Natural Merge Sort ----------------
    public static void naturalMergeSort(int[] array) {
        int n = array.length;
        naturalComparisons = 0;

        while (true) {
            int start = 0;
            boolean merged = false;

            while (start < n) {
                int mid = findRun(array, start, n);
                if (mid >= n - 1) break; // only one run left

                int end = findRun(array, mid + 1, n);
                mergeNatural(array, start, mid, end);
                merged = true;
                start = end + 1;
            }

            if (!merged) break;
        }
    }

    private static int findRun(int[] array, int start, int n) {
        int i = start;
        while (i + 1 < n && array[i] <= array[i + 1]) {
            naturalComparisons++; // comparison in findRun
            i++;
        }
        return i;
    }

    private static void mergeNatural(int[] array, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;

        while (i <= mid && j <= end) {
            naturalComparisons++;
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= mid) temp[k++] = array[i++];
        while (j <= end) temp[k++] = array[j++];

        System.arraycopy(temp, 0, array, start, temp.length);
    }

    // ---------------- Regular Merge Sort ----------------
    public static void regularMergeSort(int[] array) {
        regularComparisons = 0;
        regularMergeSort(array, 0, array.length - 1);
    }

    private static void regularMergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            regularMergeSort(array, left, mid);
            regularMergeSort(array, mid + 1, right);
            mergeRegular(array, left, mid, right);
        }
    }

    private static void mergeRegular(int[] array, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;

        while (i <= mid && j <= end) {
            regularComparisons++;
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= mid) temp[k++] = array[i++];
        while (j <= end) temp[k++] = array[j++];

        System.arraycopy(temp, 0, array, start, temp.length);
    }

    // ---------------- Benchmark ----------------
    public static void benchmark(int[] arr) {
        System.out.println("Original array: " + Arrays.toString(arr));

        // Natural Merge Sort
        int[] copy1 = Arrays.copyOf(arr, arr.length);
        long startTime = System.nanoTime();
        naturalMergeSort(copy1);
        long duration = System.nanoTime() - startTime;
        System.out.println("NaturalMergeSort result: " + Arrays.toString(copy1));
        System.out.println("Time: " + duration + " ns");
        System.out.println("Comparisons: " + naturalComparisons);

        // Regular Merge Sort
        int[] copy2 = Arrays.copyOf(arr, arr.length);
        startTime = System.nanoTime();
        regularMergeSort(copy2);
        duration = System.nanoTime() - startTime;
        System.out.println("RegularMergeSort result: " + Arrays.toString(copy2));
        System.out.println("Time: " + duration + " ns");
        System.out.println("Comparisons: " + regularComparisons);

        System.out.println("-------------------------------------");
    }

    // ---------------- Main ----------------
    public static void main(String[] args) {
        // Small test array
        int[] arr = {1, 3, 5, 2, 4, 6, 0, 8};
        benchmark(arr);

    }
}
