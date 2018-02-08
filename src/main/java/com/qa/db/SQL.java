package com.qa.db;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class SQL {
    public SQL() {
    }

    int _count=0;
    public SQL(String sql){
        sql = sql.replaceAll("\\n", " ");
        sql = sql.replaceAll("\\t", " ");
        sql = sql.replaceAll("<br/>", " ");
        sql = sql.trim();
        _count=SetUp.runStandSql(sql);
    }

    public int count(){
        return _count;
    }

}
