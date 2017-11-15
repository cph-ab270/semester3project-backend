package org.cba.model.facade;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**
 * Created by adam on 11/15/2017.
 */
public class Hasher {
    public String hashPassword(String rawPassword, String salt) {
        try {
            String saltedPassword = rawPassword += salt;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateSalt() {
        final Random r = new SecureRandom();
        byte[] salt = new byte[128];
        r.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
