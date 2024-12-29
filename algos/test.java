import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class test {

    public static void main(String[] args) {
        int coins[]={1,2,3}; 
        int target=11;

       System.out.println(countDer(3));
        
    }
       static int countDer(int n) {
        // Base case
        int arr[]=new int[n];
        HashMap<Integer,Boolean>hs=new HashMap<>();
        for(int i=0;i<n;i++){
            arr[i]=i+1;
            hs.put(arr[i],false);
            
        }
        
        int ans[]={0};
        func(arr,0,new ArrayList<>(),ans,hs);
        
        return ans[0];
        
    }
    
    public static void func(int arr[],int idx,List<Integer>empty,int ans[],
    HashMap<Integer,Boolean>hs
    ){
        if(empty.size()==arr.length){
            int i=1;
            int cnt=0;
            for(int e:empty){
                if(e!=i++) cnt++;
            }
            System.out.println(empty);
            if(cnt==arr.length) ans[0]++;
            return;
        }
        for(int i=0;i<arr.length;i++){
            if(hs.get(arr[i])==false){
                if (arr[i]!=i+1) {
                    empty.add(arr[i]);
                    hs.put(arr[i],true);
                }
                func(arr,i+1,empty,ans,hs);
                if (arr[i]!=i+1) {
                    empty.remove(empty.size()-1);
                    hs.put(arr[i],false);
                }
            }
        }
    }
}