public class MaxValue {
    public static int findMax(int[] arr) {
        int max = arr[0];  // Assume the first element is the max
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];  // Update max if current element is greater
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        System.out.println("Max value: " + findMax(arr));
    }
}
