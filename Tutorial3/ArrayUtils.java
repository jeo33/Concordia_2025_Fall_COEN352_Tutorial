public class ArrayUtils {

    // Method to reverse an array recursively
    public static void reverseArray(int[] arr, int start, int end) {
        // Base case: stop when start index meets or exceeds end index
        if (start >= end) {
            return;
        }

        // Swap elements at start and end
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;

        // Recur for the inner subarray
        reverseArray(arr, start + 1, end - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        System.out.println("Original array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        reverseArray(arr, 0, arr.length - 1);

        System.out.println("\nReversed array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
