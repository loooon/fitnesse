package com.qa.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class Update extends SQL {



    public Update(String sql) {
        super(sql);
    }

    public Update(String tableName,String values,String condition){
        String sql="update "+tableName+" set "+values+" where "+condition;

        sql = sql.replaceAll("\\n", " ");
        sql = sql.replaceAll("\\t", " ");
        sql = sql.replaceAll("<br/>", " ");
        sql = sql.trim();
       _count= SetUp.runStandSql(sql);
    }

}
