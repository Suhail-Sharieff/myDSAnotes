# Weird Algorithm

## Problem Statement

Consider an algorithm that takes as input a positive integer `n`. If `n` is even, the algorithm divides it by two, and if `n` is odd, the algorithm multiplies it by three and adds one. The algorithm repeats this until `n` is one. 

For example, the sequence for `n = 3` is as follows:

3 → 10 → 5 → 16 → 8 → 4 → 2 → 1



Your task is to simulate the execution of the algorithm for a given value of `n`.

## Input

The only input line contains an integer `n`.

## Output

Print a line that contains all values of `n` during the algorithm.

## Constraints

- 0<=n<=10e+6

## Example

**Input:**

3


**Output:**

3 10 5 16 8 4 2 1



## Additional Information

This problem is connected to the famous Collatz conjecture, which states that the above algorithm terminates for every value of `n`. However, nobody has been able to prove it. In this problem, however, we know that the initial value of `n` will be at most one million, which makes the problem much easier to solve. This problem is a simple simulation problem, which does not require much thinking.

## Solution in C++

```cpp
#include <iostream>
using namespace std;

int main() {
    long n; //always take this as long long
    cin >> n;
    while (true) {
        cout << n << " ";
        if (n == 1) break;
        if (n % 2 == 0) n /= 2;
        else n = n * 3 + 1;
    }
    cout << "\n";
}
The code first reads in the input number n, then simulates the algorithm and prints the value of n after each step.