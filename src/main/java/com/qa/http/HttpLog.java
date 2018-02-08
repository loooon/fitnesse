package com.qa.http;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/20.
 */
public class HttpLog{

    public static HttpLog getLogger(){
        return new HttpLog();
    }
    static SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss.SSS");

    public static  void info(){
        System.out.printf("%tF %<tT.%<tL",new Date());
    }
    public static void info(String str){
        String out=format.format(new Date());

        //System.out.printf("%tF %<tT.%<tL "+str+"\n",new Date());
        System.out.println(out+" "+str);
    }

}
