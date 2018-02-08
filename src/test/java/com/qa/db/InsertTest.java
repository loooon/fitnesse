package com.qa.db;

import com.google.gson.Gson;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/17.
 */
public class InsertTest {
    @Test
    public void kvToMap() throws Exception {

       Map map=Insert.kvToMap("{id:1}");
          map=Insert.kvToMap(" {id:1," +
                  "\na:2,c:'12'}");
        map=Insert.kvToMap(" {id:1," +
                "\na:2,c:'12'//ab\n}");

        map=Insert.kvToMap("{" +
                "leaderId: 204634// 领导Id\n" +

                "}");
        map=Insert.kvToMap("{" +
                "leaderId: 204634 ,// 领导Id\n" +
                "containSubTeam: true , //是否包含子团队\n" +
                "containSubStaff: true  //是否包含子团队成员\n" +
                "}");


        System.out.println( Insert.mapToSql(map));
        System.out.println(map);
    }

}