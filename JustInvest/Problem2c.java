import java.io.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class Problem2c {

    public static void addUser(String username, String password, String role) throws Exception {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        String Salt = Base64.getEncoder().encodeToString(salt);
        String Hash = hashPassword(Salt, password);
        String record = username + "|" + Salt + "|" + Hash + "|" + role;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("passwd.txt", true))) {
            writer.write(record);
            writer.newLine();
        }
    }

    public static List<String> retrieveUserInfo(String username) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("passwd.txt"))) {
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
            String Hash1 = userInfo.get(1);
            String Hash2 = hashPassword(Salt, password);
            return Hash1.equals(Hash2);
        }
        return false;
    }

    private static String hashPassword(String salt, String password) throws Exception {
        MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
        sha512.update(Base64.getDecoder().decode(salt));
        sha512.update(password.getBytes());
        byte[] hashedPassword = sha512.digest();
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}