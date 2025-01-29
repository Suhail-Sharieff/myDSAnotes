package _6_DynamicProgramming._04_Strings._02_replacement;

public class _01_edit_distance {

    public static void main(String[] args) {
        String x = "horse";
        String y = "ros";
        System.out.println(rec(x, y, x.length() - 1, y.length() - 1));
    }

    static int rec(String x, String y, int i, int j) {//base cases r imp thats all
        if (j < 0)
            return i + 1;//we have exhausted cheking y,ie y is now empty, means we need (i+1) operations(deletions) ie remaining chars of x ie x[0..i] must be deleted to get currently empty string y , +1 coz its 0 based indexing 
        if (i < 0)
            return j + 1;////we have exhausted cheking x,ie x is now empty, means we need (j+1) operations(deletions) ie remaining chars of y ie y[0..j] must be deleted to get currently empty string x , +1 coz its 0 based indexing 

        if (x.charAt(i) == y.charAt(j)) {
            return rec(x, y, i - 1, j - 1);

        } else {// ie ith and jth chars dont match

            // option1: dont consider ith char,move left of x
            int del = rec(x, y, i - 1, j);

            // option2: replace ith char with jth char
            char[] curr = x.toCharArray();
            curr[i] = y.charAt(j);
            int replace = rec(new String(curr), y, i - 1, j - 1);

            // option3: insert jth char to right of x and move left in both for further  check
            
            int insert = rec(new String(x + y.charAt(j)), y, i, j - 1);
            System.out.println(
                "Using delete: comparing: ["+x.substring(0, i)+","+y.substring(0, j)+"]={"+del+"} ->"+
                "Using replace: comparing: ["+new String(curr)+","+y.substring(0, j+1)+"]={"+replace+"} ->"+
                "Using insert: comparing: ["+new String(x+""+y.charAt(j))+","+y.substring(0, j)+"]={"+insert+"} ->"
            );
            return 1 + Math.min(del, Math.min(replace, insert));
        }
    }
}