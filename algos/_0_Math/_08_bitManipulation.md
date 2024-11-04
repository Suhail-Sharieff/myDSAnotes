
# Bitwise Operations in Programming
## Bit-Parallel Algorithms
 Bit-parallel algorithms are based on the fact that individual bits of numbers can
 be manipulated in parallel using bit operations. Thus, a way to design an efficient
 algorithm is to represent the steps of the algorithm so that they can be efficiently
 implemented using bit operations.

## And Operation

The **AND operation** (`x & y`) produces a number that has `1` bits in positions where both `x` and `y` have `1` bits. 

For example:

22 & 26 = 18 
10110 (22) &
11010 (26) = 
10010 (18)


Using the AND operation, we can check if a number `x` is even. Specifically:

- `x & 1 = 0` if `x` is even
- `x & 1 = 1` if `x` is odd

More generally, `x` is divisible by `2^k` exactly when `x & (2^k - 1) = 0`.

## Or Operation

The **OR operation** (`x | y`) produces a number that has `1` bits in positions where at least one of `x` or `y` has `1` bits.

For example:

22 | 26 = 30 10110 (22) | 11010 (26) = 11110 (30)



## Xor Operation

The **XOR operation** (`x ^ y`) produces a number that has `1` bits in positions where exactly one of `x` or `y` has a `1` bit.

For example:

22 ^ 26 = 12 10110 (22) ^ 11010 (26) = 01100 (12)

## IMP LOGIC:
Lets say we nned to find number x, such that upon xoring it with some known number (say 'a') , we would get another known number(say 'b'), ie find x such that **a^x=b, then x=a^b**


## Not Operation

The **NOT operation** (`~x`) produces a number where all the bits of `x` have been inverted. The formula `~x = -x - 1` holds true. 

For example:

~29 = -30


The result of the NOT operation at the bit level depends on the length of the bit representation. For 32-bit integers, the result is as follows:

x = 29 -> 00000000000000000000000000011101 ~x = -30 -> 11111111111111111111111111100010

``Clearly observe that for an integer 'x' , '~x' is always -1*(x+1)``


## Bit Shifts

- The **left bit shift** (`x << k`) appends `k` zero bits to the number. 
- The **right bit shift** (`x >> k`) removes the `k` last bits from the number.

- x << y  --> move y zeroes at end of x
- x >> y  --> remove last y stuff from end ox x


For example:

- 14 << 2 = 56 // 1110 becomes 111000 '
- 49 >> 3 = 6 // 110001 becomes 110


Note that `x << k` corresponds to multiplying `x` by `2^k`, and `x >> k` corresponds to dividing `x` by `2^k` rounded down to an integer.

## Bit Masks

A **bit mask** of the form `1 << k` has a `1` bit in position `k` and all other bits are zero. We can use such masks to access single bits of numbers.

**IMPORTANT**: The `k`th bit of a number is `1` exactly when `x & (1 << k)` is not zero.

Here’s code to print the bit representation of an integer `x`:

```cpp
for (int k = 31; k >= 0; k--) {
    if (x & (1 << k)) cout << "1";
    else cout << "0";
}
You can also modify single bits using similar techniques:

x | (1 << k) sets the kth bit of x to 1
x & ~(1 << k) sets the kth bit of x to 0
x ^ (1 << k) inverts the kth bit of x
x & (x - 1) sets the last 1 bit of x to 0
x & -x sets all 1 bits to 0, except for the last 1 bit
x | (x - 1) inverts all bits after the last 1 bit
A positive number x is a power of two exactly when x & (x - 1) = 0.

Note: Be cautious, as 1 << k is always an int bit mask. To create a long long bit mask, use 1LL << k.

Additional Functions
The g++ compiler provides the following functions for counting bits:

__builtin_clz(x): Number of zeros at the beginning(leading) of the bit representation
__builtin_ctz(x): Number of zeros at the end(terminating) of the bit representation
__builtin_popcount(x): Number of ones in the bit representation
__builtin_parity(x): Parity (even or odd) of the number of ones in the bit representation
These functions can be used as follows:


int x = 5328; // 00000000000000000001010011010000
cout << __builtin_clz(x) << "\n"; // 19
cout << __builtin_ctz(x) << "\n"; // 4
cout << __builtin_popcount(x) << "\n"; // 5
cout << __builtin_parity(x) << "\n"; // 1
Note: The above functions only support int numbers, but there are also long long versions available with the suffix ll.