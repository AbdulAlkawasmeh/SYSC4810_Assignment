import java.io.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


public class Problem2c {

    private static final String PASSWORD_FILE = "passwd.txt";

    public static void addUser(String username, String password, String role) throws Exception {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        String Salt = Base64.getEncoder().encodeToString(salt);

        MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
        sha512.update(Base64.getDecoder().decode(Salt));
        sha512.update(password.getBytes());
        byte[] hashedPassword = sha512.digest();
        String Hash = Base64.getEncoder().encodeToString(hashedPassword);

        String record = username + "|" + Salt + "|" + Hash + "|" + role;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASSWORD_FILE, true))) {
            writer.write(record);
            writer.newLine();
        }
    }

    public static List<String> retrieveUserInfo(String username) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(username)) {
                    String salt = parts[1];
                    String hashedPassword = parts[2];
                    String role = parts[3];
                    return Arrays.asList(salt, hashedPassword, role);
                }
            }
        }
        return null;
    }

    public static boolean verifyUser(String username, String password) throws Exception {
        List<String> userInfo = retrieveUserInfo(username);

        if (userInfo != null) {
            String Salt = userInfo.get(0);
            String actualHash = userInfo.get(1);

            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            sha512.update(Base64.getDecoder().decode(Salt));
            sha512.update(password.getBytes());
            byte[] hashedPassword = sha512.digest();
            String userHash = Base64.getEncoder().encodeToString(hashedPassword);

            return actualHash.equals(userHash);
        }

        return false;
    }

}