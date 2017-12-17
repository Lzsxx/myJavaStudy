package QRCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.spec.EncodedKeySpec;
import java.util.HashMap;

public class CreateQRCode {
    public static void main(String[] args) {
        int width = 300;
        int height = width;
        String format = "jpg";  //图片的格式
        String content = "http://www.imooc.com";    //二维码所要显示的信息，要想直接跳转网址，必须要加http://

        // 定义二维码的参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   //信息字符集
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); //允许出错的等级，从低到高依次是 L M Q H
        hints.put(EncodeHintType.MARGIN, 2);    // 四周留白间距，默认是5

        /*****设置完参数，开始传入参数，执行生成操作*****/
        try {
            // BitMatrix是zxing包里的对象
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            Path filePath = new File("E:\\Code\\JavaStudy\\QRCode_image\\img.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, filePath);

            // 总结：
            // 使用zxing生成二维码，只需要设置好参数及内容，生成BitMatrix对象，调用工具类MatrixToImageWriter以writeToPath()方法生成即可。

        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
