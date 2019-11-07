package pages;

import jsons.JsonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import setup.Setup;

import java.util.concurrent.TimeUnit;

public class BasketFunctionalityPage {

    WebDriver driver;
    Wait wait = Setup.wait;
    JsonAPI jsonAPI = new JsonAPI("BasketFunctionality.json");

    public BasketFunctionalityPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (partialLinkText = "Blouse")
    WebElement blouseProduct;

    @FindBy (name = "Submit")
    WebElement addToCartButton;

    @FindBy (id = "layer_cart")
    WebElement windowAfterBuying;

    @FindBy (id = "our_price_display")
    WebElement productPrice;

    @FindBy (id = "layer_cart_product_price")
    WebElement productPriceFromLayerCart;

    @FindBy (css = "[class='ajax_cart_shipping_cost']")
    WebElement shippingCost;

    @FindBy (css = "[class='ajax_block_cart_total']")
    WebElement totalCost;

    @FindBy (css = "[class='layer_cart_product col-xs-12 col-md-6']")
    WebElement headerMessage;

    @FindBy (css="[class='cross']")
    WebElement crossButton;

    @FindBy (css = "[class='logo img-responsive']")
    WebElement headerLogo;

    ////*[@id="header"]/div[3]/div/div/div[3]/div/a
    //css = "[class='shopping_cart']"

    @FindBy (xpath = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a")
    WebElement shoppingCart;

    @FindBy (css = "[class='ajax_cart_block_remove_link']")
    WebElement crossButtonInCart;

    @FindBy (css = "[class='icon-trash']")
    WebElement iconTrash;

    @FindBy (css = "[class='alert alert-warning']")
    WebElement actualEmptyCartMessage;

    @FindBy (css = "[class='icon-plus']")
    WebElement plusIcon;

    @FindBy (css = "[class='icon-minus']")
    WebElement minusIcon;

    //dwa ponizej do uzycia podczas mnozenia pojedynczej ceny razy N
    @FindBy (id = "product_price_2_7_0")
    WebElement unitPrice;

    @FindBy (name = "quantity_2_7_0_0")
    WebElement productQuantity;

    @FindBy (id = "total_product_price_2_7_0")
    WebElement total;

    @FindBy (id = "total_product")
    WebElement totalProductsField;

    @FindBy (id = "total_shipping")
    WebElement shipping;

    @FindBy (id = "total_price")
    WebElement fullTotalValue;

    @FindBy (id = "summary_products_quantity")
    WebElement summaryOfQuantity;


    public void clickToBlouseProduct (){
        blouseProduct.click();
    }

    public double getSumProductAndShippingCosts(){

        wait.until(ExpectedConditions.visibilityOf(productPriceFromLayerCart));
        wait.until(ExpectedConditions.visibilityOf(shippingCost));

        //zastapic replace wyrazeniem regularnym!!!
        double productCostDouble = Double.parseDouble(productPriceFromLayerCart.getText().replace("$", ""));
        double shippingCostDouble = Double.parseDouble(shippingCost.getText().replace("$", ""));

        double sumCost = productCostDouble + shippingCostDouble;

        return  sumCost;

    }
    public double getTotalCost(){

        return  Double.parseDouble(totalCost.getText().replace("$", ""));
    }
    public String getSuccessMessageAfterAdd(){
        wait.until(ExpectedConditions.visibilityOf(headerMessage));
        return headerMessage.getText();
    }

    public void clickToAddToCartButton(){
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();
    }
    public void clickToCrossButton(){
        //wait.until(ExpectedConditions.visibilityOf(crossButton));
        crossButton.click();
    }
    public void waitMillisecond (int sec){
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }
    public void hoverToCart(){
        Actions actions = new Actions(driver);

        wait.until(ExpectedConditions.visibilityOf(shoppingCart));
        actions.moveToElement(shoppingCart).perform();
    }
    public void clickToCrossButtonInCart(){
        wait.until(ExpectedConditions.visibilityOf(crossButtonInCart));
        crossButtonInCart.click();
    }
    public void goToHomePage(){
        JsonAPI json = new JsonAPI("setup.json");

        driver.navigate().to(json.getFromJSON("urlPath"));
    }
    public void clickToCart(){
        wait.until(ExpectedConditions.visibilityOf(shoppingCart));
        shoppingCart.click();
    }
    public void clickToTrashIcon(){
        wait.until(ExpectedConditions.visibilityOf(iconTrash));
        iconTrash.click();
    }
    public String getActualEmptyCartMessage(){
        wait.until(ExpectedConditions.visibilityOf(actualEmptyCartMessage));
        return actualEmptyCartMessage.getText();
    }
    public String getExpectedEmptyCartMessage(){
        return jsonAPI.getFromJSON("emptyCartMessage");
    }

    public void clickToIcon(int n, String iconType){

        wait.until(ExpectedConditions.visibilityOf(productQuantity));

        int quantity = Integer.parseInt(productQuantity.getAttribute("value"));
        int quantityAfterLoop = 0;

        if(iconType.equals("plus")){
            wait.until(ExpectedConditions.visibilityOf(plusIcon));
            for(int i = 0; i < n; i++){
                plusIcon.click();
            }
            quantityAfterLoop = quantity + n;
        }else if(iconType.equals("minus")){
            wait.until(ExpectedConditions.visibilityOf(minusIcon));
            for(int i = 0; i < n; i++){
                minusIcon.click();
            }
            quantityAfterLoop = quantity - n;
        }
        wait.until(ExpectedConditions.attributeToBe(productQuantity,"value", Integer.toString(quantityAfterLoop)));
    }

    public void changeNumberOfProductInQuantityField(String numberOfProduct){

        wait.until(ExpectedConditions.visibilityOf(productQuantity));

        //productQuantity.click();
        productQuantity.clear();
        productQuantity.sendKeys(numberOfProduct);
        //wait.until(ExpectedConditions.attributeToBe(productQuantity,"value", numberOfProduct));
    }

    public void waitForProductQuantity(String numberOfProduct){
        wait.until(ExpectedConditions.attributeToBe(productQuantity,"value", numberOfProduct));
    }
    public double multiplyProductPriceAndQuantity(){

        wait.until(ExpectedConditions.visibilityOf(unitPrice));
        wait.until(ExpectedConditions.visibilityOf(productQuantity));

        double unitPriceProduct = Double.parseDouble(unitPrice.getText().replace("$",""));
        int quantity = Integer.parseInt(productQuantity.getAttribute("value"));

        System.out.println("quantity: " + quantity);

        return unitPriceProduct * quantity;
    }
    public double getTotalValueFromProduct(){
        wait.until(ExpectedConditions.visibilityOf(unitPrice));
        return Double.parseDouble(total.getText().replace("$",""));
    }
    public double getValueFromTotalProductsField(){
        wait.until(ExpectedConditions.visibilityOf(totalProductsField));
        return Double.parseDouble(totalProductsField.getText().replace("$",""));
    }
    public double getShippingCost(){
        wait.until(ExpectedConditions.visibilityOf(shipping));
        return Double.parseDouble(shipping.getText().replace("$",""));
    }
    public double getFullTotalValue(){
        wait.until(ExpectedConditions.visibilityOf(fullTotalValue));
        return Double.parseDouble(fullTotalValue.getText().replace("$",""));
    }
    public String getSummaryOfQuantity(){
        wait.until(ExpectedConditions.visibilityOf(summaryOfQuantity));
        return summaryOfQuantity.getText();
    }
    public String expectedSummaryOfQuantity(String quantityString){
        wait.until(ExpectedConditions.textToBePresentInElement(summaryOfQuantity, quantityString + " Product"));

        if(quantityString.equals("1")){
            return quantityString + " Product";
        }else
            return quantityString + " Products";
    }
}
