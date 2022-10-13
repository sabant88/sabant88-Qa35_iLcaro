import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase{

    @BeforeMethod
    public void preCond(){
        if(!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().withEmail("noa@gmail.com").withPassword("Nnoa12345$"));
   }

   @AfterMethod
   public void post(){
        app.getHelperCar().returnHomePage();

   }

   @Test
    public void addCarSuccess(){
     //  int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
       Random random = new Random();
      int i = random.nextInt(1000)+100;

       Car car = Car.builder()
                .location("Haifa, Israel")
                .make("Bmw")
                .model("5")
                .year("2001")
                .gear("MT")
                .fuel("Petrol")
                .engine("2.0")
                .wD("RWD")
                .doors("4")
               .seats("5")
                .classCar("M")
                .fuelConsumption("5")
                .featureS("plus")
                .about("strong car")
                .price("99.9")
                .distance("5000")
                .carRegistrationNumber("10"+i)
                .build();
       logger.info("Test starts with data--->"+car.toString());

       app.getHelperCar().openCarForm();
       app.getHelperCar().fillCarForm(car);
       app.getHelperCar().atachedPhoto("C:\\Users\\saban\\Desktop\\QA35\\sabant88-Qa35_iLcaro\\src\\test\\resources\\muscle_car_207555.jpg");
       app.getHelperCar().submit();

       Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Car added");

   }
}
