package utilities;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
 
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
//import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.XMLUnit;
import org.testng.Assert;
import org.testng.Reporter;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.Status;
import base.Logger;

import base.Testbase;

public class XmlCompare extends Testbase{

	static Logger Logger;
	
	public XmlCompare() {
		Logger=new Logger();
	}

	public static boolean compareXml(String tblName, String RefXmlPath, String ActualXmlPath) throws FileNotFoundException, Exception {		

		boolean xmlComparision = false;
		
		Logger.info("==========================" + tblName + "=============================="); 
		Logger.info("Ref. File : " + RefXmlPath);
		Logger.info("Actual File : " + ActualXmlPath);
        
		//reading two xml file to compare in Java program
		FileInputStream fisRef = new FileInputStream(RefXmlPath);  
		FileInputStream fisActual = new FileInputStream(ActualXmlPath);
	      
	      // using BufferedReader for improved performance
	      BufferedReader  brRef = new BufferedReader(new InputStreamReader(fisRef));
	      BufferedReader  brActual = new BufferedReader(new InputStreamReader(fisActual));
	      
	      XMLUnit.setIgnoreComments(Boolean.TRUE);
	      XMLUnit.setNormalizeWhitespace(Boolean.TRUE);
	      XMLUnit.setIgnoreDiffBetweenTextAndCDATA(Boolean.TRUE);
	      
	      try 
	        {
	    	//creating Diff instance to compare two XML files
	            Diff diff = new Diff(brRef, brActual);
	            
	          //for getting detailed differences between two xml files
	            DetailedDiff detailXmlDiff = new DetailedDiff(diff);
	            int totDiff = detailXmlDiff.getAllDifferences().size();
	            
	            Logger.info(" "); 
	            Logger.info("Total differences : " + detailXmlDiff.getAllDifferences().size()); 
	            Logger.info(" "); 
	            
	            if (totDiff==0) { xmlComparision = true; }
	            	            
	            // output all the differences
	            for(Object difference : detailXmlDiff.getAllDifferences()){
	            	Logger.info(difference.toString()); 
	            	}  
	            
	            Logger.info(" "); 
	            
	            return xmlComparision;
	 
	        } 
	        catch (SAXException e) 
	        {
	            e.printStackTrace();
	            Logger.error("Error in xml diff"); 
	            return false;
	        } 
	        catch (IOException e)
	        {
	            e.printStackTrace();
	            Logger.error("Error in xml diff");
	            return false;
	        }
	        
	}
	public static void main(String[] args) throws Exception
	{
		try {
			
			String dir = System.getProperty("user.dir"); 
			
			compareXml("MonJobProdTxnData", dir + "\\src\\test\\resources\\files\\xmls\\Reference\\PJ_00002\\MonJobProdTxnData.xml", 
					dir + "\\src\\test\\resources\\files\\xmls\\Actual\\PJ_00168\\MonJobProdTxnData.xml");
			
			compareXml("MonJobMatTxnData", dir + "\\src\\test\\resources\\files\\xmls\\Reference\\PJ_00002\\MonJobMatTxnData.xml", 
					dir + "\\src\\test\\resources\\files\\xmls\\Actual\\PJ_00168\\MonJobMatTxnData.xml");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
