package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;
import testbase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass 
{
	@Test(groups = { "regression", "master" })
	public void test_account_registration() throws Exception 
	{
		logger.info("Starting TC_001_AccountRegistration.");
		try 
		{
			/*
			 * logger.info("Launching application."); driver.get(rb.getString("appURL"));
			 * driver.manage().window().maximize();
			 */
			HomePage hp = new HomePage(driver);
			logger.info("Clicking on Home>>My Account.");
			hp.clickMyAccount();
			logger.info("Clicking on Home>>My Account>>Register.");
			hp.clickRegister();

			logger.info("Providing customer details.");
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			regpage.setFirstName("John");
			logger.info("Entered customer's first name.");

			regpage.setLastName("Kennedy");
			logger.info("Entered customer's last name.");

			regpage.setEmail(randomstring() + "@gmail.com");
			logger.info("Entered customer's email.");

			regpage.setTelephone("65656565");
			logger.info("Entered customer's telephone number.");

			regpage.setPassword("123456");
			logger.info("Entered customer's password.");

			regpage.setConfirmPassword("123456");
			logger.info("Entered customer's confirm password.");

			regpage.setPrivacyPolicy();
			logger.info("Clicked on privacy policy.");

			regpage.clickContinue();
			logger.info("Clicked on continue button.");

			String confmsg = regpage.getConfirmationMsg();

			logger.info("Validation started....");

			if (confmsg.equals("Your Account Has Been Created!")) 
			{
				logger.info("Test case passed.");
				Assert.assertTrue(true);
			} 
			else 
			{
				logger.error("Account Registration Failed.");
				captureScreen(driver, "test_account_registration"); // Capturing screenshot
				Assert.assertTrue(false);
			}
		} 
		catch (Exception e) 
		{
			logger.fatal("Test case failed due to fatal.");
			captureScreen(driver, "test_account_registration"); // Capturing screenshot
			Assert.fail();
		}
		logger.info("Finished TC_001_AccountRegistration.");
	}
}
