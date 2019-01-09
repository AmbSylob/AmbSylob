package ProBook;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	
	String url ="http://probook.progideo.com";
	String expectedTitle1 = "Dashboard - ProBook";
	String actualTitle1 = null;
	String username = "AmineBouizmoune";
	String password = "mihani1234";
	String expectedTitle2 = "(3) fil d'actualités - ProBook";
	String actualTitle2 = null;
	WebDriver driver;
	
   @DataProvider(name = "Data1")
	  public static Object[][] data1(){
		  return new Object[][] {
			  {"amine", "123"},{"AmineBouizmoune","mihani1234"},{"Bouizmoune","mihani1234"}
			  };	
   }
   
   @Test(dataProvider ="Data1")
   public void test(String username, String password){
 	  
 	
       driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/a[1]")).click();
       
       actualTitle1 = driver.getTitle();
       
       System.out.println("Login page title : expected value is "+expectedTitle1+" actual value is "+actualTitle1);
       
       if (!actualTitle1.contentEquals(expectedTitle1)) {
           System.out.println("Test Failed");
       }

       WebDriverWait wait = new WebDriverWait(driver, 10);
       wait.until(ExpectedConditions.elementToBeClickable(By.id("login_username")));

       
       driver.findElement(By.id("login_username")).sendKeys(username);
       driver.findElement(By.id("login_password")).sendKeys(password);
       
       driver.findElement(By.id("loginBtn")).click();
       
       wait.until(ExpectedConditions.presenceOfElementLocated(By.id("account-dropdown-link")));
       

       
       
       actualTitle2 = driver.getTitle();
       
       System.out.println("Login result : expected value is "+expectedTitle2+" actual value is "+actualTitle2);
       
       if (!actualTitle2.contentEquals(expectedTitle2)) {
           System.out.println("Test Failed");
       }
   }
   
   @BeforeMethod
   public void beforeMethod() {
 	  
      
 	  System.setProperty("webdriver.gecko.driver", "/home/progideo/Desktop/geckodriver");
      
       driver = new FirefoxDriver();
     
       driver.manage().window().maximize();
   	   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   	   driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
   	   driver.manage().deleteAllCookies();
   	   driver.get(url);
       
   }

   @AfterMethod
   public void afterMethod() {

       //driver.close();
   }

}
