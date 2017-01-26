package Page_Objects;

import java.util.Hashtable;

import Libraries.Driver_Script;

public class Topup_Failure_History extends Driver_Script{
	
public static String Topup_Failure_History_Page(String locator){
		
		try{
		Hashtable<String, String> hs = new Hashtable<String, String>();
		hs.put("Load_Type", "id#loadType");
		hs.put("Load_Parameter", "id#loadParameter");
		hs.put("Load_Subscriber", "id#btnLoadSubscriber");
		hs.put("Menu_Items", "xpath#//a[@class='menu-icon dropdown-toggle']");
		hs.put("Topup_Menu", "xpath#//a[@class='catgLinkID']//span[@class='topup-menu-image']");
		hs.put("Topup_Failure_History", "xpath#//div[@class='topup-history-thumbnail']//div[@class='thumbnail-icon']");
		hs.put("From_Date", "id#fromDate");
		hs.put("To_Date", "id#toDate");
		hs.put("Topup_Mode", "id#ddTopupMode");
		hs.put("Type", "id#ddType");
		hs.put("Submit", "id#btnSubmit");
		hs.put("History_Table", "xpath#//table[@aria-labelledby='gbox_CreditCardDetails']");
		hs.put("Voucher_History_Table", "xpath#//table[@aria-labelledby='gbox_TopupVoucherDetails']");
		hs.put("Calender_Year_Dropdown", "xpath#//*[@class='ui-datepicker-title']/select[@class='ui-datepicker-year']");
		
		Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
		
		String locate = hs.get(locator).split("\\#")[1];
		
		return locate;
		
		}catch(Exception e){
			
			System.out.println("Error occurred in POM classes :"+e);
			
		}
		return null;
	}

}
