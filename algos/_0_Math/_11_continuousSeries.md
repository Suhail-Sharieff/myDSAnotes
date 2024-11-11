## Cool Ideas for CP Problems of Type Requiring Observation of Numbers from 1 to n

### Problem 1: Can You Choose K Distinct Numbers Whose Sum is X?

**Given** three positive integers: `n`, `k`, and `x`. Can you choose any `k` distinct numbers between `[1, n]` such that their sum equals `x`?

#### Solution:

To solve this problem, observe the following:

- The **maximum sum** we can get is by choosing the last `k` elements from the range `[1, n]`.
- The **minimum sum** is the sum of the first `k` elements in `[1, n]`.

Thus, we can make the decision based on whether the target `x` lies between these two sums:
- If `x` lies between the **minimum sum** and the **maximum sum**, the answer is always "Yes".
- Otherwise, the answer is "No".

**Key insight**: The answer is "Yes" if and only if the target sum `x` lies in the range `[minSum, maxSum]`.

### Example:

- Let `n = 10`, `k = 3`, and `x = 18`.
  - The maximum sum is obtained by choosing the last 3 elements: `8 + 9 + 10 = 27`.
  - The minimum sum is obtained by choosing the first 3 elements: `1 + 2 + 3 = 6`.
  - Since `18` lies between `6` and `27`, the answer is "Yes".



This problem is an example of how understanding the constraints and the properties of numbers in the range `[1, n]` can help reduce the complexity of the problem by eliminating the need to check all combinations directly.


---

