package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;

import utilities.AllureLogger;

public class DBUtil extends Testbase{

	Connection con;
	
	public Connection createconnection(String url, String user, String password) {
		/*try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(url +" : "+ user +" : "+ password);
		System.out.println("Connected to sql db");
		return con;	
	}
	
	
	public ResultSet RunQuery(String query) throws  SQLException {
		ResultSet rs = null;
		try {
			//System.out.println("--->|RunQuery : query = " + query );
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			 rs=stmt.executeQuery(query);
			
		}
		catch(Exception e) {
			
			con.close();
		}
		return rs;
	}
	
	public void Closeconnection() throws SQLException {
		con.close();
		System.out.println("Disconnected to sql db");
	}
	
	public static boolean compareResultSets(ResultSet rs1, ResultSet rs2,String file)
    {
          
		AllureLogger.logStep("Comparing Results Sets for file : "+file);
        boolean result = true;
        boolean finalresult = true;
         
        try
        {
             
                ResultSetMetaData rsMd1 = rs1.getMetaData();
                ResultSetMetaData rsMd2 = rs2.getMetaData();
                         
                int numberOfColumns1 = rsMd1.getColumnCount();
                int numberOfColumns2 = rsMd2.getColumnCount();
                if(!(numberOfColumns1 == numberOfColumns2))
                    return false;
                 
                int rowCount = 1;
               
         
                while (rs1.next() && rs2.next()) 
                {
                        //dataExists = true;
                        //System.out.println("Row " + rowCount + ":  ");
                                              
                     
                         
                         for(int columnCount = 1; columnCount <= numberOfColumns1; columnCount++)  
                         {  
                                  
                        	 	String columnName = rsMd1.getColumnName(columnCount);  
                                 int columnType = rsMd1.getColumnType(columnCount);  
                               
 
                                 if(columnType == Types.CHAR || columnType == Types.VARCHAR || columnType == Types.LONGVARCHAR)  
                                 {  
                                     String columnValue1 = rs1.getString(columnCount);
                                     String columnValue2 = rs2.getString(columnCount);
                                     if(!(columnValue1==null || columnValue2==null) ) {
	                                        if(!(columnValue1.equals(columnValue2))) {
	                                        	System.out.println("Fail for Row " + rowCount + ":  Column Name: "+columnName);
	                                        	//Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
	                                        	//AllureLogger.markStepAsFailed("Fail for Row " + rowCount + ":  Column Name: "+columnName);
	                                        	AllureLogger.markStepAsFailed("Fail for Row " + rowCount + ":  Column Name: "+columnName +" : Base Table Column Value is : " + columnValue1 + ": Actual Table Column Value is : "+columnValue2);
	                                        	result = false; 
	                                        	finalresult=false;
	                                        }
	                                        else {
	                                        	System.out.println("Pass for Row " + rowCount + ":  Column Name: "+columnName);
	                                        	//Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
	                                        	AllureLogger.markStepAsPassed("Pass for Row " + rowCount + ":  Column Name: "+columnName);
	                                        	result = true;
	                                        }
                                     }
                                     else if(columnValue1==null && columnValue2==null) {
                                     	System.out.println("Pass for Row " + rowCount + ":  Column Name: "+columnName);
                                     	//Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
                                     	AllureLogger.markStepAsPassed("Pass for Row " + rowCount + ":  Column Name: "+columnName);
                                     	result = true;
                                     }
                                    
                                     else {
                                    	 System.out.println("Base Table Column Value is : " + columnValue1 + " Actual Table Column Value is : "+columnValue2);
                                    	 //Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
                                    	 AllureLogger.markStepAsFailed("Base Table Column Value is : " + columnValue1 + " Actual Table Column Value is : "+columnValue2);
                                       	result = false; 
                                       	finalresult=false;
                                     }
                              }  
                                 else if(columnType == Types.INTEGER || columnType == Types.BIGINT  || columnType == Types.NUMERIC)  
                                 {  
                                     Long columnValue1 = rs1.getLong(columnCount);  
                                     Long columnValue2 = rs2.getLong(columnCount);  
                                     if(!(columnValue1==null || columnValue2==null) ) {
                                         if(!(columnValue1.equals(columnValue2))) {
                                        	 AllureLogger.markStepAsFailed("Fail for Row " + rowCount + ":  Column Name: "+columnName +" : Base Table Column Value is : " + columnValue1 + ": Actual Table Column Value is : "+columnValue2);
                                        	 //Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
                                        	 AllureLogger.markStepAsFailed("Fail for Row " + rowCount + ":  Column Name: "+columnName+" Base Table Column Value is : " + columnValue1 + " Actual Table Column Value is : "+columnValue2);
                                           	result = false; 
                                           	finalresult=false;
                                         }
                                         else {
                                        	 System.out.println("Pass for Row " + rowCount + ":  Column Name: "+columnName);
	                                        	//Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
	                                        	AllureLogger.markStepAsPassed("Pass for Row " + rowCount + ":  Column Name: "+columnName);
	                                        	result = true;
	                                        }
                                     	}
                                    
                                     else {
                                    	 System.out.println("Base Table Column Value is : " + columnValue1 + " Actual Table Column Value is : "+columnValue2);
                                    	 //Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
                                    	 AllureLogger.markStepAsFailed("Base Table Column Value is : " + columnValue1 + " Actual Table Column Value is : "+columnValue2);
                                       	result = false; 
                                       	finalresult=false;
                                     }
                             }  
                                 else if(columnType == Types.SMALLINT)
                                 {
                                	  
                                     String columnValue1 = rs1.getString(columnCount);  
                                     String columnValue2 = rs2.getString(columnCount); 
                                     if(!(columnValue1==null || columnValue2==null) ) {
	                                     if(!(columnValue1.equals(columnValue2))) {
	                                    	 System.out.println("Fail for Row " + rowCount + ":  Column Name: "+columnName);
	                                    	 //Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
	                                    	 AllureLogger.markStepAsFailed("Fail for Row " + rowCount + ":  Column Name: "+columnName +" : Base Table Column Value is : " + columnValue1 + ": Actual Table Column Value is : "+columnValue2);
	                                       	result = false; 
	                                       	finalresult=false;
	                                     }
	                                     else {
	                                    	 System.out.println("Pass for Row " + rowCount + ":  Column Name: "+columnName);
	                                        	//Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
	                                        	AllureLogger.markStepAsPassed("Pass for Row " + rowCount + ":  Column Name: "+columnName);
	                                        	result = true;
	                                        }
                                     }
                                     else if(columnValue1==null && columnValue2==null) {
                                     	System.out.println("Pass for Row " + rowCount + ":  Column Name: "+columnName);
                                     	//Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
                                     	AllureLogger.markStepAsPassed("Pass for Row " + rowCount + ":  Column Name: "+columnName);
                                     	result = true;
                                     }
                                     else {
                                    	 System.out.println("Base Table Column Value is : " + columnValue1 + " Actual Table Column Value is : "+columnValue2);
                                    	 //Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
                                    	 AllureLogger.markStepAsFailed("Base Table Column Value is : " + columnValue1 + " Actual Table Column Value is : "+columnValue2);
                                       	result = false; 
                                       	finalresult=false;
                                     }
                              
                                 }
                                 else if(columnType == Types.DECIMAL || columnType == Types.DOUBLE || columnType == Types.FLOAT || columnType == Types.REAL)  
                                 {  
                                	 Double columnValue1 = rs1.getDouble(columnCount);
                                     Double columnValue2 = rs2.getDouble(columnCount);
                                     if(!(columnValue1==null || columnValue2==null) ) {
	                                     if(!(columnValue1.equals(columnValue2))) {
	                                    	 System.out.println("Fail for Row " + rowCount + ":  Column Name: "+columnName);
	                                    	 //Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
	                                    	 AllureLogger.markStepAsFailed("Fail for Row " + rowCount + ":  Column Name: "+columnName +" : Base Table Column Value is : " + columnValue1 + ": Actual Table Column Value is : "+columnValue2);
	                                       result = false;
	                                       finalresult=false;
	                                     }
	                                     else {
	                                    	 System.out.println("Pass for Row " + rowCount + ":  Column Name: "+columnName);
	                                     	//Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
	                                     	AllureLogger.markStepAsPassed("Pass for Row " + rowCount + ":  Column Name: "+columnName);
	                                     	result = true;
	                                     }
                                     }
                                     
                                     else {
                                    	 System.out.println("Base Table Column Value is : " + columnValue1 + " Actual Table Column Value is : "+columnValue2);
                                    	 //Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
                                    	 AllureLogger.markStepAsFailed("Base Table Column Value is : " + columnValue1 + " Actual Table Column Value is : "+columnValue2);
                                       	result = false; 
                                       	finalresult=false;
                                     }
                                 }  
                                 else if(columnType == Types.TIME || columnType == Types.TIMESTAMP || columnType == Types.DATE)  
                                 {  
                                     Timestamp columnValue1 = rs1.getTimestamp(columnCount);  
                                     Timestamp columnValue2 = rs2.getTimestamp(columnCount); 
                                     if(!(columnValue1==null || columnValue2==null) ) {
                                         if(!(columnValue1.equals(columnValue2))) {
                                        	 System.out.println("Fail for Row " + rowCount + ":  Column Name: "+columnName);
                                        	// Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
                                        	 AllureLogger.markStepAsFailed("Fail for Row " + rowCount + ":  Column Name: "+columnName +" : Base Table Column Value is : " + columnValue1 + ": Actual Table Column Value is : "+columnValue2);
                                           result = false; 
                                           finalresult=false;
                                         }
                                     
                                     else {
                                    	  System.out.println("Pass for Row " + rowCount + ":  Column Name: "+columnName);
                                        	//Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
                                        	AllureLogger.markStepAsPassed("Pass for Row " + rowCount + ":  Column Name: "+columnName);
                                        	result = true;
                                        }
                                     } 
                                     else if(columnValue1==null && columnValue2==null) {
                                      	System.out.println("Pass for Row " + rowCount + ":  Column Name: "+columnName);
                                      	//Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
                                      	AllureLogger.markStepAsPassed("Pass for Row " + rowCount + ":  Column Name: "+columnName);
                                      	result = true;
                                      }
                                     else {
                                    	 System.out.println("Base Table Column Value is : " + columnValue1 + " Actual Table Column Value is : "+columnValue2);
                                    	 //Allure.step("Row " + rowCount + ":  Column Name: "+columnName);
                                    	 AllureLogger.markStepAsFailed("Base Table Column Value is : " + columnValue1 + " Actual Table Column Value is : "+columnValue2);
                                       	result = false; 
                                       	finalresult=false;
                                     }
                                      
                             }  
                                
                                
                         }  
         
                     
                        rowCount++;
                  }
             
            }
            catch(SQLException sqle)
            {
                System.out.println("sql expection");
                sqle.printStackTrace();
            }
            catch(Exception e)
            {
               e.getMessage();
               e.printStackTrace();
 
            }
         //System.out.println(result);
        return finalresult;
    }
     
}


