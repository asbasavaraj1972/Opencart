package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.constants.Appconstants;

public class LoginPageTest extends BaseTest {
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actualTitle=loginPage.getLoginPageTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle,Appconstants.LOGIN_PAGE_TITLE_VALUE);
	}
	
	@Test(priority=2)
	public void loginPageUrlTest() {
		String url=loginPage.getLoginPageCurrentUrl();
		Assert.assertTrue(url.contains(Appconstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test(priority=3)
	public void forgotPasswordlinkexistTest() {
		Assert.assertTrue(loginPage.isForgotpasswordLinkExists());
	}
	
	@Test(priority=4)
	public void doLoginTest() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

}
