

# Number of Factors Calculation

This guide explains how to calculate the number of factors of a given number `k` using its prime factorization.

## Steps to Find the Number of Factors/Divisors

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


# Sum of Divisors/Factors:
$$ 
\sum{divisors(k)=}
\frac{a^{n1+1}-1}{a-1}\ *
\frac{b^{n2+1}-1}{b-1}\ *
.......
\frac{x^{n+1}-1}{x-1}\ 

$$
where a,b,...x r prime factors obtained during prime factorization of 'k'
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
- ## NOTE:  the number of primes till number n starting from 2 is given by (n/ln(n)). Eample, nPrimes till 100 = 100/ln(100) = 100/4 = 25
-  (Note that a number
 n has O(logn) prime factors.)


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


## Note:
- If ``x%m + y%m == m``, then ``(x+y)%m = 0``, Example 2%4=2 and 6%4=2, also (2+4)%=0

- Some sample problem on prime factorization: [Link](https://codeforces.com/problemset/problem/1881/D). Given an array if integer, in one operation u can select 2 indices i,j, then divide a[i] by some x such that a[i]%x==0 and multiply a[j] by x. Print if its possible to make entire array equal or not. Key ob is that the product of the array would remain constant always coz for one u divide and other gets multiplied. If u visulize each number as its prime factorization, for ex  $50=2^2 *5^2 $ and $10=2*5$, u can equalize this by put one 2 on right and one 5 on right ie ultimately u make them eqaal.

