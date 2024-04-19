package ui.singletons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MySingleton3 {
    private static volatile MySingleton3 instance3;

    public WebDriver webDriver3;
    public WebDriverWait wait3;

    private MySingleton3(){}

    public static MySingleton3 getInstance3() {
        if (instance3 == null) {
            synchronized (MySingleton3.class) {
                if (instance3 == null) {
                    instance3 = new MySingleton3();
                }
            }
        }
        return instance3;
    }
}
