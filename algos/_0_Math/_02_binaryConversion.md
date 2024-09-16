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

## Conclusion

Understanding binary and decimal conversions is essential for various applications in computing. The methods outlined here cover the basic conversions for both positive and negative numbers. For additional practice, try converting different numbers and check your results.

