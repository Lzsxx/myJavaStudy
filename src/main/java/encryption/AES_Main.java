//package encryption;
//
//import org.apache.commons.codec.binary.Base64;
//
//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.security.Key;
//
//public class AES_Main {
//    public static void main(String[] args) {
//            jdkHmacMD5();
//    }
//    public static String str = "A Little Barbarous Growth  Mushroom";
//    public static void jdkHmacMD5(){
//        try {
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");;    //初始化Key生成器
//            keyGenerator.init(128);
//            SecretKey secretKey = keyGenerator.generateKey();   //产生密钥
//            byte[] keyBytes = secretKey.getEncoded();
//
//            Key aesKey = new SecretKeySpec(keyBytes, "AES");   //Key转换
//
//            // 加密
//            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); //
//            cipher.init(Cipher.ENCRYPT_MODE, aesKey);  //指定用转换后的AES密钥
//            byte[] result = cipher.doFinal(str.getBytes()); //进行加密
//            System.out.println("AES加密结果为："+ Base64.encodeBase64String(result)); //显示可读字符串
//            System.out.println("AES加密结果为："+ new String(result));    //显示乱码
//
//            //解密
//            cipher.init(Cipher.DECRYPT_MODE, aesKey);
//            result = cipher.doFinal(result);    //对加密内容进行解密
//            System.out.println("AES加密结果为："+ new String(result));    //显示str原文
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//}
