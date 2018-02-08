package com.qa.db;

import com.qa.ConnectServer;
import com.qa.http.HttpLog;
import com.qa.utils.ExceptionUtil;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class SetUp {
    static{
        HttpLog.info("db init...");
    }

    public static String DEFAULT_CONNECTION_POOL_NAME = "default";
    public static int DEFAULT_WAIT_TIMEOUT = 5000;
    static Map<String, Connection> mapConnection = new HashMap();


    public  SetUp(final String jdbcDriverClass, final String connectURI, final String username, final String password) {
        try {
            final String key = jdbcDriverClass + connectURI + username + password;
            Connection conn = mapConnection.get(key);
            if(conn==null) {
                new Thread() {
                    @Override
                    public void run() {
                        Connection connIn=null;
                        try {
                            Class.forName(jdbcDriverClass);
                            connIn = DriverManager.getConnection(connectURI, username, password);
                        } catch (ClassNotFoundException e) {
                            System.err.println("找不到驱动类！" + e.getMessage());
                        } catch (SQLException e) {
                            System.err.println("SQL连接异常！" + e.getMessage());
                        }
                        mapConnection.put(key, connIn);
                        mapConnection.put(DEFAULT_CONNECTION_POOL_NAME, connIn);
                    }
                }.start();
            }else if (!conn.isClosed()) {
                return;//mapConnection.get(key);
            }else if(conn.isClosed()){
                //
            }

            return;
        } catch (SQLException e) {
            System.err.println("SQL连接异常！" + e.getMessage());
        }
    }

    public static Connection getConnection()  {
        Connection out=mapConnection.get(DEFAULT_CONNECTION_POOL_NAME);
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            out=mapConnection.get(DEFAULT_CONNECTION_POOL_NAME);
            if(out!=null)
                return out;
        }
        return out;
    }

    public static int runStandSql(String sql){
      return   runStandSqlWithOutException(sql,false);
    }

    /**
     * 无视异常的情况还是有的.
     * @param sql
     * @return
     */
    public static int runStandSqlWithOutException(String sql,boolean ignoreEx){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        int _count=0;

        try {
            conn = SetUp.getConnection();

            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            HttpLog.info(sql);
            _count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            ExceptionUtil.printlnSo(e);
            if(!ignoreEx)
                throw new RuntimeException("message:<<run sql error"+e.getMessage()+">>");
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

        return _count;
    }
}
