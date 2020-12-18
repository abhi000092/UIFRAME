package utilities;

import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;      
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dbunit.Assertion;
import org.dbunit.dataset.excel.XlsDataSet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



import base.DriverManager;
import base.Logger;
import base.Testbase;
import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;


public class CommonFunctions extends Testbase {
	public static final int minWait = 5;
	public static final int midWait = 20;
	public static final int maxWait = 30;
	private static SecureRandom random = new SecureRandom();
	public static Properties prop;
	public static ResultSet rs;
	public static Properties Query = new Properties();

	public static boolean isElementPresent(WebDriver driver, By by) {
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			driver.findElement(by);
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			return true;
		} catch (NoSuchElementException e) {
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			return false;
		}
	}

	@Step("Run Query and Return column value")
	public static String RunQuery(String filepath, String key, String... Params)
			throws ClassNotFoundException, SQLException, IOException {
		fis = new FileInputStream(System.getProperty("user.dir") + filepath);
		Query.load(fis);
		String Query1 = Query.getProperty(key);
		String value = null;
		for (String i : Params) {
			for (int j = 1; j <= Params.length; j++) {
				// System.out.print(i + " ");
				// iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId));
				if (Query1.contains("##" + String.valueOf(j) + "##")) {
					Query1 = Query1.replace("##" + String.valueOf(j) + "##", i);
					break;
				}

			}
		}
		rs = iqdb.RunQuery(Query1);
		try {
			while (rs.next()) {
				value = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("failed");
		}

		return value;
	}
	
	@Step("Run Query and Return column value")
	public static String RunQueryandReturnColumValue(String filepath, String key,String colName, String... Params)
			throws ClassNotFoundException, SQLException, IOException {
		fis = new FileInputStream(System.getProperty("user.dir") + filepath);
		Query.load(fis);
		String Query1 =Query.getProperty(key);
		String value = null;
		//for (String i : Params) {
			for (int j = 0; j < Params.length; j++) {
				//System.out.print(i + " ");
				//iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId));
			  if (Query1.contains("##" + String.valueOf(j+1) + "##")) {
					Query1 = Query1.replace("##" + String.valueOf(j+1) + "##", Params[j]);
					
				}}
		rs = iqdb.RunQuery(Query1);	
		try {
			while (rs.next()) {		
				value = rs.getString(colName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed");
		}
		System.out.println("to remove");
		return value;
	}
	
	@Step("Run Query and Return Column as List")
	public static List<String> RunQueryandReturnColumnasList(String Path, String key, String... Params)
			throws ClassNotFoundException, SQLException, IOException {
		List<String> value1 = new ArrayList<String>();
		fis = new FileInputStream(System.getProperty("user.dir") + Path);
		Query.load(fis);
		String Query1 = Query.getProperty(key);
		String value = null;
		for (String i : Params) {
			for (int j = 1; j <= Params.length; j++) {
				if (Query1.contains("##" + String.valueOf(j) + "##")) {
					Query1 = Query1.replace("##" + String.valueOf(j) + "##", i);
					break;
				}

			}
		}
		rs = iqdb.RunQuery(Query1);
//		
		try {
			while (rs.next()) {
				value = rs.getString(1);
				value1.add(value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("failed");
		}
		return value1;
	}

	public static Properties initialize_properties(String file) {

		prop = new Properties();

		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\test\\resources\\properties\\Updated\\VerificationQueries\\" + file + ".properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public static String unique6LengthNumeric() {
		DateFormat dateFormat = new SimpleDateFormat("hhssmm");
		Date date = new Date();
		String sUniqueNumber = dateFormat.format(date);
		if (String.valueOf(sUniqueNumber.charAt(0)).equals("0")) {
			sUniqueNumber = sUniqueNumber.replaceFirst("0", String.valueOf(random.nextInt(8) + 1));
		}
		return sUniqueNumber;
	}

	public static String CurrentDateTime() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));

		/*
		 * getting current date time using calendar class An Alternative of above
		 */
		Calendar calobj = Calendar.getInstance();
		System.out.println(df.format(calobj.getTime()));
		String CDate = df.format(calobj.getTime());
		return CDate;
	}

	public static boolean Elementdisplayed_Enabled(WebElement Element) {
		if (Element.isDisplayed()) {
			if (Element.isEnabled()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static String GetValue(WebDriver driver, By locator) throws Exception {

		String sValue = "";
		if (isElementPresent(driver, locator)) {
			sValue = driver.findElement(locator).getAttribute("value").trim();
		} else {

			Assert.fail("Not able to get value.Locator is not present " + locator);
		}
		System.out.println("sValue is " + sValue);
		return sValue;
	}

	public static String getAttribute(WebDriver driver, By locator, String attribute) throws Exception {
		String sAttr = "";

		if (isElementPresent(driver, locator)) {
			sAttr = driver.findElement(locator).getAttribute(attribute);
		} else {
			System.err.println("Object doesn't exists");
		}

		return sAttr;
	}
	
	@Step("Closing tab")
	public void CloseTab(String Tab) throws InterruptedException {
		if(driver.findElements(By.xpath("//label[contains(text(),'"+Tab+"')]/parent::span/parent::li//span[@class='app__tab__close']")).size()>0) {
			WebElement ele1=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='"+Tab+"']/parent::span//following-sibling::Span")));
			wait.until(ExpectedConditions.elementToBeClickable(ele1));
			waitForPageLoaded();
			ClickElement(ele1);
			waitForPageLoaded();
		}else {
			System.out.println("Tab '"+Tab+"' doesn't exists");
		}
	}

	public static void Iquote_SelectCheckbox(WebDriver driver, String XpathForLocator, String CheckBoxState)
			throws Exception {
		String XpathForButtonstare = XpathForLocator + "/ancestor::button[@class='ckb ']";
		String CheckboxCurrentstate = driver.findElement(By.xpath(XpathForButtonstare)).getAttribute("aria-checked");
		if (CheckBoxState.equals("0")) {

			if (CheckboxCurrentstate.equals("true")) {
				driver.findElement(By.xpath(XpathForLocator)).click();
			}

		} else if (CheckBoxState.equals("1")) {
			if (CheckboxCurrentstate.equals("false")) {
				driver.findElement(By.xpath(XpathForLocator)).click();
			}

		}

	}

	public static void scrolltoWebElement(WebDriver driver, By locator) throws Exception {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}

	public static void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};

		WebDriverWait wait = new WebDriverWait(driver, 180);
		try {
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println("Timeout waiting for Page Load Request to complete.");
		}
	}

	public static boolean waitUntilElementisClickable(WebDriver driver, By locator, int TimeOut) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 180);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		} catch (TimeoutException e) {
			System.err.println("Element did not become clickable even after waiting for " + TimeOut + " seconds");
			return false;
		}
	}

	public static void SetOriginalWindowHandle(WebDriver driver) {
		String CommonOriginalHandle = null;
		String CommonOrignalwindowTitle = null;
		System.out.println("-----Setting WebDriver Control To original window -----");

		CommonOriginalHandle = driver.getWindowHandle();
		CommonOrignalwindowTitle = driver.getTitle();
	}

	public static void ClickElement(WebDriver driver, By locator) throws Exception {
		if (isElementPresent(driver, locator)) {
			if (!Elementdisplayed_Enabled(driver.findElement(locator))) {
				scrolltoWebElement(driver, locator);
			}

			try {
				driver.findElement(locator).click();
				Thread.sleep(2000);
			} catch (Exception e) {
				//(new Actions(driver)).moveToElement(driver.findElement(locator)).build().perform();
				((JavascriptExecutor)driver).executeScript("arguments[0].click()", driver.findElement(locator));
				Thread.sleep(1000);
			}
		}
	}
	public static void SendValue(WebDriver driver, By locator, String sValue) throws Exception {
		if (isElementPresent(driver, locator)) {
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys("." + Keys.CONTROL + "a" + Keys.DELETE);
			driver.findElement(locator).sendKeys(sValue);
			driver.findElement(locator).sendKeys(Keys.TAB);
		} else {
			Assert.fail("Element " + locator + " is not present");
		}
	}

	public static boolean waitUntilElementisPresent(WebDriver driver, By locator, int TimeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TimeOut);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (TimeoutException e) {
			System.err.println("Element did not appear even after waiting for " + TimeOut + " seconds");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static boolean waitForElement(WebElement ele, int timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
			driver.manage().timeouts().implicitlyWait(minWait, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(minWait, TimeUnit.SECONDS);
			return false;
		}

	}

	public static void Iquote_EnterDataintoTextfield(WebDriver driver, String XpathForLocator, String Fieldvalue)
			throws Exception {
		driver.findElement(By.xpath(XpathForLocator)).clear();
		driver.findElement(By.xpath(XpathForLocator)).click();
		driver.findElement(By.xpath(XpathForLocator)).sendKeys(Fieldvalue + Keys.TAB + Keys.TAB);

	}

	public static void SendValueWithoutClear(WebDriver driver, By locator, String sValue) throws Exception {
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(locator));
		if (isElementPresent(driver, locator)) {
			driver.findElement(locator).sendKeys(sValue + Keys.TAB);
		} else {
			Assert.fail("Element " + locator + " is not present");
		}
	}

	public static void Iquote_SelectFromDropdown(WebDriver driver, String XpathForLocator, String Fieldvalue)
			throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XpathForLocator)));
		driver.findElement(By.xpath(XpathForLocator)).click();
		Thread.sleep(2000);
		CommonFunctions.waitUntilElementisPresent(driver, By.xpath("//li/label[contains(text(),'" + Fieldvalue + "')]"),
				180);
		driver.findElement(By.xpath("//li/label[contains(text(),'" + Fieldvalue + "')]")).click();
		Thread.sleep(2000);

		String Selectedval = driver.findElement(By.xpath(XpathForLocator)).getText();
		if (Selectedval.contains(Fieldvalue)) {
			// System.out.println("Selected from dropdown : "+Selectedval);
		} else {
			System.out.println("Correct value is not selected from dropdown please investigate");
		}

	}

	public static void Iquote_SelectFromDropdown_Text(WebDriver driver, String XpathForLocator, String Fieldvalue)
			throws Exception {
		driver.findElement(By.xpath(XpathForLocator)).click();
		driver.findElement(By.xpath(XpathForLocator)).clear();
		driver.findElement(By.xpath(XpathForLocator)).click();
		driver.findElement(By.xpath(XpathForLocator)).sendKeys(Fieldvalue);
		Thread.sleep(2000);
		driver.findElement(By.xpath(XpathForLocator)).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath(XpathForLocator)).sendKeys(Keys.ENTER);

		String Selectedval = driver.findElement(By.xpath(XpathForLocator)).getAttribute("value");
		if (Selectedval.equals(Fieldvalue)) {
			// System.out.println("Selected from dropdown : "+Selectedval);
		} else {
			System.err.println("Correct value is not selected from dropdown please investigate");
		}

	}

	public static void WaitFor_ElementVisiblity(WebDriver driver, By Loc) {
		WebDriverWait wait = new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Loc));
	}
	

	public static boolean waitUntilElementisVisible(WebDriver driver, By locator, int TimeOut) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 180);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (TimeoutException e) {
			System.err.println("Element did not appear even after waiting for " + TimeOut + " seconds");
			return false;
		}
	}

	public static void selectDropdownByIndex(WebDriver driver, By locator, int index) throws Exception {
		System.out.println("Selecting dropdown with locator " + locator + " by index " + index);
		index += 1;
		if (isElementPresent(driver, locator)) {
			if (index != 0) {
				// select the multiple values from a dropdown
				Select selectByValue = new Select(driver.findElement(locator));
				selectByValue.selectByIndex(index);
			}
			Thread.sleep(1000);
		}
	}

	public static String futureDateinMMddyyyyFormat(int NumberOfdaysToAdd) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, NumberOfdaysToAdd);
		String sfutureDate = sdf.format(c.getTime());
		return sfutureDate;
	}

	public static int randInt(int min, int max) {

		// Usually this can be a field rather than a method variable
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;

	}

	public static void clickEnterKey() {
		try {
			robot = new Robot();

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static WebElement getShadowElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
				element);
		return ele;
	}



	public void ScrollToElement(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);

	}

	public enum SearchAtrribute {
		EstimateNumber, EstimateTitle, Customer, SalesPerson, Agency, ProductSatisfaction, Status;
	}

	 public void waitForPageLoaded() {
	        ExpectedCondition<Boolean> expectation = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	                    }
	                };
	        try {
	            Thread.sleep(1000);
	            WebDriverWait wait = new WebDriverWait(driver, 30);
	            wait.until(expectation);
	        } catch (Throwable error) {
	            Assert.fail("Timeout waiting for Page Load Request to complete.");
	        }
	    }
	 
	 
	 public static String RunQuery2(String filepath, String key, String Param1)
             throws ClassNotFoundException, SQLException, IOException {
             
       fis = new FileInputStream(System.getProperty("user.dir") + filepath);
       Query.load(fis);
       
      

//     /           String Query1=Query.getProperty(key);
       String Query1 = Query.getProperty("Query")
                   .replace("##Estimate##", Param1);
//                   .replace("##Description##", Param2);
       String value = "";
       
       rs = iqdb.RunQuery(Query1);
       try {
             while (rs.next()) {
                   value = rs.getString(key).trim();
                   //                      value1.add(value);
             }
       } catch (SQLException e) {
             e.printStackTrace();
             System.out.println("failed");
       }

       return value;
 }
	 
	 
	 

	 public static String RunQuery1(String filepath, String key, String Param1, String Param2)
             throws ClassNotFoundException, SQLException, IOException {
             
       fis = new FileInputStream(System.getProperty("user.dir") + filepath);
       Query.load(fis);

//     /           String Query1=Query.getProperty(key);
       String Query1 = Query.getProperty("Query")
                   .replace("##Estimate##", Param1)
                   .replace("##Description##", Param2);
       String value = "";
       
       rs = iqdb.RunQuery(Query1);
       try {
             while (rs.next()) {
                   value = rs.getString(key).trim();
                   //                      value1.add(value);
             }
       } catch (SQLException e) {
             e.printStackTrace();
             System.out.println("failed");
       }

       return value;
 }
	 
	 public static  List<String> RunQueryandReturnColumnasList2(String Path,String Param1,String ...key) throws ClassNotFoundException, SQLException, IOException{
		 List<String> value1 = new ArrayList<String>();
         fis = new FileInputStream(
                     System.getProperty("user.dir") + Path);
         Query.load(fis);

         String Query1 = Query.getProperty("Query1")
                     .replace("##Estimate##", Param1);
                  
         
         
         String value=null;
//       for (String i: Params) {
//             for(int j=1;j<=Params.length;j++) {
//                   
                     
                     for (String i: key) {
                           for(int j=1;j<=key.length;j++) {
                     //System.out.print(i + " "); 
                     // iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId));

                     if(Query1.contains("##"+String.valueOf(j)+"##")) {
                           Query1= Query1.replace("##"+String.valueOf(j)+"##", i);
                           break;
                     }

               }
         }
         rs = iqdb.RunQuery(Query1);
         try
         {                 
               while(rs.next())
               {
                     value=rs.getString(1);
                     value1.add(value);
               }
         }catch(SQLException e)
         {
               e.printStackTrace();
               System.out.println("failed");
         }


         return value1;    
   }


  		
	 
	 
	 public static  List<String> RunQueryandReturnColumnasList1(String Path,String Param1,String Param2,String ...key) throws ClassNotFoundException, SQLException, IOException{
		       
		 List<String> value1 = new ArrayList<String>();
         fis = new FileInputStream(
                     System.getProperty("user.dir") + Path);
         Query.load(fis);
//       String Query1=Query.getProperty(key)
         String Query1 = Query.getProperty("Query")
                     .replace("##Estimate##", Param1)
                     .replace("##Description##", Param2);
         
         
         String value=null;
         
                     for (String i: key) {
                           for(int j=1;j<=key.length;j++) {
                    
                     if(Query1.contains("##"+String.valueOf(j)+"##")) {
                           Query1= Query1.replace("##"+String.valueOf(j)+"##", i);
                           break;
                     }

               }
         }
         rs = iqdb.RunQuery(Query1);
         try
         {                 
               while(rs.next())
               {
                     value=rs.getString(1);
                     value1.add(value);
               }
         }catch(SQLException e)
         {
               e.printStackTrace();
               System.out.println("failed");
         }


         return value1;    
   }


  
  
 public static Boolean isElementScrollable()
 {
	 String execScript = "return document.documentElement.scrollWidth>document.documentElement.clientWidth;";
	 JavascriptExecutor scrollBarPresent = (JavascriptExecutor) DriverManager.getDriver();
	  return (Boolean) (scrollBarPresent.executeScript(execScript));
	  
 }
 
 public static void listComparator(List<String> Original ,List<String> Expected)
 {
     ///////Note: Both the lists should have same sequence 
	 int expCnt = Expected.size();
	 
	 for (int i=0;i<expCnt;i++) {
		 if(Expected.get(i).equals(Original.get(i)))
		 {
			 System.out.println(Original.get(i)+" has been updated succesfully");
		 }
		 else
		 {
			 System.err.println(Original.get(i)+" has NOT been updated succesfully");
		 }
	 }
	 
 }
 
 
 @Step("Run Query and Return Multiple Column as Hashmap")
	public Map<String,String> RunQueryandReturnMultipleColumnasHashMap(String Path, String key, int numberOfColumn,String... Params)
			throws ClassNotFoundException, SQLException, IOException {
		Map<String, String> ColumnName = new HashMap<String, String>();
		fis = new FileInputStream(System.getProperty("user.dir") + Path);
		Query.load(fis);
		String Query1 = Query.getProperty(key);

		for (String i : Params) {
			for (int j = 1; j <= Params.length; j++) {
				// System.out.print(i + " ");
				// iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId));
				if (Query1.contains("##" + String.valueOf(j) + "##")) {
					Query1 = Query1.replace("##" + String.valueOf(j) + "##", i);
					break;
				}

			}
		}

		rs = iqdb.RunQuery(Query1);
		try {
			while (rs.next()) {

				for (int i = 1; i <= numberOfColumn; i++) {

					ColumnName.put(rs.getMetaData().getColumnName(i), rs.getString(i));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("failed");
		}

		return ColumnName;
	}
 
 	public String ReturnCurrentdatetime() {
 		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
 		   LocalDateTime now = LocalDateTime.now();
 		   return dtf.format(now);
 	}
 	
 	public boolean CheckBox(By Locator,String status) {
 		boolean flag=false;
 		if(status.equals("true") && DriverManager.getDriver().findElement(Locator).getAttribute("aria-checked").equals("false")) {
 			DriverManager.getDriver().findElement(Locator).click();
 			flag=true;
 		}
 		else if(status.equals("false") && DriverManager.getDriver().findElement(Locator).getAttribute("aria-checked").equals("true")) {
 			DriverManager.getDriver().findElement(Locator).click();
 			flag=true;
 		}
 		return flag;
 	}
  
 
 	public void ExecutionSummary(int TotalLines,int Pass,int Fail) {
 		Allure.step("****************************** Summary of Test Run | Date : "+ReturnCurrentdatetime()+"******************************");
 		Allure.step("Total Test Lines : "+TotalLines);
 		Allure.step("Test Lines Passed are  : "+Pass);
 		Allure.step("Test Lines Failed are  : "+Fail);
 		Allure.step("Test Lines UnExecuted are  : "+(TotalLines-(Pass+Fail)));
 		Allure.step("*********************************************************************************************************");
 	}
 	
 	public int returnCount(By Locator) {
 		if(DriverManager.getDriver().findElements(Locator).size()>0)
 			wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
		return DriverManager.getDriver().findElements(Locator).size();
	}

	/*public int ReturnRowCount(By Locator) {
		return DriverManager.getDriver().findElements(Locator).size();
	}
*/
	public int returnColumnNumber(By Locator) {
		if(DriverManager.getDriver().findElements(Locator).size()>0)
 			wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
		return Integer.parseInt(DriverManager.getDriver()
				.findElement(Locator)
				.getAttribute("data-column"));

	}
	
	public int returnRowNumber(By Locator) {
		if(DriverManager.getDriver().findElements(Locator).size()>0)
 			wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
		return Integer.parseInt(DriverManager.getDriver()
				.findElement(Locator)
				.getAttribute("data-row"));

	}

	
	public boolean isAttribtuePresent(WebElement element, String attribute) {
	    Boolean result = false;
	    try {
	        String value = element.getAttribute(attribute);
	        if (value != null){
	            result = true;
	        }
	    } catch (Exception e) {}

	    return result;
	}

	public String readFromExcel(String path,String col) throws IOException {
		
		System.out.println("--->|readFromExcel : field = " + col +", path="+path);
		String val;
		Cell cell=null;
		 FileInputStream fis = new FileInputStream(path);
		 Workbook wb=new XSSFWorkbook(fis);  	
		 Sheet sheet=wb.getSheetAt(0);
		 
		 for(int i=1;i<=sheet.getLastRowNum();i++) {
			 if(sheet.getRow(i).getCell(0).getStringCellValue().equals(col)) {				
				 cell=sheet.getRow(i).getCell(1);
				 break;
			 }
		 }
		 
		 if(cell==null)
			 val = "";
		 else
			 val =cell.toString();			 
		 
		 System.out.println("<---|readFromExcel  = " + val );
		 return val;
	}
	
	public void writeToExcel(String path,String field,String val) throws InvalidFormatException, IOException, InterruptedException {
		
		System.out.println("--->|writeToExcel : field = " + field + ", val = "+ val + ", path="+path);
		
		boolean isExists = false;
		int i=1;
		
		 FileInputStream fis = new FileInputStream(path);
		 FileOutputStream fos;
		 Workbook wb=WorkbookFactory.create(fis);
		 Sheet sheet=wb.getSheetAt(0);
	
		 for(i=1;i<=sheet.getLastRowNum();i++) {
			 if(sheet.getRow(i).getCell(0).getStringCellValue().equals(field)) {
				 isExists = true;
				 sheet.getRow(i).getCell(1).setCellValue(val);
				 break;				
			 }
		 }
		 
		 //if given field doesnt exists, add new field
		 if (!isExists) {
			 Row row = sheet.createRow(i); 
			 row.createCell(0).setCellValue(field);
			 row.createCell(1).setCellValue(val);
		 }
		 
		 fos= new FileOutputStream(path);
		 Thread.sleep(5000);
		 wb.write(fos);
		 fos.flush();
		 fos.close();
	}
	
	@Owner(value="Velmurugan Cithaiyan")
	public static String getJobSyncStatus(String iPlanJobID) throws Exception {
	       
	       String Status = "";       
	       fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Job\\JobRelated.properties");
			Query.load(fis);
			String statusSqlQry=Query.getProperty("Jobstatus");
	       
			 	ResultSet rs = iqdb.RunQuery(statusSqlQry.replace("##IPLANJOBID##", iPlanJobID ));
	             try {
	            	 while(rs.next()) {
	            		  Status = rs.getString("IntegrationStatus");
	            	 }
	                                 
	             } catch (SQLException e) {
	                    e.printStackTrace();
	             }
	                    
	             return Status;
	    }

	@Owner(value="Velmurugan Cithaiyan")
	public String waitForJobAck(String iPlanJobID, Boolean isJobCanceled) throws Exception {
        
		System.out.println("--->|waitForJobAck : iPlan Job ID = " + iPlanJobID + ", isCanceled = " + isJobCanceled );
		
	       Boolean doContinue = true;
	       String Status = "";

	       LocalDateTime start = LocalDateTime.now();  
	       String startDateTime = (start.minusMinutes(1)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	    		               
	       do {
	             //wait for 30 sec
	            Thread.sleep(Integer.parseInt(Config.getProperty("intSyncAck.wait")));
	                          
	             Status = getJobSyncStatus(iPlanJobID);
	             if(!(Status==null)) {                      
		             if ((Status.equals("Successful")) || (Status.equals("Failed"))) {
		                    doContinue = false;
		                    AllureLogger.log.info("Status : " + Status);
		                    //if Sucessfull then wait for PrintFlow Job Sync
		                    if (Status.equals("Successful"))
		                    	waitForPrintFlowSync(iPlanJobID, isJobCanceled, startDateTime);
		             }else
		            	 AllureLogger.log.info("waitForJobAck : Status = '" + Status + "', Waiting for acknowledgement from MIS...");
	             }else
	            	 AllureLogger.log.info("waitForJobAck : Status = '" + Status + "', Waiting for acknowledgement from MIS...");
	             
	             //check the wait time
	             if (java.time.Duration.between(start, LocalDateTime.now()).toMinutes() 
	                          == Integer.parseInt(Config.getProperty("implicit.wait"))) { //if it is not processed for 5mins, then come out
	                    AllureLogger.markStepAsFailed("waitForJobAck : Didnt received the acknowledgement for Job '" + iPlanJobID + "' even after 5 mins.");
	                    System.out.println("<---|waitForJobAck = " + Status );
	                    return Status;
	             }
	             
	                          
	       } while (doContinue);
	             
	       System.out.println("<---|waitForJobAck = " + Status );	       
	       return Status; 
	}
	
	@Owner(value="Velmurugan Cithaiyan")
	public static String getCustomerSyncStatus(String iQCustID) throws Exception {
	       
	       String Status = "";       
	       fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Customer\\customer.properties");
			Query.load(fis);
			String statusSqlQry=Query.getProperty("getIQCustomer");
			ResultSet rs = iqdb.RunQuery(statusSqlQry.replace("##iqcustid##", iQCustID ));
	             try {
	            	 while(rs.next()) {
	            		  Status = rs.getString("IntegrationStatus");
	            	 }
	                                 
	             } catch (SQLException e) {
	                    e.printStackTrace();
	             }
	                    
	             return Status;
	    }

	@Owner(value="Velmurugan Cithaiyan")
	public String waitForCustomerAck(String iQCustID) throws Exception {
        
		System.out.println("<---|waitForCustomerAck : iQCustID= " + iQCustID );
	       Boolean doContinue = true;
	       String Status = "";

	       LocalDateTime start = LocalDateTime.now();  
	               
	       do {
	             //wait for 30 sec
	            Thread.sleep(Integer.parseInt(Config.getProperty("intSyncAck.wait")));
	                          
	             Status = getCustomerSyncStatus(iQCustID);
	             if(!Status.equals(null)) {                      
		             if ((Status.equals("3")) || (Status.equals("4"))) {
		            	 AllureLogger.log.info("waitForCustomerAck : Status = '" + Status + "'");
		                    doContinue = false;
		             }else
		            	 AllureLogger.log.info("waitForCustomerAck : Status = '" + Status + "', Waiting for acknowledgement from MIS...");
	             }else
	              AllureLogger.log.info("waitForCustomerAck : Status = '" + Status + "', Waiting for acknowledgement from MIS...");
	             
	             //check the wait time
	             if (java.time.Duration.between(start, LocalDateTime.now()).toMinutes() 
	                          == Integer.parseInt(Config.getProperty("implicit.wait"))) { //if it is not processed for 5mins, then come out
	                    AllureLogger.markStepAsFailed("Didnt received the acknowledgement for Customer '" + iQCustID + "' even after 5 mins.");
	                    System.out.println("<---|waitForCustomerAck = " + Status );
	         	       return Status; 
	             }
	                          
	       } while (doContinue);
	             
	       System.out.println("<---|waitForCustomerAck = " + Status );
	       return Status; 
	}
	
	@Owner(value="Daniel Joel")
	public void ClickElement(By Locator) {
		if(DriverManager.getDriver().findElements(Locator).size()>0) {
			wait.until(ExpectedConditions.elementToBeClickable(Locator));
			DriverManager.getDriver().findElement(Locator).click();
		}
		else {
			assertTrue(false,"Element not found to click");
		}
	}
	
	@Owner(value="Daniel Joel")
	public void ClickElement(WebElement ele) {
		WebElement e=wait.until(ExpectedConditions.elementToBeClickable(ele));
		e.click();
	}

	@Step("Run Query and Return Multiple Column as Hashmap")
	public Map<String,List<String>> RunQueryandReturnMultipleColumnasHashMapall(String Path, String key, int numberOfColumn,String... Params)
			throws ClassNotFoundException, SQLException, IOException {
		Map<String, List<String>> ColumnData = new LinkedHashMap<String, List<String>>();
		
		fis = new FileInputStream(System.getProperty("user.dir") + Path);
		Query.load(fis);
		String Query1 = Query.getProperty(key);

		for (String i : Params) {
			for (int j = 1; j <= Params.length; j++) { 
				if (Query1.contains("##" + String.valueOf(j) + "##")) {
					Query1 = Query1.replace("##" + String.valueOf(j) + "##", i);
					break;
				}
			}
		}

		rs = iqdb.RunQuery(Query1);
		int count = 0;
		
		try {
			for (int i = 1; i <= numberOfColumn; i++) {

				List<String> aList = new LinkedList<String>();
				ColumnData.put(rs.getMetaData().getColumnName(i), aList);
				while (rs.next()) {
					ColumnData.get(rs.getMetaData().getColumnName(i)).add(rs.getString(i));
					System.out.println("Col name is " + rs.getString(i));
				}
				rs.first();
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("failed");
		}

		return ColumnData;
	}
 
	@Owner(value="Velmurugan Cithaiyan")
	private List<String> getColumnValue(int numberOfColumn, ResultSet rset) throws SQLException {
	 
		List<String> aList = null;
		
		for (int i = 1; i <= numberOfColumn; i++) {
			aList = new LinkedList<String>();
			while (rset.next()) {
				if(rs.getString(i)!=null)
		    		aList.add(rset.getString(i));
				System.out.println("Col name is " + rset.getString(i));
			}
		}

		return aList;
	}

	@Owner(value="Velmurugan Cithaiyan")
	public String readQueryFromProperties(String path, String key) throws Exception {
		
		System.out.println("--->|readQueryFromProperties : path=" + path + ", key=" + key);
		
		prop = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir") + path);
		prop.load(fis);
		
		String query = prop.getProperty(key);
		prop = null;
		
		System.out.println("<---|readQueryFromProperties : " + query);		
		return query;
	}
	
	@Owner(value="Velmurugan Cithaiyan")
	public HashMap<String, String> getHashMapFromQuery(String qryString) {
		
		HashMap<String, String> sqlData = new HashMap<>();
		
		try {			
			
			System.out.println("---->|getHashMapFromQuery : qryString = " + qryString);
			
			ResultSet rs = iqdb.RunQuery(qryString);
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnCount = rsmd.getColumnCount();
			int row = 0;
						
			while (rs.next()) {
			    for (int i = 1; i <= columnCount; i++) {
			    	if(rs.getString(i)!=null)
			    		sqlData.put(rsmd.getColumnName(i) + i, rs.getString(i));
			    	else
			    		sqlData.put(rsmd.getColumnName(i) + i, "");
			    }	
			    row++;
			}
			
			sqlData.put("rowcount", String.valueOf(row));			
			System.out.println("getHashMapFromQuery : Total row(s) fetched = " + String.valueOf(row));
								
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sqlData;
	}
	
	@Owner(value="Velmurugan Cithaiyan")
	public String getValueOfColumn(ResultSet rs, String columnName) throws Exception {
		
		System.out.println("--->|getValueOfColumn : columnName = " + columnName );
		
		String val="";		
		ResultSetMetaData rsmd = rs.getMetaData();
		
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			if (rsmd.getColumnName(i).equalsIgnoreCase(columnName)) {
				if(rs.getString(i)!=null) {
					val = rs.getString(i);
					break;
				}
			}
	    }	
				
		System.out.println("<---|getValueOfColumn = " + val );
		return val;
	}
	
	@Owner(value="Velmurugan Cithaiyan")
	public Date convertStringToDate(String dateString, String format)
	{
		System.out.println("--->|convertStringToDate : dateString = " + dateString + ", format = " + format );
		
	    Date date = null;
	    DateFormat df = new SimpleDateFormat(format);
	    try{
	    	if(dateString!=null && !dateString.isEmpty())
	    		date = df.parse(dateString);
	    }
	    catch ( Exception ex ){
	        System.out.println("convertStringToDate : Error '"+ex+"' on parsing " + dateString+" with format "+format);
	    }
	    
	    System.out.println("<---|convertStringToDate = " + date );
	    return date;
	}
	
	@Owner(value="Velmurugan Cithaiyan")
	public String formatDate(Date date, String format)
	{
		System.out.println("--->|formatDate : date = " + date + ", format = " + format );
		
	    String formatteddate = "";
	    DateFormat df = new SimpleDateFormat(format);
	    try{
	    	if(date!=null)
	    		formatteddate = df.format(date);
	    }
	    catch ( Exception ex ){
	        System.out.println("formatDate : Error '"+ex+"' on formating "+date+" with format "+format);
	    }
	    
	    System.out.println("<---|formatDate = " + formatteddate );
	    return formatteddate;
	}
	
	@Owner(value="Velmurugan Cithaiyan")
	public static String getPrintFLowLastModifiedDate(String iPlanJobID) throws Exception {
	       
			String lastmodified = "";       
		    fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\integration\\printflow.properties");
			Query.load(fis);
			
			String statusSqlQry=Query.getProperty("getLastModifiedDate");		      
			ResultSet rs = pfdb.RunQuery(statusSqlQry.replace("##iplanid##", iPlanJobID ));
			
		    try {
	        		while(rs.next()) {
	            	lastmodified = rs.getString("lastmodified");
	        }
	                                 
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	                    
		    return lastmodified;
	    }
	
	@Owner(value="Velmurugan Cithaiyan")
	public boolean waitForPrintFlowSync(String iPlanJobID, Boolean isJobCanceled, String syncStartDateTime) throws Exception {
		
		/* IMPORTANT NOTES: Application Server and system where you are running the script should have same time
		else this function will fail due to difference in last modified date. */
		
		System.out.println("--->|waitForPrintFlowSync : iPlanJobID= " + iPlanJobID +", syncStartDateTime="+syncStartDateTime + ", isJobCanceled="+isJobCanceled );

		boolean doContinue = true;
		boolean flag = true;
	    String last = "";
	    LocalDateTime lastmodified;
	    
	    LocalDateTime startTimeStamp = LocalDateTime.parse(syncStartDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));  
	               
	       do {
	            //wait for specified wait time
	            Thread.sleep(Integer.parseInt(Config.getProperty("intSyncAck.wait")));	                          
	            last = getPrintFLowLastModifiedDate(iPlanJobID);
	            
	            if(!last.isEmpty()) {  
	            	 lastmodified = LocalDateTime.parse(last, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		             if (lastmodified.isEqual(startTimeStamp) || lastmodified.isAfter(startTimeStamp)) {
		                    doContinue = false;
		             }
	             }
	            else if (isJobCanceled){
	            	doContinue = false;
	            }
	            AllureLogger.log.info("waitForPrintFlowSync : Last Modified Date is " + last + ", Waiting for date greater than " + startTimeStamp);
	             
	             //check the wait time
	             if (java.time.Duration.between(startTimeStamp, LocalDateTime.now()).toMinutes() 
	                          == Integer.parseInt(Config.getProperty("implicit.wait"))) { //if it is not processed for 5mins, then come out
	                    AllureLogger.markStepAsFailed("waitForPrintFlowSync : Didnt received the acknowledgement for Job '" + iPlanJobID + "' even after 5 mins.");
	                    flag=false;
	                    System.out.println("<---|waitForPrintFlowSync = " + flag );
	         	       return flag; 
	             }
	             
	                          
	       } while (doContinue);
	             
       System.out.println("<---|waitForPrintFlowSync = " + flag );
	       return flag; 

	}
		
	@Owner(value="Daniel Joel")
	public void SendKeys(By Locator,String val) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
		wait.until(ExpectedConditions.elementToBeClickable(Locator));
		DriverManager.getDriver().findElement(Locator).sendKeys(Keys.CONTROL,"a",Keys.DELETE);
		DriverManager.getDriver().findElement(Locator).sendKeys(val);
		Thread.sleep(3000);
		DriverManager.getDriver().findElement(Locator).sendKeys(Keys.TAB);		
	}
	
	public static void closeAllTabs() {
		List<WebElement> eles=DriverManager.getDriver().findElements(By.xpath("//ul[@class='app__tabs']//li[@data-key-ctrl='*']//span[text()='x']"));
		for(WebElement ele:eles) {
			ele.click();
		}
	}

	public static boolean deleteFilesInFolder(String directoryname) {
		File directory = new File(directoryname);
		File[] files = directory.listFiles();
		for(File file:files) {
			if(!file.delete()) {
				return false;
			}
		}
		return true;		
	}
	
	@Owner(value="Daniel Joel")
	public void CloseOpenedProjects() throws Exception {
		//Desktop.quickSearch("Manage open Projects");
		ClickElement(By.xpath("//label[text()='Sales']//parent::span"));
		ClickElement(By.xpath("//label[text()='iQuote']//parent::span"));
		WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='select__tree lkv__menu__select']//label[text()='Manage open Multiple Jobs Creation']")));
		ele.click();
		int colcount=returnCount(By.xpath("//div[@class='ReactVirtualized__Grid grid__header']//span[@class='grid__header__cell']"));
		int rowcount=returnCount(By.xpath("//div[@class='ReactVirtualized__Grid grid__body']//span[@data-index]"))/colcount;
		for(int i=1;i<=rowcount;) {
			DriverManager.getDriver().findElement(By.xpath("//label[text()='Close Project in the server']//parent::button")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Close Project in the server']//ancestor::header")));
			DriverManager.getDriver().findElement(By.xpath("//button[@title='Confirm']")).click();
			rowcount--;
		}
		CloseTab("Manage open Multiple Jobs Creation");
	}
	
	
	public static void pressKey(String vKey) {
		try {
			
			robot = new Robot();
			
			switch(vKey) {
			case "Enter":
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			break;
			case "Right":
				robot.keyPress(KeyEvent.VK_RIGHT);
				robot.keyRelease(KeyEvent.VK_RIGHT);
				break;
			
			case "Tab":
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			break;
			
			case "Page down":
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				break;
				
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String, String> returnValuesFromExcel_HashMap(String path, String sheetName,
			String scenarioName) {
		String fPath = path;
		System.out.println("File Picked from: " + fPath);
		File file;
		FileInputStream fs;
		XSSFWorkbook workBook;
		XSSFSheet sheet;
		XSSFCell cell;
		HashMap<String, String> valsMap = new HashMap<String, String>();

		try {
			int reqColNum = 0;
			int reqRowNum = 0;
			file = new File(fPath);
			fs = new FileInputStream(file);
			workBook = new XSSFWorkbook(fs);
			sheet = workBook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();
			for (int j = 1; j <= rowCount; j++) {
				cell = sheet.getRow(j).getCell(0);
				if (cell.toString().equalsIgnoreCase(scenarioName)) {
					reqRowNum = j;
					break;
				}
			}

			for (int i = 0; i <= colCount; i++) {
				cell = sheet.getRow(0).getCell(i);
				String key = "dummy";
				String val = "";
				try {
					key = cell.toString().trim();
					cell = sheet.getRow(reqRowNum).getCell(i);

					try {
						//sheet.getRow(reqRowNum).getCell(reqColNum).setCellType(Cell.CELL_TYPE_STRING);
						DataFormatter fmt = new DataFormatter();
						val = fmt.formatCellValue(cell);
						//val = cell.toString().trim();
					} catch (Exception e) {
						val = "";
					}
					//System.out.println("ColName: " + key + " Value: " + val);
				} catch (Exception e) {
					break;
				}

				valsMap.put(key, val);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return valsMap;
	}
	public static String returndatetime(){
		Date date = new Date();
		SimpleDateFormat  formatter = new SimpleDateFormat("ddMyyhhmmss");  
		String  strDate = formatter.format(date);
		return strDate;			  
	}
	public static void Iquote_SelectFromDropdown_Fortypedtext(WebDriver driver,String Fieldvalue, String xpathfordropdowntext)
			throws Exception {
	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathfordropdowntext)));
		List<WebElement> Allelements = driver.findElements(By.xpath(xpathfordropdowntext));
		
		for(WebElement ele:Allelements)
				if (ele.getText().equalsIgnoreCase((Fieldvalue))) {
					String elementselected=ele.getText();
					ele.click();
					System.out.println(ele);
					System.out.println("selected the given text"+elementselected);
					break;
				} else {
					
				}

	}
	
	
	public static boolean write_ValueTO_Excel(String filePath, String sheetName, String scenarioName, String columnName,
			String valueToEnter) {
		File file;
		FileInputStream fs;
		XSSFWorkbook workBook;
		XSSFSheet sheet;
		XSSFCell cell;

		try {
			int reqColNum = 0;
			int reqRowNum = 0;
			file = new File(filePath);
			fs = new FileInputStream(file);
			workBook = new XSSFWorkbook(fs);
			sheet = workBook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();

			for (int i = 0; i < colCount; i++) {
				cell = sheet.getRow(0).getCell(i);
				if (cell.toString().equalsIgnoreCase(columnName)) {
					reqColNum = i;
					break;
				}
			}
			if (reqColNum != 0) {
				for (int j = 1; j <= rowCount; j++) {
					//for row with with first column
					cell = sheet.getRow(j).getCell(0);
					if (cell.toString().equalsIgnoreCase(scenarioName)) {
						reqRowNum = j;
						//break;
						// sheet.getRow(reqRowNum).getCell(reqColNum).setCellValue(valueToEnter);
						// Checking whether the cell is null if so creating cell to
						// accept string values
						cell = sheet.getRow(reqRowNum).getCell(reqColNum);
						if (cell == null) {
							cell = sheet.getRow(reqRowNum).createCell(reqColNum);
							// cell.setCellType(Cell.CELL_TYPE_STRING);
						}
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(valueToEnter);
						break;
					}
					
				}

				FileOutputStream fileOut = new FileOutputStream(file);
				workBook.write(fileOut);
				fileOut.close();

				//workBook.close();


				return true;
			} else {
				System.err.println("Please provide valid test data");
				return false;
			}

		} catch (FileNotFoundException e) {
			System.err.println("File not found or being used by other process");
			return false;
		} catch (Exception e) {
			System.err.println("Exception occured");
			return false;
		}
	}
	
	public static HashMap<String, String> returnValuesFromExcel_HashMap_secondcolumn(String path, String sheetName,
			String scenarioName) {
		String fPath = path;
		System.out.println("File Picked from: " + fPath);
		File file;
		FileInputStream fs;
		XSSFWorkbook workBook;
		XSSFSheet sheet;
		XSSFCell cell;
		HashMap<String, String> valsMap = new HashMap<String, String>();

		try {
			int reqColNum = 0;
			int reqRowNum = 0;
			file = new File(fPath);
			fs = new FileInputStream(file);
			workBook = new XSSFWorkbook(fs);
			sheet = workBook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();
			for (int j = 1; j <= rowCount; j++) {
				cell = sheet.getRow(j).getCell(1);
				if (cell.toString().equalsIgnoreCase(scenarioName)) {
					reqRowNum = j;
					break;
				}
			}

			for (int i = 0; i <= colCount; i++) {
				cell = sheet.getRow(0).getCell(i);
				String key = "dummy";
				String val = "";
				try {
					key = cell.toString().trim();
					cell = sheet.getRow(reqRowNum).getCell(i);

					try {
						//sheet.getRow(reqRowNum).getCell(reqColNum).setCellType(Cell.CELL_TYPE_STRING);
						DataFormatter fmt = new DataFormatter();
						val = fmt.formatCellValue(cell);
						//val = cell.toString().trim();
					} catch (Exception e) {
						val = "";
					}
					//System.out.println("ColName: " + key + " Value: " + val);
				} catch (Exception e) {
					break;
				}

				valsMap.put(key, val);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return valsMap;
	}
	public static boolean write_ValueTO_Excel_for_allmatchingrow(String filePath, String sheetName, String scenarioName, String columnName,
			String valueToEnter) {
		File file;
		FileInputStream fs;
		XSSFWorkbook workBook;
		XSSFSheet sheet;
		XSSFCell cell;

		try {
			int reqColNum = 0;
			int reqRowNum = 0;
			file = new File(filePath);
			fs = new FileInputStream(file);
			workBook = new XSSFWorkbook(fs);
			sheet = workBook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();

			for (int i = 0; i < colCount; i++) {
				cell = sheet.getRow(0).getCell(i);
				if (cell.toString().equalsIgnoreCase(columnName)) {
					reqColNum = i;
					break;
				}
			}
			if (reqColNum != 0) {
				for (int j = 1; j <= rowCount; j++) {
					cell = sheet.getRow(j).getCell(0);
					if (cell.toString().equalsIgnoreCase(scenarioName)) {
						reqRowNum = j;
						//break;
						// sheet.getRow(reqRowNum).getCell(reqColNum).setCellValue(valueToEnter);
						// Checking whether the cell is null if so creating cell to
						// accept string values
						cell = sheet.getRow(reqRowNum).getCell(reqColNum);
						if (cell == null) {
							cell = sheet.getRow(reqRowNum).createCell(reqColNum);
							// cell.setCellType(Cell.CELL_TYPE_STRING);
						}
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(valueToEnter);

					}
					
				}

				FileOutputStream fileOut = new FileOutputStream(file);
				workBook.write(fileOut);
				fileOut.close();

//				workBook.close();


				return true;
			} else {
				System.err.println("Please provide valid test data");
				return false;
			}

		} catch (FileNotFoundException e) {
			System.err.println("File not found or being used by other process");
			return false;
		} catch (Exception e) {
			System.err.println("Exception occured");
			return false;
		}
	}
	public static boolean write_ValueTO_Excel_secondcolumn(String filePath, String sheetName, String scenarioName, String columnName,
			String valueToEnter) {
		File file;
		FileInputStream fs;
		XSSFWorkbook workBook;
		XSSFSheet sheet;
		XSSFCell cell;

		try {
			int reqColNum = 0;
			int reqRowNum = 0;
			file = new File(filePath);
			fs = new FileInputStream(file);
			workBook = new XSSFWorkbook(fs);
			sheet = workBook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();

			for (int i = 0; i < colCount; i++) {
				cell = sheet.getRow(0).getCell(i);
				if (cell.toString().equalsIgnoreCase(columnName)) {
					reqColNum = i;
					break;
				}
			}
			if (reqColNum != 0) {
				for (int j = 1; j <= rowCount; j++) {
					cell = sheet.getRow(j).getCell(1);
					if (cell.toString().equalsIgnoreCase(scenarioName)) {
						reqRowNum = j;
						//break;
						// sheet.getRow(reqRowNum).getCell(reqColNum).setCellValue(valueToEnter);
						// Checking whether the cell is null if so creating cell to
						// accept string values
						cell = sheet.getRow(reqRowNum).getCell(reqColNum);
						if (cell == null) {
							cell = sheet.getRow(reqRowNum).createCell(reqColNum);
							// cell.setCellType(Cell.CELL_TYPE_STRING);
						}
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(valueToEnter);

					}
					
				}

				FileOutputStream fileOut = new FileOutputStream(file);
				workBook.write(fileOut);
				fileOut.close();

//				workBook.close();

				return true;
			} else {
				System.err.println("Please provide valid test data");
				return false;
			}

		} catch (FileNotFoundException e) {
			System.err.println("File not found or being used by other process");
			return false;
		} catch (Exception e) {
			System.err.println("Exception occured");
			return false;
		}
	}	
	public  static  void writerow(HashMap<String, String> from_row, HashMap<String, String> to_row,String testcase) {
		String Excel="";
		String Sheetname="Integration";
	    HashMap<String, String> valsMap = new HashMap<String, String>();
	    

	    valsMap.put("Ad1AddressName", "");
	    valsMap.put("Ad1AddressLine1", "");
	    valsMap.put("Ad1AddreesLine2", "");
	    valsMap.put("Ad1AddressLine3", "");
	    valsMap.put("Ad1countrycode", "");
	    valsMap.put("Ad1country", "");
	    valsMap.put("Ad1countycode", "");
	    valsMap.put("Ad1county", "");
	    valsMap.put("Ad1zip", "");
	    valsMap.put("Ad1Neighbour", "");
	    
	    for (String key : valsMap.keySet()) {
	    if (from_row.containsKey(key))
	    {
	    if (valsMap.get(key)=="")
	    {
	    	valsMap.put(key,from_row.get(key) );
	    	System.out.println("key:"+key+"     "+"value"+valsMap.get(key));
	    }
	    }
	    }	  
	    System.out.println("---------------------------------");
	    for (String key : valsMap.keySet()) {
	        if (to_row.containsKey(key))
	        {
	        if (to_row.get(key)=="")
	        {
	        	to_row.put(key,valsMap.get(key) );
	        	System.out.println("key:"+key+"    "+"value"+to_row.get(key));
	        }
	        }
	        }	
	    
	    for (String key : to_row.keySet()){
	    	if(to_row.get(key)!="" && to_row.get(key)!=null && to_row.get(key)!="TC1")
	    	{
	    	
	    		
	    		
	    			System.out.println(to_row.get(key));
	    CommonFunctions.write_ValueTO_Excel_secondcolumn(Excel,Sheetname,testcase,key.toString() ,to_row.get(key));
	    		
	    }
		
	    }
	    
	}
	
	@Step("Navigating  tab")
	public void navigateToTab(String Tab) throws InterruptedException {
		if(driver.findElements(By.xpath("//label[contains(text(),'"+Tab+"')]/parent::span/parent::li//span[@class='app__tab__close']")).size()>0) {
			WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='"+Tab+"']/parent::span")));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			Thread.sleep(4000);
			ele.click();
		}else {
			System.out.println("Tab '"+Tab+"' doesn't exists");
		}
	}
	
	@Step("Extacting number from String")
	public String extractedNumFromString(String vNum) throws InterruptedException {
		String newNum=vNum.replaceAll("[^\\d.]", "");
			newNum.trim();  
			Logger.info("The Number extracted from string is: "+ newNum);
			return newNum;
	}
	
	@Owner(value="Daniel Joel")
	public static String decToHexa(int n) 
    {    
        // char array to store hexadecimal number 
        char[] hexaDeciNum = new char[100]; 
        String value="";
       
        // counter for hexadecimal number array 
        int i = 0; 
        while(n!=0) 
        {    
            // temporary variable to store remainder 
            int temp  = 0; 
           
            // storing remainder in temp variable. 
            temp = n % 16; 
           
            // check if temp < 10 
            if(temp < 10) 
            { 
                hexaDeciNum[i] = (char)(temp + 48); 
                i++; 
            } 
            else
            { 
                hexaDeciNum[i] = (char)(temp + 55); 
                i++; 
            } 
           
            n = n/16; 
        } 
       
        // printing hexadecimal number array in reverse order 
        for(int j=i-1; j>=0; j--) { 
            //System.out.print(hexaDeciNum[j]);
            value=value+hexaDeciNum[j];
        }
        return value;
    } 
      
	@Owner(value="Daniel Joel")
	public String GetAttributeValue(WebElement ele,String Attribute) {
		WebElement e=wait.until(ExpectedConditions.visibilityOf(ele));
		return e.getAttribute(Attribute);
	}
    
	@Owner(value="Daniel Joel")
	public String GetTextValue(WebElement ele) {
		WebElement e=wait.until(ExpectedConditions.visibilityOf(ele));
		return e.getText();
	}

	@Owner(value="Daniel Joel")
	public String GetTextValue(By Loc) {
		WebElement e=wait.until(ExpectedConditions.visibilityOfElementLocated(Loc));
		return e.getText();
	}
	
	@Owner(value="Daniel Joel")
	public String GetAttributeValue(By Loc,String Attribute) {
		WebElement e=wait.until(ExpectedConditions.visibilityOfElementLocated(Loc));
		return e.getAttribute(Attribute);
	}    
	@Owner(value="Daniel Joel")
	public void compareExcelFiles(InputStream expected, InputStream actual)  throws Exception
			{
			  try {
			    Assertion.assertEquals(new XlsDataSet(expected), new XlsDataSet(actual));
			  }
			  finally {
			    IOUtils.closeQuietly(expected);
			    IOUtils.closeQuietly(actual);
			  }
			}
	
	@Owner(value="Madhuri Mishra")
	public void clickConfirmButton()  throws Exception
			{
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Com_ConfirmBtn")));
			}
	
	
	@Owner(value="Madhuri Mishra")
	public void clickOkButton()  throws Exception
			{
		CommonFunctions.ClickElement(driver, By.xpath(OR.getProperty("Com_OKBtn")));
			}
	@Owner(value="DAniel Joel")
	public void CloseEstimateTab() {
		ClickElement(By.xpath("//ul[@class='program__context__tabs']//span[@class='app__tab__close']"));
	}
	public static boolean waitUntilElementisPresent(WebDriver driver, WebElement element, int TimeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TimeOut);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (TimeoutException e) {
			System.err.println("Element did not appear even after waiting for " + TimeOut + " seconds");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	public static boolean waitForElement(WebDriver driver,By elexPath,int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(elexPath));
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	//Common MEthods which accepts driver
	@Owner(value="Daniel Joel")
	public void navigateTo(WebDriver driverN,String url) {
		driverN.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driverN.manage().window().maximize();
		driverN.get(url);
		driverN.manage().window().maximize();
		driverN.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);		
		
	}
	@Owner(value="Daniel Joel")
	public void closebrowser(WebDriver driverN) {
		if(driverN!=null) {
			driverN.close();
			driverN.quit();
		}
	}
	
}

