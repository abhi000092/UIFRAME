package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.aventstack.extentreports.Status;

import base.Testbase;
import io.qameta.allure.Step;

public class ReadData extends Testbase{
	public static Properties Query = new Properties();
	public static FileInputStream fis;
	
	
	public static HashMap<String, String> EstDetailsNewEstimate = new HashMap<String, String>();
	public static HashMap<String, HashMap<String, String>> EStCreateproductComp = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> ParentChildCombinationWithLinkData = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> EstQTYPage = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> EstCharacteristicEachComponent = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> EstDetailsPaperSpec = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, String> CharCPGraphBindStitch	 = new HashMap<String, String>();
	public static HashMap<String, String> CharCPGraphBindGlue	 = new HashMap<String, String>();
	public static HashMap<String, HashMap<String, String>> EstDetailsColorandVarnish = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> EstDetailsPaperFormat = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, HashMap<String, String>> CharCPGraphStampingItem = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, String> CPGenericCPOptionDesc	 = new HashMap<String, String>();
	public static HashMap<String, HashMap<String, String>> CharCPGraphGIrregFormat = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, String> CharCPGraphPrintType	 = new HashMap<String, String>();
	public static HashMap<String, String> CharCPGenericRawMaterial = new HashMap<String, String>();
	public static HashMap<String, String> CharCPGraphDieCut	 = new HashMap<String, String>();
	public static HashMap<String, String> CharCPAOptionQty	 = new HashMap<String, String>();
	public static HashMap<String, String> CharCPGraphHardCover	 = new HashMap<String, String>();
	public static HashMap<String, String> CharCPGenericCPOption	 = new HashMap<String, String>();
	public static HashMap<String, String> CharCPGraphPackBox	 = new HashMap<String, String>();
	public static HashMap<String, String> CharCPGraphLabelFormat	 = new HashMap<String, String>();
	public static HashMap<String, String> CPGraphPackagingStrapping      = new HashMap<String, String>();
	public static HashMap<String, String> CharCPFileList	 = new HashMap<String, String>();
	public static HashMap<String, String> CharCPGraphCollection = new HashMap<String, String>();
    public static HashMap<String, String> CharCPGraphBleed      = new HashMap<String, String>();
    
    public static HashMap<String, String> CharCPGraphWireOBind      = new HashMap<String, String>();
    public static HashMap<String, String> CharCPGraphPackPallet	 = new HashMap<String, String>();
    public static HashMap<String, String> CharCPGraphInitialLaminating      = new HashMap<String, String>();
    
    public static HashMap<String, String> CharCPGraphUnfinishedFormat = new HashMap<String, String>();

	public static HashMap<String, String> CharCPGraphLargeFormat	 = new HashMap<String, String>();
	public static HashMap<String, String> CharCPAGraphPageProof = new HashMap<String, String>();
	public static HashMap<String, String> CharCPGraphFiber	 = new HashMap<String, String>();
	public static HashMap<String, String> CharCPASimpleQty	 = new HashMap<String, String>();
	public static HashMap<String, String> CharCPvalueQTY = new HashMap<String, String>();
	public static HashMap<String, String> CharCPPlant	 = new HashMap<String, String>();
	public static HashMap<String, String> CharCPNote	 = new HashMap<String, String>();
	
	
	ResultSet rs,rs1;
	String OptionDesc=null;
	
	HashSet<String> Options=new HashSet<>();
	HashMap<String,HashSet<String>>Products=new HashMap<String,HashSet<String>>();
	HashMap<String,HashMap<String,HashSet<Double>>> Quantities= new HashMap<String,HashMap<String,HashSet<Double>>>();
		
	
	public HashMap<String, String> IdentificationPage(String EstimateId) throws IOException, ClassNotFoundException, SQLException
	{
		
		EstDetailsNewEstimate.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\IdentificationPage.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId));

		try
		{			
			while(rs.next())
			{
				String CustCode=rs.getString("CustomerCode");
				String CustName=rs.getString("CustomerName");
				String ConctName=rs.getString("ContactName");
				String CodeAgency=rs.getString("AgencyCode");
				String NameAgency=rs.getString("AgencyName");
				String Codesalesperson=rs.getString("SalesPersonCode");
				String NameSalesperson=rs.getString("SalesPersonName");
				String NameCsr=rs.getString("CSRName");
				String CodeProject=rs.getString("ProjectCode");
				String DescProject=rs.getString("ProjectDesc");
				String EstType=rs.getString("EstimateType");
				String EstTitle=rs.getString("EstimateTitle");
				String ProdLine=rs.getString("ProductLine");
				String VersionSpecification=rs.getString("VersionSpec");
				String ProductSubline=rs.getString("SublineProduct");
				String DlryType=rs.getString("DeliveryType");
				String TypeSales=rs.getString("SaleType");
				String Payterms=rs.getString("PaymentTerm");
				String RemurationGrp=rs.getString("RemunerationGroup");
				String Quantity=rs.getString("Qty");
				

				EstDetailsNewEstimate.put("CustomerCode", CustCode != null ? CustCode : "");
				EstDetailsNewEstimate.put("CustomerName", CustName != null ? CustName : "");
				EstDetailsNewEstimate.put("ContactName", ConctName != null ? ConctName : "");
				EstDetailsNewEstimate.put("AgencyCode", CodeAgency != null ? CodeAgency : "");
				EstDetailsNewEstimate.put("AgencyName", NameAgency != null ? NameAgency : "");
				EstDetailsNewEstimate.put("SalesPersonCode", Codesalesperson != null ? Codesalesperson : "");
				EstDetailsNewEstimate.put("SalesPersonName", NameSalesperson != null ? NameSalesperson : "");
				EstDetailsNewEstimate.put("CSRName", NameCsr != null ? NameCsr : "");
				EstDetailsNewEstimate.put("ProjectCode", CodeProject != null ? CodeProject : "");
				EstDetailsNewEstimate.put("ProjectDesc", DescProject != null ? DescProject : "");
				EstDetailsNewEstimate.put("EstimateType", EstType != null ? EstType : "");
				EstDetailsNewEstimate.put("EstimateTitle", EstTitle != null ? EstTitle : "");
				EstDetailsNewEstimate.put("ProductLine", ProdLine != null ? ProdLine : "");
				EstDetailsNewEstimate.put("VersionSpec", VersionSpecification != null ? VersionSpecification : "");
				EstDetailsNewEstimate.put("SublineProduct", ProductSubline != null ? ProductSubline : "");
				EstDetailsNewEstimate.put("DeliveryType", DlryType != null ? DlryType : "");
				EstDetailsNewEstimate.put("SaleType", TypeSales != null ? TypeSales : "");
				EstDetailsNewEstimate.put("PaymentTerm", Payterms != null ? Payterms : "");
				EstDetailsNewEstimate.put("RemunerationGroup", RemurationGrp != null ? RemurationGrp : "");
				EstDetailsNewEstimate.put("Qty", Quantity != null ? Quantity : "");
				
			}
			     
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}

		
		return EstDetailsNewEstimate;

	
	}
	
	public HashMap<String, HashMap<String, String>> CreateProductandComponents(String EstimateId) throws IOException, ClassNotFoundException, SQLException
	{
		EStCreateproductComp.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\CreateProductComponents.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId));
		try
		{
			
			while(rs.next())
			{

				HashMap<String, String> EstProductCreation = new HashMap<String, String>();

				String OptionDescp=rs.getString("OptionDescription");
				String QtyDescp=rs.getString("QtyDescription");
				String CompDescp=rs.getString("ComponentDescription");
				String CompOrder=rs.getString("ComponentOrder");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CompFinal=rs.getString("ComponentFinal");

				EstProductCreation.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstProductCreation.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				EstProductCreation.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				EstProductCreation.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				EstProductCreation.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				EstProductCreation.put("QtyDescription", QtyDescp != null ? QtyDescp : "");

				EStCreateproductComp.put(CompOrder, EstProductCreation);

			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}

		
		return EStCreateproductComp;

		
	}
	
	public HashMap<String, HashMap<String, String>> CreateProductandComponents(String EstimateId,String Option) throws IOException, ClassNotFoundException, SQLException
	{

		EStCreateproductComp.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CreateProductandComponents.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId).replace("##IdItemOption##", Option));
		try
		{
			
			while(rs.next())
			{

				HashMap<String, String> EstProductCreation = new HashMap<String, String>();

				String OptionDescp=rs.getString("OptionDescription");
				//String IdPSCmp=rs.getString("IdPSCmp");
				String CompDescp=rs.getString("ComponentDescription");
				String CompOrder=rs.getString("ComponentOrder");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CompFinal=rs.getString("ComponentFinal");

				EstProductCreation.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstProductCreation.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				EstProductCreation.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				EstProductCreation.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				EstProductCreation.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				//EstProductCreation.put("IdPSCmp", IdPSCmp != null ? IdPSCmp : "");

				EStCreateproductComp.put(CompOrder, EstProductCreation);

			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}

		
		return EStCreateproductComp;

		
	
	}
	public HashMap<String, HashMap<String, String>>ParentChildComponent(String Estimateid,String Option) throws IOException, ClassNotFoundException, SQLException
	{
		ParentChildCombinationWithLinkData.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\ParentChildCombination.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##IdItemOption##", Option));
		
		try
		{
			int keyval=1;
			while(rs.next())
			{

				HashMap<String, String> ProductChildLinkData = new HashMap<String, String>();
				String DBParentComponent=rs.getString("ParentCmp");
				String DBChildComponent=rs.getString("ChildCmp");
				String DBParentChildAggregationType=rs.getString("AggregationType");
				String DBParentChildIsIndependentProduction=rs.getString("IsIndependentProduction");
				String DBParentChildUses=rs.getString("Uses");
				String DBParentChildForEach=rs.getString("ForEach");
				String DBParentChildFixedDemand=rs.getString("FixedDemand");

				ProductChildLinkData.put("ParentComponent", DBParentComponent != null ? DBParentComponent : "");
				ProductChildLinkData.put("ChildComponent", DBChildComponent != null ? DBChildComponent : "");
				ProductChildLinkData.put("AggregationType", DBParentChildAggregationType != null ? DBParentChildAggregationType : "");
				ProductChildLinkData.put("IsIndependentProduction", DBParentChildIsIndependentProduction != null ? DBParentChildIsIndependentProduction : "");
				ProductChildLinkData.put("Uses", DBParentChildUses != null ? DBParentChildUses : "");
				ProductChildLinkData.put("ForEach", DBParentChildForEach != null ? DBParentChildForEach : "");
				ProductChildLinkData.put("FixedDemand", DBParentChildFixedDemand != null ? DBParentChildFixedDemand : "");
				

				ParentChildCombinationWithLinkData.put(keyval+"",ProductChildLinkData);
				++keyval;
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		
		return ParentChildCombinationWithLinkData;
	}

	public HashMap<String, HashMap<String, String>> QtyForEst(String EstimateId) throws SQLException, ClassNotFoundException, IOException
	{
		EstQTYPage.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\QuantityforEstimate.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId));
		
		try
		{
			
			while(rs.next())
			{

				HashMap<String, String> EstQtyPageData = new HashMap<String, String>();

				String OptionDescp=rs.getString("OptionDescription");
				String QtyEng=rs.getString("ENG");
				String QtyComponentName=rs.getString("ComponentName");
				String QTYPageQty=rs.getString("Qty");
				String QTYUnit=rs.getString("Unit");
				String QtyComponentId=rs.getString("ComponentID");

				EstQtyPageData.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstQtyPageData.put("ENG", QtyEng != null ? QtyEng : "");
				EstQtyPageData.put("ComponentName", QtyComponentName != null ? QtyComponentName : "");
				EstQtyPageData.put("Qty", QTYPageQty != null ? QTYPageQty : "");
				EstQtyPageData.put("Unit", QTYUnit != null ? QTYUnit : "");
				EstQtyPageData.put("ComponentID", QtyComponentId != null ? QtyComponentId : "");

				EstQTYPage.put(QtyComponentName, EstQtyPageData);

			
			}
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		
		return EstQTYPage;
	}
	public HashMap<Integer, HashMap<String, String>> QtyForEst(String EstimateId,String Option) throws IOException, ClassNotFoundException, SQLException
	{
		HashMap<Integer, HashMap<String, String>> EstQTYPage = new HashMap<Integer, HashMap<String, String>>();
		HashSet<Integer> HS=new HashSet<>();
		
		
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\QuantityForEstimate.properties");
		Query.load(fis);
		// opening database connection to MySQL server
		
		
		ResultSet rs;
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL serv
		
		//DBUtil iqdb=new DBUtil("jdbc:sqlserver://monarchqa18:1433;databaseName=iQuote", "sa", "efi@India");
		//System.out.println(Query1.replaceAll("##Estimate##", Config.getProperty("EstimateIDs")).replaceAll("##CompOrder##", "1"));
		//rs = iqdb.RunQuery(Query1.replaceAll("##Estimate##", Config.getProperty("EstimateIDs")).replaceAll("##CompOrder##", "1"));
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId).replace("##IdItemOption##", Option));
		while(rs.next()){
			
			

			String IdItemOptionQty=rs.getString("IdItemOptionQty");
			HS.add(Integer.parseInt(IdItemOptionQty));
			
		}
		System.out.println(HS);
		for(int h1:HS) {
			
			HashMap<String, String> EstQtyPageData = new HashMap<String, String>();
			 rs = iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId).replace("##IdItemOption##", Option)+" and  qttTMItemOptionQty.IdItemOptionQty= "+h1+"order by ComponentID desc");
	          
	            while(rs.next())
	            {
	            	
	            	String QtyComponentName=rs.getString("ComponentName");
					String QTYPageQty=rs.getString("Qty");
					EstQtyPageData.put(QtyComponentName, QTYPageQty);
					
	            }
	            EstQTYPage.put(h1, EstQtyPageData);
	            
	      }
		System.out.println(EstQTYPage);		
		return EstQTYPage;
		
	}
	public HashMap<String, HashMap<String, String>> CharacteristicForEachComponent(String Estimateid, String CompOrder) throws IOException, ClassNotFoundException, SQLException
	{
		EstCharacteristicEachComponent.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\CharacteristicsforEachComponent.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", CompOrder));
		
		try
		{
			
			while(rs.next())
			{

				//	String Quantity=rs.getString("Qty");
				HashMap<String, String> Chardescpval=new HashMap<String,String>();


				String OptionDescp=rs.getString("OptionDescription");
				String QuantityDescp=rs.getString("QtyDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder1=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String FixedCharDescp=rs.getString("FixedCharacteristicDesc");
				String CharDescp=rs.getString("CharacteristicDesc");

				Chardescpval.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				Chardescpval.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				Chardescpval.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				Chardescpval.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				Chardescpval.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				Chardescpval.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				Chardescpval.put("QtyDescription", QuantityDescp != null ? QuantityDescp : "");
				Chardescpval.put("FixedCharacteristicDesc", FixedCharDescp != null ? FixedCharDescp : "");

				EstCharacteristicEachComponent.put(CharDescp, Chardescpval);
				//System.out.println(Collections.singletonList(EstDetailsQty));
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		
		return EstCharacteristicEachComponent;
	}
	
	public HashMap<String, HashMap<String,String>> CharacteristicForEachComponent(String Estimateid, String Option,String CompOrder)throws IOException, ClassNotFoundException, SQLException{

		EstCharacteristicEachComponent.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsforEachComponent.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", CompOrder).replace("##IdItemOption##", Option));
		
		try
		{
			
			while(rs.next())
			{

				//	String Quantity=rs.getString("Qty");
				HashMap<String, String> Chardescpval=new HashMap<String,String>();


				String OptionDescp=rs.getString("OptionDescription");
				String IdPSCmp=rs.getString("IdPSCmp");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder1=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String FixedCharDescp=rs.getString("FixedCharacteristicDesc");
				String CharDescp=rs.getString("CharacteristicDesc");

				Chardescpval.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				Chardescpval.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				Chardescpval.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				Chardescpval.put("ComponentOrder", CompOrder1 != null ? CompOrder1 : "");
				Chardescpval.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				Chardescpval.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				Chardescpval.put("IdPSCmp", IdPSCmp != null ? IdPSCmp : "");
				Chardescpval.put("FixedCharacteristicDesc", FixedCharDescp != null ? FixedCharDescp : "");

				EstCharacteristicEachComponent.put(CharDescp, Chardescpval);
				//System.out.println(Collections.singletonList(EstDetailsQty));
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		
		return EstCharacteristicEachComponent;
	
	}
	public HashMap<String, HashMap<String, String>> PaperSpec(String EstimateId, String CompOrder) throws IOException, ClassNotFoundException, SQLException

	{
		EstDetailsPaperSpec.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\CharacteristicsforEachComponent.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId.replace("##ComponentOrder##", CompOrder)));
		try
		{
			int valkey=1;
			while(rs.next())
			{

				HashMap<String, String> EstColorandVarnish = new HashMap<String, String>();

				String OptionDescp=rs.getString("OptionDescription");
				String CompDescp=rs.getString("ComponentDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder1=rs.getString("ComponentOrder");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDesc=rs.getString("CharacteristicDesc");
				String CmpCarac=rs.getString("idPSCmpCarac");
				String PageSubstrateType=rs.getString("SubstrateType");
				String PageGrammage=rs.getString("Grammage");
				String PageLine=rs.getString("Line");
				String PageProvided=rs.getString("Provided");
				String Pageformatwidth=rs.getString("formatwidth");
				String Pageformatheight=rs.getString("formatheight");
				String PageGrainDirection=rs.getString("GrainDirection");
				String Pagemoreoptions=rs.getString("moreoptions");
				String PageNotes=rs.getString("Notes");
				String PageManufacturer=rs.getString("Manufacturer");
				String PageOtherManufacturer=rs.getString("OtherManufacturer");
				String PageSimulatesGenericSubstrate=rs.getString("SimulatesGenericSubstrate");

				EstColorandVarnish.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstColorandVarnish.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				EstColorandVarnish.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				EstColorandVarnish.put("ComponentOrder", CompOrder1 != null ? CompOrder1 : "");
				EstColorandVarnish.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				EstColorandVarnish.put("CharacteristicDesc", CharDesc != null ? CharDesc : "");
				EstColorandVarnish.put("idPSCmpCarac", CmpCarac != null ? CmpCarac : "");
				EstColorandVarnish.put("SubstrateType", PageSubstrateType != null ? PageSubstrateType : "");
				EstColorandVarnish.put("Grammage", PageGrammage != null ? PageGrammage : "");
				EstColorandVarnish.put("Line", PageLine != null ? PageLine : "");
				EstColorandVarnish.put("Notes", PageNotes != null ? PageNotes : "");
				EstColorandVarnish.put("Provided", PageProvided != null ? PageProvided : "");
				EstColorandVarnish.put("formatwidth", Pageformatwidth != null ? Pageformatwidth : "");
				EstColorandVarnish.put("formatheight", Pageformatheight != null ? Pageformatheight : "");
				EstColorandVarnish.put("GrainDirection", PageGrainDirection != null ? PageGrainDirection : "");

				EstColorandVarnish.put("moreoptions", Pagemoreoptions != null ? Pagemoreoptions : "");
				EstColorandVarnish.put("Manufacturer", PageManufacturer != null ? PageManufacturer : "");
				EstColorandVarnish.put("OtherManufacturer", PageOtherManufacturer != null ? PageOtherManufacturer : "");
				EstColorandVarnish.put("SimulatesGenericSubstrate", PageSimulatesGenericSubstrate != null ? PageSimulatesGenericSubstrate : "");


				EstDetailsPaperSpec.put(CompOrder1, EstColorandVarnish);
				++valkey;
			
			}
			
		}
		catch(SQLException  e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		
		return EstDetailsPaperSpec;
		
	}

	public HashMap<String, String> CPGraphBindStitch(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws ClassNotFoundException, SQLException, IOException

	{
		CharCPGraphBindStitch.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphBindStitch.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		try
		{
			//loop
			while(rs.next())
			{
				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");


				String CPGraphBindStitchQuantityOfWireStitches=rs.getString("QuantityOfWireStitches");
				String CPGraphBindStitchNote=rs.getString("Note");
				String CPGraphBindStitchWireStichWidth=rs.getString("WireStichWidth");
				String CPGraphBindStitchApplication1=rs.getString("Application1");
				String CPGraphBindStitchShape=rs.getString("Shape");



				CharCPGraphBindStitch.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphBindStitch.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphBindStitch.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphBindStitch.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGraphBindStitch.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGraphBindStitch.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphBindStitch.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");


				CharCPGraphBindStitch.put("QuantityOfWireStitches", CPGraphBindStitchQuantityOfWireStitches != null ? CPGraphBindStitchQuantityOfWireStitches : "");
				CharCPGraphBindStitch.put("Note", CPGraphBindStitchNote != null ? CPGraphBindStitchNote : "");
				CharCPGraphBindStitch.put("WireStichWidth", CPGraphBindStitchWireStichWidth != null ? CPGraphBindStitchWireStichWidth : "");
				CharCPGraphBindStitch.put("Application1", CPGraphBindStitchApplication1 != null ? CPGraphBindStitchApplication1 : "");
				CharCPGraphBindStitch.put("Shape", CPGraphBindStitchShape != null ? CPGraphBindStitchShape : "");

			}
			
		}
		catch(SQLException  e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		
		return CharCPGraphBindStitch;
		
	}

	public HashMap<String,HashMap<String, HashSet<String>>> QuantityPage(String string,String Estimate) throws ClassNotFoundException, SQLException, IOException{
		Set<String> Options=new HashSet<>();
		HashSet<String> Product=new HashSet<>();
		HashSet<String> Quantity=new HashSet<>();
		//HashMap<String,String> Quantity = new HashMap<>();
		HashMap<String, HashSet<String>> Products = new HashMap<String, HashSet<String> >();
		HashMap<String,HashMap<String, HashSet<String>>> ProQty=new HashMap<String,HashMap<String, HashSet<String>>>();
		HashMap<String,HashMap<String,HashMap<String,String>>> Quantities=new HashMap<String,HashMap<String,HashMap<String,String>>>();
		
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Quantities.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimate));
		try {
			//loop
			while(rs.next())
			{
				
				String Option=rs.getString("OptionDescription");
				String Product1=rs.getString("ComponentName");
				String Quantity1=rs.getString("Qty");
				
				/*Options.add(Option);
				Product.add(Product1);
				Quantity.put(Product1, Quantity1);
				ProQty.put(Product1, Quantity);
				Products.put(Option, Quantity);
				Quantities.put(Option, ProQty);*/
				Quantity.add(Quantity1);
				Products.put(Product1, Quantity);
				ProQty.put(Option, Products);
				//Quantity.clear();
				//Products.clear();
				
				
				
				
				
				
			}
		}
		catch(SQLException e)
		{
			
			System.out.println("failed");
		}
		
		return ProQty;
	}
	@Step("Number of options for estimateID  : {0}")
	public HashSet<String> NoOfOptions(String EstimateId) throws IOException, ClassNotFoundException, SQLException{
		Options.clear();
		
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\QuotationUniqueOptions.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId));
		
		try
		{
			while(rs.next()){
				String IdItemOption=rs.getString("IdItemOption");
				Options.add(IdItemOption);
			}
			
		}
		catch(SQLException  e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		
		return Options;
	}
	
	public HashMap<String,HashSet<String>> NoOfProductsForOption(String Optionname,String Estimate) throws IOException, ClassNotFoundException, SQLException{
		Products.clear();
		HashSet<String> Product=new HashSet<>();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Quantities.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		String Query2=Query1.replace("##Estimate##", Estimate)+"and qttTMItemOption.Description= '"+Optionname+"'";
		rs = iqdb.RunQuery(Query2);
		
		try
		{
			while(rs.next()) {
				String pro=rs.getString("ComponentName");
				Product.add(pro);
				Products.put(Optionname, Product);
			}
		}
		catch(SQLException  e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		
		
		return Products;
		
	}
	
	public String ReturnOptionDesForEstandOption(String Estimate,String Option) throws IOException, ClassNotFoundException, SQLException {
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\GetOptionDesc.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimate).replace("##IdItemOption##", Option));
		while(rs.next()) {
			OptionDesc=rs.getString("OptionDescription");
		}
		return OptionDesc;
		
	}
	public HashMap<String,HashMap<String,HashSet<Double>>> NoOfQtyForProductForOption(String Product,String Optionname,String Estimate) throws IOException, ClassNotFoundException, SQLException{
		Quantities.clear();
		HashSet<Double> Quantity=new HashSet<>();
		HashMap<String,HashSet<Double>> Product1 = new HashMap<String,HashSet<Double>> ();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Quantities.properties");
		Query.load(fis);
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		String Query2=Query1.replace("##Estimate##", Estimate)+"and qttTMItemOption.Description= '"+Optionname+"'"+"and qttTMPSCmp.Description= '"+Product+"'";
		rs = iqdb.RunQuery(Query2);
		
		try
		{
			while(rs.next()) {
				String qty=rs.getString("Qty");
				double convertedNumber = Double.parseDouble(qty);
				Quantity.add(convertedNumber);
				String pro=rs.getString("ComponentName");
				Product1.put(pro, Quantity);
				String opt=rs.getString("OptionDescription");
				Quantities.put(opt, Product1);
				
			}
			
		}catch(SQLException  e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		
		
		return Quantities;
	}

	public HashMap<String, HashMap<String, String>> CPGraphColorVanish(String EstimateId, String IdItemOption ,String CompOrderval) throws IOException, SQLException, ClassNotFoundException{
		EstDetailsColorandVarnish.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphColorVanish.properties");
		Query.load(fis);
		//System.out.println("To remove");
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs1 = iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId).replace("##CompOrder##", CompOrderval).replace("##IdItemOption##", IdItemOption));
		try
		{
			int valkey1=1;
			while(rs1.next()) {

				HashMap<String, String> EstColorandVarnish = new HashMap<String, String>();

				String OptionDescp=rs1.getString("OptionDescription");
				String CompDescp=rs1.getString("ComponentDescription");
				String CompFinal=rs1.getString("ComponentFinal");
				String CompOrder=rs1.getString("ComponentOrder");
				String CompTypeDescp=rs1.getString("ComponentTypeDesc");
				String CharDesc=rs1.getString("CharacteristicDesc");
				String PageColorOrVarnish=rs1.getString("ColorOrVarnish");
				String PageFrontOrBack=rs1.getString("FrontOrBack");
				String PageMainColor=rs1.getString("MainColor");
				String PageColor=rs1.getString("Color");
				String PageNotes=rs1.getString("Notes");
				String PagePrintInput=rs1.getString("PrintInput");
				String PageAssociation=rs1.getString("Association");
				String PageCoverage=rs1.getString("Coverage");
				String PageAdditionalPlate=rs1.getString("AdditionalPlate");




				EstColorandVarnish.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstColorandVarnish.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				EstColorandVarnish.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				EstColorandVarnish.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				EstColorandVarnish.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				EstColorandVarnish.put("CharacteristicDesc", CharDesc != null ? CharDesc : "");
				EstColorandVarnish.put("ColorOrVarnish", PageColorOrVarnish != null ? PageColorOrVarnish : "");
				EstColorandVarnish.put("FrontOrBack", PageFrontOrBack != null ? PageFrontOrBack : "");
				EstColorandVarnish.put("MainColor", PageMainColor != null ? PageMainColor : "");
				EstColorandVarnish.put("Color", PageColor != null ? PageColor : "");
				EstColorandVarnish.put("Notes", PageNotes != null ? PageNotes : "");
				EstColorandVarnish.put("PrintInput", PagePrintInput != null ? PagePrintInput : "");
				EstColorandVarnish.put("Association", PageAssociation != null ? PageAssociation : "");
				EstColorandVarnish.put("Coverage", PageCoverage != null ? PageCoverage : "");
				EstColorandVarnish.put("AdditionalPlate", PageAdditionalPlate != null ? PageAdditionalPlate : "");

				EstDetailsColorandVarnish.put(valkey1+"", EstColorandVarnish);
				++valkey1;
			
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		
		return EstDetailsColorandVarnish;
		
	}
	
	public HashMap<String, HashMap<String, String>> CPGraphRegularFormat(String EstimateId, String IdItemOption,String CompOrder ) throws SQLException, ClassNotFoundException, IOException{
		EstDetailsPaperFormat.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphRegularFormat.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId).replace("##CompOrder##", CompOrder).replace("##IdItemOption##", IdItemOption));
		try
		{
			while(rs.next()) {

				HashMap<String, String> EstColorandVarnish = new HashMap<String, String>();

				String OptionDescp=rs.getString("OptionDescription");
				String CompDescp=rs.getString("ComponentDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder1=rs.getString("ComponentOrder");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDesc=rs.getString("CharacteristicDesc");
				String CmpCarac=rs.getString("idPSCmpCarac");
				String PageFinishedFormatWidth=rs.getString("FinishedFormatWidth");
				String PageFinishedFormatHeight=rs.getString("FinishedFormatHeight");
				String PageFormat=rs.getString("Page");
				String PageBothSheetsEqual=rs.getString("BothSheetsEqual");
				String PageLeftFlap=rs.getString("LeftFlap");
				String PageRightFlap=rs.getString("RightFlap");
				String PageFlatWidth=rs.getString("FlatWidth");
				String PageFlatHeight=rs.getString("FlatHeight");
				String PageFlapRetreat=rs.getString("FlapRetreat");
				String PageFoldingSchemeCode=rs.getString("FoldingSchemeCode");
				String PageFoldingSchemeDescription=rs.getString("FoldingSchemeDescription");
				String PageLines=rs.getString("Lines");
				String PageLabelsacross=rs.getString("Labelsacross");
				String PageBleed=rs.getString("Bleed");
				String PageArrangement=rs.getString("Arrangement");
				String PageApplicationType=rs.getString("ApplicationType");
				String PageTopMargin=rs.getString("TopMargin");
				String PageBottomMargin=rs.getString("BottomMargin");
				String PageLeftMargin=rs.getString("LeftMargin");
				String PageRightMargin=rs.getString("RightMargin");
				String PageAllowsWhiteElementWasteInTheLayout=rs.getString("AllowsWhiteElementWasteInTheLayout");
				String PageQuantityOfVersions=rs.getString("QuantityOfVersions");
				String PagePosition=rs.getString("Position");
				String PageSize=rs.getString("Size");

				EstColorandVarnish.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstColorandVarnish.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				EstColorandVarnish.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				EstColorandVarnish.put("ComponentOrder", CompOrder1 != null ? CompOrder1 : "");
				EstColorandVarnish.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				EstColorandVarnish.put("CharacteristicDesc", CharDesc != null ? CharDesc : "");
				EstColorandVarnish.put("idPSCmpCarac", CmpCarac != null ? CmpCarac : "");
				EstColorandVarnish.put("FinishedFormatWidth", PageFinishedFormatWidth != null ? PageFinishedFormatWidth : "");
				EstColorandVarnish.put("FinishedFormatHeight", PageFinishedFormatHeight != null ? PageFinishedFormatHeight : "");
				EstColorandVarnish.put("Line", PageLines != null ? PageLines : "");
				EstColorandVarnish.put("Page", PageFormat != null ? PageFormat : "");
				EstColorandVarnish.put("BothSheetsEqual", PageBothSheetsEqual != null ? PageBothSheetsEqual : "");
				EstColorandVarnish.put("LeftFlap", PageLeftFlap != null ? PageLeftFlap : "");
				EstColorandVarnish.put("RightFlap", PageRightFlap != null ? PageRightFlap : "");
				EstColorandVarnish.put("FlatWidth", PageFlatWidth != null ? PageFlatWidth : "");
				EstColorandVarnish.put("FlatHeight", PageFlatHeight != null ? PageFlatHeight : "");
				EstColorandVarnish.put("FlapRetreat", PageFlapRetreat != null ? PageFlapRetreat : "");
				EstColorandVarnish.put("FoldingSchemeCode", PageFoldingSchemeCode != null ? PageFoldingSchemeCode : "");
				EstColorandVarnish.put("FoldingSchemeDescription", PageFoldingSchemeDescription != null ? PageFoldingSchemeDescription : "");
				EstColorandVarnish.put("Lines", PageLines != null ? PageLines : "");
				EstColorandVarnish.put("Labelsacross", PageLabelsacross != null ? PageLabelsacross : "");
				EstColorandVarnish.put("Bleed", PageBleed != null ? PageBleed : "");
				EstColorandVarnish.put("Arrangement", PageArrangement != null ? PageArrangement : "");
				EstColorandVarnish.put("ApplicationType", PageApplicationType != null ? PageApplicationType : "");
				EstColorandVarnish.put("TopMargin", PageTopMargin != null ? PageTopMargin : "");
				EstColorandVarnish.put("BottomMargin", PageBottomMargin != null ? PageBottomMargin : "");
				EstColorandVarnish.put("LeftMargin", PageLeftMargin != null ? PageLeftMargin : "");
				EstColorandVarnish.put("RightMargin", PageRightMargin != null ? PageRightMargin : "");
				EstColorandVarnish.put("AllowsWhiteElementWasteInTheLayout", PageAllowsWhiteElementWasteInTheLayout != null ? PageAllowsWhiteElementWasteInTheLayout : "");
				EstColorandVarnish.put("QuantityOfVersions", PageQuantityOfVersions != null ? PageQuantityOfVersions : "");
				EstColorandVarnish.put("Position", PagePosition != null ? PagePosition : "");
				EstColorandVarnish.put("Size", PageSize != null ? PageSize : "");



				EstDetailsPaperFormat.put(CompOrder1, EstColorandVarnish);

			
			}
			
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}

		
		return EstDetailsPaperFormat;
	}
	
	public HashMap<String, HashMap<String, String>> CPGraphHotStamping(String Estimateid, String IdItemOption, String CompOrder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException{
		CharCPGraphStampingItem.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphHotStamping.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", CompOrder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		try
		{
			int valkey=1;
			HashMap<String, String> CharCPGraphStampingItemHM=new HashMap<String,String>(); 
			while(rs.next()) {

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder1=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");

				String CPGraphStampingItemType=rs.getString("Type");
				String CPGraphStampingItemHeight=rs.getString("Height");
				String CPGraphStampingItemWidth=rs.getString("Width");
				String CPGraphStampingItemSurface=rs.getString("Surface");
				String CPGraphStampingItemInputNumber=rs.getString("InputNumber");
				String CPGraphStampingItemNote=rs.getString("Note");



				CharCPGraphStampingItemHM.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphStampingItemHM.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphStampingItemHM.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphStampingItemHM.put("ComponentOrder", CompOrder1 != null ? CompOrder1 : "");
				CharCPGraphStampingItemHM.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGraphStampingItemHM.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphStampingItemHM.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");


				CharCPGraphStampingItemHM.put("Type", CPGraphStampingItemType != null ? CPGraphStampingItemType : "");
				CharCPGraphStampingItemHM.put("Height", CPGraphStampingItemHeight != null ? CPGraphStampingItemHeight : "");
				CharCPGraphStampingItemHM.put("Width", CPGraphStampingItemWidth != null ? CPGraphStampingItemWidth : "");
				CharCPGraphStampingItemHM.put("Surface", CPGraphStampingItemSurface != null ? CPGraphStampingItemSurface : "");
				CharCPGraphStampingItemHM.put("InputNumber", CPGraphStampingItemInputNumber != null ? CPGraphStampingItemInputNumber : "");
				CharCPGraphStampingItemHM.put("Note", CPGraphStampingItemNote != null ? CPGraphStampingItemNote : "");

				CharCPGraphStampingItem.put(valkey+"", CharCPGraphStampingItemHM);
				++valkey;
			
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		
		return CharCPGraphStampingItem;
	}
	
	public HashMap<String, HashMap<String, String>> CPGraphMedia(String EstimateId,  String IdItemOption,String CompOrder) throws IOException, ClassNotFoundException, SQLException{
		EstDetailsPaperSpec.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphMedia.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", EstimateId).replace("##ComponentOrder##", CompOrder).replace("##IdItemOption##", IdItemOption));
		try
		{
			int valkey=1;
			while(rs.next()) {

				HashMap<String, String> EstColorandVarnish = new HashMap<String, String>();

				String OptionDescp=rs.getString("OptionDescription");
				String CompDescp=rs.getString("ComponentDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder1=rs.getString("ComponentOrder");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDesc=rs.getString("CharacteristicDesc");
				String CmpCarac=rs.getString("idPSCmpCarac");
				String PageSubstrateType=rs.getString("SubstrateType");
				String PageGrammage=rs.getString("Grammage");
				String PageLine=rs.getString("Line");
				String PageProvided=rs.getString("Provided");
				String Pageformatwidth=rs.getString("formatwidth");
				String Pageformatheight=rs.getString("formatheight");
				String PageGrainDirection=rs.getString("GrainDirection");
				String Pagemoreoptions=rs.getString("moreoptions");
				String PageNotes=rs.getString("Notes");
				String PageManufacturer=rs.getString("Manufacturer");
				String PageOtherManufacturer=rs.getString("OtherManufacturer");
				String PageSimulatesGenericSubstrate=rs.getString("SimulatesGenericSubstrate");


				EstColorandVarnish.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				EstColorandVarnish.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				EstColorandVarnish.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				EstColorandVarnish.put("ComponentOrder", CompOrder1 != null ? CompOrder1 : "");
				EstColorandVarnish.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				EstColorandVarnish.put("CharacteristicDesc", CharDesc != null ? CharDesc : "");
				EstColorandVarnish.put("idPSCmpCarac", CmpCarac != null ? CmpCarac : "");
				EstColorandVarnish.put("SubstrateType", PageSubstrateType != null ? PageSubstrateType : "");
				EstColorandVarnish.put("Grammage", PageGrammage != null ? PageGrammage : "");
				EstColorandVarnish.put("Line", PageLine != null ? PageLine : "");
				EstColorandVarnish.put("Notes", PageNotes != null ? PageNotes : "");
				EstColorandVarnish.put("Provided", PageProvided != null ? PageProvided : "");
				EstColorandVarnish.put("formatwidth", Pageformatwidth != null ? Pageformatwidth : "");
				EstColorandVarnish.put("formatheight", Pageformatheight != null ? Pageformatheight : "");
				EstColorandVarnish.put("GrainDirection", PageGrainDirection != null ? PageGrainDirection : "");

				EstColorandVarnish.put("moreoptions", Pagemoreoptions != null ? Pagemoreoptions : "");
				EstColorandVarnish.put("Manufacturer", PageManufacturer != null ? PageManufacturer : "");
				EstColorandVarnish.put("OtherManufacturer", PageOtherManufacturer != null ? PageOtherManufacturer : "");
				EstColorandVarnish.put("SimulatesGenericSubstrate", PageSimulatesGenericSubstrate != null ? PageSimulatesGenericSubstrate : "");


				EstDetailsPaperSpec.put(CompOrder1, EstColorandVarnish);
				++valkey;
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}


		return EstDetailsPaperSpec;
	}

	public HashMap<String, String> CPPlant(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException
	{
		CharCPPlant.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPPlant.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next()) {

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");

				String CPPlantPlant=rs.getString("Plant");
										
			
				CharCPPlant.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPPlant.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPPlant.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPPlant.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPPlant.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPPlant.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPPlant.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");

				CharCPPlant.put("Plant", CPPlantPlant != null ? CPPlantPlant : "");
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPPlant;
	}
	public HashMap<String, String> CPGenericCPOptionDesc(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException
	{
		CPGenericCPOptionDesc.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGenericCPOptionDesc.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next()) {

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");
				String CPGenericCPOptionDescription=rs.getString("Description");
				String CPGenericCPOptionOptions=rs.getString("Options");
				String CPGenericCPOptionQuantity=rs.getString("Quantity");





				CPGenericCPOptionDesc.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CPGenericCPOptionDesc.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CPGenericCPOptionDesc.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CPGenericCPOptionDesc.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CPGenericCPOptionDesc.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CPGenericCPOptionDesc.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CPGenericCPOptionDesc.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");
				CPGenericCPOptionDesc.put("Description", CPGenericCPOptionDescription != null ? CPGenericCPOptionDescription : "");
				CPGenericCPOptionDesc.put("Options", CPGenericCPOptionOptions != null ? CPGenericCPOptionOptions : "");
				CPGenericCPOptionDesc.put("Quantity", CPGenericCPOptionQuantity != null ? CPGenericCPOptionQuantity : "");


			
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CPGenericCPOptionDesc;
		
	}
	public HashMap<String, String> CPNote(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException
	{
		CharCPNote.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPNote.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next()) {

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");
				String CharCPNoteGraphNote=rs.getString("Note");



				CharCPNote.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPNote.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPNote.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPNote.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPNote.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPNote.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPNote.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");
				CharCPNote.put("Note", CharCPNoteGraphNote != null ? CharCPNoteGraphNote : "");

			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPNote;
		
	}
	public HashMap<String, String> CPAGraphPageProof(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException{
		CharCPAGraphPageProof.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPAGraphPageProof.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next()) {

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");
				String CPGraphDieCutQuantity=rs.getString("Quantity");
				String CPGraphDieCutNotes=rs.getString("Notes");
				String CPGraphDieCutWidth=rs.getString("Width");
				String CPGraphDieCutHeight=rs.getString("Height");
				String CPGraphDieCutDefinedPages=rs.getString("DefinedPages");
				String CPGraphDieCutDefinedFormat=rs.getString("DefinedFormat");


				CharCPAGraphPageProof.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPAGraphPageProof.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPAGraphPageProof.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPAGraphPageProof.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPAGraphPageProof.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPAGraphPageProof.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPAGraphPageProof.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");
				CharCPAGraphPageProof.put("Quantity", CPGraphDieCutQuantity != null ? CPGraphDieCutQuantity : "");
				CharCPAGraphPageProof.put("Notes", CPGraphDieCutNotes != null ? CPGraphDieCutNotes : "");
				CharCPAGraphPageProof.put("Width", CPGraphDieCutWidth != null ? CPGraphDieCutWidth : "");
				CharCPAGraphPageProof.put("Height", CPGraphDieCutHeight != null ? CPGraphDieCutHeight : "");
				CharCPAGraphPageProof.put("DefinedPages", CPGraphDieCutDefinedPages != null ? CPGraphDieCutDefinedPages : "");
				CharCPAGraphPageProof.put("DefinedFormat", CPGraphDieCutDefinedFormat != null ? CPGraphDieCutDefinedFormat : "");

			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPAGraphPageProof;
	
	}
	public HashMap<String, String> CPGraphUnfinishedFormat(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException
	{
		CharCPGraphUnfinishedFormat.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphUnfinishedFormat.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		try
		{
			while(rs.next())
			{
				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");

				String CPGraphWidth=rs.getString("Width");
				String CPGraphHeight=rs.getString("Height");
				String CPGraphThickness=rs.getString("Thickness");
				String CPGraphWeight=rs.getString("Weight");
				String CPGraphInventoryItemCode=rs.getString("InventoryItemCode");
				String CPGraphInventoryItemDescription=rs.getString("InventoryItemDescription");
				String CPGraphQuantity=rs.getString("Quantity");
				String CPGraphNote=rs.getString("Note");


				CharCPGraphUnfinishedFormat.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphUnfinishedFormat.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphUnfinishedFormat.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphUnfinishedFormat.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGraphUnfinishedFormat.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGraphUnfinishedFormat.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphUnfinishedFormat.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");

				CharCPGraphUnfinishedFormat.put("Width", CPGraphWidth != null ? CPGraphWidth : "");
				CharCPGraphUnfinishedFormat.put("Height", CPGraphHeight != null ? CPGraphHeight : "");
				CharCPGraphUnfinishedFormat.put("Thickness", CPGraphThickness != null ? CPGraphThickness : "");
				CharCPGraphUnfinishedFormat.put("Weight", CPGraphWeight != null ? CPGraphWeight : "");
				CharCPGraphUnfinishedFormat.put("InventoryItemCode", CPGraphInventoryItemCode != null ? CPGraphInventoryItemCode : "");
				CharCPGraphUnfinishedFormat.put("InventoryItemDescription", CPGraphInventoryItemDescription != null ? CPGraphInventoryItemDescription : "");
				CharCPGraphUnfinishedFormat.put("Quantity", CPGraphQuantity != null ? CPGraphQuantity : "");
				CharCPGraphUnfinishedFormat.put("Note", CPGraphNote != null ? CPGraphNote : "");

			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGraphUnfinishedFormat;
	}
	
	public HashMap<String, String> CPGraphInitialLaminating(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws ClassNotFoundException, IOException, SQLException
	{
		CharCPGraphInitialLaminating.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphInitialLaminating.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next())
			{
				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");

				String CPGraphInitialLaminatingFront=rs.getString("Front");
				String CPGraphInitialLaminatingBack=rs.getString("Back");
				String CPGraphInitialLaminatingNote=rs.getString("Note");
				String CPGraphInitialLaminatingRollWidthFront=rs.getString("RollWidthFront");
				String CPGraphInitialLaminatingRollWidthBack=rs.getString("RollWidthBack");



				CharCPGraphInitialLaminating.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphInitialLaminating.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphInitialLaminating.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphInitialLaminating.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGraphInitialLaminating.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGraphInitialLaminating.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphInitialLaminating.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");


				CharCPGraphInitialLaminating.put("Front", CPGraphInitialLaminatingFront != null ? CPGraphInitialLaminatingFront : "");
				CharCPGraphInitialLaminating.put("Back", CPGraphInitialLaminatingBack != null ? CPGraphInitialLaminatingBack : "");
				CharCPGraphInitialLaminating.put("Note", CPGraphInitialLaminatingNote != null ? CPGraphInitialLaminatingNote : "");
				CharCPGraphInitialLaminating.put("RollWidthFront", CPGraphInitialLaminatingRollWidthFront != null ? CPGraphInitialLaminatingRollWidthFront : "");
				CharCPGraphInitialLaminating.put("RollWidthBack", CPGraphInitialLaminatingRollWidthBack != null ? CPGraphInitialLaminatingRollWidthBack : "");

			}

		}

		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGraphInitialLaminating;
	}
	public HashMap<String, String> CPGraphHardCover(String Estimateid, String IdItemOption,String componentorder, String CharacteristicDescp) throws ClassNotFoundException, SQLException, IOException{
		CharCPGraphHardCover.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphHardCover.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL serv
		
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next()) {

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				//	String CharDescp=rs.getString("CharacteristicDesc");
				String CPGraphHardCoverGlueType=rs.getString("GlueType");
				String CPGraphHardCoverSewn=rs.getString("Sewn");
				String CPGraphHardPerfectBindRound=rs.getString("PerfectBindRound");
				String CPGraphHardCoverCardboard=rs.getString("CoverCardboard");
				String CPGraphHardNote=rs.getString("Note");
				String CPGraphHardSpineCardboard=rs.getString("SpineCardboard");
				String CPGraphHardJointGap=rs.getString("JointGap");
				String CPGraphHardChangeDefaultFoldOver=rs.getString("ChangeDefaultFoldOver");
				String CPGraphHardHeadadjust=rs.getString("Headadjust");
				String CPGraphHardFaceadjust=rs.getString("Faceadjust");
				String CPGraphHardFootadjust=rs.getString("Footadjust");
				String CPGraphHardFoldOverMargin=rs.getString("FoldOverMargin");
				String CPGraphHardBackingMaterial=rs.getString("BackingMaterial");
				String CPGraphHardHasheadband=rs.getString("Hasheadband");
				String CPGraphHardHeadbandLiner=rs.getString("HeadbandLiner");
				String CPGraphHardHeadbandColor=rs.getString("HeadbandColor");
				String CPGraphHardNumberofribbons=rs.getString("Numberofribbons");
				String CPGraphHardSeparateCover=rs.getString("SeparateCover");
				String CPGraphHardCoverWidthReduction=rs.getString("CoverWidthReduction");
				String CPGraphHardSubstrateType=rs.getString("SubstrateType");
				String CPGraphHardLine=rs.getString("Line");
				String CPGraphHardGrammage=rs.getString("Grammage");
				String CPGraphHardGrainDirection=rs.getString("GrainDirection");
				String CPGraphHardBookOverlapTop=rs.getString("BookOverlapTop");
				String CPGraphHardBookOverlapBottom=rs.getString("BookOverlapBottom");
				String CPGraphHardBookOverlapRight=rs.getString("BookOverlapRight");
				String CPGraphHardManufacturer=rs.getString("Manufacturer");
				String CPGraphHardAnotherManufacturer=rs.getString("AnotherManufacturer");
				String CPGraphHardNotes=rs.getString("Notes");


				CharCPGraphHardCover.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphHardCover.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphHardCover.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphHardCover.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGraphHardCover.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				//CharCPGraphHardCover.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphHardCover.put("GlueType", CPGraphHardCoverGlueType != null ? CPGraphHardCoverGlueType : "");
				CharCPGraphHardCover.put("Sewn", CPGraphHardCoverSewn != null ? CPGraphHardCoverSewn : "");
				CharCPGraphHardCover.put("PerfectBindRound", CPGraphHardPerfectBindRound != null ? CPGraphHardPerfectBindRound : "");
				CharCPGraphHardCover.put("CoverCardboard", CPGraphHardCoverCardboard != null ? CPGraphHardCoverCardboard : "");
				CharCPGraphHardCover.put("Note", CPGraphHardNote != null ? CPGraphHardNote : "");
				CharCPGraphHardCover.put("SpineCardboard", CPGraphHardSpineCardboard != null ? CPGraphHardSpineCardboard : "");
				CharCPGraphHardCover.put("JointGap", CPGraphHardJointGap != null ? CPGraphHardJointGap : "");
				CharCPGraphHardCover.put("ChangeDefaultFoldOver", CPGraphHardChangeDefaultFoldOver != null ? CPGraphHardChangeDefaultFoldOver : "");
				CharCPGraphHardCover.put("Headadjust", CPGraphHardHeadadjust != null ? CPGraphHardHeadadjust : "");
				CharCPGraphHardCover.put("Faceadjust", CPGraphHardFaceadjust != null ? CPGraphHardFaceadjust : "");
				CharCPGraphHardCover.put("Footadjust", CPGraphHardFootadjust != null ? CPGraphHardFootadjust : "");
				CharCPGraphHardCover.put("FoldOverMargin", CPGraphHardFoldOverMargin != null ? CPGraphHardFoldOverMargin : "");
				CharCPGraphHardCover.put("BackingMaterial", CPGraphHardBackingMaterial != null ? CPGraphHardBackingMaterial : "");
				CharCPGraphHardCover.put("Hasheadband", CPGraphHardHasheadband != null ? CPGraphHardHasheadband : "");
				CharCPGraphHardCover.put("HeadbandLiner", CPGraphHardHeadbandLiner != null ? CPGraphHardHeadbandLiner : "");
				CharCPGraphHardCover.put("HeadbandColor", CPGraphHardHeadbandColor != null ? CPGraphHardHeadbandColor : "");
				CharCPGraphHardCover.put("Numberofribbons", CPGraphHardNumberofribbons != null ? CPGraphHardNumberofribbons : "");
				CharCPGraphHardCover.put("SeparateCover", CPGraphHardSeparateCover != null ? CPGraphHardSeparateCover : "");
				CharCPGraphHardCover.put("CoverWidthReduction", CPGraphHardCoverWidthReduction != null ? CPGraphHardCoverWidthReduction : "");
				CharCPGraphHardCover.put("SubstrateType", CPGraphHardSubstrateType != null ? CPGraphHardSubstrateType : "");
				CharCPGraphHardCover.put("Line", CPGraphHardLine != null ? CPGraphHardLine : "");
				CharCPGraphHardCover.put("Grammage", CPGraphHardGrammage != null ? CPGraphHardGrammage : "");
				CharCPGraphHardCover.put("GrainDirection", CPGraphHardGrainDirection != null ? CPGraphHardGrainDirection : "");
				CharCPGraphHardCover.put("BookOverlapTop", CPGraphHardBookOverlapTop != null ? CPGraphHardBookOverlapTop : "");
				CharCPGraphHardCover.put("BookOverlapBottom", CPGraphHardBookOverlapBottom != null ? CPGraphHardBookOverlapBottom : "");
				CharCPGraphHardCover.put("BookOverlapRight", CPGraphHardBookOverlapRight != null ? CPGraphHardBookOverlapRight : "");
				CharCPGraphHardCover.put("Manufacturer", CPGraphHardManufacturer != null ? CPGraphHardManufacturer : "");
				CharCPGraphHardCover.put("AnotherManufacturer", CPGraphHardAnotherManufacturer != null ? CPGraphHardAnotherManufacturer : "");
				CharCPGraphHardCover.put("Notes", CPGraphHardNotes != null ? CPGraphHardNotes : "");


			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGraphHardCover;
		
	}
	public HashMap<String, String> CPGraphCollection(String Estimateid, String IdItemOption,String componentorder, String CharacteristicDescp) throws ClassNotFoundException, SQLException, IOException
	{
		CharCPGraphCollection.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphCollection.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL serv
		
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next()) {

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");
				String CPGraphCollectionTrimmed=rs.getString("Trimmed");
				String CPGraphCollectionNote=rs.getString("Note");



				CharCPGraphCollection.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphCollection.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphCollection.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphCollection.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGraphCollection.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGraphCollection.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphCollection.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");
				CharCPGraphCollection.put("Trimmed", CPGraphCollectionTrimmed != null ? CPGraphCollectionTrimmed : "");
				CharCPGraphCollection.put("Note", CPGraphCollectionNote != null ? CPGraphCollectionNote : "");


			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGraphCollection;
	}
	public HashMap<String, String> CPGraphPackPallet(String Estimateid, String IdItemOption,String componentorder, String CharacteristicDescp) throws ClassNotFoundException, SQLException, IOException
	{
		CharCPGraphPackPallet.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphPackPallet.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
				
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next())
			{

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");
				String CPGraphPackPalletUnitsInThePallet=rs.getString("UnitsInThePallet");
				String CPGraphPackPalletMaterial=rs.getString("Material");
				String CPGraphPackPalletNote=rs.getString("Note");

				CharCPGraphPackPallet.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphPackPallet.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphPackPallet.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphPackPallet.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGraphPackPallet.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGraphPackPallet.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphPackPallet.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");
				CharCPGraphPackPallet.put("UnitsInThePallet", CPGraphPackPalletUnitsInThePallet != null ? CPGraphPackPalletUnitsInThePallet : "");
				CharCPGraphPackPallet.put("Material", CPGraphPackPalletMaterial != null ? CPGraphPackPalletMaterial : "");
				CharCPGraphPackPallet.put("Note", CPGraphPackPalletNote != null ? CPGraphPackPalletNote : "");

			}
			
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				System.out.println("failed");
			}
			return CharCPGraphPackPallet;
	}
	public HashMap<String, String> CPGraphPrintType(String Estimateid, String IdItemOption,String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException
	{
		CharCPGraphPrintType.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphPrintType.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL serv
		
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next())
			{

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");


				String CPGraphPrintTypePrintingProcess=rs.getString("PrintingProcess");


				CharCPGraphPrintType.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphPrintType.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphPrintType.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphPrintType.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGraphPrintType.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGraphPrintType.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphPrintType.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");


				CharCPGraphPrintType.put("PrintingProcess", CPGraphPrintTypePrintingProcess != null ? CPGraphPrintTypePrintingProcess : "");

			
			}
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGraphPrintType;
	}

	public HashMap<String, String> CPGraphPackagingStrapping(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws ClassNotFoundException, IOException, SQLException
	{
		CPGraphPackagingStrapping.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphPackagingStrapping.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL serv
		
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next())
			{
				String UnitsInTheStrap=rs.getString("UnitsInTheStrap");
				String Material=rs.getString("Material");
				String Application=rs.getString("Application");
				String Note=rs.getString("Note");
				CPGraphPackagingStrapping.put("UnitsInTheStrap", UnitsInTheStrap != null ? UnitsInTheStrap : "");
				CPGraphPackagingStrapping.put("Material", Material != null ? Material : "");
				CPGraphPackagingStrapping.put("Application", Application != null ? Application : "");
				CPGraphPackagingStrapping.put("Note", Note != null ? Note : "");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CPGraphPackagingStrapping;
	}
	public HashMap<String, HashMap<String, String>> CPGraphGIrregFormat(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws ClassNotFoundException, SQLException, IOException
	{
		CharCPGraphGIrregFormat.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphGIrregFormat.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL serv
		
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			HashMap<String, String> CharCharacteristicCPGraphGIrregFormat=new HashMap<String,String>();
			int valkey=1;
			while(rs.next())
			{

				//	String Quantity=rs.getString("Qty");



				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CPvalueidPSCmpCarac=rs.getString("idPSCmpCarac");
				String CPGraphGIrregFormatClosedWidth=rs.getString("ClosedWidth");
				String CPGraphGIrregFormatClosedHeight=rs.getString("ClosedHeight");
				String CPGraphGIrregFormatOpenedWidth=rs.getString("OpenedWidth");
				String CPGraphGIrregFormatOpenedHeight=rs.getString("OpenedHeight");
				String CPGraphGIrregFormatDieApportionment=rs.getString("DieApportionment");
				String CPGraphGIrregFormatKnifeCode=rs.getString("KnifeCode");
				String CPGraphGIrregFormatEditDie=rs.getString("EditDie");
				String CPGraphGIrregFormatSizeWidth=rs.getString("SizeWidth");
				String CPGraphGIrregFormatSizeHeight=rs.getString("SizeHeight");
				String CPGraphGIrregFormatNumberUp=rs.getString("NumberUp");
				String CPGraphGIrregFormatDieCutterLength=rs.getString("DieCutterLength");
				String CPGraphGIrregFormatFixedDieLength=rs.getString("FixedDieLength");
				String CPGraphGIrregFormatDifficulty=rs.getString("Difficulty");
				String CPGraphGIrregFormatUtilization=rs.getString("Utilization");
				String CPGraphGIrregFormatDoNotChargeForDie=rs.getString("DoNotChargeForDie");
				String CPGraphGIrregFormatInverted=rs.getString("Inverted");
				String CPGraphGIrregFormatFixNumberUp=rs.getString("FixNumberUp");
				String CPGraphGIrregFormatHasADifferentSlot=rs.getString("HasADifferentSlot");
				String CPGraphGIrregFormatAutoGenerated=rs.getString("AutoGenerated");
				String CPGraphGIrregFormatNotes=rs.getString("Notes");
				String CPGraphGIrregFormatDieSizeWidth=rs.getString("DieSizeWidth");
				String CPGraphGIrregFormatDieSizeHeight=rs.getString("DieSizeHeight");
				String CPGraphGIrregFormatOriginalNumberUp=rs.getString("OriginalNumberUp");
				String CPGraphGIrregFormatOriginalDieLength=rs.getString("OriginalDieLength");




				CharCharacteristicCPGraphGIrregFormat.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCharacteristicCPGraphGIrregFormat.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCharacteristicCPGraphGIrregFormat.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCharacteristicCPGraphGIrregFormat.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCharacteristicCPGraphGIrregFormat.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCharacteristicCPGraphGIrregFormat.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCharacteristicCPGraphGIrregFormat.put("idPSCmpCarac", CPvalueidPSCmpCarac != null ? CPvalueidPSCmpCarac : "");
				CharCharacteristicCPGraphGIrregFormat.put("ClosedWidth", CPGraphGIrregFormatClosedWidth != null ? CPGraphGIrregFormatClosedWidth : "");
				CharCharacteristicCPGraphGIrregFormat.put("ClosedHeight", CPGraphGIrregFormatClosedHeight != null ? CPGraphGIrregFormatClosedHeight : "");
				CharCharacteristicCPGraphGIrregFormat.put("OpenedWidth", CPGraphGIrregFormatOpenedWidth != null ? CPGraphGIrregFormatOpenedWidth : "");
				CharCharacteristicCPGraphGIrregFormat.put("OpenedHeight", CPGraphGIrregFormatOpenedHeight != null ? CPGraphGIrregFormatOpenedHeight : "");
				CharCharacteristicCPGraphGIrregFormat.put("DieApportionment", CPGraphGIrregFormatDieApportionment != null ? CPGraphGIrregFormatDieApportionment : "");
				CharCharacteristicCPGraphGIrregFormat.put("KnifeCode", CPGraphGIrregFormatKnifeCode != null ? CPGraphGIrregFormatKnifeCode : "");
				CharCharacteristicCPGraphGIrregFormat.put("EditDie", CPGraphGIrregFormatEditDie != null ? CPGraphGIrregFormatEditDie : "");
				CharCharacteristicCPGraphGIrregFormat.put("SizeWidth", CPGraphGIrregFormatSizeWidth != null ? CPGraphGIrregFormatSizeWidth : "");
				CharCharacteristicCPGraphGIrregFormat.put("SizeHeight", CPGraphGIrregFormatSizeHeight != null ? CPGraphGIrregFormatSizeHeight : "");
				CharCharacteristicCPGraphGIrregFormat.put("NumberUp", CPGraphGIrregFormatNumberUp != null ? CPGraphGIrregFormatNumberUp : "");
				CharCharacteristicCPGraphGIrregFormat.put("DieCutterLength", CPGraphGIrregFormatDieCutterLength != null ? CPGraphGIrregFormatDieCutterLength : "");
				CharCharacteristicCPGraphGIrregFormat.put("FixedDieLength", CPGraphGIrregFormatFixedDieLength != null ? CPGraphGIrregFormatFixedDieLength : "");
				CharCharacteristicCPGraphGIrregFormat.put("Difficulty", CPGraphGIrregFormatDifficulty != null ? CPGraphGIrregFormatDifficulty : "");
				CharCharacteristicCPGraphGIrregFormat.put("Utilization", CPGraphGIrregFormatUtilization != null ? CPGraphGIrregFormatUtilization : "");
				CharCharacteristicCPGraphGIrregFormat.put("DoNotChargeForDie", CPGraphGIrregFormatDoNotChargeForDie != null ? CPGraphGIrregFormatDoNotChargeForDie : "");
				CharCharacteristicCPGraphGIrregFormat.put("Inverted", CPGraphGIrregFormatInverted != null ? CPGraphGIrregFormatInverted : "");
				CharCharacteristicCPGraphGIrregFormat.put("FixNumberUp", CPGraphGIrregFormatFixNumberUp != null ? CPGraphGIrregFormatFixNumberUp : "");
				CharCharacteristicCPGraphGIrregFormat.put("HasADifferentSlot", CPGraphGIrregFormatHasADifferentSlot != null ? CPGraphGIrregFormatHasADifferentSlot : "");
				CharCharacteristicCPGraphGIrregFormat.put("AutoGenerated", CPGraphGIrregFormatAutoGenerated != null ? CPGraphGIrregFormatAutoGenerated : "");
				CharCharacteristicCPGraphGIrregFormat.put("Notes", CPGraphGIrregFormatNotes != null ? CPGraphGIrregFormatNotes : "");
				CharCharacteristicCPGraphGIrregFormat.put("DieSizeWidth", CPGraphGIrregFormatDieSizeWidth != null ? CPGraphGIrregFormatDieSizeWidth : "");
				CharCharacteristicCPGraphGIrregFormat.put("DieSizeHeight", CPGraphGIrregFormatDieSizeHeight != null ? CPGraphGIrregFormatDieSizeHeight : "");
				CharCharacteristicCPGraphGIrregFormat.put("OriginalNumberUp", CPGraphGIrregFormatOriginalNumberUp != null ? CPGraphGIrregFormatOriginalNumberUp : "");
				CharCharacteristicCPGraphGIrregFormat.put("OriginalDieLength", CPGraphGIrregFormatOriginalDieLength != null ? CPGraphGIrregFormatOriginalDieLength : "");


				CharCPGraphGIrregFormat.put(valkey+"", CharCharacteristicCPGraphGIrregFormat);
				++valkey;
				//System.out.println(Collections.singletonList(EstDetailsQty));
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGraphGIrregFormat;
	}
	public HashMap<String, String> CPGraphLabelFormat(String Estimateid,String IdItemOption,String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException
	{
		CharCPGraphLabelFormat.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphLabelFormat.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL serv
		
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next())
			{

				//	String OptionDescp=rs.getString("OptionDescription");
					String CompFinal=rs.getString("ComponentFinal");
					String CompOrder=rs.getString("ComponentOrder");
					String CompDescp=rs.getString("ComponentDescription");
					String CompTypeDescp=rs.getString("ComponentTypeDesc");
				//	String CharDescp=rs.getString("CharacteristicDesc");
				//	String CharidPSCmpCarac=rs.getString("idPSCmpCarac");

					String CPGraphLabelFormatLabelType=rs.getString("LabelType");
					String CPGraphLabelFormatDeliverytype=rs.getString("Deliverytype");
					String CPGraphLabelFormatFormatWidth=rs.getString("FormatWidth");
					String CPGraphLabelFormatFormatHeight=rs.getString("FormatHeight");
					String CPGraphLabelFormatColumns=rs.getString("Columns");
					String CPGraphLabelFormatGapacross=rs.getString("Gapacross");
					String CPGraphLabelFormatGapDown=rs.getString("GapDown");
					String CPGraphLabelFormatKisscut=rs.getString("Kisscut");
					String CPGraphLabelFormatRightmargin=rs.getString("Rightmargin");
					String CPGraphLabelFormatLeftmargin=rs.getString("Leftmargin");
					String CPGraphLabelFormatSpecialDiecutting=rs.getString("Specialdiecutting");
					String CPGraphLabelFormatTrimmargin=rs.getString("Trimmargin");
						
											
				
				//	CharCPGraphLabelFormat.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
					CharCPGraphLabelFormat.put("ComponentDescription", CompDescp != null ? CompDescp : "");
					CharCPGraphLabelFormat.put("ComponentFinal", CompFinal != null ? CompFinal : "");
					CharCPGraphLabelFormat.put("ComponentOrder", CompOrder != null ? CompOrder : "");
					CharCPGraphLabelFormat.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				//	CharCPGraphLabelFormat.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				//	CharCPGraphLabelFormat.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");

					CharCPGraphLabelFormat.put("LabelType", CPGraphLabelFormatLabelType != null ? CPGraphLabelFormatLabelType : "");
					CharCPGraphLabelFormat.put("Deliverytype", CPGraphLabelFormatDeliverytype != null ? CPGraphLabelFormatDeliverytype : "");
					CharCPGraphLabelFormat.put("FormatWidth", CPGraphLabelFormatFormatWidth != null ? CPGraphLabelFormatFormatWidth : "");
					CharCPGraphLabelFormat.put("FormatHeight", CPGraphLabelFormatFormatHeight != null ? CPGraphLabelFormatFormatHeight : "");
					CharCPGraphLabelFormat.put("Columns", CPGraphLabelFormatColumns != null ? CPGraphLabelFormatColumns : "");
					CharCPGraphLabelFormat.put("Gapacross", CPGraphLabelFormatGapacross != null ? CPGraphLabelFormatGapacross : "");
					CharCPGraphLabelFormat.put("GapDown", CPGraphLabelFormatGapDown != null ? CPGraphLabelFormatGapDown : "");
					CharCPGraphLabelFormat.put("Kisscut", CPGraphLabelFormatKisscut != null ? CPGraphLabelFormatKisscut : "");
					CharCPGraphLabelFormat.put("Rightmargin", CPGraphLabelFormatRightmargin != null ? CPGraphLabelFormatRightmargin : "");
					CharCPGraphLabelFormat.put("Leftmargin", CPGraphLabelFormatLeftmargin != null ? CPGraphLabelFormatLeftmargin : "");
					CharCPGraphLabelFormat.put("SpecialDiecut", CPGraphLabelFormatSpecialDiecutting != null ? CPGraphLabelFormatSpecialDiecutting : "");
					CharCPGraphLabelFormat.put("Trimmargin", CPGraphLabelFormatTrimmargin != null ? CPGraphLabelFormatTrimmargin : "");
				
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGraphLabelFormat;
	}
	public HashMap<String, String> CPGenericCPOption(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException
	{
		CharCPGenericCPOption.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGenericCPOption.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL serv
		
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next())
			{

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CPvalueidPSCmpCarac=rs.getString("idPSCmpCarac");
				String GPoPtionDescp=rs.getString("Description");



				CharCPGenericCPOption.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGenericCPOption.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGenericCPOption.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGenericCPOption.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGenericCPOption.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGenericCPOption.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGenericCPOption.put("idPSCmpCarac", CPvalueidPSCmpCarac != null ? CPvalueidPSCmpCarac : "");
				CharCPGenericCPOption.put("Description", GPoPtionDescp != null ? GPoPtionDescp : "");
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGenericCPOption;
	}
	
	public HashMap<String, String> CPAOptionQty(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws ClassNotFoundException, SQLException, IOException
	{
		CharCPAOptionQty.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPAOptionQty.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
				
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next())
			{

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");

				String CPAOptionQtyOptionDesc=rs.getString("OptionDesc");
				String CPAOptionQtyNote=rs.getString("Note");
				String CPAOptionQtyQuantity=rs.getString("Quantity");

										
			
				CharCPAOptionQty.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPAOptionQty.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPAOptionQty.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPAOptionQty.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPAOptionQty.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPAOptionQty.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPAOptionQty.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");

				CharCPAOptionQty.put("OptionDesc", CPAOptionQtyOptionDesc != null ? CPAOptionQtyOptionDesc : "");
				CharCPAOptionQty.put("Note", CPAOptionQtyNote != null ? CPAOptionQtyNote : "");
				CharCPAOptionQty.put("Quantity", CPAOptionQtyQuantity != null ? CPAOptionQtyQuantity : "");

			
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPAOptionQty;
		
	}
	public HashMap<String, String> CPGraphDieCut(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws ClassNotFoundException, SQLException, IOException
	{
		CharCPGraphDieCut.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphDieCut.properties");
		Query.load(fis);

		String Query1=Query.getProperty("Query");
				
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next())
			{

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");
				String CPWithWasteStripping=rs.getString("WithWasteStripping");
				String CPNeedtrimming=rs.getString("Needtrimming");
				String CPIsKnifeFormatInformed=rs.getString("IsKnifeFormatInformed");
				String CPWExistingDie=rs.getString("ExistingDie");
				String CPInformedcuttinglength=rs.getString("Informedcuttinglength");
				String CPDifficulty=rs.getString("Difficulty");
				String CPNotes=rs.getString("Notes");





				CharCPGraphDieCut.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphDieCut.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphDieCut.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphDieCut.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGraphDieCut.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGraphDieCut.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphDieCut.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");
				CharCPGraphDieCut.put("WithWasteStripping", CPWithWasteStripping != null ? CPWithWasteStripping : "");
				CharCPGraphDieCut.put("Needtrimming", CPNeedtrimming != null ? CPNeedtrimming : "");
				CharCPGraphDieCut.put("IsKnifeFormatInformed", CPIsKnifeFormatInformed != null ? CPIsKnifeFormatInformed : "");
				CharCPGraphDieCut.put("ExistingDie", CPWExistingDie != null ? CPWExistingDie : "");
				CharCPGraphDieCut.put("Informedcuttinglength", CPInformedcuttinglength != null ? CPInformedcuttinglength : "");
				CharCPGraphDieCut.put("Difficulty", CPDifficulty != null ? CPDifficulty : "");
				CharCPGraphDieCut.put("Notes", CPNotes != null ? CPNotes : "");

			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGraphDieCut;
		
		
	}
	public HashMap<String, String> CPGraphWireOBind(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws ClassNotFoundException, IOException, SQLException
	{
		CharCPGraphWireOBind.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphWireOBind.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
				
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next())
			{
				String Position=rs.getString("Position");
				String Color=rs.getString("Color");
				String WithHanger=rs.getString("WithHanger");
				String Note=rs.getString("Note");
				String ProductRingsQuantity=rs.getString("ProductRingsQuantity");
				String SeperationBetweenRings=rs.getString("SeperationBetweenRings");
				String InchesOfWireO=rs.getString("InchesOfWireO");
				
				CharCPGraphWireOBind.put("Position", Position != null ? Position : "");
				CharCPGraphWireOBind.put("Color", Color != null ? Color : "");
				CharCPGraphWireOBind.put("WithHanger", WithHanger != null ? WithHanger : "");
				CharCPGraphWireOBind.put("Note", Note != null ? Note : "");
				CharCPGraphWireOBind.put("ProductRingsQuantity", ProductRingsQuantity != null ? ProductRingsQuantity : "");
				CharCPGraphWireOBind.put("SeperationBetweenRings", SeperationBetweenRings != null ? SeperationBetweenRings : "");
				CharCPGraphWireOBind.put("InchesOfWireO", InchesOfWireO != null ? InchesOfWireO : "");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGraphWireOBind;
	}
	public HashMap<String, String> CharCPGenericRawMaterial(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException
	{
		CharCPGenericRawMaterial.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGenericRawMaterial.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
				
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next())
			{

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");
				String CPGenericRawMaterialRawMaterial=rs.getString("RawMaterial");
				String CPGenericRawMaterialValue=rs.getString("Value");
				String CPGenericRawMaterialDescription=rs.getString("Description");

				CharCPGenericRawMaterial.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGenericRawMaterial.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGenericRawMaterial.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGenericRawMaterial.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGenericRawMaterial.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGenericRawMaterial.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGenericRawMaterial.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");
				CharCPGenericRawMaterial.put("RawMaterial", CPGenericRawMaterialRawMaterial != null ? CPGenericRawMaterialRawMaterial : "");
				CharCPGenericRawMaterial.put("Value", CPGenericRawMaterialValue != null ? CPGenericRawMaterialValue : "");
				CharCPGenericRawMaterial.put("Description", CPGenericRawMaterialDescription != null ? CPGenericRawMaterialDescription : "");
			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGenericRawMaterial;
	}
	

	
	public HashMap<String, String> CPGraphBleed(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws ClassNotFoundException, IOException, SQLException
	{
		CharCPGraphBleed.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphBleed.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
				
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		
		try
		{
			while(rs.next())
			{
				String Bleed=rs.getString("Bleed");
				CharCPGraphBleed.put("Bleed", Bleed != null ? Bleed : "");
				
			}

		}

		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGraphBleed;
	}
	public HashMap<String, String> CPGraphBindGlue(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException
	{
		CharCPGraphBindGlue.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphBindGlue.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		try
		{
			HashMap<String, String> CharCPGraphStampingItemHM=new HashMap<String,String>(); 
			int valkey=1;
			while(rs.next()) {
				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");


				String CPGraphBindGlueGlueType=rs.getString("GlueType");
				String CPGraphBindGlueIsSewn=rs.getString("IsSewn");
				String CPGraphBindGlueNote=rs.getString("Note");



				CharCPGraphBindGlue.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphBindGlue.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphBindGlue.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphBindGlue.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGraphBindGlue.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGraphBindGlue.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphBindGlue.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");


				CharCPGraphBindGlue.put("GlueType", CPGraphBindGlueGlueType != null ? CPGraphBindGlueGlueType : "");
				CharCPGraphBindGlue.put("IsSewn", CPGraphBindGlueIsSewn != null ? CPGraphBindGlueIsSewn : "");
				CharCPGraphBindGlue.put("Note", CPGraphBindGlueNote != null ? CPGraphBindGlueNote : "");

			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGraphBindGlue;
	}
	public HashMap<String, String> CPValueQTY(String Estimateid, String IdItemOption,String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException{
		CharCPvalueQTY.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPValueQTY.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		try
		{
			while(rs.next()) {

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String Quantity=rs.getString("Qty");
				String CPvalueidPSCmpCarac=rs.getString("idPSCmpCarac");
				String Value=rs.getString("Value");



				CharCPvalueQTY.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPvalueQTY.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPvalueQTY.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPvalueQTY.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPvalueQTY.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPvalueQTY.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPvalueQTY.put("Qty", Quantity != null ? Quantity : "");
				CharCPvalueQTY.put("idPSCmpCarac", CPvalueidPSCmpCarac != null ? CPvalueidPSCmpCarac : "");
				CharCPvalueQTY.put("Value", Value != null ? Value : "");
			
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPvalueQTY;
	}

	public HashMap<String, String> CPASimpleQty(String Estimateid, String IdItemOption,String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException{
		CharCPASimpleQty.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPASimpleQty.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		try
		{
			while(rs.next()) {

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");


				String CPASimpleQtyQuantity=rs.getString("Quantity");
				String CPASimpleQtyNote=rs.getString("Note");



				CharCPASimpleQty.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPASimpleQty.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPASimpleQty.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPASimpleQty.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPASimpleQty.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPASimpleQty.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPASimpleQty.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");


				CharCPASimpleQty.put("Quantity", CPASimpleQtyQuantity != null ? CPASimpleQtyQuantity : "");
				CharCPASimpleQty.put("Note", CPASimpleQtyNote != null ? CPASimpleQtyNote : "");

			
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
	
		return CharCPASimpleQty;
	}
	public HashMap<String, String> CPGraphFiber(String Estimateid,String IdItemOption, String componentorder, String CharacteristicDescp) throws ClassNotFoundException, SQLException, IOException
	{
		CharCPGraphFiber.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphFiber.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		try
		{
			while(rs.next()) {

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CharidPSCmpCarac=rs.getString("idPSCmpCarac");
				String CPGraphFiberGrainDirection=rs.getString("GrainDirection");


				CharCPGraphFiber.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphFiber.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphFiber.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphFiber.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGraphFiber.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGraphFiber.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphFiber.put("idPSCmpCarac", CharidPSCmpCarac != null ? CharidPSCmpCarac : "");
				CharCPGraphFiber.put("GrainDirection", CPGraphFiberGrainDirection != null ? CPGraphFiberGrainDirection : "");

			
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGraphFiber;
		
	}
	public HashMap<String, String> CPGraphPackBox(String Estimateid, String IdItemOption,String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException
	{
		CharCPGraphPackBox.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphPackBag.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		try
		{
			while(rs.next()) {

				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CPvalueidPSCmpCarac=rs.getString("idPSCmpCarac");
				String PackboxCharUnitsInThebox=rs.getString("UnitsInThebox");
				String PackboxCharBoxType=rs.getString("BoxType");
				String PackboxCharBox=rs.getString("Box");
				String PackboxCharNote=rs.getString("Note");


				CharCPGraphPackBox.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPGraphPackBox.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPGraphPackBox.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPGraphPackBox.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPGraphPackBox.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPGraphPackBox.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPGraphPackBox.put("idPSCmpCarac", CPvalueidPSCmpCarac != null ? CPvalueidPSCmpCarac : "");
				CharCPGraphPackBox.put("UnitsInThebox", PackboxCharUnitsInThebox != null ? PackboxCharUnitsInThebox : "");
				CharCPGraphPackBox.put("BoxType", PackboxCharBoxType != null ? PackboxCharBoxType : "");
				CharCPGraphPackBox.put("Box", PackboxCharBox != null ? PackboxCharBox : "");
				CharCPGraphPackBox.put("Note", PackboxCharNote != null ? PackboxCharNote : "");


			
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		
		return CharCPGraphPackBox;
		
	}
	public HashMap<String, String> CPFileList(String Estimateid, String IdItemOption,String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException
	{
		CharCPFileList.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPFileList.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		try
		{
			while(rs.next()) {


				String OptionDescp=rs.getString("OptionDescription");
				String CompFinal=rs.getString("ComponentFinal");
				String CompOrder=rs.getString("ComponentOrder");
				String CompDescp=rs.getString("ComponentDescription");
				String CompTypeDescp=rs.getString("ComponentTypeDesc");
				String CharDescp=rs.getString("CharacteristicDesc");
				String CPvalueidPSCmpCarac=rs.getString("idPSCmpCarac");
				String Files=rs.getString("Files");
				String Notes=rs.getString("Notes");
				


				CharCPFileList.put("OptionDescription", OptionDescp != null ? OptionDescp : "");
				CharCPFileList.put("ComponentDescription", CompDescp != null ? CompDescp : "");
				CharCPFileList.put("ComponentFinal", CompFinal != null ? CompFinal : "");
				CharCPFileList.put("ComponentOrder", CompOrder != null ? CompOrder : "");
				CharCPFileList.put("ComponentTypeDesc", CompTypeDescp != null ? CompTypeDescp : "");
				CharCPFileList.put("CharacteristicDesc", CharDescp != null ? CharDescp : "");
				CharCPFileList.put("idPSCmpCarac", CPvalueidPSCmpCarac != null ? CPvalueidPSCmpCarac : "");
				CharCPFileList.put("Files", Files != null ? Files : "");
				CharCPFileList.put("Notes", Notes != null ? Notes : "");
				


			
			
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPFileList;
	}
	
	
	public HashMap<String, String> CPGraphLargeFormat(String Estimateid, String IdItemOption,String componentorder, String CharacteristicDescp) throws IOException, ClassNotFoundException, SQLException
	{
		CharCPGraphLargeFormat.clear();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Updated\\CharacteristicsQueries\\CPGraphLargeFormat.properties");
		Query.load(fis);
		
		String Query1=Query.getProperty("Query");
		// opening database connection to MySQL server
		
		rs = iqdb.RunQuery(Query1.replace("##Estimate##", Estimateid).replace("##CompOrder##", componentorder).replace("##IdItemOption##", IdItemOption).replace("##CharteristicDescp##", CharacteristicDescp));
		try
		{
			while(rs.next()) {

				String FinishedFormatWidth=rs.getString("FinishedFormatWidth");
				String FinishedFormatHeight=rs.getString("FinishedFormatHeight");
				//String PrintingDirection=rs.getString("PrintingDirection");
				String Note=rs.getString("Note");
				String Margins=rs.getString("Margins");
				String IrregularFit=rs.getString("IrregularFit");
				String IrregularFitCB=rs.getString("IrregularFitCB");
				String TopMargin=rs.getString("TopMargin");
				String BottomMargin=rs.getString("BottomMargin");
				String LeftMargin=rs.getString("LeftMargin");
				String RightMargin=rs.getString("RightMargin");
				String TopBleed=rs.getString("TopBleed");
				String BottomBleed=rs.getString("BottomBleed");
				String LeftBleed=rs.getString("LeftBleed");
				String RightBleed=rs.getString("RightBleed");
				String Splicing=rs.getString("Splicing");
				String SplicingBleed=rs.getString("SplicingBleed");
				/*String LayoutWidht=rs.getString("LayoutWidht");
				String LayoutHeight=rs.getString("LayoutHeight");
				String Utilization=rs.getString("Utilization");
				String Numberup=rs.getString("Numberup");*/
				
				
				CharCPGraphLargeFormat.put("FinishedFormatWidth", FinishedFormatWidth != null ? FinishedFormatWidth : "");
				CharCPGraphLargeFormat.put("FinishedFormatHeight", FinishedFormatHeight != null ? FinishedFormatHeight : "");
				//CharCPGraphLargeFormat.put("PrintingDirection", PrintingDirection != null ? PrintingDirection : "");
				CharCPGraphLargeFormat.put("Note", Note != null ? Note : "");
				CharCPGraphLargeFormat.put("Margins", Margins != null ? Margins : "");
				CharCPGraphLargeFormat.put("IrregularFit", IrregularFit != null ? IrregularFit : "");
				CharCPGraphLargeFormat.put("IrregularFitCB", IrregularFitCB != null ? IrregularFitCB : "");
				CharCPGraphLargeFormat.put("TopMargin", TopMargin != null ? TopMargin : "");
				CharCPGraphLargeFormat.put("BottomMargin", BottomMargin != null ? BottomMargin : "");
				CharCPGraphLargeFormat.put("RightMargin", RightMargin != null ? RightMargin : "");
				CharCPGraphLargeFormat.put("TopBleed", TopBleed != null ? TopBleed : "");
				CharCPGraphLargeFormat.put("BottomBleed", BottomBleed != null ? BottomBleed : "");
				CharCPGraphLargeFormat.put("LeftMargin", LeftMargin != null ? LeftMargin : "");
				CharCPGraphLargeFormat.put("LeftBleed", LeftBleed != null ? LeftBleed : "");
				CharCPGraphLargeFormat.put("RightBleed", RightBleed != null ? RightBleed : "");
				CharCPGraphLargeFormat.put("Splicing", Splicing != null ? Splicing : "");
				CharCPGraphLargeFormat.put("SplicingBleed", SplicingBleed != null ? SplicingBleed : "");
				/*CharCPGraphLargeFormat.put("LayoutWidht", LayoutWidht != null ? LayoutWidht : "");
				CharCPGraphLargeFormat.put("LayoutHeight", LayoutHeight != null ? LayoutHeight : "");
				CharCPGraphLargeFormat.put("Utilization", Utilization != null ? Utilization : "");
				CharCPGraphLargeFormat.put("Numberup", Numberup != null ? Numberup : "");*/
				
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		return CharCPGraphLargeFormat;
	}
	
}

