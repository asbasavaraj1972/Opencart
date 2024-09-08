package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.constants.Appconstants;

public class SearchPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchProductResults = By.xpath("//div[@id='content']//div[contains(@class,'product-layout')]");

	SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int seachProductsCount() {
		int productCount= eleUtil.waitForElementsVisible(searchProductResults, Appconstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Product Count:::"+ productCount);
		return productCount;
	}

	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleUtil.waitForElementVisible(productLocator, Appconstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}

}
