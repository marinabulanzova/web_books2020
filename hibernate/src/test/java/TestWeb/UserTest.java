package TestWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTest {
    private String base_path = "http://localhost:8080/";

    @Test
    public void user_test () throws IOException, InterruptedException {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get(base_path + "books");
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Информация о книгах"));
        WebElement element = driver.findElement(By.id("tableBooks")).findElement(By.xpath("//td[6]/span/a"));
        element.click();
        Assert.assertTrue(driver.getTitle().equals("Подробная информация о книге"));
        element = driver.findElement(By.id("edit"));
        element.click();
        Assert.assertTrue(driver.getTitle().equals("Редактирование информации о книге"));
        element = driver.findElement(By.name("genre"));
        element.sendKeys("детектив");
        element = driver.findElement(By.id("edit"));
        element.click();
        driver.quit();
    }
}
