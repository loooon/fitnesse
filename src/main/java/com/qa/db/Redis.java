package com.qa.db;

/**
 * fitnesse_toc
 * Created by yu on 2017/8/11.
 */


import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;


public class Redis {
    public static String host = "127.0.0.1";
    public static String auth="";
    public static int port = 6379;
    public static Jedis jedis = null;


    public Redis() {
    }

    public Redis(String host, int port,String auth) {
        init(host,port,auth);
    }
    public void init(String host, int port,String auth){
        String oKey=host+port+auth;
        String nKey=this.host+this.port+this.auth;
        if(oKey.equals(nKey)){

        }else{
            jedis=new Jedis(host,port);
            jedis.auth(auth);
            this.host=host;
            this.port=port;
            this.auth=auth;
        }
    }
    public String get(String key){
         String value = jedis.get(key); //访问Redis服务
         return  value;
    }

}
