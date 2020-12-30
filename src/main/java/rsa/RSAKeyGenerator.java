package rsa;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Arrays;

public class RSAKeyGenerator {
    public static void main(String[] args) {
        genRSAKeyPair();

//        //待加密文件
//        byte[] s="测试解密".getBytes();
//        Cipher cipher=Cipher.getInstance("RSA");
//        //加密
//        cipher.init(Cipher.ENCRYPT_MODE, aPublic);
//        byte[] enBytes = cipher.doFinal(s);
//        System.out.println("加密后的密文：" +enBytes.toString());
//        //解密
//        String s1 = RSAUtil.encodeString(enBytes, encode1);
//        System.out.println("解密后的密文：" +s1);
    }
    /*
     * 产生RSA公私钥对
     */
    public static KeyPair genRSAKeyPair() {
        KeyPairGenerator rsaKeyGen = null;
        KeyPair rsaKeyPair = null;
        try {
            System.out.println("Generating a pair of RSA key ... ");
            rsaKeyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = new SecureRandom();
            random.nextBytes(new byte[8]);
//            random.nextBytes(new byte[1]);
            rsaKeyGen.initialize(1024, random);
//            rsaKeyGen.initialize(1024, new SecureRandom());
            rsaKeyPair = rsaKeyGen.genKeyPair();
            PublicKey rsaPublic = rsaKeyPair.getPublic();
            PrivateKey rsaPrivate = rsaKeyPair.getPrivate();
            System.out.println("1024-bit RSA key GENERATED.");

            System.out.println("rsaPublic:"+ new String(rsaPublic.getEncoded()));
            System.out.println("rsaPrivate:"+ new String(rsaPrivate.getEncoded()));

            System.out.println("=====================");
            System.out.println("rsaPublic-Base64:\n"+ new BASE64Encoder().encode(rsaPublic.getEncoded()).replaceAll("\r\n",""));
            System.out.println();
            System.out.println("rsaPrivate-Base64:\n"+ new BASE64Encoder().encode(rsaPrivate.getEncoded()).replaceAll("\r\n",""));


        } catch (Exception e) {
            System.out.println("Exception in keypair generation. Reason: " + e);
        }

        return rsaKeyPair;
    }


}
