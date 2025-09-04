public class FindPairs {
    
    // Function to find all pairs of equal elements in the array
    public static void findPairs(int[] arr) {
        // Outer loop to pick an element
        for (int i = 0; i < arr.length; i++) {
            // Inner loop to compare the selected element with all subsequent elements
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {  // Check if the elements are equal
                    System.out.println("Pair found: " + arr[i] + " and " + arr[j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 1, 4, 3};
        findPairs(arr);  // Calling the function to find pairs
    }
}
