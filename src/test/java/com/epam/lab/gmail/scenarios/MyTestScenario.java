package com.epam.lab.gmail.scenarios;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.epam.lab.gmail.bisnes_objects.GmailBO;
import com.epam.lab.gmail.bisnes_objects.LoginBO;
import com.epam.lab.gmail.drivers.DriverManager;
import com.epam.lab.gmail.models.Message;
import com.epam.lab.gmail.models.User;
import com.epam.lab.gmail.prop.DriverPropertisLoader;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyTestScenario {
	public static final String PROPERTIES_FILE_URL = "resources/driver_config.properties";
	public static final Logger LOG = Logger.getLogger(MyTestScenario.class);
	List<Message> list;
	GmailBO messageMeneger;

	@Given("^I login with credential username='(.+)' password ='(.+)'$")
	public void login(String login, String password) {
		try {
			DriverPropertisLoader.load(PROPERTIES_FILE_URL);
		} catch (Exception e) {
			LOG.error("Driver do not exist");
		}
		new LoginBO().loginAs(new User(login, password));
	}

	@And("^go to important$")
	public void goToImportant() throws Throwable {
		messageMeneger.openImportantMesssagesList();

	}

	@When("^I mark (\\d+) messages as important$")
	public void iMarkMessagesAsImportant(int number) throws Throwable {
		messageMeneger = new GmailBO();
		list = messageMeneger.markMessagesAsImportant(number);
	}

	@When("^delete recently added messages$")
	public void deleteResentlyAddetMessages() throws Throwable {
		messageMeneger.deleteMessages(list);
	}

	@Then("^check if deleted messages arent present on the page$")
	public void deletetMessagesArrentPresent() throws Throwable {
		Assert.assertFalse(messageMeneger.arePresent(list));
		DriverManager.closeDriver();
	}

}
