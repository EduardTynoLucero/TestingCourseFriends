package testingWeb.tests.base;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.annotations.*;

import testingWeb.services.utils.TheData;
import testingWeb.services.utils.web_driver_factory.WebDriverFactory;
import testingWeb.models.login.ConfigBrowser;
import testingWeb.services.login.PrincipalService;
import static testingWeb.services.utils.TheData.getExcelData;
import static testingWeb.services.utils.TheJsonModels.loadJsonModels;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//* @author Bryan Lucero

@Listeners(BaseTest.class)
public class BaseTest extends TestListenerAdapter implements IExecutionListener {
      private String browser;
    protected WebDriver webDriver;
    private ConfigBrowser configBrowser;
    public static Map<String, WebDriver> webDriverMap = new ConcurrentHashMap<>();
    private static Map<String, ConfigBrowser> configBrowserMap = new ConcurrentHashMap<>();
    public static JSONObject jsonModels;

    @Override
    public void onExecutionStart() {
        // Eliminar reporte anterior de Allure Report
       // DeleteFolder(".\\allure-results");
        // Load Config
        String[][] matriz = getExcelData(System.getProperty("user.dir") + "\\app\\src\\test\\resources\\Configurations.xlsx","Configurations");
        int rows = matriz.length;
        int columns = matriz.length > 0 ? matriz[0].length : 0;
        for (int row = 0; row < rows; row++) {
            if (columns >= 5) {
            	 ConfigBrowser configBrowser = new ConfigBrowser(matriz[row][0], matriz[row][2], matriz[row][4], matriz[row][5], matriz[row][3]);
                

                configBrowserMap.computeIfAbsent(matriz[row][1], k -> {
                    try {
                        return configBrowser;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        // Load JSON Models
        loadJsonModels(System.getProperty("user.dir") + "\\app\\src\\test\\java\\testingWeb\\models\\identifiers");
    }

    private static void DeleteFolder(String folderPath) {
        try {
            FileUtils.deleteQuietly(new File(folderPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setUp(String browser) {
        this.browser = browser;
        System.out.println(browser);
        this.configBrowser = configBrowserMap.get(browser);

        if (this.configBrowser != null) {
        
            if (this.configBrowser.getProbar()) {
             
                this.webDriver = webDriverMap.computeIfAbsent(browser, k -> { 
               
                    try {
                        return WebDriverFactory.getDriver(browser);
                    } catch (Exception e) {
                        return null;
                        // throw new RuntimeException(e);
                    }
                });
                if (this.webDriver != null) { // Éxito
                    try {
                        if (this.webDriver.getCurrentUrl().equals("data:,")) {
                            this.webDriver.get(this.configBrowser.getEnlace()); 
                        }
                    } catch (WebDriverException ex) {
                        // System.out.println(ex.getMessage());
                    }
                    this.webDriver.manage().window().maximize();
                } else { // WebDriver no encontrado o error al intentar crearlo
                    throw new RuntimeException("ERROR: Error al intentar crear el WebDriver " + browser);
                }
            } else { // No se debe probar en el navegador
                throw new SkipException("Skipping Test. No Probar Navegador " + browser);
                
            }
        } else { // Configuración del navegador no encontrada
        
            System.out.println("ERRRORRRRR");
        }
    }

    
    @BeforeClass(alwaysRun = true, dependsOnMethods = {"setUp"})
    protected void logBrowser() throws InterruptedException {
        if (this.webDriver != null) {

            //implicit wait
            WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='navbar-auth-button-desktop']/div/div")));
         

            By elementLocator = By.xpath("//*[@id='navbar-auth-button-desktop']/div/div");
            if (!this.webDriver.findElements(elementLocator).isEmpty()){
                PrincipalService theSR1_Principal = new PrincipalService(this.webDriver);
                ConfigBrowser configBrowser = configBrowserMap.get(this.browser);
                theSR1_Principal.clickMiCuenta();
                theSR1_Principal.writeUser(configBrowser.getUsuario());
                theSR1_Principal.writePassWord(configBrowser.getContrasena());
                theSR1_Principal.clickIniciarSesion();
        
            }
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) { 
    }

    @Override
    public void onFinish(ITestContext context) {
        // Omitir en reporte tests ignorados manualmente
        Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();
        while (skippedTestCases.hasNext()) {
            ITestResult skippedTestCase = skippedTestCases.next();
            ITestNGMethod method = skippedTestCase.getMethod();
            if (context.getSkippedTests().getResults(method).size() > 0) {
             
                skippedTestCases.remove();
            }
        }
    }

  public void onExecutionFinish() {
        //Close WebDrivers
     for (WebDriver driver : webDriverMap.values()) {
        if (driver != null) {
            driver.quit();
        }
     }
     
     
    }

    private void ejecutarComandoCMD(String comandoCMD) {
        try {
            Runtime.getRuntime().exec("cmd /c start " + comandoCMD);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteBrowserSkippedReportFile(String directoryPath) {
        try {
            Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(BaseTest::processReportFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processReportFile(Path filePath) {
        try {
            // Parsing file
            FileReader content = new FileReader(filePath.toString());
            // Obtener contenido del archivo
            BufferedReader bufferedReader = new BufferedReader(content);
            String contenido = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                contenido += line + "\n";
            }
            String contenidoAux = contenido;
            if (!webDriverMap.keySet().stream().anyMatch(clave -> contenidoAux.contains(
                    "{\"name\":\"browser\",\"value\":\"" + clave.toString() + "\"}"))) {
                writeFile(filePath.toString(), "");
            }
        }
        catch(Exception e) {
            System.out.println("Error al analizar Archivo Json para Reporte");
            System.out.println(e.getMessage());
        }
    }

    private static void writeFile(String thePath, String theContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(thePath))) {
            writer.write(theContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFile(String filePath) {
        File file = new File(filePath);
        try {
            FileUtils.forceDelete(file);
            // System.out.println("File is successfully deleted.");
        } catch (IOException e) {
            // System.out.println("File deletion failed.");
            e.printStackTrace();
        }
    }
}
