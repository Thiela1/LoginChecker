package com.example;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginChecker {

    public boolean validateUserName(String username) {
        return username.matches("^[a-zA-Z0-9]{6,8}$");
    }

    public boolean validatePwd(String password) {
        return password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=?<>]).{8,16}$");
    }

    public String[] readLoginAndPassword() {
        Scanner scanner = new Scanner(System.in);
        String[] loginAndPassword = new String[2];

        System.out.print("Enter username: ");
        loginAndPassword[0] = scanner.nextLine();

        System.out.print("Enter password: ");
        loginAndPassword[1] = scanner.nextLine();

        return loginAndPassword;
    }

    public boolean checkLogin(String login, String password) {
        try {
            File file = new File("confidentialInfo.txt");
            Scanner scanner = new Scanner(file);

            int numAccounts = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < numAccounts; i++) {
                String storedLogin = scanner.nextLine().trim();
                String storedPassword = scanner.nextLine().trim();

                if (login.equals(storedLogin) && password.equals(storedPassword)) {
                    return true;
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number of accounts in the file.");
        }

        return false;
    }

    public void resetPassword(String username) {
        try {
            File file = new File("confidentialInfo.txt");
            Scanner scanner = new Scanner(file);
            
            StringBuilder fileContent = new StringBuilder();
            int numAccounts = Integer.parseInt(scanner.nextLine());
            fileContent.append(numAccounts).append("\n");
            boolean userFound = false;

            for (int i = 0; i < numAccounts; i++) {
                String storedLogin = scanner.nextLine();
                String storedPassword = scanner.nextLine();

                if (username.equals(storedLogin)) {
                    userFound = true;
                    System.out.print("Enter new password: ");
                    Scanner inputScanner = new Scanner(System.in);
                    String newPassword = inputScanner.nextLine();
                    fileContent.append(storedLogin).append("\n").append(newPassword).append("\n");
                } else {
                    fileContent.append(storedLogin).append("\n").append(storedPassword).append("\n");
                }
            }
            scanner.close();

            if (userFound) {
                FileWriter writer = new FileWriter("confidentialInfo.txt");
                writer.write(fileContent.toString());
                writer.close();
                System.out.println("Password has been reset successfully.");
            } else {
                System.out.println("Username not found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            System.out.println("Error: Unable to write to file.");
        }
    }

    public static void main(String[] args) {
        LoginChecker loginChecker = new LoginChecker();
        Scanner scanner = new Scanner(System.in);

        boolean validFormat = false;
        boolean loggedIn = false;

        while (!validFormat || !loggedIn) {
            System.out.println("Choose an option:\n1. Login\n2. Reset Password");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                String[] loginAndPassword = loginChecker.readLoginAndPassword();
                String username = loginAndPassword[0];
                String password = loginAndPassword[1];

                if (loginChecker.validateUserName(username) && loginChecker.validatePwd(password)) {
                    validFormat = true;

                    if (loginChecker.checkLogin(username, password)) {
                        loggedIn = true;
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Incorrect username or password. Please try again.");
                    }
                } else {
                    System.out.println("Invalid username or password format. Please try again.");
                }
            } else if (choice == 2) {
                System.out.print("Enter username to reset password: ");
                String username = scanner.nextLine();
                loginChecker.resetPassword(username);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
