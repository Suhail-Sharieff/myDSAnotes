#include <iostream>    // Required for input/output operations (e.g., std::cout, std::cerr)
#include <sys/socket.h> // Required for socket API functions (socket, connect, send, close)
#include <netinet/in.h> // Required for Internet address family (AF_INET) and structures (sockaddr_in)
#include <arpa/inet.h>  // Required for inet_pton function (for converting IP address string to binary)
#include <unistd.h>     // Required for POSIX operating system API (close function)
#include <string.h>     // Required for strlen function

int main() {
    // Step 1: Create a socket
    // This line creates a new socket for the client.
    // AF_INET: Specifies the address family as IPv4.
    // SOCK_STREAM: Specifies the socket type as a stream socket (TCP).
    // 0: Specifies the default protocol (TCP).
    int clientSocket = socket(AF_INET, SOCK_STREAM, 0);

    // Check for errors
    // This checks if the socket creation was successful.
    if (clientSocket == -1) {
        std::cerr << "Error creating socket." << std::endl; // Print an error message.
        return -1; // Exit the program with an error code.
    }

    // Step 2: Set up the server address and port
    // This declares a structure to hold the server's address information that the client wants to connect to.
    sockaddr_in serverAddress;
    serverAddress.sin_family = AF_INET;   // Set the address family to IPv4.
    serverAddress.sin_port = htons(8080); // Set the port number to 8080 (the same port the server is listening on).

    // Convert IP address from text to binary form
    // This converts the IP address string "127.0.0.1" (localhost) into its binary representation
    // and stores it in serverAddress.sin_addr.
    // AF_INET: Specifies the address family.
    // "127.0.0.1": The IP address string.
    // &serverAddress.sin_addr: The destination for the binary IP address.
    // If inet_pton returns <= 0, an error occurred (invalid address or not supported).
    if (inet_pton(AF_INET, "127.0.0.1", &serverAddress.sin_addr) <= 0) {
        std::cerr << "Invalid address/Address not supported." << std::endl; // Print an error message.
        close(clientSocket); // Close the socket.
        return -1; // Exit the program.
    }

    // Step 3: Connect to the server
    // This attempts to establish a connection with the server at the specified address and port.
    // (struct sockaddr*)&serverAddress: Casts the sockaddr_in structure to a generic sockaddr pointer.
    // sizeof(serverAddress): Specifies the size of the address structure.
    // If connect returns -1, the connection attempt failed.
    if (connect(clientSocket, (struct sockaddr*)&serverAddress, sizeof(serverAddress)) == -1) {
        std::cerr << "Connection failed." << std::endl; // Print an error message.
        close(clientSocket); // Close the socket.
        return -1; // Exit the program.
    }
    std::cout << "Connected to the server. Sending data..." << std::endl; // Inform the user that the connection was successful.

    // Step 4: Send data to the server
    // This declares a constant character pointer to the message that will be sent.
    const char* message = "Hello from the client!";
    // This sends data to the connected server through 'clientSocket'.
    // message: The data to be sent.
    // strlen(message): The number of bytes to send (length of the string).
    // 0: Flags (usually 0 for basic sending).
    // If send returns -1, an error occurred during data transmission.
    if (send(clientSocket, message, strlen(message), 0) == -1) {
        std::cerr << "Error sending data." << std::endl; // Print an error message.
        close(clientSocket); // Close the socket.
        return -1; // Exit the program.
    }

    // Step 5: Close the socket
    // This closes the client socket, ending the communication.
    close(clientSocket);
    return 0; // Exit the program successfully.
}
