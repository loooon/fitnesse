package com.qa;

import com.qa.db.Delete;
import com.qa.db.InsertIgnore;
import com.qa.db.SetUp;
import com.qa.db.Update;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NewYearActivityAttendanceTest {
    public String userId = "204654";
    public String attendanceUrl = "/api/biz/activity/newYear/attendance";
//    public String getAttendanceUrl = "/api/biz/activity/newYear/getAttendance";

    public String httpUrlPrefix = "http://www.lieluobo.developing";
    public String jdbcUrl = "jdbc:mysql://172.16.52.81:3306/dev?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
    public String jdbcUser = "dev";
    public String jdbcPassword = "haolie123";
    /*public String httpUrlPrefix = "http://www.lieluobo.testing";
    public String jdbcUrl = "jdbc:mysql://172.16.52.81:3306/testing?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
    public String jdbcUser = "testing";
    public String jdbcPassword = "haolie123";*/


//    @Before
    public void loginHR() {
        userId = 204705 + "";
        new SetUp("com.mysql.jdbc.Driver", jdbcUrl, jdbcUser, jdbcPassword);
        new Store("headers", "{author:\"llbc\"}");

        ConnectServer cs = new ConnectServer(httpUrlPrefix + "/api/biz/account/xauth");
        cs.setBody("{\"mobile\":\"19900030001\",\"password\":\"123456\",\"channel\":\"hr\"}");
        cs.post();
        new Store("headers", "{author:\"llbc\",authorization:\"" + cs.jsonValue("body") + "\",channel:\"hr\"}");
    }

    @Before
    public void loginC() {
        userId = 204654 + "";
        new SetUp("com.mysql.jdbc.Driver", jdbcUrl, jdbcUser, jdbcPassword);
        new Store("headers", "{author:\"llbc\"}");

        ConnectServer cs = new ConnectServer(httpUrlPrefix + "/api/biz/account/xauth");
        cs.setBody("{\"mobile\":\"15000153768\",\"password\":\"123456\",\"channel\":\"c\"}");
        cs.post();
        new Store("headers", "{author:\"llbc\",authorization:\"" + cs.jsonValue("body") + "\",channel:\"c\"}");
    }

    public void doSign() {
        ConnectServer cs = new ConnectServer(httpUrlPrefix + "/api/biz/activity/attendance");
        cs.get();
    }

    public void cleanData() {
        new Delete("delete from activity_attendance where account_id=" + userId);
    }

    public void fackDataBy(int dayIndex) {
        String now = DataMaker.now();
        String yesToday = DataMaker.today(-1);
        new Delete("DELETE  from activity_attendance_history where account_id=" + userId + " and  activity_no = 'new_year_attendance'");
        new Delete("DELETE  from activity_attendance where account_id=" + userId + " and activity_no = 'new_year_attendance'");

        for (int i = 0; i < dayIndex; i++) {
            String time = DataMaker.today(-dayIndex + i);

            String timeMMdd = DataMaker.today(-dayIndex + i, "yyyyMMdd");

            String sql = "INSERT INTO activity_attendance_history (account_id,activity_no, attendance_time, attendance_time_string, created_by, updated_by, created_at, updated_at) VALUES " +
                    "( " + userId + ", '" + "new_year_attendance'" + ",'" + time + "', '" + timeMMdd + "', " + userId + ", " + userId + ", '" + time + "', '" + time + "');";
            new InsertIgnore(sql);
        }
        if (dayIndex > 0) {
            String sql2 = "INSERT INTO activity_attendance ( account_id,activity_no, recent_attendance_time, continuous_attendance_days, created_by, updated_by, created_at, updated_at) " +
                    "VALUES ( " + userId + ", '" + "new_year_attendance','" + yesToday + "', " + dayIndex + ", " + userId + ", " + userId + ", '" + now + "', '" + now + "');";
            new InsertIgnore(sql2);
            String updateSql = "UPDATE activity_attendance set continuous_attendance_days=" + dayIndex + " where account_id=" + userId + " and activity_no ='new_year_attendance'";
            new Update(updateSql);
        }
    }

    public void fackDataMoney(int count, int money) {
        String rdstring = DataMaker.string(25);
        String type = "10";//红包活动
        int countString = 2000;//特殊序号,
        new Delete("delete from activity_red_packet where account_id=" + userId);
        for (int i = 0; i < count; i++) {
            new InsertIgnore("INSERT INTO activity_red_packet ( activity_id, account_id, status, withdraw_type, ceo_interview, ceo_login, red_packet_no, red_packet_name, money, get_at, withdraw_at, project_target_id, created_by, updated_by, created_at, updated_at) VALUES " +
                    "(" + type + ", " + userId + ", 1, 2, 0, " + userId + ", '" + rdstring + "', '红包', " + money + ", '2017-12-27 16:20:06', null, " + (countString + i) + ", 2, " + userId + ", '2017-12-27 16:18:14', '2017-12-27 16:18:14');");

        }
    }


    /**
     * 测试连续第三天签到的时候 88.00 元红包已经发完
     */
    @Test
    public void at3day88At88() {
        fackDataBy(2);
        fackDataMoney(88, 88);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            deleteToday();
            String moneyString = doSignIn();
            list.add(moneyString);
        }
        report(list);
    }

    /**
     * 测试连续第六天签到的时候 188.00 元红包已经发完
     */
    @Test
    public void at6day18At188() {
        fackDataBy(5);
        fackDataMoney(18, 188);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            deleteToday();
            String moneyString = doSignIn();
            list.add(moneyString);
        }
        report(list);
    }


    /**
     * 测试连续第九天签到的时候 2018.00 元红包已经发完
     */
    @Test
    public void at9day2At2018() {
        fackDataBy(5);
        fackDataMoney(2, 2018);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            deleteToday();
            String moneyString = doSignIn();
            list.add(moneyString);
        }
        report(list);
    }

    /**
     * 重复签到
     *
     * @return
     */
    public List signCount(int count) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            deleteToday();//删除今天的记录
            String moneyString = doSignIn(); //做签到
            list.add(moneyString);//保存结果
        }
        report(list);//生成报告.
        return list;
    }

    //    @Test
    public void at2day() {
        fackDataBy(2);
    }

    /**
     * 测试连续第3天签到
     */
    @Test
    public void at3day() {
        fackDataBy(2);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            deleteToday();
            String moneyString = doSignIn();
            list.add(moneyString);
        }
        report(list);
    }


    @Test
    public void at6day() {
        fackDataBy(5);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            deleteToday();
            String moneyString = doSignIn();
            list.add(moneyString);
        }
        report(list);
    }

    @Test
    public void at9day() {
        fackDataBy(8);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            deleteToday();
            String moneyString = doSignIn();
            list.add(moneyString);
        }
        report(list);
    }

    public void report(List<String> list) {
        int count188 = 0;
        int count888 = 0;
        int count88 = 0;
        int count1880 = 0;
        int count2018 = 0;
        int countNull = 0;
        for (String tmp : list) {
            if (tmp == null) {
                countNull++;
            } else if (tmp.equals("88.0")) {
                count88++;
            } else if (tmp.equals("1.88")) {
                count188++;
            } else if (tmp.equals("8.88")) {
                count888++;
            } else if (tmp.equals("188.0")) {
                count1880++;
            } else if (tmp.equals("2018.0")) {
                count2018++;
            }
        }
        System.out.println("红包总个数:" + list.size());
        System.out.println("红包金额：null:" + countNull + "，占比：" + countNull * 1.0 / list.size());
        System.out.println("红包金额：1.88:" + count188 + "，占比：" + count188 * 1.0 / list.size());
        System.out.println("红包金额：8.88:" + count888 + "，占比：" + count888 * 1.0 / list.size());
        System.out.println("红包金额：88.8:" + count88 + "，占比：" + count88 * 1.0 / list.size());
        System.out.println("红包金额：188:" + count1880 + "，占比：" + count1880 * 1.0 / list.size());
        System.out.println("红包金额：2018:" + count2018 + "，占比：" + count2018 * 1.0 / list.size());
    }

    /**
     * 删除今天的签到记录
     */
    public void deleteToday() {
        String today = DataMaker.today();
        new Delete("DELETE  from activity_attendance_history where account_id=" + userId + " and attendance_time>'" + today + "'");
    }

    /**
     * 签到方法
     *
     * @return 返回签到后领取的红包金额
     */
    public String doSignIn() {
        ConnectServer cs = new ConnectServer(httpUrlPrefix + attendanceUrl);
        cs.get();
        if ("OK".equals(cs.jsonValue("msg"))) {
            return cs.jsonValue("body.money");
        }
        return "";
    }

    /**
     * 当天签到
     */
    @Test
    public void firstDay() {
        int var1 = 0;
        int var2 = 0;
        int num = 100;
        for (int i = 0; i < num; i++) {
            new Delete("DELETE  from activity_attendance_history where account_id =" + userId);
            String moneyString = doSignIn();
            if ("1.88".equals(moneyString)) {
                var1++;
            }
            if ("8.88".equals(moneyString)) {
                var2++;
            }
            System.out.println(moneyString);
        }
        System.out.println("共统计了" + num + "个签到");

        System.out.println("1.88的个数" + var1 + " ,占比：" + var1 * 1.0 / num * 1.0);
        System.out.println("8.88的个数" + var2 + " ,占比：" + var2 * 1.0 / num * 1.0);
    }
}
