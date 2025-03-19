package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

//import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTests {
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    WebDriver driver;


    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void openHomePageTest(){
        String actualTitle = driver.getTitle();
        assertEquals("Hands-On Selenium WebDriver with Java", actualTitle);

    }

    @Test
    void openWebFormTest(){
        String webFormUrl = "web-form.html";
        WebElement webFormLink = driver.findElement(By.xpath("//a[@href = 'web-form.html']"));
        // //h5[contains(text(), "Chapter 3.")]/../a[@href = 'web-form.html'] another path
        new Actions(driver).moveToElement(webFormLink).perform();
        webFormLink.click();
        String currentUrl = driver.getCurrentUrl(); //actual result
        WebElement title = driver.findElement(By.className("display-6"));  //actual result

        assertEquals(BASE_URL + webFormUrl, currentUrl);
        assertEquals("Web form", title.getText());
    }

    @Test
    void openNavigationTest(){
        WebElement navLink = driver.findElement(By.xpath("//h5[contains(text(), \"Chapter 3.\")]/../a[contains(@href, 'navigation1')]"));
        new Actions(driver).moveToElement(navLink).perform();
        navLink.click();
        String navUrl = "navigation1.html";
        String currentUrl = driver.getCurrentUrl(); //actual result
        WebElement title = driver.findElement(By.className("display-6"));  //actual result

        assertEquals(BASE_URL + navUrl, currentUrl);
        assertEquals("Navigation example", title.getText());
    }

    @Test
    void openDropdownMenuTest(){
        WebElement dropdownLink = driver.findElement(By.xpath("//h5[contains(text(), \"Chapter 3.\")]/../a[contains(@href, 'dropdown-menu')]"));
        new Actions(driver).moveToElement(dropdownLink).perform();
        dropdownLink.click();
        String dropdownUrl = "dropdown-menu.html";
        String currentUrl = driver.getCurrentUrl(); //actual result
        WebElement title = driver.findElement(By.className("display-6"));  //actual result

        assertEquals(BASE_URL + dropdownUrl, currentUrl);
        assertEquals("Dropdown menu", title.getText());
    }

    @Test
    void openMouseOverTest() {
        WebElement mouseOverLink = driver.findElement(By.xpath("//h5[text() ='Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'mouse-over')]"));
        String mouseOverUrl = "mouse-over.html";
        new Actions(driver).moveToElement(mouseOverLink).perform();
        mouseOverLink.click();
        String currentUrl = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));

        assertEquals(BASE_URL + mouseOverUrl, currentUrl);
        assertEquals("Mouse over", title.getText());

    }

    @Test
    void dragAndDropTest() {
        WebElement dragAndDropLink = driver.findElement(By.xpath("//h5[text() ='Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'drag-and-drop')]"));
        String dragAndDropUrl = "drag-and-drop.html";
        new Actions(driver).moveToElement(dragAndDropLink).perform();
        dragAndDropLink.click();
        String currentUrl = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));

        assertEquals(BASE_URL + dragAndDropUrl, currentUrl);
        assertEquals("Drag and drop", title.getText());

    }

    @Test
    void longPageTest(){
        WebElement longPageLink = driver.findElement(By.xpath("//h5[contains(text(), \"Chapter 4.\")]/../a[contains(text(), 'Long page')]"));
        new Actions(driver).moveToElement(longPageLink).perform();
        longPageLink.click();
        String longPageUrl = "long-page.html";
        String currentUrl = driver.getCurrentUrl(); //actual result
        WebElement title = driver.findElement(By.className("display-6"));  //actual result

        assertEquals(BASE_URL + longPageUrl, currentUrl);
        assertEquals("This is a long page", title.getText());
    }

    @Test
    void framesTest(){
        WebElement framesLink = driver.findElement(By.xpath("//h5[contains(text(), \"Chapter 4.\")]/../a[contains(text(), 'Frames')]"));
        new Actions(driver).moveToElement(framesLink).perform();
        framesLink.click();
        String framesUrl = "frames.html";
        String currentUrl = driver.getCurrentUrl();
        WebElement frameElement = driver.findElement(By.cssSelector("frame[name='frame-header']"));
        driver.switchTo().frame(frameElement);//actual result
        WebElement title = driver.findElement(By.className("display-6"));  //actual result

        assertEquals(BASE_URL + framesUrl, currentUrl);
        assertEquals("Frames", title.getText());
    }

    @Test
    void iframesTest(){
        WebElement iframesLink = driver.findElement(By.xpath("//h5[contains(text(), \"Chapter 4.\")]/../a[text() = 'IFrames']"));
        new Actions(driver).moveToElement(iframesLink).perform();
        iframesLink.click();
        String iframesUrl = "iframes.html";
        String currentUrl = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));  //actual result

        assertEquals(BASE_URL + iframesUrl, currentUrl);
        assertEquals("IFrame", title.getText());
    }

    @Test
    void geolocationTest(){
        WebElement geolocationLink = driver.findElement(By.xpath("//h5[contains(text(), \"Chapter 5.\")]/../a[text() = 'Geolocation']"));
        new Actions(driver).moveToElement(geolocationLink).perform();
        geolocationLink.click();
        String geolocationsUrl = "geolocation.html";
        String currentUrl = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));  //actual result

        assertEquals(BASE_URL + geolocationsUrl, currentUrl);
        assertEquals("Geolocation", title.getText());
    }

    @Test
    void consoleLogsTest(){
        WebElement consoleLogsLink = driver.findElement(By.xpath("//h5[contains(text(), \"Chapter 5.\")]/../a[contains (@href, 'console-logs')]"));
        new Actions(driver).moveToElement(consoleLogsLink).perform();
        consoleLogsLink.click();
        String consoleLogsUrl = "console-logs.html";
        String currentUrl = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));  //actual result

        assertEquals(BASE_URL + consoleLogsUrl, currentUrl);
        assertEquals("Console logs", title.getText());
    }

    @Test
    void loginFormTest(){
        WebElement loginFormLink = driver.findElement(By.xpath("//h5[contains(text(), \"Chapter 7.\")]/../a[contains (@href, 'login-form')]"));
        new Actions(driver).moveToElement(loginFormLink).perform();
        loginFormLink.click();
        String loginFormUrl = "login-form.html";
        String currentUrl = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));  //actual result

        assertEquals(BASE_URL + loginFormUrl, currentUrl);
        assertEquals("Login form", title.getText());
    }

    @Test
    void randomCalculatorTest(){
        WebElement randomCalculatorLink = driver.findElement(By.xpath("//h5[contains(text(), \"Chapter 8.\")]/../a[contains (@href, 'random-calculator')]"));
        new Actions(driver).moveToElement(randomCalculatorLink).perform();
        randomCalculatorLink.click();
        String randomCalculatorUrl = "random-calculator.html";
        String currentUrl = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));  //actual result

        assertEquals(BASE_URL + randomCalculatorUrl, currentUrl);
        assertEquals("Random calculator", title.getText());
    }

    @Test
    void dataTypesTest(){
        WebElement dataTypesLink = driver.findElement(By.xpath("//h5[contains(text(), \"Chapter 9.\")]/../a[contains (@href, 'data-types')]"));
        new Actions(driver).moveToElement(dataTypesLink).perform();
        dataTypesLink.click();
        String dataTypesUrl = "data-types.html";
        String currentUrl = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));  //actual result

        assertEquals(BASE_URL + dataTypesUrl, currentUrl);
        assertEquals("Data types", title.getText());
    }

    
    @Test
    void openAllLinksTest() throws InterruptedException {
        int qtyLinks = 0;
        List<WebElement> chapters =  driver.findElements(By.cssSelector("h5.card-title"));
        for (WebElement chapter : chapters) {
            List<WebElement> links = chapter.findElements(By.xpath("./../a"));
            qtyLinks += links.size();
            System.out.println(chapter.getText());
            for (WebElement link : links) {
                System.out.println(link.getText());
                new Actions(driver).moveToElement(link).perform();
                link.click();
                Thread.sleep(1000);
                driver.navigate().back();
            }
        }
        assertEquals(6, chapters.size());
        assertEquals(27, qtyLinks);
    }
}
