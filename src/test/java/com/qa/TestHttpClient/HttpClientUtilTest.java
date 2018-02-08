package com.qa.TestHttpClient;

import com.qa.utils.HttpClientUtil;

import static com.qa.utils.StringURLUtil.urlParamMatcher;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/13.
 */
public class HttpClientUtilTest {

    @org.testng.annotations.Test
    private void urlParamMatcherTest(){

        assert "/abc/1234/id?cc=bb" .equals(urlParamMatcher("/abc/1234/id","cc=bb"));

        assert "/abc/1234/id?cc=bb" .equals(urlParamMatcher("/abc/{abc}/id","abc=1234&cc=bb"));
        assert "/abc/abc/id" .equals(urlParamMatcher("/abc/abc/id",""));


    }
}