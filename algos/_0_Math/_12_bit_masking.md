# Bit Masking

Its like serializing of  some set of data into a binary string format since we can use binary operations properties to find some things that otherwise can take longer time complexity.

## Example

For example, let's say you have a query `[0,4]`. Instead of storing this query, store this as a binary string `10001` where the 0th and 4th bits are 1. You can set the ith digit as 1 if it's 0 using `x | (1 << i)`.

## Obs: say u want to find some number 'x' can be expressed as sum of powers of y
Say u want to find 9 can be represented as sum of say powers of 2, ans is yes, coz $ 9= 2^3 + 2^0$, see this thing in binary, 9 in binary is $1001$, wherever index we have 1 in this we can write sum(char[idx] power idx), ie in this case, $1*2^3 + 1*2^0 = 9$, this observation we can extend for other numbers also, say u want to find if say x=10 can be represented as sum of powers of say y=3, 10 in radix of 3 is $101$,observe that this can e solved as $1*3^2 + 1*3^0$ ie we get $3^2+3^0$, 
Observation is that radix representation of number $x$ say with radix $y$, x can only be represented as sum of powers of y, if and only if string(x radix y) doesnt contain any other numbers other than 0 and 1.

## Obs:say u want to check if some number x is divisible by $2^i$
observation is that in the binary repr of x, iff there are atleast $i$ trailing zeroes(ie last right i digits are 0), then that number will be divisible by $2^i$. Example say u wanna check if 32 is divisble by $2^4$, 32 in binary is `10000`, see that we have at least 4 zeroes at end, hence proved. `Use: `Like suppose u add anything less than $2^4$ now to this 32, this will disturb that there are 4 zeroes at last, hence it would no longer be divisble by $2^i$