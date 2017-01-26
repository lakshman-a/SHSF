package Page_Objects;

import java.util.Hashtable;

import Libraries.Driver_Script;
import Utility.ReadExcel;

public class Cancel_Initail_Loc extends Driver_Script {
	
	
	public static String RetrieveTestDataValue(String strFunctionName,String strColumnName,int strExecEventFlag) throws Exception{
		String strData=null;

		if(strExecEventFlag!=0){
			strData =ReadExcel.RetrieveTestDataFromSheet(Filepath, EnvironmentValue.getProperty("App_Component_Name"), strColumnName, gblrecordsCounter);
		}
		return strData;
	}
	
	
	public static String cancelInitalLocPage(String locator){
		
		try{
		Hashtable<String, String> hs = new Hashtable<String, String>();
				
		
		hs.put("simButton", "xpath#//a[@title='Sim']");
		hs.put("initialLocBtn", "xpath#//div[@title='Cancel Initial Location']");
		hs.put("msisdn", "id#txtMsisdnImsi");
		hs.put("submitButton", "id#btnSubmit");
		hs.put("confirm_Message", "xpath#//span[@id='lblError' and contains(@style, 'rgb')]");
		hs.put("historyBtn", "id#btnView");
		hs.put("tableView", "id#gbox_mainGrdLocation");
		hs.put("validationMsg", "xpath#//b[@class='false']");
		hs.put("networkAccessDD", "id#ddNetworkAccess");
		hs.put("networkChkbox", "id#chkRetain");
		
		
		Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
		
		String locate = hs.get(locator).split("\\#")[1];
		
		return locate;
		
		}catch(Exception e){
			
			System.out.println("Error occurred in POM classes :"+e);
			
		}
		return null;
	}


	public static String tableValues(String locator, String tableValues) throws Exception{

	String value =RetrieveTestDataValue("Cancel_Initial_Location", tableValues, 1);
	
	
	try{

		Hashtable<String, String> hs = new Hashtable<String, String>();
		
		hs.put("msisdnTableValue", "xpath#//td[@aria-describedby='mainGrdLocation_MSISDN' and contains(@title, '"+value+"')]");
		hs.put("imsiTableValue", "xpath#//td[@aria-describedby='mainGrdLocation_MSISDN' and contains(@title, '"+value+"')]//following-sibling::td[@aria-describedby='mainGrdLocation_IMSI']");
		hs.put("submitByTableValue", "xpath#//td[@aria-describedby='mainGrdLocation_MSISDN' and contains(@title, '"+value+"')]//following-sibling::td[@aria-describedby='mainGrdLocation_submittedBy']");
		hs.put("submitDateTableValue", "xpath#//td[@aria-describedby='mainGrdLocation_MSISDN' and contains(@title, '"+value+"')]//following-sibling::td[@aria-describedby='mainGrdLocation_submittedDate']");
		
		
		hs.put("networkAcsTableValue", "xpath#//td[@aria-describedby='mainGrdLocation_MSISDN' and contains(@title, '"+value+"')]//following-sibling::td[@aria-describedby='mainGrdLocation_drpNAM']");
		hs.put("retailLtnTableValue", "xpath#//td[@aria-describedby='mainGrdLocation_MSISDN' and contains(@title, '"+value+"')]//following-sibling::td[@aria-describedby='mainGrdLocation_retainLocation']");
		
		
		
		Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
		String locate = hs.get(locator).split("\\#")[1];
		
		return locate;
	}catch(Exception e){
		log.info("Error occurred in POM classes :"+e);
	}
	return null;
}

	
}
