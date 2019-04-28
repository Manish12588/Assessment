package stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import junit.framework.Assert;

public class OpenRoboForm {

	WebDriver driver;
	Properties prop;
	static String PropfilePath=System.getProperty("user.dir");
	File file = new File(PropfilePath+"\\Inputs\\RoboForm_Input.properties");

	@Given("^User should be present on the form page$")
	public void RoboForm()
	{
		FileInputStream fileInput = null;
		try 
		{
			fileInput = new FileInputStream(file);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		prop = new Properties();
		try 
		{
			prop.load(fileInput);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		String URL= prop.getProperty("URL");
		System.setProperty("webdriver.chrome.driver", "../EmakinaCEE_Assessment/Library/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(URL);
		driver.manage().window().maximize();
	}

	@When("^Logo of the form is RoboForm$")
	public void validation_correct_page()
	{
		String formlogo=driver.findElement(By.xpath("//div[@class='stickylogo']//img")).getAttribute("alt");
		Assert.assertEquals("RoboForm Logo", formlogo);
		System.out.println("Actual Logo text is appearig: "+formlogo+ " And Expexted is: RoboForm Logo");

	}

	@Then("^Select the title Dr from dropdown$")
	public void Title_Dropdown_Field() throws Throwable {
		/*There is some cookies notification is appearing on page so before selecting any values
		 * from drop-down we have to close that.
		 */
		WebElement cookiesele=driver.findElement(By.className("cookies-notification"));

		if(cookiesele.isDisplayed())
		{
			System.out.println("Cookies pop-up is present on page Let's click on Got It button");
			driver.findElement(By.xpath("//span[text()='Got it']")).click();
		}
		else
		{
			System.out.println("Cookies pop-up is not present on page.");
		}

		Select dropdownoption=new Select(driver.findElement(By.name("ttl_select")));
		dropdownoption.selectByValue(prop.getProperty("Title"));
		Thread.sleep(1000);
	}


	@Then("^Select the radio button Mr$")
	public void Title_RadioButton() throws Throwable {
		driver.findElement(By.xpath("//div[@class='mcs']//div[2]//div[2]/input[@value='Mr']")).click();
		Thread.sleep(1000);
	}

	@Then("^type some message in Message text field$")
	public void type_some_message_in_Message_text_field() throws Throwable {
		WebElement message=driver.findElement(By.xpath("//div[contains(text(),'Message')]/../div[2]/input"));
		message.sendKeys(prop.getProperty("Message"));
		System.out.println(message.getText());
		Thread.sleep(1000);
	}

	@Then("^type some comment in Comments text field$")
	public void Comments_InputField() throws Throwable {

		driver.findElement(By.xpath("//div[contains(text(),'Comments')]/../div[2]/input")).sendKeys(prop.getProperty("Comments"));
		Thread.sleep(1000);
	}

	@Then("^type some comment in Your Comments text field$")
	public void YourComments_InputField() throws Throwable {

		driver.findElement(By.xpath("//div[contains(text(),'Your Comments')]/../div[2]/input")).sendKeys(prop.getProperty("YourComment"));
		Thread.sleep(1000);
	}

	@Then("^type some comment in Say It Here text field$")
	public void SayITHere_InputField() throws Throwable {

		driver.findElement(By.xpath("//div[contains(text(),'Say It Here')]/../div[2]/input")).sendKeys(prop.getProperty("SayITHere"));
		Thread.sleep(1000);
	}

	@Then("^Give some information in Resume text area$")
	public void Resume_InputField() throws Throwable {

		driver.findElement(By.xpath("//div[contains(text(),'Resume')]/../div[2]/textarea")).sendKeys(prop.getProperty("Resume"));
		Thread.sleep(1000);
	}

	@Then("^Select the Age check box$")
	public void Age_Checkbox() throws Throwable {

		driver.findElement(By.xpath("//div[contains(text(),'Are you 18')]/input")).click();
		Thread.sleep(1500);
	}

	@Then("^Select the check box for Adverties on home page$")
	public void AdvertiesOnHomePage_Checkbox() throws Throwable {

		driver.findElement(By.xpath("//div[contains(text(),'Advertise on home page')]/input")).click();
		Thread.sleep(1000);
	}

	@Then("^Select the Marital status radion button and from drop-down as well$")
	public void MaritalStatus() throws Throwable {

		driver.findElement(By.xpath("//div[contains(text(),'Marital Status Radio')]/../div[2]/input[@value='Single']")).click();
		Thread.sleep(1000);

		Select Maritaldropdown=new Select(driver.findElement(By.xpath("//div[text()='Marital Status Selection']/../div[2]/select")));
		Maritaldropdown.selectByValue(prop.getProperty("MaritalStatus"));
		Thread.sleep(1000);
	}	

	@Then("^Type some text in text filed appear against the image$")
	public void TextFieldAgainstImage() throws Throwable {

		driver.findElement(By.xpath("//input[@name='attitude']")).sendKeys(prop.getProperty("ImageText"));
		Thread.sleep(1000);
	}

	@Then("^Give some id in My ID input field$")
	public void MyID_InputFiled() throws Throwable {

		driver.findElement(By.xpath("//div[contains(text(),'My ID')]/../div[2]/input")).sendKeys(prop.getProperty("MyID"));
		Thread.sleep(1000);
	}

	@Then("^Choose some income from dropdown$")
	public void Income_Dropdow() throws Throwable {

		Select Incomedropdown=new Select(driver.findElement(By.name("Income")));
		Incomedropdown.selectByVisibleText(prop.getProperty("IncomeDropdown"));
		Thread.sleep(1000);
	}

	@Then("^Select some salary number from Your annual salary radio button group$")
	public void Income_Radiobutton() throws Throwable {

		driver.findElement(By.xpath("//div[contains(text(),'Your annual salary?')]/../div[2]/input[@value='3']")).click();
		Thread.sleep(1000);
	}

	@Then("^Click on Reset button$")
	public void click_on_Reset_button() throws Throwable {

		driver.findElement(By.xpath("//input[@value='Reset']")).click();
		Thread.sleep(1000);

		driver.quit();
	}



}
