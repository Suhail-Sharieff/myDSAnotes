# 1. Begger Theorm / Stars and Bars Theorm

We know that we can distribute `n` items `EQUALLY` among `k` memebers using $\binom{n}{k}$ ways. But now what if i also need to find ways to distribute unequally as well, we use $\binom{n+k-1}{k-1}$

[Watch Video](https://www.youtube.com/watch?v=Hs26z1SFINE)

# Example problem:
```text
 Distribute Candies Among Children II
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given two positive integers n and limit.

Return the total number of ways to distribute n candies among 3 children such that no child gets more than limit candies.

 

Example 1:

Input: n = 5, limit = 2
Output: 3
Explanation: There are 3 ways to distribute 5 candies such that no child gets more than 2 candies: (1, 2, 2), (2, 1, 2) and (2, 2, 1).
Example 2:

Input: n = 3, limit = 3
Output: 10
Explanation: There are 10 ways to distribute 3 candies such that no child gets more than 3 candies: (0, 0, 3), (0, 1, 2), (0, 2, 1), (0, 3, 0), (1, 0, 2), (1, 1, 1), (1, 2, 0), (2, 0, 1), (2, 1, 0) and (3, 0, 0).
 

Constraints:

1 <= n <= 106
1 <= limit <= 106
```

This problem also can be solved using `DP recurrence`:
```java
static int rec(int nCandis,int limit,int level){
        if(level==3) return (nCandis==0)?1:0;
        int sum=0;
        for(int i=0;i<=limit;i++) sum+=rec(nCandis-i, limit, level+1);
        return sum;
    }
```

But this would definitely exceed time limit

## 🧠 Intuition

This problem can be understood using **combinatorics**, specifically the **Stars and Bars Theorem** and the **Inclusion-Exclusion Principle (IEP)**.

The goal is to **distribute `n` identical candies among 3 children**, while ensuring that **no child gets more than `limit` candies**.

1. First, compute the total **unrestricted ways** to distribute the candies.
2. Then, use **IEP** to subtract cases where **at least one child exceeds the limit**.

---

## 🪄 Approach

### ✅ Step 1: Stars and Bars Theorem

We want to count the number of ways to divide `n` identical candies among `3` children.

- This is a classic **stars and bars** problem.
- The formula used is:

$$
\text{Total unrestricted ways} = \binom{n + 2}{2} = \frac{(n + 2)(n + 1)}{2}
$$

- Why this works:
    - You have `n` stars (candies).
    - You place 2 bars to divide them among 3 kids.
    - Example: `**|***|` means:
        - Child 1 gets 2 candies
        - Child 2 gets 3 candies
        - Child 3 gets 0 candies

### 🚫 Step 2: Subtract Invalid Cases (Inclusion-Exclusion)

We subtract the number of distributions where **at least one child gets more than `limit`** candies.

Let:
- `A` = child 1 gets more than `limit`
- `B` = child 2 gets more than `limit`
- `C` = child 3 gets more than `limit`

Using **IEP**:

$$
|A \cup B \cup C| = |A| + |B| + |C| - |A \cap B| - |B \cap C| - |A \cap C| + |A \cap B \cap C|
$$

These are the formulas:

- For one child exceeding limit:


$$
\binom{n - (limit + 1) + 2}{2}
$$

- For two children exceeding limit:


$$
\binom{n - 2(limit + 1) + 2}{2}
$$

- For all three exceeding limit:

$$
\binom{n - 3(limit + 1) + 2}{2}
$$

Each of the first two cases is multiplied by 3 due to symmetry.

### ✅ Step 3: Final Formula

$$
\text{Valid Ways} = \binom{n + 2}{2} - 3 \cdot \binom{n - x + 2}{2} + 3 \cdot \binom{n - 2x + 2}{2} - \binom{n - 3x + 2}{2}
$$

Where `x = limit + 1`.

---

## ⏱ Complexity Analysis

- **Time Complexity:** `O(1)` — Just arithmetic operations.
- **Space Complexity:** `O(1)` — Only a few variables used.

---

## 💻 Code Implementation (Java)

```java
class Solution {
    public int distributeCandies(int n, int limit) {
        int allCases = ((n + 2) * (n + 1)) / 2;  // Total unrestricted ways

        if (3 * limit < n) return 0;  // Impossible to distribute
        if (3 * limit == n) return 1; // Exactly one way (each gets limit)

        int x = limit + 1;

        // One child exceeds limit
        int excludeOne = n - x + 2;
        int oneLimit = (excludeOne >= 2) ? 3 * ((excludeOne * (excludeOne - 1)) / 2) : 0;

        // Two children exceed limit
        int excludeTwo = n - 2 * x + 2;
        int twoLimit = (excludeTwo >= 2) ? 3 * ((excludeTwo * (excludeTwo - 1)) / 2) : 0;

        // All three exceed limit
        int excludeThree = n - 3 * x + 2;
        int threeLimit = (excludeThree >= 2) ? ((excludeThree * (excludeThree - 1)) / 2) : 0;

        // Apply Inclusion-Exclusion
        return allCases - oneLimit + twoLimit - threeLimit;
    }
}
