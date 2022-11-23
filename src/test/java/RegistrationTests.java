import manager.DataProviderUser;
import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCond() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();

        }
    }

    @AfterMethod(alwaysRun = true)
    public void postCond() {
        app.getHelperUser().checkPolicyBoxByXY();
            app.getHelperUser().clickOkButton();

    }

    @Test(dataProvider = "dataReg",dataProviderClass = DataProviderUser.class,enabled = false)
    public void RegSuccessDataProvider(User user) {


        logger.info("Registration success: " + user.toString());

        app.getHelperUser().openRegForm();
        app.getHelperUser().fillRegForm(user);
        app.getHelperUser().checkPolicyBoxByXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Registered");
        logger.info("Assert passed 'Get Title message' and 'Is logged'");


    }

    @Test(groups = {"smoke"})
    public void RegSuccess() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withEmail("totti" + i + "@mail.uz")
                .withPassword("Ttotti10new$")
                .withName("fran")
                .withLastName("totti");

        app.getHelperUser().openRegForm();
        app.getHelperUser().fillRegForm(user);
        app.getHelperUser().checkPolicyBoxByXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Registered");


    }

    @Test
    public void RegNegativeWrongPassword() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withEmail("totti" + i + "@mail.uz")
                .withPassword("totti10new")
                .withName("fran")
                .withLastName("totti");

        app.getHelperUser().openRegForm();
        app.getHelperUser().fillRegForm(user);
        app.getHelperUser().checkPolicyBoxByXY();
        app.getHelperUser().submitWithoutWait();

        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
 }

    @Test
    public void RegNegativeWrongEmail() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withEmail("totti" + i + "mail.uz")
                .withPassword("Ttotti10new$")
                .withName("fran")
                .withLastName("totti");

        app.getHelperUser().openRegForm();
        app.getHelperUser().fillRegForm(user);
        app.getHelperUser().checkPolicyBoxByXY();
        app.getHelperUser().submitWithoutWait();

        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
  }
}