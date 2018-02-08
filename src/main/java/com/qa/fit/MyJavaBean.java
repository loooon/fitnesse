package com.qa.fit;

/**
 * IAT
 * Created by yu on 2017/5/20.
 */
public class MyJavaBean {
    int number;
    String name;
    public boolean setNumber(int num){
        this.number=num;
        //DO something important
        return true;
    }
    public boolean setName(String name){
        this.name=name;
        //DO something important
        return true;
    }
    public String getName(){
        return this.name+this.number;
    }
}
