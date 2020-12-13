package WX;

import WX.util.SHA1Utils;

import java.security.DigestException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class SHA1Test {


    public static void main(String[] args) throws DigestException {
//        Date date = new Date();
//        System.out.println(date.getTime());

        String jsapi_ticket = "O3SMpm8bG7kJnF36aXbe8-L-ZVKhQT77wIczyXUnfElSgD1ByYEwpIIfp1Yf_18a8oinVuoMsPQ0neYEtrtxQg";
        String noncestr = "123456";
        String timestamp = "1607779544";
        String url = "http://qq.com";

//        String content = "jsapi_ticket=O3SMpm8bG7kJnF36aXbe8-L-ZVKhQT77wIczyXUnfElSgD1ByYEwpIIfp1Yf_18a8oinVuoMsPQ0neYEtrtxQg&noncestr=123456&timestamp=1607779544&url=http://qq.com";
        String content = "jsapi_ticket=O3SMpm8bG7kJnF36aXbe8-L-ZVKhQT77wIczyXUnfElSgD1ByYEwpIIfp1Yf_18a8oinVuoMsPQ0neYEtrtxQg&noncestr=123456&timestamp=1607779544&url=http://qq.com?test=20201213";

        String sha1Str = SHA1Utils.encode(content);
        System.out.println(sha1Str);
//        e8e873b871bcb58177d267fd5f057bb52b592a1a
    }
}
