package ui.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//Тут на конкретной странице мы выбираем необходимые элементы и (при необходимости) выполняем с ними какие-то действия
// в отдельных методах. После чего в основных классах просто получаем отсюда элементы/элементы с какими-то действиями
// с ними. Это паттерн "Page object".

@Getter
public class PageWorkingWithMain {
    private By buttonWorking_With_Fields = By.xpath("//div[@class='col1']//p[1]/a[@class='link']");
    private By buttonFields = By.xpath("//div[@class='in_nav']//a[@class='link1']");
    private By buttonWorking_With_Files = By.xpath("//div[@class='col1']//p[2]/a[@class='link']");
    private By buttonFiles = By.xpath("//div[@class='in_nav']//a[@class='link2']");


    public void pressButtonWorkingWithFields(WebDriver driver){
        driver.findElement(buttonWorking_With_Fields).click();
    }
    public void pressButtonFields(WebDriver driver){
        driver.findElement(buttonFields).click();
    }
    public void pressButtonWorkingWithFiles(WebDriver driver){
        driver.findElement(buttonWorking_With_Files).click();
    }
    public void pressButtonFiles(WebDriver driver){
        driver.findElement(buttonFiles).click();
    }
}
