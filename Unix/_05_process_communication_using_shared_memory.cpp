//_05_process_communication_using_shared_memory.cpp
#include<cstring>
#include<fcntl.h>
#include<fstream>
#include<iostream>
#include<sys/stat.h>
#include<sys/types.h>
#include<sys/wait.h>//new
#include<sys/ipc.h>//new
#include<sys/shm.h>//new
#include<stdlib.h>
#include<unistd.h>
using namespace std;

int main(){

	int shm_size=1024;
	int shm_key=1024;

	//we will need a token to create shared memory
	key_t token=ftok(".",shm_key);
	if(token==-1){
		perror("Failed to create token");
		exit(0);
	}

	//create a shared memory segment
	int shm_segment=shmget(token,shm_size,IPC_CREAT|0666);
	if(shm_segment==-1){
		perror("Failed to create a shm segment");
		exit(0);
	}

	char* attach_shm_segment=(char*)shmat(shm_segment,NULL,0);
	if(attach_shm_segment==(char*)(-1)){
		perror("Cannot atttach to memory segment");
		exit(0);
	}
	string msg="Hello from shared memory";
	strcpy(attach_shm_segment,msg.c_str());

	//create a child process
	pid_t child_thread=fork();
	if(child_thread==-1){
		perror("Cannot create child thread");
		exit(0);
	}

	if(child_thread==0)cout<<"Child process reads "<<msg<<endl;
	else wait(NULL);

	//detach shm
	if(shmdt(attach_shm_segment)==-1){
		perror("Cannot detach shm");
		exit(0);
	}
	//remove shm or control it
	if(shmctl(token,IPC_RMID,NULL)==-1){
		perror("Cannot control shm");
		exit(0);
	}

	return 0;
}