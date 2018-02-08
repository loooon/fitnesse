package com.qa;


import com.qa.http.Get;
import com.qa.http.Http;
import fitnesse.fixtures.SystemExitTableConfiguration;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.qa.Store.GLOBAL_HEADERS_KEY;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/13.
 */
public class CITest {
    // @Ignore
    // @Test
    public void st() throws Exception {
        new Store(GLOBAL_HEADERS_KEY, "{authorization:\"e7701df281864c28a58319fa6afce73f\"");
        ConnectServer cs = new ConnectServer("http://cw.lieluobo.testing/api/bd/list/developing");
        cs.setParam("id", "1");
        cs.setBody("  var out={\n" +
                "    staffId: \"204632\",\n" +
                "   // teamId: 1,\n" +
                "    page: 1,\n" +
                "    size: 10\n" +
                "}\n" +
                "out.bb=\"SS\"", "js");
        cs.post();
        assert cs.jsonContain("\t{name:\t\"客户公司-自动化测试预埋数据\",orgBdName:\t\"员工4-HRBD-自动化测试预埋数据\"}\t");
        //  cs.delete();
    }
//
//    @Test
//    public void sss() throws UnirestException {
//        Unirest.get("http://www.baidu.com")
//                .queryString("name", "Mark")
//                .asString();
//
//
//        Get get = Http.get("http://yahoo.com");
//        System.out.println(get.text());
//    }


    //  @Test
    public void ssls() {

//        for(int i=0;i<100;i++){
//            long stime=System.currentTimeMillis();
//
//            Get get = Http.get("https://baidu.com");
//            get.text();
//            long etime=System.currentTimeMillis();
//
//            System.out.println(etime-stime);
//        }
        ConnectServer cs = new ConnectServer("http://cw.lieluobo.testing/api/bd/list/developing");
        cs.setParam("id", "1");
        cs.setParam("id2", "3");
        cs.get();
    }

    @Test
    public void baiduTest() {
        new Store("headers","{auther:\"abc\"}");
        ConnectServer cs = new ConnectServer("http://baidu.com");
        cs.setParam("id", "1");
        cs.setParam("id2", "3");
        cs.get();
    }
    //@Test
    public void sk22(){
        ConnectServer cs = new ConnectServer("http://cw.lieluobo.testing/api/bd/org/101707/visit");
        cs.setHeader("author", "haolie");
        cs.setHeader("authorization", "d0d700fbb71849f3bbd4a2106a8479a0");
        cs.setHeader("channel","crmhr");
        cs.get();
    }

    //@Test
    public void ss(){

        try {
            fitnesse.slim.SlimService.main(new String[]{"1"});
        } catch (IOException e) {
            e.printStackTrace();
        }
        new SystemExitTableConfiguration();
    }
    @Test
    public void memoutTest(){
        //内存溢出测试
        Map map=new HashMap();
        for(int i=0;i<1000000000;i++){
            map.put(i,i);
        }
    }

}
