package odoo;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarPage extends NavigationMenu {

    @FindBy(how = How.XPATH, using = "//a[@class='o_menu_brand'][text()='Calendar']")
    public WebElement calendarTitle;

    public By calendarModalTitle = By.xpath("//h4[@class='modal-title'][contains(text(),'Create: Meetings')]");

    @FindBy(how = How.XPATH, using = "//input[@class='o_input'][@type='text'][ancestor::main[contains(@class,'o_calendar_quick_create')]]")
    private WebElement newMeetingSummaryInput;

    @FindBy(how = How.XPATH, using = "//button[child::span[text()='Create']][parent::footer[@class='modal-footer']]")
    private WebElement newMeetingCreateButton;

    @FindBy(how = How.XPATH, using = "//span[text()='Administrator [Me]'][ancestor::div[@class='o_calendar_filter_items'][preceding-sibling::h3[text()='Attendees']]]")
    private WebElement adminAttendeesLabel;

    @FindBy(how = How.XPATH, using = "//input[following-sibling::label[child::span[text()='Administrator [Me]'][ancestor::div[@class='o_calendar_filter_items'][preceding-sibling::h3[text()='Attendees']]]]]")
    private WebElement adminAttendeesCheckbox;

    @FindBy(how = How.XPATH, using = "//h3[text()='Responsible']")
    private WebElement responsibleTitle;

    public void clickOnTime(WebDriver driver, WebDriverWait wait, String time){
        //Get the current date and format it with the 'yyyy-MM-dd' pattern
        Calendar currentDate = new GregorianCalendar();
        SimpleDateFormat calFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = calFormat.format(currentDate.getTime());
        //Get the row and column for the meeting
        WebElement dateHeader = driver.findElement(By.xpath("//th[contains(@class,'fc-day-header')][@data-date='"+formattedDate+"']"));
        WebElement timeHeader = driver.findElement(By.xpath("//td[contains(@class,'fc-time')][parent::tr[@data-time='"+ time +"'][ancestor::div[contains(@class,'fc-time-grid')]]]"));
        //Points for the x/y coordinates
        Point dateHeaderPoint = dateHeader.getLocation();
        Point timeHeaderPoint = timeHeader.getLocation();
        //Scroll the time header into view
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", timeHeader);
        //Move the cursor to the time header then move it again to the right column and click on it
        Actions action = new Actions(driver);
        action.moveToElement(timeHeader).moveByOffset((dateHeaderPoint.getX()-timeHeaderPoint.getX()),0).click().build().perform();
        //Wait until the modal window open
        wait.until(ExpectedConditions.visibilityOfElementLocated(calendarModalTitle));
    }

    public void fillEvent(WebDriverWait wait, String name){
        newMeetingSummaryInput.clear();
        newMeetingSummaryInput.sendKeys(name);
        newMeetingCreateButton.click();
        //Wait until the modal window is not visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h4[@class='modal-title'][contains(text(),'Create: Meetings')]")));
    }
    /*
    Create a meeting in the calendar
    Args :
    - String time : Time of the meeting. Must be in the format 'hh:mm:ss'
    - String name : Name of the meeting.
     */
    public void createMeeting(WebDriver driver, WebDriverWait wait, String time, String name){
        //Get the current date and format it with the 'yyyy-MM-dd' pattern
        Calendar currentDate = new GregorianCalendar();
        SimpleDateFormat calFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = calFormat.format(currentDate.getTime());
        //Get the row and column for the meeting
        WebElement dateHeader = driver.findElement(By.xpath("//th[contains(@class,'fc-day-header')][@data-date='"+formattedDate+"']"));
        WebElement timeHeader = driver.findElement(By.xpath("//td[contains(@class,'fc-time')][parent::tr[@data-time='"+ time +"'][ancestor::div[contains(@class,'fc-time-grid')]]]"));
        //Points for the x/y coordinates
        Point dateHeaderPoint = dateHeader.getLocation();
        Point timeHeaderPoint = timeHeader.getLocation();
        //Scroll the time header into view
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", timeHeader);
        //Move the cursor to the time header then move it again to the right column and click on it
        Actions action = new Actions(driver);
        action.moveToElement(timeHeader).moveByOffset((dateHeaderPoint.getX()-timeHeaderPoint.getX()),0).click().build().perform();
        //Wait until the modal window open
        wait.until(ExpectedConditions.visibilityOfElementLocated(calendarModalTitle));
        //Fill the new meeting form and send
        newMeetingSummaryInput.clear();
        newMeetingSummaryInput.sendKeys(name);
        newMeetingCreateButton.click();
        //Wait until the modal window is not visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h4[@class='modal-title'][contains(text(),'Create: Meetings')]")));
    }

    //Uncheck the Administrator in the Attendees and check that the "Responsible" section is not present
    public void uncheckAttendees(WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOf(responsibleTitle));
        adminAttendeesLabel.click();
        wait.until(ExpectedConditions.stalenessOf(responsibleTitle));
    }
}
