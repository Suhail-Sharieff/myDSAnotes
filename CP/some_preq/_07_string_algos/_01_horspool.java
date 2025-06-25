import java.util.HashMap;

public class _01_horspool {

    public static void main(String[] args) {
        


        String text="BARBER_IS_VERY_GOOD_PERSON";
        String pattern="VERY";

        System.out.println(text);
        System.out.println(pattern);

        HashMap<Character,Integer>hs=new HashMap<>();

        for(char c:text.toCharArray()) hs.put(c, pattern.length()); 

        for(int i=0;i<pattern.length();i++) hs.put(pattern.charAt(i),pattern.length()-i-1);


        int i=pattern.length()-1;


        int found_idx=-1;

        while (i<text.length()) {
            int nCharsMatchingBackwards=0;
            int j=i;
            int k=pattern.length()-1;
            while (nCharsMatchingBackwards!=pattern.length() && text.charAt(j)==pattern.charAt(k)) {
                nCharsMatchingBackwards++;
                j--;
                k--;
            }
            System.out.println(nCharsMatchingBackwards+" at index "+i);
            if (nCharsMatchingBackwards==pattern.length()) {
                found_idx=i-pattern.length()+1;
                break;
            }
            int shift = hs.get(text.charAt(i));
            i+=Math.max(1,shift);
        }

        if (found_idx==-1) {
            System.out.println("Pattern not found!");
        }else{
            System.out.println("Pattern found at "+found_idx+" ie "+text.substring(found_idx, found_idx+pattern.length()));
        }
    }
}
