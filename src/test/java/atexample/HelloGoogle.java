package atexample;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.sql.Driver;

public class HelloGoogle {

    @Test
    public void openGoogle() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://77.50.236.203:4881/#");
        WebElement email = driver.findElement(By.cssSelector("input[name=email]"));

        WebElement password = driver.findElement(By.cssSelector("input[name=password]"));
        email.sendKeys("user@pflb.com");

        WebElement submit = driver.findElement(By.cssSelector("button[type=submit]"));
        password.sendKeys("user");
        submit.click();

        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(), 'Bad request')]"));
        assert errorMessage.isDisplayed();


       // driver.quit();




    }
}
