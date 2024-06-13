LoginChecker

Description

The LoginChecker class is a Java program designed to validate and check login credentials. It reads a username and password from the user, validates their format, and compares them to the correct username and password combination stored in a file.

Features

Validates username format (6-8 alphanumeric characters).
Validates password format (8-16 characters, including alphanumeric and certain symbols).
Reads username and password from the user.
Compares the input credentials to those stored in a file (confidentialInfo.txt).
Provides feedback on login success or failure.
Requirements

Java Development Kit (JDK) installed.
confidentialInfo.txt file in the same directory as the program.
File Format for confidentialInfo.txt

The confidentialInfo.txt file should be formatted as follows:

The first line contains the number of user accounts.
Each subsequent pair of lines contains a username and password.
