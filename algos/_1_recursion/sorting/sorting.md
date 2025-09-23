# Sorting Analysis

## Merge Sort
- Recursively divides array into 2 parts, backtrack and merge the subarrays.
- Time Complexity:  
  - Best, Average, Worst: `O(N log N)`  
  - Reason: Dividing takes `O(log N)`, merging step takes `O(N)`.
- Space Complexity: `O(N)` because we need an auxiliary array for merging.  
- **Not in-place** (needs extra space).  
- **Stable** because relative order of equal elements is preserved.  
- **Interview Notes:**  
  - Often used in external sorting (sorting huge files).  
  - Good for linked lists (since merging is easy).  
  - Stable alternative to Quick Sort when stability is required.

---

## Quick Sort
- Divide-and-conquer algorithm: choose a **pivot**, partition elements around it, then recursively sort partitions.
- Time Complexity:  
  - Best/Average: `O(N log N)`  
  - Worst: `O(N^2)` (when pivot is always smallest/largest, e.g., already sorted array).  
- Space Complexity: `O(log N)` for recursion stack (if tail recursion optimized).  
- **In-place** sorting.  
- **Not stable** unless modified.  
- **Interview Notes:**  
  - Preferred in practice due to cache efficiency.  
  - Randomized Quick Sort avoids worst-case scenario.  
  - Used in many standard library implementations (`qsort`, `std::sort`).

---

## Insertion Sort
- Builds sorted array one element at a time by inserting the current element into its correct position.  
- Time Complexity:  
  - Best: `O(N)` (when array is already sorted).  
  - Average/Worst: `O(N^2)`.  
- Space Complexity: `O(1)` (in-place).  
- **Stable** because equal elements preserve their order.  
- **Interview Notes:**  
  - Efficient for small arrays or nearly sorted arrays.  
  - Used as a fallback in hybrid algorithms like **Timsort** or **IntroSort**.  
  - Good for teaching and explaining sorting intuitively.

---

## Bubble Sort
- Repeatedly compares adjacent elements and swaps if they are in the wrong order.  
- Time Complexity:  
  - Best: `O(N)` (if already sorted with optimization).  
  - Average/Worst: `O(N^2)`.  
- Space Complexity: `O(1)` (in-place).  
- **Stable** sorting.  
- **Interview Notes:**  
  - Rarely used in practice (inefficient).  
  - Sometimes asked to test understanding of sorting basics.  
  - Easy to code but not practical for large datasets.

---

## Heap Sort
- Uses a binary heap to sort elements.  
- Steps: Build a max-heap, repeatedly extract max and place it at the end.  
- Time Complexity:  
  - Best, Average, Worst: `O(N log N)`.  
- Space Complexity: `O(1)` (in-place).  
- **Not stable** because heap operations change element order.  
- **Interview Notes:**  
  - Useful when constant extra space is required.  
  - Typically slower than Quick Sort in practice due to poor cache locality.  
  - Good to know since it balances between in-place and guaranteed `O(N log N)` time.