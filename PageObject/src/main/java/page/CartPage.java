package page;

import model.ProductInfo;
import org.checkerframework.checker.units.qual.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class CartPage extends Page {

    private final Page previousPage;

    @FindBy(xpath = "//button[@class='default-modals-cart-continue-shopping']")
    private WebElement closeCartButton;

    @FindBy(css = "input.default-modals-cart-promo-code-input")
    private WebElement promoCodeInput;

    @FindBy(css = "button.default-modals-cart-promo-code-apply")
    private WebElement promoCodeApplyButton;

    public CartPage(WebDriver driver, Page previousPage) {
        super(driver);
        this.previousPage = previousPage;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public Page continueShopping() {
        closeCartButton.click();
        return this.previousPage;
    }

    public CartPage interPromoCode(String promo) {
        promoCodeInput.sendKeys(promo);
        return this;
    }

    public CartPage activatePromoCode() {
        promoCodeApplyButton.click();
        saveLastNotification();
        return this;
    }

    public List<ProductInfo> getInCartProductInfo(){
        By productsInfoLocator = By.cssSelector("div.default-modals-cart-product-information");
        Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        List<WebElement> productsInfo = (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productsInfoLocator));

        List<ProductInfo> productInfoList = new ArrayList<ProductInfo>();
        for (WebElement productInfo : productsInfo) {
            String name = productInfo.findElement(By.cssSelector("a.default-modals-cart-product-name")).getText().trim();
            String count = productInfo.findElement(By.cssSelector("div.default-modals-cart-product-count-value")).getText().trim();
            String price = productInfo.findElement(By.cssSelector("div.default-modals-cart-product-price")).getText().trim();
            String size = null;
            if (productInfo.findElements(By.cssSelector("div.default-modals-cart-product-sizes")).size() > 0) {
                size = productInfo.findElement(By.xpath("//button[@active='true' and contains(@class, 'default-modals-cart-product-sizes-size')]")).getText().trim();
            }
            productInfoList.add(new ProductInfo(name, count, price, size));
        }
        return productInfoList;
    }
}
