package _12_Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/*
Shortest Job first---------------All taks available at t=0
Difficulty: MediumAccuracy: 68.79%Submissions: 27K+Points: 4Average Time: 30m
Geek is a software engineer. He is assigned with the task of calculating average waiting time of all the processes by following shortest job first policy.

The shortest job first (SJF) or shortest job next, is a scheduling policy that selects the waiting process with the smallest execution time to execute next.

Given an array of integers bt of size n. Array bt denotes the burst time of each process. Calculate the average waiting time of all the processes and return the nearest integer which is smaller or equal to the output.

Note: Consider all process are available at time 0.

Example 1:
Input:jobs = [3, 1, 4, 2, 5]
                
Output: 4
Explanation: 
                
The first job that will be executed is of duration 1 and the waiting time for it will be 0.
After the first job, the next shortest job with a duration of 2 will be executed with a waiting time of 1.
Following the completion of the first two jobs, the next shortest job with a duration of 3 will be executed with a waiting time of 3 (1 + 2).
Then, the job with a duration of 4 will be executed with a waiting time of 6 (1 + 2 + 3).
Finally, the job with the longest duration of 5 will be executed with a waiting time of 10 (1 + 2 + 3 + 4).
Hence, the average waiting time is calculated as (0 + 1 + 3 + 6 + 10) / 5 = 20 / 5 = 4.

Example 2:
Input:jobs = [4, 3, 7, 1, 2]
                
Output: 4
Explanation: The first job that will be executed is of duration 1, and the waiting time for it will be 0.
                
After the first job, the next shortest job with a duration of 2 will be executed with a waiting time of 1.
Following the completion of the first two jobs, the next shortest job with a duration of 3 will be executed with a waiting time of 3 (1 + 2).
Then, the job with a duration of 4 will be executed with a waiting time of 6 (1 + 2 + 3).
Finally, the job with the longest duration of 7 will be executed with a waiting time of 10 (1 + 2 + 3 + 4).
Hence, the average waiting time is calculated as (0 + 1 + 3 + 6 + 10) / 5 = 20 / 5 = 4.
Your Task:
You don't need to read input or print anything. Your task is to complete the function solve() which takes bt[] as input parameter and returns the average waiting time of all the processes.

Expected Time Complexity: O(nlog(n))
Expected Auxiliary Space: O(1)

Constraints:
1 <= n <= 105
1 <= arr[i] <= 105
 */
public class _02_shortest_job_first {

    static int func(int bt[]) {// O(nLogn)
        Arrays.sort(bt);
        int time_elapsed = 0;
        int waiting_time_sum = 0;
        for (int i = 0; i < bt.length; i++) {
            waiting_time_sum += time_elapsed;
            time_elapsed += bt[i];
        }
        return waiting_time_sum / bt.length;// ie average
    }

    // --------------------FOLLOW UP--hard
    // all tasks NOT avalable at t=0
    /*
     * 1834. Single-Threaded CPU
     * Medium
     * Topics
     * Companies
     * Hint
     * You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D
     * integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means
     * that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and
     * will take processingTimei to finish processing.
     * 
     * You have a single-threaded CPU that can process at most one task at a time
     * and will act in the following way:
     * 
     * If the CPU is idle and there are no available tasks to process, the CPU
     * remains idle.
     * If the CPU is idle and there are available tasks, the CPU will choose the one
     * with the shortest processing time. If multiple tasks have the same shortest
     * processing time, it will choose the task with the smallest index.
     * Once a task is started, the CPU will process the entire task without
     * stopping.
     * The CPU can finish a task then start a new one instantly.
     * Return the order in which the CPU will process the tasks.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
     * Output: [0,2,3,1]
     * Explanation: The events go as follows:
     * - At time = 1, task 0 is available to process. Available tasks = {0}.
     * - Also at time = 1, the idle CPU starts processing task 0. Available tasks =
     * {}.
     * - At time = 2, task 1 is available to process. Available tasks = {1}.
     * - At time = 3, task 2 is available to process. Available tasks = {1, 2}.
     * - Also at time = 3, the CPU finishes task 0 and starts processing task 2 as
     * it is the shortest. Available tasks = {1}.
     * - At time = 4, task 3 is available to process. Available tasks = {1, 3}.
     * - At time = 5, the CPU finishes task 2 and starts processing task 3 as it is
     * the shortest. Available tasks = {1}.
     * - At time = 6, the CPU finishes task 3 and starts processing task 1.
     * Available tasks = {}.
     * - At time = 10, the CPU finishes task 1 and becomes idle.
     * Example 2:
     * 
     * Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
     * Output: [4,3,2,0,1]
     * Explanation: The events go as follows:
     * - At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
     * - Also at time = 7, the idle CPU starts processing task 4. Available tasks =
     * {0,1,2,3}.
     * - At time = 9, the CPU finishes task 4 and starts processing task 3.
     * Available tasks = {0,1,2}.
     * - At time = 13, the CPU finishes task 3 and starts processing task 2.
     * Available tasks = {0,1}.
     * - At time = 18, the CPU finishes task 2 and starts processing task 0.
     * Available tasks = {1}.
     * - At time = 28, the CPU finishes task 0 and starts processing task 1.
     * Available tasks = {}.
     * - At time = 40, the CPU finishes task 1 and becomes idle.
     * 
     * 
     * Constraints:
     * 
     * tasks.length == n
     * 1 <= n <= 105
     * 1 <= enqueueTimei, processingTimei <= 109
     */
    /*
SOLUTION:----------------
Approach
First of all we should see to it that if enqueue time of a task less than or equal to currentTime, then, all these tasks will be added into our available list.
So we have to choose from the available list, some tasks that have less amount of required completion time.
Also if many tasks have same completion time we will have to take the task with least index.
Since we will be adding task to our available list at anytime, we use a priority queue so that when the task is added, we need not sort the array once again. Add operation in priority queue only takes logN time.
Required Preprocessing
Since we are to consider 3 factors, i.e enqueueTime, requiredTime and index, we will add index to every tasks[i].
Also we will sort the tasks array based on it's enqueueTime, requiredTime and index respectively. We do this so that we can get all available tasks based on its enqueueTime.
Base Case to Consider
Consider the case [[20, 20], [60, 60]].
Here after the 0th task gets completed, we will not be having any task in our queue.
So we will ensure that, if our queue is empty, we will add next available task from our tasks list.
     */
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        Task [] arr = new Task[n];
        for(int i = 0 ;i<n;i++){
            arr[i] = new Task(i, tasks[i][0],tasks[i][1]);
        }

        Arrays.sort(arr, (a,b)->{
            return Integer.compare(a.enqueueTime,b.enqueueTime);
        });

        PriorityQueue<Task> pq = new PriorityQueue<>((a,b)->{
            if(a.processingTime == b.processingTime){
                return Integer.compare(a.idx,b.idx);
            }
            return Integer.compare(a.processingTime,b.processingTime);
        });
        
        int[] ans = new int[n];
        int ansIdx = 0;
        int taskIdx = 0;
        int elapsed_time= 0;

        while(ansIdx < n){
            //push all tasks less than elapsedtime in pq
            while(taskIdx < n && arr[taskIdx].enqueueTime <= elapsed_time){//not just <
                pq.offer(arr[taskIdx++]);
            }
            if(pq.isEmpty()){
                elapsed_time = arr[taskIdx].enqueueTime;
            }else{
                Task top=pq.poll();
                elapsed_time += top.processingTime;
                ans[ansIdx++] = top.idx;
            }
        }
        return ans;       
    }
    
    static class Task {
        int idx;
        int enqueueTime;
        int processingTime;

        Task(int idx , int en , int pro){
            this.idx = idx;
            this.enqueueTime = en;
            this.processingTime = pro;
        }
    }



    //----------------FOLLOW UP:
/*
Job Sequencing Problem
Difficulty: MediumAccuracy: 34.51%Submissions: 282K+Points: 4
You are given three arrays: id, deadline, and profit, where each job is associated with an ID, a deadline, and a profit. Each job takes 1 unit of time to complete, and only one job can be scheduled at a time. You will earn the profit associated with a job only if it is completed by its deadline.

Your task is to find:

The maximum number of jobs that can be completed within their deadlines.
The total maximum profit earned by completing those jobs.
Examples :

Input: id = [1, 2, 3, 4], deadline = [4, 1, 1, 1], profit = [20, 1, 40, 30]
Output: [2, 60]
Explanation: Job1 and Job3 can be done with maximum profit of 60 (20+40).
Input: id = [1, 2, 3, 4, 5], deadline = [2, 1, 2, 1, 1], profit = [100, 19, 27, 25, 15]
Output: [2, 127]
Explanation: Job1 and Job3 can be done with maximum profit of 127 (100+27).
Input: id = [1, 2, 3, 4], deadline = [3, 1, 2, 2], profit = [50, 10, 20, 30]
Output: [3, 100]
Explanation: Job1, Job3 and Job4 can be completed with a maximum profit of 100 (50 + 20 + 30).
Constraints:
1 <=  id.size() == deadline.size() == profit.size() <= 105
1 <= id[i], deadline[i] <= id.size()
1 <= profit <= 500
 */


    //solution:
    /*
One standard greedy solution is:

Step 1: Sort jobs by profit in descending order.
Step 2: For each job, try to schedule it in the latest available time slot before its deadline.
Step 3: Use an auxiliary array (or a disjoint set union) to mark the time slots that are occupied.
     */
     static ArrayList<Integer> JobSequencing(int[] id, int[] deadline, int[] profit) {
        int len=id.length;
        Job[]arr=new Job[len];
        for(int i=0;i<len;i++) arr[i]=new Job(id[i],deadline[i],profit[i]);

        // Arrays.sort(arr,(x,y)-> {if(x.deadline==y.deadline) return Integer.compare(y.profit,x.profit);
        Arrays.sort(arr,(x,y)->y.profit-x.profit);
        int max_deadline=0;
        for(Job e:arr) max_deadline=Math.max(max_deadline,e.deadline);
        
        boolean assigned[]=new boolean[max_deadline+1];
        int ans=0,cnt=0;
        
        for(Job e:arr){
            for(int j=e.deadline;j>0;j--){
                if(!assigned[j]){
                    assigned[j]=true;
                    ans+=e.profit;
                    cnt++;
                    break;
                }
            }
        }
        return new ArrayList<>(Arrays.asList(cnt,ans));
    }


    static class Job{
        int id,deadline,profit;
        public Job(int i,int d,int p){
            id=i;
            deadline=d;
            profit=p;
        }
        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "(id:"+id+",deadline:"+deadline+",profit:"+profit+")";
        }
    }

}
