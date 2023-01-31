import constant.Env;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.HomePage;
import page.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class HomePageOrderTest {

    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String station;
    private final String comment;
    private final By clickOrderButton;

    public HomePageOrderTest(String name, String surname, String address, String phoneNumber, String station, String comment, By clickOrderButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.station = station;
        this.comment = comment;
        this.clickOrderButton = clickOrderButton;
    }


    @Parameterized.Parameters
    public static Object[][] getData() {
        HomePage homePage = new HomePage();
        return new Object[][]{
                {"Иван", "Иванов", "г. Москва, ул. Тверская, д. 24", "89001002030", "Сокол", "Хочу самокат", homePage.getOrderButtonHeader()},
                {"Петр", "Петров", "г. Москва, ул. Долгоруковская, д. 30", "+79991002039", "Лубянка", "Жду заказ как можно быстрее", homePage.getOrderButtonBottom()},
        };
    }

    @Before
    public void setUp() {
        switch (Env.BROWSER_NAME) {
            case "Firefox":
                driver = new FirefoxDriver();
                driver.get(Env.URL);
                break;
            default:
                driver = new ChromeDriver();
                driver.get(Env.URL);
                break;
        }
    }

    @Test
    public void checkOrderProcess() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOrderButton(clickOrderButton);

        OrderPage orderPage = new OrderPage(driver);
        orderPage.waitForLoadOrderPage();
        orderPage.sendTheFirstForm(name, surname, address, station, phoneNumber);
        orderPage.clickNextButton();

        orderPage.sendTheSecondForm(comment);

        orderPage.getOrderButton().click();
        orderPage.getConfirmButton().click();
        orderPage.waitForLoadSuccessText();

        assertTrue("Сообщение об успешном заказе отсутствует", orderPage.isDisplayedSuccessText());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}