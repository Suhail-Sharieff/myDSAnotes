package _9_Linked_List;

import java.util.ArrayDeque;
import java.util.Scanner;


/*  https://codeforces.com/contest/2094/problem/G


G. Chimpanzini Bananini
time limit per test2 seconds
memory limit per test256 megabytes
Chimpanzini Bananini stands on the brink of a momentous battle—one destined to bring finality.

For an arbitrary array b
 of length m
, let's denote the rizziness of the array to be ∑mi=1bi⋅i=b1⋅1+b2⋅2+b3⋅3+…+bm⋅m
.

Chimpanzini Bananini gifts you an empty array. There are three types of operations you can perform on it.

Perform a cyclic shift on the array. That is, the array [a1,a2,…,an]
 becomes [an,a1,a2,…,an−1].
Reverse the entire array. That is, the array [a1,a2,…,an]
 becomes [an,an−1,…,a1].
Append an element to the end of the array. The array [a1,a2,…,an]
 becomes [a1,a2,…,an,k]
 after appending k
 to the end of the array.
After each operation, you are interested in calculating the rizziness of your array.

Note that all operations are persistent. This means that each operation modifies the array, and subsequent operations should be applied to the current state of the array after the previous operations.

Input
The first line contains an integer t
 (1≤t≤104
) — the number of test cases.

The first line of the input contains an integer q
 (1≤q≤2⋅105
) — the number of operations you perform on your array.

The following q
 lines first contain a single integer s
 (1≤s≤3
) — the operation type.

If s=1
, then the cyclic shift operation should be performed.
If s=2
, then the reversal operation should be performed.
If s=3
, then the line will contain an additional integer k
 (1≤k≤106
), denoting the element appended to the back of the array.
It is guaranteed that the sum of q
 will not exceed 2⋅105
 over all test cases. Additionally, it is guaranteed that the first operation on each test case will be one with s=3
.

Output
For each test case, output q
 lines, outputting the rizziness of your array after each operation.

Example
InputCopy
1
13
3 1
3 2
3 3
1
3 4
2
3 5
1
3 6
2
3 7
2
1
OutputCopy
1
5
14
11
27
23
48
38
74
73
122
102
88
Note
The first six states of the array:

[1]
[1,2]
[1,2,3]
[3,1,2]
[3,1,2,4]
[4,2,1,3]


 */

public class _19_dq {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        while (tests-- > 0) {
            int ops = sc.nextInt();
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            boolean revFlag = false;
            long cnt = 0, tot = 0, ans = 0;
            while (ops-- > 0) {
                int type = sc.nextInt();
                if (type == 1) {
                    int elem = revFlag ? dq.peekFirst() : dq.peekLast();
                    if (revFlag) { dq.pollFirst(); dq.offerLast(elem); }
                    else { dq.pollLast(); dq.offerFirst(elem); }
                    ans += tot - cnt * elem;
                } else if (type == 2) {
                    ans = (cnt + 1) * tot - ans;
                    revFlag = !revFlag;
                } else {
                    int num = sc.nextInt();
                    cnt++;
                    tot += num;
                    ans += num * cnt;
                    if (revFlag) dq.offerFirst(num);
                    else dq.offerLast(num);
                }
                System.out.println(ans);
            }
        }
        sc.close();
    }
}
