package utilities;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import base.Testbase;


public class ScreenShot extends Testbase {

	
	public static String ScreenShotRegion_withPath (WebDriver driver, By locator, String ScreenshotName, String Masking,String Estimate) throws Exception
    {
          JavascriptExecutor JSE = (JavascriptExecutor) driver;
          DateFormat dateFormat = new SimpleDateFormat("hhmmssaa");
          //String Filenameval= ScreenshotName+"_"+ dateFormat.format(new Date())+".png";
          String Filenameval= ScreenshotName+".png";
          String NewFileNamePath1=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Actual\\"+Filenameval;
          
          WebElement element = driver.findElement(locator);
          JSE.executeScript("arguments[0].scrollIntoView(true);", element);
          Thread.sleep(1000);

          //Get the entire Screenshot from the driver of passed WebElement
          File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
          FileUtils.copyFile(screen, new File(NewFileNamePath1));
          
          

          //Create an instance of Buffered Image from captured screenshot
          BufferedImage img = ImageIO.read(screen);

          // Get the Width and Height of the WebElement using
          int elementWidth = element.getSize().getWidth();
          Long value = null ;
          try {
              value = (Long) JSE.executeScript("return window.scrollY;");
          } catch (ClassCastException cce) {
              Double dValue = (Double) JSE.executeScript("return window.scrollY;");
              value = Math.round(dValue);
          }
          int iScrollHeight = value.intValue();
          int elementHeight = element.getSize().getHeight();
          
//        if (elementHeight > img.getHeight())
//        {
//              elementHeight = (img.getHeight()*3)/4;
//        }

          //Create a rectangle using Width and Height
          Rectangle rect = new Rectangle(elementWidth, elementHeight);

          //Get the Location of WebElement in a Point.
          //This will provide X & Y co-ordinates of the WebElement
          Point p = element.getLocation();

          //Create image by for element using its location and size.
          //This will give image data specific to the WebElement
          BufferedImage dest = null;
          try
          {
                dest = img.getSubimage(p.getX(), p.getY(), rect.width+650, rect.height+300);
          }
          catch (RasterFormatException e)
          {
                dest = img;
          }

          //Write back the image data for element in File object
          ImageIO.write(dest, "png", screen);

          FileUtils.copyFile(screen, new File(NewFileNamePath1));
          return Filenameval;
    }
	
	
	public String ScreenShotRegion_withPath_old (WebDriver driver, By locator, String ScreenshotName, String NewFileNamePath) throws Exception
    {
          JavascriptExecutor JSE = (JavascriptExecutor) driver;
          DateFormat dateFormat = new SimpleDateFormat("hhmmssaa");
          String Filenameval= ScreenshotName+"_"+ dateFormat.format(new Date())+".png";
          String NewFileNamePath1=NewFileNamePath+"\\"+Filenameval;
          
          WebElement element = driver.findElement(locator);
        JSE.executeScript("arguments[0].scrollIntoView(true);", element);
          Thread.sleep(1000);

          //Get the entire Screenshot from the driver of passed WebElement
          File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
          FileUtils.copyFile(screen, new File(NewFileNamePath1));

          //Create an instance of Buffered Image from captured screenshot
          BufferedImage img = ImageIO.read(screen);

          // Get the Width and Height of the WebElement using
          int elementWidth = element.getSize().getWidth();
          Long value = (Long) JSE.executeScript("return window.scrollY;");
          int iScrollHeight = value.intValue();
          int elementHeight = element.getSize().getHeight();
          
//        if (elementHeight > img.getHeight())
//        {
//              elementHeight = (img.getHeight()*3)/4;
//        }

          //Create a rectangle using Width and Height
          Rectangle rect = new Rectangle(elementWidth, elementHeight);

          //Get the Location of WebElement in a Point.
          //This will provide X & Y co-ordinates of the WebElement
          Point p = element.getLocation();

          //Create image by for element using its location and size.
          //This will give image data specific to the WebElement
          BufferedImage dest = null;
          try
          {
                dest = img.getSubimage(p.getX(), p.getY(), rect.width+650, rect.height+300);
          }
          catch (RasterFormatException e)
          {
                dest = img;
          }

          //Write back the image data for element in File object
          ImageIO.write(dest, "png", screen);

          FileUtils.copyFile(screen, new File(NewFileNamePath1));
          return Filenameval;
    }

//	public void ImageCompare() throws Exception{
//        
//        String sFile1 = "E:\\ImageMagick-7.0.7-28-portable-Q16-x64\\images\\Screen1.png";
//        String sFile2 = "E:\\ImageMagick-7.0.7-28-portable-Q16-x64\\images\\Screen2.png";          
//        imageComparison(String File1,String File2, String Difference);
//  }

//
	
public static String imageComparison(String File1,String File2, String Difference,String Masking,String Estimate) throws Exception{
    String ScreenShotStatus="";
    String ImageMagick_Loc = System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\ImageMagick-7.0.7-28-portable-Q16-x64";
   
    String CMD1= "cmd /K ";
    
    String Result= System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\ImageMagick-7.0.7-28-portable-Q16-x64\\results.txt";
    String Command1=ImageMagick_Loc+" magick compare -metric AE -fuzz 10% "+File1 +" "+File2+ " "+Difference+" 2>E:\\results.txt";
   String cmd = ImageMagick_Loc+" compare "+File1+" "+File2+" "+Difference;    
   String pCmd=CMD1+Command1;
   String Path = System.getProperty("user.home");
   String sFile1=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Base\\"+File1;
   String sFile2=System.getProperty("user.dir")+"\\src\\test\\resources\\Documents\\"+Estimate+"\\Actual\\"+File2;
  
  String Differencepath= System.getProperty("user.dir")+ "\\src\\test\\resources\\Documents\\"+Estimate+"\\Difference\\"+Difference;
  //System.out.println("Base File path:- "+sFile1);
  //System.out.println("Actual File path:- "+sFile2);
  //System.out.println("Diff File path:- "+Differencepath);
  test.log(Status.INFO, "Base File path:- "+sFile1);
  test.log(Status.INFO, "Actual File path:- "+sFile2);
  test.log(Status.INFO, "Diff File path:- "+Differencepath);
  


  int returnValue = -1;
  
  if (Masking == "Yes"){

    
    String MastedFile=System.getProperty("user.dir")+"\\InputTestData\\ScreenShotValidation\\Engineering\\MaskedImages\\"+File1;
    String BaseMakedImahe = System.getProperty("user.dir")+"\\InputTestData\\ScreenShotValidation\\Engineering\\TempMasking\\"+File1;
    //System.out.println( "Base Masking Image " +BaseMakedImahe);
    String ActualMakedImahe = System.getProperty("user.dir")+"\\InputTestData\\ScreenShotValidation\\Engineering\\TempMasking\\"+File2;
    //System.out.println( "Actual Masking Image " +ActualMakedImahe);

    
   // String cmd1 = ImageMagick_Loc+" convert "+sFile1+" "+MastedFile+" -composite "+ BaseMakedImahe;
    Process p = Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64\\magick.exe convert "+sFile1+" "+MastedFile+" -composite "+ BaseMakedImahe);
      System.out.println( "Convert Base Image  - " + System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64\\magick.exe convert "+sFile1+" "+MastedFile+" -composite "+ BaseMakedImahe);
      
    Process p1 = Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64\\magick.exe convert "+sFile2+" "+MastedFile+" -composite "+ ActualMakedImahe);
      System.out.println( "Convert Actual Image  is - " + System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64\\magick.exe convert "+sFile2+" "+MastedFile+" -composite "+ ActualMakedImahe);
      
//      Thread.sleep(5000);
//      Process p2 = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd "+System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64 && magick.exe compare -metric AE -fuzz 10% "+BaseMakedImahe+" "+ActualMakedImahe+" "+Differencepath+" 2>"+System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64\\results.txt\"");
//      System.out.println( "Path is - " + Path);
  Thread.sleep(4000);
      Process p2 = Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64\\magick.exe compare -metric AE -fuzz 10% "+BaseMakedImahe+" "+ActualMakedImahe+" "+Differencepath);

      System.out.println( "Compare Masked Image to Actual Image is - " + System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64\\magick.exe compare -metric AE -fuzz 10% "+BaseMakedImahe+" "+ActualMakedImahe+" "+Differencepath);
      
      InputStream in = p2.getErrorStream();
      byte b[] = new byte[1024];
      StringBuffer buffer = new StringBuffer();
      
//      FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64\\results.txt");
      int r = in.read(b);
      while (r != -1) {
          buffer.append(new String(b, 0, r));
          r = in.read();
      }
      
      returnValue = Integer.parseInt(buffer.toString());
    
  }else
  {
    
      //Process p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd D:\\IQuote_HTML5_Selenium\\ImageMagick-7.0.3-0-portable-Q16-x64 && magick.exe compare -metric AE ..\\InputTestData\\ScreenShotValidation\\Engineering\\Actual\\Eng_125506PM.png ..\\InputTestData\\ScreenShotValidation\\Engineering\\Base\\Eng.png ..\\InputTestData\\ScreenShotValidation\\Engineering\\Difference\\EngDiff.png 2>results.txt\"");
      //Process p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd "+System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64 && magick.exe compare -metric AE -fuzz 10% "+sFile1+" "+sFile2+" "+Differencepath+" 2>"+System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64\\results.txt\"");

      Process p = Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\ImageMagick-7.0.7-28-portable-Q16-x64\\magick.exe compare -metric AE -fuzz 10% "+sFile1+" "+sFile2+" "+Differencepath);

      //System.out.println( "Path is - " + Path);
      
      InputStream in = p.getErrorStream();
      byte b[] = new byte[1024];
      StringBuffer buffer = new StringBuffer();
      
//      FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64\\results.txt");
      int r = in.read(b);
      while (r != -1) {
          buffer.append(new String(b, 0, r));
          r = in.read();
      }
      
      returnValue = Integer.parseInt(buffer.toString());
      
//        out.close();
      
  }

   
    //System.out.println("Killing all CMD Processes");
   Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
  
   
   //Below code if for getting the value from text file and printing the result
   BufferedReader br = null;
   String sCurrentLine;

//   br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64\\results.txt"));
//
//   while ((sCurrentLine = br.readLine()) != null) {
//       System.out.println(sCurrentLine);
   
   
  // System.out.println("Return value of Compare is - " + returnValue);
       int DifferenceValue = returnValue;
       
       if(DifferenceValue == 0)
       {
       System.out.println("Screenshot Validation Pass");
       ScreenShotStatus="Pass";
       }
       if(DifferenceValue >0 && DifferenceValue <= 100)
       {
       System.out.println("Screenshot Validation Warning");
       ScreenShotStatus="Warning";
       }
       
       if(DifferenceValue > 100)
       {
           System.err.println("Screenshot Validation Failed");
              ScreenShotStatus="Fail";
       }
 //  }
//       else
//       {
//            System.err.println("Screenshot Validation Failed");
//            ScreenShotStatus="Fail";
//       }
// return sCurrentLine;
   
   
//   File file = new File(System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64\\results.txt"); 
//   
//   if(file.delete()) 
//   { 
//       System.out.println("File deleted successfully"); 
//   } 
//   else
//   { 
//       System.out.println("Failed to delete the file"); 
//   } 
//
      
        return ScreenShotStatus;
}


	
	
	
public String imageComparison_old(String File1,String File2, String Difference) throws Exception{
        
         String ImageMagick_Loc = System.getProperty("user.dir")+"\\ImageMagick-7.0.3-0-portable-Q16-x64";
        //String DiffImage_Loc   = "E:\\ImageMagick-7.0.7-28-portable-Q16-x64\\images\\difference.png"; 
         String CMD1= "cmd /K ";
         
         String Result= System.getProperty("user.dir")+"\\ImageMagick-7.0.3-0-portable-Q16-x64\\results.txt";
         String Command1=ImageMagick_Loc+" magick compare -metric AE "+File1 +" "+File2+ " "+Difference+" 2>D:\\results.txt";
        String cmd = ImageMagick_Loc+" compare "+File1+" "+File2+" "+Difference;    
        String pCmd=CMD1+Command1;
        String Path = System.getProperty("user.home");
        String sFile1="..\\InputTestData\\ScreenShotValidation\\Engineering\\Base\\"+File1;
        String sFile2="..\\InputTestData\\ScreenShotValidation\\Engineering\\Actual\\"+File2;
		String Differencepath= "..\\InputTestData\\ScreenShotValidation\\Engineering\\Difference\\"+Difference;
       
        //Process p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd D:\\IQuote_HTML5_Selenium\\ImageMagick-7.0.3-0-portable-Q16-x64 && magick.exe compare -metric AE ..\\InputTestData\\ScreenShotValidation\\Engineering\\Actual\\Eng_125506PM.png ..\\InputTestData\\ScreenShotValidation\\Engineering\\Base\\Eng.png ..\\InputTestData\\ScreenShotValidation\\Engineering\\Difference\\EngDiff.png 2>results.txt\"");
        Process p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd C:\\WorkSpaceIquote\\IQuote_HTML5_Selenium\\ImageMagick-7.0.3-0-portable-Q16-x64 && magick.exe compare -metric AE "+sFile1+" "+sFile2+" "+Differencepath+" 2>results.txt\"");
      //  System.out.println( "Path is - " + Path);
        
        Thread.sleep(5000);
        Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
        Thread.sleep(3000); 
        
        //Below code if for getting the value from text file and printing the result
        BufferedReader br = null;
        String sCurrentLine;

        br = new BufferedReader(new FileReader("C:\\WorkSpaceIquote\\IQuote_HTML5_Selenium\\ImageMagick-7.0.3-0-portable-Q16-x64\\results.txt"));

        while ((sCurrentLine = br.readLine()) != null) {
           // System.out.println(sCurrentLine);
            if(sCurrentLine.equals("0"))
            {
            	System.out.println("Screenshot Validation Pass");
            }
            else
            {
            	System.err.println("ScreenshotValidation Failed");
            }
     return sCurrentLine;
    }
		return sCurrentLine;
  }

public String imageComparison_1(String File1,String File2, String Difference) throws Exception{
	int returnValue;
    String ImageMagick_Loc = System.getProperty("user.dir")+"\\ImageMagick-7.0.3-0-portable-Q16-x64";
   //String DiffImage_Loc   = "E:\\ImageMagick-7.0.7-28-portable-Q16-x64\\images\\difference.png"; 
    String CMD1= "cmd /K ";
    
    String Result= System.getProperty("user.dir")+"\\ImageMagick-7.0.3-0-portable-Q16-x64\\results.txt";
    String Command1=ImageMagick_Loc+" magick compare -metric AE "+File1 +" "+File2+ " "+Difference+" 2>D:\\results.txt";
   String cmd = ImageMagick_Loc+" compare "+File1+" "+File2+" "+Difference;    
   String pCmd=CMD1+Command1;
   String Path = System.getProperty("user.home");
   String sFile1=System.getProperty("user.dir")+"\\InputTestData\\ScreenShotValidation\\Engineering\\Base\\"+File1;
   String sFile2=System.getProperty("user.dir")+"\\InputTestData\\ScreenShotValidation\\Engineering\\Actual\\"+File2;
	String Differencepath= System.getProperty("user.dir")+"\\InputTestData\\ScreenShotValidation\\Engineering\\Difference\\"+Difference;
  
   //Process p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd D:\\IQuote_HTML5_Selenium\\ImageMagick-7.0.3-0-portable-Q16-x64 && magick.exe compare -metric AE ..\\InputTestData\\ScreenShotValidation\\Engineering\\Actual\\Eng_125506PM.png ..\\InputTestData\\ScreenShotValidation\\Engineering\\Base\\Eng.png ..\\InputTestData\\ScreenShotValidation\\Engineering\\Difference\\EngDiff.png 2>results.txt\"");
	Process p = Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\ImageMagick-7.0.3-0-portable-Q16-x64\\magick.exe compare -metric AE -fuzz 10% "+sFile1+" "+sFile2+" "+Differencepath);

   // System.out.println( "Path is - " + Path);
    
    InputStream in = p.getErrorStream();
    byte b[] = new byte[1024];
    StringBuffer buffer = new StringBuffer();
    
//    FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")+"\\ImageMagick-7.0.7-28-portable-Q16-x64\\results.txt");
    int r = in.read(b);
    while (r != -1) {
  	  buffer.append(new String(b, 0, r));
  	  r = in.read();
    }
    
    returnValue = Integer.parseInt(buffer.toString());
    

   
   Thread.sleep(5000);
   Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
   Thread.sleep(3000); 
   
   //Below code if for getting the value from text file and printing the result
   BufferedReader br = null;
   String sCurrentLine=Integer.toString(returnValue);

 //  br = new BufferedReader(new FileReader("C:\\WorkSpaceIquote\\IQuote_HTML5_Selenium\\ImageMagick-7.0.3-0-portable-Q16-x64\\results.txt"));

  
      // System.out.println(sCurrentLine);
       if(sCurrentLine.equals("0"))
       {
       	System.out.println("Screenshot Validation Pass");
       }
       else
       {
       	System.err.println("ScreenshotValidation Failed");
       }


	return sCurrentLine;
}

public void BufferedReaderExample()throws Exception
{
        

     
        	BufferedReader br = null;
            String sCurrentLine;

            br = new BufferedReader(new FileReader("D:\\IQuote_HTML5_Selenium\\ImageMagick-7.0.3-0-portable-Q16-x64\\results.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                if(sCurrentLine.equals("0"))
                {
                	System.err.println("Screenshot Validation failed");
                }
                else
                {
                	System.out.println("ScreenshotValidation Passed");
                }
        
        }

    
}


}
