package com.qa;

import java.util.HashMap;
import java.util.Map;

/**
 * IAT
 * Created by yu on 2017/5/20.
 * 这个类专门用来收集常量设置
 * 以后常量类信息不用放在代码中,
 * 直接写在测试用例中
 */
public class Store {
    public static String ENV_KEY ="test.runtime.env";
    public static String GLOBAL_HEADERS_KEY="headers";
    public static String SOCKET_TIMEOUT="SocketTimeout";

    public Store(String key, String value) {
        put(key,value);
    }
    public static void put(String key,String value){
        Map localVar= envMap.get(getEnv());
        if(localVar==null){
            localVar=new HashMap();
            envMap.put(getEnv(),localVar);
            localVar.put(key,value);
            System.setProperty(key,value);
        }else{
            localVar.put(key,value);
        }
    }

    public Store() {
    }

    /**
     * 一个参数时是设置环境变量
     * @param env
     */
    public Store(String env) {
        setEnv(env);
    }

    public static String getEnv() {
        String s=System.getProperty(ENV_KEY);
        if(s==null)
            return "dev";
        return s;
    }

    public static void setEnv(String envValue) {
        System.setProperty(ENV_KEY,envValue);
    }


    /**
     * 常量如果需要分类,使用第3个参数
     * @param key
     * @param value
     * @param env
     */
    public Store(String key, String value, String env){
        put(key,value,env);
    }
    public static void put(String key,String value,String env){
        Map localVar=envMap.get(env);
        if(localVar==null){
            localVar=new HashMap();
            localVar.put(key,value);
            envMap.put(env,localVar);
        }else{
            localVar.put(key,value);

        }
    }
    public long date(){
        return System.currentTimeMillis();
    }

    static Map<String,Map<String,String>> envMap=new HashMap();

    /**
     * 获取环境下的配置变量
     * @param env
     * @param key
     * @return
     */
    public static String getConstants(String env,String key){
        if(env==null || env.equals("")) env=getEnv();
        if(key!=null){
            Map<String,String> localVar=envMap.get(env);
            if(localVar!=null) return localVar.get(key);
        }
        return "";
    }

    /**
     * 通过env得到相依的值
     * 如 env-key-vale
     *    dev a-->b
     *    test a->c
     *    通过 c得到b
     * @param value 原值
     * @param targetEnv 目标环境
     * @return
     */
    public static String getValueSibling(String value, String targetEnv){
        if(value==null) return "";
        Map<String,String> targetMap=envMap.get(targetEnv);
        for(Map<String,String> tmpMap:envMap.values()){
            if(tmpMap!=targetMap){
                for(String key:tmpMap.keySet()){
                    if(value.equals(tmpMap.get(key))){
                        //找到 key a
                        return targetMap.get(key);
                    }
                }
            }
        }
        return value;
    }

    /**
     * 获取当前环境下Key值对应的Value
     */
    static public Object get(String key) {


        if (envMap.get(getEnv()) == null)
            return null;
        return envMap.get(getEnv()).get(key);
    }

    static public void waitOneSecend(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
