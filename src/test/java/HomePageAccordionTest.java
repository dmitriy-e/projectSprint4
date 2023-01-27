import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.HomePage;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class HomePageAccordionTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void checkAccordionTextAfterClickExists() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoadAccordion();
        homePage.clickAccordionElements();

        assertThat(homePage.getTextsFromAccordion(), everyItem(not(containsString(""))));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
