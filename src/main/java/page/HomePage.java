package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    public HomePage() {
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By accordion = By.className("accordion");
    private final By accordionButton = By.className("accordion__button");
    private final By accordionText = By.cssSelector(".accordion__panel p");
    private final By orderButtonHeader = By.xpath(".//div[contains(@class, 'Header_Nav')]/button[contains(@class, 'Button_Button')]");
    private final By orderButtonBottom = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button[contains(@class, 'Button_Button')]");

    public void waitForLoadAccordion() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(accordion));
    }

    public void clickAccordionElement(int index) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElements(accordionButton).get(index));
        driver.findElements(accordionButton).get(index).click();
    }

    public String getTextFromAccordion(int index) {
        return driver.findElements(accordionText).get(index).getText();
    }

    public By getOrderButtonHeader() {
        return orderButtonHeader;
    }

    public By getOrderButtonBottom() {
        return orderButtonBottom;
    }

    public void clickOrderButton(By locator) {
        WebElement orderButtonElement = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", orderButtonElement);
        orderButtonElement.click();
    }
}
