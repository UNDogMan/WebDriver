package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RecommendedProductPage extends Page {

    private final Page previousPage;

    @FindBy(css = "svg.default-modals-modal-close")
    private WebElement closeRecommendationProduct;

    @FindBy(css = "div.default-modals-recommendation-information button.product-information-add-to-cart")
    private WebElement addRecommendedToCart;


    public RecommendedProductPage(WebDriver driver, Page previousPage) {
        super(driver);
        this.previousPage = previousPage;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 2), this);
    }

    public Page continueShopping() {
        closeRecommendationProduct.click();
        return this.previousPage;
    }

    public CartPage addProductToCart() {
        addRecommendedToCart.click();
        return new CartPage(driver, this);
    }
}
