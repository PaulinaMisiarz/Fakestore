import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PurchaseMethods extends TestBase{

    //locators
    String firstCategory = "li[class='product-category product first']";
    String firstTrip = "ul[class='products columns-3'] li:first-child";
    String singleAddToCartButton = ".single_add_to_cart_button";
    String addToCartButton = ".add_to_cart_button";
    String notification = ".woocommerce-message";
    String seeCartButton = ".added_to_cart";
    String cartContents = "a[class='cart-contents']";
    String numberOfTripsInput = "div[class='quantity'] input";
    String allCategories = "ul[class='products columns-3'] li a.button";
    String updateCartButton = "button[name='update_cart']";
    String deleteButtonOnCartSite =".cart_item .product-remove a.remove";


    public void goToCategoriesSite(String category){
        getElementByCssAndScroll(category).click();
    }
    public void selectTrip(String trip){
        getElementByCssAndScroll(trip).click();
    }
    public void addToCartFromTripSite(){
        getElementByCssAndScroll(singleAddToCartButton).click();
    }
    public void addToCartFromCategoriesSite(){
        getElementByCssAndScroll(addToCartButton).click();
    }
    public void seeCartFromCategories(){
        getElementByCssAndScroll(seeCartButton).click();
    }
    private void typeInNumberOfTripsOnTripSite(String numberOfTrips){

        getElementByCss(numberOfTripsInput).clear();
        getElementByCss(numberOfTripsInput).sendKeys(numberOfTrips);
    }
    private void addAllTripsFromSelectedCategoryToCart() {

        List<WebElement> categories = getElementsByCss(allCategories);
        for (WebElement category : categories) {
            scrollToElement(category);
            category.click();
        }
    }
    private void updateCartFormCartView(){
        getElementByCss(updateCartButton).click();
    }
    private void removeTripFromCartSite(){
        getElementByCssAndScroll(deleteButtonOnCartSite).click();
    }
    @Test
    public void addTripToCartFormTripSite(){
        goToCategoriesSite(firstCategory);
        selectTrip(firstTrip);
        addToCartFromTripSite();
        Assertions.assertTrue(getElementByCssAndScroll(notification).getText().contains("został dodany do koszyka."));
    }
    @Test
    public void addTripToCartFormCategoriesSite(){
        goToCategoriesSite(firstCategory);
        addToCartFromCategoriesSite();
        seeCartFromCategories();
        Assertions.assertTrue(getElementByCssAndScroll(cartContents).getText().contains("1 Produkt"));
    }
    @Test
    public void addMultipleTripsToCartFromTripSite(){
        goToCategoriesSite(firstCategory);
        selectTrip(firstTrip);
        typeInNumberOfTripsOnTripSite("10");
        addToCartFromTripSite();
        Assertions.assertTrue(getElementByCssAndScroll(notification).getText().contains("zostało dodanych do koszyka."));
    }
    @Test
    public void addMultipleTripsToCartFromCategoriesSite(){
        goToCategoriesSite(firstCategory);
        addAllTripsFromSelectedCategoryToCart();
        Assertions.assertFalse(getElementByCssAndScroll(cartContents).getText().isEmpty());
    }
    @Test
    public void changeNumberOfTripsOnCartSite(){
        this.addTripToCartFormCategoriesSite();
        typeInNumberOfTripsOnTripSite("5");
        updateCartFormCartView();
        Assertions.assertTrue(getElementByCssAndScroll(notification).getText().contains("Koszyk zaktualizowany"));
    }
    @Test
    public void removeOneTripFromCartSite(){
        this.addTripToCartFormCategoriesSite();
        removeTripFromCartSite();
        Assertions.assertTrue(getElementByCssAndScroll(notification).getText().contains("Usunięto:"));
    }

}
