package com.qa.utils;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class GsonJsonUtil {
    /**
     * 防止 int转换成 double
    * */
    public static Gson gson=new GsonBuilder().
            registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
                @Override
                public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                    if (src == src.longValue())
                        return new JsonPrimitive(src.longValue());
                    return new JsonPrimitive(src);
                }
            }).create();


    /**
     * 这个 GSON 会 自动转换为下划线的格式.用于数据库
     *  JSON模型转换
     *  如isDeveloper-->is_developer
     */
    public static Gson gsonLower=new GsonBuilder().registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
        @Override
        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == src.longValue())
                return new JsonPrimitive(src.longValue());
            return new JsonPrimitive(src);
        }
    }).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    /**
     * pretty print
     */
    public static Gson gsonPretty= new GsonBuilder().
        registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
        @Override
        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == src.longValue())
                return new JsonPrimitive(src.longValue());
            return new JsonPrimitive(src);
        }
     }).setPrettyPrinting().create();
}
