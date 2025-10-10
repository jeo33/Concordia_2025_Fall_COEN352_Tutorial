import java.util.Random;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPlayground {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║        JAVA RANDOM FUNCTION PLAYGROUND           ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");
        
        // 1. Basic Random Usage
        basicRandomExamples();
        
        // 2. Random with Seeds
        randomWithSeeds();
        
        // 3. Different Random Number Ranges
        randomRanges();
        
        // 4. Random Boolean and Choices
        randomBooleanAndChoices();
        
        // 5. Random Arrays and Collections
        randomArrays();
        
        // 6. ThreadLocalRandom (faster for concurrent use)
        threadLocalRandomExamples();
        
        // 7. Math.random() vs Random
        mathRandomVsRandom();
        
        // 8. Practical Applications
        practicalApplications();
    }
    
    // 1. Basic Random Usage
    public static void basicRandomExamples() {
        System.out.println("\n┌─── 1. BASIC RANDOM USAGE ───┐\n");
        
        Random rand = new Random();
        
        System.out.println("Random integers (unbounded):");
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + rand.nextInt());
        }
        
        System.out.println("\nRandom integers (0 to 99):");
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + rand.nextInt(100));
        }
        
        System.out.println("\nRandom doubles (0.0 to 1.0):");
        for (int i = 0; i < 5; i++) {
            System.out.printf("  %.6f%n", rand.nextDouble());
        }
        
        System.out.println("\nRandom floats (0.0 to 1.0):");
        for (int i = 0; i < 5; i++) {
            System.out.printf("  %.6f%n", rand.nextFloat());
        }
        
        System.out.println("\nRandom longs:");
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + rand.nextLong());
        }
        
        System.out.println("\nRandom Gaussian (mean=0, std=1):");
        for (int i = 0; i < 5; i++) {
            System.out.printf("  %.6f%n", rand.nextGaussian());
        }
    }
    
    // 2. Random with Seeds
    public static void randomWithSeeds() {
        System.out.println("\n\n┌─── 2. RANDOM WITH SEEDS (Reproducible) ───┐\n");
        
        long seed = 12345L;
        
        System.out.println("First run with seed " + seed + ":");
        Random rand1 = new Random(seed);
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + rand1.nextInt(100));
        }
        
        System.out.println("\nSecond run with SAME seed " + seed + ":");
        Random rand2 = new Random(seed);
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + rand2.nextInt(100));
        }
        
        System.out.println("\n→ Notice: Same seed produces same sequence!");
        
        System.out.println("\nThird run with DIFFERENT seed:");
        Random rand3 = new Random(54321L);
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + rand3.nextInt(100));
        }
    }
    
    // 3. Different Random Number Ranges
    public static void randomRanges() {
        System.out.println("\n\n┌─── 3. DIFFERENT RANDOM RANGES ───┐\n");
        
        Random rand = new Random();
        
        // Range: 1 to 10 (inclusive)
        System.out.println("Random numbers from 1 to 10:");
        for (int i = 0; i < 10; i++) {
            int num = rand.nextInt(10) + 1;
            System.out.print(num + " ");
        }
        
        // Range: 50 to 100 (inclusive)
        System.out.println("\n\nRandom numbers from 50 to 100:");
        for (int i = 0; i < 10; i++) {
            int num = rand.nextInt(51) + 50;
            System.out.print(num + " ");
        }
        
        // Range: -50 to 50
        System.out.println("\n\nRandom numbers from -50 to 50:");
        for (int i = 0; i < 10; i++) {
            int num = rand.nextInt(101) - 50;
            System.out.print(num + " ");
        }
        
        // Random double in range [5.0, 10.0]
        System.out.println("\n\nRandom doubles from 5.0 to 10.0:");
        for (int i = 0; i < 5; i++) {
            double num = 5.0 + (10.0 - 5.0) * rand.nextDouble();
            System.out.printf("%.2f ", num);
        }
        System.out.println();
    }
    
    // 4. Random Boolean and Choices
    public static void randomBooleanAndChoices() {
        System.out.println("\n\n┌─── 4. RANDOM BOOLEAN AND CHOICES ───┐\n");
        
        Random rand = new Random();
        
        System.out.println("Random booleans (coin flips):");
        int heads = 0, tails = 0;
        for (int i = 0; i < 20; i++) {
            boolean flip = rand.nextBoolean();
            System.out.print((flip ? "H" : "T") + " ");
            if (flip) heads++;
            else tails++;
        }
        System.out.println("\nHeads: " + heads + ", Tails: " + tails);
        
        // Random choice from array
        System.out.println("\n\nRandom fruit selection:");
        String[] fruits = {"Apple", "Banana", "Orange", "Grape", "Mango"};
        for (int i = 0; i < 8; i++) {
            int index = rand.nextInt(fruits.length);
            System.out.println("  " + fruits[index]);
        }
        
        // Random character
        System.out.println("\nRandom uppercase letters:");
        for (int i = 0; i < 15; i++) {
            char c = (char) (rand.nextInt(26) + 'A');
            System.out.print(c + " ");
        }
        System.out.println();
    }
    
    // 5. Random Arrays and Collections
    public static void randomArrays() {
        System.out.println("\n\n┌─── 5. RANDOM ARRAYS ───┐\n");
        
        Random rand = new Random();
        
        // Fill array with random numbers
        System.out.println("Array filled with random numbers (1-100):");
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100) + 1;
        }
        System.out.println(Arrays.toString(arr));
        
        // Shuffle an array
        System.out.println("\nOriginal ordered array:");
        int[] orderedArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(orderedArr));
        
        shuffleArray(orderedArr);
        System.out.println("Shuffled array:");
        System.out.println(Arrays.toString(orderedArr));
        
        // Random unique numbers (no duplicates)
        System.out.println("\nRandom unique numbers (1-20, pick 10):");
        int[] uniqueNumbers = generateUniqueRandomNumbers(1, 20, 10);
        System.out.println(Arrays.toString(uniqueNumbers));
    }
    
    // 6. ThreadLocalRandom
    public static void threadLocalRandomExamples() {
        System.out.println("\n\n┌─── 6. THREADLOCALRANDOM (Better Performance) ───┐\n");
        
        System.out.println("Random integers (0-99):");
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + ThreadLocalRandom.current().nextInt(100));
        }
        
        System.out.println("\nRandom integers in range (50-100):");
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + ThreadLocalRandom.current().nextInt(50, 101));
        }
        
        System.out.println("\nRandom doubles in range (5.0-10.0):");
        for (int i = 0; i < 5; i++) {
            System.out.printf("  %.2f%n", ThreadLocalRandom.current().nextDouble(5.0, 10.0));
        }
    }
    
    // 7. Math.random() vs Random
    public static void mathRandomVsRandom() {
        System.out.println("\n\n┌─── 7. MATH.RANDOM() VS RANDOM ───┐\n");
        
        System.out.println("Using Math.random() [0.0 to 1.0):");
        for (int i = 0; i < 5; i++) {
            System.out.printf("  %.6f%n", Math.random());
        }
        
        System.out.println("\nUsing Math.random() for integers (1-100):");
        for (int i = 0; i < 5; i++) {
            int num = (int) (Math.random() * 100) + 1;
            System.out.println("  " + num);
        }
        
        System.out.println("\nUsing Random class (1-100):");
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + (rand.nextInt(100) + 1));
        }
        
        System.out.println("\n→ Random class is more flexible and efficient!");
    }
    
    // 8. Practical Applications
    public static void practicalApplications() {
        System.out.println("\n\n┌─── 8. PRACTICAL APPLICATIONS ───┐\n");
        
        Random rand = new Random();
        
        // Dice roll
        System.out.println("Rolling two dice:");
        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        System.out.println("  Die 1: " + die1);
        System.out.println("  Die 2: " + die2);
        System.out.println("  Total: " + (die1 + die2));
        
        // Random password generator
        System.out.println("\nRandom password (8 characters):");
        System.out.println("  " + generateRandomPassword(8));
        
        // Random color (RGB)
        System.out.println("\nRandom RGB color:");
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        System.out.printf("  RGB(%d, %d, %d)%n", r, g, b);
        System.out.printf("  Hex: #%02X%02X%02X%n", r, g, b);
        
        // Weighted random (probability)
        System.out.println("\nWeighted random (70% A, 20% B, 10% C):");
        int countA = 0, countB = 0, countC = 0;
        for (int i = 0; i < 100; i++) {
            String result = weightedRandom();
            if (result.equals("A")) countA++;
            else if (result.equals("B")) countB++;
            else countC++;
        }
        System.out.println("  A: " + countA + "%");
        System.out.println("  B: " + countB + "%");
        System.out.println("  C: " + countC + "%");
    }
    
    // Helper method: Shuffle array
    public static void shuffleArray(int[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    
    // Helper method: Generate unique random numbers
    public static int[] generateUniqueRandomNumbers(int min, int max, int count) {
        Random rand = new Random();
        int[] result = new int[count];
        boolean[] used = new boolean[max - min + 1];
        
        for (int i = 0; i < count; i++) {
            int num;
            do {
                num = rand.nextInt(max - min + 1) + min;
            } while (used[num - min]);
            
            used[num - min] = true;
            result[i] = num;
        }
        
        return result;
    }
    
    // Helper method: Random password generator
    public static String generateRandomPassword(int length) {
        Random rand = new Random();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(rand.nextInt(chars.length())));
        }
        
        return password.toString();
    }
    
    // Helper method: Weighted random
    public static String weightedRandom() {
        Random rand = new Random();
        int num = rand.nextInt(100);
        
        if (num < 70) return "A";
        else if (num < 90) return "B";
        else return "C";
    }
}