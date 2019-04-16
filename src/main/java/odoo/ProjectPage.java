package odoo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectPage extends NavigationMenu {

    @FindBy(how = How.XPATH, using = "//li[text()='Projects'][parent::ol[@role='navigation']][ancestor::div[@class='o_control_panel']]")
    public WebElement projectPageTitle;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Create')][contains(@class,'o-kanban-button-new')]")
    private WebElement createProjectButton;

    @FindBy(how = How.XPATH, using = "//input[contains(@class,'o_project_name')][@name='name'][@type='text']")
    private WebElement projectNameInput;

    @FindBy(how = How.XPATH, using = "//button[@name='open_tasks'][child::span[text()='Create']][contains(@class,'o_open_tasks')]")
    private WebElement createProjectModalButton;

    //Open modal window
    public void openNewProjectWindow(WebDriverWait wait){
        createProjectButton.click();
        //Wait until the modal window opens
        wait.until(ExpectedConditions.elementToBeClickable(createProjectModalButton));
    }

    //Fill form and create
    public TasksPage createProject(WebDriver driver, String projectName){
        projectNameInput.clear();
        projectNameInput.sendKeys(projectName);
        createProjectModalButton.click();
        return PageFactory.initElements(driver, TasksPage.class);
    }

    //Select the specified project
    public TasksPage selectProject(WebDriver driver, String projectName){
        driver.findElement(By.xpath("//div[contains(@class,'o_kanban_record')][@role='article'][descendant::span[text()='"+ projectName +"']]")).click();
        return PageFactory.initElements(driver, TasksPage.class);
    }
}
