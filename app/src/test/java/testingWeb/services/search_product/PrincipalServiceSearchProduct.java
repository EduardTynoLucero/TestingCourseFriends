package testingWeb.services.search_product;

import java.io.File;
import java.io.IOException;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import testingWeb.models.search_product.SearchProduct;

public class PrincipalServiceSearchProduct {
    private SearchProduct theSearchProduct;
    private WebDriver theWebDriver;


    public PrincipalServiceSearchProduct(WebDriver driver){
        this.theSearchProduct = new SearchProduct();
        theWebDriver = driver; 
    }


    public void transactionSearchProducto(String product){

        System.out.println("toy en la transaccion");
        clickMenu();
        clickCelulares();
        clickSansumg();
    }

    public void clickMenu(){
        WebDriverWait wait = new WebDriverWait(theWebDriver, Duration.ofSeconds(10));
        WebElement inputProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='secondary-navbar-item-desktop-megamenu']")));
        inputProduct.click();
        System.out.println("SI DI CLICK OK: ");
        takeScreenshot("SearchProduct");
    }


    public void clickCelulares(){
        WebDriverWait wait = new WebDriverWait(theWebDriver, Duration.ofSeconds(10));
        WebElement inputProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mobile-megamenu-v2-button-desktop-1342']")));
        inputProduct.click();
        System.out.println("SI DI CLICK OK: ");
        takeScreenshot("SearchProduct");
    }


    public void clickSansumg(){
        WebDriverWait wait = new WebDriverWait(theWebDriver, Duration.ofSeconds(10));
        WebElement inputProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mobile-megamenu-v2-categories-section-category-link-desktop-1511']")));
        inputProduct.click();
        System.out.println("SI DI CLICK OK: ");
        takeScreenshot("SearchProduct");
    }





    public  void takeScreenshot(String methodName){
        File screenShot = ((TakesScreenshot) theWebDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShot,  new File(System.getProperty("user.dir") +  "screenshot/" +  methodName +  ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
