package com.learning.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListner implements ITestListener {

    public void onTestStart(ITestResult result) {
        System.out.println("---Test is Starting---");
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("---Test Passed---");
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("---Test Failed---");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("---Test Skipped---");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {
        System.out.println("---START---");
    }

    public void onFinish(ITestContext context) {
        System.out.println("---FINISH---");
    }


}
