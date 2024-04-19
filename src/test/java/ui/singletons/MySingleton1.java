package ui.singletons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MySingleton1 {
    private static volatile MySingleton1 instance1;

    public WebDriver webDriver1;
    public WebDriverWait wait1;

    private MySingleton1(){}

    public static MySingleton1 getInstance1() {
        if (instance1 == null) {
            synchronized (MySingleton1.class) {
                if (instance1 == null) {
                    instance1 = new MySingleton1();
                }
            }
        }
        return instance1;
    }
}
