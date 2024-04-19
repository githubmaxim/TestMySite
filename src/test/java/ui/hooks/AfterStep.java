package ui.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.singletons.MySingleton1;
import ui.singletons.MySingleton2;

public class AfterStep {
    MySingleton1 instance1 = MySingleton1.getInstance1();
    MySingleton2 instance2 = MySingleton2.getInstance2();
//    MySingleton3 instance3 = MySingleton3.getInstance3();

    @After
    public void closeMainPage() {
        if (!(instance1.webDriver1 == null)) {
            instance1.webDriver1.quit();
        }
    }

    @After
    public void closeWorkingWithFieldsPage() {
        if (!(instance2.webDriver2 == null)) {
            //Так не срабатывает - зачем-то пытается ждать появления этого элемента и при закрытии "driver1"
//            instance2.wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='col21']//div[text()='You sent by form-method: number=1, word=w']")));

            //Так срабатывает - хватает 5 секунд ожидания, после за закрытия "driver1", на выполнение и 2-го сценария Cucumber
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            instance2.webDriver2.quit();
        }
    }
//
//    @After
//    public void closeWorkingWithFilesPage() {
//        if (!(instance3.webDriver3 == null)) {
//            instance3.webDriver3.quit();
//        }
//    }

}
