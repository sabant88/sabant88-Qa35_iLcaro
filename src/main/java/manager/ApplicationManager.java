package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class ApplicationManager {

    WebDriver wd;

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

   HelperUser HelperUser;
   HelperCar HelperCar;

    @BeforeTest
    public void init(){

        wd = new ChromeDriver();
        logger.info("All tests start in Chrome Browser");
        wd.navigate().to("https://ilcarro-1578153671498.web.app/search");
        wd.navigate().refresh();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        HelperUser = new HelperUser(wd);
        HelperCar = new HelperCar(wd);

    }

    @AfterTest
    public void stop(){
        wd.quit();
    }

    public manager.HelperUser getHelperUser() {
        return HelperUser;
    }

    public manager.HelperCar getHelperCar() {
        return HelperCar;
    }
}
