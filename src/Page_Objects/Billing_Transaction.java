package Page_Objects;

import java.util.Hashtable;

import Libraries.Driver_Script;
import Utility.ReadExcel;

public class Billing_Transaction extends Driver_Script {
	
public static String billingTransactionPage(String locator){
		
		try{
		Hashtable<String, String> hs = new Hashtable<String, String>();
				
		hs.put("billingMenuDropdown", "xpath#//a[@class='menu-icon dropdown-toggle']");
		hs.put("billingButton", "xpath#//a[@title='Billing']");
		hs.put("billingTnsBtn", "xpath#//div[@title='Billing Transaction']");
		hs.put("ticketID", "id#txtTicketID");
		hs.put("status", "id#transStatus");
		hs.put("type", "id#transType");
		hs.put("fromDate", "id#FromdatePick");
		hs.put("toDate", "id#TodatePick");
		hs.put("search", "id#btnLoad");
		hs.put("reset", "id#btnResetTrans");
		hs.put("tableView", "id#gview_mainTransTable");
		
		
		//Get Values from tables
		
		hs.put("verifyTnsID", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']");
		hs.put("typeVerify", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_Type']");
		hs.put("MSISDN", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_MSISDN']");
		hs.put("debitAmt", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_DebitAmt']");
		hs.put("reCreditAmt", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_RecreditAmt']");
		
		hs.put("dialledNo", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_DialledNo']");
		hs.put("dialledDate", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_DialledDate']");
		hs.put("duration", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_Duration']");
		
		hs.put("ticketIDVerify", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_TicketId']");
		hs.put("reason", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_Reason']");
		hs.put("comments", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_Comments']");
		hs.put("submitBy", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_SubmittedBy']");
		hs.put("submitDate", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_RequestDate']");
		hs.put("authoriseDate", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_Authoriseddate']");
		hs.put("authoriseBy", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_AuthorisedBy']");
		hs.put("authoriseStatus", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_AuthorisedStatus']");
		hs.put("prevBal", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_OldBalance']");
		hs.put("curBal", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_NewBalance']");
		hs.put("channel", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_Channel']");
		
		hs.put("MSISDNFrom", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_MSISDNFrom']");
		hs.put("MSISDNTo", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_MSISDNTo']");
		hs.put("transferAmt", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_TransferAmt']");
		hs.put("adminCmts", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@tabindex, '-1') and not (contains(@style, 'height'))]//td[@aria-describedby='subTransTable_Id']/following-sibling::td[@aria-describedby='subTransTable_AdminComments']");
		
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

		String value =RetrieveTestDataValue("Cancel_Initial_Location", tableValues, 1);
		String getRunTime = Runtimevalue.getProperty(value);
	
		try{

			Hashtable<String, String> hs = new Hashtable<String, String>();
		
			hs.put("transactionID", "xpath#//tr[@class='ui-widget-content jqgrow ui-row-ltr' and contains(@style, 'height')]//td//a[text()='"+getRunTime+"']");
			
			Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
			String locate = hs.get(locator).split("\\#")[1];
		
			System.out.println("Locate ::"+locate);
		
			return locate;
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	 }	


	}
