
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

## Example 1:

Supose u wanna build an array of size n (n is always even) such that first half r even and second half r odd, also the sum of first_half==sum of second_half, use this concept.
Exmaple: if n=8
arr=[2,4,6,8,   1,3,5,11]

Observe carefully that we can form such array only if (abs((sum_even-sum_odd))) is EVEN and also observe that first n/2 even nums r in normal order and only last (n/2)-1 odd numbers r in normal consecutive order, the last odd elemnt is (normal orderwise elemnt+differenceBetweenOdd&evenSum)


---
## Example 2:


---
## Example 3:


---
## Example 4:



---
## Example 5:




---
## Example 6:




---
## Example 7: