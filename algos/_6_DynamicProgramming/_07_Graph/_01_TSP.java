package _6_DynamicProgramming._07_Graph;


/*Travelling salesman problem: 
 * 
 Given a matrix cost of size n where cost[i][j] denotes the cost of moving from city i to city j. Your task is to complete a tour from city 0 (0-based index) to all other cities such that you visit each city exactly once and then at the end come back to city 0 at minimum cost.
 * 
 */

public class _01_TSP {

    static class Optimal {
        public static void main(String[] args) {
            int mat[][] = {
                    { 0, 2, 5, 7 },
                    { 2, 0, 8, 3 },
                    { 5, 8, 0, 1 },
                    { 7, 3, 1, 0 },
            };
            System.out.println(optimal(mat));
        }

        // better(0001, 0, mat, nCities);
        static int better(int currMask, int currCity, int cost[][], int nCities) {
            if (currMask == (1 << nCities) - 1) {
                return cost[currCity][0];
            }
            int ans = Integer.MAX_VALUE;
            for (int newCity = 0; newCity < nCities; newCity++) {
                boolean isNewCityAlreadyVisited = (currMask & (1 << newCity)) != 0;
                if (!isNewCityAlreadyVisited) {
                    int newMask = ((1 << newCity) | currMask);
                    ans = Math.min(ans, cost[currCity][newCity] + better(newMask, newCity, cost, nCities));
                }
            }
            return ans;
        }

        // calllike: brute(0, mat, nCities, 1, new boolean[nCities], new
        // StringBuilder(), 0);
        static int brute(int currCity, int cost[][], int nCities, int nCovered, boolean isVis[], StringBuilder sb,
                int sum) {
            if (nCovered == nCities) {
                System.out.println("0->" + sb + "0 Cost=" + sum);
                return cost[currCity][0];
            }
            isVis[currCity] = true;
            int min = Integer.MAX_VALUE;
            for (int newCity = 0; newCity < nCities; newCity++) {
                if (isVis[newCity])
                    continue;
                min = Math.min(min, cost[currCity][newCity] + brute(newCity, cost, nCities, nCovered + 1, isVis,
                        new StringBuilder(sb).append(newCity).append("->"), sum + cost[currCity][newCity]));
            }
            isVis[currCity] = false;
            return min;
        }
    }

    static int optimal(int cost[][]) {
        int nCities = cost.length;
        int dp[][] = new int[(1 << nCities)][nCities];
        // currMask going from 1 to (1<<nCities)-1,currCity form 0 to nCities-1
        for(int newCity=0;newCity<nCities;newCity++) dp[(1<<nCities)-1][newCity]=cost[newCity][0];
        for (int currMask = (1 << nCities) - 2; currMask >=0; currMask--) {
            for (int currCity = nCities-1; currCity>=0; currCity--) {
                int ans = Integer.MAX_VALUE;
                for (int newCity = 0; newCity < nCities; newCity++) {
                    boolean isNewCityAlreadyVisited = (currMask & (1 << newCity)) != 0;
                    if (!isNewCityAlreadyVisited) {
                        int newMask = ((1 << newCity) | currMask);
                        ans = Math.min(ans, cost[currCity][newCity] + dp[newMask][newCity]);
                    }
                }
                dp[currMask][currCity]=ans;
            }
        }
        return dp[1][0];

    }

}
