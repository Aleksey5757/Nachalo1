package sort;

import Help.TestValues;
import config.ApplicationConfig;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.LoginPage;
import page.UserPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class main {
    private RemoteWebDriver driver;
    private ApplicationConfig config;

    private WebDriverWait wait;



    @BeforeAll
    public void configInit() {
        config = new ApplicationConfig();
    }

    @BeforeEach
    public void init() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(200));
    }

    // @AfterEach
 // public void tearDown() throws InterruptedException {
     // Thread.sleep(8000);
       // driver.quit();}



    @Test
    public void loginTest() {
        //driver.get("http://77.50.236.203:4881/#");
        driver.get(config.baseUrl);

        WebElement loginInput = driver.findElement(By.cssSelector("input[name=email]"));
        WebElement passwordInput = driver.findElement(By.cssSelector("input[name=password]"));
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type=submit]"));
        loginInput.sendKeys(config.username);
        passwordInput.sendKeys(config.userPassword);
        submitBtn.click();

        String alertText = wait.until(driver -> {
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            alert.dismiss();
            return text;
        });
        Assertions.assertTrue(alertText.contains("Successful authorization"), "Alert text doesn't contains info about successful auth");
    }

    @Test
    public void loginTestUsingPo() {
        driver.get(config.baseUrl);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.fillLoginInput(config.username);
        loginPage.fillPasswordInput(config.userPassword);
        loginPage.submitForm();
        String alertText = loginPage.getAlertText();
        Assertions.assertTrue(alertText.contains("Successful authorization"), "Alert text doesn't contains info about successful auth");
        loginPage.dismissAlert();
    }

    @Test
    public void loginTestUsingN() {
        driver.get(config.baseUrl);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.fillLoginInputN(config.usernameN);
        loginPage.fillPasswordInput(config.userPassword);
        loginPage.submitForm();
        String alertText = loginPage.getAlertText();
        Assertions.assertTrue(alertText.contains("Bad request"), "!!!");
        loginPage.dismissAlert();
    }

    @Test
    public void sort() {
        driver.get(config.baseUrl);
        var userPage = new UserPage(driver, wait);
        userPage.CLPage();
        userPage.setSortingByID(true);
       // userPage.Assert();
        Assert.assertEquals(userPage.getFirstRow(),TestValues.TEST_ROW);
        Assert.assertEquals(userPage.getSecondRow(),TestValues.TESTSecond_ROW);
       // Assert.assertEquals(userPage.getSecondLastRow(),TestValues.TESTSecondLast_ROW); //строки добавляются
       // Assert.assertEquals(userPage.getLastRow(),TestValues.TESTLast_ROW);// строки добавляются
       //Assert.assertEquals(userPage.getMiddlRow(),TestValues.TESTMidl_ROW);//строки добавляются

         }
}
