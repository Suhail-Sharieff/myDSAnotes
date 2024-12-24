
# Minimum Hamming Distance

The Hamming distance `hamming(a, b)` between two strings `a` and `b` of equal length is the number of positions where the strings differ. For example,

hamming(01101, 11001) = 2.



## Problem Statement

Consider the following problem: Given `n` bit strings, each of length `k`, calculate the minimum Hamming distance between two strings. For example, the answer for `[00111, 01101, 11110]` is `2`, because:
- `hamming(00111, 01101) = 2`
- `hamming(00111, 11110) = 3`
- `hamming(01101, 11110) = 3`

A straightforward way to solve the problem is to go through all pairs of strings and calculate their Hamming distances, which yields an `O(n^2 * k)` time algorithm.


So we can optimize it using the following observation:
``
The hamming distance between two strings 'a' and 'b' is always equal to number of '1's in the binary string of (intValue(a)^intValue(b))
`` coz xor of 2 different bits is always 1 and same bits is 0

So Procedure is:
- Let x = integer value of a
- Let y = integer value of b
- Let s = binaryString Representation of (a^b)
- Hamming Distance btw a & b = Number of Ones in s

## Optimized Algorithm

### C++ Code

```cpp
#include <bits/stdc++.h>
using namespace std;

int main() {
    int t, len;
    cin >> t >> len;

    vector<int> nums(t);
    for (int i = 0; i < t; i++) {
        string binaryString;
        cin >> binaryString;
        nums[i] = stoi(binaryString, nullptr, 2);  // Store the integer representation
    }

    int minHammingDistance = INT_MAX;

    for (int i = 0; i < t; i++) {
        for (int j = i + 1; j < t; j++) {
            // Calculate Hamming distance directly using the stored integers
            int hammingDistance = __builtin_popcount(nums[i] ^ nums[j]);
            //builtinPopcount function returns nOf ones in the binary representation of passed value
            minHammingDistance = min(minHammingDistance, hammingDistance);
        }
    }

    cout << minHammingDistance << endl;
    return 0;
}

```
### Java Code
```java
public static int solve() throws IOException {
    int n = scanInt();
    int len = scanInt();
    int arr[] = new int[n];
    for (int i = 0; i < n; i++) {
        String s = scanString();
        arr[i] = Integer.parseInt(s, 2);
    }

    int cnt = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            int hammingDistance = Integer.bitCount(arr[i] ^ arr[j]);//bitcount function returns nOf ones in binary representation of the passed number
            cnt = Math.min(cnt, hammingDistance);
        }
    }
    return(cnt);
    
}