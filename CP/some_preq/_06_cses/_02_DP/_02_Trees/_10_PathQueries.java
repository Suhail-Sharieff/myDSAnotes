
package some_preq._06_cses._02_DP._02_Trees;


//Similar to subtree queriess, wit lil modification

/*You are given a rooted tree consisting of n nodes. The nodes are numbered 1,2,\ldots,n, and node 1 is the root. Each node has a value.
Your task is to process following types of queries:

change the value of node s to x
calculate the sum of values on the path from the root to node s

Input
The first input line contains two integers n and q: the number of nodes and queries. The nodes are numbered 1,2,\ldots,n.
The next line has n integers v_1,v_2,\ldots,v_n: the value of each node.
Then there are n-1 lines describing the edges. Each line contains two integers a and b: there is an edge between nodes a and b.
Finally, there are q lines describing the queries. Each query is either of the form "1 s x" or "2 s".
Output
Print the answer to each query of type 2.
Constraints

1 \le n, q \le 2 \cdot 10^5
1 \le a,b, s \le n
1 \le v_i, x \le 10^9

Example
Input:
5 3
4 2 5 2 1
1 2
1 3
3 4
3 5
2 4
1 3 2
2 4

Output:
11
8 */


/*******BISMILLAHIRRAHMAANIRRAHEEM*******/




//https://www.youtube.com/watch?v=kIqZcI4hesg&list=PL-Jc9J83PIiHymm1DHZBkac0_hhFBXryO&index=6


//idea is same as that of subtree queries, but here we use the idea to nullifu those nodes what cannot come into that path by also adding -ve of val in seg[exit[u]], just do dry run f below example to know, the sum query would be as asked in question entry[0] to entry[u], for update, first make seg[entry[u]] as newVal, also update seg[exit[u]] as -newVal



/*

    Example:
                4(1st)
               / \
        2(2nd)   5(3rd)
                /  \
            2(4th)  1(5th)
            
    entry:  [0,1,3,4,6]
    exit:   [9,2,8,5,7]
 entryvsVal:[4,2,-2,5,2,-2,1,-1,-5,-6]---for path sum problem
 entryvsVal:[4,2,0,5,2,0,1,0,0,0]---for subtree sum problem,observe difference

 Suppose u want sum of path from 1st to 5th

 entry of 1st=0, entry of 5th=6
  sum=seg.getSum(0,6)

  ideally it should be 4+5+1 ie 10 , the seg tree would sum from 0..6 == 4+2+-2+5+2+-2+1 ie 10, observe that u r doing 4+5+1+(+2-2), the one in parenthesis is the contribution of 4th node which we nullify by addind -2


 */

import java.io.*;
import java.util.*;

public class _10_PathQueries{
    public static void main(String[] args) throws IOException {
        scanner = new FastScanner();
        writer = new PrintWriter(System.out);
        solve();
        writer.flush();
    }

    static List<Integer>[]adj;
    static int time;
    static int val[];
    static int entry[];
    static int exit[];
    static long entry_vs_val[];

    @SuppressWarnings("unchecked")
    public static void solve() throws IOException {
        int nv=scanInt();
        int nq=scanInt();
        val=scanIntArray(nv);

        adj=new ArrayList[nv];
        for(int i=0;i<nv;i++) adj[i]=new ArrayList<>();
        for(int i=1;i<nv;i++){
            int u=scanInt()-1;
            int v=scanInt()-1;
            adj[u].add(v);
            adj[v].add(u);
        }
        entry=new int[nv];
        exit=new int[nv];
        entry_vs_val=new long[2*nv];
        time=0;
        build_timings(0,-1);

        // println(Arrays.toString(entry));
        // println(Arrays.toString(exit));
        // println(Arrays.toString(entry_vs_val));


        SegTree seg=new SegTree(entry_vs_val);

        while (nq-->0) {
            int type=scanInt();
            int root=scanInt()-1;
            if(type==2){
                println(seg.getSum(0, 0, entry_vs_val.length-1, entry[0], entry[root]));//sum from entry[0](ie 0) to entry[u]
            }else{
                int newVal=scanInt();
                seg.update(0, 0, entry_vs_val.length-1, entry[root], newVal);
                seg.update(0, 0, entry_vs_val.length-1, exit[root], -newVal);//extra,----MISTAKE:forgot this update
            }
        }

    }


    static void build_timings(int u,int par){
        entry[u]=time++;
        entry_vs_val[entry[u]]=val[u];
        for(int v:adj[u]) if(v!=par) build_timings(v, u);
        exit[u]=time++;
        entry_vs_val[exit[u]]=-val[u];//extra
    }

    static int[] scanIntArray(int nv) throws IOException {
        int arr[]=new int[nv];
        for(int i=0;i<nv;i++) arr[i]=scanInt();
        return arr;
    }

    static class SegTree{
        long seg[];
        long val[];
        SegTree(long[] entry_vs_val){
            this.val=entry_vs_val;
            int n=entry_vs_val.length;
            seg=new long[n<<2];
            build(0, 0, n-1);
        }
        void build(int i,int l,int r){
            if(l==r){seg[i]=val[l];return;}
            int mid=(l+r)>>1;
            build(2*i+1, l, mid);
            build(2*i+2, mid+1, r);
            seg[i]=seg[2*i+1]+seg[2*i+2];
        }
        void update(int i,int l,int r,int ti,int tv){
            if(l==r){seg[i]=val[ti]=tv;return;}
            int mid=(l+r)>>1;
            if(ti<=mid) update(2*i+1, l, mid, ti, tv);
            else update(2*i+2, mid+1, r, ti, tv);
            seg[i]=seg[2*i+1]+seg[2*i+2];
        }
        long getSum(int i,int l,int r,int f,int t){
            if(r<f||t<l) return 0l;
            if(l>=f&&t>=r) return seg[i]*1l;
            int mid=(l+r)>>1;
            return getSum(2*i+1, l, mid, f, t)+getSum(2*i+2, mid+1, r, f, t);
        }
    }

    static FastScanner scanner;
    static PrintWriter writer;

    static int scanInt() throws IOException {
        return scanner.nextInt();
    }
 
    static class FastScanner {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        private FastScanner() throws IOException {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
                ret = ret * 10 + c - '0';
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
                ret = ret * 10 + c - '0';
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public char nextChar() throws IOException {
            byte c = read();
            while (c <= ' ')
                c = read();
            return (char) c;
        }

        public String nextString() throws IOException {
            StringBuilder ret = new StringBuilder();
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret.append((char) c);
            } while ((c = read()) > ' ');
            return ret.toString();
        }

        public String nextLine() throws IOException {
            StringBuilder ret = new StringBuilder();
            byte c = read();
            while (c == '\n' || c == '\r') c = read(); // skip newlines
            do {
                ret.append((char) c);
                c = read();
            } while (c != -1 && c != '\n' && c != '\r');
            return ret.toString();
        }

        public void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        public byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }


    static void println(Object o) throws IOException {
        writer.println(o.toString());
    }

}
