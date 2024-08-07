package _1_recursion.multipleCall;
//https://www.youtube.com/watch?v=tWnHbSHwNmA&list=PLDzeHZWIZsTqBmRGnsCOGNDG5FY0G04Td&index=8
import java.util.ArrayList;
import java.util.List;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below.
Note that 1 does not map to any letters.

String arr[]={"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

Example 1:
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:
Input: digits = ""
Output: []

Example 3:
Input: digits = "2"
Output: ["a","b","c"]
*/

public class _11_phoneDigit {

    public static void build(List<String> ans, String typedString, String reference[], int idx, StringBuilder current) {
        if (idx >= typedString.length()) {
            ans.add(current.toString());
            return;
        }
        int currDigit = typedString.charAt(idx) - '0';
        String valueOfCurrDigit = reference[currDigit];

        for (int i = 0; i < valueOfCurrDigit.length(); i++) {
            current.append(valueOfCurrDigit.charAt(i));
            build(ans, typedString, reference, idx + 1, current);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        List<String> li = new ArrayList<>();
        String s = "23";
        String reference[] = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        build(li, s, reference, 0, new StringBuilder());
        System.out.println(li);
    }
}
