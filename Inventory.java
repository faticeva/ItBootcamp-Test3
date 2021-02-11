package itBootcampTest3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Inventory {
	
	public static final String URL = "https://www.saucedemo.com/";
	private static final String SORT_BUTTON = "//*[@id=\"inventory_filter_container\"]/select";
	
	public static void sortItems(WebDriver driver) {
		WebElement button = driver.findElement(By.xpath(SORT_BUTTON));
		button.click();
		Select select = new Select(button);
		select.selectByVisibleText("Price (low to high)");
	}
	
	public static void sortiranje(double[]niz) {
		for(int i = 0; i<niz.length; i++) {
			for(int j = i+1; j<niz.length; j++) {
				if(niz[i] > niz[j]) {
					double pom = niz[i];
					niz[i] = niz[j];
					niz[j] = pom;
					
				}
			}
		}
	}

}
