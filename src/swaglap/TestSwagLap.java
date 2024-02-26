package swaglap;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSwagLap {
	String MyWebsite ="https://www.saucedemo.com/";
	String UserName ="standard_user";
	String Password="secret_sauce";
	WebDriver driver = new ChromeDriver();
@BeforeTest
public void MySetUp () {
	driver.manage().window().maximize();
	driver.get(MyWebsite);
	
}
@Test(priority =1)
public void MyFirstSetUp( ) {
	WebElement UserNameInput = driver.findElement(By.id("user-name"));
	WebElement PasswordInput = driver.findElement(By.id("password"));
	WebElement LoginButton = driver.findElement(By.id("login-button"));
	UserNameInput.sendKeys(UserName);
	PasswordInput.sendKeys(Password);
	LoginButton.click();
}
	@Test(enabled = false)
	public void PrintTheFirstItem() {
		WebElement FirstItem =driver.findElement(By.cssSelector("a[id='item_4_title_link'] div[class='inventory_item_name ']"));
		String FirstItemtext =FirstItem.getText();
		System.out.println(FirstItemtext);
	
}
	@Test(enabled = false)
public void PrintTheAllItems() {
		List<WebElement> AllItems =driver.findElements(By.className("inventory_item_name"));
		for(int i=0;i<AllItems.size();i++) {
			System.out.println(AllItems.get(i).getText());
		}
	}
		@Test(priority =2)
		public void AddAllItemsToCart() throws InterruptedException {
			List<WebElement> AddToCartButtons = driver.findElements(By.className("btn"));
			List<WebElement>ItemsNames = driver.findElements(By.className("inventory_item_name"));
			List<WebElement>PriceOfTheItems =driver.findElements(By.className("inventory_item_price"));
			
			for(int i=0;i<AddToCartButtons.size();i++) {
				String ItemsName =ItemsNames.get(i).getText();
				String ItemsPrice =PriceOfTheItems.get(i).getText();
				double TheItemPriceAsDouble = Double.parseDouble(ItemsPrice.replace("$", ""));
				double TaxValue =0.08;
				double ThePriceAfterTax =(TheItemPriceAsDouble*TaxValue)+TheItemPriceAsDouble;
				
				System.out.println(TheItemPriceAsDouble);
				
				if(ItemsName.contains("Bike")|| ItemsName.contains("Onesie")) {
						AddToCartButtons.get(i).click();
						System.out.println("The item"+ItemsName+"has the original price of"+ItemsPrice+"the price after is"+ThePriceAfterTax);
			}}
		}
		
		
	
@AfterTest
public void AfterSetUp() {}
}