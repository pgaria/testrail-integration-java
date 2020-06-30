package com.pawangaria.testsrail.test;

import com.pawangaria.testrail.example.listeners.TestNgTestRailListener;
import com.pawangaria.testrail.example.util.UseAsTestRailId;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Example of Implementing the Data Provider with TestRail
 * @author Pawan Garia
 */
@Listeners(TestNgTestRailListener.class)
public class ExampleWithDataProvider {

    @UseAsTestRailId(testRailId = 70218)
    @Test(dataProvider = "provideTestData")
    public void testUsingDataProvider(String userName, String password, String result) {
        if (result.equalsIgnoreCase("Pass")) {
            Assert.assertTrue(true);
        } else if (result.equalsIgnoreCase("Fail")) {
            //Fail the test
            Assert.assertTrue(false);
        } else if (result.equalsIgnoreCase("Skip")) {
            //Skip the Test
            throw new SkipException("Skipping the Test");
        }
    }

    @DataProvider(name = "provideTestData")
    public Object[][] provideTestData() {

        return new Object[][]{
                {"UserName 1", "Password 1", "Pass"},
                {"UserName 2", "Password 2", "Fail"},
                {"UserName 3", "Password 3", "Skip"},
        };
    }
}
