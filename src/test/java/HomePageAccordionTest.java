import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.HomePage;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class HomePageAccordionTest {

    private WebDriver driver;
    private final String browserName;

    public HomePageAccordionTest(String browserName) {
        this.browserName = browserName;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Chrome"},
                {"Firefox"},
        };
    }

    @Before
    public void setUp() {
        switch (browserName) {
            case "Chrome":
                driver = new ChromeDriver();
                driver.get("https://qa-scooter.praktikum-services.ru/");
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                driver.get("https://qa-scooter.praktikum-services.ru/");
                break;
        }
    }

    @Test
    public void checkAccordionTextAfterClickExists() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoadAccordion();
        homePage.clickAccordionElements();

        assertThat("Элемент выпадающего списка пустой", homePage.getTextsFromAccordion(), everyItem(not(containsString(""))));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
