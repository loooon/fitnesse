package com.qa.fit;


import com.qa.utils.ExceptionUtil;
import fit.Parse;

/**
 * Created by yu on 16/8/25.
 */
public class AnyFix {

    public AnyFix() {
        System.out.println("AnyFix.....");

    }


    public void doTables(Parse tables) {
        System.out.println("AnyFix dotables...");
        try {
            Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {
            ExceptionUtil.printlnSo(e);
        }

    }

    public boolean doTable() {
        System.out.println("AnyFix. dotable  ..");
        try {
            Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {
            ExceptionUtil.printlnSo(e);
        }
        return true;

    }

    public boolean sk() {
        System.out.println("sk........");
        try {
            Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {
            ExceptionUtil.printlnSo(e);
        }
        return true;
    }


    public String bb() {
        System.out.println("bb....");
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            ExceptionUtil.printlnSo(e);
        }
        return "BB";
    }
}
