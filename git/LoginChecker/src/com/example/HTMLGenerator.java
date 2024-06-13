package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HTMLGenerator {

    public static void main(String[] args) {
        LoginChecker loginChecker = new LoginChecker();
        FileWriter htmlFile = null;

        try {
            htmlFile = new FileWriter("login.html");

            htmlFile.write("<!DOCTYPE html>\n");
            htmlFile.write("<html>\n");
            htmlFile.write("<head>\n");
            htmlFile.write("<title>Login Page</title>\n");
            htmlFile.write("</head>\n");
            htmlFile.write("<body>\n");
            htmlFile.write("<h2>Login Page</h2>\n");
            htmlFile.write("<form>\n");
            htmlFile.write("<label for=\"username\">Username:</label><br>\n");
            htmlFile.write("<input type=\"text\" id=\"username\" name=\"username\"><br>\n");
            htmlFile.write("<label for=\"password\">Password:</label><br>\n");
            htmlFile.write("<input type=\"password\" id=\"password\" name=\"password\"><br><br>\n");
            htmlFile.write("<input type=\"submit\" value=\"Login\">\n");
            htmlFile.write("</form>\n");
            htmlFile.write("</body>\n");
            htmlFile.write("</html>");

            System.out.println("HTML document generated successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while generating HTML document.");
            e.printStackTrace();
        } finally {
            if (htmlFile != null) {
                try {
                    htmlFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

