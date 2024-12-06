import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        run();
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("justInvest System");
        System.out.println("____________________________________");
        System.out.println("Operations available on the system:");
        System.out.println("1. View account balance");
        System.out.println("2. View investment portfolio");
        System.out.println("3. Modify investment portfolio");
        System.out.println("4. View financial advisor contact info");
        System.out.println("5. View financial planner contact info");
        System.out.println("6. View money market instruments");
        System.out.println("7. View private consumer instruments");

        while (true) {
            System.out.print("Please enter your username: ");
            String usernameInput = scanner.nextLine();
            if (usernameInput != null) {
                try {
                    List<String> userInfo = Problem2c.retrieveUserInfo(usernameInput);

                    if (userInfo != null) {
                        String passwordInput;
                        boolean loggedIn = false;

                        while (!loggedIn) {
                            System.out.print("Please enter your password: ");
                            System.out.println("\nPress 'E' to exit: ");
                            passwordInput = scanner.nextLine();
                            if (passwordInput.equals("E")) {
                                run();
                                return;
                            }
                            if (Problem4ab.LoginUser(usernameInput, passwordInput)) {
                                System.out.println("ACCESS GRANTED!");
                                String role = userInfo.get(2);
                                while (true) {
                                    System.out.println("Which operation would you like to perform?");
                                    System.out.println("Please enter the number only (e.g., 1)");
                                    System.out.println("Press 'E' to exit: ");
                                    String operation = scanner.nextLine();
                                    Problem1c.checkOperations(role, operation);
                                    if (operation.equalsIgnoreCase("E")) {
                                        run();
                                        return;
                                    }
                                }
                            }
                            else {
                                System.out.println("Invalid password. Please try again.");
                            }
                        }
                    }
                    else {
                        System.out.println("User not found, please enroll first.");
                        System.out.println("Would you like to enroll? (Y/N)");
                        String enroll = scanner.nextLine();
                        if (enroll.equals("Y")) {
                            Problem3a.enrollUser();
                            System.out.println("Enrollment complete. Would you like to login? (Y/N)");
                            String login = scanner.nextLine();
                            if (login.equals("Y")) {
                                run();
                            } else {
                                run();
                                return;
                            }
                        } else {
                            run();
                            return;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while retrieving user data. Please try again later.");
                    e.printStackTrace();
                }
            }
        }
    }
}