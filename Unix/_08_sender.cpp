#include <cstdlib>
#include <cstring>
#include <iostream>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/types.h>
#include <unistd.h>
using namespace std;
// Define a structure for the message data
struct Message {
    long mtype;
    char mtext[100];
};
int main() {
    key_t key;
    int msgid;
    Message message;
    // Step 1: Create a key for the message queue
    key = ftok("/tmp", '1');
    if (key == -1) {
        perror("ftok");
        exit(1);
    }
    // Step 2: Create or open the message queue
    msgid = msgget(key, 0666 | IPC_CREAT);
    if (msgid == -1) {
        perror("msgget");
        exit(1);
    }
    // Sender: Send data to the message queue
    message.mtype =
        1; // Message type (you can use different types for different purposes)
    strcpy(message.mtext, "Hello, this is a message from the sender!");
    // Step 3: Send the message to the queue
    if (msgsnd(msgid, &message, sizeof(message.mtext), 0) == -1) {
        perror("msgsnd");
        exit(1);
    }
    cout << "Data sent to message queue." << endl;
    return 0;
}