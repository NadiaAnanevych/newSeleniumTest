package ui.WaitsWindowsFrames;

import org.assertj.core.api.recursive.assertion.DefaultRecursiveAssertionIntrospectionStrategy;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

public class WaitsTests {
    WebDriver driver;
    private static final String IMAGE_URL= "https://bonigarcia.dev/selenium-webdriver-java/loading-images.html";

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    @Tag("positive")
    void implicitWaitTest(){

        driver.get(IMAGE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        WebElement award = driver.findElement(By.id("award"));
        assertTrue(award.getDomAttribute("src").contains("award"));
    }

    @Test
    @Tag("negative")
    void findingImageNegativeTest(){
        driver.get(IMAGE_URL);
        Assertions.assertThrows(NoSuchElementException.class, () ->
        {driver.findElement(By.id("award"));
        }, "Image cannot be found");
    }

    @Test
    void explicitWaitTest(){
        driver.get(IMAGE_URL);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("award")));
        WebElement award = driver.findElement(By.id("award"));
        assertTrue(award.getDomAttribute("src").contains("award"));
    }

    @Test
    void fluentWaitTest() {
        driver.get(IMAGE_URL);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(7))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("award")));
        WebElement award = driver.findElement(By.id("award"));
        assertTrue(award.getDomAttribute("src").contains("award"));
    }

}
