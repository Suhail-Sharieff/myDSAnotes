package _11_Graphs;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * grpahs
 */

//read dfs.pdf&&bfs.pdf before
class graphVertex{
    //every vertx has some name/label like A,B etc
    public char label;
    public boolean wasVisited;
    public graphVertex(char label){
        this.label=label;
        wasVisited=false;
    }
}

class myGraph{
    private final int maxVertices=20;
    private graphVertex arr[];//graph to hold vertex
    private int adjMatrix[][];
    private int nVertices;

    public myGraph(){
        arr=new graphVertex[maxVertices];
        adjMatrix=new int[maxVertices][maxVertices];
        nVertices=0;
        //instantiting all edges to 0
        for (int[] row : adjMatrix) {
            Arrays.fill(row, 0);
        }
    }
    public void addVertex(char label){
        arr[nVertices++]=new graphVertex(label);
    }
    public void addEdge(int fromVertexIndex,int toVertexIndex){
        adjMatrix[fromVertexIndex][toVertexIndex]=1;
        adjMatrix[toVertexIndex][fromVertexIndex]=1;
    }
    public void displayVertex(int nThVertex){
        System.out.print(arr[nThVertex].label+" --> ");
    }
    //DFS
    public int getAdjUnvisitedVertex(int nThVertex){//returns index of unvisited vertex in specified row in which it is called
        for (int column = 0; column < nVertices; column++) {
            if (adjMatrix[nThVertex][column]==1 && !arr[column].wasVisited) {//move column by column to find if edge is there btw 2 vertex....will make it visited during search process
                return column;
            }
        }
        return -1;//everyone was visited for that vertex onwards
    }
    public void DFS(){
        Stack<Integer>st=new Stack<>();
        arr[0].wasVisited=true;//make first vertex prevsisted
        displayVertex(0);
        st.push(0);
        
        while (!st.isEmpty()) {
            int indexOfUnvisited=getAdjUnvisitedVertex(st.peek());

            if (indexOfUnvisited==-1) {//if no unvisited vertex in that row
                st.pop();
            }else{
                arr[indexOfUnvisited].wasVisited=true;//mark it visited
                displayVertex(indexOfUnvisited);
                st.push(indexOfUnvisited);
            }
        }

        //statck is now empty, so restore all marked "wasVisted" to false
        for (int i = 0; i < nVertices; i++) {
            arr[i].wasVisited=false;
        }
        System.out.println();
    }
    public void BFS(){
        Queue<Integer>q=new ConcurrentLinkedQueue<>();
        arr[0].wasVisited=true;
        displayVertex(0);
        q.add(0);
        int currIndex;

        while (!q.isEmpty()) {
            int indexOfFront=q.poll();//removes and returns head of the queue(which is initially zero)--{could have used remove() method , but it may throw exception if q is empty,so i have used poll() which only returns null rather than exception}----initially indexOfFront is 0
            while ((currIndex=getAdjUnvisitedVertex(indexOfFront))!=-1) {
                arr[currIndex].wasVisited=true;
                displayVertex(currIndex);
                q.add(currIndex);
            }
        }

        //queue is now empty, so restore all marked "wasVisted" to false
        for (int i = 0; i < nVertices; i++) {
            arr[i].wasVisited=false;
        }
        System.out.println();
    }
    public void mst(){//min spanning tree
        Stack<Integer>st=new Stack<>();
        arr[0].wasVisited=true;//make first vertex prevsisted
        // displayVertex(0); //no need to display
        st.push(0);
        while (!st.isEmpty()) {
            int indexOfUnvisited=getAdjUnvisitedVertex(st.peek());
            int currVertex=st.peek();

            if (indexOfUnvisited==-1) {//if no unvisited vertex in that row
                st.pop();
            }else{
                arr[indexOfUnvisited].wasVisited=true;//mark it visited

                //display method we show edge like
                System.out.print("(");
                displayVertex(currVertex);
                displayVertex(indexOfUnvisited);
                System.out.print(")");

                st.push(indexOfUnvisited);
            }
        }
        for (int i = 0; i < nVertices; i++) {
            arr[i].wasVisited=false;
        }
        System.out.println();
    }

}


public class implementation {
    public static void main(String[] args) {
        myGraph g=new myGraph();
        g.addVertex('A');
        g.addVertex('B');
        g.addVertex('C');
        g.addVertex('D');
        g.addVertex('E');
        //see fig in dfs.pdf for ref im doing as per it only
        g.addEdge(0, 1);//connect A to B
        g.addEdge(1, 2);//connect B to C
        g.addEdge(0, 3);//connect A to D
        g.addEdge(3, 4);//connect D to E
        System.out.println("Visits sequence in DFS :");
        g.DFS();
        System.out.println("Visit sequence in BFS:");
        g.BFS();
        System.out.println("Visit sequence in MST:");
        g.mst();


    }
}