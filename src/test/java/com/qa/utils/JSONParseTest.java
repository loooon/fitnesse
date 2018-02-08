package com.qa.utils;


import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * IAT @wkzf
 * Created by yu on 2017/7/12.
 */
public class JSONParseTest {
    @Test
    public void jsonOnArrayFind() throws Exception {


       assert "1088".equals(  JsonUtil.jsonOnArrayFind(buildMap(),".body\\[.*\\].updatedBy"));

    }
    public Map buildMap(){
        Map<String,String> map=new HashMap();

        map.put(".body[0].name",	"好猎网");
        map.put(".body[0].id","	1");
        map.put(".body[0].updatedAt","	2017-06-16T17\",\"16\",\"34+08\",\"00");
        map.put(".body[1].updatedBy","1088");
        map.put(".body[1].level","	1");
        map.put(".body[1].isDelete","	0");
        map.put(".body[1].teamMemberCount","");
        return map;
    }
    @Test
    public void jsonOnArrayFindR() throws Exception {


        assert "1088".equals(  JsonUtil.jsonOnArrayFindR(buildMap(),".body[*].updatedBy"));

    }
    @Test
    public void subPointStringTest(){
        assert "abc".equals( JsonUtil.subPointString(".abc") );
        assert "abc".equals( JsonUtil.subPointString("abc") );
        assert "abc".equals( JsonUtil.subPointString("abc") );
        assert "a".equals( JsonUtil.subPointString(".a") );
        assert "a".equals( JsonUtil.subPointString("a") );

    }
}