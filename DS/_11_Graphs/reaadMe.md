Graphs are a special type of data structures it is nothing but a tree in which a node can have multiple number of children they are actually not children's but here they are connections and each and every node shall now be called a vertex may be connected to another vertex or vertices by using edges. 

Adjacent vertex r those which r connected by a single egde

There are mainly two types of graphs Weighted and non-weighted graph

Weighted graphs are those in which we assign some value to that particular edge that can be used in order to find the shortest distance say in the map and non weighted graphs are those to whose edges we don't design any value


Complete graphs are the one in which intelligence one way through which a vertex can be reached through any vertex in a tree and non complete graphs are just vice versa


Every vertex in case of a graph shall have a label example like ABC and so on and we will have a boolean variable for each vertex to determine if it was previously visited or not

So the task is you now how to represent the edges

So So the edges in case of a graph can be represented by using ones and zeros if an edge is present in between two vertex A and B then it shall be represented by one or else it should be represented by zero so the basic idea is to form a 2D matrix called adjency matrix where ith row is going to represent the ith vertex and J th column is also going to represent the Jth vertex

there r imp 2 ways to search some vertex or value in it in graphs:
-->bredth first search(BFS)-----uses stack
-->depth first search(DFS)------uses queue


Minimum spanning trees (MST) for the special type of graph in which we try to reduce the number of edges that is a graph with minimal number of edges the main idea behind this would be to just make one edge between 2 vertex rather than mini edges from a single vertex it is as same as the dfs but the main difference is this mst somehow record the edges which are traveled. Instead of showing the vertex travelled like ABCDE, we show edges ie AB BC CD DE sequentially.

Directed graphs are those in which the edges point to some particular direction that is in case of a directed graph you can proceed only one way along can the main difference between a non directed graph and a directed graph is that an edge in a directed graph has only one entry in the adjancy matrix, ie Each row has only "1", unlike that of non directed where each row could have multiple "1"s. 

for addEdge func in:
Un-directed: arr[from][to]=1;arr[to][from]=1;
directed: arr[from][to]=1;-----x----------


Topological sorting basically refers to complete a prerequisite task first for another task which we want to do, ie in a directed fashion