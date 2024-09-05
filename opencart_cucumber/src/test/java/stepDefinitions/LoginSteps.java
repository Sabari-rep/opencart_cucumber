package stepDefinitions;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

import org.junit.Assert;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class LoginSteps {

	WebDriver driver;
	HomePage hp;
	LoginPage lp;
	MyAccountPage myacc;

	List<HashMap<String, String>> datamap; // Used for Data Driven test

	@Given("the user navigates to login page")
	public void the_user_navigates_to_login_page() {

		BaseClass.getLogger().info("Go to Myaccount ---> Click on Login");
		hp = new HomePage(BaseClass.getDriver());
		hp.clickMyaccount();
		hp.clickLogin();
	}

	@When("the user enter valid email {string} and password {string}")
	public void the_user_enter_valid_email_and_password(String email, String pwd) {

		BaseClass.getLogger().info("Entering email and password");
		lp = new LoginPage(BaseClass.getDriver());
		lp.setEmail(email);
		lp.setPassword(pwd);
	}

	@When("the user clicked the login button")
	public void the_user_clicked_the_login_button() {

		lp.clickLogin();
		BaseClass.getLogger().info("Clicked on login button");

	}

	@Then("the user should be redirected to Myaccount page")
	public void the_user_should_be_redirected_to_myaccount_page() {

		myacc = new MyAccountPage(BaseClass.getDriver());
		boolean targetpage = myacc.isMyAccountPageExists();

		Assert.assertEquals(targetpage, true);

	}

	// ******* Data Driven test **************

	@Then("the user should redirect to MyAccount page by passing email and passowrd with excel row {string}")
	public void the_user_should_redirect_to_my_account_page_by_passing_email_and_passowrd_with_excel_row(String rows) {

		try {
			datamap = DataReader.data(System.getProperty("user.dir") + "\\testData\\Opencart_LoginData.xlsx", "Sheet1");
		} catch (Exception e) {

		}

		int index = Integer.parseInt(rows) - 1;
		String email = datamap.get(index).get("username");
		String pwd = datamap.get(index).get("password");
		String exp_res = datamap.get(index).get("res");

		lp = new LoginPage(BaseClass.getDriver());
		lp.setEmail(email);
		lp.setPassword(pwd);

		lp.clickLogin();

		myacc = new MyAccountPage(BaseClass.getDriver());
		try {
			boolean targetpage = myacc.isMyAccountPageExists();
			System.out.println("target page: " + targetpage);
			
			if (exp_res.equalsIgnoreCase("Valid")) 
			{
				if (targetpage == true) 
				{
					MyAccountPage myaccpage = new MyAccountPage(BaseClass.getDriver());
					myaccpage.clickLogout();
					Assert.assertTrue(true);
				} else 
				{
					Assert.assertTrue(false);
				}
			}

			if (exp_res.equalsIgnoreCase("Invalid")) 
			{
				if (targetpage == true) 
				{
					myacc.clickLogout();
					Assert.assertTrue(false);
				} 
				else 
				{
					Assert.assertTrue(true);
				}
			}

		} 
		
		catch (Exception e) 
		{

			Assert.assertTrue(false);
		}
	}

}
