package testingWeb.tests.login_test;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.TestInstanceParameter;
import testingWeb.services.utils.TheDataProvider;
import testingWeb.tests.base.BaseTest;

public class FlowTest extends BaseTest {

    String text;

    @TestInstanceParameter("Interaction")
    private Object[] args;

	@Factory(dataProvider = "provideClassArgs", dataProviderClass = TheDataProvider.class)
    public FlowTest(Object[] args){
        this.args = args;
        this.text = args[0].toString();
    }
    
    @Severity(SeverityLevel.CRITICAL)
	@Description("")
	@Test(priority = 1, description = "")
	private void goLogin() throws InterruptedException {
       Thread.sleep(3000);
	    
	}
}
