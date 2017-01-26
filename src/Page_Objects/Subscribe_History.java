package Page_Objects;

import java.util.Hashtable;

import Libraries.Driver_Script;
import Utility.ReadExcel;

public class Subscribe_History extends Driver_Script {
	
	public static String subHistoryPage(String locator){
		
		try{
		Hashtable<String, String> hs = new Hashtable<String, String>();
			
		hs.put("simButton", "xpath#//a[@title='Sim']");
		hs.put("subscribeHistBtn", "xpath#//div[@title='Subscriber History']");
		hs.put("fromDate", "id#txtfromdate");
		hs.put("toDate", "id#txtToDate");
		hs.put("type", "id#ddType");
		hs.put("submit", "id#btnSubmit");
		hs.put("reset", "id#btnReset");
		
		hs.put("confirmMsg", "xpath#//span[@id='Msg' and contains(@style, 'rgb')]");
		hs.put("tableView", "id#divGETSubscriberHistory");
		
		//Alert validation
		hs.put("valFromDate", "xpath#//input[@id='txtfromdate' and contains(@aria-required, 'true')]");
		hs.put("valToDate", "xpath#//input[@id='txtToDate' and contains(@aria-required, 'true')]");
		hs.put("valType", "xpath#//select[@id='ddType' and contains(@aria-required, 'true')]");
		
		
		
		Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
		
		String locate = hs.get(locator).split("\\#")[1];
		
		return locate;
		
		}catch(Exception e){
			
			System.out.println("Error occurred in POM classes :"+e);
			
		}
		return null;
	}

	
	public static String RetrieveTestDataValue(String strFunctionName,String strColumnName,int strExecEventFlag) throws Exception{
		String strData=null;

		if(strExecEventFlag!=0){
			strData =ReadExcel.RetrieveTestDataFromSheet(Filepath, EnvironmentValue.getProperty("App_Component_Name"), strColumnName, gblrecordsCounter);
		}
		return strData;
	}

	public static String tableValues(String locator, String tableValues) throws Exception{

	String value = RetrieveTestDataValue("Subscribe_History", tableValues, 1);
	
	
	try{

		Hashtable<String, String> hs = new Hashtable<String, String>();
		
		hs.put("tableType", "xpath#//td[@aria-describedby='GETSubscriberHistory_type' and contains(@title, '"+value+"')]");
		hs.put("status", "xpath#//td[@aria-describedby='GETSubscriberHistory_type' and contains(@title, '"+value+"')]//following-sibling::td[@aria-describedby='GETSubscriberHistory_status']");
		hs.put("reason", "xpath#//td[@aria-describedby='GETSubscriberHistory_type' and contains(@title, '"+value+"')]//following-sibling::td[@aria-describedby='GETSubscriberHistory_reason']");
		hs.put("ticketID", "xpath#//td[@aria-describedby='GETSubscriberHistory_type' and contains(@title, '"+value+"')]//following-sibling::td[@aria-describedby='GETSubscriberHistory_ticketId']");
		hs.put("submittedDate", "xpath#//td[@aria-describedby='GETSubscriberHistory_type' and contains(@title, '"+value+"')]//following-sibling::td[@aria-describedby='GETSubscriberHistory_submitedDate']");
		hs.put("userName", "xpath#//td[@aria-describedby='GETSubscriberHistory_type' and contains(@title, '"+value+"')]//following-sibling::td[@aria-describedby='GETSubscriberHistory_userName']");
		
		Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
		String locate = hs.get(locator).split("\\#")[1];
		
		return locate;
	}catch(Exception e){
		log.info("Error occurred in POM classes :"+e);
	}
	return null;
}



}
