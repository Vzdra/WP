package mk.ukim.finki.wp.lab.selenium;

import lombok.Data;
import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class CoursePage extends AbstractPage{

    @FindBy(className = "editdelete")
    private List<WebElement> editDeleteButtons;

    @FindBy(className = "addcourse")
    private List<WebElement> addbutton;

    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public static CoursePage to(WebDriver driver){
        get(driver, "/courses");
        return PageFactory.initElements(driver, CoursePage.class);
    }

    public void assertElements(int editDelBtns, int addbtn){
        Assert.assertEquals("Add/Delete buttons do not match.", editDelBtns, this.getEditDeleteButtons().size());
        Assert.assertEquals("Add button does not match.", addbtn, this.getAddbutton().size());
    }
}
