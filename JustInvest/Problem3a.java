import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3a {

    private static final Scanner scanner = new Scanner(System.in);


    public static void enrollUser() { //enrolls new users
        String password;
        while (true) {
            String username = scanner.nextLine();
            try {
                if (Problem2c.retrieveUserInfo(username) != null) { //if the username exists, prevent user from registering
                    System.out.println("Username already exists. Please choose a different one.");
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            while (true) { //if username doesn't exist, ask for password
                System.out.print("Enter a password: ");
                System.out.println("\nPress 'E' to exit");
                password = scanner.nextLine();
                if (Problem3b.isValidPassword(password, username)) { //check if the password is valid based on username
                    break;
                } else if (password.equals("E")) {
                    Main.run();
                } else {
                    System.out.println("Please try again.");
                }
            }
            String role;
            while (true) { //choice of roles to choose from
                System.out.println("Available roles: Client (C), Premium Client (PC), Financial Advisor (FA), Financial Planner (FP), Teller (T)");
                System.out.print("Select a role by entering the corresponding short form for each role: ");
                System.out.println("\nPress 'E' to exit");
                role = scanner.nextLine();
                if (checkRole(role)) {
                    break;
                } else if (role.equals("E")) {
                    Main.run();
                } else {
                    System.out.println("Invalid role");
                }
            }
            try {
                Problem2c.addUser(username, password, role); //add the user to the system
                System.out.println("Enrollment successful!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public static boolean checkRole(String role) {
        if (role.equals("C") || role.equals("PC") || role.equals("FA") || role.equals("FP") || role.equals("T")) {
            return true;
        }
        else {
            return false;
        }
    }
}
