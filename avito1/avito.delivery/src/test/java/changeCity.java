
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class changeCity {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverManager webDriverManager = new WebDriverManager();

    @BeforeTest
    public void start(){
        driver = webDriverManager.getDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
        public void changeCity(){
        driver.get("https://www.avito.ru/");
        driver.findElement(By.xpath("//span[text()='Во всех регионах']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Улица и номер дома']")));

        WebElement divParent = driver.findElement(By.xpath("//div[@data-marker='popup-location/radius-list']"));
        List<WebElement> children = divParent.findElements(By.xpath("./*"));
        int expChildCount = 10;

        if (children.size() != expChildCount) {
            throw new AssertionError("Expected 10 elements, but found " + children.size());
        } else {
            System.out.println("Number of child elements equals: " + children.size() + ", expected: " + expChildCount);
        }

        WebElement cityInput = driver.findElement(By.xpath("//input[@placeholder='Улица и номер дома']"));
        cityInput.sendKeys("Москва, Красная площадь, 1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@data-marker=\"suggest-list\"]")));
        driver.findElement(By.xpath("//ul[@data-marker='suggest-list']/li")).click();
        wait.until(ExpectedConditions.attributeToBe(cityInput, "value", "Москва, Красная площадь, 1"));
        driver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']")).click();
        wait.until(ExpectedConditions.titleContains("Авито | Объявления во"));
    }

    @AfterTest
    public void stop(){
        webDriverManager.quitDriver();
    }
}
