package com.qa

import com.qa.db.Delete
import com.qa.db.InsertIgnore
import com.qa.db.SetUp
import com.qa.db.Update
import org.junit.Before
import org.junit.Test
import java.util.ArrayList

open class NewYearAttendanceTest {

    var userId = "204654"
    var attendanceUrl = "/api/biz/activity/newYear/attendance"
//    public String getAttendanceUrl = "/api/biz/activity/newYear/getAttendance";

    var httpUrlPrefix = "http://www.lieluobo.developing"
    var jdbcUrl = "jdbc:mysql://172.16.52.81:3306/dev?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false"
    var jdbcUser = "dev"
    var jdbcPassword = "haolie123"
    /*public String httpUrlPrefix = "http://www.lieluobo.testing";
    public String jdbcUrl = "jdbc:mysql://172.16.52.81:3306/testing?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
    public String jdbcUser = "testing";
    public String jdbcPassword = "haolie123";*/


//    @Before
fun loginHR() {
    userId = 204705.toString() + ""
    SetUp("com.mysql.jdbc.Driver", jdbcUrl, jdbcUser, jdbcPassword)
    Store("headers", "{author:\"llbc\"}")

    val cs = ConnectServer(httpUrlPrefix + "/api/biz/account/xauth")
    cs.setBody("{\"mobile\":\"19900030001\",\"password\":\"123456\",\"channel\":\"hr\"}")
    cs.post()
    Store("headers", "{author:\"llbc\",authorization:\"" + cs.jsonValue("body") + "\",channel:\"hr\"}")
}

    @Before
    fun loginC() {
        userId = 204654.toString() + ""
        SetUp("com.mysql.jdbc.Driver", jdbcUrl, jdbcUser, jdbcPassword)
        Store("headers", "{author:\"llbc\"}")

        val cs = ConnectServer(httpUrlPrefix + "/api/biz/account/xauth")
        cs.setBody("{\"mobile\":\"15000153768\",\"password\":\"123456\",\"channel\":\"c\"}")
        cs.post()
        Store("headers", "{author:\"llbc\",authorization:\"" + cs.jsonValue("body") + "\",channel:\"c\"}")
    }

    fun doSign() {
        val cs = ConnectServer(httpUrlPrefix + "/api/biz/activity/attendance")
        cs.get()
    }

    fun cleanData() {
        Delete("delete from activity_attendance where account_id=" + userId)
    }

    fun fackDataBy(dayIndex: Int) {
        val now = DataMaker.now()
        val yesToday = DataMaker.today(-1)
        Delete("DELETE  from activity_attendance_history where account_id=$userId and  activity_no = 'new_year_attendance'")
        Delete("DELETE  from activity_attendance where account_id=$userId and activity_no = 'new_year_attendance'")

        for (i in 0 until dayIndex) {
            val time = DataMaker.today(-dayIndex + i)

            val timeMMdd = DataMaker.today(-dayIndex + i, "yyyyMMdd")

            val sql = "INSERT INTO activity_attendance_history (account_id,activity_no, attendance_time, attendance_time_string, created_by, updated_by, created_at, updated_at) VALUES " +
                    "( " + userId + ", '" + "new_year_attendance'" + ",'" + time + "', '" + timeMMdd + "', " + userId + ", " + userId + ", '" + time + "', '" + time + "');"
            InsertIgnore(sql)
        }
        if (dayIndex > 0) {
            val sql2 = "INSERT INTO activity_attendance ( account_id,activity_no, recent_attendance_time, continuous_attendance_days, created_by, updated_by, created_at, updated_at) " +
                    "VALUES ( " + userId + ", '" + "new_year_attendance','" + yesToday + "', " + dayIndex + ", " + userId + ", " + userId + ", '" + now + "', '" + now + "');"
            InsertIgnore(sql2)
            val updateSql = "UPDATE activity_attendance set continuous_attendance_days=$dayIndex where account_id=$userId and activity_no ='new_year_attendance'"
            Update(updateSql)
        }
    }

    fun fackDataMoney(count: Int, money: Int) {
        val rdstring = DataMaker.string(25)
        val type = "10"//红包活动
        val countString = 2000//特殊序号,
        Delete("delete from activity_red_packet where account_id=" + userId)
        for (i in 0 until count) {
            InsertIgnore("INSERT INTO activity_red_packet ( activity_id, account_id, status, withdraw_type, ceo_interview, ceo_login, red_packet_no, red_packet_name, money, get_at, withdraw_at, project_target_id, created_by, updated_by, created_at, updated_at) VALUES " +
                    "(" + type + ", " + userId + ", 1, 2, 0, " + userId + ", '" + rdstring + "', '红包', " + money + ", '2017-12-27 16:20:06', null, " + (countString + i) + ", 2, " + userId + ", '2017-12-27 16:18:14', '2017-12-27 16:18:14');")

        }
    }


    /**
     * 测试连续第三天签到的时候 88.00 元红包已经发完
     */
    @Test
    fun at3day88At88() {
        fackDataBy(2)
        fackDataMoney(88, 88)
        val list = ArrayList<String>()
        for (i in 0..99) {
            deleteToday()
            val moneyString = doSignIn()
            list.add(moneyString)
        }
        report(list)
    }

    /**
     * 测试连续第六天签到的时候 188.00 元红包已经发完
     */
    @Test
    fun at6day18At188() {
        fackDataBy(5)
        fackDataMoney(18, 188)
        val list = ArrayList<String>()
        for (i in 0..99) {
            deleteToday()
            val moneyString = doSignIn()
            list.add(moneyString)
        }
        report(list)
    }


    /**
     * 测试连续第九天签到的时候 2018.00 元红包已经发完
     */
    @Test
    fun at9day2At2018() {
        fackDataBy(5)
        fackDataMoney(2, 2018)
        val list = ArrayList<String>()
        for (i in 0..99) {
            deleteToday()
            val moneyString = doSignIn()
            list.add(moneyString)
        }
        report(list)
    }

    /**
     * 重复签到
     *
     * @return
     */
    fun signCount(count: Int): List<*> {
        val list = ArrayList<String>()
        for (i in 0 until count) {
            deleteToday()//删除今天的记录
            val moneyString = doSignIn() //做签到
            list.add(moneyString)//保存结果
        }
        report(list)//生成报告.
        return list
    }

    //    @Test
    fun at2day() {
        fackDataBy(2)
    }

    /**
     * 测试连续第3天签到
     */
    @Test
    fun at3day() {
        fackDataBy(2)
        val list = ArrayList<String>()
        for (i in 0..99) {
            deleteToday()
            val moneyString = doSignIn()
            list.add(moneyString)
        }
        report(list)
    }


    @Test
    fun at6day() {
        fackDataBy(5)
        val list = ArrayList<String>()
        for (i in 0..99) {
            deleteToday()
            val moneyString = doSignIn()
            list.add(moneyString)
        }
        report(list)
    }

    @Test
    fun at9day() {
        fackDataBy(8)
        val list = ArrayList<String>()
        for (i in 0..99) {
            deleteToday()
            val moneyString = doSignIn()
            list.add(moneyString)
        }
        report(list)
    }

    fun report(list: List<String>) {
        var count188 = 0
        var count888 = 0
        var count88 = 0
        var count1880 = 0
        var count2018 = 0
        var countNull = 0
        for (tmp in list) {
            if (tmp == null) {
                countNull++
            } else if (tmp == "88.0") {
                count88++
            } else if (tmp == "1.88") {
                count188++
            } else if (tmp == "8.88") {
                count888++
            } else if (tmp == "188.0") {
                count1880++
            } else if (tmp == "2018.0") {
                count2018++
            }
        }
        println("红包总个数:" + list.size)
        println("红包金额：null:" + countNull + "，占比：" + countNull * 1.0 / list.size)
        println("红包金额：1.88:" + count188 + "，占比：" + count188 * 1.0 / list.size)
        println("红包金额：8.88:" + count888 + "，占比：" + count888 * 1.0 / list.size)
        println("红包金额：88.8:" + count88 + "，占比：" + count88 * 1.0 / list.size)
        println("红包金额：188:" + count1880 + "，占比：" + count1880 * 1.0 / list.size)
        println("红包金额：2018:" + count2018 + "，占比：" + count2018 * 1.0 / list.size)
    }

    /**
     * 删除今天的签到记录
     */
    fun deleteToday() {
        val today = DataMaker.today()
        Delete("DELETE  from activity_attendance_history where account_id=$userId and attendance_time>'$today'")
    }

    /**
     * 签到方法
     *
     * @return 返回签到后领取的红包金额
     */
    fun doSignIn(): String {
        val cs = ConnectServer(httpUrlPrefix + attendanceUrl)
        cs.get()
        return if ("OK" == cs.jsonValue("msg")) {
            cs.jsonValue("body.money")
        } else ""
    }

    /**
     * 当天签到
     */
    @Test
    fun firstDay() {
        var var1 = 0
        var var2 = 0
        val num = 100
        for (i in 0 until num) {
            Delete("DELETE  from activity_attendance_history where account_id =" + userId)
            val moneyString = doSignIn()
            if ("1.88" == moneyString) {
                var1++
            }
            if ("8.88" == moneyString) {
                var2++
            }
            println(moneyString)
        }
        println("共统计了" + num + "个签到")

        println("1.88的个数" + var1 + " ,占比：" + var1 * 1.0 / num * 1.0)
        println("8.88的个数" + var2 + " ,占比：" + var2 * 1.0 / num * 1.0)
    }


}