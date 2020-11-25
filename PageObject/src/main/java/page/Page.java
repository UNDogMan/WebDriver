package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class Page {

    protected final WebDriver driver;
    protected String lastNotification;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.lastNotification = "";
    }

    protected void saveLastNotification() {
        By notificationLocator = By.cssSelector("span.default-notifications div.default-notifications-notification-inside div");
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(1))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        WebElement notification = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(notificationLocator));
        lastNotification = notification.getText().trim();
    }

    public String getLastNotification() {
        return lastNotification;
    }
}
