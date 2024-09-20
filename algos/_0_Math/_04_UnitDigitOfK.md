Here's a README.md file explaining the method for finding the unit digit of a number raised to a power, with examples included:

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
- `x = 15`, `k = 10`
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

Use these rules to quickly calculate the unit digit of any number raised to any power!

This README.md file provides clear instructions, examples, and step-by-step explanations for finding the unit digit of a number raised to a power.

