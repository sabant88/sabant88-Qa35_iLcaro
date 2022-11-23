import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteCarTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCond(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("noa@gmail.com").withPassword("Nnoa12345$"));
        }
    }

    @Test
    public void deleteOneCarSuccess(){

    }
}
