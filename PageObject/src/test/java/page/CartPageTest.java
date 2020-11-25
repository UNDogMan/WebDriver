package page;

import model.TShirtSize;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import properties.ConfigurationProperties;

public class CartPageTest {
    public static WebDriver driver;
    public static CartPage cartPage;
    public static TShirtPage tshirtPage;

    @BeforeTest
    public void browserSetup() {
        System.setProperty("webdriver.chrome.driver", ConfigurationProperties.getProperty("chromedriver"));

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void buyTShirtWithRecommendedProduct() {
        tshirtPage = new TShirtPage(driver);
        tshirtPage =  (TShirtPage)tshirtPage.setSize(TShirtSize.S)
                .clickAddToCartButton()
                .continueShopping();
        cartPage = tshirtPage.selectFirstRecommendedProduct()
                .addProductToCart();

    }

    @Test
    public void noValidPromoCodeMessage() {

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
