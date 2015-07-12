/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.security;

import ch.burninghammer.vstrophy.webportal.entities.user.User;
import ch.burninghammer.vstrophy.webportal.error.WebPortalException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Singleton;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Singleton
public class PasswordUtils {

    private static final int BYTE_LENGTH = 32;

    public void setPassword(User user, String original) throws WebPortalException {
        try {
            byte salt[] = new byte[BYTE_LENGTH];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(salt);
            PBEKeySpec spec = new PBEKeySpec(original.toCharArray(), salt, 1000, BYTE_LENGTH * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte password[] = skf.generateSecret(spec).getEncoded();
            user.setPassword(password);
            user.setSalt(salt);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            throw new WebPortalException("Could not set secure Password!", ex);
        }
    }
}
