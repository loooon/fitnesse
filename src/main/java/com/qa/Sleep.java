package com.qa;

import java.io.*;
import java.util.Date;

/**
 * fitnesse_toc
 * Created by yu on 2017/7/24.
 * 由于不能直接调用静态方法,需要来包装一个方法
 */
public class Sleep {
    public Sleep() {
    }

    public Sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按生命周期来延时处理,
     * 缓存文件 .last
     */
    public static void utilLastRun(int ms) {
        //序列化对象
        try {
            recordFileInit();
            Date now = new Date();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(".last"));
            Date oldDate = (Date) in.readObject();    //读取匿名Date对象
            in.close();
            long time=now.getTime()-oldDate.getTime() ;

            if (time< ms && time>0) {
                Thread.sleep(ms -time);
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(".last"));
            out.writeObject(new Date());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void recordFileInit() {
        File record = new File(".last");

        try {
            if (!record.exists()) {
                record.createNewFile();
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(".last"));
                out.writeObject(new Date());
                out.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void sleepInterval() {
        utilLastRun(1000);
    }

    public static void sleepInterval(int ms) {
        utilLastRun(ms);
    }
}
