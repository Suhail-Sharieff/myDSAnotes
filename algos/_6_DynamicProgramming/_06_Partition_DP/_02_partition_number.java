package _6_DynamicProgramming._06_Partition_DP;

public class _02_partition_number {
    public static void main(String[] args) {
        String s = "1234";
        rec(s, 0, "", 0);
        /*
         * Output:
         * 1+2+3+4 => 10
         * 1+2+34 => 37
         * 1+23+4 => 28
         * 1+234 => 235
         * 12+3+4 => 19
         * 12+34 => 46
         * 123+4 => 127
         * 1234 => 1234
         */
    }

    public static void rec(String s, int i, String expression, int currSum) {
        if (i == s.length()) {
            System.out.println(expression + " => " + currSum);
            return;
        }
        for (int j = i; j < s.length(); j++) {
            String substr = s.substring(i, j + 1);
            int val = Integer.parseInt(substr);
            if (i == 0) {
                // we r starting, ie we have 1 digit,no need to append + sign
                rec(s, j + 1, substr, val);
            } else {
                rec(s, j + 1, expression + "+" + substr, currSum + val);
            }
        }
    }
}
