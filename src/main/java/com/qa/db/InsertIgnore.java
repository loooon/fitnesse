package com.qa.db;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class InsertIgnore extends Insert{

    public InsertIgnore(String sql) {
        super();
        _count=SetUp.runStandSqlWithOutException(sql,true);
    }

    public InsertIgnore(String tableName, String kv) {
        super();
        String sql="insert into "+tableName+mapToSql(  kvToMap(kv));
        _count=SetUp.runStandSqlWithOutException(sql,true);
    }
}
