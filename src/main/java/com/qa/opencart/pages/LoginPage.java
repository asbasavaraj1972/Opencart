package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.constants.Appconstants;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// private by locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink=By.linkText("Register");

	// page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// page actions/methods
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleContainsAndFetch(Appconstants.DEFAULT_SHORT_TIME_OUT, Appconstants.LOGIN_PAGE_TITLE_VALUE);
		return title;
	}

	public String getLoginPageCurrentUrl() {
		String url=eleUtil.waitForURLContainsAndFetch(Appconstants.DEFAULT_SHORT_TIME_OUT,Appconstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		return url;
	}

	public boolean isForgotpasswordLinkExists() {
		return eleUtil.waitForElementVisible(forgotPwdLink, Appconstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}

	public AccountsPage doLogin(String un, String pwd) {
		//eleUtil.waitForElementVisible(emailId, Appconstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		eleUtil.getElement(emailId).sendKeys(un);
		eleUtil.waitForElementVisible(password, Appconstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(pwd);
		eleUtil.waitForElementVisible(loginBtn, Appconstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new AccountsPage(driver);
	}

	public RegistrationPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}

}
