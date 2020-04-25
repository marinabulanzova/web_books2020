package TestWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GuestTest {
    private String base_path = "http://localhost:8080/";

    @Test
    public void guest_test() throws IOException, InterruptedException {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get(base_path);
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Информация о книгах"));
        WebElement element = driver.findElement(By.className("watch"));
        element.click();

        Assert.assertTrue(driver.getTitle().equals("Подробная информация о книге"));
        element = driver.findElement(By.xpath("//h3[2]"));
        Assert.assertEquals(element.getText(), "Название: Лирика");
        driver.findElement(By.linkText("Регистрация")).click();

        Assert.assertEquals(driver.getTitle(), "Регистрация");
        driver.findElement(By.name("first_name")).sendKeys("Влад");
        driver.findElement(By.name("e_mail")).sendKeys("vlad@mail.ru");
        driver.findElement(By.name("phone_number")).sendKeys("89092408909");
        driver.findElement(By.name("password_hash")).sendKeys("1234");
        driver.findElement(By.id("register")).click();

        Assert.assertEquals(driver.getTitle(), "Вход");
        driver.findElement(By.name("e_mail")).sendKeys("vlad@mail.ru");
        driver.findElement(By.name("password")).sendKeys("1234");
        driver.findElement(By.id("login")).click();
        Assert.assertEquals(driver.getTitle(), "Информация о книгах");
        driver.quit();
    }
}
