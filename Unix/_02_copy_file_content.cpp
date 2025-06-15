//// 2. a. Copy of a file using system calls.
//  b. Output the contents of its Environment list

#include<cstring>
#include<fstream>
#include<fcntl.h>
#include<iostream>
#include<stdio.h>
#include<sys/stat.h>
#include<sys/types.h>
#include<stdlib.h>
#include<unistd.h>
using namespace std;


int main(){
    
    const char* srcf="src.txt";
    const char* destf="dest.txt";
    
    int sop=open(srcf,O_RDONLY);
    if(sop==-1){
        cout<<"Cant open src file"<<endl;
        exit(0);
    }
    
    //write only, if not exists create, set read and write permission for user(usr)
    int dop=open(destf,O_WRONLY | O_CREAT, S_IRUSR|S_IWUSR);
    if(dop==-1){
        cout<<"Cant open dest file"<<endl;
        exit(0);
    }
    
    char buffer[1026];
    ssize_t nRead;
    while(
        ( nRead= read(sop,buffer,sizeof(buffer)) )
        >0
        ){
          ssize_t  nWriten=write(dop,buffer,nRead);
          if(nWriten!=nRead){
              close(sop);
              close(dop);
              cout<<"Error while copying"<<endl;
              exit(0);
          }
        }
    
    close(sop);
    close(dop);
    
    
    
    return 0;
}
