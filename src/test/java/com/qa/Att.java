package com.qa;

import com.qa.db.Delete;
import com.qa.db.InsertIgnore;
import com.qa.db.SetUp;
import com.qa.db.Update;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * fitnesse_toc
 * Created by yu on 2017/12/26.
 * 红包签到活动专项测试
 */
public class Att {

    public String userId="204705";

   // @Before
    public void loginHR(){

        userId=204705+"";
        //204705,19900020002,HR自动化2002,
        new SetUp("com.mysql.jdbc.Driver",	"jdbc:mysql://172.16.52.81:3306/testing?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false","testing",	"haolie123");

        new Store("headers","{author:\"haolie\"}");

        ConnectServer cs = new ConnectServer("http://www.lieluobo.testing/api/biz/account/xauth");
        cs.setBody("{\"mobile\":\"19900030001\",\"password\":\"123456\",\"channel\":\"hr\"}");
        cs.post();

        new Store("headers","{author:\"haolie\",authorization:\""+cs.jsonValue("body")+"\",channel:\"hr\"}");
    }
   // @Before
    public void loginC(){
        userId=204641+"";
        new SetUp("com.mysql.jdbc.Driver",	"jdbc:mysql://172.16.52.81:3306/testing?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false","testing",	"haolie123");

        new Store("headers","{author:\"haolie\"}");

        ConnectServer cs = new ConnectServer("http://www.lieluobo.testing/api/biz/account/xauth");
        cs.setBody("{\"mobile\":\"19900030001\",\"password\":\"123456\",\"channel\":\"c\"}");
        cs.post();

        new Store("headers","{author:\"haolie\",authorization:\""+cs.jsonValue("body")+"\",channel:\"c\"}");
    }
    //@Test
    public void Test01At188(){

        //签到
        ConnectServer cs = new ConnectServer("http://www.lieluobo.testing/api/activity/attendance");
        cs.get();
       // cs.jsonValue()
    }

    public void doSign(){
        ConnectServer cs = new ConnectServer("http://www.lieluobo.testing/api/biz/activity/attendance");
        cs.get();
    }

    public void cleanData(){
        new Delete("delete from activity_attendance where account_id=204705");//
    }
   // @Test
    public void jj(){
        fackDataBy(2);
    }
    public void fackDataBy(int dayIndex){
        String now=DataMaker.now();
        String yesToday=DataMaker.today(-1);
        new Delete("DELETE  from activity_attendance_history where account_id="+userId);
        new Delete("DELETE  from activity_attendance where account_id="+userId);

        for(int i=0;i<dayIndex;i++){
            String time=DataMaker.today(-dayIndex+i);

            String timeMMdd=DataMaker.today(-dayIndex+i,"yyyyMMdd");

            String sql="INSERT INTO activity_attendance_history (account_id, attendance_time, attendance_time_string, created_by, updated_by, created_at, updated_at) VALUES " +
                    "( "+userId+", '"+time+"', '"+timeMMdd+"', "+userId+", "+userId+", '"+time+"', '"+time+"');";


            new InsertIgnore(sql);
        }
        if(dayIndex>0) {

            String sql2 = "INSERT INTO activity_attendance ( account_id, recent_attendance_time, continuous_attendance_days, created_by, updated_by, created_at, updated_at) " +
                    "VALUES ( " + userId + ", '" + yesToday + "', " + dayIndex + ", " + userId + ", " + userId + ", '" + now + "', '" + now + "');";
            new InsertIgnore(sql2);
            String updateSql = "UPDATE activity_attendance set continuous_attendance_days=" + dayIndex + " where account_id=" + userId;
            new Update(updateSql);
        }
    }
    public void fackDataMoney(int count,int money){
        String rdstring=DataMaker.string(25);
        String type="9";//红包活动
        int  countString=2000;//特殊序号,
        new Delete("delete from activity_red_packet where account_id="+userId);
        for(int i=0;i<count;i++){
            new InsertIgnore("INSERT INTO activity_red_packet ( activity_id, account_id, status, withdraw_type, ceo_interview, ceo_login, red_packet_no, red_packet_name, money, get_at, withdraw_at, project_target_id, created_by, updated_by, created_at, updated_at) VALUES " +
                    "("+type+", "+userId+", 1, 2, 0, "+userId+", '"+rdstring+"', '红包', "+money+", '2017-12-27 16:20:06', null, "+(countString+i)+", 2, "+userId+", '2017-12-27 16:18:14', '2017-12-27 16:18:14');");

        }
    }

    /**
     * 第3天,已经有100个中了88元,只能中1.88元!
     */
   // @Test
    public void at3day100At88(){
        fackDataBy(2);
        fackDataMoney(100,88);
        List<String> list=signCount(100);
        for(String tmp:list){
            if(tmp.equals("88")){
                throw new RuntimeException("不能有88元的签到红包了!!!");
            }
        }
    }
   // @Test
    public void at3day99At88(){
        fackDataBy(2);
        fackDataMoney(99,88);
        List<String> list=signCount(100);
        int count=0;
        for(String tmp:list){
            if(tmp.equals("88.0")){
                count++;
            }
        }
        if(count!=1){
              //正确的,今天的签到记录已经删除.只有一条
        }
    }
   // @Test
    public void at5day49At188(){
        fackDataBy(4);
        fackDataMoney(49,188);
        List<String> list=signCount(100);
        int count=0;
        for(String tmp:list){
            if(tmp.equals("188.0")){
                count++;
            }
        }
        if(count!=1){
             //正确的,今天的签到记录已经删除.只有一条
      }
    }
    //@Test
    public void at5day50At188(){
        fackDataBy(4);
        fackDataMoney(50,188);
        List<String> list=signCount(100);
        int count=0;
        for(String tmp:list){
            if(tmp.equals("188.0")){
                count++;
            }
        }
        if(count>0){
            throw new RuntimeException("188元的只能有0个");
        }
    }


    /**
     * 重复签到
     * @return
     */
    public List signCount(int count){
        List<String> list=new ArrayList<>();
        for(int i=0;i<count;i++){
            deleteToday();//删除今天的记录
            String moneyString=doSignIn(); //做签到
            list.add(moneyString);//保存结果
        }
        report(list);//生成报告.
        return  list;
    }

   // @Test
    public void at2day(){

        fackDataBy(10);
    }
   // @Test
    public void at3day(){
        fackDataBy(2);
        List<String> list=new ArrayList<>();
        for(int i=0;i<100;i++){
            deleteToday();
            String moneyString=doSignIn();
            list.add(moneyString);
        }
       report(list);
    }
   // @Test
    public void at15day(){
        fackDataBy(14);
        List<String> list=new ArrayList<>();
        for(int i=0;i<2200;i++){
            deleteToday();
            String moneyString=doSignIn();
            list.add(moneyString);
        }
        report(list);
    }

    //@Test
    public void at5day(){
        fackDataBy(4);
        List<String> list=new ArrayList<>();
        for(int i=0;i<200;i++){
            deleteToday();
            String moneyString=doSignIn();
            list.add(moneyString);
        }
        report(list);
    }
    //@Test
    public void at6day(){
        fackDataBy(5);
        List<String> list=new ArrayList<>();
        for(int i=0;i<200;i++){
            deleteToday();
            String moneyString=doSignIn();
            list.add(moneyString);
        }
        report(list);
    }

    public void report(List<String> list){
        int count188=0;
        int count88=0;
        int count1880=0;
        int count2018=0;
        int countNull=0;
        for(String tmp:list){
            if(tmp==null){
                countNull++;
            }else if(tmp.equals("88.0")){
                count88++;
            }else if(tmp.equals("1.88")){
                count188++;
            }else if(tmp.equals("188.0")){
                count1880++;
            }else if(tmp.equals("2018.0")){
                count2018++;
            }
        }
        double st=count88*1.0/(count188+count88);
        System.out.println("--------->null:"+countNull);
        System.out.println("--------->1.88:"+count188);
        System.out.println("--------->88:"+count88);
        System.out.println("--------->188:"+count1880);
        System.out.println("--------->2018:"+count2018);

    }

    public void deleteToday(){
        String today=DataMaker.today();

        new Delete("DELETE  from activity_attendance_history where account_id="+userId+" and attendance_time>'"+today+"'");
    }
    public String doSignIn(){
        ConnectServer cs = new ConnectServer("http://www.lieluobo.testing/api/biz/activity/attendance");
        cs.get();
        if("OK".equals(cs.jsonValue("msg"))){
            String redPacketId= cs.jsonValue("body.redPacketId");
            cs = new ConnectServer("http://www.lieluobo.testing/api/biz/activity/accountGetRedPacket/"+redPacketId);
            cs.get();
            String moneyString= cs.jsonValue("body.money");
            return moneyString;
        }
        return "";
    }


    //@Test
    /** 当天签到
     *
     * 测试通过.
     */
    public void firstDay(){
        for(int i=0;i<100;i++){
            new Delete("DELETE  from activity_attendance_history where account_id="+userId);
            String moneyString=doSignIn();
            if(!"1.88".equals(moneyString)){
                throw new RuntimeException("金额不为1.88元,实际为:"+moneyString);
            }

        }

    }
}
