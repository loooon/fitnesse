package com.qa.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class Delete  extends SQL{


    public  Delete(String tableName,String condition){
        String sql="delete from "+tableName+" where "+condition;
        sql = sql.replaceAll("\\n", " ");
        sql = sql.replaceAll("\\t", " ");
        sql = sql.replaceAll("<br/>", " ");
        sql = sql.trim();
        _count=SetUp.runStandSql(sql);
    }
    public  Delete(String sql){
        super(sql);
    }

}
