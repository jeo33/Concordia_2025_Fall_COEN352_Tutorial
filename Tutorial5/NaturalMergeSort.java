import java.util.Arrays;

public class NaturalMergeSort {

    public static void naturalMergeSort(int[] array) {
        int n = array.length;

        // Repeat until the entire array is sorted in a single run
        while (true) {
            int start = 0;
            boolean merged = false;

            while (start < n) {
                // Find the first run
        
        System.out.println("before:   " + Arrays.toString(array));
                int mid = findRun(array, start, n);
                if (mid >= n - 1) break; // Only one run left — sorted

                // Find the second run
                int end = findRun(array, mid + 1, n);

                // Merge the two runs
                merge(array, start, mid, end);
                merged = true;

                System.out.println("start"+start);
                System.out.println("mid"+mid);
                System.out.println("end"+end);
                // Move to the next pair of runs
                start = end + 1;
        
        System.out.println("after array:   " + Arrays.toString(array));
            }

            if (!merged) break; // No merges done — array is sorted
        }
    }

    // Finds the end index of a run starting from 'start'
    private static int findRun(int[] array, int start, int n) {
        int i = start;
        while (i + 1 < n && array[i] <= array[i + 1]) {
            i++;
        }
        return i;
    }

    // Merges two sorted subarrays: array[start..mid] and array[mid+1..end]
    private static void merge(int[] array, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];

        int i = start;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= mid) temp[k++] = array[i++];
        while (j <= end) temp[k++] = array[j++];

        // Copy merged result back to original array
        System.arraycopy(temp, 0, array, start, temp.length);
    }

    // Testing the algorithm
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 4, 6, 0, 8};
        System.out.println("Original array: " + Arrays.toString(arr));

        naturalMergeSort(arr);

        System.out.println("Sorted array:   " + Arrays.toString(arr));
    }
}

