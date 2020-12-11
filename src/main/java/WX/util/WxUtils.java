package WX.util;

import WX.vo.AccessToken;
import WX.vo.JsapiTicket;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxUtils {

    private static Logger log = LoggerFactory.getLogger(WxUtils.class);


    // 凭证获取（GET）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    /**
     * 获取接口访问凭证
     *
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        // 发起GET请求获取凭证
        JSONObject jsonObject = HttpUtils.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setAccessToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }

    /**
     * 获取jsapi_ticket
     *
     * @param accessToken 访问Token
     * @return
     */
    public static JsapiTicket getJsapiTicket(String accessToken) {
        JsapiTicket jsapiTicket = null;
        String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);
        // 发起GET请求获取凭证
        JSONObject jsonObject = HttpUtils.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                jsapiTicket = new JsapiTicket();
                jsapiTicket.setErrCode(jsonObject.getString("errcode"));
                jsapiTicket.setErrMsg(jsonObject.getString("errmsg"));
                jsapiTicket.setTicket(jsonObject.getString("ticket"));
                jsapiTicket.setExpiresIn(jsonObject.getInt("expires_in"));

            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return jsapiTicket;
    }

}
