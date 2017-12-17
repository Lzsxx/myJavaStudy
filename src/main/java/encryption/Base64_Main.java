package encryption;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.lang.*;

public class Base64_Main {
    public static void main(String[] args) {
        jdkBase64();
        commonCodeBase64();
    }

    public static String str = "A Little Barbarous Growth  Mushroom";
    public static void jdkBase64(){

        try {
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(str.getBytes());
            System.out.println("encode: " + encode);

            BASE64Decoder decoder = new BASE64Decoder();
            String decode = new String(decoder.decodeBuffer(encode));
            System.out.println("decode: " + decode);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void commonCodeBase64(){
        byte[] encodeBytes = Base64.encodeBase64(str.getBytes());
        System.out.println("common Encode: " + new String(encodeBytes));

        byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
        System.out.println("common Decode: " + new String(decodeBytes));
    }
}
