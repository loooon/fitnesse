package com.qa.utils;

import java.text.MessageFormat;

/**
 * Created by yu on 2017/1/17.
 *
 */
public class ExceptionUtil {
    //暂不关联配置文件
    static String STACK_TARGET_PACKAGE="com.qa";

    /**
     * 不打印所有异常栈,只打印类关联的异常栈
     * @param obj 目标对象,只打印目标对象相关的异常
     * @param excetion 异常对象
     */
    public static void printlnSo(Object obj,Exception excetion){
         StringBuilder sbException = new StringBuilder();
        sbException.append(excetion.getClass().getName() + ": " + excetion.getMessage()+"\n");
        for (StackTraceElement ele : excetion.getStackTrace()) {
            if (obj != null &&  obj.getClass().getName().equals(ele.getClassName())) {
                sbException.append(MessageFormat.format("\tat {0}.{1}({2}:{3})\n",ele.getClassName(), ele.getMethodName(), ele.getFileName(), ele.getLineNumber()));
            }
        }
        /**LOG4j  **/
        System.err.println(sbException);
    }

    /**
     * 字符串匹配到的异常才打打印
     * @param match 匹配字符串
     * @param excetion 异常对象
     */
    public static void printlnSo(String match, Exception excetion){
        int sCount=0;
        StringBuilder sbException = new StringBuilder();
        sbException.append(excetion.getClass().getName() + ": " + excetion.getMessage()+"\n");
        for (StackTraceElement ele : excetion.getStackTrace()) {
            if(sCount<1){
                sbException.append(MessageFormat.format("\tat {0}.{1}({2}:{3})\n",ele.getClassName(), ele.getMethodName(), ele.getFileName(), ele.getLineNumber()));
            }
            sCount++;

            if (match != null &&  ele.getClassName().contains(match)) {
                sbException.append(MessageFormat.format("\tat {0}.{1}({2}:{3})\n",ele.getClassName(), ele.getMethodName(), ele.getFileName(), ele.getLineNumber()));
            }

        }
        /**LOG4j  **/
        System.err.println(sbException);

    }

    /**
     * 只打印当前com.qa.*包下的异常栈!
     * @param excetion 异常对象,默认参数为 "STACK_TARGET_PACKAGE"
     */
    public static void printlnSo(Exception excetion){
        printlnSo(STACK_TARGET_PACKAGE,excetion);
    }
    public static void printlnSo(Object obj,Exception excetion,String appendString){
        System.err.println(appendString);
        printlnSo(obj,excetion);
    }
    public static void printlnSo(Exception excetion,String appendString){
        System.err.println(appendString);
        printlnSo(excetion);
    }
}
