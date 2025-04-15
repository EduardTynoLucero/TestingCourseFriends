package testingWeb.services.login;


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

import testingWeb.models.login.Principal;

public class PrincipalService {
    private Principal thePrincipal;
    private WebDriver theWebDriver;
    
    public PrincipalService(WebDriver driver){
        this.thePrincipal = new Principal();
        theWebDriver = driver; 
    }

    public void writeUser(String text){
        WebDriverWait wait = new WebDriverWait(theWebDriver, Duration.ofSeconds(10));
        WebElement usuarioField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(thePrincipal.getUsuario().getId())));
        usuarioField.sendKeys(text);
        takeScreenshot("writeUsuario");
    }

    public void writePassWord(String text){
        WebDriverWait wait = new WebDriverWait(theWebDriver, Duration.ofSeconds(10));
        WebElement contrasenaField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(thePrincipal.getContrasena().getId())));
        contrasenaField.sendKeys(text);
        takeScreenshot("writeContrasena");
    }

    public void clickMiCuenta() {
        WebDriverWait wait = new WebDriverWait(theWebDriver,Duration.ofSeconds(10) );
        WebElement miCuentaButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='navbar-auth-button-desktop']/div/div")));
        miCuentaButton.click();
        takeScreenshot("clickMiCuenta");
    }

    public void clickIniciarSesion() {
        WebDriverWait wait = new WebDriverWait(theWebDriver, Duration.ofSeconds(10));
        WebElement iniciarSesionButton = wait.until(ExpectedConditions.elementToBeClickable(By.id(thePrincipal.getIniciarSesion().getId())));
        iniciarSesionButton.click();
        takeScreenshot("clickIniciarSesion");
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
