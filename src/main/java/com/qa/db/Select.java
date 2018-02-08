package com.qa.db;

import com.google.gson.Gson;
import com.qa.http.HttpLog;
import com.qa.utils.ExceptionUtil;
import com.qa.utils.GsonJsonUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class Select extends SQL{

    Gson gson= GsonJsonUtil.gson;

    /**
     *  标准缓存
     * 多列式多值式直接保存方式
     * [{password: e10adc3949ba59abbe56e057f20f883e}]
     */
    static  ArrayList<Map<String,Object>> dataTable = new ArrayList();


    public Select(String sql) {
      //  List<List<List<String>>> dataTable = getDataTable(sql);
      //  new ArrayList<Object>(dataTable);
        getDataTable(sql);
    }

    public Select(String tableName, String condition) {
        String sql="select * from "+tableName+" where "+condition;

        sql = sql.replaceAll("\\n", " ");
        sql = sql.replaceAll("\\t", " ");
        sql = sql.replaceAll("<br/>", " ");
        sql = sql.trim();
        getDataTable(sql);
     }

    public Select() {

    }

    //为何以 Json 格式返回呢?
     public String data(){
         return gson.toJson(dataTable);
     }

     public int count(){
         return dataTable.size();
     }

    /**
     * 注:只返回第一行的字段值.
     * @param key
     * @return
     */
     public Object value(String key){
        for(Map map:dataTable){
           return map.get(key);
        }
        return null;
     }

    /**
     * 统一输出格式为 json 字符串
     * @param key
     * @return
     */
    public String values(String key){
         List out=new ArrayList();
        for(Map map:dataTable){
            out.add( map.get(key));
        }
        return gson.toJson(out);
    }

    public static List getDataTable(String sql) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        dataTable = new ArrayList();

        try {
            conn = SetUp.getConnection();

            stmt = conn.createStatement();
            rset = stmt.executeQuery(sql);
            HttpLog.info(sql);

            ArrayList<String> columnNames = new ArrayList<String>();

            int numcols = rset.getMetaData().getColumnCount();

            for (int i = 1; i <= numcols; i++) {
                columnNames.add(rset.getMetaData().getColumnName(i));
            }

            while (rset.next()) {
                Map<String,Object> dataItem = new HashMap();
                for (int i = 1; i <= numcols; i++) {
                    dataItem.put(String.valueOf(columnNames.get(i - 1)),String.valueOf(rset.getString(i)));
                }
                dataTable.add(dataItem);
            }
        } catch (SQLException e) {
            ExceptionUtil.printlnSo(e);
            throw new RuntimeException("message:<<"+e.getMessage()+">>");
        } finally {
            try {
                if (rset != null)
                    rset.close();
            } catch (Exception e) {
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
            }

        }

        return dataTable;
    }
}
