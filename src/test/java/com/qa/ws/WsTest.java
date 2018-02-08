package com.qa.ws;

import com.qa.ConnectServer;
import com.qa.ConnectWs;
import com.qa.Store;
import com.qa.http.HttpLog;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import static com.qa.Store.SOCKET_TIMEOUT;
import static com.qa.utils.HaoLieUtil.Utf8ArrayToStr;

public class WsTest{


    @Test
    public void aTest() throws URISyntaxException {
        WebSocketClient client = new WebSocketClient(new URI( "ws://im.lieluobo.testing/chat")) {
            @Override
            public void onOpen( ServerHandshake handshakedata ) {
                System.out.println("已经连接");
                getConnection().send("{\"requestId\":\"ba7f373f-9821-7a2a-02b1-6d10f61a8a5f\",\"method\":0,\"sourceUid\":\"150113265729136\",\"body\":{\"accessToken\":\"02c28e73-9792-4c29-b862-26cf1594ec22\",\"did\":\"76af7177-04d9-12f6-fa28-358b860a6973\",\"platform\":\"PC\",\"channel\":\"hr\"}}");
            }

            @Override
            public void onMessage( String message ) {
                System.out.println(message);
            }

            @Override
            public void onClose( int code, String reason, boolean remote ) {
                System.out.println("退出");
            }
            @Override
            public void onMessage(ByteBuffer byteBuffer){
                //HttpLog.info("--binary--"+client.getURI()+"--");
                System.out.println(Utf8ArrayToStr(byteBuffer));
            }

            @Override
            public void onError( Exception ex ) {
                System.out.println("出错.");
            }
        };
        client.connect();
        //client.onMessage();
        // 存在子线程.

        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void publiTest() throws URISyntaxException {

        WebSocketClient client = new WebSocketClient(new URI( "ws://121.40.165.18:8088")) {
            @Override
            public void onOpen( ServerHandshake handshakedata ) {
                System.out.println("已经连接");
                getConnection().send("AAAA");
            }

            @Override
            public void onMessage( String message ) {
                System.out.println(message);
            }

            @Override
            public void onClose( int code, String reason, boolean remote ) {
                System.out.println("退出"+reason+code);
            }
            @Override
            public void onMessage(ByteBuffer byteBuffer){
                //HttpLog.info("--binary--"+client.getURI()+"--");
                System.out.println(Utf8ArrayToStr(byteBuffer));
            }

            @Override
            public void onError( Exception ex ) {
                System.out.println("出错.");
            }
        };
        client.connect();
        //client.onMessage();
        // 存在子线程.

//        try {
//        //    Thread.sleep(500000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void voTest(){
        ConnectWs cs=new ConnectWs("ws://121.40.165.18:8088");
        System.out.println(cs.value());
        cs.send("ABC");
        System.out.println(cs.value());




    }
    @Test
    public void v2oTest(){
        Store store = new Store(Store.SOCKET_TIMEOUT,"500");
        ConnectServer cs=new ConnectServer("ws://121.40.165.18:8088");
        System.out.println(cs.value());
        cs.send("ABC");
        System.out.println(cs.value());

    }
}
