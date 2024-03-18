package utilities;

import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportFactory {
	
	 private static ExtentReports instance ;

	    /**
	     * This method initializes a lazy singleton instance of the Extent Report, then returns it to the caller.
	     * @return instance of Extent Report
	     */
	    public static ExtentReports getReportInstance() throws IOException {
	    	if(instance == null) {
	            instance =  new ExtentReports();
           
	            ExtentSparkReporter htmlReporter = new ExtentSparkReporter (GenericMethods.getProperty("reportPath")+"report"+".html");
	            htmlReporter.config().setDocumentTitle("API Automation Practice"); // Title of report
	            htmlReporter.config().setReportName("API Automation Practice"); // Name of the report
	            htmlReporter.config().setTheme(Theme.STANDARD);//theme of report 
	            instance.attachReporter(htmlReporter);
	            
	        }
	        return instance;
	    }

}
