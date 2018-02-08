package com.qa;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import static org.testng.Assert.*;

/**
 * fitnesse_toc
 * Created by yu on 2017/7/26.
 */
public class SleepTest {
    @Test
    public void testSleepInterval() throws Exception {
        Sleep.sleepInterval();
        Sleep.sleepInterval();
    }

    @Test
    public void testSleepInterval1() throws Exception {
        Sleep.sleepInterval();
        long stime=System.currentTimeMillis();
        Sleep.sleepInterval(2000);
        long etime=System.currentTimeMillis();
      //  assert etime-stime>2000;
    }
    @Test
    public void testSleepInterval3() throws Exception {

        int rvl=1000;
        long time=new Date().getTime();
        writeDate(new Date(time-rvl));

        //配置为1秒前

        long stime=System.currentTimeMillis();
        Sleep.sleepInterval(rvl*2);
        long etime=System.currentTimeMillis();
        assert etime-stime<rvl+100;
    }

    public void writeDate(Date date){
        try {
            File record = new File(".last");
            if (!record.exists()) {
                record.createNewFile();
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(".last"));
            out.writeObject(date);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSleepInterval4() throws Exception {

        int rvl=60*1000;
        long time=new Date().getTime();
        writeDate(new Date(time-rvl));
        //配置为1秒前

        long stime=System.currentTimeMillis();
        Sleep.sleepInterval(rvl);
        long etime=System.currentTimeMillis();
        assert etime-stime<100;
    }

}