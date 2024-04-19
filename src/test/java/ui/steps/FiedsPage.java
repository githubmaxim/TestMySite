package ui.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pages.PageWorkingWithFields;
import ui.pages.PageWorkingWithFiles;
import ui.pages.PageWorkingWithMain;
import ui.singletons.MySingleton2;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

public class FiedsPage {
    PageWorkingWithMain pageWorkingWithMain = new PageWorkingWithMain();
    PageWorkingWithFields pageWorkingWithFields = new PageWorkingWithFields();
    PageWorkingWithFiles pageWorkingWithFiles = new PageWorkingWithFiles();
    MySingleton2 instance2 = MySingleton2.getInstance2();

    //переменные, которые будем использовать для возврата на страницу WorkingWithFields (ту, которую проверяем)
    String url;
    WebDriver driver;

    @Given("open Webdriver2 for site {string}")
    public void openWebdriverForSite(String url2) {
        System.setProperty("webdriver.chrome.driver", "D:\\Java\\Selenium\\chromedriver.exe");
        WebDriver driver2 = new ChromeDriver();
        driver2.get(url2);
        instance2.webDriver2 = driver2;
        instance2.wait2 = new WebDriverWait(driver2, Duration.ofSeconds(10));
        driver2.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        url = url2;
        driver = driver2;
    }

    @When("check working the button On_Main to go to another page")
    public void checkWorkingTheButtonOn_MainToGoToAnotherPage() {
        WebElement button = instance2.webDriver2.findElement(pageWorkingWithFields.getButtonOn_Main());
        pageWorkingWithFields.pressButtonOnMain(instance2.webDriver2);
        instance2.wait2.until(ExpectedConditions.stalenessOf(button));
        Assertions.assertTrue(instance2.webDriver2.findElement(pageWorkingWithMain.getButtonFiles()).isEnabled());
    }

    @And("check working the button Main to go to enother page")
    public void checkWorkingTheButtonMainToGoToEnotherPage() {
        //возвращаюсь на страницу WorkingWithFields (ту, которую проверяем)
        driver.get(url);

        WebElement button = instance2.webDriver2.findElement(pageWorkingWithFields.getButtonMain());
        pageWorkingWithFields.pressButtonMain(instance2.webDriver2);
        instance2.wait2.until(ExpectedConditions.stalenessOf(button));
        Assertions.assertTrue(instance2.webDriver2.findElement(pageWorkingWithMain.getButtonFiles()).isEnabled());
    }

    @And("check working the button Working_with_files to go to another page")
    public void checkWorkingTheButtonWorking_with_filesToGoToAnotherPage() {
        driver.get(url);
        WebElement button = instance2.webDriver2.findElement(pageWorkingWithFields.getButtonWorking_With_Files());
        pageWorkingWithFields.pressButtonWorkingWithFiles(instance2.webDriver2);
        instance2.wait2.until(ExpectedConditions.stalenessOf(button));
        Assertions.assertTrue(instance2.webDriver2.findElement(pageWorkingWithFiles.getButtonPush_for_download()).isEnabled());
    }

    @And("check working the button Files to go to enother page")
    public void checkWorkingTheButtonFilesToGoToEnotherPage() {
        driver.get(url);
        WebElement button = instance2.webDriver2.findElement(pageWorkingWithFields.getButtonFiles());
        pageWorkingWithFields.pressButtonFiles(instance2.webDriver2);
        instance2.wait2.until(ExpectedConditions.stalenessOf(button));
        Assertions.assertTrue(instance2.webDriver2.findElement(pageWorkingWithFiles.getButtonPush_for_download()).isEnabled());
    }

    @And("check working the button load_all_Users to get data for all users")
    public void checkWorkingTheButtonLoad_all_UsersToGetDataForAllUsers() {
        //последний раз возвращаюсь на страницу WorkingWithFields, дальше мы с этой страницы уже не уходим
        driver.get(url);

        pageWorkingWithFields.pressButtonLoadAllUser(instance2.webDriver2);
        Assertions.assertTrue(instance2.webDriver2.findElement(pageWorkingWithFields.getFieldInTableHeaderUser_Name()).isEnabled());
    }

    @And("check working the button Create_user to upload new user data")
    public void checkWorkingTheButtonCreate_userToUploadNewUserData() {
        instance2.webDriver2.findElement(pageWorkingWithFields.getFieldForCreateUserUser_name()).sendKeys("MmmName");
        instance2.webDriver2.findElement(pageWorkingWithFields.getFieldForCreateUserUser_login()).sendKeys("MmmLogin");
        instance2.webDriver2.findElement(pageWorkingWithFields.getFieldForCreateUserUser_email()).sendKeys("Mmm@ukr.net");
        instance2.webDriver2.findElement(pageWorkingWithFields.getFieldForCreateUserUser_university_name()).sendKeys("KPI");
        instance2.webDriver2.findElement(pageWorkingWithFields.getFieldForCreateUserUser_university_city()).sendKeys("Kiev");
        pageWorkingWithFields.pressButtonCreateUser(instance2.webDriver2);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement a = instance2.webDriver2.findElement(pageWorkingWithFields.getFieldInTableUser_Name());
        String aa = a.getAttribute("textContent");
        assertThat(aa).isEqualTo("MmmName");
    }

    @And("check working the button Delete")
    public void checkWorkingTheButtonDelete() {
        WebElement button = instance2.webDriver2.findElement(pageWorkingWithFields.getButtonForDeleteUser());
        pageWorkingWithFields.pressButtonDeleteUser(instance2.webDriver2);
        Assertions.assertTrue(instance2.wait2.until(ExpectedConditions.stalenessOf(button)));
    }

    @Then("ckeck working the button Send that load data to the server using form method")
    public void ckeckWorkingTheButtonSendThatLoadDataToTheServerUsingFormMethod() {
        instance2.webDriver2.findElement(pageWorkingWithFields.getFieldForSendFormNumber()).sendKeys("1");
        instance2.webDriver2.findElement(pageWorkingWithFields.getFieldForSendFormWord()).sendKeys("w");
        pageWorkingWithFields.pressButtonSend(instance2.webDriver2);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement a = instance2.webDriver2.findElement(pageWorkingWithFields.getFieldForOutputMessage());
        String aa = a.getText();
        System.out.println(aa);
        assertThat(aa).isEqualTo("You sent by form-method: number=1, word=w");
    }


}
