public class ComplexFunction {

    public int complexFunction(int n) {
        int c = 0;
        // First loop with O(1) operation
        for (int i = 0; i < n; i++) {
            c++;
        }

        // Second loop with O(1) operation
        for (int j = 1; j < n; j = j * 2) {
            c++;
        }
        return c;
    }

    public static void main(String[] args) {
        ComplexFunction cf = new ComplexFunction();

        // Example with n = 10
        int n1 = 10;
        int result1 = cf.complexFunction(n1);
        System.out.println("For n = " + n1 + ", the value of c is: " + result1);
    }
}
