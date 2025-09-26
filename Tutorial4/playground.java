package Tutorial4;

public class playground {
    public static void main(String[] args) {
        String str1 = "Alice";
        String str2 = "Bob";

        System.out.printf("\"%s\".compareTo(\"%s\") = %d%n", str1, str2, str1.compareTo(str2));
        System.out.printf("\"%s\".compareTo(\"%s\") = %d%n", str2, str1, str2.compareTo(str1));
        System.out.printf("\"%s\".compareTo(\"%s\") = %d%n", str1, str1, str1.compareTo(str1));
        int a = 10;
        int b = 20;
        int c = 10;

        System.out.println("Comparing a and b(Integer.compare(a, b)): " + Integer.compare(a, b)); // -1
        System.out.println("Comparing b and a(Integer.compare(b, a)): " + Integer.compare(b, a)); // 1
        System.out.println("Comparing a and c(Integer.compare(a, c)): " + Integer.compare(a, c)); // 0
        // Extra explanation
        System.out.println("\nExplanation:");
        System.out.println("Negative → first string < second string");
        System.out.println("Positive → first string > second string");
        System.out.println("Zero     → both strings are equal");
    }
}
