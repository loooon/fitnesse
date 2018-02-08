package com.qa;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * fitnesse_toc
 * Created by yu on 2017/7/25.
 * 测试数据构造可以使用专业的工具,也可以使用开源的工具,
 * 还可以跟数据库结合,这里只显示一些简单的方法.
 */
public class DataMakerTest {
    @Test
    public void testUuid1() throws Exception {
    }

    @Test
    public void testTid2() throws Exception {
    }

    @Test
    public void testTid3() throws Exception {
        for(int i=0;i<100;i++)
            System.out.println(DataMaker.chineseName()) ;
    }

    @Test
    public void testIntArray1() throws Exception {
    }

    @Test
    public void testIntArray2() throws Exception {
        for(int i=0;i<100;i++){
            //System.out.println(Math.random()*10);
          //  System.out.println(Math.round(Math.random()*10));
            System.out.println(DataMaker.randomIsoDate()) ;
        }
    }

    @Test
    public void isoDate(){
        for(int i=0;i<100;i++)
            System.out.println(DataMaker.isoDate()) ;
    }

    @Test
    public void testPend() throws Exception {
        assert "e10adc3949ba59abbe56e057f20f883e".equals( DataMaker.md5("123456"));
    }

    @Test
    public void testPendArray() throws Exception {
        System.out.println(DataMaker.today(0));

        System.out.println(DataMaker.today(1));
    }

    @Test
    public void testUuid() throws Exception {
        System.out.println(DataMaker. weekdayTime(1));
        System.out.println(DataMaker. weekdayTime(7));
      //  DataMaker.        mondayToSunday();
    }

    @Test
    public void testTid() throws Exception {
        System.out.println(DataMaker. string(100).length());
        System.out.println(DataMaker. string(5000).length());
        for(int i=0;i<100;i++){
            assert DataMaker. string(i).length()==i;
        }

        for(int i=0;i<100;i++){
            System.out.println(  DataMaker. chineseString(i));
        }
    }


    @Test
    public void testTid1() throws Exception {
        DataMaker.telephone();
    }

    @Test
    public void testIntArray() throws Exception {
        System.out.println( DataMaker.intArray());
    }

}