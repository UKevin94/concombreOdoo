package odoo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(how = How.XPATH, using = "//input[@id='login'][@type='text']")
    private WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@id='password'][@type='password']")
    private WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[@type='submit'][text()='Log in']")
    private WebElement logInButton;

    /*
    LogIn with the specified username and password
     */
    public InboxPage logIn(WebDriver driver, String username, String password){
        emailInput.clear();
        emailInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        logInButton.click();
        return PageFactory.initElements(driver, InboxPage.class);
    }

    /*
    Init the login page with the PageFactory to start the test
     */
    static public LoginPage initLoginPage(WebDriver driver){
        return PageFactory.initElements(driver, LoginPage.class);
    }



}
