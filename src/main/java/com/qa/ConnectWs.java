package com.qa;

import com.qa.http.Http;
import com.qa.http.HttpLog;
import com.qa.utils.JsonUtil;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;

import static com.qa.utils.HaoLieUtil.Utf8ArrayToStr;

/**
 * fitnesse_toc
 * Created by yu on 2018/1/5.
 */
public class ConnectWs {

    JsonUtil jsonUtil = new JsonUtil();

    WebSocketClient client = null;

    String responseBody = "";
    String requestBody = "";
    String WS_URL="";


    public ConnectWs(String url) {

        try {
            client = new WebSocketClient(new URI(url)) {
                @Override
                public void onOpen(ServerHandshake handshakeData) {
                    HttpLog.info("ConnectSuccess:" + url);
                    WS_URL=url;
                }

                @Override
                public void onMessage(String message) {
                    HttpLog.info("Message:"+message);
                    responseBody=message;

                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    HttpLog.info("ConnectClose:"+WS_URL);
                }

                @Override
                public void onMessage(ByteBuffer byteBuffer) {
                    HttpLog.info("--Binary:---");
                    //这里使用了,haolie的格式.
                    responseBody=Utf8ArrayToStr(byteBuffer);
                    HttpLog.info(responseBody);
                }

                @Override
                public void onError(Exception ex) {
                    HttpLog.info("WS_ERROR:"+ex.getMessage());
                }
            };
        } catch (URISyntaxException e) {
            HttpLog.info(e.getMessage());
        } catch (NotYetConnectedException e) {
            HttpLog.info(e.getMessage());
        }
        client.connect();
    }

    public boolean send(String string) {

        for(int i=0;i<5;i++){
            if(!client.isOpen()){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    HttpLog.info(e.getMessage());
                }
            }
        }
        HttpLog.info("Send:"+string);
        client.getConnection().send(string);
        return true;
    }

    public String jsonValue(String node) {
        jsonUtil.parseJson(responseBody);
        return jsonUtil.getResult(node);
    }

    public String value() {
        return responseBody;
    }
}
