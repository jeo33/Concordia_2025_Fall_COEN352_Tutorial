import java.util.Stack;
import java.util.Scanner;

class Benchmark {
    
    private static String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    private static String cleanString(String s) {
        StringBuilder cleaned = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        return cleaned.toString();
    }
    
    public static boolean isPalindromeRecursive(String s) {
        String cleaned = cleanString(s);
        return checkPalindromeRecursive(cleaned, 0, cleaned.length() - 1);
    }
    
    private static boolean checkPalindromeRecursive(String str, int left, int right) {
        if (left >= right) {
            return true;
        }
        
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        
        return checkPalindromeRecursive(str, left + 1, right - 1);
    }
    
    public static boolean isPalindromeStack(String s) {
        String cleaned = cleanString(s);
        Stack<Character> stack = new Stack<Character>();
        int length = cleaned.length();
        
        int midPoint = length / 2;
        for (int i = 0; i < midPoint; i++) {
            stack.push(cleaned.charAt(i));
        }
        
        int startIndex = (length % 2 == 0) ? midPoint : midPoint + 1;
        for (int i = startIndex; i < length; i++) {
            if (stack.isEmpty() || stack.pop() != cleaned.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean isPalindromeTwoPointer(String s) {
        String cleaned = cleanString(s);
        int length = cleaned.length();
        
        for (int i = 0; i < length / 2; i++) {
            if (cleaned.charAt(i) != cleaned.charAt(length - 1 - i)) {
                return false;
            }
        }
        
        return true;
    }

    public static void testPalindromes() {
        String[] testCases = {
            "A man, a plan, a canal: Panama",
            "race a car",
            "",
            "a",
            "Madam",
            "No 'x' in Nixon",
            "Mr. Owl ate my metal worm",
            "hello world",
            "Was it a car or a cat I saw?",
            "12321"
        };
        
        System.out.println("Testing Palindrome Solutions in Java:\n");
        
        for (int i = 0; i < testCases.length; i++) {
            String test = testCases[i];
            boolean recursive = isPalindromeRecursive(test);
            boolean stack = isPalindromeStack(test);
            boolean twoPointer = isPalindromeTwoPointer(test);
            
            System.out.println("Test " + (i + 1) + ": \"" + test + "\"");
            System.out.println("  Cleaned: \"" + cleanString(test) + "\"");
            System.out.println("  Recursive:       " + recursive);
            System.out.println("  Stack:           " + stack);
            System.out.println("  Two-Pointer:     " + twoPointer);
            System.out.println();
        }
    }
    
    public static void performanceAnalysis() {
        System.out.println("Time and Space Complexity Analysis:");
        System.out.println("\n1. Recursive Approach:");
        System.out.println("   Time Complexity: O(n) - visits each character once");
        System.out.println("   Space Complexity: O(n) - recursion call stack");
        
        System.out.println("\n2. Stack Approach (Optimized):");
        System.out.println("   Time Complexity: O(n) - single pass through string");
        System.out.println("   Space Complexity: O(n/2) - stores half the characters");
        
        System.out.println("\n3. For Loop Approach (Head & Tail):");
        System.out.println("   Time Complexity: O(n/2) - only checks half the string");
        System.out.println("   Space Complexity: O(1) - only uses constant extra space");
        System.out.println("   Note: Most efficient approach!");
    }
    
    public static void benchmark(String testString, int iterations) {
        System.out.println("\nBenchmarking with string: \"" + testString + "\"");
        System.out.println("Iterations: " + iterations + "\n");
        
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            isPalindromeRecursive(testString);
        }
        long recursiveTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            isPalindromeStack(testString);
        }
        long stackTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            isPalindromeTwoPointer(testString);
        }
        long twoPointerTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Recursive:    " + recursiveTime + " ms");
        System.out.println("Stack:        " + stackTime + " ms");
        System.out.println("Two-Pointer:  " + twoPointerTime + " ms");
    }
    
    public static void main(String[] args) {
        System.out.println(repeat("=", 60));
        System.out.println("         VALID PALINDROME - COMPLETE TESTING");
        System.out.println(repeat("=", 60));
        
        testPalindromes();
        System.out.println(repeat("=", 60));
        performanceAnalysis();
        System.out.println(repeat("=", 60));
        benchmark("A man, a plan, a canal: Panama", 100000);
        System.out.println(repeat("=", 60));
        
        System.out.println("\nSample tests completed!");
        System.out.println("To test your own strings, modify the testCases array in the code.");
    }
}