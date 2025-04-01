package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ElementsInteractions {
    WebDriver driver;
    Actions actions;

    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    String webForm_url = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
    String navigation_url = "https://bonigarcia.dev/selenium-webdriver-java/navigation1.html";
    String dropdown_url = "https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html";
    String dragAndDrop_url = "https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html";

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actions = new Actions(driver);
    }

    @AfterEach
    void close() {
        driver.close();
    }

    //Web form tests
    @Test
    void textInputTest() throws InterruptedException {
        driver.get(webForm_url);
        WebElement inputField = driver.findElement(By.id("my-text-id"));
        WebElement passwordField = driver.findElement(By.name("my-password"));
        inputField.sendKeys("test");
        Thread.sleep(3000);
        passwordField.sendKeys("pass");
        Thread.sleep(3000);
        assertEquals("test", inputField.getAttribute("value"));
        assertEquals("pass", passwordField.getAttribute("value"));
    }

    @Test
    void disabledFieldTest() {
        driver.get(webForm_url);
        WebElement disabledField = driver.findElement(By.cssSelector("input[placeholder='Disabled input']"));
        assertFalse(disabledField.isEnabled());
    }

    @Test
    void readOnlyTest() {
        driver.get(webForm_url);
        WebElement readOnly = driver.findElement(By.name("my-readonly"));
        assertEquals("Readonly input", readOnly.getDomProperty("value"));
    }

    @Test
    void dropdownSelectTest() {
        driver.get(webForm_url);
        WebElement selectOptions = driver.findElement(By.className("form-select"));
        Select select = new Select(selectOptions);
        assertTrue(select.getFirstSelectedOption().isSelected());
        select.selectByValue("3");
        assertEquals("Three", select.getFirstSelectedOption().getText());
        select.selectByIndex(1);
        assertEquals("One", select.getFirstSelectedOption().getText());
        List<WebElement> selectedOptionsOptions = select.getAllSelectedOptions();

    }

    @Test
    void dropdowDatalistTest() throws InterruptedException {
        driver.get(webForm_url);
        WebElement datalistInput = driver.findElement(By.name("my-datalist"));
        datalistInput.sendKeys("myCity");
        assertEquals("myCity", datalistInput.getAttribute("value"));
        List<WebElement> datalistOptions = driver.findElements(By.cssSelector("datalist#my-options option"));
        for (WebElement option : datalistOptions) {
            datalistInput.clear();
            String optionValue = option.getAttribute("value");
            datalistInput.sendKeys(optionValue);
            Thread.sleep(2000);
        }
    }

    @Test
    void fileInputTest() throws InterruptedException {
        driver.get(webForm_url);
        File uploadFile = new File("src/test/resources/testInputFile");
        WebElement fileInput = driver.findElement(By.name("my-file"));
        WebElement submitButton = driver.findElement(By.tagName("button"));
        fileInput.sendKeys(uploadFile.getAbsolutePath());
        actions.moveToElement(submitButton).perform();
        submitButton.click();  //this part of test also checks Submit button
        Thread.sleep(3000);
        WebElement title = driver.findElement(By.className("display-6"));
        assertEquals("Form submitted", title.getText());
    }

    @Test
    void checkboxTest() {
        driver.get(webForm_url);
        WebElement checkedCheckbox = driver.findElement(By.xpath("//input[@checked and @type = \"checkbox\"]"));
        //assertTrue(checkedCheckbox.isSelected());
        boolean isSelected = checkedCheckbox.isSelected();
        if (!isSelected) {
            checkedCheckbox.click();
        }
    }

    @Test
    void defaultRadioTest() {
        driver.get(webForm_url);
        WebElement defauldRadio = driver.findElement(By.xpath("//input[not(@checked) and @name= \"my-radio\"]"));
        assertFalse(defauldRadio.isSelected());
    }

    @Test
    void datePickerTest() throws InterruptedException {
        driver.get(webForm_url);
        WebElement dateValue = driver.findElement(By.cssSelector("[name = 'my-date']"));
        dateValue.sendKeys("07 01 1992");
        dateValue.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        assertEquals("07/01/1992", dateValue.getAttribute("value"));
    }

    @Test
    void rangePickerTest() throws InterruptedException {
        driver.get(webForm_url);
        WebElement rangeInput = driver.findElement(By.cssSelector(".form-range"));
        actions.moveToElement(rangeInput).perform();
        rangeInput.click();
        String firstValue = rangeInput.getDomProperty("value");
        actions.clickAndHold(rangeInput).moveByOffset(60, 0).release().perform();
        String secondValue = rangeInput.getDomProperty("value");
        assertNotEquals(firstValue, secondValue);
    }

    //navigation Tests

    @Test
    void previousButtonDisabledTest(){
        driver.get(navigation_url);
        WebElement previousPage = driver.findElement(By.xpath("//a[text() = \"Previous\"]/parent::li[@class='page-item disabled']"));
        assertTrue(previousPage.getAttribute("class").contains("disabled"));
    }

    @Test
    void clickNextButtonTest() {
        driver.get(navigation_url);
        WebElement nextButton = driver.findElement(By.xpath("//a[text() = \"Next\"]"));
        actions.moveToElement(nextButton).perform();
        nextButton.click();
        String secondPage = driver.getCurrentUrl();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation2.html", secondPage);
    }

    @Test
    void checkTextTest(){
        driver.get(navigation_url);
        WebElement textBlock = driver.findElement(By.className("lead"));
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", textBlock.getText());
    }

    //dropdown Tests
    @Test
    void checkDropdowns() throws InterruptedException {
        driver.get(dropdown_url);
        WebElement leftDropdown = driver.findElement(By.id("my-dropdown-1"));
        WebElement rightDropdown = driver.findElement(By.id("my-dropdown-2"));
        WebElement doubleDropdown = driver.findElement(By.id("my-dropdown-3"));
        actions.click(leftDropdown).perform();
        Thread.sleep(2000);
        actions.contextClick(rightDropdown).perform();
        Thread.sleep(2000);
        actions.doubleClick(doubleDropdown).perform();
        Thread.sleep(2000);
    }

    //dragAndDrop tests
    @Test
    void dragAnaDropTest() throws InterruptedException {
        driver.get(dragAndDrop_url);
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("target"));
        actions.dragAndDrop(draggable, target).perform();
        Thread.sleep(2000);
    }


}
