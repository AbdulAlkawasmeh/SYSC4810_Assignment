import java.util.*;

public class Problem3b {

    private static final Set<String> WEAK_PASSWORDS = new HashSet<>(); //list of weak passwords

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

    public static boolean isValidPassword(String password, String username) { //password checker
        if (password.length() < 8 || password.length() > 12) { //checks if password is within allowed size between 8 and 12 characters
            System.out.println("Password must be between 8 and 12 characters.");
            return false;
        }
        if (password.equals(username)) { //checks if password is equal to username
            System.out.println("Username and password cannot be the same.");
            return false;
        }
        if (WEAK_PASSWORDS.contains(password)) { //checks if password is weak
            System.out.println("Please choose a stronger password.");
            return false;
        }
        boolean uppercase = false;
        boolean lowercase = false;
        boolean number = false;
        boolean special = false;

        String specialchars = "@#$%^&+=!";
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) { //checks if it includes uppercase
                uppercase = true;
            }
            if (Character.isLowerCase(ch)) { //checks if it includes lowercase
                lowercase = true;
            }
            if (Character.isDigit(ch)) { //checks if it includes digit
                number = true;
            }
            if (specialchars.indexOf(ch) != -1) { //checks if it includes special characters
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
