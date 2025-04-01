package ui.WaitsWindowsFrames;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookiesTests {
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
    void addCookieTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        driver.manage().addCookie(new Cookie("username", "John"));
    }

    @Test
    void getCookiesTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        driver.manage().addCookie(new Cookie("username", "John"));
        driver.manage().addCookie(new Cookie("date", "10/07/2009"));
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                assertEquals(cookie.getValue(), "John");
            }

            if (cookie.getName().equals("date")) {
                assertEquals(cookie.getValue(), "10/07/2009");
            }
        }
        driver.findElement(By.id("refresh-cookies")).click();
        Thread.sleep(2000);

    }

    @Test
    void deleteCookieTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        driver.manage().addCookie(new Cookie("username", "John"));
        driver.manage().deleteCookieNamed("username");
        driver.findElement(By.id("refresh-cookies")).click();
        Thread.sleep(2000);
    }

}
