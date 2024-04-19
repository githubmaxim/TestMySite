package ui.singletons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MySingleton2 {
    private static volatile MySingleton2 instance2;

    public WebDriver webDriver2;
    public WebDriverWait wait2;

    private MySingleton2(){}

    public static MySingleton2 getInstance2() {
        if (instance2 == null) {
            synchronized (MySingleton2.class) {
                if (instance2 == null) {
                    instance2 = new MySingleton2();
                }
            }
        }
        return instance2;
    }
}
