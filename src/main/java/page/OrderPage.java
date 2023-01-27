package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    private final By ORDER_PAGE_HEADER = By.className("Order_Header__BZXOb");
    private final By INPUT_NAME = By.xpath("//input[@placeholder ='* Имя']");
    private final By INPUT_SURNAME = By.xpath("//input[@placeholder ='* Фамилия']");
    private final By INPUT_ADDRESS = By.xpath("//input[@placeholder ='* Адрес: куда привезти заказ']");
    private final By INPUT_METRO_STATION = By.xpath("//input[@placeholder ='* Станция метро']");
    private final By INPUT_PHONE_NUMBER = By.xpath("//input[@placeholder ='* Телефон: на него позвонит курьер']");
    private final By NEXT_BUTTON = By.cssSelector("#root > div > div.Order_Content__bmtHS > div.Order_NextButton__1_rCA > button");
    private final By INPUT_DATE = By.xpath("//input[@placeholder ='* Когда привезти самокат']");
    private final By INPUT_DATE_PICKER = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/div[2]/div[5]/div[5]");
    private final By INPUT_RENTAL_PERIOD = By.className("Dropdown-placeholder");
    private final By INPUT_RENTAL_DAY = By.xpath(".//div[@class='Dropdown-menu']/div[text()='сутки']");
    private final By COMMENT = By.cssSelector("#root > div > div.Order_Content__bmtHS > div.Order_Form__17u6u > div.Input_InputContainer__3NykH > input");
    private final By ORDER_BUTTON = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[contains(text(), 'Заказать')]");
    private final By CONFIRM_BUTTON = By.xpath("//button[contains(text(), 'Да')]");
    public final By SUCCESS_TEXT = By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[1]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadOrderPage(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_PAGE_HEADER));
    }

    public WebElement getName() {
        return driver.findElement(INPUT_NAME);
    }

    public WebElement getSurname() {
        return driver.findElement(INPUT_SURNAME);
    }

    public WebElement getAddress() {
        return driver.findElement(INPUT_ADDRESS);
    }

    public WebElement getPhoneNumber() {
        return driver.findElement(INPUT_PHONE_NUMBER);
    }

    public WebElement getStation() {
        return driver.findElement(INPUT_METRO_STATION);
    }

    public WebElement getTitleOrder() {
        return driver.findElement(ORDER_PAGE_HEADER);
    }

    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
    }

    public WebElement getInputDate() {
        return driver.findElement(INPUT_DATE);
    }

    public WebElement getInputDatePicker() {
        return driver.findElement(INPUT_DATE_PICKER);
    }

    public WebElement getRentalPeriod() {
        return driver.findElement(INPUT_RENTAL_PERIOD);
    }

    public WebElement getRentalDay() {
        return driver.findElement(INPUT_RENTAL_DAY);
    }

    public WebElement getComment() {
        return driver.findElement(COMMENT);
    }

    public WebElement getOrderButton() {
        return driver.findElement(ORDER_BUTTON);
    }

    public WebElement getConfirmButton() {
        return driver.findElement(CONFIRM_BUTTON);
    }

    public boolean isDisplayedSuccessText() {
        return driver.findElement(SUCCESS_TEXT).isDisplayed();
    }

    public void sendTheFirstForm(WebDriver driver, String name, String surname, String address, String station, String phoneNumber) {
        getName().sendKeys(name);
        getSurname().sendKeys(surname);
        getAddress().sendKeys(address);
        new Actions(driver).moveToElement(getStation()).click().sendKeys(station)
                .sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        getPhoneNumber().sendKeys(phoneNumber);
    }

    public void sendTheSecondForm(String comment) {
        getInputDate().click();
        getInputDatePicker().click();
        getRentalPeriod().click();
        getRentalDay().click();
        getComment().sendKeys(comment);
    }
}
