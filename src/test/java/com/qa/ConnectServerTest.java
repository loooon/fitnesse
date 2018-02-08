package com.qa;

import com.qa.utils.GsonJsonUtil;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/11.
 */
public class ConnectServerTest {
    @Test
    public void testPost() throws Exception {
    }

    @Test
    public void testPost1() throws Exception {
    }

    @Test
    public void testDelete() throws Exception {
    }

    @Test
    public void testGet() throws Exception {
    }

    @Test
    public void testIsGson() throws Exception {
    }

    @Test
    public void testGsonParse() throws Exception {
    }

    @Test
    public void testUuid() throws Exception {
    }

    @Test
    public void testTid() throws Exception {
    }

    @Test
    public void testTid1() throws Exception {
    }

    @Test
    public void testSetParam() throws Exception {
    }

    @Test
    public void testSetParam1() throws Exception {
    }

    @Test
    public void testSetHeader() throws Exception {
    }

    @Test
    public void testSetHeader1() throws Exception {
    }

    @Test
    public void testRequestForString() throws Exception {
    }

    @Test
    public void testFillRequestHeader() throws Exception {
    }

    @Test
    public void testJsonValue() throws Exception {
    }

    @Test
    public void testJsonLike() throws Exception {
    }

    @Test
    public void testJsonMatch() throws Exception {
        ConnectServer cs = new ConnectServer("empty");
        cs.responseBody = "{\"code\":1,\"msg\":\"OK\",\"body\":[{\"id\":101716,\"name\":\"客户公司-变动状态-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":20,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[1,44],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0},{\"id\":101705,\"name\":\"客户公司-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":10,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[5,8,2],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0}],\"meta\":{\"pagination\":{\"page\":1,\"size\":10,\"total\":2,\"totalPages\":1}}}\n";
        assert cs.jsonContain("{name:\t\"客户公司-自动化测试预埋数据\",orgBdName:\t\"员工4-HRBD-自动化测试预埋数据\"}");
        cs.jsonUtil.parseJson(new JSONObject(cs.responseBody));
        assert cs.jsonMatch("body[0].name","客户公司.*");
        assert !cs.jsonMatch("body[0].name","客户公司A.*");
    }

    @Test
    public void testJsonCompare() throws Exception {
    }

    @Test
    public void testJsonCompare1() throws Exception {
    }

    @Test
    public void testTextContain() throws Exception {
    }

    @Test
    public void testJsonSimilar() throws Exception {
    }

    @Test
    public void testRequestForJSON() throws Exception {
    }

    @Test
    public void testRequestForXML() throws Exception {
    }

    @Test
    public void testJsonStructure() throws Exception {
    }

    @Test
    public void testResponseHeader() throws Exception {
    }

    @Test
    public void testSetType() throws Exception {
    }

    @Test
    public void testUpStr() throws Exception {
    }

    @Test
    public void testAutoGetEnvirementIPUrl() throws Exception {
    }

    @Test
    public void testAUTO_GET_BASE_URL() throws Exception {
    }

    @Test
    public void testAutoSetBaseUrl() throws Exception {
    }

    @Test
    public void testIsEmpty() throws Exception {
    }

    @Test
    public void testIsNotEmpty() throws Exception {
    }

    @Test
    public void testStore() throws Exception {
    }

    @Test
    public void testDelSimpleHtmlTag() throws Exception {
    }

    @Test
    public void testDelHTMLTag() throws Exception {
    }

    @Test
    public void testAddOneUrlCount() throws Exception {
    }

    @Test
    public void testAddShutdownHook() throws Exception {
    }

    @Test
    public void testReport() throws Exception {
    }

    @Test
    public void testSortMap() throws Exception {
    }

    @Test
    public void testUrlMapMerge() throws Exception {
    }

    @Test
    public void testUnescapeHtml() throws Exception {
    }

    @Test
    public void testSetURL() throws Exception {
    }

    @Test
    public void testHttpCode() throws Exception {
    }

    @Test
    public void testGetResponseCode() throws Exception {
    }

    @Test
    public void testGetResponseBody() throws Exception {
    }

    @Test
    public void testSetResponseCode() throws Exception {
    }

    @Test
    public void testBuildURLKey() throws Exception {
    }

    @Test
    public void delHtmlPre() throws Exception {
        assert ConnectServer.delHtmlPre("<pre> \n" +
                "        ^ in <eval> at line number 1 at column number 8</pre>").equals("^ in <eval> at line number 1 at column number 8");
    }

    @Test
    public void setBody() throws Exception {
        ConnectServer cs = new ConnectServer("empty");
        cs.setBody(" {\n" +
                "staffId: 1,//注释\n" +
                "teamId: 1,\n" +
                "page: 1,\n" +
                "size: 10\n" +
                "}", "json");
        assert "{\"staffId\":1,\"teamId\":1,\"page\":1,\"size\":10}".equals(cs.requestBody);
        cs.setBody("[1,2,3]");
        assert "[1,2,3]".equals(cs.requestBody);
    }

    @Test
    public void setBody2() throws Exception {
        ConnectServer cs = new ConnectServer("empty");
        cs.setBody(" {\n" +
                "staffId: 1,//注释\n" +
                "teamId: 1,\n" +
                "page: 1,\n" +
                "size: 10\n" +
                "}");
        assert "{\"staffId\":1,\"teamId\":1,\"page\":1,\"size\":10}".equals(cs.requestBody);
        cs.setBody("[1,2,3]");
        assert "[1,2,3]".equals(cs.requestBody);
    }

    @Test
    public void jsonContain() {
        ConnectServer cs = new ConnectServer("empty");
        cs.responseBody = "{\"code\":1,\"msg\":\"OK\",\"body\":{\"industryList\":[{\"code\":1,\"amount\":1},{\"code\":18,\"amount\":2},{\"code\":56,\"amount\":1}],\"locationList\":[{\"code\":110000,\"amount\":1},{\"code\":310000,\"amount\":1}]}}\n";
        assert cs.jsonContain("{\"code\":1,\"amount\":1}");
    }

    @Test
    public void javaScript() {
        ConnectServer cs = new ConnectServer("empty");
        cs.responseBody = "{\"code\":1,\"msg\":\"OK\",\"body\":{\"industryList\":[{\"code\":1,\"amount\":1},{\"code\":18,\"amount\":2},{\"code\":56,\"amount\":1}],\"locationList\":[{\"code\":110000,\"amount\":1},{\"code\":310000,\"amount\":1}]}}\n";
        assert (Boolean) cs.javaScript("CONTAIN(response,{\"code\":1,\"amount\":1})");
    }

    @Test
    public void jsTest(){
        String body="{\"code\":1,\"msg\":\"OK\",\"body\":\"\\n\\n\\nfunction execute() {\\n    var rows = [[{\\\"value\\\":1,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-08-29\\\",\\\"key\\\":\\\"/llb/project/new_project_org_belong_type\\\"}}},{\\\"value\\\":193,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"inProgressProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-08-29\\\",\\\"key\\\":\\\"/llb/status_manage/doing_project_org_belong_type\\\"}}},{\\\"value\\\":1,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"receiving\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-08-29\\\",\\\"key\\\":\\\"/llb/project_assign/order_take_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newReport\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-08-29\\\",\\\"key\\\":\\\"/llb/project_target/order_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"report\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-08-29\\\",\\\"key\\\":\\\"/llb/project_target/recommond_report_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"interview\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-08-29\\\",\\\"key\\\":\\\"/llb/project_interview/interview_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"offer\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-08-29\\\",\\\"key\\\":\\\"/llb/project_offer/offer_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"order\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-08-29\\\",\\\"key\\\":\\\"/llb/project_target/order_success_org_belong_type\\\"}}},{\\\"value\\\":1,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"entry\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-08-29\\\",\\\"key\\\":\\\"/llb/project_warranty/entry_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"warranty\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-08-29\\\",\\\"key\\\":\\\"/llb/project_warranty/pass_warranty_org_belong_type\\\"}}}],[{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-27\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project/new_project_org_belong_type\\\"}}},{\\\"value\\\":190,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"inProgressProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-27\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/status_manage/doing_project_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"receiving\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-27\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_assign/order_take_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newReport\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-27\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_target/order_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"report\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-27\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_target/recommond_report_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"interview\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-27\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_interview/interview_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"offer\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-27\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_offer/offer_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"order\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-27\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_target/order_success_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"entry\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-27\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_warranty/entry_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"warranty\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-27\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_warranty/pass_warranty_org_belong_type\\\"}}}],[{\\\"value\\\":1,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-09-04\\\",\\\"key\\\":\\\"/llb/project/new_project_org_belong_type\\\"}}},{\\\"value\\\":193,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"inProgressProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-09-04\\\",\\\"key\\\":\\\"/llb/status_manage/doing_project_org_belong_type\\\"}}},{\\\"value\\\":1,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"receiving\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-09-04\\\",\\\"key\\\":\\\"/llb/project_assign/order_take_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newReport\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-09-04\\\",\\\"key\\\":\\\"/llb/project_target/order_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"report\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-09-04\\\",\\\"key\\\":\\\"/llb/project_target/recommond_report_org_belong_type\\\"}}},{\\\"value\\\":72,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"interview\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-09-04\\\",\\\"key\\\":\\\"/llb/project_interview/interview_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"offer\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-09-04\\\",\\\"key\\\":\\\"/llb/project_offer/offer_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"order\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-09-04\\\",\\\"key\\\":\\\"/llb/project_target/order_success_org_belong_type\\\"}}},{\\\"value\\\":4,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"entry\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-09-04\\\",\\\"key\\\":\\\"/llb/project_warranty/entry_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"warranty\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-28\\\",\\\"end\\\":\\\"2017-09-04\\\",\\\"key\\\":\\\"/llb/project_warranty/pass_warranty_org_belong_type\\\"}}}],[{\\\"value\\\":44,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-21\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project/new_project_org_belong_type\\\"}}},{\\\"value\\\":192,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"inProgressProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-21\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/status_manage/doing_project_org_belong_type\\\"}}},{\\\"value\\\":3,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"receiving\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-21\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_assign/order_take_org_belong_type\\\"}}},{\\\"value\\\":7,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newReport\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-21\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_target/order_org_belong_type\\\"}}},{\\\"value\\\":5,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"report\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-21\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_target/recommond_report_org_belong_type\\\"}}},{\\\"value\\\":12,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"interview\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-21\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_interview/interview_org_belong_type\\\"}}},{\\\"value\\\":1,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"offer\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-21\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_offer/offer_org_belong_type\\\"}}},{\\\"value\\\":1,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"order\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-21\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_target/order_success_org_belong_type\\\"}}},{\\\"value\\\":2,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"entry\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-21\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_warranty/entry_org_belong_type\\\"}}},{\\\"value\\\":55,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"warranty\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-21\\\",\\\"end\\\":\\\"2017-08-28\\\",\\\"key\\\":\\\"/llb/project_warranty/pass_warranty_org_belong_type\\\"}}}],[{\\\"value\\\":31,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-14\\\",\\\"end\\\":\\\"2017-08-21\\\",\\\"key\\\":\\\"/llb/project/new_project_org_belong_type\\\"}}},{\\\"value\\\":168,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"inProgressProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-14\\\",\\\"end\\\":\\\"2017-08-21\\\",\\\"key\\\":\\\"/llb/status_manage/doing_project_org_belong_type\\\"}}},{\\\"value\\\":5,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"receiving\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-14\\\",\\\"end\\\":\\\"2017-08-21\\\",\\\"key\\\":\\\"/llb/project_assign/order_take_org_belong_type\\\"}}},{\\\"value\\\":11,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newReport\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-14\\\",\\\"end\\\":\\\"2017-08-21\\\",\\\"key\\\":\\\"/llb/project_target/order_org_belong_type\\\"}}},{\\\"value\\\":15,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"report\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-14\\\",\\\"end\\\":\\\"2017-08-21\\\",\\\"key\\\":\\\"/llb/project_target/recommond_report_org_belong_type\\\"}}},{\\\"value\\\":8,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"interview\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-14\\\",\\\"end\\\":\\\"2017-08-21\\\",\\\"key\\\":\\\"/llb/project_interview/interview_org_belong_type\\\"}}},{\\\"value\\\":7,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"offer\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-14\\\",\\\"end\\\":\\\"2017-08-21\\\",\\\"key\\\":\\\"/llb/project_offer/offer_org_belong_type\\\"}}},{\\\"value\\\":6,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"order\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-14\\\",\\\"end\\\":\\\"2017-08-21\\\",\\\"key\\\":\\\"/llb/project_target/order_success_org_belong_type\\\"}}},{\\\"value\\\":2,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"entry\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-14\\\",\\\"end\\\":\\\"2017-08-21\\\",\\\"key\\\":\\\"/llb/project_warranty/entry_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"warranty\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-14\\\",\\\"end\\\":\\\"2017-08-21\\\",\\\"key\\\":\\\"/llb/project_warranty/pass_warranty_org_belong_type\\\"}}}],[{\\\"value\\\":124,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-01\\\",\\\"end\\\":\\\"2017-09-01\\\",\\\"key\\\":\\\"/llb/project/new_project_org_belong_type\\\"}}},{\\\"value\\\":210,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"inProgressProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-01\\\",\\\"end\\\":\\\"2017-09-01\\\",\\\"key\\\":\\\"/llb/status_manage/doing_project_org_belong_type\\\"}}},{\\\"value\\\":66,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"receiving\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-01\\\",\\\"end\\\":\\\"2017-09-01\\\",\\\"key\\\":\\\"/llb/project_assign/order_take_org_belong_type\\\"}}},{\\\"value\\\":87,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newReport\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-01\\\",\\\"end\\\":\\\"2017-09-01\\\",\\\"key\\\":\\\"/llb/project_target/order_org_belong_type\\\"}}},{\\\"value\\\":43,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"report\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-01\\\",\\\"end\\\":\\\"2017-09-01\\\",\\\"key\\\":\\\"/llb/project_target/recommond_report_org_belong_type\\\"}}},{\\\"value\\\":103,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"interview\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-01\\\",\\\"end\\\":\\\"2017-09-01\\\",\\\"key\\\":\\\"/llb/project_interview/interview_org_belong_type\\\"}}},{\\\"value\\\":26,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"offer\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-01\\\",\\\"end\\\":\\\"2017-09-01\\\",\\\"key\\\":\\\"/llb/project_offer/offer_org_belong_type\\\"}}},{\\\"value\\\":20,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"order\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-01\\\",\\\"end\\\":\\\"2017-09-01\\\",\\\"key\\\":\\\"/llb/project_target/order_success_org_belong_type\\\"}}},{\\\"value\\\":12,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"entry\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-01\\\",\\\"end\\\":\\\"2017-09-01\\\",\\\"key\\\":\\\"/llb/project_warranty/entry_org_belong_type\\\"}}},{\\\"value\\\":55,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"warranty\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-08-01\\\",\\\"end\\\":\\\"2017-09-01\\\",\\\"key\\\":\\\"/llb/project_warranty/pass_warranty_org_belong_type\\\"}}}],[{\\\"value\\\":35,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-07-01\\\",\\\"end\\\":\\\"2017-08-01\\\",\\\"key\\\":\\\"/llb/project/new_project_org_belong_type\\\"}}},{\\\"value\\\":129,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"inProgressProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-07-01\\\",\\\"end\\\":\\\"2017-08-01\\\",\\\"key\\\":\\\"/llb/status_manage/doing_project_org_belong_type\\\"}}},{\\\"value\\\":60,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"receiving\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-07-01\\\",\\\"end\\\":\\\"2017-08-01\\\",\\\"key\\\":\\\"/llb/project_assign/order_take_org_belong_type\\\"}}},{\\\"value\\\":91,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newReport\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-07-01\\\",\\\"end\\\":\\\"2017-08-01\\\",\\\"key\\\":\\\"/llb/project_target/order_org_belong_type\\\"}}},{\\\"value\\\":99,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"report\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-07-01\\\",\\\"end\\\":\\\"2017-08-01\\\",\\\"key\\\":\\\"/llb/project_target/recommond_report_org_belong_type\\\"}}},{\\\"value\\\":20,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"interview\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-07-01\\\",\\\"end\\\":\\\"2017-08-01\\\",\\\"key\\\":\\\"/llb/project_interview/interview_org_belong_type\\\"}}},{\\\"value\\\":16,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"offer\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-07-01\\\",\\\"end\\\":\\\"2017-08-01\\\",\\\"key\\\":\\\"/llb/project_offer/offer_org_belong_type\\\"}}},{\\\"value\\\":11,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"order\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-07-01\\\",\\\"end\\\":\\\"2017-08-01\\\",\\\"key\\\":\\\"/llb/project_target/order_success_org_belong_type\\\"}}},{\\\"value\\\":7,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"entry\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-07-01\\\",\\\"end\\\":\\\"2017-08-01\\\",\\\"key\\\":\\\"/llb/project_warranty/entry_org_belong_type\\\"}}},{\\\"value\\\":0,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"warranty\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"2017-07-01\\\",\\\"end\\\":\\\"2017-08-01\\\",\\\"key\\\":\\\"/llb/project_warranty/pass_warranty_org_belong_type\\\"}}}],[{\\\"value\\\":259,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"1970-01-01\\\",\\\"end\\\":\\\"2100-01-01\\\",\\\"key\\\":\\\"/llb/project/new_project_org_belong_type\\\"}}},{\\\"value\\\":258,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"inProgressProject\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"1970-01-01\\\",\\\"end\\\":\\\"2100-01-01\\\",\\\"key\\\":\\\"/llb/status_manage/doing_project_org_belong_type\\\"}}},{\\\"value\\\":249,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"receiving\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"1970-01-01\\\",\\\"end\\\":\\\"2100-01-01\\\",\\\"key\\\":\\\"/llb/project_assign/order_take_org_belong_type\\\"}}},{\\\"value\\\":501,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"newReport\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"1970-01-01\\\",\\\"end\\\":\\\"2100-01-01\\\",\\\"key\\\":\\\"/llb/project_target/order_org_belong_type\\\"}}},{\\\"value\\\":255,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"report\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"1970-01-01\\\",\\\"end\\\":\\\"2100-01-01\\\",\\\"key\\\":\\\"/llb/project_target/recommond_report_org_belong_type\\\"}}},{\\\"value\\\":308,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"interview\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"1970-01-01\\\",\\\"end\\\":\\\"2100-01-01\\\",\\\"key\\\":\\\"/llb/project_interview/interview_org_belong_type\\\"}}},{\\\"value\\\":159,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"offer\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"1970-01-01\\\",\\\"end\\\":\\\"2100-01-01\\\",\\\"key\\\":\\\"/llb/project_offer/offer_org_belong_type\\\"}}},{\\\"value\\\":115,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"order\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"1970-01-01\\\",\\\"end\\\":\\\"2100-01-01\\\",\\\"key\\\":\\\"/llb/project_target/order_success_org_belong_type\\\"}}},{\\\"value\\\":84,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"entry\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"1970-01-01\\\",\\\"end\\\":\\\"2100-01-01\\\",\\\"key\\\":\\\"/llb/project_warranty/entry_org_belong_type\\\"}}},{\\\"value\\\":55,\\\"link\\\":{\\\"api\\\":\\\"/chart/detail/:id\\\",\\\"type\\\":\\\"warranty\\\",\\\"params\\\":{\\\"customIds\\\":[42,46640,-1],\\\"cumstom_belong_type\\\":1,\\\"cIds\\\":[19,39,40,-1],\\\"c_belong_type\\\":1,\\\"start\\\":\\\"1970-01-01\\\",\\\"end\\\":\\\"2100-01-01\\\",\\\"key\\\":\\\"/llb/project_warranty/pass_warranty_org_belong_type\\\"}}}]]\\n    var data = [];\\n    \\n    for(var x in rows){\\n        var v = []\\n        data.push(v)\\n        for(var y in rows[x]){\\n            var tmp = rows[x][y][\\\"value\\\"]\\n            v.push(tmp)\\n        }\\n    }\\n\\n    var option = [{\\n        \\\"type\\\": \\\"list\\\",\\n        \\\"data\\\": {\\n            \\\"xAxis\\\": [\\\"新职位\\\", \\\"进展职位\\\", \\\"接单\\\", \\\"CV\\\",\\\"推荐报告\\\", \\\"面试\\\", \\\"OFFER\\\", \\\"成单\\\", \\\"入职\\\", \\\"过保\\\"],\\n            \\\"yAxis\\\": [\\\"今天\\\", \\\"昨天\\\", \\\"本周\\\", \\\"上周\\\", \\\"上上周\\\",\\\"本月\\\",\\\"上月\\\",\\\"历史\\\"],\\n            \\\"xTips\\\": [\\n                '查询时间范围内，新建的职位总数',\\n                '查询时间范围内，处于进展中状态的职位总数',\\n                '查询时间范围内，被猎头接单的职位总数',\\n                '查询时间范围内,所有猎头发送给职位的CV的总数量',\\n                '查询时间范围内，CW推送给Hr的推荐报告总数，反复发送同一个推荐报告不重复计算',\\n                '查询时间范围内,创建的面试总数量,同一个订单的N场面试按多N场算，时间以创建时间为准',\\n                '查询时间范围内,平台上创建的offer数量总和',\\n                '查询时间范围内,平台上接受的offer数量总和',\\n                '查询时间范围内,平台上确认过候选人已入职的订单数量总和',\\n                '查询时间范围内,平台上确认过候选人已过保的订单总数量总和'\\n            ],\\n            \\\"data\\\": data\\n        },\\n        \\\"link\\\": function(param){\\n            return rows[param.y][param.x][\\\"link\\\"]\\n        }\\n    }];\\n\\n    var chart = {\\n        \\\"type\\\": \\\"SUMMARY\\\",\\n        \\\"option\\\": option\\n    };\\n    return chart\\n}\"}";
        ConnectServer cs = new ConnectServer("empty");
        cs.responseBody=body;
       Object sk=  cs.javaScript("eval(response.body)");
       assert "function".equals(sk);

        System.out.println(cs.javaScript("execute().option[0].data.data[1][0]"));

    }

    @Test
    public void as() {
        ConnectServer cs = new ConnectServer("empty");
        cs.responseBody = "{\"code\":1,\"msg\":\"OK\",\"body\":[{\"id\":101716,\"name\":\"客户公司-变动状态-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":20,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[1,44],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0},{\"id\":101705,\"name\":\"客户公司-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":10,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[5,8,2],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0}],\"meta\":{\"pagination\":{\"page\":1,\"size\":10,\"total\":2,\"totalPages\":1}}}\n";
        assert cs.jsonContain("{name:\t\"客户公司-自动化测试预埋数据\",orgBdName:\t\"员工4-HRBD-自动化测试预埋数据\"}");

    }

    @Test
    public void htmlTest(){
        System.out.println(ConnectServer.unescapeHtml("for(var i=0;i&lt;response.body.length;i++)&lt;&lt;&lt;"));
    }
    @Test
    public void rujsExit(){
        ConnectServer cs = new ConnectServer("empty");
        cs.responseBody="{body:[]}";

      //  cs.javaScript("exit");
        cs.javaScript("var out=\"OK\"\n" +
                "for(var i=0;i<response.body.length;i++){\n" +
                "  var curr=response.body[i]\n" +
                "  for(var j=i+1;j<response.body.length;j++){\n" +
                "  var next=response.body[j]\n" +
                "   if(curr.orderId==next.orderId){\n" +
                "     out=\"fuck\"\n" +
                "     break \n" +
                "   }\n" +
                "  }\n" +
                "}\n" +
                "out");
    }


    @Test
    public void urlMapMergeTest() {
        Integer it = null;
        // int a=it; //不能转化

        Pattern.compile("(http://\\w\\.)").matcher("DELETE:http://www.lieluobo.testing/api/resume/groups/3725").find();
        ConnectServer cs = new ConnectServer("empty");

        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/groups/3725");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/groups/3725");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/groups/3725");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/groups/3725");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/groups/3725");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/groups/3725");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/groups/3725");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/subscriber/cw/list?a=k");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/subscriber/cw/list?a=b");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/subscriber/cw/list?c=d");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/groups/3726");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/groups/3726");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/list");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/financial/update");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/resumeStatusStat");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/queryAll");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/searchProjectNotice");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/app/version/save");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/report/shareToHrManager");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/attachment");
        cs.addOneUrlCount("DELETE:http://cw.lieluobo.testing/api/todo/{id}");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/cw/detail/sendReports");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/searchProjectNotice");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/organization/hasOfficialContract");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/organization/searchAllOrgs");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/experiences/{id}");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/ownProjects");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/receivingPositions");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/allPositionName");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/order/star");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/project/c/indexProjects");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/organization/hasLocationDetail");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/receivecDetail");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/remark/delete/{remarkId}");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/remind/markAsRead");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/organization/getQccOrgDetail");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/workflow/remark");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/common/qiniu/token");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/shareReport/sendMail");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api//bd/org/{orgId}/business");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/organization/cws");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/remind/batch");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/workflow/mailSuffix");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/org/{orgId}/business");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/contact?orgId=102167");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/organization/updateQA");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/common/outNumber");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/project/warranty");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/account/pwd");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/norecommendHunter");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/checkResumeRepeat");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/attachment/id");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/import");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/organization/add");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/registAccountDetail");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/contact_record/search");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/saveCwProjectPublish");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/hunter");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/interviews");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/position/detail/receivedReports");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/im/listCacheConversation");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/organization/addContract");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/projectAssigns");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/hrManager/orders");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/entry");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/account/hunterExcelTemplate");
        cs.addOneUrlCount("POST:http://bi.api.lieluobo.testing/action");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/unfrequentCustomerCompany");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/cProjects");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/org/{orgId}");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/contact_record/call/remark");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/position/data");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/distribution");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/assignReceiving");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/scoreDict");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/contact");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/periodAmount");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/mail/saveDraft");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/publish/updateStatus");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/initWarranty");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/redlist/page");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/saveCwProjectPublishRelease");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/workflow/query");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/feedback/list");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/org/{orgId}/stockholders");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/position/detail/reveiveds");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/im/obtainMsgCount");
        cs.addOneUrlCount("DELETE:http://cw.lieluobo.testing/api/bd/redlist/{id}");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/receiving");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/belongAccountDetail");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/saveProjectNotice");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/inProgressProject");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/cwResumesGroupC");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/account/hrManagerFreeze");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/weChat/jsapiTicket");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/haveOrderCOrganization");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/order/evaluate");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/haveInterviewHeadhunters");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/account/simpleregister");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/workflow/{id}/update");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/noInterviewHunter");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/account/hrManager");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/initOffer");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/account/updateLoginMail");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/common/qiniu/token");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/warranty");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/account/saveHr");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/common/innerOrg");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/im/obtainMsg");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/cwReport");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/educations/{id}");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/registerapply/query");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/financial/detail/{id}");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/project/projectAttention");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/remark");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/position/detail/inProcesses");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/account/me");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/position/detail/warranty");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/save");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/positions");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/logout");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hrManager/me");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/app/version/page");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/cw/detail/toBePublished");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/remark");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/interviewHunter");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/orderDetail");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/project/projects");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/assignHunter");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/unfrequentHunterCompany");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/account/get");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/resume/location");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/report");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/organization/import");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/noReceivingPositions");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/cwTeamResumes");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/attachment");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/account/updateLoginMobile");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/cw/detail/hireCount");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/position/detail/interviews");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/inactiveHunterCompany");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/organization");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/cwMajorProjects");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/getOneAll");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/remind/save");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/order/updateStatus");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/updateQA");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/account/team/byLeader");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/interviews");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/signOrgDetail");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/score/save");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/account/{id}/score");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/haveOrderHrOrganization");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/sms");
        cs.addOneUrlCount("GET:http://bms.lieluobo.testing/api/dashboard/{key}");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/report/id");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/resume/groups");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/org/parseExcel");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/attachment/{id}");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/cw/detail/rejectedCvs");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/position/detail/offers");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/order/remark");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/subscriber/cw/match");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/account/team/treeByLeader?leaderId=-11");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/order");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/im/createConversationList");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/org/{id}/score");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/feedback/{id}");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/cwRecommendProjects");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/newProject");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/cw/performance");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/query");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/account/team/treeByLeader?leaderId=204634");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/account/role/list");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/contact_record/call/create");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/list/developing");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/attachment/id");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/organization/updateContract");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/org/{orgId}/visit");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/interview");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/cw/detail/offers");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/registerHeadhunters");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/targetCodeInfo");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/position/updateStatus");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/organization/checkOrgCwProject");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/pwd");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/project/searchProjectNotice");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/app/version/{id}/delete");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/project/unProjectAttention");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/feedback");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/account/parseExcel");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/recommendToHeadhunterPositions");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/projectAssignPages");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/homePage/overView");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/positions/count");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/noassignHunter");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/account/imgVerifyCode");
        cs.addOneUrlCount("GET:http://bms.lieluobo.testing/api/bd/contact");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/leaveMessages/{positionId}");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/unfrequentHunter");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/resumeStatus");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/havePositionOrganization");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/report");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/recommendPositions");
        cs.addOneUrlCount("DELETE:http://cw.lieluobo.testing/api/bd/contact/{orgId}/{id}");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/groups/2500");
        cs.addOneUrlCount("DELETE:http://cw.lieluobo.testing/api/bd/bdUser/{orgId}/{id}");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/recommendHeadhunters");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/receivingRecommends");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/feedback/update");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/org/searchOrg");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/organization/search");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/assignApplyAudit");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/recommendHunter");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/cwProjectPublishPage");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/haveReportOrganization");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/organization/checkOrgName");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/newResumesTodayCw");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/resume/id/{id}");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/account/roleconfig/{roleId}");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/workflow/create");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/initInterview");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/attachment");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/reset");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/getOneAllPublish");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/redlist/save");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/publish/positions");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/preference");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/feedback/dict");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/grant");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/order/share");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/account/save");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/organization/listContract/{orgId}");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/position/{id}");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/chart/detail/{id}");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/account/{id}/score");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/team/list");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/resumes");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/xauth");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/project/offer");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/haveEntryHeadhunters");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/financial/searchOrderFlow");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/subscriber/cw/history");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/mail/search");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/todo/{id}");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/resumeStatus");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/contact");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/account/chkImgVerifyCode");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/organization/{id}");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/haveOfferHeadhunters");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/position/detail/sendReports");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/account/saveC");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/report/share/{uriCode}");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/visitDetail");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/order/evaluate");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/resume/extractor");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/workflow/contract/{id}");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/report/updateStatus");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/account/imgVerifyCode");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/resume/saveLabel");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/groups/qeqe");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/cwTeamProjectsStat");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/report");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/cwTodos");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hrManager/orders");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/account/hrManager/{id}");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/recommendCustomerDetail");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/saveProjectTargetScoreError");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/attachment");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/todo/list");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/registerapply");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/account/get");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/hasPermission");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/cw/detail/receivedOrders");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/resume/experiences");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/cw/detail/receivedCvs");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/mail/{mailId}/detail");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/im/ignoreConversation");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/haveWarrantyHeadhunters");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/position/detail/entries");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/project/org/projects");
        cs.addOneUrlCount("GET:http://bms.lieluobo.testing/api/bi/detail/offer");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/updateFreeze");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/common/outNumber");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/project/leaveMessages");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/order/remark");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/project/interview");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/project/ownResumes");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/project/leaveMessage");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/location/list");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/haveReportHeadhunters");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/activityCustomerDetail");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/orders");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/registerApply");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/account/get");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/team/query");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/dict/score");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/groups/3725");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/subscriber/cw/list");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/groups/3726");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/report/preview");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/subscriber/cw/add");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/cwProjects");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/remind/get/{id}");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/report/shares/{uriGroup}");
        cs.addOneUrlCount("DELETE:http://www.lieluobo.testing/api/resume/projects/{id}");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/position/inviteProgress");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/teamResumesStat");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/crm/statics");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/organization/hasPhoneNumber");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/bdUser");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/resume/projects");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/org/{orgId}/base");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/mail/send");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/remind/list");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/recommendcDetail");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/account/xauth");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/cancelReceivingPositions");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/detail/projectParameter");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/jdhc");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/projects");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/organization/update");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/org/{orgId}/visit");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/position/overview");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/account/save");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/order/report");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/assignApplyAudit");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/attachment/download");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/cw/detail/entries");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/preference");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/account/team/treeByLeader?leaderId=1");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/org/delay");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/cw/detail/interviews");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/teamCs");
        cs.addOneUrlCount("POST:http://bms.lieluobo.testing/api/account/xauth");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/status");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/position/dataSummary");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/account/me");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/cw/performanceSummary");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/mail/receiveNew");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/mail/contact");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/orgDetail");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/account/forgetPwd");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/im/listConversation");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/org/create");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/org/{id}/score");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hrManager/order");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/interviewcDetail");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/cwMajorResumes");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/org/{orgId}/staff");
        cs.addOneUrlCount("POST:http://hr.lieluobo.testing/api/hr/publish/position");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/resume/remark");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/position/detail/orders");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/feedback");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/resume/search");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/resume/basic");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/cw/detail/warranty");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/projectResumesGroupC");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/orgVisitDetail");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/organization/qccSearch");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/projectAttentions");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/org/{orgId}/business");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/org/{orgId}/stockholders");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/resume/groups/addTo");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/orders/count");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/c/own/static");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/todo");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/newReport");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/ownResumesStat");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/cwTeamProjects");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/account/xauth");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/common/env");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/remind/count");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/publishPositions");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/remarks");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/save");
        cs.addOneUrlCount("POST:http://bms.lieluobo.testing/api/chart/execute/{id}");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bd/successJudge");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/projectParameter");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/mail/updateSettings");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/hrManager/overview");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/account/repeat");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/cwResumes");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/getOneAll?id=7l6hbfhto2a8");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/resume");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/bdUser");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/workflow/{id}/records");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/subscriberProjects");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/resume/educations");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/common/qiniu/token");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/mail/batchOps");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/account/hrManagers");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/location");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/im/obtainHistoryMsg");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/project/leaveMessage");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/feedback/{id}");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/remark");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/organization/contract/{id}");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/resume/check");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/financial/list");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/project/cwReportLine");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/org/{orgId}/base");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bd/org/operate");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/report/send");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/events");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/account/role/constraint");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/projectResumesGroupCDetails");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/cwResumesGroupCDetails");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/ownProjectNewResume");
        cs.addOneUrlCount("POST:http://www.lieluobo.testing/api/resume/groups");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/project/subscriber/cw/headhunters");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/order/522");
        cs.addOneUrlCount("GET:http://hr.lieluobo.testing/api/hr/order/522");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/loginHeadhunters");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/mail/accountInfo");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api//bd/crm/detail/interviewCustomerCompany");
        cs.addOneUrlCount("POST:http://cw.lieluobo.testing/api/bi/position/detail/news");
        cs.addOneUrlCount("GET:http://www.lieluobo.testing/api/account/me");
        cs.addOneUrlCount("GET:http://cw.lieluobo.testing/api/bi/detail/haveRecommendHeadhunters");
        // cs.report();
    }

    /*
    @Test
     public void groovyTest(){
         ConnectServer cs = new ConnectServer("empty");
         cs.responseBody="{\"code\":1,\"msg\":\"OK\",\"body\":[{\"id\":101716,\"name\":\"客户公司-变动状态-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":20,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[1,44],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0},{\"id\":101705,\"name\":\"客户公司-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":10,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[5,8,2],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0}],\"meta\":{\"pagination\":{\"page\":1,\"size\":10,\"total\":2,\"totalPages\":1}}}\n";
         assert  cs.groovyScript("response.code==1").equals(true);
         assert  cs.groovyScript("response.code").equals(1);
         assert  cs.groovyScript("response.body[0].id").equals(101716);
         assert  !cs.groovyScript("response.body[0].id").equals(101715);
     }
     @Test
     public void groovyTest2(){
         ConnectServer cs = new ConnectServer("empty");
         cs.responseBody="{\"code\":1,\"msg\":\"OK\",\"body\":[{\"id\":101716,\"name\":\"客户公司-变动状态-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":20,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[1,44],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0},{\"id\":101705,\"name\":\"客户公司-自动化测试预埋数据\",\"orgBdId\":204632,\"orgBdName\":\"员工4-HRBD-自动化测试预埋数据\",\"bdPriority\":10,\"hunterOrderProgress\":0,\"signStatus\":4,\"industryCodes\":[5,8,2],\"natureCode\":1,\"locationId\":310000,\"type\":1,\"projectProgress\":0}],\"meta\":{\"pagination\":{\"page\":1,\"size\":10,\"total\":2,\"totalPages\":1}}}\n";
         assert  cs.groovyScript("response.code==1").equals(true);
         assert  cs.groovyScript("response.code").equals(1);
         assert  cs.groovyScript("response.body[0].id").equals(101716);
         assert  !cs.groovyScript("response.body[0].id").equals(101715);
     }*/
    @Test
    public void subHttpIpPort() {

        assert "http://www.dev.haolie.cn".equals(ConnectServer.subHttpIpPort("http://www.dev.haolie.cn/api/account/xauth"));
        assert "http://10.0.18.42:80".equals(ConnectServer.subHttpIpPort("http://10.0.18.42:80/api/account/xauth"));
        assert "http://10.0.18.42:80".equals(ConnectServer.subHttpIpPort("10.0.18.42:80/api/account/xauth"));
    }

    @Test
    public void SeTES() {
        new Store("HAO_LIE_HR", "http://www.dev.haolie.cn", "dev");
        new Store("HAO_LIE_CW", "http://CW.dev.haolie.cn", "dev");
        new Store("HAO_LIE_HR", "http://www.dev.haolie.test", "test");
        new Store("HAO_LIE_CW", "http://CW.dev.haolie.test", "test");
        Store.setEnv("test");
        ConnectServer cs = new ConnectServer("http://www.dev.haolie.cn/abc/abc.rest");
        //  cs.autoSetBaseUrl();
        assert "http://www.dev.haolie.test".equals(cs.BASE_URL);
        assert "http://www.dev.haolie.test/abc/abc.rest".equals(cs.BASE_URL + cs.URL);
    }

    @Test
    public void uuidTest() {
        System.out.println(UUID.randomUUID().toString());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()));
        ;
        System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date(1000000000)));
    }


    @Test
    public void stringCompareTest() {
        String big = "{\n" +
                "    \"code\": 1,\n" +
                "    \"msg\": \"OK\",\n" +
                "    \"body\": {\n" +
                "        \"projectTarget\": {\n" +
                "            \"id\": 616,\n" +
                "            \"projectId\": \"5x5p5ps9s9vxvxzbh\",\n" +
                "            \"resumeId\": 342,\n" +
                "            \"status\": 53,\n" +
                "            \"code\": \"onboard\",\n" +
                "            \"lockStatus\": 2,\n" +
                "            \"createdBy\": 204641,\n" +
                "            \"updatedBy\": 204638,\n" +
                "            \"createdAt\": \"2017-07-27T15:12:28+08:00\",\n" +
                "            \"updatedAt\": \"2017-07-27T15:43:30+08:00\",\n" +
                "            \"created\": {\n" +
                "                \"avatar\": \"https://i.jihui.io/o_1bgq7p42q5oq1k7l163asngsvo7.png\",\n" +
                "                \"chineseName\": \"猎头-1-自动化测试预埋数据\",\n" +
                "                \"email\": \"hunter@test.com\",\n" +
                "                \"gender\": 1,\n" +
                "                \"id\": 204641,\n" +
                "                \"mobile\": \"19900030001\",\n" +
                "                \"workingEmail\": \"test@test.com\",\n" +
                "                \"workingPhone\": \"19900030001\",\n" +
                "                \"company\": {\n" +
                "                    \"id\": 101708,\n" +
                "                    \"name\": \"猎头公司-强状态-自动化预埋数据\",\n" +
                "                    \"type\": 2,\n" +
                "                    \"logoUrl\": null,\n" +
                "                    \"siteUrl\": \"\",\n" +
                "                    \"financingStatus\": 0,\n" +
                "                    \"empScale\": 0,\n" +
                "                    \"natureCode\": 0,\n" +
                "                    \"locationId\": 310000,\n" +
                "                    \"locationDetail\": \"\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"managerId\": 1088,\n" +
                "                    \"createdAt\": \"2017-07-12T14:44:29+08:00\",\n" +
                "                    \"updatedAt\": \"2017-07-31T15:56:32+08:00\",\n" +
                "                    \"createdBy\": 204628,\n" +
                "                    \"updatedBy\": 204641,\n" +
                "                    \"industryCodes\": [],\n" +
                "                    \"signStatus\": 6,\n" +
                "                    \"orderProgress\": 0,\n" +
                "                    \"projectProgress\": 0,\n" +
                "                    \"hunterOrderProgress\": 3,\n" +
                "                    \"hunterProjectProgress\": 0\n" +
                "                },\n" +
                "                \"imid\": \"149984216387509\",\n" +
                "                \"displayName\": \"猎头-1-自动化测试预埋数据\",\n" +
                "                \"phoneType\": \"\"\n" +
                "            },\n" +
                "            \"activeStatus\": 1,\n" +
                "            \"companyId\": 101708,\n" +
                "            \"hrAttentionType\": 2,\n" +
                "            \"orderNo\": 20170727000001,\n" +
                "            \"attachments\": []\n" +
                "        },\n" +
                "        \"resume\": {\n" +
                "            \"id\": 342,\n" +
                "            \"name\": \"候选A\",\n" +
                "            \"gender\": 2,\n" +
                "            \"mobile\": \"15021049947\",\n" +
                "            \"email\": \"zhangyaligazi@163.com\",\n" +
                "            \"birthday\": \"1991-09-01T00:00:00+09:00\",\n" +
                "            \"currentLocationCode\": 310000,\n" +
                "            \"startedWorkAt\": \"2012-01-01T08:00:00+08:00\",\n" +
                "            \"orgName\": \"美满电子科技（上海）有限公司\",\n" +
                "            \"position\": \"软件测试工程师\",\n" +
                "            \"annualSalary\": 100000,\n" +
                "            \"annualSalaryType\": 1,\n" +
                "            \"degree\": 2,\n" +
                "            \"skills\": [],\n" +
                "            \"certificates\": [],\n" +
                "            \"langs\": [],\n" +
                "            \"expectLocations\": [],\n" +
                "            \"expectSalary\": 200000,\n" +
                "            \"expectSalaryType\": 1,\n" +
                "            \"expectIndustries\": [],\n" +
                "            \"leftReason\": \"职业前景/潜在升职机会\",\n" +
                "            \"dimissionPeriod\": \"10\",\n" +
                "            \"evaluate\": \"第一个测试\",\n" +
                "            \"schoolCode\": 322003600,\n" +
                "            \"schoolName\": \"江苏理工学院\",\n" +
                "            \"schoolTypes\": [],\n" +
                "            \"seniority\": 6,\n" +
                "            \"age\": 25\n" +
                "        },\n" +
                "        \"project\": {\n" +
                "            \"id\": \"xjvxbee2m66r1hhk9\",\n" +
                "            \"type\": 2,\n" +
                "            \"status\": 0,\n" +
                "            \"title\": \"职位A-自动化测试\",\n" +
                "            \"orgId\": 102167,\n" +
                "            \"addressIds\": \"310000\",\n" +
                "            \"headcount\": 10,\n" +
                "            \"forecastTurnover\": 48000,\n" +
                "            \"salaryDetail\": {\n" +
                "                \"salaryType\": 2,\n" +
                "                \"welfares\": []\n" +
                "            },\n" +
                "            \"salaryLower\": 200000,\n" +
                "            \"salaryUpper\": 300000,\n" +
                "            \"contractId\": 101719,\n" +
                "            \"warrantyPeriod\": 3,\n" +
                "            \"rate\": 20,\n" +
                "            \"majorId\": 204665,\n" +
                "            \"hrId\": 204705,\n" +
                "            \"cwId\": 204638,\n" +
                "            \"ownerId\": 204665,\n" +
                "            \"createdBy\": 204665,\n" +
                "            \"updatedBy\": 204638,\n" +
                "            \"createdAt\": \"2016-07-27T13:21:13+08:00\",\n" +
                "            \"updatedAt\": \"2017-08-04T16:16:44+08:00\",\n" +
                "            \"resumeNum\": 1,\n" +
                "            \"successNum\": 1,\n" +
                "            \"major\": {\n" +
                "                \"avatar\": \"https://i.jihui.io/o_1bgq7p42q5oq1k7l163asngsvo7.png\",\n" +
                "                \"chineseName\": \"员工11-自动化测试预埋-集权\",\n" +
                "                \"email\": \"autotest11@test.com\",\n" +
                "                \"englishName\": \"autotest11\",\n" +
                "                \"gender\": 1,\n" +
                "                \"id\": 204665,\n" +
                "                \"mobile\": \"19900000011\",\n" +
                "                \"workingEmail\": \"\",\n" +
                "                \"workingPhone\": \"19900000011\",\n" +
                "                \"company\": {\n" +
                "                    \"id\": 1,\n" +
                "                    \"name\": \"猎萝卜网\",\n" +
                "                    \"type\": 3,\n" +
                "                    \"logoUrl\": null,\n" +
                "                    \"siteUrl\": \"\",\n" +
                "                    \"financingStatus\": 0,\n" +
                "                    \"empScale\": 0,\n" +
                "                    \"natureCode\": 0,\n" +
                "                    \"locationId\": 330100,\n" +
                "                    \"locationDetail\": \"\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"managerId\": 1088,\n" +
                "                    \"createdAt\": \"2017-04-24T15:14:46+08:00\",\n" +
                "                    \"updatedAt\": \"2017-07-31T15:56:32+08:00\",\n" +
                "                    \"createdBy\": 0,\n" +
                "                    \"updatedBy\": 21194,\n" +
                "                    \"industryCodes\": [],\n" +
                "                    \"signStatus\": 6,\n" +
                "                    \"orderProgress\": 0,\n" +
                "                    \"projectProgress\": 0,\n" +
                "                    \"hunterOrderProgress\": 1,\n" +
                "                    \"hunterProjectProgress\": 0\n" +
                "                },\n" +
                "                \"imid\": \"19900000011\",\n" +
                "                \"displayName\": \"员工11-自动化测试预埋-集权\",\n" +
                "                \"phoneType\": \"\"\n" +
                "            },\n" +
                "            \"hr\": {\n" +
                "                \"avatar\": \"https://i.jihui.io/o_1bgq7p42q5oq1k7l163asngsvo7.png\",\n" +
                "                \"chineseName\": \"HR自动化2\",\n" +
                "                \"email\": \"test002002@test.com\",\n" +
                "                \"gender\": 1,\n" +
                "                \"id\": 204705,\n" +
                "                \"mobile\": \"19900020002\",\n" +
                "                \"workingEmail\": \"\",\n" +
                "                \"company\": {\n" +
                "                    \"id\": 102167,\n" +
                "                    \"name\": \"签约客户公司-自动化测试预埋数据\",\n" +
                "                    \"type\": 1,\n" +
                "                    \"logoUrl\": null,\n" +
                "                    \"siteUrl\": \"\",\n" +
                "                    \"financingStatus\": 0,\n" +
                "                    \"empScale\": 0,\n" +
                "                    \"natureCode\": 4,\n" +
                "                    \"locationId\": 310000,\n" +
                "                    \"locationDetail\": \"上海\",\n" +
                "                    \"description\": \"\",\n" +
                "                    \"managerId\": 204665,\n" +
                "                    \"createdAt\": \"2017-07-27T11:18:58+08:00\",\n" +
                "                    \"updatedAt\": \"2017-07-27T13:28:58+08:00\",\n" +
                "                    \"createdBy\": 204632,\n" +
                "                    \"updatedBy\": 204638,\n" +
                "                    \"industryCodes\": [],\n" +
                "                    \"signStatus\": 2,\n" +
                "                    \"orderProgress\": 0,\n" +
                "                    \"projectProgress\": 30,\n" +
                "                    \"hunterOrderProgress\": 0,\n" +
                "                    \"hunterProjectProgress\": 0\n" +
                "                },\n" +
                "                \"imid\": \"150113265729136\",\n" +
                "                \"displayName\": \"HR自动化2\",\n" +
                "                \"phoneType\": \"\"\n" +
                "            },\n" +
                "            \"organization\": {\n" +
                "                \"id\": 102167,\n" +
                "                \"name\": \"签约客户公司-自动化测试预埋数据\",\n" +
                "                \"type\": 1,\n" +
                "                \"logoUrl\": null,\n" +
                "                \"siteUrl\": \"\",\n" +
                "                \"financingStatus\": 0,\n" +
                "                \"empScale\": 0,\n" +
                "                \"natureCode\": 4,\n" +
                "                \"locationId\": 310000,\n" +
                "                \"locationDetail\": \"上海\",\n" +
                "                \"description\": \"\",\n" +
                "                \"managerId\": 204665,\n" +
                "                \"createdAt\": \"2017-07-27T11:18:58+08:00\",\n" +
                "                \"updatedAt\": \"2017-07-27T13:28:58+08:00\",\n" +
                "                \"createdBy\": 204632,\n" +
                "                \"updatedBy\": 204638,\n" +
                "                \"industryCodes\": [\n" +
                "                    1,\n" +
                "                    44\n" +
                "                ],\n" +
                "                \"signStatus\": 2\n" +
                "            },\n" +
                "            \"passInterviewRate\": 0,\n" +
                "            \"interviewNum\": 1,\n" +
                "            \"forecastTurnoverLower\": 36000,\n" +
                "            \"forecastTurnoverUpper\": 60000,\n" +
                "            \"requiredMajorIds\": [],\n" +
                "            \"requiredOthers\": [],\n" +
                "            \"alterMajorIds\": [],\n" +
                "            \"alterOthers\": [],\n" +
                "            \"negativeMajorIds\": [],\n" +
                "            \"negativeOthers\": [],\n" +
                "            \"highlights\": [],\n" +
                "            \"functionCodes\": [],\n" +
                "            \"attentionType\": 0,\n" +
                "            \"positionClassifyCodes\": [\n" +
                "                363,\n" +
                "                366\n" +
                "            ],\n" +
                "            \"interviewAllNum\": 1,\n" +
                "            \"evaluateOrgQuality\": 0,\n" +
                "            \"evaluateOrgPeriod\": 0,\n" +
                "            \"evaluateNewReason\": 0,\n" +
                "            \"evaluateEntryTime\": 0,\n" +
                "            \"evaluateInterviewTime\": 0,\n" +
                "            \"evaluateDifficultyReason\": 0,\n" +
                "            \"evaluateBasicScore\": 100,\n" +
                "            \"evaluateOrgAttraction\": 0,\n" +
                "            \"evaluateSalaryAttraction\": 0,\n" +
                "            \"evaluateReceivables\": 0,\n" +
                "            \"evaluateResumeAchieve\": 0,\n" +
                "            \"evaluateOtherHeadhunter\": 0,\n" +
                "            \"evaluateProgressStage\": 0,\n" +
                "            \"evaluateKeyScore\": 100,\n" +
                "            \"scoreId\": 57,\n" +
                "            \"receiveType\": 0,\n" +
                "            \"resumeAllNum\": 1,\n" +
                "            \"subscriberType\": 0,\n" +
                "            \"requiredProfessionAbility\": [],\n" +
                "            \"requiredEducationExperience\": {\n" +
                "                \"schoolCodes\": [],\n" +
                "                \"majorCodes\": [],\n" +
                "                \"degreeCodes\": []\n" +
                "            },\n" +
                "            \"alterProfessionAbility\": [],\n" +
                "            \"alterEducationExperience\": {\n" +
                "                \"schoolCodes\": [],\n" +
                "                \"majorCodes\": [],\n" +
                "                \"degreeCodes\": []\n" +
                "            },\n" +
                "            \"negativeProfessionAbility\": [],\n" +
                "            \"negativeEducationExperience\": {\n" +
                "                \"schoolCodes\": [],\n" +
                "                \"majorCodes\": [],\n" +
                "                \"degreeCodes\": []\n" +
                "            },\n" +
                "            \"projectNo\": \"HI85057\",\n" +
                "            \"rateLower\": 0.18,\n" +
                "            \"rateUpper\": 0.2,\n" +
                "            \"cnum\": 1,\n" +
                "            \"cmaxNum\": 50,\n" +
                "            \"callNum\": 1,\n" +
                "            \"ccurrentReceiveNum\": 0,\n" +
                "            \"cmaxReceiveNum\": 0,\n" +
                "            \"cNum\": 1,\n" +
                "            \"cMaxNum\": 50,\n" +
                "            \"cAllNum\": 1,\n" +
                "            \"cCurrentReceiveNum\": 0,\n" +
                "            \"cMaxReceiveNum\": 0\n" +
                "        },\n" +
                "        \"projectTargetScore\": {\n" +
                "            \"id\": 510,\n" +
                "            \"projectId\": \"nhn68cgh8cghl57si\",\n" +
                "            \"resumeId\": 342,\n" +
                "            \"salary\": 2,\n" +
                "            \"address\": 0,\n" +
                "            \"requireScore\": 50,\n" +
                "            \"alterScore\": 0,\n" +
                "            \"allScore\": 100\n" +
                "        }\n" +
                "    }\n" +
                "}";
        String target = "{\"id\": 616,\n" +
                "            \"projectId\": \"xxx\",\n" +
                "            \"resumeId\": 342,\n" +
                "            \"status\": 53,\n" +
                "            \"code\": \"onboard\",\n" +
                "            \"lockStatus\": 2,\n" +
                "            \"createdBy\": 204641,\n" +
                "            \"updatedBy\": 204638,\n" +
                "            \"createdAt\": \"2017-07-27T15:12:28+08:00\",\n" +
                "            \"updatedAt\": \"2017-07-27T15:43:30+08:00\"}";
        System.out.println(ConnectServer.jsonCompare(big, target));

        big = "{\"code\":1,\"msg\":\"OK\",\"body\":[{\"id\":\"ibeef2ij4mn6qr8ca\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.19.38.24.024\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T19:38:15+08:00\",\"updatedAt\":\"2017-08-04T19:38:15+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"XC09304\"},{\"id\":\"9st99vvx2jjmm66rp\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.19.35.14.014\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T19:35:05+08:00\",\"updatedAt\":\"2017-08-04T19:35:05+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"WX04194\"},{\"id\":\"lk55t9uvt9uvab0e3\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.19.33.10.010\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T19:33:01+08:00\",\"updatedAt\":\"2017-08-04T19:33:01+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"XI28713\"},{\"id\":\"vyhkk55ppss99vvx1\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.19.28.43.043\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T19:28:34+08:00\",\"updatedAt\":\"2017-08-04T19:28:34+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"JN60754\"},{\"id\":\"af7st9uvf2ij4mn6o\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.19.17.40.040\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T19:17:31+08:00\",\"updatedAt\":\"2017-08-04T19:17:31+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"XX58221\"},{\"id\":\"480e0eijijn6n68cy\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.17.46.25.025\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T17:46:16+08:00\",\"updatedAt\":\"2017-08-04T17:46:16+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"QY47622\"},{\"id\":\"507suvyzuvyz0eijl\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.16.08.16.016\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T16:08:07+08:00\",\"updatedAt\":\"2017-08-04T16:08:07+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"ZS42949\"},{\"id\":\"unyz0eijn68cghl5u\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.15.48.16.016\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T15:48:07+08:00\",\"updatedAt\":\"2017-08-04T16:05:43+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"ZW40129\"},{\"id\":\"xz5p5ps9s9vxvxzbh\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.14.37.36.036\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T14:37:27+08:00\",\"updatedAt\":\"2017-08-04T16:05:43+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"NU59647\"},{\"id\":\"be7st9uvf2ij4mn6o\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.13.31.57.057\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T13:31:48+08:00\",\"updatedAt\":\"2017-08-04T16:05:43+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"EN12195\"},{\"id\":\"dp6rrcc1pss99vvxm\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.04.11.35.54.054\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-04T11:35:45+08:00\",\"updatedAt\":\"2017-08-04T16:05:43+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"VM85964\"},{\"id\":\"9juvab0e4mn6d1ght\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.03.20.38.57.057\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-03T20:38:48+08:00\",\"updatedAt\":\"2017-08-04T11:06:32+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"JV75341\"},{\"id\":\"j56rc1hkc1hk5ps9j\",\"status\":0,\"title\":\"职位A-自动化测试2017.08.03.19.16.40.040\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-08-03T19:16:31+08:00\",\"updatedAt\":\"2017-08-04T11:06:32+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"WG34721\"},{\"id\":\"1cijijn6n68c8cgh0\",\"status\":0,\"title\":\"职位C-自动化测试\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-01-01T11:13:43+08:00\",\"updatedAt\":\"2017-08-04T11:06:33+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":2,\"projectNo\":\"HV96042\"},{\"id\":\"yxl5t9uvt9uvab0e3\",\"status\":0,\"title\":\"职位B\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2017-01-01T11:08:32+08:00\",\"updatedAt\":\"2017-08-04T11:06:33+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":0,\"projectNo\":\"IY59183\"},{\"id\":\"icijijn6n68c8cgh0\",\"status\":0,\"title\":\"职位A-自动化测试\",\"orgId\":102167,\"hrId\":204705,\"cwId\":204638,\"createdAt\":\"2016-07-27T13:21:13+08:00\",\"updatedAt\":\"2017-08-04T16:16:44+08:00\",\"cw\":{\"id\":204638,\"displayName\":\"员工0-多权限-自动化预埋数据\"},\"hr\":{\"id\":204705,\"displayName\":\"HR自动化2\"},\"organization\":{\"name\":\"签约客户公司-自动化测试预埋数据\"},\"evaluateBasicScore\":100,\"evaluateKeyScore\":100,\"resumeAllNum\":1,\"projectNo\":\"HI85057\"}],\"meta\":{\"pagination\":{\"page\":1,\"size\":100,\"total\":16,\"totalPages\":1}}}";


        target = "{\"id\": \"9st99vvx2jjmm66\",\n" +
                "            \"status\": 0,\n" +
                "            \"title\": \"职位A-s自动化测试2017.08.04.19.35.14.014\",\n" +
                "            \"orgId\": 102167,\n" +
                "            \"hrId\": 204705,\n" +
                "            \"cwId\": 204638,\n" +
                "            \"createdAt\": \"2017-08-04T19:35:05+08:00\",\n" +
                "            \"updatedAt\": \"2017-08-04T19:35:05+08:00\"}";
        big = GsonJsonUtil.gsonPretty.toJson(GsonJsonUtil.gsonPretty.fromJson(big, Map.class));
        target = GsonJsonUtil.gsonPretty.toJson(GsonJsonUtil.gsonPretty.fromJson(target, Map.class));
        System.out.println(ConnectServer.jsonCompare(big, target));

    }
}