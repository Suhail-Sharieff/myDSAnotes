```
EUCLIDIAN ALGORITHM

O(log(max(a,b)))

```
# Calculate GCD Function

The following is a recursive function to calculate the Greatest Common Divisor (GCD) of two integers:

``` java


int gcd(int a, int b) {
    // Base case: if b is 0, return a
    if (b == 0) {
        return a;
    }
    // Recursive case: GCD of b and the remainder of a divided by b
    return gcd(b, a % b);
}
```

## Application 1: Simplifying Fractions

Suppose you want to simplify a fraction into its irreducible format. For example, you want to simplify the fraction **"2/6"** into **"1/3"**. Here’s how you can do it using GCD:

1. **Let Nr** be the numerator of the fraction.
2. **Let Dr** be the denominator of the fraction.
3. **Calculate the GCD** of Nr and Dr:
   ```
   gcd = gcd(Nr, Dr)
   ```
4. The simplified form of the fraction is then:
   ```
   (Nr/gcd) / (Dr/gcd)
   ```

### Example:

- For the fraction **"2/6"**:
  - **Nr = 2**
  - **Dr = 6**
  - Calculate `gcd(2, 6)`:
    - The GCD is **2**.
  - The simplified form is:
    ```
    (2/2) / (6/2) = 1/3
    ```

Thus, the fraction **"2/6"** simplifies to **"1/3"**.


----


## Some useful properties of GCD
- $GCD(a,b) \leq min(a,b)$ 


