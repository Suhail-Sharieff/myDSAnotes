# Remainder theorm 
- (a+b) % k = ( (a%k) + (b%k) ) % k
- (a*b) % k = ( (a%k) * (b%k) ) % k
- (a-b) % k = ( (a%k) - (b%k) + m) % k
- (a/b) % k = ( (a%k) * Inverse(b) ) % k
---
# Fermat's Little Theorm (Eulers's therom)
Let `p` be a prime number, then:
- a ^ (p-1) =1
- inverse(a) = a ^ (p-1)
# Finding the Unit Digit of a Number Raised to a Power

This guide explains how to determine the unit digit of a number `x` raised to a power `k` using patterns based on the unit digit of `x`.

## Steps to Find the Unit Digit of `x^k`

Let `x` be a number with its unit digit as `n`. The power `k` is raised to this number, i.e.,

x = (.....n)^k

The unit digit of `x^k` can be determined as follows:

### Case 1: **n = 0, 1, 5, or 6**
- If the unit digit of `x` is `0`, `1`, `5`, or `6`, the unit digit of `x^k` will **always be the same** as `n`, regardless of the value of `k`.
- These are called **1 power cycle** numbers.

#### Example:
- `x = 15`, `k = 10`, `n=5`
- The unit digit of `x` is `5`, so the unit digit of `15^10` is `5`.

### Case 2: **n = 2, 3, 7, or 8**
- If the unit digit of `x` is `2`, `3`, `7`, or `8`, the unit digit repeats every **4 power cycles**.
- To calculate the unit digit of `x^k`, first find the remainder `rem = k % 4`.
  - If `rem = 0`, use the 4th power of `n`.
  - If `rem = 1`, use the 1st power of `n`.
  - If `rem = 2`, use the 2nd power of `n`.
  - If `rem = 3`, use the 3rd power of `n`.

#### Example:
- `x = 27`, `k = 5`
- The unit digit of `x` is `7`.
- Calculate `k % 4 = 5 % 4 = 1`.
- The unit digit of `27^5` is the same as the unit digit of `7^1`, which is `7`.

### Case 3: **n = 4**
- If the unit digit of `x` is `4`:
  - If `k` is odd, the unit digit of `x^k` is **4**.
  - If `k` is even, the unit digit of `x^k` is **6**.

#### Example:
- `x = 14`, `k = 3`
- The unit digit of `x` is `4`, and since `k` is odd, the unit digit of `14^3` is `4`.

- `x = 14`, `k = 4`
- The unit digit of `x` is `4`, and since `k` is even, the unit digit of `14^4` is `6`.

### Case 4: **n = 9**
- If the unit digit of `x` is `9`:
  - If `k` is odd, the unit digit of `x^k` is **9**.
  - If `k` is even, the unit digit of `x^k` is **1**.

#### Example:
- `x = 19`, `k = 3`
- The unit digit of `x` is `9`, and since `k` is odd, the unit digit of `19^3` is `9`.

- `x = 19`, `k = 4`
- The unit digit of `x` is `9`, and since `k` is even, the unit digit of `19^4` is `1`.

## Summary

- **n = 0, 1, 5, 6** → Unit digit is always `n`.
- **n = 2, 3, 7, 8** → Unit digit repeats every 4 cycles. Use `k % 4` to find the corresponding power.
- **n = 4** → Odd `k` gives unit digit `4`, even `k` gives unit digit `6`.
- **n = 9** → Odd `k` gives unit digit `9`, even `k` gives unit digit `1`.


Here's a special problem example, which you can add as a section to your README.md:

## Special Problem: Finding the Unit Digit of a Complex Expression

### Problem:

Find the unit digit of the following expression:

(13^23) * (27^37) * (19^45)

### Solution:

To solve this, we will compute the unit digits of each term individually and combine them.

1. **Unit digit of 13^23**:
   - The unit digit of 13 is `3`.
   - Since `3` repeats its unit digit every 4 cycles, we need to find `23 % 4 = 3`.
   - The unit digit of `13^23` is the same as the unit digit of `3^3`, which is `7`.

2. **Unit digit of 27^37**:
   - The unit digit of 27 is `7`.
   - Since `7` repeats its unit digit every 4 cycles, we need to find `37 % 4 = 1`.
   - The unit digit of `27^37` is the same as the unit digit of `7^1`, which is `7`.

3. **Unit digit of 19^45**:
   - The unit digit of 19 is `9`.
   - Since `9` has a repeating cycle where odd powers result in a unit digit of `9` and even powers result in a unit digit of `1`, and `45` is odd, the unit digit of `19^45` is `9`.

### Final Calculation:

Now, multiply the unit digits of each term:

7 * 7 * 9 = 49 * 9 = 441

The unit digit of `441` is `1`.

### Answer:

The unit digit of `(13^23) * (27^37) * (19^45)` is **1**.

### Intuitive Approach:

By intuition, you can observe that the unit digits of the individual terms are `7`, `7`, and `9`. Multiplying:

7 * 7 = 49 → Unit digit is 9 9 * 9 = 81 → Unit digit is 1

Thus, the final unit digit is **1**.T






## Special Problem: Finding the Unit Digit of a Complex Expression

### Problem:

Find the unit digit of the following expression:

(39^(39!)) * (127^(127!))

### Solution:

We will compute the unit digits of each part individually and then combine the results.

### 1. **Unit digit of 39^(39!)**:
- The unit digit of 39 is `9`.
- Since `9` follows a repeating pattern:
  - If `k` is odd, the unit digit of `9^k` is `9`.
  - If `k` is even, the unit digit of `9^k` is `1`.
  
  Now, `39!` (factorial of 39) is a **very large even number**, because it is the product of all integers from 1 to 39, **but one important observation is that it must obviously have many zeroes at its end (coz it has 5!,and all factorials after 5 has zeoes), now sincet it has zeroes,(m!)%4 must be 0,there fore just compute 9^0 ie 9 power even, ie unit digit will be 1**

- Therefore, the unit digit of `39^(39!)` is `1`.

### 2. **Unit digit of 127^(127!)**:
- The unit digit of 127 is `7`.
- The unit digit of powers of `7` repeats every 4 cycles:
  - `7^1` → Unit digit is `7`
  - `7^2` → Unit digit is `9`
  - `7^3` → Unit digit is `3`
  - `7^4` → Unit digit is `1`

  Now, we need to compute `127! % 4` to determine the correct power cycle:
  - Since `127!` contains multiples of 4 (because 4 is a factor of numbers in 127!), we have:
    ```
    127! % 4 = 0
    ```

- Therefore, the unit digit of `127^(127!)` is the same as the unit digit of `7^4`, which is `1`.

### Final Calculation:

Now, multiply the unit digits of the two terms:

1 * 1 = 1

### Answer:

The unit digit of `(39^(39!)) * (127^(127!))` is **1**.

This explanation breaks down the problem by focusing on the patterns of unit digits for each base number and handles the large factorial exponents efficiently.





Use these rules to quickly calculate the unit digit of any number raised to any power!

This README.md file provides clear instructions, examples, and step-by-step explanations for finding the unit digit of a number raised to a power

