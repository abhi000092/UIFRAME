package utilities;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import base.Logger;
import base.DBUtil;
import base.Testbase;

public class Sql2Xml  extends Testbase{
		
	static Logger Logger;
	
	public Sql2Xml() {
		Logger=new Logger();
	}

	protected static DBUtil dbObj = new DBUtil();
	public static FileInputStream fis;
	
	public void convertSql2Xml(DBUtil dbObj, String qryName, String qryString, String fileFullPath ) throws Exception {
				
		try {			

			Logger.info("");
			Logger.info("Extracting '" + qryName + "'| details...");
			
			ResultSet rs = dbObj.RunQuery(qryString);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			Logger.info("# Columns = " + rsmd.getColumnCount()); 
			Logger.info("");
			
			// If the file doesn't exists, create and write to it
			// If the file exists, truncate (remove all content) and write to it
	        try (FileWriter writer = new FileWriter(fileFullPath );
	             BufferedWriter bw = new BufferedWriter(writer)) {

	            bw.write("<?xml version='1.0' encoding='utf-8' ?>");
	            bw.newLine();
	            bw.write("<data>");
	            bw.newLine();
	            
	            int row = 1;
	            
	            String xmlHdr="", xmlData="";	            

				int columnCount = rsmd.getColumnCount();

				while (rs.next()) {
					
					bw.write("  <row" + row + ">");
					bw.newLine();
					
				    for (int i = 1; i <= columnCount; i++) {
				    	
				        xmlHdr = PrepareColumnHdr4XML(rsmd.getColumnName(i));
				        xmlData = PrepareData4XML(rs.getString(i));
				        
				        bw.write("    <" + xmlHdr + ">" + xmlData + "</" + xmlHdr + ">");
				        bw.newLine();
				        
				    }				    

					bw.write("  </row" + row +  ">");
					bw.newLine();

			        row++;
				}				

				bw.write("</data>");				

				Logger.info("# Rows = " + (row-1));

	        } catch (IOException e) {
	            System.err.format("IOException: %s%n", e);
	        }
						
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static String PrepareColumnHdr4XML(String clmnName) {
		
		clmnName = clmnName.replace("_", "");
		clmnName = clmnName.replace("-", "");
		clmnName = clmnName.replace("/", "");
		clmnName = clmnName.replace("(usd)", "");
		clmnName = clmnName.replace(" ", "");
				  
		return clmnName.toLowerCase();
		
	}
	
	static String PrepareData4XML(String clmnValue) {
		
		String value = "";
		
		if (clmnValue != null) {
			value = clmnValue.replace("&", "");
			value = value.replace("  ", " ");
			value = value.replace("  ", " ");
			value = value.replace("<", "");
			value = value.replace(">", "");
			value = value.toLowerCase();
		}	
		
		return value;
		
	}
	
	public static void main(String[] args)
	{
	 /*
		Sql2Xml.ConvertSql2Xml(dbObj, 
				"select idQuote, Description, IDProject, IdCustomer from qttTMQuote order by idQuote", 
				"c:\\temp\\estimate.xml");
		*/
	}
	
}