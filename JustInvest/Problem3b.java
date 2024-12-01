import java.util.*;

public class Problem3b {


    public static final List<Problem3a.User> users = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    private static final Set<String> WEAK_PASSWORDS = new HashSet<>();

    static {
        WEAK_PASSWORDS.add("123456");
        WEAK_PASSWORDS.add("password");
        WEAK_PASSWORDS.add("123456789");
        WEAK_PASSWORDS.add("qwerty123");
        WEAK_PASSWORDS.add("qwerty1");
        WEAK_PASSWORDS.add("111111");
        WEAK_PASSWORDS.add("123123");
        WEAK_PASSWORDS.add("abc123");
        WEAK_PASSWORDS.add("password1");
        WEAK_PASSWORDS.add("iloveyou");
        WEAK_PASSWORDS.add("welcome");
        WEAK_PASSWORDS.add("admin");
    }



    public static boolean isValidPassword(String password, String username) {
        if (password.length() < 8 || password.length() > 12) {
            System.out.println("Password must be between 8 and 12 characters.");
            return false;
        }

        if (password.equals(username)) {
            System.out.println("Username and password cannot be the same.");
            return false;
        }

        if (WEAK_PASSWORDS.contains(password)) {
            System.out.println("Please choose a stronger password.");
            return false;
        }

        boolean uppercase = false;
        boolean lowercase = false;
        boolean number = false;
        boolean special = false;

        String specialchars = "@#$%^&+=!";


        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                uppercase = true;
            }
            if (Character.isLowerCase(ch)) {
                lowercase = true;
            }

            if (Character.isDigit(ch)) {
                number = true;
            }
            if (specialchars.indexOf(ch) != -1) {
                special = true;
            }

        }

        if (!lowercase){
            System.out.println("Missing lower case character.");
        }

        if (!uppercase){
            System.out.println("Missing upper case character.");
        }

        if (!number){
            System.out.println("Missing number character.");
        }

        if (!special){
            System.out.println("Missing special character.");
        }

        return uppercase && lowercase && number && special;
    }
}
