/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.security;

import ch.burninghammer.vstrophy.entities.user.User;
import ch.burninghammer.vstrophy.entities.user.UserEntityManager;
import ch.burninghammer.vstrophy.webportal.error.WebPortalException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateless
public class PasswordUtils {

    private static final int BYTE_LENGTH = 32;

    @Inject
    private UserEntityManager userEntityManager;

    public void setPassword(User user, String original) throws WebPortalException {
        try {
            byte salt[] = new byte[BYTE_LENGTH];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(salt);
            byte password[] = hash(original.toCharArray(), salt);
            user.setPassword(password);
            user.setSalt(salt);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            throw new WebPortalException("Could not set secure Password!", ex);
        }
    }

    private byte[] hash(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, 1000, BYTE_LENGTH * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return skf.generateSecret(spec).getEncoded();
    }

    public boolean checkCredentials(String username, String password) {
        User userEntity = userEntityManager.getUser(username);
        try {
            byte[] hashToCheck = hash(password.toCharArray(), userEntity.getSalt());
            return slowEquals(hashToCheck, userEntity.getPassword());
        } catch (Throwable ex) {
            return false;
        }
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }
}
