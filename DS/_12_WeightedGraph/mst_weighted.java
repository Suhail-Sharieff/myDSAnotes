package _12_WeightedGraph;
//prim's algo
import java.util.*;

class graphVertex{
    //every vertx has some name/label like A,B etc
    public char label;
    public boolean isInTree;
    public graphVertex(char label){
        this.label=label;
        isInTree=false;
    }
}
class edge{
    public int sourceVert;
    public int destinationVertex;
    public int weight;//assujme it as equiiavalent to distance here

    public edge(int sourceVert,int destinationVertex,int weight){
        this.sourceVert=sourceVert;
        this.destinationVertex=destinationVertex;
        this.weight=weight;
    }
}
class myPriorityQ{
    //PQ of max elemnts at bottom ie at index 0 and min elemnts at index size-1
    private final int size=20;
    private edge[] weightArr;//creating a PQ of edge's weight
    private int nElmnts;

    public myPriorityQ(){
        weightArr=new edge[size];
        nElmnts=0;
    }
    public void insertWeightedEdge(edge item){
        //same impletation as that of inserting an elemnty in a prioority quue
        int j=0;
        for ( j = 0; j < nElmnts; j++) {//find place to insert
            if (item.weight>=weightArr[j].weight) {
                break;
            }
        }
        for (int i = nElmnts-1; i >=j; i--) {//move up items up
            weightArr[i+1]=weightArr[i];
        }
        weightArr[j]=item;//insert item
        nElmnts++;
    }
    public edge removeMinWeightedEdge(){
        return weightArr[--nElmnts];
    }
    public void removeEdgeAtIndex(int index){
        for (int i = index; i < nElmnts-1; i++) {
            weightArr[i]=weightArr[i+1];
        }
        nElmnts--;
    }
    public edge peekMinEdge(){//min weight at top of PQ
        return weightArr[nElmnts-1];
    }
    public int size(){
        return  nElmnts;
    }
    public boolean isEmpty(){
        return nElmnts==0;
    }
    public edge peekNthEdge(int index){
        return weightArr[index];
    }
    public int indexOf(int weight){
        for (int i = 0; i < nElmnts; i++) {
            if (weightArr[i].destinationVertex==weight) {
                return i;
            }
        }
        return -1;//not found
    }
}
class myGraph{
    private final int MAX_VERTS=20;
    private final int INFINITY=1000000;//we will asssign the vertex which doesnt have edge with our current vertex (initially 1st) as infinite
    private graphVertex vertexArr[];
    private int adjancyMatrix[][];
    private int nVerticesInGraph;
    private int indexOfCurrVertex;
    private myPriorityQ pq;
    private int nVerticesInTree;

    public myGraph(){
        vertexArr=new graphVertex[MAX_VERTS];
        adjancyMatrix=new int[MAX_VERTS][MAX_VERTS];
        nVerticesInGraph=0;
        for (int[] row : adjancyMatrix) {
            Arrays.fill(row, 0);
        }
        pq=new myPriorityQ();
    }
    public void addVertex(char label){
        vertexArr[nVerticesInGraph++]=new graphVertex(label);
    }
    public void addEdge(int from,int to,int weight){
        adjancyMatrix[from][to]=weight;
        adjancyMatrix[to][from]=weight;
    }
    public void displayVertex(int indexOfVertex){
        System.out.print(vertexArr[indexOfVertex].label);
    }
    //VVIMP
    public void TRAVERSE_MST_WEIGHTED(){
        indexOfCurrVertex=0;//starrt from (0,0) in adjMatrix

        while (nVerticesInTree<nVerticesInGraph-1) {//while not all verts in tree put curr vertex in tree
            vertexArr[indexOfCurrVertex].isInTree=true;
            nVerticesInTree++;
            //Insert edges to current vertex in PQ
            for (int i = 0; i < nVerticesInGraph; i++) {
                if (i==indexOfCurrVertex) {
                    continue;
                }
                if (vertexArr[i].isInTree) {//skip if already inserted into tree
                    continue;
                }
                int weight=adjancyMatrix[indexOfCurrVertex][i];
                if (weight==INFINITY) {//continue if no edge
                    continue;
                }
                putWeightOfEdgeInPQ(i, weight);

            }

            if (pq.size()==0) {
                System.out.println("Graph not connected");
                return;
            }

            //remove edge with min distance from pq
            edge theEdge=pq.removeMinWeightedEdge();
            int sourceVertx=theEdge.sourceVert;
            indexOfCurrVertex=theEdge.destinationVertex;

            //display Edge from source to curr
            System.out.print(vertexArr[sourceVertx].label);
            System.out.print(vertexArr[indexOfCurrVertex].label);
            System.out.print(" --> ");
        }

        ////mst is complete, unmark all vertx
        for (int i = 0; i < nVerticesInGraph; i++) {
            vertexArr[i].isInTree=false;
        }
    }
    public void putWeightOfEdgeInPQ(int newIndexOfVertex,int newWeightOfEdge){
        //First find if there is another edge with the same destination
        int qIndex=pq.indexOf(newIndexOfVertex);
        if (qIndex!=-1) {//got edge's index
            edge tempEdge=pq.peekNthEdge(qIndex);//get that edge
             int oldWeight=tempEdge.weight;
            if (oldWeight>newWeightOfEdge) {
                pq.removeEdgeAtIndex(qIndex);
                edge theEdge=new edge(indexOfCurrVertex, newIndexOfVertex, newWeightOfEdge);
                pq.insertWeightedEdge(theEdge);
            }
            
        }else{//no edge with same destination vcertex
            edge thgEdge=new edge(indexOfCurrVertex, newIndexOfVertex, newWeightOfEdge);
            pq.insertWeightedEdge(thgEdge);
        }
    }

}


public class mst_weighted {
    public static void main(String[] args) {
        myGraph g=new myGraph();
        g.addVertex('A');
        g.addVertex('B');
        g.addVertex('C');
        g.addVertex('D');
        g.addVertex('E');
        g.addVertex('F');

        g.addEdge(0, 1, 6);//AB 6
        g.addEdge(0, 3, 4);//AD 4
        g.addEdge(1, 2, 10);//BC 10
        g.addEdge(1, 3, 7);//BD 7
        g.addEdge(1, 4, 7);//BE 7
        g.addEdge(2, 3, 8);//CD 8
        g.addEdge(2, 4, 5);//CE 5
        g.addEdge(2, 5, 6);//CF 6
        g.addEdge(3, 4, 12);//DE 12
        g.addEdge(4, 5, 7);// EF 7

        System.out.println("MST:");
        g.TRAVERSE_MST_WEIGHTED();
    }
}
