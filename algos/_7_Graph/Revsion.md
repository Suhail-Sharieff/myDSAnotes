## Revision Set
- [Number islands](./_00_Matrix/_01_BFS_DFS/_2_count_Islands.java) vs [Number of disticnt islands](./_00_Matrix//_01_BFS_DFS/_8_nDistinct_Islands.java), see how we can find distict isalnds by appending `(x-nx)(y-nx)` or also while backtracking how we smartly append some dummy char
- [Cheapest flights with k stops](_00_Matrix/_02_djikstra/_03_cheapest_flights_with_k_stops.java), pretty standard problem, understand when to use PQ and when to Q for BFS
- [Cycle Detection](/algos/_7_Graph/_01_Undir_Unweigh/_01_detect_cycle.java), learn detecting cycle in undir(if neigh!=srcICameFrom) and directed(kahn or if visited and dfs done), similar stuff works for [Bipartite Graph](./_01_Undir_Unweigh/_02_bipartite_graph.java)
- See how similar are [Number of critical connec](./_01_Undir_Unweigh/_03_number_of_critical_connections.java) and [Articulation Points](./_01_Undir_Unweigh/_04_articulation_points.java) revise smart usage of arrival times and caching oldest neigh bours reachable for graphs(also used in tree problems)--uses `Tarjan`
- How reversing edges and multiple DFS calls can help for some problems, ex [Strongly connected Componenets](./_02_Dir_Unweigh/_06_strongly_connected_componenets.java)
- Pretty standard string problems that uses graph algorithms are [Alien Dictionary](./_02_Dir_Unweigh/_04_alien_dictionary.java)(easy type) and [Word ladder](./_02_Dir_Unweigh/_05_word_ladder.java)(lil hard type)
- [Application of Floyd warshal](./_03_Undir_weigh/_03_city_with_smallest_number_of_neigh.java), see how computing smallest distance from i to j can help us
- [Prim(uses Pq) and Kruskal(uses DSU)](./_03_Undir_weigh/_04_minimum_spanning_tree.java) to form MST

