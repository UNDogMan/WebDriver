package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 2), this);
    }

    public Page continueShopping() {
        closeCartButton.click();
        return this.previousPage;
    }


}
