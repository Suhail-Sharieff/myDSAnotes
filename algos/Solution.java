
public class Solution {
    public static void main(String[] args) {

        for (var e : Car.values()) {
            System.err.println(e);
        }
    }

}

interface Operation {
    int operation(int x, int y);
}

enum Car {
    Audi(10000), Benz(200000), Mercedes(900000);

    private final int price;

    Car(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.name() + " cost =" + this.price;
    }
}

class TreeNode<T extends Number> implements Cloneable {
    TreeNode<T> left;
    TreeNode<T> right;
    T val;

    public TreeNode(T val) {
        this.val = val;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}