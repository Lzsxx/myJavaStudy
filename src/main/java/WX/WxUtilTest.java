package WX;

import WX.constant.WxConstant;
import WX.util.WxUtils;
import WX.vo.AccessToken;
import WX.vo.JsapiTicket;

public class WxUtilTest {


    public static void main(String[] args) throws Exception {
        // 申请accessToken
        AccessToken accessToken = WxUtils.getAccessToken(WxConstant.appId, WxConstant.appSecret);
        System.out.println("access_token:"+ accessToken.getAccessToken());
        System.out.println("expires_in:"+ accessToken.getExpiresIn());

        // 申请jsapi_ticket
        JsapiTicket jsapiTicket = WxUtils.getJsapiTicket(accessToken.getAccessToken());
        System.out.println("errcode:"+jsapiTicket.getErrCode());
        System.out.println("errmsg:"+jsapiTicket.getErrMsg());
        System.out.println("ticket:"+jsapiTicket.getTicket());
        System.out.println("expire_in:"+jsapiTicket.getExpiresIn());
    }
}