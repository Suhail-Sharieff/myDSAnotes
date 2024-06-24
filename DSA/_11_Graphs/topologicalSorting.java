package _11_Graphs;

import java.util.Arrays;

//refer to that pdf



public class topologicalSorting {
    private final int maxVertices=20;
    private graphVertex arr[];//graph to hold vertex
    private int adjMatrix[][];
    private int nVertices;
    //IMP
    private char topoSortArr[];

    public topologicalSorting(){
        arr=new graphVertex[maxVertices];
        adjMatrix=new int[maxVertices][maxVertices];
        nVertices=0;
        //instantiting all edges to 0
        for (int[] row : adjMatrix) {
            Arrays.fill(row, 0);
        }
        topoSortArr=new char[maxVertices];
    }
    public void addVertex(char label){
        arr[nVertices++]=new graphVertex(label);
    }
    public void addEdge(int fromVertexIndex,int toVertexIndex){
        adjMatrix[fromVertexIndex][toVertexIndex]=1;
        //NOTE: no adjMatrix[to][from] coz its directed graph
    }
    public void displayVertex(int nThVertex){
        System.out.print(arr[nThVertex].label+" --> ");
    }

    //IMP
    //start with vertex having no successor or no child
    //delete it
    //repeat process 1 and 2
    public void traverseTopologically(){
        int orig_nVertices=nVertices;

        while (nVertices>0) {
            int indexOfVertexWithNoSuccessors=findIndexOfVertexWithNoSuccessors();
            if (indexOfVertexWithNoSuccessors==-1) {//has cycles or is an un-directed graph
                System.out.println("Graph has cycles");
                return;
            }
            //store those vertexWithNoSucc in topoSortedEmtyArray 
            //fill it from last
            topoSortArr[nVertices-1]=arr[indexOfVertexWithNoSuccessors].label;

            //now delete that vertex and move nxt
            deleteVertex(indexOfVertexWithNoSuccessors);

        }

        //now all vertex are gone display topoSortedArr
        System.out.println("Topologcal traversal/sorting is :");
        for (int i = 0; i < orig_nVertices; i++) {
            System.out.print(topoSortArr[i]+" --> ");
        }
        System.out.println();
    }
    public int findIndexOfVertexWithNoSuccessors(){
        boolean hasEdge;
        //search throughout matrix if any vertex has no successors
        for(int row=0;row<nVertices;row++){
            hasEdge=false;
            for(int column=0;column<nVertices;column++){
                if (adjMatrix[row][column]==1) {
                    hasEdge=true;
                    break;//that row has some edge ie has succossor,move to next row---breaking out of this inner loop only
                }
            }
            if (!hasEdge) {//if that row doesnt have any edge ie inner loop didnt find any 1 amng all columns of that row
                return row;
            }
        }
        return -1;//no such vertex
    }

    //----VIMP
    public void deleteVertex(int indexOfVertexWithNoSuccessors){
        if (indexOfVertexWithNoSuccessors!=nVertices-1) {//if not last vertex in graph
            for (int i = indexOfVertexWithNoSuccessors; i < nVertices-1; i++) {
                arr[i]=arr[i+1];//delete vertex from arr
            }

            //to delete that row from adJMatrix
            for (int row = indexOfVertexWithNoSuccessors; row < nVertices-1; row++) {
                //to moveRowUp
                for (int column = 0; column < nVertices; column++) {
                    adjMatrix[row][column]=adjMatrix[row+1][column];
                }
            }

            //to delete that column from adJmatrix
            for (int column = indexOfVertexWithNoSuccessors; column < nVertices-1; column++) {
                //to move column left
                for (int row = 0; row < nVertices; row++) {
                    adjMatrix[row][column]=adjMatrix[row][column+1];
                }
            }
        }
        nVertices--;
    }
      public static void main(String[] args) {
        topologicalSorting g=new topologicalSorting();
        g.addVertex('A');//0
        g.addVertex('B');//1
        g.addVertex('C');//2
        g.addVertex('D');//3
        g.addVertex('E');//4
        g.addVertex('F');//5
        g.addVertex('G');//6
        g.addVertex('H');//7

        g.addEdge(0,3);//AE
        g.addEdge(0,4);//BE
        g.addEdge(1,4);//BE
        g.addEdge(2,5);//CF
        g.addEdge(3,6);//DG
        g.addEdge(4,6);//EG
        g.addEdge(5,7);//FH
        g.addEdge(6,7);//GH
        
        g.traverseTopologically();
    }
}
