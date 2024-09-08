package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.constants.Appconstants;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink=By.linkText("Logout");
	private By accountHeaders= By.cssSelector("#content h2");
	private By searchInput= By.name("search");
	private By searchIcon=By.cssSelector("#search Button");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getAccsPageTitle() {
		String AccntTitle= eleUtil.waitForTitleContainsAndFetch(Appconstants.DEFAULT_MEDIUM_TIME_OUT, Appconstants.ACCOUNTS_PAGE_TITLE_VALUE);
		return AccntTitle;
	}
	
	public String getAcctCurrentURL() {
		String url=eleUtil.waitForURLContainsAndFetch(Appconstants.DEFAULT_MEDIUM_TIME_OUT, Appconstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
		return url;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, Appconstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
		}
	
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(searchInput, Appconstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public List<String> accntsHeadersList() {
		
		List<WebElement> headersList = eleUtil.waitForElementsVisible(accountHeaders, Appconstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> actHeaderList=new ArrayList<>();
		
		for(WebElement e: headersList) {
			String text=e.getText();
			actHeaderList.add(text);
		}
		
		return actHeaderList;
	}
	
	public SearchPage performSearch(String productName) {
		
		if(isSearchExist()) {
			eleUtil.doSendKeys(searchInput, productName);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
		}else {
			System.out.println("Search field is not present in the page");
			return null;
		}
	}
	
	

}
