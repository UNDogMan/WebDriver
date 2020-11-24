import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeWebDriverTest extends ChromeWebDriver{

    @BeforeTest
    public void setUp(){
        initialization();
    }

    @Test
    public void verifyProductCount() {
        Assert.assertEquals(productCount.getText().trim(), "1");
    }

    @Test
    public void verifyProductSize() {
        Assert.assertEquals(productSize.getText().trim(), "S");
    }

    @Test
    public void verifyProductPrice() {
        Assert.assertEquals(productPrice.getText().trim(), "2 300 RUB");
    }


    @Test
    public void verifyRecommendedProductCount() {
        Assert.assertEquals(recommendedProductCount.getText().trim(), "1");
    }

    @Test
    public void verifyRecommendedProductPrice() {
        Assert.assertEquals(recommendedProductPrice.getText().trim(), "1 500 RUB");
    }

    @AfterTest
    private void tearDown(){
        driver.quit();
    }
}
