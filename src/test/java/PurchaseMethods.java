import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PurchaseMethods extends TestBase{

    //locators
    String firstCategory = "li[class='product-category product first']";
    String firstTrip = "ul[class='products columns-3'] li:first-child";
    String singleAddToCartButton = ".single_add_to_cart_button";
    String addToCartButton = ".add_to_cart_button";
    String notification = ".woocommerce-message";
    String seeCartButton = ".added_to_cart";
    String cartContents = "a[class='cart-contents']";

    //values
    String notificationAddToCartText = "został dodany do koszyka.";
    String oneProductTextOnCartContents = "1 Produkt";

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

    @Test
    public void addTripToCartFormTripSite(){
        goToCategoriesSite(firstCategory);
        selectTrip(firstTrip);
        addToCartFromTripSite();
        Assertions.assertTrue(getElementByCssAndScroll(notification).getText().contains(notificationAddToCartText));
    }
    @Test
    public void addTripToCartFormCategoriesSite(){
        goToCategoriesSite(firstCategory);
        addToCartFromCategoriesSite();
        seeCartFromCategories();
        Assertions.assertTrue(getElementByCssAndScroll(cartContents).getText().contains(oneProductTextOnCartContents));
    }
}
