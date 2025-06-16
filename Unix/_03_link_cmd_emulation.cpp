#include<cstring>
#include<fcntl.h>
#include<fstream>
#include<iostream>
#include<sys/stat.h>
#include<sys/types.h>
#include<stdlib.h>
#include<unistd.h>

//emulate hard link command in unix, hardlink means creting another file opf the same name the changes in one file is also reflected into the other file as well

int main(int arg,char*argv[]){

	const char* src_file=argv[0];
	const char* dest_file=argv[1];
	int hard_link_created=link(src_file,dest_file);
	if(hard_link_created==-1){
		perror("Cannot create the hardlink!");
		exit(0);
	}

	//g++ _.cpp
	// ./a.out src.txt dest.txt


	return 0;
}