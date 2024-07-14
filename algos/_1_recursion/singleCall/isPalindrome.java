package _1_recursion.singleCall;
public class isPalindrome {
    public static boolean isPalin(String s,int startIndex){
        if (startIndex>s.length()-1) {
            return true;
        }
        if (s.charAt(startIndex)==s.charAt(s.length()-startIndex-1)) {
            return isPalin(s, startIndex+1);
        }
        return false;
    }
    public static void main(String[] args) {
        String s="malayalam";
        System.out.println(isPalin(s, 0));
    }
}
