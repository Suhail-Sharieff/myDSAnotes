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