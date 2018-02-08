package com.qa.db;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qa.utils.GsonJsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class Insert extends SQL {
    public Insert() {
    }

    public Insert(String sql){
        super(sql);
    }
    public Insert(String tableName,String kv){
       String sql="insert into "+tableName+mapToSql(  kvToMap(kv));
        _count=SetUp.runStandSql(sql);
    }

    /**
     * 把 map 对象转换成一个 sql 插入语句,再只支持 mysql
     * 如
     * {leaderId=204634.0, containSubTeam=true, containSubStaff=true}
     *  ==>
     *  ( leaderId, containSubTeam, containSubStaff) values('204634.0','true','true')
     * @param map
     * @return
     */
    public static String mapToSql(Map map){
        StringBuilder sbKey = new StringBuilder();
        StringBuilder sbValue = new StringBuilder();

        for(Object k : map.keySet()){
            sbKey.append(" ");
            sbKey.append(k+",");
            sbValue.append("'"+map.get(k)+"'"+",");
        }
        String sql ="(" +sbKey.substring(0,sbKey.length()-1)+") values("+sbValue.substring(0,sbValue.length()-1)+")";
        return sql;
    }

    public static Map  kvToMap(String kv){

        Gson gson= GsonJsonUtil.gsonLower;
       return gson.fromJson(kv,Map.class);

    }


}
