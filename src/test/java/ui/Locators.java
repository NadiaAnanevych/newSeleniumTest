package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Locators {
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
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
    void titleFormPage() {
        String actualTitle = driver.getTitle();
        assertEquals("Hands-On Selenium WebDriver with Java", actualTitle);
    }
    @Test
    void subtitleForm(){
        WebElement webFormSubtitle = driver.findElement(By.className("display-6"));
        assertEquals("Web form", webFormSubtitle.getText());
    }

    @Test
    void textInputField() {
        WebElement textInput = driver.findElement(By.xpath("//label[contains(text(), \"Text input\")]"));
      WebElement inputField = driver.findElement(By.id("my-text-id"));
      assertEquals("Text input", textInput.getText());
      assertEquals("input", inputField.getTagName());
    }

    @Test
    void passwordInputField() {
        WebElement passwordInput = driver.findElement(By.xpath("//label[contains(text(), \"Password\")]"));
        WebElement passwordField = driver.findElement(By.name("my-password"));
        assertEquals("Password", passwordInput.getText());
        assertEquals("input", passwordField.getTagName());
    }

    @Test
    void inputFieldsTest() {
        WebElement textInput = driver.findElement(By.xpath("//label[contains(text(), \"Text input\")]"));
        WebElement inputField = driver.findElement(By.id("my-text-id"));
        WebElement passwordInput = driver.findElement(By.xpath("//label[contains(text(), \"Password\")]"));
        WebElement passwordField = driver.findElement(By.name("my-password"));
        WebElement textAreaInput = driver.findElement(By.xpath("//label[contains(text(), \"Textarea\")]"));
        WebElement textAreaField = driver.findElement(By.name("my-textarea"));
        WebElement disabledInput = driver.findElement(By.xpath("//label[contains(text(), \"Disabled input\")]"));
        WebElement disabledField = driver.findElement(By.cssSelector("input[placeholder='Disabled input']"));
        WebElement readonlyInput = driver.findElement(By.xpath("//label[contains(text(), \"Readonly input\")]"));
        WebElement readonlyField = driver.findElement(By.cssSelector("input[value='Readonly input']"));
        assertEquals("Text input", textInput.getText());
        assertEquals("input", inputField.getTagName());
        assertEquals("Password", passwordInput.getText());
        assertEquals("input", passwordField.getTagName());
        assertEquals("Textarea", textAreaInput.getText());
        assertEquals("textarea", textAreaField.getTagName());
        assertEquals("Disabled input", disabledInput.getText());
        assertEquals("input", disabledField.getTagName());
        assertEquals("Readonly input", readonlyInput.getText());
        assertEquals("input", readonlyField.getTagName());
    }

    @Test
    void indexTest(){
        WebElement indexLink = driver.findElement(By.linkText("Return to index"));
        assertEquals("Return to index", indexLink.getText());
    }

    @Test
    void footerTest(){
        WebElement copyrightText = driver.findElement(By.xpath("//span[@class='text-muted' and normalize-space(text())='Copyright © 2021-2025']"));
        WebElement boniGarciaLink = driver.findElement(By.xpath("//a[contains (text(), 'Boni García')]"));
        //assertEquals("Copyright © 2021-2025", copyrightText.getText());
        assertEquals("Boni García", boniGarciaLink.getText());

    }

    @Test
    void dropdownSelectTest() {
        WebElement dropdownSelect = driver.findElement(By.xpath("//select[@class = 'form-select']/parent::label"));
        WebElement selectOptions = driver.findElement(By.className("form-select"));
        assertEquals("Dropdown (select)\n" +
                "                Open this select menu\n" +
                "                One\n" +
                "                Two\n" +
                "                Three\n" +
                "              ", dropdownSelect.getText());
        assertEquals("select", selectOptions.getTagName());
    }

    @Test
    void dropdownDatalistTest(){
        WebElement dropdownDatalist = driver.findElement(By.xpath("//input[@list = \"my-options\" and @name = \"my-datalist\"]/.."));
        WebElement datalistInput = driver.findElement(By.name("my-datalist"));
        assertEquals("Dropdown (datalist)", dropdownDatalist.getText());
        assertEquals("input", datalistInput.getTagName());
    }

    @Test
    void fileInputTest() {
        WebElement fileInputTitle = driver.findElement(By.xpath("//input[@name = \"my-file\"]/.."));
        WebElement fileInputField = driver.findElement(By.name("my-file"));
        assertEquals("File input", fileInputTitle.getText());
        assertEquals("input", fileInputField.getTagName());
    }

    @Test
    void checkboxTests(){
        WebElement checkedCheckbox = driver.findElement(By.xpath("//input[@checked and @type = \"checkbox\"]"));
        WebElement defaultCheckbox = driver.findElement(By.id("my-check-2"));
        assertEquals("input", checkedCheckbox.getTagName());
        assertEquals("input", defaultCheckbox.getTagName());
    }

    @Test
    void radioButtonTests(){
        WebElement checkedRadio = driver.findElement(By.xpath("//input[@checked and @name= \"my-radio\"]"));
        WebElement defauldRadio = driver.findElement(By.xpath("//input[not(@checked) and @name= \"my-radio\"]"));
        assertEquals("input", checkedRadio.getTagName());
        assertEquals("input", defauldRadio.getTagName());
    }

    @Test
    void SubmitTest(){
        WebElement submitButton = driver.findElement(By.tagName("button"));
        assertEquals("Submit", submitButton.getText());
    }

    @Test
    void colorPickerTest() {
        WebElement colorPickerLabel = driver.findElement(By.xpath("//input[@type = 'color']/parent::label "));
        WebElement colorValue = driver.findElement(By.cssSelector("[name = 'my-colors']"));
        assertEquals("Color picker", colorPickerLabel.getText());
        assertEquals("input", colorValue.getTagName());
    }
    @Test
    void datePickerTest() {
        WebElement datePickerLabel = driver.findElement(By.xpath("//input[@name = 'my-date']/parent::label "));
        WebElement dateValue = driver.findElement(By.cssSelector("[name = 'my-date']"));
        assertEquals("Date picker", datePickerLabel.getText());
        assertEquals("input", dateValue.getTagName());
    }

    @Test
    void rangeTest(){
        WebElement rangeTitle = driver.findElement(By.xpath("//input[@name = 'my-range']/parent::label"));
        WebElement rangeInput = driver.findElement(By.cssSelector(".form-range"));
        assertEquals("Example range", rangeTitle.getText());
        assertEquals("input", rangeInput.getTagName());
    }





}
