package Shared.Base;

import YoutubeMusic.Pages.loginPage;
import YoutubeMusic.Pages.homePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class BaseDriver {


    public static WebDriver driver = null;
    protected static String reportDateFormat = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss").format(Calendar.getInstance().getTime());
    protected static int passCount = 0;
    protected static int failCount = 0;
    protected static int skipCount = 0;
    protected static int testCount = 0;
    public static long startTime = System.currentTimeMillis();
    public static long endTime;
    public static ExtentReports extent;
    public static ExtentTest test = null;
    protected static String timeCount = null;
    protected static String testCategory;
    protected static String reportPath = System.getProperty("user.dir") + "/testResults/report-"+ reportDateFormat +"/report/mobile-automation-report.html";

    protected static loginPage loginPage = null;
    protected static homePage homePage = null;


    @BeforeMethod
    public void launchBrowser() throws Exception {
        setDriver();
    }

    public void setUpDriver() throws Exception {
        String projectPath = System.getProperty("user.dir");
        FileInputStream file = new FileInputStream(projectPath + "/Shared/Config/Config.properties");
        Properties prop = new Properties();
        prop.load(file);

        if (prop.getProperty("browser").equals("Chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            if (prop.getProperty("devicePlatform").equals("Linux")){
                System.setProperty("webdriver.chrome.driver", projectPath+"/Shared/Driver/Linux/chromedriver");
                driver = new ChromeDriver(options);
            }else if (prop.getProperty("devicePlatform").equals("Mac")){
                System.setProperty("webdriver.chrome.driver", projectPath+"/Shared/Driver/Mac/chromedriver");
                driver = new ChromeDriver(options);
            }else if (prop.getProperty("devicePlatform").equals("Mac_M1")){
                System.setProperty("webdriver.chrome.driver", projectPath+"/Shared/Driver/Mac_M1/chromedriver");
                driver = new ChromeDriver(options);
            }else if (prop.getProperty("devicePlatform").equals("Windows")){
                System.setProperty("webdriver.chrome.driver", projectPath+"/Shared/Driver/Windows/chromedriver");
                driver = new ChromeDriver(options);
            }
        }else if (prop.getProperty("browser").equals("Firefox")){
            if (prop.getProperty("devicePlatform").equals("Linux")){
                System.setProperty("webdriver.chrome.driver", projectPath+"/Shared/Driver/Linux/geckodriver");
                driver = new FirefoxDriver();
            }else if (prop.getProperty("devicePlatform").equals("Mac")){
                System.setProperty("webdriver.chrome.driver", projectPath+"/Shared/Driver/Mac/geckodriver");
                driver = new FirefoxDriver();
            }else if (prop.getProperty("devicePlatform").equals("Mac_M1")){
                System.setProperty("webdriver.chrome.driver", projectPath+"/Shared/Driver/Mac_M1/geckodriver");
                driver = new FirefoxDriver();
            }else if (prop.getProperty("devicePlatform").equals("Windows")){
                System.setProperty("webdriver.chrome.driver", projectPath+"/Shared/Driver/Windows/geckodriver");
                driver = new FirefoxDriver();
            }
        }
        System.out.println("SUCCESSFULLY SETUP DRIVER");
    }

    public WebDriver setDriver() throws Exception{
        if (driver==null){
            setUpDriver();
            loginPage = new loginPage();
            homePage = new homePage();
        }
        return driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        driver.quit();
        driver = null;
    }

    public void info(String message) {
        System.out.println("INFO : " + message);
        test.log(Status.INFO,message);
    }

    // Get log info and print
    public void pass(String message) {
        System.out.println("PASS : " + message);
        test.log(Status.PASS, message);
    }

    // Get fail and take screenshot
    public void fail(String message) {
        System.out.println("FAIL : " + message);
        test.log(Status.FAIL, message);
    }

}
