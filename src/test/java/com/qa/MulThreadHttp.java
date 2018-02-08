package com.qa;

import com.qa.http.Get;
import com.qa.http.Http;
import com.qa.utils.JavaShellUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * fitnesse_toc
 * Created by yu on 2017/8/2.
 */
public class MulThreadHttp {

    /**
     * HTTP多线程测试,超级多的
     */
    @Test
    public void mulTest() throws InterruptedException {
        int countThreadTotal=100;//总线程数
        int countTimeStep=100;//步进时间ms
        int countPreThread=1;// 每线程重进次数
        //不使用时间参数


        Thread[] threads=new Thread[100];
        long[] expendTime=new long[100];
        Map stack=new HashMap();
        for(int i=0;i<100;i++){

            int tid=i;//把值复制出来!
            Thread thread=new Thread(()->{
                long stime=System.currentTimeMillis();
                stack.put(tid+"","ing");
                Get get= Http.get("http://cw.lieluobo.testing/api/dict");
                get.header("authorization","6bbce45e9c814138a51bbc44bf4e9d52");
                get.text();
                long etime=System.currentTimeMillis();
                expendTime[tid]=etime-stime;
                System.out.println(etime-stime);
                stack.remove(tid);
            });
            threads[i]=thread;
        }
        for(int i=0;i<100;i++){
            Thread.sleep(100);
            threads[i].start();
        }
        Thread.sleep(1000);

        for(int i=0;i<1000;i++){
            if(stack.size()>0){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                for(int j=0;j<100;j++){
                    System.out.print(expendTime[j]+",");
                }
                break;
            }
        }

    }

    @Test
   void shellTest(){
        System.out.println(JavaShellUtil.execCommand("curl http://www.baidu.com").toString());
            System.out.println(JavaShellUtil.execCommand("dir").toString());
            System.out.println(JavaShellUtil.execCommand("ls -l").toString());
            //System.out.println(JavaShellUtil.execCommand("ping www.baidu.com").toString());
            System.out.println(JavaShellUtil.execCommand("aapt v").toString());
            System.out.println(JavaShellUtil.execCommand("aapt.exe").toString());


    }
}
