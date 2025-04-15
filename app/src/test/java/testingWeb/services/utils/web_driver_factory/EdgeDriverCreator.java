package testingWeb.services.utils.web_driver_factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverCreator extends WebDriverCreator {

    @Override
    public WebDriver createWebDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}
