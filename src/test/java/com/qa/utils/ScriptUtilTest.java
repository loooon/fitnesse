package com.qa.utils;

import com.qa.ConnectServer;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/13.
 */
public class ScriptUtilTest {
    @Test
    public void beanShell() throws Exception {
        long sTime=System.currentTimeMillis();
        ScriptUtil.beanShell("System.out.println(\"a,b\")");
        long eTime=System.currentTimeMillis();
        System.out.println(eTime-sTime);
    }

    @Test
    public void beanShellAc(){
        ScriptUtil.beanShell("com.qa.Command('ls');");
    }

    @Test
    public void preLoadCompileJs() throws Exception {
        ScriptUtil.preLoadCompileJs();
        assert (Boolean) ScriptUtil.runJavaScript("CONTAIN({\"page\":1,\"size\":10},{\"page\":1,\"size\":10})");
        assert !(Boolean) ScriptUtil.runJavaScript("CONTAIN({\"page\":1,\"size\":10},{\"page\":1,\"size\":11})");
        assert (Boolean) ScriptUtil.runJavaScript("CONTAIN({\"code\":1,\"msg\":\"OK\",\"body\":{\"industryList\":[{\"code\":1,\"amount\":1},{\"code\":18,\"amount\":2},{\"code\":56,\"amount\":1}],\"locationList\":[{\"code\":110000,\"amount\":1},{\"code\":310000,\"amount\":1}]}}\n,{\"code\":110000,\"amount\":1})");
    }


    @Test
    public void isJson() throws Exception {
        assert ScriptUtil.isJson("{}");
        assert ScriptUtil.isJson("{}");
        assert ScriptUtil.isJson("{\"code\":401,\"msg\":\"未登录\"}");

    }
    @Test
    public void runGroovyScript(){
      //  ConnectServer.respo
        assert ScriptUtil.runGroovyScript("");

    }
    @Test
    public void isJavascript() throws Exception {
    }
    @Test
    public void undefined(){
      assert  !(Boolean)ScriptUtil.runJavaScript("undefined");
    }
    @Test
    public void runBool(){
        assert  (Boolean) ScriptUtil.runJavaScript("true");
    }
    @Test
    public void runScript(){
        assert  (Boolean) ScriptUtil.runJavaScript("true==true");
    }

    @Test
    public void runScriptBinding(){
        ScriptUtil.binding("string","ss");
        assert  (Boolean) ScriptUtil.runJavaScript("ss==\"string\"");
    }


}