package com.pawangaria.testrail.example.listeners;

import com.pawangaria.testrail.example.client.APIClient;
import com.pawangaria.testrail.example.client.TestRailStatusID;
import org.json.simple.JSONObject;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

public class PostResults {

    /**
     * Post Test Rail Results.
     *
     * @param testRailId
     * @param result
     */
    public void postTestRailResult(int testRailId, ITestResult result) {
        // Post To TestRail
        System.out.println("Test case Id [" + testRailId + "] with Status::" + result.getStatus());
        post(testRailId, getTestRailMetaData(result));
    }

    /**
     * Post Test Rail Results with the Parameters
     *
     * @param testRailId
     * @param result
     * @param parameters
     */
    public void postTestRailResult(int testRailId, ITestResult result, String parameters) {
        // Post To TestRail
        System.out.println("TestId [" + testRailId + "] with parameter " + parameters + " with Status::" + result.getStatus());
        post(testRailId, getTestRailMetaData(result, parameters));
    }

    /**
     * Create a Map object which contains the MetaData for the Test which needs to be updated in Test Rail.
     * This is with the Parameters.
     *
     * @param result
     * @return
     */
    private Map getTestRailMetaData(ITestResult result, String parameters) {
        //Set the status_id for the test Rail for Pass, Fail and Skip status.
        Map dataTestRail = new HashMap();
        if (result.getStatus() == ITestResult.SUCCESS) {
            dataTestRail.put("status_id", TestRailStatusID.PASS);
            dataTestRail.put("comment", "SUCCESS with parameters :" + parameters);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            dataTestRail.put("status_id", TestRailStatusID.FAIL);
            dataTestRail.put("comment", "FAILURE with parameters :" + parameters + "\n" + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            dataTestRail.put("status_id", TestRailStatusID.RETEST);
            dataTestRail.put("comment", "SKIPPED with parameters :" + parameters + "\n" + result.getThrowable());
        }
        return dataTestRail;
    }

    /**
     * Create a Map object which contains the MetaData for the Test which needs to be updated in Test Rail.
     *
     * @param result
     * @return
     */
    private Map getTestRailMetaData(ITestResult result) {
        //Set the status_id for the test Rail for Pass, Fail and Skip status.
        Map dataTestRail = new HashMap();
        if (result.getStatus() == ITestResult.SUCCESS) {
            dataTestRail.put("status_id", TestRailStatusID.PASS);
            dataTestRail.put("comment", "SUCCESS");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            dataTestRail.put("status_id", TestRailStatusID.FAIL);
            dataTestRail.put("comment", "FAILURE" + "\n" + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            dataTestRail.put("status_id", TestRailStatusID.RETEST);
            dataTestRail.put("comment", "SKIPPED" + "\n" + result.getThrowable());
        }
        return dataTestRail;
    }

    /**
     * Call TestRail Client and Post Request.
     * Note: Update the Test Run ID properly of you will see 400 in Response.
     *
     * @param testRailId
     * @param data
     */
    private void post(int testRailId, Map data) {
        try {
            JSONObject r = (JSONObject) getTestRailAPIClient().sendPost("add_result/" + testRailId + "/<TestRunID>", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the Test Rail Api Client.
     *
     * @return
     */
    private APIClient getTestRailAPIClient() {
        APIClient client = new APIClient("<Test Rail URL>");
        client.setUser("<TestRailUserName>");
        client.setPassword("<Password>");
        return client;
    }
}
