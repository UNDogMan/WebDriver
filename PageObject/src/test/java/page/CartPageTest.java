package page;

import model.ProductInfo;
import model.TShirtSize;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import properties.ConfigurationProperties;

import java.util.List;

public class CartPageTest {
    public static WebDriver driver;
    public static CartPage cartPage;
    public static TShirtPage tshirtPage;

    @BeforeTest
    public void browserSetup() {
        System.setProperty("webdriver.chrome.driver", ConfigurationProperties.getProperty("chromedriver"));

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void buyTShirtWithRecommendedProduct() {
        ProductInfo tshirt = new ProductInfo("B.O.M.J Black", "1", "2 300 RUB" , "S" );
        ProductInfo cup = new ProductInfo("Термочашка", "1", "1 500 RUB" , null);
        tshirtPage = new TShirtPage(driver);
        tshirtPage =  (TShirtPage)tshirtPage.setSize(TShirtSize.S)
                .clickAddToCartButton()
                .continueShopping();
        cartPage = tshirtPage.selectFirstRecommendedProduct()
                .addProductToCart();
        List<ProductInfo> productInfoList = cartPage.getInCartProductInfo();
        Assert.assertEquals(productInfoList.get(0).name, "B.O.M.J Black");
        Assert.assertEquals(productInfoList.get(1).name, "Термочашка");
    }

    @Test
    public void noValidPromoCodeMessage() {
        cartPage = new TShirtPage(driver)
                .openCart()
                .interPromoCode("Gold")
                .activatePromoCode();
        Assert.assertEquals(cartPage.lastNotification, "Запрашиваемого промокода не существует или он закончился.");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
