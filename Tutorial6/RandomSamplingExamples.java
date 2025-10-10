import java.util.*;
import java.util.stream.Collectors;

public class RandomSamplingExamples {
    private static Random rand = new Random();
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║            RANDOM SAMPLING IN JAVA               ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");
        
        // 1. Simple Random Sampling
        simpleRandomSampling();
        
        // 2. Sampling Without Replacement
        samplingWithoutReplacement();
        
        // 3. Sampling With Replacement
        samplingWithReplacement();
        
        // 4. Stratified Sampling
        stratifiedSampling();
        
        // 5. Weighted Random Sampling
        weightedRandomSampling();
        
        // 6. Reservoir Sampling
        reservoirSampling();
        
        // 7. Bootstrap Sampling
        bootstrapSampling();
    }
    
    // 1. Simple Random Sampling
    public static void simpleRandomSampling() {
        System.out.println("\n┌─── 1. SIMPLE RANDOM SAMPLING ───┐");
        System.out.println("Select k random items from population\n");
        
        int[] population = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int sampleSize = 5;
        
        System.out.println("Population: " + Arrays.toString(population));
        System.out.println("Sample size: " + sampleSize);
        
        int[] sample = simpleRandomSample(population, sampleSize);
        System.out.println("Random sample: " + Arrays.toString(sample));
    }
    
    public static int[] simpleRandomSample(int[] population, int k) {
        List<Integer> list = new ArrayList<>();
        for (int num : population) {
            list.add(num);
        }
        Collections.shuffle(list);
        
        int[] sample = new int[k];
        for (int i = 0; i < k; i++) {
            sample[i] = list.get(i);
        }
        return sample;
    }
    
    // 2. Sampling Without Replacement
    public static void samplingWithoutReplacement() {
        System.out.println("\n\n┌─── 2. SAMPLING WITHOUT REPLACEMENT ───┐");
        System.out.println("Each item can be selected only once\n");
        
        String[] fruits = {"Apple", "Banana", "Orange", "Grape", "Mango", 
                          "Pineapple", "Strawberry", "Watermelon"};
        int sampleSize = 4;
        
        System.out.println("Population: " + Arrays.toString(fruits));
        System.out.println("Sample size: " + sampleSize);
        
        String[] sample = sampleWithoutReplacement(fruits, sampleSize);
        System.out.println("Sample (no duplicates): " + Arrays.toString(sample));
        
        // Try multiple samples to show different results
        System.out.println("\nMultiple samples:");
        for (int i = 0; i < 3; i++) {
            sample = sampleWithoutReplacement(fruits, sampleSize);
            System.out.println("  Sample " + (i+1) + ": " + Arrays.toString(sample));
        }
    }
    
    public static <T> T[] sampleWithoutReplacement(T[] population, int k) {
        List<T> list = new ArrayList<>(Arrays.asList(population));
        Collections.shuffle(list);
        
        @SuppressWarnings("unchecked")
        T[] sample = (T[]) java.lang.reflect.Array.newInstance(
            population.getClass().getComponentType(), k);
        
        for (int i = 0; i < k; i++) {
            sample[i] = list.get(i);
        }
        return sample;
    }
    
    // 3. Sampling With Replacement
    public static void samplingWithReplacement() {
        System.out.println("\n\n┌─── 3. SAMPLING WITH REPLACEMENT ───┐");
        System.out.println("Items can be selected multiple times\n");
        
        String[] colors = {"Red", "Blue", "Green", "Yellow", "Purple"};
        int sampleSize = 10;
        
        System.out.println("Population: " + Arrays.toString(colors));
        System.out.println("Sample size: " + sampleSize);
        
        String[] sample = sampleWithReplacement(colors, sampleSize);
        System.out.println("Sample (with possible duplicates): " + Arrays.toString(sample));
        
        // Count frequencies
        Map<String, Integer> frequency = new HashMap<>();
        for (String color : sample) {
            frequency.put(color, frequency.getOrDefault(color, 0) + 1);
        }
        System.out.println("\nFrequency count:");
        frequency.forEach((color, count) -> 
            System.out.println("  " + color + ": " + count));
    }
    
    public static <T> T[] sampleWithReplacement(T[] population, int k) {
        @SuppressWarnings("unchecked")
        T[] sample = (T[]) java.lang.reflect.Array.newInstance(
            population.getClass().getComponentType(), k);
        
        for (int i = 0; i < k; i++) {
            sample[i] = population[rand.nextInt(population.length)];
        }
        return sample;
    }
    
    // 4. Stratified Sampling
    public static void stratifiedSampling() {
        System.out.println("\n\n┌─── 4. STRATIFIED SAMPLING ───┐");
        System.out.println("Sample proportionally from each group/stratum\n");
        
        // Dataset with categories
        Map<String, List<String>> dataset = new HashMap<>();
        dataset.put("Freshmen", Arrays.asList("Alice", "Bob", "Charlie", "David"));
        dataset.put("Sophomores", Arrays.asList("Eve", "Frank", "Grace", "Henry", "Ivy", "Jack"));
        dataset.put("Juniors", Arrays.asList("Kate", "Liam", "Mia"));
        dataset.put("Seniors", Arrays.asList("Noah", "Olivia", "Peter", "Quinn", "Ruby"));
        
        System.out.println("Population by class:");
        dataset.forEach((grade, students) -> 
            System.out.println("  " + grade + " (" + students.size() + "): " + students));
        
        // Sample 50% from each stratum
        double samplingRate = 0.5;
        System.out.println("\nSampling rate: " + (samplingRate * 100) + "% from each class");
        
        Map<String, List<String>> stratifiedSample = stratifiedSample(dataset, samplingRate);
        
        System.out.println("\nStratified sample:");
        stratifiedSample.forEach((grade, students) -> 
            System.out.println("  " + grade + " (" + students.size() + "): " + students));
    }
    
    public static <K, V> Map<K, List<V>> stratifiedSample(Map<K, List<V>> data, double rate) {
        Map<K, List<V>> result = new HashMap<>();
        
        for (Map.Entry<K, List<V>> entry : data.entrySet()) {
            List<V> stratum = new ArrayList<>(entry.getValue());
            Collections.shuffle(stratum);
            
            int sampleSize = (int) Math.ceil(stratum.size() * rate);
            result.put(entry.getKey(), stratum.subList(0, sampleSize));
        }
        
        return result;
    }
    
    // 5. Weighted Random Sampling
    public static void weightedRandomSampling() {
        System.out.println("\n\n┌─── 5. WEIGHTED RANDOM SAMPLING ───┐");
        System.out.println("Items have different probabilities of selection\n");
        
        String[] items = {"Common", "Uncommon", "Rare", "Epic", "Legendary"};
        double[] weights = {50.0, 30.0, 15.0, 4.0, 1.0}; // probabilities
        
        System.out.println("Items with weights:");
        for (int i = 0; i < items.length; i++) {
            System.out.printf("  %s: %.1f%%\n", items[i], weights[i]);
        }
        
        System.out.println("\nSampling 1000 times:");
        Map<String, Integer> counts = new HashMap<>();
        for (String item : items) counts.put(item, 0);
        
        for (int i = 0; i < 1000; i++) {
            String selected = weightedRandomSample(items, weights);
            counts.put(selected, counts.get(selected) + 1);
        }
        
        System.out.println("\nActual distribution:");
        for (String item : items) {
            double percentage = (counts.get(item) / 1000.0) * 100;
            System.out.printf("  %s: %d times (%.1f%%)\n", 
                item, counts.get(item), percentage);
        }
    }
    
    public static <T> T weightedRandomSample(T[] items, double[] weights) {
        double totalWeight = 0;
        for (double w : weights) totalWeight += w;
        
        double randomValue = rand.nextDouble() * totalWeight;
        double cumulative = 0;
        
        for (int i = 0; i < items.length; i++) {
            cumulative += weights[i];
            if (randomValue <= cumulative) {
                return items[i];
            }
        }
        
        return items[items.length - 1];
    }
    
    // 6. Reservoir Sampling
    public static void reservoirSampling() {
        System.out.println("\n\n┌─── 6. RESERVOIR SAMPLING ───┐");
        System.out.println("Sample from stream of unknown size (Algorithm R)\n");
        
        // Simulate a stream of data
        int[] stream = new int[100];
        for (int i = 0; i < 100; i++) stream[i] = i + 1;
        
        int k = 10; // sample size
        System.out.println("Stream size: " + stream.length + " items");
        System.out.println("Sample size: " + k);
        
        int[] reservoir = reservoirSample(stream, k);
        System.out.println("Reservoir sample: " + Arrays.toString(reservoir));
        
        System.out.println("\nUseful for:");
        System.out.println("  - Sampling from large files");
        System.out.println("  - Streaming data");
        System.out.println("  - Unknown population size");
    }
    
    public static int[] reservoirSample(int[] stream, int k) {
        int[] reservoir = new int[k];
        
        // Fill reservoir with first k elements
        for (int i = 0; i < k; i++) {
            reservoir[i] = stream[i];
        }
        
        // Replace elements with gradually decreasing probability
        for (int i = k; i < stream.length; i++) {
            int j = rand.nextInt(i + 1);
            if (j < k) {
                reservoir[j] = stream[i];
            }
        }
        
        return reservoir;
    }
    
    // 7. Bootstrap Sampling
    public static void bootstrapSampling() {
        System.out.println("\n\n┌─── 7. BOOTSTRAP SAMPLING ───┐");
        System.out.println("Sample with replacement, same size as population\n");
        
        double[] data = {23.5, 27.8, 19.2, 31.4, 25.6, 28.9, 22.1, 26.3};
        
        System.out.println("Original data: " + Arrays.toString(data));
        System.out.println("Mean: " + calculateMean(data));
        
        System.out.println("\nBootstrap samples (used for statistical inference):");
        for (int i = 0; i < 5; i++) {
            double[] bootstrap = bootstrapSample(data);
            System.out.printf("  Sample %d: Mean = %.2f\n", 
                i + 1, calculateMean(bootstrap));
        }
        
        System.out.println("\nUseful for:");
        System.out.println("  - Estimating confidence intervals");
        System.out.println("  - Testing statistical hypotheses");
        System.out.println("  - Machine learning (bagging)");
    }
    
    public static double[] bootstrapSample(double[] data) {
        double[] sample = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            sample[i] = data[rand.nextInt(data.length)];
        }
        return sample;
    }
    
    public static double calculateMean(double[] data) {
        double sum = 0;
        for (double d : data) sum += d;
        return sum / data.length;
    }
}