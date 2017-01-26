package Page_Objects;

import java.util.Hashtable;

import Libraries.Driver_Script;

public class Voucher_Status extends Driver_Script {

public static String Voucher_Status_Page(String locator){
		
		try{
		Hashtable<String, String> hs = new Hashtable<String, String>();
		
		hs.put("Load_Type", "id#loadType");
		hs.put("Load_Parameter", "id#loadParameter");
		hs.put("Load_Subscriber", "id#btnLoadSubscriber");
		hs.put("Menu_Items", "xpath#//a[@class='menu-icon dropdown-toggle']");
		hs.put("Topup_Menu", "xpath#//a[@class='catgLinkID']//span[@class='topup-menu-image']");
		hs.put("Voucher_Status", "xpath#//div[@class='voucher-status-thumbnail']//div[@class='thumbnail-icon']");
		hs.put("Voucher_Type", "id#voucherType");
		hs.put("Apply", "id#btnVoucherStatus");
		hs.put("Reset", "id#btnResetVoucherStatus");
		hs.put("Mandatory_Alert_Message", "id#resultdiv");
		hs.put("Voucher_Code", "id#voucherCode");
		hs.put("Alert_Message", "xpath#.//*[@id='dvVoucherDetails']//*[contains(@class, 'errorMessage')]");
		hs.put("Voucher_Number", "xpath#.//*[@id='dvVoucherDetails']//h4/b");
		hs.put("Status", "xpath#//strong[contains(text(),'Status')]/../../div[2]");
		hs.put("Activation_Date", "xpath#//strong[contains(text(),'Activation Date')]/../../div[2]");
		hs.put("Used_MSISDN", "xpath#//strong[contains(text(),'Used MSISDN')]/../../div[2]");
		hs.put("Blocked_Date", "xpath#//strong[contains(text(),'Blocked Date')]/../../div[2]");
		hs.put("Blocked_Reason", "xpath#//strong[contains(text(),'Blocked Reason')]/../../div[2]");
		hs.put("Recharge_Date", "xpath#//strong[contains(text(),'Recharge Date')]/../../div[2]");
		hs.put("Reseller_ID", "xpath#//strong[contains(text(),'Reseller ID')]/../../div[2]");
		hs.put("Activation_Level", "xpath#//strong[contains(text(),'Activation Level')]/../../div[2]");
		hs.put("Reseller_Message", "xpath#//strong[contains(text(),'Reseller Message')]/../../div[2]");
		hs.put("Plan_Name", "xpath#//strong[contains(text(),'Plan Name')]/../../div[2]");
		hs.put("Bundle", "xpath#//strong[contains(text(),'Bundle')]/../../div[2]");
		hs.put("Face_Value", "xpath#//strong[contains(text(),'Face Value')]/../../div[2]");
		hs.put("Onnet_Mins", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[2]/td[2]");
		hs.put("Onnet_SMS", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[2]/td[3]");
		hs.put("MTOnnet_Mins", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[3]/td[2]");
		hs.put("MTOnnet_SMS", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[3]/td[3]");
		hs.put("MTOffnet_Mins", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[4]/td[2]");
		hs.put("MTOffnet_SMS", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[4]/td[3]");
		hs.put("Zone1_Mins", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[6]/td[2]");
		hs.put("Zone1_SMS", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[6]/td[3]");
		hs.put("Zone2_Mins", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[7]/td[2]");
		hs.put("Zone2_SMS", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[7]/td[3]");
		hs.put("Zone3_Mins", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[8]/td[2]");
		hs.put("Zone3_SMS", "xpath#.//*[@id='dvVoucherDetails']//table/tbody/tr[8]/td[3]");
		
		hs.put("close_button", "xpath#//li[@title='Subscriber Logout']");
		Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
		
		String locate = hs.get(locator).split("\\#")[1];
		
		return locate;
		
		}catch(Exception e){
			
			System.out.println("Error occurred in POM classes :"+e);
			
		}
		return null;
	}
	
	
}