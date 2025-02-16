[https://math.meta.stackexchange.com/questions/5020/mathjax-basic-tutorial-and-quick-reference]

## Number of subarrays in an array of size 'n':

- n*(n+1)/2
  - $$
    \frac{n*(n+1)}{2}\

    $$

## Number of subarrays each of size exactly equal to  'k' in an array of size 'n':

- n-k+1
- $$
  n-k+1

  $$
- Example: [a,b,c] , number of subarrays of this array of say size 2 are 2 ie[a,b] and [b,c]

## Number of subarrays each of size ATLEAST 'k' in an array of size 'n':

-Summation((n-k+1)+(n-k)+(n-k-1)...1) ie

$$
sum_{k=1}^n \frac{(n-k+1)(n-k+2)}{2}

$$
