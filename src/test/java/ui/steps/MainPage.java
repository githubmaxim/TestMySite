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
import ui.singletons.MySingleton1;
import ui.pages.PageWorkingWithFields;
import ui.pages.PageWorkingWithFiles;
import ui.pages.PageWorkingWithMain;

import java.time.Duration;

public class MainPage {
    PageWorkingWithMain pageWorkingWithMain = new PageWorkingWithMain();
    PageWorkingWithFields pageWorkingWithFields = new PageWorkingWithFields();
    PageWorkingWithFiles pageWorkingWithFiles = new PageWorkingWithFiles();
    MySingleton1 instance1 = MySingleton1.getInstance1();

    //переменные, которые будем использовать для возврата на страницу Main (ту, которую проверяем)
    String url;
    WebDriver driver;

    @Given("open Webdriver1 for site {string}")
    public void openWebdriverForSite(String url1) {
        System.setProperty("webdriver.chrome.driver", "D:\\Java\\Selenium\\chromedriver.exe");
        WebDriver driver1 = new ChromeDriver();
        driver1.get(url1);
        instance1.webDriver1 = driver1;
        instance1.wait1 = new WebDriverWait(driver1, Duration.ofSeconds(10));

        driver = driver1;
        url = url1;
    }

    @When("check working the button Working_with_fields")
    public void checkWorkingTheButtonWorking_with_fields() {
        //Вначале получаем элемент на странице "Main", иначе потом его для проверки на исчезновение получить не получится
        WebElement button = instance1.webDriver1.findElement(pageWorkingWithMain.getButtonWorking_With_Fields());

        //Нажимаем на кнопку перехода на страницу "WorkingWithFields"
        pageWorkingWithMain.pressButtonWorkingWithFields(instance1.webDriver1);

        //ожидаем исчезновения элемента на странице "Main" и как следствие перехода на страницу "WorkingWithFields" для возможности поиска там какого-то элемента (рекомендовали делать этот промежуточный шаг)
        instance1.wait1.until(ExpectedConditions.stalenessOf(button));

        //Проверяем наличие элемента на странице "WorkingWithFields"
        Assertions.assertTrue(instance1.webDriver1.findElement(pageWorkingWithFields.getButtonCreate_user()).isEnabled());
        System.out.println("ButtonWorking_with_fieds - ok");
    }

    @And("check working the button Fields")
    public void checkWorkingTheButtonFields() {
        //Возвращаемся на страницу Main (ту, которую проверяем)
        driver.get(url);

        WebElement button = instance1.webDriver1.findElement(pageWorkingWithMain.getButtonFields());
        pageWorkingWithMain.pressButtonFields(instance1.webDriver1);
        instance1.wait1.until(ExpectedConditions.stalenessOf(button));
        Assertions.assertTrue(instance1.webDriver1.findElement(pageWorkingWithFields.getButtonCreate_user()).isEnabled());
        System.out.println("ButtonFieds - ok");
    }

    @And("check working the button Working_with_files")
    public void checkWorkingTheButtonWorking_with_files() {
        //Возвращаемся на страницу Main (ту, которую проверяем)
        driver.get(url);

        WebElement button = instance1.webDriver1.findElement(pageWorkingWithMain.getButtonWorking_With_Files());
        pageWorkingWithMain.pressButtonWorkingWithFiles(instance1.webDriver1);
        instance1.wait1.until(ExpectedConditions.stalenessOf(button));
        Assertions.assertTrue(instance1.webDriver1.findElement(pageWorkingWithFiles.getButtonPush_for_download()).isEnabled());
        System.out.println("ButtonWorking_with_files - ok");
    }

    @Then("check working the button Files")
    public void checkWorkingTheButtonFiles() {
        //Возвращаемся на страницу Main (ту, которую проверяем)
        driver.get(url);

        WebElement button = instance1.webDriver1.findElement(pageWorkingWithMain.getButtonFiles());
        pageWorkingWithMain.pressButtonFiles(instance1.webDriver1);
        instance1.wait1.until(ExpectedConditions.stalenessOf(button));
        Assertions.assertTrue(instance1.webDriver1.findElement(pageWorkingWithFiles.getButtonPush_for_download()).isEnabled());
        System.out.println("ButtonFiles - ok");
    }
}
