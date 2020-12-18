//package com.testcases.demo;
//
//
//import java.sql.SQLException;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
//import com.github.javafaker.Faker;
//
//import base.DriverManager;
//import base.Logger;
//import base.Testbase;
//import io.qameta.allure.Description;
//import io.qameta.allure.Owner;
//import io.qameta.allure.Severity;
//import io.qameta.allure.SeverityLevel;
//import pages.Desktop;
//import pages.Engineering;
//import pages.Estimate;
//import pages.IQuoteLogin;
//import pages.Negotiation;
//import pages.NewEstimate;
//import pages.Product;
//import pages.Qty_Price;
//import utilities.AllureLogger;
//import utilities.CommonFunctions;
//
//@Listeners(utilities.ExtentListeners.class) 
//@Owner(value="Santoshi")
//public class IQuote_9049_Estimate_1 extends Testbase {
//	CommonFunctions CFS;
//	Estimate Est;
//	Engineering Eng;
//	Negotiation Neg;
//	Product prd;
//	Qty_Price QP;
//	NewEstimate NE;
//	static Faker random;
//	
//	@BeforeMethod(alwaysRun = true)
//	public void init() {
//		CFS=new CommonFunctions();
//		Est=new Estimate();
//		Eng = new Engineering();
//		prd = new Product();
//		NE=new NewEstimate();
//		random=new Faker();
//	}
//	@BeforeMethod(alwaysRun = true)
//	@Severity(SeverityLevel.NORMAL)
//	@Description("Login to Application")
//	public void logintoiquote() {
//		AllureLogger.startTest();
//		try {
//			IQuoteLogin.login(Config.getProperty("UserName"), Config.getProperty("Password"));
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//	}
//	@Test
//	@Severity(SeverityLevel.BLOCKER)
//	@Description("Verify the different actions from the estimate tool bar")
//	public void Estimatetoolbar() throws Exception, SQLException
//	{
//		AllureLogger.startTest();
//		try {
//
//			//Substrate details
// 			String subsType = CFS.readFromExcel(xlMasterData,"substype4text");
// 			String subsLine = CFS.readFromExcel(xlMasterData,"subsline4text");
// 			String subsGsm = CFS.readFromExcel(xlMasterData,"subsgsm4text"); 	
// 			
// 			String ProdName = "Bound";
// 			String ProdSubLine = "Perfect Bind";
// 			String ProdSize = "7 X 10";
// 			String Quantity = "5550";
// 			String spCode = "520";
// 			//Plant details
// 			String pPlant = CFS.readFromExcel(xlMasterData,"parentplant");
// 			String cPlant = CFS.readFromExcel(xlMasterData,"childplant");
// 			
// 			Logger.info("estimat1");
//			
// 			Desktop.quickSearch("Estimate");
// 			
// 			Estimate.ClickonNewEstimate();
// 			
// 			//Enter the prod spec
// 			NE.EnterIdentificationDetails("", ProdName, ProdSubLine, "", ProdSize, 
// 			String.valueOf(random.number().numberBetween(1000, 15000)));
//	 		softAssert.assertTrue(prd.selectComponent("Cover"));
//	 		Assert.assertTrue(prd.enterPaperDetails(subsType, subsLine, subsGsm));
//	 		Assert.assertTrue(prd.enterColors("4", "4"));
//	 		softAssert.assertTrue(prd.selectComponent("Text"));
//	 		Assert.assertTrue(prd.enterPaperDetails(subsType, subsLine, subsGsm));
//	 		Assert.assertTrue(prd.enterColors("4", "4"));
//	 		prd.enterPageNumber("64");
//	 		 Estimate.SaveEstimate();
//	 		Assert.assertTrue(Estimate.CalculateEstimate(),"Estimate Calculation Failed");
//	         Estimate.SaveEstimate();
//	         Estimate.NavigateToNegotiationTab();
//	         
//	       String newest=Estimate.getEstimateNumber();
//	       String  newest_withoutcomma=newest.replace(",", "");
//	       
//	       
//			Est.Navigate_to_ProductTab();
//			
// 			
//			/*
//			 * driver.findElement(By.xpath(
//			 * "//span[@class='wizard__step']//label[text()='Product']/parent::span")).click
//			 * (); 
//			 */
//			Logger.info("------------------------Addcomponent---------------------");
//			Est.AddComponent("Leaflet");
//			Logger.info("------------------------Addcomponent Ends---------------------");
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='diagram']/span[@class='diagram__item ps--item ps--cmp'][1]/label")));
//			Logger.info("------------------------Add Duplicate component---------------------");
//			String NewComponentCreated = Est.AddDuplicatecomponent();
//			
//			Logger.info("------------------------Add Duplicate component Ends---------------------");
//			Logger.info("------------------------Delete component---------------------");
//			
//			Est.Deletecomponent(NewComponentCreated);
//			Logger.info("------------------------Delete componentEnds---------------------");
//			
//			Logger.info("------------------------Add Duplicate componentSet---------------------");
//			String NewCreatedproduct = Est.AddDuplicatecomponentSet("Bound");
//			Logger.info("------------------------Add Duplicate componentSetEnds---------------------");
//			
//			Logger.info("------------------------Delete componentSet---------------------");
//			Est.DeletecomponentSet(NewCreatedproduct);
//			Logger.info("------------------------Delete componentSet Ends---------------------");
//			//String NewCreatedproduct = Est.AddDuplicatecomponentSet("Bound");
//			Logger.info("------------------------Add Duplicate componentSetEnds---------------------");
//			
//			Logger.info("------------------------Delete componentSet---------------------");
//			Est.DeletecomponentSet("Leaflet");
//			Logger.info("------------------------Delete componentSet Ends---------------------");
//
//			Est.CalculateEstimate();
//			Thread.sleep(7000);
//			Est.AddDuplicatecomponent();
//			Logger.info("------------------------AddCharacterist---------------------");
//
//			//select first component
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='diagram']/span[@class='diagram__item ps--item ps--cmp'][1]/label")));
//			String FromComp = DriverManager.getDriver().findElement(By.xpath("//span[@class='diagram']/span[@class='diagram__item ps--item ps--cmp'][1]/label")).getText();
//			Thread.sleep(7000);
//			String Character = Est.AddCharacteristics1(FromComp);
//			Logger.info(Character);
//			Logger.info("------------------------AddCharacterist Ends---------------------");
//			
//			Logger.info("------------------------CopyCharacteristic---------------------");
//			//select first component
//			//			String FromComp = DriverManager.getDriver().findElement(By.xpath("//span[@class='diagram']/span[@class='diagram__item ps--item ps--cmp'][1]/label")).getText();
//
//			//2nd component
//			String ToComp = DriverManager.getDriver().findElement(By.xpath("//span[@class='diagram']/span[@class='diagram__item ps--item ps--cmp'][2]/label")).getText();
//				
//			Est.CopyCharacteristic(Character,FromComp,ToComp);
//			Logger.info("Copy character Negative scenario");
//			
//			Est.CopyCharacteristic("Perfect Binding","Bound",ToComp);
//			Logger.info("------------------------CopyCharacteristic Ends---------------------");
//			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='diagram']/span[@class='diagram__item ps--item ps--product'][1]/label")));		
//			Est.AddDuplicatecomponentSet("Bound");
//
//			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='diagram__item ps--item ps--product'][2]")));
//
//			Logger.info("------------------------MoveFinalProductUp---------------------");	
//			Est.MoveFinalProductUp();
//			Logger.info("------------------------MoveFinalProductUp ENds---------------------");
//			
//			Logger.info("------------------------MoveFinalDown---------------------");
//			Est.MoveFinalProductDown();
//			Logger.info("------------------------MoveFinalDown Ends---------------------");
//			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='diagram']/span[@class='diagram__item ps--item ps--product'][1]/label")));
//			Logger.info("------------------------Verify adding new final prod into the estimate using the toolbar icon---------------------");
//			
//			Est.AddNewFinalProduct("Perfect Bind");
//			Logger.info("------------------------Verify adding new final prod into the estimate using the toolbar icon Ends---------------------");
//			Logger.info("------------------------Verify adding new repeated final prod into the estimate using the toolbar icon---------------------");
//			Est.AddRepeatedProduct();
//			Logger.info("------------------------Verify adding new repeated final prod into the estimate using the toolbar icon Ends---------------------");
//
//			Logger.info("------------------------AddAssociation ---------------------");
//			Est.addAssociation("Irreg.Association");
//			Logger.info("------------------------AddAssociation Ends---------------------");
//			Logger.info("------------------------Delete componentSet---------------------");
//			Est.DeletecomponentSet("Irreg.Association");
//			Logger.info("------------------------Delete componentSet Ends---------------------");
//			
//			
//			CFS.CloseTab("Estimate");
//			
//			Desktop.quickSearch("Estimate");
// 			
// 			Est.selectEstimate();
// 			Est.editEstimate();
// 			
// 			Est.NavigateToEngineeringTab();
// 			
//			Logger.info("-----------Verify zoom in/out using the toolbar icon--------------");
//			Eng.Zoom_out_Eng();
//			Eng.Zoom_in_Eng();
//			Logger.info("-----------Verify zoom in/out using the toolbar icon ENds--------------");
// 			CFS.CloseTab("Estimate");
// 				
// 			
//			
//		} catch (Exception e) {
//			throw e;
//		}
//		softAssert.assertAll();
//		AllureLogger.endTest();
//	}	
//}
