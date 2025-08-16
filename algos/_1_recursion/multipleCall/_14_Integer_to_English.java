package _1_recursion.multipleCall;
//https://www.youtube.com/watch?v=9FLpWWJ987w
public class _14_Integer_to_English {


    public static void main(String[] args) {
        System.out.println(numberToWords(12345678));
    }

    static String numberToWords(int num) {
        if(num==0) return "Zero";
        return rec(num).trim();
    }




    static String rec(int x){
        //1 digit:"1:One"
        if(x<=9) return one_digit(x);
        //2 digit(le 19):"12:Twelve"
        if(x<=19) return two_digit_le_19(x);
        //2 digit(le 99):"23":rec(2)+"Three"
        if(x<=99) return  two_digit_le_99(x/10)+" "+one_digit(x%10);
        //3 digit: "123":"rec(1)+Hundred+rec(23)"
        if(x<=999) return rec(x/100)+" Hundred "+rec(x%100);
        //4/5/6 digit: "123456":rec(123)+"Thousand"+rec(234)
        if(x<=999_999) return rec(x/1000)+" Thousand "+rec(x%1000);
        //7/8/9 digit "123 456 789"
         if(x<=999_999_999) return rec(x/1000_000)+" Million "+rec(x%1000_000);
        //10/11/12
        return rec(x/1000_000_000)+" Billion "+rec(x%1000_000_000);
    }



    static String one_digit(int x){
        switch (x) {
            case 1->{return "One";}
            case 2->{return "Two";}
            case 3->{return "Three";}
            case 4->{return "Four";}
            case 5->{return "Five";}
            case 6->{return "Six";}
            case 7->{return "Seven";}
            case 8->{return "Eight";}
            case 9->{return "Nine";}
            default->{return "";}
        }
    }
    static String two_digit_le_99(int x){//two digit less than or equal to 99
        switch (x) {
            case 2->{return "Twenty";}
            case 3->{return "Thirty";}
            case 4->{return "Forty";}
            case 5->{return "Fifty";}
            case 6->{return "Sixty";}
            case 7->{return "Seventy";}
            case 8->{return "Eighty";}
            case 9->{return "Ninety";}
            default->{return "";}
        }
    }
    static String two_digit_le_19(int x){//two digit less than or equal to 19
        switch (x) {
            case 10->{return "Ten";}
            case 11->{return "Eleven";}
            case 12->{return "Twelve";}
            case 13->{return "Thirteen";}
            case 14->{return "Fourteen";}
            case 15->{return "Fifteen";}
            case 16->{return "Sixteen";}
            case 17->{return "Seventeen";}
            case 18->{return "Eighteen";}
            case 19->{return "Nineteen";}
            default->{return "";}
        }
    }
}
