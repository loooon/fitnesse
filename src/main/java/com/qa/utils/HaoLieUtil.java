package com.qa.utils;

import java.nio.ByteBuffer;

/**
 * fitnesse_toc
 * Created by yu on 2018/1/5.
 */
public class HaoLieUtil {
    /**
     * 这个方法用于 HaoLie.com im消息解码.
     * 前7位用于生成事件码
     * 后面的生成字符串.
     * 其实是前7字节是特殊码,后面是纯utf8字符串.
     * @param array
     * @return
     */
    public static String Utf8ArrayToStr (ByteBuffer array){
        String out = "";
        int i = 6;
        int c;
        byte char2;
        byte char3;
        byte[] chars=  array.array();
        int len = chars.length;
        while (i < len) {
            c = chars[i++];
            switch (c >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    // 0xxxxxxx
                    out += Character.valueOf((char) c);
                    break;
                case 12:
                case 13:
                    // 110x xxxx   10xx xxxx
                    char2 = chars[i++];
                    out += Character.valueOf((char) (((c & 0x1F) << 6) | (char2 & 0x3F)));
                    break;
                case 14:
                    // 1110 xxxx  10xx xxxx  10xx xxxx
                    char2 = chars[i++];
                    char3 = chars[i++];
                    out +=  Character.valueOf((char) (((c & 0x0F) << 12) |
                            ((char2 & 0x3F) << 6) |
                            ((char3 & 0x3F) << 0)));
                    break;
            }
        }
        return out;
    }
}
