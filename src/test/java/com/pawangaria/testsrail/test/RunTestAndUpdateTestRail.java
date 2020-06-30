package com.pawangaria.testsrail.test;

import com.pawangaria.testrail.example.listeners.TestNgTestRailListener;
import com.pawangaria.testrail.example.util.UseAsTestRailId;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Example to run the simple Tests and Update the test Rail.
 * @author Pawan Garia
 */
@Listeners(TestNgTestRailListener.class)
public class RunTestAndUpdateTestRail {

    @Test
    @UseAsTestRailId(testRailId =74633)
    public void testShouldPass()
    {
        System.out.println("Running Test which will Pass");
        Assert.assertTrue(true);
    }

    @Test
    @UseAsTestRailId(testRailId =74634)
    public void testShouldFailWithException() {
        System.out.println("Running Test which will fail");
        Assert.assertTrue(false);
    }

    @Test
    @UseAsTestRailId(testRailId =74638)
    public void testShouldSkipWithException() {
        System.out.println("Running Test which will Skip");
        throw new SkipException("Skipping the Test.");
    }
}
