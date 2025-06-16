#include<cstring>
#include<fcntl.h>
#include<fstream>
#include<iostream>
#include<pthread.h>//new
#include<sys/stat.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<sys/ipc.h>
#include<sys/shm.h>
#include<semaphore.h>//new
#include<stdlib.h>
#include<unistd.h>
#include<vector>//new
using namespace std;
vector<int>buffer;
#define MAX 10
#define N 5
sem_t mutex;
sem_t empty_cnt;
sem_t full_cnt;

void* producer_function(void* item){
	int value=*((int*)item);
	while(true){
		sleep(1);
		sem_wait(&empty_cnt);
	    sem_wait(&mutex);
	    buffer.push_back(value);
	    cout<<"PRODUCED "<<value<<endl;
	    sem_post(&mutex);
	    sem_post(&full_cnt);
	}
}
void* consumer_function(void* item){
	while(true){
		sleep(1);
		sem_wait(&full_cnt);
		sem_wait(&mutex);
		int x=buffer.back();
		buffer.pop_back();
		cout<<"CONSUMED "<<x<<endl;
		sem_post(&mutex);
		sem_post(&empty_cnt);
	}
}
int main(){

	//initialize all mutex
	//trick middle we have 0, end pattern: "1-MAX-0"
	sem_init(&mutex,0,1);
	sem_init(&empty_cnt,0,MAX);
	sem_init(&full_cnt,0,0);

	//create producer and consumer threads
	pthread_t producer_thread[N];
	pthread_t consumer_thread[N];
	for(int i=0;i<N;i++){
		int* item=new int(i);
		pthread_create(&producer_thread[i],NULL,producer_function,(void*)item);
		pthread_create(&consumer_thread[i],NULL,consumer_function,NULL);
	}

	//join to main trhread
	for(int i=0;i<N;i++){
		pthread_join(producer_thread[i],NULL);
		pthread_join(consumer_thread[i],NULL);
	}

	return 0;
}