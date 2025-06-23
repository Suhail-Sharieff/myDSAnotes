#include <iostream>    // Required for input/output operations (e.g., std::cout, std::cerr)
#include <sys/socket.h> // Required for socket API functions (socket, bind, listen, accept, recv, close)
#include <netinet/in.h> // Required for Internet address family (AF_INET) and structures (sockaddr_in)
#include <unistd.h>     // Required for POSIX operating system API (close function)

int main() {
    // Step 1: Create a socket
    // This line creates a new socket.
    // AF_INET: Specifies the address family as IPv4.
    // SOCK_STREAM: Specifies the socket type as a stream socket (TCP), which provides reliable, ordered, and error-checked delivery.
    // 0: Specifies the protocol. 0 typically means to choose the default protocol for the given family and type (TCP for SOCK_STREAM).
    int serverSocket = socket(AF_INET, SOCK_STREAM, 0);

    // Check for errors
    // This checks if the socket creation was successful. If 'socket' returns -1, an error occurred.
    if (serverSocket == -1) {
        std::cerr << "Error creating socket." << std::endl; // Print an error message to the standard error stream.
        return -1; // Exit the program with an error code.
    }

    // Step 2: Bind the socket to an IP address and port
    // This declares a structure to hold the server's address information.
    sockaddr_in serverAddress;
    serverAddress.sin_family = AF_INET;       // Set the address family to IPv4.
    serverAddress.sin_addr.s_addr = INADDR_ANY; // Set the IP address. INADDR_ANY means the server will listen on all available network interfaces.
    serverAddress.sin_port = htons(8080);     // Set the port number to 8080. htons() converts the port number from host byte order to network byte order.

    // Bind the socket
    // This associates the created socket with the specified IP address and port.
    // (struct sockaddr*)&serverAddress: Casts the sockaddr_in structure to a generic sockaddr pointer, as required by bind.
    // sizeof(serverAddress): Specifies the size of the address structure.
    // If bind returns -1, an error occurred.
    if (bind(serverSocket, (struct sockaddr*)&serverAddress, sizeof(serverAddress)) == -1) {
        std::cerr << "Error binding socket." << std::endl; // Print an error message.
        close(serverSocket); // Close the socket before exiting to release resources.
        return -1; // Exit the program with an error code.
    }

    // Step 3: Listen for incoming connections
    // This puts the server socket into a listening state, waiting for client connections.
    // 5: Specifies the maximum number of pending connections in the queue. If more clients try to connect than this backlog, their connections might be refused.
    // If listen returns -1, an error occurred.
    if (listen(serverSocket, 5) == -1) {
        std::cerr << "Error listening for connections." << std::endl; // Print an error message.
        close(serverSocket); // Close the socket.
        return -1; // Exit the program.
    }
    std::cout << "Server listening on port 8080..." << std::endl; // Inform the user that the server is listening.

    // Step 4: Accept a connection
    // This declares a structure to hold the client's address information when a connection is accepted.
    sockaddr_in clientAddress;
    // This declares a variable to store the size of the client address structure.
    socklen_t clientAddrSize = sizeof(clientAddress);
    // This function blocks until a client connects. When a connection is established, it returns a new socket descriptor (clientSocket)
    // specifically for communication with that client. The original serverSocket continues to listen for new connections.
    // (struct sockaddr*)&clientAddress: Fills this structure with the client's address information.
    // &clientAddrSize: Points to the size of the clientAddress structure.
    int clientSocket = accept(serverSocket, (struct sockaddr*)&clientAddress, &clientAddrSize);

    // Check for errors
    // If accept returns -1, an error occurred during connection acceptance.
    if (clientSocket == -1) {
        std::cerr << "Error accepting connection." << std::endl; // Print an error message.
        close(serverSocket); // Close the listening socket.
        return -1; // Exit the program.
    }
    std::cout << "Connection accepted. Waiting for data..." << std::endl; // Inform the user that a connection has been accepted.

    // Step 5: Receive data from the client
    // This declares a character array (buffer) to store the data received from the client.
    char buffer[1024];
    // This receives data from the client through the 'clientSocket'.
    // buffer: The buffer to store the received data.
    // sizeof(buffer): The maximum number of bytes to receive.
    // 0: Flags (usually 0 for basic receiving).
    // The function returns the number of bytes read (or -1 on error).
    ssize_t bytesRead = recv(clientSocket, buffer, sizeof(buffer), 0);

    // Check for errors
    // If recv returns -1, an error occurred during data reception.
    if (bytesRead == -1) {
        std::cerr << "Error receiving data." << std::endl; // Print an error message.
        close(serverSocket); // Close the listening socket.
        close(clientSocket); // Close the client-specific socket.
        return -1; // Exit the program.
    }
    // Null-terminate the received data to ensure it can be treated as a C-style string.
    buffer[bytesRead] = '\0';

    // Step 6: Print the received data
    std::cout << "Received data from client: " << buffer << std::endl; // Print the data received from the client.

    // Step 7: Close the sockets
    // This closes the listening socket, preventing new connections.
    close(serverSocket);
    // This closes the client-specific socket, ending communication with the connected client.
    close(clientSocket);
    return 0; // Exit the program successfully.
}
