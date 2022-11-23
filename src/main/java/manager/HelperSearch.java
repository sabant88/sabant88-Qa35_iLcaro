package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperSearch extends HelperBase{

    Logger logger = LoggerFactory.getLogger(HelperSearch.class);
    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public void searchCurrentMonth(String city, String fromDate, String toDate) {
        typeCity(city);
     //   clearTextBox();
        click(By.id("dates"));

        String[] from = fromDate.split("/");
        String locator = "//div[text()=' "+from[1]+" ']";
        click(By.xpath(locator));

        String [] to = toDate.split("/");
        String locator2 = "//div[text()=' "+to[1]+" ']";

        click(By.xpath(locator2));

    }

    private void typeCity(String city) {
        type(By.id("city"),city);
        pause(500);
        click(By.cssSelector("div.pac-item"));

    }

    public void searchCurrentMonth2(String city, String dataFrom, String dataTo) {

        typeCity(city);
        clearTextBox();
        click(By.id("dates"));

        String[] from =dataFrom.split("/");


        //String locator = "//div[text()=' "+from[1]+" ']";
        String locator = String.format("//div[text()=' %s ']",from[1]) ;
        click(By.xpath(locator));


        String [] to = dataTo.split("/");

        String locator2 =  String.format("//div[text()=' %s ']",to[1]);

        click(By.xpath(locator2));

    }

    public void searchNextMonth(String city, String fromDate, String toDate) {
        typeCity(city);
        clearTextBox();
        click(By.id("dates"));
        click(By.cssSelector("button[aria-label$='Next month']"));

        String[] from = fromDate.split("/");
        String locator = "//div[text()=' " +from[1] + " ']";
        click(By.xpath(locator));

        String [] to = toDate.split("/");
        String locator1 = String.format("//div[text()=' %s ']",to[1]);
        click(By.xpath(locator1));


    }

    public boolean isListOfCarAppears(){

   //  return   wd.findElements(By.xpath("//div[@class='car-img-container ng-star-inserted']")).size()>0;
        return isElementPresent(By.xpath("//div[@class='car-img-container ng-star-inserted']"));
    }

    public String getTextPricePerDay() {

      return   wd.findElement(By.xpath("//label[.='Price range ($/per day):']")).getText();
    }

    public void selectAnyPeriod(String city, String fromDate, String toDate) {
        typeCity(city);
        clearTextBox();
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        LocalDate from=LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(toDate,DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffYear;
        int diffMonth;
        diffYear = from.getYear()-now.getYear();
        if(diffYear==0){
            diffMonth= from.getMonthValue()-now.getMonthValue(); //11-10 = 1
        }else {
            diffMonth=12-now.getMonthValue()+from.getMonthValue();// 12-10+3 = 5
        }

        clickNextMonth(diffMonth);
        String locator = String.format("//div[text()=' %s ']",from.getDayOfMonth());
        click(By.xpath(locator));

        ///****************
        diffYear = to.getYear()-from.getYear();
        if (diffYear==0){
            diffMonth = to.getMonthValue()-from.getMonthValue();
        }else {
            diffMonth = 12-from.getMonthValue()+ to.getMonthValue();
        }
        clickNextMonth(diffMonth);
        locator = String.format("//div[text()=' %s ']",to.getDayOfMonth());
        click(By.xpath(locator));

//        String[] from = fromDate.split("/");
//        int diffYear = Integer.parseInt(from[2]) - now.getYear();

    }

    private void clickNextMonth(int count) {
        for (int i=0;i<count;i++){
            click(By.cssSelector("button[aria-label$='Next month']"));
        }
    }

    public boolean isDataCorrect(String from, String to) {
        WebElement el = wd.findElement(By.cssSelector("input[aria-haspopup='dialog']"));
        return true;
    }

    public void typePeriodPast(String city, String from, String to) {
        typeCity(city);
        clearTextBox();
        typePeriod(from,to);
    }

    private void typePeriod(String from, String to) {
        type(By.id("dates"),from + " - " + to);
        click(By.cssSelector(".cdk-overlay-container"));
      //  click(By.cssSelector("div[class$='cdk-overlay-backdrop mat-overlay-transparent-backdrop cdk-overlay-backdrop-showing']"));
    }

    public void clickReturn() {
        click(By.cssSelector(".header a.logo"));
    }

    public void clearTextBox(){
        WebElement  el = wd.findElement(By.id("dates"));
        String osName = System.getProperty("os.name");
        System.out.println(osName);   /// Mac OS X
        if(osName.startsWith("Mac")){
            logger.info("OS is --->" +osName);
            // Command +a
            el.sendKeys(Keys.COMMAND,"a");
        }else {
            logger.info("OS is --->" +osName);
            el.sendKeys(Keys.CONTROL,"a");
            //  Cntr +a
        }
        el.sendKeys(Keys.DELETE);
    }
}
