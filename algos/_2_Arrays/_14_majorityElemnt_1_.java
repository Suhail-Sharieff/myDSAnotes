package _2_Arrays;

import java.util.HashMap;

public class _14_majorityElemnt_1_ {
    //find the only elemnt in the array whch appears strictly more than N/2 times where n is given

    public static int better(int arr[],int n){//O(nlogn+n)---O(n)
        HashMap<Integer,Integer>hs=new HashMap<>();
        int ans=-1;
        for (int e : arr) {
            hs.put(e, hs.getOrDefault(e, 0)+1);
            if (hs.getOrDefault(e, 0)>n/2) {
                ans=e;
                break;
            }
        }
        return ans;
    }


    //moore's voting algorithm--interview explain intutuion first then then code
    //https://www.youtube.com/watch?v=nP_ns3uSh80&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=23
    //O(2n)--O(1)
    public static int optimal(int arr[],int n){
        int tempCnt=0;
        int assumedAns=-1;
        for (int i = 0; i < arr.length; i++) {
            if (tempCnt==0) {
                tempCnt=1;
                assumedAns=arr[i];
            }else if (arr[i]==assumedAns) {
                tempCnt++;
            }else{//if not equal
                tempCnt--;
            }
        }
        int c=0;
        for (int e : arr) {
            if (e==assumedAns) {
                c++;
            }
        }
        return (c>n/2?assumedAns:-1);

    }


    public static void main(String[] args) {
        System.out.println(better(new int[]{2,2,1,3,1,1,3,1,1}, 9));
        System.out.println(optimal(new int[]{2,2,1,3,1,1,3,1,1}, 9));
    }
}
