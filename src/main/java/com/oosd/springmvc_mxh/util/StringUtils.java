package com.oosd.springmvc_mxh.util;

public final class StringUtils {

    public static String EMPTY = "";

    public static String toHex(byte[] bytes) {
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