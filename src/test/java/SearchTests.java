import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void returnHomePage(){
        app.getHelperSearch().clickReturn();

    }

    @Test
    public void currentMonthSuccess(){
        app.getHelperSearch().searchCurrentMonth("Tashkent","10/25/2022","10/30/2022");
        app.getHelperSearch().submit();

        Assert.assertTrue(app.getHelperSearch().isListOfCarAppears());
        Assert.assertEquals(app.getHelperSearch().getTextPricePerDay(),"Price range ($/per day):");
    }

    @Test
    public void currentMonthSuccess2(){
        app.getHelperSearch().searchCurrentMonth2("Haifa","11/25/2022","11/30/2022");
        app.getHelperSearch().submit();

        Assert.assertTrue(app.getHelperSearch().isListOfCarAppears());
    }

    @Test
    public void NextMonthSuccess(){
        app.getHelperSearch().searchNextMonth("Moscow","12/25/2022","12/30/2022");
        app.getHelperSearch().submit();

        Assert.assertTrue(app.getHelperSearch().isListOfCarAppears());
    }

    @Test(groups = {"smoke"},priority = 1)
    public void searchAnyPeriod(){
        app.getHelperSearch().selectAnyPeriod("Toronto", "03/08/2023", "6/10/2023");
        app.getHelperSearch().submit();
        Assert.assertTrue(app.getHelperSearch().isListOfCarAppears());
        Assert.assertTrue(app.getHelperSearch().isDataCorrect("11/08/2022", "6/10/2023"));
    }

    @Test
    public void searchPast(){
        app.getHelperSearch().typePeriodPast("Rome", "03/08/2022", "6/10/2023");
        app.getHelperSearch().submitWithoutWait();

        Assert.assertFalse(app.getHelperSearch().isListOfCarAppears());
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed());
        logger.info("Assert passed");
    }

}
