package some_preq._06_cses._02_DP._02_Trees;


//---------------------Euler Tour



/*******BISMILLAHIRRAHMAANIRRAHEEM*******/
/*You are given a rooted tree consisting of n nodes. The nodes are numbered 1,2,\ldots,n, and node 1 is the root. Each node has a value.
Your task is to process following types of queries:

change the value of node s to x
calculate the sum of values in the subtree of node s

Input
The first input line contains two integers n and q: the number of nodes and queries. The nodes are numbered 1,2,\ldots,n.
The next line has n integers v_1,v_2,\ldots,v_n: the value of each node.
Then there are n-1 lines describing the edges. Each line contans two integers a and b: there is an edge between nodes a and b.
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
2 3
1 5 3
2 3

Output:
8
10 */


//https://www.youtube.com/watch?v=wo--reAZJZU



//core idea: if i some how track the entry and exit times of dfs for each node, then map each entry time 't' to the value of node visited at time 't', now suppose if i want the sum of subtree rooted at 'u', then answer is sum of all nodes values visited in interval [entry[u],exit[u]] ie can be easily found using Segment Tree in LogN for each query 
import java.io.*;
import java.util.*;

public class _09_SubtreeQueries{
    public static void main(String[] args) throws IOException {
        scanner = new FastScanner();
        writer = new PrintWriter(System.out);
        solve();
        writer.flush();
    }

    static List<Integer>adj[];
    static int val[];//value of ith node
    static int entry[];//entry time of ith node
    static int exit[];//exit time of ith node
    static int entry_vs_val[];//map of entry time and value of node visted at entry time i

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
        entry_vs_val=new int[nv];   
        order=0;
        build_timings(0, -1);


        SegTree seg=new SegTree(entry_vs_val);
        while (nq-->0) {
            int type=scanInt();
            int root=scanInt()-1;
            if(type==2){
                println(seg.getSum(0, 0, nv-1, entry[root], exit[root]));
            }else{
                seg.update(0, 0, nv-1, entry[root], scanInt());
            }
        }
    }

    static int order;
    static void build_timings(int u,int par){
        entry[u]=order++;
        entry_vs_val[entry[u]]=val[u];
        for(int v:adj[u]) if(v!=par) build_timings(v, u);
        exit[u]=order-1;
    }

    static class SegTree{
        int seg[];
        int val[];
        SegTree(int val[]){
            this.val=val;
            int n=val.length;
            seg=new int[n<<2];
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
            if(l>=f&&t>=r) return seg[i];
            int mid=(l+r)>>1;
            return getSum(2*i+1, l, mid, f, t)+getSum(2*i+2, mid+1, r, f, t);
        }
    }

    static FastScanner scanner;
    static PrintWriter writer;

    static int scanInt() throws IOException {
        return scanner.nextInt();
    }

    static long scanLong() throws IOException {
        return scanner.nextLong();
    }

    static String scanString() throws IOException {
        return scanner.nextString();
    }

    static String scanLine() throws IOException {
        return scanner.nextLine();
    }

    static int[] scanIntArray(int size) throws IOException {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanInt();
        }
        return array;
    }

    static long[] scanLongArray(int size) throws IOException {
        long array[] = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanLong();
        }
        return array;
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
