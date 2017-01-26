package Libraries;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.net.InetAddress;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;

import java.util.Properties;
import java.util.Scanner;

import autoitx4java.AutoItX;

import com.jacob.com.LibraryLoader;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import Utility.ReadExcel;
import Libraries.Driver_Script;

public class Function_Library extends Driver_Script implements Runnable{

	static int timeout_integer=0;
	public static Connection con = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	static String XML_Request=null;
	static String Updated_XML_Request=null;
	static String gbstrXMLResponseData;
	public static Connection OracleConnection=null;
	public static Statement RRBSoraclestmt=null;
	public static ResultSet rs_SQLServer=null;
	public static ResultSet RRBS_SQLServer=null;

	static Connection rrbsconnection = null;
	static Statement rrbsstatement = null;
	static ResultSet rrbsresultset = null;
	static Connection exibsconnection = null;
	static Statement exibsstatement = null;
	static ResultSet exibsresultset = null;

	//Static Variables to set the timeout for Webdriverwait methods. To getting the timeout values from Param.Property file
	static String getDisplayTimeout = null;
	static String getAlertTimeout = null;
	static int intElementDisplayTimeout = 0;
	static int intstaticAlertTimeout = 0;

	public static Connection EshopConnection=null;
	public static Statement EShopstmt=null;
	public static ResultSet Eshop_SQLServer=null;

	/*--LAKSHMAN----*/
	public static Connection con_GBR = null;
	public static Statement stmt_GBR = null;
	public static ResultSet rs_GBR = null;
	public static ResultSet rs_SQLServer_GBR=null;

	public static Connection con_TT = null;
	public static Statement stmt_TT = null;
	public static ResultSet rs_TT = null;
	public static ResultSet rs_SQLServer_TT=null;

	//XMLRequestResponse Declaration
	static DocumentBuilderFactory factory =null;
	static DocumentBuilder builder=null;
	static XPath xpath=null;
	static XPathFactory xpathfactory=null;
	static NodeList nodeList=null;
	static String strURL=null;	
	static String RequestXML=null;
	static Document document=null;
	//	static ExecCommand ec=null;
	//	static CustomTask Execution_Output=null;
	static File file=null;
	static BufferedInputStream in=null;
	
	//Unix object variable declaration
	static JSch jsch=null;
	public static Session JSHsession=null;
	public static Properties Jschconfig=null;
	public static Channel channel=null;
	static boolean connectionStatus=false;
	static boolean disconnectedStatus=false;
	static boolean Executionstatus=false;
	
	//HLR Connection
	static Connection hlrConnection = null;
	static Statement hlrStatement = null;
	static ResultSet hlrResultset = null;
	
	//IMG Connection
	static Connection imgConnection = null;
	static Statement imgStatement = null;
	static ResultSet imgResultset = null;

	//MS Access DB Connection 
	static Connection msAcsConnection = null; 
	static Statement msAcsStatement = null;
	static ResultSet msAcsResultset = null;

	/**
	 * 
	 * @Objective <b>Check any particular process running already. Returns True if running</b>
	 * @param serviceName
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean isProcessRunning(String serviceName) throws Exception {

		Process p=null;
		BufferedReader reader=null;
		String line=null;
		try{
			p = Runtime.getRuntime().exec("tasklist");
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = reader.readLine()) != null) {
				if (line.contains(serviceName)) {
					return true;
				}
			}
			return false;
		}catch(Exception e){
			log.info("Exception occured while checking the Service Running state, Returing false. Exception is : "+e);
			return false;
		}finally{
			p=null;
			reader=null;
			line=null;
		}
	}

	/**
	 * 
	 * @Objective <b>To validate the app_component test steps for test reports</b>
	 * @param ComponentName
	 * @param testStepsStatus
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean Validate_AppComponent(String ComponentName,boolean testStepsStatus) throws Exception{
		boolean Validate_AppComponent= false;
		if(testStepsStatus){
			Report_Functions.ReportEventSuccess(doc, "1", "Validate_AppComponent", "Application Component '"+ComponentName+"' execution is  successful", 2);
			Validate_AppComponent = true;
		}else{
			Report_Functions.ReportEventFailure(doc, "Validate_AppComponent", "Application Component '"+ComponentName+"' execution is not successful", false);
			Validate_AppComponent = true;
		}
		return Validate_AppComponent;
	}

	/**
	 * 
	 * @Objective <b>To get the app_component name from excel sheet</b>
	 * @param strFunctionName
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static String RetrieveTestDataValue(String strFunctionName,String strColumnName,int strExecEventFlag) throws Exception{
		String strData=null;
		//try{
		if(strExecEventFlag!=0){
			strData =ReadExcel.RetrieveTestDataFromSheet(Filepath, EnvironmentValue.getProperty("App_Component_Name"), strColumnName, gblrecordsCounter);
		}
		return strData;
	}

	/**
	 * 
	 * @Objective <b>This method is to handle the expected pop-up using multi threading runnable interface, while execution time and the pop-up will be closed</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>24-May-16</b>
	 */

	@Override
	public void run() {


		log.info("***********Monitoring Thread is Started***********");

		while(!Driver_Script.threadBooleanStatus){
			try {

				Runtime.getRuntime().exec("AutoIT//IE8_browser_update.exe");

			} catch (IOException e) {
				e.getMessage();
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.getMessage();
			}

		}

		log.info("***********Monitoring Thread is Stopped***********");

	}


	/*----------------------------------------- LAKSHMAN CODE ( Base Code )-----------------------------------------*/

	/**
	 * 
	 * @Objective <b>Refresh object is to check and search the element for 10 seconds, when element is not available in page</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>5-April-16</b>
	 */

	public static boolean RefreshObject(String getValueFromPOM){

		boolean refresh = false;

		try{
			log.info("RefreshObject function Try block");

			int a = 0;
			while(a < 25){
				Thread.sleep(1000);	
				selectByLocatorType(getValueFromPOM);
				a++;

				if(selectByLocatorType(getValueFromPOM).isDisplayed()){
					refresh = true;
					log.info("Element is displayed by used refresh object");
					break;
				}

			}
		}catch(Exception e){
			log.info("While refreshing, element is not found.");
			refresh = false;

		}
		return refresh;

	}

	/**
	 * 
	 * @Objective <b>Retrieve the date from local machine and set it to for test results</b>
	 * @param DATE_FORMAT <b>- Return the date from local machine</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static String DateFormatChange(String DATE_FORMAT)  {

		Date date=null;
		SimpleDateFormat sdf =null;
		try
		{
			date = new Date();
			sdf = new SimpleDateFormat(DATE_FORMAT);
			return sdf.format(date);
		}catch(Exception e){
			log.info("Exception in DateFormatChange is : "+e);
			throw e;
		}finally{
			date=null;
		}
	}

	/**
	 * 
	 * @Objective <b>Close the test browser, by using the browser name. Browser value gets from property file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean CloseWebBrowser() throws Exception{

		boolean CloseWebBrowser=false;
		String strBrowserName=null;
		try{
			if(Param.getProperty("testBrowser").equalsIgnoreCase("Mozilla")){
				strBrowserName = "Mozilla";
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("chrome")){
				strBrowserName = "chrome";
			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("IE")){
				strBrowserName = "IE";
			}		

			try{
				driver.close();

				log.info("Closing the Focused Browser.");
			}catch(Exception e){
				log.info("close : "+e);
			}
			try{
				if(driver!=null){
					log.info("Quiting the Driver Session!");	
					driver.quit();
				}
			}catch(Exception e){
				log.info("quit : "+e);
			}

			Report_Functions.ReportEventSuccess(doc,"1", "CloseBrowser", "Successfully Closed the browser "+ strBrowserName, 2);
			CloseWebBrowser=true;
		}catch(Exception e){
			log.info("In Exception of close browser. Exception is : " + e);
			Report_Functions.ReportEventFailure(doc, "CloseBrowser", "Unable to close the browser "+strBrowserName, false);
			CloseWebBrowser=false;
		}finally{
			//Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			driver=null;
		}
		return CloseWebBrowser;
	}

	/**
	 * 
	 * @Objective <b>This method is to enter the username and password credentials for application, values will get from property file.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>- Enter the values for test result</b>
	 * @param strColumnName <b>- Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>- Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 * 
	 */

	public static boolean WebEditEnterUsername(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag ) throws Exception {

		boolean WebEditEnterUsername= false;
		String strData=null;
		String strUserID=null;
		try {

			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebEditEnterUsername",strColumnName,strExecEventFlag);
			}

			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebEditEnterUsername",  "Required details are not provided in the data sheet.", false);
				return false;
			}

			if(Param.getProperty("Use_ENV_UserID_Password").equalsIgnoreCase("Yes")){
				strUserID=Param.getProperty(strData);
			}else{
				strUserID=strData;
			}

			if(strUserID==null){
				Report_Functions.ReportEventFailure(doc,  "WebEditEnterUsername",  "No value to enter in the field : '"+ strTestObject +"'.", false);
				return false;
			}
			selectByLocatorType(getValueFromPOM).sendKeys(strUserID);
			Report_Functions.ReportEventSuccess(doc,"1","WebEditEnterUsername ","The Value '" +  strUserID + "' is entered in the '"+strTestObject+"' TextBox successfully",3);		
			WebEditEnterUsername = true;
		}catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebEditEnterUsername","The Value '" + strUserID + "' was not entered in the '"+strTestObject+"' textbox'", true);
			WebEditEnterUsername = false;
			log.info("No Element Found to enter UserID : " + e);
		}
		return WebEditEnterUsername;
	}

	/**
	 * 
	 * @Objective <b>This method is to enter the password credentials for application, values will get from property file.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>- Enter the values for test result</b>
	 * @param strColumnName <b>- Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>- Based on integer value, will execute property or excel file</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>21-April-16</b>
	 * 
	 */

	public static boolean WebEditEnterPassword(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag ) throws Exception {

		boolean WebEditEnterPassword= false;
		String strData=null;
		String strUserID=null;
		try {

			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebEditEnterPassword",strColumnName,strExecEventFlag);
			}


			if(Param.getProperty("Use_ENV_UserID_Password").equalsIgnoreCase("Yes")){
				strUserID=Param.getProperty(strData);
			}else{
				strUserID=strData;
			}

			if(strUserID==null){
				strUserID = "";
			}

			selectByLocatorType(getValueFromPOM).sendKeys(strUserID);
			Report_Functions.ReportEventSuccess(doc,"1","WebEditEnterPassword ","The Value is entered in the '"+strTestObject+"' TextBox successfully",3);		
			WebEditEnterPassword = true;
		}catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebEditEnterPassword","The Value was not entered in the '"+strTestObject+"' textbox'", true);
			WebEditEnterPassword = false;
			log.info("No Element Found to enter UserID : " + e);
		}
		return WebEditEnterPassword;
	}

	/**
	 * 
	 * @Objective <b>This method is to clickable for web buttons and synchronized with webdriver wait with seconds.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 * 
	 */

	public static boolean WebButtonClick(String getValueFromPOM, String strTestObject) throws Exception {

		boolean WebButtonClick= false;
		try {
			selectByLocatorType(getValueFromPOM).click();
			Report_Functions.ReportEventSuccess(doc, "1", "WebButtonClick","Web Button '"+strTestObject+"'  clicked successfully", 3);
			WebButtonClick=true;
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebButtonClick","Web Button '"+strTestObject+"' Button was not clicked", true); 	
			log.info("No Element Found to click :" + e);
			WebButtonClick=false;
		}
		return WebButtonClick;
	}

	/**
	 * 
	 * @Objective <b>This method is to clickable for all web elements and synchronized with webdriver wait 60 seconds.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>05-April-16</b>
	 * 
	 */

	public static boolean WebElementClick(String getValueFromPOM, String strTestObject)throws Exception  {

		boolean WebElementClick= false;
		try {
			selectByLocatorType(getValueFromPOM).click();
			Report_Functions.ReportEventSuccess (doc,"1","","'"+strTestObject+"' is clicked successfully ",2);	
			WebElementClick=true;
		}catch(StaleElementReferenceException e){

			log.info("StaleElementReferenceException function Catch block. Calling Refresh Object.");
			RefreshObject(getValueFromPOM);

			if((selectByLocatorType(getValueFromPOM)).isDisplayed()){
				return WebElementClick(getValueFromPOM, strTestObject);
			}else
			{
				WebElementClick = false;
			}

		}

		catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","'"+strTestObject+"' was not clicked" , true); 
			WebElementClick=false;
			log.info("No Element Found to click :" + e);
		}
		return WebElementClick;
	}

	/**
	 * 
	 * @Objective <b>This method is to compare the machine date with expected web element date.</b>
	 * @param getValueFromPOM
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>03-May-16</b>
	 */

	public static boolean webElementDateCompare(String getValueFromPOM, String dateFormat) throws Exception{

		boolean web_Date_Compare = false;

		String webElementValue = null;
		DateFormat dateformat = null;
		Date date = null;
		String todayDate = null;
		String conValueFromWeb = null;

		try{

			webElementValue = selectByLocatorType(getValueFromPOM).getText();
			conValueFromWeb = webElementValue.split("\\ ")[0];
			
			dateformat = new SimpleDateFormat(dateFormat);
			date = new Date();
			todayDate = dateformat.format(date);

			if((conValueFromWeb.trim()).equals(todayDate.trim())){

				Report_Functions.ReportEventSuccess(doc,"1","","The expected date '"+conValueFromWeb+"' is mached with today's date '"+todayDate+"' successfully ",2);
				web_Date_Compare = true;
			}
			else{

				Report_Functions.ReportEventFailure(doc,"","The expected date '"+conValueFromWeb+"' is not mached with today's date '"+todayDate+"' " , true); 
				web_Date_Compare = false;
			}

		}catch(Exception e){

			Report_Functions.ReportEventFailure(doc,"","'"+conValueFromWeb+"' is not mached with today's date" , true); 
			web_Date_Compare = false;

		}

		return web_Date_Compare;
	}

	/**
	 * 
	 * @Objective <b>This method is to click the links for web elements and synchronized with webdriver wait 60 seconds.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 * 
	 */

	public static boolean WebLinkClick(String getValueFromPOM, String strTestObject) throws Exception {

		boolean WebLinkClick= false;
		try {
			selectByLocatorType(getValueFromPOM).click();
			Report_Functions.ReportEventSuccess(doc,"1","","The Link: '"+strTestObject+"' clicked successfully",3);
			WebLinkClick=true;
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","'The Link: "+strTestObject+"' was not clicked" , true); 
			WebLinkClick=false;
			log.info("No Element Found to click : " + e);
		}
		return WebLinkClick;
	}

	/**
	 * 
	 * @Objective <b>This method is to click the radio button for web elements and synchronized with webdriver wait 60 seconds.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param rdbOptions <b>Radio button option name</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebRadioSelect(String getValueFromPOM, String strTestObject, String rdbOptions)throws Exception  {

		boolean WebRadioSelect= false;
		try {
			selectByLocatorType(getValueFromPOM).click();
			Report_Functions.ReportEventSuccess(doc,"4","","Selected the Option '"+rdbOptions+"' from the Radio button '" + strTestObject + "'",3);		
			WebRadioSelect=true;
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","The Option '"+rdbOptions+"' is not Selected from the Radio button '" + strTestObject + "'", true); 	
			WebRadioSelect=false;
			log.info("No Element Found to Select : " + e);
		}
		return WebRadioSelect;
	}

	/**
	 * 
	 * @Objective <b>This method is to enter the values for all web elements.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebEditEnterText(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {

		boolean WebEditEnterText= false;
		String strData=null;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebEditEnterText",strColumnName,strExecEventFlag);
			}

			selectByLocatorType(getValueFromPOM).clear();
			selectByLocatorType(getValueFromPOM).sendKeys(strData);
			Report_Functions.ReportEventSuccess(doc, "1", "WebEditEnterText", "The Text '" +  strData + "' is entered in the Textbox -  '"+strTestObject+"'  successfully", 3);
			WebEditEnterText=true;	
		} catch (Exception e) { 	
			Report_Functions.ReportEventFailure(doc, "WebEditEnterText", "The Text '" + strData + "' was not entered in the Textbox - '"+strTestObject+"'", true);
			WebEditEnterText=false;
			log.info("No Element Found to enter text : " + e);
		}
		return WebEditEnterText;
	}

	/**
	 * 
	 * @Objective <b>This method is tab to move on webElement</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>27-Jul-16</b>
	 * 
	 */

	public static boolean webElementTabClick(String getValueFromPOM, String strTestObject)throws Exception  {

		boolean elementStatus = false;
		try {
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				selectByLocatorType(getValueFromPOM).sendKeys(Keys.TAB);			
				Report_Functions.ReportEventSuccess(doc,"1","webElementTabClick","Tab button is clicked successfully on '"+strTestObject+"' field",3);		
				elementStatus = true;
			}else{

				Report_Functions.ReportEventFailure(doc,"webElementTabClick","Element is not found and Tab button is not clicked on '"+strTestObject+"' field", true); 	
				elementStatus = false;

			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"webElementTabClick","Exception occurred in 'webElementTabClick' function", true); 	
			elementStatus = false;
			log.info("No Element Found to click : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to clear the text from textbox</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>28-Jul-16</b>
	 */

	public static boolean webElementClearText(String getValueFromPOM, String strTestObject)throws Exception  {

		boolean elementStatus = false;
		try {
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				selectByLocatorType(getValueFromPOM).sendKeys(Keys.DELETE);
				selectByLocatorType(getValueFromPOM).clear();			
				Report_Functions.ReportEventSuccess(doc,"1","webElementClearText","Text is cleared successfully in '"+strTestObject+"'",3);		
				elementStatus = true;
			}else{

				Report_Functions.ReportEventFailure(doc,"webElementClearText","Element is not found and Text is not cleared in '"+strTestObject+"'", true); 	
				elementStatus = false;

			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"webElementClearText","Exception occurred in 'webElementClearText' function", true); 	
			elementStatus = false;
			log.info("No Element Found to click : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to enter the values and click on Enter button</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>15-Jul-16</b>
	 */

	public static boolean enterValuesAndClickEnterBtn(String getValueFromPOM, String strTestObject, String strColumnName, int strExecEventFlag)throws Exception  {

		boolean enterValuesAndClickEnter = false;

		String strData = null;
		try {
			if(strExecEventFlag == 1){
				strData = RetrieveTestDataValue("EnterValuesAndClickEnter", strColumnName, strExecEventFlag);
			}

			WebElement element = selectByLocatorType(getValueFromPOM);

			if(element.isDisplayed()){

				selectByLocatorType(getValueFromPOM).clear();
				selectByLocatorType(getValueFromPOM).sendKeys(strData);
				selectByLocatorType(getValueFromPOM).sendKeys(Keys.ENTER);

				Report_Functions.ReportEventSuccess(doc, "1", "EnterValuesAndClickEnter", "Text '" +  strData + "' is entered in the Textbox -  '"+strTestObject+"' and clicked on 'ENTER' button successfully", 3);
				enterValuesAndClickEnter = true;

			}else{

				Report_Functions.ReportEventFailure(doc, "EnterValuesAndClickEnter", "Text '" + strData + "' was not entered in the Textbox - '"+strTestObject+"' and not clicked on 'ENTER' button", true);
				enterValuesAndClickEnter = false;

			}
		} catch (Exception e) { 	
			Report_Functions.ReportEventFailure(doc, "EnterValuesAndClickEnter", "Text '" + strData + "' was not entered in the Textbox - '"+strTestObject+"' and not clicked on 'ENTER' button", true);
			enterValuesAndClickEnter = false;
			log.info("No Element Found to enter text : " + e);
		}
		return enterValuesAndClickEnter;
	}

	/**
	 * 
	 * @Objective <b>This method is to select(CTRL + A) the values, enter the values and click on Enter button</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>15-Jul-16</b>
	 */

	public static boolean selectEnterTextClickEnter(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {

		boolean selectEnterTextClickEnter = false;

		String strData=null;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("SelectEnterTextClickEnter",strColumnName,strExecEventFlag);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "SelectEnterTextClickEnter",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			selectByLocatorType(getValueFromPOM).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			selectByLocatorType(getValueFromPOM).sendKeys(strData);
			selectByLocatorType(getValueFromPOM).sendKeys(Keys.ENTER);

			Report_Functions.ReportEventSuccess(doc, "1", "", "Cleared text and '" +  strData + "' is entered in the Textbox -  '"+strTestObject+"' and clicked on 'ENTER' button successfully", 3);
			selectEnterTextClickEnter = true;	
		} catch (Exception e) { 	
			Report_Functions.ReportEventFailure(doc, "", "The Text '" + strData + "' was not cleared and entered in the Textbox - '"+strTestObject+"'", true);
			selectEnterTextClickEnter = false;
			log.info("No Element Found to enter text : " + e);
		}
		return selectEnterTextClickEnter;
	}

	/**
	 * 
	 * @Objective <b>This method is to double click on element</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>15-Jul-16</b>
	 */

	public static boolean doubleClickOnElement(String getValueFromPOM, String strTestObject) throws Exception{

		boolean doubleClickOnElement = false;

		try {
			WebElement element = selectByLocatorType(getValueFromPOM);

			if(element.isDisplayed()){

				Actions action = new Actions(driver).doubleClick(element);
				action.build().perform();
				Report_Functions.ReportEventSuccess (doc,"1","DoubleClickOnElement","'"+strTestObject+"' is double clicked successfully ",2);	
				doubleClickOnElement = true;

			}else{

				Report_Functions.ReportEventFailure(doc,"DoubleClickOnElement","'"+strTestObject+"' was not double clicked" , true); 
				doubleClickOnElement = false;


			}

		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"DoubleClickOnElement","'"+strTestObject+"' was not double clicked" , true); 
			doubleClickOnElement = false;
			log.info("No Element Found to double click :" + e);
		}
		return doubleClickOnElement;
	}

	/**
	 * 
	 * @Objective <b>First is to check whether element is displayed in webpage, if availble it gets text from displayed element</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebElementisVisible(String getValueFromPOM, String strTestObject) throws Exception {

		boolean elementvisible;
		boolean WebElementisVisible = false;
		try {
			elementvisible=selectByLocatorType(getValueFromPOM).isDisplayed();
			String output=selectByLocatorType(getValueFromPOM).getText();
			if(elementvisible){
				WebElementisVisible = true;
				Report_Functions.ReportEventSuccess(doc, "1", "", "'"+strTestObject+"' is Displayed with value '"+output+ "'", 3);
			}else{
				WebElementisVisible=false;
				Report_Functions.ReportEventFailure(doc, "", "'"+strTestObject+"' is not Displayed", true);
			}
		} catch (Exception e) { 	
			log.info("Exception while finding visibility" + e);
			WebElementisVisible=false;
		}
		return WebElementisVisible;
	}

	/**
	 * 
	 * 
	 * @Objective <b>This method to check whether element is not displayed in webpage.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	/**
	 * 
	 * 
	 * @Objective <b>This method to check whether element is not displayed in webpage.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebElementisNotVisible(String getValueFromPOM, String strTestObject) throws Exception {

		boolean WebElementisNotVisible = false;

		try {
			int elementcount=listSelectByLocatorType(getValueFromPOM).size();

			if(elementcount > 0){
				boolean elementvisible=selectByLocatorType(getValueFromPOM).isDisplayed();
				if(!elementvisible){
					WebElementisNotVisible = true;
					Report_Functions.ReportEventSuccess(doc, "1", "WebElementisNotVisible", "'"+strTestObject+"' is not displayed", 3);	
				} else {
					WebElementisNotVisible = false;
					Report_Functions.ReportEventFailure(doc, "WebElementisNotVisible", "'"+strTestObject+"' is Displayed(visible)", true);
				}
			} else{
				WebElementisNotVisible = true;
				Report_Functions.ReportEventSuccess(doc, "1", "WebElementisNotVisible", "'"+strTestObject+"' is not displayed", 3);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) { 	

			WebElementisNotVisible = true;
			Report_Functions.ReportEventSuccess(doc, "1", "WebElementisNotVisible", "'"+strTestObject+"' is not Displayed", 3);

		} catch (NullPointerException e){

			WebElementisNotVisible = true;
			Report_Functions.ReportEventSuccess(doc, "1", "WebElementisNotVisible", "'"+strTestObject+"' is not Displayed", 3);
		}

		catch (Exception e) { 	
			log.info("Exception while finding visibility" + e);
			WebElementisNotVisible = false;
			Report_Functions.ReportEventFailure(doc, "WebElementisNotVisible", "'"+strTestObject+"' is Displayed(visible)", true);

		}

		return WebElementisNotVisible;
	}


	/**
	 * 
	 * @Objective <b>This method to wait until progress bar or ajax call is displayed</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>07-April-16</b>
	 */

	public static boolean waitUntilDisappear(String getValueFromPOM, String strTestObject) throws Exception{

		boolean disappear =false;

		try{
			log.info("Check element is appear");
			Thread.sleep(500);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				for(int i=0; i<90; i++){
					Thread.sleep(1000);
					log.info("Element is appearing");

					if(!selectByLocatorType(getValueFromPOM).isDisplayed()){

						disappear = true;
						log.info("Element is disappeared");
						Thread.sleep(1000);
						Report_Functions.ReportEventSuccess(doc,"1","","'The element '"+ strTestObject +"' is disappeared.",3);
						break;
					}
				}

			}else{

				Thread.sleep(500);
				log.info("Element doesn't exist");
				disappear = true;
				Thread.sleep(1000);
				Report_Functions.ReportEventSuccess(doc,"1","","'The element '"+ strTestObject +"' is not displayed",3);

			}

		}catch(StaleElementReferenceException e){

			Report_Functions.ReportEventSuccess(doc,"1","","'The element '"+ strTestObject +"' is not displayed",3);
			return true;


		}
		catch(NullPointerException e){

			log.info("Null pointer exception occurred :"+e);
			Report_Functions.ReportEventSuccess(doc,"1","","'The element '"+ strTestObject +"' is not displayed",3);
			return true;

		}catch(Exception e){

			log.info("Element is not found :"+e);
			disappear = false;
			Report_Functions.ReportEventFailure(doc,"","Exception occured. Error message is : "+ e +"." , true);
		}

		return disappear;

	}

	/**
	 * 
	 * @Objective <b>This method is to check the page gets loaded successfully</b>
	 * @author <b>HariPrakash</b>
	 * @since <b>22-April-16</b>
	 */

	public static boolean pageLoadCheck() throws Exception{

		Date s = new GregorianCalendar().getTime();
		Report_Functions.ReportEventSuccess(doc,"4","","Page load Start Time " + s,3);

		int flag=0;
		String docStatus;
		JavascriptExecutor js = (JavascriptExecutor)driver;

		try {
			for (int i=0; i<60; i++){ 
				if(js.executeScript("return document.readyState").toString().equals("complete")){                             
					log.info("Page Loaded Successfully");
					flag=1;
					break;
				}

				else{
					docStatus= js.executeScript("return document.readyState").toString();
					Thread.sleep(1000); 
					Report_Functions.ReportEventSuccess(doc,"4","","Else condition page loading status :" +docStatus, 3);
				}

			}

			if(flag==1){        	
				Date s2 = new GregorianCalendar().getTime();        	
				Report_Functions.ReportEventSuccess(doc,"1","","Page is loaded successfully",3);
				Report_Functions.ReportEventSuccess(doc,"4","","Page load End Time " + s2,3);
				return true;
			}
			else{
				Date s1 = new GregorianCalendar().getTime();        		
				Report_Functions.ReportEventFailure(doc,"","Page doesn't loaded Successfully, Load time :"+s1+"" , true);
				Report_Functions.ReportEventSuccess(doc,"4","","Page load End Time " + s1,3);
				return false;
			}    


		}catch (WebDriverException e) {                      
			log.info("Webdriver Occured "+e);
			Report_Functions.ReportEventSuccess(doc,"4","","Javascript Error for StackTrace occured while Page Load Check" , 3);
			e.printStackTrace();
			return true;

		}catch (InterruptedException e) {                      
			log.info("InterruptedException Occured "+e);
			Report_Functions.ReportEventFailure(doc,"","Error occured while Page Load Check. Exception is "+e.getLocalizedMessage() , true);
			e.printStackTrace();
			return false;

		}catch (Exception e1) {                         
			log.info("Exception Occured " + e1);
			Report_Functions.ReportEventFailure(doc,"","Error occured while Page Load Check. Exception is "+e1.getLocalizedMessage() , true);
			e1.printStackTrace();
			return false;
		}

	}

	/**
	 * 
	 * @Objective <b>This method to select the all fields in web element and enter the values.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean Webeditselectentertext(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {

		boolean Webeditselectentertext= false;
		String strData=null;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("Webeditselectentertext",strColumnName,strExecEventFlag);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "Webeditselectentertext",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			selectByLocatorType(getValueFromPOM).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			selectByLocatorType(getValueFromPOM).sendKeys(strData);
			Report_Functions.ReportEventSuccess(doc, "1", "", "The Text '" +  strData + "' is entered in the Textbox -  '"+strTestObject+"'  successfully", 3);
			Webeditselectentertext=true;	
		} catch (Exception e) { 	
			Report_Functions.ReportEventFailure(doc, "", "The Text '" + strData + "' was not entered in the Textbox - '"+strTestObject+"'", true);
			Webeditselectentertext=false;
			log.info("No Element Found to enter text : " + e);
		}
		return Webeditselectentertext;
	}

	/**
	 * 
	 * @Objective <b>This method is to wait the element is exist in webpage and synchronized with webdriver wait for 120 seconds</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>27-Jul-16</b>
	 */

	public static boolean waitUntilExist(String getValueFromPOM,  String strTestObject) throws Exception {

		boolean elementStatus= false;
		getDisplayTimeout = Param.getProperty("elementDisplayTimeout");
		intElementDisplayTimeout = Integer.parseInt(getDisplayTimeout);

		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver,intElementDisplayTimeout);

		try{

			String loc = Runtimevalue.getProperty("locatorType");

			switch(loc.toLowerCase()){

			case "id":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(getValueFromPOM)));
				if(element.isDisplayed()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;


			case "xpath":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getValueFromPOM)));
				if(element.isDisplayed()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			case "css":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(getValueFromPOM)));
				if(element.isDisplayed()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			case "classname":	
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(getValueFromPOM)));
				if(element.isDisplayed()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			case "name":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(getValueFromPOM)));
				if(element.isDisplayed()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			case "linktext":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(getValueFromPOM)));
				if(element.isDisplayed()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			case "tagname":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(getValueFromPOM)));
				if(element.isDisplayed()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			case "partiallinktext":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(getValueFromPOM)));
				if(element.isDisplayed()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			default:

				elementStatus = false;
				throw new IllegalArgumentException("Unable to found");
			}	

		}catch(StaleElementReferenceException e1){

			log.info("StaleElementReferenceException function Catch block. Calling Refresh Object.");
			RefreshObject(getValueFromPOM);

			if((selectByLocatorType(getValueFromPOM)).isDisplayed()){
				return waitUntilExist(getValueFromPOM, strTestObject);
			}else
			{
				elementStatus = false;
			}

		}catch(Exception e){

			log.info("Exception Occured in 'waitUntilExist':"+ e);
			elementStatus = false;

		}

		if(elementStatus){
			Report_Functions.ReportEventSuccess(doc,"1","","The element '"+ strTestObject +"' is displayed successfully",3);
		}else{
			Report_Functions.ReportEventFailure(doc,"","The element '"+ strTestObject +"' is not displayed within time of "+intElementDisplayTimeout+" seconds" , true);
		}

		return elementStatus;
	}	

	/**
	 * 
	 * @Objective <b>This method is to wait the element for 2 seconds</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>05-April-16</b>
	 * 
	 */

	public static boolean sleepForFunction(){

		try{

			Thread.sleep(5000);

		}catch(Exception e){

			log.info("Issue in Thread.sleep "+e.getMessage());
			e.printStackTrace();

		}
		return true;
	}

	/**
	 * 
	 * @Objective <b>This method is click the radio button in web table</b>
	 * @param getValueFromPOM
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>10-May-16</b>
	 */

	public static boolean webTableRadioButton(String getValueFromPOM, String strColumnName, int strExecEventFlag) throws Exception{
		
		boolean elementStatus = false;
		String elementValue = null;
		String actualValue = null;
		
		try{
			
			if(strExecEventFlag==1){
				
				elementValue = RetrieveTestDataValue("WebTableRadioButton", strColumnName, strExecEventFlag);
			}
			
			if(elementValue == null){
				
				Report_Functions.ReportEventFailure(doc,  "WebTableRadioButton",  "Required details are not provided in the data sheet.", false);
				return false;
				
			}
			
		List<WebElement> element = listSelectByLocatorType(getValueFromPOM);
		
		for(WebElement checkElement : element){
			
			actualValue = checkElement.getText().trim();

			if(actualValue.equalsIgnoreCase(elementValue)){
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].style.border='2px groove white'", checkElement);
				//if(!checkElement.getAttribute("checked").equals("true")){
				checkElement.click();
				//}
				Report_Functions.ReportEventSuccess(doc,"1","webTableRadioButton","'"+elementValue+"' radio button is clicked from the web table successfully" ,3);		
				return true;
				
			}
			
		}
		
			if(!actualValue.equalsIgnoreCase(elementValue)){
		
				Report_Functions.ReportEventFailure(doc,"webTableRadioButton","'"+elementValue +"' radio button is not clicked from the web table" , true); 
				return false;
		
			}
		
		
		}catch(Exception e){
			
			log.info("Exception occurred in web table radio button :"+e.getMessage());
			Report_Functions.ReportEventFailure(doc,"webTableRadioButton","'"+elementValue +"' radio button is not clicked from the web table" , true); 
			elementStatus = false;
			
		}
		
		return elementStatus;
		
	}

	/**
	 * 
	 * @Objective <b>This method is click the element from list dropdown</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>17-Jun-16</b>
	 */

	public static boolean webListDropdownClick(String getValueFromPOM, String strTestObject, String strColumnName, int strExecEventFlag) throws Exception{

		boolean elementStatus = false;
		String elementValue = null;
		String actualText = null;

		try{

			if(strExecEventFlag==1){

				elementValue = RetrieveTestDataValue("webListDropdownClick", strColumnName, strExecEventFlag);
			}

			if(elementValue == null){

				Report_Functions.ReportEventFailure(doc,  "webListDropdownClick",  "Required details are not provided in the data sheet.", false);
				return false;

			}

			List<WebElement> element = listSelectByLocatorType(getValueFromPOM);

			for(WebElement dropdownValues : element){

				actualText = dropdownValues.getText();

				if((dropdownValues.getText()).equalsIgnoreCase(elementValue)){

					dropdownValues.click();

					Report_Functions.ReportEventSuccess (doc,"1","","'"+actualText+"' is clicked successfully from "+strTestObject+" list dropdown",2);	
					elementStatus = true;

				}

			}

		}catch(Exception e){

			log.info("Exception occurred in web table radio button :"+e.getMessage());
			Report_Functions.ReportEventFailure(doc,"","'"+actualText+"' is not clicked from "+strTestObject+" list dropdown" , true);  
			elementStatus = false;

		}
		return elementStatus;

	}

	/**
	 * 
	 * @Objective <b>This method is to download the file from IE browser and will be stored in specific location</b>
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>24-Jun-16</b>
	 */

	public static boolean ticketingAttachmentFileDownload(String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{

			String locatioPath = Param.getProperty("download_FilePath");
			//Call the AutoIT for download process and store the file in specific path which is given in AutoIT script	

			Runtime.getRuntime().exec("AutoIT/CRM_file_download_ticketsAttach.exe");

			long first;
			long second;

			while(true){

				File file = new File(locatioPath);
				File files[] = file.listFiles();
				if(files.length > 0){

					first = files[0].length()/1024;
					Thread.sleep(1000);

					file = new File(locatioPath);
					files = file.listFiles();

					second = files[0].length()/1024;

					if(first != second){

						log.info("File downloading");

					}else {

						log.info("File download completed");
						Report_Functions.ReportEventSuccess (doc,"1","","File is downloaded from '"+strTestObject+"' and stored in specific location",2);	
						elementStatus = true;
						break;

					}
				}

			}

		}catch(Exception e){

			log.info("Exception occurred in AutoIT file download for tickets attachment :"+e.getMessage());
			Report_Functions.ReportEventFailure(doc,"","File is not downloaded from the browser" , true);  
			elementStatus = false;

		}
		return elementStatus;

	}

	/**
	 * 
	 * @Objective <b>This method is to upload the file from specific location</b>
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>28-Jun-16</b>
	 */

	public static boolean uploadFileNewTicket(String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{

			//Call the AutoIT for upload the file in specific path which is given in AutoIT script	
			Runtime.getRuntime().exec("AutoIT/CRM_upload_file.exe");
			Report_Functions.ReportEventSuccess (doc,"1","","File uploaded successfully for '"+strTestObject+"' module",2);	
			elementStatus = true;

		}catch(Exception e){

			log.info("Exception occurred in AutoIT file upload for create tickets :"+e.getMessage());
			Report_Functions.ReportEventFailure(doc,"","File is not uploaded" , true);  
			elementStatus = false;
		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to enter the values for DatePicker using Java script executor</b>
	 * @param locatorType
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>27-Jun-16</b>
	 */

	public static boolean javaScriptDatePicker(String getValueFromPOM, String strTestObject, String strColumnName, int strExecEventFlag) throws Exception{

		boolean elementStatus = false;
		String elementValue = null;

		try{

			if(strExecEventFlag==1){

				elementValue = RetrieveTestDataValue("javaScriptDatePicker", strColumnName, strExecEventFlag);

			}

			if(elementValue == null){

				Report_Functions.ReportEventFailure(doc,  "javaScriptDatePicker",  "Required details are not provided in the data sheet.", false);
				return false;

			}

			//Get the locatorType from POM during runtime
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('"+getValueFromPOM+"').value = '"+elementValue+"'");
			Report_Functions.ReportEventSuccess (doc,"1","","Date picker value '"+elementValue+"' is entered for "+strTestObject+" ",2);	
			elementStatus = true;

		}catch(Exception e){

			log.info("Exception occurred in web table radio button :"+e.getMessage());
			e.printStackTrace();
			Report_Functions.ReportEventFailure(doc,"","Date picker value '"+elementValue+"' is not entered for "+strTestObject+" " , true);  
			elementStatus = false;

		}

		return elementStatus;

	}

	/**
	 * 
	 * @Objective <b>This method is to check whether scroll bar is present on page</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>22-Jun-16</b>
	 */

	public static boolean checkScrollBarIsPresent(String getValueFromPOM, String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{

			//Xpath for Javascript executor, if value is present will return as true or not present will return as false
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Object scrollBarStatus = js.executeScript("return document.evaluate(\""+getValueFromPOM+"\",document.body,null,XPathResult.UNORDERED_NODE_ITERATOR_TYPE,null).iterateNext()!=null;");

			if(scrollBarStatus.equals(true)){

				log.info("Scroll bar is available on page");
				Runtimevalue.setProperty("checkScrollBarPresent", Param.getProperty("scrollBarPresent"));
				Report_Functions.ReportEventSuccess (doc,"1","","Scroll bar is available on "+strTestObject+" page",2);
				elementStatus = true;

			}else{				
				log.info("Scroll bar is not available on page");
				Runtimevalue.setProperty("checkScrollBarPresent", Param.getProperty("scrollBarNotPresent"));
				Report_Functions.ReportEventSuccess (doc,"1","","Scroll bar is not available on "+strTestObject+" page",2);
				elementStatus = true;
			}

		}catch(Exception e){

			log.info("Error occurred, while finding the scroll bar element on page");
			Report_Functions.ReportEventFailure(doc,"","Scroll bar is not available on "+strTestObject+" page" , true);
			elementStatus = false;
		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to check whether scroll bar is present on browser</b>
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>12-Aug-16</b>
	 */

	public static boolean verifyScrollPresentOnBrowser(String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{

			JavascriptExecutor js = (JavascriptExecutor) driver;
			Object scrollBarStatus = js.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");

			if(scrollBarStatus.equals(true)){

				log.info("Scroll bar is available on page");
				Runtimevalue.setProperty("checkScrollBarPresent", Param.getProperty("scrollBarPresent"));
				Report_Functions.ReportEventSuccess (doc,"1","","Scroll bar is available on "+strTestObject+" page",2);
				elementStatus = true;

			}else{				
				log.info("Scroll bar is not available on page");
				Runtimevalue.setProperty("checkScrollBarPresent", Param.getProperty("scrollBarNotPresent"));
				Report_Functions.ReportEventSuccess (doc,"1","","Scroll bar is not available on "+strTestObject+" page",2);
				elementStatus = true;
			}


		}catch(Exception e){

			log.info("Error occurred, while finding the scroll bar element on page");
			Report_Functions.ReportEventFailure(doc,"","Scroll bar is not available on "+strTestObject+" page" , true);
			elementStatus = false;

		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to change the scroll bar status OFF</b>
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>22-Jun-16</b>
	 * 
	 */

	public static boolean setScrollBarOFF(String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{
			Runtimevalue.setProperty("checkScrollBarPresent", Param.getProperty("scrollBarNotPresent"));
			Report_Functions.ReportEventSuccess (doc,"1","","Changed status for Scroll bar status as 'No' in "+strTestObject+"",2);
			elementStatus = true;

		}catch(Exception e){

			log.info("Error occurred, while set the status for scroll bar");
			Report_Functions.ReportEventFailure(doc,"","Error occurred, while set the status for scroll bar in "+strTestObject+"" , true);
			elementStatus = false;
		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to scroll UP the page using Selenium Keys Button</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>22-Jul-16</b>
	 */

	public static boolean pageScrollUp(String getValueFromPOM, String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{
			WebElement scroll = selectByLocatorType(getValueFromPOM);
			scroll.sendKeys(Keys.PAGE_UP);
			Report_Functions.ReportEventSuccess (doc,"1","","Scroll bar moved up successfully in "+strTestObject+" page",2);
			elementStatus = true;

		}catch(WebDriverException e){

			Report_Functions.ReportEventSuccess (doc,"1","","Scroll bar moved up successfully in "+strTestObject+" page",2);
			elementStatus = true;

		}

		catch(Exception e){

			log.info("Error occurred, while set the status for scroll bar");
			Report_Functions.ReportEventFailure(doc,"","Error occurred, while scroll bar moved up in "+strTestObject+" page" , true);
			elementStatus = false;

		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to click the element from web table pagination</b>
	 * @param getValueFromPOM
	 * @param nextBtnValueFromPOM
	 * @param pageCountValFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>14-Jul-16</b>
	 */

	public static boolean getValueFromPagination(String getValueFromPOM, String nextBtnValueFromPOM, String pageCountValFromPOM, String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Object checkElementStatusJS;
			WebElement clickElement;

			checkElementStatusJS = js.executeScript("return document.evaluate(\""+getValueFromPOM+"\",document.body,null,XPathResult.UNORDERED_NODE_ITERATOR_TYPE,null).iterateNext()!=null;");

			//Checking the element is exist on page
			if(checkElementStatusJS.equals(true)){

				//If exist, clicking on element
				clickElement = selectByLocatorType(getValueFromPOM);
				clickElement.click();
				Report_Functions.ReportEventSuccess (doc,"1","getValueFromPagination","Element is clicked successfully from pagination table in "+strTestObject+"",2);
				elementStatus = true;


			} else {

				WebElement pageCount = selectByLocatorType(pageCountValFromPOM);
				//Get the page count and convert into Integer
				String strCount = pageCount.getText();
				int count = Integer.parseInt(strCount);

				//Element is not exist, clicking on paginaton by page count
				for(int i = 1; i <= count; i++){

					WebElement clickingOnNextBtn = selectByLocatorType(nextBtnValueFromPOM);
					clickingOnNextBtn.click();

					//Again find the element by using JS executor
					checkElementStatusJS = js.executeScript("return document.evaluate(\""+getValueFromPOM+"\",document.body,null,XPathResult.UNORDERED_NODE_ITERATOR_TYPE,null).iterateNext()!=null;");

					//If element is exist in loop
					if(checkElementStatusJS.equals(true)){

						clickElement = selectByLocatorType(getValueFromPOM);
						clickElement.click();
						Report_Functions.ReportEventSuccess (doc,"1","getValueFromPagination","Element is clicked successfully from pagination table in "+strTestObject+"",2);
						elementStatus = true;
						break;

					}

				} if(checkElementStatusJS.equals(false)) {

					log.info("Element is not available in table pagination");
					Report_Functions.ReportEventFailure(doc,"getValueFromPagination","Element is not available from pagination table in "+strTestObject+"" , true);
					elementStatus = false;
				}
			}

		}catch(Exception e){

			log.info("Exception occurred in 'getValueFromPagination' method :" +e.getMessage());
			Report_Functions.ReportEventFailure(doc,"getValueFromPagination","Element is not clicked from pagination table in"+strTestObject+"" , true);
			elementStatus = false;
		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to close the unexpected static alert, while doing the registration process</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>18-May-16</b>
	 */

	public static boolean threadingForEvents(){

		try{

			Runtime.getRuntime().exec("AutoIT//Registration_page_alert_close.exe");

		}catch(Exception e){

			log.info("Error occurred, while executing AutoIT function for Registration process");
			e.printStackTrace();

		}

		return true;

	}

	/**
	 * 
	 * @Objective <b>This method is to select the values from the dropdown</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebListSelect(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag)throws Exception  {

		String strData=null;
		boolean WebListSelect=false;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebListSelect",strColumnName,strExecEventFlag);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebListSelect",  "Required details are not provided in the data sheet.", false);
				return false;
			}

			new Select(selectByLocatorType(getValueFromPOM)).selectByVisibleText(strData);
			Report_Functions.ReportEventSuccess(doc,"1","","The Item '" +  strData + "' is selected from the  '"+strTestObject+"' List box successfully" ,3);
			WebListSelect=true;

		} catch (StaleElementReferenceException e) {
			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				return WebListSelect(getValueFromPOM, strTestObject, strColumnName, strExecEventFlag);

			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","The Item '" +  strData + " was not selected from the  '"+strTestObject+"' List box " , true); 
			WebListSelect=false;
			log.info("No Element Found to select text" + e);
		}
		return WebListSelect;
	}

	/**
	 * 
	 * @Objective <b>This method is to verify whether value is already selected in dropdown</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strColumnName <b>Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>05-April-16</b>
	 * 
	 */

	public static boolean WebListSelectedValue(String getValueFromPOM, String strTestObject, String strColumnName, int strExecEventFlag) throws Exception{

		String strData = null;
		boolean WebListSelectedValue = false;
		String selectedValue = null;
		try{

			if(strExecEventFlag==1){

				strData = RetrieveTestDataValue("WebListSelectedValue", strColumnName, strExecEventFlag);
			}	
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebListSelectedValue",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			selectedValue = new Select(selectByLocatorType(getValueFromPOM)).getFirstSelectedOption().getText();

			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				if(selectedValue.trim().equalsIgnoreCase(strData.trim())){
					Report_Functions.ReportEventSuccess(doc,"1","",strTestObject +"'s selected dropdown value '"+selectedValue + "' matches with the Expected Value '"+strData+"'" ,3);
					WebListSelectedValue = true;
				}else{
					Report_Functions.ReportEventFailure(doc,"",strTestObject +"'s selected dropdown value '"+selectedValue + "' does not matches with the Expected Value '"+strData+"'"  , true); 
					WebListSelectedValue=false;
				}
			}else{
				Report_Functions.ReportEventFailure(doc,"",strTestObject +"'s selected dropdown value '"+selectedValue + "'is not displayed" , true); 
				WebListSelectedValue=false;
			}

		}catch(StaleElementReferenceException e){

			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				return WebListSelectedValue;

			}

		}catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","Selected dropdown value is '" +  selectedValue + " is not shown in "+strTestObject+"" , true); 
			WebListSelectedValue=false;
			log.info("No Element Found to select text" + e);
		}

		return WebListSelectedValue;

	}

	/**
	 * 
	 * @Objective <b>This method is to find the different locators in webpage. We can get the element in POM classes and locator type in run-time environment</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>05-April-16</b>
	 */

	public static WebElement selectByLocatorType(String getValueFromPOM){

		WebElement element = null;

		try{

			String loc = Runtimevalue.getProperty("locatorType");

			// To get the status of scroll bar in runtime environment
			String scrollBarStatus = Runtimevalue.getProperty("checkScrollBarPresent");

			switch(loc.toLowerCase()){

			case "id":

				// If scroll bar is present, navigation will be presented for id element

				if(scrollBarStatus.equalsIgnoreCase("Yes")){
					element = driver.findElement(By.id(getValueFromPOM));
					Coordinates coordinates = ((Locatable)element).getCoordinates();
					coordinates.onPage();
					coordinates.inViewPort();

				} else if(scrollBarStatus.equalsIgnoreCase("No")){

					element = driver.findElement(By.id(getValueFromPOM));
				}
				break;

			case "xpath":

				// If scroll bar is present, navigation will be presented for xpath element

				if(scrollBarStatus.equalsIgnoreCase("Yes")){
					element = driver.findElement(By.xpath(getValueFromPOM));
					Coordinates coordinates = ((Locatable)element).getCoordinates();
					coordinates.onPage();
					coordinates.inViewPort();

				} else if(scrollBarStatus.equalsIgnoreCase("No")){

					element = driver.findElement(By.xpath(getValueFromPOM));
				}
				break;

			case "css":
				element = driver.findElement(By.cssSelector(getValueFromPOM));
				break;

			case "classname":	
				element = driver.findElement(By.className(getValueFromPOM));
				break;

			case "name":
				element = driver.findElement(By.name(getValueFromPOM));
				break;

			case "linktext":
				element = driver.findElement(By.linkText(getValueFromPOM));
				break;

			case "tagname":
				element = driver.findElement(By.tagName(getValueFromPOM));
				break;

			case "partiallinktext":
				element = driver.findElement(By.partialLinkText(getValueFromPOM));
				break;

			default:

				throw new IllegalArgumentException("Unable to found");

			}	

		}catch(StaleElementReferenceException e1){

			log.info("StaleElementReferenceException function Catch block. Calling Refresh Object.");
			RefreshObject(getValueFromPOM);
			if((selectByLocatorType(getValueFromPOM)).isDisplayed()){

				return selectByLocatorType(getValueFromPOM);
			}

		}catch (ElementNotVisibleException e2) {

			log.info("ElementNotVisibleException Exception Occured :"+ e2);
			RefreshObject(getValueFromPOM);
			if((selectByLocatorType(getValueFromPOM)).isDisplayed()){

				return selectByLocatorType(getValueFromPOM);
			}

		}catch(NullPointerException e3) {

			log.info("NullPointerException Occured :"+ e3.getMessage());

		}catch (Exception e) {

			log.info("Exception: Element is not found ;"+ e);

		}	


		return element;


	}

	/**
	 * 
	 * 
	 * @Objective <b>This method is to find the different locators in webpage and store in list web elements. We can get the element in POM classes and locator type in run-time environment</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>10-May-16</b>
	 */

	public static List<WebElement> listSelectByLocatorType(String getValueFromPOM){

		List<WebElement> element = null;

		try{

			String loc = Runtimevalue.getProperty("locatorType");

			switch(loc.toLowerCase()){

			case "id":
				element = driver.findElements(By.id(getValueFromPOM));
				break;

			case "xpath":
				element = driver.findElements(By.xpath(getValueFromPOM));
				break;

			case "css":
				element = driver.findElements(By.cssSelector(getValueFromPOM));
				break;

			case "classname":	
				element = driver.findElements(By.className(getValueFromPOM));
				break;

			case "name":
				element = driver.findElements(By.name(getValueFromPOM));
				break;

			case "linktext":
				element = driver.findElements(By.linkText(getValueFromPOM));
				break;

			case "tagname":
				element = driver.findElements(By.tagName(getValueFromPOM));
				break;

			case "partiallinktext":
				element = driver.findElements(By.partialLinkText(getValueFromPOM));
				break;

			default:

				throw new IllegalArgumentException("Unable to found");

			}	

		}catch(StaleElementReferenceException e1){

			log.info("StaleElementReferenceException function Catch block. Calling Refresh Object.");
			RefreshObject(getValueFromPOM);
			if((selectByLocatorType(getValueFromPOM)).isDisplayed()){

				return listSelectByLocatorType(getValueFromPOM);
			}

		}catch (ElementNotVisibleException e2) {

			log.info("ElementNotVisibleException Exception Occured :"+ e2);
			RefreshObject(getValueFromPOM);
			if((selectByLocatorType(getValueFromPOM)).isDisplayed()){

				return listSelectByLocatorType(getValueFromPOM);
			}

		}catch(NullPointerException e3) {

			log.info("NullPointerException Occured :"+ e3.getMessage());

		}catch (Exception e) {

			log.info("Exception: Element is not found ;"+ e);

		}	


		return element;


	}

	/**
	 * 
	 * @Objective <b>This method is to wait till text to be presented in webpage</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean waituntiltextpresent(String getValueFromPOM, String strTestObject) throws Exception {

		boolean elementStatus= false;
		getDisplayTimeout = Param.getProperty("elementDisplayTimeout");
		intElementDisplayTimeout = Integer.parseInt(getDisplayTimeout);

		try{
			elementStatus = new WebDriverWait(driver,intElementDisplayTimeout).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return selectByLocatorType(getValueFromPOM).getText().length() != 0;
				}
			});
		} catch(StaleElementReferenceException e1){
			log.info("RefreshObject function Catch block");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				return waituntiltextpresent(getValueFromPOM, strTestObject);
			}
		} catch(Exception e){
			Report_Functions.ReportEventFailure(doc,"","Error occured while waiting for the element : '"+ strTestObject +"'.Error description is : "+ e +"." , true);
			elementStatus = false;
		}

		if(elementStatus){
			Report_Functions.ReportEventSuccess(doc,"1","","'The text is present in '"+ strTestObject +"' successfully",3);
			return elementStatus;
		}else{
			Report_Functions.ReportEventFailure(doc,"","'The text is not present in '"+ strTestObject +"'." , true);
			return elementStatus;
		}
	}

	/**
	 * 
	 * @Objective <b>This method is to wait till value to be presented in webpage</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean waituntilvaluepresent(String getValueFromPOM, String strTestObject) throws Exception {
		boolean elementStatus= false;
		int icount = 0;

		try{

			if((selectByLocatorType(getValueFromPOM)).getAttribute("value").length() != 0){
				elementStatus = true;
			}

			while((selectByLocatorType(getValueFromPOM)).getAttribute("value").length() == 0){
				RefreshObject(getValueFromPOM);

				if((selectByLocatorType(getValueFromPOM)).getAttribute("value").length() != 0){
					elementStatus = true;
					break;
				}
				if(icount == 10  && (selectByLocatorType(getValueFromPOM)).getAttribute("value").length() == 0){
					break;
				}
				icount = icount + 1;
			}
		} catch(StaleElementReferenceException e1){
			log.info("RefreshObject function Catch block");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				return waituntilvaluepresent(getValueFromPOM, strTestObject);
			}
		} catch(Exception e){
			Report_Functions.ReportEventFailure(doc,"","Error occured while waiting for the element : '"+ strTestObject +"'.Error description is : "+ e +"." , true);
			elementStatus = false;
		}

		if(elementStatus){
			Report_Functions.ReportEventSuccess(doc,"1","","'The text is present in "+ strTestObject +"' successfully",3);
			return elementStatus;
		}else{
			Report_Functions.ReportEventFailure(doc,"","'The text is not present in "+ strTestObject +"'." , true);
			return elementStatus;
		}
	}

	/**
	 * 
	 * @Objective <b>This method is to wait till value not to be presented in webpage</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean waituntilvaluenotpresent(String getValueFromPOM, String strTestObject) throws Exception {

		boolean elementStatus= false;
		getDisplayTimeout = Param.getProperty("elementDisplayTimeout");
		intElementDisplayTimeout = Integer.parseInt(getDisplayTimeout);

		try{

			elementStatus = new WebDriverWait(driver,intElementDisplayTimeout).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return selectByLocatorType(getValueFromPOM).getAttribute("value").equals("");
				}
			});
		} catch(StaleElementReferenceException e1){
			log.info("RefreshObject function Catch block");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				return waituntilvaluenotpresent(getValueFromPOM, strTestObject);

			}
		} catch(Exception e){
			Report_Functions.ReportEventFailure(doc,"waituntilvaluenotpresent","Error occured while waiting for the element : '"+ strTestObject +"'.Error description is : "+ e +"." , true);
			elementStatus = false;
		}

		if(elementStatus){
			Report_Functions.ReportEventSuccess(doc,"1","waituntilvaluenotpresent","'The text is not present in "+ strTestObject +"' successfully",3);
			return elementStatus;
		}else{
			Report_Functions.ReportEventFailure(doc,"waituntilvaluenotpresent","'The text is present in "+ strTestObject +"'." , true);
			return elementStatus;
		}
	}

	/**
	 * 
	 * @Objective <b>This method check whether element is not selected. If element is not selected, click on check-box button</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>05-April-16</b>
	 */

	public static boolean WebCheckboxON(String getValueFromPOM, String strTestObject )throws Exception  {
		boolean elementStatus=false;
		try {

			if (!selectByLocatorType(getValueFromPOM).isSelected())
			{
				selectByLocatorType(getValueFromPOM).click();
			}
			Report_Functions.ReportEventSuccess(doc,"1","WebCheckboxON"," The checkbox  '"+ strTestObject +"' is  selected successfully.",3);
			elementStatus=true;
		} catch (StaleElementReferenceException e) {
			log.info("StaleElementReferenceException captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				return WebCheckboxON(getValueFromPOM,strTestObject);
			}
		} catch (NoSuchElementException e) {
			Report_Functions.ReportEventFailure(doc,"WebCheckboxON","WebElement  '"+ strTestObject +"'  is not selected." , true); 
			log.info("No Element Found to enter text" + e);
			elementStatus=false;
		}catch(Exception e){

			Report_Functions.ReportEventFailure(doc,"WebCheckboxON","WebElement  '"+ strTestObject +"'  is not selected." , true); 
			log.info("No Element Found to check" + e);
			elementStatus=false;

		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to initialize an test execution by the reference of app component name, before start the execution kill the running browsers</b>
	 * @param strAppName <b>App component name, will get it from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean LaunchApplication(String strAppName,int strExecEventFlag) throws Exception{
		boolean LaunchApplication= false;
		String strdata=null;
		String strURLdata=null;
		try{

			if(strExecEventFlag==1){
				strURLdata=RetrieveTestDataValue("LaunchApplication",strAppName,strExecEventFlag);
			}

			strdata=Param.getProperty(strURLdata);

			if(strdata==null){
				Report_Functions.ReportEventFailure(doc,  "LaunchApplication",  "Required details are not provided.", false);
				return false;
			}

			//Kill all the existing browsers at the start of Launch Application
			try{
				if(Param.getProperty("To_Kill_All_Running_Browsers").equalsIgnoreCase("True")){

					//Checking IE Browser already running. If Running, kill the browser.
					if (isProcessRunning(Param.getProperty("IE_ImageName"))) {
						log.info("IE browser running. Killing the Process before start of TestCase.");
						Runtime.getRuntime().exec("taskkill /F /IM " + Param.getProperty("IE_ImageName"));
						Thread.sleep(1000);

						//Checking Mozilla Browser already running. If Running, kill the browser.
					}else if (isProcessRunning(Param.getProperty("Mozilla_ImageName"))) {
						log.info("Mozilla browser running. Killing the Process before start of TestCase.");
						Runtime.getRuntime().exec("taskkill /F /IM " + Param.getProperty("Mozilla_ImageName"));
						Thread.sleep(1000);

						//Checking Chrome Browser already running. If Running, kill the browser.
					}else if (isProcessRunning(Param.getProperty("Chrome_ImageName"))) {
						log.info("Chrome browser running. Killing the Process before start of TestCase.");
						Runtime.getRuntime().exec("taskkill /F /IM " + Param.getProperty("Chrome_ImageName"));
						Thread.sleep(1000);
					}
				}
			}catch(Exception e){
				log.info("Exception occured while killing the Existing running browsers. Exception occured is : "+ e);
				throw e;
			}
			
			//Kill all the existing browsers at the start of Launch Application
			try{
				if(Param.getProperty("To_Kill_Browser_Driver").equalsIgnoreCase("True")){

					//Checking IE Browser already running. If Running, kill the browser.
					if (isProcessRunning(Param.getProperty("IEDriver_ImageName"))) {
						log.info("IE Browser Driver running. Killing the Process before start of TestCase.");
						Runtime.getRuntime().exec("taskkill /F /IM " + Param.getProperty("IEDriver_ImageName"));
						Thread.sleep(3000);
						//Checking Chrome Browser already running. If Running, kill the browser.
					}else if (isProcessRunning(Param.getProperty("ChromeDriver_ImageName"))) {
						log.info("Chrome browser driver running. Killing the Process before start of TestCase.");
						Runtime.getRuntime().exec("taskkill /F /IM " + Param.getProperty("ChromeDriver_ImageName"));
						Thread.sleep(3000);
					}
				}
			}catch(Exception e){
				log.info("Exception occured while killing the browser drivers. Exception occured is : "+ e);
				throw e;
			}

			if(Param.getProperty("testBrowser").equalsIgnoreCase("Mozilla")){
				//To Load Firefox driver Instance. 
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				log.info("Firefox Driver Instance loaded successfully.");

				try{
					driver.get(strdata);
					Report_Functions.ReportEventSuccess(doc, "1", "LaunchApplication", "URL '"+strdata+"' launched successfully", 2);
					LaunchApplication =true;
				}catch(Exception e){
					log.info("Exception in Launching Application. Exception is :  "+ e);
					Report_Functions.ReportEventFailure(doc, "LaunchApplication", "Error in opening the application '"+strdata+"'", false);		
					LaunchApplication =false;
				}

			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("Chrome")){
				//To Load Chrome driver Instance.
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//BrowserDrivers//chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				log.info("Chrome Driver Instance loaded successfully.");

				try{
					driver.get(strdata);
					Report_Functions.ReportEventSuccess(doc, "1", "LaunchApplication", "URL '"+strdata+"' launched successfully", 2);
					LaunchApplication =true;
				}catch(Exception e){
					log.info("Exception in Launching Application. Exception is :  "+ e);
					Report_Functions.ReportEventFailure(doc, "LaunchApplication", "Error in opening the application '"+strdata+"'", false);		
					LaunchApplication =false;
				}

			}else if(Param.getProperty("testBrowser").equalsIgnoreCase("IE")){
				//To Load IE driver Instance.

				File file = new File("BrowserDrivers/IEDriverServer.exe");

				String browserPath = file.getAbsolutePath();
				//System.setProperty("webdriver.ie.driver", "BrowserDrivers/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", browserPath);

//				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
//				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
//				DesiredCapabilities.internetExplorer().setCapability("ignoreProtectedModeSettings", true);
//				caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
//				caps.setCapability("ignoreZoomSetting", true);
//				caps.setCapability("requireWindowFocus", false);
//				caps.setJavascriptEnabled(true);

				//Handled exception to find the error fadminor IE11 browser update pop-up				
				driver = new InternetExplorerDriver();

				/*System.setProperty("webdriver.ie.driver", "BrowserDrivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();*/

				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				log.info("IE Driver Instance loaded successfully.");

				try{
					driver.navigate().to(strdata);
					Report_Functions.ReportEventSuccess(doc, "1", "LaunchApplication", "URL '"+strdata+"' launched successfully", 2);
					LaunchApplication =true;
				}catch(Exception e){
					log.info("Exception in Launching Application. Exception is :  "+ e);
					Report_Functions.ReportEventFailure(doc, "LaunchApplication", "Error in opening the application '"+strdata+"'", false);		
					LaunchApplication =false;
				}

			}			
		} catch(WebDriverException e){

			log.info("Error occurred in launch application :" +e.getMessage());
			LaunchApplication(strAppName, strExecEventFlag);
			LaunchApplication = true;

		} catch(Exception e){
			log.info("Exception in LaunchApplication is : "+ e);
			LaunchApplication=false;
		}
		return LaunchApplication;
	}

	/**
	 * 
	 * @Objective <b>This method is to compare the values between excel sheet and given webelement. If true the expected value will be return</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Will get the compare values in excel sheet<>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebElementValueCompare(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {

		String actualResult=null;
		String strData=null;
		boolean WebElementValueCompare=false;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebElementValueCompare",strColumnName,strExecEventFlag);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebElementValueCompare",  "Required details are not provided.", false);
				return false;
			}

			actualResult = selectByLocatorType(getValueFromPOM).getAttribute("value");

			if((actualResult.trim()).equalsIgnoreCase(strData.trim())){
				WebElementValueCompare=true;
				Report_Functions.ReportEventSuccess(doc,"1", "", ""+strTestObject+"  Actual Value '" + actualResult + "' matches the Expected value '" + strData + "'", 2);
			}else{
				WebElementValueCompare=false;
				Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+" Actual Value '" + actualResult + "' does not match the Expected Value '" + strData + "'", true);
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+" Actual Value '" + actualResult + "' does not match the Expected Value '" + strData + "'", true);
			WebElementValueCompare=false;
			log.info("No Element Found to compare Text : " + e);
		}
		return WebElementValueCompare;
	}

	/**
	 * 
	 * @Objective <b>This method is to get the attribute value for title and compare between excel sheet and given webelement</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @return
	 * @throws Exception
	 */

	public static boolean WebElementAttTitleCompare(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {

		String actualResult=null;
		String strData=null;
		boolean WebElementAttTitleCompare = false;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebElementAttTitleCompare",strColumnName,strExecEventFlag);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebElementAttTitleCompare",  "Required details are not provided.", false);
				return false;
			}

			actualResult = selectByLocatorType(getValueFromPOM).getAttribute("title");

			if((actualResult.trim()).equalsIgnoreCase(strData.trim())){
				WebElementAttTitleCompare = true;
				Report_Functions.ReportEventSuccess(doc,"1", "", ""+strTestObject+"  Actual Value '" + actualResult + "' matches the Expected value '" + strData + "'", 2);
			}else{
				WebElementAttTitleCompare = false;
				Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+" Actual Value '" + actualResult + "' does not match the Expected Value '" + strData + "'", true);
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+" Actual Value '" + actualResult + "' does not match the Expected Value '" + strData + "'", true);
			WebElementAttTitleCompare = false;
			log.info("No Element Found to compare Text : " + e);
		}
		return WebElementAttTitleCompare;
	}

	/**
	 * 
	 * @Objective <b>This method is to get text from the webelement</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static String WebElementGetValue(String getValueFromPOM)throws Exception  {
		String strData=null;
		try {
			strData= selectByLocatorType(getValueFromPOM).getText();
			Report_Functions.ReportEventSuccess(doc,"1","WebElementGetValue"," The Value of the Element  '"+Runtimevalue.getProperty("Element")+"' is  : " +  strData ,3);
			return strData;
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebListSelect","WebElement  '"+Runtimevalue.getProperty("Element")+"'  is not available and no value is returned" , true); 
			log.info("No Element Found to enter text" + e);
			return null;
		}
	}

	/**
	 * 
	 * @Objective <b>This method is to verify the all elements in webpage, dropdown values are added in list. Expected value will be verified to actual value(value will be in excel sheet)</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Will get the compare values in excel sheet<>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebListVerifyValue(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag) throws Exception {

		String strData=null;
		boolean match=false;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebListVerifyValue",strColumnName,strExecEventFlag);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebListVerifyValue",  "Required details are not provided.", false);
				return false;
			}
			WebElement dropdown = selectByLocatorType(getValueFromPOM);
			Select select = new Select(dropdown);  
			List<WebElement> options = select.getOptions();  
			for(WebElement we:options)  
			{  
				if (we.getText().equals(strData)){
					match = true;
				}
			}
			if(match){
				Report_Functions.ReportEventSuccess (doc,"1","WebListVerifyValue","The item '"+ strData +"' is present in WebList  '"+strTestObject+"'.",2);
			}else{
				Report_Functions.ReportEventFailure(doc,"WebListVerifyValue","The item '"+ strData +"' is not present in WebList  '"+strTestObject+"'." , true);
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebListVerifyValue","The WebList element  '"+strTestObject+"' is not found " , true);
			log.info("No Element Found to enter text" + e);
		}
		return match;
	}

	/**
	 * 
	 * @Objective <b>This method is to checking and opening the SQL connection<b>
	 * @param sqlserver
	 * @param sqldbname
	 * @param sqlusername
	 * @param sqlpassword
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean SQLDBOpenConnection(String sqlserver, String sqldbname, String sqlusername, String sqlpassword)throws Exception  {
		boolean elementStatus= false;
		//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"        
		String dbUrl = "jdbc:sqlserver://"+ sqlserver +";DatabaseName=" + sqldbname +";";                  
		//Database Username     
		String username = sqlusername;   
		//Database Password     
		String password = sqlpassword; 

		if(sqldbname==null || sqlusername==null || sqlpassword==null || sqlserver==null){
			Report_Functions.ReportEventFailure(doc,  "SQLDBOpenConnection",  "Required details are not provided.", false);
			return false;
		}
		try {
			//Load mysql jdbc driver        
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  

			//Create Connection to DB       
			con = DriverManager.getConnection(dbUrl,username,password);
			//Create Statement Object       
			stmt = con.createStatement(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "SQLDBOpenConnection", "SQL Connection is established from '"+sqldbname+"' DB in '"+sqlserver+"' Successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "SQLDBOpenConnection",  "Error occured while connecting to the SQL Server.Error description is : "+ e.getMessage() +".", false);
			log.info("SQLDBOpenConnection Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * @Objective <b>This method is to deleting records from SQL DB<b>
	 * @param sqltablename
	 * @param sqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean SQLDBDelete(String sqltablename, String sqlcondition, int strExecEventFlag)throws Exception  {
		boolean elementStatus= false;
		String tablename = null;
		String condition = null;

		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("SQLDBDelete",sqltablename,strExecEventFlag);
				condition=RetrieveTestDataValue("SQLDBDelete",sqlcondition,strExecEventFlag);
			}

			if(tablename==null || condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBDelete",  "Required details are not provided in test data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Error log in reading file is "+e);
			throw e;
		}

		//Query to check records are available in DB
		//Query to Execute  

		//String check = "IF EXISTS(SELECT * FROM "+tablename+" WHERE "+condition+") SELECT 'TRUE' as STATUS ELSE SELECT 'FALSE' as STATUS";

		String check = "select * from "+tablename +" where "+condition;

		String query = "Delete from "+ tablename +" where "+ condition;

		ResultSet rs = null;

		try {			
			rs = stmt.executeQuery(check);		

			int temp=0;	
			while(rs.next()){
				temp++;
			}

			if(temp >= 1){

				stmt.execute(query);
				elementStatus=true;

				Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDelete", "The SQL Query : "+ query + " executed successfully.", 2);

			}//If rows not available FALSE will be returned so no delete
			else if(temp < 1){
				Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDelete", "The SQL Query : "+ query + " records are not availbale in DB", 2);
				elementStatus=true;
			}

		}catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "SQLDBDelete",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", false);
			log.info("SQLDBDelete Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to closing the SQL DB<b>
	 * @return
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean SQLDBCloseConnection()throws Exception  {
		boolean elementStatus= false;

		try {
			// closing DB Connection       
			con.close(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "SQLDBCloseConnection", "The SQL DB Connection disconnected successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "SQLDBCloseConnection",  "Error occured while closing the SQL DB.Error description is : "+ e.getMessage() +".", false);
			log.info("SQLDBCloseConnection Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Check for the webelement enabled<b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebElementEnabled(String getValueFromPOM, String strTestObject) throws Exception {

		boolean elementenable;
		boolean WebElementEnabled = false;
		try {
			elementenable=selectByLocatorType(getValueFromPOM).isEnabled();

			if(elementenable){
				WebElementEnabled = true;
				Report_Functions.ReportEventSuccess(doc, "1", "", "The object '"+strTestObject+"' is enabled as expected.", 3);
			}else{
				WebElementEnabled=false;
				Report_Functions.ReportEventFailure(doc, "", "The object '"+strTestObject+"' is not enabled.", true);
			}

		} catch (Exception e) { 	
			log.info("Exception while finding enabled or disabled" + e);
			WebElementEnabled=false;
		}
		return WebElementEnabled;
	}

	/**
	 * 
	 * @Objective <b>Check for the webelement disabled<b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebElementDisabled(String getValueFromPOM, String strTestObject) throws Exception {

		boolean elementenable;
		boolean WebElementDisabled = false;
		try {
			elementenable=selectByLocatorType(getValueFromPOM).isEnabled();

			if(elementenable){
				WebElementDisabled=false;
				Report_Functions.ReportEventFailure(doc, "", "The object '"+strTestObject+"' is not disabled.", true);
			}else{
				WebElementDisabled = true;
				Report_Functions.ReportEventSuccess(doc, "1", "", "The object '"+strTestObject+"' is disabled as expected.", 3);
			}

		} catch (Exception e) { 	
			log.info("Exception while finding enabled or disabled" + e);
			WebElementDisabled=false;
		}
		return WebElementDisabled;
	}

	/**
	 * 
	 * @Objective <b>This method is to check the status for selected checkbox</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>22-April-16</b>
	 */

	public static boolean webCheckBoxCheckStatus(String getValueFromPOM, String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{

			if(selectByLocatorType(getValueFromPOM).getAttribute("checked").equals("true")){

				Report_Functions.ReportEventSuccess(doc, "1", "", "Checbox status '"+ strTestObject +"' is checked successfully.",   3);
				elementStatus = true;
			}

			else if(selectByLocatorType(getValueFromPOM).getAttribute("checked").equals(null)){

				Report_Functions.ReportEventFailure(doc, "", "Checkbox status for '"+ strTestObject +"' is unchecked.", true);
				elementStatus = false;
			}

		}catch(StaleElementReferenceException e){

			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				return webElementSelectedStatus(getValueFromPOM, strTestObject);
			}
		}catch(Exception e){

			Report_Functions.ReportEventFailure(doc, "", "Checkbox status '"+ strTestObject +"' is unchecked.", true);
			elementStatus = false;
			log.info("No Element Found to check the checkbox" + e);

		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to check the status for unselected checkbox</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>5-May-16</b>
	 */

	public static boolean webCheckBoxUnCheckStatus(String getValueFromPOM, String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{


			if(selectByLocatorType(getValueFromPOM).getAttribute("disabled").equals("true")){

				Report_Functions.ReportEventSuccess(doc, "1", "", "Checbox status '"+ strTestObject +"' is unchecked successfully.",   3);
				elementStatus = true;
			}

			else{

				Report_Functions.ReportEventFailure(doc, "", "Checkbox status for '"+ strTestObject +"' is not unchecked.", true);
				elementStatus = false;

			}
		}catch(StaleElementReferenceException e){

			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				return webCheckBoxUnCheckStatus(getValueFromPOM, strTestObject);
			}
		}catch(Exception e){

			Report_Functions.ReportEventFailure(doc, "", "Checkbox status '"+ strTestObject +"' is not unchecked.", true);
			elementStatus = false;
			log.info("No Element Found to check the checkbox" + e);

		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to check the status for checkbox</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>28-April-16</b>
	 */

	public static boolean webCheckBoxStatus(String getValueFromPOM, String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{

			if(selectByLocatorType(getValueFromPOM).getAttribute("value").equals("on")){

				Report_Functions.ReportEventSuccess(doc, "1", "", "Checbox status '"+ strTestObject +"' is ON",   3);
				elementStatus = true;
			}

			else{

				log.info("Element is not available");
				Report_Functions.ReportEventFailure(doc, "", "Checkbox status '"+ strTestObject +"' is OFF", true);
				elementStatus = false;

			}

		}catch(StaleElementReferenceException e){

			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				return webCheckBoxStatus(getValueFromPOM, strTestObject);
			}
		}catch(Exception e){

			Report_Functions.ReportEventFailure(doc, "", "Checkbox status '"+ strTestObject +"' is OFF", true);
			elementStatus = false;
			log.info("No Element Found to check the checkbox" + e);

		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method check whether element is selected. If is selected, click on check-box button to uncheck it</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebCheckboxOFF(String getValueFromPOM, String strTestObject)throws Exception  {
		boolean elementStatus=false;
		try {
			if ((selectByLocatorType(getValueFromPOM)).isSelected())
			{
				selectByLocatorType(getValueFromPOM).click();
			}
			Report_Functions.ReportEventSuccess(doc,"1","WebCheckboxOFF"," The checkbox  '"+ strTestObject +"' is  unchecked successfully.",3);
			elementStatus=true;
		} catch (StaleElementReferenceException e) {
			log.info("StaleElementReferenceException captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				return WebCheckboxOFF(getValueFromPOM, strTestObject);

			}
		} catch (NoSuchElementException e) {
			Report_Functions.ReportEventFailure(doc,"WebCheckboxOFF","Exception occured while identifying the object  '"+ strTestObject +"'." , true); 
			log.info("Exception while unchecking the checkbox." + e);
			elementStatus=false;
		}catch(Exception e){

			Report_Functions.ReportEventFailure(doc,"WebCheckboxOFF","WebElement  '"+ strTestObject +"'  is not unselected." , true); 
			log.info("No Element Found to check" + e);
			elementStatus=false;

		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Verifies the webelement is empty</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebElementEmpty(String getValueFromPOM, String strTestObject )throws Exception  {
		boolean elementStatus=false;
		try {

			if ((selectByLocatorType(getValueFromPOM)).getAttribute("value").equals(""))

			{
				Report_Functions.ReportEventSuccess(doc,"1","WebElementEmpty"," The object  '"+ strTestObject +"' value is empty.",3);
				elementStatus=true;
			}
		} catch (StaleElementReferenceException e) {
			log.info("StaleElementReferenceException captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			return WebElementEmpty(getValueFromPOM, strTestObject);
		}  catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebElementEmpty","Exception occured while identifying the object  '"+ strTestObject +"'." , true); 
			log.info("No Element Found to verify text" + e);
			elementStatus=false;
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Verifies the length of the webelement</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebElementLengthCompare(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {

		int actualResult;
		int expectedresult;
		String strData=null;
		boolean WebElementLengthCompare=false;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebElementLengthCompare",strColumnName,strExecEventFlag);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebElementLengthCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			expectedresult = Integer.parseInt(selectByLocatorType(getValueFromPOM).getAttribute("maxlength"));
			selectByLocatorType(getValueFromPOM).sendKeys(strData);
			actualResult = selectByLocatorType(getValueFromPOM).getAttribute("value").length();

			if(actualResult == expectedresult){
				WebElementLengthCompare=true;
				Report_Functions.ReportEventSuccess(doc,"1", "WebElementLengthCompare", "The object '"+strTestObject+"'s  actual length '" + actualResult + "' matches the Expected length '" + expectedresult + "'", 2);
			}else{
				WebElementLengthCompare=false;
				Report_Functions.ReportEventFailure(doc,"WebElementLengthCompare", "The object '"+strTestObject+"'s  actual length '" + actualResult + "' doesnot matches with the Expected length '" + expectedresult + "'" + strData + "'", true);
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebElementLengthCompare", "Exception occured while getting the maxlength of the object  '"+ strTestObject +"'.", true);
			WebElementLengthCompare=false;
			log.info("No Element Found to compare Text : " + e);
		}
		return WebElementLengthCompare;
	}

	/**
	 * 
	 * @Objective <b>Opening RRBS DB Connection</b>
	 * @param dbserver
	 * @param portnumber
	 * @param dbname
	 * @param dbusername
	 * @param dbpassword
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean RRBSDBOpenConnection(String dbserver, String portnumber, String dbname, String dbusername, String dbpassword)throws Exception  {
		boolean elementStatus= false;

		String serverName = Param.getProperty(dbserver);
		String portNumber = Param.getProperty(portnumber);
		String sid = Param.getProperty(dbname);


		//Connection URL Syntax: "jdbc:oracle:thin:@://ipaddress:portnumber:db_name"        
		String dbUrl = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid; 

		//Database Username     
		String username = Param.getProperty(dbusername);   
		//Database Password     
		String password = Param.getProperty(dbpassword); 

		if(dbname==null || dbusername==null || dbpassword==null || dbserver==null || portnumber==null){
			Report_Functions.ReportEventFailure(doc,  "RRBSDBOpenConnection",  "Required details are not provided.", false);
			return false;
		}
		try {
			// Load the JDBC driver

			String driverName = "oracle.jdbc.OracleDriver";

			Class.forName(driverName);         
			//Create Connection to DB       
			rrbsconnection = DriverManager.getConnection(dbUrl,username,password);
			//Create Statement Object       
			rrbsstatement = rrbsconnection.createStatement(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBOpenConnection", "RRBS DB Connection is established from '"+sid+"' DB in '"+serverName+"' Successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBOpenConnection",  "Error occured while connecting to the RRBS DB Server.Error description is : "+ e.getMessage() +".", false);
			log.info("RRBSDBOpenConnection Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Deleting the record from RRBS DB</b>
	 * @param rrbstablename
	 * @param rrbscondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean RRBSDBDelete(String rrbstablename, String rrbscondition, int strExecEventFlag)throws Exception  {
		boolean elementStatus= false;
		String tablename = null;
		String condition = null;

		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("RRBSDBDelete",rrbstablename,strExecEventFlag);
				condition=RetrieveTestDataValue("RRBSDBDelete",rrbscondition,strExecEventFlag);
			}

			if(tablename==null || condition==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBDelete",  "Required details are not provided in test data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Errror log in reading file is "+e);
			throw e;
		}
		//Query to Execute      
		String check = "select * from "+tablename +" where "+condition;
		String query = "Delete from "+ tablename +" where "+ condition;

		ResultSet rs = null;

		try {

			rs = rrbsstatement.executeQuery(check);

			int temp = 0;
			while(rs.next()){

				temp++;

			}

			if(temp > 0){

				rrbsstatement.execute(query); 
				elementStatus=true;
				Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBDelete", "The RRBS Query : "+ query + " executed successfully", 2);

			}

			//If rows not available FALSE will be returned so delete operation doesn't works here

			else if(temp < 1){

				Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBDelete", "The RRBS Query : "+ query + " records are not availbale in DB", 2);
				elementStatus = true;

			}

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBDelete",  "Error occured while executing the RRBS query.Error description is : "+ e.getMessage() +".", false);
			log.info("RRBSDBDelete Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Verify to compare the dates in RRBS DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param Date_Format
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>03-May-16</b>
	 */

	public static boolean RRBSDBDateCompare(String sqltablename, String strsqlcolumnname,String strsqlcondition,String Date_Format,int strExecEventFlag)throws Exception  {

		boolean RRBSDBDateCompare= false;
		String query = null;  
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Expected_value = null;
		String Actual_Value = null;
		String Current_Date=null;
		String expected_db_Date = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("RRBSDBDateCompare",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("RRBSDBDateCompare",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("RRBSDBDateCompare",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();

			Current_Date = dateFormat.format(date);

			Expected_value = Current_Date.trim();

			// Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";

			rrbsresultset = rrbsstatement.executeQuery(query);

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBDateCompare Error : " + e);
			RRBSDBDateCompare=false;
		}

		try{

			rrbsresultset.next();

			Actual_Value = rrbsresultset.getString(1);
			String db_Date = Actual_Value.split(" ")[0];

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date dateToChange = dateFormat.parse(db_Date);			

			SimpleDateFormat finalDateFormat = new SimpleDateFormat(Date_Format);
			expected_db_Date = finalDateFormat.format(dateToChange);


		} catch (Exception ne) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "No Record found for this query: "+query, true);
			log.info("RRBSDBDateCompare Error : "+ne);
			RRBSDBDateCompare=false;
		}

		try{

			if(!rrbsresultset.wasNull()){            // If some value is present in the fired Query

				if(expected_db_Date.equals(Expected_value)){

					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBDateCompare", "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' matches with the expected date : '"+Expected_value+"' ", 2);
					RRBSDBDateCompare=true;

				}else if(!(expected_db_Date.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' does not match with the expected date : '"+Expected_value+"' ", true);
					RRBSDBDateCompare=false;
				}
			}
			else if(rrbsresultset.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBDateCompare", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					RRBSDBDateCompare=true;
				}

				else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					RRBSDBDateCompare=false;
				}
			}

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "Error occured while comparing the dates in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBDateCompare Error : " + e);
			RRBSDBDateCompare=false;
		}

		return RRBSDBDateCompare;
	}

	public static boolean RRBSDBSelect(String sqltablename, String strsqlcolumnname,String strsqlcondition,String strExpectedvalue,int strExecEventFlag)throws Exception  {
		boolean RRBSDBSelect= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		//String Expected_value = null;		
		String Expected_value = "";
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("RRBSDBSelect",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("RRBSDBSelect",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("RRBSDBSelect",strsqlcondition,strExecEventFlag);
				Expected_value=RetrieveTestDataValue("RRBSDBSelect",strExpectedvalue,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBSelect",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";

			rrbsresultset = rrbsstatement.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "RRBSDBSelect",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBSelect Error : " + e);
			RRBSDBSelect=false;
		}

		try{
			rrbsresultset.next();
			Actual_Value = rrbsresultset.getString(1).trim();

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "RRBSDBSelect",  "No Record found for this query: "+query, true);
			log.info("RRBSDBSelect Error : ");
			RRBSDBSelect=false;
		}

		try{
			if(!rrbsresultset.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","RRBSDBSelect", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches the expected value : '"+Expected_value+"'", 2);
					RRBSDBSelect=true;
				}else if(!(Actual_Value.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBSelect",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);
					RRBSDBSelect=false;
				}
			}

			else if(rrbsresultset.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBSelect", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					RRBSDBSelect=true;
				}else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBSelect",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					RRBSDBSelect=false;
				}
			}
		}catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "RRBSDBSelect",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBSelect Error : " + e);
			RRBSDBSelect=false;
		}
		return RRBSDBSelect;
	}

	/**
	 * 
	 * @Objective <b>losing RRBS DB connection</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean RRBSDBCloseConnection()throws Exception  {
		boolean elementStatus= false;

		try {
			// closing DB Connection       
			rrbsconnection.close(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBCloseConnection", "The RRBS DB Connection disconnected successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBCloseConnection",  "Error occured while closing the RRBS DB.Error description is : "+ e.getMessage() +".", false);
			log.info("RRBSDBCloseConnection Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Verifies the popup alert</b>
	 * @param Expected_Value
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean Statictextcompare(String Expected_Value, int strExecEventFlag)throws Exception  {

		boolean elementStatus= false;
		String expectedtext = null;
		String actualtext = null;
		getAlertTimeout = Param.getProperty("staticAlertTimeout");
		intstaticAlertTimeout = Integer.parseInt(getAlertTimeout);

		try{

			if(strExecEventFlag==1){
				expectedtext=RetrieveTestDataValue("Statictextcompare",Expected_Value,strExecEventFlag);
			}

			if(expectedtext==null){
				Report_Functions.ReportEventFailure(doc,  "Statictextcompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Errror log in reading file is "+e);
			throw e;
		}

		try {
			new WebDriverWait(driver,intstaticAlertTimeout).until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			actualtext = alert.getText();
			String actual = actualtext.replaceAll("[\n\r]", "");
			String exp = expectedtext.replaceAll("[\n\r]", "");

			if(actual.equalsIgnoreCase(exp))
			{
				elementStatus=true;
				Report_Functions.ReportEventSuccess(doc, "1", "Statictextcompare", "The actual value : "+ actualtext + " matches with the expected value :"+ expectedtext +".", 2);
			} else {
				elementStatus=false;
				Report_Functions.ReportEventFailure(doc,  "Statictextcompare",  "The actual value : "+ actualtext + " doesn't matches with the expected value :"+ expectedtext +".", false);	
			}
			alert.accept();
		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "Statictextcompare",  "Error occured while retriveing the text from Popup alert.Error description is : "+ e.getMessage() +".", false);
			log.info("Statictextcompare Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to cancel the java script alerts</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>25-May-16</b>
	 * 
	 */

	public static boolean staticAlertDismiss(String expectedValue, int strExecEventFlag) throws Exception{

		boolean elementStatus = false;
		String actualText = null;
		String expectedText = null;
		getAlertTimeout = Param.getProperty("staticAlertTimeout");
		intstaticAlertTimeout = Integer.parseInt(getAlertTimeout);

		try{

			if(strExecEventFlag==1){
				expectedText = RetrieveTestDataValue("staticAlertDismiss", expectedValue, strExecEventFlag);
			}

			if(expectedText == null){
				Report_Functions.ReportEventFailure(doc,  "staticAlertDismiss",  "Required details are not provided in test data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Errror log in reading file is "+e);
			e.printStackTrace();
		}

		try{

			new WebDriverWait(driver, intstaticAlertTimeout).until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			actualText = alert.getText();
			alert.dismiss();

			if(actualText.equalsIgnoreCase(expectedText))
			{
				elementStatus=true;
				Report_Functions.ReportEventSuccess(doc, "1", "staticAlertDismiss", "The actual value : "+ actualText + " matches with the expected value :"+expectedText+" and alert is dismissed successfully.", 2);
			} else {
				elementStatus=false;
				Report_Functions.ReportEventFailure(doc,  "staticAlertDismiss",  "The actual value : "+ actualText + " doesn't matches with the expected value :"+ expectedText +" and alert is dismissed", false);	
			}

		}catch(Exception e){

			log.info("Exception occurred in Static alert dismiss");
			e.printStackTrace();
			Report_Functions.ReportEventFailure(doc,"","Static alert is not dismissed successfully" , true);
			elementStatus = false;

		}

		return elementStatus;

	}
	
	
	/**
	 * 
	 * @Objective <b>Verifies the alert messages based on the regular expression pattern</b>
	 * @param getValueFromPOM
	 * @param strPattern
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebElementDynamicStringVerify(String getValueFromPOM, String strPattern, int strExecEventFlag) throws Exception
	{
		String RetrievedMessage=selectByLocatorType(getValueFromPOM).getText();
		boolean matchedStatus;
		boolean elementStatus= false;

		String ExpectedPattern=null;

		try{

			if(strExecEventFlag==1){
				ExpectedPattern=RetrieveTestDataValue("WebElementDynamicStringVerify",strPattern,strExecEventFlag);
			}

			if(ExpectedPattern==null){
				Report_Functions.ReportEventFailure(doc,  "WebElementDynamicStringVerify",  "Required details are not provided in test data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Errror log in reading file is "+e);
			throw e;
		}

		try{
			Pattern expPattern=Pattern.compile(ExpectedPattern);
			Matcher matchedPattern = expPattern.matcher(RetrievedMessage);
			matchedStatus=matchedPattern.find();
			if(matchedStatus)
			{	
				Report_Functions.ReportEventSuccess(doc, "1", "WebElementDynamicStringVerify", "Retrieved pattern from the webElement Matched Successfully with the message : '"+matchedPattern.group(0)+"'", 2);
				elementStatus= true;
			}else{
				Report_Functions.ReportEventFailure(doc,  "WebElementDynamicStringVerify",  "Error occured while matching the Expected pattern:'"+ExpectedPattern+"' Actual:'"+matchedPattern.group(0)+"'", false);
				elementStatus= false;
			}
		}catch(Exception e){
			log.info(e.getMessage());
			Report_Functions.ReportEventFailure(doc,  "WebElementDynamicStringVerify",  "Error occured while matching the Expected pattern-matchedPattern.find():"+e.getMessage(), false);
		}
		return elementStatus;

	}

	/**
	 * 
	 * @Objective <b>Verifies the SQL DB Update</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean SQLDBUpdate(String sqltablename, String strsqlcolumnname,String strsqlcolumnvalue,String strsqlcondition,int strExecEventFlag)throws Exception  {
		boolean SQLDBUpdate= false;
		String Table_name = null;
		String Column_name = null;
		String Column_Value = null;
		String SQL_condition = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBUpdate",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBUpdate",strsqlcolumnname,strExecEventFlag);
				Column_Value=RetrieveTestDataValue("SQLDBUpdate",strsqlcolumnvalue,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBUpdate",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || Column_Value==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBUpdate",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			String query=null;

			if(Column_Value.equalsIgnoreCase("NULL")){
				query = "update "+Table_name+" set "+Column_name+"=NULL where "+SQL_condition;
			}else{
				query = "update "+Table_name+" set "+Column_name+"="+Column_Value+" where "+SQL_condition;
			}

			//Query to Execute      
			//String query = "update "+Table_name+" set "+Column_name+"="+Column_Value+" where "+SQL_condition;

			stmt.execute(query);
			SQLDBUpdate=true;
			Report_Functions.ReportEventSuccess(doc, "1", "SQLDBUpdate", "The SQL Update Query : "+ query + " executed successfully.", 2);

		} catch (Exception e) {
			SQLDBUpdate=false;
			Report_Functions.ReportEventFailure(doc,  "SQLDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBUpdate Error : " + e);
		}
		return SQLDBUpdate;
	}

	/**
	 * 
	 * @Objective <b>This method is send the values to new line(enter button)</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebButtonClickEnter(String getValueFromPOM, String strTestObject) throws Exception {
		boolean WebButtonClickEnter= false;
		try {
			selectByLocatorType(getValueFromPOM).sendKeys("\n");
			Thread.sleep(2000);
			Report_Functions.ReportEventSuccess(doc, "1", "WebButtonClickEnter","'The button "+strTestObject+"' is clicked successfully", 3);
			WebButtonClickEnter=true;
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebButtonClickEnter","'The button "+strTestObject+"' is not clicked successfully", true); 	
			log.info("No Element Found to click :" + e);
			WebButtonClickEnter=false;
		}
		return WebButtonClickEnter;
	}

	/**
	 * 
	 * @Objective <b>This method is to wait until expected text is available in webpage(enter button)</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean Waituntilexpectedtext(String getValueFromPOM, String strTestObject) throws Exception {
		boolean Waituntilexpectedtext= false;
		String Element_Text = null;
		try{
			FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver);
			fWait.withTimeout(300, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(selectByLocatorType(getValueFromPOM)));
			Element_Text = selectByLocatorType(getValueFromPOM).getText();
		}catch(Exception e){
			Report_Functions.ReportEventFailure(doc,"Waituntilexpectedtext","Error occured while getting the text from the element '"+strTestObject+"' .Error description is : "+ e.getMessage() +"." , true); 
			Waituntilexpectedtext=false;
		}
		if(!(Element_Text==null)){
			Report_Functions.ReportEventSuccess(doc,"1","Waituntilexpectedtext","The text '"+Element_Text+ "' is present in the element '"+strTestObject+"'",3);
			Waituntilexpectedtext= true;
		}else{
			Report_Functions.ReportEventFailure(doc,"Waituntilexpectedtext","No text is not present in the element '"+strTestObject+"'", true);
			Waituntilexpectedtext= false;
		}
		return Waituntilexpectedtext;
	}

	/**
	 * 
	 * @Objective <b>To hanlde the dynamic elements in webpage</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strPattern
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebElementDynamicStringVerify(String getValueFromPOM, String strTestObject, String strPattern,int strExecEventFlag) throws Exception{
		boolean WebElementDynamicStringVerify=false;
		boolean matchedStatus=false;
		Matcher matchedPattern = null;
		String Pattern_String = null;
		String RetrievedMessage=null;
		try{
			if(strExecEventFlag==1){
				Pattern_String=RetrieveTestDataValue("WebElementDynamicStringVerify",strPattern,strExecEventFlag);
			}

			if(Pattern_String==null){
				Report_Functions.ReportEventFailure(doc,  "WebElementDynamicStringVerify",  "Required details are not provided in test data sheet.", false);
				return false;
			}
			Pattern_String = Pattern_String.trim();
			RetrievedMessage=selectByLocatorType(getValueFromPOM).getText().trim();
			Pattern expPattern=Pattern.compile(Pattern_String);
			matchedPattern = expPattern.matcher(RetrievedMessage);
			matchedStatus=matchedPattern.find();
		}catch(Exception e){
			Report_Functions.ReportEventFailure(doc,  "WebElementDynamicStringVerify",  "Error occured while matching the Expected pattern-matchedPattern.find():"+e.getMessage(), true);
			WebElementDynamicStringVerify=false;
		}

		try{
			if(matchedStatus==true){
				log.info("Matched status pass:"+matchedStatus);
				Report_Functions.ReportEventSuccess(doc, "1", "WebElementDynamicStringVerify", "The Expected pattern '"+Pattern_String+"' in the webElement '"+strTestObject+"' matches with the actual pattern: '"+RetrievedMessage+"' successfully", 2);
				//Report_Functions.ReportEventSuccess(doc, "1", "WebElementDynamicStringVerify", "Retrieved pattern from the webElement '"+strTestObject+"'. Matched Successfully'"+matchedPattern.group()+"'", 2);
				WebElementDynamicStringVerify=true;
			}else if(matchedStatus==false){
				log.info("Matched status fail:"+matchedStatus);
				Report_Functions.ReportEventFailure(doc,  "WebElementDynamicStringVerify",  "The Expected pattern '"+Pattern_String+"' in the webElement '"+strTestObject+"' does not match with the actual pattern: '"+RetrievedMessage+"'", true);
				WebElementDynamicStringVerify=false;
			}
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "WebElementDynamicStringVerify",  "Error occured while finding the Pattern in the function 'WebElementDynamicStringVerify'.Error description is : "+ e.getMessage() +".", true);
			log.info("WebElementDynamicStringVerify Error : " + e);
			WebElementDynamicStringVerify=false;
		}
		return WebElementDynamicStringVerify;
	}

	/**
	 * 
	 * @Objective <b>To retrieve the values in webpage</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strSplitString
	 * @param NumberOfCharacters
	 * @param EnvVariableName
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebElementRetrieveValuesEnvVar(String getValueFromPOM, String strTestObject,String strSplitString,int NumberOfCharacters,String EnvVariableName,int strExecEventFlag) throws Exception{
		boolean WebElementRetrieveValuesEnvVar=false;
		String SplitString = null;
		String ContentToStore = null;
		try{
			if(strExecEventFlag==1){
				SplitString = RetrieveTestDataValue("WebElementRetrieveValuesEnvVar",strSplitString,strExecEventFlag);
			}
			if(SplitString==null){
				Report_Functions.ReportEventFailure(doc,  "WebElementRetrieveValuesEnvVar",  "Required details are not provided in test data sheet.", false);
				return false;
			}
			String RetrievedMessage=selectByLocatorType(getValueFromPOM).getText().trim();
			String[] split1Content=RetrievedMessage.split(SplitString);
			ContentToStore=split1Content[1].substring(0,NumberOfCharacters);

			// Storing the value in the Environment Variable
			Runtimevalue.setProperty(EnvVariableName, ContentToStore);

		}catch(Exception e){
			Report_Functions.ReportEventFailure(doc,  "WebElementRetrieveValuesEnvVar",  "Error in retrieving the value from webelement  :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			WebElementRetrieveValuesEnvVar=false;
		}

		if(!(ContentToStore==null)){
			Report_Functions.ReportEventSuccess(doc, "1", "WebElementRetrieveValuesEnvVar", "The Value:'" +ContentToStore+ "' is stored in the Environment Variable: '"+ EnvVariableName +"' successfully", 2);
			WebElementRetrieveValuesEnvVar=true;
		}else{
			Report_Functions.ReportEventFailure(doc,  "WebElementRetrieveValuesEnvVar",  "No Value is stored in Environment Variable: '"+ EnvVariableName +"'", true);
			WebElementRetrieveValuesEnvVar=false;
		}
		return WebElementRetrieveValuesEnvVar;
	}

	/**
	 * 
	 * @Objective <b>Verify whether to check web element is disabled</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebEditDisabled(String getValueFromPOM, String strTestObject)throws Exception  {
		boolean WebEditDisabled= false;
		boolean Enable_Flag = true;
		try {
			Enable_Flag = selectByLocatorType(getValueFromPOM).isEnabled();

			if(Enable_Flag==false){
				Report_Functions.ReportEventSuccess(doc, "1", "WebEditDisabled", "The text field:'" +strTestObject+ "' is disabled as expected", 2);
				WebEditDisabled=true;
			}else{
				Report_Functions.ReportEventFailure(doc,  "WebEditDisabled",  "The text field:'" +strTestObject+ "' is not disabled as expected", true);
				WebEditDisabled=false;
			}
		}catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "WebEditDisabled",  "Error occured while getting the 'disabled' property of the input field :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			WebEditDisabled=false;
		}
		return WebEditDisabled;
	}

	/**
	 * 
	 * @Objective <b>To compare the values both excel sheet and web element</b>
	 * @param element
	 * @param strTestObject
	 * @param strexpectedValue
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebEditTextCompare(WebElement element,String strTestObject,String strexpectedValue,int strExecEventFlag) throws Exception  {
		boolean WebEditTextCompare=false;
		String ActualValue=null;
		String ExpectedValue=null;
		try {
			if(strExecEventFlag==1){
				ExpectedValue = RetrieveTestDataValue("WebEditTextCompare",strexpectedValue,strExecEventFlag);
			}
			if(ExpectedValue==null){
				Report_Functions.ReportEventFailure(doc,  "WebEditTextCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			ActualValue = element.getAttribute("value");
		}catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebEditTextCompare",  "Error occured while getting the text from the input field :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			WebEditTextCompare=false;
		}

		try{
			if((ActualValue.trim()).equals(ExpectedValue.trim())){
				Report_Functions.ReportEventSuccess(doc,"1", "WebEditTextCompare", "The  Actual Value '" +ActualValue+ "' matches with the Expected value '"+ExpectedValue+ "' in the input field '"+strTestObject+"", 2);
				WebEditTextCompare=true;
			}else{
				Report_Functions.ReportEventFailure(doc,"WebEditTextCompare", "The  Actual Value '" +ActualValue+ "' does not match with the Expected value '"+ExpectedValue+ "' in the input field '"+strTestObject+"", true);
				WebEditTextCompare=false;
			}
		}catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebEditTextCompare",  "Error occured while comparing actual and expected values. Error description is :"+e.getMessage(), true);
			WebEditTextCompare=false;
		}
		return WebEditTextCompare;
	}

	/**
	 * 
	 * @Objective <b>To compare the texts both excel sheet and web element</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebElementTextCompare(String getValueFromPOM, String strTestObject,String strColumnName, int strExecEventFlag )throws Exception  {
		String actualResult=null;
		String strData=null;
		boolean WebElementTextCompare=false;

		try{
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebElementTextCompare",strColumnName,strExecEventFlag);
			}
			actualResult = selectByLocatorType(getValueFromPOM).getText();
		}catch (StaleElementReferenceException e){
			//Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			log.info("Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage());
			return WebElementTextCompare(getValueFromPOM, strTestObject, strColumnName, strExecEventFlag );
			
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			WebElementTextCompare=false;
		}

		try{
			if((actualResult.trim()).equalsIgnoreCase(strData.trim())){

				Report_Functions.ReportEventSuccess(doc,"1", "WebElementTextCompare", "The  Actual Value '" +actualResult+ "' matches with the Expected value '"+strData+ "' in the input field '"+strTestObject+"'", 2);
				WebElementTextCompare=true;
			}else{
				Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "The  Actual Value '" +actualResult+ "' does not match with the Expected value '"+strData+ "' in the input field '"+strTestObject+"'", true);
				WebElementTextCompare=false;
			}
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while comparing actual and expected values. Error description is :"+e.getMessage(), true);
			WebElementTextCompare=false;
		}
		return WebElementTextCompare;
	}

	public static boolean getTicketIDDynamicValues(String getValueFromPOM, String strTestObject,String strColumnName, int strExecEventFlag )throws Exception  {

		String actualResult = null;
		String expectedText = null;
		String dynamicValue = null;
		boolean getTicketIDDynamicValues = false;

		try{
			if(strExecEventFlag == 1){
				expectedText = RetrieveTestDataValue("getTicketIDDynamicValues", strColumnName, strExecEventFlag);
			}
			actualResult = selectByLocatorType(getValueFromPOM).getText();

			Pattern pattern = Pattern.compile(expectedText);
			Matcher matcher = pattern.matcher(actualResult);

			while(matcher.find()){

				dynamicValue = matcher.group(1)+matcher.group(2)+matcher.group(3);

				log.info("dynamicValue :"+dynamicValue);

			}


		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			getTicketIDDynamicValues = false;
		}

		return getTicketIDDynamicValues;
	}

	/**
	 * 
	 * @Objective <b>To compare the environment varibale text both excel sheet and web element</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strEnvironmentVariable
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebElementTextCompareEnvVar(String getValueFromPOM, String strTestObject,String strEnvironmentVariable)throws Exception{
		boolean WebElementTextCompareEnvVar=false;
		String actualResult=null;
		String strData=null;
		try{
			strData = Runtimevalue.getProperty(strEnvironmentVariable);

			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebElementTextCompareEnvVar",  "Required details are not provided.", false);
				return false;
			}

			actualResult = selectByLocatorType(getValueFromPOM).getText();
		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompareEnvVar", "Error has occured. Error description is :"+e.getMessage(), true);
			WebElementTextCompareEnvVar=false;
		}

		try{
			if((actualResult.trim()).equals(strData.trim())){
				Report_Functions.ReportEventSuccess(doc,"1", "WebElementTextCompareEnvVar", "The  Actual Value '" +actualResult+ "' matches with the Expected value '"+strData+ "' in the input field '"+strTestObject+"", 2);
				WebElementTextCompareEnvVar=true;
			}else{
				Report_Functions.ReportEventFailure(doc,"WebElementTextCompareEnvVar", "The  Actual Value '" +actualResult+ "' does not match with the Expected value '"+strData+ "' in the input field '"+strTestObject+"", true);
				WebElementTextCompareEnvVar=false;
			}
		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompareEnvVar", "Error has occured while comparing actual and expected values. Error description is :"+e.getMessage(), true);
			WebElementTextCompareEnvVar=false;
		}
		return WebElementTextCompareEnvVar;
	}

	/**
	 * 
	 * @Objective <b>Verifies the SQL DB select</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean SQLDBSelect(String sqltablename, String strsqlcolumnname,String strsqlcondition,String strExpectedvalue,int strExecEventFlag)throws Exception  {
		boolean SQLDBSelect= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		//String Expected_value = null;		
		String Expected_value = "";
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBSelect",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBSelect",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBSelect",strsqlcondition,strExecEventFlag);
				Expected_value=RetrieveTestDataValue("SQLDBSelect",strExpectedvalue,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			rs_SQLServer = stmt.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelect Error : " + e);
			SQLDBSelect=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1).trim();

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "No Record found for this query: "+query, true);
			log.info("SQLDBSelect Error : ");
			SQLDBSelect=false;
		}

		//Below code is to validate current date in the db
		//SampleInput format - CURRENT_DATE#yyy-MM-dd
		try{
		if (Expected_value.contains("CURRENT_DATE")){
			
			String []ExpectedvalueWithFormat=Expected_value.split("#");
			
			String db_Date = Actual_Value.split(" ")[0];
			Actual_Value=db_Date;
			
			Date date=new Date();			
			SimpleDateFormat dateformat=new SimpleDateFormat(ExpectedvalueWithFormat[1]);
			Expected_value=dateformat.format(date);
			
		}
		}	catch(Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "No Record found for this query: "+query+".Exception occured - "+e.getMessage(), true);
			log.info("SQLDBSelect Error : ");
			SQLDBSelect=false;

		
		}
		
		try{
			if(!rs_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","SQLDBSelect", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches the expected value : '"+Expected_value+"'", 2);
					SQLDBSelect=true;
				}else if(!(Actual_Value.equalsIgnoreCase(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);
					SQLDBSelect=false;
				}
			}

			else if(rs_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equalsIgnoreCase("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBSelect", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					SQLDBSelect=true;
				}else if(!(Expected_value.equalsIgnoreCase("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					SQLDBSelect=false;
				}
			}
		}catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelect Error : " + e);
			SQLDBSelect=false;
		}
		return SQLDBSelect;
	}

	/**
	 * 
	 * @Objective <b>Verifies to get the value from DB and store in RunTime environment</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>12-Jul-16</b>
	 */

	public static boolean storeSQLDBValueInEnv(String sqltablename, String strsqlcolumnname, String strsqlcondition, int strExecEventFlag)throws Exception  {

		boolean storeSQLDBValueInEnv= false;

		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String getIDValueFromRecord = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("StoreSQLDBValueInEnv",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("StoreSQLDBValueInEnv",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("StoreSQLDBValueInEnv",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "StoreSQLDBValueInEnv",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";

			rs_SQLServer = stmt.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "StoreSQLDBValueInEnv",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelect Error : " + e);
			storeSQLDBValueInEnv = false;
		}

		try{
			rs_SQLServer.next();

			getIDValueFromRecord = rs_SQLServer.getString(1).trim();

			//Store the value in RunTime environment
			Runtimevalue.setProperty("getIDValueForPendingApprovals", getIDValueFromRecord);
			//			log.info("Runtime value *************: "+Runtimevalue.getProperty("getIDValueForPendingApprovals"));

			Report_Functions.ReportEventSuccess(doc, "1", "StoreSQLDBValueInEnv", "Selected DB value is stored in RunTime Environment successfully", 2);
			storeSQLDBValueInEnv = true;

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "StoreSQLDBValueInEnv",  "No Record found for this query: "+query, true);
			e.printStackTrace();
			log.info("SQLDBSelect Error : ");
			storeSQLDBValueInEnv = false;
		}

		return storeSQLDBValueInEnv;
	}

		/**
	 * 
	 * @Objective <b>Verifies the EShopSQLDBSelect</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Muralimohan M</b>
	 * @since <b>20-May-15</b>
	 */

	public static boolean EShopSQLDBSelect(String sqltablename, String strsqlcolumnname,String strsqlcondition,String strExpectedvalue,int strExecEventFlag)throws Exception  {

		boolean Executionstatus = false;

		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Expected_value = "";
		String Actual_Value = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("EShopSQLDBSelect",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("EShopSQLDBSelect",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("EShopSQLDBSelect",strsqlcondition,strExecEventFlag);
				Expected_value=RetrieveTestDataValue("EShopSQLDBSelect",strExpectedvalue,strExecEventFlag);

			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "EShopSQLDBSelect",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+" order by 1 desc";
			Eshop_SQLServer = EShopstmt.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "EShopSQLDBSelect",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("EShopSQLDBSelect Error : " + e.getMessage());
			Executionstatus=false;
		}

		try{

			Eshop_SQLServer.next();
			Actual_Value = Eshop_SQLServer.getString(1).trim();
			//Added by Murali - to compare against system IP from which the script are running vs dbvalue
			if (Expected_value.equalsIgnoreCase("GET_HOST_IP")){
				InetAddress IP=InetAddress.getLocalHost();
				Expected_value=	IP.getHostAddress();
			}

		}catch(NullPointerException e){

			log.info("Null value occurs " +e.getMessage());

		}

		catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "EShopSQLDBSelect",  "No Record found for this query: "+query, true);
			log.info("EShopSQLDBSelect Error : "+e.getMessage());
			Executionstatus=false;
		}

		try{
			if(!Eshop_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","EShopSQLDBSelect", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches the expected value : '"+Expected_value+"'", 2);
					Executionstatus=true;
				}else if(!(Actual_Value.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "EShopSQLDBSelect",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);
					Executionstatus=false;
				}
			}else if(Eshop_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "EShopSQLDBSelect", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					Executionstatus=true;
				}else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "EShopSQLDBSelect",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					Executionstatus=false;
				}
			}
		} catch(NullPointerException e){

			log.info("Null value occurs " +e.getMessage());

		}

		catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "EShopSQLDBSelect",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("EShopSQLDBSelect Error : "+e.getMessage());
			Executionstatus=false;
		}
		return Executionstatus;
	}


	/**
	 * 
	 * @Objective <b>Verifies to check value is exist in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean SQLDBCheckValueExist(String sqltablename, String strsqlcolumnname,String strsqlcondition,int strExecEventFlag)throws Exception  {
		boolean SQLDBCheckValueExist= false;
		String query=null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBCheckValueExist",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBCheckValueExist",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBCheckValueExist",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBCheckValueExist",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			rs_SQLServer= stmt.executeQuery(query);

		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBCheckValueExist",  "Error occured while executing the Eshop SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBCheckValueExist Error : " + e);
			SQLDBCheckValueExist=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1);
		} catch (Exception NullPointerException) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBCheckValueExist",  "No Record found for this query: "+query, true);
			log.info("SQLDBCheckValueExist Error : ");
			SQLDBCheckValueExist=false;
		}

		try{
			if(!rs_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(!(Actual_Value==null)){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBCheckValueExist", "The Value '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' exists. ", 2);
					SQLDBCheckValueExist=true;
				}else if(Actual_Value==null){
					Report_Functions.ReportEventFailure(doc,  "SQLDBCheckValueExist",  "The Value in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not exists. ", true);
					SQLDBCheckValueExist=false;
				}
			}else if(rs_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				Report_Functions.ReportEventFailure(doc,  "SQLDBCheckValueExist",  "The value 'NULL'  is present in the column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"'", true);  	 
				SQLDBCheckValueExist=false;
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBCheckValueExist",  "Error occured while checking whether the executed EShop query is having any record (or) not. Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBCheckValueExist Error : " + e);
			SQLDBCheckValueExist=false;
		}
		return SQLDBCheckValueExist;
	}

	/**
	 * 
	 * @Objective <b>Verifies to check value is exist in RRBS DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>12-Jul-16</b>
	 */

	public static boolean RRBSDBCheckValueExist(String sqltablename, String strsqlcolumnname,String strsqlcondition,int strExecEventFlag)throws Exception  {

		boolean RRBSDBCheckValueExist = false;
		String query=null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("RRBSDBCheckValueExist",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("RRBSDBCheckValueExist",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("RRBSDBCheckValueExist",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBCheckValueExist",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			rrbsresultset= rrbsstatement.executeQuery(query);

		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "RRBSDBCheckValueExist",  "Error occured while executing the Eshop SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBCheckValueExist Error : " + e);
			RRBSDBCheckValueExist = false;
		}

		try{
			rrbsresultset.next();
			Actual_Value = rrbsresultset.getString(1);
		} catch (Exception NullPointerException) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "RRBSDBCheckValueExist",  "No Record found for this query: "+query, true);
			log.info("RRBSDBCheckValueExist Error : ");
			RRBSDBCheckValueExist = false;
		}

		try{
			if(!rrbsresultset.wasNull()){            // If some value is present in the fired Query
				if(!(Actual_Value==null)){
					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBCheckValueExist", "The Value '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' value is exists. ", 2);
					RRBSDBCheckValueExist = true;
				}else if(Actual_Value==null){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBCheckValueExist",  "The Value in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not exists. ", true);
					RRBSDBCheckValueExist = false;
				}
			}else if(rrbsresultset.wasNull()){        // If "NULL" value is present in the fired Query
				Report_Functions.ReportEventFailure(doc,  "RRBSDBCheckValueExist",  "The value 'NULL'  is present in the column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"'", true);  	 
				RRBSDBCheckValueExist = false;
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "RRBSDBCheckValueExist",  "Error occured while checking whether the executed EShop query is having any record (or) not. Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBCheckValueExist Error : " + e);
			RRBSDBCheckValueExist = false;
		}
		return RRBSDBCheckValueExist;
	}

	/**
	 * 
	 * @Objective <b>This method is to verifies the values are available in EShop SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>30-May-16</b>
	 */

	public static boolean eShopSQLDBCheckValueExist(String sqltablename, String strsqlcolumnname,String strsqlcondition,int strExecEventFlag)throws Exception  {

		boolean elementStatus = false;

		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Actual_Value = null;
		try {
			if(strExecEventFlag == 1){

				Table_name=RetrieveTestDataValue("eShopSQLDBCheckValueExist",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("eShopSQLDBCheckValueExist",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("eShopSQLDBCheckValueExist",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "eShopSQLDBCheckValueExist",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			Eshop_SQLServer = EShopstmt.executeQuery(query);

		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "eShopSQLDBCheckValueExist",  "Error occured while executing the Eshop SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBCheckValueExist Error : " + e);
			elementStatus = false;
		}

		try{
			Eshop_SQLServer.next();
			Actual_Value = Eshop_SQLServer.getString(1);
		} catch (Exception NullPointerException) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "eShopSQLDBCheckValueExist",  "No Record found for this query: "+query, true);
			log.info("EShopSQLDBCheckValueExist Error : ");
			elementStatus = false;
		}

		try{
			if(!Eshop_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(!(Actual_Value==null)){
					Report_Functions.ReportEventSuccess(doc, "1", "eShopSQLDBCheckValueExist", "The Value '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' exists. ", 2);
					elementStatus = true;
				}else if(Actual_Value==null){
					Report_Functions.ReportEventFailure(doc,  "eShopSQLDBCheckValueExist",  "The Value in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not exists. ", true);
					elementStatus = false;
				}
			}else if(Eshop_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				Report_Functions.ReportEventFailure(doc,  "eShopSQLDBCheckValueExist",  "The value 'NULL'  is present in the column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"'", true);  	 
				elementStatus = false;
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "eShopSQLDBCheckValueExist",  "Error occured while checking whether the executed EShop query is having any record (or) not. Error description is : "+ e.getMessage() +".", true);
			log.info("eShopSQLDBCheckValueExist Error : " + e);
			elementStatus = false;
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Verifies to check date comparision in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param Date_Format
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean SQLDBDateCompare(String sqltablename, String strsqlcolumnname,String strsqlcondition,String Date_Format,int strExecEventFlag)throws Exception  {
		boolean SQLDBDateCompare= false;
		String query = null;  
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Expected_value = null;
		String Actual_Value = null;
		String Current_Date=null;
		String expected_db_Date = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBDateCompare",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBDateCompare",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBDateCompare",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			Current_Date = dateFormat.format(date);
			Expected_value = Current_Date.trim();

			// Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			rs_SQLServer= stmt.executeQuery(query);

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBDateCompare Error : " + e);
			SQLDBDateCompare=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1);
			String db_Date = Actual_Value.split(" ")[0];

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date dateToChange = dateFormat.parse(db_Date);

			SimpleDateFormat finalDateFormat = new SimpleDateFormat(Date_Format);
			expected_db_Date = finalDateFormat.format(dateToChange);	

		} catch (Exception NullPointerException) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "No Record found for this query: "+query, true);
			log.info("SQLDBDateCompare Error : ");
			SQLDBDateCompare=false;
		}

		try{

			if(!rs_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(expected_db_Date.equals(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDateCompare", "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' matches with the expected date : '"+Expected_value+"' ", 2);
					SQLDBDateCompare=true;
				}else if(!(expected_db_Date.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' does not match with the expected date : '"+Expected_value+"' ", true);
					SQLDBDateCompare=false;
				}
			}else if(rs_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDateCompare", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					SQLDBDateCompare=true;
				}else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					SQLDBDateCompare=false;
				}
			}

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Error occured while comparing the dates in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBDateCompare Error : " + e);
			SQLDBDateCompare=false;
		}
		return SQLDBDateCompare;
	}

	/**
	 * 
	 * @Objective <b>Check element to click on main menu in webpage</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean Web_MainMenu_Click(String getValueFromPOM, String strTestObject) throws Exception {
		boolean Web_MainMenu_Click= false;
		try {
			((JavascriptExecutor)driver).executeScript("arguments[0].click()",selectByLocatorType(getValueFromPOM));
			Report_Functions.ReportEventSuccess(doc,"1","Web_MainMenu_Click","The main menu link '"+strTestObject+"' is clicked successfully",3);
			Web_MainMenu_Click=true;
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"Web_MainMenu_Click","The main menu link '"+strTestObject+"' is not clicked successfully", true); 
			Web_MainMenu_Click=false;
			log.info("No Main menu link Found to click : " + e);
		}
		return Web_MainMenu_Click;
	}

	/**
	 * 
	 * @Objective <b>Verifies to update the data for RRBS in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean RRBSDBUpdate(String sqltablename, String strsqlcolumnname,String strsqlcolumnvalue,String strsqlcondition,int strExecEventFlag)throws Exception  {

		boolean elementStatus= false;
		String Table_name = null;
		String Column_name = null;
		String Column_Value = null;
		String SQL_condition = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("RRBSDBUpdate",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("RRBSDBUpdate",strsqlcolumnname,strExecEventFlag);
				Column_Value=RetrieveTestDataValue("RRBSDBUpdate",strsqlcolumnvalue,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("RRBSDBUpdate",strsqlcondition,strExecEventFlag);
			}


			if(Table_name==null || Column_name==null || Column_Value==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBUpdate",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute   
			String query=null;

			if(Column_Value.contains("to_date")){
				log.info("Query has formula");
				query = "update "+Table_name+" set "+Column_name+"="+Column_Value+" where "+SQL_condition;
			}else{

				if(Column_Value.equalsIgnoreCase("null")){
					query = "update "+Table_name+" set "+Column_name+"=null where "+SQL_condition;
				}else{
					query = "update "+Table_name+" set "+Column_name+"='"+Column_Value+"' where "+SQL_condition;
				}
			}

			rrbsstatement.execute(query);
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBUpdate", "The RRBS Update Query : "+ query + " executed successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", false);
			log.info("RRBSDBUpdate Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Opening EXIBS DB Connection</b>
	 * @param dbserver
	 * @param portnumber
	 * @param dbname
	 * @param dbusername
	 * @param dbpassword
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean EXIBSDBOpenConnection(String dbserver, String portnumber, String dbname, String dbusername, String dbpassword)throws Exception  {
		boolean elementStatus= false;

		String serverName = Param.getProperty(dbserver);
		String portNumber = Param.getProperty(portnumber);
		String sid = Param.getProperty(dbname);


		//Connection URL Syntax: "jdbc:oracle:thin:@://ipaddress:portnumber:db_name"        
		String dbUrl = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;                  
		//Database Username     
		String username = Param.getProperty(dbusername);   
		//Database Password     
		String password = Param.getProperty(dbpassword); 

		if(serverName==null || portNumber==null || sid==null || username==null || password==null){
			Report_Functions.ReportEventFailure(doc,  "EXIBSDBOpenConnection",  "Required details are not provided in test data sheet.", false);
			return false;
		}
		try {
			// Load the JDBC driver
			String driverName = "oracle.jdbc.OracleDriver";
			Class.forName(driverName);         
			//Create Connection to DB       
			exibsconnection = DriverManager.getConnection(dbUrl, username, password);
			//Create Statement Object       
			exibsstatement = exibsconnection.createStatement(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "EXIBSDBOpenConnection", "EXIBS DB Connection is established Successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "EXIBSDBOpenConnection",  "Error occured while connecting to the EXIBS DB Server.Error description is : "+ e.getMessage() +".", false);
			log.info("EXIBSDBOpenConnection Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Deleting the record from RRBS DB</b>
	 * @param exibstablename
	 * @param exibscondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean EXIBSDBDelete(String exibstablename, String exibscondition, int strExecEventFlag)throws Exception  {
		boolean elementStatus= false;
		String tablename = null;
		String condition = null;

		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("RRBSDBDelete",exibstablename,strExecEventFlag);
				condition=RetrieveTestDataValue("RRBSDBDelete",exibscondition,strExecEventFlag);
			}

			if(tablename==null || condition==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBDelete",  "Required details are not provided in test data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Errror log in reading file is "+e);
			throw e;
		}
		//Query to Execute      
		String query = "Delete from "+ tablename +" where "+ condition;
		try {
			exibsstatement.execute(query); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "EXIBSDBDelete", "The EXIBS Query : "+ query + " executed successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "EXIBSDBDelete",  "Error occured while executing the EXIBS query.Error description is : "+ e.getMessage() +".", false);
			log.info("EXIBSDBDelete Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Verifying the column value in RRBS DB</b>
	 * @param exibstablename
	 * @param exibscolumnname
	 * @param exibscondition
	 * @param exibscolumnvalue
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean EXIBSDBSelect(String exibstablename, String exibscolumnname, String exibscondition, String exibscolumnvalue, int strExecEventFlag)throws Exception  {
		boolean elementStatus= false;
		String tablename = null;
		String condition = null;
		String columnname = null;
		String columnvalue = null;
		String actualvalue = null;
		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("EXIBSDBSelect",exibstablename,strExecEventFlag);
				condition=RetrieveTestDataValue("EXIBSDBSelect",exibscondition,strExecEventFlag);
				columnname=RetrieveTestDataValue("EXIBSDBSelect",exibscolumnname,strExecEventFlag);
				columnvalue=RetrieveTestDataValue("EXIBSDBSelect",exibscolumnvalue,strExecEventFlag);
			}

			if(tablename==null || condition==null || columnname==null){
				Report_Functions.ReportEventFailure(doc,  "EXIBSDBSelect",  "Required details are not provided in test data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Errror log in reading file is "+e);
			throw e;
		}
		//Query to Execute      
		String query = "select "+ columnname +" from "+ tablename +" where "+ condition;

		try {
			ResultSet exibsresultset = exibsstatement.executeQuery(query);
			while (exibsresultset.next()){
				actualvalue = exibsresultset.getString(1);
			}
			if(actualvalue.equalsIgnoreCase(columnvalue))
			{
				elementStatus=true;
				Report_Functions.ReportEventSuccess(doc, "1", "EXIBSDBSelect", "The actual value : "+ actualvalue + " matches with the expected value :"+ columnvalue +" under the column : '"+ columnname +"' of the table : '"+ tablename +"'.", 2);
			} else {
				elementStatus=false;
				Report_Functions.ReportEventFailure(doc,  "EXIBSDBSelect",  "The actual value : "+ actualvalue + " doesnot matches with the expected value :"+ columnvalue +" under the column : '"+ columnname +"' of the table : '"+ tablename +"'.", false);	
			}
		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "EXIBSDBSelect",  "Error occured while executing the EXIBS query.Error description is : "+ e.getMessage() +".", false);
			log.info("EXIBSDBSelect Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Verifies the update value in RRBS DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean EXIBSDBUpdate(String sqltablename, String strsqlcolumnname,String strsqlcolumnvalue,String strsqlcondition,int strExecEventFlag)throws Exception  {

		boolean elementStatus = false;
		String Table_name = null;
		String Column_name = null;
		String Column_Value = null;
		String SQL_condition = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("EXIBSDBUpdate",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("EXIBSDBUpdate",strsqlcolumnname,strExecEventFlag);
				Column_Value=RetrieveTestDataValue("EXIBSDBUpdate",strsqlcolumnvalue,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("EXIBSDBUpdate",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_Value==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "EXIBSDBUpdate",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			String query = "update "+Table_name+" set "+Column_name+"="+Column_Value+" where "+SQL_condition;
			exibsstatement.execute(query); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "EXIBSDBUpdate", "The EXIBS Update Query : "+ query + " executed successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "EXIBSDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("EXIBSDBUpdate Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Verifies closing RRBS DB connection</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean EXIBSDBCloseConnection()throws Exception  {
		boolean elementStatus= false;

		try {
			// closing DB Connection       
			exibsconnection.close(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "EXIBSDBCloseConnection", "The EXIBS DB Connection disconnected successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "EXIBSDBCloseConnection",  "Error occured while closing the EXIBS DB.Error description is : "+ e.getMessage() +".", false);
			log.info("EXIBSDBCloseConnection Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to change the values in web.config file</b>
	 * @param filePath
	 * @param attributeKey
	 * @param validInvalidKey
	 * @param attributeValue
	 * @param validInvalidValue
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>26-April-16</b>
	 */

	public static boolean XMLValueUpdate(String filePath, String key, String value, int strExecEventFlag) throws Exception{

		boolean elementStatus = false;

		String Path = null;
		String validKey = null;
		String validValue = null;
		String prevValue = null;
		String newValue = null;

		try{

			if(strExecEventFlag == 1){

				Path = Param.getProperty(RetrieveTestDataValue("XMLValueUpdate", filePath, strExecEventFlag));
				validKey = RetrieveTestDataValue("XMLValueUpdate", key, strExecEventFlag);
				validValue = Param.getProperty(RetrieveTestDataValue("XMLValueUpdate", value, strExecEventFlag));

			}

			if(Path==null || validKey==null || validValue==null){
				Report_Functions.ReportEventFailure(doc,  "XMLValueUpdate",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			File input = new File("//\\" +Path);

			Document doc = builder.parse(input);
			XPath xpath = XPathFactory.newInstance().newXPath();

			//Xpath Expression
			String expression = "//*[@key='"+validKey+"']";

			//Using node and elements to get the all child attributes 
			NodeList nodeList = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);

			for(int i = 0; i<nodeList.getLength(); i++){

				Node nNode = nodeList.item(i);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){

					Element eElement = (Element) nNode;
					if(eElement.getAttribute("value") != null){

						prevValue = eElement.getAttribute("value");
						log.info("Current Value :"+prevValue);

						eElement.setAttribute("value", validValue);

						newValue = eElement.getAttribute("value");

						log.info("Updated Value :"+newValue);

					} else {

						log.info("Given attribute value is not available");

					}

				}

				// write the content into config file

				TransformerFactory transFormerFactory = TransformerFactory.newInstance();
				Transformer transFormer = transFormerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(input);
				transFormer.transform(source, result);
				elementStatus = true;

			}


		}catch(XPathExpressionException | ParserConfigurationException | SAXException | IOException e){

			e.printStackTrace();
			elementStatus = false;

		}catch(Exception e){

			e.printStackTrace();
			elementStatus = false;

		}

		if(elementStatus){
			Report_Functions.ReportEventSuccess(doc,"1","","XML value '"+newValue+"' is updated for key '"+validKey+"' successfully",3);
		}else{
			Report_Functions.ReportEventFailure(doc,"","XML value '"+newValue+"' is not updated for key '"+validKey+"'" , true);
		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to delete the files in log folder, after to verify the confrmation message in log</b>
	 * @param filePath
	 * @param startsWith
	 * @param endsWith
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>6-May-16</b>
	 * 
	 */

	public static boolean deleteLogFiles(String filePath, String startsWith, String endsWith, int strExecEventFlag) throws Exception{

		boolean functionStatus = true;

		String path = null;
		String startValue = null;
		String endValue = null;
		int flag = 0;

		try{

			if(strExecEventFlag == 1){

				path = Param.getProperty(RetrieveTestDataValue("DeletelogFiles", filePath, strExecEventFlag));
				startValue = RetrieveTestDataValue("DeletelogFiles", startsWith, strExecEventFlag);
				endValue = RetrieveTestDataValue("DeletelogFiles", endsWith, strExecEventFlag);

			}

			if(path==null || startValue==null || endValue==null){
				Report_Functions.ReportEventFailure(doc,  "DeletelogFiles",  "Required details are not provided in test data sheet.", false);
				return false;
			}


			File directory = new File("//\\" +path);


			for(File listOfFiles : directory.listFiles()){
				if(listOfFiles.getName().startsWith(startValue) && listOfFiles.getName().endsWith(endValue)){
					listOfFiles.delete();
					flag = 1;
				}
			}

		}catch(Exception e){
			log.info("Exception occurs in deleteFiles function "+e.getMessage());
			Report_Functions.ReportEventFailure(doc,"","Error occurred, while delete the file with expected text '"+startValue+"'" , true);
			functionStatus = false;
		}

		if(flag == 1){
			Report_Functions.ReportEventSuccess(doc,"1","DeletelogFiles","Files are deleted sucessfully with file name Starts like '"+startValue+"' and file type as '"+endValue+"'",3);
		} else {
			Report_Functions.ReportEventSuccess(doc,"1","DeletelogFiles","Files with type '"+endValue+"' are not present in the path : '"+ path +"'.",3);
		}

		return functionStatus;
	}

	/**
	 * @Objective Read the log file
	 * @param filePath
	 * @param startsWith
	 * @param endsWith
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>6-May-16</b>
	 */

	public static boolean readLogFiles(String filePath, String startsWith, String endsWith, String textName, int strExecEventFlag) throws Exception{

		boolean functionStatus = false;

		String path = null;
		String startValue = null;
		String endValue = null;
		String textValue = null;
		File[] listOfFile = null;
		String fileName = null;
		String actualText = null;
		Pattern pattern = null;

		try{

			if(strExecEventFlag == 1){

				path = Param.getProperty(RetrieveTestDataValue("ReadlogFiles", filePath, strExecEventFlag));
				startValue = RetrieveTestDataValue("ReadlogFiles", startsWith, strExecEventFlag);
				endValue = RetrieveTestDataValue("ReadlogFiles", endsWith, strExecEventFlag);
				textValue = RetrieveTestDataValue("ReadlogFiles", textName, strExecEventFlag);

			}

			if(path==null || startValue==null || endValue==null || textValue==null){
				Report_Functions.ReportEventFailure(doc,  "DeletelogFiles",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Get the filename by given startName and endName

			File directory = new File("//\\" +path);
			listOfFile = directory.listFiles();
			if(listOfFile.length != 0){
				for(int i = 0; i<listOfFile.length; i++){
					if(listOfFile[i].isFile()){
						fileName = listOfFile[i].getName();
						if(fileName.startsWith(startValue) && fileName.endsWith(endValue)){
							//Read the file and match the expected value

							File ff = new File("//\\" +path +fileName);
							String fileContent = IOUtils.toString(ff.toURI());
							actualText = "Text is not available in log file";
							pattern = Pattern.compile(textValue);
							Matcher matcher = pattern.matcher(fileContent);

							while(matcher.find()){
								actualText = matcher.group();
								log.info("Text is matched");
								Report_Functions.ReportEventSuccess(doc,"1","","Text '"+actualText+"' is matched with expected text "+textValue+" in the filename of '"+startValue+"'",3);
								return true;
							}
						}

					}

				}

			}else{

				log.info("File is not available in directory");
				Report_Functions.ReportEventFailure(doc,"","File is not available in directory" , true);
				return false;


			}

			log.info(actualText);
			functionStatus = false;
			Report_Functions.ReportEventFailure(doc,"","The expected text '" + textValue + "' doesn't exist in the filename of '"+startValue+"'" , true);


		}catch(Exception e){

			log.info("Exception occurs in read log file"+e.getMessage());
			functionStatus = false;
			Report_Functions.ReportEventFailure(doc,"","Error occurred, while match with expected text "+textValue+" in the filename of '"+startValue+"'" , true);

		}

		return functionStatus;

	}

	/**
	 * 
	 * @Objective <b>Function to delete or select the record from EXIBS DB based on the condition from environment variable</b>
	 * @param SwitchCase
	 * @param exibstablename
	 * @param exibscolumnname
	 * @param exibscondition
	 * @param exibscolumnvalue
	 * @param environmentvariable
	 * @param requiredcharacterslength
	 * @param environmentvariable to store
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>11-May-2016</b>
	 */

	public static boolean EXIBSDBConditionEnvVar(String SwitchCase, String exibstablename, String exibscolumnname, String exibscondition, String exibscolumnvalue, String strenvvar, int strLength, String strenvvartostore, int strExecEventFlag)throws Exception  {
		boolean EXIBSDBConditionEnvVar= false;
		String tablename = null;
		String condition = null;
		String columnname = null;
		String columnvalue = null;
		String actualvalue = null;
		String envvar = null;
		String strvalue = null;
		String reqCondition = null;
		String query = null;
		switch (SwitchCase){

		case "Select" :
			try{

				if(strExecEventFlag==1){
					tablename=RetrieveTestDataValue("EXIBSDBConditionEnvVar",exibstablename,strExecEventFlag);
					condition=RetrieveTestDataValue("EXIBSDBConditionEnvVar",exibscondition,strExecEventFlag);
					columnname=RetrieveTestDataValue("EXIBSDBConditionEnvVar",exibscolumnname,strExecEventFlag);
					columnvalue=RetrieveTestDataValue("EXIBSDBConditionEnvVar",exibscolumnvalue,strExecEventFlag);
				}

				if(tablename==null || condition==null || columnname==null ){
					Report_Functions.ReportEventFailure(doc,  "EXIBSDBConditionEnvVar",  "Required details are not provided in the data sheet.", false);
					return false;
				}

			}catch(Exception e){
				log.info("Errror log in reading file is "+e);
				throw e;
			}

			envvar = Runtimevalue.getProperty(strenvvar);
			if(envvar==null){
				Report_Functions.ReportEventFailure(doc,  "EXIBSDBConditionEnvVar",  "Value in the environment variable : '"+ strenvvar +"' is empty.", false);
				return false;
			}

			strvalue = envvar.substring(envvar.length() - strLength);

			try{
				Runtimevalue.setProperty(strenvvartostore, strvalue);
				EXIBSDBConditionEnvVar=true;
				Report_Functions.ReportEventSuccess(doc, "1", "EXIBSDBConditionEnvVar", "The value : "+ strvalue + " is stored in the environment variable :"+ strenvvartostore +".", 2);
			} catch (Exception e){
				EXIBSDBConditionEnvVar=false;
				Report_Functions.ReportEventFailure(doc,  "EXIBSDBConditionEnvVar",  "Error occured while storing the value in environment variable.Error description is : "+ e.getMessage() +".", false);	
			}

			reqCondition = condition + " = '"+ strvalue +"'";
			//Query to Execute      
			query = "select "+ columnname +" from "+ tablename +" where "+ reqCondition;

			try {
				exibsresultset = exibsstatement.executeQuery(query);
				while (exibsresultset.next()){
					actualvalue = exibsresultset.getString(1);
				}
				if(actualvalue.equalsIgnoreCase(columnvalue))
				{
					EXIBSDBConditionEnvVar=true;
					Report_Functions.ReportEventSuccess(doc, "1", "EXIBSDBConditionEnvVar", "The actual value : "+ actualvalue + " matches with the expected value :"+ columnvalue +"  under the column : '"+ columnname +"' of the table : '"+ tablename +"'.", 2);
				} else {
					EXIBSDBConditionEnvVar=false;
					Report_Functions.ReportEventFailure(doc,  "EXIBSDBConditionEnvVar",  "The actual value : "+ actualvalue + " doesnot matches with the expected value :"+ columnvalue +"  under the column : '"+ columnname +"' of the table : '"+ tablename +"'.", false);	
				}
			} catch (Exception e) { 
				EXIBSDBConditionEnvVar=false;
				Report_Functions.ReportEventFailure(doc,  "EXIBSDBConditionEnvVar",  "Error occured while executing the EXIBS query.Error description is : "+ e.getMessage() +".", false);
				log.info("EXIBSDBConditionEnvVar Error : " + e);
			}

			break;
		case "Delete" :
			try{

				if(strExecEventFlag==1){
					tablename=RetrieveTestDataValue("EXIBSDBConditionEnvVar",exibstablename,strExecEventFlag);
					condition=RetrieveTestDataValue("EXIBSDBConditionEnvVar",exibscondition,strExecEventFlag);
				}

				if(tablename==null || condition==null ){
					Report_Functions.ReportEventFailure(doc,  "EXIBSDBConditionEnvVar",  "Required details are not provided in the data sheet.", false);
					return false;
				}

			}catch(Exception e){
				log.info("Errror log in reading file is "+e);
				throw e;
			}

			envvar = Runtimevalue.getProperty(strenvvar);
			if(envvar==null){
				Report_Functions.ReportEventFailure(doc,  "EXIBSDBConditionEnvVar",  "Value in the environment variable : '"+ strenvvar +"' is empty.", false);
				return false;
			}

			strvalue = envvar.substring(envvar.length() - strLength);

			try{
				Runtimevalue.setProperty(strenvvartostore, strvalue);

			} catch (Exception e){
				EXIBSDBConditionEnvVar=false;
				Report_Functions.ReportEventFailure(doc,  "EXIBSDBConditionEnvVar",  "Error occured while storing the value in environment variable inside the function : '"+ EXIBSDBConditionEnvVar +"'.Error description is : "+ e.getMessage() +".", false);	
			}

			reqCondition = condition + " = '"+ strvalue +"'";
			//Query to Execute      
			query = "Delete from "+ tablename +" where "+ reqCondition;

			try {
				exibsstatement.executeQuery(query);
				EXIBSDBConditionEnvVar=true;
				Report_Functions.ReportEventSuccess(doc, "1", "EXIBSDBConditionEnvVar", "The value : '"+ strvalue + "' is successfully deleted from the table : '"+ tablename +"'.", 2);

			} catch (Exception e) { 
				EXIBSDBConditionEnvVar=false;
				Report_Functions.ReportEventFailure(doc,  "EXIBSDBConditionEnvVar",  "Error occured while executing the EXIBS query.Error description is : "+ e.getMessage() +".", false);
				log.info("EXIBSDBConditionEnvVar Error : " + e);
			}
			break;
		}
		return EXIBSDBConditionEnvVar;
	}

	/**
	 * 
	 * @Objective <b>Function to delete or select the record from SQL DB based on the condition from environment variable</b>
	 * @param SwitchCase
	 * @param sqltablename
	 * @param sqlcolumnname
	 * @param sqlcondition
	 * @param sqlcolumnvalue
	 * @param environmentvariable
	 * @param requiredcharacterslength
	 * @param environmentvariable to store
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>11-May-2016</b>
	 */

	public static boolean SQLDBConditionEnvVar(String SwitchCase, String sqltablename, String sqlcolumnname, String sqlcondition, String sqlcolumnvalue, String strenvvar, int strLength, String strenvvartostore, int strExecEventFlag)throws Exception  {
		boolean SQLDBConditionEnvVar= false;
		String tablename = null;
		String condition = null;
		String columnname = null;
		String columnvalue = null;
		String actualvalue = null;
		String envvar = null;
		String strvalue = null;
		String reqCondition = null;
		String query = null;
		switch (SwitchCase){

		case "Select" :
			try{

				if(strExecEventFlag==1){
					tablename=RetrieveTestDataValue("SQLDBConditionEnvVar",sqltablename,strExecEventFlag);
					condition=RetrieveTestDataValue("SQLDBConditionEnvVar",sqlcondition,strExecEventFlag);
					columnname=RetrieveTestDataValue("SQLDBConditionEnvVar",sqlcolumnname,strExecEventFlag);
					columnvalue=RetrieveTestDataValue("SQLDBConditionEnvVar",sqlcolumnvalue,strExecEventFlag);
				}

				if(tablename==null || condition==null || columnname==null ){
					Report_Functions.ReportEventFailure(doc,  "SQLDBConditionEnvVar",  "Required details are not provided in the data sheet.", false);
					return false;
				}

			}catch(Exception e){
				log.info("Errror log in reading file is "+e);
				throw e;
			}

			envvar = Runtimevalue.getProperty(strenvvar);
			if(envvar==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBConditionEnvVar",  "Value in the environment variable : '"+ strenvvar +"' is empty.", false);
				return false;
			}

			strvalue = envvar.substring(envvar.length() - strLength);

			try{
				Runtimevalue.setProperty(strenvvartostore, strvalue);
				SQLDBConditionEnvVar=true;
				Report_Functions.ReportEventSuccess(doc, "1", "SQLDBConditionEnvVar", "The value : "+ strvalue + " is stored in the environment variable :"+ strenvvartostore +".", 2);
			} catch (Exception e){
				SQLDBConditionEnvVar=false;
				Report_Functions.ReportEventFailure(doc,  "SQLDBConditionEnvVar",  "Error occured while storing the value in environment variable.Error description is : "+ e.getMessage() +".", false);	
			}

			reqCondition = condition + " = '"+ envvar +"'";
			//Query to Execute      
			query = "select "+ columnname +" from "+ tablename +" where "+ reqCondition;

			try {
				rs_SQLServer = stmt.executeQuery(query);
				while (rs_SQLServer.next()){
					actualvalue = rs_SQLServer.getString(1);
				}
				if(actualvalue.equalsIgnoreCase(columnvalue))
				{
					SQLDBConditionEnvVar=true;
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBConditionEnvVar", "The actual value : "+ actualvalue + " matches with the expected value :"+ columnvalue +".", 2);
				} else {
					SQLDBConditionEnvVar=false;
					Report_Functions.ReportEventFailure(doc,  "SQLDBConditionEnvVar",  "The actual value : "+ actualvalue + " doesnot matches with the expected value :"+ columnvalue +".", false);	
				}
			} catch (Exception e) { 
				SQLDBConditionEnvVar=false;
				Report_Functions.ReportEventFailure(doc,  "SQLDBConditionEnvVar",  "Error occured while executing the EXIBS query.Error description is : "+ e.getMessage() +".", false);
				log.info("SQLDBConditionEnvVar Error : " + e);
			}

			break;
		case "Delete" :
			try{

				if(strExecEventFlag==1){
					tablename=RetrieveTestDataValue("SQLDBConditionEnvVar",sqltablename,strExecEventFlag);
					condition=RetrieveTestDataValue("SQLDBConditionEnvVar",sqlcondition,strExecEventFlag);
				}

				if(tablename==null || condition==null ){
					Report_Functions.ReportEventFailure(doc,  "SQLDBConditionEnvVar",  "Required details are not provided in the data sheet.", false);
					return false;
				}

			}catch(Exception e){
				log.info("Errror log in reading file is "+e);
				throw e;
			}

			envvar = Runtimevalue.getProperty(strenvvar);
			if(envvar==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBConditionEnvVar",  "Value in the environment variable : '"+ strenvvar +"' is empty.", false);
				return false;
			}

			strvalue = envvar.substring(envvar.length() - strLength);

			try{
				Runtimevalue.setProperty(strenvvartostore, strvalue);

			} catch (Exception e){
				SQLDBConditionEnvVar=false;
				Report_Functions.ReportEventFailure(doc,  "SQLDBConditionEnvVar",  "Error occured while storing the value in environment variable inside the function : '"+ SQLDBConditionEnvVar +"'.Error description is : "+ e.getMessage() +".", false);	
			}

			reqCondition = condition + " = '"+ envvar +"'";

			String checkquery = "Select * from "+ tablename +" where "+ reqCondition;
			//Query to Execute      
			query = "Delete from "+ tablename +" where "+ reqCondition;

			try {
				rs_SQLServer = stmt.executeQuery(checkquery);

				int temp=0;	
				while(rs_SQLServer.next()){
					temp++;
				}

				if(temp >= 1){
					stmt.executeQuery(query);
					SQLDBConditionEnvVar=true;
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBConditionEnvVar", "The value : '"+ envvar + "' is successfully deleted from the table : '"+ tablename +"'.", 2);
				} else {
					SQLDBConditionEnvVar=true;
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBConditionEnvVar", "The value : '"+ envvar + "' is not present in the table : '"+ tablename +"' to delete.", 2);	
				}
			} catch (Exception e) { 
				SQLDBConditionEnvVar=false;
				Report_Functions.ReportEventFailure(doc,  "SQLDBConditionEnvVar",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", false);
				log.info("SQLDBConditionEnvVar Error : " + e);
			}
			break;
		}
		return SQLDBConditionEnvVar;
	}

	/**
	 * 
	 * @Objective <b>Function to delete or select the record from RRBS DB based on the condition from environment variable</b>
	 * @param SwitchCase
	 * @param rrbstablename
	 * @param rrbscolumnname
	 * @param rrbscondition
	 * @param rrbscolumnvalue
	 * @param environmentvariable
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>11-May-2016</b>
	 */

	public static boolean RRBSDBConditionEnvVar(String SwitchCase, String rrbstablename, String rrbscolumnname, String rrbscondition, String rrbscolumnvalue, String strenvvar, int strExecEventFlag)throws Exception  {
		boolean RRBSDBConditionEnvVar= false;
		String tablename = null;
		String condition = null;
		String columnname = null;
		String columnvalue = null;
		String actualvalue = null;
		String envvar = null;
		String reqCondition = null;
		String query = null;
		String query2 = null;
		switch (SwitchCase){

		case "Select" :
			try{

				if(strExecEventFlag==1){
					tablename=RetrieveTestDataValue("RRBSDBConditionEnvVar",rrbstablename,strExecEventFlag);
					condition=RetrieveTestDataValue("RRBSDBConditionEnvVar",rrbscondition,strExecEventFlag);
					columnname=RetrieveTestDataValue("RRBSDBConditionEnvVar",rrbscolumnname,strExecEventFlag);
					columnvalue=RetrieveTestDataValue("RRBSDBConditionEnvVar",rrbscolumnvalue,strExecEventFlag);
				}

				if(tablename==null || condition==null || columnname==null ){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBConditionEnvVar",  "Required details are not provided in the data sheet.", false);
					return false;
				}

			}catch(Exception e){
				log.info("Errror log in reading file is "+e);
				throw e;
			}

			envvar = Runtimevalue.getProperty(strenvvar);
			if(envvar==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBConditionEnvVar",  "Value in the environment variable : '"+ strenvvar +"' is empty.", false);
				return false;
			}

			reqCondition = condition + " = '"+ envvar +"'";
			//Query to Execute      
			query = "select "+ columnname +" from "+ tablename +" where "+ reqCondition;

			try {
				rrbsresultset = rrbsstatement.executeQuery(query);
				while (rrbsresultset.next()){
					actualvalue = rrbsresultset.getString(1);
				}
				if(actualvalue.equalsIgnoreCase(columnvalue))
				{
					RRBSDBConditionEnvVar=true;
					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBConditionEnvVar", "The actual value : '"+ actualvalue + "' in the column : '"+ columnname +"' of the table : '"+ tablename +"' with condition : '"+ reqCondition +"' matches with the expected value : '"+ columnvalue +"'.", 2);
				} else {
					RRBSDBConditionEnvVar=false;
					Report_Functions.ReportEventFailure(doc,  "RRBSDBConditionEnvVar",  "The actual value : '"+ actualvalue + "' in the column : '"+ columnname +"' of the table : '"+ tablename +"' with condition : '"+ reqCondition +"' doesnot matches with the expected value : '"+ columnvalue +"'.", false);	
				}
			} catch (Exception e) { 
				RRBSDBConditionEnvVar=false;
				Report_Functions.ReportEventFailure(doc,  "RRBSDBConditionEnvVar",  "Error occured while executing the EXIBS query.Error description is : "+ e.getMessage() +".", false);
				log.info("RRBSDBConditionEnvVar Error : " + e);
			}

			break;
		case "Delete" :
			try{

				if(strExecEventFlag==1){
					tablename=RetrieveTestDataValue("RRBSDBConditionEnvVar",rrbstablename,strExecEventFlag);
					condition=RetrieveTestDataValue("RRBSDBConditionEnvVar",rrbscondition,strExecEventFlag);
				}

				if(tablename==null || condition==null ){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBConditionEnvVar",  "Required details are not provided in the data sheet.", false);
					return false;
				}

			}catch(Exception e){
				log.info("Errror log in reading file is "+e);
				throw e;
			}

			envvar = Runtimevalue.getProperty(strenvvar);
			if(envvar==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBConditionEnvVar",  "Value in the environment variable : '"+ strenvvar +"' is empty.", false);
				return false;
			}

			reqCondition = condition + " = '"+ envvar +"'";
			//Query to Execute      
			query = "Delete from "+ tablename +" where "+ reqCondition;

			try {
				rs_SQLServer = rrbsstatement.executeQuery(query);
				RRBSDBConditionEnvVar=true;
				Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBConditionEnvVar", "The value : '"+ envvar + "' is successfully deleted from the table : '"+ tablename +"'.", 2);

			} catch (Exception e) { 
				RRBSDBConditionEnvVar=false;
				Report_Functions.ReportEventFailure(doc,  "RRBSDBConditionEnvVar",  "Error occured while executing the EXIBS query.Error description is : "+ e.getMessage() +".", false);
				log.info("RRBSDBConditionEnvVar Error : " + e);
			}
			break;

		case "Update" :
			try{

				if(strExecEventFlag==1){
					tablename=RetrieveTestDataValue("RRBSDBConditionEnvVar",rrbstablename,strExecEventFlag);
					condition=RetrieveTestDataValue("RRBSDBConditionEnvVar",rrbscondition,strExecEventFlag);
					columnname=RetrieveTestDataValue("RRBSDBConditionEnvVar",rrbscolumnname,strExecEventFlag);
					columnvalue=RetrieveTestDataValue("RRBSDBConditionEnvVar",rrbscolumnvalue,strExecEventFlag);
				}

				if(tablename==null || condition==null || columnname==null || columnvalue==null ){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBConditionEnvVar",  "Required details are not provided in the data sheet.", false);
					return false;
				}

			}catch(Exception e){
				log.info("Errror log in reading file is "+e);
				throw e;
			}

			envvar = Runtimevalue.getProperty(strenvvar);
			if(envvar==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBConditionEnvVar",  "Value in the environment variable : '"+ strenvvar +"' is empty.", false);
				return false;
			}

			reqCondition = condition + " = '"+ envvar +"'";
			//Query to Execute      
			query = "select "+ columnname +" from "+ tablename +" where "+ reqCondition;

			try {
				rrbsresultset = rrbsstatement.executeQuery(query);
				if (rrbsresultset.next()){
					query2 = "update "+ tablename +" set "+ columnname +" = "+ columnvalue +" where "+ reqCondition +"";
					rrbsstatement.executeQuery(query2);
					RRBSDBConditionEnvVar=true;
					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBConditionEnvVar", "The value : '"+ columnvalue + "' is successfully updated under the column : '"+ columnname +"'  for the record : '"+ envvar +"' in the table : '"+ tablename +"'.", 2);
				}else {
					RRBSDBConditionEnvVar=false;
					Report_Functions.ReportEventFailure(doc,  "RRBSDBConditionEnvVar",  "No records found to update.", false);	
				}
			} catch (Exception e) { 
				RRBSDBConditionEnvVar=false;
				Report_Functions.ReportEventFailure(doc,  "RRBSDBConditionEnvVar",  "Error occured while executing the EXIBS query.Error description is : "+ e.getMessage() +".", false);
				log.info("RRBSDBConditionEnvVar Error : " + e);
			}

			break;
		}
		return RRBSDBConditionEnvVar;
	}

	/**
	 * 
	 * @Objective <b>Verifying the column value in RRBS DB</b>
	 * @param rrbstablename
	 * @param rrbscolumnname
	 * @param rrbscondition
	 * @param environmentvariabletostore
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>11-May-2016</b>
	 */

	public static boolean RetrieveRRBSValueStoresInEnvVar(String rrbstablename, String rrbscolumnname, String rrbscondition, String strenvvar, int strExecEventFlag)throws Exception  {
		boolean RetrieveRRBSValueStoresInEnvVar = false;
		String tablename = null;
		String condition = null;
		String columnname = null;
		String actualvalue = null;
		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("RetrieveRRBSValueStoresInEnvVar",rrbstablename,strExecEventFlag);
				condition=RetrieveTestDataValue("RetrieveRRBSValueStoresInEnvVar",rrbscondition,strExecEventFlag);
				columnname=RetrieveTestDataValue("RetrieveRRBSValueStoresInEnvVar",rrbscolumnname,strExecEventFlag);
			}

			if(tablename==null || condition==null || columnname==null ){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Errror log in reading file is "+e);
			throw e;
		}
		//Query to Execute      
		String query = "select "+ columnname +" from "+ tablename +" where "+ condition;
		try {
			ResultSet rrbsresultset = rrbsstatement.executeQuery(query);
			while (rrbsresultset.next()){
				actualvalue = rrbsresultset.getString(1);
			}

			Runtimevalue.setProperty(strenvvar, actualvalue);

			RetrieveRRBSValueStoresInEnvVar = true;
			Report_Functions.ReportEventSuccess(doc, "1", "RetrieveRRBSValueStoresInEnvVar", "The value : '"+ actualvalue + "' is stored in the environment variable :"+ strenvvar +".", 2);

		} catch (Exception e) { 
			RetrieveRRBSValueStoresInEnvVar = false;
			Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Error occured while executing the RRBS query.Error description is : "+ e.getMessage() +".", false);
			log.info("RetrieveRRBSValueStoresInEnvVar Error : " + e);
		}
		return RetrieveRRBSValueStoresInEnvVar;
	}

	/**
	 * 
	 * @Objective <b>To compare the texts both Environment value and web element</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Muralimohan M</b>
	 * @since <b>09-May-16</b>
	 */

	public static boolean WebElementValueCompareFromEnv(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {
		String actualResult=null;
		String strData=null;
		boolean WebElementValueCompareFromEnv=false;
		try{
			if (strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebElementValueCompareFromEnv",strColumnName,strExecEventFlag);
				strData=Param.getProperty(strData);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			actualResult = selectByLocatorType(getValueFromPOM).getAttribute("value");

		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementValueCompareFromEnv", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			WebElementValueCompareFromEnv=false;
		}

		try{
			if((actualResult.trim()).equalsIgnoreCase(strData.trim())){
				Report_Functions.ReportEventSuccess(doc,"1", "WebElementValueCompareFromEnv", "The  Actual Value '" +actualResult+ "' matches with the Expected value '"+strData+ "' in the input field '"+strTestObject+"'", 2);
				WebElementValueCompareFromEnv=true;
			}else{
				Report_Functions.ReportEventFailure(doc,"WebElementValueCompareFromEnv", "The  Actual Value '" +actualResult+ "' does not match with the Expected value '"+strData+ "' in the input field '"+strTestObject+"'", true);
				WebElementValueCompareFromEnv=false;
			}
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementValueCompareFromEnv", "Error occured while comparing actual and expected values. Error description is :"+e.getMessage(), true);
			WebElementValueCompareFromEnv=false;
		}
		return WebElementValueCompareFromEnv;
	}

	/**
	 * 
	 * @Objective <b>This method is to select the values from the dropdown using data from Property file value</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Muralimohan M</b>
	 * @since <b>09-May-16</b>
	 */

	public static boolean WebListSelectFromEnv(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag)throws Exception  {

		String strData=null;
		boolean WebListSelectFromEnv=false;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebListSelectFromEnv",strColumnName,strExecEventFlag);
				strData=Param.getProperty(strData);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,"WebListSelectFromEnv","Required details are not provided in the data sheet." , true);
				return false;
			}

			new Select(selectByLocatorType(getValueFromPOM)).selectByVisibleText(strData);
			Report_Functions.ReportEventSuccess(doc,"1","","The Item '"+strData+"' is selected from the  '"+strTestObject+"' List box successfully" ,3);
			WebListSelectFromEnv=true;

		} catch (StaleElementReferenceException e) {
			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				return WebListSelectFromEnv(getValueFromPOM, strTestObject, strColumnName,strExecEventFlag);

			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","The Item '" +  strData + "' was not selected from the  '"+strTestObject+"' List box- "+e.getMessage() , true); 
			WebListSelectFromEnv=false;
			log.info("No Element Found to select text" + e);
		}
		return WebListSelectFromEnv;
	}

	/**
	 * 
	 * @Objective <b>This method is to closing the SQL DB<b>
	 * @return
	 * @author <b>Muralimohan</b>
	 * @since <b>09-May-16</b>
	 */

	public static boolean EshopSQLDBCloseConnection()throws Exception  {
		boolean elementStatus= false;

		try {
			// closing DB Connection       
			EshopConnection.close(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "SQLDBCloseConnection", "The SQL DB Connection disconnected successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "SQLDBCloseConnection",  "Error occured while closing the SQL DB.Error description is : "+ e.getMessage() +".", false);
			log.info("SQLDBCloseConnection Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * @Objective <b>This method is to deleting records from EShop SQL DB<b>
	 * @param sqltablename
	 * @param sqlcondition
	 * @param strExecEventFlag
	 * @author <b>Muralimohan M</b>
	 * @since <b>09-May-16</b>
	 */

	public static boolean EshopSQLDBDelete(String sqltablename, String sqlcondition, int strExecEventFlag)throws Exception  {
		boolean elementStatus= false;
		String tablename = null;
		String condition = null;

		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("EshopSQLDBDelete",sqltablename,strExecEventFlag);
				condition=RetrieveTestDataValue("EshopSQLDBDelete",sqlcondition,strExecEventFlag);
			}

			if(tablename==null || condition==null){
				Report_Functions.ReportEventFailure(doc,"EshopSQLDBDelete","Required details are not provided in the data sheet." , true);
				return false;
			}

		}catch(Exception e){
			log.info("Error log in reading file is "+e);
			throw e;
		}

		String check = "select * from "+tablename +" where "+condition;

		String query = "Delete from "+ tablename +" where "+ condition;

		ResultSet rs = null;

		try {			
			rs = EShopstmt.executeQuery(check);		

			int temp=0;	
			while(rs.next()){
				temp++;
			}

			if(temp >= 1){

				EShopstmt.execute(query);
				elementStatus=true;

				Report_Functions.ReportEventSuccess(doc, "1", "EshopSQLDBDelete", "The SQL Query : "+ query + " executed successfully.", 2);

			}//If rows not available FALSE will be returned so no delete
			else if(temp < 1){
				Report_Functions.ReportEventSuccess(doc, "1", "EshopSQLDBDelete", "The SQL Query : "+ query + " records are not availbale in DB", 2);
				elementStatus=true;
			}

		}catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "EshopSQLDBDelete",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", false);
			log.info("EshopSQLDBDelete Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Verifies the Eshop SQL DB Update</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Muralimohan M</b>
	 * @since <b>09-May-16</b>
	 */

	public static boolean EshopSQLDBUpdate(String sqltablename, String strsqlcolumnname,String strsqlcolumnvalue,String strsqlcondition,int strExecEventFlag)throws Exception  {
		boolean EshopSQLDBUpdate= false;
		String Table_name = null;
		String Column_name = null;
		String Column_Value = null;
		String SQL_condition = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("EshopSQLDBUpdate",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("EshopSQLDBUpdate",strsqlcolumnname,strExecEventFlag);
				Column_Value=RetrieveTestDataValue("EshopSQLDBUpdate",strsqlcolumnvalue,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("EshopSQLDBUpdate",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || Column_Value==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,"EshopSQLDBUpdate","Required details are not provided in the data sheet." , true);
				return false;
			}

			//Query to Execute      
			String query = "update "+Table_name+" set "+Column_name+"="+Column_Value+" where "+SQL_condition;
			EShopstmt.execute(query);
			EshopSQLDBUpdate=true;
			Report_Functions.ReportEventSuccess(doc, "1", "EshopSQLDBUpdate", "The SQL Update Query : "+ query + " executed successfully.", 2);

		} catch (Exception e) {
			EshopSQLDBUpdate=false;
			Report_Functions.ReportEventFailure(doc,  "EshopSQLDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("EshopSQLDBUpdate Error : " + e);
		}
		return EshopSQLDBUpdate;
	}

	/**
	 * 
	 * @Objective <b>Open Eshop SQL Connection</b>
	 * @author <b>Muralimohan M</b>
	 * @since <b>09-May-2016</b>
	 * */

	public static boolean EshopSQLDBOpenConnection()throws Exception  {
		boolean elementStatus= false;
		//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"        
		String dbUrl = "jdbc:sqlserver://"+ Param.getProperty("ESHOP_SQL_Server") +";DatabaseName=" + Param.getProperty("ESHOP_SQL_Server_DB_Name") +";";                  
		//Database Username     
		String username = Param.getProperty("ESHOP_SQL_Server_UID");   
		//Database Password     
		String password = Param.getProperty("ESHOP_SQL_Server_PWD"); 
		if(username==null || password==null){
			Report_Functions.ReportEventFailure(doc,"EshopSQLDBOpenConnection","Required details are not provided." , true);
			return false;
		}
		try {
			//Load mysql jdbc driver        
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");         
			//Create Connection to DB       
			EshopConnection = DriverManager.getConnection(dbUrl,username,password);
			//Create Statement Object       
			EShopstmt = EshopConnection.createStatement(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "EshopSQLDBOpenConnection", "Eshop SQL Connection is established Successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "EshopSQLDBOpenConnection",  "Error occured while connecting to the SQL Server.Error description is : "+ e.getMessage() +".", false);
			log.info("EshopSQLDBOpenConnection Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to verify the items count in dropdown.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Yogendra</b>
	 * @since <b>12-May-2016</b>
	 */

	public static boolean WebListCheckMoreItemsExist(String getValueFromPOM, String strTestObject) throws Exception {

		boolean WebListCheckMoreItemsExist=false;
		try {
			WebElement dropdown = selectByLocatorType(getValueFromPOM);
			Select select = new Select(dropdown);  
			int itemlistcount = select.getOptions().size();

			if(itemlistcount > 1){
				Report_Functions.ReportEventSuccess (doc,"1","WebListCheckMoreItemsExist","The number of items present in WebList  '"+strTestObject+"' is : '"+ itemlistcount +"' which is more than 1.",2);
				WebListCheckMoreItemsExist = true;
			}else{
				Report_Functions.ReportEventFailure(doc,"WebListCheckMoreItemsExist","The number of items present in WebList  '"+strTestObject+"' is : '"+ itemlistcount +"' which is less than or equal to 1." , true);
				WebListCheckMoreItemsExist = false;
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebListCheckMoreItemsExist","Error occured while getting the items from the dropdown. Error description is : '"+ e.getMessage() +"'." , true);
			log.info("Error occured in function WebListCheckMoreItemsExist" + e);
		}
		return WebListCheckMoreItemsExist;
	}

	/**
	 * 
	 * @Objective <b>Check for the webelement is not editable<b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Yogendra</b>
	 * @since <b>12-May-2016</b>
	 */

	public static boolean WebElementNotEditable(String getValueFromPOM, String strTestObject) throws Exception {

		String elementenable;
		boolean WebElementEnabled = false;
		try {
			elementenable=selectByLocatorType(getValueFromPOM).getAttribute("readonly");
			if(elementenable.equalsIgnoreCase("true")){
				WebElementEnabled = true;
				Report_Functions.ReportEventSuccess(doc, "1", "WebElementNotEditable", "The object '"+strTestObject+"' is not editable as expected.", 3);
			}else{
				WebElementEnabled=false;
				Report_Functions.ReportEventFailure(doc, "WebElementNotEditable", "The object '"+strTestObject+"' is editable.", true);
			}

		} catch (Exception e) { 	
			log.info("Exception while finding enabled or disabled" + e);
			WebElementEnabled=false;
		}
		return WebElementEnabled;
	}

	/**
	 * 
	 * @Objective <b>This method is to upload the files using AutoITX function using Java</b>
	 * @param filePath
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>09-Aug-16</b>
	 * 
	 */

	public static boolean fileUpload(String filePath, String strTestObject) throws Exception{

		boolean elementStatus = false;
		String getBrowserName = Param.getProperty("testBrowser");

		try{
			//Set the Path for DLL
			File file = new File("lib", "jacob-1.14.3-x86.dll");
			//Get the absolute path for DLL
			System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());
			//Get the Jacob DLL path from local
			File jacobDLLPath = new File(Param.getProperty("AutoIT_Jacob_DLL_Path"));
			String dLLAbsolutePath = jacobDLLPath.getAbsolutePath();
			//Get the Jacob DLL absolute path
			System.setProperty(LibraryLoader.JACOB_DLL_PATH, dLLAbsolutePath);
			LibraryLoader.loadJacobLibrary();
			File localPath = new File(filePath);
			//Get the upload file absolute path
			String absoluteFilepath = localPath.getAbsolutePath();
			AutoItX autoIT = new AutoItX();

			//Based on Browsers AutoIT tool will be executed
			if(getBrowserName.equalsIgnoreCase("IE")){

				autoIT.winActivate("Choose File to Upload");
				if(autoIT.winWaitActive("Choose File to Upload", "", 10)){
					if(autoIT.winExists("Choose File to Upload")){
						autoIT.send(absoluteFilepath);
						autoIT.send("{Enter}",false);	
						log.info("File has been uploaded successfully in IE browser");
						elementStatus = true;

					}
				}

				log.info("File has been uploaded successfully in IE browser");
				elementStatus = true;
			} 

			else if(getBrowserName.equalsIgnoreCase("Mozilla")){

				autoIT.winActivate("File Upload");
				if(autoIT.winWaitActive("File Upload", "", 10)){
					if(autoIT.winExists("File Upload")){
						autoIT.sleep(500);
						autoIT.send(absoluteFilepath);	                
						autoIT.send("{Enter}",false);
						log.info("File has been uploaded successfully in Firefox browser");
						elementStatus = true;
						

					}
				}

			}else if(getBrowserName.equalsIgnoreCase("Chrome")){

				autoIT.winActivate("Open");
				if(autoIT.winWaitActive("Open", "", 10)){
					if(autoIT.winExists("Open")){
						autoIT.sleep(500);
						autoIT.send(absoluteFilepath);	                
						autoIT.send("{Enter}",false);
						log.info("File has been uploaded successfully in Chrome browser");
						elementStatus = true;
					}		
				}

			}	
		}catch(Exception e){

			log.info("Exception occurred in FileUpload using AutoITX :"+e.getMessage());
			e.printStackTrace();
			elementStatus = false;

		}
		
		
		if(elementStatus){
			Report_Functions.ReportEventSuccess(doc, "1", "Uploadfiletestdata", "The file is uploaded successfully", 3);
		} else{
			Report_Functions.ReportEventFailure(doc, "Uploadfiletestdata",  "Error occured while uploading the file ", false);
		}
		

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to enter the value from Runtime Properties and click on Enter button</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>11-AUG-16</b>
	 */

	public static boolean enterEnvValueAndClickEnter(String getValueFromPOM, String envVariableColName, String strTestObject, int strExecEventFlag)throws Exception  {
		//enterEnvValueAndClickEnter(PendingApproval.pendingApproval_Page("requestID_Text"),"EnvVaraibleName_ID","RequestID",1);
		boolean enterValuesAndClickEnter = false;
		String envVariable=null;

		log.info("envVariableColName is : "+envVariableColName);

		String strData = null;
		try {
			if(strExecEventFlag == 1){
				envVariable = RetrieveTestDataValue("EnterValuesAndClickEnter", envVariableColName, strExecEventFlag);
				//return false;
			}

			log.info("envVariable is : "+envVariable);

			if(envVariable == null){
				Report_Functions.ReportEventFailure(doc, "EnterValuesAndClickEnter", "Error while retrieving data from Sheet", false);
				return false;
			}


			strData = Runtimevalue.getProperty(envVariable);

			log.info("strData is : "+strData);

			if(strData == null){
				Report_Functions.ReportEventFailure(doc, "EnterValuesAndClickEnter", "Environment Variable '" + strData + "' is Empty", false);
				return false;
			}


			WebElement element = selectByLocatorType(getValueFromPOM);

			if(element.isDisplayed()){

				selectByLocatorType(getValueFromPOM).sendKeys(Keys.chord(Keys.CONTROL, "a"));
				selectByLocatorType(getValueFromPOM).sendKeys(strData);
				selectByLocatorType(getValueFromPOM).sendKeys(Keys.ENTER);

				Report_Functions.ReportEventSuccess(doc, "1", "EnterValuesAndClickEnter", "Text '" +  strData + "' is entered in the Textbox -  '"+strTestObject+"' and clicked on 'ENTER' button successfully", 3);
				enterValuesAndClickEnter = true;

			}else{

				Report_Functions.ReportEventFailure(doc, "EnterValuesAndClickEnter", "Text '" + strData + "' was not entered in the Textbox - '"+strTestObject+"' and not clicked on 'ENTER' button", true);
				enterValuesAndClickEnter = false;

			}
		} catch (Exception e) { 	
			Report_Functions.ReportEventFailure(doc, "EnterValuesAndClickEnter", "Text '" + strData + "' was not entered in the Textbox - '"+strTestObject+"' and not clicked on 'ENTER' button", true);
			enterValuesAndClickEnter = false;
			log.info("No Element Found to enter text : " + e);
		}
		return enterValuesAndClickEnter;
	}

	/** 
	 * @Objective <b>This method is to enter the given value from datasheet into the TextBox and compares how many character
	 *  TextBox accepted with the value from datasheet</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Value to enter into the TextBox from the datasheet<>
	 * @param strExpectedValueColumn <b>Compares this value from Excel with the value in the Element<>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>11-AUG-16</b>
	 */

	public static boolean WebEditEnterValueAndCompareSame(String getValueFromPOM, String strTestObject,String strColumnName,String strExpectedValueColumn,int strExecEventFlag )throws Exception  {

		String actualResult=null;
		String strData=null;
		String strExpectedData=null;
		boolean WebEditEnterAndCompareValue=false;



		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebEditEnterText",strColumnName,strExecEventFlag);
				strExpectedData=RetrieveTestDataValue("WebEditEnterText",strExpectedValueColumn,strExecEventFlag);
			}

			if(strData==null || strExpectedData==null){
				Report_Functions.ReportEventFailure(doc,  "WebElementValueCompare",  "Required details are not provided in the dataSheet.", false);
				return false;
			}

			selectByLocatorType(getValueFromPOM).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			selectByLocatorType(getValueFromPOM).sendKeys(strData);
			Report_Functions.ReportEventSuccess(doc, "1", "WebEditEnterText", "Entering the Text '" +  strData + "' in the Textbox -  '"+strTestObject+"'", 3);
			//WebEditEnterAndCompareValue=true;	
		} catch (Exception e) { 	
			Report_Functions.ReportEventFailure(doc, "WebEditEnterText", "The Text '" + strData + "' was not entered in the Textbox - '"+strTestObject+"'", true);
			WebEditEnterAndCompareValue=false;
			log.info("No Element Found to enter text : " + e);
		}

		try {
			actualResult = selectByLocatorType(getValueFromPOM).getAttribute("value");

			if((actualResult.trim()).equalsIgnoreCase(strExpectedData.trim())){
				WebEditEnterAndCompareValue=true;
				Report_Functions.ReportEventSuccess(doc,"1", "", ""+strTestObject+"'s  accepted value '" + actualResult + "' matches the Expected value '" + strExpectedData + "'", 2);
			}else{
				WebEditEnterAndCompareValue=false;
				Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+"'s accepted value '" + actualResult + "' does not match the Expected Value '" + strExpectedData + "'", true);
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+"'s accepted value '" + actualResult + "' does not match the Expected Value '" + strExpectedData + "'", true);
			WebEditEnterAndCompareValue=false;
			log.info("No Element Found to compare Text : " + e);
		}
		return WebEditEnterAndCompareValue;
	}

	/**
	 * @Objective <b>This method is to read the given single Log file content and match with the expected content<b>
	 * @param filePath Path of the file
	 * @param fileNameValue filename with its extension value
	 * @param textName Content to match
	 * @author <b>Lakshman</b>
	 * @since <b>18-AUG-16</b>
	 */

	public static boolean readLogFile(String filePath, String startsWith, String endsWith,String fileNameValue, String textName, int strExecEventFlag) throws Exception{

		boolean functionStatus = false;
		String path = null;
		String startValue = null;
		String endValue = null;
		String textValue = null;
		File[] listOfFile = null;
		String fileName = null;
		String actualText = null;
		Pattern pattern = null;
		String fileNameFromExcel=null;

		try{
			if(strExecEventFlag == 1){
				path = Param.getProperty(RetrieveTestDataValue("ReadlogFiles", filePath, strExecEventFlag));
				startValue = RetrieveTestDataValue("ReadlogFiles", startsWith, strExecEventFlag);
				endValue = RetrieveTestDataValue("ReadlogFiles", endsWith, strExecEventFlag);
				textValue = RetrieveTestDataValue("ReadlogFiles", textName, strExecEventFlag);
				fileNameFromExcel= RetrieveTestDataValue("ReadlogFiles", fileNameValue, strExecEventFlag);
			}

			if(path==null || startValue==null || endValue==null || textValue==null){
				Report_Functions.ReportEventFailure(doc,  "DeletelogFiles",  "Required details are not provided in test data sheet.", false);
				return false;
			}
			log.info("FIle name to search is : "+fileNameFromExcel);
			//Get the filename by given startName and endName

			File directory = new File("//\\" +path);
			
			boolean fileexist=false;
			int fileAppeartime=0;

			while(fileAppeartime<30){
				Thread.sleep(1000);
				listOfFile = directory.listFiles();
				if(listOfFile.length != 0){
					log.info("Directory has files");
					fileexist=true;
					break;

				}else{
					log.info("No File is available in directory. looping again with 60 secs");
					//Report_Functions.ReportEventFailure(doc,"","No File is not available in directory" , false);
					//return false;
				}

				log.info("No File is available in directory with 60 secs");
				fileAppeartime++;

			}

			if(fileexist){
				log.info("Files avialble in the directiory");

			}else{
				log.info("No File is available in directory for 60secs");
				Report_Functions.ReportEventFailure(doc,"","No File is not available in directory" , false);
				return false;
			}
			
			boolean foundstatus=false;
			int time=0;
			while(time<30){
				Thread.sleep(1000);	
				listOfFile = directory.listFiles();
				for(int i = 0; i<listOfFile.length; i++){
					log.info("i is : "+time);
					if(listOfFile[i].isFile()){

						fileName = listOfFile[i].getName();
						log.info("Found a file and name is : "+fileName);
						if(fileName.equals(fileNameFromExcel)){
							log.info("FileName exact match : "+fileName);
							fileName=fileNameFromExcel;
							foundstatus=true;
							break;
						}
					}
				}
				if(foundstatus){
					log.info("FileName exact match is found. Braking the loop");
					break;
				}else{
					log.info("FileName not found in path. Continue the loop");
				}
				time++;
			}

			if(fileName == null){
				Report_Functions.ReportEventFailure(doc,"","'"+fileNameFromExcel+"' file is not available in the directory" , false);
				log.info("Search File is not available in directory");
				return false;
			}

			if(!(fileName.equals(fileNameFromExcel))){
				Report_Functions.ReportEventFailure(doc,"","'"+fileNameFromExcel+"' file is not available in the directory" , false);
				log.info("Search File is not available in directory");
				return false;
			}

			File ff = new File("//\\" +path +fileName);
			log.info(ff.getAbsolutePath()+" || "+ff.getName());
			String fileContent = IOUtils.toString(ff.toURI());
			actualText = "Text is not available in log file";
			pattern = Pattern.compile(textValue);
			Matcher matcher = pattern.matcher(fileContent);
			while(matcher.find()){
				actualText = matcher.group(1)+matcher.group(2)+matcher.group(3);
				log.info("Text is matched");
				Report_Functions.ReportEventSuccess(doc,"1","","Text '"+actualText+"' is matched with expected text in the filename of '"+startValue+"'",3);
				return true;
			}

			log.info(actualText);
			functionStatus = false;
			Report_Functions.ReportEventFailure(doc,"",""+actualText+" in the filename of '"+startValue+"'" , true);
		}catch(Exception e){

			log.info("Exception occurs in read log file"+e.getMessage());
			functionStatus = false;
			Report_Functions.ReportEventFailure(doc,"","Error occurred, while match with expected text in the filename of '"+startValue+"'" , true);
		}
		return functionStatus;
	}

	/**
	 * @Objective <b>This method is to delete the single log file from given folder</b>
	 * @param filePath
	 * @param startsWith
	 * @param endsWith
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>11-AUG-16</b>
	 * 
	 */

	public static boolean deleteLogFile(String filePath, String fileName, int strExecEventFlag) throws Exception{

		boolean functionStatus = false;
		String path = null;
		String fileNameValue = null;
		int flag = 0;

		try{
			if(strExecEventFlag == 1){
				path = Param.getProperty(RetrieveTestDataValue("DeletelogFiles", filePath, strExecEventFlag));
				fileNameValue = RetrieveTestDataValue("DeletelogFiles", fileName, strExecEventFlag);
			}

			if(path==null || fileNameValue==null){
				Report_Functions.ReportEventFailure(doc,  "DeletelogFiles",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			File directory = new File("//\\" +path);

			if(directory.listFiles().length == 0){
				log.info("Files are not available");
				Report_Functions.ReportEventSuccess(doc,"1","","Expected files are not available in directory",3);
				return true;
			}else {
				log.info("Files are available its going to delete the files");
			}

			for(File listOfFiles : directory.listFiles()){

				if(listOfFiles.getName().equals(fileNameValue)){

					log.info("File with given filename is available in the Path");
					listOfFiles.delete();
					log.info("file is deleted");

					flag = 1;
					functionStatus = true;
					break;

				}else if(!(listOfFiles.getName().equals(fileNameValue))){
					log.info("File with given filename is available in the Path");
					functionStatus = true;
				}
			}
			Thread.sleep(5000);

		}catch(Exception e){
			log.info("Exception occurs in deleteFiles function "+e.getMessage());
			Report_Functions.ReportEventFailure(doc,"","Error occurred while deleting the file with Name '"+fileNameValue+"'" , true);
			functionStatus = false;
		}

		if(flag == 1){
			Report_Functions.ReportEventSuccess(doc,"1","","File with expected filename '"+fileNameValue+"' deleted sucessfully.",3);
		}else{
			Report_Functions.ReportEventSuccess(doc,"1","","File with expected filename '"+fileNameValue+"' is not available in the directory.",3);
		}
		return functionStatus;
	}

	/**
	 * @Objective <b>Verifies to get the value from DB and store in RunTime environment</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @param envValColumnName Envirnment variable name from Excel sheet
	 * @author <b>Lakshman</b>
	 * @since <b>12-AUG-16</b>
	 */

	public static boolean storeSQLDBValueInEnv(String sqltablename, String strsqlcolumnname, String strsqlcondition,String envValColumnName, int strExecEventFlag)throws Exception  {

		boolean storeSQLDBValueInEnv= false;

		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String setParameterName=null;
		String getIDValueFromRecord = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("StoreSQLDBValueInEnv",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("StoreSQLDBValueInEnv",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("StoreSQLDBValueInEnv",strsqlcondition,strExecEventFlag);
				setParameterName=RetrieveTestDataValue("StoreSQLDBValueInEnv",envValColumnName,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null ||setParameterName==null){
				Report_Functions.ReportEventFailure(doc,  "StoreSQLDBValueInEnv",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";

			rs_SQLServer = stmt.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "StoreSQLDBValueInEnv",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelect Error : " + e);
			storeSQLDBValueInEnv = false;
		}

		try{
			rs_SQLServer.next();

			getIDValueFromRecord = rs_SQLServer.getString(1).trim();

			//Store the value in RunTime environment
			Runtimevalue.setProperty(setParameterName, getIDValueFromRecord);
			//			log.info("Runtime value *************: "+Runtimevalue.getProperty("getIDValueForPendingApprovals"));

			Report_Functions.ReportEventSuccess(doc, "1", "StoreSQLDBValueInEnv", "Dynamic Value '"+getIDValueFromRecord+"' from DB is stored successfully in the Runtime Variable '"+setParameterName+"'", 2);
			storeSQLDBValueInEnv = true;

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "StoreSQLDBValueInEnv",  "No Record found for this query: "+query, true);
			e.printStackTrace();
			log.info("SQLDBSelect Error : ");
			storeSQLDBValueInEnv = false;
		}

		return storeSQLDBValueInEnv;
	}

	/**
	 * @Objective <b>Verifies the SQL GBR DB select with Expected Value from ENV Variable</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>10-AUG-16</b>
	 */

	public static boolean SQLDBSelectFromEnv(String sqltablename, String strsqlcolumnname,String strsqlcondition,String strEnvVariableColumn,int strExecEventFlag)throws Exception  {
		boolean SQLDBSelect= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		//String Expected_value = null;		
		String Expected_value = null;
		String envVariable=null;
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBSelect",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBSelect",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBSelect",strsqlcondition,strExecEventFlag);
				envVariable=RetrieveTestDataValue("SQLDBSelect",strEnvVariableColumn,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			Expected_value=Runtimevalue.getProperty(envVariable);

			if(Expected_value==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Dynamic Variable '"+envVariable+"' has no value.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+" order by 1 desc";

			rs_SQLServer = stmt.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelect Error : " + e);
			SQLDBSelect=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1).trim();

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "No Record found for this query: "+query, true);
			log.info("SQLDBSelect Error : ");
			SQLDBSelect=false;
		}

		try{
			if(!rs_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","SQLDBSelect", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches the expected Dynamic value : '"+Expected_value+"'", 2);
					SQLDBSelect=true;
				}else if(!(Actual_Value.equalsIgnoreCase(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected Dynamic value : '"+Expected_value+"'", true);
					SQLDBSelect=false;
				}
			}

			else if(rs_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equalsIgnoreCase("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBSelect", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected Dynamic value :'"+Expected_value+"'", 2);
					SQLDBSelect=true;
				}else if(!(Expected_value.equalsIgnoreCase("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected Dynamic value : '"+Expected_value+"'", true);  	 
					SQLDBSelect=false;
				}
			}
		}catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelect Error : " + e);
			SQLDBSelect=false;
		}
		return SQLDBSelect;
	}

	/**
	 * @Objective <b>Verifies the SQL GBR DB select with Expected Value from ENV Variable</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>10-AUG-16</b>
	 */

	public static boolean EShopSQLDBSelectFromEnv(String sqltablename, String strsqlcolumnname,String strsqlcondition,String strEnvVariableColumn,int strExecEventFlag)throws Exception  {
		boolean SQLDBSelect= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		//String Expected_value = null;		
		String Expected_value = null;
		String envVariable=null;
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBSelect",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBSelect",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBSelect",strsqlcondition,strExecEventFlag);
				envVariable=RetrieveTestDataValue("SQLDBSelect",strEnvVariableColumn,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			Expected_value=Runtimevalue.getProperty(envVariable);

			if(Expected_value==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Dynamic Variable '"+envVariable+"' has no value.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+" order by 1 desc";

			Eshop_SQLServer = EShopstmt.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", false);
			log.info("SQLDBSelect Error : " + e);
			SQLDBSelect=false;
		}

		try{
			Eshop_SQLServer.next();
			Actual_Value = Eshop_SQLServer.getString(1).trim();

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "No Record found for this query: "+query, false);
			log.info("SQLDBSelect Error : ");
			SQLDBSelect=false;
		}

		try{
			if(!Eshop_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","SQLDBSelect", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches the expected Dynamic value : '"+Expected_value+"'", 2);
					SQLDBSelect=true;
				}else if(!(Actual_Value.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected Dynamic value : '"+Expected_value+"'", false);
					SQLDBSelect=false;
				}
			}

			else if(Eshop_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBSelect", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected Dynamic value :'"+Expected_value+"'", 2);
					SQLDBSelect=true;
				}else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected Dynamic value : '"+Expected_value+"'", false);  	 
					SQLDBSelect=false;
				}
			}
		}catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", false);
			log.info("SQLDBSelect Error : " + e);
			SQLDBSelect=false;
		}
		return SQLDBSelect;
	}

	public static boolean WebElementTextStoreDynamicValue(String getValueFromPOM, String strTestObject,String strColumnName, int strExecEventFlag )throws Exception  {
		String actualText="";
		String strData=null;
		boolean WebElementTextCompare=false;
try{
		try{
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebElementTextCompare",strColumnName,strExecEventFlag);
			}
			actualText = selectByLocatorType(getValueFromPOM).getText();
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			WebElementTextCompare=false;
		}
		
		if(actualText == null){
			actualText="";
			log.info("Value is NULL . Setting EMPTY to Runtime Property '"+strData+"'");
			Report_Functions.ReportEventSuccess(doc,"1", "WebElementTextStoreInEnv", "The Empty value '"+actualText+"' is stored in the '"+strTestObject+"' is stored in the Runtime variable '"+strData+"'", 2);
			return false;
		}

		try{
			Runtimevalue.setProperty(strData, actualText);
			log.info("Value set to Runtime Property '"+strData+"' is => '"+Runtimevalue.getProperty(strData)+"'");
			Report_Functions.ReportEventSuccess(doc,"1", "WebElementTextStoreInEnv", "The Dynamic value '"+actualText+"' of Element '"+strTestObject+"' is successfully stored in the Runtime variable '"+strData+"'", 2);
			WebElementTextCompare=true;
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while Stroing the Dynamic value of element '"+strTestObject+"' in the Runtime variable '"+strData+"'. Error description is :"+e.getMessage(), true);
			WebElementTextCompare=false;
		}
		
} catch (Exception e){
	Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
	WebElementTextCompare=false;
}
		return WebElementTextCompare;
	}

	/**
	 * 
	 * @Objective <b>This method is to do a click action and enter the values for all web element.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>17-AUG-16</b>
	 */

	public static boolean WebEditClickAndEnterText(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {

		boolean WebEditEnterText= false;
		String strData=null;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebEditEnterText",strColumnName,strExecEventFlag);
			}

			selectByLocatorType(getValueFromPOM).click();
			selectByLocatorType(getValueFromPOM).clear();
			selectByLocatorType(getValueFromPOM).sendKeys(strData);
			Report_Functions.ReportEventSuccess(doc, "1", "WebEditEnterText", "The Text '" +  strData + "' is entered in the Textbox -  '"+strTestObject+"'  successfully", 3);
			WebEditEnterText=true;	
		} catch (Exception e) { 	
			Report_Functions.ReportEventFailure(doc, "WebEditEnterText", "The Text '" + strData + "' was not entered in the Textbox - '"+strTestObject+"'", true);
			WebEditEnterText=false;
			log.info("No Element Found to enter text : " + e);
		}
		return WebEditEnterText;
	}

	/**
	 * 
	 * @Objective <b>To Stroe the Webement Text value in a Runtime Variable to use in future steps</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>19-AUG-16</b>
	 */

	public static boolean WebElementTextStoreDynamicSubstringValue(String getValueFromPOM, String strTestObject,String envVariableColumn,String startIndexColumn,String endIndexColumn, int strExecEventFlag )throws Exception  {
		String actualText=null;
		String substring=null;
		String strDataStart=null;
		String strDataEnd=null;
		String envVariableName=null;
		int intDataStart=0;
		int intDataend=0;
		boolean WebElementTextCompare=false;

		try{
			if(strExecEventFlag==1){
				strDataStart=RetrieveTestDataValue("WebElementTextCompare",startIndexColumn,strExecEventFlag);
				strDataEnd=RetrieveTestDataValue("WebElementTextCompare",endIndexColumn,strExecEventFlag);
				envVariableName=RetrieveTestDataValue("WebElementTextCompare",envVariableColumn,strExecEventFlag);
			}

			if(strDataEnd==null|| strDataEnd==null || envVariableName==null){
				Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while retreiving data from Spreadsheet. Value is NULL", false);
				return false;
			}

			actualText = selectByLocatorType(getValueFromPOM).getText();
			intDataStart = Integer.parseInt(strDataStart);
			intDataend = Integer.parseInt(strDataEnd);

			substring = actualText.substring(intDataStart, intDataend);

		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			return false;
		}

		try{
			Runtimevalue.setProperty(envVariableName, substring);
			Report_Functions.ReportEventSuccess(doc,"1", "WebElementTextStoreInEnv", "The Dynamic value '"+substring+"' of Element '"+strTestObject+"' is successfully stored in the Runtime variable '"+envVariableName+"'", 2);
			WebElementTextCompare=true;
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while Stroing the Dynamic value of element '"+strTestObject+"' in the Runtime variable '"+envVariableName+"'. Error description is :"+e.getMessage(), true);
			WebElementTextCompare=false;
		}
		return WebElementTextCompare;
	}

	/**
	 * 
	 * @Objective <b>This method is to check whether scroll bar is present on page</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Lakshman</b>
	 * @since <b>19-AUG-16</b>
	 */

	public static boolean checkTicketMsgIsPresent(String getValueFromPOM, String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{

			Thread.sleep(10000);

			//Xpath for Javascript executor, if value is present will return as true or not present will return as false
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Object scrollBarStatus = js.executeScript("return document.evaluate(\""+getValueFromPOM+"\",document.body,null,XPathResult.UNORDERED_NODE_ITERATOR_TYPE,null).iterateNext()!=null;");

			if(scrollBarStatus.equals(true)){

				log.info("Faded frame is present is available on page");
				Runtimevalue.setProperty("checkScrollBarPresent", Param.getProperty("scrollBarPresent"));
				Report_Functions.ReportEventSuccess (doc,"1","","Text Message Box is displayed in '"+strTestObject+"'",2);
				elementStatus = true;

			}else{				
				log.info("Faded frame is not available on page");
				Runtimevalue.setProperty("checkScrollBarPresent", Param.getProperty("scrollBarNotPresent"));
				Report_Functions.ReportEventSuccess (doc,"1","","Text Message Box is not displayed in '"+strTestObject+"'",2);
				elementStatus = true;
			}

		}catch(Exception e){

			log.info("Error occurred, while finding the scroll bar element on page");
			Report_Functions.ReportEventFailure(doc,"","Message box is not available on '"+strTestObject+"'" , true);
			elementStatus = false;
		}

		return elementStatus;
	}

	/** @Objective <b>This method is to enter the values into an elements where Value is got from ENV Variable.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebEditEnterTextFromEnvVariable(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {

		boolean WebEditEnterText= false;
		String strData=null;
		String dataToEnter=null;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebEditEnterText",strColumnName,strExecEventFlag);
			}

			if(strData==null){
				Report_Functions.ReportEventFailure(doc, "WebEditEnterText", "Error while retrieving data from Spreadsheet", false);
				WebEditEnterText=false;
			}

			dataToEnter=Runtimevalue.getProperty(strData);

			selectByLocatorType(getValueFromPOM).clear();
			selectByLocatorType(getValueFromPOM).sendKeys(dataToEnter);
			Report_Functions.ReportEventSuccess(doc, "1", "WebEditEnterText", "The Text '" +  dataToEnter + "' from Runtime Variable '"+strData+"' is entered in the Textbox -  '"+strTestObject+"'  successfully", 3);
			WebEditEnterText=true;	
		} catch (Exception e) { 	
			Report_Functions.ReportEventFailure(doc, "WebEditEnterText", "The Text '" + dataToEnter + "' from  Runtime Variable '"+strData+"' was not entered in the Textbox - '"+strTestObject+"'", true);
			WebEditEnterText=false;
			log.info("No Element Found to enter text : " + e);
		}
		return WebEditEnterText;
	}

	/**
	 * 
	 * @Objective <b>This method is to wait until expected text is available in webpage(enter button)</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Lakshman</b>
	 * @since <b>18-AUG-16</b>
	 */

	public static boolean WaitUntilElementClickable(String getValueFromPOM, String strTestObject) throws Exception {
		boolean Waituntilexpectedtext= false;
		String Element_Text = null;
		try{
			FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver);
			fWait.withTimeout(300, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(selectByLocatorType(getValueFromPOM)));
			Element_Text = selectByLocatorType(getValueFromPOM).getText();
		}catch(Exception e){
			Report_Functions.ReportEventFailure(doc,"Waituntilexpectedtext","Error occured while getting the text from the element '"+strTestObject+"' .Error description is : "+ e.getMessage() +"." , true); 
			Waituntilexpectedtext=false;
		}
		if(!(Element_Text==null)){
			Report_Functions.ReportEventSuccess(doc,"1","Waituntilexpectedtext","The Element '"+strTestObject+ "' is now Clickable",3);
			Waituntilexpectedtext= true;
		}else{
			Report_Functions.ReportEventFailure(doc,"Waituntilexpectedtext","The Element '"+strTestObject+ "' still not Clickable after certain period of time", true);
			Waituntilexpectedtext= false;
		}
		return Waituntilexpectedtext;
	}

	/**
	 * 
	 * @Objective <b>This method is to wait until expected text is available in webpage(enter button)</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Lakshman</b>
	 * @since <b>20-AUG-16</b>
	 */

	public static boolean WaitUntilElementVisible(String getValueFromPOM, String strTestObject) throws Exception {
		boolean Waituntilexpectedtext= false;
		String Element_Text = null;
		try{
			FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver);
			fWait.withTimeout(300, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(selectByLocatorType(getValueFromPOM)));
			Element_Text = selectByLocatorType(getValueFromPOM).getText();
		}catch(Exception e){
			Report_Functions.ReportEventFailure(doc,"Waituntilexpectedtext","Error occured while getting the text from the element '"+strTestObject+"' .Error description is : "+ e.getMessage() +"." , true); 
			Waituntilexpectedtext=false;
		}
		if(!(Element_Text==null)){
			Report_Functions.ReportEventSuccess(doc,"1","Waituntilexpectedtext","The Element '"+strTestObject+ "' is now Visible",3);
			Waituntilexpectedtext= true;
		}else{
			Report_Functions.ReportEventFailure(doc,"Waituntilexpectedtext","The Element '"+strTestObject+ "' still not Visible", true);
			Waituntilexpectedtext= false;
		}
		return Waituntilexpectedtext;
	}

	/**
	 * 
	 * @Objective <b>First is to check whether element is displayed in webpage, if availble it gets text from displayed element</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>20-AUG-16</b>
	 */

	public static boolean VerifyWebElementisVisible(String getValueFromPOM, String strTestObject) throws Exception {

		boolean elementvisible;
		boolean WebElementisVisible = false;
		try {

			elementvisible=selectByLocatorType(getValueFromPOM).isEnabled();

			String output=selectByLocatorType(getValueFromPOM).getText();
			if(elementvisible){
				WebElementisVisible = true;
				Report_Functions.ReportEventSuccess(doc, "1", "", "'"+strTestObject+"' is Displayed with value '"+output+ "'", 3);
			}else{
				WebElementisVisible=false;
				Report_Functions.ReportEventFailure(doc, "", "'"+strTestObject+"' is not Displayed", true);
			}
		} catch (Exception e) { 	
			log.info("Exception while finding visibility" + e);
			WebElementisVisible=false;
		}
		return WebElementisVisible;
	}

	/**
	 * 
	 * @Objective <b>This method is to wait the element is exist in webpage and synchronized with webdriver wait for 120 seconds</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>20-AUG-16</b>
	 */

	public static boolean waitUntilEnabled(String getValueFromPOM,  String strTestObject) throws Exception {

		boolean elementStatus= false;
		getDisplayTimeout = Param.getProperty("elementDisplayTimeout");
		intElementDisplayTimeout = Integer.parseInt(getDisplayTimeout);

		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver,intElementDisplayTimeout);

		try{

			String loc = Runtimevalue.getProperty("locatorType");

			switch(loc.toLowerCase()){

			case "id":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(getValueFromPOM)));
				if(element.isEnabled()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;


			case "xpath":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getValueFromPOM)));
				if(element.isEnabled()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			case "css":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(getValueFromPOM)));
				if(element.isEnabled()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			case "classname":	
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(getValueFromPOM)));
				if(element.isEnabled()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			case "name":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(getValueFromPOM)));
				if(element.isEnabled()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			case "linktext":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(getValueFromPOM)));
				if(element.isEnabled()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			case "tagname":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(getValueFromPOM)));
				if(element.isDisplayed()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			case "partiallinktext":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(getValueFromPOM)));
				if(element.isEnabled()){
					elementStatus = true;
				}else{
					elementStatus = false;
				}
				break;

			default:

				elementStatus = false;
				throw new IllegalArgumentException("Unable to found");
			}	

		}catch(StaleElementReferenceException e1){

			log.info("StaleElementReferenceException function Catch block. Calling Refresh Object.");
			RefreshObject(getValueFromPOM);

			if((selectByLocatorType(getValueFromPOM)).isDisplayed()){
				return waitUntilEnabled(getValueFromPOM, strTestObject);
			}else
			{
				elementStatus = false;
			}

		}catch(Exception e){

			log.info("Exception Occured in 'waitUntilExist':"+ e);
			elementStatus = false;

		}

		if(elementStatus){
			Report_Functions.ReportEventSuccess(doc,"1","","The element '"+ strTestObject +"' is enabled and visible successfully",3);
		}else{
			Report_Functions.ReportEventFailure(doc,"","The element '"+ strTestObject +"' is not enabled and visible within time of "+intElementDisplayTimeout+" seconds" , true);
		}

		return elementStatus;
	}	

	/**
	 * @Objective <b>This method is to wait Simply for given secs and presses esc on current open window once the time elapses</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>20-AUG-16</b>
	 */

	public static boolean PerformAction_doubleClick(String getValueFromPOM, String strOject) throws Exception {

		boolean elementStatus= false;

		try{
			WebElement element=selectByLocatorType(getValueFromPOM);
			Actions action = new Actions(driver);

			action.moveToElement(element).doubleClick(element).build().perform();

			elementStatus=true;


		}catch(Exception e){

			log.info("Exception Occured in 'PerformAction_doubleClick':"+ e);
			elementStatus = false;

		}

		if(elementStatus){
			Report_Functions.ReportEventSuccess(doc,"1","","Action double click performed over the Element '"+ strOject +"'",3);
		}else{
			Report_Functions.ReportEventFailure(doc,"","Action double click could not be performed over the Element '"+ strOject +"'" , true);
		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to checking and opening the SQL GBR connection<b>
	 * @param sqlserver
	 * @param sqldbname
	 * @param sqlusername
	 * @param sqlpassword
	 * @author <b>Lakshman</b>
	 * @since <b>11-AUG-16</b>
	 */

	public static boolean SQLDBOpenConnection_TT(String sqlserver, String sqldbname, String sqlusername, String sqlpassword)throws Exception  {
		boolean elementStatus= false;
		//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"        
		String dbUrl = "jdbc:sqlserver://"+ sqlserver +";DatabaseName=" + sqldbname +";";                  
		//Database Username     
		String username = sqlusername;   
		//Database Password     
		String password = sqlpassword; 

		if(sqldbname==null || sqlusername==null || sqlpassword==null || sqlserver==null){
			Report_Functions.ReportEventFailure(doc,  "SQLDBOpenConnection",  "Required details are not provided.", false);
			return false;
		}
		try {
			//Load mysql jdbc driver        
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  

			//Create Connection to DB       
			con_TT = DriverManager.getConnection(dbUrl,username,password);
			//Create Statement Object       
			stmt_TT = con_TT.createStatement(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "SQLDBOpenConnection2", "SQL Connection for Ticketing Tool is established from '"+sqldbname+"' DB in '"+sqlserver+"' Successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "SQLDBOpenConnection2",  "Error occured while connecting to the SQL  Ticketing Tool DB. Error description is : "+ e.getMessage() +".", false);
			log.info("SQLDBOpenConnection Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * @Objective <b>This method is to closing the SQL DB<b>
	 * @author <b>Lakshman</b>
	 * @since <b>11-AUG-16</b>
	 */

	public static boolean SQLDBCloseConnection_TT()throws Exception  {
		boolean elementStatus= false;

		try {
			// closing DB Connection       
			con_TT.close(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "SQLDBCloseConnection", "The SQL Connection for Ticketing Tool DB disconnected successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "SQLDBCloseConnection",  "Error occured while closing the connection for Ticketing Tool DB. Error description is : "+ e.getMessage() +".", false);
			log.info("SQLDBCloseConnection Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * @Objective <b>Verifies the SQL GBR DB select</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>10-AUG-16</b>
	 */

	public static boolean SQLDBSelect_TT(String sqltablename, String strsqlcolumnname,String strsqlcondition,String strExpectedvalue,int strExecEventFlag)throws Exception  {
		boolean SQLDBSelect= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		//String Expected_value = null;		
		String Expected_value = "";
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBSelect",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBSelect",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBSelect",strsqlcondition,strExecEventFlag);
				Expected_value=RetrieveTestDataValue("SQLDBSelect",strExpectedvalue,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+" order by 1 desc";

			rs_SQLServer_TT = stmt_TT.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", false);
			log.info("SQLDBSelect Error : " + e);
			SQLDBSelect=false;
		}

		try{
			rs_SQLServer_TT.next();
			Actual_Value = rs_SQLServer_TT.getString(1).trim();

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "No Record found for this query: "+query, true);
			log.info("SQLDBSelect Error : ");
			SQLDBSelect=false;
		}

		try{
			if(!rs_SQLServer_TT.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","SQLDBSelect", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches the expected value : '"+Expected_value+"'", 2);
					SQLDBSelect=true;
				}else if(!(Actual_Value.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", false);
					SQLDBSelect=false;
				}
			}

			else if(rs_SQLServer_TT.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBSelect", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					SQLDBSelect=true;
				}else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", false);  	 
					SQLDBSelect=false;
				}
			}
		}catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", false);
			log.info("SQLDBSelect Error : " + e);
			SQLDBSelect=false;
		}
		return SQLDBSelect;
	}

	/**
	 * @Objective <b>This method is to deleting records from SQL GBR DB<b>
	 * @param sqltablename
	 * @param sqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>11-AUG-16</b>
	 */

	public static boolean SQLDBDelete_TT(String sqltablename, String sqlcondition, int strExecEventFlag)throws Exception  {
		boolean elementStatus= false;
		String tablename = null;
		String condition = null;

		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("SQLDBDelete",sqltablename,strExecEventFlag);
				condition=RetrieveTestDataValue("SQLDBDelete",sqlcondition,strExecEventFlag);
			}

			if(tablename==null || condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBDelete",  "Required details are not provided in test data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Error log in reading file is "+e);
			throw e;
		}

		//Query to check records are available in DB
		//Query to Execute  

		//String check = "IF EXISTS(SELECT * FROM "+tablename+" WHERE "+condition+") SELECT 'TRUE' as STATUS ELSE SELECT 'FALSE' as STATUS";


		String check = "select * from "+tablename +" where "+condition;

		String query = "Delete from "+ tablename +" where "+ condition;

		ResultSet rs = null;

		try {			
			rs = stmt_TT.executeQuery(check);		

			int temp=0;	
			while(rs.next()){
				temp++;
			}

			if(temp >= 1){

				stmt_TT.execute(query);
				elementStatus=true;

				Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDelete", "The SQL Query : "+ query + " executed successfully.", 2);

			}//If rows not available FALSE will be returned so no delete
			else if(temp < 1){
				Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDelete", "The SQL Query : "+ query + " records are not availbale in DB", 2);
				elementStatus=true;
			}

		}catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "SQLDBDelete",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", false);
			log.info("SQLDBDelete Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * @Objective <b>Verifies the SQL GBR DB Update</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>10-AUG-16</b>
	 */

	public static boolean SQLDBUpdate_TT(String sqltablename, String strsqlcolumnname,String strsqlcolumnvalue,String strsqlcondition,int strExecEventFlag)throws Exception  {
		boolean SQLDBUpdate= false;
		String Table_name = null;
		String Column_name = null;
		String Column_Value = null;
		String SQL_condition = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBUpdate",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBUpdate",strsqlcolumnname,strExecEventFlag);
				Column_Value=RetrieveTestDataValue("SQLDBUpdate",strsqlcolumnvalue,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBUpdate",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || Column_Value==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBUpdate",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			String query = "update "+Table_name+" set "+Column_name+"="+Column_Value+" where "+SQL_condition;
			stmt_TT.execute(query);
			SQLDBUpdate=true;
			Report_Functions.ReportEventSuccess(doc, "1", "SQLDBUpdate", "The SQL Update Query : "+ query + " executed successfully.", 2);

		} catch (Exception e) {
			SQLDBUpdate=false;
			Report_Functions.ReportEventFailure(doc,  "SQLDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBUpdate Error : " + e);
		}
		return SQLDBUpdate;
	}

	/**
	 * 
	 * @Objective <b>This method is to checking and opening the SQL GBR connection<b>
	 * @param sqlserver
	 * @param sqldbname
	 * @param sqlusername
	 * @param sqlpassword
	 * @author <b>Lakshman</b>
	 * @since <b>18-AUG-16</b>
	 */

	public static boolean EshopSQLDBOpenConnection(String sqlserver, String sqldbname, String sqlusername, String sqlpassword)throws Exception  {
		boolean elementStatus= false;
		//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"        
		String dbUrl = "jdbc:sqlserver://"+ sqlserver +";DatabaseName=" + sqldbname +";";                  
		//Database Username     
		String username = sqlusername;   
		//Database Password     
		String password = sqlpassword; 

		if(sqldbname==null || sqlusername==null || sqlpassword==null || sqlserver==null){
			Report_Functions.ReportEventFailure(doc,  "SQLDBOpenConnection",  "Required details are not provided.", false);
			return false;
		}
		try {
			//Load mysql jdbc driver        
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  

			//Create Connection to DB       
			EshopConnection = DriverManager.getConnection(dbUrl,username,password);
			//Create Statement Object       
			EShopstmt = EshopConnection.createStatement(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "SQLDBOpenConnection2", "ESHOP SQL Connection for GBR is established from '"+sqldbname+"' DB in '"+sqlserver+"' Successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "SQLDBOpenConnection2",  "Error occured while connecting to the ESHOP SQL Server - GBR. Error description is : "+ e.getMessage() +".", false);
			log.info("EShopSQLDBOpenConnection_GBR Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * @Objective <b>Verifies to get the value from DB and store in RunTime environment</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @param envValColumnName Envirnment variable name from Excel sheet
	 * @author <b>Lakshman</b>
	 * @since <b>12-AUG-16</b>
	 */

	public static boolean storeSQLDBValueInEnv_TT(String sqltablename, String strsqlcolumnname, String strsqlcondition,String envValColumnName, int strExecEventFlag)throws Exception  {

		boolean storeSQLDBValueInEnv= false;

		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String setParameterName=null;
		String getIDValueFromRecord = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("StoreSQLDBValueInEnv",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("StoreSQLDBValueInEnv",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("StoreSQLDBValueInEnv",strsqlcondition,strExecEventFlag);
				setParameterName=RetrieveTestDataValue("StoreSQLDBValueInEnv",envValColumnName,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null ||setParameterName==null){
				Report_Functions.ReportEventFailure(doc,  "StoreSQLDBValueInEnv",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";

			rs_SQLServer_TT = stmt_TT.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "StoreSQLDBValueInEnv",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelect Error : " + e);
			storeSQLDBValueInEnv = false;
		}

		try{
			rs_SQLServer_TT.next();

			getIDValueFromRecord = rs_SQLServer_TT.getString(1).trim();

			//Store the value in RunTime environment
			Runtimevalue.setProperty(setParameterName, getIDValueFromRecord);
			//			log.info("Runtime value *************: "+Runtimevalue.getProperty("getIDValueForPendingApprovals"));

			Report_Functions.ReportEventSuccess(doc, "1", "StoreSQLDBValueInEnv", "Dynamic Value '"+getIDValueFromRecord+"' from DB is stored successfully in the Runtime Variable '"+setParameterName+"'", 2);
			storeSQLDBValueInEnv = true;

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "StoreSQLDBValueInEnv",  "No Record found for this query: "+query, true);
			e.printStackTrace();
			log.info("SQLDBSelect Error : ");
			storeSQLDBValueInEnv = false;
		}

		return storeSQLDBValueInEnv;
	}

	/**
	 * 
	 * @Objective <b>This method is to enter the values using Java script executor</b>
	 * @param locatorType
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>21-AUG-16</b>
	 */

	public static boolean javaScriptEnterText(String getValueFromPOM, String strTestObject, String strColumnName, int strExecEventFlag) throws Exception{

		boolean elementStatus = false;
		String elementValue = null;

		try{

			if(strExecEventFlag==1){

				elementValue = RetrieveTestDataValue("javaScriptDatePicker", strColumnName, strExecEventFlag);

			}

			if(elementValue == null){

				Report_Functions.ReportEventFailure(doc,  "javaScriptDatePicker",  "Required details are not provided in the data sheet.", false);
				return false;

			}

			//Get the locatorType from POM during runtime
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('"+getValueFromPOM+"').value = '"+elementValue+"'");
			Report_Functions.ReportEventSuccess (doc,"1","","The Text '"+elementValue+"' is entered for the Element "+strTestObject+" using JavaScript",2);	
			elementStatus = true;

		}catch(Exception e){

			log.info("Exception occurred in web table radio button :"+e.getMessage());
			e.printStackTrace();
			Report_Functions.ReportEventFailure(doc,"","The Text '"+elementValue+"' is not entered for "+strTestObject+"  using JavaScript" , true);  
			elementStatus = false;

		}

		return elementStatus;

	}

	/**
	 * 
	 * @Objective <b>This method verifies the Popup Modal is present - waits for 60 secs to Appear</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>22-AUG-16</b>
	 */

	public static boolean VerifyIsModalPopupPresent(String strTestObject) throws Exception{

		Object isModalPresent=false;

		try{
			log.info("Check element is appear");

			for(int i=0; i<60; i++){

				try{
					Thread.sleep(1000);
					log.info("Inside Loop... i is -> "+i);

					isModalPresent = ((JavascriptExecutor)driver).executeScript("return document.activeElement.getElementsByClassName('modal-footer')[0].innerHTML.trim()!=null;");

					/*JavascriptExecutor jse = (JavascriptExecutor)driver;
					WebElement element = driver.findElements(By.className("modal-footer"));
					isModalPresent = (jse.executeScript("return arguments[0].innerHTML.trim()!=null;", element));*/


					//JavascriptExecutor jse = (JavascriptExecutor)driver;
					//WebElement element = driver.findElement(By.xpath("//div[contains(text(), 'Ticket ID:')]"));
					//RetrievedMessage = (jse.executeScript("return arguments[0].innerHTML;", selectByLocatorType(getValueFromPOM)));

					if((boolean) isModalPresent){
						log.info("Element is appeared");
						break;
					}
				}catch(WebDriverException e){
					//DO Nothinf
					log.info("WebDriverException caught inside for since Popup is not appeared");
				}
			}

			if((boolean) isModalPresent){
				Report_Functions.ReportEventSuccess(doc,"1","","'The Modal Popup '"+ strTestObject +"' is displayed.",3);
				return true;
			}else{
				Report_Functions.ReportEventSuccess(doc,"1","","'The Modal Popup '"+ strTestObject +"' is not displayed within 60 secs.",3);
				return false;
			}


		}catch(StaleElementReferenceException e){
			log.info("StaleElementReferenceException caught inside for since Popup is not appeared");
			Report_Functions.ReportEventSuccess(doc,"1","","'The element '"+ strTestObject +"' is not displayed",3);
			return false;
		}
		catch(Exception e){
			log.info("Element is not found :"+e);
			Report_Functions.ReportEventFailure(doc,"","Exception occured. Error message is : "+ e +"." , true);
			return false;
		}
	}

	/**
	 * 
	 * @Objective <b>This method gets the Text available in the Modal using Javascript </b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>22-AUG-16</b>
	 */

	public static boolean VerifyModalPopupDynamicText(String getValueFromPOM, String strTestObject,String strPattern, int strExecEventFlag) throws Exception{

		boolean ModalVerifyDynamicText=false;
		boolean matchedStatus=false;
		Matcher matchedPattern = null;
		String Pattern_String = null;
		Object RetrievedMessage=null;
		
		try{

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//WebElement element = driver.findElement(By.xpath("//div[contains(text(), 'Ticket ID:')]"));
		RetrievedMessage = (jse.executeScript("return arguments[0].innerHTML;", selectByLocatorType(getValueFromPOM)));
		log.info("RetrievedMessage is : "+RetrievedMessage);

		try{
			if(strExecEventFlag==1){
				Pattern_String=RetrieveTestDataValue("",strPattern,strExecEventFlag);
			}

			if(Pattern_String==null){
				Report_Functions.ReportEventFailure(doc,  "",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			Pattern_String = Pattern_String.trim();
			Pattern expPattern=Pattern.compile(Pattern_String);
			matchedPattern = expPattern.matcher(RetrievedMessage.toString());
			matchedStatus=matchedPattern.find();
		}catch(Exception e){
			Report_Functions.ReportEventFailure(doc,  "",  "Error occured while matching the Expected pattern-matchedPattern.find():"+e.getMessage(), true);
			ModalVerifyDynamicText=false;
		}

		try{
			if(matchedStatus==true){
				log.info("Matched status pass:"+matchedStatus);
				Report_Functions.ReportEventSuccess(doc, "1", "", "The Expected pattern '"+Pattern_String+"' in the webElement '"+strTestObject+"' matches with the actual pattern: '"+RetrievedMessage+"' successfully", 2);
				ModalVerifyDynamicText=true;
			}else if(matchedStatus==false){
				log.info("Matched status fail:"+matchedStatus);
				Report_Functions.ReportEventFailure(doc,  "",  "The Expected pattern '"+Pattern_String+"' in the webElement '"+strTestObject+"' does not match with the actual pattern: '"+RetrievedMessage+"'", true);
				ModalVerifyDynamicText=false;
			}
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "",  "Error occured while finding the Pattern in the function 'WebElementDynamicStringVerify'.Error description is : "+ e.getMessage() +".", true);
			log.info("WebElementDynamicStringVerify Error : " + e);
			ModalVerifyDynamicText=false;
		}
		
		}catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "",  "Error occured while Verifying Moda lPopup Dynamic Text. Error description is : "+ e.getMessage() +".", true);
			log.info("WebElementDynamicStringVerify Error : " + e);
			ModalVerifyDynamicText=false;
		}
		return ModalVerifyDynamicText;
	}

	/**
	 * 
	 * @Objective <b>This method gets the Text available in the Modal and saves it in the Env Variable using Javascript </b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>22-AUG-16</b>
	 */

	public static boolean StoreModalPopupDynamicSubstringValue(String getValueFromPOM, String strTestObject,String envVariableColumn,String startIndexColumn,String endIndexColumn, int strExecEventFlag )throws Exception  {

		Object actualText=null;
		String substring=null;
		String strDataStart=null;
		String strDataEnd=null;
		String envVariableName=null;
		int intDataStart=0;
		int intDataend=0;
		boolean WebElementTextCompare=false;

		try{

			if(strExecEventFlag==1){
				strDataStart=RetrieveTestDataValue("WebElementTextCompare",startIndexColumn,strExecEventFlag);
				strDataEnd=RetrieveTestDataValue("WebElementTextCompare",endIndexColumn,strExecEventFlag);
				envVariableName=RetrieveTestDataValue("WebElementTextCompare",envVariableColumn,strExecEventFlag);
			}

			if(strDataEnd==null|| strDataEnd==null || envVariableName==null){
				Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while retreiving data from Spreadsheet. Value is NULL", false);
				return false;
			}

			JavascriptExecutor jse = (JavascriptExecutor)driver;
			//	WebElement element = driver.findElement(By.xpath("//div[contains(text(), 'Ticket ID:')]"));
			actualText = (jse.executeScript("return arguments[0].innerHTML;", selectByLocatorType(getValueFromPOM)));
			intDataStart = Integer.parseInt(strDataStart);
			intDataend = Integer.parseInt(strDataEnd);
			substring = actualText.toString().trim().substring(intDataStart, intDataend).trim();

		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			return false;
		}

		try{
			Runtimevalue.setProperty(envVariableName, substring);
			Report_Functions.ReportEventSuccess(doc,"1", "WebElementTextStoreInEnv", "The Dynamic value '"+substring+"' of Element '"+strTestObject+"' is successfully stored in the Runtime variable '"+envVariableName+"'", 2);
			WebElementTextCompare=true;
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while Stroing the Dynamic value of element '"+strTestObject+"' in the Runtime variable '"+envVariableName+"'. Error description is :"+e.getMessage(), true);
			WebElementTextCompare=false;
		}
		return WebElementTextCompare;
	}

	/**
	 * 
	 * @Objective <b>This method is to click a webelement using Javascript</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>23-AUG-16</b>
	 * 
	 */

	public static boolean javascriptWebElementClick(String getValueFromPOM, String strTestObject)throws Exception  {

		boolean WebElementClick= false;
		try {

			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", selectByLocatorType(getValueFromPOM));
			Report_Functions.ReportEventSuccess (doc,"1","","'"+strTestObject+"' is clicked successfully ",2);	
			WebElementClick=true;
		}catch(StaleElementReferenceException e){
			log.info("StaleElementReferenceException function Catch block. Calling Refresh Object.");
			RefreshObject(getValueFromPOM);
		}

		catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","'"+strTestObject+"' was not clicked" , true); 
			WebElementClick=false;
			log.info("No Element Found to click :" + e);
		}
		return WebElementClick;
	}

	/**
	 * 
	 * @Objective <b>To update the XML node value based on XPath</b>
	 * @param Location
	 * @param AttributeXPath
	 * @param strsqlcondition
	 * @param ValueToSet
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>23-AUG-16</b>
	 */

	public static boolean XMLTextUpdate(String Location,String AttributeXPath,String ValueToSet,int strExecEventFlag) throws Exception, IOException{
		log.info("XMLTextUpdate");
		boolean Executionstatus=false;

		if (Location==""){
			Report_Functions.ReportEventFailure(doc,  "XMLTextUpdate",  "Location Path for WebConfig is missing", true);
			return false;
		}
		if (AttributeXPath==""){
			Report_Functions.ReportEventFailure(doc,  "XMLTextUpdate",  "AttributeXPath Path for WebConfig is missing", true);
			return false;
		}
		if (ValueToSet==""){
			Report_Functions.ReportEventFailure(doc,  "XMLTextUpdate",  "ValueToSet in the node for WebConfig is missing", true);
			return false;
		}
		if(strExecEventFlag==1){
			Location=RetrieveTestDataValue("XMLTextUpdate",Location,strExecEventFlag);
			Location=Param.getProperty(Location);
			AttributeXPath=RetrieveTestDataValue("XMLTextUpdate",AttributeXPath,strExecEventFlag);
			ValueToSet=RetrieveTestDataValue("XMLTextUpdate",ValueToSet,strExecEventFlag);
			ValueToSet=Param.getProperty(ValueToSet);

			if (ValueToSet==""){
				Report_Functions.ReportEventFailure(doc,  "XMLTextUpdate",  "Value present in Property File Seems to Empty.Please check the property file.", true);
				return false;
			}
		}

		try {

			factory=DocumentBuilderFactory.newInstance();
			builder=factory.newDocumentBuilder();;
			file=new File("//\\"+Location);

			document=builder.parse(file);
			document.getDocumentElement().normalize();
			NodeList nodeList=null;
			xpath=XPathFactory.newInstance().newXPath();
			nodeList=(NodeList)xpath.compile(AttributeXPath).evaluate(document,XPathConstants.NODESET);

			nodeList.item(0).setTextContent(ValueToSet);


			TransformerFactory transFormerFactory = TransformerFactory.newInstance();
			Transformer transFormer = transFormerFactory.newTransformer();
			DOMSource source = new DOMSource(document);

			StreamResult result = new StreamResult(file);
			transFormer.transform(source, result);
			result.getOutputStream().close();
			Executionstatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "XMLTextUpdate", "XML config File '"+Location+"' has been updated successfully for the tag '"+AttributeXPath+"' with the value set as '"+ValueToSet+"'", 2);
			Thread.sleep(2000);
		} catch (ParserConfigurationException e) {
			log.info("catch ParserConfigurationException");
			e.printStackTrace();
			Report_Functions.ReportEventFailure(doc,  "XMLTextUpdate",  "ValueToSet in the node for WebConfig is Not successfull due to reason: '"+e.getMessage()+"'", false);
			Executionstatus=false;

		} catch (Exception e) {
			log.info("catch Excpetion");
			e.printStackTrace();
			Report_Functions.ReportEventFailure(doc,  "XMLTextUpdate",  "Exception occured during XMLTextUpdate. Reason: '"+e.getMessage()+"'", false);
			Executionstatus=false;
		}
		builder=null;
		xpath=null;
		nodeList=null;
		return Executionstatus;
	}

	/** 
	 * @Objective <b>This method is to checks the Max length of the field and try to enter char greater than the accepted length
	 * and validates the same</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strMaxLegthColumn <b>Enter the Expected Max length value of the field</b>
	 * @param strColumnName <b>Value to enter into the TextBox from the datasheet<>
	 * @param strExpectedValueColumn <b>Compares this value from Excel with the value in the Element<>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>23-AUG-16</b>
	 */

	public static boolean WebEditCheckMaxLengthAndEnterValueCompareSame(String getValueFromPOM, String strTestObject,String strMaxLegthColumn,String strColumnName,String strExpectedValueColumn,int strExecEventFlag )throws Exception  {

		String actualResult=null;
		String strData=null;
		String strExpectedData=null;
		String strMaxLengthOfWebEdit=null;
		String actualfieldlength=null;
		boolean WebEditEnterAndCompareValue=false;



		try {
			if(strExecEventFlag==1){
				strMaxLengthOfWebEdit=RetrieveTestDataValue("WebEditEnterText",strMaxLegthColumn,strExecEventFlag);
				strData=RetrieveTestDataValue("WebEditEnterText",strColumnName,strExecEventFlag);
				strExpectedData=RetrieveTestDataValue("WebEditEnterText",strExpectedValueColumn,strExecEventFlag);
			}

			if(strMaxLengthOfWebEdit==null||strData==null || strExpectedData==null){
				Report_Functions.ReportEventFailure(doc,  "WebElementValueCompare",  "Required details are not provided in the dataSheet.", false);
				return false;
			}

			try{

				actualfieldlength=selectByLocatorType(getValueFromPOM).getAttribute("maxlength");

				if((actualfieldlength.trim()).equalsIgnoreCase(strMaxLengthOfWebEdit.trim())){
					WebEditEnterAndCompareValue=true;
					Report_Functions.ReportEventSuccess(doc,"1", "", ""+strTestObject+"'s  Max length value '" + actualResult + "' matches the Expected Max length '" + strExpectedData + "'", 2);
				}else{
					WebEditEnterAndCompareValue=false;
					Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+"'s Max length value '" + actualResult + "' does not match the Expected Max length '" + strExpectedData + "'", true);
				}
			}catch(Exception e){
				Report_Functions.ReportEventFailure(doc,"", "Exception occured while getting the Max length of '"+strTestObject+"'", true);
				WebEditEnterAndCompareValue=false;
				log.info("No Element Found to Get Length Text : " + e);
			}


			selectByLocatorType(getValueFromPOM).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			selectByLocatorType(getValueFromPOM).sendKeys(strData);
			Report_Functions.ReportEventSuccess(doc, "1", "WebEditEnterText", "Entering the Text '" +  strData + "' in the Textbox -  '"+strTestObject+"'", 3);
			//WebEditEnterAndCompareValue=true;	
		} catch (Exception e) { 	
			Report_Functions.ReportEventFailure(doc, "WebEditEnterText", "The Text '" + strData + "' was not entered in the Textbox - '"+strTestObject+"'", true);
			WebEditEnterAndCompareValue=false;
			log.info("No Element Found to enter text : " + e);
		}

		try {
			actualResult = selectByLocatorType(getValueFromPOM).getAttribute("value");

			if((actualResult.trim()).equalsIgnoreCase(strExpectedData.trim())){
				WebEditEnterAndCompareValue=true;
				Report_Functions.ReportEventSuccess(doc,"1", "", ""+strTestObject+"'s  accepted value '" + actualResult + "' matches the Expected value '" + strExpectedData + "'", 2);
			}else{
				WebEditEnterAndCompareValue=false;
				Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+"'s accepted value '" + actualResult + "' does not match the Expected Value '" + strExpectedData + "'", true);
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+"'s accepted value '" + actualResult + "' does not match the Expected Value '" + strExpectedData + "'", true);
			WebEditEnterAndCompareValue=false;
			log.info("No Element Found to compare Text : " + e);
		}
		return WebEditEnterAndCompareValue;
	}

	/** 
	 * 
	 * @Objective <b>This method is to Verify the color of the given element with Expected color</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Value to enter into the TextBox from the datasheet<>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>23-AUG-16</b>
	 */

	public static boolean WebElementVerifyElementColor(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {

		String strData=null;
		boolean WebEditEnterAndCompareValue=false;
		String colorOfElement=null;
		String colorInCode=null;

		if(strExecEventFlag==1){
			strData=RetrieveTestDataValue("WebEditEnterText",strColumnName,strExecEventFlag);
		}

		if(strData==null){
			Report_Functions.ReportEventFailure(doc,  "WebElementValueCompare",  "Required details are not provided in the dataSheet.", false);
			return false;
		}

		try{

			colorOfElement=selectByLocatorType(getValueFromPOM).getCssValue("color");
			log.info("Color of the Element is : "+colorOfElement);

			//color conversion:
			if(colorOfElement.trim().equalsIgnoreCase("rgba(0, 128, 0, 1)")){
				colorInCode="green";
			}else if(colorOfElement.trim().equalsIgnoreCase("rgba(255, 0, 0, 1)")){
				colorInCode="red";
			}else{
				colorInCode="No Color Conversion is done";
			}



			if((colorInCode.trim()).equalsIgnoreCase(strData.trim())){
				WebEditEnterAndCompareValue=true;
				Report_Functions.ReportEventSuccess(doc,"1", "", ""+strTestObject+"'s  Color '" + colorInCode + "' matches the Expected Color '" + strData + "'", 2);
			}else{
				WebEditEnterAndCompareValue=false;
				Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+"'s Max length value '" + colorInCode + "' does not match the Expected Max length '" + strData + "'", true);
			}
		}catch(Exception e){
			Report_Functions.ReportEventFailure(doc,"", "Exception occured while comparing the color '"+strTestObject+"'", true);
			WebEditEnterAndCompareValue=false;
			log.info("Exception occured in Color compare : " + e);
		}

		return WebEditEnterAndCompareValue;
	}

	/**
	 * 
	 * @Objective <b>This method will generate the future date based on the days given and compares with expected web element date.</b>
	 * @param getValueFromPOM
	 * @author <b>Yogendra</b>
	 * @since <b>20-July-16</b>
	 */

	public static boolean webElementFutureDateCompare(String getValueFromPOM, String strtestobject, String date_format, String Days_to_add, int strExecEventFlag) throws Exception{

		boolean webElementFutureDateCompare = false;

		String webElementValue = null;
		DateFormat dateformat = null;
		Date date = null;
		String futureDate = null;
		String dateFormat = null;
		String daystoadd = null;

		try{

			if(strExecEventFlag == 1){
				dateFormat = RetrieveTestDataValue("webElementFutureDateCompare", date_format, strExecEventFlag);
				daystoadd = RetrieveTestDataValue("webElementFutureDateCompare", Days_to_add, strExecEventFlag);
			}

			if(dateFormat==null || daystoadd ==null){
				Report_Functions.ReportEventFailure(doc,  "webElementFutureDateCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			int Add_Days = Integer.parseInt(daystoadd);

			webElementValue = selectByLocatorType(getValueFromPOM).getText();

			dateformat = new SimpleDateFormat(dateFormat);
			date = new Date();
			Calendar expdate = Calendar.getInstance();
			expdate.setTime(date);
			expdate.add(Calendar.DATE, Add_Days);
			futureDate = dateformat.format(expdate.getTime());

			if((webElementValue.trim()).equals(futureDate.trim())){

				Report_Functions.ReportEventSuccess(doc,"1","webElementFutureDateCompare","The actual date '"+webElementValue+"' is matched with expected date '"+ futureDate +"' successfully ",2);
				webElementFutureDateCompare = true;
			}
			else{

				Report_Functions.ReportEventFailure(doc,"webElementFutureDateCompare","The actual date '"+webElementValue+"' is not matched with expected date '"+ futureDate +"' " , true); 
				webElementFutureDateCompare = false;
			}

		}catch(Exception e){

			Report_Functions.ReportEventFailure(doc,"webElementFutureDateCompare", "Error occured while getting the text from the WebElement :'"+ strtestobject +"'and the error description is :"+e.getMessage(), true);
			webElementFutureDateCompare=false;

		}

		return webElementFutureDateCompare;
	}

	/**
	 * 
	 * @Objective <b>This method is to click a webelement using Javascript</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>23-AUG-16</b>
	 * 
	 */

	public static boolean JSWebElementValueCompare(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {

		String actualResult=null;
		String strData=null;
		boolean WebElementValueCompare=false;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("",strColumnName,strExecEventFlag);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "",  "Required details are not provided.", false);
				return false;
			}

			//actualResult = selectByLocatorType(getValueFromPOM).getAttribute("value");

			JavascriptExecutor executor = (JavascriptExecutor)driver;
			//document.getElementById("myBtn").getAttribute("onclick");

			actualResult = (String) executor.executeScript("return arguments[0].getAttribute('value');", selectByLocatorType(getValueFromPOM));

			if((actualResult.trim()).equalsIgnoreCase(strData.trim())){
				WebElementValueCompare=true;
				Report_Functions.ReportEventSuccess(doc,"1", "", ""+strTestObject+"  Actual Value '" + actualResult + "' matches the Expected value '" + strData + "'", 2);
			}else{
				WebElementValueCompare=false;
				Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+" Actual Value '" + actualResult + "' does not match the Expected Value '" + strData + "'", true);
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+" Actual Value '" + actualResult + "' does not match the Expected Value '" + strData + "'", true);
			WebElementValueCompare=false;
			log.info("No Element Found to compare Text : " + e);
		}
		return WebElementValueCompare;
	}

	/**
	 * 
	 * @Objective <b>This method gets the Text available in the Modal using Javascript </b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>22-AUG-16</b>
	 */

	public static boolean JSWebElementTextCompare(String getValueFromPOM, String strTestObject,String strColumn, int strExecEventFlag) throws Exception{

		String expectedContent=null;
		Object actualResult=null;


		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//WebElement element = driver.findElement(By.xpath("//div[contains(text(), 'Ticket ID:')]"));
		actualResult = (jse.executeScript("return arguments[0].innerHTML;", selectByLocatorType(getValueFromPOM)));
		log.info("RetrievedMessage is : "+actualResult);

		if(strExecEventFlag==1){
			expectedContent=RetrieveTestDataValue("",strColumn,strExecEventFlag);
		}

		if(expectedContent==null){
			Report_Functions.ReportEventFailure(doc,  "",  "Required details are not provided in test data sheet.", false);
			return false;
		}


		try{
			if((actualResult.toString().trim()).equalsIgnoreCase(expectedContent.trim())){

				Report_Functions.ReportEventSuccess(doc,"1", "WebElementTextCompare", "The  Actual Value '" +actualResult.toString()+ "' matches with the Expected value '"+expectedContent+ "' in the input field '"+strTestObject+"'", 2);
				return true;
			}else{
				Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "The  Actual Value '" +actualResult.toString()+ "' does not match with the Expected value '"+expectedContent+ "' in the input field '"+strTestObject+"'", true);
				return false;
			}
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while comparing actual and expected values. Error description is :"+e.getMessage(), true);
			return false;
		}
	}

	/**
	 * 
	 * @Objective <b>This method is to compare the title vattribute value between excel sheet and given webelement. If true the expected value will be return</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Will get the compare values in excel sheet<>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>29-AUG-16</b>
	 */

	public static boolean WebElementTitleAttributeCompare(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {

		String actualResult=null;
		String strData=null;
		boolean WebElementValueCompare=false;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebElementValueCompare",strColumnName,strExecEventFlag);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebElementValueCompare",  "Required details are not provided.", false);
				return false;
			}

			actualResult = selectByLocatorType(getValueFromPOM).getAttribute("title");

			if((actualResult.trim()).equalsIgnoreCase(strData.trim())){
				WebElementValueCompare=true;
				Report_Functions.ReportEventSuccess(doc,"1", "", ""+strTestObject+"  Actual Value '" + actualResult + "' matches the Expected value '" + strData + "'", 2);
			}else{
				WebElementValueCompare=false;
				Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+" Actual Value '" + actualResult + "' does not match the Expected Value '" + strData + "'", true);
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+" Actual Value '" + actualResult + "' does not match the Expected Value '" + strData + "'", true);
			WebElementValueCompare=false;
			log.info("No Element Found to compare Text : " + e);
		}
		return WebElementValueCompare;
	}
	
	/**
	 * 
	 * @Objective <b>This method verifies the Popup Modal is present - waits for 60 secs to Appear</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>22-AUG-16</b>
	 */

	public static boolean VerifyAddressModalPopupPresent(String strTestObject, String expectedValue) throws Exception{

		Object strvalue="notdisplayed";
		boolean isModalPresent=false;

		try{
			log.info("Check element is appear");

			for(int i=0; i<60; i++){

				try{
					Thread.sleep(1000);
					log.info("Inside Loop... i is -> "+i);

					//isModalPresent = ((JavascriptExecutor)driver).executeScript("return document.activeElement.getElementById('lstAddressResult').value!=null;");
					//strvalue = ((JavascriptExecutor)driver).executeScript("return document.activeElement.getElementByXPath('//div[@class='col-md-11 col-md-offset-1']').getText();");
					//div[@class='col-md-11 col-md-offset-1']//strong
					
					/*JavascriptExecutor jse = (JavascriptExecutor)driver;
					 String script = "return   document.getElementByXPath('//div[@class='col-md-11 col-md-offset-1']').getText();";
					 strvalue = jse.executeScript(script).toString();
					*/
					strvalue = ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML",driver.findElement(By.xpath("//strong[contains(text(),'Address List')]")));
					
					/*JavascriptExecutor jse = (JavascriptExecutor)driver;
					WebElement element = driver.findElements(By.className("modal-footer"));
					isModalPresent = (jse.executeScript("return arguments[0].innerHTML.trim()!=null;", element));*/
					if(strvalue==null){
						log.info("Value is NULL");
					}else{
					log.info("Value is : "+strvalue.toString());
					}
					//JavascriptExecutor jse = (JavascriptExecutor)driver;
					//WebElement element = driver.findElement(By.xpath("//div[contains(text(), 'Ticket ID:')]"));
					//RetrievedMessage = (jse.executeScript("return arguments[0].innerHTML;", selectByLocatorType(getValueFromPOM)));
					

					if(strvalue.toString().trim().equalsIgnoreCase(expectedValue.trim())){
						log.info("Element is appeared");
						isModalPresent=true;
						break;
					}
					
					
					/*if((boolean) isModalPresent){
						log.info("Element is appeared");
						break;
					}*/
				}catch(WebDriverException e){
					//DO Nothinf
					log.info("WebDriverException caught inside for since Popup is not appeared "+e);
				}catch(NullPointerException e){
				//DO Nothinf
				log.info("NullPointerException caught inside for since Popup is not appeared "+e);
			}
			}

			if((boolean) isModalPresent){
				Report_Functions.ReportEventSuccess(doc,"1","","'The Modal Popup '"+ strTestObject +"' is displayed.",3);
				return true;
			}else{
				Report_Functions.ReportEventSuccess(doc,"1","","'The Modal Popup '"+ strTestObject +"' is not displayed within 60 secs.",3);
				return false;
			}


		}catch(StaleElementReferenceException e){
			log.info("StaleElementReferenceException caught inside for since Popup is not appeared");
			Report_Functions.ReportEventSuccess(doc,"1","","'The element '"+ strTestObject +"' is not displayed",3);
			return false;
		}
		catch(Exception e){
			log.info("Element is not found :"+e);
			Report_Functions.ReportEventFailure(doc,"","Exception occured. Error message is : "+ e +"." , true);
			return false;
		}
	}
	
	/**
	 * 
	 * @Objective <b>Verifies the SQL DB select</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>30-AUG-16</b>
	 */

	public static boolean SQLDBCommonSelect(String Type,String sqltablename, String strsqlcolumnname,String strsqlcondition,String strExpectedvalue,String Days_to_add,int strExecEventFlag)throws Exception  {
		
		boolean result=false;
		String actionType=null;
		try{
			
			if (strExecEventFlag==1){
				actionType=RetrieveTestDataValue("",Type,strExecEventFlag);
			}
			if(actionType==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
		
			if(actionType.trim().equalsIgnoreCase("Normal")){
				result= SQLDBSelect(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("Date")){
				result= SQLDBDateFormatCompare(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("FutureDate")){
				result= SQLDBFutureDateCompare(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, Days_to_add, strExecEventFlag);
			}else{
				log.info("Invalid Action item from Excel");
				Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet - "+actionType, false);
			}
		}catch (Exception e) {
			log.info("Exception occured SQLDBCommonSelect. Exception is : "+e);
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			return false;
		}
		return result;

	}
	
	/**
	 * 
	 * @Objective <b>Verifies to check date comparision in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param Date_Format
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>30-AUG-16</b>
	 */

	public static boolean SQLDBDateFormatCompare(String sqltablename, String strsqlcolumnname,String strsqlcondition,String dateFormatFromExcel,int strExecEventFlag)throws Exception  {
		boolean SQLDBDateCompare= false;
		String query = null;  
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Expected_value = null;
		String Actual_Value = null;
		String Current_Date=null;
		String expected_db_Date = null;
		String Date_Format=null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBDateCompare",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBDateCompare",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBDateCompare",strsqlcondition,strExecEventFlag);
				Date_Format=RetrieveTestDataValue("SQLDBDateCompare",dateFormatFromExcel,strExecEventFlag);
			}
			
			log.info("Table_name is : "+Table_name);
			log.info("Column_name is : "+Column_name);
			log.info("SQL_condition is : "+SQL_condition);
			log.info("Date_Format is : "+Date_Format);

			if(Table_name==null || Column_name==null || SQL_condition==null || Date_Format ==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat dateFormat = new SimpleDateFormat(Date_Format);
			Date date = new Date();
			Current_Date = dateFormat.format(date);
			Expected_value = Current_Date.trim();

			// Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			rs_SQLServer= stmt.executeQuery(query);
			
			log.info("Quer is : "+query);

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBDateCompare Error : " + e);
			SQLDBDateCompare=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1);
			String db_Date = Actual_Value.split(" ")[0];

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date dateToChange = dateFormat.parse(db_Date);

			SimpleDateFormat finalDateFormat = new SimpleDateFormat(Date_Format);
			expected_db_Date = finalDateFormat.format(dateToChange);	

		} catch (Exception NullPointerException) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "No Record found for this query: "+query, true);
			log.info("SQLDBDateCompare Error : ");
			SQLDBDateCompare=false;
		}

		try{

			if(!rs_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(expected_db_Date.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDateCompare", "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' matches with the expected date : '"+Expected_value+"' ", 2);
					SQLDBDateCompare=true;
				}else if(!(expected_db_Date.equalsIgnoreCase(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' does not match with the expected date : '"+Expected_value+"' ", true);
					SQLDBDateCompare=false;
				}
			}else if(rs_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equalsIgnoreCase("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDateCompare", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					SQLDBDateCompare=true;
				}else if(!(Expected_value.equalsIgnoreCase("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					SQLDBDateCompare=false;
				}
			}

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Error occured while comparing the dates in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBDateCompare Error : " + e);
			SQLDBDateCompare=false;
		}
		return SQLDBDateCompare;
	}

	/**
	 * 
	 * @Objective <b>Verifies the values from SQLDB in the dropdown</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param getValueFromPOM
	 * @param strExecEventFlag
	 * @author <b>LAKSHMAN</b>
	 * @since <b>30-AUG-2016</b>
	 */

	public static boolean WeblistNumbersVerify(String getValueFromPOM, String strTestObject, String totalNumbersColumn,int itemscount_not_consider,int strExecEventFlag)throws Exception  {

		boolean WeblistSQLDBitemsverify= false;
		String strData=null;
		int totalNumber;
		
		String[] weblistvalues = null;
		
		if(strExecEventFlag==1){
			strData=RetrieveTestDataValue("WebElementValueCompare",totalNumbersColumn,strExecEventFlag);
		}
		if(strData==null){
			Report_Functions.ReportEventFailure(doc,  "WebElementValueCompare",  "Required details are not provided.", false);
			return false;
		}
		
		totalNumber=Integer.parseInt(strData);

		try{

			Select se = new Select(selectByLocatorType(getValueFromPOM));
			List<WebElement> options = se.getOptions();

			//if you want to get all elements text into array list
			List<String> all_elements_text=new ArrayList<String>();

			for(int j=0; j<options.size(); j++){

				//loading text of each element in to array all_elements_text
				all_elements_text.add(options.get(j).getText());

			}

			weblistvalues = (String[]) all_elements_text.toArray(new String[all_elements_text.size()]);

			log.info("Int length is : "+totalNumbersColumn);
			log.info("weblistvalues length is : "+weblistvalues.length);

			if(totalNumber == weblistvalues.length - itemscount_not_consider)
			{
				if((weblistvalues.length - itemscount_not_consider)==0){
					
					log.info("No value present in DB as well list");
					Report_Functions.ReportEventSuccess(doc, "1","", "No Dropdown Value is Present in the Dropdown '"+strTestObject+"' and the corresponding Table", 2);
					WeblistSQLDBitemsverify=true;
				
				}else{
					for (int i=0; i<(weblistvalues.length - itemscount_not_consider); i++){
						
						if (weblistvalues[i+1].equals(Integer.toString(i+1))){
							Report_Functions.ReportEventSuccess(doc, "1","WeblistSQLDBitemsverify", "The actual value : '"+ weblistvalues[i+1] +"' in the dropdown : "+ strTestObject +"' matches the expected value : '"+(i+1)+"'.", 2);
							WeblistSQLDBitemsverify=true;
						} else{
							Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "The actual value : '"+ weblistvalues[i+1] +"' in the dropdown : "+ strTestObject +"' doesn't matches the expected value : '"+(i+1)+"'.", true);
							WeblistSQLDBitemsverify=false;
						}
					}
				}
			} else {
				Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "The number of items present in the dropdown : "+ strTestObject +"' doesn't matches with the given number of items '"+ totalNumber +"'.", true);
				WeblistSQLDBitemsverify=false;
			}

		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "Error occured while comparing the values. Error description is : "+ e.getMessage() +".", true);
			log.info("WeblistSQLDBitemsverify Error : " + e);
			WeblistSQLDBitemsverify=false;
		}
		return WeblistSQLDBitemsverify;
	}
	
	/**
	 * @Objective <b>This method is to wait Simply for given secs and presses esc on current open window once the time elapses</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>30-AUG-16</b>
	 */

	public static boolean PerformAction_MoveToElementAndClick(String getValueFromPOM, String strOject) throws Exception {

		boolean elementStatus= false;

		try{
			//Thread.sleep(2000);
			WebElement element=selectByLocatorType(getValueFromPOM);
			Actions action = new Actions(driver);
			action.moveToElement(element).click(element).build().perform();
			elementStatus=true;
		}catch(Exception e){
			log.info("Exception Occured in PerformActionMoveToElementAndClick. Exception is : "+ e);
			elementStatus = false;
		}

		if(elementStatus){
			Report_Functions.ReportEventSuccess(doc,"1","","Mouse moved to the element '"+strOject+"' and click is done",3);
		}else{
			Report_Functions.ReportEventFailure(doc,"","Mouse not  moved to the element '"+strOject+"' and click action is not performed" , true);
		}

		return elementStatus;
	}
	
	/**
	 * 
	 * @Objective <b>To compare the texts both Environment value and web element</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>LAKSHMAN</b>
	 * @since <b>30-AUG-16</b>
	 */

	public static boolean WebElementTextCompareFromEnv(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {
		String actualResult=null;
		String strData=null;
		boolean WebElementValueCompareFromEnv=false;
		try{
			if (strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebElementValueCompareFromEnv",strColumnName,strExecEventFlag);
				strData=Runtimevalue.getProperty(strData);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			actualResult = selectByLocatorType(getValueFromPOM).getText();

		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementValueCompareFromEnv", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			WebElementValueCompareFromEnv=false;
		}

		try{
			if((actualResult.trim()).equalsIgnoreCase(strData.trim())){
				Report_Functions.ReportEventSuccess(doc,"1", "WebElementValueCompareFromEnv", "The  Actual Value '" +actualResult+ "' matches with the Expected value '"+strData+ "' in the input field '"+strTestObject+"'", 2);
				WebElementValueCompareFromEnv=true;
			}else{
				Report_Functions.ReportEventFailure(doc,"WebElementValueCompareFromEnv", "The  Actual Value '" +actualResult+ "' does not match with the Expected value '"+strData+ "' in the input field '"+strTestObject+"'", true);
				WebElementValueCompareFromEnv=false;
			}
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementValueCompareFromEnv", "Error occured while comparing actual and expected values. Error description is :"+e.getMessage(), true);
			WebElementValueCompareFromEnv=false;
		}
		return WebElementValueCompareFromEnv;
	}
	
	/**
	 * 
	 * @Objective <b>Check for the webelement enabled<b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Lakshman</b>
	 * @since <b>31-AUG-16</b>
	 */

	public static boolean WaitUntilNoLongerExist(String getValueFromPOM, String strTestObject) throws Exception {

		boolean elementenable=false;

		try{
			String loc = Runtimevalue.getProperty("locatorType");

			int a = 0;
			while(a < 60){
				try{
					Thread.sleep(1000);	

					switch(loc.toLowerCase()){

					case "id":
						driver.findElement(By.id(getValueFromPOM));break;
					case "xpath":
						driver.findElement(By.xpath(getValueFromPOM));break;
					case "css":
						driver.findElement(By.cssSelector(getValueFromPOM));break;
					case "classname":	
						driver.findElement(By.className(getValueFromPOM));break;
					case "name":
						driver.findElement(By.name(getValueFromPOM));	break;
					case "linktext":
						driver.findElement(By.linkText(getValueFromPOM));	break;
					case "tagname":
						driver.findElement(By.tagName(getValueFromPOM)); break;
					case "partiallinktext":
						driver.findElement(By.partialLinkText(getValueFromPOM)); break;
					default:
						throw new IllegalArgumentException("Check Locator used to indentify the Element");
					}	

					log.info("Element is present in the Page i is : "+a);
					a++;

				}catch(org.openqa.selenium.NoSuchElementException e){
					log.info("No Element is present in the Page. Exception is :"+ e);
					elementenable= true;
					break;

				}catch(org.openqa.selenium.StaleElementReferenceException e){
					log.info("Stale Element Error is present in the Page. Exception is : "+ e);
					elementenable= true;
					break;
				}
			}

			if(elementenable){
				Report_Functions.ReportEventSuccess(doc, "1", "", "The object '"+strTestObject+"' is not present as expected.", 3);
			}else{
				Report_Functions.ReportEventFailure(doc, "", "The object '"+strTestObject+"' is still present in the Page.", true);
			}
		}catch(Exception e) {
			Report_Functions.ReportEventFailure(doc, "", "Error in WaitUntilNoLongerExist. Exception is : "+e, true);

		}

		return elementenable;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies to update the data for RRBS in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>31-AUG-16</b>
	 */

	public static boolean SQLDBCommonPreCondition(String actionName,String sqltablename, String strsqlcolumnname,String strsqlcolumnvalue,String strsqlcondition,int strExecEventFlag)throws Exception  {

		boolean elementStatus= false;
		String action=null;
		
		try {
			
			if (strExecEventFlag==1){
				action=RetrieveTestDataValue("",actionName,strExecEventFlag);
			}
			if(action==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			
			if(action.trim().equalsIgnoreCase("Update")){
				elementStatus=SQLDBUpdate(sqltablename, strsqlcolumnname, strsqlcolumnvalue, strsqlcondition, strExecEventFlag);
			}else if(action.trim().equalsIgnoreCase("Delete")){
				elementStatus=SQLDBDelete(sqltablename, strsqlcondition, strExecEventFlag);
			}else{
				log.info("Invalid Action item from Excel");
				Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet - "+action, false);
			}
	
		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBUpdate Error : " + e);
		}
		return elementStatus;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies to update the data for RRBS in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>31-AUG-16</b>
	 */

	public static boolean RRBSDBCommonSelect(String strType,String sqltablename, String strsqlcolumnname,String strsqlcondition,String strsqlcolumnvalue,String Date_Format,String Days_to_add,int strExecEventFlag)throws Exception  {

		boolean result= false;
		String type=null;
		
		try {
			
			if (strExecEventFlag==1){
				type=RetrieveTestDataValue("",strType,strExecEventFlag);
			}
			
			if(type==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			
			if(type.trim().equalsIgnoreCase("Normal")){
				result=RRBSDBSelect(sqltablename, strsqlcolumnname, strsqlcondition, strsqlcolumnvalue, strExecEventFlag);
			}else if(type.trim().equalsIgnoreCase("Date")){
				result=RRBSDBDateCompare(sqltablename, strsqlcolumnname, strsqlcondition, Date_Format, strExecEventFlag);
			}else if(type.trim().equalsIgnoreCase("FutureDate")){
				result=RRBSDBFutureDateCompare(sqltablename, strsqlcolumnname, strsqlcondition, Date_Format, Days_to_add, strExecEventFlag);
			}else{
				log.info("Invalid Action item from Excel");
				Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet - "+type, false);
			}
	
		} catch (Exception e) { 
			result=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBUpdate Error : " + e);
		}
		return result;
	}
	
	/**
	 * 
	 * @Objective <b>To start the Winservice</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>31-AUG-16</b>
	 */

	public static boolean WinserviceStartStopUsingSC(String strServerIP,String strActionToDo, String strWinserviceName,int strExecEventFlag)throws Exception  {

		boolean result= false;
		String serverIP=null;
		String actionToDo=null;
		String winserviceName=null;
		String state=null;
		Process p;

		try {

			if (strExecEventFlag==1){
				serverIP=RetrieveTestDataValue("",strServerIP,strExecEventFlag);
				actionToDo=RetrieveTestDataValue("",strActionToDo,strExecEventFlag);
				winserviceName=RetrieveTestDataValue("",strWinserviceName,strExecEventFlag);
			}

			if(serverIP==null ||actionToDo==null ||winserviceName==null ){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}

			log.info("Querying the Winservice state...");
			
			try{
				
				p=Runtime.getRuntime().exec("sc \\\\"+serverIP+" query "+winserviceName+"");
				
			}catch(Exception e){
					Report_Functions.ReportEventFailure(doc,  "",  "Error occured while Query the state of winservice. Exception is : "+ e.getMessage() +".", false);
					return false;
				}

			BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 

			String line=reader.readLine();
			while(line!=null) { 

				if(line.trim().startsWith("STATE")){
					if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("1")){
						log.info("Stopped");
						state="STOPPED";
					}else if(line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("2")){
						log.info("Startting....");
						state="STARTING";
					}else if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("3")){
						log.info("Stopping....");
						state="STOPPING";
					}else if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("4")){
						log.info("Running");
						state="RUNNING";
					}
				}
				line=reader.readLine(); 
			} 
			
			//Start the Winservice
			if(actionToDo.trim().equalsIgnoreCase("START")){
				
				if(state.equalsIgnoreCase("STOPPED")){
					
					log.info("Starting the service...");
					try{
						
					p=Runtime.getRuntime().exec("sc \\\\"+serverIP+" start "+winserviceName+"");
					Report_Functions.ReportEventSuccess(doc, "1", "", "SC Command 'sc \\\\"+serverIP+" start "+winserviceName+"' exceuted successfully.", 3);
					
					}catch(Exception e){
						Report_Functions.ReportEventFailure(doc,  "",  "Error occured while starting the winservice. Exception is : "+ e.getMessage() +".", false);
						return false;
					}
					
					while(line!=null) { 

						if(line.trim().startsWith("STATE")){
							if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("1")){
								log.info("Stopped");
							}else if(line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("2")){
								log.info("Startting....");
							}else if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("3")){
								log.info("Stopping....");
							}else if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("4")){
								log.info("Running");
							}
						}
						line=reader.readLine(); 
					}
					
					Report_Functions.ReportEventSuccess(doc, "1", "", "Winservice '"+winserviceName+"' in Server IP '"+serverIP+"' is Started successfully", 3);
					Thread.sleep(4000);
					result=true;
					
				}else if(state.equalsIgnoreCase("STOPPING")){
					
					log.info("Winservice is Stopping state. Wait and starting again");
					Thread.sleep(5000);
					p=Runtime.getRuntime().exec("sc \\\\"+serverIP+" start "+winserviceName+"");
					
					while(line!=null) { 

						if(line.trim().startsWith("STATE")){
							if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("1")){
								log.info("Stopped");
								state="STOPPED";
							}else if(line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("2")){
								log.info("Startting....");
								state="STARTING";
							}else if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("3")){
								log.info("Stopping....");
								state="STOPPING";
							}else if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("4")){
								log.info("Running");
								state="RUNNING";
							}
						}
						line=reader.readLine(); 
					}
					
					Report_Functions.ReportEventSuccess(doc, "1", "", "Winservice '"+winserviceName+"' in Server IP '"+serverIP+"' is Started successfully", 3);
					Thread.sleep(4000);
					result=true;
					
				}else if(state.equalsIgnoreCase("STARTING")){
					
					log.info("Winservice is starting state. Cannot push start command again");
					Report_Functions.ReportEventSuccess(doc, "1", "", "Winservice '"+winserviceName+"' in Server IP '"+serverIP+"' is already in Starting State", 3);
					result=true;

				}else if(state.equalsIgnoreCase("RUNNING")){
					
					log.info("Winservice is already in Running state. Cannot push start command again");
					Report_Functions.ReportEventSuccess(doc, "1", "", "Winservice '"+winserviceName+"' in Server IP '"+serverIP+"' is already in Running State", 3);
					result=true;

				}
				
				//Start the Winservice
			}else if(actionToDo.trim().equalsIgnoreCase("STOP")){
				
				if(state.equalsIgnoreCase("RUNNING")){
					
					log.info("Stopping the service...");
					
					try{
						
						p=Runtime.getRuntime().exec("sc \\\\"+serverIP+" stop "+winserviceName+"");
						Report_Functions.ReportEventSuccess(doc, "1", "", "SC Command 'sc \\\\"+serverIP+" stop "+winserviceName+"' exceuted successfully.", 3);
						
						}catch(Exception e){
							Report_Functions.ReportEventFailure(doc,  "",  "Error occured while stopping the winservice. Exception is : "+ e.getMessage() +".", false);
							return false;
						}
					
					
					while(line!=null) { 
						if(line.trim().startsWith("STATE")){
							if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("1")){
								log.info("Stopped");
							}else if(line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("2")){
								log.info("Startting....");
							}else if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("3")){
								log.info("Stopping....");
							}else if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("4")){
								log.info("Running");
							}
						}
						line=reader.readLine(); 
					}
					
					Report_Functions.ReportEventSuccess(doc, "1", "", "Winservice '"+winserviceName+"' in Server IP '"+serverIP+"' is Stopped successfully", 3);
					Thread.sleep(4000);
					result=true;
					
				}else if(state.equalsIgnoreCase("STARTING")){
					
					log.info("Winservice is Starting state. Wait and stopping the service");
					Thread.sleep(3000);
					p=Runtime.getRuntime().exec("sc \\\\"+serverIP+" stop "+winserviceName+"");
					
					while(line!=null) { 

						if(line.trim().startsWith("STATE")){
							if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("1")){
								log.info("Stopped");
							}else if(line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("2")){
								log.info("Startting....");
							}else if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("3")){
								log.info("Stopping....");
							}else if (line.trim().substring(line.trim().indexOf(":")+1,line.trim().indexOf(":")+4).trim().equals("4")){
								log.info("Running");
							}
						}
						line=reader.readLine(); 
					}
					
					Report_Functions.ReportEventSuccess(doc, "1", "", "Winservice '"+winserviceName+"' in Server IP '"+serverIP+"' is Stopped successfully", 3);
					Thread.sleep(4000);
					result=true;
					
				}else if(state.equalsIgnoreCase("STOPPING")){
					
					log.info("Winservice is Stopping state. Cannot push Stop command again");
					Report_Functions.ReportEventSuccess(doc, "1", "", "Winservice '"+winserviceName+"' in Server IP '"+serverIP+"' is already in Stopping State.", 3);
					
					result=true;

				}else if(state.equalsIgnoreCase("STOPPED")){
					
					log.info("Winservice is already in Stopped state. Cannot push Stop command again");
					Report_Functions.ReportEventSuccess(doc, "1", "", "Winservice '"+winserviceName+"' in Server IP '"+serverIP+"' is already in Stooped State.", 3);
					result=true;

				}
			}

		} catch(Exception e) { 
			result=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBUpdate",  "Error occured in Start-Stop Winservice function. Error description is : "+ e.getMessage() +".", false);
			log.info("Error : " + e);
		}
		return result;
	}

	public static String dateFormatter(String format, String fileType)throws Exception  {

		String expectedDate=null;

		try{
			if(format.trim().equalsIgnoreCase("ddMMyyyy") || (format.trim().equalsIgnoreCase("dMyyyy")) ){

				DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
				Date date = new Date();

				dateFormat = new SimpleDateFormat("dd");
				String checkDate=dateFormat.format(date);
				int checkDateInt=Integer.parseInt(checkDate);
				expectedDate=String.valueOf(checkDateInt);

				dateFormat = new SimpleDateFormat("MM");
				String checkMonth=dateFormat.format(date);
				int checkDMonthInt=Integer.parseInt(checkMonth);
				expectedDate=expectedDate+String.valueOf(checkDMonthInt);

				dateFormat = new SimpleDateFormat("yyyy");
				String checkYear=dateFormat.format(date);
				expectedDate=expectedDate+checkYear+fileType;

				log.info("Needed Date is : "+expectedDate);
			}

		} catch(Exception e) { 
			log.info("Error in date formater : " + e);
		}
		return expectedDate;
	}
	
	/**
	 * 
	 * @Objective <b>To start the Winservice</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>1-SEP-16</b>
	 */

	public static boolean WaitUntilPatternAppearsInLog(String filePath,String fileNameValue,String strLineContains,String strPatterToCheck,String envVariableName ,String strdateFormat,int strExecEventFlag)throws Exception  {

		boolean result= false;
		String path = null;
		File[] listOfFile = null;
		String fileName = null;
		String fileNameFromExcel=null;
		Scanner in = null;
		boolean found=false;
		String lineContains=null;
		String patterToCheck=null;
		String dateFormat=null;
		String envVariable=null;

		try{
			if(strExecEventFlag == 1){
				path = Param.getProperty(RetrieveTestDataValue("ReadlogFiles", filePath, strExecEventFlag));
				fileNameFromExcel= RetrieveTestDataValue("ReadlogFiles", fileNameValue, strExecEventFlag);
				lineContains= RetrieveTestDataValue("ReadlogFiles", strLineContains, strExecEventFlag);
				patterToCheck= RetrieveTestDataValue("ReadlogFiles", strPatterToCheck, strExecEventFlag);
				dateFormat= RetrieveTestDataValue("ReadlogFiles", strdateFormat, strExecEventFlag);
				envVariable= RetrieveTestDataValue("ReadlogFiles", envVariableName, strExecEventFlag);
			}

			if(path==null ||fileNameFromExcel==null ||lineContains==null||patterToCheck==null||dateFormat==null){
				Report_Functions.ReportEventFailure(doc,  "DeletelogFiles",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			if(fileNameFromExcel.trim().equalsIgnoreCase("DateFormat"))
				fileNameFromExcel=dateFormatter(dateFormat,".txt");

			log.info("File name to search is : "+fileNameFromExcel);

			File directory = new File("//\\" +path);
			

			boolean fileexist=false;
			int fileAppeartime=0;

			while(fileAppeartime<30){
				Thread.sleep(1000);
				listOfFile = directory.listFiles();
				if(listOfFile.length != 0){
					log.info("Directory has files");
					fileexist=true;
					break;

				}else{
					log.info("No File is available in directory. looping again with 60 secs");
					//Report_Functions.ReportEventFailure(doc,"","No File is not available in directory" , false);
					//return false;
				}

				log.info("No File is available in directory with 60 secs");
				
				fileAppeartime++;
			}

			if(fileexist){
				log.info("Files avialble in the directiory");

			}else{
				log.info("No File is available in directory for 60secs");
				Report_Functions.ReportEventFailure(doc,"","No File is not available in directory" , false);
				return false;
			}

			boolean foundstatus=false;
			int time=0;
			
			while(time<30){
				Thread.sleep(1000);	
				listOfFile = directory.listFiles();
				for(int i = 0; i<listOfFile.length; i++){
					log.info("i is : "+i);
					
					if(listOfFile[i].isFile()){
						fileName = listOfFile[i].getName();
						log.info("Found a file and name is : "+fileName);
						
						if(fileName.equals(fileNameFromExcel)){
							log.info("FileName exact match : "+fileName);
							fileName=fileNameFromExcel;
							foundstatus=true;
							break;
						}
					}
				}
				
				if(foundstatus){
					log.info("FileName exact match is found. Braking the loop");
					break;
				}else{
					log.info("FileName not found in path. Continue the loop");
				}
				
				time++;
			}

			if(fileName == null){
				Report_Functions.ReportEventFailure(doc,"","'"+fileNameFromExcel+"' file is not available in the directory" , false);
				log.info("Search File is not available in directory");
				return false;
			}

			if(!(fileName.equals(fileNameFromExcel))){
				Report_Functions.ReportEventFailure(doc,"","'"+fileNameFromExcel+"' file is not available in the directory" , false);
				log.info("Search File is not available in directory");
				return false;
			}

			File file =new File("//\\" +path +fileName);
			log.info(file.getAbsolutePath()+" || "+file.getName());

			int i=0;
			while(i<60){
				Thread.sleep(1000);
				
				try {
					in = new Scanner(file);
					
					while(in.hasNext())	{
						
						String line=in.nextLine();
						if(line.contains(lineContains)){
							log.info("Line contains Match found. Line -> "+line);
							Pattern p = Pattern.compile(patterToCheck);
							Matcher m = p.matcher(line);

							while (m.find()){
								found=true;
								log.info("Pattern Match found in the Line!");
								log.info("Match is : "+m.group(0));
								Report_Functions.ReportEventSuccess(doc, "1", "","Log containing the word '"+lineContains+"' has the Matched Expected Pattern '"+m.group(0) +"'", 3);
								if(!(envVariable.trim().equalsIgnoreCase("NA"))){
									log.info("Storing the Pattern Matched in the Env Variable '"+envVariable+"'");
									Runtimevalue.setProperty(envVariable, m.group(0));
									Report_Functions.ReportEventSuccess(doc, "1", "", "The Dynamic Value '"+m.group(0)+"' is successfully stored in the Runtime Varaible '"+envVariable+"'.", 3);
								}
								break;
							}

							if(found){
								log.info("Pattern Match found breaking the HasNext Loop");
								break;
							}else{
								log.info("Pattern Match not found. Checking teh next line that contains expected word");
							}
						}
					}

					if(found){
						log.info("Match found, Breaking the Time Loop");
						result=true;
						break;
					}else{
						log.info("Match not found. Continue the Loop...");
					}

				} catch (FileNotFoundException e) {
					log.info("FileNotFoundException occured match founder... :"+e);
					//e.printStackTrace();
				}catch (Exception e) {
					log.info("Excption occured match founder... : "+e);
					Report_Functions.ReportEventFailure(doc,  "",  "Error occured while finding the pattern from Winservice Log. Error description is : "+ e.getMessage() +".", false);
					//e.printStackTrace();
				}
				
				i++;
			}

			if(result){
				Report_Functions.ReportEventSuccess(doc, "1", "", "Log file '"+fileNameFromExcel+"' from Path '"+path+"' contains the Expected Log Pattern '"+patterToCheck+"'", 3);
			}else{
				Report_Functions.ReportEventFailure(doc,  "","Log file '"+fileNameFromExcel+"' from Path '"+path+"' does not contains the Expected Log Pattern '"+patterToCheck+"' within 60 secs", false);
			}

		} catch(Exception e) { 
			result=false;
			Report_Functions.ReportEventFailure(doc,  "",  "Error occured in the WinserviceWaitUntilPatternAppears. Error description is : "+ e.getMessage() +".", false);
			log.info("Error : " + e);
		}
		return result;
	}

	/**
	 * 
	 * @Objective <b>Verifies to update the data for RRBS in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>31-AUG-16</b>
	 */

	public static boolean RRBSDBCommonPreCondition(String actionName,String sqltablename, String strsqlcolumnname,String strsqlcolumnvalue,String strsqlcondition,int strExecEventFlag)throws Exception  {

		boolean result= false;
		String action=null;
		
		try {
			
			if (strExecEventFlag==1){
				action=RetrieveTestDataValue("",actionName,strExecEventFlag);
			}
			if(action==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			
			if(action.trim().equalsIgnoreCase("Update")){
				result=RRBSDBUpdate(sqltablename, strsqlcolumnname, strsqlcolumnvalue, strsqlcondition, strExecEventFlag);
			}else if(action.trim().equalsIgnoreCase("Delete")){
				result=RRBSDBDelete(sqltablename, strsqlcondition, strExecEventFlag);
			}else{
				log.info("Invalid Action item from Excel");
				Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet - "+action, false);
			}
	
		} catch (Exception e) { 
			result=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBUpdate Error : " + e);
		}
		return result;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies to update the data for RRBS in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>1-SEP-16</b>
	 */

	public static boolean EXIBSDBCommonPreCondition(String actionName,String sqltablename, String strsqlcolumnname,String strsqlcolumnvalue,String strsqlcondition,int strExecEventFlag)throws Exception  {

		boolean result= false;
		String action=null;
		
		try {
			
			if (strExecEventFlag==1){
				action=RetrieveTestDataValue("",actionName,strExecEventFlag);
			}
			if(action==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			
			if(action.trim().equalsIgnoreCase("Update")){
				result=EXIBSDBUpdate(sqltablename, strsqlcolumnname, strsqlcolumnvalue, strsqlcondition, strExecEventFlag);
			}else if(action.trim().equalsIgnoreCase("Delete")){
				result=EXIBSDBDelete(sqltablename, strsqlcondition, strExecEventFlag);
			}else{
				log.info("Invalid Action item from Excel");
				Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet - "+action, false);
			}
	
		} catch (Exception e) { 
			result=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBUpdate Error : " + e);
		}
		return result;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies to update the data for RRBS in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>1-Sep-16</b>
	 */

	public static boolean DBCommonPreCondition(String dbType, String actionName,String sqltablename, String strsqlcolumnname,String strsqlcolumnvalue,String strsqlcondition,int strExecEventFlag)throws Exception  {

		boolean status= false;
		String action=null;
		String Database=null;
		try {

			if (strExecEventFlag==1){
				Database=RetrieveTestDataValue("",dbType,strExecEventFlag);
				action=RetrieveTestDataValue("",actionName,strExecEventFlag);
			}
			if(action==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}

			if(Database.trim().equalsIgnoreCase("SQL")){

				if(action.trim().equalsIgnoreCase("Update")){
					status=SQLDBUpdate(sqltablename, strsqlcolumnname, strsqlcolumnvalue, strsqlcondition, strExecEventFlag);
				}else if(action.trim().equalsIgnoreCase("Delete")){
					status=SQLDBDelete(sqltablename, strsqlcondition, strExecEventFlag);
				}else{
					log.info("Invalid Action item from Excel");
					Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet - "+action, false);
					status=false;
				}

			}else if(Database.trim().equalsIgnoreCase("RRBS")) {

				if(action.trim().equalsIgnoreCase("Update")){
					status=RRBSDBUpdate(sqltablename, strsqlcolumnname, strsqlcolumnvalue, strsqlcondition, strExecEventFlag);
				}else if(action.trim().equalsIgnoreCase("Delete")){
					status=RRBSDBDelete(sqltablename, strsqlcondition, strExecEventFlag);
				}else{
					log.info("Invalid Action item from Excel");
					Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet - "+action, false);
					status=false;
				}

			}else{
				log.info("Invalid Action item from Excel");
				Report_Functions.ReportEventFailure(doc,  "",  "Invalid Database Type described in Excel sheet - "+action, false);
				status=false;
			}

		} catch (Exception e) { 
			status=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBUpdate Error : " + e);
		}
		return status;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies to update the data for RRBS in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>1-Sep-16</b>
	 */

	public static boolean DBCommonPostCondition(String dbType, String strformatType,String sqltablename, String strsqlcolumnname,String strsqlcondition,String strExpectedvalue,String Days_to_add,int strExecEventFlag)throws Exception{
		boolean status= false;
		String formatType=null;
		String Database=null;
		try {

			if (strExecEventFlag==1){
				Database=RetrieveTestDataValue("",dbType,strExecEventFlag);
				formatType=RetrieveTestDataValue("",strformatType,strExecEventFlag);
			}
			if(Database==null || formatType==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}

			if(Database.trim().equalsIgnoreCase("SQL")){

				if(formatType.trim().equalsIgnoreCase("Normal")){
					status= SQLDBSelect(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strExecEventFlag);
				}else if(formatType.trim().equalsIgnoreCase("Date")){
					status= SQLDBDateFormatCompare(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strExecEventFlag);
				}else if(formatType.trim().equalsIgnoreCase("FutureDate")){
					status= SQLDBFutureDateCompare(sqltablename, strsqlcolumnname, strsqlcondition, "dd/MM/yyyy", Days_to_add, strExecEventFlag);
				}else if(formatType.trim().equalsIgnoreCase("EnvVariable")){
					status= SQLDBSelectFromEnv(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strExecEventFlag);
				}else{
					log.info("Invalid Action item from Excel");
					Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet for SQL - "+formatType, false);
				}

			}else if(Database.trim().equalsIgnoreCase("RRBS")) {

				if(formatType.trim().equalsIgnoreCase("Normal")){
					status= RRBSDBSelect(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strExecEventFlag);
				}else if(formatType.trim().equalsIgnoreCase("Date")){
					status= RRBSDBDateCompare(sqltablename, strsqlcolumnname, strsqlcondition, "dd/MM/yyyy", strExecEventFlag);
				}else if(formatType.trim().equalsIgnoreCase("FutureDate")){
					status= RRBSDBFutureDateCompare(sqltablename, strsqlcolumnname, strsqlcondition, "dd/MM/yyyy", Days_to_add, strExecEventFlag);
				}else{
					log.info("Invalid Action item from Excel");
					Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet for RRBS - "+formatType, false);
				}

			}else{
				log.info("Invalid Action item from Excel");
				Report_Functions.ReportEventFailure(doc,  "",  "Invalid Database Type described in Excel sheet - "+Database, false);
				status=false;
			}

		} catch (Exception e) { 
			status=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBUpdate Error : " + e);
		}
		return status;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies to check date comparision in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param Date_Format
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean SQLDBFutureDateCompare(String sqltablename, String strsqlcolumnname,String strsqlcondition,String Date_Format,String Days_to_add,int strExecEventFlag)throws Exception  {
		boolean SQLDBDateCompare= false;
		String query = null;  
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Expected_value = null;
		String Actual_Value = null;
		String Current_Date=null;
		String expected_db_Date = null;
		String daystoadd = null;
		

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBDateCompare",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBDateCompare",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBDateCompare",strsqlcondition,strExecEventFlag);
				daystoadd = RetrieveTestDataValue("RRBSDBFutureDateCompare", Days_to_add, strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}
			
			int Add_Days = Integer.parseInt(daystoadd);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			
			Calendar expdate = Calendar.getInstance();
			expdate.setTime(date);
			expdate.add(Calendar.DATE, Add_Days);
			Current_Date = dateFormat.format(expdate.getTime());

			Expected_value = Current_Date.trim();

			// Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			rs_SQLServer= stmt.executeQuery(query);

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBDateCompare Error : " + e);
			SQLDBDateCompare=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1);
			String db_Date = Actual_Value.split(" ")[0];

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date dateToChange = dateFormat.parse(db_Date);

			SimpleDateFormat finalDateFormat = new SimpleDateFormat(Date_Format);
			expected_db_Date = finalDateFormat.format(dateToChange);	

		} catch (Exception NullPointerException) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "No Record found for this query: "+query, true);
			log.info("SQLDBDateCompare Error : ");
			SQLDBDateCompare=false;
		}

		try{

			if(!rs_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(expected_db_Date.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDateCompare", "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' matches with the expected date : '"+Expected_value+"' ", 2);
					SQLDBDateCompare=true;
				}else if(!(expected_db_Date.equalsIgnoreCase(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' does not match with the expected date : '"+Expected_value+"' ", true);
					SQLDBDateCompare=false;
				}
			}else if(rs_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equalsIgnoreCase("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDateCompare", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					SQLDBDateCompare=true;
				}else if(!(Expected_value.equalsIgnoreCase("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					SQLDBDateCompare=false;
				}
			}

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Error occured while comparing the dates in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBDateCompare Error : " + e);
			SQLDBDateCompare=false;
		}
		return SQLDBDateCompare;
	}
	
	/**
	 * 
	 * @Objective <b>This method is to delete the files in log folder, after to verify the confrmation message in log</b>
	 * @param filePath
	 * @param startsWith
	 * @param endsWith
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>6-May-16</b>
	 * 
	 */

	public static boolean deleteAllFileInPath(String filePath, int strExecEventFlag) throws Exception{

		boolean functionStatus = true;

		String path = null;
		int flag = 0;

		try{

			if(strExecEventFlag == 1){
				path = Param.getProperty(RetrieveTestDataValue("DeletelogFiles", filePath, strExecEventFlag));
			}

			if(path==null ){
				Report_Functions.ReportEventFailure(doc,  "DeletelogFiles",  "Required details are not provided in test data sheet.", false);
				return false;
			}


			File directory = new File("//\\" +path);


			for(File listOfFiles : directory.listFiles()){
				if(true){
					listOfFiles.delete();
					flag = 1;
				}
			}

		}catch(Exception e){
			log.info("Exception occurs in deleteFiles function "+e.getMessage());
			Report_Functions.ReportEventFailure(doc,"","Error occurred, while deleting the file in the Path", false);
			functionStatus = false;
		}

		if(flag == 1){
			Report_Functions.ReportEventSuccess(doc,"1","DeletelogFiles","All Files in the Path '"+path+"' are deleted sucessfully",3);
		} else {
			Report_Functions.ReportEventSuccess(doc,"1","DeletelogFiles","No file are available in the given Path '"+path+"' to delete.",3);
		}

		return functionStatus;
	}
	
	/**
	 * 
	 * @Objective <b>Verifying the column value in RRBS DB</b>
	 * @author <b>LAKSHMAN</b>
	 * @since <b>6-SEP-2016</b>
	 */

	public static boolean RRBSValueStoreInEnvVarFromEnvVarCondition(String rrbstablename, String rrbscolumnname, String rrbsconditionColumn,String strValueInVariable, String strEnvVariableToSave, int strExecEventFlag)throws Exception  {
		boolean RetrieveRRBSValueStoresInEnvVar = false;
		String tablename = null;
		String conditionCol = null;
		String columnname = null;
		String actualvalue = null;
		String envVariable="";
		String SQL_condition_value = "";	
		String valueInVariableName=null;
		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("RetrieveRRBSValueStoresInEnvVar",rrbstablename,strExecEventFlag);
				conditionCol=RetrieveTestDataValue("RetrieveRRBSValueStoresInEnvVar",rrbsconditionColumn,strExecEventFlag);
				columnname=RetrieveTestDataValue("RetrieveRRBSValueStoresInEnvVar",rrbscolumnname,strExecEventFlag);
				envVariable=RetrieveTestDataValue("RetrieveRRBSValueStoresInEnvVar",strEnvVariableToSave,strExecEventFlag);
				valueInVariableName=RetrieveTestDataValue("RetrieveRRBSValueStoresInEnvVar",strValueInVariable,strExecEventFlag);
				
			}
			
			log.info("tablename is : "+tablename);
			log.info("conditionCol is : "+conditionCol);
			
			log.info("columnname is : "+columnname);
			log.info("envVariable is : "+envVariable);
			log.info("valueInVariableName is : "+valueInVariableName);

			if(tablename==null || conditionCol==null || columnname==null || envVariable==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			
			SQL_condition_value = Runtimevalue.getProperty(valueInVariableName);
			
			log.info("SQL_condition_value is : "+SQL_condition_value);
			
			if(SQL_condition_value == null){
				SQL_condition_value="";
			}

		}catch(Exception e){
			log.info("Errror log in reading file is "+e);
			throw e;
		}
		//Query to Execute      
		String query = "select "+ columnname +" from "+ tablename +" where "+ conditionCol+"='"+SQL_condition_value+"'";
		log.info("Query is: "+query);
		try {
			ResultSet rrbsresultset = rrbsstatement.executeQuery(query);
			while (rrbsresultset.next()){
				actualvalue = rrbsresultset.getString(1);
			}
			
			if(actualvalue==null){
				actualvalue="";
			}

			Runtimevalue.setProperty(envVariable, actualvalue);

			RetrieveRRBSValueStoresInEnvVar = true;
			Report_Functions.ReportEventSuccess(doc, "1", "RetrieveRRBSValueStoresInEnvVar", "The value : '"+ actualvalue + "' is stored in the environment variable :"+ envVariable +".", 2);

		} catch (Exception e) { 
			RetrieveRRBSValueStoresInEnvVar = false;
			Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Error occured while executing the RRBS query.Error description is : "+ e.getMessage() +".", false);
			log.info("RetrieveRRBSValueStoresInEnvVar Error : " + e);
		}
		return RetrieveRRBSValueStoresInEnvVar;
	}
	
	/**
	 * 
	 * @Objective <b>Deleting the record from RRBS DB</b>
	 * @param rrbstablename
	 * @param rrbscondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-SEP-15</b>
	 */

	public static boolean RRBSDBDeleteCondtionEnv(String rrbstablename, String rrbsconditioncol,String envValueInVariable, int strExecEventFlag)throws Exception  {
		boolean elementStatus= false;
		String tablename = null;
		String conditionCol = null;
		String envVariableName=null;
		String envValue=null;

		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("RRBSDBDelete",rrbstablename,strExecEventFlag);
				conditionCol=RetrieveTestDataValue("RRBSDBDelete",rrbsconditioncol,strExecEventFlag);
				envVariableName=RetrieveTestDataValue("RRBSDBDelete",envValueInVariable,strExecEventFlag);
			}

			if(tablename==null || conditionCol==null || envVariableName==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBDelete",  "Required details are not provided in test data sheet.", false);
				return false;
			}
			
			envValue=Runtimevalue.getProperty(envVariableName);
			

		}catch(Exception e){
			log.info("Errror log in reading file is "+e);
			throw e;
		}
		//Query to Execute      
		String check = "select * from "+tablename +" where "+conditionCol+"='"+envValue+"'";
		String query = "Delete from "+ tablename +" where "+ conditionCol+"='"+envValue+"'";
		log.info("query is: "+query);
		
		ResultSet rs = null;

		try {

			rs = rrbsstatement.executeQuery(check);

			int temp = 0;
			while(rs.next()){

				temp++;

			}

			if(temp > 0){

				rrbsstatement.execute(query); 
				elementStatus=true;
				Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBDelete", "The RRBS Query : "+ query + " executed successfully", 2);

			}

			//If rows not available FALSE will be returned so delete operation doesn't works here

			else if(temp < 1){

				Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBDelete", "The RRBS Query : "+ query + " records are not availbale in DB", 2);
				elementStatus = true;

			}

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBDelete",  "Error occured while executing the RRBS query.Error description is : "+ e.getMessage() +".", false);
			log.info("RRBSDBDelete Error : " + e);
		}
		return elementStatus;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies to update the data for RRBS in SQL DB</b>
	 * @author <b>Lakshman</b>
	 * @since <b>6-SEP-16</b>
	 */

	public static boolean RRBSDBCommonSelectFromEnv(String strType,String sqltablename, String strsqlcolumnname,String strsqlconditionCol,String evnNameWhichHasValue, String strsqlcolumnvalue,String Date_Format,String Days_to_add,int strExecEventFlag)throws Exception  {

		boolean result= false;
		String type=null;
		
		try {
			
			if (strExecEventFlag==1){
				type=RetrieveTestDataValue("",strType,strExecEventFlag);
			}
			
			if(type==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			
			
			
			if(type.trim().equalsIgnoreCase("Normal")){
				result=RRBSDBSelectFromEnvCondition(sqltablename, strsqlcolumnname, strsqlconditionCol, evnNameWhichHasValue, strsqlcolumnvalue,strExecEventFlag);
			}else if(type.trim().equalsIgnoreCase("Date")){
				result=RRBSDBDateCompareFromEnvCondition(sqltablename, strsqlcolumnname, strsqlconditionCol,evnNameWhichHasValue, Date_Format, strExecEventFlag);
			}else if(type.trim().equalsIgnoreCase("FutureDate")){
				result=RRBSDBFutureDateCompareFromEnvCondition(sqltablename, strsqlcolumnname, strsqlconditionCol,evnNameWhichHasValue, Date_Format, Days_to_add, strExecEventFlag);
			}else{
				log.info("Invalid Action item from Excel");
				Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet - "+type, false);
			}
			
			
	
		} catch (Exception e) { 
			result=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBUpdate Error : " + e);
		}
		return result;
	}
	
	/**
	 * 
	 * @Objective <b>Verify to compare the dates in RRBS DB</b>
	 * @author <b>Lakshman</b>
	 * @since <b>06-SEP-16</b>
	 */
	
	public static boolean RRBSDBSelectFromEnvCondition(String sqltablename, String strsqlcolumnname,String strsqlconditionCol,String envVariableColumnName,String strExpectedvalue,int strExecEventFlag)throws Exception  {
		boolean RRBSDBSelect= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		//String Expected_value = null;		
		String Expected_value = "";
		String Actual_Value = null;
		String envVariable=null;
		String evnCondtion=null;
		
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("RRBSDBSelect",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("RRBSDBSelect",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("RRBSDBSelect",strsqlconditionCol,strExecEventFlag);
				Expected_value=RetrieveTestDataValue("RRBSDBSelect",strExpectedvalue,strExecEventFlag);
				envVariable=RetrieveTestDataValue("RRBSDBSelect",envVariableColumnName,strExecEventFlag);
				evnCondtion=Runtimevalue.getProperty(envVariable);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null||envVariable==null||evnCondtion==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBSelect",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"='"+evnCondtion+"'";

			rrbsresultset = rrbsstatement.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "RRBSDBSelect",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBSelect Error : " + e);
			RRBSDBSelect=false;
		}

		try{
			rrbsresultset.next();
			Actual_Value = rrbsresultset.getString(1).trim();

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "RRBSDBSelect",  "No Record found for this query: "+query, true);
			log.info("RRBSDBSelect Error : ");
			RRBSDBSelect=false;
		}

		try{
			if(!rrbsresultset.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","RRBSDBSelect", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+evnCondtion+"' matches the expected value : '"+Expected_value+"'", 2);
					RRBSDBSelect=true;
				}else if(!(Actual_Value.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBSelect",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+evnCondtion+"' does not match the expected value : '"+Expected_value+"'", true);
					RRBSDBSelect=false;
				}
			}

			else if(rrbsresultset.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBSelect", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+evnCondtion+"' matches with the expected value :'"+Expected_value+"'", 2);
					RRBSDBSelect=true;
				}else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBSelect",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+evnCondtion+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					RRBSDBSelect=false;
				}
			}
		}catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "RRBSDBSelect",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBSelect Error : " + e);
			RRBSDBSelect=false;
		}
		return RRBSDBSelect;
	}
	
	/**
	 * 
	 * @Objective <b>Verify to compare the dates in RRBS DB</b>
	 * @author <b>Lakshman</b>
	 * @since <b>06-SEP-16</b>
	 */

	public static boolean RRBSDBDateCompareFromEnvCondition(String sqltablename, String strsqlcolumnname,String strsqlcondition,String envVariableColumnName,String Date_Format,int strExecEventFlag)throws Exception  {

		boolean RRBSDBDateCompare= false;
		String query = null;  
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Expected_value = null;
		String Actual_Value = null;
		String Current_Date=null;
		String expected_db_Date = null;
		String envVariable=null;
		String evnCondtion=null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("RRBSDBDateCompare",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("RRBSDBDateCompare",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("RRBSDBDateCompare",strsqlcondition,strExecEventFlag);
				envVariable=RetrieveTestDataValue("RRBSDBSelect",envVariableColumnName,strExecEventFlag);
				evnCondtion=Runtimevalue.getProperty(envVariable);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null || envVariable==null||evnCondtion==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();

			Current_Date = dateFormat.format(date);

			Expected_value = Current_Date.trim();

			// Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"='"+evnCondtion+"'";

			rrbsresultset = rrbsstatement.executeQuery(query);

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBDateCompare Error : " + e);
			RRBSDBDateCompare=false;
		}

		try{

			rrbsresultset.next();

			Actual_Value = rrbsresultset.getString(1);
			String db_Date = Actual_Value.split(" ")[0];

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date dateToChange = dateFormat.parse(db_Date);			

			SimpleDateFormat finalDateFormat = new SimpleDateFormat(Date_Format);
			expected_db_Date = finalDateFormat.format(dateToChange);


		} catch (Exception ne) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "No Record found for this query: "+query, true);
			log.info("RRBSDBDateCompare Error : "+ne);
			RRBSDBDateCompare=false;
		}

		try{

			if(!rrbsresultset.wasNull()){            // If some value is present in the fired Query

				if(expected_db_Date.equals(Expected_value)){

					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBDateCompare", "The Actual date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+evnCondtion+"' matches with the expected date : '"+Expected_value+"' ", 2);
					RRBSDBDateCompare=true;

				}else if(!(expected_db_Date.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "The Actual date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+evnCondtion+"' does not match with the expected date : '"+Expected_value+"' ", true);
					RRBSDBDateCompare=false;
				}
			}
			else if(rrbsresultset.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBDateCompare", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+evnCondtion+"' matches with the expected value :'"+Expected_value+"'", 2);
					RRBSDBDateCompare=true;
				}

				else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+evnCondtion+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					RRBSDBDateCompare=false;
				}
			}

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "Error occured while comparing the dates in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBDateCompare Error : " + e);
			RRBSDBDateCompare=false;
		}

		return RRBSDBDateCompare;
	}
	
	/**
	 * 
	 * @Objective <b>Verify to compare the dates in RRBS DB</b>
	 * @author <b>LAKSHMAN</b>
	 * @since <b>24-Aug-2016</b>
	 */

	public static boolean RRBSDBFutureDateCompareFromEnvCondition(String sqltablename, String strsqlcolumnname,String strsqlcondition,String envVariableColumnName,String Date_Format,String Days_to_add,int strExecEventFlag)throws Exception  {

		boolean RRBSDBFutureDateCompare= false;
		String query = null;  
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Expected_value = null;
		String Actual_Value = null;
		String Current_Date=null;
		String expected_db_Date = null;
		String daystoadd = null;

		String envVariable=null;
		String evnCondtion=null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("RRBSDBFutureDateCompare",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("RRBSDBFutureDateCompare",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("RRBSDBFutureDateCompare",strsqlcondition,strExecEventFlag);
				daystoadd = RetrieveTestDataValue("RRBSDBFutureDateCompare", Days_to_add, strExecEventFlag);
				envVariable=RetrieveTestDataValue("RRBSDBSelect",envVariableColumnName,strExecEventFlag);
				evnCondtion=Runtimevalue.getProperty(envVariable);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null|| envVariable==null||evnCondtion==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBFutureDateCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			int Add_Days = Integer.parseInt(daystoadd);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();

			Calendar expdate = Calendar.getInstance();
			expdate.setTime(date);
			expdate.add(Calendar.DATE, Add_Days);
			Current_Date = dateFormat.format(expdate.getTime());


			Expected_value = Current_Date.trim();

			// Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"='"+evnCondtion+"'";

			rrbsresultset = rrbsstatement.executeQuery(query);

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "RRBSDBFutureDateCompare",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBFutureDateCompare Error : " + e);
			RRBSDBFutureDateCompare=false;
		}

		try{

			rrbsresultset.next();

			Actual_Value = rrbsresultset.getString(1);
			String db_Date = Actual_Value.split(" ")[0];

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date dateToChange = dateFormat.parse(db_Date);			

			SimpleDateFormat finalDateFormat = new SimpleDateFormat(Date_Format);
			expected_db_Date = finalDateFormat.format(dateToChange);


		} catch (Exception ne) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "RRBSDBFutureDateCompare",  "No Record found for this query: "+query, true);
			log.info("RRBSDBFutureDateCompare Error : "+ne);
			RRBSDBFutureDateCompare=false;
		}

		try{

			if(!rrbsresultset.wasNull()){            // If some value is present in the fired Query

				if(expected_db_Date.equals(Expected_value)){

					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBFutureDateCompare", "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+evnCondtion+"' matches with the expected date : '"+Expected_value+"' ", 2);
					RRBSDBFutureDateCompare=true;

				}else if(!(expected_db_Date.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBFutureDateCompare",  "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+evnCondtion+"' does not match with the expected date : '"+Expected_value+"' ", true);
					RRBSDBFutureDateCompare=false;
				}
			}
			else if(rrbsresultset.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBFutureDateCompare", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+evnCondtion+"' matches with the expected value :'"+Expected_value+"'", 2);
					RRBSDBFutureDateCompare=true;
				}

				else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBFutureDateCompare",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+evnCondtion+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					RRBSDBFutureDateCompare=false;
				}
			}

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "RRBSDBFutureDateCompare",  "Error occured while comparing the dates in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBDateCompare Error : " + e);
			RRBSDBFutureDateCompare=false;
		}

		return RRBSDBFutureDateCompare;
	}
	
	/**
	 * 
	 * @Objective <b>Verify to compare the dates in RRBS DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param Date_Format
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>08-SEP-16</b>
	 */

	public static boolean RRBSStoreValueInEnvVar(String rrbstablename, String rrbscolumnname, String rrbscondition, String strenvvar, int strExecEventFlag)throws Exception  {
		boolean RetrieveRRBSValueStoresInEnvVar = false;
		String tablename = null;
		String condition = null;
		String columnname = null;
		String actualvalue = null;
		String envVariableName="";
		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("RetrieveRRBSValueStoresInEnvVar",rrbstablename,strExecEventFlag);
				condition=RetrieveTestDataValue("RetrieveRRBSValueStoresInEnvVar",rrbscondition,strExecEventFlag);
				columnname=RetrieveTestDataValue("RetrieveRRBSValueStoresInEnvVar",rrbscolumnname,strExecEventFlag);
				envVariableName=RetrieveTestDataValue("RetrieveRRBSValueStoresInEnvVar",strenvvar,strExecEventFlag);
			}

			if(tablename==null || condition==null || columnname==null ){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			

		}catch(Exception e){
			log.info("Errror log in reading file is "+e);
			throw e;
		}
		//Query to Execute      
		String query = "select "+ columnname +" from "+ tablename +" where "+ condition;
		try {
			ResultSet rrbsresultset = rrbsstatement.executeQuery(query);
			while (rrbsresultset.next()){
				actualvalue = rrbsresultset.getString(1);
			}

			Runtimevalue.setProperty(envVariableName, actualvalue);

			RetrieveRRBSValueStoresInEnvVar = true;
			Report_Functions.ReportEventSuccess(doc, "1", "RetrieveRRBSValueStoresInEnvVar", "The value : '"+ actualvalue + "' is stored in the environment variable :"+ envVariableName +".", 2);

		} catch (Exception e) { 
			RetrieveRRBSValueStoresInEnvVar = false;
			Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Error occured while executing the RRBS query.Error description is : "+ e.getMessage() +".", false);
			log.info("RetrieveRRBSValueStoresInEnvVar Error : " + e);
		}
		return RetrieveRRBSValueStoresInEnvVar;
	}
	 
	/**
	 * 
	 * @Objective <b>This method is to select the values from the dropdown</b>
	 * @author <b>Lakshman</b>
	 * @since <b>07-Sep-16</b>
	 */

	public static boolean waitUntilListLoads(String getValueFromPOM, String strTestObject)throws Exception  {

		String strData=null;
		boolean WebListSelect=false;
		Select se=null;
		List<WebElement> options=null;
		
		try{

		int i=0;
		int listSize=0;
		boolean displayed=false;
		while(i<10){
			Thread.sleep(1000);
			
			se=new Select(selectByLocatorType(getValueFromPOM));
			options = se.getOptions();
			
			log.info("Size of Wiblist is : "+options.size());
			listSize=options.size();
			if(options.size()>1){
				log.info("List is populated");
				displayed=true;
				break;
			}else{
				log.info("List yet not populated");
			}
			
			i++;
		}
			
		if(displayed){
			Report_Functions.ReportEventSuccess(doc,"1","","The List is Loaded for the dropdown '"+strTestObject+"' successfully" ,3);
			WebListSelect=true;
		}else{
			if(listSize==1){
			Report_Functions.ReportEventSuccess(doc,"1","","The List has only one value Loaded for the dropdown '"+strTestObject+"'" ,3);
			}
			WebListSelect=true;
		}

		} catch (StaleElementReferenceException e) {
			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				return waitUntilListLoads(getValueFromPOM, strTestObject);
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","The Item '" +  strData + " was not selected from the  '"+strTestObject+"' List box " , true); 
			WebListSelect=false;
			log.info("No Element Found to select text" + e);
		}
		return WebListSelect;
	}
	
	/**
	 * 
	 * @Objective <b>Verify to compare the dates in RRBS DB</b>
	 * @author <b>Lakshman</b>
	 * @since <b>08-SEP-16</b>
	 */

	public static boolean RRBSDateAndWebElementTextCompare(String getValueFromPOM, String sqltablename, String strsqlcolumnname,String strsqlcondition,String DateFormatCol,String strTestObject,int strExecEventFlag)throws Exception  {

		boolean RRBSDBDateCompare= false;
		String query = null;  
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Actual_Value = null;
		String expected_db_Date = null;
		String valueFromWebElement=null;
		String dateFormat=null;
		String expected_Object_Date=null;


		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("RRBSDBDateCompare",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("RRBSDBDateCompare",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("RRBSDBDateCompare",strsqlcondition,strExecEventFlag);
				dateFormat=RetrieveTestDataValue("RRBSDBDateCompare",DateFormatCol,strExecEventFlag);
			}
			
			log.info("Table_name is : "+Table_name);
			log.info("Column_name is : "+Column_name);
			log.info("SQL_condition is : "+SQL_condition);
			log.info("dateFormat is : "+dateFormat);

			if(Table_name==null || Column_name==null || SQL_condition==null ||dateFormat==null ){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			try{

				valueFromWebElement = selectByLocatorType(getValueFromPOM).getText();

			} catch (Exception e){
				Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
				return false;
			}
			
			
			log.info("valueFromWebElement is : "+valueFromWebElement);
			//15-04-2017

			SimpleDateFormat sDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			Date dateToChange1 = sDateFormat.parse(valueFromWebElement);			

			SimpleDateFormat finalDateFormat1 = new SimpleDateFormat(dateFormat);
			expected_Object_Date = finalDateFormat1.format(dateToChange1);
			
			log.info("expected_Object_Date is : "+expected_Object_Date);


			// Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";

			rrbsresultset = rrbsstatement.executeQuery(query);

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBDateCompare Error : " + e);
			RRBSDBDateCompare=false;
		}

		try{

			rrbsresultset.next();

			Actual_Value = rrbsresultset.getString(1);
			String db_Date = Actual_Value.split(" ")[0];

			SimpleDateFormat SdateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date dateToChange = SdateFormat2.parse(db_Date);			

			SimpleDateFormat finalDateFormat = new SimpleDateFormat(dateFormat);
			expected_db_Date = finalDateFormat.format(dateToChange);

			log.info("expected_db_Date is : "+expected_db_Date);

		} catch (Exception ne) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "No Record found for this query: "+query, true);
			log.info("RRBSDBDateCompare Error : "+ne);
			RRBSDBDateCompare=false;
		}

		try{

			if(!rrbsresultset.wasNull()){            // If some value is present in the fired Query

				if(expected_db_Date.equals(expected_Object_Date)){

					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBDateCompare", "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' matches with the Element's date : '"+expected_Object_Date+"' ", 2);
					RRBSDBDateCompare=true;

				}else if(!(expected_db_Date.equals(expected_Object_Date))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' does not match with the Element's date : '"+expected_Object_Date+"' ", true);
					RRBSDBDateCompare=false;
				}
			}
			else if(rrbsresultset.wasNull()){        // If "NULL" value is present in the fired Query
				if(expected_Object_Date.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBDateCompare", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the Element's date :'"+expected_Object_Date+"'", 2);
					RRBSDBDateCompare=true;
				}

				else if(!(expected_Object_Date.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the Element's date : '"+expected_Object_Date+"'", false);  	 
					RRBSDBDateCompare=false;
				}
			}

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "RRBSDBDateCompare",  "Error occured while comparing the dates in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBDateCompare Error : " + e);
			RRBSDBDateCompare=false;
		}

		return RRBSDBDateCompare;
	}
	
	
	/** 
	 * @Objective <b>This method is to enter the given value from datasheet into the TextBox and compares how many character
	 *  TextBox accepted with the value from datasheet</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Value to enter into the TextBox from the datasheet<>
	 * @param strExpectedValueColumn <b>Compares this value from Excel with the value in the Element<>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>13-SEP-16</b>
	 */

	public static boolean WebElementAttributeValueCompare(String getValueFromPOM, String strTestObject,String strAttributeName,String strExpectedAttrValue,int strExecEventFlag )throws Exception  {

		boolean WebEditEnterAndCompareValue=false;
		String actualAttributeValue=null;
		String attributeName=null;
		String expectedAttributeValue=null;

		try {
			if(strExecEventFlag==1){
				attributeName=RetrieveTestDataValue("WebEditEnterText",strAttributeName,strExecEventFlag);
				expectedAttributeValue=RetrieveTestDataValue("WebEditEnterText",strExpectedAttrValue,strExecEventFlag);
			}
			
			log.info("attributeName is : "+attributeName);
			log.info("expectedAttributeValue is : "+expectedAttributeValue);

			if(attributeName==null || expectedAttributeValue==null){
				Report_Functions.ReportEventFailure(doc,  "WebElementValueCompare",  "Required details are not provided in the dataSheet.", false);
				return false;
			}
			
			actualAttributeValue = selectByLocatorType(getValueFromPOM).getAttribute(attributeName);
			log.info("actualAttributeValue is : "+actualAttributeValue);
			
			
			if((actualAttributeValue.trim()).equalsIgnoreCase(expectedAttributeValue.trim())){
				WebEditEnterAndCompareValue=true;
				Report_Functions.ReportEventSuccess(doc,"1", "", "The Actual attribute value '"+actualAttributeValue+"' of "+strTestObject+" matches with the expected value '" + actualAttributeValue + "' matches with the Expected value '" + expectedAttributeValue + "'", 2);
				
				if(strAttributeName.trim().equalsIgnoreCase("disabled")){
					Report_Functions.ReportEventSuccess(doc,"1", "", "The "+strTestObject+" is disabled", 2);	
				}
			}else{
				WebEditEnterAndCompareValue=false;
				Report_Functions.ReportEventFailure(doc,"",  "The Actual attribute value '"+actualAttributeValue+"' of "+strTestObject+" does not match with the expected value '" + actualAttributeValue + "' matches with the Expected value '" + expectedAttributeValue + "'", true);
				if(strAttributeName.trim().equalsIgnoreCase("disabled")){
					Report_Functions.ReportEventFailure(doc,"", "The "+strTestObject+" is not disabled", true);
				}
			}
			
		} catch (NullPointerException e) {
			
				WebEditEnterAndCompareValue=true;
				Report_Functions.ReportEventSuccess(doc,"1", "", "The Actual attribute value '"+actualAttributeValue+"' of "+strTestObject+" matches with the expected value '" + actualAttributeValue + "' from the Web element", 2);
			}
		catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"", "Error occured with Web element attribute value compare. Exception is "+e.getMessage(), true);
			WebEditEnterAndCompareValue=false;
			log.info("No Element Found to compare Text : " + e);
		}
		return WebEditEnterAndCompareValue;
	}
	
	/**
	 * 
	 * @Objective <b>This method is to enter the values using Java script executor</b>
	 * @param locatorType
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>13-SEP-16</b>
	 */

	public static boolean JavaScriptWebEditEnterText(String getValueFromPOM, String strTestObject, String strColumnName, int strExecEventFlag) throws Exception{

		boolean elementStatus = false;
		String elementValue = null;

		try{

			if(strExecEventFlag==1){
				elementValue = RetrieveTestDataValue("javaScriptDatePicker", strColumnName, strExecEventFlag);
			}

			if(elementValue == null){

				Report_Functions.ReportEventFailure(doc,  "javaScriptDatePicker",  "Required details are not provided in the data sheet.", false);
				return false;

			}

			//Get the locatorType from POM during runtime
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//document.getElementById("txtExpireDate").innerHTML = "10/09/2020"
			//document.getElementById("text").innerHTML="some new text"
			
			log.info("Scrip is : document.getElementById(\""+getValueFromPOM+"\").value = \""+elementValue+"\"");
			js.executeScript("document.getElementById(\""+getValueFromPOM+"\").value = \""+elementValue+"\"");
			
			Report_Functions.ReportEventSuccess (doc,"1","","The Text '"+elementValue+"' is entered for the Element "+strTestObject+" using JavaScript",2);	
			elementStatus = true;

		}catch(Exception e){

			log.info("Exception occurred in web table radio button :"+e.getMessage());
			e.printStackTrace();
			Report_Functions.ReportEventFailure(doc,"","The Text '"+elementValue+"' is not entered for "+strTestObject+"  using JavaScript" , true);  
			elementStatus = false;

		}

		return elementStatus;

	}
	
	
	 /** @Objective <b>This method verifies the Popup Modal is present - waits for 60 secs to Appear</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>13-SEP-16</b>
	 */

	
	public static boolean VerifyDateModalPopupPresent(String strTestObject) throws Exception{

		Object isModalPresent=false;

		try{
			log.info("Check element is appear");

			for(int i=0; i<60; i++){

				try{
					Thread.sleep(1000);
					log.info("Inside Loop... i is -> "+i);

					isModalPresent = ((JavascriptExecutor)driver).executeScript("return document.activeElement.getElementsByClassName('modal-title')[0].innerHTML.trim()!=null;");

					if((boolean) isModalPresent){
						log.info("Element is appeared");
						break;
					}
				}catch(WebDriverException e){
					//DO Nothinf
					log.info("WebDriverException caught inside for since Popup is not appeared");
				}
			}

			if((boolean) isModalPresent){
				Report_Functions.ReportEventSuccess(doc,"1","","'The Modal Popup '"+ strTestObject +"' is displayed.",3);
				return true;
			}else{
				Report_Functions.ReportEventSuccess(doc,"1","","'The Modal Popup '"+ strTestObject +"' is not displayed within 60 secs.",3);
				return false;
			}


		}catch(StaleElementReferenceException e){
			log.info("StaleElementReferenceException caught inside for since Popup is not appeared");
			Report_Functions.ReportEventSuccess(doc,"1","","'The element '"+ strTestObject +"' is not displayed",3);
			return false;
		}
		catch(Exception e){
			log.info("Element is not found :"+e);
			Report_Functions.ReportEventFailure(doc,"","Exception occured. Error message is : "+ e +"." , true);
			return false;
		}
	}
	
	/**
	 * 
	 * @Objective <b>Verifies to update the data for RRBS in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>29-SEP-16</b>
	 */

	public static boolean ESHOPDBCommonPreCondition(String actionName,String sqltablename, String strsqlcolumnname,String strsqlcolumnvalue,String strsqlcondition,int strExecEventFlag)throws Exception  {

		boolean elementStatus= false;
		String action=null;
		
		try {
			
			if (strExecEventFlag==1){
				action=RetrieveTestDataValue("",actionName,strExecEventFlag);
			}
			if(action==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			
			if(action.trim().equalsIgnoreCase("Update")){
				elementStatus=EshopSQLDBUpdate(sqltablename, strsqlcolumnname, strsqlcolumnvalue, strsqlcondition, strExecEventFlag);
			}else if(action.trim().equalsIgnoreCase("Delete")){
				elementStatus=EshopSQLDBDelete(sqltablename, strsqlcondition, strExecEventFlag);
			}else{
				log.info("Invalid Action item from Excel");
				Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet - "+action, false);
			}
	
		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "RRBSDBUpdate",  "Error occured while executing the ESHOP SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBUpdate Error : " + e);
		}
		return elementStatus;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies the SQL DB select</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>30-SEP-16</b>
	 */

	public static boolean SQLAllSelect(String Type,String sqltablename, String strsqlcolumnname,String strsqlcondition,String strExpectedvalue,String Days_to_add,String strenvironmentvariable,int strExecEventFlag)throws Exception  {
		
		boolean result=false;
		String actionType=null;
		try{
			
			if (strExecEventFlag==1){
				actionType=RetrieveTestDataValue("",Type,strExecEventFlag);
			}
			if(actionType==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
		
			if(actionType.trim().equalsIgnoreCase("Normal")){
				result= SQLDBSelect(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("Date")){
				result= SQLDBDateFormatCompare(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("FutureDate")){
				result= SQLDBFutureDateCompare(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, Days_to_add, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("EnvVar")){
				result= SQLDBSelectFromEnv(sqltablename, strsqlcolumnname, strsqlcondition, strenvironmentvariable, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("EnvCon")){
				result= SQLDBSelectConditionFromEnvvar(sqltablename, strsqlcolumnname, strsqlcondition, strenvironmentvariable, strExpectedvalue, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("DateInEnvVar")){
				result= SQLDBDateCompareInEnvVar(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strenvironmentvariable, 1);
			}else{
				log.info("Invalid Action item from Excel");
				Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet - "+actionType, false);
			}
		}catch (Exception e) {
			log.info("Exception occured SQLDBCommonSelect. Exception is : "+e);
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			return false;
		}
		return result;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies the SQL DB select</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>30-SEP-16</b>
	 */

	public static boolean EShopSQLAllSelect(String Type,String sqltablename, String strsqlcolumnname,String strsqlcondition,String strExpectedvalue,String Days_to_add,String strenvironmentvariable,int strExecEventFlag)throws Exception  {
		
		boolean result=false;
		String actionType=null;
		try{
			
			if (strExecEventFlag==1){
				actionType=RetrieveTestDataValue("",Type,strExecEventFlag);
			}
			if(actionType==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
		
			if(actionType.trim().equalsIgnoreCase("Normal")){
				result= EShopSQLDBSelect(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("Date")){
				result= EShopSQLDBDateFormatCompare(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("FutureDate")){
				result= EShopSQLDBFutureDateCompare(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, Days_to_add, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("EnvVar")){
				result= EShopSQLDBSelectFromEnv(sqltablename, strsqlcolumnname, strsqlcondition, strenvironmentvariable, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("EnvCon")){
				result= ESHOPSQLDBSelectConditionEnvvar1(sqltablename, strsqlcolumnname, strsqlcondition, strenvironmentvariable, strExpectedvalue, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("EnvConSuffix")){
				result= ESHOPSQLDBSelectConditionEnvvarSuffix(sqltablename, strsqlcolumnname, strsqlcondition, strenvironmentvariable, strExpectedvalue,Days_to_add, strExecEventFlag);
			}else{
				log.info("Invalid Action item from Excel");
				Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet - "+actionType, false);
			}
		}catch (Exception e) {
			log.info("Exception occured SQLDBCommonSelect. Exception is : "+e);
			Report_Functions.ReportEventFailure(doc,  "ESHOP SQLDBSelect",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			return false;
		}
		return result;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies to check date comparision in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param Date_Format
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>30-SEP-16</b>
	 */

	public static boolean EShopSQLDBDateFormatCompare(String sqltablename, String strsqlcolumnname,String strsqlcondition,String dateFormatFromExcel,int strExecEventFlag)throws Exception  {
		boolean SQLDBDateCompare= false;
		String query = null;  
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Expected_value = null;
		String Actual_Value = null;
		String Current_Date=null;
		String expected_db_Date = null;
		String Date_Format=null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBDateCompare",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBDateCompare",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBDateCompare",strsqlcondition,strExecEventFlag);
				Date_Format=RetrieveTestDataValue("SQLDBDateCompare",dateFormatFromExcel,strExecEventFlag);
			}
			
			log.info("Table_name is : "+Table_name);
			log.info("Column_name is : "+Column_name);
			log.info("SQL_condition is : "+SQL_condition);
			log.info("Date_Format is : "+Date_Format);

			if(Table_name==null || Column_name==null || SQL_condition==null || Date_Format ==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat dateFormat = new SimpleDateFormat(Date_Format);
			Date date = new Date();
			Current_Date = dateFormat.format(date);
			Expected_value = Current_Date.trim();

			// Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			Eshop_SQLServer= EShopstmt.executeQuery(query);
			
			log.info("Quer is : "+query);

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBDateCompare Error : " + e);
			SQLDBDateCompare=false;
		}

		try{
			Eshop_SQLServer.next();
			Actual_Value = Eshop_SQLServer.getString(1);
			String db_Date = Actual_Value.split(" ")[0];

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date dateToChange = dateFormat.parse(db_Date);

			SimpleDateFormat finalDateFormat = new SimpleDateFormat(Date_Format);
			expected_db_Date = finalDateFormat.format(dateToChange);	

		} catch (Exception NullPointerException) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "No Record found for this query: "+query, true);
			log.info("SQLDBDateCompare Error : ");
			SQLDBDateCompare=false;
		}

		try{

			if(!Eshop_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(expected_db_Date.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDateCompare", "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' matches with the expected date : '"+Expected_value+"' ", 2);
					SQLDBDateCompare=true;
				}else if(!(expected_db_Date.equalsIgnoreCase(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' does not match with the expected date : '"+Expected_value+"' ", true);
					SQLDBDateCompare=false;
				}
			}else if(Eshop_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equalsIgnoreCase("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDateCompare", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					SQLDBDateCompare=true;
				}else if(!(Expected_value.equalsIgnoreCase("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					SQLDBDateCompare=false;
				}
			}

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Error occured while comparing the dates in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBDateCompare Error : " + e);
			SQLDBDateCompare=false;
		}
		return SQLDBDateCompare;
	}
	
	
	/**
	 * 
	 * @Objective <b>Verifies to check date comparision in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param Date_Format
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>30-SEP-15</b>
	 */

	public static boolean EShopSQLDBFutureDateCompare(String sqltablename, String strsqlcolumnname,String strsqlcondition,String Date_Format,String Days_to_add,int strExecEventFlag)throws Exception  {
		boolean SQLDBDateCompare= false;
		String query = null;  
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Expected_value = null;
		String Actual_Value = null;
		String Current_Date=null;
		String expected_db_Date = null;
		String daystoadd = null;
		

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBDateCompare",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBDateCompare",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBDateCompare",strsqlcondition,strExecEventFlag);
				daystoadd = RetrieveTestDataValue("RRBSDBFutureDateCompare", Days_to_add, strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}
			
			int Add_Days = Integer.parseInt(daystoadd);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			
			Calendar expdate = Calendar.getInstance();
			expdate.setTime(date);
			expdate.add(Calendar.DATE, Add_Days);
			Current_Date = dateFormat.format(expdate.getTime());

			Expected_value = Current_Date.trim();

			// Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			Eshop_SQLServer= EShopstmt.executeQuery(query);

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBDateCompare Error : " + e);
			SQLDBDateCompare=false;
		}

		try{
			Eshop_SQLServer.next();
			Actual_Value = Eshop_SQLServer.getString(1);
			String db_Date = Actual_Value.split(" ")[0];

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date dateToChange = dateFormat.parse(db_Date);

			SimpleDateFormat finalDateFormat = new SimpleDateFormat(Date_Format);
			expected_db_Date = finalDateFormat.format(dateToChange);	

		} catch (Exception NullPointerException) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "No Record found for this query: "+query, true);
			log.info("SQLDBDateCompare Error : ");
			SQLDBDateCompare=false;
		}

		try{

			if(!Eshop_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(expected_db_Date.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDateCompare", "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' matches with the expected date : '"+Expected_value+"' ", 2);
					SQLDBDateCompare=true;
				}else if(!(expected_db_Date.equalsIgnoreCase(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' does not match with the expected date : '"+Expected_value+"' ", true);
					SQLDBDateCompare=false;
				}
			}else if(Eshop_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equalsIgnoreCase("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDateCompare", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					SQLDBDateCompare=true;
				}else if(!(Expected_value.equalsIgnoreCase("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					SQLDBDateCompare=false;
				}
			}

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Error occured while comparing the dates in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBDateCompare Error : " + e);
			SQLDBDateCompare=false;
		}
		return SQLDBDateCompare;
	}

	/**
	 * 
	 * @Objective <b>To retrieve Value from Resoruce file using unique Key</b>
	 * @author <b>Lakshman</b>
	 * @since <b>05-OCT-16</b>
	 */

	public static String RetrieveValueUsingAutomationKey(String strAutomationkey) throws Exception{

		String resourceFileName=null;
		String resourceKey=null;
		String resourceCountry=null;
		String filePathLocation=null;
		NodeList nodeList=null;
		String resxFilePath="//\\"+Param.getProperty("resourceMgmtfilekeyPath");

		try{

			resourceFileName =ReadExcel.RetrieveAutomationKeyFromExcel(resxFilePath, Param.getProperty("resourcefileSheetName"), "Resource_FileName", strAutomationkey);
			resourceKey=ReadExcel.RetrieveAutomationKeyFromExcel(resxFilePath, Param.getProperty("resourcefileSheetName"), "Resource_Key", strAutomationkey);
			resourceCountry=ReadExcel.RetrieveAutomationKeyFromExcel(resxFilePath, Param.getProperty("resourcefileSheetName"), "Country_KeyInPropFile", strAutomationkey);
			log.info("resourceFileName is "+resourceFileName);
			log.info("resourceKey is :"+resourceKey);
			log.info("resourceCountry Key is :"+resourceCountry);

			if(resourceFileName==null || resourceFileName.trim().equals("") || resourceKey==null || resourceKey.trim().equals("") || resourceCountry==null || resourceCountry.trim().equals("")){
				Report_Functions.ReportEventFailure(doc, "", "Resource Filename or key is Empty or NULL in the Resource Management sheet", false);
				return null;
			}

			filePathLocation=Param.getProperty(resourceCountry);
			String resourceFileToGetValue=filePathLocation+"\\"+resourceFileName;
			log.info("File path is "+resourceFileToGetValue);
			File file=new File("//\\"+resourceFileToGetValue);

			String commonAttribute=Param.getProperty("attributeCommonValue");
			String fullAttributewithName=commonAttribute+"[@name='"+resourceKey+"']/value";
			log.info("Attribute to Retrieve Node value is : "+fullAttributewithName);

			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document document=builder.parse(file);
			document.getDocumentElement().normalize();
			XPath xpath=XPathFactory.newInstance().newXPath();
			nodeList=(NodeList)xpath.compile(fullAttributewithName).evaluate(document,XPathConstants.NODESET);

			String nodeValue = nodeList.item(0).getTextContent();
			log.info("Value for the Key is : "+nodeValue);
			Report_Functions.ReportEventSuccess(doc,"1", "", "Retrieved Value of the Key '" +resourceKey+ "' from the Resource file '"+resourceFileName+"' is '"+nodeValue+"'.", 2);

			return nodeValue;

		}catch(Exception e){

			log.info("Exception occured : "+e);
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while Retrieving the value for the corresponding key from Resource file. Error description is :"+e.getMessage(), false);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @Objective <b>To compare the actual Text with the content in the Resource file retireved using a Key</b>
	 * @author <b>Lakshman</b>
	 * @since <b>05-OCT-16</b>
	 */

	public static boolean WebElementResxKeyValueStaticCompare(String getValueFromPOM, String strTestObject,String strColumnName, int strExecEventFlag )throws Exception  {
		String actualResult=null;
		String automationKey=null;
		String valueFromResxFile=null;

		try{
			if(strExecEventFlag==1){
				automationKey=RetrieveTestDataValue("WebElementTextCompare",strColumnName,strExecEventFlag);
			}else{
				automationKey=strColumnName;
			}
			
			log.info("automationKey in Fl RESX STATIC COMPARE is : "+automationKey);
			
			if(automationKey==null || automationKey.equalsIgnoreCase("")){
				Report_Functions.ReportEventFailure(doc,"",  "Automation key details are not provided in the Data Sheet.", false);
				return false;
			}
			
			//Get the Value for the Key from the .resx file using the Automation from Resource Management file
			valueFromResxFile=RetrieveValueUsingAutomationKey(automationKey);
			log.info("valueFromResxFile in Fl RESX STATIC COMPARE is : "+valueFromResxFile);
			
			if(valueFromResxFile==null || automationKey.equalsIgnoreCase("")){
				Report_Functions.ReportEventFailure(doc,"",  "Error occured while retrieving data from the Resource Management Data file.", false);
				return false;
			}
			
			//Actual value of the Element
			actualResult = selectByLocatorType(getValueFromPOM).getText();

			if((actualResult.trim()).equals(valueFromResxFile.trim())){
				Report_Functions.ReportEventSuccess(doc,"1","", "'"+strTestObject+"'s actual value '" +actualResult+ "' matches with the content in the Resource file '"+valueFromResxFile+"'", 2);
				return true;
			}else{
				Report_Functions.ReportEventFailure(doc,"","'"+strTestObject+"'s actual value '" +actualResult+ "' does not matches with the content in the Resource file '"+valueFromResxFile+"'", true);
				return false;
			}
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while comparing the text of a WebElement '"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			return false;
		}

	}

		/**
	 * 
	 * @Objective <b>Compare the Date in Env variable to date in SQL DB</b>
	 * @author <b>Lakshman</b>
	 * @since <b>17-OCT-16</b>
	 */
	
	public static boolean WebElementEnterCurrentDate(String getValueFromPOM,  String strTestObject,String strdateFormatInDataSheet,String envVariable, int strExecEventFlag) throws Exception{

		boolean functionStatus= false;
		String strDateFormat=null;
		String currentDate=null;
		String envVariableName=null;
		
		
		try {
			if(strExecEventFlag==1){
				strDateFormat=RetrieveTestDataValue("WebEditEnterText",strdateFormatInDataSheet,strExecEventFlag);
				envVariableName=RetrieveTestDataValue("WebEditEnterText",envVariable,strExecEventFlag);
			}else{
				strDateFormat=strdateFormatInDataSheet;
				envVariableName=envVariable;
			}

			SimpleDateFormat dateformat = new SimpleDateFormat(strDateFormat);
			Date date = new Date();
			currentDate = dateformat.format(date);
			
			if(!(envVariableName.equalsIgnoreCase("NA"))){
				Runtimevalue.setProperty(envVariableName, currentDate);
				Report_Functions.ReportEventSuccess (doc,"1","","Dynamic Date value '"+currentDate+"' is stored in the Runtime Variable  "+envVariableName+"'",2);
			}
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('"+getValueFromPOM+"').value = '"+currentDate+"'");
			Report_Functions.ReportEventSuccess (doc,"1","","Current Date '"+currentDate+"' is entered in the '"+strTestObject+"'",2);
			functionStatus=true;	
		} catch (Exception e) { 	
			Report_Functions.ReportEventFailure(doc, "WebEditEnterText","Current Date '"+currentDate+"' is not entered in the '"+strTestObject+"'", true);
			functionStatus=false;
			log.info("No Element Found to enter text : " + e);
		}
		return functionStatus;
		
	}
	
	/**
	 * 
	 * @Objective <b>Compare the Date in Env variable to date in SQL DB</b>
	 * @author <b>Lakshman</b>
	 * @since <b>17-OCT-16</b>
	 */
	
	public static boolean WebElementFutureMonthValueComapre(String getValueFromPOM,  String strTestObject,String strdateFormatInDataSheet,String monthsToAddInSheet,String envVariable,int strExecEventFlag) throws Exception{

		boolean functionStatus= false;
		String strDateFormat=null;
		String monthsToAdd=null;
		String funtureMonthDate=null;
		String actualResult=null;
		String envVariableName=null;
		
		try {
			if(strExecEventFlag==1){
				strDateFormat=RetrieveTestDataValue("WebEditEnterText",strdateFormatInDataSheet,strExecEventFlag);
				monthsToAdd=RetrieveTestDataValue("WebEditEnterText",monthsToAddInSheet,strExecEventFlag);
				envVariableName=RetrieveTestDataValue("WebEditEnterText",envVariable,strExecEventFlag);
			}else{
				strDateFormat=strdateFormatInDataSheet;
				monthsToAdd=monthsToAddInSheet;
				envVariableName=envVariable;
			}
			
			SimpleDateFormat dateformat = new SimpleDateFormat(strDateFormat);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, +(Integer.parseInt(monthsToAdd)));
			funtureMonthDate = dateformat.format(cal.getTime());
			
			if(!(envVariableName.equalsIgnoreCase("NA"))){
				Runtimevalue.setProperty(envVariableName, funtureMonthDate);
				Report_Functions.ReportEventSuccess (doc,"1","","Dynamic Future date '"+funtureMonthDate+"' is stored in the Runtime Variable  "+envVariableName+"'",2);
			}
			
			actualResult = selectByLocatorType(getValueFromPOM).getAttribute("value");

			if((actualResult.trim()).equalsIgnoreCase(funtureMonthDate.trim())){
				functionStatus=true;
				Report_Functions.ReportEventSuccess(doc,"1", "", ""+strTestObject+"'s  actual date '" + actualResult + "' matches the Expected future date '" + funtureMonthDate + "'", 2);
			}else{
				functionStatus=false;
				Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+" actual date '" + actualResult + "' does not match the expected date '" + funtureMonthDate + "'", true);
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","Exception occured while comparing the Future date.", true);
			functionStatus=false;
			log.info("No Element Found to compare Text : " + e);
		}
		return functionStatus;	
	}
	
	/**
	 * 
	 * @Objective <b>Compare the Date in Env variable to date in SQL DB</b>
	 * @author <b>Lakshman</b>
	 * @since <b>17-OCT-16</b>
	 */

	public static boolean SQLDBDateCompareInEnvVar(String sqltablename, String strsqlcolumnname,String strsqlcondition,String dateFormatInSheet,String envVariable,int strExecEventFlag)throws Exception  {
		boolean SQLDBDateCompare= false;
		String query = null;  
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Expected_value = null;
		String Actual_Value = null;
		String expected_db_Date = null;
		String Date_Format=null;
		String envVariableName=null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBDateCompare",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBDateCompare",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBDateCompare",strsqlcondition,strExecEventFlag);
				Date_Format=RetrieveTestDataValue("SQLDBDateCompare",dateFormatInSheet,strExecEventFlag);
				envVariableName=RetrieveTestDataValue("SQLDBDateCompare",envVariable,strExecEventFlag);
			}
			
			/*log.info("Table_name is : "+Table_name);
			log.info("Column_name is : "+Column_name);
			log.info("SQL_condition is : "+SQL_condition);
			log.info("Date_Format is : "+Date_Format);*/

			if(Table_name==null || Column_name==null || SQL_condition==null || Date_Format ==null || envVariableName==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			Expected_value=Runtimevalue.getProperty(envVariableName);

			// Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			rs_SQLServer= stmt.executeQuery(query);
			
			//log.info("Quer is : "+query);

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBDateCompare Error : " + e);
			SQLDBDateCompare=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1);
			String db_Date = Actual_Value.split(" ")[0];

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date dateToChange = dateFormat.parse(db_Date);

			SimpleDateFormat finalDateFormat = new SimpleDateFormat(Date_Format);
			expected_db_Date = finalDateFormat.format(dateToChange);	

		} catch (Exception NullPointerException) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "No Record found for this query: "+query, true);
			log.info("SQLDBDateCompare Error : ");
			SQLDBDateCompare=false;
		}

		try{

			if(!rs_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(expected_db_Date.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDateCompare", "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' matches with the expected date : '"+Expected_value+"' ", 2);
					SQLDBDateCompare=true;
				}else if(!(expected_db_Date.equalsIgnoreCase(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' does not match with the expected date : '"+Expected_value+"' ", true);
					SQLDBDateCompare=false;
				}
			}else if(rs_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equalsIgnoreCase("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDateCompare", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					SQLDBDateCompare=true;
				}else if(!(Expected_value.equalsIgnoreCase("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					SQLDBDateCompare=false;
				}
			}

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateCompare",  "Error occured while comparing the dates in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBDateCompare Error : " + e);
			SQLDBDateCompare=false;
		}
		return SQLDBDateCompare;
	}
	
	/**
	 * 
	 * @Objective <b>Compare the Date in Env variable to date in SQL DB</b>
	 * @author <b>Lakshman</b>
	 * @since <b>18-OCT-16</b>
	 */
	
	public static boolean SQLDBSelectStoreValueInEnvVar(String sqltablename, String strsqlcolumnname,String strsqlcondition,String envVariable,int strExecEventFlag)throws Exception  {
		boolean SQLDBSelect= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Actual_Value = null;
		String envVariableName=null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBSelect",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBSelect",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBSelect",strsqlcondition,strExecEventFlag);
				envVariableName=RetrieveTestDataValue("SQLDBSelect",envVariable,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null||envVariableName==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			rs_SQLServer = stmt.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelect Error : " + e);
			SQLDBSelect=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1).trim();

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "No Record found for this query: "+query, true);
			log.info("SQLDBSelect Error : ");
			SQLDBSelect=false;
		}


		try{
			if(!rs_SQLServer.wasNull()){
				Runtimevalue.setProperty(envVariableName, Actual_Value);
				Report_Functions.ReportEventSuccess(doc, "1","", "Dynamic value '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+" is stored in the Runtime Variable '"+envVariableName+"'", 2);
				SQLDBSelect=true;
			}
		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while stroing the values env variable from in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelect Error : " + e);
			SQLDBSelect=false;
		}
		return SQLDBSelect;
	}

	/**
	 * 
	 * @Objective <b>Compare the Date in Env variable to date in SQL DB</b>
	 * @author <b>Lakshman</b>
	 * @since <b>18-OCT-16</b>
	 */
	
	public static boolean WebElementValueCompareFromRuntimeEnv(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag )throws Exception  {
		String actualResult=null;
		String strData=null;
		boolean WebElementValueCompareFromEnv=false;
		String envVariable=null;
		
		
		try{
			if (strExecEventFlag==1){
				envVariable=RetrieveTestDataValue("WebElementValueCompareFromEnv",strColumnName,strExecEventFlag);
			}else{
				envVariable=strColumnName;
			}
			
			if(envVariable==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			
			strData=Runtimevalue.getProperty(envVariable);
			actualResult = selectByLocatorType(getValueFromPOM).getAttribute("value");

		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementValueCompareFromEnv", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			WebElementValueCompareFromEnv=false;
		}

		try{
			if((actualResult.trim()).equalsIgnoreCase(strData.trim())){
				Report_Functions.ReportEventSuccess(doc,"1", "", "The  Actual Value '" +actualResult+ "' matches with the EnvVariable '"+envVariable+"'s value '"+strData+ "' in the input field '"+strTestObject+"'", 2);
				WebElementValueCompareFromEnv=true;
			}else{
				Report_Functions.ReportEventFailure(doc,"", "The  Actual Value '" +actualResult+ "' does not match with the EnvVariable '"+envVariable+"'s value '"+strData+ "' in the input field '"+strTestObject+"'", true);
				WebElementValueCompareFromEnv=false;
			}
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"", "Error occured while comparing actual and expected values. Error description is :"+e.getMessage(), true);
			WebElementValueCompareFromEnv=false;
		}
		return WebElementValueCompareFromEnv;
	}
	
	/**
	 * 
	 * @Objective <b>Compare the Date in Env variable to date in SQL DB</b>
	 * @author <b>Lakshman</b>
	 * @since <b>18-OCT-16</b>
	 */
	
	public static boolean WeblistMonthVerify(String getYearValueFromPOM,String getMonthValueFromPOM, String strYearObject,String strMonthObject, String totalNumbersColumn,int itemscount_not_consider,int strExecEventFlag)throws Exception  {

		boolean WeblistSQLDBitemsverify= false;
		String strData=null;
		int totalNumberMonths;
		
		String[] weblistvalues = null;
		
		if(strExecEventFlag==1){
			strData=RetrieveTestDataValue("WebElementValueCompare",totalNumbersColumn,strExecEventFlag);
		}
		if(strData==null){
			Report_Functions.ReportEventFailure(doc,  "WebElementValueCompare",  "Required details are not provided.", false);
			return false;
		}
		
		totalNumberMonths=Integer.parseInt(strData);
		
		int noOfYears = totalNumberMonths/12;

		try{
			if(noOfYears>=1){
				//Weblist the years box
				Select se = new Select(selectByLocatorType(getYearValueFromPOM));
				List<WebElement> options = se.getOptions();

				//if you want to get all elements text into array list
				List<String> all_elements_text=new ArrayList<String>();

				for(int j=0; j<options.size(); j++){

					//loading text of each element in to array all_elements_text
					all_elements_text.add(options.get(j).getText());

				}

				weblistvalues = (String[]) all_elements_text.toArray(new String[all_elements_text.size()]);

				log.info("Int length is : "+strData);
				log.info("weblistvalues length is : "+weblistvalues.length);

				if(noOfYears == weblistvalues.length - itemscount_not_consider)
				{
					if((weblistvalues.length - itemscount_not_consider)==0){
						
						log.info("No value present in DB as well list");
						Report_Functions.ReportEventSuccess(doc, "1","", "No Dropdown Value is Present in the Dropdown '"+strYearObject+"' and the corresponding Table", 2);
						WeblistSQLDBitemsverify=true;
					
					}else{
						for (int i=0; i<(weblistvalues.length - itemscount_not_consider); i++){
							
							if (weblistvalues[i+1].equals(Integer.toString(i+1))){
								Report_Functions.ReportEventSuccess(doc, "1","WeblistSQLDBitemsverify", "The actual value : '"+ weblistvalues[i+1] +"' in the dropdown : "+ strYearObject +"' matches the expected value : '"+(i+1)+"'.", 2);
								WeblistSQLDBitemsverify=true;
							} else{
								Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "The actual value : '"+ weblistvalues[i+1] +"' in the dropdown : "+ strYearObject +"' doesn't matches the expected value : '"+(i+1)+"'.", true);
								WeblistSQLDBitemsverify=false;
							}
						}
					}
				} else {
					Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "The number of items present in the dropdown : "+ strYearObject +"' doesn't matches with the given number of items '"+ noOfYears +"'.", true);
					WeblistSQLDBitemsverify=false;
				}
			
			}
			
			//For months
			
			
			Select se = new Select(selectByLocatorType(getMonthValueFromPOM));
			List<WebElement> options = se.getOptions();

			//if you want to get all elements text into array list
			List<String> all_elements_text=new ArrayList<String>();

			for(int j=0; j<options.size(); j++){

				//loading text of each element in to array all_elements_text
				all_elements_text.add(options.get(j).getText());

			}

			weblistvalues = (String[]) all_elements_text.toArray(new String[all_elements_text.size()]);

			log.info("Int length is : "+strData);
			log.info("weblistvalues length is : "+weblistvalues.length);
			itemscount_not_consider=0;

			if(totalNumberMonths == weblistvalues.length - itemscount_not_consider)
			{
				if((weblistvalues.length - itemscount_not_consider)==0){
					
					log.info("No value present in DB as well list");
					Report_Functions.ReportEventSuccess(doc, "1","", "No Dropdown Value is Present in the Dropdown '"+strMonthObject+"' and the corresponding Table", 2);
					WeblistSQLDBitemsverify=true;
				
				}else{
					for (int i=0; i<(weblistvalues.length - 1); i++){
						
						if (weblistvalues[i+1].equals(Integer.toString(i+1))){
							Report_Functions.ReportEventSuccess(doc, "1","WeblistSQLDBitemsverify", "The actual value : '"+ weblistvalues[i+1] +"' in the dropdown : "+ strMonthObject +"' matches the expected value : '"+(i+1)+"'.", 2);
							WeblistSQLDBitemsverify=true;
						} else{
							Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "The actual value : '"+ weblistvalues[i+1] +"' in the dropdown : "+ strMonthObject +"' doesn't matches the expected value : '"+(i+1)+"'.", true);
							WeblistSQLDBitemsverify=false;
						}
					}
				}
			} else {
				Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "The number of items present in the dropdown : "+ strMonthObject +"' doesn't matches with the given number of items '"+ totalNumberMonths +"'.", true);
				WeblistSQLDBitemsverify=false;
			}

			
			
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "Error occured while comparing the values. Error description is : "+ e.getMessage() +".", true);
			log.info("WeblistSQLDBitemsverify Error : " + e);
			WeblistSQLDBitemsverify=false;
		}
		return WeblistSQLDBitemsverify;
	}
	
	/**
	 * 
	 * @Objective <b>Compare the Date in Env variable to date in SQL DB</b>
	 * @author <b>Lakshman</b>
	 * @since <b>18-OCT-16</b>
	 */
	
	public static boolean JSEnterLastDateOfAFutureYear(String getValueFromPOM,  String strTestObject,String strdateFormatInDataSheet,String strfutureYearsToAdd,String envVariable, int strExecEventFlag) throws Exception{

		boolean functionStatus= false;
		String strDateFormat=null;
		String envVariableName=null;
		String futureYearsToAdd=null;
		String futureDateToEnter=null;
		
		try {
			if(strExecEventFlag==1){
				strDateFormat=RetrieveTestDataValue("WebEditEnterText",strdateFormatInDataSheet,strExecEventFlag);
				envVariableName=RetrieveTestDataValue("WebEditEnterText",envVariable,strExecEventFlag);
				futureYearsToAdd=RetrieveTestDataValue("WebEditEnterText",strfutureYearsToAdd,strExecEventFlag);
			}else{
				strDateFormat=strdateFormatInDataSheet;
				envVariableName=envVariable;
				futureYearsToAdd=strfutureYearsToAdd;
			}

			SimpleDateFormat dateformatToGetYear = new SimpleDateFormat("yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, +(Integer.parseInt(futureYearsToAdd)));
			String addedYear=dateformatToGetYear.format(cal.getTime());
			
			SimpleDateFormat dateformatToEnter = new SimpleDateFormat(strDateFormat);
			Calendar calToGetFutureDate = Calendar.getInstance();
			calToGetFutureDate.set(Integer.parseInt(addedYear),  12, 31);
			calToGetFutureDate.add(Calendar.MONTH, -1);
			futureDateToEnter=dateformatToEnter.format(calToGetFutureDate.getTime());
			
			
			if(!(envVariableName.equalsIgnoreCase("NA"))){
				Runtimevalue.setProperty(envVariableName, futureDateToEnter);
				Report_Functions.ReportEventSuccess (doc,"1","","Dynamic Date value '"+futureDateToEnter+"' is stored in the Runtime Variable  "+envVariableName+"'",2);
			}
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('"+getValueFromPOM+"').value = '"+futureDateToEnter+"'");
			Report_Functions.ReportEventSuccess (doc,"1","","The Last date of the Future year '"+futureDateToEnter+"' is entered in the '"+strTestObject+"'",2);
			functionStatus=true;	
		} catch (Exception e) { 	
			Report_Functions.ReportEventFailure(doc, "WebEditEnterText","The Last date of the Future year '"+futureDateToEnter+"' is not entered in the '"+strTestObject+"'", true);
			functionStatus=false;
			log.info("No Element Found to enter text : " + e);
		}
		return functionStatus;
		
	}
	
	public static boolean WebElementFutureMonthValueComapreFromEnv(String getValueFromPOM,  String strTestObject,String strdateFormatInDataSheet,String strMonthsFromSheet,String envVariableHoldingDate,String envVariableToStoreNewDate,int strExecEventFlag) throws Exception{

		boolean functionStatus= false;
		String strDateFormat=null;
		String monthsToAdd=null;
		String funtureMonthDate=null;
		String actualResult=null;
		String envVariableName=null;
		String dateInEnvVariable=null;
		
		try {
			if(strExecEventFlag==1){
				strDateFormat=RetrieveTestDataValue("WebEditEnterText",strdateFormatInDataSheet,strExecEventFlag);
				monthsToAdd=RetrieveTestDataValue("WebEditEnterText",strMonthsFromSheet,strExecEventFlag);
				dateInEnvVariable=RetrieveTestDataValue("WebEditEnterText",envVariableHoldingDate,strExecEventFlag);
				envVariableName=RetrieveTestDataValue("WebEditEnterText",envVariableToStoreNewDate,strExecEventFlag);
				
			}else{
				strDateFormat=strdateFormatInDataSheet;
				monthsToAdd=strMonthsFromSheet;
				dateInEnvVariable=envVariableHoldingDate;
				envVariableName=envVariableToStoreNewDate;
			}
			
			String dateFromEnvVariable=Runtimevalue.getProperty(dateInEnvVariable);
			
			// 31/10/218
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
			cal.setTime(sdf.parse(dateFromEnvVariable));			
			cal.add(Calendar.MONTH, +(Integer.parseInt(monthsToAdd)));
			funtureMonthDate = sdf.format(cal.getTime());
			
			if(!(envVariableName.equalsIgnoreCase("NA"))){
				Runtimevalue.setProperty(envVariableName, funtureMonthDate);
				Report_Functions.ReportEventSuccess (doc,"1","","Dynamic Future date '"+funtureMonthDate+"' is stored in the Runtime Variable  "+envVariableName+"'",2);
			}
			
			actualResult = selectByLocatorType(getValueFromPOM).getAttribute("value");

			if((actualResult.trim()).equalsIgnoreCase(funtureMonthDate.trim())){
				functionStatus=true;
				Report_Functions.ReportEventSuccess(doc,"1", "", ""+strTestObject+"'s  actual date '" + actualResult + "' matches the Expected future date '" + funtureMonthDate + "'", 2);
			}else{
				functionStatus=false;
				Report_Functions.ReportEventFailure(doc,"", ""+strTestObject+" actual date '" + actualResult + "' does not match the expected date '" + funtureMonthDate + "'", true);
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","Exception occured while comparing the Future date.", true);
			functionStatus=false;
			log.info("No Element Found to compare Text : " + e);
		}
		return functionStatus;	
	}
	

	public static boolean WebElementValueStoreDynamicValue(String getValueFromPOM, String strTestObject,String strColumnName, int strExecEventFlag )throws Exception  {
		String actualText="";
		String strData=null;
		boolean WebElementTextCompare=false;
		try{
			try{
				if(strExecEventFlag==1){
					strData=RetrieveTestDataValue("",strColumnName,strExecEventFlag);
				}
				actualText = selectByLocatorType(getValueFromPOM).getAttribute("value");
			} catch (Exception e){
				Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
				WebElementTextCompare=false;
			}

			if(actualText == null){
				actualText="";
				log.info("Value is NULL . Setting EMPTY to Runtime Property '"+strData+"'");
				Report_Functions.ReportEventSuccess(doc,"1", "WebElementTextStoreInEnv", "The Empty value '"+actualText+"' is stored in the '"+strTestObject+"' is stored in the Runtime variable '"+strData+"'", 2);
				return false;
			}

			try{
				Runtimevalue.setProperty(strData, actualText);
				log.info("Value set to Runtime Property '"+strData+"' is => '"+Runtimevalue.getProperty(strData)+"'");
				Report_Functions.ReportEventSuccess(doc,"1", "WebElementTextStoreInEnv", "The Dynamic value '"+actualText+"' of Element '"+strTestObject+"' is successfully stored in the Runtime variable '"+strData+"'", 2);
				WebElementTextCompare=true;
			} catch (Exception e){
				Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while Stroing the Dynamic value of element '"+strTestObject+"' in the Runtime variable '"+strData+"'. Error description is :"+e.getMessage(), true);
				WebElementTextCompare=false;
			}

		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementTextCompare", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			WebElementTextCompare=false;
		}
		return WebElementTextCompare;
	}
	

	/**
	 * 
	 * @Objective <b>Verifies the RRBS DB select</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>20-OCT-16</b>
	 */

	public static boolean RRBSAllSelect(String Type,String sqltablename, String strsqlcolumnname,String strsqlcondition,String strExpectedvalue,String Days_to_add,String strenvironmentvariable,int strExecEventFlag)throws Exception  {
		
		boolean result=false;
		String actionType=null;
		try{
			
			if (strExecEventFlag==1){
				actionType=RetrieveTestDataValue("",Type,strExecEventFlag);
			}
			if(actionType==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
		
			if(actionType.trim().equalsIgnoreCase("Normal")){
				result=RRBSDBSelect(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("Date")){
				result=RRBSDBDateCompare(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("FutureDate")){
				result=RRBSDBFutureDateCompare(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, Days_to_add, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("EnvVar")){
				//result= SQLDBSelectFromEnv(sqltablename, strsqlcolumnname, strsqlcondition, strenvironmentvariable, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("EnvCon")){
				//result= SQLDBSelectConditionEnvvar(sqltablename, strsqlcolumnname, strsqlcondition, strenvironmentvariable, strExpectedvalue, strExecEventFlag);
			}else if(actionType.trim().equalsIgnoreCase("DateInEnvVar")){
				//result= SQLDBDateCompareInEnvVar(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strenvironmentvariable, 1);
			}else if(actionType.trim().equalsIgnoreCase("DateCompServer")){
				result= RRBSDateCompareFromDB(sqltablename, strsqlcolumnname, strsqlcondition, strExpectedvalue, strExecEventFlag);
			}
			
			else{
				log.info("Invalid Action item from Excel");
				Report_Functions.ReportEventFailure(doc,  "",  "Invalid Action Type described in Excel sheet - "+actionType, false);
			}
		}catch (Exception e) {
			log.info("Exception occured SQLDBCommonSelect. Exception is : "+e);
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			return false;
		}
		return result;
	}
	
	
	/**
	 * 
	 * @Objective <b>WebListSelectedValueCompare</b>
	 * @author <b>Lakshman</b>
	 * @since <b>21-OCT-16</b>
	 * 
	 */

	public static boolean WebListSelectedOptionCompare(String getValueFromPOM, String strTestObject, String strColumnName, int strExecEventFlag) throws Exception{

		String strData = null;
		boolean WebListSelectedValue = false;
		String selectedValue = null;
		try{

			if(strExecEventFlag==1){

				strData = RetrieveTestDataValue("WebListSelectedValue", strColumnName, strExecEventFlag);
			}	
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebListSelectedValue",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			selectedValue = new Select(selectByLocatorType(getValueFromPOM)).getFirstSelectedOption().getText();

			//if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				if(selectedValue.trim().equalsIgnoreCase(strData.trim())){
					Report_Functions.ReportEventSuccess(doc,"1","",strTestObject +"'s selected dropdown value '"+selectedValue + "' matches with the Expected Value '"+strData+"'" ,3);
					WebListSelectedValue = true;
				}else{
					Report_Functions.ReportEventFailure(doc,"",strTestObject +"'s selected dropdown value '"+selectedValue + "' does not matches with the Expected Value '"+strData+"'"  , true); 
					WebListSelectedValue=false;
				}
			/*}else{
				Report_Functions.ReportEventFailure(doc,"",strTestObject +"'s selected dropdown value '"+selectedValue + "'is not displayed" , true); 
				WebListSelectedValue=false;
			}*/

		}catch(StaleElementReferenceException e){

			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				return WebListSelectedValue;

			}

		}catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","Selected dropdown value is '" +  selectedValue + " is not shown in "+strTestObject+"" , true); 
			WebListSelectedValue=false;
			log.info("No Element Found to select text" + e);
		}

		return WebListSelectedValue;

	}
	
	/**
	 * 
	 * @Objective <b>SQLDBCheckValueExist</b>
	 * @author <b>Lakshman</b>
	 * @since <b>21-OCT-16</b>
	 * 
	 */
	
	public static boolean SQLDBCheckValueNotExist(String sqltablename, String strsqlcolumnname,String strsqlcondition,int strExecEventFlag)throws Exception  {
		boolean SQLDBCheckValueExist= false;
		String query=null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBCheckValueExist",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBCheckValueExist",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBCheckValueExist",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBCheckValueExist",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			rs_SQLServer = stmt.executeQuery(query);		

			int temp=0;	
			while(rs_SQLServer.next()){
				temp++;
			}

			if(temp >= 1){
				//stmt.execute(query);
				SQLDBCheckValueExist=false;
				Report_Functions.ReportEventFailure(doc, "SQLDBDelete", "Query : "+ query + " executed successfully and records exist for the Query.", false);

			}//If rows not available FALSE will be returned so no delete
			else if(temp < 1){
				Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDelete", "Query : "+ query + " has not records availbale in DB", 2);
				SQLDBCheckValueExist=true;
			}
			
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBCheckValueExist",  "Error occured while checking whether the executed query has any records (or) not. Error description is : "+ e.getMessage() +".", false);
			log.info("SQLDBCheckValueExist Error : " + e);
			SQLDBCheckValueExist=false;
		}
		return SQLDBCheckValueExist;
	}
	
	
	public static boolean SQLDBCheckNoOfRowsExist(String sqltablename, String strsqlcolumnname,String strsqlcondition,String  strNoOfRowsShouldBePresent,int strExecEventFlag)throws Exception  {
		boolean SQLDBCheckValueExist= false;
		String query=null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String noOfRowsShouldBePresent=null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBCheckValueExist",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBCheckValueExist",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBCheckValueExist",strsqlcondition,strExecEventFlag);
				noOfRowsShouldBePresent=RetrieveTestDataValue("SQLDBCheckValueExist",strNoOfRowsShouldBePresent,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBCheckValueExist",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			rs_SQLServer = stmt.executeQuery(query);		

			int temp=0;	
			while(rs_SQLServer.next()){
				temp++;
			}

			if(temp==(Integer.parseInt(noOfRowsShouldBePresent))){
				Report_Functions.ReportEventSuccess(doc, "1", "", "Actual No of Rows '"+temp+"' for the Query *"+query+"* matches with expected No of Rows '"+noOfRowsShouldBePresent+"'", 2);
				SQLDBCheckValueExist=true;
			}else{
				Report_Functions.ReportEventFailure(doc, "", "Actual No of Rows '"+temp+"' for the Query *"+query+"* does not match with expected No of Rows '"+noOfRowsShouldBePresent+"'", false);
				SQLDBCheckValueExist=true;
			}

		}catch (NullPointerException e) {
				Report_Functions.ReportEventFailure(doc,  "SQLDBCheckValueExist",  "Null Pointer exception occured while comparing the No.of Records", false);
				log.info(" NullPointerException Error : " + e);
				SQLDBCheckValueExist=false;
				
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBCheckValueExist",  "Error occured while checking whether the executed query has any records (or) not. Error description is : "+ e.getMessage() +".", false);
			log.info("Exception Error : " + e);
			SQLDBCheckValueExist=false;
		}
		return SQLDBCheckValueExist;
	}
	
	/**
	 * 
	 * @Objective <b>To start the Winservice</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>1-SEP-16</b>
	 */

	public static boolean WaitUntilPatternAppearsInLogFiles(String filePath,String startFileNameValue,String endFileNameValue,String strLineContains,String strPatterToCheck,String envVariableName ,int strExecEventFlag)throws Exception  {

		boolean result= false;
		String path = null;
		File[] listOfFile = null;
		String fileName = null;
		Scanner in = null;
		boolean found=false;
		String lineContains=null;
		String patterToCheck=null;
		String envVariable=null;
		String startFileNameFromExcel=null;
		String endFileNameFromExcel=null;

		try{
			if(strExecEventFlag == 1){
				path = Param.getProperty(RetrieveTestDataValue("ReadlogFiles", filePath, strExecEventFlag));
				startFileNameFromExcel= RetrieveTestDataValue("ReadlogFiles", startFileNameValue, strExecEventFlag);
				endFileNameFromExcel= RetrieveTestDataValue("ReadlogFiles", endFileNameValue, strExecEventFlag);
				lineContains= RetrieveTestDataValue("ReadlogFiles", strLineContains, strExecEventFlag);
				patterToCheck= RetrieveTestDataValue("ReadlogFiles", strPatterToCheck, strExecEventFlag);
				envVariable= RetrieveTestDataValue("ReadlogFiles", envVariableName, strExecEventFlag);
			}

			if(path==null ||startFileNameFromExcel==null ||endFileNameFromExcel==null ||lineContains==null||patterToCheck==null){
				Report_Functions.ReportEventFailure(doc,  "DeletelogFiles",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			File directory = new File("//\\" +path);
			
			boolean fileexist=false;
			int fileAppeartime=0;

			while(fileAppeartime<30){
				Thread.sleep(1000);
				listOfFile = directory.listFiles();
				if(listOfFile.length != 0){
					log.info("Directory has files");
					fileexist=true;
					break;

				}else{
					log.info("No File is available in directory. looping again with 60 secs");
					//Report_Functions.ReportEventFailure(doc,"","No File is not available in directory" , false);
					//return false;
				}

				log.info("No File is available in directory with 60 secs");
				
				fileAppeartime++;
			}

			if(fileexist){
				log.info("Files avialble in the directiory");

			}else{
				log.info("No File is available in directory for 60secs");
				Report_Functions.ReportEventFailure(doc,"","No File is not available in directory" , false);
				return false;
			}

			boolean foundstatus=false;
			int time=0;
			

			while(time<30){
				Thread.sleep(1000);	
				listOfFile = directory.listFiles();
				for(int i = 0; i<listOfFile.length; i++){
					log.info("i is : "+i);
					
					if(listOfFile[i].isFile()){
						fileName = listOfFile[i].getName();
						log.info("Found a file and name is : "+fileName);
						
						if(fileName.startsWith(startFileNameFromExcel) && fileName.endsWith(endFileNameFromExcel)){
							log.info("FileName exact match : "+fileName);
							fileName=listOfFile[i].getName();
							//fileName=fileNameFromExcel;
							foundstatus=true;
							//break;
						}
					}
				}
				
				if(foundstatus){
					log.info("FileName exact match is found. Braking the loop");
					break;
				}else{
					log.info("FileName not found in path. Continue the loop");
				}
				
				time++;
			}

			if(fileName == null){
				Report_Functions.ReportEventFailure(doc,"","File starting with Name '"+startFileNameFromExcel+"' file is not available in the directory" , false);
				log.info("Search File is not available in directory");
				return false;
			}

			/*if(!(fileName.equals(fileNameFromExcel))){
				Report_Functions.ReportEventFailure(doc,"","'"+fileNameFromExcel+"' file is not available in the directory" , false);
				log.info("Search File is not available in directory");
				return false;
			}*/

			File file =new File("//\\" +path +fileName);
			log.info(file.getAbsolutePath()+" || "+file.getName());

			int i=0;
			while(i<60){
				Thread.sleep(1000);
				
				try {
					in = new Scanner(file);
					
					while(in.hasNext())	{
						
						String line=in.nextLine();
						if(line.contains(lineContains)){
							log.info("Line contains Match found. Line -> "+line);
							Pattern p = Pattern.compile(patterToCheck);
							Matcher m = p.matcher(line);

							while (m.find()){
								found=true;
								log.info("Pattern Match found in the Line!");
								log.info("Match is : "+m.group(0));
								Report_Functions.ReportEventSuccess(doc, "1", "","Log containing the word '"+lineContains+"' has the Matched Expected Pattern '"+m.group(0) +"'", 3);
								if(!(envVariable.trim().equalsIgnoreCase("NA"))){
									log.info("Storing the Pattern Matched in the Env Variable '"+envVariable+"'");
									Runtimevalue.setProperty(envVariable, m.group(0));
									Report_Functions.ReportEventSuccess(doc, "1", "", "The Dynamic Value '"+m.group(0)+"' is successfully stored in the Runtime Varaible '"+envVariable+"'.", 3);
								}
								break;
							}

							if(found){
								log.info("Pattern Match found breaking the HasNext Loop");
								break;
							}else{
								log.info("Pattern Match not found. Checking teh next line that contains expected word");
							}
						}
					}

					if(found){
						log.info("Match found, Breaking the Time Loop");
						result=true;
						break;
					}else{
						log.info("Match not found. Continue the Loop...");
					}

				} catch (FileNotFoundException e) {
					log.info("FileNotFoundException occured match founder... :"+e);
					//e.printStackTrace();
				}catch (Exception e) {
					log.info("Excption occured match founder... : "+e);
					Report_Functions.ReportEventFailure(doc,  "",  "Error occured while finding the pattern from Winservice Log. Error description is : "+ e.getMessage() +".", false);
					//e.printStackTrace();
				}
				
				i++;
			}

			if(result){
				Report_Functions.ReportEventSuccess(doc, "1", "", "Log file starting with name '"+startFileNameFromExcel+"' from Path '"+path+"' contains the Expected Log Pattern '"+patterToCheck+"'", 3);
			}else{
				Report_Functions.ReportEventFailure(doc,  "","Log file starting with name '"+startFileNameFromExcel+"' from Path '"+path+"' does not contains the Expected Log Pattern '"+patterToCheck+"' within 60 secs", false);
			}

		} catch(Exception e) { 
			result=false;
			Report_Functions.ReportEventFailure(doc,  "",  "Error occured in the WinserviceWaitUntilPatternAppears. Error description is : "+ e.getMessage() +".", false);
			log.info("Error : " + e);
		}
		return result;
	}
	
	/**
	 * 
	 * @Objective <b>Compare Address from GAF ENV</b>
	 * @author <b>Lakshman</b>
	 * @since <b>2-NOV-16</b>
	 */
	
	public static boolean WebElementTextCompareFromGAF(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag)throws Exception{
		String actualResult=null;
		String strData=null;
		boolean WebElementValueCompareFromEnv=false;
		try{
			if (strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebElementValueCompareFromEnv",strColumnName,strExecEventFlag);
				strData=GAFValue.getProperty(strData);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveRRBSValueStoresInEnvVar",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			actualResult = selectByLocatorType(getValueFromPOM).getText();

		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementValueCompareFromEnv", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			WebElementValueCompareFromEnv=false;
		}

		try{
			if((actualResult.trim()).equalsIgnoreCase(strData.trim())){
				Report_Functions.ReportEventSuccess(doc,"1", "WebElementValueCompareFromEnv", "The  Actual Value '" +actualResult+ "' matches with the Expected value '"+strData+ "' in the input field '"+strTestObject+"'", 2);
				WebElementValueCompareFromEnv=true;
			}else{
				Report_Functions.ReportEventFailure(doc,"WebElementValueCompareFromEnv", "The  Actual Value '" +actualResult+ "' does not match with the Expected value '"+strData+ "' in the input field '"+strTestObject+"'", true);
				WebElementValueCompareFromEnv=false;
			}
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementValueCompareFromEnv", "Error occured while comparing actual and expected values. Error description is :"+e.getMessage(), true);
			WebElementValueCompareFromEnv=false;
		}
		return WebElementValueCompareFromEnv;
	}
	
	public static boolean SQLDBSelectConditionFromEnvvar(String sqltablename, String strsqlcolumnname,String strsqlcondition,String strenvironmentvariable,String strExpectedvalue,int strExecEventFlag)throws Exception  {
		
		boolean SQLDBSelectConditionEnvvar= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String SQL_condition_value = null;		
		String Expected_value = "";
		String Actual_Value = null;
		String environmentvariable=null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBSelectConditionEnvvar",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBSelectConditionEnvvar",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBSelectConditionEnvvar",strsqlcondition,strExecEventFlag);
				Expected_value=RetrieveTestDataValue("SQLDBSelectConditionEnvvar",strExpectedvalue,strExecEventFlag);
				Expected_value=RetrieveTestDataValue("SQLDBSelectConditionEnvvar",strExpectedvalue,strExecEventFlag);
				environmentvariable=RetrieveTestDataValue("SQLDBSelectConditionEnvvar",strenvironmentvariable,strExecEventFlag);
				SQL_condition_value = Runtimevalue.getProperty(environmentvariable);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBSelectConditionEnvvar",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"='"+ SQL_condition_value +"'";
			log.info("query is : "+query);
			rs_SQLServer = stmt.executeQuery(query);
			

		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelectConditionEnvvar",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQL_condition_value Error : " + e);
			SQLDBSelectConditionEnvvar=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1).trim();
			
		} catch(NullPointerException e){
			
			log.info("Null pointer occurred :"+e.getMessage());
			
		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelectConditionEnvvar",  "No Record found for this query: "+query, true);
			log.info("SQL_condition_value Error : ");
			SQLDBSelectConditionEnvvar=false;
		}

		try{
			if(!rs_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","SQLDBSelectConditionEnvvar", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+ SQL_condition_value +"' matches the expected value : '"+Expected_value+"'", 2);
					SQLDBSelectConditionEnvvar=true;
				}else if(!(Actual_Value.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelectConditionEnvvar",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+ SQL_condition_value +"' does not match the expected value : '"+Expected_value+"'", true);
					SQLDBSelectConditionEnvvar=false;
				}
			}
			
			else if(rs_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBSelectConditionEnvvar", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+ SQL_condition_value +"' matches with the expected value :'"+Expected_value+"'", 2);
					SQLDBSelectConditionEnvvar=true;
				}else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelectConditionEnvvar",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+ SQL_condition_value +"' does not match the expected value : '"+Expected_value+"'", true);  	 
					SQLDBSelectConditionEnvvar=false;
				}
			}
		}catch(NullPointerException e){
			
			log.info("Null pointer occurred :"+e.getMessage());
			
		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelect Error : " + e);
			SQLDBSelectConditionEnvvar=false;
		}
		return SQLDBSelectConditionEnvvar;
	}
	
		
	/**
	 * 
	 * @Objective <b>Verifies the SQL DB select</b>
	 * @author <b>LAKSHMAN</b>
	 * @since <b>10-NOV-2016</b>
	 */

	public static boolean ESHOPSQLDBSelectConditionEnvvarSuffix(String sqltablename, String strsqlcolumnname,String strsqlcondition,String strenvironmentvariable,String strExpectedvalue,String strSuffixToADD,int strExecEventFlag)throws Exception  {
		boolean ESHOPSQLDBSelectConditionEnvvar= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String SQL_condition_value = null;		
		String Expected_value = "";
		String Actual_Value = null;
		String suffixToADD =null;
		
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",strsqlcondition,strExecEventFlag);
				Expected_value=RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",strExpectedvalue,strExecEventFlag);
				SQL_condition_value = Runtimevalue.getProperty(RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",strenvironmentvariable,strExecEventFlag));
				suffixToADD=RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",strSuffixToADD,strExecEventFlag);
				
			}

			if(Table_name==null || Column_name==null || SQL_condition==null||suffixToADD==null){
				Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"='"+ SQL_condition_value+suffixToADD+"'";

			Eshop_SQLServer = EShopstmt.executeQuery(query);
			//Newly added on  20-09-2016 to validate system IP against DB value
			if (Expected_value.equalsIgnoreCase("GET_HOST_IP")){
				InetAddress IP=InetAddress.getLocalHost();
				Expected_value=	IP.getHostAddress();
			}


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("ESHOPSQLDBSelectConditionEnvvar Error : " + e);
			ESHOPSQLDBSelectConditionEnvvar=false;
		}

		try{
			Eshop_SQLServer.next();
			Actual_Value = Eshop_SQLServer.getString(1).trim();

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "No Record found for this query: "+query, true);
			log.info("ESHOPSQLDBSelectConditionEnvvar Error : ");
			ESHOPSQLDBSelectConditionEnvvar=false;
		}

		try{
			if(!Eshop_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","ESHOPSQLDBSelectConditionEnvvar", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches the expected value : '"+Expected_value+"'", 2);
					ESHOPSQLDBSelectConditionEnvvar=true;
				}else if(!(Actual_Value.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);
					ESHOPSQLDBSelectConditionEnvvar=false;
				}
			}

			else if(Eshop_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "ESHOPSQLDBSelectConditionEnvvar", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					ESHOPSQLDBSelectConditionEnvvar=true;
				}else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					ESHOPSQLDBSelectConditionEnvvar=false;
				}
			}
		}catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("ESHOPSQLDBSelectConditionEnvvar Error : " + e);
			ESHOPSQLDBSelectConditionEnvvar=false;
		}
		return ESHOPSQLDBSelectConditionEnvvar;
	}
	
	/**
	 * 
	 * @Objective <b>To compare the values both excel sheet and web element</b>
	 * @author <b>Lakshman</b>
	 * @since <b>11-NOV-16</b>
	 */

	public static boolean waitUntilWebElementTextAppear(String getValueFromPOM,String strTestObject) throws Exception  {
		boolean WebEditTextCompare=false;
		String ActualValue=null;
		boolean appears=false;
		int i=0;
		try {

			//ActualValue = element.getText();

			while(!appears && i<30){
				ActualValue = selectByLocatorType(getValueFromPOM).getText();
				log.info("Actual value is : '"+ActualValue+"' and length is : '"+ActualValue.length()+"'");
				if(ActualValue.length() == 0){
					log.info("Text not appears and i is : "+i);
					i++;
					Thread.sleep(1000);
				}else{
					log.info("Text appeared and i is : "+i+" value is : "+ActualValue);
					appears=true;
				}
			}
		}catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebEditTextCompare",  "Error occured while getting the text from the input field :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			WebEditTextCompare=false;
		}

		try{
			if(appears){
				Report_Functions.ReportEventSuccess(doc,"1", "WebEditTextCompare", "Text '" +ActualValue+ "' appeared in the Webelement '"+strTestObject+"' within 30 Secs", 2);
				WebEditTextCompare=true;
			}else{
				Report_Functions.ReportEventFailure(doc,"WebEditTextCompare", "No Text appeared in the Webelement '"+strTestObject+"' within 30 Secs", true);
				WebEditTextCompare=false;
			}
		}catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebEditTextCompare",  "Error occured while comparing actual and expected values. Error description is :"+e.getMessage(), true);
			WebEditTextCompare=false;
		}
		return WebEditTextCompare;
	}
	
	
	/*----------------------------------------- LAKSHMAN ***END***-----------------------------------------*/


	/*----------------------------------------- PRAVEEN CODE -----------------------------------------*/

	/**
	 * 
	 * @Objective <b>This method is to check the element is displayed on webpage</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>22-Aug-16</b>
	 * 
	 */

	public static boolean checkElementIsDisplayed(String getValueFromPOM, String strTestObject) throws Exception {

		boolean elementvisible;
		boolean checkElementIsDisplayed = false;
		try {
			elementvisible=selectByLocatorType(getValueFromPOM).isDisplayed();
			if(elementvisible){
				checkElementIsDisplayed = true;
				Report_Functions.ReportEventSuccess(doc, "1", "", "'"+strTestObject+"' is Displayed successfully", 3);
			}else{
				checkElementIsDisplayed = false;
				Report_Functions.ReportEventFailure(doc, "", "'"+strTestObject+"' is not Displayed", true);
			}
		} catch (Exception e) { 	
			log.info("Exception while finding visibility" + e);
			checkElementIsDisplayed = false;
		}
		return checkElementIsDisplayed;
	}

	/**
	 * 
	 * @Objective <b>This method is to switch the element to Webdriver Coordinates</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>22-Aug-16</b>
	 */

	public static boolean changeToCoordinates(String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{
			Runtimevalue.setProperty("checkScrollBarPresent", Param.getProperty("scrollBarPresent"));

			log.info("******* :"+ Runtimevalue.getProperty("checkScrollBarPresent"));

			Report_Functions.ReportEventSuccess (doc,"1","","Changed status for Scroll bar status as 'Yes' in "+strTestObject+" page",2);
			elementStatus = true;

		}catch(Exception e){

			log.info("Error occurred, while set the status for scroll bar");
			Report_Functions.ReportEventFailure(doc,"","Error occurred, while set the status for scroll bar in "+strTestObject+" page" , true);
			elementStatus = false;
		}

		return elementStatus;
	}

	/**
	 * 


		/**
	 * 
	 * @Objective <b>This method is to read the files in log folder, after to verify the confrmation message in log</b>
	 * @param filePath
	 * @param startsWith
	 * @param endsWith
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>24-Aug-16</b>
	 * 	 
	 */

	public static boolean readLogTopUpBal(String filePath, String startsWith, String endsWith, String textName, int strExecEventFlag) throws Exception{

		boolean functionStatus = false;

		String path = null;
		String startValue = null;
		String endValue = null;
		String textValue = null;
		File[] listOfFile = null;
		String fileName = null;
		String actualText = null;
		Pattern pattern = null;

		try{

			if(strExecEventFlag == 1){

				path = Param.getProperty(RetrieveTestDataValue("readLogTopUp", filePath, strExecEventFlag));
				startValue = RetrieveTestDataValue("readLogTopUp", startsWith, strExecEventFlag);
				endValue = RetrieveTestDataValue("readLogTopUp", endsWith, strExecEventFlag);
				textValue = RetrieveTestDataValue("readLogTopUp", textName, strExecEventFlag);

			}

			if(path==null || startValue==null || endValue==null || textValue==null){
				Report_Functions.ReportEventFailure(doc,  "readLogTopUp",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Get the filename by given startName and endName

			File directory = new File("//\\" +path);
			listOfFile = directory.listFiles();
			if(listOfFile.length != 0){
				for(int i = 0; i<listOfFile.length; i++){
					if(listOfFile[i].isFile()){
						fileName = listOfFile[i].getName();
						if(fileName.startsWith(startValue) && fileName.endsWith(endValue)){

							File ff = new File("//\\" +path +fileName);
							String fileContent = IOUtils.toString(ff.toURI());
							actualText = "Text is not available in log file";
							pattern = Pattern.compile(textValue);
							Matcher matcher = pattern.matcher(fileContent);

							while(matcher.find()){
								actualText = matcher.group(1)+matcher.group(2)+matcher.group(3)+matcher.group(4)+matcher.group(5);
								log.info("Text is matched");
								Report_Functions.ReportEventSuccess(doc,"1","","Text '"+actualText+"' is matched with expected text in the filename of '"+startValue+"'",3);
								return true;
							}

							log.info(actualText);
							functionStatus = false;
							Report_Functions.ReportEventFailure(doc,"",""+actualText+" in the filename of '"+startValue+"'" , true);

						}

					}

				}

			}else{

				log.info("File is not available in directory");
				Report_Functions.ReportEventFailure(doc,"","File is not available in directory" , true);
				return false;
			}

		}catch(Exception e){

			log.info("Exception occurs in read log file"+e.getMessage());
			functionStatus = false;
			Report_Functions.ReportEventFailure(doc,"","Error occurred, while match with expected text in the filename of '"+startValue+"'" , true);

		}

		return functionStatus;

	}

	/**
	 * 
	 * @Objective <b>This method is to read the files in log folder, after to verify the confrmation message in log</b>
	 * @param filePath
	 * @param startsWith
	 * @param endsWith
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>24-Aug-16</b>
	 * 	 
	 */

	public static boolean readLogTopUpBundle(String filePath, String startsWith, String endsWith, String textName, int strExecEventFlag) throws Exception{

		boolean functionStatus = false;

		String path = null;
		String startValue = null;
		String endValue = null;
		String textValue = null;
		File[] listOfFile = null;
		String fileName = null;
		String actualText = null;
		Pattern pattern = null;

		try{

			if(strExecEventFlag == 1){

				path = Param.getProperty(RetrieveTestDataValue("readLogTopUp", filePath, strExecEventFlag));
				startValue = RetrieveTestDataValue("readLogTopUp", startsWith, strExecEventFlag);
				endValue = RetrieveTestDataValue("readLogTopUp", endsWith, strExecEventFlag);
				textValue = RetrieveTestDataValue("readLogTopUp", textName, strExecEventFlag);

			}

			if(path==null || startValue==null || endValue==null || textValue==null){
				Report_Functions.ReportEventFailure(doc,  "readLogTopUp",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Get the filename by given startName and endName

			File directory = new File("//\\" +path);
			listOfFile = directory.listFiles();
			if(listOfFile.length != 0){
				for(int i = 0; i<listOfFile.length; i++){
					if(listOfFile[i].isFile()){
						fileName = listOfFile[i].getName();
						if(fileName.startsWith(startValue) && fileName.endsWith(endValue)){

							File ff = new File("//\\" +path +fileName);
							String fileContent = IOUtils.toString(ff.toURI());
							actualText = "Text is not available in log file";
							pattern = Pattern.compile(textValue);
							Matcher matcher = pattern.matcher(fileContent);

							while(matcher.find()){
								actualText = matcher.group(1)+matcher.group(2)+matcher.group(3)+matcher.group(4)+matcher.group(5)+matcher.group(6)+matcher.group(7)+matcher.group(8)+matcher.group(9);
								log.info("Text is matched");
								Report_Functions.ReportEventSuccess(doc,"1","","Text '"+actualText+"' is matched with expected text in the filename of '"+startValue+"'",3);
								return true;
							}

							log.info(actualText);
							functionStatus = false;
							Report_Functions.ReportEventFailure(doc,"",""+actualText+" in the filename of '"+startValue+"'" , true);

						}	
					}

				}

			}else{

				log.info("File is not available in directory");
				Report_Functions.ReportEventFailure(doc,"","File is not available in directory" , true);
				return false;
			}

		}catch(Exception e){

			log.info("Exception occurs in read log file"+e.getMessage());
			functionStatus = false;
			Report_Functions.ReportEventFailure(doc,"","Error occurred, while match with expected text in the filename of '"+startValue+"'" , true);

		}

		return functionStatus;

	}

	/**
	 * 
	 * @Objective <b>Modal Handle</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Lakshman</b>
	 * @since <b>22-AUG-16</b>
	 */

	public static boolean PerformAction_ModalHandle(String strTestObject) throws Exception{

		boolean handle =false;
		Object scrollBarPresent=false;

		try{
			log.info("Check element is appear");
			log.info("Sleeping for 30 secs");
			Thread.sleep(500);
			Thread.sleep(30000);
			log.info("Slept for 30 secs");
			//if(selectByLocatorType(getValueFromPOM).isDisplayed()){

			for(int i=0; i<60; i++){

				try{

					Thread.sleep(1000);
					log.info("Insdie IF.... i is -> "+i);
					//log.info("Element is appearing");

					scrollBarPresent = ((JavascriptExecutor)driver).executeScript("return document.activeElement.getElementsByClassName('modal-footer')[0].innerHTML.trim()!=null;");

					if((boolean) scrollBarPresent){
						handle = true;
						Report_Functions.ReportEventSuccess(doc,"1","","'The element '"+ strTestObject +"' is displayed.",3);
						log.info("Element is appeared");
						break;
					}


				}catch(WebDriverException e){
					//DO Nothinf
					log.info("WebDriverException caught inside for since Popup is not appeared");
				}






			}

			if((boolean) scrollBarPresent){
			}else{
				Report_Functions.ReportEventSuccess(doc,"1","","'The element '"+ strTestObject +"' is not displayed.",3);
			}


		}catch(StaleElementReferenceException e){

			Report_Functions.ReportEventSuccess(doc,"1","","'The element '"+ strTestObject +"' is not displayed",3);
			return PerformAction_ModalHandle(   strTestObject);


		}
		catch(Exception e){

			log.info("Element is not found :"+e);
			handle = false;
			Report_Functions.ReportEventFailure(doc,"","Exception occured. Error message is : "+ e +"." , true);
		}

		return handle;

	}

	/**
	 * 
	 * @Objective <b>This method is to connect the DB for HLR</b>
	 * @param dbserver
	 * @param portnumber
	 * @param dbname
	 * @param dbusername
	 * @param dbpassword
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>27-Aug-16</b>
	 * 
	 * 
	 */
	
	public static boolean HLRDBOpenConnection(String dbserver, String portnumber, String dbname, String dbusername, String dbpassword)throws Exception  {
		boolean elementStatus= false;

		String serverName = Param.getProperty(dbserver);
		String portNumber = Param.getProperty(portnumber);
		String sid = Param.getProperty(dbname);


		//Connection URL Syntax: "jdbc:oracle:thin:@://ipaddress:portnumber:db_name"        
		String dbUrl = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid; 

		//Database Username     
		String username = Param.getProperty(dbusername);   
		//Database Password     
		String password = Param.getProperty(dbpassword); 

		if(dbname==null || dbusername==null || dbpassword==null || dbserver==null || portnumber==null){
			Report_Functions.ReportEventFailure(doc,  "HLROpenConnection",  "Required details are not provided.", false);
			return false;
		}
		try {
			// Load the JDBC driver

			String driverName = "oracle.jdbc.OracleDriver";

			Class.forName(driverName);         
			//Create Connection to DB       
			hlrConnection = DriverManager.getConnection(dbUrl,username,password);
			//Create Statement Object       
			hlrStatement = hlrConnection.createStatement(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "HLROpenConnection", "HLR DB Connection is established from '"+sid+"' DB in '"+serverName+"' Successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "HLROpenConnection",  "Error occured while connecting to the HLR DB Server.Error description is : "+ e.getMessage() +".", false);
			log.info("HLROpenConnection Error : " + e);
		}
		return elementStatus;
	}

/**
	 * 
	 * @Objective <b>This method is to connect the DB for IMG</b>
	 * @param dbserver
	 * @param portnumber
	 * @param dbname
	 * @param dbusername
	 * @param dbpassword
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>01-Sep-16</b>
	 * 
	 * 
	 */
	
	public static boolean IMGDBOpenConnection(String dbserver, String portnumber, String dbname, String dbusername, String dbpassword)throws Exception  {
		boolean elementStatus= false;

		String serverName = Param.getProperty(dbserver);
		String portNumber = Param.getProperty(portnumber);
		String sid = Param.getProperty(dbname);


		//Connection URL Syntax: "jdbc:oracle:thin:@://ipaddress:portnumber:db_name"        
		String dbUrl = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid; 

		//Database Username     
		String username = Param.getProperty(dbusername);   
		//Database Password     
		String password = Param.getProperty(dbpassword); 

		if(dbname==null || dbusername==null || dbpassword==null || dbserver==null || portnumber==null){
			Report_Functions.ReportEventFailure(doc,  "IMGOpenConnection",  "Required details are not provided.", false);
			return false;
		}
		try {
			// Load the JDBC driver

			String driverName = "oracle.jdbc.OracleDriver";

			Class.forName(driverName);         
			//Create Connection to DB       
			imgConnection = DriverManager.getConnection(dbUrl,username,password);
			//Create Statement Object       
			imgStatement = imgConnection.createStatement(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "IMGOpenConnection", "IMG DB Connection is established from '"+sid+"' DB in '"+serverName+"' Successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "IMGOpenConnection",  "Error occured while connecting to the IMG DB Server.Error description is : "+ e.getMessage() +".", false);
			log.info("IMGOpenConnection Error : " + e);
		}
		return elementStatus;
	}
	
	/**
	 * 
	 * @Objective <b>This method is to close the connection DB for IMG</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>01-Sep-16</b>
	 * 
	 */
	
	public static boolean IMGDBCloseConnection()throws Exception  {
		boolean elementStatus= false;

		try {
			// closing DB Connection       
			imgConnection.close(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "IMGDBCloseConnection", "The IMG DB Connection disconnected successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "IMGDBCloseConnection",  "Error occured while closing the IMG DB.Error description is : "+ e.getMessage() +".", false);
			log.info("IMGDBCloseConnection Error : " + e);
		}
		return elementStatus;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies to update the data for IMG in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>01-Sep-16</b>
	 */

	public static boolean IMGDBUpdate(String sqltablename, String strsqlcolumnname,String strsqlcolumnvalue,String strsqlcondition,int strExecEventFlag)throws Exception  {

		boolean elementStatus= false;
		String Table_name = null;
		String Column_name = null;
		String Column_Value = null;
		String SQL_condition = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("IMGDBUpdate",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("IMGDBUpdate",strsqlcolumnname,strExecEventFlag);
				Column_Value=RetrieveTestDataValue("IMGDBUpdate",strsqlcolumnvalue,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("IMGDBUpdate",strsqlcondition,strExecEventFlag);
			}


			if(Table_name==null || Column_name==null || Column_Value==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "IMGDBUpdate",  "Required details are not provided in test data sheet.", false);
				return false;
			}


			//Query to Execute      
			String query = "update "+Table_name+" set "+Column_name+"="+Column_Value+" where "+SQL_condition;

			imgStatement.execute(query); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "IMGDBUpdate", "The IMG Update Query : "+ query + " executed successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			e.printStackTrace();
			Report_Functions.ReportEventFailure(doc,  "IMGDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("IMGDBUpdate Error : " + e);
		}
		return elementStatus;
	}
	
	/**
	 * 
	 * @Objective <b>Verifying the column value in IMG DB</b>
	 * @param rrbstablename
	 * @param rrbscolumnname
	 * @param rrbscondition
	 * @param rrbscolumnvalue
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>01-Sep-16</b>
	 */

	public static boolean IMGDBSelect(String imgtablename, String imgcolumnname, String imgcondition, String imgcolumnvalue, int strExecEventFlag)throws Exception  {
		boolean elementStatus= false;
		String tablename = null;
		String condition = null;
		String columnname = null;
		String columnvalue = null;
		String actualvalue = null;
		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("IMGDBSelect",imgtablename,strExecEventFlag);
				condition=RetrieveTestDataValue("IMGDBSelect",imgcondition,strExecEventFlag);
				columnname=RetrieveTestDataValue("IMGDBSelect",imgcolumnname,strExecEventFlag);
				columnvalue=RetrieveTestDataValue("IMGDBSelect",imgcolumnvalue,strExecEventFlag);
			}

			if(tablename==null || condition==null || columnname==null){
				Report_Functions.ReportEventFailure(doc,  "IMGDBSelect",  "Required details are not provided in test data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Errror log in reading file is "+e);
			throw e;
		}
		//Query to Execute      
		String query = "select "+ columnname +" from "+ tablename +" where "+ condition;
		
		try {
			imgResultset = imgStatement.executeQuery(query);
			while (imgResultset.next()){
				actualvalue = imgResultset.getString(1);
			}
			
			
			if(actualvalue.equalsIgnoreCase(columnvalue))
			{
				elementStatus=true;
				Report_Functions.ReportEventSuccess(doc, "1", "IMGDBSelect", "The actual value : '"+ actualvalue + "' in column : '"+columnname+"' of table : '"+tablename+"' with condition : '"+condition+"' matches with the expected value :"+ columnvalue, 2);
			} else {
				elementStatus=false;
				Report_Functions.ReportEventFailure(doc,  "IMGDBSelect",  "The actual value : '"+ actualvalue + "' in column : '"+columnname+"' of table : '"+tablename+"' with condition : '"+condition+"' doesn't matches with the expected value :"+ columnvalue, false);	
			}
		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "IMGDBSelect",  "Error occured while executing the IMG query.Error description is : "+ e.getMessage() +".", false);
			log.info("IMGDBSelect Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to close the connection DB for HLR</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>27-Aug-16</b>
	 * 
	 */
	
	public static boolean HLRDBCloseConnection()throws Exception  {
		boolean elementStatus= false;

		try {
			// closing DB Connection       
			hlrConnection.close(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "HLRDBCloseConnection", "The HLR DB Connection disconnected successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "HLRDBCloseConnection",  "Error occured while closing the HLR DB.Error description is : "+ e.getMessage() +".", false);
			log.info("HLRDBCloseConnection Error : " + e);
		}
		return elementStatus;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies to update the data for HLR in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>27-Aug-16</b>
	 */

	public static boolean HLRDBUpdate(String sqltablename, String strsqlcolumnname,String strsqlcolumnvalue,String strsqlcondition,int strExecEventFlag)throws Exception  {

		boolean elementStatus= false;
		String Table_name = null;
		String Column_name = null;
		String Column_Value = null;
		String SQL_condition = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("HLRDBUpdate",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("HLRDBUpdate",strsqlcolumnname,strExecEventFlag);
				Column_Value=RetrieveTestDataValue("HLRDBUpdate",strsqlcolumnvalue,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("HLRDBUpdate",strsqlcondition,strExecEventFlag);
			}


			if(Table_name==null || Column_name==null || Column_Value==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "HLRDBUpdate",  "Required details are not provided in test data sheet.", false);
				return false;
			}


			//Query to Execute      
			String query = "update "+Table_name+" set "+Column_name+"="+Column_Value+" where "+SQL_condition;

			hlrStatement.execute(query); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "HLRDBUpdate", "The HLR Update Query : "+ query + " executed successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "HLRDBUpdate",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("HLRDBUpdate Error : " + e);
		}
		return elementStatus;
	}
	
	/**
	 * 
	 * @Objective <b>Verifying the column value in HLR DB</b>
	 * @param rrbstablename
	 * @param rrbscolumnname
	 * @param rrbscondition
	 * @param rrbscolumnvalue
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>27-Aug-16</b>
	 */

	public static boolean HLRDBSelect(String hlrtablename, String hlrcolumnname, String hlrcondition, String hlrcolumnvalue, int strExecEventFlag)throws Exception  {
		boolean elementStatus= false;
		String tablename = null;
		String condition = null;
		String columnname = null;
		String columnvalue = null;
		String actualvalue = null;
		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("HLRDBSelect",hlrtablename,strExecEventFlag);
				condition=RetrieveTestDataValue("HLRDBSelect",hlrcondition,strExecEventFlag);
				columnname=RetrieveTestDataValue("HLRDBSelect",hlrcolumnname,strExecEventFlag);
				columnvalue=RetrieveTestDataValue("HLRDBSelect",hlrcolumnvalue,strExecEventFlag);
			}

			if(tablename==null || condition==null || columnname==null){
				Report_Functions.ReportEventFailure(doc,  "HLRDBSelect",  "Required details are not provided in test data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Errror log in reading file is "+e);
			throw e;
		}
		//Query to Execute      
		String query = "select "+ columnname +" from "+ tablename +" where "+ condition;
		
		try {
			hlrResultset = hlrStatement.executeQuery(query);
			while (hlrResultset.next()){
				actualvalue = hlrResultset.getString(1);
			}
			
			
			if(actualvalue.equalsIgnoreCase(columnvalue))
			{
				elementStatus=true;
				Report_Functions.ReportEventSuccess(doc, "1", "HLRDBSelect", "The actual value : '"+ actualvalue + "' in column : '"+columnname+"' of table : '"+tablename+"' with condition : '"+condition+"' matches with the expected value :"+ columnvalue, 2);
			} else {
				elementStatus=false;
				Report_Functions.ReportEventFailure(doc,  "HLRDBSelect",  "The actual value : '"+ actualvalue + "' in column : '"+columnname+"' of table : '"+tablename+"' with condition : '"+condition+"' doesn't matches with the expected value :"+ columnvalue, false);	
			}
		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "HLRDBSelect",  "Error occured while executing the HLR query.Error description is : "+ e.getMessage() +".", false);
			log.info("HLRDBSelect Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to read the files in log folder, after to verify the confirmation message in log</b>
	 * @param filePath
	 * @param startsWith
	 * @param endsWith
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>29-Aug-16</b>
	 * 
	 */
	
	public static boolean readLogCallForward(String filePath, String startsWith, String endsWith, String textName, int strExecEventFlag) throws Exception{

		boolean functionStatus = false;

		String path = null;
		String startValue = null;
		String endValue = null;
		String textValue = null;
		File[] listOfFile = null;
		String fileName = null;
		String actualText = null;
		Pattern pattern = null;

		try{

			if(strExecEventFlag == 1){

				path = Param.getProperty(RetrieveTestDataValue("readLogCallForward", filePath, strExecEventFlag));
				startValue = RetrieveTestDataValue("readLogCallForward", startsWith, strExecEventFlag);
				endValue = RetrieveTestDataValue("readLogCallForward", endsWith, strExecEventFlag);
				textValue = RetrieveTestDataValue("readLogCallForward", textName, strExecEventFlag);

			}

			if(path==null || startValue==null || endValue==null || textValue==null){
				Report_Functions.ReportEventFailure(doc,  "readLogCallForward",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Get the filename by given startName and endName

			File directory = new File("//\\" +path);
			listOfFile = directory.listFiles();
			if(listOfFile.length != 0){
				for(int i = 0; i<listOfFile.length; i++){
					if(listOfFile[i].isFile()){
						fileName = listOfFile[i].getName();
						if(fileName.startsWith(startValue) && fileName.endsWith(endValue)){
							
							File ff = new File("//\\" +path +fileName);
							String fileContent = IOUtils.toString(ff.toURI());
							actualText = "Text is not available in log file";
							pattern = Pattern.compile(textValue);
							Matcher matcher = pattern.matcher(fileContent);
							
							while(matcher.find()){
								actualText = matcher.group(1)+matcher.group(2)+matcher.group(3)+matcher.group(4)+matcher.group(5);
								log.info("Text is matched");
								Report_Functions.ReportEventSuccess(doc,"1","","Text '"+actualText+"' is matched with expected text in the filename of '"+startValue+"'",3);
								return true;
							}

							log.info(actualText);
							functionStatus = false;
							Report_Functions.ReportEventFailure(doc,"",""+actualText+" in the filename of '"+startValue+"'" , true);
							
							}

					}

				}

			}else{

				log.info("File is not available in directory");
				Report_Functions.ReportEventFailure(doc,"","File is not available in directory" , true);
				return false;
			}

		}catch(Exception e){

			log.info("Exception occurs in read log file"+e.getMessage());
			functionStatus = false;
			Report_Functions.ReportEventFailure(doc,"","Error occurred, while match with expected text in the filename of '"+startValue+"'" , true);

		}

		return functionStatus;

	}
	
	//	MS ACCESS DB connection
	
	/**
	 *
	 * @Objective <b>Open Connection for MS Access DB</b>
	 * @param filePath
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>08-Sep-16</b>
	 * 
	 */
	
	public static boolean msAccessDBOpenConnection(String filepath)throws Exception  {
		
		boolean elementStatus= false;

		try{
			
			msAcsConnection=DriverManager.getConnection("jdbc:ucanaccess:"+filepath+"");
			msAcsStatement = msAcsConnection.createStatement();
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "msAccessDBOpenConnection", "MSAccess DB Connection is established from '"+filepath+"' Successfully.", 2);

			
		}catch(Exception e){
			
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "msAccessDBOpenConnection",  "Error occured while connecting to the MSAccess DB Server.Error description is : "+ e.getMessage() +".", false);
			log.info("msAccessDBOpenConnection Error : " + e);
			e.printStackTrace();
			
		}

		return elementStatus;
		
	}
	
	/**
	 * 
	 * @Objective <b>This method is to close the connection DB for MS Access DB</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>08-Sep-16</b>
	 * 
	 */
	
	public static boolean msAccessCloseConnection()throws Exception  {
		boolean elementStatus= false;

		try {
			// closing DB Connection       
			msAcsConnection.close(); 
			elementStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "msAccessCloseConnection", "The MS Access DB Connection disconnected successfully.", 2);

		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "msAccessCloseConnection",  "Error occured while closing the MS Access DB.Error description is : "+ e.getMessage() +".", false);
			log.info("msAccessCloseConnection Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Select query for MS Access DB</b>
	 * @param msAcstablename
	 * @param msAcscolumnname
	 * @param msAcscondition
	 * @param msAcscolumnvalue
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>08-Sep-16</b>
	 */
	
	public static boolean msAccessDBSelect(String msAcstablename, String msAcscolumnname, String msAcscondition, String msAcscolumnvalue, int strExecEventFlag)throws Exception  {
		boolean elementStatus= false;
		String tablename = null;
		String condition = null;
		String columnname = null;
		String columnvalue = null;
		String actualvalue = null;
		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("msAccessDBSelect",msAcstablename,strExecEventFlag);
				condition=RetrieveTestDataValue("msAccessDBSelect",msAcscondition,strExecEventFlag);
				columnname=RetrieveTestDataValue("msAccessDBSelect",msAcscolumnname,strExecEventFlag);
				columnvalue=RetrieveTestDataValue("msAccessDBSelect",msAcscolumnvalue,strExecEventFlag);
			}

			if(tablename==null || condition==null || columnname==null){
				Report_Functions.ReportEventFailure(doc,  "msAccessDBSelect",  "Required details are not provided in test data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Errror log in reading file is "+e);
			throw e;
		}
		//Query to Execute      
		String query = "select "+ columnname +" from "+ tablename +" where "+ condition;
		
		try {
			msAcsResultset = msAcsStatement.executeQuery(query);
			while (msAcsResultset.next()){
				actualvalue = msAcsResultset.getString(1);
			}
			
			
			if(actualvalue.equalsIgnoreCase(columnvalue))
			{
				elementStatus=true;
				Report_Functions.ReportEventSuccess(doc, "1", "msAccessDBSelect", "The actual value : '"+ actualvalue + "' in column : '"+columnname+"' of table : '"+tablename+"' with condition : '"+condition+"' matches with the expected value :"+ columnvalue, 2);
			} else {
				elementStatus=false;
				Report_Functions.ReportEventFailure(doc,  "msAccessDBSelect",  "The actual value : '"+ actualvalue + "' in column : '"+columnname+"' of table : '"+tablename+"' with condition : '"+condition+"' doesn't matches with the expected value :"+ columnvalue, false);	
			}
		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "msAccessDBSelect",  "Error occured while executing the IMG query.Error description is : "+ e.getMessage() +".", false);
			log.info("msAccessDBSelect Error : " + e);
		}
		return elementStatus;
	}
	
	
	/**
	 * 
	 * @Objective <b>This method is to compare the date values with SQL DB server and webpage value and expected date pattern</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>28-Sep-16</b>
	 */
	
	public static boolean DateCompareSQLDBValue(String getValueFromPOM, String datePattern, String strTestObject)throws Exception  {
		
		boolean elementStatus= false;
		
		String query = null;
		String actualvalue = null;
		String getDateValueFromWeb = null;
		String splittedValueFromWeb = null;
		
		if(datePattern.equalsIgnoreCase("dd/mm/yyyy")){
			
			query = "Select Convert(varchar(10),getDate(),103)";
			
		}else if(datePattern.equalsIgnoreCase("dd-mm-yyyy")){
			
			query = "Select Convert(varchar(10),getDate(),105)";
		}
		
		getDateValueFromWeb = selectByLocatorType(getValueFromPOM).getText();
		splittedValueFromWeb = getDateValueFromWeb.split("\\ ")[0];
		
		try {
			rs_SQLServer = stmt.executeQuery(query);
			while (rs_SQLServer.next()){
				actualvalue = rs_SQLServer.getString(1);
			}
			
			if(actualvalue.equalsIgnoreCase(splittedValueFromWeb))
			{
				elementStatus=true;
				Report_Functions.ReportEventSuccess(doc, "1", "DateCompareSQLDBValue", "The actual value : '"+actualvalue+"' in SQL DB is matched with the expected value from webpage :'"+splittedValueFromWeb, 2);
			} else {
				elementStatus=false;
				Report_Functions.ReportEventFailure(doc,  "DateCompareSQLDBValue",  "The actual value : '"+actualvalue+"' in SQL DB doesn't match with the expected value from webpage :"+splittedValueFromWeb, false);	
			}
		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "DateCompareSQLDBValue",  "Error occured while executing the Date ompare SQLDB query.Error description is : "+ e.getMessage() +".", false);
			log.info("DateCompareSQLDBValue Error : " + e);
		}
		return elementStatus;
	}
	
	
	/**
	 * 
	 * @Objective <b>This method is to compare the date values with RRBS DB server and webpage value and expected date pattern</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>28-Sep-16</b>
	 */
	
	
	public static boolean DateCompareRRBSDBValue(String getValueFromPOM, String datePattern, String strTestObject)throws Exception  {
		
		boolean elementStatus= false;
		
		
		String query = null;
		String actualvalue = null;
		String getDateValueFromWeb = null;
		String splittedValueFromWeb = null;
		
				
			query = "SELECT TO_CHAR (SYSDATE, '"+datePattern+"') AS today FROM dual";
			
		getDateValueFromWeb = selectByLocatorType(getValueFromPOM).getText();
		splittedValueFromWeb = getDateValueFromWeb.split("\\ ")[0];
		
		try {
			rrbsresultset = rrbsstatement.executeQuery(query);
			while (rrbsresultset.next()){
				actualvalue = rrbsresultset.getString(1).trim();
			}
			
			if(actualvalue.equalsIgnoreCase(splittedValueFromWeb))
			{
				elementStatus=true;
				Report_Functions.ReportEventSuccess(doc, "1", "DateCompareRRBSDBValue", "The actual value : '"+actualvalue+"' in RRBS DB is matched with the expected value from webpage :'"+splittedValueFromWeb, 2);
			} else {
				elementStatus=false;
				Report_Functions.ReportEventFailure(doc,  "DateCompareRRBSDBValue",  "The actual value : '"+actualvalue+"' in RRBS DB doesn't match with the expected value from webpage :"+splittedValueFromWeb, false);	
			}
		} catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "DateCompareRRBSDBValue",  "Error occured while executing the Date ompare RRBS DB query.Error description is : "+ e.getMessage() +".", false);
			log.info("DateCompareRRBSDBValue Error : " + e);
		}
		return elementStatus;
	}
	
	
	
		/**
	 * 
	 * @Objective <b>This method is to verify the SMS log file with expected pattern</b>
	 * @param filePath
	 * @param startsWith
	 * @param endsWith
	 * @param textName
	 * @param strExecEventFlag
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>30-Sep-16</b>
	 */
	
	public static boolean readLogSMSFiles(String filePath, String startsWith, String endsWith, String textName, int strExecEventFlag) throws Exception{

		boolean functionStatus = false;

		String path = null;
		String startValue = null;
		String endValue = null;
		String textValue = null;
		File[] listOfFile = null;
		String fileName = null;
		String actualText = null;
		Pattern pattern = null;

		try{

			if(strExecEventFlag == 1){

				path = Param.getProperty(RetrieveTestDataValue("readLogSMSFiles", filePath, strExecEventFlag));
				startValue = RetrieveTestDataValue("readLogSMSFiles", startsWith, strExecEventFlag);
				endValue = RetrieveTestDataValue("readLogSMSFiles", endsWith, strExecEventFlag);
				textValue = RetrieveTestDataValue("readLogSMSFiles", textName, strExecEventFlag);

			}

			if(path==null || startValue==null || endValue==null || textValue==null){
				Report_Functions.ReportEventFailure(doc,  "readLogSMSFiles",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Get the filename by given startName and endName

			File directory = new File("//\\" +path);
			listOfFile = directory.listFiles();
			if(listOfFile.length != 0){
				for(int i = 0; i<listOfFile.length; i++){
					if(listOfFile[i].isFile()){
						fileName = listOfFile[i].getName();
						if(fileName.startsWith(startValue) && fileName.endsWith(endValue)){
							//Read the file and match the expected value

							File ff = new File("//\\" +path +fileName);
							String fileContent = IOUtils.toString(ff.toURI());
							actualText = "Text is not available in log file";
							pattern = Pattern.compile(textValue);
							Matcher matcher = pattern.matcher(fileContent);

							while(matcher.find()){
								actualText = matcher.group(1)+matcher.group(2)+matcher.group(3);
								log.info("Text is matched");
								Report_Functions.ReportEventSuccess(doc,"1","","Text '"+actualText+"' is matched with expected text "+textValue+" in the filename of '"+startValue+"'",3);
								return true;
							}
						}

					}

				}

			}else{

				log.info("File is not available in directory");
				Report_Functions.ReportEventFailure(doc,"","File is not available in directory" , true);
				return false;


			}

			log.info(actualText);
			functionStatus = false;
			Report_Functions.ReportEventFailure(doc,"","The expected text '" + textValue + "' doesn't exist in the filename of '"+startValue+"'" , true);


		}catch(Exception e){

			log.info("Exception occurs in read log file"+e.getMessage());
			functionStatus = false;
			Report_Functions.ReportEventFailure(doc,"","Error occurred, while match with expected text "+textValue+" in the filename of '"+startValue+"'" , true);

		}

		return functionStatus;

	}
	
	
	
	
	public static boolean alertAccept() throws Exception{

		boolean functionStatus = false;
		
		try{

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.getText();
			alert.accept();
			functionStatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "alertAccept", "Alert is accepted successfully", 2);

		}catch(Exception e){

			log.info("Exception occurred, while accepting the alert :"+e.getMessage());
			Report_Functions.ReportEventFailure(doc,"","Alert is not accepted" , true);
			functionStatus = false;

		}

		return functionStatus;

	}
	
	
	
	/**
	 * 
	 * @Objective <b>This method is to compare and verifies the values from SQLDB to UI dropdown values</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param getValueFromPOM
	 * @param strExecEventFlag
	 * @author <b>Praveen</b>
	 * @since <b>9-Nov-2016</b>
	 */

	public static boolean weblistUICompareSQLDBValues(String getValueFromPOM, String strTestObject, String sqltablename, String strsqlcolumnname,String strsqlcondition,int itemscount_not_consider,int strExecEventFlag)throws Exception  {

		boolean WeblistSQLDBitemsverify= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String[] sqldbvalues = null;
		String[] weblistvalues = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("WeblistSQLDBitemsverify",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("WeblistSQLDBitemsverify",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("WeblistSQLDBitemsverify",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "Required details are not provided in the Data Sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";

			rs_SQLServer = stmt.executeQuery(query);

			List<String> rowValues = new ArrayList<String>();

			while (rs_SQLServer.next()) {

				rowValues.add(rs_SQLServer.getString(1));
			}   
			// You can then put this back into an array if necessary
			sqldbvalues = (String[]) rowValues.toArray(new String[rowValues.size()]);

		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("WeblistSQLDBitemsverify Error : " + e);
			WeblistSQLDBitemsverify=false;
		}


		try{

			Select se = new Select(selectByLocatorType(getValueFromPOM));
			List<WebElement> options = se.getOptions();

			//if you want to get all elements text into array list
			List<String> all_elements_text=new ArrayList<String>();

			for(int j=1; j<options.size(); j++){

				//loading text of each element in to array all_elements_text
				all_elements_text.add(options.get(j).getText());

			}

			weblistvalues = (String[]) all_elements_text.toArray(new String[all_elements_text.size()]);

			log.info("sqldbvalues length is : "+sqldbvalues.length);
			log.info("weblistvalues length is : "+weblistvalues.length);

			if(sqldbvalues.length == weblistvalues.length - itemscount_not_consider)
			{
				if(sqldbvalues.length==0){
					
					log.info("No value present in DB as well list");
					Report_Functions.ReportEventSuccess(doc, "1","", "No Dropdown Value is Present in the Dropdown '"+strTestObject+"' and the corresponding Table", 2);
					WeblistSQLDBitemsverify=true;
				
				}else{
					for (int i=0; i<sqldbvalues.length; i++){

						if (weblistvalues[i+1].equals(sqldbvalues[i])){
							Report_Functions.ReportEventSuccess(doc, "1","WeblistSQLDBitemsverify", "The actual value : '"+ weblistvalues[i+1] +"' in the dropdown : "+ strTestObject +"' matches the expected value : '"+ sqldbvalues[i] +"'.", 2);
							WeblistSQLDBitemsverify=true;
						} else{
							Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "The actual value : '"+ weblistvalues[i+1] +"' in the dropdown : "+ strTestObject +"' doesn't matches the expected value : '"+ sqldbvalues[i] +"'.", true);
							WeblistSQLDBitemsverify=false;
						}
					}
				}
			} else {
				Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "The number of items present in the dropdown : "+ strTestObject +"' doesn't matches with the number of items in the table : '"+ Table_name +"' with condition : '"+ SQL_condition +"'.", true);
				WeblistSQLDBitemsverify=false;
			}

		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("WeblistSQLDBitemsverify Error : " + e);
			WeblistSQLDBitemsverify=false;
		}
		return WeblistSQLDBitemsverify;
	}
	
	
	
	/**
	 * 
	 * @Objective <b>This method is to compare the date values with RRBS DB server and webpage value and expected date pattern</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>17-Nov-16</b>
	 */
	
	
	public static boolean RRBSDateCompareFromDB(String sqltablename, String strsqlcolumnname,String strsqlcondition,String Date_Format,int strExecEventFlag)throws Exception  {

		boolean RRBSDBDateCompare= false;
		
		String dateQuery = null;
		String query = null;  
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String datePattern = null;
		String dbDatevalue = null;
		String dbValue = null;
		String splittedDBValue = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("RRBSDateCompareFromDB",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("RRBSDateCompareFromDB",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("RRBSDateCompareFromDB",strsqlcondition,strExecEventFlag);
				datePattern = RetrieveTestDataValue("RRBSDateCompareFromDB",Date_Format,strExecEventFlag);
				
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDateCompareFromDB",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Date query to execute
			dateQuery = "SELECT TO_CHAR (SYSDATE, '"+datePattern+"') AS today FROM dual";

				rrbsresultset = rrbsstatement.executeQuery(dateQuery);
				while (rrbsresultset.next()){
				dbDatevalue = rrbsresultset.getString(1).trim();
				}
				
			// Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			rrbsresultset = rrbsstatement.executeQuery(query);
			while (rrbsresultset.next()){
				dbValue = rrbsresultset.getString(1).trim();
				
				splittedDBValue = dbValue.split("\\ ")[0];
				
				}
			
			
			if(splittedDBValue.trim().equalsIgnoreCase(dbDatevalue)){
				
				Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBDateCompare", "The actual date : '"+splittedDBValue+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' matches with the expected date : '"+dbDatevalue+" from the DB' ", 2);
				RRBSDBDateCompare=true;
				
			}else{
				
				Report_Functions.ReportEventFailure(doc,  "RRBSDateCompareFromDB",  "The actual date : '"+splittedDBValue+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' is not matched with the expected date : '"+dbDatevalue+" from the DB' "+ ".", true);
				RRBSDBDateCompare=false;
				
				
			}
			
		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "RRBSDateCompareFromDB",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDateCompareFromDB Error : " + e);
			RRBSDBDateCompare=false;
			}
		
		return RRBSDBDateCompare;
		
	}
		

	
	
	/**
	 * 
	 * @Objective <b>Compare the Date in Env variable to date in Eshop DB</b>
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>21-Nov-16</b>
	 */
	
	public static boolean ESHOPSelectStoreValueInEnvVar(String sqltablename, String strsqlcolumnname,String strsqlcondition,String envVariable,int strExecEventFlag)throws Exception  {
		boolean SQLDBSelect= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Actual_Value = null;
		String envVariableName=null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("ESHOPSelectStoreValueInEnvVar",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("ESHOPSelectStoreValueInEnvVar",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("ESHOPSelectStoreValueInEnvVar",strsqlcondition,strExecEventFlag);
				envVariableName=RetrieveTestDataValue("ESHOPSelectStoreValueInEnvVar",envVariable,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null||envVariableName==null){
				Report_Functions.ReportEventFailure(doc,  "ESHOPSelectStoreValueInEnvVar",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			Eshop_SQLServer = EShopstmt.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "ESHOPSelectStoreValueInEnvVar",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("ESHOPSelectStoreValueInEnvVar Error : " + e);
			SQLDBSelect=false;
		}

		try{
			Eshop_SQLServer.next();
			Actual_Value = Eshop_SQLServer.getString(1).trim();

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "ESHOPSelectStoreValueInEnvVar",  "No Record found for this query: "+query, true);
			log.info("ESHOPSelectStoreValueInEnvVar Error : ");
			SQLDBSelect=false;
		}


		try{
			if(!Eshop_SQLServer.wasNull()){
				Runtimevalue.setProperty(envVariableName, Actual_Value);
				Report_Functions.ReportEventSuccess(doc, "1","", "Dynamic value '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+" is stored in the Runtime Variable '"+envVariableName+"'", 2);
				SQLDBSelect=true;
			}
		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "ESHOPSelectStoreValueInEnvVar",  "Error occured while stroing the values env variable from in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("ESHOPSelectStoreValueInEnvVar Error : " + e);
			SQLDBSelect=false;
		}
		return SQLDBSelect;
	}


	/*----------------------------------------- PRAVEEN ***END***-----------------------------------------*/


	/*----------------------------------------- MURALI CODE -----------------------------------------*/

	/**
	 * 
	 * @Objective <b>Check for the webelement is  editable<b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Muralimohan M</b>
	 * @since <b>24-May-2016</b>
	 */

	public static boolean WebElementEditable(String getValueFromPOM, String strTestObject) throws Exception {

		String elementenable;
		boolean WebElementEnabled = false;
		try {
			elementenable=selectByLocatorType(getValueFromPOM).getAttribute("readonly");
			if(elementenable.equalsIgnoreCase("false")){
				WebElementEnabled = true;
				Report_Functions.ReportEventSuccess(doc, "1", "WebElementEditable", "The object '"+strTestObject+"' is  editable as expected.", 3);
			}else{
				WebElementEnabled=false;
				Report_Functions.ReportEventFailure(doc, "WebElementEditable", "The object '"+strTestObject+"' is not editable.", true);
			}

		} catch (Exception e) { 	
			log.info("Exception while finding enabled or disabled:" + e.getLocalizedMessage());
			WebElementEnabled=false;
		}
		return WebElementEnabled;
	}

	/**
	 * 
	 * @Objective <b>This method is to check whether scroll bar is present on page</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Praveen Lakshmanan</b>
	 * @since <b>22-Jun-16</b>
	 */

	public static boolean WebElementNotPresent(String getValueFromPOM, String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{

			//Xpath for Javascript executor, if value is present will return as true or not present will return as false
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Object webElementObj = js.executeScript("return document.evaluate(\""+getValueFromPOM+"\",document.body,null,XPathResult.UNORDERED_NODE_ITERATOR_TYPE,null).iterateNext()!=null;");
			log.info("webElementObj:"+webElementObj.toString());
			if(webElementObj.equals(false)){

				log.info("Scroll bar is not available on page");
				Report_Functions.ReportEventSuccess (doc,"1","","WebELement  '"+strTestObject+"' is not available on page",2);
				elementStatus = true;

			}else{				
				log.info("Scroll bar is available on page");
				Report_Functions.ReportEventFailure (doc,"","WebElement  '"+strTestObject+"' is available",true);
				elementStatus = true;
			}

		}catch(Exception e){

			log.info("Error occurred, while finding the scroll bar element on page");
			Report_Functions.ReportEventFailure(doc,"","WebElement is available on "+strTestObject+" page" , true);
			elementStatus = false;
		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to select the values from the dropdown</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebListSelectByValue(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag)throws Exception  {

		String strData=null;
		boolean WebListSelect=false;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebListSelectByValue",strColumnName,strExecEventFlag);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebListSelectByValue",  "Required details are not provided in the data sheet.", false);
				return false;
			}

			new Select(selectByLocatorType(getValueFromPOM)).selectByValue(strData);
			Report_Functions.ReportEventSuccess(doc,"1","","The Item '" +  strData + "' is selected from the  '"+strTestObject+"' List box successfully" ,3);
			WebListSelect=true;

		} catch (StaleElementReferenceException e) {
			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				return WebListSelect(getValueFromPOM, strTestObject, strColumnName, strExecEventFlag);

			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","The Item '" +  strData + " was not selected from the  '"+strTestObject+"' List box " , true); 
			WebListSelect=false;
			log.info("No Element Found to select text" + e);
		}
		return WebListSelect;
	}
	
	/**
	 * 
	 * @Objective <b>This method is to wait the element is exist in webpage and synchronized with webdriver wait for 120 seconds</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Muralimohan M</b>
	 * @since <b>27-Jul-16</b>
	 */

	public static boolean ScrollIntoElement(String getValueFromPOM,  String strTestObject) throws Exception {
		
		boolean ScrollIntoElement=false;
		int timeout=30;
		try{
			for(int i=0;i<timeout;i++){
				WebElement elementenable=selectByLocatorType(getValueFromPOM);
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", elementenable);
				if(elementenable.isDisplayed()){
					Report_Functions.ReportEventSuccess(doc,"1","","The Item focus  moved to Object named '"+strTestObject+"' successfully with in '"+i+"' seconds" ,3);
					ScrollIntoElement=true;
					break;
				}
				Thread.sleep(1000L);
			}
		
		
		}catch(Exception e){
			Report_Functions.ReportEventFailure(doc,"","The Item focus not moved to Object named '"+strTestObject+"' waited for '"+timeout+"' seconds" , true); 
			return ScrollIntoElement;
		}
		return ScrollIntoElement;
	}

	/**
	 * @Objective <b>This method is to read the given single Log file content and match with the expected content<b>
	 * @param filePath Path of the file
	 * @param fileNameValue filename with its extension value
	 * @param textName Content to match
	 * @author <b>Muralimohan</b>
	 * @since <b>07-SEP-16</b>
	 */

	public static boolean readLogFiles_2(String filePath, String fileNameValue, String textName, int strExecEventFlag) throws Exception{

		boolean functionStatus = false;
		String path = null;
		String textValue = null;
		File[] listOfFile = null;
		String fileName = null;
		String actualText = null;
		Pattern pattern = null;
		String fileNameFromExcel=null;

		try{
			if(strExecEventFlag == 1){
				path = Param.getProperty(RetrieveTestDataValue("ReadlogFiles", filePath, strExecEventFlag));
				textValue = RetrieveTestDataValue("ReadlogFiles", textName, strExecEventFlag);
				fileNameFromExcel= RetrieveTestDataValue("ReadlogFiles", fileNameValue, strExecEventFlag);
			}

			if(path==null || fileNameFromExcel==null || textValue==null){
				Report_Functions.ReportEventFailure(doc,  "DeletelogFiles",  "Required details are not provided in test data sheet.", false);
				return false;
			}
			log.info("FIle name to search is : "+fileNameFromExcel);
			//Get the filename by given startName and endName

			File directory = new File("//\\" +path);

			boolean fileexist=false;
			int fileAppeartime=0;

			while(fileAppeartime<30){
				Thread.sleep(1000);
				listOfFile = directory.listFiles();
				if(listOfFile.length != 0){
					log.info("Directory has files");
					fileexist=true;
					break;

				}else{
					log.info("No File is available in directory. looping again with 60 secs");
					//Report_Functions.ReportEventFailure(doc,"","No File is not available in directory" , false);
					//return false;
				}

				log.info("No File is available in directory with 60 secs");
				fileAppeartime++;

			}

			if(fileexist){
				log.info("Files avialble in the directiory");

			}else{
				log.info("No File is available in directory for 60secs");
				Report_Functions.ReportEventFailure(doc,"","No File is not available in directory" , false);
				return false;
			}

			boolean foundstatus=false;
			int time=0;
			while(time<30){
				Thread.sleep(1000);	
				for(int i = 0; i<listOfFile.length; i++){
					log.info("i is : "+time);
					if(listOfFile[i].isFile()){

						fileName = listOfFile[i].getName();
						log.info("Found a file and name is : "+fileName);
						if(fileName.equals(fileNameFromExcel)){
							log.info("FileName exact match : "+fileName);
							fileName=fileNameFromExcel;
							foundstatus=true;
							break;
						}
					}
				}
				if(foundstatus){
					log.info("FileName exact match is found. Braking the loop");
					break;
				}else{
					log.info("FileName not found in path. Continue the loop");
				}
				time++;
			}

			if(fileName == null){
				Report_Functions.ReportEventFailure(doc,"","'"+fileNameFromExcel+"' file is not available in the directory" , false);
				log.info("Search File is not available in directory");
				return false;
			}

			if(!(fileName.equals(fileNameFromExcel))){
				Report_Functions.ReportEventFailure(doc,"","'"+fileNameFromExcel+"' file is not available in the directory" , false);
				log.info("Search File is not available in directory");
				return false;
			}

			File ff = new File("//\\" +path +fileName);
			log.info(ff.getAbsolutePath()+" || "+ff.getName());
			String fileContent = IOUtils.toString(ff.toURI());
			System.out.println("FileContent:"+fileContent);
			actualText = "Text is not available in log file";
			System.out.println("textValue:"+textValue);
			pattern = Pattern.compile(textValue);
			Matcher matcher = pattern.matcher(fileContent);

			while(matcher.find()){
				actualText = matcher.group();
				//	actualText = matcher.group();
				log.info("Text is matched");
				Report_Functions.ReportEventSuccess(doc,"1","","Text '"+actualText+"' is matched with expected text in the filename of '"+fileNameFromExcel+"'",3);
				return true;
			}

			log.info(actualText);
			functionStatus = false;
			Report_Functions.ReportEventFailure(doc,"",""+textValue+" in the filename of '"+fileNameFromExcel+"'" , true);
		}catch(Exception e){

			log.info("Exception occurs in read log file"+e.getMessage());
			functionStatus = false;
			Report_Functions.ReportEventFailure(doc,"","Error occurred, while match with expected text in the filename of '"+fileNameFromExcel+"'" , true);
		}
		return functionStatus;
	}


	/**
	 * @Objective <b>This method is to read the given single Log file content and match with the expected content<b>
	 * @param filePath Path of the file
	 * @param fileNameValue filename with its extension value
	 * @param textName Content to match
	 * @author <b>Muralimohan M</b>
	 * @since <b>06-Sep-16</b>
	 */

	public static boolean readLogFilesWithEnvironmentValue(String filePath, String startsWith, String endsWith, String fileNameFromExcel1,String textName1,String StrEnvironmentVariable,  String textName2,int strExecEventFlag) throws Exception{

		boolean functionStatus = false;
		String path = null;
		String startValue = null;
		String endValue = null;
		String textValue1 = null;
		String textValue2 = null;
		File[] listOfFile = null;
		String fileName = null;
		String actualText = null;
		Pattern pattern = null;
		String fileNameFromExcel=null;
		String EnvironmentVariable=null;
		try{
			if(strExecEventFlag == 1){
				path = Param.getProperty(RetrieveTestDataValue("readLogFilesWithEnvironmentValue", filePath, strExecEventFlag));
				startValue = RetrieveTestDataValue("readLogFilesWithEnvironmentValue", startsWith, strExecEventFlag);
				endValue = RetrieveTestDataValue("readLogFilesWithEnvironmentValue", endsWith, strExecEventFlag);
				textValue1 = RetrieveTestDataValue("readLogFilesWithEnvironmentValue", textName1, strExecEventFlag);
				EnvironmentVariable=Runtimevalue.getProperty(RetrieveTestDataValue("readLogFilesWithEnvironmentValue", StrEnvironmentVariable, strExecEventFlag));
				textValue2 = RetrieveTestDataValue("readLogFilesWithEnvironmentValue", textName2, strExecEventFlag);
				fileNameFromExcel = RetrieveTestDataValue("readLogFilesWithEnvironmentValue", fileNameFromExcel1, strExecEventFlag);
			}

			if(path==null || startValue==null || endValue==null || textValue1==null||textValue2==null){
				Report_Functions.ReportEventFailure(doc,  "DeletelogFiles",  "Required details are not provided in test data sheet.", false);
				return false;
			}
			log.info("FIle name to search is : "+fileNameFromExcel);
			//Get the filename by given startName and endName

			File directory = new File("//\\" +path);
			
			boolean fileexist=false;
			int fileAppeartime=0;

			while(fileAppeartime<30){
				Thread.sleep(1000);
				listOfFile = directory.listFiles();
				if(listOfFile.length != 0){
					log.info("Directory has files");
					fileexist=true;
					break;

				}else{
					log.info("No File is available in directory. looping again with 60 secs");
					//Report_Functions.ReportEventFailure(doc,"","No File is not available in directory" , false);
					//return false;
				}

				log.info("No File is available in directory with 60 secs");
				fileAppeartime++;

			}

			if(fileexist){
				log.info("Files avialble in the directiory");

			}else{
				log.info("No File is available in directory for 60secs");
				Report_Functions.ReportEventFailure(doc,"","No File is not available in directory" , false);
				return false;
			}
			
			boolean foundstatus=false;
			int time=0;
			while(time<30){
				Thread.sleep(1000);	
				for(int i = 0; i<listOfFile.length; i++){
					log.info("i is : "+time);
					if(listOfFile[i].isFile()){

						fileName = listOfFile[i].getName();
						log.info("Found a file and name is : "+fileName);
						if(fileName.equals(fileNameFromExcel)){
							log.info("FileName exact match : "+fileName);
							fileName=fileNameFromExcel;
							foundstatus=true;
							break;
						}
					}
				}
				if(foundstatus){
					log.info("FileName exact match is found. Braking the loop");
					break;
				}else{
					log.info("FileName not found in path. Continue the loop");
				}
				time++;
			}

			if(fileName == null){
				Report_Functions.ReportEventFailure(doc,"","'"+fileNameFromExcel+"' file is not available in the directory" , false);
				log.info("Search File is not available in directory");
				return false;
			}

			if(!(fileName.equals(fileNameFromExcel))){
				Report_Functions.ReportEventFailure(doc,"","'"+fileNameFromExcel+"' file is not available in the directory" , false);
				log.info("Search File is not available in directory");
				return false;
			}

			File ff = new File("//\\" +path +fileName);
			log.info(ff.getAbsolutePath()+" || "+ff.getName());
			String fileContent = IOUtils.toString(ff.toURI());
			//actualText = "Text is not available in log file";
			String FramedPattern=textValue1+EnvironmentVariable+textValue2;
			//System.out.println("FramedPattern:"+FramedPattern);
			pattern = Pattern.compile(FramedPattern);
			//System.out.println("Filecontent:"+fileContent);

			Matcher matcher = pattern.matcher(fileContent);
			int counter = 0;
			int counterLimit=30;
			
			while(counter<=counterLimit){
				
				if(matcher.find()){
				
				actualText = matcher.group();
				
				log.info("Text is matched:"+actualText+"in seconds:"+counter);
				Report_Functions.ReportEventSuccess(doc,"1","","Text '"+actualText+"' is matched with expected text in the filename of '"+fileNameFromExcel+"'",3);
				return true;
				}
				counter++;
			}

			log.info(actualText);
			functionStatus = false;
			Report_Functions.ReportEventFailure(doc,"","Text'"+FramedPattern+" is not available in the filename of '"+fileNameFromExcel+"'" , true);
		}catch(Exception e){

			log.info("Exception occurs in read log file"+e.getMessage());
			functionStatus = false;
			Report_Functions.ReportEventFailure(doc,"","Error occurred, while match with expected text in the filename of '"+startValue+"'" , true);
		}
		return functionStatus;
	}
	
	/**
	 * 
	 * @Objective <b>This method is to select the values from the dropdown</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */
	public static boolean WebListSelectByValueUntilExpectedValue(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag)throws Exception  {
		int executionFlag=0;
		String strData=null;
		boolean WebListSelect=false;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebListSelectByValue",strColumnName,strExecEventFlag);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebListSelectByValue",  "Required details are not provided in the data sheet.", false);
				return false;
			}

			new Select(selectByLocatorType(getValueFromPOM)).selectByValue(strData);
			Thread.sleep(1000);
			String SelectedItem= new Select(selectByLocatorType(getValueFromPOM)).getFirstSelectedOption().getText();

			int counter=0;
			int counterLimit=10;
			if(!SelectedItem.trim().equals(strData.trim())){

				while(counter<counterLimit){
					new Select(selectByLocatorType(getValueFromPOM)).selectByValue(strData);
					Thread.sleep(1000);
					if(SelectedItem.trim().equals(strData.trim())){
						log.info("Element selected with in the timelimit of ******'"+counter);
						Report_Functions.ReportEventSuccess (doc,"1","","The Item '"+strData+"' was  selected from the '"+strTestObject+"' List box ",2);
						return true;				
					}

					counter=counter+1;
					if(counter>10){
						log.info("Element not selected with in the timelimit of ******'"+counter);

						executionFlag=0;
						break;
					}
				}

			}else{
				Thread.sleep(1000);
				if(!SelectedItem.trim().equals(strData.trim())){
					new Select(selectByLocatorType(getValueFromPOM)).selectByValue(strData);
				}
				log.info("not inside if condition while selecting the dropdown.Hence value has been set succesfully in first attempt");
				executionFlag=1;
				WebListSelect=true;
				Report_Functions.ReportEventSuccess (doc,"1","","The Item '"+strData+"' was  selected from the '"+strTestObject+"' List box ",2);
				return true;

			}


			if (executionFlag==0){
				Report_Functions.ReportEventFailure(doc,"","The Item '" +  strData + " was not selected from the  '"+strTestObject+"' List box " , true); 
				return false;
			}

		} catch (StaleElementReferenceException e) {
			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				System.out.println("Exception:"+e.getMessage());
				return WebListSelect(getValueFromPOM, strTestObject, strColumnName, strExecEventFlag);

			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","The Item '" +  strData + " was not selected from the  '"+strTestObject+"' List box " , true); 
			WebListSelect=false;
			log.info("No Element Found to select text" + e);
		}
		return WebListSelect;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies the SQL DB select</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strenvironmentvariable
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>06-SEP-2016</b>
	 */

	public static boolean ESHOPSQLDBSelectConditionEnvvar1(String sqltablename, String strsqlcolumnname,String strsqlcondition,String strenvironmentvariable,String strExpectedvalue,int strExecEventFlag)throws Exception  {
		boolean ESHOPSQLDBSelectConditionEnvvar= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String SQL_condition_value = null;		
		String Expected_value = "";
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",strsqlcondition,strExecEventFlag);
				Expected_value=RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",strExpectedvalue,strExecEventFlag);
				SQL_condition_value = Runtimevalue.getProperty(RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",strenvironmentvariable,strExecEventFlag));
				
				
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"='"+ SQL_condition_value +"'";

			Eshop_SQLServer = EShopstmt.executeQuery(query);
			//Newly added on  20-09-2016 to validate system IP against DB value
			if (Expected_value.equalsIgnoreCase("GET_HOST_IP")){
				InetAddress IP=InetAddress.getLocalHost();
				Expected_value=	IP.getHostAddress();
			}


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("ESHOPSQLDBSelectConditionEnvvar Error : " + e);
			ESHOPSQLDBSelectConditionEnvvar=false;
		}

		try{
			Eshop_SQLServer.next();
			Actual_Value = Eshop_SQLServer.getString(1).trim();

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "No Record found for this query: "+query, true);
			log.info("ESHOPSQLDBSelectConditionEnvvar Error : ");
			ESHOPSQLDBSelectConditionEnvvar=false;
		}

		try{
			if(!Eshop_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","ESHOPSQLDBSelectConditionEnvvar", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches the expected value : '"+Expected_value+"'", 2);
					ESHOPSQLDBSelectConditionEnvvar=true;
				}else if(!(Actual_Value.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);
					ESHOPSQLDBSelectConditionEnvvar=false;
				}
			}

			else if(Eshop_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "ESHOPSQLDBSelectConditionEnvvar", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					ESHOPSQLDBSelectConditionEnvvar=true;
				}else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					ESHOPSQLDBSelectConditionEnvvar=false;
				}
			}
		}catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("ESHOPSQLDBSelectConditionEnvvar Error : " + e);
			ESHOPSQLDBSelectConditionEnvvar=false;
		}
		return ESHOPSQLDBSelectConditionEnvvar;
	}
	
	/**
	 * 
	 * @Objective <b>This method is to select the values from the dropdown using data from Property file value</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Muralimohan M</b>
	 * @since <b>12-Oct-16</b>
	 */

	public static boolean WebListSelectFromGAFEnv(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag)throws Exception  {

		String strData=null;
		boolean WebListSelectFromEnv=false;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebListSelectFromEnv",strColumnName,strExecEventFlag);
				strData=GAFValue.getProperty(strData);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,"WebListSelectFromEnv","Required details are not provided in the data sheet." , true);
				return false;
			}

			new Select(selectByLocatorType(getValueFromPOM)).selectByVisibleText(strData);
			Report_Functions.ReportEventSuccess(doc,"1","","The Item '"+strData+"' is selected from the  '"+strTestObject+"' List box successfully" ,3);
			WebListSelectFromEnv=true;

		} catch (StaleElementReferenceException e) {
			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				return WebListSelectFromEnv(getValueFromPOM, strTestObject, strColumnName,strExecEventFlag);

			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","The Item '" +  strData + "' was not selected from the  '"+strTestObject+"' List box- "+e.getMessage() , true); 
			WebListSelectFromEnv=false;
			log.info("No Element Found to select text" + e);
		}
		return WebListSelectFromEnv;
	}
	
	
	/*-----------------------------------------***MURALI-END***-----------------------------------------*/


	/*----------------------------------------- YOGENDRA CODE -----------------------------------------*/

	/**
	 * 
	 * @Objective <b>Verifies the values from SQLDB in the dropdown</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param getValueFromPOM
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>25-May-2016</b>
	 */

	public static boolean WeblistSQLDBitemsverify(String getValueFromPOM, String strTestObject, String sqltablename, String strsqlcolumnname,String strsqlcondition,int itemscount_not_consider,int strExecEventFlag)throws Exception  {

		boolean WeblistSQLDBitemsverify= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String[] sqldbvalues = null;
		String[] weblistvalues = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("WeblistSQLDBitemsverify",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("WeblistSQLDBitemsverify",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("WeblistSQLDBitemsverify",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "Required details are not provided in the Data Sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";

			rs_SQLServer = stmt.executeQuery(query);

			List<String> rowValues = new ArrayList<String>();

			while (rs_SQLServer.next()) {

				rowValues.add(rs_SQLServer.getString(1));
			}   
			// You can then put this back into an array if necessary
			sqldbvalues = (String[]) rowValues.toArray(new String[rowValues.size()]);

		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("WeblistSQLDBitemsverify Error : " + e);
			WeblistSQLDBitemsverify=false;
		}


		try{

			Select se = new Select(selectByLocatorType(getValueFromPOM));
			List<WebElement> options = se.getOptions();

			//if you want to get all elements text into array list
			List<String> all_elements_text=new ArrayList<String>();

			for(int j=0; j<options.size(); j++){

				//loading text of each element in to array all_elements_text
				all_elements_text.add(options.get(j).getText());

			}

			weblistvalues = (String[]) all_elements_text.toArray(new String[all_elements_text.size()]);

			log.info("sqldbvalues length is : "+sqldbvalues.length);
			log.info("weblistvalues length is : "+weblistvalues.length);

			if(sqldbvalues.length == weblistvalues.length - itemscount_not_consider)
			{
				if(sqldbvalues.length==0){
					
					log.info("No value present in DB as well list");
					Report_Functions.ReportEventSuccess(doc, "1","", "No Dropdown Value is Present in the Dropdown '"+strTestObject+"' and the corresponding Table", 2);
					WeblistSQLDBitemsverify=true;
				
				}else{
					for (int i=0; i<sqldbvalues.length; i++){

						if (weblistvalues[i+1].equals(sqldbvalues[i])){
							Report_Functions.ReportEventSuccess(doc, "1","WeblistSQLDBitemsverify", "The actual value : '"+ weblistvalues[i+1] +"' in the dropdown : "+ strTestObject +"' matches the expected value : '"+ sqldbvalues[i] +"'.", 2);
							WeblistSQLDBitemsverify=true;
						} else{
							Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "The actual value : '"+ weblistvalues[i+1] +"' in the dropdown : "+ strTestObject +"' doesn't matches the expected value : '"+ sqldbvalues[i] +"'.", true);
							WeblistSQLDBitemsverify=false;
						}
					}
				}
			} else {
				Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "The number of items present in the dropdown : "+ strTestObject +"' doesn't matches with the number of items in the table : '"+ Table_name +"' with condition : '"+ SQL_condition +"'.", true);
				WeblistSQLDBitemsverify=false;
			}

		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "WeblistSQLDBitemsverify",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("WeblistSQLDBitemsverify Error : " + e);
			WeblistSQLDBitemsverify=false;
		}
		return WeblistSQLDBitemsverify;
	}

	/**
	 * 
	 * @Objective <b>Verify to compare the dates in RRBS DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param Date_Format
	 * @param Days_To_Add
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>24-Aug-2016</b>
	 */

	public static boolean RRBSDBFutureDateCompare(String sqltablename, String strsqlcolumnname,String strsqlcondition,String Date_Format,String Days_to_add,int strExecEventFlag)throws Exception  {

		boolean RRBSDBFutureDateCompare= false;
		String query = null;  
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Expected_value = null;
		String Actual_Value = null;
		String Current_Date=null;
		String expected_db_Date = null;
		String daystoadd = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("RRBSDBFutureDateCompare",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("RRBSDBFutureDateCompare",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("RRBSDBFutureDateCompare",strsqlcondition,strExecEventFlag);
				daystoadd = RetrieveTestDataValue("RRBSDBFutureDateCompare", Days_to_add, strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "RRBSDBFutureDateCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			int Add_Days = Integer.parseInt(daystoadd);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();

			Calendar expdate = Calendar.getInstance();
			expdate.setTime(date);
			expdate.add(Calendar.DATE, Add_Days);
			Current_Date = dateFormat.format(expdate.getTime());


			Expected_value = Current_Date.trim();

			// Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";

			rrbsresultset = rrbsstatement.executeQuery(query);

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "RRBSDBFutureDateCompare",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBFutureDateCompare Error : " + e);
			RRBSDBFutureDateCompare=false;
		}

		try{

			rrbsresultset.next();

			Actual_Value = rrbsresultset.getString(1);
			String db_Date = Actual_Value.split(" ")[0];

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date dateToChange = dateFormat.parse(db_Date);			

			SimpleDateFormat finalDateFormat = new SimpleDateFormat(Date_Format);
			expected_db_Date = finalDateFormat.format(dateToChange);


		} catch (Exception ne) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "RRBSDBFutureDateCompare",  "No Record found for this query: "+query, true);
			log.info("RRBSDBFutureDateCompare Error : "+ne);
			RRBSDBFutureDateCompare=false;
		}

		try{

			if(!rrbsresultset.wasNull()){            // If some value is present in the fired Query

				if(expected_db_Date.equals(Expected_value)){

					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBFutureDateCompare", "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' matches with the expected date : '"+Expected_value+"' ", 2);
					RRBSDBFutureDateCompare=true;

				}else if(!(expected_db_Date.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBFutureDateCompare",  "The selected date : '"+expected_db_Date+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' does not match with the expected date : '"+Expected_value+"' ", true);
					RRBSDBFutureDateCompare=false;
				}
			}
			else if(rrbsresultset.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "RRBSDBFutureDateCompare", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					RRBSDBFutureDateCompare=true;
				}

				else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "RRBSDBFutureDateCompare",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					RRBSDBFutureDateCompare=false;
				}
			}

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "RRBSDBFutureDateCompare",  "Error occured while comparing the dates in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RRBSDBDateCompare Error : " + e);
			RRBSDBFutureDateCompare=false;
		}

		return RRBSDBFutureDateCompare;
	}

	/**
	 * 
	 * @Objective <b>To compare the texts both excel sheet and web element</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>25-August-2016</b>
	 */

	public static boolean WebElementMultilineTextCompare(String getValueFromPOM, String strTestObject,String strColumnName, int strExecEventFlag )throws Exception  {
		String actualResult=null;
		String strData=null;
		boolean WebElementMultilineTextCompare=false;

		try{
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebElementMultilineTextCompare",strColumnName,strExecEventFlag);
			}
			actualResult = selectByLocatorType(getValueFromPOM).getText();
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementMultilineTextCompare", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			WebElementMultilineTextCompare=false;
		}

		actualResult=actualResult.replaceAll("[\n\r]", "");

		try{
			if((actualResult.trim()).equalsIgnoreCase(strData.trim())){

				Report_Functions.ReportEventSuccess(doc,"1", "WebElementMultilineTextCompare", "The  Actual Value '" +actualResult+ "' matches with the Expected value '"+strData+ "' in the input field '"+strTestObject+"'", 2);
				WebElementMultilineTextCompare=true;
			}else{
				Report_Functions.ReportEventFailure(doc,"WebElementMultilineTextCompare", "The  Actual Value '" +actualResult+ "' does not match with the Expected value '"+strData+ "' in the input field '"+strTestObject+"'", true);
				WebElementMultilineTextCompare=false;
			}
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementMultilineTextCompare", "Error occured while comparing actual and expected values. Error description is :"+e.getMessage(), true);
			WebElementMultilineTextCompare=false;
		}
		return WebElementMultilineTextCompare;
	}

	/**
	 * 
	 * @Objective <b>Clciks on the webelement using javascript executor</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Yogendra</b>
	 * @since <b>26-August-2016</b>
	 */

	public static boolean Webelementjavascriptclick(String getValueFromPOM, String strTestObject) throws Exception {
		boolean Webelementjavascriptclick= false;
		try {
			((JavascriptExecutor)driver).executeScript("arguments[0].click()",selectByLocatorType(getValueFromPOM));
			Report_Functions.ReportEventSuccess(doc,"1","Webelementjavascriptclick","The element '"+strTestObject+"' is clicked successfully",3);
			Webelementjavascriptclick=true;
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"Webelementjavascriptclick","The element '"+strTestObject+"' is not clicked successfully", true); 
			Webelementjavascriptclick=false;
			log.info("No Main menu link Found to click : " + e);
		}
		return Webelementjavascriptclick;

	}
	
	/**
	 * 
	 * @Objective <b>Verifies the SQL DB select</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param sqltablenamedelete
	 * @param strsqlcolumnnamedelete
	 * @author <b>Yogendra</b>
	 * @since <b>19-August-2016</b>
	 */
	
	public static boolean SQLDBSelectDelete(String sqltablename, String strsqlcolumnname,String strsqlcondition,String sqltablenamedelete,String strsqlcolumnnamedelete,int strExecEventFlag)throws Exception  {
		boolean SQLDBSelectDelete= false;
		String query = null;
		String query2 = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String Actual_Value = null;
		String Table_name_delete = null;
		String Column_name_delete = null;
		
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBSelectDelete",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBSelectDelete",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBSelectDelete",strsqlcondition,strExecEventFlag);
				Table_name_delete=RetrieveTestDataValue("SQLDBSelectDelete",sqltablenamedelete,strExecEventFlag);
				Column_name_delete=RetrieveTestDataValue("SQLDBSelectDelete",strsqlcolumnnamedelete,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null || Table_name_delete==null || Column_name_delete==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBSelectDelete",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
			
			rs_SQLServer = stmt.executeQuery(query);
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1).trim();

		} catch(NullPointerException e){
			
			log.info("Null pointer occurred :"+e.getMessage());
			Report_Functions.ReportEventSuccess(doc, "1", "", "The record is not present in the table '" +  Table_name + "' based on the condition  '"+SQL_condition+"'.", 3);
			SQLDBSelectDelete=true;
			
			
		} catch (Exception e) {
			Report_Functions.ReportEventSuccess(doc, "1", "", "The record is not present in the table '" +  Table_name + "' based on the condition  '"+SQL_condition+"'.", 3);
			log.info("SQLDBSelect Error: " + e);
			SQLDBSelectDelete=true;
		}

		try{
			if(Actual_Value.length() != 0){
			//Query to Execute      
			query2 = "Delete from "+Table_name_delete+" where "+ Column_name_delete +" = "+ Actual_Value ;
			stmt.execute(query2);
			}
			Report_Functions.ReportEventSuccess(doc, "1", "", "The value '" +  Actual_Value + "' is successfully deleted from the table  '"+Table_name_delete+"'.", 3);
			SQLDBSelectDelete=true;
			
		} catch(NullPointerException e){
			
			log.info("Null pointer occurred :"+e.getMessage());
			
		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelectDelete",  "No Record found for this query: "+query, true);
			log.info("SQLDBSelect Error : ");
			SQLDBSelectDelete=false;
		}


		return SQLDBSelectDelete;
	}

	/**
	 * 
	 * @Objective <b>Entering the calender text in the calender object<b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */

	public static boolean WebEditEnterCalenderText(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag)throws Exception  {

		boolean WebEditEnterCalenderText= false;
		String strData=null;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebEditEnterCalenderText",strColumnName,strExecEventFlag);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebEditEnterCalenderText",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('onkeydown',arguments[1]);",selectByLocatorType(getValueFromPOM),"return true;");
			selectByLocatorType(getValueFromPOM).sendKeys(strData);
			Report_Functions.ReportEventSuccess(doc, "1", "", "The Text '" +  strData + "' is entered in the Textbox -  '"+strTestObject+"'  successfully", 3);
			WebEditEnterCalenderText=true;	
		} catch (Exception e) { 	
			Report_Functions.ReportEventFailure(doc, "", "The Text '" + strData + "' was not entered in the Textbox - '"+strTestObject+"'", true);
			WebEditEnterCalenderText=false;
			log.info("No Element Found to enter text : " + e);
		}
		return WebEditEnterCalenderText;
	}
	
	/**
	 * 
	 * @Objective <b>Enters the uploaded file path</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strPropertyName
	 * @param strFilename
	 * @author <b>Yogendra</b>
	 * @since <b>19-August-2016</b>
	 */

	public static boolean Browsefiletoupload(String getValueFromPOM, String strTestObject,String strPropertyName,String strFilename,int strExecEventFlag)throws Exception  {

		boolean Browsefiletoupload= false;
		String strData=null;
		String filename=null;
		String filepath = null;
		
		try {
			if(!(strPropertyName==null)){
				strData = System.getProperty("user.dir")+Param.getProperty(strPropertyName);
			}
			
			if(strExecEventFlag == 1){
				filename = RetrieveTestDataValue("Browsefiletoupload", strFilename, strExecEventFlag);
			}

			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "Browsefiletoupload",  "Required details are not provided in test data sheet.", false);
				return false;
			}
			
			filepath = strData + filename;
			
			selectByLocatorType(getValueFromPOM).sendKeys(filepath);

			Report_Functions.ReportEventSuccess(doc, "1", "Browsefiletoupload", "The Text '" +  filepath + "' is entered in the Textbox -  '"+strTestObject+"'  successfully", 3);
			Browsefiletoupload=true;	
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc, "Browsefiletoupload", "The Text '" + filepath + "' was not entered in the Textbox - '"+strTestObject+"'", true);
			Browsefiletoupload=false;
			log.info("No Element Found to enter text : " + e);
		}
		return Browsefiletoupload;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies to retrieve the value for environment variable in SQL DB</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param sqlcondition
	 * @param strEnvironmentVariable
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */
	
	public static boolean RetrieveSQLValueStoresInEnvVar(String sqltablename, String strsqlcolumnname,String sqlcondition,String strEnvironmentVariable,int strExecEventFlag)throws Exception  {
		boolean RetrieveSQLValueStoresInEnvVar= false;
		String query = null;
		String Table_name = null;
		String Column_Name = null;
		String SQL_condition = null;
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("RetrieveSQLValueStoresInEnvVar",sqltablename,strExecEventFlag);
				Column_Name=RetrieveTestDataValue("RetrieveSQLValueStoresInEnvVar",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("RetrieveSQLValueStoresInEnvVar",sqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_Name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "RetrieveSQLValueStoresInEnvVar",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute
			query = "select "+Column_Name+" from "+Table_name+" where "+SQL_condition+"";
			rs_SQLServer= stmt.executeQuery(query);

		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "RetrieveSQLValueStoresInEnvVar",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("RetrieveSQLValueStoresInEnvVar Error : " + e);
			RetrieveSQLValueStoresInEnvVar=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1);
		}catch(Exception e){
			Report_Functions.ReportEventFailure(doc,  "RetrieveSQLValueStoresInEnvVar",  "Error has occured. Error description is :"+e.getMessage(), true);
			RetrieveSQLValueStoresInEnvVar=false;
		}	
		try{
			Runtimevalue.setProperty(strEnvironmentVariable, Actual_Value);
		}catch(Exception e){
			Report_Functions.ReportEventFailure(doc,  "RetrieveSQLValueStoresInEnvVar",  "Error occured while storing the value :'"+Actual_Value+"' in the Environment variable '"+strEnvironmentVariable+"'", true);
			RetrieveSQLValueStoresInEnvVar=false;
		}	

		try{
			if(!(Actual_Value==null)){
				Report_Functions.ReportEventSuccess(doc, "1", "RetrieveSQLValueStoresInEnvVar", "The Value:'" +Actual_Value+ "' is stored in the Environment Variable: '"+ strEnvironmentVariable +"' successfully", 2);
				RetrieveSQLValueStoresInEnvVar=true;
			}else{
				Report_Functions.ReportEventFailure(doc,  "RetrieveSQLValueStoresInEnvVar",  "No Value is stored in Environment Variable: '"+ strEnvironmentVariable +"'", true);
				RetrieveSQLValueStoresInEnvVar=false;
			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "RetrieveSQLValueStoresInEnvVar",  "Error occured while checking whether the SQL query value '"+Actual_Value+"' is null (or) not", true);
			log.info("RetrieveSQLValueStoresInEnvVar Error : " + e);
			RetrieveSQLValueStoresInEnvVar=false;
		}
		return RetrieveSQLValueStoresInEnvVar;
	}
	
	/**
	 * 
	 * @Objective <b>Verifies the SQL DB select</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strenvironmentvariable
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>22-August-2016</b>
	 */
	
	public static boolean SQLDBSelectConditionEnvvar(String sqltablename, String strsqlcolumnname,String strsqlcondition,String strenvironmentvariable,String strExpectedvalue,int strExecEventFlag)throws Exception  {
		boolean SQLDBSelectConditionEnvvar= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String SQL_condition_value = null;		
		String Expected_value = "";
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBSelectConditionEnvvar",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBSelectConditionEnvvar",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBSelectConditionEnvvar",strsqlcondition,strExecEventFlag);
				Expected_value=RetrieveTestDataValue("SQLDBSelectConditionEnvvar",strExpectedvalue,strExecEventFlag);
				SQL_condition_value = Runtimevalue.getProperty(strenvironmentvariable);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBSelectConditionEnvvar",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"='"+ SQL_condition_value +"'";
			
			rs_SQLServer = stmt.executeQuery(query);
			

		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelectConditionEnvvar",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQL_condition_value Error : " + e);
			SQLDBSelectConditionEnvvar=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1).trim();
			
		} catch(NullPointerException e){
			
			log.info("Null pointer occurred :"+e.getMessage());
			
		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelectConditionEnvvar",  "No Record found for this query: "+query, true);
			log.info("SQL_condition_value Error : ");
			SQLDBSelectConditionEnvvar=false;
		}

		try{
			if(!rs_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","SQLDBSelectConditionEnvvar", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+ SQL_condition_value +"' matches the expected value : '"+Expected_value+"'", 2);
					SQLDBSelectConditionEnvvar=true;
				}else if(!(Actual_Value.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelectConditionEnvvar",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+ SQL_condition_value +"' does not match the expected value : '"+Expected_value+"'", true);
					SQLDBSelectConditionEnvvar=false;
				}
			}
			
			else if(rs_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBSelectConditionEnvvar", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+ SQL_condition_value +"' matches with the expected value :'"+Expected_value+"'", 2);
					SQLDBSelectConditionEnvvar=true;
				}else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelectConditionEnvvar",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"='"+ SQL_condition_value +"' does not match the expected value : '"+Expected_value+"'", true);  	 
					SQLDBSelectConditionEnvvar=false;
				}
			}
		}catch(NullPointerException e){
			
			log.info("Null pointer occurred :"+e.getMessage());
			
		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelect",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelect Error : " + e);
			SQLDBSelectConditionEnvvar=false;
		}
		return SQLDBSelectConditionEnvvar;
	}
	
	/**
	 * 
	 * @Objective <b>This method is to upload the file given in test data sheet using AutoITX function using Java</b>
	 * @param filePath
	 * @param strTestObject
	 * @author <b>Yogendra</b>
	 * @throws Exception 
	 * @since <b>29-Aug-16</b>
	 * 
	 */

	public static boolean Uploadfiletestdata(String strPropertyName, String strFilename, int strExecEventFlag) throws Exception{

		boolean elementStatus = false;
		String strData=null; 
		String filename=null;
		String filepath = null;
		String getBrowserName = Param.getProperty("testBrowser");
		
		try {
			if(!(strPropertyName==null)){
				strData = System.getProperty("user.dir")+Param.getProperty(strPropertyName);
			}
			
			if(strExecEventFlag == 1){
				filename = RetrieveTestDataValue("Uploadfiletestdata", strFilename, strExecEventFlag);
			}
			
			filepath = strData + filename;

			//Set the Path for DLL
			File file = new File("lib", "jacob-1.14.3-x86.dll");
			//Get the absolute path for DLL
			System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());
			//Get the Jacob DLL path from local
			File jacobDLLPath = new File(Param.getProperty("AutoIT_Jacob_DLL_Path"));
			String dLLAbsolutePath = jacobDLLPath.getAbsolutePath();
			//Get the Jacob DLL absolute path
			System.setProperty(LibraryLoader.JACOB_DLL_PATH, dLLAbsolutePath);
			LibraryLoader.loadJacobLibrary();
			File localPath = new File(filepath);
			//Get the upload file absolute path
			String absoluteFilepath = localPath.getAbsolutePath();
			AutoItX autoIT = new AutoItX();

			//Based on Browsers AutoIT tool will be executed
			if(getBrowserName.equalsIgnoreCase("IE")){

				autoIT.winActivate("Choose File to Upload");
				if(autoIT.winWaitActive("Choose File to Upload", "", 10)){
					if(autoIT.winExists("Choose File to Upload")){
						autoIT.send(absoluteFilepath);
						autoIT.send("{Enter}",false);	
						log.info("File has been uploaded successfully in IE browser");
						elementStatus = true;

					}
				}

				log.info("File has been uploaded successfully in IE browser");
				elementStatus = true;
			} 

			else if(getBrowserName.equalsIgnoreCase("Mozilla")){

				autoIT.winActivate("File Upload");
				if(autoIT.winWaitActive("File Upload", "", 10)){
					if(autoIT.winExists("File Upload")){
						autoIT.sleep(500);
						autoIT.send(absoluteFilepath);	                
						autoIT.send("{Enter}",false);
						log.info("File has been uploaded successfully in Firefox browser");
						elementStatus = true;

					}
				}
				
				log.info("File has been uploaded successfully in IE browser");
				elementStatus = true;

			}else if(getBrowserName.equalsIgnoreCase("Chrome")){

				
				autoIT.winActivate("Open");
				if(autoIT.winWaitActive("Open", "", 10)){
					if(autoIT.winExists("Open")){
						autoIT.sleep(500);
						autoIT.send(absoluteFilepath);	                
						autoIT.send("{Enter}",false);
						log.info("File has been uploaded successfully in Chrome browser");
						elementStatus = true;
					}

				}
			}

		}catch(Exception e){

			log.info("Exception occurred in FileUpload using AutoITX :"+e.getMessage());
			e.printStackTrace();
			elementStatus = false;

		}
		
		if(elementStatus){
			Report_Functions.ReportEventSuccess(doc, "1", "Uploadfiletestdata", "The file : '" +  filename + "' is uploaded successfully", 3);
		} else{
			Report_Functions.ReportEventFailure(doc, "Uploadfiletestdata",  "Error occured while uploading the file : '"+ filename +"'.", false);
		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to Open Unix connection to the server<b>
	 * @return
	 * @author <b>Muralimohan M</b>
	 * @since <b>17-May-16</b>
	 */

	public static boolean jsh_Unix_Open_Connection(String strserver, String strusername, String strpassword) throws Exception{
		boolean Executionstatus=false;
		jsch=new JSch();
		String ipaddress=Param.getProperty(strserver);
		String username =Param.getProperty(strusername);
		String password =Param.getProperty(strpassword);

		try {
			JSHsession=jsch.getSession(username, ipaddress);
			JSHsession.setPassword(password);
			Properties JSHProperties=new Properties();
			JSHProperties.put("StrictHostKeyChecking", "no");
//			JSHsession.setConfig("PreferredAuthentications","publickey,keyboard-interactive,password");
			JSHsession.setConfig(JSHProperties);
			JSHsession.connect();

		
			if(JSHsession.isConnected()){
				Report_Functions.ReportEventSuccess(doc, "1", "Open_Unix_Connection", "UNIX Server with IP Address '"+ipaddress+"' has been connected successfully", 2);
				Executionstatus=true;
			}else{
				Report_Functions.ReportEventFailure(doc,  "Open_Unix_Connection",  "Unable to connect to the UNIX Server with IP Address==> "+ipaddress+".", false);
				Executionstatus=true;
			}

		} catch (JSchException e) {
			Report_Functions.ReportEventFailure(doc,  "Open_Unix_Connection",  "Unable to connect to the UNIX Server with IP Address==> "+ipaddress+"."+e.getMessage(), false);
			//e.printStackTrace();
			return Executionstatus;
		}
		return Executionstatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to Close Unix connection to the server<b>
	 * @return boolean
	 * @author <b>Muralimohan M</b>
	 * @since <b>17-May-16</b>
	 */
	
	public static boolean Jsh_closeUnixSession() throws Exception{
		boolean Executionstatus=false;
		try{
			JSHsession.disconnect();
			//Executionstatus=JSHsession.disconnect();
			if(JSHsession.isConnected()){
				Report_Functions.ReportEventFailure(doc,  "Jsh_closeUnixSession",  "Unable to disconnect  UNIX Server with IP Address==> "+Param.getProperty("Unix_ITG_Server_IP")+".", false);
				Executionstatus=false;
			}else{
				Report_Functions.ReportEventSuccess(doc, "1", "Jsh_closeUnixSession", "Unix Connection disconnected successfully", 2);
				Executionstatus=true;
			}
		}catch(Exception e){
			Report_Functions.ReportEventFailure(doc,  "closeUnixSession",  "Unable to disconnect  UNIX Server with IP Address "+Param.getProperty("Unix_ITG_Server_IP")+".Exception Occured:"+e.getMessage(), false);
			log.info("Close Unix connection- Exception occured==>"+e.getMessage());
			return Executionstatus;
		}
		return Executionstatus;
	}
	
	/**
	 * 
	 * @Objective <b>This method is to check for the file existence and delete the file in unix server<b>
	 * @author <b>Yogendra M</b>
	 * @since <b>29-Aug-16</b>
	 */

	public static boolean Delete_File_Unix(String strfilelocation, String strfilename, int strExecEventFlag) throws Exception{
		boolean Delete_File_Unix=false;
		String strData=null;
		String filename=null;
		String filepath=null;
		InputStream strpath=null;

		try {

			if(!(strfilelocation==null)){
				strData = Param.getProperty(strfilelocation);
			}

			if(strExecEventFlag == 1){
				filename = RetrieveTestDataValue("Delete_File_Unix", strfilename, strExecEventFlag);
			}

			filepath = strData + filename;
			Channel channel=JSHsession.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp)channel ;

			try {
				strpath = channelSftp.get(filepath);

				if(strpath != null){

					channelSftp.rm(filepath);
					Report_Functions.ReportEventSuccess(doc, "1", "Delete_File_Unix", "The file : '"+ filename +"' is successfully deleted from the location : '"+ strData +"'.", 2);	
					Delete_File_Unix = true;
				}
			} catch(Exception e){
				Report_Functions.ReportEventSuccess(doc, "1", "Delete_File_Unix", "The file : '"+ filename +"' is not present in the location : '"+ strData +"'.", 2);	
				Delete_File_Unix = true;
			}

		} catch (Exception e) {
			log.info("Delete_File_Unix- Exception occured==>"+e.getMessage());
			Report_Functions.ReportEventFailure(doc,  "Delete_File_Unix",  "Unable to delete the file : '"+ filename +"' from the location : '"+ strData +"'.Error description is : "+ e.getMessage(), false);
			Delete_File_Unix = false;
		}
		return Delete_File_Unix;
	}

	/**
	 * 
	 * @Objective <b>This method is to check for the file existence in unix server<b>
	 * @author <b>Yogendra M</b>
	 * @since <b>29-Aug-16</b>
	 */

	public static boolean Check_File_Unix(String strfilelocation, String strfilename, int strExecEventFlag) throws Exception{
		boolean Check_File_Unix=false;
		String strData=null;
		String filename=null;
		String filepath=null;
		InputStream strpath=null;

		try {

			if(!(strfilelocation==null)){
				strData = Param.getProperty(strfilelocation);
			}

			if(strExecEventFlag == 1){
				filename = RetrieveTestDataValue("Check_File_Unix", strfilename, strExecEventFlag);
			}

			filepath = strData + filename;

			Channel channel=JSHsession.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp)channel ;

			try {
				strpath = channelSftp.get(filepath);

				if(strpath != null){
					Report_Functions.ReportEventSuccess(doc, "1", "Check_File_Unix", "The file : '"+ filename +"' is present in the location : '"+ strData +"'.", 2);	
					Check_File_Unix = true;
				}
			} catch(Exception e){
				Report_Functions.ReportEventFailure(doc, "Check_File_Unix", "The file : '"+ filename +"' is not present in the location : '"+ strData +"'.", false);	
				Check_File_Unix = false;
			}

		} catch (Exception e) {
			log.info("Check_File_Unix- Exception occured==>"+e.getMessage());
			Report_Functions.ReportEventFailure(doc,  "Check_File_Unix",  "Unable to check the file : '"+ filename +"' in the location : '"+ strData +"'.Error description is : "+ e.getMessage(), false);
			Check_File_Unix = false;
		}
		return Check_File_Unix;
	}

	/**
	 * 
	 * @Objective <b>Replaces the particular text from a string in environment variable and stores it in another environment variable.</b>
	 * @param strenvironmentvariable
	 * @param strstoreenvvariable
	 * @param strStringtoreplace
	 * @param strenvironmentvariable
	 * @param strReplacementstring
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>06-Sep-2016</b>
	 */

	public static boolean ReplaceStringStoreEnvvar(String strenvironmentvariable,String strstoreenvvariable,String strStringtoreplace,String strReplacementstring,int strExecEventFlag)throws Exception  {
		boolean ReplaceStringStoreEnvvar= false;
		String SQL_condition_value = null;		
		String String_To_Replace = null;
		String Replacement_String = null;
		String Expected_value =null;
		try {
			if(strExecEventFlag==1){
				String_To_Replace=RetrieveTestDataValue("ReplaceStringStoreEnvvar",strStringtoreplace,strExecEventFlag);
				Replacement_String = RetrieveTestDataValue("ReplaceStringStoreEnvvar",strReplacementstring,strExecEventFlag);
				SQL_condition_value = Runtimevalue.getProperty(strenvironmentvariable);
			}

			if(SQL_condition_value==null){
				Report_Functions.ReportEventFailure(doc,  "ReplaceStringStoreEnvvar",  "The value in the environment variable : '"+ strenvironmentvariable +"' is empty.", false);
				return false;
			}


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "ReplaceStringStoreEnvvar",  "Error occured while retrieving the values from test data sheet.Error description is : "+ e.getMessage() +".", true);
			log.info("ReplaceStringStoreEnvvar Error : " + e);
			ReplaceStringStoreEnvvar=false;
		}

		Expected_value = SQL_condition_value.replaceAll(String_To_Replace, Replacement_String);

		try{

			Runtimevalue.setProperty(strstoreenvvariable, Expected_value);

			Report_Functions.ReportEventSuccess(doc, "1", "ReplaceStringStoreEnvvar", "The value : '"+ Expected_value +"' is stored in the environment variable : '"+ strstoreenvvariable +"'.", 2);
			ReplaceStringStoreEnvvar=true;


		}catch(Exception e){
			Report_Functions.ReportEventFailure(doc,  "ReplaceStringStoreEnvvar",  "Error occured while storing the value in environment variable.Error description is : "+ e.getMessage() +".", true);
			log.info("ReplaceStringStoreEnvvar Error : " + e);
			ReplaceStringStoreEnvvar=false;
		}
		return ReplaceStringStoreEnvvar;
	}

	/**
	 * @Objective <b>Deletes the record from SQL table based on the condition from environment variable<b>
	 * @param sqltablename
	 * @param sqlcondition
	 * @param strenvironmentvariable
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>06-SEP-2016</b>
	 */

	public static boolean SQLDBDeleteConditionEnvvar(String sqltablename, String sqlcondition, String strenvironmentvariable, int strExecEventFlag)throws Exception  {
		boolean elementStatus= false;
		String tablename = null;
		String condition = null;
		String SQL_condition_value = null;

		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("SQLDBDeleteConditionEnvvar",sqltablename,strExecEventFlag);
				condition=RetrieveTestDataValue("SQLDBDeleteConditionEnvvar",sqlcondition,strExecEventFlag);
				SQL_condition_value = Runtimevalue.getProperty(strenvironmentvariable);
			}

			if(tablename==null || condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBDeleteConditionEnvvar",  "Required details are not provided in test data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Error log in reading file is "+e);
			throw e;
		}

		//String check = "IF EXISTS(SELECT * FROM "+tablename+" WHERE "+condition+") SELECT 'TRUE' as STATUS ELSE SELECT 'FALSE' as STATUS";

		String check = "select * from "+tablename +" where "+condition+"= '"+ SQL_condition_value +"'";

		String query = "Delete from "+ tablename +" where "+condition+"= '"+ SQL_condition_value +"'";

		ResultSet rs = null;

		try {			
			rs = stmt.executeQuery(check);		

			int temp=0;	
			while(rs.next()){
				temp++;
			}

			if(temp >= 1){

				stmt.execute(query);
				elementStatus=true;

				Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDeleteConditionEnvvar", "The SQL Query : "+ query + " executed successfully.", 2);

			}//If rows not available FALSE will be returned so no delete
			else if(temp < 1){
				Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDeleteConditionEnvvar", "The SQL Query : "+ query + " records are not availbale in DB", 2);
				elementStatus=true;
			}

		}catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "SQLDBDeleteConditionEnvvar",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", false);
			log.info("SQLDBDeleteConditionEnvvar Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * @Objective <b>Deletes the record from SQL table based on the condition from environment variable<b>
	 * @param sqltablename
	 * @param sqlcondition
	 * @param strenvironmentvariable
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>06-SEP-2016</b>
	 */

	public static boolean ESHOPSQLDBDeleteConditionEnvvar(String sqltablename, String sqlcondition, String strenvironmentvariable, int strExecEventFlag)throws Exception  {
		boolean elementStatus= false;
		String tablename = null;
		String condition = null;
		String SQL_condition_value = null;

		try{

			if(strExecEventFlag==1){
				tablename=RetrieveTestDataValue("ESHOPSQLDBDeleteConditionEnvvar",sqltablename,strExecEventFlag);
				condition=RetrieveTestDataValue("ESHOPSQLDBDeleteConditionEnvvar",sqlcondition,strExecEventFlag);
				SQL_condition_value = Runtimevalue.getProperty(strenvironmentvariable);
			}

			if(tablename==null || condition==null){
				Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBDeleteConditionEnvvar",  "Required details are not provided in test data sheet.", false);
				return false;
			}

		}catch(Exception e){
			log.info("Error log in reading file is "+e);
			throw e;
		}

		//String check = "IF EXISTS(SELECT * FROM "+tablename+" WHERE "+condition+") SELECT 'TRUE' as STATUS ELSE SELECT 'FALSE' as STATUS";

		String check = "select * from "+tablename +" where "+condition+"= '"+ SQL_condition_value +"'";

		String query = "Delete from "+ tablename +" where "+condition+"= '"+ SQL_condition_value +"'";

		ResultSet Eshop_SQLServer = null;

		try {			
			Eshop_SQLServer = EShopstmt.executeQuery(check);		

			int temp=0;	
			while(Eshop_SQLServer.next()){
				temp++;
			}

			if(temp >= 1){

				EShopstmt.execute(query);
				elementStatus=true;

				Report_Functions.ReportEventSuccess(doc, "1", "ESHOPSQLDBDeleteConditionEnvvar", "The SQL Query : "+ query + " executed successfully.", 2);

			}//If rows not available FALSE will be returned so no delete
			else if(temp < 1){
				Report_Functions.ReportEventSuccess(doc, "1", "ESHOPSQLDBDeleteConditionEnvvar", "The SQL Query : "+ query + " records are not availbale in DB", 2);
				elementStatus=true;
			}

		}catch (Exception e) { 
			elementStatus=false;
			Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBDeleteConditionEnvvar",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", false);
			log.info("ESHOPSQLDBDeleteConditionEnvvar Error : " + e);
		}
		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>Verifies the SQL DB select</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strenvironmentvariable
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>06-SEP-2016</b>
	 */

	public static boolean ESHOPSQLDBSelectConditionEnvvar(String sqltablename, String strsqlcolumnname,String strsqlcondition,String strenvironmentvariable,String strExpectedvalue,int strExecEventFlag)throws Exception  {
		boolean ESHOPSQLDBSelectConditionEnvvar= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String SQL_condition_value = null;		
		String Expected_value = "";
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",strsqlcondition,strExecEventFlag);
				Expected_value=RetrieveTestDataValue("ESHOPSQLDBSelectConditionEnvvar",strExpectedvalue,strExecEventFlag);
				SQL_condition_value = Runtimevalue.getProperty(strenvironmentvariable);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"='"+ SQL_condition_value +"'";

			Eshop_SQLServer = EShopstmt.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("ESHOPSQLDBSelectConditionEnvvar Error : " + e);
			ESHOPSQLDBSelectConditionEnvvar=false;
		}

		try{
			Eshop_SQLServer.next();
			Actual_Value = Eshop_SQLServer.getString(1).trim();

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "No Record found for this query: "+query, true);
			log.info("ESHOPSQLDBSelectConditionEnvvar Error : ");
			ESHOPSQLDBSelectConditionEnvvar=false;
		}

		try{
			if(!Eshop_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","ESHOPSQLDBSelectConditionEnvvar", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches the expected value : '"+Expected_value+"'", 2);
					ESHOPSQLDBSelectConditionEnvvar=true;
				}else if(!(Actual_Value.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);
					ESHOPSQLDBSelectConditionEnvvar=false;
				}
			}

			else if(Eshop_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "ESHOPSQLDBSelectConditionEnvvar", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					ESHOPSQLDBSelectConditionEnvvar=true;
				}else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					ESHOPSQLDBSelectConditionEnvvar=false;
				}
			}
		}catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectConditionEnvvar",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("ESHOPSQLDBSelectConditionEnvvar Error : " + e);
			ESHOPSQLDBSelectConditionEnvvar=false;
		}
		return ESHOPSQLDBSelectConditionEnvvar;
	}

	/**
	 * 
	 * @Objective <b>Verifies the SQL DB select</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strenvironmentvariable
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>06-SEP-2016</b>
	 */

	public static boolean ESHOPSQLDBSelectCompConditionEnvvar(String sqltablename, String strsqlcolumnname,String strsqlcondition,String strenvironmentvariable,String strExpectedvalueenvvar,int strExecEventFlag)throws Exception  {
		boolean ESHOPSQLDBSelectCompConditionEnvvar= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String SQL_condition_value = null;		
		String Expected_value = "";
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("ESHOPSQLDBSelectCompConditionEnvvar",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("ESHOPSQLDBSelectCompConditionEnvvar",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("ESHOPSQLDBSelectCompConditionEnvvar",strsqlcondition,strExecEventFlag);
				Expected_value=Runtimevalue.getProperty(strExpectedvalueenvvar);
				SQL_condition_value = Runtimevalue.getProperty(strenvironmentvariable);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectCompConditionEnvvar",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"='"+ SQL_condition_value +"'";

			Eshop_SQLServer = EShopstmt.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectCompConditionEnvvar",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("ESHOPSQLDBSelectCompConditionEnvvar Error : " + e);
			ESHOPSQLDBSelectCompConditionEnvvar=false;
		}

		try{
			Eshop_SQLServer.next();
			Actual_Value = Eshop_SQLServer.getString(1).trim();

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectCompConditionEnvvar",  "No Record found for this query: "+query, true);
			log.info("ESHOPSQLDBSelectCompConditionEnvvar Error : ");
			ESHOPSQLDBSelectCompConditionEnvvar=false;
		}

		try{
			if(!Eshop_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","ESHOPSQLDBSelectCompConditionEnvvar", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches the expected value : '"+Expected_value+"'", 2);
					ESHOPSQLDBSelectCompConditionEnvvar=true;
				}else if(!(Actual_Value.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectCompConditionEnvvar",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);
					ESHOPSQLDBSelectCompConditionEnvvar=false;
				}
			}

			else if(Eshop_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "ESHOPSQLDBSelectCompConditionEnvvar", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+Expected_value+"'", 2);
					ESHOPSQLDBSelectCompConditionEnvvar=true;
				}else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectCompConditionEnvvar",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+Expected_value+"'", true);  	 
					ESHOPSQLDBSelectCompConditionEnvvar=false;
				}
			}
		}catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "ESHOPSQLDBSelectCompConditionEnvvar",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("ESHOPSQLDBSelectCompConditionEnvvar Error : " + e);
			ESHOPSQLDBSelectCompConditionEnvvar=false;
		}
		return ESHOPSQLDBSelectCompConditionEnvvar;
	}

	/**
	 * 
	 * @Objective <b>This method is to get the values from webTable with pagination</b>
	 * @param getValueFromPOM
	 * @param primaryCol
	 * @param secondaryCol
	 * @param expRowValue
	 * @param expTextFromTable
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>02-Sep-16</b>
	 */

	public static boolean webTableVerifyDetailshashtable(String getValueFromPOM, String strTestObject, String primaryCol, String secondaryCol, String expRowValue, String expTextFromTable, int strExecEventFlag) throws Exception{

		boolean webTableVerifyDetailshashtable = false;
		//Initialized the variables as dummy
		int itemexist = 0;
		int testcasestatus = 0;
		String primaryColumnName = null;
		String secColumnName = null;
		String expectedRowValue = null;
		String expectedTextFromTable = null;
		String attributeID = null;
		String actualValueFromTable = null;
		String actualvalue = null;

		// Get the values from excel sheet to find the rows and columns from web table
		if(strExecEventFlag == 1){

			primaryColumnName = RetrieveTestDataValue("webTableVerifyDetailshashtable", primaryCol, strExecEventFlag);
			secColumnName = RetrieveTestDataValue("webTableVerifyDetailshashtable", secondaryCol, strExecEventFlag);
			expectedRowValue = RetrieveTestDataValue("webTableVerifyDetailshashtable", expRowValue, strExecEventFlag);
			expectedTextFromTable = RetrieveTestDataValue("webTableVerifyDetailshashtable", expTextFromTable, strExecEventFlag);

		}

		if(primaryColumnName == null || secColumnName == null || expectedRowValue == null || expectedTextFromTable == null){
			Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtable",  "Required details are not provided in the data sheet.", false);
			return false;
		}

		try{	
			//Get the ID from xpath(whole web table) 
			attributeID = selectByLocatorType(getValueFromPOM).getAttribute("id");


			List<WebElement> headerColumns = driver.findElements(By.xpath("//*[@id='"+attributeID+"']/thead/tr[1]/th"));

			System.out.println("Header count : "+ headerColumns.size());

			int findPrimaryColumn = 0;

			for(int headerCounterPrimaryColumn = 1;headerCounterPrimaryColumn <= headerColumns.size(); headerCounterPrimaryColumn++){
				String GetTitleHeaders = driver.findElement(By.xpath("//*[@id='"+attributeID+"']/thead/tr[1]/th["+ headerCounterPrimaryColumn +"]")).getText();
				if(GetTitleHeaders.equalsIgnoreCase(primaryColumnName)){
					findPrimaryColumn = headerCounterPrimaryColumn;
					break;
				}
			}

			if(findPrimaryColumn == 0){
				Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtable",  "The primary key column : '"+ primaryColumnName +"' in not available in the table : "+ strTestObject +"'.", true);
				return false;
			}

			String[] headernames=secColumnName.split("\\|");
			String[] headervalues=expectedTextFromTable.split("\\|");

			Hashtable<String, String> hstable = new Hashtable<String, String>();

			for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++)
			{
				hstable.put(headernames[hdrcount], headervalues[hdrcount]);
			}

			Hashtable<String, Integer> hstableheaders = new Hashtable<String, Integer>();
			for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++)
			{
				int iheaderexist = 0;
				for( int headercolumn = 1; headercolumn <= headerColumns.size(); headercolumn++)
				{
					String headername = driver.findElement(By.xpath("//*[@id='"+attributeID+"']/thead/tr[1]/th["+ headercolumn +"]")).getText();
					if(headernames[hdrcount].equalsIgnoreCase(headername)){
						hstableheaders.put(headernames[hdrcount], headercolumn);
						iheaderexist = 1;
						break;
					}
				}

				if(iheaderexist == 0){
					hstableheaders.put(headernames[hdrcount], 0);
				}

			}

			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='"+attributeID+"']/tbody/tr"));

			for(int row=1; row <= rows.size(); row++)
			{
				actualValueFromTable = driver.findElement(By.xpath("//table[@id='"+attributeID+"']/tbody/tr["+row+"]/td["+findPrimaryColumn+"]")).getText();

				if(actualValueFromTable.equals(expectedRowValue)){
					for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++) {
						if(hstableheaders.get(headernames[hdrcount]) != 0){
							actualvalue = driver.findElement(By.xpath("//table[@id='"+attributeID+"']/tbody/tr["+row+"]/td["+ hstableheaders.get(headernames[hdrcount]) +"]")).getText();

							if(actualvalue.equals(hstable.get(headernames[hdrcount]))){
								Report_Functions.ReportEventSuccess(doc, "1","webTableVerifyDetailshashtable", "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' matches with the expected value : '"+ hstable.get(headernames[hdrcount]) +"'.", 2);
							}else{
								Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtable",  "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' doesnot matches with the expected value : '"+ hstable.get(headernames[hdrcount]) +"'.", true);
								testcasestatus = 1;
							}
						}else{
							Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtable",  "The required header : '"+ headernames[hdrcount] +"' is not present in the table : '"+ strTestObject +"'.", true);
							testcasestatus = 1;
						}
					}
					itemexist = 1;
					break;
				}
			}

			if(itemexist == 0){
				Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtable",  "The value : '"+ expectedRowValue +"' is not present in the table : '"+ strTestObject +"'.", true);
				testcasestatus = 1;
			}


		}	catch(Exception e){
			Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtable",  "Exception occured. Error message is : "+ e +"'.", true);
			testcasestatus = 1;	 
		}
		if(testcasestatus == 0){
			webTableVerifyDetailshashtable = true;
		}
		return webTableVerifyDetailshashtable;
	}

	/**
	 * 
	 * @Objective <b>This method is to get the values from webTable with pagination</b>
	 * @param getValueFromPOM
	 * @param primaryCol
	 * @param secondaryCol
	 * @param expRowValue
	 * @param expTextFromTable
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>07-Sep-16</b>
	 */

	public static boolean webTableVerifyMultiDetailshashtable(String getValueFromPOM, String strTestObject, String primaryCol_1, String primaryCol_2, String secondaryCol, String expRowValue_1, String expRowValue_2, String expTextFromTable, int strExecEventFlag) throws Exception{

		boolean webTableVerifyMultiDetailshashtable = false;
		//Initialized the variables as dummy
		int itemexist = 0;
		int testcasestatus = 0;
		String primaryColumnName_1 = null;
		String primaryColumnName_2 = null;
		String secColumnName = null;
		String expectedRowValue_1 = null;
		String expectedRowValue_2 = null;
		String expectedTextFromTable = null;
		String attributeID = null;
		String actualValueFromTable_1 = null;
		String actualValueFromTable_2 = null;
		String actualvalue = null;

		// Get the values from excel sheet to find the rows and columns from web table
		if(strExecEventFlag == 1){

			primaryColumnName_1 = RetrieveTestDataValue("webTableVerifyMultiDetailshashtable", primaryCol_1, strExecEventFlag);
			primaryColumnName_2 = RetrieveTestDataValue("webTableVerifyMultiDetailshashtable", primaryCol_2, strExecEventFlag);
			secColumnName = RetrieveTestDataValue("webTableVerifyMultiDetailshashtable", secondaryCol, strExecEventFlag);
			expectedRowValue_1 = RetrieveTestDataValue("webTableVerifyMultiDetailshashtable", expRowValue_1, strExecEventFlag);
			expectedRowValue_2 = RetrieveTestDataValue("webTableVerifyMultiDetailshashtable", expRowValue_2, strExecEventFlag);
			expectedTextFromTable = RetrieveTestDataValue("webTableVerifyMultiDetailshashtable", expTextFromTable, strExecEventFlag);

		}

		if(primaryColumnName_1 == null || primaryColumnName_2==null || secColumnName == null || expectedRowValue_1 == null || expectedRowValue_2 == null || expectedTextFromTable == null){
			Report_Functions.ReportEventFailure(doc,  "webTableVerifyMultiDetailshashtable",  "Required details are not provided in the data sheet.", false);
			return false;
		}

		try{	
			//Get the ID from xpath(whole web table) 
			attributeID = selectByLocatorType(getValueFromPOM).getAttribute("id");


			List<WebElement> headerColumns = driver.findElements(By.xpath("//*[@id='"+attributeID+"']/thead/tr[1]/th"));

			int findPrimaryColumn_1 = 0;

			for(int headerCounterPrimaryColumn = 1;headerCounterPrimaryColumn <= headerColumns.size(); headerCounterPrimaryColumn++){
				String GetTitleHeaders = driver.findElement(By.xpath("//*[@id='"+attributeID+"']/thead/tr[1]/th["+ headerCounterPrimaryColumn +"]")).getText();
				if(GetTitleHeaders.equalsIgnoreCase(primaryColumnName_1)){
					findPrimaryColumn_1 = headerCounterPrimaryColumn;
					break;
				}
			}

			if(findPrimaryColumn_1 == 0){
				Report_Functions.ReportEventFailure(doc,  "webTableVerifyMultiDetailshashtable",  "The primary key column : '"+ primaryColumnName_1 +"' in not available in the table : "+ strTestObject +"'.", true);
				return false;
			}

			int findPrimaryColumn_2 = 0;

			for(int headerCounterPrimaryColumn = 1;headerCounterPrimaryColumn <= headerColumns.size(); headerCounterPrimaryColumn++){
				String GetTitleHeaders = driver.findElement(By.xpath("//*[@id='"+attributeID+"']/thead/tr[1]/th["+ headerCounterPrimaryColumn +"]")).getText();
				if(GetTitleHeaders.equalsIgnoreCase(primaryColumnName_2)){
					findPrimaryColumn_2 = headerCounterPrimaryColumn;
					break;
				}
			}

			if(findPrimaryColumn_2 == 0){
				Report_Functions.ReportEventFailure(doc,  "webTableVerifyMultiDetailshashtable",  "The primary key column : '"+ primaryColumnName_2 +"' in not available in the table : "+ strTestObject +"'.", true);
				return false;
			}

			String[] headernames=secColumnName.split("\\|");
			String[] headervalues=expectedTextFromTable.split("\\|");

			Hashtable<String, String> hstable = new Hashtable<String, String>();

			for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++)
			{
				hstable.put(headernames[hdrcount], headervalues[hdrcount]);
			}

			Hashtable<String, Integer> hstableheaders = new Hashtable<String, Integer>();
			for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++)
			{
				int iheaderexist = 0;
				for( int headercolumn = 1; headercolumn <= headerColumns.size(); headercolumn++)
				{
					String headername = driver.findElement(By.xpath("//*[@id='"+attributeID+"']/thead/tr[1]/th["+ headercolumn +"]")).getText();
					if(headernames[hdrcount].equalsIgnoreCase(headername)){
						hstableheaders.put(headernames[hdrcount], headercolumn);
						iheaderexist = 1;
						break;
					}
				}

				if(iheaderexist == 0){
					hstableheaders.put(headernames[hdrcount], 0);
				}

			}

			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='"+attributeID+"']/tbody/tr"));

			for(int row=1; row <= rows.size(); row++)
			{
				actualValueFromTable_1 = driver.findElement(By.xpath("//table[@id='"+attributeID+"']/tbody/tr["+row+"]/td["+findPrimaryColumn_1+"]")).getText();
				actualValueFromTable_2 = driver.findElement(By.xpath("//table[@id='"+attributeID+"']/tbody/tr["+row+"]/td["+findPrimaryColumn_2+"]")).getText();


				if(actualValueFromTable_1.equals(expectedRowValue_1) && actualValueFromTable_2.equals(expectedRowValue_2)){
					for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++) {
						if(hstableheaders.get(headernames[hdrcount]) != 0){
							actualvalue = driver.findElement(By.xpath("//table[@id='"+attributeID+"']/tbody/tr["+row+"]/td["+ hstableheaders.get(headernames[hdrcount]) +"]")).getText();

							if(actualvalue.equals(hstable.get(headernames[hdrcount]))){
								Report_Functions.ReportEventSuccess(doc, "1","webTableVerifyMultiDetailshashtable", "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' matches with the expected value : '"+ hstable.get(headernames[hdrcount]) +"'.", 2);
							}else{
								Report_Functions.ReportEventFailure(doc,  "webTableVerifyMultiDetailshashtable",  "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' doesnot matches with the expected value : '"+ hstable.get(headernames[hdrcount]) +"'.", true);
								testcasestatus = 1;
							}
						}else{
							Report_Functions.ReportEventFailure(doc,  "webTableVerifyMultiDetailshashtable",  "The required header : '"+ headernames[hdrcount] +"' is not present in the table : '"+ strTestObject +"'.", true);
							testcasestatus = 1;
						}
					}
					itemexist = 1;
					break;
				}
			}

			if(itemexist == 0){
				Report_Functions.ReportEventFailure(doc,  "webTableVerifyMultiDetailshashtable",  "The value : '"+ expectedRowValue_1 +"' or '"+ expectedRowValue_2 +"' is not present in the table : '"+ strTestObject +"'.", true);
				testcasestatus = 1;
			}


		}	catch(Exception e){
			Report_Functions.ReportEventFailure(doc,  "webTableVerifyMultiDetailshashtable",  "Exception occured. Error message is : "+ e +"'.", true);
			testcasestatus = 1;	 
		}
		if(testcasestatus == 0){
			webTableVerifyMultiDetailshashtable = true;
		}
		return webTableVerifyMultiDetailshashtable;
	}

	/**
	 * 
	 * @Objective <b>Verifies the SQL DB select</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strsqlcondition2
	 * @param strenvironmentvariable
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>07-Sep-2016</b>
	 */

	public static boolean SQLDBSelectMultiConditionEnvvar(String sqltablename, String strsqlcolumnname,String strsqlcondition_1,String strsqlcondition_2,String strenvironmentvariable,String strExpectedvalue,int strExecEventFlag)throws Exception  {
		boolean SQLDBSelectMultiConditionEnvvar= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition_1 = null;
		String SQL_condition_2 = null;
		String SQL_condition_value = null;		
		String Expected_value = "";
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBSelectMultiConditionEnvvar",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBSelectMultiConditionEnvvar",strsqlcolumnname,strExecEventFlag);
				SQL_condition_1=RetrieveTestDataValue("SQLDBSelectMultiConditionEnvvar",strsqlcondition_1,strExecEventFlag);
				SQL_condition_2=RetrieveTestDataValue("SQLDBSelectMultiConditionEnvvar",strsqlcondition_2,strExecEventFlag);
				Expected_value=RetrieveTestDataValue("SQLDBSelectMultiConditionEnvvar",strExpectedvalue,strExecEventFlag);
				SQL_condition_value = Runtimevalue.getProperty(strenvironmentvariable);
			}

			if(Table_name==null || Column_name==null || SQL_condition_1==null || SQL_condition_2==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBSelectMultiConditionEnvvar",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition_1+"='"+ SQL_condition_value +"' and "+ SQL_condition_2;

			System.out.print(query);

			rs_SQLServer = stmt.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelectMultiConditionEnvvar",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelectMultiConditionEnvvar Error : " + e);
			SQLDBSelectMultiConditionEnvvar=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1).trim();

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelectMultiConditionEnvvar",  "No Record found for this query: "+query, true);
			log.info("SQLDBSelectMultiConditionEnvvar Error : ");
			SQLDBSelectMultiConditionEnvvar=false;
		}

		try{
			if(!rs_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","SQLDBSelectMultiConditionEnvvar", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition_1+"='"+ SQL_condition_value +"' and "+ SQL_condition_2 +"' matches the expected value : '"+Expected_value+"'", 2);
					SQLDBSelectMultiConditionEnvvar=true;
				}else if(!(Actual_Value.equals(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelectMultiConditionEnvvar",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition_1+"='"+ SQL_condition_value +"' and "+ SQL_condition_2 +"' does not match the expected value : '"+Expected_value+"'", true);
					SQLDBSelectMultiConditionEnvvar=false;
				}
			}

			else if(rs_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBSelectMultiConditionEnvvar", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition_1+"='"+ SQL_condition_value +"' and "+ SQL_condition_2 +"' matches with the expected value :'"+Expected_value+"'", 2);
					SQLDBSelectMultiConditionEnvvar=true;
				}else if(!(Expected_value.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelectMultiConditionEnvvar",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition_1+"='"+ SQL_condition_value +"' and "+ SQL_condition_2 +"' does not match the expected value : '"+Expected_value+"'", true);  	 
					SQLDBSelectMultiConditionEnvvar=false;
				}
			}
		}catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelectMultiConditionEnvvar",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelectMultiConditionEnvvar Error : " + e);
			SQLDBSelectMultiConditionEnvvar=false;
		}
		return SQLDBSelectMultiConditionEnvvar;
	}

	
			/**
			 * 
			 * @Objective <b>This method is to get the values based on the headers from first webTable and validate the data in second webtable if both webtables having 1 common property.</b>
			 * @param getValueFromPOM
			 * @param primaryCol
			 * @param secondaryCol
			 * @param expRowValue
			 * @param expTextFromTable
			 * @param strExecEventFlag
			 * @author <b>Yogendra</b>
			 * @since <b>08-Sep-16</b>
			 */
			
				public static boolean webTableVerifyDetailshashtablelabel(String getValueFromPOM, String strTestObject, String primaryCol, String secondaryCol, String expRowValue, String expTextFromTable, int strExecEventFlag) throws Exception{
				
				boolean webTableVerifyDetailshashtablelabel = false;
				//Initialized the variables as dummy
				int itemexist = 0;
				int testcasestatus = 0;
				String primaryColumnName = null;
				String secColumnName = null;
				String expectedRowValue = null;
				String expectedTextFromTable = null;
				String attributeID = null;
				String actualValueFromTable = null;
				String actualvalue = null;
				
				String Expected_value = null;
				String Current_Date=null;
				
				// Get the values from excel sheet to find the rows and columns from web table
					if(strExecEventFlag == 1){
						
						primaryColumnName = RetrieveTestDataValue("webTableVerifyDetailshashtablelabel", primaryCol, strExecEventFlag);
						secColumnName = RetrieveTestDataValue("webTableVerifyDetailshashtablelabel", secondaryCol, strExecEventFlag);
						expectedRowValue = RetrieveTestDataValue("webTableVerifyDetailshashtablelabel", expRowValue, strExecEventFlag);
						expectedTextFromTable = RetrieveTestDataValue("webTableVerifyDetailshashtablelabel", expTextFromTable, strExecEventFlag);
						
					}
					
						if(primaryColumnName == null || secColumnName == null || expectedRowValue == null || expectedTextFromTable == null){
							Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtablelabel",  "Required details are not provided in the data sheet.", false);
							return false;
						}
						
					try{	
					//Get the ID from xpath(whole web table) 
					attributeID = selectByLocatorType(getValueFromPOM).getAttribute("aria-labelledby");
					
					
					List<WebElement> headerColumns = driver.findElements(By.xpath("//table[@aria-labelledby='"+attributeID+"']/thead/tr[1]/th"));
					
					System.out.println("Header count : "+ headerColumns.size());
					
					int findPrimaryColumn = 0;
					
					for(int headerCounterPrimaryColumn = 1;headerCounterPrimaryColumn <= headerColumns.size(); headerCounterPrimaryColumn++){
						String GetTitleHeaders = driver.findElement(By.xpath("//table[@aria-labelledby='"+attributeID+"']/thead/tr[1]/th["+ headerCounterPrimaryColumn +"]")).getText();

						if(GetTitleHeaders.trim().equalsIgnoreCase(primaryColumnName.trim())){
							findPrimaryColumn = headerCounterPrimaryColumn;
							break;
						}
					}

					if(findPrimaryColumn == 0){
						Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtablelabel",  "The primary key column : '"+ primaryColumnName +"' in not available in the table : "+ strTestObject +"'.", true);
						return false;
					}
					
					String[] headernames=secColumnName.split("\\|");
					String[] headervalues=expectedTextFromTable.split("\\|");
					
					Hashtable<String, String> hstable = new Hashtable<String, String>();

					for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++)
					{
						hstable.put(headernames[hdrcount], headervalues[hdrcount]);
					}
					
					Hashtable<String, Integer> hstableheaders = new Hashtable<String, Integer>();
					for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++)
					{
						int iheaderexist = 0;
						for( int headercolumn = 1; headercolumn <= headerColumns.size(); headercolumn++)
						{
							String headername = driver.findElement(By.xpath("//table[@aria-labelledby='"+attributeID+"']/thead/tr[1]/th["+ headercolumn +"]")).getText();
							if(headernames[hdrcount].trim().equalsIgnoreCase(headername.trim())){
							hstableheaders.put(headernames[hdrcount], headercolumn);
							iheaderexist = 1;
							break;
							}
						}
						
						if(iheaderexist == 0){
						hstableheaders.put(headernames[hdrcount], 0);
						}
						
					}
				
				        	List<WebElement> rows = driver.findElements(By.xpath("//table[@aria-labelledby='"+attributeID+"']/tbody/tr"));
			            	
			                for(int row=1; row <= rows.size(); row++)
			                    {
			                        actualValueFromTable = driver.findElement(By.xpath("//table[@aria-labelledby='"+attributeID+"']/tbody/tr["+row+"]/td["+findPrimaryColumn+"]")).getText();
				
				                        if(actualValueFromTable.equals(expectedRowValue)){
				                        	for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++) {
				                        		if(hstableheaders.get(headernames[hdrcount]) != 0){
					                        		actualvalue = driver.findElement(By.xpath("//table[@aria-labelledby='"+attributeID+"']/tbody/tr["+row+"]/td["+ hstableheaders.get(headernames[hdrcount]) +"]")).getText();
					                        		
					                        		if(hstable.get(headernames[hdrcount]).contains("CURRENT_DATE")){
					                        		
						                    			DateFormat dateFormat = new SimpleDateFormat(hstable.get(headernames[hdrcount]).split("\\;")[1]);
						                    			Date date = new Date();
						                    			Current_Date = dateFormat.format(date);
						                    			Expected_value = Current_Date.trim();
						                    			actualvalue = actualvalue.split(" ")[0];
							                        	if(actualvalue.equals(Expected_value)){
								    						Report_Functions.ReportEventSuccess(doc, "1","webTableVerifyDetailshashtablelabel", "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' matches with the expected value : '"+ Expected_value +"'.", 2);
								    					}else{
								    						Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtablelabel",  "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' doesnot matches with the expected value : '"+ Expected_value +"'.", true);
								    						testcasestatus = 1;
								    					}
					                        		
						                        	} else if(hstable.get(headernames[hdrcount]).contains("ENVVARCOMPARE")){
						                        		
							                    			String strenvvar = hstable.get(headernames[hdrcount]).split("\\;")[1];
							                    			Expected_value = Runtimevalue.getProperty(strenvvar);
							                    			if(Expected_value != null){
									                        	if(actualvalue.equals(Expected_value)){
										    						Report_Functions.ReportEventSuccess(doc, "1","webTableVerifyDetailshashtablelabel", "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' matches with the expected value : '"+ Expected_value +"'.", 2);
										    					}else{
										    						Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtablelabel",  "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' doesnot matches with the expected value : '"+ Expected_value +"'.", true);
										    						testcasestatus = 1;
										    					}
							                    			} else {
							                    				
									    						Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtablelabel",  "The value in the environment variable : '"+ strenvvar +"' is empty.", true);
									    						testcasestatus = 1;
							                    				
							                    			}
							                        }	else {
								                        	if(actualvalue.equals(hstable.get(headernames[hdrcount]))){
									    						Report_Functions.ReportEventSuccess(doc, "1","webTableVerifyDetailshashtablelabel", "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' matches with the expected value : '"+ hstable.get(headernames[hdrcount]) +"'.", 2);
									    					}else{
									    						Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtablelabel",  "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' doesnot matches with the expected value : '"+ hstable.get(headernames[hdrcount]) +"'.", true);
									    						testcasestatus = 1;
									    					}
						                        	}	
				                        		}else{
				                        			Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtablelabel",  "The required header : '"+ headernames[hdrcount] +"' is not present in the table : '"+ strTestObject +"'.", true);
				                        			testcasestatus = 1;
				                        		}
			                        		}
				                        	itemexist = 1;
				                        	break;
				                        }
			                    }
			                
			                if(itemexist == 0){
		            			Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtablelabel",  "The value : '"+ expectedRowValue +"' is not present in the table : '"+ strTestObject +"'.", true);
		            			testcasestatus = 1;
			                }

			            
					}	catch(Exception e){
						Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtablelabel",  "Exception occured. Error message is : "+ e +"'.", true);
						testcasestatus = 1;	 
					}
					if(testcasestatus == 0){
						webTableVerifyDetailshashtablelabel = true;
					}
				return webTableVerifyDetailshashtablelabel;
			}

	/**
	* 
	* @Objective <b>Wait for file to download and rename it after downloading.</b>
	* @param getvalueFromPOM
	* @param strTestObject
	* @param strColumnName
	* @param strExecEventFlag
	* @author <b>Yogendra</b>
	* @since <b>10-Sep-2016</b>
	*/

	public static boolean Waitforfiletodownload(String strdownloadfilepath, String strColumnName, int strExecEventFlag )throws Exception  {
		long first;
		long second;
		String strData = null;
		int iflag =0;
		boolean Waitforfiletodownload=false;

			if(strExecEventFlag==1){
			strData=RetrieveTestDataValue("Waitforfiletodownload",strColumnName,strExecEventFlag);
			}
			
			if(strData == null){
				Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtablelabel",  "Required details are not provided in the data sheet.", false);
				return false;
			}


		try{
				while(true){
				
				File file = new File(System.getProperty("user.dir")+Param.getProperty(strdownloadfilepath)+"\\");
				File files[] = file.listFiles();
				if(files.length > 0){
					
					first = files[0].length()/1024;
					Thread.sleep(1000);
					
					file = new File(System.getProperty("user.dir")+Param.getProperty(strdownloadfilepath)+"\\");
					files = file.listFiles();
					
					second = files[0].length()/1024;
					
					if(first != second){
						
						System.out.println("File downloading");
						
					}else{
						
							System.out.println("File download completed");
							files[0].renameTo(new File(System.getProperty("user.dir")+Param.getProperty(strdownloadfilepath)+"\\"+strData));
							iflag = 1;
							break;
							
						 }
					}
					
				}
				
				if(iflag==1){
					Report_Functions.ReportEventSuccess(doc, "1", "Waitforfiletodownload", "The file has been downloaded successfully in the path :'"+ System.getProperty("user.dir")+Param.getProperty(strdownloadfilepath) +"' and the file has been renamed to :'"+ strData +"'.", 2);
					Waitforfiletodownload=true;
				}
		} catch (Exception e){
		  Report_Functions.ReportEventFailure(doc,"Waitforfiletodownload", "Error occured while downloading the file and rename that file. Error description is :"+e.getMessage(), true);
		  Waitforfiletodownload=false;
		}
		 return Waitforfiletodownload;
	}			

	
	/**
	 * 
	 * @Objective <b>Verifies the values in the calender year dropdown is displayed based on the confiurations.</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param yearscounttoverify
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>12-Sep-2016</b>
	 */
	
	public static boolean WeblistCalenderYearsVerify(String getValueFromPOM, String strTestObject, String yearscounttoverify,int strExecEventFlag)throws Exception  {
		
		boolean WeblistCalenderYearsVerify= false;
		String yearsconfigured = null;
		String[] yearvalues = null;
		String[] weblistvalues = null;
		Date date = null;
		String dateFormat = null;
		int stringToInteger = 0;
		String integerToString = null;
		
		try {
			if(strExecEventFlag==1){
				yearsconfigured=RetrieveTestDataValue("WeblistCalenderYearsVerify",yearscounttoverify,strExecEventFlag);
			}

			if(yearsconfigured==null){
				Report_Functions.ReportEventFailure(doc,  "WeblistCalenderYearsVerify",  "Required details are not provided in the Data Sheet.", false);
				return false;
			}

			int yearcount = Integer.parseInt(yearsconfigured);
			
			date = new Date();
			dateFormat = new SimpleDateFormat("yyyy").format(date);
						
			List<String> rowValues = new ArrayList<String>();
			
			for(int k = yearcount;k>=0;k--){
			
				stringToInteger = Integer.parseInt(dateFormat) - k;
				
				integerToString = Integer.toString(stringToInteger);
			
				rowValues.add(integerToString);
			
			}
  
			// You can then put this back into an array if necessary
			yearvalues = (String[]) rowValues.toArray(new String[rowValues.size()]);

		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "WeblistCalenderYearsVerify",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("WeblistCalenderYearsVerify Error : " + e);
			WeblistCalenderYearsVerify=false;
		}


		try{
			
			Select se = new Select(selectByLocatorType(getValueFromPOM));
			List<WebElement> options = se.getOptions();
			
		    //if you want to get all elements text into array list
		    List<String> all_elements_text=new ArrayList<String>();

		    for(int j=0; j<options.size(); j++){

		        //loading text of each element in to array all_elements_text
		        all_elements_text.add(options.get(j).getText());

		    }
		    
		    weblistvalues = (String[]) all_elements_text.toArray(new String[all_elements_text.size()]);
		    
			if(yearvalues.length == weblistvalues.length)
			{
		         for (int i=0; i<yearvalues.length; i++){

		             if (weblistvalues[i].equals(yearvalues[i])){
							Report_Functions.ReportEventSuccess(doc, "1","WeblistCalenderYearsVerify", "The actual value : '"+ weblistvalues[i] +"' in the dropdown : "+ strTestObject +"' matches the expected value : '"+ yearvalues[i] +"'.", 2);
							WeblistCalenderYearsVerify=true;
		             } else{
							Report_Functions.ReportEventFailure(doc,  "WeblistCalenderYearsVerify",  "The actual value : '"+ weblistvalues[i] +"' in the dropdown : "+ strTestObject +"' doesn't matches the expected value : '"+ yearvalues[i] +"'.", true);
							WeblistCalenderYearsVerify=false;
		             }
		          }
			} else {
				Report_Functions.ReportEventFailure(doc,  "WeblistCalenderYearsVerify",  "The number of items present in the dropdown : "+ strTestObject +"' doesn't matches with the number of expected items : '"+ yearvalues.length +"'.", true);
				WeblistCalenderYearsVerify=false;
			}
			
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "WeblistCalenderYearsVerify",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("WeblistSQLDBitemsverify Error : " + e);
			WeblistCalenderYearsVerify=false;
		}
		return WeblistCalenderYearsVerify;
	}
	
	
	/**
	 * 
	 * @Objective <b>Verify to comapare SQL DB environment variable</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param Propertyfilename
	 * @param strEnvironmentVariable
	 * @param strExecEventFlag
	 * @author <b>Lakshman</b>
	 * @since <b>02-November-15</b>
	 */
	public static boolean SQLDBEnvironmentVariableCompare(String sqltablename, String strsqlcolumnname,String strsqlcondition,String Propertyfilename,String strEnvironmentVariable,int strExecEventFlag)throws Exception  {
		boolean SQLDBEnvironmentVariableCompare= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String strExpectedvalue = null;
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBEnvironmentVariableCompare",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBEnvironmentVariableCompare",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBEnvironmentVariableCompare",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBEnvironmentVariableCompare",  "Required details are not provided in test data sheet.", false);
				return false;
			}
			if(Propertyfilename.equalsIgnoreCase("Runtime")){
				strExpectedvalue=Runtimevalue.getProperty(strEnvironmentVariable);
				strExpectedvalue=strExpectedvalue.trim();
			}

			if(Propertyfilename.equalsIgnoreCase("Param")){
				strExpectedvalue=Param.getProperty(strEnvironmentVariable);
				strExpectedvalue=strExpectedvalue.trim();
			}

		}catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBEnvironmentVariableCompare",  "Error occured .Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBEnvironmentVariableCompare Error : " + e);
			SQLDBEnvironmentVariableCompare=false;
		}

		try {
			if(strExpectedvalue==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBEnvironmentVariableCompare",  "The Value in environment variable: '"+ strEnvironmentVariable +"' is empty", true);
				SQLDBEnvironmentVariableCompare=false;
			}else{
				//Query to Execute      
				query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+"";
				rs_SQLServer= stmt.executeQuery(query);
			}
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBEnvironmentVariableCompare",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBEnvironmentVariableCompare Error : " + e);
			SQLDBEnvironmentVariableCompare=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1);
		} catch (Exception NullPointerException) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBEnvironmentVariableCompare",  "No Record found for this query: "+query, true);
			log.info("SQLDBEnvironmentVariableCompare Error : ");
			SQLDBEnvironmentVariableCompare=false;
		}

		try{
			if(!rs_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equals(strExpectedvalue)){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBEnvironmentVariableCompare", "The selected value : '"+Actual_Value+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' matches with the expected value : '"+strExpectedvalue+"' ", 2);
					SQLDBEnvironmentVariableCompare=true;
				}else if(!(Actual_Value.equals(strExpectedvalue))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBEnvironmentVariableCompare",  "The Selected value : '"+Actual_Value+"' in the column : '"+Column_name+"' of table : '"+Table_name+"' does not match with the expected value : '"+strExpectedvalue+"' ", true);
					SQLDBEnvironmentVariableCompare=false;
				}
			}else if(rs_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(strExpectedvalue.equals("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBEnvironmentVariableCompare", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected value :'"+strExpectedvalue+"'", 2);
					SQLDBEnvironmentVariableCompare=true;
				}else if(!(strExpectedvalue.equals("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBEnvironmentVariableCompare",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected value : '"+strExpectedvalue+"'", true);  	 
					SQLDBEnvironmentVariableCompare=false;
				}
			}
		}catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBEnvironmentVariableCompare",  "Error occured while comparing the values in SQL Query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBEnvironmentVariableCompare Error : " + e);
			SQLDBEnvironmentVariableCompare=false;
		}
		return SQLDBEnvironmentVariableCompare;
	}


	/**
	 * 
	 * @Objective <b>To update the XML node value based on XPath</b>
	 * @param Location
	 * @param AttributeXPath
	 * @param strsqlcondition
	 * @param ValueToSet
	 * @param strExecEventFlag
	 * @author <b>Karthik</b>
	 * @since <b>13-Sep-16</b>
	 */

	public static boolean XMLTextUpdate_TestData(String Location,String AttributeXPath,String ValueToSet,int strExecEventFlag) throws Exception, IOException{
		log.info("XMLTextUpdate_TestData");
		boolean Executionstatus=false;

		if (Location==""){
			Report_Functions.ReportEventFailure(doc,  "XMLTextUpdate_TestData",  "Location Path for WebConfig is missing", true);
			return false;
		}
		if (AttributeXPath==""){
			Report_Functions.ReportEventFailure(doc,  "XMLTextUpdate_TestData",  "AttributeXPath Path for WebConfig is missing", true);
			return false;
		}
		if (ValueToSet==""){
			Report_Functions.ReportEventFailure(doc,  "XMLTextUpdate_TestData",  "ValueToSet in the node for WebConfig is missing", true);
			return false;
		}
		if(strExecEventFlag==1){
			Location=RetrieveTestDataValue("XMLTextUpdate_TestData",Location,strExecEventFlag);
			Location=Param.getProperty(Location);
			AttributeXPath=RetrieveTestDataValue("XMLTextUpdate_TestData",AttributeXPath,strExecEventFlag);
			ValueToSet=RetrieveTestDataValue("XMLTextUpdate_TestData",ValueToSet,strExecEventFlag);
			//ValueToSet=Param.getProperty(ValueToSet);
			
			System.out.println("Location "+Location);
			System.out.println("AttributeXPath "+AttributeXPath);
			System.out.println("ValueToSet "+ValueToSet);


			if (ValueToSet==""){
				Report_Functions.ReportEventFailure(doc,  "XMLTextUpdate_TestData",  "Value present in Property File Seems to Empty.Please check the property file.", true);
				return false;
			}
		}

		try {

			factory=DocumentBuilderFactory.newInstance();
			builder=factory.newDocumentBuilder();;
			file=new File("//\\"+Location);

			document=builder.parse(file);
			document.getDocumentElement().normalize();
			NodeList nodeList=null;
			xpath=XPathFactory.newInstance().newXPath();
			nodeList=(NodeList)xpath.compile(AttributeXPath).evaluate(document,XPathConstants.NODESET);

			nodeList.item(0).setTextContent(ValueToSet);


			TransformerFactory transFormerFactory = TransformerFactory.newInstance();
			Transformer transFormer = transFormerFactory.newTransformer();
			DOMSource source = new DOMSource(document);

			StreamResult result = new StreamResult(file);
			transFormer.transform(source, result);
			result.getOutputStream().close();
			Executionstatus=true;
			Report_Functions.ReportEventSuccess(doc, "1", "XMLTextUpdate_TestData", "XML config File '"+Location+"' has been updated successfully for the tag '"+AttributeXPath+"' with the value set as '"+ValueToSet+"'", 2);
			Thread.sleep(2000);
		} catch (ParserConfigurationException e) {
			log.info("catch ParserConfigurationException");
			e.printStackTrace();
			Report_Functions.ReportEventFailure(doc,  "XMLTextUpdate_TestData",  "ValueToSet in the node for WebConfig is Not successfull due to reason: '"+e.getMessage()+"'", false);
			Executionstatus=false;

		} catch (Exception e) {
			log.info("catch Excpetion");
			e.printStackTrace();
			Report_Functions.ReportEventFailure(doc,  "XMLTextUpdate_TestData",  "Exception occured during XMLTextUpdate_TestData. Reason: '"+e.getMessage()+"'", false);
			Executionstatus=false;
		}
		builder=null;
		xpath=null;
		nodeList=null;
		return Executionstatus;
	}

	
		/**
	 * 
	 * @Objective <b>To Find and Replace a text</b>
	 * @author <b>Karthik</b>
	 * @since <b>20-Sep-16</b>
	 */

	public static boolean XML_Find_and_Replace(String strLocation,String FromTag, String ToTag, int strExecEventFlag) throws Exception, IOException{
		log.info("XML_Find_and_Replace");
		boolean Executionstatus=false;
		String Location=null;	

		if (FromTag==""){
			Report_Functions.ReportEventFailure(doc,  "XML_Find_and_Replace",  "ValueToSet in the node for WebConfig is missing", true);
			return false;
		}
		if (ToTag==""){
			Report_Functions.ReportEventFailure(doc,  "XML_Find_and_Replace",  "ValueToSet in the node for WebConfig is missing", true);
			return false;
		}
		if(strExecEventFlag==1){
			Location=RetrieveTestDataValue("XML_Find_and_Replace",strLocation,strExecEventFlag);
            Location=Param.getProperty(Location);
			FromTag=RetrieveTestDataValue("XML_Find_and_Replace",FromTag,strExecEventFlag);
			ToTag=RetrieveTestDataValue("XML_Find_and_Replace",ToTag,strExecEventFlag);
		}

		try {
			ArrayList<String> lines = new ArrayList<String>();
			String line = null;
			
		       File f1=null;
		        FileReader fr=null;
		        BufferedReader br=null;
		        FileWriter fw=null;
		        BufferedWriter out=null;
		        try {
		            f1 = new File(Location);
		            fr = new FileReader(f1);
		            br = new BufferedReader(fr);
		            while ((line = br.readLine()) != null) {
		                if (line.contains(FromTag))
		                    line = line.replace(FromTag, ToTag);
		                lines.add(line);
		                lines.add("\n");
		            }

		            fw = new FileWriter(f1);
		            out = new BufferedWriter(fw);
		            for (String s : lines)
		                out.write(s);
		            out.flush();

		        } catch (Exception ex) {
		            ex.printStackTrace();
					Report_Functions.ReportEventFailure(doc,  "XML_Find_and_Replace",  "Exception occured while replacing the from tag '"+FromTag+"' to tag '"+ToTag+"'. Reason: '"+ex.getMessage()+"'", false);
					Executionstatus=false;
		        } finally {
		            try{
		            fr.close();
		            br.close();
		            out.close();
		            }catch(IOException ioe){
		            ioe.printStackTrace();
		            }
		        }
				Report_Functions.ReportEventSuccess(doc, "1", "XML_Find_and_Replace", "The tag '"+ToTag+"' has been updated successfully in the XML file '"+Location+"'", 2);
				Executionstatus=true;
		} catch (Exception e) {
			log.info("catch Exception");
			e.printStackTrace();
			Report_Functions.ReportEventFailure(doc,  "XML_Find_and_Replace",  "Exception occured while replacing the from tag '"+FromTag+"' to tag '"+ToTag+"'. Reason: '"+e.getMessage()+"'", false);
			Executionstatus=false;
		}
		builder=null;
		xpath=null;
		nodeList=null;
		return Executionstatus;
	}

	/**
	 * 
	 * @Objective <b>Verifies the SQL DB Update</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>03-October-2016</b>
	 */

	public static boolean SQLDBPastDateUpdateCDR(String sqltablename, String strsqlcolumnname,String strsqlcondition, String seperator, int Monthdifference, String envvar, int strExecEventFlag)throws Exception  {
		boolean SQLDBPastDateUpdateCDR= false;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBPastDateUpdateCDR",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBPastDateUpdateCDR",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBPastDateUpdateCDR",strsqlcondition,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBPastDateUpdateCDR",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			String query=null;

		    Calendar cal = Calendar.getInstance();
		    int month = cal.get(Calendar.MONTH) + Monthdifference;
		    int year = cal.get(Calendar.YEAR);
		    
		    String expmonth = Integer.toString(month);
		
		    if (month / 10 == 0) {
		    	expmonth = "0" + expmonth;
	        }
		    
		    
		    String ExpectedDate = year + seperator +"01" + seperator + expmonth +" "+ "00:00:00.000";
		    
		    String envvardate = "01" + "/" + expmonth + "/" + year;
		    
		    Runtimevalue.setProperty(envvar, envvardate);
		    
			query = "update "+Table_name+" set "+ Column_name + " = '"+ ExpectedDate +"' where "+SQL_condition;

			System.out.println(query);
			//Query to Execute      
			//String query = "update "+Table_name+" set "+Column_name+"="+Column_Value+" where "+SQL_condition;

			stmt.execute(query);
			SQLDBPastDateUpdateCDR=true;
			Report_Functions.ReportEventSuccess(doc, "1", "SQLDBPastDateUpdateCDR", "The SQL Update Query : "+ query + " executed successfully.", 2);

		} catch (Exception e) {
			SQLDBPastDateUpdateCDR=false;
			Report_Functions.ReportEventFailure(doc,  "SQLDBPastDateUpdateCDR",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBPastDateUpdateCDR Error : " + e);
		}
		return SQLDBPastDateUpdateCDR;
	}

	
	/**
	 * 
	 * @Objective <b>Verifies the SQL DB Update</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcolumnvalue
	 * @param strsqlcondition
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>03-October-2016</b>
	 */

	public static boolean SQLDBDateUpdateStoreinEnvVar(String sqltablename, String strsqlcolumnname,  String strsqlcondition, String DBType, String strCdrYearDay, String strCallDateDay,String seperator, int Monthdifference, String envvar, int strExecEventFlag)throws Exception  {
		boolean SQLDBDateUpdateStoreinEnvVar= false;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		String expectedType = null;
		String Cdr_Year_Day = null;
		String Call_date_Day = null;
		String ExpectedDate = null;

		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBDateUpdateStoreinEnvVar",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBDateUpdateStoreinEnvVar",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBDateUpdateStoreinEnvVar",strsqlcondition,strExecEventFlag);
				expectedType  = RetrieveTestDataValue("SQLDBDateUpdateStoreinEnvVar", DBType, strExecEventFlag);
				Cdr_Year_Day  = RetrieveTestDataValue("SQLDBDateUpdateStoreinEnvVar", strCdrYearDay, strExecEventFlag);
				Call_date_Day = RetrieveTestDataValue("SQLDBDateUpdateStoreinEnvVar", strCallDateDay, strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null || expectedType==null || Cdr_Year_Day==null || Call_date_Day==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBDateUpdateStoreinEnvVar",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			String query=null;

		    Calendar cal = Calendar.getInstance();
		    int month = cal.get(Calendar.MONTH) + Monthdifference;
		    int year = cal.get(Calendar.YEAR);
		    
		    String expmonth = Integer.toString(month);
		
		    if (month / 10 == 0) {
		    	expmonth = "0" + expmonth;
	        }
		    
		    if(expectedType.equalsIgnoreCase("REPORTDB")){
		    ExpectedDate = year + seperator +"01" + seperator + expmonth +" "+ "00:00:00.000";
		    }
		    
		    if(expectedType.equalsIgnoreCase("MONTHDB")){
		    ExpectedDate = year + seperator + expmonth + seperator + "01" +"000000";
		    }
		    
		    Runtimevalue.setProperty(envvar, ExpectedDate);
		    
			query = "update "+Table_name+" set "+ Column_name + " = '"+ ExpectedDate +"' where "+SQL_condition;

			System.out.println(query);
			//Query to Execute      
			//String query = "update "+Table_name+" set "+Column_name+"="+Column_Value+" where "+SQL_condition;

			stmt.execute(query);
			SQLDBDateUpdateStoreinEnvVar=true;
			Report_Functions.ReportEventSuccess(doc, "1", "SQLDBDateUpdateStoreinEnvVar", "The SQL Update Query : "+ query + " executed successfully.", 2);

		} catch (Exception e) {
			SQLDBDateUpdateStoreinEnvVar=false;
			Report_Functions.ReportEventFailure(doc,  "SQLDBDateUpdateStoreinEnvVar",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBDateUpdateStoreinEnvVar Error : " + e);
		}
		return SQLDBDateUpdateStoreinEnvVar;
	}
	
	/**
	 * 
	 * @Objective <b>This method is to enter the values for DatePicker using Java script executor</b>
	 * @param locatorType
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @param strColumnName
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>03-Oct-16</b>
	 */

	public static boolean javaScriptDatePickerEnv(String getValueFromPOM, String strTestObject, String envvar) throws Exception{

		boolean elementStatus = false;
		String elementValue = null;

		try{

			elementValue = Runtimevalue.getProperty(envvar);

			if(elementValue == null){

				Report_Functions.ReportEventFailure(doc,  "javaScriptDatePickerEnv",  "Required details are not provided in the environment variable : '"+ envvar +"'.", false);
				return false;

			}

			//Get the locatorType from POM during runtime
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('"+getValueFromPOM+"').value = '"+elementValue+"'");
			Report_Functions.ReportEventSuccess (doc,"1","","Date picker value '"+elementValue+"' is entered for "+strTestObject+" ",2);	
			elementStatus = true;

		}catch(Exception e){

			log.info("Exception occurred in web table radio button :"+e.getMessage());
			e.printStackTrace();
			Report_Functions.ReportEventFailure(doc,"","Date picker value '"+elementValue+"' is not entered for "+strTestObject+" " , true);  
			elementStatus = false;

		}

		return elementStatus;

	}	


	/**
	 * 
	 * @Objective <b>This method is to check the status for selected checkbox or selected radio button</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Yogendra</b>
	 * @since <b>07-Oct-16</b>
	 */

	public static boolean webElementSelectedStatus(String getValueFromPOM, String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{

			if(selectByLocatorType(getValueFromPOM).getAttribute("disabled").equals("true")){

				Report_Functions.ReportEventSuccess(doc, "1", "webElementSelectedStatus", "The status of the object '"+ strTestObject +"' is checked or selected as expected.",   3);
				elementStatus = true;
			}

			else if(selectByLocatorType(getValueFromPOM).getAttribute("disabled").equals(null)){

				Report_Functions.ReportEventFailure(doc, "webElementSelectedStatus", "The status of the object '"+ strTestObject +"' is unchecked or unselected.", true);
				elementStatus = false;
			}

		}catch(StaleElementReferenceException e){

			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				return webCheckBoxCheckStatus(getValueFromPOM, strTestObject);
			}
		}catch(Exception e){

			Report_Functions.ReportEventFailure(doc, "webElementSelectedStatus", "Error occured while checking the status of the element. Error description is : '"+ e.getMessage() +"'.", true);
			elementStatus = false;
			log.info("No Element Found to check the status of radio button or checkbox" + e);

		}

		return elementStatus;
	}

	/**
	 * 
	 * @Objective <b>This method is to get the values from webTable with pagination</b>
	 * @param getValueFromPOM
	 * @param primaryCol
	 * @param secondaryCol
	 * @param expRowValue
	 * @param expTextFromTable
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>12-Oct-16</b>
	 */

	public static boolean webTableVerifyDetailshashtable_2(String getValueFromPOM, String strTestObject, String primaryCol, String secondaryCol, String expRowValue, String expTextFromTable, int strExecEventFlag) throws Exception{

		boolean webTableVerifyDetailshashtable_2 = false;
		//Initialized the variables as dummy
		int itemexist = 0;
		int testcasestatus = 0;
		String primaryColumnName = null;
		String secColumnName = null;
		String expectedRowValue = null;
		String expectedTextFromTable = null;
		String attributeID = null;
		String actualValueFromTable = null;
		String actualvalue = null;

		// Get the values from excel sheet to find the rows and columns from web table
		if(strExecEventFlag == 1){

			primaryColumnName = RetrieveTestDataValue("webTableVerifyDetailshashtable_2", primaryCol, strExecEventFlag);
			secColumnName = RetrieveTestDataValue("webTableVerifyDetailshashtable_2", secondaryCol, strExecEventFlag);
			expectedRowValue = RetrieveTestDataValue("webTableVerifyDetailshashtable_2", expRowValue, strExecEventFlag);
			expectedTextFromTable = RetrieveTestDataValue("webTableVerifyDetailshashtable_2", expTextFromTable, strExecEventFlag);

		}

		if(primaryColumnName == null || secColumnName == null || expectedRowValue == null || expectedTextFromTable == null){
			Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtable_2",  "Required details are not provided in the data sheet.", false);
			return false;
		}

		try{	
			//Get the ID from xpath(whole web table) 
			attributeID = selectByLocatorType(getValueFromPOM).getAttribute("id");


			List<WebElement> headerColumns = driver.findElements(By.xpath("//*[@id='"+attributeID+"']//thead/tr[1]/th"));

			System.out.println("Header count : "+ headerColumns.size());

			int findPrimaryColumn = 0;

			for(int headerCounterPrimaryColumn = 1;headerCounterPrimaryColumn <= headerColumns.size(); headerCounterPrimaryColumn++){
				String GetTitleHeaders = driver.findElement(By.xpath("//*[@id='"+attributeID+"']//thead/tr[1]/th["+ headerCounterPrimaryColumn +"]")).getText();
				if(GetTitleHeaders.equalsIgnoreCase(primaryColumnName)){
					findPrimaryColumn = headerCounterPrimaryColumn;
					break;
				}
			}

			if(findPrimaryColumn == 0){
				Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtable_2",  "The primary key column : '"+ primaryColumnName +"' in not available in the table : "+ strTestObject +"'.", true);
				return false;
			}

			String[] headernames=secColumnName.split("\\|");
			String[] headervalues=expectedTextFromTable.split("\\|");

			Hashtable<String, String> hstable = new Hashtable<String, String>();

			for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++)
			{
				hstable.put(headernames[hdrcount], headervalues[hdrcount]);
			}

			Hashtable<String, Integer> hstableheaders = new Hashtable<String, Integer>();
			for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++)
			{
				int iheaderexist = 0;
				for( int headercolumn = 1; headercolumn <= headerColumns.size(); headercolumn++)
				{
					String headername = driver.findElement(By.xpath("//*[@id='"+attributeID+"']//thead/tr[1]/th["+ headercolumn +"]")).getText();
					if(headernames[hdrcount].equalsIgnoreCase(headername)){
						hstableheaders.put(headernames[hdrcount], headercolumn);
						iheaderexist = 1;
						break;
					}
				}

				if(iheaderexist == 0){
					hstableheaders.put(headernames[hdrcount], 0);
				}

			}

			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='"+attributeID+"']//tbody/tr"));

			for(int row=1; row <= rows.size(); row++)
			{
				actualValueFromTable = driver.findElement(By.xpath("//*[@id='"+attributeID+"']//tbody/tr["+row+"]/td["+findPrimaryColumn+"]")).getText();

				if(actualValueFromTable.equals(expectedRowValue)){
					for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++) {
						if(hstableheaders.get(headernames[hdrcount]) != 0){
							actualvalue = driver.findElement(By.xpath("//*[@id='"+attributeID+"']//tbody/tr["+row+"]/td["+ hstableheaders.get(headernames[hdrcount]) +"]")).getText();

							if(actualvalue.equals(hstable.get(headernames[hdrcount]))){
								Report_Functions.ReportEventSuccess(doc, "1","webTableVerifyDetailshashtable_2", "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' matches with the expected value : '"+ hstable.get(headernames[hdrcount]) +"'.", 2);
							}else{
								Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtable_2",  "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' doesnot matches with the expected value : '"+ hstable.get(headernames[hdrcount]) +"'.", true);
								testcasestatus = 1;
							}
						}else{
							Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtable_2",  "The required header : '"+ headernames[hdrcount] +"' is not present in the table : '"+ strTestObject +"'.", true);
							testcasestatus = 1;
						}
					}
					itemexist = 1;
					break;
				}
			}

			if(itemexist == 0){
				Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtable_2",  "The value : '"+ expectedRowValue +"' is not present in the table : '"+ strTestObject +"'.", true);
				testcasestatus = 1;
			}


		}	catch(Exception e){
			Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtable_2",  "Exception occured. Error message is : "+ e +"'.", true);
			testcasestatus = 1;	 
		}
		if(testcasestatus == 0){
			webTableVerifyDetailshashtable_2 = true;
		}
		return webTableVerifyDetailshashtable_2;
	}


	/**
	 * 
	 * @Objective <b>This method is to select the values based on the tooltips from the dropdown</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Yogendra</b>
	 * @since <b>20-Oct-2016</b>
	 */

	public static boolean WebListSelectTooltip(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag)throws Exception  {

		String strData=null;
		String tooltip=null;
		boolean WebListSelectTooltip=false;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebListSelectTooltip",strColumnName,strExecEventFlag);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebListSelectTooltip",  "Required details are not provided in the data sheet.", false);
				return false;
			}

			//tooltip = selectByLocatorType(getValueFromPOM).getAttribute("title");
			
			//To get all the options that are selected in the dropdown.
			Select dropdown= new Select(selectByLocatorType(getValueFromPOM));
			List<WebElement> allSelectedOptions = dropdown.getOptions();
			for (WebElement webElement : allSelectedOptions)
			{
			tooltip = webElement.getAttribute("title");
				if(tooltip.trim().equalsIgnoreCase(strData)){
				new Select(selectByLocatorType(getValueFromPOM)).selectByVisibleText(webElement.getText());
				Report_Functions.ReportEventSuccess(doc,"1","WebListSelectTooltip","The Item '" +  tooltip + "' is selected from the  '"+strTestObject+"' List box successfully" ,3);
				WebListSelectTooltip=true;
				break;
				}
			}
			


		} catch (StaleElementReferenceException e) {
			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				return WebListSelectTooltip(getValueFromPOM, strTestObject, strColumnName, strExecEventFlag);

			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebListSelectTooltip","The Item '" +  tooltip + " was not selected from the  '"+strTestObject+"' List box " , true); 
			WebListSelectTooltip=false;
			log.info("No Element Found to select text" + e);
		}
		return WebListSelectTooltip;
	}

	/**
	 * 
	 * @Objective <b>This method is to get the values from webTable with pagination</b>
	 * @param getValueFromPOM
	 * @param primaryCol
	 * @param secondaryCol
	 * @param expRowValue
	 * @param expTextFromTable
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>25-Oct-16</b>
	 */

	public static boolean webTableVerifyDetailshashtableenvvar(String getValueFromPOM, String strTestObject, String primaryCol, String secondaryCol, String expRowValue, String expTextFromTable, int strExecEventFlag) throws Exception{

		boolean webTableVerifyDetailshashtableenvvar = false;
		//Initialized the variables as dummy
		int itemexist = 0;
		int testcasestatus = 0;
		String primaryColumnName = null;
		String secColumnName = null;
		String expectedRowValue = null;
		String expectedTextFromTable = null;
		String attributeID = null;
		String actualValueFromTable = null;
		String actualvalue = null;

		String Expected_value = null;
		String Current_Date=null;
		
		// Get the values from excel sheet to find the rows and columns from web table
		if(strExecEventFlag == 1){

			primaryColumnName = RetrieveTestDataValue("webTableVerifyDetailshashtableenvvar", primaryCol, strExecEventFlag);
			secColumnName = RetrieveTestDataValue("webTableVerifyDetailshashtableenvvar", secondaryCol, strExecEventFlag);
			expectedRowValue = Runtimevalue.getProperty(expRowValue);
			expectedTextFromTable = RetrieveTestDataValue("webTableVerifyDetailshashtableenvvar", expTextFromTable, strExecEventFlag);

		}

		if(primaryColumnName == null || secColumnName == null || expectedRowValue == null || expectedTextFromTable == null){
			Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtableenvvar",  "Required details are not provided in the data sheet.", false);
			return false;
		}

		try{	
			//Get the ID from xpath(whole web table) 
			attributeID = selectByLocatorType(getValueFromPOM).getAttribute("id");


			List<WebElement> headerColumns = driver.findElements(By.xpath("//*[@id='"+attributeID+"']//thead/tr[1]/th"));

			System.out.println("Header count : "+ headerColumns.size());

			int findPrimaryColumn = 0;

			for(int headerCounterPrimaryColumn = 1;headerCounterPrimaryColumn <= headerColumns.size(); headerCounterPrimaryColumn++){
				String GetTitleHeaders = driver.findElement(By.xpath("//*[@id='"+attributeID+"']//thead/tr[1]/th["+ headerCounterPrimaryColumn +"]")).getText();
				if(GetTitleHeaders.equalsIgnoreCase(primaryColumnName)){
					findPrimaryColumn = headerCounterPrimaryColumn;
					break;
				}
			}

			if(findPrimaryColumn == 0){
				Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtableenvvar",  "The primary key column : '"+ primaryColumnName +"' in not available in the table : "+ strTestObject +"'.", true);
				return false;
			}

			String[] headernames=secColumnName.split("\\|");
			String[] headervalues=expectedTextFromTable.split("\\|");

			Hashtable<String, String> hstable = new Hashtable<String, String>();

			for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++)
			{
				hstable.put(headernames[hdrcount], headervalues[hdrcount]);
			}

			Hashtable<String, Integer> hstableheaders = new Hashtable<String, Integer>();
			for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++)
			{
				int iheaderexist = 0;
				for( int headercolumn = 1; headercolumn <= headerColumns.size(); headercolumn++)
				{
					String headername = driver.findElement(By.xpath("//*[@id='"+attributeID+"']//thead/tr[1]/th["+ headercolumn +"]")).getText();
					if(headernames[hdrcount].equalsIgnoreCase(headername)){
						hstableheaders.put(headernames[hdrcount], headercolumn);
						iheaderexist = 1;
						break;
					}
				}

				if(iheaderexist == 0){
					hstableheaders.put(headernames[hdrcount], 0);
				}

			}

			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='"+attributeID+"']//tbody/tr"));

			for(int row=1; row <= rows.size(); row++)
			{
				actualValueFromTable = driver.findElement(By.xpath("//*[@id='"+attributeID+"']//tbody/tr["+row+"]/td["+findPrimaryColumn+"]")).getText();

				if(actualValueFromTable.equals(expectedRowValue)){
					for( int hdrcount = 0; hdrcount <= headernames.length - 1; hdrcount++) {
						if(hstableheaders.get(headernames[hdrcount]) != 0){
							actualvalue = driver.findElement(By.xpath("//*[@id='"+attributeID+"']//tbody/tr["+row+"]/td["+ hstableheaders.get(headernames[hdrcount]) +"]")).getText();

					        if(hstable.get(headernames[hdrcount]).contains("CURRENT_DATE")){
					                        		
						       DateFormat dateFormat = new SimpleDateFormat(hstable.get(headernames[hdrcount]).split("\\;")[1]);
						       Date date = new Date();
						       Current_Date = dateFormat.format(date);
						       Expected_value = Current_Date.trim();
						       actualvalue = actualvalue.split(" ")[0];
								   if(actualvalue.equals(Expected_value)){
									  Report_Functions.ReportEventSuccess(doc, "1","webTableVerifyDetailshashtableenvvar", "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' matches with the expected value : '"+ Expected_value +"'.", 2);
								   }else{
									  Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtableenvvar",  "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' doesnot matches with the expected value : '"+ Expected_value +"'.", true);
									  testcasestatus = 1;
								   }
					                        		
						     } else if(hstable.get(headernames[hdrcount]).contains("FUTUREDATECOMPARE")){
	                        		
							    	   DateFormat dateFormat = new SimpleDateFormat(hstable.get(headernames[hdrcount]).split("\\;")[1]);
								       Date date = new Date();
										Calendar expdate = Calendar.getInstance();
										expdate.setTime(date);
										expdate.add(Calendar.DATE, Integer.parseInt(hstable.get(headernames[hdrcount]).split("\\;")[2]) - 1);
										Current_Date = dateFormat.format(expdate.getTime());
										Expected_value = Current_Date.trim();
										
								       actualvalue = actualvalue.split(" ")[0];
									   if(actualvalue.equals(Expected_value)){
										  Report_Functions.ReportEventSuccess(doc, "1","webTableVerifyDetailshashtableenvvar", "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' matches with the expected value : '"+ Expected_value +"'.", 2);
									   }else{
										  Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtableenvvar",  "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' doesnot matches with the expected value : '"+ Expected_value +"'.", true);
										  testcasestatus = 1;
									   }
									 } else if(hstable.get(headernames[hdrcount]).contains("ENVVARCOMPARE")){
						                        		
										String strenvvar = hstable.get(headernames[hdrcount]).split("\\;")[1];
										Expected_value = Runtimevalue.getProperty(strenvvar);
										if(Expected_value != null){
											if(actualvalue.equals(Expected_value)){
											    Report_Functions.ReportEventSuccess(doc, "1","webTableVerifyDetailshashtableenvvar", "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' matches with the expected value : '"+ Expected_value +"'.", 2);
										    }else{
												Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtableenvvar",  "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' doesnot matches with the expected value : '"+ Expected_value +"'.", true);
											    testcasestatus = 1;
											}
								    } else {
								                    				
										Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtableenvvar",  "The value in the environment variable : '"+ strenvvar +"' is empty.", true);
										testcasestatus = 1;
							                    				
							    }
							 }	else {
								if(actualvalue.equals(hstable.get(headernames[hdrcount]))){
								Report_Functions.ReportEventSuccess(doc, "1","webTableVerifyDetailshashtableenvvar", "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' matches with the expected value : '"+ hstable.get(headernames[hdrcount]) +"'.", 2);
								}else{
								Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtableenvvar",  "The actual value : '"+ actualvalue +"' in the table : "+ strTestObject +"' doesnot matches with the expected value : '"+ hstable.get(headernames[hdrcount]) +"'.", true);
								testcasestatus = 1;
							 }
						  }
						}else{
							Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtableenvvar",  "The required header : '"+ headernames[hdrcount] +"' is not present in the table : '"+ strTestObject +"'.", true);
							testcasestatus = 1;
						}
					}
					itemexist = 1;
					break;
				}
			}

			if(itemexist == 0){
				Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtableenvvar",  "The value : '"+ expectedRowValue +"' is not present in the table : '"+ strTestObject +"'.", true);
				testcasestatus = 1;
			}


		}	catch(Exception e){
			Report_Functions.ReportEventFailure(doc,  "webTableVerifyDetailshashtableenvvar",  "Exception occured. Error message is : "+ e +"'.", true);
			testcasestatus = 1;	 
		}
		if(testcasestatus == 0){
			webTableVerifyDetailshashtableenvvar = true;
		}
		return webTableVerifyDetailshashtableenvvar;
	}



	/**
	 * 
	 * @Objective <b>This method is to verify whether the selected item is only existing in dropdown</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strColumnName <b>Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Yogendra</b>
	 * @since <b>31-Oct-16</b>
	 * 
	 */

	public static boolean WebListCheckSelectedExist(String getValueFromPOM, String strTestObject, String strColumnName, int itemscount, int strExecEventFlag) throws Exception{

		String strData = null;
		boolean WebListCheckSelectedExist = false;
		String selectedValue = null;
		Select select_dropdown = null;
		int itemlistcount;
		
		try{

			if(strExecEventFlag==1){

				strData = RetrieveTestDataValue("WebListCheckSelectedExist", strColumnName, strExecEventFlag);
			}	
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebListCheckSelectedExist",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			selectedValue = new Select(selectByLocatorType(getValueFromPOM)).getFirstSelectedOption().getText();
			
			select_dropdown = new Select(selectByLocatorType(getValueFromPOM));  
			itemlistcount = select_dropdown.getOptions().size();

			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				if(selectedValue.trim().equalsIgnoreCase(strData.trim()) && itemlistcount == itemscount){
					Report_Functions.ReportEventSuccess(doc,"1","WebListCheckSelectedExist",strTestObject +"'s selected dropdown value '"+selectedValue + "' matches with the Expected Value '"+strData+"' and this is the only item present in the dropdown." ,3);
					WebListCheckSelectedExist = true;
				}else{
					Report_Functions.ReportEventFailure(doc,"WebListCheckSelectedExist",strTestObject +"'s selected dropdown value '"+selectedValue + "' does not matches with the Expected Value '"+strData+"' and this is not the only item present in the dropdown."  , true); 
					WebListCheckSelectedExist=false;
				}
			}else{
				Report_Functions.ReportEventFailure(doc,"WebListCheckSelectedExist",strTestObject +"'s selected dropdown value '"+selectedValue + "'is not displayed" , true); 
				WebListCheckSelectedExist=false;
			}

		}catch(StaleElementReferenceException e){

			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				return WebListCheckSelectedExist;

			}

		}catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"WebListCheckSelectedExist","Selected dropdown value is '" +  selectedValue + " is not shown in "+strTestObject+"" , true); 
			WebListCheckSelectedExist=false;
			log.info("No Element Found to select text" + e);
		}

		return WebListCheckSelectedExist;

	}

	/**
	 * 
	 * @Objective <b>Compare Address from GAF ENV</b>
	 * @author <b>Yogendra</b>
	 * @since <b>16-NOV-16</b>
	 */
	
	public static boolean WebElementValueCompareFromGAF(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag)throws Exception{
		String actualResult=null;
		String strData=null;
		boolean WebElementValueCompareFromGAF=false;
		try{
			if (strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebElementValueCompareFromGAF",strColumnName,strExecEventFlag);
				strData=GAFValue.getProperty(strData);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,  "WebElementValueCompareFromGAF",  "Required details are not provided in the data sheet.", false);
				return false;
			}
			actualResult = selectByLocatorType(getValueFromPOM).getAttribute("value");

		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementValueCompareFromGAF", "Error occured while getting the text from the WebElement :'"+strTestObject+"'and the error description is :"+e.getMessage(), true);
			WebElementValueCompareFromGAF=false;
		}

		try{
			if((actualResult.trim()).equalsIgnoreCase(strData.trim())){
				Report_Functions.ReportEventSuccess(doc,"1", "WebElementValueCompareFromGAF", "The  Actual Value '" +actualResult+ "' matches with the Expected value '"+strData+ "' in the input field '"+strTestObject+"'", 2);
				WebElementValueCompareFromGAF=true;
			}else{
				Report_Functions.ReportEventFailure(doc,"WebElementValueCompareFromGAF", "The  Actual Value '" +actualResult+ "' does not match with the Expected value '"+strData+ "' in the input field '"+strTestObject+"'", true);
				WebElementValueCompareFromGAF=false;
			}
		} catch (Exception e){
			Report_Functions.ReportEventFailure(doc,"WebElementValueCompareFromGAF", "Error occured while comparing actual and expected values. Error description is :"+e.getMessage(), true);
			WebElementValueCompareFromGAF=false;
		}
		return WebElementValueCompareFromGAF;
	}


	/**
	 * 
	 * @Objective <b>This method is to check the status for selected checkbox</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Yogendra</b>
	 * @since <b>18-Nov-16</b>
	 */

	public static boolean WebCheckboxUncheckedStatus(String getValueFromPOM, String strTestObject) throws Exception{


		boolean elementStatus = false;

		try{


			if(!selectByLocatorType(getValueFromPOM).getAttribute("checked").equals("true")){

				Report_Functions.ReportEventSuccess(doc, "1", "WebCheckboxUncheckedStatus", "The feature '"+ strTestObject +"' is in deactive status.",   3);
				elementStatus = true;
			}else {

				Report_Functions.ReportEventFailure(doc, "WebCheckboxUncheckedStatus", "The feature '"+ strTestObject +"' is in active status.", true);
				elementStatus = false;
			}

		}catch(StaleElementReferenceException e){

			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				return WebCheckboxUncheckedStatus(getValueFromPOM, strTestObject);
			}
			
		}catch(NullPointerException e){

				log.info("Null pointer exception occurred :"+e);
				Report_Functions.ReportEventSuccess(doc, "1", "WebCheckboxUncheckedStatus", "The feature '"+ strTestObject +"' is in deactive status.",   3);
				elementStatus = true;

		}catch(Exception e){

			Report_Functions.ReportEventFailure(doc, "WebCheckboxUncheckedStatus", "Exception occured while finding the element. Error description is : "+ e.getMessage() +".", true);
			elementStatus = false;
			log.info("No Element Found to check the checkbox" + e);

		}

		return elementStatus;
	}
	
	/**
	 * 
	 * @Objective <b>This method is to check the status for selected checkbox</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Yogendra</b>
	 * @since <b>18-Nov-16</b>
	 */

	public static boolean WebCheckboxcheckedStatus(String getValueFromPOM, String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{

					
			if(selectByLocatorType(getValueFromPOM).getAttribute("checked").equals("true")){

				Report_Functions.ReportEventSuccess(doc, "1", "WebCheckboxcheckedStatus", "The feature '"+ strTestObject +"' is in active status.",   3);
				elementStatus = true;
			}else {

				Report_Functions.ReportEventFailure(doc, "WebCheckboxcheckedStatus", "The feature '"+ strTestObject +"' is in deactive status.", true);
				elementStatus = false;
			}

		}catch(StaleElementReferenceException e){

			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				return WebCheckboxcheckedStatus(getValueFromPOM, strTestObject);
			}
		}catch(NullPointerException e){

				log.info("Null pointer exception occurred :"+e);
				Report_Functions.ReportEventFailure(doc, "WebCheckboxcheckedStatus", "The feature '"+ strTestObject +"' is in deactive status.", true);
				elementStatus = false;

		}catch(Exception e){

			Report_Functions.ReportEventFailure(doc, "WebCheckboxcheckedStatus", "Exception occured while finding the element. Error description is : "+ e.getMessage() +".", true);
			elementStatus = false;
			log.info("No Element Found to check the checkbox" + e);

		}

		return elementStatus;
	}

	
		/**
	 * 
	 * @Objective <b>This method is to click the webelement based on the value from the test data.</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @author <b>Yogendra</b>
	 * @since <b>18-Nov-2016</b>
	 * 
	 */

	public static boolean DynamicElementClick(String getValueFromPOM, String strTestObject)throws Exception  {

		boolean DynamicElementClick= false;
		try {

			selectByLocatorType(getValueFromPOM).click();
			Report_Functions.ReportEventSuccess (doc,"1","DynamicElementClick","'"+strTestObject+"' is clicked successfully ",2);	
			DynamicElementClick=true;
		}catch(StaleElementReferenceException e){

			log.info("StaleElementReferenceException function Catch block. Calling Refresh Object.");
			RefreshObject(getValueFromPOM);

			if((selectByLocatorType(getValueFromPOM)).isDisplayed()){
				return DynamicElementClick(getValueFromPOM, strTestObject);
			}else{
				DynamicElementClick = false;
			}

		}

		catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"DynamicElementClick","Exception occured while finding the element. Error description is : "+ e.getMessage() +"." , true); 
			DynamicElementClick=false;
			log.info("No Element Found to click :" + e);
		}
		return DynamicElementClick;
	}
	
	/**
	 * 
	 * @Objective <b>This method is to check the status of the toggle</b>
	 * @param getValueFromPOM
	 * @param strTestObject
	 * @author <b>Yogendra</b>
	 * @since <b>21-Nov-16</b>
	 */

	public static boolean Togglecheckdisabled(String getValueFromPOM, String strTestObject) throws Exception{

		boolean elementStatus = false;

		try{

					
			if(selectByLocatorType(getValueFromPOM).getAttribute("disabled").equals("true")){

				Report_Functions.ReportEventSuccess(doc, "1", "Togglecheckdisabled", "The feature '"+ strTestObject +"' is disabled as expected.",   3);
				elementStatus = true;
			}else {

				Report_Functions.ReportEventFailure(doc, "Togglecheckdisabled", "The feature '"+ strTestObject +"' is not disabled.", true);
				elementStatus = false;
			}

		}catch(StaleElementReferenceException e){

			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){
				return Togglecheckdisabled(getValueFromPOM, strTestObject);
			}
		}catch(Exception e){

			Report_Functions.ReportEventFailure(doc, "Togglecheckdisabled", "Exception occured while finding the element. Error description is : "+ e.getMessage() +".", true);
			elementStatus = false;
			log.info("No Element Found to check the checkbox" + e);

		}

		return elementStatus;
	}


	/**
	 * 
	 * @Objective <b>This method is to select the values from the dropdown using data from Property file value</b>
	 * @param getValueFromPOM <b>- Return the value from hashtables in POM classes</b>
	 * @param strTestObject <b>Enter the values for test result</b>
	 * @param strColumnName <b>Read the values from excel sheet</b>
	 * @param strExecEventFlag <b>Based on integer value, will execute property or excel file</b>
	 * @author <b>Yogendra</b>
	 * @since <b>23-Nov-16</b>
	 */

	public static boolean WebEditEnterTextFromGAFEnv(String getValueFromPOM, String strTestObject,String strColumnName,int strExecEventFlag)throws Exception  {

		String strData=null;
		boolean WebEditEnterTextFromGAFEnv=false;
		try {
			if(strExecEventFlag==1){
				strData=RetrieveTestDataValue("WebEditEnterTextFromGAFEnv",strColumnName,strExecEventFlag);
				strData=GAFValue.getProperty(strData);
			}
			if(strData==null){
				Report_Functions.ReportEventFailure(doc,"WebEditEnterTextFromGAFEnv","Required details are not provided in the data sheet." , true);
				return false;
			}

			selectByLocatorType(getValueFromPOM).clear();
			selectByLocatorType(getValueFromPOM).sendKeys(strData);
			
			Report_Functions.ReportEventSuccess(doc,"1","","The text '"+strData+"' is entered in the  '"+strTestObject+"' successfully" ,3);
			WebEditEnterTextFromGAFEnv=true;

		} catch (StaleElementReferenceException e) {
			log.info("StaleElementReferenceException Exception captured. Doing Recursive call...");
			RefreshObject(getValueFromPOM);
			if(selectByLocatorType(getValueFromPOM).isDisplayed()){

				return WebEditEnterTextFromGAFEnv(getValueFromPOM, strTestObject, strColumnName,strExecEventFlag);

			}
		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,"","The text '" +  strData + "' is not entered in the  '"+strTestObject+"'."+e.getMessage() , true); 
			WebEditEnterTextFromGAFEnv=false;
			log.info("No Element Found to select text" + e);
		}
		return WebEditEnterTextFromGAFEnv;
	}
	
	/**
	 * @Objective <b>Verifies the SQL GBR DB select with Expected Value from ENV Variable</b>
	 * @param sqltablename
	 * @param strsqlcolumnname
	 * @param strsqlcondition
	 * @param strExpectedvalue
	 * @param strExecEventFlag
	 * @author <b>Yogendra</b>
	 * @since <b>23-Nov-16</b>
	 */

	public static boolean SQLDBSelectFromGAFEnv(String sqltablename, String strsqlcolumnname,String strsqlcondition,String strEnvVariableColumn,int strExecEventFlag)throws Exception  {
		boolean SQLDBSelectFromGAFEnv= false;
		String query = null;
		String Table_name = null;
		String Column_name = null;
		String SQL_condition = null;
		//String Expected_value = null;		
		String Expected_value = null;
		String envVariable=null;
		String Actual_Value = null;
		try {
			if(strExecEventFlag==1){
				Table_name=RetrieveTestDataValue("SQLDBSelectFromGAFEnv",sqltablename,strExecEventFlag);
				Column_name=RetrieveTestDataValue("SQLDBSelectFromGAFEnv",strsqlcolumnname,strExecEventFlag);
				SQL_condition=RetrieveTestDataValue("SQLDBSelectFromGAFEnv",strsqlcondition,strExecEventFlag);
				envVariable=RetrieveTestDataValue("SQLDBSelectFromGAFEnv",strEnvVariableColumn,strExecEventFlag);
			}

			if(Table_name==null || Column_name==null || SQL_condition==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBSelectFromGAFEnv",  "Required details are not provided in test data sheet.", false);
				return false;
			}

			Expected_value=GAFValue.getProperty(envVariable);

			if(Expected_value==null){
				Report_Functions.ReportEventFailure(doc,  "SQLDBSelectFromGAFEnv",  "Dynamic Variable '"+envVariable+"' has no value.", false);
				return false;
			}

			//Query to Execute      
			query = "select "+Column_name+" from "+Table_name+" where "+SQL_condition+" order by 1 desc";

			rs_SQLServer = stmt.executeQuery(query);


		} catch (Exception e) {
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelectFromGAFEnv",  "Error occured while executing the SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelectFromGAFEnv Error : " + e);
			SQLDBSelectFromGAFEnv=false;
		}

		try{
			rs_SQLServer.next();
			Actual_Value = rs_SQLServer.getString(1).trim();

		} catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		} catch (Exception e) {           // If no record is present in the fired Query
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelectFromGAFEnv",  "No Record found for this query: "+query, true);
			log.info("SQLDBSelectFromGAFEnv Error : ");
			SQLDBSelectFromGAFEnv=false;
		}

		try{
			if(!rs_SQLServer.wasNull()){            // If some value is present in the fired Query
				if(Actual_Value.equalsIgnoreCase(Expected_value)){
					Report_Functions.ReportEventSuccess(doc, "1","SQLDBSelectFromGAFEnv", "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches the expected Dynamic value : '"+Expected_value+"'", 2);
					SQLDBSelectFromGAFEnv=true;
				}else if(!(Actual_Value.equalsIgnoreCase(Expected_value))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelectFromGAFEnv",  "The actual value : '"+Actual_Value+"' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected Dynamic value : '"+Expected_value+"'", true);
					SQLDBSelectFromGAFEnv=false;
				}
			}

			else if(rs_SQLServer.wasNull()){        // If "NULL" value is present in the fired Query
				if(Expected_value.equalsIgnoreCase("NULL")){
					Report_Functions.ReportEventSuccess(doc, "1", "SQLDBSelectFromGAFEnv", "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' matches with the expected Dynamic value :'"+Expected_value+"'", 2);
					SQLDBSelectFromGAFEnv=true;
				}else if(!(Expected_value.equalsIgnoreCase("NULL"))){
					Report_Functions.ReportEventFailure(doc,  "SQLDBSelectFromGAFEnv",  "The actual value 'NULL' in column : '"+Column_name+"' of table : '"+Table_name+"' with condition : '"+SQL_condition+"' does not match the expected Dynamic value : '"+Expected_value+"'", true);  	 
					SQLDBSelectFromGAFEnv=false;
				}
			}
		}catch(NullPointerException e){

			log.info("Null pointer occurred :"+e.getMessage());

		}catch (Exception e){
			Report_Functions.ReportEventFailure(doc,  "SQLDBSelectFromGAFEnv",  "Error occured while comparing the values in SQL query.Error description is : "+ e.getMessage() +".", true);
			log.info("SQLDBSelectFromGAFEnv Error : " + e);
			SQLDBSelectFromGAFEnv=false;
		}
		return SQLDBSelectFromGAFEnv;
	}

	
	/*----------------------------------------- YOGENDRA ***END***-----------------------------------------*/


}
