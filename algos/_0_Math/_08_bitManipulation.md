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

## NOTE: if a^b = c then :

- b^c = a
- a^c = b
- a^b^c = 0
- a^a = 0 ie even times same input, output = 0
- a^a^a = a ie odd times same input, output = a

## Not Operation

The **NOT operation** (`~x`) produces a number where all the bits of `x` have been inverted. The formula `~x = -x - 1` holds true.

For example:

~29 = -30

The result of the NOT operation at the bit level depends on the length of the bit representation. For 32-bit integers, the result is as follows:

x = 29 -> 00000000000000000000000000011101 ~x = -30 -> 11111111111111111111111111100010

`Clearly observe that for an integer 'x' , '~x' is always -1*(x+1)`

## Bit Shifts

- The **left bit shift** (`x << k`) appends `k` zero bits to the number.
- The **right bit shift** (`x >> k`) removes the `k` last bits from the number.

- x << y --> move y zeroes at end of x
- x >> y --> remove last y stuff from end ox x

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
```
## Some tricks :
 https://www.youtube.com/watch?v=XjtYsFjXtoE&list=PLauivoElc3giVROwL-6g9hO-LlSen_NaV&index=5&ab_channel=Luv
- ith bit of x is 1 : if ``x & ( 1 << i)`` is 1
- if ith bit of x is 1 , to convert it to 0 : do  ``` x & ( ~ (1 << i) )```
- if ith bit of x is 0 , to convert it to 1 : do  ``` x | (  (1 << i) )```
- to toggle ith bit of x : `` x ^ (1 << i)``
- to multiply x by 2 : `x<<2`
- to divide x by 2 `x>>2`
- to convert upper case to lowercase of some uppercase letter X  `` X | (1<<5) `` using observation that the lower case letter has 1 after its most significant 1 and upper has 0 after its most significant 1 and remaining numbers are same
- to convert upper case X to lowercase ``X|' '``-->X or space
- to convert lowercase x to uppercase ``x&_ ``-->x and underscore
- to make all bits to 0 from right to left ie say from [0,i] do `` x & (~ ((1<<i)-1))``
- x is power of 2 if ``(n>0&&(n&(n-1))==0)`` using observation that if x is exact power of 2 it will have say binary repr where theres only 1 one say like 10000, now when u do -1 for that number u get 01111, now with this if u do & with 1, u will get 1 showing its power of 2
- to swap a and b : `(a=a^b) then (b=a^b) then (a=a^b) =>a and b r swapped`
- number x is odd if ``x&1`` is 1 else even
- ``x<<1`` is as same as x**2 and `x>>1` as same as x/2
- If number `x` is even, the number of `1s` in the binary repr of x is as same as number of 1s in binary repr of `x/2`. And if x is odd its as same as that of `[(x-1)/2]+1`


## Indeed one more interesting observation
- Say u have some number $x=1234$, its obvious that ``x%(10^i)`` we can easily obtain `ith digit`(indexing from R to L). Does it work for binary?
- Let `x` be my number,  `r` be the binary representation of the remainder obtained by taking `x%(2^i)` say of length `z`, u will find that this `r` is exactly same as `last z` digits in the binary rep of `x`
- Example 1234 in binary is `10011010010`
- (1234%2)=>0=>(0) matches last 1 bits of bin of x
- (1234%4)=>2=>(10) matches last 2
- (1234%8)=>2=>(010) matches last 3 

- `Example Usage`: [Link](https://codeforces.com/problemset/problem/1909/B): U are given an array, u r basically supposed to find some k such that upon transforming each `ai` in array into `ai%k`, u get exactly 2 distint numbers(idea can be extended to some `m` disctint as well).'The idea is to just check if we r succeding to get 2 unique remainders, checking 1 bit from R to L in bin rep for each ai, assuming k=2^i