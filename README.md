[![Build Status](https://travis-ci.com/Madave94/sple-exercise.svg?token=sBsi8fZsf8GM9ptYZkau&branch=master)](https://travis-ci.com/Madave94/sple-exercise)
# sple-exercise
Software Product Line Engineering Samples to work on

by David Tschirschwitz, Digital Engineering, 119383


Start chat server with

    java server.Server <port>
    
and start a chat client with

    java client.Client <host> <port> <password>

Start default server with

    java server.Server

and start a default chat client with

    java client.Client

# sources

Threading_1: http://tutorials.jenkov.com/java-concurrency/creating-and-starting-threads.html

Threading_2: http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_14_003.htm

IO_Stream: http://openbook.rheinwerk-verlag.de/javainsel/18_004.html#u18.4

Server-Client_1: http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_21_007.htm#mj1ba27dc5fdf53f527163767f188e1d2e

Server-Client_2: https://www.youtube.com/watch?v=LAwp_F6PSAw&list=PLE4E8B5A20B0A5091&index=4

Logging: http://openbook.rheinwerk-verlag.de/java7/1507_20_001.html

# Realization of the features for version 1.0

### Color

When typing the following

	<color> some text </color>

the sent text message will get the previously specified color. The following colors are possible:
black, red, green, yellow, blue, purple, cyan, white.
The colors can be typed in lower or upper case. Only one color can be applied per message.

### Authentification

The server and client perform a handshake, where the server asks the client for the password. The client sends the password and the server checks if the password is correct. The server sends a authentification message which contains a boolean value, depending on the correctness of the password the boolean value will be true or false. In case it is false, the server will kick the client, and the client will close all threads and end the process. If the password is true then the client will be connected to the connections set.

### Encryption

Encryption is only applied for the text messages. During initialization of a text message object the encryption will be applied, when calling the method getContent the text message will be decrypted. Currently Swap2Letters is used for encryption.

### Simple log file

Only the servers logs the actions and there only the main events are logged. Adding new events to the logger works by calling a getLogger method in the Connection or ServerAuthentification class and then use the appropriate log method.
The log is only writen to the log.xml file. No output is displayed on the servers consol.
