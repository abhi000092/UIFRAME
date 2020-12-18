package base;


import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {
	protected static ThreadLocal<ExtentTest> test =new ThreadLocal<ExtentTest>();
	private ExtentFactory() {
		
	}
	private static ExtentFactory instance=new ExtentFactory();
	
	public static ExtentFactory getInstance() {
		return instance;
	}
		
	public ExtentTest getExtent() {
		return test.get();
	}
	public void setExtent(ExtentTest extenttestobj) {
		test.set(extenttestobj);
		
	}
	
	public void removeExtent() {
		test.remove();
	}
	
}
