import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Problem4ab {

    public static boolean LoginUser(String username, String password) {
        try {
            List<String> userInfo = Problem2c.retrieveUserInfo(username);

            if (userInfo != null) {
                if (Problem2c.verifyUser(username, password)) {
                    String role = userInfo.get(2);
                    if (role.equals("T")) {
                        Scanner scanner = new Scanner(System.in);

                        while (true) {
                            System.out.print("Set your time (HH:MM, 24-hour format): ");
                            String timeInput = scanner.nextLine();

                            try {
                                LocalTime userTime = LocalTime.parse(timeInput);

                                if (Problem1c.checkTime(userTime)) {
                                    System.out.println("ACCESS GRANTED!");
                                    break;
                                } else {
                                    System.out.println("Access Denied: Tellers can only access the system between 9:00 AM and 5:00 PM.");
                                    System.out.println("Returning to the main screen...");
                                    return false;
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid time format.");
                            }
                        }
                    }

                    System.out.println("\n--- User Details ---");
                    System.out.println("Username: " + username);
                    System.out.println("Role: " + role);
                    System.out.println("Permissions:");
                    Problem1c.showPermissions(role);
                    System.out.println("---------------------\n");

                    return true;
                } else {
                    System.out.println("Incorrect password. Please try again.");
                    return false;
                }
            } else {
                System.out.println("User not found. Please enroll first.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while retrieving user data.");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
            e.printStackTrace();
            return false;
        }
    }
}

