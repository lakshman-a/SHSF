package Libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Properties;
import java.io.FileFilter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Libraries.App_Components;
import Libraries.Report_Functions;
import Utility.Log;
import Utility.ReadExcel;

public class Driver_Script{
	public static Properties Param = null;
	public static Properties DBconfig = null;
	public static Properties Runtimevalue = null;
	public static Properties EnvironmentValue = null;
	public static Properties GAFValue=null;
	
	protected static WebDriver driver=null;
	protected static Document doc=null;
	public static String Filepath=null;
	public static int gblrecordsCounter;
	public static int gblAppComponentCounter;
	static protected Logger log = Logger.getLogger(Driver_Script.class.getName());
	static boolean threadBooleanStatus = false;
	static String threadCompletedStatus = "completed";

	public static void main(String[] args) throws Exception {

		FileInputStream fip=null;
		FileInputStream fipdb=null;
		FileInputStream fiprtv=null;
		FileInputStream fipEnv=null;
		FileInputStream GAFfipEnv=null;
		PropertyConfigurator.configure("src\\Property\\log4j.properties");

		//This object creation used to close the expected pop-up's while execution, using multi-threading concept
		Function_Library threadRun = new Function_Library();
		Thread initializeThread = new Thread(threadRun);
		initializeThread.start();
		
		try{
			Log.startTestSuite();
			log.info("Driver_Script main() Method");

			//Initialize Environment.properties file for Storing all the Driver Script Related Environment variables.
			EnvironmentValue = new Properties();
			fipEnv = new FileInputStream(System.getProperty("user.dir")+"//src//Property//Environment.properties");
			EnvironmentValue.load(fipEnv);

			//Initialize Param.properties file for storing all the General Properties for testRun.
			Param = new Properties();
			fip = new FileInputStream(System.getProperty("user.dir")+"//src//Property//Param.properties");
			Param.load(fip);

			//Initialize Runtime.properties file for Storing all the Temprory Runtime variable.
			Runtimevalue = new Properties();
			fiprtv = new FileInputStream(System.getProperty("user.dir")+"//src//Property//Runtime.properties");
			Runtimevalue.load(fiprtv);
			log.info("All Properties file are loaded successfully.");	
			
			//Initialize Runtime.properties file for Storing all the Temprory Runtime variable.
			GAFValue = new Properties();
			GAFfipEnv = new FileInputStream(System.getProperty("user.dir")+"//src//Property//GlobalAddressFinderValues.properties");
			GAFValue.load(GAFfipEnv);
			log.info("All Properties file are loaded successfully.");	

			//Get the property value to set for Scroll bar status
			Runtimevalue.setProperty("checkScrollBarPresent", Param.getProperty("scrollBarNotPresent"));

			//Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");

			//Create an Instance for Driver Script
			Driver_Script testsuite = new Driver_Script();

			//Start the Driver Script.
			testsuite.start();

			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");

			//End the Driver Script - deallocate all memory.
			testsuite.end();

		}catch(Exception e){
			log.info("Exception in Main method. Exp is : " + e);
		}finally{
			if(fip!=null){
				fip.close();}
			fip=null;
			if(fipdb!=null){
				fipdb.close();}
			fipdb=null;
			if(fiprtv!=null){
				fiprtv.close();}
			fiprtv=null;
			if(fipEnv!=null){
				fipEnv.close();}
			fipEnv=null;
		}


		log.info("***********Testcase execution completed***********");
		if(threadCompletedStatus.equalsIgnoreCase("completed")){
			threadBooleanStatus = true;

		}


		//regressionExcelReportGenerator();

	}

	public void start() throws Exception{

		String Test_Batch_Excel=null;
		String Test_Data_Excel=null;
		App_Components App_Comp=null;
		int noofTCs;
		String testCaseExecFlag=null;
		String TESTCASE_NAME=null;
		String testCaseDesc=null;
		int noOfAppComponents;
		String App_Component_Name=null;
		String appComponentDesc=null;
		String appComponentExecFlag=null;
		String strAppComponentName=null;
		int noOfRecords;
		String testDataExecFlag=null;
		String testDataObjective=null;
		Method callAppComponent=null;
		File testBatchFolder=null;
		File[] testBatchFiles=null;
		boolean testCaseSheetExist=false;

		try{
			log.info("Start() of Driver Script starts.");

			//Create Instance for the App_Components class to invoke the App_Components in it
			App_Comp = new App_Components();

			//Retrieve the folder path of the Test Batch Spreadsheets
			testBatchFolder = new File(System.getProperty("user.dir")+Param.getProperty("testBatchFilePath"));

			//Retrieve all the TestBatch Spreadsheets from the TestBatch folder
			String regexpattern = "^Test_Batch_(\\d+)\\.xls";
			FileFilter filter = new RegexFileFilter(regexpattern);
			testBatchFiles = testBatchFolder.listFiles(filter);
			log.info("No. of TestBatch files : "+testBatchFiles.length);

			//Function ArraySort to Sort the TestBatch file names in Numerical Order.
			Arrays.sort(testBatchFiles, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					int n1 = extractNumber(o1.getName());
					int n2 = extractNumber(o2.getName());
					return n1 - n2;
				}
				private int extractNumber(String name) {
					int i = 0;
					try {
						int s = name.indexOf('_')+7;
						int e = name.lastIndexOf('.');
						String number = name.substring(s, e);
						i = Integer.parseInt(number);
					} catch(Exception e) {
						i = 0; 
					}
					return i;
				}
			}	);

			//Looping through all the Batch sheets in the TestBatch folder
			for (int testBatchPointer = 0; testBatchPointer < testBatchFiles.length; testBatchPointer++) {   

				log.info("Executing the TestBatch Sheet : "+testBatchFiles[testBatchPointer].getName());
				EnvironmentValue.setProperty("CURRENT_TESTBATCH_FILE", testBatchFiles[testBatchPointer].getName());

				//Initialize doc to create XML nodes for the Current TestBatch Cases for Reporting.
				doc = Report_Functions.generateResultReport(Param.getProperty("testSuiteName"),System.getProperty("os.name"));

				//Get the path of Current Test Batch sheet
				Test_Batch_Excel = System.getProperty("user.dir")+Param.getProperty("testBatchFilePath")+testBatchFiles[testBatchPointer].getName();

				//Retrieving the No of Test Cases from the Batch Sheet
				noofTCs = ReadExcel.GetRowCount(Test_Batch_Excel, "Test_Case_List");
				log.info("No of TestCase in the TestBatch sheet is : " +(noofTCs-1));

				//Looping through all the TestCases of Batch sheet
				for(int testCasePointer=1;testCasePointer<noofTCs;testCasePointer++){

					//Retrieving the Execution Flag of the test case
					testCaseExecFlag = ReadExcel.RetrieveExecutionFlagOfTestCase(Test_Batch_Excel, "Test_Case_List","Test_Case_Execute", testCasePointer);
					log.info("");
					log.info("TestCaseExecFlag of TestCase No. "+testCasePointer+"  is : "+ testCaseExecFlag);

					//If Test Case Execution Flag is Yes, the test case will be executed else will be skipped
					if(testCaseExecFlag.equalsIgnoreCase("Yes")){

						//Retrieve the Test Case Name 
						TESTCASE_NAME=ReadExcel.RetrieveTestCaseNameFromBatch(Test_Batch_Excel, "Test_Case_List", "Test_Case_Name", testCasePointer);
						EnvironmentValue.setProperty("TESTCASE_NAME", TESTCASE_NAME);

						//Retrieve the Test Case Description
						testCaseDesc=ReadExcel.RetrieveTestCaseDescFromBatch(Test_Batch_Excel, "Test_Case_List", "Test_Case_Description", testCasePointer);
						Log.startTestCase(testCasePointer,TESTCASE_NAME,testCaseDesc);

						//Add the Test Case node to test result
						Report_Functions.CreateTestCaseElement(doc, TESTCASE_NAME+": "+testCaseDesc);

						//Get the Test Data Excel Path
						Test_Data_Excel = (System.getProperty("user.dir")+Param.getProperty("testDataFilePath")+EnvironmentValue.getProperty("TESTCASE_NAME")+".xls");
						//Assign Test_Data_Excel to Static variable to use it in other Class
						Filepath=Test_Data_Excel;

						//Check whether sheet in TestData Spreadsheet exist with the Testcase Name
						testCaseSheetExist=ReadExcel.CheckTestCaseNamedSheetExist(Filepath,TESTCASE_NAME);

						//If Sheet exist with TestCase name in TestData SpreadSheet
						if(testCaseSheetExist){

							//Retrieve the Number of Application Components in the test case	
							noOfAppComponents= ReadExcel.GetRowCount(Test_Data_Excel, EnvironmentValue.getProperty("TESTCASE_NAME"));
							log.info("No of AppComponents in TestCase is : "+(noOfAppComponents -1));
							if (noOfAppComponents==0){
								Report_Functions.ReportEventFailure(doc, "TESTCASE_NAME", "Test Data/sheet Missing", false);

							}
							//Executing the Application Components in the test case by AppCompnents Looping
							for(int intAppComponentCounter=1;intAppComponentCounter<noOfAppComponents;intAppComponentCounter++){

								//Assign intAppComponentCounter to Static variable to use it in other classes
								gblAppComponentCounter=intAppComponentCounter;

								//Retrieve the Application Component Name 
								App_Component_Name=ReadExcel.RetrieveAppCompName(Test_Data_Excel, EnvironmentValue.getProperty("TESTCASE_NAME"), "Execution_Order", intAppComponentCounter);
								EnvironmentValue.setProperty("App_Component_Name", App_Component_Name);

								//Retrieve the Application Component Description
								appComponentDesc=ReadExcel.RetrieveTestCaseDescFromBatch(Test_Data_Excel, EnvironmentValue.getProperty("TESTCASE_NAME"), "App_Component_Description", intAppComponentCounter);

								//Retrieve the Execution Flag of the Application Component		
								appComponentExecFlag=ReadExcel.RetrieveAppCompExecFlag(Test_Data_Excel, EnvironmentValue.getProperty("TESTCASE_NAME"), "App_Component_Execute", intAppComponentCounter);
								log.info("AppComponent Exec Flag of ["+App_Component_Name+"] is : "+ appComponentExecFlag);

								//Execute the Application Component (Skip if Execute status of App Component is No)
								if(!(appComponentExecFlag.equalsIgnoreCase("No"))){

									Log.startComponent(intAppComponentCounter,App_Component_Name, appComponentDesc);

									//Add the App Component node in the test result file	
									Report_Functions.CreateAppComponentElement(doc,EnvironmentValue.getProperty("App_Component_Name")+": "+ appComponentDesc);

									//' For naming the error screenshots
									EnvironmentValue.setProperty("Glbl_Error_ScreenShotName", App_Component_Name) ;

									//Initializing the flag App_Component_Status_Flag
									EnvironmentValue.setProperty("App_Component_Status_Flag", "1") ;

									//Retrieve the Application Component Name 
									strAppComponentName = EnvironmentValue.getProperty("App_Component_Name");

									//Retrieving the total records in the App Component			
									int noOfRecordsinAC=ReadExcel.GetRowCount(Test_Data_Excel, strAppComponentName);
									log.info("No of Records in AppComponent is : "+(noOfRecordsinAC -1));

									//Reduce the count by -1 to leave the header in Excel uncounted in Report
									noOfRecords=noOfRecordsinAC-1;

									if(noOfRecordsinAC >2){
										Report_Functions.ReportEventSuccess(doc, "4", "componentCheck", "Total Number of Records :  "+noOfRecords, 2);
									}

									//Loop to handle Multiple Test Data in the App Component
									for(int recordsCounter=1;recordsCounter<noOfRecordsinAC;recordsCounter++){

										//Assign recordsCounter to Static variable to use it other classes.
										gblrecordsCounter=recordsCounter;

										//Retrieve the Exec Flag of the Test Data				
										testDataExecFlag=ReadExcel.RetrieveTestDataExecFlag(Test_Data_Excel, strAppComponentName, "TestData_Execute", recordsCounter);
										testDataObjective=ReadExcel.RetrieveTestDataFromSheet(Test_Data_Excel, strAppComponentName, "Objective", recordsCounter);
										log.info("TestData Execution Flag of  ["+strAppComponentName+"]'s Dataset line "+recordsCounter+" is : "+ testDataExecFlag);

										log.info("Test data objective of  ["+strAppComponentName+"]'s Dataset line : "+recordsCounter+" is : "+ testDataObjective);
										//Execute the test data, Skip it if NO	
										if(testDataExecFlag.equalsIgnoreCase("Yes")){
											Report_Functions.ReportEventSuccess(doc, "4", "Test Data Objective", "Test Data Objective : "+testDataObjective, 2);

											if(noOfRecordsinAC > 2){
												Report_Functions.ReportEventSuccess(doc, "4", "Processing Record", "Processing Record# : "+(recordsCounter), 2);

											}//If condition for noOfRecordsinAC

											log.info("Calling the the App_Component : "+ strAppComponentName+"() from App_Component Class");

											//Using Reflection API, Call the corresponding App_Component function is App_Component Class.
											try{

												callAppComponent = App_Comp.getClass().getMethod(strAppComponentName);
												callAppComponent.invoke(App_Comp);

											}catch(NoSuchMethodException e){

												Report_Functions.ReportEventFailure(doc, "App_componenet", "App component is not present-"+e.getMessage(), false);

											}catch(Exception e){

												Report_Functions.ReportEventFailure(doc, "App_componenet", "Exception occured in the App component : "+e.getMessage(), false);
											}

										}//testDataExecFlag to Yes in DataSet of Appcomponent check condition ends

									}//For loop for record counter when multiple dataset exist ends

									Log.endComponent(strAppComponentName);
								}//appComponentExecFlag to Yes check ends

							}//AppComp Pointer of a TestCase ends

							//Completed Execution of the Test Case updating the Test Results
							Report_Functions.ReportEventSuccess(doc, "4", "End of Test Case", "End of Test Case :  "+EnvironmentValue.getProperty("TESTCASE_NAME"), 2);

							Log.endTestCase(EnvironmentValue.getProperty("TESTCASE_NAME"));

						}
						//If Sheet does not exist with TestCase name in TestData SpreadSheet
						else{
							log.info("Name Mismatch. TestData Excel - "+EnvironmentValue.getProperty("TESTCASE_NAME")+" does not contain sheet named : "+EnvironmentValue.getProperty("TESTCASE_NAME"));

							//Creating AppComp with testCase Name to Report the Name Mismatch.
							Report_Functions.CreateAppComponentElement(doc,EnvironmentValue.getProperty("TESTCASE_NAME"));

							//Creating failure event to Report the Name Mismatch.
							Report_Functions.ReportEventFailure(doc, "Read Sheet", "TestData Excel '"+EnvironmentValue.getProperty("TESTCASE_NAME")+"'.xls does not contain the sheet named : "+EnvironmentValue.getProperty("TESTCASE_NAME")+". Name Mismatch.", false);
						}

					} //testCaseExecFlag to Yes condition check ends
					else if(testCaseExecFlag.equalsIgnoreCase("No")){
						//log.info("TestCase No. "+testCasePointer+"  is not Executed");
					}else {
						//if either Yes Nor No is provided in Test_Case_Execute, exit the for loop 	
						break;
					}

				}//TestCase Pointer ends

				//Saving the Report XML file
				//Report_Functions.SaveTestReport(doc,Param.getProperty("ReportfileName")+"_"+testBatchFiles[testBatchPointer].getName().replaceFirst("[.][^.]+$", ""));
				Report_Functions.SaveTestReport(doc);
				//For opening the xml result file after executing each test batch
				Thread.sleep(5000);
				//Report_Functions.OpenResultFile();
				//Reseting the doc to Create a New Report File for the Next TestBatch Execution 
				doc=null;

			}//TestBatch file counter
		}catch(Exception e){
			log.info("Exception is start() of Main Method. Exp is : "+ e);
		}finally{
			Test_Batch_Excel=null;
			Test_Data_Excel=null;
			App_Comp=null;
			testCaseExecFlag=null;
			TESTCASE_NAME=null;
			testCaseDesc=null;
			App_Component_Name=null;
			appComponentDesc=null;
			appComponentExecFlag=null;
			strAppComponentName=null;
			testDataExecFlag=null;
			callAppComponent=null;
			testBatchFolder=null;
			if(testBatchFiles!=null){
				for(int i=0;i<testBatchFiles.length;i++){
					testBatchFiles[i]=null;
				}
				testBatchFiles=null;
			}
			Filepath=null;
			DBconfig = null;
			driver=null;
			doc=null;
		}
	}

	public void end() throws Exception{

		FileOutputStream fileOut=null;
		FileOutputStream fileOutEnv=null;
		try{
			log.info("end() of Driver Script starts.");

			//Clearing the RuntimeFile and Saving it
			fileOut = new FileOutputStream(System.getProperty("user.dir")+"//src//Property//Runtime.properties");
			Runtimevalue.clear();
			Runtimevalue.store(fileOut, "Checking the propfile");
			fileOut.close();

			//Clearing the Environment file and Saving it
			fileOutEnv = new FileOutputStream(System.getProperty("user.dir")+"//src//Property//Environment.properties");
			EnvironmentValue.clear();
			EnvironmentValue.store(fileOutEnv, "Checking the Env");
			fileOutEnv.close();

			//Saving the Report XML file
			//Report_Functions.SaveTestReport(doc,Param.getProperty("ReportfileName"));

			//Explicit call for Garbage Collection
			if(Param.getProperty("GC_RUN_EXPLICIT").equalsIgnoreCase("TRUE")){
				System.gc();
			}

		}catch(Exception e){
			log.info("Exception in end() of Main method. Exp is : "+ e);
		}finally{
			if(fileOut!=null){
				fileOut=null;
			}
			if(fileOutEnv!=null){
				fileOutEnv=null;
			}
			Param = null;
			Runtimevalue = null;
			EnvironmentValue = null;

			Log.endTestSuite();
		}
	}

	//To method is to generate the test results in excel

	@SuppressWarnings("rawtypes")
	public static void updateScriptDetailsTracker(File scriptDetailsFilePath,String scriptDetailsFileName,String sheetName,ArrayList arrayTestScriptNames,HashMap hashMapTestScriptStatus,HashMap hashMapTSFailReason) throws IOException{   

		try{    	
			String strExpTestScriptName,strActualTestScriptName,strTestScriptStatus,strFailReason,strScriptsColName,strCellValue,strFileName;
			int intStatusCol,intCellType,intAutoScriptCol=0,intReasonCol;

			//The Column in Script Details Tracker where AutomationScript names were provided
			strScriptsColName="AutomationScript"; 

			//Retrieving the Script Details Tracker
			File file = new File(scriptDetailsFilePath+"\\"+scriptDetailsFileName);    
			FileInputStream inputStream = new FileInputStream(file); 
			strFileName=scriptDetailsFileName;

			//Find the file extension by splitting file name in substring and getting only extension name 
			String fileExtensionName = strFileName.substring(strFileName.indexOf("."));

			Workbook excelWorkbook = null;

			//Checking whether the file is xlsx 
			if(fileExtensionName.equals(".xlsx")){
				excelWorkbook = new XSSFWorkbook(inputStream);
			}   

			//Checking whether the file is xls 
			else if(fileExtensionName.equals(".xls")){
				excelWorkbook = new HSSFWorkbook(inputStream); 
			}

			//Reading the worksheet inside the workbook by sheetname 
			Sheet excelSheet = excelWorkbook.getSheet("Script_Details");
			Row headerRow=excelSheet.getRow(0);
			//Finding number of rows in excel file
			int rowCount = excelSheet.getLastRowNum()+1;

			//Retrieving AutomationScripts Column Number
			int intTotalColumns = headerRow.getPhysicalNumberOfCells();
			for(int p=0;p<intTotalColumns;p++){
				intCellType=headerRow.getCell(p).getCellType();		
				if (intCellType==1){
					strCellValue=headerRow.getCell(p).getStringCellValue();
					if(strCellValue.equalsIgnoreCase(strScriptsColName)){
						intAutoScriptCol=p;
					}
				}
			}


			//Loop to update Test Script status and Failed Reason in ScriptDetails tracker    
			for(int i=0;i<arrayTestScriptNames.size();i++){
				strExpTestScriptName=(String)arrayTestScriptNames.get(i);
				//System.out.println("strExpTestScriptName " + strExpTestScriptName);
				strTestScriptStatus=(String) hashMapTestScriptStatus.get(arrayTestScriptNames.get(i));
				//System.out.println("strTestScriptStatus " + strTestScriptStatus);
				strFailReason=(String) hashMapTSFailReason.get(arrayTestScriptNames.get(i));
				//System.out.println("strFailReason " + strFailReason);

				//Looping through all the rows of excel file
				for (int j = 0; j < rowCount; j++) { 
					Row row = excelSheet.getRow(j);
					switch (row.getCell(intAutoScriptCol).getCellType()) 
					{	
					case 0: // 0 means numeric value in the cell           	
						//System.out.println("Value in the Numeric Cell " +row.getCell(intAutoScriptCol).getNumericCellValue());
						break;   
					case 1: // 1 means string value in the cell
						strActualTestScriptName=row.getCell(intAutoScriptCol).getStringCellValue();
						if(strExpTestScriptName.equalsIgnoreCase(strActualTestScriptName))
						{	        			
							intStatusCol=intAutoScriptCol+2;
							Cell cellTestCaseStatus=row.createCell(intStatusCol);
							cellTestCaseStatus = row.getCell(intStatusCol);          			 
							cellTestCaseStatus.setCellValue(strTestScriptStatus);

							intReasonCol=intAutoScriptCol+3;        			 
							Cell cellFailedReason=row.createCell(intReasonCol);
							intReasonCol=intAutoScriptCol+3;        			 
							cellFailedReason = row.getCell(intReasonCol); 
							cellFailedReason.setCellValue(strFailReason);	
						}        		
						break;            
					}
				}//Closing for loop j
			}//Closing for loop i

			inputStream.close(); 

			FileOutputStream outputStream = new FileOutputStream(file);
			//write data in the excel file        			 
			excelWorkbook.write(outputStream);
			//close output stream        			 
			outputStream.close(); 

		} catch (Exception e){
			e.printStackTrace();	
		}  

	}  

	//To method is to generate the test results in excel

	@SuppressWarnings({ "rawtypes", "unchecked", "static-access", "unused" })
	public static void regressionExcelReportGenerator(){

		try {			
			String strTestCaseNodeValue,testScriptStatus, strAppComponent, strEvent, strAppCompNodeValue, strEachTestScriptName[],strEventStatus,strEventFailureReason,scriptDetailsFileName,strsheetName,strResultFileName;		
			int intTestScriptStatus,intEventStatus,flag,intTotalFile;
			Properties config = null;

			FileInputStream fip = null;		
			File scriptDetailsFilePath = null, resultFolderArray[], xmlFile = null;

			// Creating ArrayList to store Test script Names		
			ArrayList<String> arrayTestScriptNames=new ArrayList<String>(); 

			// Creating ArrayList to store Result XML Files		
			ArrayList<String> arrayResultXMLFiles=new ArrayList<String>();

			//Creating HashMap to store the test script name and test script status
			HashMap hashMapTestScriptStatus= new HashMap();

			//Creating HashMap to store the test script name and failure reason
			HashMap hashMapTSFailReason= new HashMap();

			config= new Properties();
			fip = new FileInputStream(System.getProperty("user.dir")+"//src//Property//Param.properties");
			config.load(fip);

			//Script Details File Path
			scriptDetailsFilePath = new File(System.getProperty("user.dir")+config.getProperty("ScriptDetailsPath"));		

			scriptDetailsFileName=config.getProperty("ScriptDetailsFileName");		
			strsheetName="Script_Details";

			File xmlResultPath = new File(System.getProperty("user.dir")+config.getProperty("resutsFilePath"));		

			// Storing files in Results Folder
			resultFolderArray=xmlResultPath.listFiles();

			// No of folders/files in Results folder
			intTotalFile=xmlResultPath.listFiles().length;	


			//Storing all Results XML files in an array
			for(int n=0;n<intTotalFile;n++){
				strResultFileName=resultFolderArray[n].getAbsoluteFile().toString();			
				if(strResultFileName.contains(".xml")){
					arrayResultXMLFiles.add(strResultFileName);
				}
			}

			//Looping through all the results xml files to update Script Details Tracker
			for(int r=0;r<arrayResultXMLFiles.size();r++){
				xmlFile = new File(arrayResultXMLFiles.get(r));			

				DocumentBuilderFactory docBuildFac = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder=docBuildFac.newDocumentBuilder();		
				Document doc = dBuilder.parse(xmlFile);	
				doc.getDocumentElement().normalize();	

				//Retrieving Total No. of test cases
				NodeList nodeListTestcase = doc.getElementsByTagName("TestCase");        
				//System.out.println("Total No. of Test Scripts: " + nodeListTestcase.getLength());

				// Looping through all the test case nodes in results XML
				for (int temp = 0; temp < nodeListTestcase.getLength(); temp++) {
					flag=0;
					// Test Case Node
					Node nTestCaseNode = nodeListTestcase.item(temp);

					Element eElement = (Element) nTestCaseNode;           
					// Retrieving value in App Component Node
					strAppComponent=eElement.getElementsByTagName("AppComponent").item(0).getTextContent();

					// Retrieving value in Event Node
					strEvent=eElement.getElementsByTagName("Event").item(0).getTextContent();           

					// Retrieving the value in the Test Case Node
					strTestCaseNodeValue=eElement.getTextContent();

					// Retrieving the Test script status to check whether the test script is Pass or Fail 
					testScriptStatus=eElement.getAttribute("Status");
					intTestScriptStatus=Integer.parseInt(testScriptStatus);
					if (intTestScriptStatus==1){            	  
						testScriptStatus="Passed";
					}
					else if (intTestScriptStatus==3){
						testScriptStatus="Failed";
					}

					// Splitting the value in Test Case node using : to retrieve the test script name 
					strEachTestScriptName=strTestCaseNodeValue.split(":");
					System.out.println("Test Script is " + strEachTestScriptName[0]);

					// Storing the test case names in array list
					arrayTestScriptNames.add(strEachTestScriptName[0]);

					// Storing the Test Script Name and Test Script Status in HashMap           
					hashMapTestScriptStatus.put(arrayTestScriptNames.get(temp),testScriptStatus);

					// Moving towards AppComponents Level          
					if (nTestCaseNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElementTestCase = (Element) nTestCaseNode;
						// Retrieving the App Components related to the current test script
						NodeList appComponentList = eElementTestCase.getElementsByTagName("AppComponent");              
						for (int count = 0;count < appComponentList.getLength(); count++) {           	   	 
							Node nAppCompNode = appComponentList.item(count);

							if(nAppCompNode.getNodeType() == nAppCompNode.ELEMENT_NODE) {
								Element appComponentElement = (Element) nAppCompNode;                                        
								//System.out.println(appComponentElement.getTextContent());

								// Retrieving the App Component Name by Splitting the value in App Component using : 
								strAppCompNodeValue=appComponentElement.getTextContent();                    

								// Moving towards Event Level
								// Retrieving the Events related to the current App Component
								NodeList nodeListEvent = appComponentElement.getElementsByTagName("Event");
								// System.out.println("Total No. of Events: " + nodeListEvent.getLength());

								for (int counter = 0;counter < nodeListEvent.getLength(); counter++) {                  	   	 
									Node nEventNode = nodeListEvent.item(counter);
									if (nEventNode.getNodeType() ==nEventNode.ELEMENT_NODE) {
										Element eventElement = (Element) nEventNode;                                                
										if(eventElement.hasAttribute("Status")){

											// Retrieving the Event Status, if failed getting the Failed Reason
											strEventStatus=eventElement.getAttribute("Status");                        	
											intEventStatus=Integer.parseInt(strEventStatus);                        
											if(intEventStatus==3){
												strEventFailureReason=eventElement.getTextContent();                            	
												// Storing the Test Script Name and Failure Reason in HashMap           
												hashMapTSFailReason.put(arrayTestScriptNames.get(temp),strEventFailureReason);
												//System.out.println("\n Script is failed -- Event Failure Reason is " + strEventFailureReason);
												flag=1;
												break;
											}
										}                       
									}                    
								}//Looping Event Nodes
							}
							if(flag==1){break;} // As soon as an event in failed status is encountered, the script will come out of the loop

						}// Looping AppComp Nodes 
					} 
				}// Looping TestCase Nodes 	

				// Method to update the Script Details Tracker
				updateScriptDetailsTracker(scriptDetailsFilePath,scriptDetailsFileName,strsheetName,arrayTestScriptNames,hashMapTestScriptStatus,hashMapTSFailReason);
				arrayTestScriptNames.clear();  // Clearing the ArrayList
			}// Looping all result xml files        

		} catch (Exception e) {			
			e.printStackTrace();
		}


	}

}