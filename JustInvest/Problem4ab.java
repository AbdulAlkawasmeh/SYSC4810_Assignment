import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Problem4ab {

    public static boolean LoginUser(String username, String password) {
        Scanner scanner = new Scanner(System.in);
        try {
            List<String> userInfo = Problem2c.retrieveUserInfo(username);
            if (userInfo != null) {
                if (Problem2c.verifyUser(username, password)) {
                    String role = userInfo.get(2);
                    if (role.equals("T")) {
                        while (true) {
                            System.out.print("Set your time (HH:MM): ");
                            String time = scanner.nextLine();
                            try {
                                if (Problem1c.checkTime(LocalTime.parse(time))) {
                                    System.out.println("ACCESS GRANTED!");
                                    break;
                                } else {
                                    System.out.println("Access Denied: Tellers can only access the system between 9:00 AM and 5:00 PM.");
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
                    return false;
                }
            } else {
                System.out.println("User not found. Please enroll first.");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

