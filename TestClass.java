package itBootcampTest3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestClass {
	
	private static WebDriver driver;
	
	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\38164\\Downloads\\chromedriver_win32.zip");
		driver = new ChromeDriver();
	}
	
	@Test
	public void testLoginUnsuccessful() {
		
		File f = new File("data.xlsx");
		try  {
			InputStream inp = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			
			SoftAssert sa = new SoftAssert();
			
			for(int i = 0; i<= 12; i++)  {
				Row row = sheet.getRow(i);
				
				Cell c1 = row.getCell(0);
				Cell c2 = row.getCell(1);
				
				String username = c1.toString(); //ubacila sam locked out user u pogresne usere
				String password = c2.toString();
				
				driver.navigate().to(HomePage.URL);
				HomePage.inputUsername(driver, username);
				HomePage.inputPassword(driver, password);
				HomePage.clickLogin(driver);
				
				String actual = driver.getCurrentUrl();
				String expected = HomePage.URL;
				
				sa.assertEquals(actual, expected);
				
			}
			sa.assertAll();
			wb.close();
		} catch (IOException e) {
			System.out.println("Fajl nije pronadjen!");
			e.printStackTrace();
		}
	}
	@Test
	public void sortItems() {
		driver.navigate().to(Inventory.URL); 
		
		double[] niz =  {29.99 , 9.99 , 15.99 , 49.99 , 7.99 , 15.99 };
		Inventory.sortiranje(niz);
		Inventory.sortItems(driver);
		
		double[] expected =  { 7.99 , 9.99 , 15.99 , 15.99 , 29.99 , 49.99 };
		
		Assert.assertEquals(niz, expected);
	}

}
