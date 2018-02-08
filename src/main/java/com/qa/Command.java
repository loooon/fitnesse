package com.qa;

import com.qa.utils.JavaShellUtil;

/**
 * fitnesse_toc
 * Created by yu on 2017/8/10.
 * 执行系统命令.
 */
public class Command {
    public Command(String cmd){
       String log= JavaShellUtil.execCommand(cmd).toString();
        System.out.println(log);
    }
}
