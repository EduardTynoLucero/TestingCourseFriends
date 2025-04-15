package testingWeb.services.utils.web_driver_factory;


import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
    public static WebDriver getDriver(String browser) throws Exception {
        switch (browser) {
            case "chrome":
                ChromeDriverCreator chromeDriverCreator = new ChromeDriverCreator();
                return chromeDriverCreator.createWebDriver();
            case "edge":
                EdgeDriverCreator edgeDriverCreator = new EdgeDriverCreator();
                return edgeDriverCreator.createWebDriver();
            case "firefox":
                FirefoxDriverCreator firefoxDriverCreator = new FirefoxDriverCreator();
                return firefoxDriverCreator.createWebDriver();
            case "browserstack":
                BrowserstackDriverCreator browserstackDriverCreator = new BrowserstackDriverCreator();
                return browserstackDriverCreator.createWebDriver();
            default:
                throw new Exception("Navegador " + browser + " no soportado");
        }
    }
}
