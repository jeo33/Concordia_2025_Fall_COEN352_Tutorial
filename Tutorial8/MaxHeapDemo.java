public class MaxHeapDemo {
    
    public static void main(String[] args) throws Exception {
        System.out.println("=== MAX-PRIORITY QUEUE DEMONSTRATION ===\n");
        
        // Initial array of size 12
        int[] initialArray = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1, 6, 5};
        
        System.out.println("Initial array: ");
        printArray(initialArray);
        
        // Build max heap
        MaxHeap heap = new MaxHeap(initialArray);
        System.out.println("\nAfter building max heap:");
        heap.printArray();
        
        // Operation 1: Maximum
        System.out.println("\n--- Operation 1: MAXIMUM ---");
        int max = heap.maximum();
        System.out.println("Maximum element: " + max);
        System.out.println("Heap after maximum (unchanged):");
        heap.printArray();
        
        // Operation 2: Extract-Max
        System.out.println("\n--- Operation 2: EXTRACT-MAX ---");
        int extractedMax = heap.extractMax();
        System.out.println("Extracted maximum: " + extractedMax);
        System.out.println("Heap after extract-max:");
        heap.printArray();
        
        // Operation 3: Increase-Key
        System.out.println("\n--- Operation 3: INCREASE-KEY ---");
        System.out.println("Increasing key at index 11 from 6 to 15");
        heap.increaseKey(11, 15);
        System.out.println("Heap after increase-key:");
        heap.printArray();
        
        // Operation 4: Insert
        System.out.println("\n--- Operation 4: INSERT ---");
        System.out.println("Inserting 12 into heap");
        heap.insert(12);
        System.out.println("Heap after insert:");
        heap.printArray();
        
        System.out.println("\n=== DEMONSTRATION COMPLETE ===");
    }
    
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}

// Complete MaxHeap Implementation
class MaxHeap {
    private int[] heap;
    private int heapSize;
    private int capacity;
    
    // Constructor with initial array
    public MaxHeap(int[] arr) {
        this.heapSize = arr.length;
        this.capacity = arr.length * 2; // Extra capacity for inserts
        this.heap = new int[capacity + 1]; // 1-indexed
        
        // Copy array (1-indexed)
        for (int i = 0; i < arr.length; i++) {
            heap[i + 1] = arr[i];
        }
        
        // Build max heap
        buildMaxHeap();
    }
    
    // Build max heap from unordered array
    private void buildMaxHeap() {
        for (int i = heapSize / 2; i >= 1; i--) {
            maxHeapify(i);
        }
    }
    
    // Maintain max heap property at index i
    private void maxHeapify(int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest = i;
        
        // Find largest among node and its children
        if (left <= heapSize && heap[left] > heap[largest]) {
            largest = left;
        }
        if (right <= heapSize && heap[right] > heap[largest]) {
            largest = right;
        }
        
        // If largest is not the current node, swap and recurse
        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest);
        }
    }
    
    // Return maximum element (root)
    public int maximum() throws Exception {
        if (heapSize < 1) {
            throw new Exception("Heap underflow");
        }
        return heap[1];
    }
    
    // Extract and return maximum element
    public int extractMax() throws Exception {
        if (heapSize < 1) {
            throw new Exception("Heap underflow");
        }
        
        int max = heap[1];              // Save maximum
        heap[1] = heap[heapSize];       // Move last element to root
        heapSize--;                     // Reduce heap size
        maxHeapify(1);                  // Restore heap property
        return max;
    }
    
    // Increase value at index i to newKey
    public void increaseKey(int i, int newKey) throws Exception {
        if (newKey < heap[i]) {
            throw new Exception("New key is smaller than current key");
        }
        
        heap[i] = newKey;
        
        // Bubble up: compare with parent and swap if necessary
        while (i > 1 && heap[i / 2] < heap[i]) {
            swap(i, i / 2);
            i = i / 2;
        }
    }
    
    // Insert new element into heap
    public void insert(int key) throws Exception {
        heapSize++;
        
        if (heapSize > capacity) {
            throw new Exception("Heap overflow");
        }
        
        // Insert with minimum value
        heap[heapSize] = Integer.MIN_VALUE;
        
        // Use increaseKey to place it correctly
        increaseKey(heapSize, key);
    }
    
    // Swap two elements in heap
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    // Print entire heap array
    public void printArray() {
        System.out.print("[");
        for (int i = 1; i <= heapSize; i++) {
            System.out.print(heap[i]);
            if (i < heapSize) System.out.print(", ");
        }
        System.out.println("]");
    }
}
