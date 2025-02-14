## Some keys to find if problem requires DP to be solved efficiently
- count the number of distinct ways
- find min/max
- best way among all ways

## Common procedure to solve DP problems:
- represent data (say array) in terms of index
- try all possible stuff as per problem statement
- aslo simultaneoulsy  count, find max/min


## How to convert a memo soln to a tabulation solution:
-always write changing states in order, for ex
```java
//suppose your recursive solution is rec(idx1,idx2),loop as(asssuming idx depends on idx2):
for(int i=0;i<n;i++){//wrt idx1
    for(int j=0;j<n;j++){//wrt idx2
        //what so ever code
    }
} 
```
- One more important thing , its not just about writing loops , it also needs revresal of way of traversal,
suppose ur recurive function is ``rec(0 —————> n)``, then loop as 
```java 
for(int i=n;i>=0;i--)
```
, now supoose ur recurive function is ``rec(n —————> 0)``,
then loop as 
```java
for(int i=0;i<n;i++)
```