import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class test {

    public static void main(String[] args) {
        int graph[][]={
            {},
            {3},
            {3},
            {},
            {0,1},
            {0,2},
        };

        HashMap<Integer,Integer>hp=new HashMap<>();



        boolean isVis[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++)hp.put(i, 0);
        for(int i=0;i<graph.length;i++){
                dfs(i, hp, graph, isVis);
        }

        System.out.println(hp);

        List<ppppp>li=new ArrayList<>();

        for(var e:hp.entrySet()){
            li.add(new ppppp(e.getKey(), e.getValue()));
        }
        Collections.sort(li,(x,y)->x.f-y.f);

        List<Integer>ans=new  ArrayList<>();
        for(var e:li){
            ans.add(e.v);
        }
        System.out.println(ans);
    }

    public static void dfs(int n,HashMap<Integer,Integer>hs,int graph[][],boolean isVis[]){
       isVis[n]=true;
       Stack<Integer>st=new Stack<>();
       st.push(n);


        for(int e:graph[n]){
            hs.put(e, hs.getOrDefault(e, 0)+1);
            // if(!isVis[e]){
                st.push(e);
            // }
        }
       
    }

   
}

class ppppp{
    int v,f;
    public ppppp(int x,int y){
        this.v=x;this.f=y;
    }
}
