package testingWeb.services.utils;

import org.testng.annotations.DataProvider;

import static testingWeb.services.utils.TheData.getExcelData;

import java.util.Arrays;

public class TheDataProvider {
    
    @DataProvider(name = "provideClassArgs")
    public static Object[][] provideClassArgs(){
        Object[][] matriz = Arrays.stream(getExcelData( "C:\\Users\\eduar\\Documents\\TESTING\\testingCourse\\app\\src\\test\\resources\\DatosGenerales\\DataTable.xlsx","Data"))
        .map(row -> new Object[]{row})
        .toArray(Object[][]::new);
        return matriz;
    }
}
