package com.oosd.springmvc_mxh.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Hasher {

    public static class Algorithm {
        public static final String SHA_256 = "SHA-256";
        public static final String SHA_512 = "SHA-512";
    }

    public static String hash(String content, String algorithm) {
        final MessageDigest messageDigest;

        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            return StringUtils.EMPTY;
        }

        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        messageDigest.update(bytes);

        return StringUtils.toHex(messageDigest.digest());
    }

}