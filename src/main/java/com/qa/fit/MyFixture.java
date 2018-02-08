package com.qa.fit;

import fit.Fixture;
import fit.Parse;
import fit.exception.FitParseException;



/**
 * Created by yu on 16/8/25.
 */
public class MyFixture extends Fixture {

    public Parse all;

    @Override
    public void doTable(Parse table){
       // System.out.println("do Table :"+table);
        this.all=table;
        super.right(table.parts);
       // super.doTable(table);
        doRows(table.parts); //原来是用table.parts.more 忽略第一行

    }

    @Override
    public void doTables(Parse table){
//        System.out.println(table.body);
//        table.addToTag(" class=\"pass\"");
//        System.out.println("abc");


        //super.doTables(table);
        this.doTable(table);
    }

    @Override
    public void doCell(Parse cell, int col){
        cell.addToTag(" class=\"pass\"");
         System.out.println("abc");

    }
    public void doRows(Parse row, int col){
        System.out.println("abc");

    }


    public void AAstest() throws FitParseException {

        MyFixture tt=new MyFixture();
        tt.doTables(new Parse("<table>\n" +
                "<tbody><tr>\n" +
                "<td colspan=\"2\">com.qa.fit.MyFixture</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>abccc</td>\n" +
                "<td>abafs</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td colspan=\"2\">abafs</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td colspan=\"2\">abafs</td>\n" +
                "</tr>\n" +
                "</tbody></table>"));
       // tt.
        //

        System.out.println( tt.all.body);
    }



}

