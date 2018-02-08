package com.qa.fit;

import com.qa.utils.ExceptionUtil;

/**
 * Created by yu on 16/8/25.
 */
public class ComClassB {

    public boolean aa1(){
        try {
            Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {
            ExceptionUtil.printlnSo(e);
        }
        return true;
    }

    public boolean bb1(){
        try {
            Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {
            ExceptionUtil.printlnSo(e);
        }
        return true;
    }

    public boolean cc1(){
        return true;
    }

    public boolean dd1(){
        return true;
    }

    public boolean ee1(){
        return true;
    }
}
