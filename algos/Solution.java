public class Solution {
    public static void main(String[] args) {
    }

}

class A {
    int x;
    A(){
        System.out.println("Parent class");
    }

}
class B extends A {
    
    B(){
        System.out.println("Child class");
    }
}