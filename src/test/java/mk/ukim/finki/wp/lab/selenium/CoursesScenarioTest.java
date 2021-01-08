package mk.ukim.finki.wp.lab.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CoursesScenarioTest {

    private HtmlUnitDriver driver;

    @BeforeEach
    private void setUp(){
        this.driver = new HtmlUnitDriver(true);
    }

    @AfterEach
    private void destroy(){
        if(this.driver!=null){
            this.driver.close();
        }
    }

    @Test
    public void testScenario() throws Exception {
        CoursePage coursePage = LoginPage.login(this.driver, "user", "user");
        coursePage.assertElements(0, 0);
        CoursePage coursePage2 = LoginPage.login(this.driver, "admin", "admin");
        coursePage2.assertElements(5,1);
    }

}
