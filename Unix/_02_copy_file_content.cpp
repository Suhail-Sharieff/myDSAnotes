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

	const char* src="src.txt";
	const char* dest="dest.txt";

	int src_id=open(src,O_RDONLY);
	if(src_id==-1){
		cout<<"Cannot open src"<<endl;
		exit(0);
	}

	int dest_id=open(dest,O_WRONLY|O_CREAT,S_IRUSR|S_IWUSR);
	if(dest_id==-1){
		cout<<"Cannot open dest"<<endl;
		exit(0);
	}

	char buffer[1024];
	ssize_t nRead;

	while((nRead=read(src_id,buffer,sizeof(buffer)))>0){
		ssize_t nWrote=write(dest_id,buffer,nRead);
		if(nRead!=nWrote){
			close(src_id);
			close(dest_id);
			exit(0);
		}
	}
	cout<<"src copied into dest"<<endl;
	close(src_id);
	close(dest_id);


	return 0;
}