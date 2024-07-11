
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SwagLabs {

    public static void main(String[] args)  {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        // Credentials - make them constants so they don't change
        final String USERNAME = "standard_user";
        final String PASSWORD = "secret_sauce";

        /* Login page web elements: */
        WebElement firstNameInputBox = driver.findElement(By.id("user-name"));
        WebElement lastNameInputBox = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        /* Actions */
        firstNameInputBox.sendKeys(USERNAME);
        lastNameInputBox.sendKeys(PASSWORD);
        loginButton.click();

        /* Expected Constants */
        final String EXPECTED_PAGE_HEADER = "Products";
        final int EXPECTED_ITEMS_COUNT = 6;
        final String EXPECTED_ATC_TEXT_COLOR = "rgba(19, 35, 34, 1)"; // ATC - ADD TO CART
        final String EXPECTED_ATC_BORDER_COLOR = "rgb(19, 35, 34)"; // ATC - ADD TO CART
        final String EXPECTED_REMOVE_BUTTON_TEXT_COLOR = "rgba(226, 35, 26, 1)";
        final String EXPECTED_REMOVE_BUTTON_BORDER_COLOR = "rgb(226, 35, 26)";
        final String EXPECTED_REMOVE_BUTTON_TEXT = "Remove";

        /* Actions on products page */

        /* Verify Page Header */
        WebElement pageHeader = driver.findElement(By.cssSelector(".title"));
        String actualPageHeader = pageHeader.getText();
        System.out.println("Successfully Logged In. Page header: " + actualPageHeader);
        if(EXPECTED_PAGE_HEADER.equals(actualPageHeader)){
            System.out.println("- Verify Page Header: PASSED");
        } else {
            System.out.println("- Verify Page Header: FAILED");
        }

        /* Verify Items Count */
        List<WebElement> allProducts = driver.findElements(By.cssSelector(".inventory_item"));
        System.out.println("Available items on Products page: " + allProducts.size());
        if(allProducts.size() == EXPECTED_ITEMS_COUNT){
            System.out.println("- Verify Items Count: PASSED");
        } else {
            System.out.println("- Verify Items Count: FAILED");
        }

        /* Check for Specific Product */
        boolean productIsPresent = driver.findElements(By.xpath("//*[text() = 'Sauce Labs Bike Light']")).size() > 0;
        if(productIsPresent){
            System.out.println("- Verify 'Sauce Labs Bike Light' Presence: PASSED");
        } else {
            System.out.println("- Verify 'Sauce Labs Bike Light' Presence: FAILED");
        }

        /* ADD TO CART (ATC) Button Displayed and Enabled State */
        WebElement sauceLabsBoltTShirtButton = driver.findElement(By.xpath("//*[text() = 'Sauce Labs Bolt T-Shirt']/../../..//button"));
        if(sauceLabsBoltTShirtButton.isDisplayed() && sauceLabsBoltTShirtButton.isEnabled()){
            System.out.println("- Verify \"ADD TO CARD\" button Displayed and Enabled: PASSED");
        } else {
            System.out.println("- Verify \"ADD TO CARD\" button Displayed and Enabled: FAILED");
        }

        /* Verify ADD TO CART Button CSS Properties: */
        // atc - add to cart
        String atcTextColor = sauceLabsBoltTShirtButton.getCssValue("color");
        String atcBorder = sauceLabsBoltTShirtButton.getCssValue("border");
        String atcBorderColor = atcBorder.substring(atcBorder.indexOf("rgb"));

        /* Verify Button Text Color */
        if(EXPECTED_ATC_TEXT_COLOR.equals(atcTextColor)){
            System.out.println("- Verify Button Text Color: PASSED");
        } else {
            System.out.println("- Verify Button Text Color: FAILED");
        }

        /* Verify Button Border Color */
        if(EXPECTED_ATC_BORDER_COLOR.equals(atcBorderColor)){
            System.out.println("- Verify Button Border Color: PASSED");
        } else {
            System.out.println("- Verify Button Border Color: FAILED");
        }

        /* Interact with Button and Verify Changes */
        sauceLabsBoltTShirtButton.click();
        System.out.println("Clicked 'ATT TO CART' button.");

        // relocate the button to avoid stale element exception after we interact with the element
        sauceLabsBoltTShirtButton = driver.findElement(By.xpath("//*[text() = 'Sauce Labs Bolt T-Shirt']/../../..//button"));
        String sauceLabsBoltTShirtButtonText = sauceLabsBoltTShirtButton.getText();
        System.out.println("Current button displayed text: " + sauceLabsBoltTShirtButtonText);

        /* Verify Button Text */
        if(EXPECTED_REMOVE_BUTTON_TEXT.equals(sauceLabsBoltTShirtButtonText)){
            System.out.println("- Verify Button Text: PASSED.");
        } else {
            System.out.println("- Verify Button Text: FAILED.");
        }

        /* Extract the css values from the button after click */
        String rmButtonTextColor = sauceLabsBoltTShirtButton.getCssValue("color");
        String rmButtonBorder = sauceLabsBoltTShirtButton.getCssValue("border");
        String rmButtonBorderColor = rmButtonBorder.substring(rmButtonBorder.indexOf("rgb"));

        /* Verify Button Text Color */
        if(EXPECTED_REMOVE_BUTTON_TEXT_COLOR.equals(rmButtonTextColor)){
            System.out.println("- Verify Button Text Color: PASSED");
        } else {
            System.out.println("- Verify Button Text Color: FAILED");
        }

        /* Verify Button Border Color */
        if(EXPECTED_REMOVE_BUTTON_BORDER_COLOR.equals(rmButtonBorderColor)){
            System.out.println("- Verify Button Border Color: PASSED");
        } else {
            System.out.println("- Verify Button Border Color: FAILED");
        }

        /* Cart Verification */
        WebElement cartButton = driver.findElement(By.cssSelector(".shopping_cart_link"));
        cartButton.click();
        System.out.println("Clicked Cart button.");

        /* Cart Page Actions */
        List<WebElement> allCartProducts = driver.findElements(By.cssSelector(".cart_item"));
        System.out.println("Items in the cart: " + allCartProducts.size());

        /* Verify items count in cart */
        if(allCartProducts.size() == 1){
            System.out.println("- Verify Cart Items: PASSED");
        } else {
            System.out.println("- Verify Cart Items: FAILED");
        }


        driver.quit();

    }
}


