import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {

    public static final String RED="\u001B[31m";
    public static final String RESET="\u001B[0m";
    public static final String BLUE="\u001B[34m";
    public static final String GREEN="\u001B[32m";
    public static final String CYAN="\u001B[36m";
    private static final String FILE_NAME = "login_register.txt";

    private List<User> users = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);


    public void loadUsers() 
    {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
                String[] users_detail = line.split(",");
                User newUser = new User(users_detail[0], users_detail[1]);
                users.add(newUser);
            }
        } catch (IOException e) 
        {
            System.out.println(RED + "Error reading from file: " + e.getMessage() + RESET);
        }
    }

    public void saveUsers() 
    {
        try (BufferedWriter us = new BufferedWriter(new FileWriter(FILE_NAME))) 
        {
            for (User user : users) {
                us.write(user.getUsername() + "," + user.getPassword());
                us.newLine();
            }
        } catch (IOException e) 
        {
        System.out.println(RED + "Error writing to file: " + e.getMessage() + RESET);
        }
    }

    public boolean registerUser() {

        boolean flag = false;
        System.out.println("Enter a username:");
        String username = sc.nextLine();

        System.out.println("Enter a password:");
        String password = sc.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                flag = true;
                
            }
        }

        if(!flag)
        {
            User newUser = new User(username, password);
            users.add(newUser);
            System.out.println(GREEN+"Registration successful!"+RESET);
            saveUsers();
            return true;
        }
        
        System.out.println(RED+"Username: "+username+" is already created"+RESET);
        return false;
        



        
    }

    public boolean loginUser() {
        System.out.println("Enter your username:");
        String username = sc.nextLine();

        System.out.println("Enter your password:");
        String password = sc.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println(GREEN+"Login successful!"+RESET);
                return true;
            }
        }
        System.out.println(RED+"Either Invalid username / password  or Not registered"+RESET);
        return false;
    }
}
