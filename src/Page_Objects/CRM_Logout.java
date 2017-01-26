package Page_Objects;

import java.util.Hashtable;

import Libraries.Driver_Script;

public class CRM_Logout extends Driver_Script {


	public static String Logout_Page(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
			
			hs.put("menu_Dropdown", "id#dropdownMenu1");
			hs.put("logout_Button", "id#logoutCRM");

			Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
			String locate = hs.get(locator).split("\\#")[1];
			return locate;
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

}
