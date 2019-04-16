package odoo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TasksPage extends NavigationMenu {

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Tasks')][preceding-sibling::li[child::a[text()='Projects']]]")
    public WebElement tasksTitle;

    private By columnInput = By.xpath("//input[@placeholder='Column title'][@type='text'][contains(@class,'o_input')]");

    private By addColumnButton = By.xpath("//button[text()='Add'][contains(@class,'o_kanban_add')][ancestor::div[@class='o_kanban_header']]");

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'o_kanban_project_tasks')]/div[1]//i[@title='Quick add']")
    private WebElement newTaskCol1Button;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'o_kanban_project_tasks')]/div[2]")
    private WebElement column2;

    @FindBy(how = How.XPATH, using = "//input[@name='name'][@type='text'][ancestor::div[@class='o_kanban_quick_create']]")
    private WebElement taskTitleInput;

    @FindBy(how = How.XPATH, using = "//button[contains(@class,'o_kanban_add')][text()='Add'][ancestor::div[@class='o_kanban_quick_create']]")
    private WebElement taskAddButton;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'oe_kanban_card')]")
    private WebElement kanbanCard;

    //Add a new column in the project
    public void addColumn(WebDriver driver, WebDriverWait wait, String name){
        driver.findElement(columnInput).clear();
        driver.findElement(columnInput).sendKeys(name);
        driver.findElement(addColumnButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[parent::div[@class='o_kanban_header_title']][text()='"+ name +"']")));
    }

    //Create a new task in the first column with the specified name
    public void addCard(WebDriverWait wait, String name){
        newTaskCol1Button.click();
        wait.until(ExpectedConditions.elementToBeClickable(taskTitleInput));
        taskTitleInput.clear();
        taskTitleInput.sendKeys(name);
        taskAddButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ name +"'][ancestor::div[contains(@class,'oe_kanban_card')]]")));
    }

    //Drag and drop the created task to the second column
    public void moveCardToColumn(WebDriver driver){
        Actions action = new Actions(driver);
        action.dragAndDrop(kanbanCard, column2).build().perform();
    }
}
