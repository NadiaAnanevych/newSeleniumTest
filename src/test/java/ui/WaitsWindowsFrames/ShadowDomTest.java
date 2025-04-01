package ui.WaitsWindowsFrames;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class ShadowDomTest<LocalStorage> {
    WebDriver driver;


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
    void shadowDomTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html");
        WebElement content = driver.findElement(By.id("content"));
        SearchContext shadowRoot = content.getShadowRoot();
        WebElement textElement = shadowRoot.findElement(By.cssSelector("p"));
        assertThat(textElement.getText()).contains("Hello Shadow DOM");
    }
}
