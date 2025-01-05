package _10_Heaps;


class heapNode{
    public int data;
    public heapNode(int data){
        this.data=data;
    }
}



//if curr node's index in arr is i, then it's parent's index is ((i-1)/2), its left child index is (2i+1), right child's index is (2i+2);


public class descendingHeap {
    //In this case the root is having the maximum value that is highest among all the descendants and each and every node is going to have maximum value against all its children that is descendants
    private heapNode arr[];
    private int nElements;
    private int maxSize;
    public descendingHeap(int maxSize){
        this.maxSize=maxSize;
        nElements=0;
        arr=new heapNode[maxSize];
    }
    public boolean isEmpty(){
        return nElements==0;
    }
    public void insert(int val){
        heapNode newNode=new heapNode(val);
        //first add that node into last of arr and then rearrange by sending it up ie "tricklin up"/"bubbling up to right place in tree"
        if (nElements==maxSize) {
            System.out.println("Tree Heap is full, cant insert");
            return;
        }
        arr[nElements]=newNode;
        trickleUp(nElements++);//to arrange its pos in tree correcly
        System.out.println("Inserted successfully. Heap array is:");
        displayHeap();

    }
    public void trickleUp(int indexOfNewNode){
        //u need to tricle up by swapping newNode's val by swapping it with its parent if its val is higher than parent's
        int indexOfParent=(indexOfNewNode-1)/2;
        heapNode lastNode=arr[indexOfNewNode];//coz we have inserted any newNode at last of array,so store it somewhere for first time we inserted it
        while (indexOfNewNode>0&&lastNode.data>arr[indexOfParent].data) {//jab tak newNode ki value uske parent se badi hai ,swap kar kar kar upar bhejo
            arr[indexOfNewNode]=arr[indexOfParent];//swapped
            indexOfNewNode=indexOfParent;//after swapping its newIndex becomes equal to its parent's index ryt
            //now need to compare with parent's parent,ie newNode's new parent after swapping
            indexOfParent=(indexOfParent-1)/2;
        }
    }
    public heapNode removeRoot(){//its permitted to remove only root, coz if we remove in  any random location, the tree heap could become incompplete, which violates the fact that "a heap is always a complete tree"

        //replace root by last elemnt in array, obviosuly it will be smaller than its children,so we swap it with higher val of its children untill it(replaced elent) bexomes greater than all its desendants ie "trickleDown untill u become gretaest of your desendants"

        //root is present at first for any desending heap
        heapNode root=arr[0];
        arr[0]=arr[--nElements];//replace root's val with last elemnt's val
        trickleDown(0);
        System.out.println("Removed root successfully(ie max elemnt). Heap arr is:");
        displayHeap();
        return root;
    }
    public void trickleDown(int indexOfCurrRoot){
        int indexOfLargerChild;
        heapNode firstNode=arr[indexOfCurrRoot];//start from root which now has elemnt which was last elemnt of heaparr
        while (indexOfCurrRoot<nElements/2) {//while node has atleast one child
            int indexOfLeftChild=2*indexOfCurrRoot+1;
            int indexOfRightChild=2*indexOfCurrRoot+2;

            if (indexOfRightChild<nElements&&arr[indexOfRightChild].data>arr[indexOfLeftChild].data) {//if right child exists and is greater than its left child
                indexOfLargerChild=indexOfRightChild;
            }else{
                indexOfLargerChild=indexOfLeftChild;
            }

            if (firstNode.data>=arr[indexOfLargerChild].data) {//if firstNode is already larger than both of its children
                break;
            }

            //else:
            arr[indexOfCurrRoot]=arr[indexOfLargerChild];
            indexOfCurrRoot=indexOfLargerChild;
        }
        //root bring to lastindex to be swapped
        arr[indexOfCurrRoot]=firstNode;

    }
    public void changeValAt(int index,int valueToReplace){
        if (index<0||index>=nElements) {
            System.out.println("Index cant exceed "+nElements);
            return;
        }
        int currVal=arr[index].data;
        arr[index].data=valueToReplace;
        //IMP
        if (currVal<valueToReplace) {
            trickleUp(index);
        }else{
            trickleDown(index);
        }
        System.out.println(arr[index].data+" at index "+index+" is now changed to "+valueToReplace+". New heap is:");
        displayHeap();

    }
    public void displayHeap(){
       for (int i = 0; i < nElements; i++) {
        if (arr[i]!=null) {
            System.out.print(arr[i].data+" ");
        }
       }
        System.out.println();
    }
    public void displaySortedHeap(){//always call this method at last coz it gonna make heap empty
        int sorted[]=new int[nElements];
        for (int i = 0; i <nElements+2; i++) {
            sorted[i]=removeRoot().data;
        }
        System.out.println("Sorted heapArray is:");
        for (int i : sorted) {
            System.out.print(i+" ");
        }
        System.out.println();

    }
    public static void main(String[] args) {
        descendingHeap h= new descendingHeap(5);
        h.insert(70);
        h.insert(40);
        h.insert(50);
        h.insert(20);
        h.removeRoot();
        h.changeValAt(1, 45);
        h.displayHeap();
        h.displaySortedHeap();//always call it at last coz by then heap is completely consumed
    }
}
