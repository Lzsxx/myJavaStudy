package encryption;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD_Main {
    public static String str = "A Little Barbarous Growth  Mushroom";

    public static void main(String[] args) {
        jdkMD5();
    }

    public static void jdkMD5(){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestBytes = md.digest(str.getBytes());

            System.out.println("JDK MD5: "+ Hex.encodeHexString(digestBytes));
            System.out.println("JDK MD5 with my encodeHex: ");
            myEncodeHexString(digestBytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public static void myEncodeHexString(byte[] bytesArr){
        for (byte abyte : bytesArr) {
            int byteHexnt = abyte & 0xff;       //将每一个byte转为int
            System.out.print(Integer.toHexString(byteHexnt));
        }
    }
}

