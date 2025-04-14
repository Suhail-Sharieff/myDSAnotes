package some_preq._06_cses._04_graph;
//-----------------VERY IMP AND INTERESTING QUESTION

/*
 You and some monsters are in a labyrinth. When taking a step to some direction in the labyrinth, each monster may simultaneously take one as well. Your goal is to reach one of the boundary squares without ever sharing a square with a monster.
Your task is to find out if your goal is possible, and if it is, print a path that you can follow. Your plan has to work in any situation; even if the monsters know your path beforehand.
Input
The first input line has two integers n and m: the height and width of the map.
After this there are n lines of m characters describing the map. Each character is . (floor), # (wall), A (start), or M (monster). There is exactly one A in the input.
Output
First print "YES" if your goal is possible, and "NO" otherwise.
If your goal is possible, also print an example of a valid path (the length of the path and its description using characters D, U, L, and R). You can print any path, as long as its length is at most n \cdot m steps.
Constraints

1 \le n,m \le 1000

Example 1
Input:
5 8
########
#M..A..#
#.#.M#.#
#M#..#..
#.######

Output:
YES
5
RRDDR


Example 2
Input:
8 8
###MMMMM
#.AMMMMM
#.#MMMMM
#.MMMMMM
...

Output:
NO, coz observe that before A reaches last second row, the monster in last second row can come left and not allow human to move
 */
/*******BISMILLAHIRRAHMAANIRRAHEEM*******/



//this could have been a straight forward question if monsters were not allowed to kve, but here all monsters can move and bloc human bfr he reaches any border, so the main idea is to predict for each cell the min time(using plain BFS) for any moster to reach any cell, we basically predict for each cell the min possible time by any monster to reach that cell, so that when we move human we can avoid that cell if human takes more time to reach it than monster-----thts all


import java.io.*;
import java.util.*;

public class _05_Monsters {
    public static void main(String[] args) throws IOException {
        // int t = scanInt();
        // while (t-- > 0) {
        solve();
        // }
    }

    public static void solve() throws IOException {
        int nRows = scanInt(), nCols = scanInt();
        char mat[][] = new char[nRows][nCols];
        for (int i = 0; i < nRows; i++) mat[i] = scanString().toCharArray();

        bfs(mat);
        
    }

    

    static int dirs[][] = {//make sure u take order of dirs as gievn in question
            { 1, 0, 'D' },
            { -1, 0, 'U' },
            { 0, -1, 'L' },
            { 0, 1, 'R' }
    };

   static void bfs(char mat[][]) throws IOException{

        //BFS FOR MONSTERS
        Queue<int[]>q=new LinkedList<>();//form: {xPos,yPos,distMovedByIt}
        int min_time_taken_by_any_monster_to_reach[][]=new int[mat.length][mat[0].length];//stores [i,j], min distance to reach it by any monster
        boolean  isVis_by_monster[][]=new boolean[mat.length][mat[0].length];

        //add all mosters to queue, we can also store start location of human first only
        int human_location_x=-1,human_location_y=-1;
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                min_time_taken_by_any_monster_to_reach[i][j]=Integer.MAX_VALUE;
                if(mat[i][j]=='M'){
                    q.offer(new int[]{i,j,0});
                    isVis_by_monster[i][j]=true;
                }
                else if(mat[i][j]=='A'){
                    human_location_x=i;
                    human_location_y=j;
                }
            }
        }
        //do BFS for monsters
        while(!q.isEmpty()){
            int top[]=q.poll();
            for(int dir[]:dirs){
                int x=top[0]+dir[0];
                int y=top[1]+dir[1];
                int curr_time=top[2];
                if(x>=0 && x<mat.length && y>=0 && y<mat[0].length){
                    if(mat[x][y]!='#' && !isVis_by_monster[x][y]){//is not a wall and not visited
                        isVis_by_monster[x][y]=true;
                        q.offer(new int[]{x,y,curr_time+1});
                        min_time_taken_by_any_monster_to_reach[x][y]=curr_time+1;
                    }
                }
            }
        }


       //do BFS for human, we can use same Q since its empty now
       boolean  isVis_by_human[][]=new boolean[mat.length][mat[0].length];
       int parentDir[][][]=new int[mat.length][mat[0].length][3];//{x,y,dir} like {i,j,L}
       q.offer(new int[]{human_location_x,human_location_y,0});
       while (!q.isEmpty()) {
            int top[]=q.poll();
             //at any point of time human reaches border, its YES
             if(top[0]==0 || top[0]==mat.length-1 || top[1]==0 || top[1]==mat[0].length-1){//reached grid border
                print("YES");
                print(top[2]);//print direction

                StringBuilder ans=new StringBuilder();
                int curr[]=top;
                while (curr[0]!=human_location_x || curr[1]!=human_location_y) {
                    ans.append((char)parentDir[curr[0]][curr[1]][2]);//print direction
                    int temp[]=parentDir[curr[0]][curr[1]];
                    curr[0]=temp[0];
                    curr[1]=temp[1];
                }
                print(ans.reverse());
                return;
            }
            for(int dir[]:dirs){
                int x=top[0]+dir[0];
                int y=top[1]+dir[1];
                int curr_time=top[2];
                if(x>=0 && x<mat.length && y>=0 && y<mat[0].length){
                    if (mat[x][y]=='.' && !isVis_by_human[x][y]) {
                        if (curr_time+1 < min_time_taken_by_any_monster_to_reach[x][y]) {//if human can reach that point bfr monster
                            q.offer(new int[]{x,y,curr_time+1});
                            isVis_by_human[x][y]=true;
                            parentDir[x][y]=new int[]{top[0],top[1],dir[2]};
                        }
                    }
                }
            }
       }


       print("NO");


   }


   

    static int MOD = 1_000_000_007;
    static long fact[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int scanInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    static long scanLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    static String scanString() throws IOException {
        return nextToken();
    }

    static int[] scanIntArray(int size) throws IOException {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanInt();
        }
        return array;
    }

    static int GCD(int a, int b) {
        return (b == 0) ? (a) : GCD(b, a % b);
    }

    static int LCM(int a, int b) {
        return ((a * b) / GCD(a, b));
    }

    static String nextToken() throws IOException {
        if (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static List<Integer> getPrimeList(int from, int tillWhere) {
        boolean isPrime[] = new boolean[tillWhere + 1];
        List<Integer> primesList = new ArrayList<>();
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= tillWhere; i++) {
            if (isPrime[i]) {
                if (i >= from) {
                    primesList.add(i);
                }
                for (int j = i * i; j <= tillWhere; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return primesList;
    }

    static List<Integer> getDivisorListOf(int num) {
        List<Integer> divisorList = new ArrayList<>();
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                divisorList.add(i);
                if (num / i != i) {
                    divisorList.add(num / i);
                }
            }
        }
        return divisorList;
    }

    static void printArray(int arr[]) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int e : arr) {
            sb.append(e + " ");
        }
        bw.write(sb.toString().trim());
        bw.newLine();
        bw.flush();
    }

    static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if ((n % i) == 0) {
                return false;
            }
        }
        return true;
    }

    static List<Integer> getPrimeFactorsListOf(int num) {
        if (num <= 1) {
            return new ArrayList<>();
        }
        List<Integer> primefactorsList = new ArrayList<>();
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                primefactorsList.add(i);
                while (num % i == 0) {
                    num /= i;
                }
            }
        }
        if (num != 1) {
            primefactorsList.add(num);
        }
        return primefactorsList;
    }

    static long pow(long base, long exp) {
        long ans = 1l;
        boolean isNegativeExponent = exp < 0;
        exp = Math.abs(exp);
        while (exp > 0) {
            if ((exp & 1) == 1) {
                ans = (ans * base * 1l) % MOD;
            }
            base = (base * base * 1l) % MOD;
            exp >>= 1;
        }
        return isNegativeExponent ? (1l / ans) : ans;
    }

    static void compute_fact() {
        fact = new long[100001];
        fact[0] = fact[1] = 1;
        for (int i = 2; i <= 100000; i++) {
            fact[i] = (i * 1l * fact[i - 1]) % MOD;
        }
    }

    static long nCr(int n, int r) {
        long nr = fact[n];
        long dr = (fact[n - r] * 1l * fact[r]) % MOD;
        long inv = pow(dr, MOD - 2);// using fermat little theorm, inverse(x)=pow(x,m-2) given m is prime
        long ans = (nr * 1l * inv) % MOD;
        return ans;
    }

    static void print(Object o) throws IOException {
        bw.write(o.toString());
        bw.newLine();
        bw.flush();
    }

    static List<List<Integer>> get_adj(int graph[][], int nNodes) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= nNodes; i++)
            adj.add(new ArrayList<>());
        for (int con[] : graph) {
            adj.get(con[0]).add(con[1]);
            adj.get(con[1]).add(con[0]);
        }
        return adj;
    }

}
