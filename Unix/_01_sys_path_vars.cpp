#include<cstring>
#include<fcntl.h>
#include<fstream>
#include<iostream>
#include<pthread.h>
#include<sys/stat.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<sys/ipc.h>
#include<sys/shm.h>
#include<semaphore.h>
#include<stdlib.h>
#include<unistd.h>
#include<vector>


int main()
{
    // Check and print the number of clock ticks
    printf("No. of clock ticks: %ld\n", sysconf(_SC_CLK_TCK));
    // Check and print the max number of child processes
    printf("Max. no. of child processes: %ld\n", sysconf(_SC_CHILD_MAX));
    // Check and print the max path length
    printf("Max. path length: %ld\n", pathconf("/", _PC_PATH_MAX));
    // Check and print the max number of characters in a file name
    printf("Max. no. of characters in a file name: %ld\n", pathconf("/", _PC_NAME_MAX));
    // Check and print the max number of open files per process
    printf("Max. no. of open files/process: %ld\n", sysconf(_SC_OPEN_MAX));
    return 0;
}