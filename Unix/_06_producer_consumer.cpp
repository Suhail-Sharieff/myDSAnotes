#include<algorithm>
#include<cstring>
#include<cstdlib>
#include<fstream>
#include<fcntl.h>
#include<iostream>
#include<pthread.h>
#include<semaphore.h>//dont forget .h here
#include<sys/types.h>
#include<sys/wait.h>
#include<sys/stat.h>
#include<sys/ipc.h>
#include<sys/shm.h>
#include<unistd.h>
#include<vector>

using namespace std;

sem_t mutex;
sem_t f;//full
sem_t e;//empty
vector<int>buffer;

void* producer_function(void* arg){
	while(1){
		sleep(1);
		sem_wait(&mutex);sem_wait(&e);
		buffer.push_back(*((int*)arg));
		cout<<"Produced "<<*((int*)arg)<<endl;
		sem_post(&mutex);sem_post(&f);
	}
	return NULL;
}
void* consumer_function(void* arg){
	while(1){
		sleep(1);
		sem_wait(&mutex);sem_wait(&f);
		cout<<"Consumed "<<buffer.back()<<endl;
		buffer.pop_back();
		sem_post(&mutex);sem_post(&e);
	}
	return NULL;
}

int main(){

	int n=10;

	sem_init(&mutex,0,1);
	sem_init(&e,0,n);
	sem_init(&f,0,0);

	pthread_t p[n],c[n];

	for(int i=0;i<n;i++) pthread_create(&p[i],NULL,producer_function,(void*)new int(i));
	for(int i=0;i<n;i++) pthread_create(&c[i],NULL,consumer_function,NULL);
	for(int i=0;i<n;i++) pthread_join(p[i],NULL);
	for(int i=0;i<n;i++) pthread_join(c[i],NULL);
}
