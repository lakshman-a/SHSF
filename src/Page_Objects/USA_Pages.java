package Page_Objects;

import java.util.Hashtable;

import Libraries.Driver_Script;
import Libraries.Report_Functions;
import Utility.ReadExcel;

public class USA_Pages extends Driver_Script{
	
	/**
	 * 
	 * @Objective <b>Get the Input from Data Sheet</b>
	 * @author <b>LAKSHMAN</b>
	 * @since <b>17-OCT-16</b>
	 */

	public static String RetrieveTestDataValue(String strFunctionName,String strColumnName,int strExecEventFlag) throws Exception{
		String strData=null;

		if(strExecEventFlag!=0){
			strData =ReadExcel.RetrieveTestDataFromSheet(Filepath, EnvironmentValue.getProperty("App_Component_Name"), strColumnName, gblrecordsCounter);
		}
		return strData;
	}

	public static String ChangePlan(String locator){

		try{

			Hashtable<String, String> hs = new Hashtable<String, String>();

			hs.put("services_Select", "id#loadType");
			hs.put("mobileNo_Textbox", "id#loadParameter");
			hs.put("loadSubscriber_Button", "id#btnLoadSubscriber");
			hs.put("staff_MenuDropdown", "xpath#//a[@class='menu-icon dropdown-toggle']");
			hs.put("Bundle_Icon", "xpath#//a[@class='catgLinkID' and @title='Bundle']//span");
			hs.put("ChangePlan_Icon", "xpath#//div[@title='Change Plan']/following::*");
			
			hs.put("currentPlan", "id#currentPlan");
			//hs.put("currentPlan", "id#currentPlan");
			hs.put("disabledcurrentPlan", "xpath#//input[@id='currentPlan' and @disabled='disabled']");
			hs.put("ddlnewPlan", "id#ddlnewPlan");
			hs.put("reason", "id#reason");
			
			//Agent to submit request:
			hs.put("btnSubmit", "id#btnSubmit");
			
			//admin to submit request
			hs.put("btnChangeplanAccept", "id#btnChangeplanAccept");
			hs.put("btnChangeplanReset", "id#btnChangeplanReset");
			
			//Admin to approve/Reject:
			hs.put("btnChangeplanApprove", "id#btnChangeplanApprove");
			hs.put("btnChangeplanReject", "id#btnChangeplanReject");
			
			//Reject Reason Box:
			hs.put("rejectReason_Id", "id#rejectReason_Id");
			
			
			hs.put("planChngeHistory", "id#planChngeHistory");
			//hs.put("responseMessage", "id#responseMessage");
			hs.put("responseMessage", "xpath#//label[@id='responseMessage' and (@class='true' or @class='false')]");
			
			hs.put("disabledMessage", "xpath#//label[contains(@style,'color')]");
			
			hs.put("close_button", "xpath#//li[@title='Subscriber Logout']");
			
			//Approval
			hs.put("pendingApproval_Body_Type", "xpath#//b[contains(text(),'Type')]");
			hs.put("linkChangePlan", "linktext#Change Plan");
			
			//Full view
			hs.put("DOB_Icon", "xpath#//li[@title='Subscriber View']");
			//hs.put("lbl_Plan", "xpath#//strong[contains(text(),'Plan')]/following::span[@class='ng-binding']");
			//hs.put("lbl_Plan", "xpath#//strong//following::*//span[contains(@style,'color: green')]");
			hs.put("lbl_Plan", "xpath#//strong//following::*//span[contains(text(),'AUTO')]");
			
			//History table:
			hs.put("tbl_ChangePlanHistory", "xpath#//div[@class='bundle-open1 changeplanHistory']//table");
			hs.put("btn_OkPlanHistory", "id#OkPlanHistory");
			
				
			Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
			String locate = hs.get(locator).split("\\#")[1];
			return locate;
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}
	
	
	public static String Pending_MSISDN(String locator,String strMSISDN,int strEventFlag) throws Exception{

		String MSISDN=null;

		try{
			MSISDN=RetrieveTestDataValue("", strMSISDN, strEventFlag);
			System.out.println("strMSISDN is : "+strMSISDN);

			if(MSISDN==null){
				Report_Functions.ReportEventFailure(doc,  "",  "Required details for Dynamic Locator are not provided in the data sheet", false);
				return null;
			}

			System.out.println("locator is : "+locator);

			Hashtable<String, String> hs = new Hashtable<String, String>();

			hs.put("strPendingMSISDN", "xpath#//td[@aria-describedby='pendingItemTable_Msisdn']//a[@title='"+MSISDN+"']");

			Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
			String locate = hs.get(locator).split("\\#")[1];
			return locate;
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}
	
	public static String ChangePlanHistoryTable(String locator,String rowNumber,int strEventFlag) throws Exception{

		String rowNumberInTable=null;

		try{
			rowNumberInTable=RetrieveTestDataValue("", rowNumber, strEventFlag);
			System.out.println("Row Number is : "+rowNumberInTable);
			
			if(rowNumberInTable==null){
				Report_Functions.ReportEventFailure(doc,  "",  "Required details for Dynamic Locator are not provided in the data sheet", false);
				return null;
			}

			Hashtable<String, String> hs = new Hashtable<String, String>();

			String rowType=null;
			if(rowNumberInTable.equalsIgnoreCase("header")||rowNumberInTable.equalsIgnoreCase("head")||rowNumberInTable.equalsIgnoreCase("primary")){
				rowType="th";
				rowNumberInTable="1";
			}else{
				rowType="td";
			}

			hs.put("tblChangePlan_Date", "xpath#//div[@class='bundle-open1 changeplanHistory']//table//tr["+rowNumberInTable+"]//"+rowType+"[1]");
			hs.put("tblChangePlan_Time", "xpath#//div[@class='bundle-open1 changeplanHistory']//table//tr["+rowNumberInTable+"]//"+rowType+"[2]");
			hs.put("tblChangePlan_CurrentPlan", "xpath#//div[@class='bundle-open1 changeplanHistory']//table//tr["+rowNumberInTable+"]//"+rowType+"[3]");
			hs.put("tblChangePlan_PreviousPlan", "xpath#//div[@class='bundle-open1 changeplanHistory']//table//tr["+rowNumberInTable+"]//"+rowType+"[4]");
			hs.put("tblChangePlan_Channel", "xpath#//div[@class='bundle-open1 changeplanHistory']//table//tr["+rowNumberInTable+"]//"+rowType+"[5]");
			hs.put("tblChangePlan_Reason", "xpath#//div[@class='bundle-open1 changeplanHistory']//table//tr["+rowNumberInTable+"]//"+rowType+"[6]");

			Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
			String locate = hs.get(locator).split("\\#")[1];
			return locate;
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}
	

}
