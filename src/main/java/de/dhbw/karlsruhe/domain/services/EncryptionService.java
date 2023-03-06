package de.dhbw.karlsruhe.domain.services;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionService {

  public String getSHAEncryptedPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    return hashToHexString(md.digest(password.getBytes(StandardCharsets.UTF_8)));
  }

  private String hashToHexString(byte[] hashValue) {
    BigInteger number = new BigInteger(1, hashValue);
    StringBuilder hexString = new StringBuilder(number.toString(16));

    while (hexString.length() < 32) {
      hexString.insert(0, '0');
    }

    return hexString.toString();
  }

}
