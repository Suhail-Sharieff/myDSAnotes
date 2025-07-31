
## Sum of Consecutive Numbers

This project contains formulas for calculating the sums of the first \( n \) consecutive odd and even numbers.

### Sum of Odd Numbers

The sum of the first `n` odd numbers is:

**S_odd = n^2**

#### Example:
- For n = 1: S_odd = 1^2 = 1
- For n = 3: S_odd = 3^2 = 9 (1 + 3 + 5 = 9)

---

### Sum of Even Numbers

The sum of the first `n` even numbers is:

**S_even = n(n + 1)**

#### Example:
- For n = 1: S_even = 1(1 + 1) = 2
- For n = 3: S_even = 3(3 + 1) = 12 (2 + 4 + 6 = 12)

---

## Some Observations:
-->Sum type
- odd + odd = even
- even + even = even
- even + odd = odd
- odd + even = odd
---
-->Product type
- odd * odd = odd
- even * even = even
- even * odd = even
- odd * even = even

----
- `(a+b)=(a^b)-2(a&b)`
- For any tow numbers `x` and `y`, the 0th bit obtained by (x^y) and also by (x+y) remains same, for ex, of (x+y) has 0th bit as 0, then (x^y) will also have 0th bit as 0, usefull coz if 0th bit is 0 means its even number else odd 

