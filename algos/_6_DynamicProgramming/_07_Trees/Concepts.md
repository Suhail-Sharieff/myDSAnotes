## Note: All Tree+DP problems are solved in CSES/DP/Trees

# Some Key algorithms
- Some DP on trees require u to think like, `either the path would pass via curr node or may not` ex: [Tree Matching ](/CP/some_preq/_06_cses/_02_DP/_02_Trees/_01_TreeMatching.java)
- [Rerooting](/CP/some_preq/_06_cses/_02_DP/_02_Trees/_04_TreeDistancesII.java), technique, teaches how to reach goal using 2 or more DFS calls
- <details>
  <summary>LCA and applicaions</summary>
  
  LCA and applications, solve from __05__to__07__, LCA dode is as below, also feel the importance of maintaining `parent[u][2^i]`,`level[u].

  ```java
  static void build_levels_and_initialize_parents(int u,int  par,int currLevl){
        level[u]=currLevl;
        for(int v:adj[u]){
            if(v!=par){
                up[v][0]=u;
                build_levels_and_initialize_parents(v, u, currLevl+1); 
            }
        }
    }

    static int get_lca(int u,int v){
        if(level[u]<level[v]){int temp=u;u=v;v=temp;}
        //i will bring u to same levelas that of v
        int k=level[u]-level[v];
        for(int i=0;i<=30;i++) if((k&(1<<i))!=0) if(u!=-1) u=up[u][i]; else break;
        if(u==v) return u;
        for(int i=30;i>=0;i--){
            if(up[u][i]!=-1){
                if(up[u][i]!=up[v][i]){
                    u=up[u][i];
                    v=up[v][i];
                }
            }
        }
        int lca=up[u][0];
        return lca;
    }
  ```
</details>

- Learn how [Query Sum](/CP/some_preq/_01_Queries/_01_query_SUM.java) concept is used to solve [Counting Paths](/CP/some_preq/_06_cses/_02_DP/_02_Trees/_08_CountingPaths.java)