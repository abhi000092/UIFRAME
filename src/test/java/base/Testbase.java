package base;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Testbase {

	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public static ExtentReports extent;
    public static ExtentTest test;
    public static String browser;
    public static Actions actions;
    public static int Optionqty=0;
    public static  Robot robot;
    String nodeURL;
    public SoftAssert softAssert = new SoftAssert();   
    public static final int minWait = 2;
	public static final int midWait = 12;
	public static final int maxWait = 20;
    
    protected static DBUtil iqdb = new DBUtil();
    protected static DBUtil pfdb = new DBUtil();
	
    public static String xlMasterData=null, xlDataExchange=null;
   
    @BeforeMethod(alwaysRun = true)
	@Parameters({ "propertyfile" ,"portNO"})
	public void setUp(@Optional("CICD") String propertyfile,@Optional("") String portNO) throws Exception {
		
			
			try{
				fis = new FileInputStream(			
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\"+propertyfile+".properties");
			}catch(Exception e)
			{
				fis = new FileInputStream(			
						getProjectPath() + "\\src\\test\\resources\\properties\\"+propertyfile+".properties");
					
			}
			Config.load(fis);
			//set the xls path
			xlMasterData=System.getProperty("user.dir")+Config.getProperty("xlMasterTestData");
			xlDataExchange=System.getProperty("user.dir")+Config.getProperty("xlDataExchange");
			
			try{
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
				}catch(Exception e){
					fis = new FileInputStream(
							getProjectPath() + "\\src\\test\\resources\\properties\\OR.properties");
					
				}
				
				OR.load(fis);
			
			if(!portNO.isEmpty() && Config.getProperty("browser").equals("chrome"))
			{
				DesiredCapabilities dc = DesiredCapabilities.chrome();
				dc.setPlatform(Platform.LINUX);
				dc.setBrowserName("chrome");
				dc.setVersion("");
				URL url = new URL("http://localhost:4444/wd/hub");
				 driver = new RemoteWebDriver(url,dc);
				driver.navigate().to(Config.getProperty("testsiteurl"));
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				wait = new WebDriverWait(driver, 30);
			}
			else if(portNO.isEmpty() && Config.getProperty("browser").equals("chrome")) {
				
					
				FileInputStream fis2;
				try{
					fis2=new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
					System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
					fis2.close();
				}catch(Exception e){
					fis2=new FileInputStream(getProjectPath() + "\\src\\test\\resources\\executables\\chromedriver.exe");
					System.setProperty("webdriver.chrome.driver",getProjectPath() + "\\src\\test\\resources\\executables\\chromedriver.exe");
				}
				// WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				DriverManager.setDriver(driver);
				System.out.println(Config.getProperty("testsiteurl"));
				DriverManager.getDriver().get(Config.getProperty("testsiteurl"));
				
				DriverManager.getDriver().manage().window().maximize();
				DriverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
						TimeUnit.SECONDS);
				
				wait = new WebDriverWait(driver,60);
			}
		    robot = new Robot();
		    if(Config.getProperty("iQDBUrl")!=null)
		    	iqdb.createconnection(Config.getProperty("iQDBUrl"), Config.getProperty("DBUsername"), Config.getProperty("DBPassWord"));
	        if(Config.getProperty("pfDBUrl")!=null)
	        	pfdb.createconnection(Config.getProperty("pfDBUrl"), Config.getProperty("DBUsername"), Config.getProperty("DBPassWord"));
	        BasicConfigurator.configure();

	}
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
    	int i=result.getStatus();
     if (i== ITestResult.FAILURE || i== ITestResult.SKIP) {
    	 // Desktop.logout();
         driver.quit();
         driver = null;
      } 
    } 
	protected static void saveProperties(Properties p,String val) throws Exception
    {
        FileOutputStream fr = new FileOutputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\"+val+".properties");
        p.store(fr, "Properties");
        fr.close();
        System.out.println("After saving properties: " + p);       
    }

	@AfterClass
	public void tearDown() throws SQLException {
		try {
		        
			//Quit all drivers
			if(driver!=null) {
				// Desktop.logout();
				driver.quit();			
			}
		}catch(Exception e) {
			throw e;
		}
		   
		//close all db connections
		if(iqdb.con!=null)
			iqdb.Closeconnection();
		if(pfdb.con!=null)
			pfdb.Closeconnection();
		
	}
	public static String getProjectPath(){
			
		return "C:\\Users\\Administrator\\EFI\\Automation\\Radius_Core_Automation\\IquoteAutomation\\SeleniumScripts";
		
	}

}