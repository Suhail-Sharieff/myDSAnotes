

# Number of Factors Calculation

This guide explains how to calculate the number of factors of a given number `k` using its prime factorization.

## Steps to Find the Number of Factors

1. **Prime Factorization**:
   - First, perform the prime factorization of `k`.
   - Express `k` as the product of its prime factors raised to their respective powers. That is:
     ```
     k = a^n1 * b^n2 * c^n3 * ... * z^nz
     ```
     Here, `a`, `b`, `c`, ..., `z` are prime numbers and `n1`, `n2`, `n3`, ..., `nz` are their corresponding powers in the factorization.

2. **Formula for the Number of Factors**:
   - Once you have the prime factorization, the total number of factors `T(k)` of `k` can be calculated using the formula:
     ```
     T(k) = (n1 + 1) * (n2 + 1) * (n3 + 1) * ... * (nz + 1)
     ```
     Where `n1`, `n2`, `n3`, ..., `nz` are the powers of the prime factors in the factorization of `k`.

### Example

Consider the number `k = 180`.

1. **Prime Factorization**:

180 = 2^2 * 3^2 * 5^1

2. **Applying the Formula**:

T(180) = (2 + 1)(2 + 1)(1 + 1) = 3 * 3 * 2 = 18

So, the number `180` has 18 factors ie
[1, 180, 2, 90, 3, 60, 4, 45, 5, 36, 6, 30, 9, 20, 10, 18, 12, 15]


## Notes

- The formula works because each factor of `k` is formed by choosing a power for each prime factor from `0` to its maximum power in the factorization.
- The process applies to any positive integer `k`.

## Applications

This method is useful in:
- Number theory
- Mathematical problem-solving
- Competitive programming

This README.md explains the method clearly and includes an example for better understanding.


Example problem:

In how many ways can 1000 chocolates be distributed equally? 

1000=2^2 * 3^2 * 3^2


Ans: (2+1) (2+1) (2+1) ie 27 ways

