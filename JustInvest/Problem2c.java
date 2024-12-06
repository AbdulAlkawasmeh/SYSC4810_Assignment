import java.io.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class Problem2c {

    public static void addUser(String username, String password, String role) throws Exception {  //adds users to the system/passwd file
        byte[] salt = new byte[16];  //salt with size 16 bytes
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        String Salt = Base64.getEncoder().encodeToString(salt); //encoding salt
        String Hash = hashPassword(Salt, password); //hashing password with salt
        String record = username + "|" + Salt + "|" + Hash + "|" + role;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("passwd.txt", true))) {
            writer.write(record);
            writer.newLine();
        }
    }

    public static List<String> retrieveUserInfo(String username) throws IOException { //gets each data row of a user
        try (BufferedReader reader = new BufferedReader(new FileReader("passwd.txt"))) { //reading the passwd file
            String line;
            while ((line = reader.readLine()) != null) { //reading each line
                String[] parts = line.split("\\|"); //splitting each part of the row
                if (parts[0].equals(username)) {
                    String salt = parts[1]; //salt is 2nd value in the data row
                    String hashedPassword = parts[2]; //password is 3rd value in data row
                    String role = parts[3]; //role is 4th value in data row
                    return Arrays.asList(salt, hashedPassword, role); //return all 3 items as an arraylist
                }
            }
        }
        return null;
    }

    public static boolean verifyUser(String username, String password) throws Exception { //verifies the user
        List<String> userInfo = retrieveUserInfo(username);
        if (userInfo != null) {
            String Salt = userInfo.get(0);
            String Hash1 = userInfo.get(1);
            String Hash2 = hashPassword(Salt, password);
            return Hash1.equals(Hash2);
        }
        return false;
    }

    private static String hashPassword(String salt, String password) throws Exception { //hashes the password with SHA512
        MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
        sha512.update(Base64.getDecoder().decode(salt));
        sha512.update(password.getBytes());
        byte[] hashedPassword = sha512.digest();
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}