package some_preq._02_bit_masking;
/*Prereq Knowledge:

Let x=000000 (binary)

-U can set ith bit(0 based) to 1 using "x|(1<<i)"
- To toggle ith bit "x^(1<<i)"
 
 
 */
public class _01_basic {
    public static void main(String[] args) {

        //How to set ith bit to 1 if
        int x=1<<9;//say this is 1000000000 in binary
        for(int i=0;i<=8;i++){
            x=x^(1<<i);
            System.out.print("Setting "+i+"th bit to 1, we get "+Integer.toBinaryString(x)+" again ");
            x=x^(1<<i);
            System.out.println("setting "+i+"th bit to 0, we get "+Integer.toBinaryString(x));
        }

    }
}
