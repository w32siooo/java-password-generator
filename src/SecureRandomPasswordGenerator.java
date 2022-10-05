import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class SecureRandomPasswordGenerator {
    // ASCII table for reference
    // https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
    // -128 and a maximum value of 127 (inclusive).
    public static byte[] genBytes(int length) throws NoSuchAlgorithmException {
        Random rand = SecureRandom.getInstanceStrong();
        byte[] bytesOut = new byte[length];
        int i=0;
        while (i < length) {
            byte[] array = new byte[1];
            rand.nextBytes(array);
            int toAdd = Math.abs(array[0]);
            if(array[0] > 32 && array[0] < 126) {
                bytesOut[i++]= (byte) toAdd;
            }
        }

        return bytesOut;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
            System.out.println(new String(genBytes(25),StandardCharsets.US_ASCII));
    }
}