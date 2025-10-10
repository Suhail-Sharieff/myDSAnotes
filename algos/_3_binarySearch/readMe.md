## When to Use Binary Search

Binary Search is a powerful algorithm with specific use cases. Here are scenarios where Binary Search can be effectively applied:

1. **Known Range for Solution**
   - When you are certain that the solution lies within a specific range `[x, y]`, you can initialize `low = x`, `high = y`, and `mid = (low + high) / 2`. Depending on the problem, `low`, `high`, and `mid` can be used as indices or values.

   **NOTE** 
   - Sometimes its important to note that sole binary search in log(n) cant solve the entire problem and may require nlog(n), which basically means that we need a helper func inside that binary search...ie for each possible values in our range we might want to first find if that value actually fulfills all cases, so we can create some helper function inside the binary search which would help us in that. **This pattern is usually used in problems having**
   some keywords like **maximize the minimum** or **even vice versa** or even just maximize something by splitting(see _20_splitArray.java), how to slplit array into K parts using some condition

2. **Potential Answer at Midpoint**
   - If the value at `nums[mid]` (or `mid` itself, depending on the problem) could be the answer, you can consider returning it. For example:
     - To find a minimum value, adjust `high = mid - 1` to check for lower values.
     - To find a maximum value, adjust `low = mid + 1` to check for higher values.

3. **Array with Sorted Subarrays**
   - Even if the entire array is not sorted, Binary Search can be used if the array is composed of sorted subarrays. The binary search logic can still apply to each sorted segment.

4. **Distinct Patterns in Array**
   - When there is a distinct pattern in the elements before and after the midpoint, Binary Search can be utilized to exploit these patterns for efficient searching.


5. **Dividing arrays into k parts such that somethng is mimimal or maximal**
   - Try thinking of BS



   ## Revision Set
   - [BS in rotated array](/algos/_3_binarySearch/_5_searchInRotattedAr.java), [Find min elemnt in rotated sorted](/algos/_3_binarySearch/_6_findMinInRotArr.java), how smartly u can find things in `logN` time
   - [Basic BS pattern](/algos/_3_binarySearch/_13_kokoBananas.java), this is most common pattern usually asy interviews ask
   - [Kth missing number](/algos/_3_binarySearch/_16_kthMissing.java), watch mind blowing solution