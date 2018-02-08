package com.qa.fit;

import com.qa.utils.ExceptionUtil;

import java.lang.reflect.Method;

/**
 * Created by yu on 16/8/30.
 */
public class Rc {

    public Rc(){

    }

    /**
     * 注册方法
     * @param name
     * @param me
     */
    public void reg(String name,String me){
        Class clazz = null;
        try {
            clazz = Class.forName("");
        } catch (ClassNotFoundException e) {
            ExceptionUtil.printlnSo(e);
        }
        Method m1 = null;
        try {
            m1 = clazz.getDeclaredMethod("outInfo");
        } catch (NoSuchMethodException e) {
            ExceptionUtil.printlnSo(e);
        }
        Method m2 = null;
        try {
            m2 = clazz.getDeclaredMethod("setMsg", String.class);
            Method m3 = clazz.getDeclaredMethod("getMsg");
        } catch (NoSuchMethodException e) {
            ExceptionUtil.printlnSo(e);
        }

//        m1.invoke(foo);
//        m2.invoke(foo, "重新设置msg信息！");
    }
}
