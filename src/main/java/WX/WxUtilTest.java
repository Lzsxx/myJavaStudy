package WX;

import WX.constant.WxConstant;
import WX.thread.TicketThread;
import WX.thread.TokenThread;
import WX.util.WxUtils;
import WX.vo.AccessToken;
import WX.vo.JsapiTicket;
import sun.tools.jstat.Token;

public class WxUtilTest {


    public static void main(String[] args) throws Exception {
//        // 申请accessToken
//        AccessToken accessToken = WxUtils.getAccessToken(WxConstant.APP_ID, WxConstant.APP_SECRET);
//        System.out.println("access_token:"+ accessToken.getAccessToken());
//        System.out.println("expires_in:"+ accessToken.getExpiresIn());
//
//        // 申请jsapi_ticket
//        JsapiTicket jsapiTicket = WxUtils.getJsapiTicket(accessToken.getAccessToken());
//        System.out.println("errcode:"+jsapiTicket.getErrCode());
//        System.out.println("errmsg:"+jsapiTicket.getErrMsg());
//        System.out.println("ticket:"+jsapiTicket.getTicket());
//        System.out.println("expire_in:"+jsapiTicket.getExpiresIn());

        process();
    }

    private static void process() throws InterruptedException {
        // 先申请accessToken，定时90分钟刷新一次，每次刷新后将应答和accessToken分别写入文件，并记录时间
        // 每10分钟申请一次jsapi_ticket，每次从文件拿accessToken，并将应答写入文件
        Thread tokenThread = new Thread(new TokenThread(60 * 60 * 1000L));
        Thread ticketThread = new Thread(new TicketThread(5 * 60 * 1000L));

        tokenThread.start();
//        tokenThread.join();

        ticketThread.start();
//        ticketThread.join();
    }


}