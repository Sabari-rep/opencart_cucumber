package stepDefinitions;

import java.util.Map;

import org.openqa.selenium.WebDriver;


import factory.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class RegistrationSteps {
	
	WebDriver driver;
	HomePage hp;
	LoginPage lp;
	AccountRegistrationPage accregister;
	
	
	@Given("the user navigates to account registration page")
	public void the_user_navigates_to_account_registration_page() {
	   BaseClass.getLogger().info("Enter the account registration page");
	   hp = new HomePage(BaseClass.getDriver());
		hp.clickMyaccount();
		hp.clickRegister();
	}

	@When("the user enters the details into beow field")
	public void the_user_enters_the_details_into_beow_field(DataTable dataTable) {
		
		Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
		accregister = new AccountRegistrationPage(BaseClass.getDriver());
		accregister.setFirstName(dataMap.get("First name"));
		accregister.setLastName(dataMap.get("Last name"));
		accregister.setTelephone(dataMap.get("telephone"));
		accregister.setEmail(BaseClass.randomAlphaNumeric()+"@gmail.com");
		accregister.setPassword(dataMap.get("password"));
		accregister.setConfirmPassword(dataMap.get("password"));


	}

	@When("the user selects the privacy policy check box")
	public void the_user_selects_the_privacy_policy_check_box() {
		
		accregister.setPrivacyPolicy();
	   
	}

	@When("the user click the continue button")
	public void the_user_click_the_continue_button() {
		accregister.clickContinue();
	}

	@Then("account registration got successful")
	public void account_registration_got_successful() {
		String confirmationmsg = accregister.getConfirmationMsg();
		Assert.assertEquals(confirmationmsg, "Your Account Has Been Created!");
	}


}
