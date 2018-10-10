import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class SampleClass {
	
	public WebDriver driver;
	
	
	 @BeforeSuite
	  public void beforeSuite() {
		 
	  }
	 @BeforeTest
	  public void beforeTest() {
		 ChromeOptions opt = new ChromeOptions();
         String [] s = new String[] {"--start-maximized"};//"disable-extensions",
         opt.addArguments(s);
         driver = new ChromeDriver(opt);
	  }
	  @Test(priority=1)
	  public void f() {
		  driver.get("https://the-internet.herokuapp.com/login");
	  }
	  @Test(priority=2)
	  public void LoginCheck() {
		  WebElement txtBx_username = driver.findElement(By.id("username"));
		  WebElement txtBx_password = driver.findElement(By.id("password"));
		  WebElement btn_login = driver.findElement(By.xpath("//button[contains(@type,'submit')]"));
		  txtBx_username.sendKeys("tomsmith");
		  txtBx_password.sendKeys("SuperSecretPassword!");
		  btn_login.click();
		  Assert.assertTrue(driver.findElement(By.id("flash")).isDisplayed() && driver.findElement(By.xpath("//a[contains(.,' Logout')]")).isDisplayed(), "Test Fails");
		  driver.findElement(By.xpath("//a[contains(.,' Logout')]")).click(); 
	  }
	  @Test(priority=3)
	  public void LoginIncorrectUserName() {
		  WebElement txtBx_username = driver.findElement(By.id("username"));
		  WebElement txtBx_password = driver.findElement(By.id("password"));
		  WebElement btn_login = driver.findElement(By.xpath("//button[contains(@type,'submit')]"));
		  txtBx_username.sendKeys("tomsmith11");
		  txtBx_password.sendKeys("SuperSecretPassword!");
		  btn_login.click();
		  Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'flash error')]")).isDisplayed() && driver.findElement(By.xpath("//button[contains(@type,'submit')]")).isDisplayed(), "Test Fails");
	  }
	  @Test(priority=4)
	  public void LoginIncorrectPassword() {
		  WebElement txtBx_username = driver.findElement(By.id("username"));
		  WebElement txtBx_password = driver.findElement(By.id("password"));
		  WebElement btn_login = driver.findElement(By.xpath("//button[contains(@type,'submit')]"));
		  txtBx_username.sendKeys("tomsmith");
		  txtBx_password.sendKeys("SuperSecretPassword!11");
		  btn_login.click();
		  Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'flash error')]")).isDisplayed() && driver.findElement(By.xpath("//button[contains(@type,'submit')]")).isDisplayed(), "Test Fails");
	  }
	  @Test(priority=5)
	  public void LoginEmptyFieldUsername() {
		  WebElement txtBx_username = driver.findElement(By.id("username"));
		  WebElement txtBx_password = driver.findElement(By.id("password"));
		  WebElement btn_login = driver.findElement(By.xpath("//button[contains(@type,'submit')]"));
		  //txtBx_username.sendKeys("tomsmith");
		  txtBx_password.sendKeys("SuperSecretPassword!11");
		  btn_login.click();
		  Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'flash error')]")).isDisplayed() && driver.findElement(By.xpath("//button[contains(@type,'submit')]")).isDisplayed(), "Test Fails");
	  }
	  @AfterTest
	  public void afterTest() {
		  driver.close();
	  }
	  @AfterSuite
	  public void afterSuite() {
		  
	  }

}
