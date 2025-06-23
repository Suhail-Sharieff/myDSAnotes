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
#include<sys/msg.h>
#include<unistd.h>
#include<vector>

using namespace std;


//parent thread writes to pipe and child reads it

int main(){#include<algorithm>
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
#include<sys/msg.h>
#include<unistd.h>
#include<vector>

using namespace std;


//parent thread writes to pipe and child reads it

int main(){
	int arr[2];
	int created=pipe(arr);
	if(created==-1) perror("pipe creation failed..");
	pid_t child=fork();
	if(child==0){
		//child thread
		//to read
		char buffer[1024];
		ssize_t br=read(arr[0],buffer,sizeof(buffer));
		if(br==-1) perror("cant read");
		buffer[br]='\0';
		cout<<buffer<<endl;
	}else{
		//parent thread
		//to write
		string msg="Hello world";
		int wr=write(arr[1],msg.c_str(),msg.length());
		if(wr==-1) perror("Failed to write");
	}
	return 0;
}
	int arr[2];
	int created=pipe(arr);
	if(created==-1) perror("pipe creation failed..");
	pid_t child=fork();
	if(child==0){
		//child thread
		//to read
		char buffer[1024];
		ssize_t br=read(arr[0],buffer,sizeof(buffer));
		if(br==-1) perror("cant read");
		buffer[br]='\0';
		cout<<buffer<<endl;
	}else{
		//parent thread
		//to write
		string msg="Hello world";
		int wr=write(arr[1],msg.c_str(),msg.length());
		if(wr==-1) perror("Failed to write");
	}
	return 0;
}
