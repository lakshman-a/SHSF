package Page_Objects;

import java.util.Hashtable;

import Libraries.Driver_Script;
import Utility.ReadExcel;

public class ValueAddedServices extends Driver_Script{
	
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

	public static String SafeCustody(String locator){

		try{

			Hashtable<String, String> hs = new Hashtable<String, String>();

			hs.put("services_Select", "id#loadType");
			hs.put("mobileNo_Textbox", "id#loadParameter");
			hs.put("loadSubscriber_Button", "id#btnLoadSubscriber");
			hs.put("staff_MenuDropdown", "xpath#//a[@class='menu-icon dropdown-toggle']");
			hs.put("VAS_Icon", "xpath#//a[@class='catgLinkID' and @title='VAS']//span");
			hs.put("safeCustody_Icon", "xpath#//div[@title='MSISDN Safe Custody']/following::*");
			
			hs.put("lblStatus", "id#lblStatus");
			hs.put("txtEmail", "id#txtEmail");
			hs.put("input_StartDate", "id#StartDate");
			hs.put("lst_SafeDateYear", "id#SafeDateYear");
			hs.put("lst_SafeDateMonth", "id#SafeDateMonth");
			hs.put("lbl_EndDate", "id#EndDate");
			hs.put("lbl_SafeHoldingFee", "id#SafeHoldingFee");
			hs.put("btnConfirm", "id#btnConfirm");
			hs.put("lbl_moneyType", "xpath#//input[@id='SafeHoldingFee']/following::*");
			hs.put("lbl_Notes", "xpath#//strong[contains(.,'Notes')]");
			hs.put("lbl_NotesContent", "xpath#//strong[contains(.,'Notes')]/following::*");
			
			//Confirm Page:
			hs.put("lblConfirmStatus", "id#spStatus");
			hs.put("lblConfirmEmail", "id#spEmail");
			hs.put("lblConfirmStartDate", "id#spStartDate");
			hs.put("lblConfirmSafeDate", "id#spSafeDate");
			hs.put("lblConfirmEndDate", "id#spEndDate");
			hs.put("lblConfirmSafeHoldingFee", "id#spSafeHoldingFee");
			hs.put("lblConfirmMoneySymbol", "xpath#//label[@id='spSafeHoldingFee']/following::*");
			hs.put("btnSubmit", "id#btnSubmit");
			hs.put("btnBack", "id#btnBack");
			
			//Date:
			hs.put("selectYear", "xpath#//select[@data-handler='selectYear']");
			hs.put("selectMonth", "xpath#//select[@data-handler='selectMonth']");
			hs.put("selectDate31", "linktext#31");
				
			
			hs.put("NumberLockerMsg", "id#NumberLockerMsg");
			hs.put("close_button", "xpath#//li[@title='Subscriber Logout']");
			
			Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
			String locate = hs.get(locator).split("\\#")[1];
			return locate;
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}
	
	

}
