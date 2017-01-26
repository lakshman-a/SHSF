package Page_Objects;

import java.util.Hashtable;

import Libraries.Driver_Script;

public class Registration_POL extends Driver_Script {

	public static String Registration_Page(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();
		
			hs.put("txtNewRegistration", "id#subsParameter");
			hs.put("btnNewRegister", "id#btnNewRegister");
			hs.put("rdbPersonal", "id#rdopersonal");
			hs.put("lstPOLtitle", "id#POLtitle");
			
			hs.put("txtfirstName", "id#txtfirstName");
			hs.put("txtlastName", "id#txtlastName");
			hs.put("txtPolDOB", "id#txtPolDOB");
			hs.put("select_Personallang", "id#ddlPersonallang");
			hs.put("txtStreet", "id#txtStreet_1");
			hs.put("txtCity", "id#txtCity_1");
			hs.put("txtPostCode1", "id#txtCode_1");
			hs.put("txtPostCode2", "id#txtCodesuff_1");
			hs.put("txtHouseNo", "id#txtHouseNo_1");
			hs.put("listAddressResult", "id#lstAddressResult");
			hs.put("btnAcceptAddress", "id#btnAcceptAddress");
			hs.put("ModalSpace", "id#myModel");
			hs.put("Icon_SearchAddress", "xpath#//a[@id='btnFindAddressPers']//span[@class='glyphicon glyphicon-search']");	
			hs.put("rdomobile", "id#rdomobile");
			hs.put("LabelMSISDN", "id#MSISDN");
			hs.put("txtsimnumber", "id#txtsimnumber");
			hs.put("rdopesel", "id#rdopesel");
			hs.put("txtpesel", "id#txtpesel");
			
			hs.put("cbxMarketingSMS1", "id#cbxMarketingSMS1");		
			hs.put("chktermsconditions_1", "id#chktermsconditions_1");		
			hs.put("btnPOLcancel", "id#btnPOLcancel");		
			hs.put("btnPOLnext", "id#btnPOLnext");		
			
			hs.put("lblTitle", "id#lblTitle");	
			hs.put("lblfirstname", "id#lblfirstname");	
			hs.put("lbllastname", "id#lbllastname");	
			
			hs.put("lblDOB", "id#lblDOB");	
			hs.put("lblLanguage", "id#lblLanguage");	
			hs.put("lblStreet", "id#lblStreet");	
			hs.put("lblCity", "id#lblCity");	
			hs.put("lblcode", "id#lblcode");	
			hs.put("lblHouseNo", "id#lblHouseNo");	
			hs.put("lblMSISDN_1", "id#lblMSISDN_1");	
			hs.put("lblsimnumber_1", "id#lblsimnumber_1");	
			hs.put("lblidproof", "id#lblidproof");	
			hs.put("btnPOLback", "id#btnPOLback");	
			hs.put("btnPOLsubmit", "id#btnPOLsubmit");	
						
			hs.put("label_Regmessage", "xpath#//label[@id='btnCirclePOLReg' and @class='true']");	
			hs.put("label_RegErrmessage", "xpath#//label[@id='btnCirclePOLReg' and @class='false']");	
			hs.put("btnClose", "classname#close-icon");

			
			
			
			
			
			
			
					
					
			
			
			/*------------------------------MURALI GBR--------------------------------*/
			/*
			//Murali -start
			hs.put("txtNewRegistration", "id#subsParameter");
			hs.put("Error_Message", "id#subsParameter-error");
			hs.put("btnNewRegister", "id#btnNewRegister");
			hs.put("portoutalert", "id#spnPortinSubscriberMsg");
			hs.put("btnFindAddress", "xpath#.//*[@id='btnFindAddress']/span");
			hs.put("lblPersonalInfo", "xpath#//div[text()='Personal Information']");
			hs.put("restrictErrorMsg", "id#restrictErrorMsg");
			hs.put("txtEmail1", "id#txtEmail1");
			hs.put("EmailErrorMsg", "id#txtEmail1-error");
			//Murali -End
			hs.put("title_Dropdown","id#ddlTitle");
			hs.put("confirm_Title", "id#step2Title");
			hs.put("firstName_TxtBox", "id#txtfirstName");
			hs.put("lastName_TxtBox", "id#txtlastName");
			hs.put("email_TxtBox", "id#txtEmail1");
			hs.put("country_TxtBox", "id#txtCountry_1");
			hs.put("dob_TxtBox", "id#txtdateofbirth");
			hs.put("county_TxtBox", "id#txtCounty");
			hs.put("PostcodeGBR_TxtBox", "id#txtCode_1");
			hs.put("StateGBR_TxtBox", "id#txtState");
			hs.put("HouseNoGBR_TxtBox", "id#txtLine1_1");
			hs.put("StreetGBR_TxtBox", "id#txtLine2_1");
			hs.put("HouseNameGBR_TxtBox", "id#txtHouseName");
			hs.put("ContactGBR_TxtBox", "id#txtContact1");
			hs.put("MobileNo_Label", "id#txtMobileNo");
			hs.put("PUKCode_TxtBox", "id#txtPukCode");
			hs.put("Language_DropDown", "id#GBRLang");
			hs.put("HearAbout_DropDown", "id#ddlHearAbout");
			hs.put("CheckSMSMarket_Select", "id#chkSMS");
			hs.put("city_TxtBox", "id#txtCity_1");
			hs.put("SIMisNotAssociateToResellerError", "id#step1error");
			hs.put("MSISDN_TxtBox", "id#txtMobileNo");
			hs.put("terms_Checkbox", "id#Terms_Conditions");
			hs.put("personal_Info", "xpath#//div[text()='Personal Information']");
			hs.put("cancel_Btn", "id#Cancel");
			hs.put("next_Btn", "id#Next");
			hs.put("ErrorMsg_Label", "id#spnPortinSubscriberMsg");
			hs.put("RegLoad_Icon", "xpath#//span[@class='glyphicon-rotate col-md-12 text-center']");
			//Buttons in Registration
			hs.put("back_Button", "id#Back");
			hs.put("submit_Button", "id#Submit");
			hs.put("successMessage_Label", "//span[@class='SuccessClass']");
			//Edit Registration
			hs.put("edit_RegBtn", "id#editPreRegxn");
			hs.put("edit_CancelBtn", "id#Cancel");
			//Close Button
			hs.put("reg_CloseBtn", "xpath#//span[@class='close-icon']");
			hs.put("viewSubReg_CloseBtn", "xpath#//li[@class='close-icon']");
			//div[text()='Personal Information']
			//View Registration
			hs.put("expand_RegIcon", "xpath#//li[@class='su-expand-icon']");
			hs.put("view_RegBtn", "id#viewPreRegxn");
			hs.put("viewReg_LoadIcon", "xpath#//span[@class='glyphicon-rotate']");
			hs.put("title_Text", "xpath#//label[@class='bold' and contains(text(), 'Title')]");
			hs.put("view_Title", "xpath#//label[text()='Title']/ancestor::div/following-sibling::div[@class='form-group col-md-1']");
			hs.put("view_FirstName", "xpath#//label[text()='Title']/ancestor::div/following-sibling::div[@class='form-group col-md-2'][1]");
			hs.put("view_LastName", "xpath#//label[text()='Title']/ancestor::div/following-sibling::div[@class='form-group col-md-2'][2]");
			hs.put("view_DOB", "xpath#//text()[normalize-space()='Date Of Birth']/ancestor::div/following-sibling::div[@class='form-group col-md-2']");
			hs.put("view_Email", "xpath#//label[text()='E-Mail']/ancestor::div/following-sibling::div[@class='form-group col-md-2']");
			hs.put("view_ConEmail", "xpath#//label[text()='Confirm']/ancestor::div/following-sibling::div[@class='form-group col-md-2']");
			hs.put("view_Contact", "xpath#//label[text()='Contact']/ancestor::div/following-sibling::div[@class='form-group col-md-2']");
			hs.put("view_AccNo", "xpath#//label[text()='Account Number']/ancestor::div/following-sibling::div[@class='form-group col-md-2']");
			hs.put("view_Country", "xpath#//label[text()='Address']/parent::div/parent::div/following-sibling::div[1]");
			hs.put("view_PostCode", "xpath#//label[text()='Address']/parent::div/parent::div/following-sibling::div[2]");
			hs.put("view_HouseNo", "xpath#//label[text()='Address']/parent::div/parent::div/following-sibling::div[3]");
			hs.put("view_StreetAdd", "xpath#//label[text()='Address']/parent::div/parent::div/following-sibling::div[4]");
			hs.put("view_City", "xpath#//label[text()='Address']/parent::div/parent::div/following-sibling::div[5]//div[@class='col-md-3 col-md-offset-2 b-margin']");
			hs.put("view_State", "xpath#//label[text()='Address']/parent::div/parent::div/following-sibling::div[5]//div/following-sibling::div");
			hs.put("view_PUKCode", "xpath#//label[text()='PUK Code']/ancestor::div/following-sibling::div[@class='col-md-3'][1]");
			hs.put("view_MSISDN", "xpath#//label[text()='MSISDN']/ancestor::div/following-sibling::div[@class='col-md-3']");
			hs.put("view_IMSI1", "xpath#//label[text()='IMSI-1']/ancestor::div/following-sibling::div[@class='col-md-3'][1]");
			hs.put("view_IMSI2", "xpath#//label[text()='IMSI-2']/ancestor::div/following-sibling::div[@class='col-md-3']");
			hs.put("view_ICCID", "xpath#//label[text()='ICCID']/ancestor::div/following-sibling::div[@class='col-md-3']");
			hs.put("view_MostCallCtry", "xpath#//label[text()='Country you call most']/ancestor::div/following-sibling::div[@class='form-group col-md-3'][1]");
			hs.put("view_HearAbUs", "xpath#//label[text()='How did you hear about us?']/ancestor::div/following-sibling::div[@class='form-group col-md-3']");
			hs.put("view_SecQues", "xpath#//label[text()='Secret Question']/ancestor::div/following-sibling::div[@class='form-group col-md-3']");
			hs.put("view_SecAns", "xpath#//label[text()='Secret Answer']/ancestor::div/following-sibling::div[@class='col-md-3']");
			hs.put("view_Language", "xpath#//label[text()='Language']/ancestor::div/following-sibling::div[@class='form-group col-md-3']");
			hs.put("view_CloseButton", "xpath#//h4[@class='modal-title' and contains(text(), 'United Kingdom')]/preceding-sibling::button[@class='close']");
			 */
			Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
			String locate = hs.get(locator).split("\\#")[1];
			return locate;
	
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

	public static String registration_GBR_Page(String locator){

		try{
			Hashtable<String, String> hs = new Hashtable<String, String>();

			hs.put("newSubscriber_txtBox", "id#subsParameter");

			Runtimevalue.setProperty("locatorType", hs.get(locator).split("\\#")[0]);
			String locate = hs.get(locator).split("\\#")[1];
			return locate;
		
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

	public static String registration_GBR_DatePicker(String locator){

		try{

			Hashtable<String, String> hs = new Hashtable<String, String>();

			hs.put("DOB", "txtdateofbirth");

			String locate = hs.get(locator);
			return locate;
		
		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

	public static String Registration_Page_ResetObj(String locator){

		try{

			Hashtable<String, String> hs = new Hashtable<String, String>();

			hs.put("btnFindAddress", ".//*[@id='btnFindAddress']/span");

			String locate = hs.get(locator);
			return locate;

		}catch(Exception e){
			log.info("Error occurred in POM classes :"+e);
		}
		return null;
	}

}
