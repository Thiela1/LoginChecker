package com.example;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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

    public static void main(String[] args) {
        LoginChecker loginChecker = new LoginChecker();
        Scanner scanner = new Scanner(System.in);

        boolean validFormat = false;
        boolean loggedIn = false;

        while (!validFormat || !loggedIn) {
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
        }

        scanner.close();
    }
}

