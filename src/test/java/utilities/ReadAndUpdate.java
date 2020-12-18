package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.apache.poi.ss.usermodel.*;

public class ReadAndUpdate {

	
	Connection conn=null;
	Statement stmt=null;
	String sql="";
	ResultSet rs=null; 
	int rs1 ;
	int iRowCount;
	String sValue = null;
	String sValue1 = null;
	public static String ExcelPath1 = "";
	private static String ExcelPath;
	
	public ReadAndUpdate()
	{
		final String dir = System.getProperty("user.dir");
		ExcelPath = dir.concat(ExcelPath1);
		System.setProperty("org.apache.poi.util.POILogger", "org.apache.commons.logging.impl.NoOpLog");
	}
	
	public String ReadFunction(String SheetName, String ScenarioName, String TestCase, String ReadColumnName)
	{
		String ReadColumnValue = "";
		
		FileInputStream ExcelInput = null;
		HSSFWorkbook ExcelBook = null;
		HSSFSheet ExcelSheet = null;
		HSSFRow ExcelRow = null;
		
		try
		{
			ExcelInput = new FileInputStream(ExcelPath);
			ExcelBook = new HSSFWorkbook(ExcelInput);
			ExcelSheet = ExcelBook.getSheet(SheetName);

			int ScenraioColIndex = -1, TestCaseColIndex = -1, ReadColumnIndex = -1;

			ExcelRow = ExcelSheet.getRow(0);
			for (Cell cell : ExcelRow)
			{
				String sColName= cell.getRichStringCellValue().getString().trim();
				if (sColName.equals("Scenario")) {
					ScenraioColIndex = cell.getColumnIndex();
				} else if (sColName.equals("Testcase")) {
					TestCaseColIndex = cell.getColumnIndex();
				} else if (sColName.equals(ReadColumnName)) {
					ReadColumnIndex = cell.getColumnIndex();
				}

				if (ScenraioColIndex >= 0 && TestCaseColIndex >= 0 && ReadColumnIndex >= 0) {
					break;
				}
			}

			for (Row eachRow : ExcelSheet)
			{
				try {
					String Data1 = eachRow.getCell(ScenraioColIndex).getRichStringCellValue().getString().trim();
					String Data2 = eachRow.getCell(TestCaseColIndex).getRichStringCellValue().getString().trim();
					if (Data1.equals(ScenarioName) && Data2.equals(TestCase))
					{
						ReadColumnValue = eachRow.getCell(ReadColumnIndex).getRichStringCellValue().getString().trim();
						break;
					}
				} catch (NullPointerException e) {
				}
			}
			//ExcelBook.close();
			ExcelInput.close();

			System.out.println("For SheetName = "+SheetName+", TestCase = "+TestCase+", '"+ReadColumnName+"' = "+ReadColumnValue);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ExcelInput = null;
			ExcelBook = null;
			ExcelSheet = null;
			ExcelRow = null;
		}
		
		return ReadColumnValue;
	}

	public void UpdateFunction(String SheetName,String ScenarioName, String TestCase, String UpdateColumnName, String UpdateColumnValue)
	{
		FileOutputStream Ouput = null;
		FileInputStream ExcelInput = null;
		HSSFWorkbook ExcelBook = null;
		HSSFSheet ExcelSheet = null;
		HSSFRow ExcelRow = null;
		
		try
		{
			ExcelInput = new FileInputStream(ExcelPath);
			ExcelBook = new HSSFWorkbook(ExcelInput);
			ExcelSheet = ExcelBook.getSheet(SheetName);

			int ScenraioColIndex = -1, TestCaseColIndex = -1, UpdateColumnIndex = -1;

			ExcelRow = ExcelSheet.getRow(0);
			for (Cell cell : ExcelRow)
			{
				String sColName= cell.getRichStringCellValue().getString().trim();
				if (sColName.equals("Scenario")) {
					ScenraioColIndex = cell.getColumnIndex();
				} else if (sColName.equals("Testcase")) {
					TestCaseColIndex = cell.getColumnIndex();
				} else if (sColName.equals(UpdateColumnName)) {
					UpdateColumnIndex = cell.getColumnIndex();
				}

				if (ScenraioColIndex >= 0 && TestCaseColIndex >= 0 && UpdateColumnIndex >= 0) {
					break;
				}
			}

			for (Row eachRow : ExcelSheet)
			{
				try {
					String Data1 = eachRow.getCell(ScenraioColIndex).getRichStringCellValue().getString().trim();
					String Data2 = eachRow.getCell(TestCaseColIndex).getRichStringCellValue().getString().trim();
					if (Data1.equals(ScenarioName) && Data2.equals(TestCase))
					{
						if (eachRow.getCell(UpdateColumnIndex) == null) {
							eachRow.createCell(UpdateColumnIndex);
						}
						eachRow.getCell(UpdateColumnIndex).setCellValue(UpdateColumnValue);
						break;
					}
				} catch (NullPointerException e) {
				}
			}
			ExcelInput.close();
			
			Ouput = new FileOutputStream(ExcelPath);
			ExcelBook.write(Ouput);
			Ouput.close();
			
			System.out.println("For SheetName = "+SheetName+", TestCase = "+TestCase+", updated the '"+UpdateColumnName+"' = "+UpdateColumnValue);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			ExcelInput = null;
			ExcelBook = null;
			ExcelSheet = null;
			ExcelRow = null;
		}
	}

	public void UpdateFunction1(String ExcelPathval,String SheetName,String ScenarioName, String TestCase, String UpdateColumnName, String UpdateColumnValue)
	{
		FileOutputStream Ouput = null;
		FileInputStream ExcelInput = null;
		HSSFWorkbook ExcelBook = null;
		HSSFSheet ExcelSheet = null;
		HSSFRow ExcelRow = null;
		
		try
		{
			ExcelInput = new FileInputStream(ExcelPathval);
			ExcelBook = new HSSFWorkbook(ExcelInput);
			ExcelSheet = ExcelBook.getSheet(SheetName);

			int ScenraioColIndex = -1, TestCaseColIndex = -1, UpdateColumnIndex = -1;

			ExcelRow = ExcelSheet.getRow(0);
			for (Cell cell : ExcelRow)
			{
				String sColName= cell.getRichStringCellValue().getString().trim();
				if (sColName.equals("Scenario")) {
					ScenraioColIndex = cell.getColumnIndex();
				} else if (sColName.equals("Testcase")) {
					TestCaseColIndex = cell.getColumnIndex();
				} else if (sColName.equals(UpdateColumnName)) {
					UpdateColumnIndex = cell.getColumnIndex();
				}

				if (ScenraioColIndex >= 0 && TestCaseColIndex >= 0 && UpdateColumnIndex >= 0) {
					break;
				}
			}

			for (Row eachRow : ExcelSheet)
			{
				try {
					String Data1 = eachRow.getCell(ScenraioColIndex).getRichStringCellValue().getString().trim();
					String Data2 = eachRow.getCell(TestCaseColIndex).getRichStringCellValue().getString().trim();
					if (Data1.equals(ScenarioName) && Data2.equals(TestCase))
					{
						if (eachRow.getCell(UpdateColumnIndex) == null) {
							eachRow.createCell(UpdateColumnIndex);
						}
						eachRow.getCell(UpdateColumnIndex).setCellValue(UpdateColumnValue);
						break;
					}
				} catch (NullPointerException e) {
				}
			}
			ExcelInput.close();
			
			Ouput = new FileOutputStream(ExcelPath);
			ExcelBook.write(Ouput);
			Ouput.close();
			
			System.out.println("For SheetName = "+SheetName+", TestCase = "+TestCase+", updated the '"+UpdateColumnName+"' = "+UpdateColumnValue);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			ExcelInput = null;
			ExcelBook = null;
			ExcelSheet = null;
			ExcelRow = null;
		}
	}
	
	public void UpdateFunction_DifferentFile(String FileName,String SheetName,String ScenarioName, String TestCase, String UpdateColumnName, String UpdateColumnValue)
	{
		FileOutputStream Ouput = null;
		FileInputStream ExcelInput = null;
		HSSFWorkbook ExcelBook = null;
		HSSFSheet ExcelSheet = null;
		HSSFRow ExcelRow = null;
		
		try
		{
			ExcelInput = new FileInputStream(FileName);
			ExcelBook = new HSSFWorkbook(ExcelInput);
			ExcelSheet = ExcelBook.getSheet(SheetName);

			int ScenraioColIndex = -1, TestCaseColIndex = -1, UpdateColumnIndex = -1;

			ExcelRow = ExcelSheet.getRow(0);
			for (Cell cell : ExcelRow)
			{
				String sColName= cell.getRichStringCellValue().getString().trim();
				if (sColName.equals("Scenario")) {
					ScenraioColIndex = cell.getColumnIndex();
				} else if (sColName.equals("Testcase")) {
					TestCaseColIndex = cell.getColumnIndex();
				} else if (sColName.equals(UpdateColumnName)) {
					UpdateColumnIndex = cell.getColumnIndex();
				}

				if (ScenraioColIndex >= 0 && TestCaseColIndex >= 0 && UpdateColumnIndex >= 0) {
					break;
				}
			}

			for (Row eachRow : ExcelSheet)
			{
				try {
					String Data1 = eachRow.getCell(ScenraioColIndex).getRichStringCellValue().getString().trim();
					String Data2 = eachRow.getCell(TestCaseColIndex).getRichStringCellValue().getString().trim();
					if (Data1.equals(ScenarioName) && Data2.equals(TestCase))
					{
						if (eachRow.getCell(UpdateColumnIndex) == null) {
							eachRow.createCell(UpdateColumnIndex);
						}
						eachRow.getCell(UpdateColumnIndex).setCellValue(UpdateColumnValue);
						break;
					}
				} catch (NullPointerException e) {
				}
			}
			ExcelInput.close();
			
			Ouput = new FileOutputStream(ExcelPath);
			ExcelBook.write(Ouput);
			Ouput.close();
			
			System.out.println("For SheetName = "+SheetName+", TestCase = "+TestCase+", updated the '"+UpdateColumnName+"' = "+UpdateColumnValue);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			ExcelInput = null;
			ExcelBook = null;
			ExcelSheet = null;
			ExcelRow = null;
		}
	}

	
	public HashMap<String,String> ReadAllDataFunction(String SheetName,String ScenarioName, String TestCase) throws Exception
	{
		FileInputStream ExcelInput = null;
		HSSFWorkbook ExcelBook = null;
		HSSFSheet ExcelSheet = null;
		HSSFRow ExcelRow = null;
		
		HashMap<String,String> TempHasMap=new HashMap<String, String>();
		List<String> ColumnNameList=new ArrayList<String>();
		
		try
		{
			ExcelInput = new FileInputStream(ExcelPath);
			ExcelBook = new HSSFWorkbook(ExcelInput);
			ExcelSheet = ExcelBook.getSheet(SheetName);
			
			int ColumnCount = 0, ScenraioColIndex = -1, TestCaseColIndex = -1;
			
			ExcelRow = ExcelSheet.getRow(0);
			for(Cell celvalue : ExcelRow)
			{	
				ColumnCount=ColumnCount+1;
				ColumnNameList.add(celvalue.getStringCellValue());
				
				if (ScenarioName.equals("Scenario")) {
					ScenraioColIndex = celvalue.getColumnIndex();
				} else if (TestCase.equals("Testcase")) {
					TestCaseColIndex = celvalue.getColumnIndex();
				}
			}
			
			for (Row eachRow : ExcelSheet)
			{
				String Data1 = eachRow.getCell(ScenraioColIndex).getRichStringCellValue().getString().trim();
				String Data2 = eachRow.getCell(TestCaseColIndex).getRichStringCellValue().getString().trim();
				if (Data1.equals(ScenarioName) && Data2.equals(TestCase)) {						
					for (int i = 0; i < ColumnCount; i++) {
						TempHasMap.put(ColumnNameList.get(i), eachRow.getCell(i).getRichStringCellValue().getString().trim());
					}
					break;						
				}
			}			
			//ExcelBook.close();
			ExcelInput.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ExcelInput = null;
			ExcelBook = null;
			ExcelSheet = null;
			ExcelRow = null;
		}
		
		return TempHasMap;
	}
	
	public String ReadCellValue(String SheetName, String KeyColName, String KeyColValue, String ReadColumnName)
	{
		String ReadColumnValue = "";
		
		FileInputStream ExcelInput = null;
		HSSFWorkbook ExcelBook = null;
		HSSFSheet ExcelSheet = null;
		HSSFRow ExcelRow = null;
		
		try
		{
			ExcelInput = new FileInputStream(ExcelPath);
			ExcelBook = new HSSFWorkbook(ExcelInput);
			ExcelSheet = ExcelBook.getSheet(SheetName);

			int KeyColIndex = -1, ReadColumnIndex = -1;

			ExcelRow = ExcelSheet.getRow(0);
			for (Cell cell : ExcelRow)
			{
				String sColName= cell.getRichStringCellValue().getString().trim();
				if (sColName.equals(KeyColName)) {
					KeyColIndex = cell.getColumnIndex();
				} else if (sColName.equals(ReadColumnName)) {
					ReadColumnIndex = cell.getColumnIndex();
				}

				if (KeyColIndex >= 0 && ReadColumnIndex >= 0) {
					break;
				}
			}

			for (Row eachRow : ExcelSheet)
			{
				String Data1 = eachRow.getCell(KeyColIndex).getRichStringCellValue().getString().trim();
				if (Data1.equals(KeyColValue))
				{
					ReadColumnValue = eachRow.getCell(ReadColumnIndex).getRichStringCellValue().getString().trim();
					break;
				}
			}
			//ExcelBook.close();
			ExcelInput.close();

			System.out.println("For "+KeyColName+" = "+KeyColValue+", '"+ReadColumnName+"' = "+ReadColumnValue);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ExcelInput = null;
			ExcelBook = null;
			ExcelSheet = null;
			ExcelRow = null;
		}
		
		return ReadColumnValue;
	}

	public void UpdateCellValue(String SheetName, String KeyColName, String KeyColValue, String UpdateColumnName, String UpdateColumnValue)
	{
		FileOutputStream Ouput = null;
		FileInputStream ExcelInput = null;
		HSSFWorkbook ExcelBook = null;
		HSSFSheet ExcelSheet = null;
		HSSFRow ExcelRow = null;
		
		try
		{
			ExcelInput = new FileInputStream(ExcelPath);
			ExcelBook = new HSSFWorkbook(ExcelInput);
			ExcelSheet = ExcelBook.getSheet(SheetName);

			int KeyColIndex = -1, UpdateColumnIndex = -1;

			ExcelRow = ExcelSheet.getRow(0);
			for (Cell cell : ExcelRow)
			{
				String sColName= cell.getRichStringCellValue().getString().trim();
				if (sColName.equals(KeyColName)) {
					KeyColIndex = cell.getColumnIndex();
				} else if (sColName.equals(UpdateColumnName)) {
					UpdateColumnIndex = cell.getColumnIndex();
				}

				if (KeyColIndex >= 0 && UpdateColumnIndex >= 0) {
					break;
				}
			}

			for (Row eachRow : ExcelSheet)
			{
				String Data1 = eachRow.getCell(KeyColIndex).getRichStringCellValue().getString().trim();
				if (Data1.equals(KeyColValue))
				{
					if (eachRow.getCell(UpdateColumnIndex) == null) {
						eachRow.createCell(UpdateColumnIndex);
					}
					eachRow.getCell(UpdateColumnIndex).setCellValue(UpdateColumnValue);
					break;
				}
			}
			ExcelInput.close();
			
			Ouput = new FileOutputStream(ExcelPath);
			ExcelBook.write(Ouput);
			Ouput.close();
			
			System.out.println("For SheetName = "+SheetName+", updated the '"+UpdateColumnName+"' = "+UpdateColumnValue);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			ExcelInput = null;
			ExcelBook = null;
			ExcelSheet = null;
			ExcelRow = null;
		}
	}

	public String readCellValueByRowNumber(String ExcelFilePath, String SheetName, int RowNumber, String ColumnName)
	{
		FileInputStream input = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		String sFilePath = "";
		
		String sReadValue = "";

		try
		{
			int iColNum = -1;

			sFilePath = ExcelPath;

			input = new FileInputStream(sFilePath);
			workbook = new HSSFWorkbook(input);
			sheet = workbook.getSheet(SheetName);
			row = sheet.getRow(0);
			for (Cell cell : row)
			{
				String sColName= cell.getRichStringCellValue().getString().trim();						    
				if (sColName.equals(ColumnName))
				{
					iColNum = cell.getColumnIndex();
				}				
			}

			sReadValue = sheet.getRow(RowNumber).getCell(iColNum).getRichStringCellValue().getString().trim();
			//workbook.close();
			input.close();
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
		finally 
		{
			try
			{
				input = null;
				workbook = null;
				sheet = null;
				row = null;
			}
			catch(Exception e){System.err.println(e);}
		}
		
		return sReadValue;
	}
	
	public void updateCellValueByRowNumber(String ExcelFilePath, String SheetName, int RowNumber, String ColumnName, String UpdateValue)
	{
		FileInputStream input = null;
		FileOutputStream output = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		String sFilePath = "";

		try
		{
			int iColNum = -1;

			if (ExcelFilePath.isEmpty())
			{
				sFilePath = ExcelPath;
			}
			else
			{
				sFilePath = ExcelFilePath;
			}

			input = new FileInputStream(sFilePath);
			workbook = new HSSFWorkbook(input);
			sheet = workbook.getSheet(SheetName);
			row = sheet.getRow(0);
			for (Cell cell : row)
			{
				String sColName= cell.getRichStringCellValue().getString().trim();						    
				if (sColName.equals(ColumnName))
				{
					iColNum = cell.getColumnIndex();
				}				
			}

			if (sheet.getRow(RowNumber).getCell(iColNum) == null) {
				sheet.getRow(RowNumber).createCell(iColNum);
			}
			sheet.getRow(RowNumber).getCell(iColNum).setCellValue(UpdateValue);
			input.close();

			output = new FileOutputStream(sFilePath);
			workbook.write(output);
			output.close();
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
		finally 
		{
			try
			{
				input = null;
				workbook = null;
				sheet = null;
				row = null;
			}
			catch(Exception e){System.err.println(e);}
		}
	}
	
	public int getRowNumber(String ExcelFilePath, String SheetName, String ColumnName, String ColumnValue)
	{
		FileInputStream input = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		String sFilePath = "";
		int iRowNumber = -1;

		try
		{
			int iColNum = -1;

			if (ExcelFilePath.isEmpty())
			{
				sFilePath = ExcelPath;
			}
			else
			{
				sFilePath = ExcelFilePath;
			}

			input = new FileInputStream(sFilePath);
			workbook = new HSSFWorkbook(input);
			sheet = workbook.getSheet(SheetName);
			row = sheet.getRow(0);
			for (Cell cell : row)
			{
				String sColName = cell.getRichStringCellValue().getString().trim();						    
				if (sColName.equals(ColumnName))
				{
					iColNum = cell.getColumnIndex();
				}
			}

			for (Row eachRow : sheet)
			{
				String sValue = eachRow.getCell(iColNum).getRichStringCellValue().getString().trim();
				if (sValue.equals(ColumnValue))
				{
					iRowNumber = eachRow.getRowNum();
					break;
				}
			}
			//workbook.close();
			input.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				input = null;
				workbook = null;
				sheet = null;
				row = null;
			}
			catch(Exception e){System.err.println(e);}
		}
		
		return iRowNumber;
	}
	
	
	
	public boolean CompareExcelSheet(String ExcelSheetPath, String SheetName1, String SheetName2)
	{
	      boolean Comp1 = false;
	      try {
	            
	        ArrayList arr1 = new ArrayList();
	        ArrayList arr2 = new ArrayList();
	        ArrayList arr3 = new ArrayList();
	        ArrayList arr4 = new ArrayList();
	        FileInputStream file1 = new FileInputStream(new File(
	                  ExcelSheetPath));

	        FileInputStream file2 = new FileInputStream(new File(
	                  ExcelSheetPath));

	        // Get the workbook instance for XLS file
	        HSSFWorkbook workbook1 = new HSSFWorkbook(file1);
	        HSSFWorkbook workbook2 = new HSSFWorkbook(file2);

	        // Get first sheet from the workbook
	     //   HSSFSheet sheet1 = workbook1.getSheetAt(0);
	        HSSFSheet sheet1= workbook1.getSheet(SheetName1);
	    //    HSSFSheet sheet2 = workbook2.getSheetAt(0);
	        HSSFSheet sheet2 = workbook2.getSheet(SheetName2);
	        // Compare sheets

	        // Get iterator to all the rows in current sheet1
	        Iterator<Row> rowIterator1 = sheet1.iterator();
	        Iterator<Row> rowIterator2 = sheet2.iterator();

	        while (rowIterator1.hasNext()) {
	            Row row = rowIterator1.next();
	            // For each row, iterate through all the columns
	            Iterator<Cell> cellIterator = row.cellIterator();
	            
	           // System.out.println("Get last row :-"+row.getLastCellNum()); 
	            while (cellIterator.hasNext()) {

	                Cell cell = cellIterator.next();
	                 // System.out.println(cell.getRowIndex());
	                // This is for read only one column from excel
	                if (cell.getColumnIndex() >= 0) {
	                    // Check the cell type and format accordingly
	                    switch (cell.getCellType()) {
	                    case Cell.CELL_TYPE_NUMERIC:
	                       // System.out.print(cell.getNumericCellValue());
	                        arr1.add(cell.getNumericCellValue());
	                        break;
	                    case Cell.CELL_TYPE_STRING:
	                        arr1.add(cell.getStringCellValue());
	                       // System.out.print(cell.getStringCellValue());
	                        break;
	                    case Cell.CELL_TYPE_BOOLEAN:
	                        arr1.add(cell.getBooleanCellValue());
	                       // System.out.print(cell.getBooleanCellValue());
	                        break;
	                    }

	                }

	            }

	            //System.out.println(" ");
	        }

	        file1.close();

	      //  System.out.println("-----------------------------------");
	        // For retrive the second excel data
	        while (rowIterator2.hasNext()) {
	            Row row1 = rowIterator2.next();
	            // For each row, iterate through all the columns
	            Iterator<Cell> cellIterator1 = row1.cellIterator();

	            while (cellIterator1.hasNext()) {

	                Cell cell1 = cellIterator1.next();
	                // Check the cell type and format accordingly

	                // This is for read only one column from excel
	                if (cell1.getColumnIndex() >= 0) {
	                  System.out.println(cell1.getCellType());
	                    switch (cell1.getCellType()) {
	                    
	           
	                    case Cell.CELL_TYPE_NUMERIC:
	                        arr2.add(cell1.getNumericCellValue());
	                      //  System.out.print(cell1.getNumericCellValue());
	                        
	                        break;
	                    case Cell.CELL_TYPE_STRING:
	                        arr2.add(cell1.getStringCellValue());
	                       // System.out.print(cell1.getStringCellValue());
	                        break;
	                    case Cell.CELL_TYPE_BOOLEAN:
	                        arr2.add(cell1.getBooleanCellValue());
	                       // System.out.print(cell1.getBooleanCellValue());
	                        break;

	                    }

	                }
	                // this continue is for
	                // continue;
	            }

	            //System.out.println("");
	        }

	        System.out.println("book1.xls -- " + arr1.size());
	        System.out.println("book1.xls -- " + arr2.size());

	        // compare two arrays
	        for (Object process : arr1) 
	        {
	            if (!arr2.contains(process)) {
	                arr3.add(process);
	                //arr4.add()
	               // System.out.println("False");
	                Comp1=false;
	            }
	            else
	            {
	                //  System.out.println("True");
	                  Comp1=true;
	            }
	            
	            
	        }
	        //System.out.println("arr3 list values - = - = + " + arr3);
	       
	       

	        // closing the files
	        file1.close();
	        file2.close();
	        
	        if (arr3.size()>0)
	        {
	        	System.err.println("Difference in The Excel sheet as as follows:- ");
	        	System.err.println("arr3 list values - = - = + " + arr3);
	        	//Assert.fail("Difference in The Excel sheet is as follows:- " + arr3);
	        	
	        }
	        else
	        {
	        	System.out.println("Difference in The Excel sheet as as follows:- ");
	        	System.out.println("arr3 list values - = - = + " + arr3);
	        }
	        
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	      return Comp1;
	}
	
	
	public boolean CompareExcelSheet_Iquote(String ExcelSheetPath1,String ExcelSheetPath2 ,String SheetName1, String SheetName2)
	{
	      boolean Comp1 = false;
	      try {
	            
	        ArrayList arr1 = new ArrayList();
	        ArrayList arr2 = new ArrayList();
	        ArrayList arr3 = new ArrayList();
	        ArrayList arr4 = new ArrayList();
	        FileInputStream file1 = new FileInputStream(new File(
	        		ExcelSheetPath1));

	        FileInputStream file2 = new FileInputStream(new File(
	        		ExcelSheetPath2));

	        // Get the workbook instance for XLS file
	        XSSFWorkbook workbook1 = new XSSFWorkbook(file1);
	        XSSFWorkbook workbook2 = new XSSFWorkbook(file2);

	        // Get first sheet from the workbook
	     //   HSSFSheet sheet1 = workbook1.getSheetAt(0);
	        XSSFSheet sheet1= workbook1.getSheet(SheetName1);
	    //    HSSFSheet sheet2 = workbook2.getSheetAt(0);
	        XSSFSheet sheet2 = workbook2.getSheet(SheetName2);
	        // Compare sheets

	        // Get iterator to all the rows in current sheet1
	        Iterator<Row> rowIterator1 = sheet1.iterator();
	        Iterator<Row> rowIterator2 = sheet2.iterator();

	        while (rowIterator1.hasNext()) {
	            Row row = rowIterator1.next();
	            // For each row, iterate through all the columns
	            Iterator<Cell> cellIterator = row.cellIterator();
	            
	           // System.out.println("Get last row :-"+row.getLastCellNum()); 
	            while (cellIterator.hasNext()) {

	                Cell cell = cellIterator.next();
	                 // System.out.println(cell.getRowIndex());
	                // This is for read only one column from excel
	                if (cell.getColumnIndex() >= 0) {
	                    // Check the cell type and format accordingly
	                    switch (cell.getCellType()) {
	                    case Cell.CELL_TYPE_NUMERIC:
	                       // System.out.print(cell.getNumericCellValue());
	                        arr1.add(cell.getNumericCellValue());
	                        break;
	                    case Cell.CELL_TYPE_STRING:
	                        arr1.add(cell.getStringCellValue());
	                        //System.out.print(cell.getStringCellValue());
	                        break;
	                    case Cell.CELL_TYPE_BOOLEAN:
	                        arr1.add(cell.getBooleanCellValue());
	                        //System.out.print(cell.getBooleanCellValue());
	                        break;
	                    }

	                }

	            }

	            //System.out.println(" ");
	        }

	        file1.close();

	        //System.out.println("-----------------------------------");
	        // For retrive the second excel data
	        while (rowIterator2.hasNext()) {
	            Row row1 = rowIterator2.next();
	            // For each row, iterate through all the columns
	            Iterator<Cell> cellIterator1 = row1.cellIterator();

	            while (cellIterator1.hasNext()) {

	                Cell cell1 = cellIterator1.next();
	                // Check the cell type and format accordingly

	                // This is for read only one column from excel
	                if (cell1.getColumnIndex() >= 0) {
	                  //System.out.println(cell1.getCellType());
	                    switch (cell1.getCellType()) {
	                    
	           
	                    case Cell.CELL_TYPE_NUMERIC:
	                        arr2.add(cell1.getNumericCellValue());
	                        //System.out.print(cell1.getNumericCellValue());
	                        
	                        break;
	                    case Cell.CELL_TYPE_STRING:
	                        arr2.add(cell1.getStringCellValue());
	                       // System.out.print(cell1.getStringCellValue());
	                        break;
	                    case Cell.CELL_TYPE_BOOLEAN:
	                        arr2.add(cell1.getBooleanCellValue());
	                       // System.out.print(cell1.getBooleanCellValue());
	                        break;

	                    }

	                }
	                // this continue is for
	                // continue;
	            }

	            //System.out.println("");
	        }

	        System.out.println("book1.xls -- " + arr1.size());
	        System.out.println("book1.xls -- " + arr2.size());

	        // compare two arrays
	        for (Object process : arr1) 
	        {
	            if (!arr2.contains(process)) {
	                arr3.add(process);
	                //arr4.add()
	               // System.out.println("False");
	                Comp1=false;
	            }
	            else
	            {
	                //  System.out.println("True");
	                  Comp1=true;
	            }
	            
	            
	        }
	        System.out.println("arr3 list values - = - = + " + arr3);
	       
	       

	        // closing the files
	        file1.close();
	        file2.close();
	        
	        if (arr3.size()>0)
	        {
	        	System.err.println("Difference in The Excel sheet as as follows:- ");
	        	System.err.println("arr3 list values - = - = + " + arr3);
	        	//Assert.fail("Difference in The Excel sheet is as follows:- " + arr3);
	        	Comp1= false;
	        }
	        else
	        {
	        	System.out.println("Difference in The Excel sheet as as follows:- ");
	        	System.out.println("arr3 list values - = - = + " + arr3);
	        	Comp1 =true;
	        }
	        
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return Comp1;
	      
	}
	

	public void UpdateFunction_Iquote(String ExcelPath, String SheetName, String UpdateColumnName, String UpdateColumnValue, int Rownumber)
	{
		FileOutputStream Ouput = null;
		FileInputStream ExcelInput = null;
		XSSFWorkbook ExcelBook = null;
		XSSFSheet ExcelSheet = null;
		XSSFRow ExcelRow = null;
		
		
		//System.out.println("Row NUmber is : "+Rownumber);
		try
		{
			ExcelInput = new FileInputStream(ExcelPath);
			ExcelBook = new XSSFWorkbook(ExcelInput);
			ExcelSheet = ExcelBook.getSheet(SheetName);

			int ScenraioColIndex = -1, TestCaseColIndex = -1, UpdateColumnIndex = -1;

			 Font headerFont = ExcelBook.createFont();
	           //headerFont.setBold(true);
	           
	           headerFont.setFontHeightInPoints((short) 14);
	           headerFont.setColor(IndexedColors.RED.getIndex());

	           // Create a CellStyle with the font
	           CellStyle headerCellStyle = ExcelBook.createCellStyle();
	           headerCellStyle.setFont(headerFont);
			
			ExcelRow = ExcelSheet.getRow(0);
			for (Cell cell : ExcelRow)
			{
				String sColName= cell.getRichStringCellValue().getString().trim();
				 if (sColName.equals(UpdateColumnName)) {
					UpdateColumnIndex = cell.getColumnIndex();
				}

				if (UpdateColumnIndex >= 0) {
					break;
				}
			}
			 ExcelRow=ExcelSheet.getRow(Rownumber);
			if (ExcelRow==null)
			{
			ExcelRow = ExcelSheet.createRow(Rownumber);
			}
			
			
				try {
//					String Data1 = eachRow.getCell(ScenraioColIndex).getRichStringCellValue().getString().trim();
//					String Data2 = eachRow.getCell(TestCaseColIndex).getRichStringCellValue().getString().trim();
//					if (Data1.equals(ScenarioName) && Data2.equals(TestCase))
//					{
//						if (ExcelRow.getCell(UpdateColumnIndex) == null) {
//							ExcelRow.createCell(UpdateColumnIndex);
//						}

					
			               Cell cell = ExcelRow.createCell(UpdateColumnIndex);
			               cell.setCellValue(UpdateColumnValue);
	 		               //System.out.println(cell.getRichStringCellValue());
			               cell.setCellStyle(headerCellStyle);
			   		      
					
				        int rVal=ExcelRow.getRowNum();
				       // System.out.println("get Row NUmber is : "+rVal);
						
						
//					}
				} catch (NullPointerException e) {
				}
			
			ExcelInput.close();
			
			Ouput = new FileOutputStream(ExcelPath);
			ExcelBook.write(Ouput);
			Ouput.close();
			
	//		System.out.println("For SheetName = "+SheetName+", TestCase = "+TestCase+", updated the '"+UpdateColumnName+"' = "+UpdateColumnValue);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			ExcelInput = null;
			ExcelBook = null;
			ExcelSheet = null;
			ExcelRow = null;
		}
	}

	public void Create_Excel(String ExcelPath, String SheetName, String[] CloumnsName) throws IOException
	{
	     {
	   	  //{"Materialtype","Element","Process","Material","Quantity","Measurementunit","Provided","Inventoryitem","Plant","MISJobID"};
	   	  String[] columns = CloumnsName;
	   	  
	           Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

	           /* CreationHelper helps us create instances of various things like DataFormat, 
	              Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
	           CreationHelper createHelper = workbook.getCreationHelper();

	           // Create a Sheet
	           Sheet sheet = workbook.createSheet(SheetName);

	           // Create a Font for styling header cells
	           Font headerFont = workbook.createFont();
	           //headerFont.setBold(true);
	           headerFont.setFontHeightInPoints((short) 14);
	           headerFont.setColor(IndexedColors.RED.getIndex());

	           // Create a CellStyle with the font
	           CellStyle headerCellStyle = workbook.createCellStyle();
	           headerCellStyle.setFont(headerFont);

	           // Create a Row
	           Row headerRow = sheet.createRow(0);

	           // Create cells
	           for(int i = 0; i < columns.length; i++) {
	               Cell cell = headerRow.createCell(i);
	               cell.setCellValue(columns[i]);
	               cell.setCellStyle(headerCellStyle);
	   		        }


	           // Write the output to a file
	           FileOutputStream fileOut = new FileOutputStream(ExcelPath);
	           workbook.write(fileOut);
	           fileOut.close();

	           // Closing the workbook
	          // workbook.close();
	       }
	}
	
	

}
