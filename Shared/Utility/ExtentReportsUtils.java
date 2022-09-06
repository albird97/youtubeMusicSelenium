package Shared.Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import Shared.Base.BaseDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ExtentReportsUtils extends BaseDriver {
    static ExtentReports extent;

    public synchronized static ExtentReports getInstance() throws IOException {

        if (extent == null)
            createInstance();

        return extent;
    }

    public static ExtentReports createInstance() throws IOException {
        ConfigUtils config = new ConfigUtils();

        File dir = new File(reportPath);
        dir.getParentFile().mkdirs();

        File reportConfig = new File(System.getProperty("user.dir") + "/shared/config/extent-config.xml");
        ExtentSparkReporter reports = new ExtentSparkReporter(reportPath)
                .viewConfigurer().viewOrder()
                .as(new ViewName[]{ViewName.DASHBOARD,ViewName.TEST,ViewName.CATEGORY,ViewName.EXCEPTION}).apply();
        try {
            reports.loadXMLConfig(reportConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }

        extent = new ExtentReports();
        extent.attachReporter(reports);
        extent.setSystemInfo("Platform", config.getConfig("devicePlatform"));
        extent.setSystemInfo("Browser", config.getConfig("browser"));

        return extent;
    }
}
