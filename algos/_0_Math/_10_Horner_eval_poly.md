consider an equaltion 6x^3 - 2x^2 + 7x + 5 be f(x), say u need to find f(n). Say the coefficients r given as an array [a0,a1,a2,a3] as [6,-2,7,5].


say u need to find value of f(3) and u r given degree of polynomial deg=3, 

brute force:

code:
```cpp
int ans=0;
int x=3;
int deg=3;
for(int i=0;i<a.length;i++){
    ans+=a[i]*(Math.pow(x,deg));
    deg--;
}
return ans;
```
the time complexity is nlog(n) since for every elemnt in array we calcultate pow which takes log n time, observe that nOfMultiplications performed is n


//horner's method in O(n):

logic: the eqn can be rewriiten as :
->(6x^2 - 2x + 7)x + 5
->((6x - 2)x + 7)x + 5
->(((6)x - 2)x + 7)x + 5

, se that we have reduced the number of multplication in above eqn to just 3 ie equal to deg of polunomial:
code:
```java
int ans=a[0];
int x=3;
int deg=3;
for(int i=1;i<a.length;i++){
    ans=a[i]+ans*x;
}
return ans;
```