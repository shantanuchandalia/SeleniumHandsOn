
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class SampleRegistration {
	
    public String[][] readExcel(String filePath,String fileName,String sheetName) throws IOException
    {
	    File file =    new File(filePath+"\\"+fileName);
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook Workbook = null;
	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
	    String [][] outputString;
	    if(fileExtensionName.equals(".xlsx"))
	    {
	    	Workbook = new XSSFWorkbook(inputStream);
	    }
	    else if(fileExtensionName.equals(".xls"))
	    {
	       Workbook = new HSSFWorkbook(inputStream);
	    }
	    Sheet Sheet = Workbook.getSheet(sheetName);
	    int numRows = Sheet.getLastRowNum()+1;
	    int numCols = Sheet.getRow(0).getLastCellNum();
	    outputString = new String [numRows][numCols];
	    for (int i=0;i<numRows;i++)
	    {
	    	Row row = Sheet.getRow(i);
	    	for(int j=0;j<numCols;j++)
	    	{
	    		outputString[i][j]=row.getCell(j).getStringCellValue();
	    	}
	    }	    
	    return outputString;
    }

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		WebDriver driver;
		 ChromeOptions opt = new ChromeOptions();
         String [] s = new String[] {"--start-maximized"};//"disable-extensions",
         opt.addArguments(s);
         driver = new ChromeDriver(opt);
         driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.MINUTES);
         driver.get("http://automationpractice.com/index.php");
         driver.findElement(By.xpath("//a[contains(@class,'login')]")).click();
         SampleRegistration o = new SampleRegistration();
         //Fetching Values from Excel
         String filepath = "D:\\";
         String FileName = "Book2.xlsx";
         String SheetName = "Sheet1";
         String [][] ExcelOutput = o.readExcel(filepath, FileName, SheetName);
         //Loop to fetch all emailIDs
         WebDriverWait wait = new WebDriverWait(driver, 100);
         for(int i = 0; i< ExcelOutput.length; i++)
         {
        	 System.out.println("Registering with Email ID : " +ExcelOutput[i][0]);
        	 driver.findElement(By.id("email_create")).click();
             driver.findElement(By.id("email_create")).sendKeys(ExcelOutput[i][0]);
             driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
             Thread.sleep(3000);
             WebElement searchTool = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_lastname")));  
             searchTool.click();
             driver.findElement(By.xpath("//a[contains(@class,'login')]")).click();
         }       
         driver.close();
         driver.quit();
	}
}
