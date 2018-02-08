package com.qa;

public class CommonTest {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	//	SSOLogin legal = new SSOLogin("/login","test","post");
//		String wksso = legal.getWKSSO("13162108888","111111","https://yun2.test.wkzf/cpbs/customer/index.action");
//		String wksso = legal.getHrWKSSO("13162108888","111111","https://hryun2.test.wkzf:8178/account/getUsers.action");

//		System.out.println("getWKSSO test>>>>>>>>>>>>>>>."+wksso);

//		MonkeyKingBackstageServer mk = new MonkeyKingBackstageServer("/estatePartner/addOrModifyPartnerCheckInfo.action","test","post");
//		try {
//			mk.setHeader("Host","yun2.test.wkzf");
//			mk.setHeader("Cookie",wksso);
//			mk.setParam("id","2","int");
//			mk.setParam("theoryCheck","125","int");
//			mk.setParam("practiceCheck","3","string");
//			mk.setParam("agentId","19223","int");
//			mk.testRun("status");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			ExceptionUtil.printlnSo(e);
//		}
//		System.out.println("first test>>>>>>>>>>>>>>>.");
//		String execution = legal.firstLoginRun(ConfigConstantsTest.SSO_BASE_URL + legal.URL,legal.getData());
//		System.out.println("second test>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
//		legal.setParam("execution", execution, "string");
//		legal.setHeader("Cookie", legal.JSESSIONID);
//		System.out.println("JSESSIONID:----------->"+legal.JSESSIONID);
//		String CASTGC = legal.secondLoginRun(ConfigConstantsTest.SSO_BASE_URL + legal.URL,legal.getData());
//		System.out.println("getwksso test>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		legal.setHeader("Cookie", CASTGC);
//		System.out.println("getwksso test>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>."+legal.getwksso("https://yun2.test.wkzf/fs2/customerReceivables/index",legal.getData()));
//		System.out.println("end test>>>>>>>>>>>>>>>.");
	}

	public void ss(){
		//import com.qa.db.*;
		Object obj= new Store("headers","{author:\"haolie\"");
		ConnectServer obj1=new	ConnectServer("http://www.lieluobo.testing/api/account/team/byLeader");
		obj1.setBody("{\"leaderId\":null}");
		obj1.post();
		check(obj1.jsonStructure("code,msg")+"",	true);
		check(obj1.jsonValue("msg"),	"请求参数不可为空");
	}


	public void check(String src,Object object){

	}

	@org.testng.annotations.Test
	public void ch(){
		long stime=System.currentTimeMillis();
		Object obj= new Store("headers","{author:\"haolie\"");
		ConnectServer obj1=new	ConnectServer("http://www.lieluobo.testing/api/account/team/byLeader");
		System.out.println(System.currentTimeMillis()-stime);

		return ;
	}

}
