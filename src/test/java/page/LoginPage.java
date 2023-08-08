package page;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement loginInput;
    private WebElement loginInputN;

    private WebElement passwordInput;
    private WebElement submitBtn;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super();
        this.driver = driver;
        this.wait = wait;
        loginInput = driver.findElement(By.cssSelector("input[name=email]"));
        loginInputN = driver.findElement(By.cssSelector("input[name=email]"));
        passwordInput = driver.findElement(By.cssSelector("input[name=password]"));
        submitBtn = driver.findElement(By.cssSelector("button[type=submit]"));
    }

    private Alert findAlert() {
        return wait.until(drv -> drv.switchTo().alert());
    }
    public void fillLoginInput(String text) {
        loginInput.clear();
        loginInput.sendKeys(text);
    }

    public void fillLoginInputN(String text) {
        loginInputN.clear();
        loginInputN.sendKeys(text);
    }
    public void fillPasswordInput(String text) {
        passwordInput.clear();
        passwordInput.sendKeys(text);
    }
    public void submitForm() {
        submitBtn.click();
    }

    public String getAlertText() {
        return findAlert().getText();
    }

    public void dismissAlert() {
        findAlert().dismiss();
    }
    @Deprecated
    public String getAlertTextThenDismiss() {
        return wait.until(drv -> {
            Alert alert = drv.switchTo().alert();
            String text = alert.getText();
            alert.dismiss();
            return text;
        });
    }
}

