public class HalvingExample {
    public static int halvingSearch(int n) {
        int count = 0;
        while (n > 1) {
            n = n / 2;  // Halve the value of n
            count++;    // Increment the count with each halving
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 1024;
        System.out.println("Number of halvings: " + halvingSearch(n));
    }
}



