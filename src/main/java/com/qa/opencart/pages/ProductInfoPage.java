package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.constants.Appconstants;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productpriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity=By.id("input-quantity");
	private By addToCartBtn=By.id("button-cart");
	private By cartSuccessMessg=By.cssSelector(".alert.alert-success");

	private Map<String, String> productInfomap;

	ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeaderValue() {
		String headerText = eleUtil.doElementGetText(productHeader);
		System.out.println(headerText);
		return headerText;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsVisible(productImages, Appconstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("product images count: " + imagesCount);
		return imagesCount;
	}

	public Map<String, String> getProductInfo() {
		productInfomap = new HashMap<>();

		productInfomap.put("product name", getProductHeaderValue());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productInfomap);
		
		return productInfomap;
		
		//hash is not orderes it will maintain in an unorderd way
		//linkedmap is a ordered map it will store the values in the way how you put in the linked map
		//Treemap is a sorted map it will store the values in the alphabetical order.
		// all the maps are the childs of map interface
	}
	
	public void enterQuantity(int qty) {
		System.out.println("Product Quantity: " + qty);
		eleUtil.doSendKeys(quantity, String.valueOf(qty));
	}
	
	public String addProductToCart() {
		eleUtil.doClick(addToCartBtn);
		String successMessg = eleUtil.waitForElementVisible(cartSuccessMessg, Appconstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		StringBuilder sb = new StringBuilder(successMessg);
		String mesg = sb.substring(0, successMessg.length()-1).replace("\n", "").toString();
		
		System.out.println("Cart Success Mesg: " + mesg);
		return mesg;
	}

	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for (WebElement ele : metaList) {
			String meta = ele.getText();
			String[] metaInfo = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfomap.put(key, value);
		}
	}

	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(productpriceData);
		String price = priceList.get(0).getText();
		String exTax = priceList.get(1).getText();

		String exTaxVal = exTax.split(":")[1].trim();
		productInfomap.put("productprice", price);
		productInfomap.put("exTax", exTaxVal);
	}

}
