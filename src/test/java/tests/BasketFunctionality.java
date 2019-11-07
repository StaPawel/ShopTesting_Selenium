package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BasketFunctionalityPage;
import setup.Setup;
import java.util.concurrent.TimeUnit;

public class BasketFunctionality {

    WebDriver driver;
    //Wait wait;
    BasketFunctionalityPage basketFunctionalityPage;

    @BeforeTest
    void setup() {
        driver = Setup.driver;
        //wait = Setup.wait;
        basketFunctionalityPage = new BasketFunctionalityPage(driver);
    }

    @AfterMethod ()
    void goToHomePage(){

        basketFunctionalityPage.goToHomePage();
    }
    @Test (priority = 1)
    void addOneProductToBasket(){

        double summaryCost;
        double totalCost;

        basketFunctionalityPage.clickToBlouseProduct();
        basketFunctionalityPage.clickToAddToCartButton();

        summaryCost = basketFunctionalityPage.getSumProductAndShippingCosts();
        totalCost = basketFunctionalityPage.getTotalCost();

        Assert.assertEquals(summaryCost, totalCost);

    }

    @Test (priority = 2)
    void increaseQuantityInProductCart(){

        String numberOfProducts = "24";
        basketFunctionalityPage.clickToCart();
        basketFunctionalityPage.changeNumberOfProductInQuantityField(numberOfProducts);
        String expectedQuantity = basketFunctionalityPage.expectedSummaryOfQuantity(numberOfProducts);

        double multiplyUnitAndQuantity = basketFunctionalityPage.multiplyProductPriceAndQuantity();
        double totalValue = basketFunctionalityPage.getTotalValueFromProduct();
        double totalProductsValue = basketFunctionalityPage.getValueFromTotalProductsField();
        double shippingCost = basketFunctionalityPage.getShippingCost();
        double fullTotalValue = basketFunctionalityPage.getFullTotalValue();
        String summaryOfQuantity = basketFunctionalityPage.getSummaryOfQuantity();

        double fullTotalValueSum = multiplyUnitAndQuantity + shippingCost;

        Assert.assertEquals(multiplyUnitAndQuantity, totalValue);
        Assert.assertEquals(multiplyUnitAndQuantity, totalProductsValue);
        Assert.assertEquals(fullTotalValue, fullTotalValueSum);
        Assert.assertEquals(summaryOfQuantity, expectedQuantity);
    }

    @Test (priority = 3)
    void increaseQuantityInProductCartClickingPlusIcon(){

        int numberOfClick = 9;

        basketFunctionalityPage.clickToCart();
        basketFunctionalityPage.clickToIcon(numberOfClick, "plus"); //n = how many click to plus icon

        driver.navigate().refresh();

        double multiplyUnitAndQuantity = basketFunctionalityPage.multiplyProductPriceAndQuantity();
        double totalValue = basketFunctionalityPage.getTotalValueFromProduct();
        double totalProductsValue = basketFunctionalityPage.getValueFromTotalProductsField();
        double shippingCost = basketFunctionalityPage.getShippingCost();
        double fullTotalValue = basketFunctionalityPage.getFullTotalValue();

        double fullTotalValueSum = multiplyUnitAndQuantity + shippingCost;

        Assert.assertEquals(multiplyUnitAndQuantity, totalValue);
        Assert.assertEquals(multiplyUnitAndQuantity, totalProductsValue);
        Assert.assertEquals(fullTotalValue, fullTotalValueSum);
    }
    @Test (priority = 4)
    void reduceNumberOfProductByFillingTextField(){

        String numberOfProducts = "19";

        basketFunctionalityPage.clickToCart();
        basketFunctionalityPage.changeNumberOfProductInQuantityField(numberOfProducts);
        String expectedQuantity = basketFunctionalityPage.expectedSummaryOfQuantity(numberOfProducts);

        double multiplyUnitAndQuantity = basketFunctionalityPage.multiplyProductPriceAndQuantity();
        double totalValue = basketFunctionalityPage.getTotalValueFromProduct();
        double totalProductsValue = basketFunctionalityPage.getValueFromTotalProductsField();
        double shippingCost = basketFunctionalityPage.getShippingCost();
        double fullTotalValue = basketFunctionalityPage.getFullTotalValue();
        String summaryOfQuantity = basketFunctionalityPage.getSummaryOfQuantity();

        double fullTotalValueSum = multiplyUnitAndQuantity + shippingCost;

        Assert.assertEquals(multiplyUnitAndQuantity, totalValue);
        Assert.assertEquals(multiplyUnitAndQuantity, totalProductsValue);
        Assert.assertEquals(fullTotalValue, fullTotalValueSum);
        Assert.assertEquals(summaryOfQuantity, expectedQuantity);
    }

    @Test (priority = 5)
    void reduceNumberOfProductByClickingMinusIcon(){

        int numberOfClick = 9;

        basketFunctionalityPage.clickToCart();
        basketFunctionalityPage.clickToIcon(numberOfClick, "minus");

        driver.navigate().refresh();

        double multiplyUnitAndQuantity = basketFunctionalityPage.multiplyProductPriceAndQuantity();
        double totalValue = basketFunctionalityPage.getTotalValueFromProduct();
        double totalProductsValue = basketFunctionalityPage.getValueFromTotalProductsField();
        double shippingCost = basketFunctionalityPage.getShippingCost();
        double fullTotalValue = basketFunctionalityPage.getFullTotalValue();

        double fullTotalValueSum = multiplyUnitAndQuantity + shippingCost;

        System.out.println(multiplyUnitAndQuantity);
        System.out.println(fullTotalValueSum);

        Assert.assertEquals(multiplyUnitAndQuantity, totalValue);
        Assert.assertEquals(multiplyUnitAndQuantity, totalProductsValue);
        Assert.assertEquals(fullTotalValue, fullTotalValueSum);

    }

    @Test (priority = 6)
    void changeProductQuantityToZero(){

        String numberOfProducts = "0";

        basketFunctionalityPage.clickToCart();
        basketFunctionalityPage.changeNumberOfProductInQuantityField(numberOfProducts);

        String actualMessage = basketFunctionalityPage.getActualEmptyCartMessage();
        String expectedMessage = basketFunctionalityPage.getExpectedEmptyCartMessage();

        System.out.println(actualMessage);
        System.out.println(expectedMessage);

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    /*@Test (priority = 4)
    void removeProductFromBasketByClickingTrashIcon(){

        basketFunctionalityPage.clickToCart();
        basketFunctionalityPage.clickToTrashIcon();

        String actualMessage = basketFunctionalityPage.getActualEmptyCartMessage();
        String expectedMessage = basketFunctionalityPage.getExpectedEmptyCartMessage();

        Assert.assertEquals(actualMessage, expectedMessage);
    }*/
    //Tests
}
