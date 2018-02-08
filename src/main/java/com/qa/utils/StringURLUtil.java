package com.qa.utils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/20.
 */
public class StringURLUtil {

    /**
     * www.baidu.com/aapi/{id}/abc.api
     * 替换 url 上的 IP
     *
     * @return URL
     */
    public static String urlParamMatcher(String url, String formString) {


        if (Pattern.matches(".*\\{\\w*\\}.*", url)) {
            // 按指定模式在字符串查找
            Map<String, String> map = buildMapByForm(formString);
            // 创建 Pattern 对象
            String partern = "(\\{\\w*\\})";

            Pattern rurl = Pattern.compile(partern);
            // 现在创建 matcher 对象
            Matcher m = rurl.matcher(url);
            while (m.find()) {

                String namePix = m.group(0);
                String namePull = namePix.replace("{", "").replace("}", "");
                url = url.replace(namePix, map.get(namePull));
                map.remove(namePull);
            }
            return urlClear(url + "?" + buildFromByMap(map));
        } else {
            if(formString==null)
                formString="";
            return urlClear(url + "?" + formString);
        }
    }

    public static String urlClear(String url) {

        if (url != null) {
            if (url.endsWith("?")) {
                return url.substring(0, url.lastIndexOf("?"));
            } else
                return url;
        } else {
            return "";
        }

    }

    /**
     * 把表单转换成 map
     * 如  a=b&bc=c  => a:b,bc:c
     */
    public static Map buildMapByForm(String form) {
        Map map = new HashMap();
        if (form != null) {
            String[] all = form.split("\\&");
            for (String s : all) {
                String[] nameValue = s.split("=");
                if (nameValue.length == 1)
                    map.put(nameValue[0], "");
                else {
                    map.put(nameValue[0], nameValue[1]);
                }
            }
        }
        return map;
    }

    public static String buildFromByMap(Map<String, String> map) {
        String out = "";
        for (String key : map.keySet()) {
            out += key + "=" + URLEncoder.encode(map.get(key))+"&";
        }
        if(out.endsWith("&"))
            out=out.substring(0,out.length()-1);
        return out;
    }


}
