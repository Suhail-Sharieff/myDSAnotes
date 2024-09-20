Let x be some number having it's unit digit as n, let k be the power raised to that number, ie

x=(.....n)^k



if n is 0,1,5,6
 then, the unit digit of x is n itself irrespective of k and it's called as 1 power cycle


if n is 2,3,7,8
 then, the unit digit repeats every 4 cycles of power. So to calculate the unit digit of x in this case,
let rem=k%4 ie remainder 
then the unit digitiof x is as same as thr unit digit of n^k



if n is 4, 
 if k is odd, unit digit is always 4
 if k is even, unit digit is always 6

if n is 9,
 if k is odd, unit digit is always 9
 if k ia even, the unit digit is always 1