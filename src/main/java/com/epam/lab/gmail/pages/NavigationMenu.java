package com.epam.lab.gmail.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.lab.gmail.decorator.ElementDecorator;
import com.epam.lab.gmail.drivers.DriverManager;
import com.epam.lab.gmail.elements.Button;

public class NavigationMenu {
	private static Logger LOG = Logger.getLogger(NavigationMenu.class);

	@FindBy
	private Button inboxItem;

	@FindBy(css = "span.CJ")
	private Button moreItem;

	@FindBy(css = "div.TN.GLujEb.aHS-bns")
	private Button importantItem;

	public NavigationMenu() {
		LOG.info("NavigationMenu constructor");
		PageFactory.initElements(new ElementDecorator(DriverManager.getInstance()), this);
	}

	public void clikOnImportant() {
		LOG.info("clikOnImportant method");
		importantItem.click();
		waitUtilBoxLoaded();
	}

	public void clikOnMore() {
		LOG.info("clikOnMore method");
		moreItem.click();
	}

	private void waitUtilBoxLoaded() {
		LOG.info("waitUntilBoxLoaded method");
		try {
			new WebDriverWait(DriverManager.getInstance(), 3)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@class='vY']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		new WebDriverWait(DriverManager.getInstance(), 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@class='vY nq']")));

	}

}
