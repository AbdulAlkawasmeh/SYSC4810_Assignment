import java.util.HashMap;
import java.util.Map;
import java.time.LocalTime;

public class Problem1c {
    public static final Map<String, String[]> permissions = new HashMap<>();  //hashmap to store operations for each role/user

    static {
        permissions.put("C", new String[]{"1.View Account Balance", "2.View Investment Portfolio", "4.View Financial Advisor Contact info"});
        permissions.put("PC", new String[]{"1.View Account Balance", "2.View Investment Portfolio", "3.Modify Investment Portfolio", "4.View Financial Advisor Contact info", "5.View Financial Planner Contact info"});
        permissions.put("FA", new String[]{"1.View Account Balance", "2.View Investment Portfolio", "3.Modify Investment Portfolio", "7.View Private Consumer Instruments"});
        permissions.put("FP", new String[]{"1.View Account Balance", "2.View Investment Portfolio","3.Modify Investment Portfolio", "6.View Money Market Instruments"});
        permissions.put("T", new String[]{"1.View Account Balance", "2.View Investment Portfolio"});
    }

    public static void showPermissions(String roleInput) {  //function used to show permissions of a user
        String[] rolePermissions = permissions.get(roleInput);

        if (rolePermissions != null) {

            for (String permission : rolePermissions) {
                System.out.println(permission); //printing every permission
            }
        } else {
            System.out.println("Invalid role selected.");
        }
    }

    public static boolean checkTime(LocalTime time) {
        return (time.isAfter(LocalTime.of(8, 59)) && time.isBefore(LocalTime.of(17, 1)));  //check if time is between 9 am and 5 pm
    }

    public static void checkOperations(String Role, String operation) { //check if each operation is valid/invalid based on role
        if (Role.equals("C") && (operation.equals("1") || operation.equals("2") || operation.equals("4"))) {
            System.out.println("Operation Successful!");
        } else if (Role.equals("PC") && (operation.equals("1") || operation.equals("2") || operation.equals("3") || operation.equals("4") || operation.equals("5"))) {
            System.out.println("Operation Successful!");
        } else if (Role.equals("FA") && (operation.equals("1") || operation.equals("2") || operation.equals("3") || operation.equals("7"))) {
            System.out.println("Operation Successful!");
        } else if (Role.equals("FP") && (operation.equals("1") || operation.equals("2") ||  operation.equals("3") || operation.equals("6") )) {
            System.out.println("Operation Successful!");
        } else if (Role.equals("T") && (operation.equals("1") || operation.equals("2"))) {
            System.out.println("Operation Successful!");
        }
        else {
            System.out.println("Invalid operation/option selected.");
        }
    }
}