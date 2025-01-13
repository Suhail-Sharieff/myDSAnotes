public class test {

    public static void main(String[] args) {
        int n=10;

        for (int i = 0; i <=n; i++) {
            System.out.print(i+" ->");
            for(int j=6;j>=0;j--){
                System.out.print(((i&(1<<j))!=0)?1:0);
            }
            System.out.println();
        }
    }
}