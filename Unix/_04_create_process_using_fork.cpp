#include<cstring>
#include<fcntl.h>
#include<fstream>
#include<iostream>
#include<sys/stat.h>
#include<sys/types.h>
#include<stdlib.h>
#include<unistd.h>
using namespace std;
int main(){
	pid_t child_thread=fork();
	if(child_thread==-1){
		perror("Unable to fork a child!");
		exit(0);
	}
	for(int i=0;i<5;i++){
		if(child_thread==0) cout<<"Parent "<<i<<endl;
		else cout<<"Child "<<i<<endl;
		sleep(1);
	}
	return 0;
}