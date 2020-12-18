package base;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Testbase1 {

	public static FileInputStream fis;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static WebDriverWait wait;
	public static String xlMasterData=null, xlDataExchange=null;
	protected static DBUtil iqdb = new DBUtil();
    protected static DBUtil pfdb = new DBUtil();
	BrowserFactory BF=new BrowserFactory();
	  @BeforeMethod(alwaysRun = true)
	  @Parameters({ "propertyfile" ,"portNO"})
		public void setUp(@Optional("PremierPress") String propertyfile,@Optional("") String portNO) throws Exception {
			
				
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
				
				
				 if(portNO.isEmpty() && Config.getProperty("browser").equals("chrome")) {
					
						
					FileInputStream fis2;
					try{
						fis2=new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
						System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
						fis2.close();
					}catch(Exception e){
						fis2=new FileInputStream(getProjectPath() + "\\src\\test\\resources\\executables\\chromedriver.exe");
						System.setProperty("webdriver.chrome.driver",getProjectPath() + "\\src\\test\\resources\\executables\\chromedriver.exe");
					}
					DriverFactory.getInstance().setDriver(BF.createBrowserInstance("Chrome"));
					WebDriver driver = DriverFactory.getInstance().getDriver();
					DriverManager.setDriver(driver);
					System.out.println(Config.getProperty("testsiteurl"));
					DriverManager.getDriver().get(Config.getProperty("testsiteurl"));
					
					DriverManager.getDriver().manage().window().maximize();
					DriverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
							TimeUnit.SECONDS);
					
					wait = new WebDriverWait(driver,60);
				}
			 
			    if(Config.getProperty("iQDBUrl")!=null)
			    	iqdb.createconnection(Config.getProperty("iQDBUrl"), Config.getProperty("DBUsername"), Config.getProperty("DBPassWord"));
		        if(Config.getProperty("pfDBUrl")!=null)
		        	pfdb.createconnection(Config.getProperty("pfDBUrl"), Config.getProperty("DBUsername"), Config.getProperty("DBPassWord"));
		        BasicConfigurator.configure();

		}
	  @AfterClass
		public void tearDown() throws SQLException {
			DriverFactory.getInstance().closeBrowser();
			   
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
