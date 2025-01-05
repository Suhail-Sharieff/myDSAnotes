package _6_Recursion;

public class towersOfHanoi {
    
    public static void TOH(int noOfDiscs,char source,char auxillary,char destination){
        if (noOfDiscs==1) {
            System.out.println("Move top disc from "+source+" to "+destination);
            return;
        }
        TOH(noOfDiscs-1, source, destination, auxillary);
        System.out.println("Move top disc from "+source+" to "+destination);
        TOH(noOfDiscs-1, auxillary, source, destination);
    }
    public static void main(String[] args) {
        TOH(3   , 'A', 'B', 'C');
    }
}
