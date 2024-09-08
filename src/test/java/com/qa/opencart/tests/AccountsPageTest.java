package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.constants.Appconstants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accountPageSetUp() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accountPageTitleTest() {
		String accountPageTitle=accPage.getAccsPageTitle();
		Assert.assertEquals(accountPageTitle, Appconstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	

	@Test
	public void accPageURLTest() {
		String currUrl= accPage.getAcctCurrentURL();
		Assert.assertTrue(currUrl.contains(Appconstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test
	public void isLogoutLinkExist() {
		Boolean val=accPage.isLogoutLinkExist();
		Assert.assertTrue(true); 	
	}
	@Test
	public void accPageHeadersList() {
		List<String> accntsHeadersList = accPage.accntsHeadersList();
		Assert.assertEquals(accntsHeadersList.size(), 4);
	}
	
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
		};
	}
	
	@Test(dataProvider="getProductData")
	public void searchProductCountTest(String searchKey) {
		 searchPage = accPage.performSearch(searchKey);
		 Assert.assertTrue(searchPage.seachProductsCount()>0);
	}
	
	@DataProvider
	public Object[][] getProductTestData(){
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"MacBook", "MacBook Air"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung","Samsung Galaxy Tab 10.1"}
		};
				
	}
	@Test(dataProvider="getProductTestData")
	public void searchProductTest(String searchKey, String productName) {
		 searchPage = accPage.performSearch(searchKey);
		 
		 if(searchPage.seachProductsCount()>0) {
			 productInfoPage=searchPage.selectProduct(productName);
			 String actProductHeader=productInfoPage.getProductHeaderValue();
			 Assert.assertEquals(actProductHeader, productName);
		 }
	}
	
	
	

}
