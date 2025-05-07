## How do u extract 2 numbers from a single number by encrypting it
- Say for ex u want to extract 2 and 3 from some number x, how do u modify x so that u exactract 2 and 3 from x
- Solution is , if i somehow represnt x as (2+b3) be z, where b is some constant, so whenevr i want to extract 2, i will do (z%b) and to extract 3 i can do (z/3). Here let z=2+1000*3=30002, z%1000=2 and z/1000=3, this is how its done.
## Try observing if u can reduce 2 or more numbers into a singe value that has characteristics of all of those numbers
- Say for ex wht do u find common in between the pairs [1,2] and [2,1], observe that if u do (smaller*10)+ larger in both pairs u get 1*10+2 ==  1*10+2 ie same, hence in some places u can smartly encrypt some value and save time, space or may be both