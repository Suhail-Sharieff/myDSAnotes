## **Binary and Decimal Systems in Computer Science**

### Understanding Binary and Decimal Systems

**Binary System:**
* Uses only two digits: 0 and 1.
* Base-2 system.
* Used extensively in computer science due to its simplicity for electronic circuits.

**Decimal System:**
* Uses ten digits: 0 to 9.
* Base-10 system.
* The most common number system used in everyday life.

# Conversion Between Binary and Decimal

This document provides a guide for converting numbers between binary and decimal systems, including both positive and negative numbers.

## Table of Contents

1. [Binary to Decimal Conversion](#binary-to-decimal-conversion)
2. [Decimal to Binary Conversion](#decimal-to-binary-conversion)
3. [Negative Numbers Conversion](#negative-numbers-conversion)
4. [Examples](#examples)

## Binary to Decimal Conversion

To convert a binary number to decimal, follow these steps:

1. **Identify each bit's position**: Positions are indexed from right to left, starting from 0.
2. **Calculate the value of each bit**: Multiply each bit by 2 raised to the power of its position.
3. **Sum all the values**: The result is the decimal representation of the binary number.

### Example 1: Positive Binary Number

Convert the binary number `1011` to decimal.

- **Binary number**: `1011`
- **Calculation**:
  - \(1 \times 2^3 = 8\)
  - \(0 \times 2^2 = 0\)
  - \(1 \times 2^1 = 2\)
  - \(1 \times 2^0 = 1\)

- **Sum**: \(8 + 0 + 2 + 1 = 11\)

- **Decimal number**: `11`

### Example 2: Negative Binary Number (Two's Complement)

Convert the 8-bit binary number `11101001` to decimal.

- **Binary number**: `11101001`
- **Identify if negative**: The leftmost bit (sign bit) is `1`, so the number is negative.
- **Find the positive value**:
  1. **Invert the bits**: `00010110`
  2. **Add 1**: `00010111`
  3. **Convert `00010111` to decimal**:
     - \(1 \times 2^4 = 16\)
     - \(0 \times 2^3 = 0\)
     - \(1 \times 2^2 = 4\)
     - \(1 \times 2^1 = 2\)
     - \(1 \times 2^0 = 1\)
  - **Sum**: \(16 + 4 + 2 + 1 = 23\)
  - **Decimal value**: `-23`

## Decimal to Binary Conversion

To convert a decimal number to binary:

1. **Divide the decimal number by 2** and record the remainder.
2. **Continue dividing the quotient** by 2 until the quotient is 0.
3. **Read the remainders backward** to get the binary representation.

### Example 1: Positive Decimal Number

Convert the decimal number `13` to binary.

- **Decimal number**: `13`
- **Division steps**:
  - 13 ÷ 2 = 6 remainder 1
  - 6 ÷ 2 = 3 remainder 0
  - 3 ÷ 2 = 1 remainder 1
  - 1 ÷ 2 = 0 remainder 1

- **Binary number**: `1101`

### Example 2: Negative Decimal Number

Convert the decimal number `-7` to binary (using 8-bit two's complement).

- **Decimal number**: `7`
- **Convert `7` to binary**: `00000111`
- **Invert the bits**: `11111000`
- **Add 1**: `11111001`

- **Binary number**: `11111001`

## Negative Numbers Conversion

Negative numbers are typically represented using two's complement in binary.

### Steps for Two's Complement

1. **Convert the absolute value to binary**.
2. **Pad the binary number** to the desired number of bits (e.g., 8 bits).
3. **Invert the bits** (change 0s to 1s and vice versa).
4. **Add 1** to the result.

### Example 1: Decimal -5 to 8-bit Binary

- **Decimal number**: `5`
- **Binary representation**: `00000101`
- **Invert the bits**: `11111010`
- **Add 1**: `11111011`

- **Binary number**: `11111011`

### Example 2: Decimal -12 to 8-bit Binary

- **Decimal number**: `12`
- **Binary representation**: `00001100`
- **Invert the bits**: `11110011`
- **Add 1**: `11110100`

- **Binary number**: `11110100`






# Conversion Between Binary and Decimal Using One's and Two's Complement

This document explains how to convert numbers between binary and decimal using one's complement and two's complement representations.

## Table of Contents

1. [One's Complement](#ones-complement)
   - [Conversion of Positive Numbers](#conversion-of-positive-numbers)
   - [Conversion of Negative Numbers](#conversion-of-negative-numbers)
2. [Two's Complement](#twos-complement)
   - [Conversion of Positive Numbers](#conversion-of-positive-numbers-1)
   - [Conversion of Negative Numbers](#conversion-of-negative-numbers-1)
3. [Examples](#examples)

## One's Complement

One's complement is a binary representation where negative numbers are obtained by inverting all bits of the positive number.

### Conversion of Positive Numbers

1. **Convert the decimal number to binary** as usual.
2. **Pad the binary number** to the desired bit length (e.g., 8 bits).

### Conversion of Negative Numbers

1. **Convert the absolute value of the number to binary**.
2. **Pad the binary number** to the desired bit length.
3. **Invert all bits** (change 0s to 1s and vice versa).

#### Example 1: Positive Decimal Number

Convert the decimal number `6` to an 8-bit binary number.

- **Decimal number**: `6`
- **Binary representation**: `00000110`

- **One's complement**: `00000110`

#### Example 2: Negative Decimal Number

Convert the decimal number `-6` to an 8-bit binary number using one's complement.

- **Decimal number**: `6`
- **Binary representation**: `00000110`
- **Invert all bits**: `11111001`

- **One's complement representation**: `11111001`

## Two's Complement

Two's complement is a binary representation where negative numbers are obtained by inverting all bits of the positive number and adding 1.

### Conversion of Positive Numbers

1. **Convert the decimal number to binary** as usual.
2. **Pad the binary number** to the desired bit length (e.g., 8 bits).

### Conversion of Negative Numbers

1. **Convert the absolute value of the number to binary**.
2. **Pad the binary number** to the desired bit length.
3. **Invert all bits**.
4. **Add 1** to the result.

#### Example 1: Positive Decimal Number

Convert the decimal number `6` to an 8-bit binary number.

- **Decimal number**: `6`
- **Binary representation**: `00000110`

- **Two's complement representation**: `00000110`

#### Example 2: Negative Decimal Number

Convert the decimal number `-6` to an 8-bit binary number using two's complement.

- **Decimal number**: `6`
- **Binary representation**: `00000110`
- **Invert all bits**: `11111001`
- **Add 1**: `11111010`

- **Two's complement representation**: `11111010`

## Examples

### One's Complement

1. **Decimal `5` to 8-bit binary**:
   - **Binary**: `00000101`
   - **One's complement**: `00000101` (for positive numbers)

2. **Decimal `-5` to 8-bit binary**:
   - **Decimal `5`**: `00000101`
   - **Invert bits**: `11111010`
   - **One's complement**: `11111010`

### Two's Complement

1. **Decimal `5` to 8-bit binary**:
   - **Binary**: `00000101`
   - **Two's complement**: `00000101` (for positive numbers)

2. **Decimal `-5` to 8-bit binary**:
   - **Decimal `5`**: `00000101`
   - **Invert bits**: `11111010`
   - **Add 1**: `11111011`
   - **Two's complement**: `11111011`






# Bitwise Shift Operators

Bitwise shift operators are used to shift the bits of a binary number to the left or right. This document explains the left shift and right shift operators, including how to calculate their effects easily.

## Table of Contents

1. [Left Shift Operator](#left-shift-operator)
   - [Concept](#concept)
   - [Formula](#formula)
   - [Examples](#examples)
2. [Right Shift Operator](#right-shift-operator)
   - [Concept](#concept-1)
   - [Formula](#formula-1)
   - [Examples](#examples-1)

## Left Shift Operator

The left shift operator (`<<`) shifts the bits of a binary number to the left by a specified number of positions. 

### Concept

- Each shift to the left by one position multiplies the number by 2.
- Bits shifted out of the left end are discarded, and zeroes are added on the right end.

### Formula

For a number `n` shifted left by `k` positions, the new number can be calculated as:

\[ \text{New Number} = \text{Original Number} \times 2^k \]

### Examples

#### Example 1: Positive Number

Convert the number `6` (binary `00000110`) using a left shift by 2 positions.

- **Original binary number**: `00000110` (which is `6` in decimal)
- **Shift left by 2**:
  - Shifted binary: `00011000`
  - **Decimal result**: `6 \times 2^2 = 6 \times 4 = 24`
  - **New binary number**: `00011000`

#### Example 2: Negative Number

Convert the number `-3` (8-bit binary `11111101`) using a left shift by 3 positions.

- **Original binary number**: `11111101` (which is `-3` in decimal)
- **Shift left by 3**:
  - Shifted binary: `11110100`
  - **Decimal result**: `-3 \times 2^3 = -3 \times 8 = -24`
  - **New binary number**: `11110100`

## Right Shift Operator

The right shift operator (`>>`) shifts the bits of a binary number to the right by a specified number of positions.

### Concept

- Each shift to the right by one position divides the number by 2.
- The effect on the sign bit depends on whether the shift is logical (unsigned) or arithmetic (signed).

### Formula

For a number `n` shifted right by `k` positions, the new number can be calculated as:

\[ \text{New Number} = \text{Original Number} \div 2^k \]

### Examples

#### Example 1: Positive Number

Convert the number `24` (binary `00011000`) using a right shift by 3 positions.

- **Original binary number**: `00011000` (which is `24` in decimal)
- **Shift right by 3**:
  - Shifted binary: `00000011`
  - **Decimal result**: `24 \div 2^3 = 24 \div 8 = 3`
  - **New binary number**: `00000011`

#### Example 2: Negative Number

Convert the number `-24` (8-bit binary `11101000`) using a right shift by 4 positions.

- **Original binary number**: `11101000` (which is `-24` in decimal)
- **Shift right by 4**:
  - Shifted binary (arithmetic shift): `11111110`
  - **Decimal result**: `-24 \div 2^4 = -24 \div 16 = -1.5` (rounded towards negative infinity)
  - **New binary number**: `11111110`

## Conclusion

Bitwise shift operations are fundamental in low-level programming and digital logic. The left shift operation multiplies a number by powers of 2, while the right shift divides it. Understanding these operations can help in optimizing algorithms and manipulating binary data efficiently.


## Conclusion

Understanding one's and two's complement representations is crucial for working with binary numbers, especially in computer systems. This guide covers the basics of converting between decimal and binary for both positive and negative numbers in these two complement systems. For further practice, apply these methods to different numbers and verify your results.


## Conclusion

Understanding binary and decimal conversions is essential for various applications in computing. The methods outlined here cover the basic conversions for both positive and negative numbers. For additional practice, try converting different numbers and check your results.

