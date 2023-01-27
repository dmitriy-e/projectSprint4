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
    private final String browserName;
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String station;
    private final String comment;
    private final By clickOrderButton;

    public HomePageOrderTest(String browserName, String name, String surname, String address, String phoneNumber, String station, String comment, By clickOrderButton) {
        this.browserName = browserName;
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
                {"Chrome", "Иванов", "Иван", "119049, г Москва, ул Донская, д 18", "89001002030", "Сокол", "Хочу самокат", homePage.getOrderButtonHeader()},
                {"Chrome", "Петров", "Петр", "101000, г Москва, ул Пушкина, д 7", "+79991002039", "Лубянка", "Жду заказ как можно быстрее", homePage.getOrderButtonBottom()},
                {"Chrome", "Иванов", "Иван", "119049, г Москва, ул Донская, д 18", "89001002030", "Сокол", "Хочу самокат", homePage.getOrderButtonHeader()},
                {"Chrome", "Петров", "Петр", "101000, г Москва, ул Пушкина, д 7", "+79991002039", "Лубянка", "Жду заказ как можно быстрее", homePage.getOrderButtonBottom()},
        };
    }

    @Before
    public void setUp() {
        if (browserName.equals("Chrome")) {
            driver = new ChromeDriver();
            driver.get("https://qa-scooter.praktikum-services.ru/");
        } else if (browserName.equals("Firefox")) {
            driver = new FirefoxDriver();
            driver.get("https://qa-scooter.praktikum-services.ru/");
        }
    }

    @Test
    public void checkOrderProcess() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOrderButton(clickOrderButton);

        OrderPage orderPage = new OrderPage(driver);
        orderPage.waitForLoadOrderPage();
        orderPage.sendTheFirstForm(driver, name, surname, address, station, phoneNumber);
        orderPage.clickNextButton();

        orderPage.sendTheSecondForm(comment);

        orderPage.getOrderButton().click();
        orderPage.getConfirmButton().click();

        assertTrue("Сообщение об успешном заказе отсутствует", orderPage.isDisplayedSuccessText());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
