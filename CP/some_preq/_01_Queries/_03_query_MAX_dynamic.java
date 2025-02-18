package some_preq._01_Queries;
/*
SCANLINE ALGORITHM :

Starting with a 1-indexed array of zeros and a list of queries, for each operation add a value to each array element between two given indices, inclusive. Once all queries have been performed, return the maximum value in the array after all queries r performed.
//https://www.hackerrank.com/challenges/crush/problem
 */
public class _03_query_MAX_dynamic {
    public static void main(String[] args) {
        //given len=10;

        int len=10;

        int pref[]=new int[len+2];//add 0 to first and last, now u can play from 1 to n, no problem will ovccurr with 0idx and n+1 idx

        int queries[][]={{1,5,3},{4,8,7},{6,9,1}};//1 based


    /*optimal strategy
        //its based on observation that say initally we have pref filled with 0, suppose u r given 1 query as [l,r,val](1 based), if u make pref[l] as pref[l]+val and pref[r+1] as pref[r+1]-val , then take prefSum of that array from 1 to n, u will get to know that the array obtained will be as same as looping from l-->r and updating each elemnt as per val, for ex
        pref=[0,0,0,0,0]
        say l=2,r=3,val=9
        if we do using normal: [0,9,9,0,0] ie updated [l,r] with +9
        but if we use our observation and just make pref[l]=pref[l]+val and pref[r+1]=pref[r+1]-val, we get arr=[0,9,0,-9,0], now take pref array update arr[i]+=arr[i-1], we would get pref=[0,9,9,0,0] woah, its as same as the array we obtained after looping from [l,r], so we can use this intellingent observation, instead of looping for each query from [l,r] and updating elemnt of that range to +val, we just , for each query make pref[l]+=val and pref[r+1]-=val, then after all queries r done, convert same array to prefSum, get max elemnt in that 
        */

        for(int q[]:queries){
            int l=q[0];
            int r=q[1];
            int val=q[2];
            pref[l]+=val;
            pref[r+1]-=val;
        }
        for(int i=1;i<=len;i++) pref[i]+=pref[i-1];

        int ans=0;
        for(int e:pref) ans=Math.max(e, ans);

        System.out.println("Maximum value in array after all queries r performed is "+ans);
        
    }
}
