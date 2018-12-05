package com.learning.base;

import com.aventstack.extentreports.ExtentReports;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

public class ExtentReportListner implements IReporter {

    private static final String OUTPUT_FOLDER = "target/";
    private static final String FILE_NAME = "ExtentReport.html";

    private ExtentReports extent;
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites){

    }

}
