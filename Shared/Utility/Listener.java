package Shared.Utility;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import Shared.Base.BaseDriver;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Listener extends BaseDriver implements ITestListener {

    ExtentReports extent;
    WebDriver driver;
    private List<ITestNGMethod> testMethods = null;
    String testName = null;
    String testCategoryTemp = null;

    {
        try {
            extent = ExtentReportsUtils.createInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    ExtentTest test;
    @Override
    public void onTestStart(ITestResult iTestResult) {
        driver = ((BaseDriver) iTestResult.getInstance()).getDriver();
        String priority = "<span class=\"badge badge-info\">"+"P"+iTestResult.getMethod().getPriority()+"</span>";
        test = extent.createTest(priority+" "+iTestResult.getMethod().getMethodName());

        String testClass = iTestResult.getMethod().getTestClass().getName();
        testCategoryTemp = testClass.substring(0, testClass.indexOf(".")).toUpperCase();

        testName = testCategoryTemp;
        test.assignCategory(testCategoryTemp);
        if (testCategory == null){
            testCategory = testCategoryTemp;
        } else if (!testCategory.contains(testCategoryTemp)){
            testCategory = testCategory + ", " + testCategoryTemp;
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test.pass(MarkupHelper.createLabel(iTestResult.getName() + "--Test case pass", ExtentColor.GREEN));
        passCount = passCount + 1;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ExtentColor color;
        test.fail(iTestResult.getThrowable());
        failCount = failCount + 1;
        String screenshotName = iTestResult.getName() + "_" + reportDateFormat;
        File imageFile = null;
        imageFile = ((TakesScreenshot) ((BaseDriver) iTestResult.getInstance()).getDriver()).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/testResults/report-"+ reportDateFormat +"/screenshots/" + screenshotName + ".png";
        File file = new File(System.getProperty("user.dir") + "/testResults/report-"+ reportDateFormat +"/screenshots");
        file.mkdirs();


        File failureImageFile = new File(screenshotPath);

        try {
            Files.copy(imageFile, failureImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String newScreenshotPath = "../screenshots/" + iTestResult.getName() + "_" + reportDateFormat + ".png";
        test.fail(iTestResult.getThrowable().getMessage(),
                MediaEntityBuilder.createScreenCaptureFromPath(newScreenshotPath).build());

        if (iTestResult.getThrowable().toString().contains("NoSuchElementException") ||
                iTestResult.getThrowable().toString().contains("TimeoutException")) {
            color = ExtentColor.ORANGE;
            test.fail(MarkupHelper.createLabel(iTestResult.getName() + "--Timeout or Element Not Found", color));
        } else if (iTestResult.getThrowable().toString().contains("AssertionError")) {
            color = ExtentColor.YELLOW;
            test.fail(MarkupHelper.createLabel(iTestResult.getName() + "--Assertion Error", color));
        } else {
            color = ExtentColor.RED;
            test.fail(MarkupHelper.createLabel(iTestResult.getName() + "--Test case failed", color));
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ExtentColor color;
        test.skip(iTestResult.getThrowable());
        skipCount = skipCount + 1;
        String screenshotName = iTestResult.getName() + "_" + reportDateFormat;
        File imageFile = null;
        imageFile = ((TakesScreenshot) ((BaseDriver) iTestResult.getInstance()).getDriver()).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/testResults/report-"+ reportDateFormat +"/screenshots/" + screenshotName + ".png";
        File file = new File(System.getProperty("user.dir") + "/testResults/report-"+ reportDateFormat +"/screenshots");
        file.mkdirs();


        File failureImageFile = new File(screenshotPath);

        try {
            Files.copy(imageFile, failureImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String newScreenshotPath = "../screenshots/" + iTestResult.getName() + "_" + reportDateFormat + ".png";
        test.skip(iTestResult.getThrowable().getMessage(),
                MediaEntityBuilder.createScreenCaptureFromPath(newScreenshotPath).build());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        testMethods = Arrays.asList(iTestContext.getAllTestMethods());
        testCount = testMethods.size();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

        endTime = System.currentTimeMillis();
        long totalTimeMilliSeconds = endTime - startTime;
        long timeMilliSeconds = totalTimeMilliSeconds % 1000;
        long timeSeconds = TimeUnit.MILLISECONDS.toSeconds(totalTimeMilliSeconds) % 60;
        long timeMinutes = TimeUnit.MILLISECONDS.toMinutes(totalTimeMilliSeconds) % 60;
        long timeHour = TimeUnit.MILLISECONDS.toHours(totalTimeMilliSeconds);

        timeCount = timeHour + "h " + timeMinutes + "m " + timeSeconds + "s " + timeMilliSeconds + "ms";

        extent.flush();
    }
}