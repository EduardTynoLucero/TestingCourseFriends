package testingWeb.tests.login_test;
import java.util.List;

import org.testng.TestNG;

import com.beust.jcommander.internal.Lists;

public class RunTest {
    public static void main(String[] args){
	    TestNG testng = new TestNG();
	    List<String> suites = Lists.newArrayList();
        suites.add("C:\\Users\\eduar\\Documents\\TESTING\\testingCourse\\app\\src\\test\\java\\testingWeb\\tests\\login_test\\FlowTest.xml"
        );

	    testng.setTestSuites(suites);
	    testng.run();
    }
}
