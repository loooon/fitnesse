package com.qa.load;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * fitnesse_toc
 * Created by yu on 2017/8/9.
 */
public class HttpLoadTest {
    @Test
    public void loadGet() throws Exception {
        new HttpLoad("http://www.baidu.com",10,100,100);
    }

    @Test
    public void mta() throws Exception {
    }

}