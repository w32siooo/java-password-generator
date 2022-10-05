import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class SecureRandomPasswordGenerator {
    // ASCII table for reference
    // https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
    // -128 and a maximum value of 127 (inclusive).
    public static byte[] asciiSpecialChars(int length) throws NoSuchAlgorithmException {
        Random rand = SecureRandom.getInstanceStrong();
        byte[] bytesOut = new byte[length];
        int i = 0;
        while (i < length) {
            byte[] array = new byte[1];
            rand.nextBytes(array);
            int toAdd = Math.abs(array[0]);
            if (array[0] > 32 && array[0] < 126) {
                bytesOut[i++] = (byte) toAdd;
            }
        }

        return bytesOut;
    }

    public static byte[] asciiAlphaNumSmall(int length) throws NoSuchAlgorithmException {
        Random rand = SecureRandom.getInstanceStrong();
        byte[] bytesOut = new byte[length];
        int i = 0;
        while (i < length) {
            byte[] array = new byte[1];
            rand.nextBytes(array);
            int toAdd = Math.abs(array[0]);
            if ((array[0] > 48 && array[0] < 58) || (array[0] > 96 && array[0] < 123)) {
                bytesOut[i++] = (byte) toAdd;
            }
        }

        return bytesOut;
    }

    public static byte[] asciiAlphaNumAll(int length) throws NoSuchAlgorithmException {
        Random rand = SecureRandom.getInstanceStrong();
        byte[] bytesOut = new byte[length];
        int i = 0;
        while (i < length) {
            byte[] array = new byte[1];
            rand.nextBytes(array);
            int toAdd = Math.abs(array[0]);
            if ((array[0] > 48 && array[0] < 58) || (array[0] > 64 && array[0] < 91) || (array[0] > 96 && array[0] < 123)) {
                bytesOut[i++] = (byte) toAdd;
            }
        }

        return bytesOut;
    }

    public static byte[] asciiAlphaNumAllWithSpecialChars(int length, int numSpecialChars) throws NoSuchAlgorithmException {
        Random rand = SecureRandom.getInstanceStrong();
        byte[] bytesOut = new byte[length];
        int i = 0;
        while (i < length) {
            byte[] array = new byte[1];
            rand.nextBytes(array);
            int toAdd = Math.abs(array[0]);
            if (numSpecialChars != 0) {
                if ((array[0] > 32 && array[0] < 126) && !Character.isAlphabetic((char) toAdd) && !Character.isDigit((char) toAdd)) {
                    bytesOut[i++] = (byte) toAdd;
                    numSpecialChars--;
                }
            } else if ((array[0] > 48 && array[0] < 58) || (array[0] > 64 && array[0] < 91) || (array[0] > 96 && array[0] < 123)) {
                bytesOut[i++] = (byte) toAdd;
            }
        }

        // then shuffle...
        for (int q = length-1; q > 0; q--) {

            int j = rand.nextInt(q+1);
            byte temp = bytesOut[q];
            bytesOut[q] = bytesOut[j];
            bytesOut[j] = temp;
        }

        return bytesOut;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(new String(asciiAlphaNumAllWithSpecialChars(25, 5), StandardCharsets.US_ASCII));
    }
}