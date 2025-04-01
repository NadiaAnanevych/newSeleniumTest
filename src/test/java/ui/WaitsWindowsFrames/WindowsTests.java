package ui.WaitsWindowsFrames;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class WindowsTests {

    WebDriver driver;


    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";


    String longPage_url = "https://bonigarcia.dev/selenium-webdriver-java/long-page.html";
    String shadowDom_url = "https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html";
    String cookies_url = BASE_URL + "cookies.html";
    String frames_url = BASE_URL + "frames.html";
    String iframes_url = BASE_URL + "iframes.html";
    String dialogBoxes_url = BASE_URL + "dialog-boxes.html";
    String webStorage_url = BASE_URL + "web-storage.html";





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
    void chromeOptionsTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.getBrowserName();
        chromeOptions.getBrowserVersion();
        String name = chromeOptions.getBrowserName();
        driver = new ChromeDriver(chromeOptions);
        driver.get(BASE_URL);
        assertFalse(name.isEmpty(), "Browser name should not be empty");

    }


    @Test
    void switchTabsTest() throws InterruptedException {
        driver.get(BASE_URL);
        driver.manage().window().fullscreen();
        String currentWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(longPage_url);
        assertEquals(longPage_url, driver.getCurrentUrl());
        Thread.sleep(1000);
        driver.switchTo().window(currentWindow);
        }


        @Test
        void switchWindowsTest() throws InterruptedException {
            driver.get(BASE_URL);
            assertThat(driver.getWindowHandles()).hasSize(1);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(numberOfWindowsToBe(2));
            driver.switchTo().newWindow(WindowType.WINDOW);
            assertThat(driver.getWindowHandles()).hasSize(2);
        }

        @Test
        void navigationTest(){
           driver.get(BASE_URL);
           driver.navigate().to(frames_url);
           driver.navigate().back();
           assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
           driver.navigate().forward();
           driver.navigate().refresh();
           assertEquals(frames_url, driver.getCurrentUrl());
        }

}
