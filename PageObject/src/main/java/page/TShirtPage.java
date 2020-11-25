package page;

import model.TShirtSize;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import properties.ConfigurationProperties;

public class TShirtPage extends Page {

    @FindBy(xpath = "//button[@class='product-information-add-to-cart']")
    private WebElement addToCart;

    @FindBy(xpath = "//div[div[normalize-space()='Термочашка']]")
    private WebElement recommendedProduct;

    @FindBy(css = "div.default-header-cart")
    private WebElement openCartButton;

    private String buttonSelectSizeLocator = "//button[normalize-space()='$']";

    public TShirtPage(WebDriver driver) {
        super(driver);
        driver.get(ConfigurationProperties.getProperty("tshirtpage"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 2), this);
    }

    public TShirtPage setSize(TShirtSize size) {
        WebElement setSizeButton = driver.findElement(By.xpath(buttonSelectSizeLocator.replace("$", size.getSize())));
        setSizeButton.click();
        return this;
    }

    public CartPage clickAddToCartButton() {
        addToCart.click();
        saveLastNotification();
        return new CartPage(driver, this);
    }

    public CartPage openCart() {
        openCartButton.click();
        return new CartPage(driver, this);
    }

    public RecommendedProductPage selectFirstRecommendedProduct() {
        recommendedProduct.click();
        return new RecommendedProductPage(driver, this);
    }
}
