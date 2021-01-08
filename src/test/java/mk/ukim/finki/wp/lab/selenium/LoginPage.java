package mk.ukim.finki.wp.lab.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage{

    private WebElement username;

    private WebElement password;

    @FindBy(css = ".btn")
    private WebElement submit;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static CoursePage login(WebDriver driver, String username, String password){
        get(driver, "/login?logout");
        System.out.println(driver.getCurrentUrl());
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.submit.click();
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, CoursePage.class);
    }
}
