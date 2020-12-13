package WX.thread;

import WX.constant.WxConstant;
import WX.util.CommonUtil;
import WX.util.WxUtils;
import WX.vo.JsapiTicket;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TicketThread implements Runnable{

    private long sleepTime = 10 * 60 * 1000L;

    public TicketThread(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        while (true) {
            try {
                process();
                Thread.sleep(sleepTime); // 每隔10分钟获取一次ticket

            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void process() throws IOException {
        // 1. 读取accessToken
        String accessToken = CommonUtil.readFile(WxConstant.path, WxConstant.accessTokenFile);

        // 2. 申请jsapi_ticket并写入文件
        JSONObject jsonObject = WxUtils.getJsapiTicketResp(accessToken);
        // 将应答写入文件
        System.out.println(CommonUtil.getTime() + ":" + "ticket_resp" + ":" +  JSONUtils.valueToString(jsonObject));
        CommonUtil.writeToFile(WxConstant.path, WxConstant.jsapiTicketRespFile, JSONUtils.valueToString(jsonObject));
    }
}
