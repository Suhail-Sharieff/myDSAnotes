# Process Scheduling Algorithms
## `FCFS`: First come first serve
- Jus like brute force, which ever process comes first is scheduled
- Very inefficient, more chances of starvation
- We can just use Queue of processes and execute one by one
## `SJFS`: Shortest job first (Non Preamptive)
- Jus greedy algorithm uses `sort tasks based on arrival time+ pq based on burst time `, make sure u hadnle first time thats all
```java
class Solution {
    public int[] getOrder(int[][] task) {

        //task[i]={id,arrival_time,burst_time}

        PriorityQueue<Process>pq=new PriorityQueue<>(
            (x,y)->{
                if(x.bt!=y.bt) return x.bt-y.bt;
                return x.id-y.id;
            }
        );
        
        Arrays.sort(task,(x,y)->x[1]-y[1]);//sort based on arrival time

        int ans[]=new int[task.length];
        int i=0;
        int k=0;//ansIdx
        int time=0;

        while(k<task.length){
            while(i<task.length && task[i][1]<=time) pq.offer(new Process(task[i][0],task[i][1],task[i++][2]));
            if(pq.isEmpty()) time=task[i][1];//----IMP, handle for first time
            else{
                Process top=pq.poll();
                ans[k++]=top.id;
                time+=top.bt;
            }
        }
        return ans;

    }
    static class Process{
        int id;
        int at;//arrival time
        int bt;//burst time
        public Process(int id,int at,int bt){
            this.id=id;
            this.at=at;
            this.bt=bt;
        }
    }
}
```
## Priority Scheduling `premptive`
- Similar to above, just change the PQ condition wrt priority provided
## `RR`: Round Robin Algorithm
