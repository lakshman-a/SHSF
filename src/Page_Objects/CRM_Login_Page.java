package Page_Objects;

import java.util.Hashtable;

import Libraries.Driver_Script;

public class CRM_Login_Page extends Driver_Script{

	public static String Login_Page(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
		
			hs.put("txtbox_UserName", "id#UserName");
			hs.put("txtbox_Password", "id#Password");
			hs.put("btn_LogIn", "id#Login");

			Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
			String locate = hs.get(locator).split("\\#")[1];
			return locate;
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

}