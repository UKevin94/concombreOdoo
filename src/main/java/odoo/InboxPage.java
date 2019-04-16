package odoo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class InboxPage extends NavigationMenu{

    @FindBy(how = How.XPATH, using = "//a[text()='Discuss'][@class='o_menu_brand'][@role='button']")
    public WebElement inboxTitle;
}
