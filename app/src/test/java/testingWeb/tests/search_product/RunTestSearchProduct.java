package testingWeb.tests.search_product;

import java.util.List;

import org.testng.TestNG;

import com.beust.jcommander.internal.Lists;

public class RunTestSearchProduct {
    
    public static void main(String[] args){
	    TestNG testng = new TestNG();
	    List<String> suites = Lists.newArrayList();
        suites.add("C:\\Users\\eduar\\Documents\\TESTING\\PROYECTOSELENIUM\\TestingCourseFriends\\app\\src\\test\\java\\testingWeb\\tests\\search_product\\FlowTestSearchProduct.xml"
        );

	    testng.setTestSuites(suites);
	    testng.run();
    }
}
