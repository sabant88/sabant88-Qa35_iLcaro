package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class HelperUser extends HelperBase{


    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void logout() {
        wd.findElement(By.xpath("//a[.=' Logout ']")).click();
    }

    public boolean isLogged() {
//               List<WebElement> list = wd.findElements(By.xpath("//a[.=' Logout ']"));
//        return list.size()>0;
        return isElementPresent(By.xpath("//a[.=' Logout ']"));
    }

    public void openLoginForm() {
        wd.findElement(By.xpath("//*[.=' Log in ']")).click();
    }

    public void fillLoginForm(String email,String password) {
        type(By.cssSelector("#email"),email);
        type(By.cssSelector("#password"),password);
    }

    public void fillLoginForm(User user) {
        type(By.cssSelector("#email"),user.getEmail());
        type(By.cssSelector("#password"), user.getPassword());
    }

    public void submit() {
        wd.findElement(By.xpath("//*[@type='submit']")).click();
    }

    public void clickOkButton() {
        pause(2000);
       // wd.findElement(By.xpath("//*[@class='positive-button ng-star-inserted']")).click();
        if(isElementPresent(By.xpath("//*[@class='positive-button ng-star-inserted']")))
        click(By.xpath("//*[@class='positive-button ng-star-inserted']"));
    }

    public String getMessage(){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("div.dialog-container"))));

        return  wd.findElement(By.cssSelector("h2.message")).getText();

    }

    public void getErrorText(){
        wd.findElement(By.xpath("//div[@class='ng-star-inserted']")).getText();
    }

    public boolean isYallaBtnNotActive(){

        boolean res =isElementPresent(By.cssSelector("button[disabled]"));

        return res &&!wd.findElement(By.cssSelector("[type='submit']")).isEnabled();
    }

    public String getTitleMessage() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("div.dialog-container"))));

        return  wd.findElement(By.cssSelector("div.dialog-container h1")).getText();
    }

    public void openRegForm() {
        click(By.xpath("//a[.=' Sign up ']"));
    }

    public void checkPolicyBoxByXY() {
    //    click(By.cssSelector("label[for='terms-of-use']")); easy way ---- not work with my comp screen

        WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));
        Rectangle rect = label.getRect();
        int wight = rect.getWidth();
        int height = rect.getHeight();
        int x = rect.getX();
        int y = rect.getY();
        int xOffSet = wight/2;
        int yOffSet = height/2;

        Actions actions = new Actions(wd);
        actions.moveToElement(label,-xOffSet,-yOffSet).click().release().perform();
    }

    public void fillRegForm(User user) {
        type(By.cssSelector("#email"),user.getEmail());
        type(By.cssSelector("#password"), user.getPassword());
        type(By.cssSelector("#name"),user.getName());
        type(By.cssSelector("#lastName"), user.getLastName());
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submit();
        clickOkButton();
    }

    public boolean isErrorMessageDisplayed() {
      String text =   wd.findElement(By.cssSelector("div.ng-star-inserted")).getText();
       return text.equals("You can't pick date before today");
    }
}
