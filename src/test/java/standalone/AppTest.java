package standalone;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppTest {

    //Where the Appium server is running. Localhost in this case.
    private final static String SERVER_URL = "http://127.0.0.1:4723/wd/hub";
    private final static String APP_BUNDLE_ID = "com.afollestad.materialdialogssample";
    private final static String APPT_TO_DEPLOY = "https://github.com/afollestad/material-dialogs/raw/main/sample/sample.apk";

    private AndroidDriver<MobileElement> driver;

    @BeforeSuite
    public void setup() throws MalformedURLException {

        //Full list of capabilities can be found here:
        //https://github.com/appium/appium/blob/1.5/docs/en/writing-running-appium/caps.md#appium-server-capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //Currently ignored for Android devices, more important for IOS.
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        //Automation engine to use. Appium is the default if this isn't specified.
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        //The driver will try to install this application when initiated. Can be a URL or relative/absolute path on the filesystem.
        capabilities.setCapability(MobileCapabilityType.APP, APPT_TO_DEPLOY);
        //This will reset the app state after the session ends (so.. after this class finishes running? ¯\_(ツ)_/¯). Default = false.
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
        //This one will do the same as above but go the extra mile and also uninstall the application. Default = false.
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "false");


        driver = new AndroidDriver<MobileElement>(new URL(SERVER_URL), capabilities);
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