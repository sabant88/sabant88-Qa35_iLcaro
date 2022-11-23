package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    WebDriver wd;

   HelperUser helperUser;
   HelperCar helperCar;

   HelperSearch helperSearch;

   String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }


    @BeforeTest
    public void init(){
        if(browser.equals(Browser.CHROME.browserName())){ // firefox!=chrome
            wd = new ChromeDriver();
            logger.info("All tests start in  ChromeDriver");
        }else if (browser.equals(Browser.FIREFOX.browserName())){
            //System.setProperty("webdriver.gecko.driver","/Users/tayahatum/Qa35/Qa35_IlCarro/geckodriver");//firefox =firefox
            wd= new FirefoxDriver();
            logger.info("All tests start in  Firefox");
        }else if (browser.equals(Browser.EDGE.browserName())){
            wd= new EdgeDriver();
            logger.info("All tests start in  EdgeDriver");
        }

        WebDriverListener listener = new MyListener();
        wd=new EventFiringDecorator<>(listener).decorate(wd);

        wd.navigate().to("https://ilcarro-1578153671498.web.app/search");
        wd.navigate().refresh();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        helperUser = new HelperUser(wd);
        helperCar = new HelperCar(wd);
        helperSearch = new HelperSearch(wd);

    }

    @AfterTest
    public void stop(){
        wd.quit();
    }

    public manager.HelperUser getHelperUser() {
        return helperUser;
    }

    public manager.HelperCar getHelperCar() {
        return helperCar;
    }

    public HelperSearch getHelperSearch() {
        return helperSearch;
    }
}
