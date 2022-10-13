import manager.DataProviderUser;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCond(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @AfterMethod
    public void postCond(){
      //  if(app.getHelperUser().isElementPresent(By.xpath("//*[@class='positive-button ng-star-inserted']")))
        app.getHelperUser().clickOkButton();
    }



    @Test
    public void loginSuccess(){

        logger.info("Test loginSuccess");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("noa@gmail.com", "Nnoa12345$");
        logger.info("user data with mail--noa@gmail.com and password--Nnoa12345$");
        app.getHelperUser().submit();

        Assert.assertTrue(true,"Logged in success");
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("asserts passed");

    }

    @Test
    public void loginSuccessWithModel(){

        User user = new User().withEmail("noa@gmail.com").withPassword("Nnoa12345$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertTrue(true,"Logged in success");
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }

    @Test
    public void loginNegativeWrongEmail(){

        User user = new User().withEmail("noagmail.com").withPassword("Nnoa12345$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

    Assert.assertFalse(false,"Logged in success");
    Assert.assertFalse(app.getHelperUser().isLogged());
    //Assert.assertFalse(app.getHelperUser().isElementPresent(By.xpath("//button[@type='submit']")));
   // Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
    Assert.assertFalse(app.getHelperUser().isYallaBtnNotActive());

    }

    @Test
    public void loginNegativeWrongPassword(){

        User user = new User().withEmail("noa@gmail.com").withPassword("Qwoa12345$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertFalse(false,"Logged in success");
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(),"Wrong email or password");

        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Authorization error");

    }

    @Test(dataProvider = "dataLogin",dataProviderClass = DataProviderUser.class)
    public void loginSuccessDataProvider(String email, String password) {


        logger.info("Login success with data email "+email+" and data password "+password);

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email,password);
        app.getHelperUser().submit();

        Assert.assertTrue(true,"Logged in success");
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }

    @Test(dataProvider = "dataModel",dataProviderClass = DataProviderUser.class)
    public void loginSuccessWithModelDataProvider(User user){

        logger.info("Login success with model" + user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertTrue(true,"Logged in success");
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }


}
