package TestWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTest {
    private String base_path = "http://localhost:8080/";

    @Test
    public void user_test() throws IOException, InterruptedException {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get(base_path + "login");
        String title = driver.getTitle();
        // USC2: вход
        Assert.assertTrue(title.equals("Вход"));
        driver.findElement(By.name("e_mail")).sendKeys("vlad@mail.ru");
        driver.findElement(By.name("password")).sendKeys("1234");
        driver.findElement(By.id("login")).click();

        // USC4: добавление книг в корзину
        Assert.assertEquals(driver.getTitle(), "Информация о книгах");
        driver.findElement(By.name("title")).sendKeys("Элементы математики в физике");
        driver.findElement(By.id("search")).click();
        driver.findElement(By.className("watch")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[2]")).getText(), "Название: Элементы математики в физике");
        driver.findElement(By.name("count")).sendKeys("1");
        driver.findElement(By.className("add")).click();
        driver.findElement(By.name("title")).sendKeys("Оно");
        driver.findElement(By.id("search")).click();
        driver.findElement(By.className("watch")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[2]")).getText(), "Название: Оно");
        driver.findElement(By.name("count")).sendKeys("2");
        driver.findElement(By.className("add")).click();

        driver.findElement(By.linkText("Корзина")).click();

        //USC5: Оформление заказа
        Assert.assertEquals(driver.getTitle(), "Корзина");
        driver.findElement(By.name("delivery_address")).sendKeys("г.Москва, ул.Гагарина, д.45");
        driver.findElement(By.name("payment_card")).click();
        driver.findElement(By.className("edit")).click();
        Assert.assertEquals(driver.getTitle(), "Заказы");

        //USC6: редактирование профиля (удаление профиля)
        driver.findElement(By.linkText("Профиль")).click();
        Assert.assertEquals( driver.findElement(By.xpath("//h3[1]")).getText(), "Фамилия:");
        driver.findElement(By.className("edit")).click();
        driver.findElement(By.name("surname")).clear();
        driver.findElement(By.name("surname")).sendKeys("Егоров");
        driver.findElement(By.className("edit")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[1]")).getText(), "Фамилия: Егоров");

        driver.findElement(By.id("remove")).click();
        Assert.assertEquals(driver.getTitle(), "Регистрация");
        driver.quit();
    }
}
