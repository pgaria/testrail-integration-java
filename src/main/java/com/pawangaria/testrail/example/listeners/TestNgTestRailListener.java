package com.pawangaria.testrail.example.listeners;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.pawangaria.testrail.example.client.APIClient;
import com.pawangaria.testrail.example.client.TestRailStatusID;
import com.pawangaria.testrail.example.util.UseAsTestRailId;
import org.json.simple.JSONObject;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

/**
 * TestNgTestRailListener for Tracking the Test Results and Updating the Test Rail.
 * Implementation supports both normal tests and Data Provider test.
 *
 * @author Pawan Garia
 */
public class TestNgTestRailListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        UseAsTestRailId useAsTestRailId =
                method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(UseAsTestRailId.class);
        //Data driven tests need to be handled differently
        if (method.getTestMethod().isDataDriven()) {
            // Get the Parameters from the Result
            Object[] parameters = testResult.getParameters();
            //Added this Code as There is an Issue with the TestNG Right now in Skip Status version: '7.1.0'
            if (testResult.getThrowable() instanceof SkipException) {
                testResult.setStatus(ITestResult.SKIP);
            }
            // Post the result to Test Rail
            new PostResults().postTestRailResult(
                    useAsTestRailId.testRailId(), testResult, Arrays.toString(parameters));
        } else {
            new PostResults().postTestRailResult(useAsTestRailId.testRailId(), testResult);
        }
    }

}