package com.qa;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * fitnesse_toc
 * Created by yu on 2017/8/10.
 */
public class JavaTest {
    @Test
    public void run() throws Exception {
      new   Java().run("System.out.print('A')");
        new   Java("System.out.print('A')");
    }


   // @org.testng.annotations.Test
    public void runTS(){
        System.out.println( new FileRunner().preFormat("" +
                "|script    |Connect Server|https://test.51tniu.com/tniu/stockBanker/stockBankerUserListV2|\n" +
                "|set Header|auth_token    |               |3b34bc5dc1074d6a9d7977eb70300e4b              |\n" +
                "|set Body                                                                                |{{{\n" +
                "{\n" +
                "\"mainId\":5120, \n" +
                "\"offset\":0,\n" +
                "\"limit\":10,\n" +
                "\"forward\":0\n" +
                "}\n" +
                "}}}|\n" +
                "|post              |\n" +
                "|check|json Contain|{{{\n" +
                "{\n" +
                "             \"uid\": 1002443,\n" +
                "            \"nickname\": \"Tryuu\",\n" +
                "           \n" +
                "            \"summary\": 10000,\n" +
                "            \"guessAction\": 0,\n" +
                "            \"forward\": 0,\n" +
                "            \"joinTime\": 1515568840,\n" +
                "            \"resultNum\": 0,\n" +
                "            \"result\": 0\n" +
                "}\n" +
                "}}}|true|\n" +
                "|check|json Structure|status,data,msg|true   |\n" +
                "|check|json Value    |status         |0      |\n" +
                "|check|json Value    |msg            |success|"));

    }
   //@org.testng.annotations.Test
    public void runnn(){

        ConnectServer       object=new ConnectServer("https://test.51tniu.com/tniu/stockBanker/stockBankerUserListV2");
        object.setHeader("auth_token","3b34bc5dc1074d6a9d7977eb70300e4b");
        object.setBody("{\"mainId\":5120,\"offset\":0,\"limit\":10,\"forward\":0}");
        object.post();
    }
}