package com.qa;

import bsh.EvalError;
import bsh.Interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.qa.utils.GsonJsonUtil;
import org.json.JSONObject;

/**
 * fitnesse_toc
 * Created by yu on 2017/12/26.
 * <p>
 * format WIKI text,then run
 * |script    |Connect Server|${url}            |
 * |set Param |id            ||1                |
 * |set Body  |{{{
 * {
 * phone:"", //公司电话
 * location:"" //公司详细地址
 * }
 * }}}                               |
 * |post      |
 * |check     |json Structure|code,msg,body|true|
 * |check     |json Value    |msg          |OK  |
 */
public class FileRunner {
    JSONObject jj=new JSONObject();

    static String  example = "|Store| headers|{author:\"llbc\"}|\n" +
            "|script  |Connect Server |http://cw.lieluobo.testing/api/account/xauth|\n" +
            "|set Body|{\"mobile\":\"19900000001\",\"password\":\"123456\",\"channel\":\"cw\"}|\n" +
            "|post                                                               |\n" +
            "|check   |json Structure |code,msg,body          |true              |\n" +
            "|check   |json Value     |msg                |OK         |\n" +
            "|$token= |json Value     |body                                   |\n";

    List<Map<String, String>> actionList = new ArrayList(); //   setParam-->args
    public static void runWiki(String src){
        Interpreter i = new Interpreter();
        String tableScript=preFormat(src);
        try {
            i.eval(tableScript);
         } catch (EvalError evalError) {
        System.err.println(evalError.getMessage() + ":");
    }

    }
    public String cornvertWikiToScript(String src){
        return preFormat(src);
    }

    /**
     * format  {{{ }}}  then run use BeanShell
     */
    public  static String preFormat(String src) {
        String tableScript="import com.qa.*;\n";

            String[] arr = src.split("\\|\n");
            for (String line : arr) {
                String script = "";
                line = line.replaceAll("\\{\\{\\{", "");
                line = line.replaceAll("\\}\\}\\}", "");
                String[] words = line.split("\\|");
                words=cleanFirst(words);

                script=buildScript(words);


                //i.eval(script);
               // System.out.println(script);
                tableScript+=script+"\n";
            }
            //System.err.printf(tableScript);
        return tableScript;
    }

    /**
     *  javaScript  Array.join
     * @param strAry  array
     * @param joinChar  ,
     * @return
     */
    public static String join(String[] strAry,String joinChar){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<strAry.length;i++){
            if("".equals(strAry[i])) continue;
            if(!isInteger(strAry[i])) {

                strAry[i]="\""+mayBeGson(strAry[i]).replaceAll("\"","\\\\\"").replaceAll("\n","")+"\"";
            }
            if(i==(strAry.length-1)){
                sb.append(strAry[i]);
            }else{
                sb.append(strAry[i]).append(joinChar);
            }
        }

        return new String(sb);
    }
    public static boolean isInteger(String str) {
        return  false;
       // Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        //return pattern.matcher(str).matches();
    }
    public static String buildScript(String[] words){
        if("script".equals(words[0])  ){
            return  "object=new " +asKey(words[1])+"("+ join(cleanFirst(cleanFirst(words)),",")+");";
        }
        if("Store".equals(words[0])){
            return  "object=new " +asKey(words[0])+"("+ join(cleanFirst(words),",")+");";
        }
        if("check".equals(words[0])){
            //TODO
            return "";
            //return "assert object."+asKey(words[1])+"(\""+ words[2]+"\") == " +words[3]+";";
        }
        if(words[0].startsWith("$")){
            //TODO
            return "";
        }

        return  "object."+asKey(words[0])+"("+join(cleanFirst(words),",")+")"+";";
    }
    public static String asKey(String src){
        return  src.replaceAll(" ","");
    }


    public static String[] cleanFirst(String[] src){
        String[] out=new String[src.length-1];
        for(int i=0;i<out.length;i++){
             out[i]=src[i+1].trim();
        }
        return  out;
    }
    public boolean check() {
        return true;
    }

    public static void main(String[] args) {
        System.out.println( new FileRunner().preFormat(example));;
    }

    public static String mayBeGson(String mayBeJson){
             try{
                Map map= GsonJsonUtil.gson.fromJson(mayBeJson,Map.class);
                return GsonJsonUtil.gson.toJson(map);
            }catch (Exception e){
                try{
                    List list= GsonJsonUtil.gson.fromJson(mayBeJson,List.class);
                    return GsonJsonUtil.gson.toJson(list);
                }catch (Exception e2){
                    return mayBeJson;
                }
            }

    }
}
