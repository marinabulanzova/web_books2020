package TestWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

        //USC3: просмотр информации о книге
        driver.findElement(By.name("title")).sendKeys("Лирика");
        driver.findElement(By.id("search")).click();
        driver.findElement(By.className("watch")).click();
        Assert.assertEquals( driver.findElement(By.xpath("//h3[2]")).getText(), "Название: Лирика");

        //USC1:регистрация
        driver.findElement(By.linkText("Регистрация")).click();
        Assert.assertEquals(driver.getTitle(), "Регистрация");
        driver.findElement(By.name("first_name")).sendKeys("Виктор");
        driver.findElement(By.name("e_mail")).sendKeys("victor@mail.ru");
        driver.findElement(By.name("phone_number")).sendKeys("89092408909");
        driver.findElement(By.name("password_hash")).sendKeys("12345");
        driver.findElement(By.id("register")).click();

        // USC2: вход
        Assert.assertEquals(driver.getTitle(), "Вход");
        driver.findElement(By.name("e_mail")).sendKeys("victor@mail.ru");
        driver.findElement(By.name("password")).sendKeys("12345");
        driver.findElement(By.id("login")).click();
        Assert.assertEquals(driver.getTitle(), "Информация о книгах");

        driver.findElement(By.linkText("Выход")).click();
        driver.quit();
    }
}
