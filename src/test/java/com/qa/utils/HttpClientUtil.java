package com.qa.utils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qa.exceptions.HttpStatusException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;

import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;


import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static com.qa.utils.StringURLUtil.urlParamMatcher;

public class HttpClientUtil {

    private CloseableHttpClient httpclient = null;
    long stime = System.currentTimeMillis();
    private String responseBody = null;
    private HttpResponse response = null;
    private HttpResponse wkssoResponse = null;
    private long responseTime = 999999999;
    CookieStore httpCookieStore = new BasicCookieStore();
    int timeout = 10;


    @SuppressWarnings("deprecation")
    public HttpClientUtil() {

        buildNewHttpClient();

        // HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        //httpClientBuilder.setSSLContext(ctx);
        //httpClientBuilder.setSslcontext(ctx);//not work on 4.4
        //this.httpclient = httpClientBuilder.build();
        //this.httpclient = HttpClients.createDefault();
    }

    public   void buildNewHttpClient() {
        stime=System.currentTimeMillis();
        SSLContext ctx = null;  // 4.3
        SSLContext sslContext = null;// 4.4

        try {
            ctx = SSLContext.getInstance("SSL");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
                    return true;
                }

                public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
                    return true;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            System.out.println("http初始化时间： " +  (System.currentTimeMillis()-stime )+ "ms");
            sslContext = SSLContexts.custom()
                    .loadTrustMaterial(null, new TrustStrategy() {
                        @Override
                        public boolean isTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
                            return true;
                        }
                    })
                    //.useTLS()
                    .build();
        } catch (Exception e) {
            ExceptionUtil.printlnSo(e);
        }
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        CloseableHttpClient client;//= HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        System.out.println("http初始化时间： " +  (System.currentTimeMillis()-stime )+ "ms");
        client = HttpClients.custom()
                //	.setKeepAliveStrategy(myStrategy)
                //     .setSslcontext(sslContext)
                .setSSLSocketFactory(sslsf)
                .setDefaultRequestConfig(config)
                //        .setConnectionManager(connManager)  // bug? not tr ust
                //.setDefaultRequestConfig(config)
                .setConnectionManagerShared(true)
                .setDefaultCookieStore(httpCookieStore)
                .build();

        this.httpclient = client;
        System.out.println("http初始化时间： " +  (System.currentTimeMillis()-stime )+ "ms");
    }

    public String httpPostRequest(String URL, HttpRequestCallback ci) throws IOException {
        stime = System.currentTimeMillis();

        Iterator<Map.Entry<String, String>> iter = ci.getHeaderParameters();
        final HttpPost httpPost = new HttpPost(urlParamMatcher(URL, ci.getParam()));
        //  httpPost.setConfig(RequestConfig.custom().setCookieSpec());
        int flag = 0;
        int CTFlag = 0;
        System.out.println("------------------------------------------------------------------");

        System.out.println("请求地址：  POST  " + httpPost.getURI());

        if (iter != null) {
            while (iter.hasNext()) {
                Map.Entry<String, String> me = iter.next();
                httpPost.addHeader(me.getKey(), me.getValue());
                System.out.println("请求头：  " + me.getKey() + ":" + me.getValue());
                if ("os".equals(me.getKey())) {
                    flag = 1;
                }
                if ("Content-Type".equals(me.getKey())) {
                    CTFlag = 1;
                    String vv = me.getValue();
                    if (vv != null && vv.contains("application/json")) {
                        CTFlag = 2;// JSON
                    }
                    if (vv != null && vv.contains("application/x-www-form-urlencoded")) {
                        CTFlag = 3;// form
                    }
                }
            }
        }

        if (0 == flag) {
            httpPost.addHeader("os", "monitor");
        }
        if (1 == CTFlag) {
            System.out.println("未知类型：  Content-Type:" + httpPost.getFirstHeader("Content-Type"));
        }
        if (0 == CTFlag) {
            System.out.println("请求参数：  " + ci.getJsonParam());
            StringEntity entity = new StringEntity(ci.getJsonParam(), "utf-8");
            entity.setContentType("application/json;charset=UTF-8");
            httpPost.setEntity(entity);
        }
        if ((2 == CTFlag) && (null != ci.getJsonParam())) {
            System.out.println("请求参数：  " + ci.getJsonParam());
            StringEntity entity = new StringEntity(ci.getJsonParam(), "utf-8");
            entity.setContentType("application/json;charset=UTF-8");
            httpPost.setEntity(entity);
        }
        if ((3 == CTFlag) && (null != ci.getParam())) {
            System.out.println("请求参数：  " + ci.getParam());
            StringEntity entity = new StringEntity(ci.getParam(), "utf-8");
            entity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(entity);
        }


        // Before end
//            ResponseHandler<String> responseHandler = createResponseHandler();
        if (!ci.getIsRedirect()) {
            //      buildNewHttpClient();
        }
        httpPost.setConfig(RequestConfig.custom().setRedirectsEnabled(ci.getIsRedirect()).setCircularRedirectsAllowed(false).build());
        //  new Runnable();

        asyncCloseTimeout(httpPost);
        response = httpclient.execute(httpPost);

        //    List<Cookie> cookies = ((AbstractHttpClient) httpclient).getCookieStore().getCookies();
        try {
            printState(response, ci);
        } catch (HttpStatusException e) {
            if (e.status == 401) {
                //目前是 json
            } else if (e.status == 400 || e.status == 500) {
                throw e;
            }
        }


        return responseBody;

    }

    /**
     * 监视线程
     *
     * @param httpPOSTorGET
     */
    public void asyncCloseTimeout(final AbstractExecutionAwareRequest httpPOSTorGET) {
        response = null;
        Runnable watch = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(timeout * 1000);
                    System.err.println("force close http connection(强制断开连接)");
                    // responseBody=null;
                    HttpEntity entity = null;
                    if (response != null) {
                        entity = response.getEntity();
                    }
                    if (entity != null) {
                        EntityUtils.consume(entity);
                    }
                    if (httpPOSTorGET != null) {
                        httpPOSTorGET.abort();
                    }
                    httpclient.close();
                } catch (InterruptedException e) {
                    ExceptionUtil.printlnSo(e);
                } catch (IOException e) {
                    ExceptionUtil.printlnSo(e);
                }
            }
        };
        new Thread(watch).start();
    }

    public void printState(HttpResponse response, HttpRequestCallback ci) throws IOException {
        int status = response.getStatusLine().getStatusCode();
        System.out.println("返回状态码:" + status);
        responseTime = System.currentTimeMillis() - stime;
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            setResponseBody(EntityUtils.toString(entity, "utf-8"));

            if (response.getFirstHeader("Content-Type").getValue().contains("text/html")) {
                System.out.println("返回:<HTML>");
            } else {
                System.out.println("完整响应体： " + responseBody);
            }

        } else if (status == 302) {
            // response.getFirstHeader("Location");
            String location = response.getFirstHeader("Location").getValue();
            System.out.println("返回:<中间状态:302,自动重定向:" + ci.getIsRedirect() + "> " + response.getFirstHeader("Location"));
            // httpGetRequest(location,ci); 死循环
        } else {
            System.out.println("响应时间： " + responseTime + "ms");
            System.out.println("未处理状态码 :" + status);
            HttpEntity entiy = response.getEntity();
            if (entiy != null) {
                String errorMsg = EntityUtils.toString(entiy, "utf-8");
                System.out.println("完整响应体： " + errorMsg);
                if (errorMsg != null)
                    responseBody = errorMsg + "";
                throw new HttpStatusException(status, "message:<< 未处理状态码:" + status + " 返回结果:" + errorMsg + ">>");

            }
            //throw new ClientProtocolException("Unexpected response status(未处理状态码): " + status);
        }
        System.out.println("响应时间： " + responseTime + "ms");

    }

    /**
     * @param URL
     * @param ci  反向取值方法
     * @return
     * @throws IOException
     */
    public String httpGetRequest(String URL, HttpRequestCallback ci)     throws IOException {
        URL = urlParamMatcher(URL, ci.getParam());
        final HttpGet httpDelete=new HttpGet(URL);
        return  httpDeleteGetRequest(URL,ci,httpDelete);
    }
    public String httpDeleteGetRequest(String URL, HttpRequestCallback ci,HttpRequestBase httpGet)throws IOException {
        try {
            stime = System.currentTimeMillis();
            Iterator<Map.Entry<String, String>> iter = ci.getHeaderParameters();

            System.out.println("------------------------------------------------------------------");
            System.out.println(httpGet.getMethod()+" 请求地址：  " + httpGet.getURI());

            int flag = 0;
            while (iter.hasNext()) {
                Map.Entry<String, String> me = iter.next();
                httpGet.addHeader(me.getKey(), me.getValue());
                System.out.println("请求头：  " + me.getKey() + ":" + me.getValue());
                if ("os".equals(me.getKey())) {
                    flag = 1;
                }
            }
            if (0 == flag) {
                httpGet.addHeader("os", "monitor");
            }

            if (!ci.getIsRedirect()) {
                //   buildNewHttpClient();
            }
            httpGet.setConfig(RequestConfig.custom().setRedirectsEnabled(ci.getIsRedirect()).setCircularRedirectsAllowed(false).build());

            response = null;
            asyncCloseTimeout(httpGet);
            response = httpclient.execute(httpGet);

            printState(response, ci);
            return responseBody;
        } finally {
            httpclient.close();
        }

    }

    public String httpDeleteRequest(String URL, HttpRequestCallback ci) throws IOException {
        URL = urlParamMatcher(URL, ci.getParam());
        final HttpDelete httpDelete=new HttpDelete(URL);
        return httpDeleteGetRequest(URL,ci,httpDelete);
    }



//    ResponseHandler createResponseHandler() {
//        return new ResponseHandler<String>() {
//            public String handleResponse(final HttpResponse response)
//                    throws ClientProtocolException, IOException {
//                int status = response.getStatusLine().getStatusCode();
//                if (status >= 200 && status < 300) {
//                    HttpEntity entity = response.getEntity();
//                    return entity != null ? EntityUtils.toString(entity)
//                            : null;
//                } else {
//                    throw new ClientProtocolException(
//                            "Unexpected response status: " + status);
//                }
//            }
//        };
//    }

    public String httpRequest(String URL, HttpRequestCallback ci, String type) throws IOException {
        if ("get".equals(type.toLowerCase())) {
            return httpGetRequest(URL, ci);
        } else if ("post".equals(type.toLowerCase())) {
            return httpPostRequest(URL, ci);
        } else if("delete".equals(type.toLowerCase())){
            return httpDeleteRequest(URL,ci);
        }else{
            return "";
        }
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public Header[] getResponseHeader(String responseHeaderKey) {
        Header[] headers = response.getHeaders(responseHeaderKey);
        return headers;
    }

//    public Header[] getwkssoResponseHeader(String responseHeaderKey) {
//        Header[] headers = wkssoResponse.getHeaders(responseHeaderKey);
//        return headers;
//    }

    public long getResponseTime() {
        return responseTime;
    }
}