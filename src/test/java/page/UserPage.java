package page;

import Help.TestValues;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class UserPage extends AbstractPage  {
    public UserPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    @FindBy(css = "table tbody tr:first-child")
    private WebElement firstRow;

    @FindBy(css = "table tbody tr:nth-child(2)")
    private WebElement secondRow;

    @FindBy(css = "table tbody tr:nth-last-child(2)")
    private WebElement secondLastRow;
    @FindBy(css = "table tbody tr:last-child")
    private WebElement lastRow;
    @FindBy(css = "a[id=basic-nav-dropdown]")
    private WebElement firstCL;

    @FindBy(linkText = "Read all")
    private WebElement readAllCL;

    @FindBy(css = "#root table")
    private WebElement vanillaUsersTable;




    private List<WebElement> findUsersTrs() {
        return vanillaUsersTable.findElements(By.cssSelector("tbody tr"));
    }

    public WebElement findMiddleElement() {
        List<WebElement> trs = findUsersTrs();
        int middleIndex = trs.size() / 2;
        return trs.get(middleIndex);
    }

    public UserPage() {
        PageFactory.initElements(driver,this);
    }

    public String getMiddlRow() {
        WebElement middleElement = findMiddleElement();
        return middleElement.getText();
    }

        public void CLPage(){
        firstCL.click();
        readAllCL.click();
        //idCL.click();
    }


    public String getFirstRow(){
        return firstRow.getText();
    }
    public String getSecondRow(){
        return secondRow.getText();
    }

    public String getSecondLastRow(){
        return secondLastRow.getText();
    }
    public String getLastRow(){
        return lastRow.getText();
    }

    private WebElement getSortButton(String buttonName)
    {
        var buttons = driver.findElements(By.tagName("button"));
        var button = buttons.stream().filter((opt) -> opt.getText().contains(buttonName)).findFirst().orElse(null);
       return button;
    }

    public void setSortingByID(boolean sort)
    {
        var idColumn = getSortButton("ID");
        try { Thread.sleep(5000); }
        catch(InterruptedException ie) {}
        if(sort)
            idColumn.click();
        else
        {
            idColumn.click();
            idColumn.click();
        }
    }
    public void Assert(){
        Assert.assertEquals(getFirstRow(), TestValues.TEST_ROW);
        Assert.assertEquals(getSecondRow(),TestValues.TESTSecond_ROW);
       // Assert.assertEquals(getSecondLastRow(),TestValues.TESTSecondLast_ROW); //строки добавляются
       // Assert.assertEquals(getLastRow(),TestValues.TESTLast_ROW);// строки добавляются
        //Assert.assertEquals(getMiddlRow(),TestValues.TESTMidl_ROW);//строки добавляются
    }







}