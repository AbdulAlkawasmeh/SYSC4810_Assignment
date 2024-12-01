import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3a {

    static class User {
        String username;
        String role;
        String password;

        User(String username, String role, String password) {
            this.username = username;
            this.role = role;
            this.password = password;
        }
    }

    public static final List<Problem3a.User> users = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);


    public static void enrollUser() {

        while (true) {
            System.out.print("Enter a username: ");
            String username = scanner.nextLine();

            try {
                if (Problem2c.retrieveUserInfo(username) != null) {
                    System.out.println("Username already exists. Please choose a different one.");
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            String password;
            while (true) {
                System.out.print("Enter a password: ");
                password = scanner.nextLine();
                if (Problem3b.isValidPassword(password, username)) {
                    break;
                } else {
                    System.out.println("Please try again.");
                }
            }

            System.out.println("Available roles: Client (C), Premium Client (PC) , Financial Advisor (FA), Financial Planner (FP), Teller (T)");
            System.out.print("Select a role by entering the exact corresponding short form for each role: ");
            String role = scanner.nextLine();

            try {
                checkRole(role);
                Problem2c.addUser(username, password, role);
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

        } else {
            System.out.println("Invalid role entered. Please try again.");
            return false;
        }

    }
}
