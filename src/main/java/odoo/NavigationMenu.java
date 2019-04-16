package odoo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationMenu {

    @FindBy(how = How.XPATH, using = "//a[@data-toggle='dropdown'][ancestor::nav[@class='o_main_navbar']][ancestor::ul[@class='o_menu_apps']]")
    private WebElement mainMenuButton;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Project')][@class='dropdown-item o_app'][@role='menuitem'][ancestor::ul[@class='o_menu_apps']]")
    private WebElement projectLink;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Calendar')][@class='dropdown-item o_app'][@role='menuitem'][ancestor::ul[@class='o_menu_apps']]")
    private WebElement calendarLink;

    @FindBy(how = How.XPATH, using = "//a[child::span[text()='Administrator']][parent::li[@class='o_user_menu']][@class='dropdown-toggle']")
    private WebElement administratorMenu;

    @FindBy(how = How.XPATH, using = "//a[text()='Log out'][@role='menuitem'][@class='dropdown-item'][ancestor::li[@class='o_user_menu show']]")
    private WebElement logOutButton;

    //Open the dropdown menu and click on the project link
    public ProjectPage goToProject(WebDriver driver, WebDriverWait wait){
        mainMenuButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(projectLink));
        projectLink.click();
        return PageFactory.initElements(driver, ProjectPage.class);
    }

    //Open the dropdown menu and click on the calendar link
    public CalendarPage goToCalendar(WebDriver driver, WebDriverWait wait){
        mainMenuButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(calendarLink));
        calendarLink.click();
        return PageFactory.initElements(driver, CalendarPage.class);
    }

    //Log out from the right window
    public void logOut(WebDriverWait wait){
        administratorMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(logOutButton));
        logOutButton.click();
    }
}
