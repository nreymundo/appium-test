package standalone;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.Date;

import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppTest {

    private static final String CURRENT_DATE = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                                                .format(new Timestamp(new Date().getTime()));
    //Where the Appium server is running. Localhost in this case.
    private final static String APP_BUNDLE_ID = "com.afollestad.materialdialogssample";
    //Browserstacks config
    private final static String BS_SERVER = "http://hub.browserstack.com/wd/hub";
    private final static String BS_USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    private final static String BS_PASSWORD = System.getenv("BROWSERSTACK_ACCESS_KEY");
    private final static String APP_ID_ON_BS = System.getenv("BROWSERSTACK_APP_ID");

    private AndroidDriver<AndroidElement> driver;

    @BeforeSuite
    public void setup() throws MalformedURLException {

        //Full list of capabilities can be found here:
        //https://github.com/appium/appium/blob/1.5/docs/en/writing-running-appium/caps.md#appium-server-capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Currently ignored for Android devices, more important for IOS.
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        //Automation engine to use. Appium is the default if this isn't specified.
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        //This will reset the app state after the session ends (so.. after this class finishes running? ¯\_(ツ)_/¯). Default = false.
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        //This one will do the same as above but go the extra mile and also uninstall the application. Default = false.
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "false");

        //Browserstack capabilities.
        capabilities.setCapability("browserstack.user", BS_USERNAME);
        capabilities.setCapability("browserstack.key", BS_PASSWORD);
        capabilities.setCapability(MobileCapabilityType.APP, APP_ID_ON_BS);
        //The driver will try to install this application when initiated. Can be a URL or relative/absolute path on the filesystem.
        capabilities.setCapability(MobileCapabilityType.APP, APP_ID_ON_BS);
        // Specify device and os_version for testing
        capabilities.setCapability("device", "Google Pixel 3");
        capabilities.setCapability("os_version", "9.0");
        //Misc BS configs
        capabilities.setCapability("project", "Test Project");
        capabilities.setCapability("build", "Java Android");
        capabilities.setCapability("name", CURRENT_DATE);

        driver = new AndroidDriver<AndroidElement>(new URL(BS_SERVER), capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void teardown() {
        driver.removeApp(APP_BUNDLE_ID);
    }

    @BeforeTest
    public void launch_app() {
        driver.launchApp();
    }

    @Test public void check_app_is_installed() {
        Assert.assertTrue(driver.isAppInstalled(APP_BUNDLE_ID));
    }

    @Test public void get_random_stuff_validate_not_empty(){
        Assert.assertTrue(driver.getAppStringMap().size() > 0);
    }
}