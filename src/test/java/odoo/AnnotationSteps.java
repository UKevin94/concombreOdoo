package odoo;

import cucumber.api.java.fr.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AnnotationSteps {

    WebDriver driver;
    WebDriverWait wait;
    static final String USERNAME = "admin";
    static final String PASSWORD = "azerty";
    static final String PROJECT_NAME = "TestingProject";
    InboxPage inboxPage;
    ProjectPage projectPage;
    TasksPage tasksPage;
    CalendarPage calendarPage;
    static final String FIRST_CARD = "FirstCreated";

    @Étantdonnéque("je suis sur le site d'Odoo")
    public void je_suis_sur_le_site_d_Odoo() throws Exception {
        String browser = System.getProperty("web.browser");
        switch (browser) {
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                driver = new ChromeDriver();
                break;
            default:
                throw new Exception("No supported browser has been specified");
        }
        wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(System.getProperty("page.url"));
    }

    @Étantdonnéque("je suis connecté en tant qu'admin")
    public void je_suis_connecté_en_tant_qu_admin() {
        LoginPage loginPage = LoginPage.initLoginPage(driver);
        //InboxPage after LogIn
        inboxPage = loginPage.logIn(driver, USERNAME, PASSWORD);
        wait.until(ExpectedConditions.visibilityOf(inboxPage.inboxTitle));
    }

    @Soit("je suis sur la page des projets")
    public void je_suis_sur_la_page_des_projets() {
        projectPage = inboxPage.goToProject(driver, wait);
        wait.until(ExpectedConditions.visibilityOf(projectPage.projectPageTitle));
    }

    @Lorsque("je clique sur le bouton create")
    public void je_clique_sur_le_bouton_create() {
        projectPage.openNewProjectWindow(wait);
    }

    @Lorsque("je rempli la fenêtre modale")
    public void je_rempli_la_fenêtre_modale() {
        tasksPage = projectPage.createProject(driver, PROJECT_NAME);
    }

    @Alors("mon projet est créé")
    public void mon_projet_est_créé() {

        wait.until(ExpectedConditions.visibilityOf(tasksPage.tasksTitle));
        driver.quit();
    }

    @Soit("je suis sur la page de mon projet")
    public void je_suis_sur_la_page_de_mon_projet() {
        ProjectPage projectPage = inboxPage.goToProject(driver, wait);
        wait.until(ExpectedConditions.visibilityOf(projectPage.projectPageTitle));
        tasksPage = projectPage.selectProject(driver, PROJECT_NAME);
        wait.until(ExpectedConditions.visibilityOf(tasksPage.tasksTitle));
    }

    @Soit("j'ai créé les colonnes {string}, {string} et {string} dans mon tableau")
    public void j_ai_créé_les_colonnes_dans_mon_tableau(String col1, String col2, String col3) {
        tasksPage.addColumn(driver, wait, col1);
        tasksPage.addColumn(driver, wait, col2);
        tasksPage.addColumn(driver, wait, col3);
    }

    @Lorsque("j'ajoute de nouvelles tâches dans la première colonne")
    public void j_ajoute_de_nouvelles_tâches_dans_la_première_colonne() {
        tasksPage.addCard(wait, FIRST_CARD);
    }

    @Alors("elles sont affichées dans le tableau")
    public void elles_sont_affichées_dans_le_tableau() {
        Assert.assertTrue(driver.findElements(By.xpath("//div[@role='feed'][contains(@class,'o_kanban_group')]//span[text()='"+ FIRST_CARD +"']")).size()>0);
        driver.quit();
    }

    @Etantdonnéque("je suis sur la page \"Calendar\"")
    public void je_suis_sur_la_page_calendar() {
        calendarPage = inboxPage.goToCalendar(driver, wait);
        wait.until(ExpectedConditions.visibilityOf(calendarPage.calendarTitle));
    }

    @Quand("je clique sur la case correspondant à {string} heures")
    public void je_clique_sur_la_case_correspondant_à_heures(String heure) {
        calendarPage.clickOnTime(driver, wait, heure);
    }

    @Quand("je rempli le formulaire avec pour nom {string}")
    public void je_rempli_le_formulaire_avec_pour_nom(String nom) {
        calendarPage.fillEvent(wait, nom);
    }

    @Alors("le rendez-vous {string} est enregistré dans le calendrier")
    public void le_rendez_vous_est_enregistré_dans_le_calendrier(String nom) {
        Assert.assertTrue(driver.findElements(By.xpath("//a[contains(@class,'fc-event')][descendant::div[contains(text(),'"+ nom +"')]]")).size()>0);
        driver.quit();
    }
}
