package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.pages.SearchPage;

public class ProductPageInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetUp() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductImagesTestData(){
		return new Object[][] {
			{"MacBook","MacBook Pro",4},
			{"MacBook", "MacBook Air",4},
			{"iMac","iMac",3},
			{"Apple","Apple Cinema 30\"",6},
			{"Samsung","Samsung Galaxy Tab 10.1",7}
		};
				
	}
	
	@Test(dataProvider="getProductImagesTestData")
	public void productImagesCount(String searchKey, String productName, int imagesCount) {
		searchPage = accPage.performSearch(searchKey);
		productInfoPage=searchPage.selectProduct(productName);
		int actImagesCount=productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
	}
	
	@Test
	public void productInfoTest() {
		searchPage = accPage.performSearch("Macbook");
		productInfoPage=searchPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("product name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("productprice"), "$2,000.00");
		
		softAssert.assertAll();
		
	}
	
	@DataProvider
	public Object[][] getCartTestData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro", 1},
			{"iMac", "iMac", 2},
		};
	}
	@Test(dataProvider="getCartTestData")
	public void addtToCartTest(String searchKey, String productName, int quantity) {
		searchPage = accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		productInfoPage.enterQuantity(quantity);
		String actCartMesg = productInfoPage.addProductToCart();
		//Success: You have added MacBook Pro to your shopping cart!
		softAssert.assertTrue(actCartMesg.indexOf("Success")>=0);
		softAssert.assertTrue(actCartMesg.indexOf(productName)>=0);
		softAssert.assertEquals(actCartMesg, "Success: You have added" +productName+ "to your shopping cart!");
		
		
		
		//new code: checking cart details as well:
//		viewCartPopUpPage = productInfoPage.openCart();
//		List<String> cartProdList = viewCartPopUpPage.getProductsValueListInCart();
//		
//		Object[][] data = getCartTestData();
//		expProdListInCart = new ArrayList<String>();
//		for(int i=0; i< data.length; i++) {
//			expProdListInCart.add(data[i][1].toString());
//		}
//		
//		System.out.println(expProdListInCart);
//		softAssert.assertTrue(expProdListInCart.containsAll(cartProdList));
//		
//		
//		softAssert.assertAll();

	}
	
	

}
