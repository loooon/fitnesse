package com.qa.db;

import com.qa.utils.ExceptionUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class SelectWait extends Select {
    private   int timeout=SetUp.DEFAULT_WAIT_TIMEOUT;


    /**
     * 等到数据可以查询到才返回
     * @param sql
     * @param timeout 毫秒
     */
    public SelectWait(String sql, int timeout) {
        super();

        sql = sql.replaceAll("\\n", " ");
        sql = sql.replaceAll("\\t", " ");
        sql = sql.replaceAll("<br/>", " ");
        sql = sql.trim();
        this.timeout=timeout;
        this.waitForRowCount(sql);
    }
    public SelectWait(String sql) {
        sql = sql.replaceAll("\\n", " ");
        sql = sql.replaceAll("\\t", " ");
        sql = sql.replaceAll("<br/>", " ");
        sql = sql.trim();
        this.waitForRowCount(sql);
    }

    private void waitForRowCount(String sql) {

        int rowCount = 0;
        long now=System.currentTimeMillis();
        long endTime=now+timeout;

        while (rowCount == 0 && ((System.currentTimeMillis()<= endTime))) {
            rowCount = getDataTable(sql).size();

            if (rowCount > 0) {
                return;
            }else{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    ExceptionUtil.printlnSo(e);
                }
            }
        }
        throw new RuntimeException("message:<<wait for row count could not be completed within " + timeout + "ms>>");
    }
}
