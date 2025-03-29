'''
Time limit: 1.00 s
Memory limit: 512 MB



You have two coin piles containing a and b coins. On each move, you can either remove one coin from the left pile and two coins from the right pile, or two coins from the left pile and one coin from the right pile.
Your task is to efficiently find out if you can empty both the piles.
Input
The first input line has an integer t: the number of tests.
After this, there are t lines, each of which has two integers a and b: the numbers of coins in the piles.
Output
For each test, print "YES" if you can empty the piles and "NO" otherwise.
Constraints

1 \le t \le 10^5
0 \le a, b \le 10^9

Example
Input:
3
2 1
2 2
3 3

Output:
YES
NO
YES
''' 

def rec(a,b,li)->bool:
    if(a<0 or b<0): return False
    if(a==0 and b==0): return True
    if li[a][b] != -1:  
        return li[a][b]
    li[a][b]=(rec(a-1,b-2,li) or rec(a-2,b-1,li))
    return li[a][b]
'''
Approach: To solve the problem, follow the below idea:

For a query, we have 2 piles having A and B coins. Let's say we remove 1 coin from the first pile X number of times and remove 2 coins from the first pile Y number of times. So, if we want to empty the first pile, the following equation should be true:



1 * X + 2 * Y = A (Equation 1)



When we remove 1 coin from the first pile, we also remove 2 coins from the second pile. Similarly, when we remove 2 coins from the first pile, we also remove 1 coin from the second pile. So, if we want to empty the first pile, the following equation should be true:



2 * X + 1 * Y = B (Equation 2)



Simplifying the above equations, we get the value of X = (2 * B - A)/3 and the value of Y = (2 * A - B)/3. So, to check whether it is possible to empty both the piles, (2 * B - A) should be positive and divisible by 3 and (2 * A - B) should pe positive and divisible by 3. 



Step-by-step algorithm:

Take the number of queries Q and queries[][] as the input.
For each query, check if (1 * X + 2 * Y = A) is positive and divisible by 3 and (2 * X + 1 * Y = B) is positive and divisible by 3.
If yes, then print "YES", else print "NO".
'''
t=(int)(input())
while(t!=0):
    a, b = map(int, input().split())
    b= (a + b) % 3 == 0 and 2 * a >= b and 2 * b >= a
    if(b): print("YES")
    else: print("NO")
    t=t-1