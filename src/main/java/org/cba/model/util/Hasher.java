package org.cba.model.util;

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
            String saltedPassword = rawPassword + salt;
            return performHash(saltedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String performHash(String saltedPassword) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        return convertByteHashToString(hash);
    }

    private String convertByteHashToString(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String generateSalt() {
        final Random r = new SecureRandom();
        byte[] salt = new byte[128];
        r.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
