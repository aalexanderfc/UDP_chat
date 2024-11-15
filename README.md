# UDP Chat Application

This repository contains a simple UDP-based chat application with a graphical user interface (GUI). The application allows multiple users to join a multicast chatroom, send messages, and see a list of connected users in real-time.

## Features

- **Multicast Chatroom**: Uses UDP and Multicast for communication, allowing multiple users to connect and chat simultaneously.
- **Graphical User Interface (GUI)**: Built with Java Swing, featuring a chat area, message input field, and a list of active users.
- **Real-time Updates**: Shows messages from other users in real-time and updates the user list as people join or leave the chat.
- **Disconnect Button**: Allows users to safely disconnect from the chat, removing their name from the list of active users.
- **Dynamic User List**: Shows the list of currently connected users and updates automatically when someone joins or leaves.

## Requirements

- Java JDK 8 or later

## How to Run

1. Clone this repository:

   ```bash
   git clone https://github.com/aalexanderfc/UDP_chat.git
   cd UDP_chat
   ```
2. Compile and run the ChatClient Java class:
```bash
javac ChatClient.java
java ChatClient
```

3. Enter your username when prompted, and you'll join the chatroom.

## Usage

**Sending Messages:** Type a message in the input field at the bottom and press Enter to send it to all connected users.
**Disconnecting:** Click the "Koppla ner" button to disconnect from the chat, which removes you from the user list.

## How It Works

The application uses UDP multicast to allow multiple users to send and receive messages within a chatroom. The UserListManager class handles the user list, ensuring that the GUI shows the current list of connected users. Each user’s message is broadcasted to all others in the chatroom, including join and leave notifications.

## Limitations

This application assumes all users are cooperative and well-behaved (no handling for inappropriate behavior).
Username uniqueness is not enforced, so users can join with duplicate usernames.
