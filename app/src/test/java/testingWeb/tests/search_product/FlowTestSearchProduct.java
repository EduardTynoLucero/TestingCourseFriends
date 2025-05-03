package testingWeb.tests.search_product;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.TestInstanceParameter;
import testingWeb.services.utils.TheDataProvider;
import testingWeb.services.search_product.PrincipalServiceSearchProduct;
import testingWeb.tests.base.BaseTest;



public class FlowTestSearchProduct extends BaseTest {

    String product;

    @TestInstanceParameter("Interaction")
    private Object[] args;

	@Factory(dataProvider = "provideClassArgs", dataProviderClass = TheDataProvider.class)
    public FlowTestSearchProduct(Object[] args){
        this.args = args;
        this.product = args[1].toString();
    }
    
    @Severity(SeverityLevel.CRITICAL)
	@Description("")
	@Test(priority = 1, description = "")
	private void goLogin() throws InterruptedException {
       Thread.sleep(3000);
	    
	}

    @Severity(SeverityLevel.CRITICAL)
	@Description("")
	@Test(priority = 2, description = "")
	private void transactionSearchProducto() throws InterruptedException {
        PrincipalServiceSearchProduct thePrincipalServiceSearchProduct = new PrincipalServiceSearchProduct(webDriver);

        Thread.sleep(3000);
        thePrincipalServiceSearchProduct.transactionSearchProducto(product);
        Thread.sleep(10000);
	}

}
