package com.qa;

import com.qa.http.*;
import com.qa.utils.*;
import com.qa.constants.ConfigConstantsTest;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qa.Store.GLOBAL_HEADERS_KEY;
import static com.qa.Store.SOCKET_TIMEOUT;
import static com.qa.Store.getEnv;
import static com.qa.utils.GsonJsonUtil.gsonPretty;
import static com.qa.utils.HaoLieUtil.Utf8ArrayToStr;
import static com.qa.utils.StringURLUtil.buildFromByMap;
import static com.qa.utils.StringURLUtil.urlParamMatcher;

/**
 * Created by yus on 2016/11/26.
 */
public class ConnectServer {

    public static String BASE_URL = "";
    JsonUtil jsonUtil=new JsonUtil();
    String URL;
    String env = null;
    String responseBody = "";
    int responseCode=0;
    String requestBody=null;
    Map<String,String> requestMap=new HashMap();
    Map<String,String> requestHeaderMap=new HashMap();
    Map<String,List<String>> responseHeaderMap=new HashMap();
    String type = null;
    HttpLog logger=HttpLog.getLogger();
    static int __count_object_=0;
    WebSocketClient wsclient = null;

    String WS_URL="";

    public ConnectServer() {

    }
    public ConnectServer(String URL) {
        this(URL,null,null);
    }

    public ConnectServer(String URL, String env) {
         this(URL,env,null);
    }

    public ConnectServer(String URL, String env, String type) {
        addShutdownHook();
        this.URL = delHTMLTag(URL);
        this.env = env;
        this.type = type;
        this.autoSetBaseUrl();
        __count_object_++;
        AUTO_GET_BASE_URL();//根据配置文件自动获取IP/URL
        if(URL.startsWith("ws://")){
            buildConnectWs(this.URL);
        }

    }
    public void buildConnectWs(String url) {

        try {
            this.wsclient = new WebSocketClient(new URI(url)) {
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
        wsclient.connect();
    }

    public void sleep(int mis){
        try {
            Thread.sleep(mis);
        } catch (InterruptedException e) {
            HttpLog.info(e.getMessage());
        }
    }
    public void sleep(String mis){
        sleep(Integer.parseInt(mis));
    }
    public void sleep(){
        sleep(50);
    }

    /**
     * only for ws://localhost,默认等待250ms
     * @param string
     * @return
     */
    public boolean send(String string) {
        //headers	{author:"haolie"}
        int timeoutms=250;
        if(Store.get(SOCKET_TIMEOUT)!=null){
            timeoutms=Integer.parseInt(Store.get(SOCKET_TIMEOUT)+"");
        }
        for(int i=0;i<5;i++){
            if(wsclient!=null&&!wsclient.isOpen()){
                try {
                    Thread.sleep(timeoutms/5);
                } catch (InterruptedException e) {
                    HttpLog.info(e.getMessage());
                }
            }
        }
        HttpLog.info("Send:"+string);
        responseBody=null;
        if(wsclient==null) throw new RuntimeException("Connect Server fail");

//        if(!wsclient.isOpen()){
//            throw new RuntimeException("!Connect Closed!");
//        }
        wsclient.getConnection().send(string);
        for(int i=0;i<5;i++){
            if(responseBody==null){
                try {
                    Thread.sleep(timeoutms/5);
                } catch (InterruptedException e) {
                    HttpLog.info(e.getMessage());
                }
            }
        }
        return true;
    }



    public String value() {
        return responseBody;
    }
    public boolean post(String url){return  true;}

    public boolean post() {
        jsonUtil.parseJson( requestForJSON(BASE_URL + URL));
        return true;
       // return "message:<<OK>>";
    }
    public boolean delete(){
        this.type="DELETE";
        jsonUtil.parseJson(requestForJSON(BASE_URL+URL));
        return true;
    }
    public boolean get(){
        this.type="GET";
        jsonUtil.parseJson(requestForJSON(BASE_URL+URL));
        return true;
    }

    /**
     *注:默认删除 html 标记
     */
    public boolean setBody(String bodyString) {
        bodyString=delHtmlPre(bodyString);
        if(isGson(bodyString)){
            this.requestBody=GsonJsonUtil.gson.toJson(gsonParse(bodyString));
        }else{
            this.requestBody=bodyString;
        }
        return true;
    }

    /**
     * 判断是不是一个普通json,并可以用Gson 来解析
     * @return
     */
    boolean isGson(String maybeJson){
        Object ob=gsonParse(maybeJson);
        if(ob==null)return false;
        return true;
    }
    Object gsonParse(String mayBeJson){
        try{
            Map map= GsonJsonUtil.gson.fromJson(mayBeJson,Map.class);
            return map;
        }catch (Exception e){
            try{
                List list= GsonJsonUtil.gson.fromJson(mayBeJson,List.class);
                return list;
            }catch (Exception e2){
                System.out.println("debug:> json parse ERROR (语法错误或可能多余的逗号这些)");
                return null;
            }
        }
    }

    /**
     * 生成一个随机数
     * @return
     */
    public String uuid(){
         return UUID.randomUUID().toString();
    }

    /**
     * 生成一个时间线 ID 毫秒为结果
     * 如: 2017-01-01 12:12:00=>2017.01.01.12.12.00.000
     * 带点
     * @return
     */
    public String tid(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.sss");
        return   sf.format(new Date() );
    }
    public String tid(String mat){
        SimpleDateFormat sf=new SimpleDateFormat(mat);
        return   sf.format(new Date() );
    }

    /**
     * 支持书写 js 转换
     * @param bodyString 脚本
     * @param type 实现有 js,json,groovy
     * @return
     */
    public boolean setBody(String bodyString,String type){
        if("raw".equals(type)){
            this.requestBody=bodyString;
        }else {
            this.requestBody=ScriptUtil.buildScript(delHtmlPre(bodyString), type);
        }
        return true;
    }


    /**
     * 设置http请求参数和 URL 参数
     * @param name 名称
     * @param value 值
     * @return
     * @throws Exception
     */
    public boolean setParam(String name, String value){
        requestMap.put(name, value);
        return true;
    }
    public boolean setParam(String nameValue){
        if(nameValue!=null ) {
            if(  nameValue.contains("=")){
                requestHeaderMap.put(nameValue.split("=")[0], nameValue.split("=")[1]);
            }else if( nameValue.contains(":")){
                requestHeaderMap.put(nameValue.split(":")[0], nameValue.split(":")[1]);
            }
        }
        return false;
    }
    public boolean setHeader(String name, String value) {
        requestHeaderMap.put(name, value);
        return true;
    }

    /**
     * 允许使用 a=b和a:b形式的字符串.
     * @param nameValue
     * @return
     */
    public boolean setHeader(String nameValue) {
        if(nameValue!=null ){
            if(  nameValue.contains("=")){
                requestHeaderMap.put(nameValue.split("=")[0], nameValue.split("=")[1]);
            }else if( nameValue.contains(":")){
                requestHeaderMap.put(nameValue.split(":")[0], nameValue.split(":")[1]);
            }
            return true;
        }
        return false;
    }


    public String requestForString(String fullUrl) {
        if (type == null) {
            type = "POST";
        }
        //唯一 key,用于接口统计.
        String keyUrl=type+":"+fullUrl;
        fullUrl=urlParamMatcher(fullUrl, buildFromByMap(requestMap));
        logger.info("---------------------------------------------------");
        logger.info("Address:  "+this.type+"  "+fullUrl);

        if("GET".equals(type)){
            Get get=Http.get(fullUrl);
            fillRequestHeader(get,requestHeaderMap);
            responseBody= get.text();
            responseHeaderMap=get.headers();
            responseCode=get.responseCode();
            logger.info("Response:  "+responseBody);
        }
        if("POST".equals(type)){
            Post post = Http.post(fullUrl, requestBody);
            fillRequestHeader(post,requestHeaderMap);
            responseBody= post.text();
            responseHeaderMap=post.headers();
            responseCode=post.responseCode();
            logger.info("Request:   "+requestBody);
            logger.info("Response:  "+responseBody);
        }
        if("DELETE".equals(type)){
            Delete delete=Http.delete(fullUrl);
            fillRequestHeader(delete,requestHeaderMap);
            responseBody= delete.text();
            responseHeaderMap=delete.headers();
            responseCode=delete.responseCode();
            logger.info("Response:  "+responseBody);
        }
        //_URL_COUNT_.put(this.URL,responseCode);
        addOneUrlCount(keyUrl);
        return responseBody;
    }
    /**
     * 填充请求 header,包括全局 header
    * */
    public void fillRequestHeader(Request request,Map<String,String> headerMap){
        if(Store.get(GLOBAL_HEADERS_KEY)!=null){
            Map<String,String> headersMap= GsonJsonUtil.gson.fromJson((String)Store.get("headers"),Map.class);

            for(String key:headersMap.keySet()){
                request.header(key,headersMap.get(key));
                logger.info("Header:   "+key+":"+headersMap.get(key));

            }
        }

        if(headerMap!=null){
            for(String key:headerMap.keySet()){
                request.header(key,headerMap.get(key));
                logger.info("Header:   "+key+":"+headerMap.get(key));

            }
        }
    }

    /**
     * 获取返回结果中的json结果
     * @return string Value
     */
    public String jsonValue(String key){
        try {
            return jsonUtil.getResult(key);
        }catch (Exception e){
            throw new RuntimeException("message:<< response is not json "+responseBody+">>");
        }
    }

    public boolean jsonLike(String json){
        return true;
    }
    public Boolean jsonContain(String json){
        json=delHtmlPre(json);
        ScriptUtil.preLoadCompileJs();
        ScriptUtil.runJavaScript("response=null");
        ScriptUtil.runJavaScript("response="+responseBody);
        //ScriptUtil.binding(responseBody,"response"); //绑定为直接的字符串
        //ScriptUtil.runJavaScript("response=JSON.parse(response)");
        Boolean out=  (Boolean) ScriptUtil.runJavaScript("CONTAIN(response,"+json+")");
        if(out!=true){
            HttpLog.info(jsonCompare(responseBody,json));
        }
        return out;
    }
    public boolean jsonMatch(String key,String matchString){
        String ori= jsonUtil.getResult(key);
        Pattern pattern = Pattern.compile(matchString);
        // 忽略大小写的写法
         Matcher matcher = pattern.matcher(ori);
        // 查找字符串中是否有匹配正则表达式的字符/字符串
         return  matcher.find();

    }
    /**
     给出对比分析结果
     */
    public Object jsonCompare(String json){
        json=delHtmlPre(json);
        return jsonCompare(responseBody,json);
    }

    /**
     * 生成字符串的对比结果,
     * 只对比多行文本,以行为比较结果
     * @param big
     * @param target
     * @return
     */
    public static String jsonCompare(String big, String target){

        big=gsonPretty.toJson(  gsonPretty.fromJson(big,Map.class));
        target=gsonPretty.toJson(  gsonPretty.fromJson(target,Map.class));
        String[] lines=target.split("\n");
        String[] lineNotEqual=new String[lines.length];
        String[] linesBig=big.split("\n");

        String outMessage="";
        for(String line :lines){
            String msg="";
            if(line.contains("\":")){
               if(big.contains(line.trim())){
                   //ok
               }else{
                   double similarity=0;
                   for(String oriLines:linesBig){
                       String key= line.split(":")[0];
                       if(oriLines.contains("\":") && oriLines.contains(key)){
                           double csi= StringSimilarity.SimilarDegree(oriLines.trim(),line.trim());//想以度
                           if(csi>similarity){
                               msg=line.trim()+" <--> "+oriLines.trim()+"\n";
                               similarity=csi;
                           }

                       }
                   }
               }
            }
            outMessage+=msg;
        }
        return outMessage;
    }
    public boolean textContain(String target){
        return  responseBody.toLowerCase().contains(target.toLowerCase());
    }
    public  Object javaScript(String js){
        js=delHtmlPre(js);
        ScriptUtil.preLoadCompileJs();
        ScriptUtil.runJavaScript("response=null");
        ScriptUtil.runJavaScript("response="+responseBody);
        ScriptUtil.runJavaScript("request=null");
        ScriptUtil.runJavaScript("request="+requestBody);
        return ScriptUtil.runJavaScript(js);
    }

    /**
     * 判断JSON结构内含相似性
     * 如 {a:1,b:2} +s> {a:0,b:0}
     * @return
     */
    public boolean jsonSimilar(String json){
        json=delHtmlPre(json);
        ScriptUtil.preLoadCompileJs();
        ScriptUtil.runJavaScript("response=null");
        ScriptUtil.runJavaScript("response="+responseBody);
        return (Boolean) ScriptUtil.runJavaScript("SIMILAR(response,"+json+")");
    }
  /*  public Object groovyScript(String groovyString) {
        groovyString=delHtmlPre(groovyString);
        Binding sharedData = new Binding();
        Object object =new JsonSlurper().parseText(responseBody);
        sharedData.setProperty("response",object);
        GroovyShell shell = new GroovyShell()  ;
        shell.setProperty("response",object);
        Object result2 = shell.evaluate(groovyString)  ;

        return result2;
    }*/



    public JSONObject requestForJSON(String fullurl) {
        if (type == null) {
            type = "POST";
        }

        if(requestHeaderMap.get("Content-Type")==null){
            requestHeaderMap.put("Content-Type", "application/json;charset=UTF-8");
        }
        requestForString(fullurl);
        if(isGson(responseBody)){
            JSONObject objResponse = new JSONObject(responseBody);
            return objResponse;
        }
        return new JSONObject("{}");
    }

    public String requestForXML(String fullurl) {
        if (type == null) {
            type = "POST";
        }
        return requestForString(fullurl);
    }



    public boolean jsonStructure(String str){
        return jsonUtil.checkParam(str);
    }

    public String responseHeader(String responseHeaderKey) {
        return "";
    }


    public void setType(String type) {
        this.type = type;
    }

    /**
     * author: aerchi@gmail.com
     */

    public static String UpStr(String str) {
        if (str == null || str.equals("")) return "";
        return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
    }

    /**
     * 根据环境清单自动得到 IP 地址
     */
    public void autoGetEnvirementIPUrl() {

    }

    /**
     * 根据服务名称自动从配置文件中获取BASE_URL
     * 如 自动获取为
     * 测试环境为：
     * Sim 环境为：
     * (环境名首字母大写);
     */
    public void AUTO_GET_BASE_URL() {
        Class clazz = null;
        this.env=getEnv();

        try {
            if (env == null || env.equals("dev") || env.equals(""))
                clazz = ConfigConstantsTest.class;
            else
                clazz = Class.forName("com.qa.constants." + "ConfigConstants" + UpStr(env));
            String currName = this.getClass().getSimpleName();
            // String al
            Field[] allfield = clazz.getFields();//
            for (Field tmp : allfield) {
                String oriName = tmp.getName().replace("_", "");
                String oriName2 = (tmp.getName() + "AppServer").replace("_", "");
                String toName = currName + "BASEURL";
                if (oriName.toLowerCase().equals(toName.toLowerCase())
                        || oriName2.toLowerCase().equals(toName.toLowerCase())
                        ) {
                    String base_url = (String) tmp.get(null);//静态变量,直接获取
                    this.BASE_URL = base_url;
                }
            }
        } catch (ClassNotFoundException e) {

            ExceptionUtil.printlnSo(e);
        } catch (IllegalAccessException e) {
            ExceptionUtil.printlnSo(e);
        }
    }

    /**
     * 根据环境信息自动设置 URL 前缀
     * 如  BAIDU/api.rest=> http://baidu.com/api.rest
     */
    public void autoSetBaseUrl(){
        String httpIpPort=subHttpIpPort(this.URL);
        if(isNotEmpty( httpIpPort)) {
            this.BASE_URL = Store.getValueSibling(httpIpPort, Store.getEnv());
            this.URL=this.URL.replace(httpIpPort,"");
        }
    }
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 保存数据结构
     * 用法在于约定.例如: header:{author:haolie}
     * @param key
     * @param data
     * @return
     */
    public static boolean store(String key,String data){
        return true;
    }

    /**
     * 从 URL 中提取 IP:端口
     * TODO
     * @param url
     * @return
     */
    public static String subHttpIpPort(String url){

        if(url!=null){
            if(url.startsWith("http")){
                return url;
            }else{
                String[] tmp1= url.split("http://");
                String  tmpString=tmp1.length>1?tmp1[1]:tmp1[0];
                String[] tmp2= tmpString.split("/");
                return "http://"+tmp2[0];
            }
        }
        return "";
    }

    /**
     * 只删除少量的 Html,如前缀和后缀,只去除最外层一个标签标签
     * @param maybeHtml 可能为html的文本
     * @return
     */
    public static String delSimpleHtmlTag(String maybeHtml){
         return maybeHtml;
         //TODO
    }
    public static String delHtmlPre(String htmlStr){
        return unescapeHtml(htmlStr.trim().replace("<pre>","").replace("</pre>","").trim());
    }

    /**
     * 去除 html 中所有的标签和脚本,只保留文本
     * @param htmlString 源文本
     * @return 文本
     */
    public static String delHTMLTag(String htmlString) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlString);
        htmlString = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlString);
        htmlString = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlString);
        htmlString = m_html.replaceAll(""); //过滤html标签

        return htmlString.trim(); //返回文本字符串
    }

    /**
     *     统计 URL 访问次数

     */
    public static Map<String,Integer> _URL_COUNT_ =new HashMap();

    /**
     * 添加统计数据,增加一个值
     */
    static public void  addOneUrlCount( String url){
        url=url.split("\\?")[0];
        Integer i= _URL_COUNT_.get(url);
        if(i==null) i=0;
        //分离 GET:http://cw.lieluobo.testing/api/project/subscriber/cw/list?a=b 不允许带get参数做统计.
        _URL_COUNT_.put(url ,i+1);
    }

    /**
     * 添加静态代码,处理退出钩子
     */
    static{
      //  addShutdownHook();
    }
    static  boolean ADD_SHUTDOWN_HOOK_DOWN=false;
    synchronized static void addShutdownHook(){
        if(ADD_SHUTDOWN_HOOK_DOWN==false){
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    HttpLog.info( report());
                }
            });
            ADD_SHUTDOWN_HOOK_DOWN=true;
        }
    }


    /**
     * 显示数据报告,这里只统计 URL 次数
     *
     * @return
     */
    public static  String report(){
        urlMapMerge(_URL_COUNT_);
        _URL_COUNT_=sortMap(_URL_COUNT_);
        String msg="请求所有的URL总个数为:"+ _URL_COUNT_.size()+"\n";
        msg+="HTTP请求总次数:"+__count_object_+"\n";
        msg+="状态码次数分类:"+0+"\n";
        String msgb="";
        Map<String,Integer> zoneMap=new HashMap();//  http://cw.请求合计
        Map<String,Integer> zoneCount=new HashMap();//  http://cw. url合计

        Pattern pattern = Pattern.compile("(http://\\w+\\.)");

        int countTotal=0;
        for(String key: _URL_COUNT_.keySet()){
            Matcher m= pattern.matcher(key);
            if(m.find()){
              String zoneKey= m.group(0);
              Integer n=zoneMap.get(zoneKey);
              if(n==null) n=0;
              zoneMap.put(zoneKey,n+_URL_COUNT_.get(key));
              Integer c=zoneCount.get(zoneKey);
              if(c==null) c=0;
              zoneCount.put(zoneKey,c+1);
            }
            countTotal++;
            msgb+=key+" "+ _URL_COUNT_.get(key)+"\n";
        }
        for(String key:zoneMap.keySet()){
            msg+=key+" :"+zoneCount.get(key)+"/"+zoneMap.get(key)+"(个数/次数)\n";
        }
        msg+="total:"+countTotal+"\n";
        return  msgb+msg;
    }

    /**
     * 按key排序
     * @param myMapTmp
     * @return
     */
    public static Map sortMap(Map myMapTmp){
        Map<String,Object> myMap = new LinkedHashMap();
        List<String> keyList = new ArrayList();
        Iterator<String> it =myMapTmp.keySet().iterator();
        while(it.hasNext()){
            keyList.add(it.next());
        }
        Collections.sort(keyList);
        Iterator<String> it2 = keyList.iterator();
        while(it2.hasNext()){
            String key = it2.next();
            myMap.put(key, myMapTmp.get(key));
        }
        return myMap;
    }

    /**
     *合并url的统计数据
     * 如:
     DELETE:http://www.lieluobo.testing/api/resume/groups/3725
     GET:http://cw.lieluobo.testing/api/project/subscriber/cw/list?a=b
     GET:http://cw.lieluobo.testing/api/project/subscriber/cw/list?c=d
     DELETE:http://www.lieluobo.testing/api/resume/groups/3726
     合并为2条
     再只针对数字.
     * @param map
     */
    public static void urlMapMerge(Map<String,Integer> map){
        String regEx = "/\\d+";
        List<String> removeList=new ArrayList();
        Map<String,Integer> newMap=new HashMap();
        Pattern pattern = Pattern.compile(regEx);// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        for(String key:map.keySet()){
            Matcher matcher = pattern.matcher(key);
            if(matcher.find()){
                String newKey=key.replaceAll(regEx,"/{id}");
                Integer i=map.get(key);
                Integer j=newMap.get(newKey);
                if(i==null) i=0;
                if(j==null) j=0;
                newMap.put( newKey,i+j+1);//
                removeList.add(key);
            }else{

            }

        }
        map.putAll(newMap);
        for(String removeKey:removeList){
            map.remove(removeKey);
        }

    }
    public static String unescapeHtml(String fString){
        fString = fString.replace("&gt;", ">");
        fString = fString.replace( "&lt;", "<");
        fString = fString.replace("&nbsp;",String.valueOf(32));
        fString = fString.replace("&quot;",String.valueOf(34));
        fString = fString.replace("&#39;",String.valueOf(39));
        fString = fString.replace( "<BR>", String.valueOf(10));
        return  fString;
    }
    public void setURL(String URL) {
        this.URL = URL;
    }
    public int httpCode(){
        return responseCode;
    }
    public int getResponseCode() {
        return responseCode;
    }
    public String getResponseBody() {
        return responseBody;
    }


    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * URL统计不能带参数,但是要带方法
     * @param oraURL
     */
    public String buildURLKey(String oraURL){
        return "";
    }

}
