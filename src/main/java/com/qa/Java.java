package com.qa;

import com.qa.utils.ScriptUtil;

/**
 * fitnesse_toc
 * Created by yu on 2017/8/10.
 */
public class Java {
    /**
     * 执行一个java静态方法
     */
    public Java(String staticMethod){
        ScriptUtil.beanShell(staticMethod);
    }
    public Java(){

    }
    public Object run(String script){
        return ScriptUtil.beanShell(script);
    }
}
