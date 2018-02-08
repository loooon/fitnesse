package com.qa.db;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/20.
 */
public class SetUpTest {
    //@Test
    public void getConnection() throws Exception {

         new SetUp("com.mysql.jdbc.Driver","jdbc:mysql://172.16.52.81:3306/testing?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false",
                "testing",
                "haolie123");

       //  Thread.sleep(100);

       new  Select("	select * from account");

    }

}