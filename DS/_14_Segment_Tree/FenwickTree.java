package _14_Segment_Tree;

//https://www.youtube.com/watch?v=DPiY9wFxGIw
// then
//https://www.youtube.com/watch?v=CWDQJGaN1gY




public class FenwickTree {

    int ft[];
    int nums[];

    public FenwickTree(int arr[]) {
        int sz = arr.length;
        ft = new int[sz + 1];
        nums = arr.clone();
        build_tree();
    }

    public void build_tree() {
        for (int i = 0; i < nums.length; i++) {
            int idx = i + 1;
            int val = nums[i];
            while (idx < ft.length) {
                ft[idx] += val;
                idx = childOf(idx);
            }
        }
    }

    public void update(int targetIdx, int newVal) {
        int toAdd = newVal - nums[targetIdx];
        nums[targetIdx] = newVal;
        targetIdx++;
        while (targetIdx < ft.length) {
            ft[targetIdx] += toAdd;
            targetIdx = childOf(targetIdx);// move next
        }
    }

    public int getPrefixSum(int till) {
        till++;
        int sum = 0;
        while (till > 0) {
            sum += ft[till];
            till = parentOf(till);// move up
        }
        return sum;
    }

    public int getSum(int from, int to) {
        return getPrefixSum(to) - getPrefixSum(from - 1);
    }

    public int parentOf(int idx) {
        return idx - (idx & (-idx));// parent(x)=x-(x&(2'scomplement of x))
    }

    public int childOf(int idx) {
        return idx + (idx & (-idx));
    }

}