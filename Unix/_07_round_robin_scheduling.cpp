#include<algorithm>
#include<cstring>
#include<cstdlib>
#include<fstream>
#include<fcntl.h>
#include<iostream>
#include<pthread.h>
#include<semaphore.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<sys/stat.h>
#include<sys/ipc.h>
#include<sys/shm.h>
#include<unistd.h>
#include<vector>

using namespace std;

int main(){

	int n=3;
	int qt=2;
	int at[]={0,1,2},bt[]={2,3,4},rt[]={2,3,4};//arrival,burst,remaining

	int nCompleted=0;
	int toavg_turnaround_timel_time=0;
	int avg_turnaround_time=0;
	int avg_waiting_time=0;
	while(nCompleted<n){
		for(int i=0;i<n;i++){
			if(at[i]<=toavg_turnaround_timel_time){
				if(rt[i]>0){
					//its not yet completed
					if(rt[i]>=qt){
						toavg_turnaround_timel_time+=qt;
						rt[i]-=qt;
					}else{
						toavg_turnaround_timel_time+=rt[i];
						rt[i]=0;
					}
				}
				if(rt[i]==0){
					cout<<"Process "<<i
					<<" Burst time: "<<bt[i]
					<<" Turnaround time: "<<(toavg_turnaround_timel_time-at[i])
					<<" Waiting time: "<<(toavg_turnaround_timel_time-bt[i]-at[i])<<endl;
					nCompleted++;
					avg_waiting_time+=(toavg_turnaround_timel_time-at[i]-bt[i]);
					avg_turnaround_time+=(toavg_turnaround_timel_time-at[i]);
					rt[i]=-1;
				}
			}
		}
	}
	cout<<"Avg waiting "<<(avg_waiting_time/n)<<endl;
	cout<<"Avg turnaround "<<(avg_turnaround_time/n)<<endl;
	return 0;
}
