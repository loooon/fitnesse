package com.qa.db;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * fitnesse_toc
 * Created by yu on 2017/8/11.
 */
public class RedisTest {
   // @Test
    public void get() throws Exception {

        Redis r = new Redis("172.16.52.99", 6379, "123456");
        long sTime=System.currentTimeMillis();
        String ou = r.get("img_verify_code_8c6a9f51b62b42b89b7bf77fa48144c4");
        long eTime=System.currentTimeMillis();
        System.out.println(ou+" " +(eTime-sTime));


        ou = r.get("img_verify_code_8c6a9f51b62b42b89b7bf77fa48144c4");
        eTime=System.currentTimeMillis();
        System.out.println(ou+" " +(eTime-sTime));



        ou = r.get("img_verify_code_8c6a9f51b62b42b89b7bf77fa48144c4");
        eTime=System.currentTimeMillis();
        System.out.println(ou+" " +(eTime-sTime));

    }

}