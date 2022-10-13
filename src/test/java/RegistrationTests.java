import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCond() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();

        }
    }

    @AfterMethod
    public void postCond() {
        app.getHelperUser().checkPolicyBoxByXY();
            app.getHelperUser().clickOkButton();

    }

    @Test
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
        app.getHelperUser().submit();

        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertFalse(app.getHelperUser().isYallaBtnNotActive());
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
        app.getHelperUser().submit();

        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertFalse(app.getHelperUser().isYallaBtnNotActive());
  }
}