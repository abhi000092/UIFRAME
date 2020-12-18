//package com.testcases.demo;
//
//import static org.testng.Assert.assertTrue;
//
//import java.sql.SQLException;
//
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
//import com.github.javafaker.Faker;
//
//import base.Logger;
//import base.Testbase;
//import io.qameta.allure.Description;
//import io.qameta.allure.Owner;
//import io.qameta.allure.Severity;
//import io.qameta.allure.SeverityLevel;
//import pages.Desktop;
//import pages.Engineering;
//import pages.Estimate;
//import pages.Estimate.SearchAtrribute;
//import pages.IQuoteLogin;
//import pages.Identification;
//import pages.NewEstimate;
//import pages.Product;
//import pages.ProductionGroup;
//import utilities.AllureLogger;
//import utilities.CommonFunctions;
//
//@Listeners(utilities.ExtentListeners.class)
//@Owner(value="Madhuri Mishra")
//public class IQuote_9738_Coefficient extends Testbase{
//	CommonFunctions CFS;
//	Estimate Est;
//	Engineering Eng;
//	Identification Ident;
//	static Faker random;
//	NewEstimate NE;
//	ProductionGroup Prodg;
//	Product prd;
//	
//	
//	@BeforeMethod(alwaysRun = true)
//	public void init() {
//		CFS = new CommonFunctions();
//		Est = new Estimate();
//		Ident = new Identification(); 
//		NE=new NewEstimate();
//		random=new Faker();
//		Eng=new Engineering();
//	    Prodg=new ProductionGroup();
//	    prd=new Product();
//	    }
//
//	@BeforeMethod(alwaysRun = true)
//	@Severity(SeverityLevel.NORMAL)
//	@Description("login to Application")
//	public void logintoiquote() {
//
//		AllureLogger.startTest();
//		try {
//			IQuoteLogin.login(Config.getProperty("UserName"), Config.getProperty("Password"));
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		AllureLogger.endTest();
//
//		}
//	
//	@Test
//	@Severity(SeverityLevel.BLOCKER)
//	@Description("IQUOTE-9733:Coefficient #21: Validate the calculation for \"Passes (Press)\" Coefficient & filters")
//	public void ValidateCalculationForQtyOfPages() throws Exception, SQLException {
//		AllureLogger.startTest();
//		try {
//			//Substrate details
//			String subsType = CFS.readFromExcel(xlMasterData,"substype4text");
//			String subsLine = CFS.readFromExcel(xlMasterData,"subsline4text");
//			String subsGsm = CFS.readFromExcel(xlMasterData,"subsgsm4text"); 	
//			
//			
//			//Plant details
//			String ProdName = "Bound";
// 			String ProdSubLine = "Perfect Bind";
// 			String ProdSize = "5 X 7";
// 			String Quantity = "2500";
// 			String vComponent= "Text";
// 			String Filter_Desc="# Fold(s)";
// 			String Filter_Condition1="1";
// 			String Filter_Condition2="2";
// 			String Filter_Condition3="3";
// 			String Filter_Condition4="4";
// 			String Filter_Condition5=">=5";	
// 			String cofficient="Folds amount";
// 		    int rowNo=0;
// 		    
//			System.out.println("Step 1. Create an estimate for Bound");
//            //estimat1 perfect bound
//			Desktop.quickSearch("Estimate");
// 			Thread.sleep(2000);
// 			Estimate.ClickonNewEstimate();
// 			
// 			//Enter the prod spec
// 			NE.EnterIdentificationDetails("", ProdName, ProdSubLine, "", ProdSize, 
// 			Quantity);
//	 		softAssert.assertTrue(prd.selectComponent("Cover"));
//	 		Assert.assertTrue(prd.enterPaperDetails(subsType, subsLine, subsGsm));
//	 		Assert.assertTrue(prd.enterColors("4", "4"));
//	 		prd.addPrintTypeChar("Cover", "Print Type", "Sheetfed Offset Press");
//	 		softAssert.assertTrue(prd.selectComponent("Text"));
//	 		Assert.assertTrue(prd.enterPaperDetails(subsType, subsLine, subsGsm));
//	 		Assert.assertTrue(prd.enterColors("2", "2"));
//	 		prd.enterPageNumber("64");
//	 		prd.addPrintTypeChar("Text", "Print Type", "Sheetfed Offset Press");
//	 		Estimate.SaveEstimate();
//	 		
//	 		Assert.assertTrue(Estimate.CalculateEstimate(),"Estimate Calculation Failed");
//	         Estimate.SaveEstimate();
//	         Estimate.NavigateToNegotiationTab();
//	         Thread.sleep(2000);
//	       String newest=Estimate.getEstimateNumber();
//	       String  newest_withoutcomma=newest.replace(",", "");
//	       Est.NavigateToEngineeringTab();
//	       CFS.waitForPageLoaded();
//	       Eng.ToolBar_EyeOptionUncheck();
//	       CFS.waitForPageLoaded();
//	       Thread.sleep(2000);
//	       String [] vOptions= {"Components", "Activities","Production"};
//	       Eng.ToolBar_EyeOptionCheck(vOptions);
//	       CFS.pressKey("Tab");
//	       Thread.sleep(2000);
//	       CFS.waitForPageLoaded();
//	       String Production_Group1=Eng.getProductionGrouptName(vComponent, "Sheet-Fed");
//	       String Comp=Eng.getComponentTextUsingProdGroup(Production_Group1, "Text");
//	       CFS.CloseTab("Estimate");  
//	       CFS.waitForPageLoaded();
//	       Estimate.search(SearchAtrribute.EstimateNumber, newest);
//		   Est.editEstimate();
//		   Estimate.NavigateToEngineeringTab();
//		   CFS.waitForPageLoaded();
//	       String Production_Group=Eng.getProdGroupnNameFromDetails(Comp,"Fold");         
//	       if (Production_Group.isEmpty() || Production_Group.equals(""))
//		      {
//		    	  assertTrue(false, "Text Failed :'Fold' Production Group is not present in the engineering calculation");
//		      }
//	       CFS.CloseTab("Estimate");  
//	     //Navigate to production group
//	       System.out.println("Step 2. Add coefficient to sheetfed printing production group");
//	       
//	       Desktop.quickSearch("Production Group");
//	       Prodg.searchProductionGroup(Production_Group);
//	       Prodg.clickAdvSetting();
//	       Logger.info("Uncheck Interpolate Speed nd Interpolate production waste checkbox");
//	       Prodg.Navigate_toTab_prodgroup("Speed");
//	       Prodg.navigateToSpeedTable();
//	       Prodg.selectInterpolateSpeed("Uncheck");
//	       Prodg.saveChangesInSpeedTable();
//	       Prodg.Navigate_toTab_prodgroup("Producing Waste");
//	       Prodg.navigatetoProductionWasteTable();
//	       Prodg.selectInterpolateWaste("Uncheck");
//	       Prodg.saveChangesInSpeedTable();
//	       Prodg.save_close_prodGroup();
//	       Thread.sleep(2000);
//	       CFS.waitForPageLoaded();
//	       Logger.info(" Delete Created Filters And Activities if already exists");
//	       Prodg.searchProductionGroup(Production_Group);
//	       Prodg.clickAdvSetting();
//	       Prodg.Navigate_toTab_prodgroup("Filters");
//	       Prodg.deleteCreatedFilter(Filter_Desc); 
//	       Prodg.save_close_prodGroup();
//	       CFS.waitForPageLoaded();
//	       Prodg.searchProductionGroup(Production_Group);
//	       Prodg.clickAdvSetting();
//	       Prodg.Navigate_toTab_prodgroup("Units");
//	       Prodg.selectUOM("Control and Production", "Each");
//	       Prodg.selectCoefficientAndEnterDenominator("Control and Production", cofficient, "1");
//	       Prodg.selectUOM("Speed", "Each");
//	       Prodg.selectCoefficientAndEnterDenominator("Speed", cofficient, "1");
//	       Prodg.save_close_prodGroup();
//	     
//	       
//	       
//	       System.out.println("Step 3. Add filter conditions to proofing production group");
//		  
//	       Prodg.searchProductionGroup(Production_Group);
//		   Prodg.clickAdvSetting();
//		   Prodg.Navigate_toTab_prodgroup("Filters");
//		   Prodg.add_filter_inProductionGroup(Filter_Desc);
//		   Prodg.add_filterOptions_FilterTab(Filter_Condition1);
//		   Prodg.add_Condition_For_Filter_Selection("Pages", "equal to", "4");
//		   Thread.sleep(1000);
//		   Prodg.add_filterOptions_FilterTab(Filter_Condition2);
//		   Prodg.add_Condition_For_Filter_Selection("Pages", "equal to", "8");
//		   Thread.sleep(1000);
//		   Prodg.add_filterOptions_FilterTab(Filter_Condition3);
//		   Prodg.add_Condition_For_Filter_Selection("Pages", "equal to", "16");
//		   Thread.sleep(1000);
//		   Prodg.add_filterOptions_FilterTab(Filter_Condition4);
//		   Prodg.add_Condition_For_Filter_Selection("Pages", "equal to", "32");
//		   Thread.sleep(1000);
//		   Prodg.add_filterOptions_FilterTab(Filter_Condition5);
//		   Prodg.add_Condition_For_Filter_Selection("Pages", "greater or equal to", "64");
//		   Thread.sleep(1000);
//		   
//		   Prodg.save_close_prodGroup();
//		   Prodg.close_prodgroup_page();
//		   
//		   
//		   System.out.println("Step 4. Re-calculate the estimate");
//			
//		      CFS.waitForPageLoaded();	      
//
//		      Estimate.search(SearchAtrribute.EstimateNumber, newest);
//			  Est.editEstimate();
//			  Estimate.NavigateToEngineeringTab();
//			  Estimate.RecalculateEstimate();
//			  
//			  
//		   System.out.println("Step 5. Verify the production and speed for text comp sheetfed printing");
//			  
//			  Eng.ProdGroup_Options_Navigation(Comp, "Details");
//			  Eng.selectProductionGroupForDetails(Production_Group);
//			  Eng.clickOptionsBtn();
//			  Eng.verifyFilters(Filter_Desc, Filter_Condition1);
//			  
//			  System.out.println("Step 6. Verify the production and speed for text comp");
//			  
//			  rowNo=Eng.returnProdGroupRowNumberInDetails(Production_Group);
//			  String speedPerHour=Eng.getProductionGroupDetails(rowNo,Production_Group, "Speed (/h)");
//			  String goodsQty=Eng.getProductionGroupDetails(rowNo,Production_Group, "Goods Qty.");
//			  String goodsQtyFromDetails=goodsQty.replace(",", "");	
//			  double gdqty=Double.parseDouble(goodsQtyFromDetails);
//			  String goodsQtyFromNextProcess=Eng.getProductionGroupDetails(rowNo+1,Production_Group, "Good + Waste");
//			  String goodsQtyFromNextProcess1=goodsQtyFromNextProcess.replace(",", "");	
//			  double gdqty1=Double.parseDouble(goodsQtyFromNextProcess1);
//			  String runningTimeFromDetails=Eng.getProductionGroupDetails(rowNo,Production_Group, "Running Time");	  
//			  String setUpTimeFromDetails=Eng.getProductionGroupDetails(rowNo,Production_Group, "Setup Time / Rep");			 
//			  String fixedSetUpTimeFromDetails=Eng.getProductionGroupDetails(rowNo,Production_Group, "Fixed Setup Time");			  
//			  String totalTimeFromDetails=Eng.getProductionGroupDetails(rowNo,Production_Group, "Total Time");			  
//			  Eng.clickButtonsOnDetailsPopup("Functionalities");
//			  String NoOfFolds= Eng.getComponentDetails("Functionalities", "Description", "1 - Folder");
//			  Eng.clickOKButton();
//			  int s= NoOfFolds.indexOf(":");
//			  //int e=NoOfFolds.indexOf(")");
//			  double folds=Double.parseDouble(NoOfFolds.substring(s+1,NoOfFolds.length()-1));
//			 
//			  Eng.Exit_Details();
//			  CFS.CloseTab("Estimate");
//			  
//			  //get speed from speed table
//			   Desktop.quickSearch("Production Group");
//		       CFS.waitForPageLoaded();
//		       Prodg.searchProductionGroup(Production_Group);
//		       Prodg.clickAdvSetting();
//		       Prodg.Navigate_toTab_prodgroup("Speed");
//		       String speedFromSpeedTable=Prodg.getSpeedFromSpeedTable(goodsQty);
//		       String speedFromSpeedTable1=speedFromSpeedTable.replace(",", "");
//		       CFS.CloseTab("Speed Table");
//		       Prodg.close_prodgroup_page();
//		       
//		   String runningTimeCalculated=Eng.calculateRunningTimeInHRMMSSformat(speedFromSpeedTable1, goodsQtyFromDetails);
//		   String totalTimeCalculated=Eng.calculateTotalTimeinHHMMSSformat(runningTimeCalculated,setUpTimeFromDetails, fixedSetUpTimeFromDetails);
//		   Thread.sleep(2000);
//			  
//			  
//			  //speed verification
//			  if(speedPerHour.equals(speedFromSpeedTable))
//			    System.out.println("Test Passed: Speed per hour for cover component of production group "+Production_Group+" is correct");
//			  else
//				System.err.println("Test Failed: Speed per hour for cover component of production group "+Production_Group+" is incorrect");
//			
//			  //goods qty verification
//			  if(gdqty== gdqty1*folds)
//			    System.out.println("Test Passed: Goods Qty for cover component of production group "+Production_Group+" is correct");
//			  else
//				System.err.println("Test Failed: Goods Qty for cover component of production group "+Production_Group+" is incorrect");
//			  
//			  //running time verification 
//			  if(runningTimeFromDetails.equals(runningTimeCalculated))
//				System.out.println("Test Passed: Running time calculaion for cover component of production group "+Production_Group+" is correct");
//			  else
//				System.err.println("Test Failed: Running time calculation for cover component of production group "+Production_Group+" is incorrect");
//			  
//			  //total time verification
//			  if(totalTimeFromDetails.equals(totalTimeCalculated))
//			     System.out.println("Test Passed:Total time calculation for cover component of production group "+Production_Group+" is correct");
//			   else
//				 System.err.println("Test Failed:Total time calculation for cover component of production group "+Production_Group+" is incorrect");
//			  
//		
//		  System.out.println("Step 7. Delete Created Filters And Activities");
//		  Desktop.quickSearch("Production Group");
//		  Prodg.searchProductionGroup(Production_Group);
//		  Prodg.clickAdvSetting();
//		  Prodg.Navigate_toTab_prodgroup("Filters");
//		  Prodg.deleteCreatedFilter(Filter_Desc);
//		  Prodg.save_close_prodGroup();
//		  CFS.CloseTab("Production Group");
//		  
//		  
//				} catch (Exception e) {
//					throw e;
//				}
//			
//			softAssert.assertAll();
//			AllureLogger.endTest();
//		}
//		
//		
//}
