
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class deliveryTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverManager webDriverManager = new WebDriverManager();

    @BeforeTest
    public void start(){
        driver = webDriverManager.getDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void deliveryTest(){
        driver.get("https://www.avito.ru/");
        driver.findElement(By.xpath("//h4[@data-marker='service-dostavka/title']")).click();

        switchToWindow();

        wait.until(titleIs("Авито Доставка"));
        // создаем экземпляр JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // прокручиваем страницу вниз до конца
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        driver.findElement(By.xpath("//div[@class='footer-section-wrapper-xIM5e']/a")).click();

        switchToWindow();
        wait.until(titleContains("Купить личные вещи недорого"));

        List<WebElement> elements = driver.findElements(By.xpath("//div[@data-marker='category-widget']"));

        if (elements.size() != 5) {
            throw new AssertionError("Expected 5 elements, but found " + elements.size());
        } else {
            System.out.println("На странице 5 нужных элементов");
        }
    }

    public void switchToWindow() {
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
    }

    @AfterTest
    public void stop(){
        webDriverManager.quitDriver();
    }
}
