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
    // Step 1: Create a key for the message queue (use the same key as in the
    // sender)
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
    // Receiver: Read data from the message queue
    // Step 3: Receive a message from the queue with message type 1
    if (msgrcv(msgid, &message, sizeof(message.mtext), 1, 0) == -1) {
        perror("msgrcv");
        exit(1);
    }
    cout << "Data received gmessage queue: " << message.mtext << endl;
    return 0;
}
