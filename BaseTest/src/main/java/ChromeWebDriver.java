import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeWebDriver {
    public static WebDriver driver;
    public static WebElement productCount;
    public static WebElement productPrice;
    public static WebElement productSize;

    public static WebElement recommendedProductCount;
    public static WebElement recommendedProductPrice;
    public static WebElement recommendedProductSize;

    public static void initialization(){
        System.setProperty("webdriver.chrome.driver", "D:\\Main\\Labs\\3\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://jolybell.com/product/1");
        driver.manage().window().maximize();

        WebElement sizeButton = driver.findElement(By.xpath("//button[normalize-space()='S']"));
        sizeButton.click();

        WebElement addToCartButton = driver.findElement(By.xpath("//button[@class='product-information-add-to-cart']"));
        addToCartButton.click();

        WebElement continueShoppingButton = waitForElementLocatedBy(driver, By.xpath("//button[@class='default-modals-cart-continue-shopping']"));
        continueShoppingButton.click();

        WebElement recommendedProduct = waitForElementLocatedBy(driver, By.xpath("//div[div[normalize-space()='Термочашка']]"));
        recommendedProduct.click();
        addToCartButton = waitForElementLocatedBy(driver, By.cssSelector("div.default-modals-recommendation-information button.product-information-add-to-cart"));
        addToCartButton.click();

        productCount = waitForElementLocatedBy(driver, By.xpath("//div[@class='default-modals-cart-product-information' and a[@href='/product/1']]//div[@class='default-modals-cart-product-count-value']"));
        productPrice = waitForElementLocatedBy(driver, By.xpath("//div[@class='default-modals-cart-product-information' and a[@href='/product/1']]//div[@class='default-modals-cart-product-price']"));
        productSize = waitForElementLocatedBy(driver, By.xpath("//div[@class='default-modals-cart-product-information' and a[@href='/product/1']]//button[@active='true' and @class='default-modals-cart-product-sizes-size']"));

        recommendedProductCount = waitForElementLocatedBy(driver, By.xpath("//div[@class='default-modals-cart-product-information' and a[@href='/product/52']]//div[@class='default-modals-cart-product-count-value']"));;
        recommendedProductPrice = waitForElementLocatedBy(driver, By.xpath("//div[@class='default-modals-cart-product-information' and a[@href='/product/52']]//div[@class='default-modals-cart-product-price']"));
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
