package WX.thread;

import WX.constant.WxConstant;
import WX.util.CommonUtil;
import WX.util.WxUtils;
import WX.vo.AccessToken;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import java.io.FileNotFoundException;

public class TokenThread implements Runnable{

    private long sleepTime = 90 * 60 * 1000L;

    public TokenThread(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public TokenThread() {
    }

    @Override
    public void run() {
        while (true) {
            try {
                process();
                Thread.sleep(sleepTime);

            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void process() throws FileNotFoundException {
        // 先申请accessToken，定时90分钟刷新一次，每次刷新后将应答和accessToken分别写入文件，并记录时间
        JSONObject jsonObject = WxUtils.getAccessTokenResp(WxConstant.APP_ID, WxConstant.APP_SECRET);
        // 将应答写入文件
//        System.out.println(CommonUtil.getTime() + ":" + "token_resp" + ":" + JSONUtils.valueToString(jsonObject));
        CommonUtil.writeToFile(WxConstant.path, WxConstant.accessTokenRespFile, JSONUtils.valueToString(jsonObject));

        // 解析应答
        AccessToken accessToken = null;
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setAccessToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                System.out.println(String.format("获取token失败 errcode:%s errmsg:%s", jsonObject.getInt("errcode"), jsonObject.getString("errmsg")));
                return;
            }
        }
        // 将解析后的accessToken写入文件
        System.out.println(CommonUtil.getTime()+ ":" + "【token】" + ":" +  accessToken.getAccessToken());
        CommonUtil.writeToFileCover(WxConstant.path, WxConstant.accessTokenFile, accessToken.getAccessToken());
    }

    public static void main(String[] args) throws FileNotFoundException {
        TokenThread tokenThread = new TokenThread(1);
        tokenThread.process();
    }
}
