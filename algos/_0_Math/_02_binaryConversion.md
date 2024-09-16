olv and Decimal Systems in Computer Science
Understanding Binary and Decimal Systems
Binary System:
 * Uses only two digits: 0 and 1.
 * Base-2 system.
 * Used extensively in computer science due to its simplicity for electronic circuits.
Decimal System:
 * Uses ten digits: 0 to 9.
 * Base-10 system.
 * The most common number system used in everyday life.
Conversion Between Binary and Decimal
Decimal to Binary:
 * Repeated Division:
   * Divide the decimal number by 2.
   * Record the remainder (0 or 1).
   * Repeat until the quotient is 0.
   * The remainders, read from bottom to top, form the binary equivalent.
Binary to Decimal:
 * Positional Notation:
   * Multiply each digit in the binary number by 2 raised to its position (starting from 0 on the right).
   * Add the products.
One's Complement
 * Negation: To find the one's complement of a binary number, simply invert all the bits (0 becomes 1, 1 becomes 0).
 * Used for:
   * Representing negative numbers in some older computer systems.
   * Bitwise operations.
Two's Complement
 * Negation:
   * Find the one's complement.
   * Add 1 to the result.
 * Used for:
   * Representing signed integers in modern computers.
   * Arithmetic operations involving negative numbers.
Tricks for Binary Conversion
Decimal to Binary:
 * Repeated Halving: Divide the decimal number by 2 repeatedly until you reach 0. The remainders in reverse order form the binary equivalent.
Binary to Decimal:
 * Power of 2: Multiply each digit in the binary number by 2 raised to its position (starting from 0 on the right) and add the products.
Negative Numbers:
 * Two's Complement: Use two's complement to represent negative numbers in binary. The most significant bit (MSB) indicates the sign (0 for positive, 1 for negative).
Example:
 * Convert 13 (decimal) to binary:
   * 13 / 2 = 6 (remainder 1)
   * 6 / 2 = 3 (remainder 0)
   * 3 / 2 = 1 (remainder 1)
   * 1 / 2 = 0 (remainder 1)
   * Binary representation: 1101
