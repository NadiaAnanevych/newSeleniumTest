package ui.WaitsWindowsFrames;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertsTests {
    WebDriver driver;
    Actions actions;

    private static final String DIALOG_URL= "https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html";

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actions = new Actions(driver);

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }


    @Test
    void openAlertTest() {
        driver.get(DIALOG_URL);
        WebElement launchAlert = driver.findElement(By.id("my-alert"));
        new Actions(driver).moveToElement(launchAlert).click().perform();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        assertEquals(text, "Hello world!");
    } //this should be done as separate method and used in follow tests

    @Test
    void confirmAlertTest() {
        driver.get(DIALOG_URL);
        WebElement launchConfirm = driver.findElement(By.id("my-confirm"));
        actions.moveToElement(launchConfirm).click().perform();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        assertEquals(text, "Is this correct?");
        alert.accept();
        WebElement confirmText = driver.findElement(By.id("confirm-text"));
        assertEquals("You chose: true", confirmText.getText());
    }

    @Test
    void declineAlertTest() {
        driver.get(DIALOG_URL);
        WebElement launchConfirm = driver.findElement(By.id("my-confirm"));
        actions.moveToElement(launchConfirm).click().perform();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        assertEquals(text, "Is this correct?");
        alert.dismiss();
    }

    @Test
    void alertPromptTest(){
        driver.get(DIALOG_URL);
        WebElement launchPrompt = driver.findElement(By.id("my-prompt"));
        actions.moveToElement(launchPrompt).click().perform();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        assertEquals(text, "Please enter your name");
        alert.sendKeys("Nadia");
        alert.accept();
        WebElement typedText = driver.findElement(By.id("prompt-text"));
        assertEquals("You typed: Nadia", typedText.getText());
    }

    @Test
    void modalAlertTest() {
        driver.get(DIALOG_URL);
        WebElement launchModal = driver.findElement(By.id("my-modal"));
        actions.moveToElement(launchModal).click().perform();
        WebElement saveChangesButton = driver.findElement(By.xpath("//div[@class='modal-footer']//button[contains(@class, 'btn-primary')]"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.elementToBeClickable(saveChangesButton));
        saveChangesButton.click();
    }

}
