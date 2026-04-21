package com.oosd.springmvc_mxh.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Hasher {

    public enum Algorithm {

        SHA_256("SHA-256"),
        SHA_512("SHA-512");

        private final String value;

        Algorithm(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public static String hash(String content, Algorithm algorithm) {
        final MessageDigest messageDigest;

        try {
            messageDigest = MessageDigest.getInstance(algorithm.getValue());
        } catch (NoSuchAlgorithmException e) {
            return "";
        }

        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        messageDigest.update(bytes);

        return toHex(messageDigest.digest());
    }

    private static String toHex(byte[] bytes) {
        final char[] HEX = "0123456789ABCDEF".toCharArray();
        char[] out = new char[bytes.length * 2];

        for (int i = 0, j = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            out[j++] = HEX[v >>> 4];
            out[j++] = HEX[v & 0x0F];
        }

        return new String(out);
    }

}