# Bit Masking

Its like serializing of  some set of data into a binary string format since we can use binary operations properties to find some things that otherwise can take longer time complexity.

## Example

For example, let's say you have a query `[0,4]`. Instead of storing this query, store this as a binary string `10001` where the 0th and 4th bits are 1. You can set the ith digit as 1 if it's 0 using `x | (1 << i)`.
