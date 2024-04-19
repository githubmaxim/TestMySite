package ui.pages;

import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class PageWorkingWithFiles {
    private By buttonPush_for_download = By.xpath("//div[@class='col21']//input[@value='push for download']");
}
