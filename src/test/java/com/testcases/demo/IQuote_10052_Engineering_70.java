package com.testcases.demo;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import base.Logger;
import base.Testbase;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


import utilities.AllureLogger;
import utilities.CommonFunctions; 

//@Listeners(utilities.ExtentListeners.class)
//@Owner(value="Santoshi")
//public class IQuote_10052_Engineering_70 extends Testbase{
//	static Faker random; 
//	User userpage;
//	CommonFunctions CFS;
//	Desktop Ds;
//	NewEstimate NE;
//	Product Prod;
//	Engineering eng;
//	JobPage JP;
//	Estimate Est;
//	Product Prd;
//	HistoryEstimate Hist;
//	Qty_Price QtyPrice;


//	@BeforeMethod(alwaysRun = true)
//	public void init() {
//		random = new Faker();
//		userpage=new User();
//		CFS=new CommonFunctions();
//		Ds=new Desktop();
//		NE= new NewEstimate();
//		eng=new Engineering();
//		JP=new JobPage();
//		Est=new Estimate();
//		Prd=new Product();
//		Hist=new HistoryEstimate();
//		QtyPrice=new Qty_Price();

//	}

//	@BeforeMethod(alwaysRun = true)
//	@Description("Login to Application")
//	public void logintoiquote() {
//
//		AllureLogger.startTest();
//		try {
//			IQuoteLogin.login(Config.getProperty("UserName"), Config.getProperty("Password"));
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//	}

//	@Test()
//	@Severity(SeverityLevel.BLOCKER)
//	@Description("IQUOTE-10052 :Engineering #70 Estimate with models(sheet/ leaflet/flat sheet)")
//	public void Iquote_10052() throws Exception {
//		try {
//
//			//Substrate details
//			String subsType = CFS.readFromExcel(xlMasterData,"substype4text");
//			String subsLine = CFS.readFromExcel(xlMasterData,"subsline4text");
//			String subsGsm = CFS.readFromExcel(xlMasterData,"subsgsm4text"); 	
//
//			//Plant details
//			String pPlant = CFS.readFromExcel(xlMasterData,"parentplant");
//			String cPlant = CFS.readFromExcel(xlMasterData,"childplant");
//
//			//Enter version
//			String Version ="3";
//
//			//quantity
//			String[] qty = new String[3];
//			qty[0]="119";
//			qty[1]="333";
//			qty[2]="500";
//
//			Logger.info("------------1.IQ>Verify Creating Estimate for a Sheet product--------");
//
//			Desktop.quickSearch("Estimate");	
//			Estimate.ClickonNewEstimate();	
//			NE.EnterIdentificationDetails("", "Sheets", "Flat Sheet","", "7 X 10", "100");
//			softAssert.assertTrue(Prd.selectComponent("Sheet"));	
//			Assert.assertTrue(Prd.enterPaperDetails(subsType, subsLine, subsGsm),"Entering Paper Details");
//			Assert.assertTrue( Prd.enterColors("4","4"),"Entering Color");
//			Estimate.SaveEstimate();
//			Assert.assertTrue(Estimate.CalculateEstimate(),"Estimate Calculation Failed");
//			
//			Estimate.NavigateToNegotiationTab();
//			String newest=Estimate.getEstimateNumber();
//
//			Est.Navigate_to_ProductTab();
//			CFS.waitForPageLoaded();	
//			softAssert.assertTrue(Prd.selectComponent("Sheet"));
//			
//			//click on specification leafformat
//			
//			Logger.info("------------2.IQ>Verify Creating Estimate for a Sheet product: with models--------");
//
//			Est.Specification_changeProduct("Leaf Format", "Folded Format (model)");
//			CFS.waitForPageLoaded();
//			Est.ProdSpecification_AddVersion(Version);
//		
//			Est.NavigateToQtyPriceTab();
//			CFS.waitForPageLoaded();
//			QtyPrice.AddQuantityForProduct("Template 1", qty[0]);
//			Thread.sleep(1000);
//			QtyPrice.AddQuantityForProduct("Template 2", qty[1]);
//			Thread.sleep(1000);
//			QtyPrice.AddQuantityForProduct("Template 3", qty[2]);
//			Thread.sleep(1000);
//			Est.RecalculateEstimate();
//			Est.SaveEstimate();
//			
//			//validate Engineering
//			Est.NavigateToEngineeringTab();
//			String[] array = new String[3];
//			array[0]="Template 1";
//			array[1]="Template 2";
//			array[2]="Template 3";
//
//			for(int i=0;i<=2;i++)
//			{
//				int product = driver.findElements(By.xpath("//label[text()='"+array[i]+"']")).size();
//				int Qty = driver.findElements(By.xpath("//*[contains(text(),'"+qty[i]+" Sheet')]")).size();
//
//				if(product>0 && Qty>0)
//				{
//					Logger.info("Version with specified Quantity is validated in engineering");
//				}
//				else
//				{
//					Logger.error("Version with specified Quantity is Failed in engineering");
//				}
//			}
//
//			Logger.info("------------3.IQ>Verify Creating Estimate for a Sheet + leaf product: with models(add leaf product to above estimate only)--------");
//
//			Est.Navigate_to_ProductTab();
//			CFS.waitForPageLoaded();
//			Est.AddComponent("Leaf");
//			softAssert.assertTrue(Prd.selectComponent("Leaf"));
//			Assert.assertTrue(Prd.enterPaperDetails(subsType, subsLine, subsGsm),"Entering Paper Details");			
//			Prd.enterSize("4x4", By.xpath("//label[text()='Size (WxH)']/parent::span/span//input"));	
//			Assert.assertTrue(Prd.enterColors("4","4"),"Entering Color");
//			
//
//			CFS.waitForPageLoaded();
//			Est.Specification_changeProduct("Leaf Format", "Folded Format (model)");
//
//			//click on version and enter version
//			Est.ProdSpecification_AddVersion(Version);
//			Est.NavigateToQtyPriceTab();
//			CFS.waitForPageLoaded();
//			QtyPrice.AddQuantityForProduct("Template 4", qty[0]);
//			Thread.sleep(1000);
//			QtyPrice.AddQuantityForProduct("Template 5", qty[1]);
//			Thread.sleep(1000);
//			QtyPrice.AddQuantityForProduct("Template 6", qty[2]);
//			Thread.sleep(1000);
//			Est.RecalculateEstimate();
//			Est.SaveEstimate();
//			
//			//validate Engineering
//			Est.NavigateToEngineeringTab();
//			Thread.sleep(2000);
//			String[] array1 = new String[3];
//			array1[0]="Template 4";
//			array1[1]="Template 5";
//			array1[2]="Template 6";
//
//			for(int j=0;j<=2;j++)
//			{
//				boolean product = eng.verifyTextinDiagram(array1[j]);
//				
//			//	int product = driver.findElements(By.xpath("//label[text()='"+array1[j]+"']")).size();
//				//int Qty = driver.findElements(By.xpath("//*[contains(text(),'"+qty[j]+" Sheet')]")).size();
//
//				if(product==true )
//				{
//					Logger.info("Version with specified Quantity is validated in engineering");
//				}
//				else
//				{
//					Logger.error("Version with specified Quantity is Failed in engineering");
//				}
//			}
//			
//			CFS.CloseTab("Estimate");
//			
//		} catch (Exception e) {
//			throw e;
//		}
//
//		softAssert.assertAll();
//		AllureLogger.endTest();
//	}
//
//}


