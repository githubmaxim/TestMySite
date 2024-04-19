package ui.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter
public class PageWorkingWithFields {
    private By buttonOn_Main = By.xpath("//div[@class='col1']//a[@href='maiin.html']");
    private By buttonWorking_With_Files = By.xpath("//div[@class='col1']//a[@href='workingWithFiles.html']");
    private By buttonMain = By.xpath("//div[@class='in_nav']//a[@href='maiin.html']");
    private By buttonFiles = By.xpath("//div[@class='in_nav']//a[@href='workingWithFiles.html']");

    private By buttonCreate_user = By.xpath("//div[@class='col21']//input[@value='Create user']");
    private By buttonLoad_all_Users = By.xpath("//div[@class='col21']//input[@value='load all Users']");
    private By buttonSendForm = By.xpath("//div[@class='col21']//form[@id='form']//input[@id='button_form']");
    private By buttonForDeleteUser = By.xpath("//div[@class='col21']//tbody/tr[last()]/td[last()]/button");

    private By fieldInTableHeaderUser_Name = By.xpath("//div[@class='col21']//th[text()='User name']");
    private By fieldInTableUser_Name = By.xpath("//div[@class='col21']//tbody/tr[last()]/td[2]");

    private By fieldForCreateUserUser_name = By.xpath("//div[@class='col21']//input[@placeholder='User name']");
    private By fieldForCreateUserUser_login = By.xpath("//div[@class='col21']//input[@placeholder='User login']");
    private By fieldForCreateUserUser_email = By.xpath("//div[@class='col21']//input[@placeholder='User email']");
    private By fieldForCreateUserUser_university_name = By.xpath("//div[@class='col21']//input[@placeholder='User university name']");
    private By fieldForCreateUserUser_university_city = By.xpath("//div[@class='col21']//input[@placeholder='User university city']");

    private By fieldForSendFormNumber = By.xpath("//div[@class='col21']//form[@id='form']//input[@name='number']");
    private By fieldForSendFormWord = By.xpath("//div[@class='col21']//form[@id='form']//input[@name='word']");
    private By fieldForOutputMessage = By.xpath("//div[@class='col21']//div[@id='message1']");


    public void pressButtonOnMain(WebDriver driver) {
        driver.findElement(buttonOn_Main).click(); }

    public void pressButtonWorkingWithFiles(WebDriver driver) {
        driver.findElement(buttonWorking_With_Files).click(); }

    public void pressButtonMain(WebDriver driver) {
        driver.findElement(buttonMain).click();
    }

    public void pressButtonFiles(WebDriver driver) {
        driver.findElement(buttonFiles).click();
    }

    public void pressButtonCreateUser(WebDriver driver) {
        driver.findElement(buttonCreate_user).click();
    }

    public void pressButtonLoadAllUser(WebDriver driver) {
        driver.findElement(buttonLoad_all_Users).click();
    }

    public void pressButtonDeleteUser(WebDriver driver) {
        driver.findElement(buttonForDeleteUser).click(); }

    public void pressButtonSend(WebDriver driver) {
        driver.findElement(buttonSendForm).click();
    }

}
