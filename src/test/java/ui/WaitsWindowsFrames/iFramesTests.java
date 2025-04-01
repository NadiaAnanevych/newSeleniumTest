package ui.WaitsWindowsFrames;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import static org.assertj.core.api.Assertions.assertThat;

public class iFramesTests {
    WebDriver driver;
    Actions actions;

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
    void iFrameTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/iframes.html");
        WebElement iframe = driver.findElement(By.id("my-iframe"));
        driver.switchTo().frame(iframe);
        /*driver.switchTo().frame("buttonframe"); using id
        driver.switchTo().frame("myframe"); using name
        driver.switchTo().frame(1); using index */
        WebElement scrollTo = driver.findElement(By.xpath("//div[@id='content']//p[@class='lead'][last()]"));
        new Actions(driver)
                .scrollToElement(scrollTo)
                .perform();
        Thread.sleep(2000);
        assertThat(driver.findElement(By.className("lead")).getText()).contains("Lorem ipsum dolor sit amet");
        driver.switchTo().defaultContent();


    }

    @Test
    @Tag("negative")
    void iFrameNegativeTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/iframes.html");
        Assertions.assertThrows(NoSuchElementException.class, () ->
        {driver.findElement(By.id("content"));
        }, "iFrame cannot be found");
    }
}

