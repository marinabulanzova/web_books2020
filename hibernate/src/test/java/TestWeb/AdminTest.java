package TestWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
public class AdminTest {
    private String base_path = "http://localhost:8080/";
    @Test
    public void admin_test() throws IOException, InterruptedException {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get(base_path + "login");
        String title = driver.getTitle();

        // USC2: вход
        Assert.assertTrue(title.equals("Вход"));
        driver.findElement(By.name("e_mail")).sendKeys("margo@mail.ru");
        driver.findElement(By.name("password")).sendKeys("1234");
        driver.findElement(By.id("login")).click();

        // USC7: Проверка и изменение статуса заказа (удаление заказа)
        driver.findElement(By.linkText("Информация о заказах")).click();
        driver.findElement(By.name("status")).sendKeys("в обработке");
        driver.findElement(By.id("search")).click();
        driver.findElement(By.className("watch")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[6]")).getText(), "Статус: в обработке");
        driver.findElement(By.className("edit")).click();
        driver.findElement(By.name("status")).clear();
        driver.findElement(By.name("status")).sendKeys("доставлен");
        driver.findElement(By.className("edit")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[6]")).getText(), "Статус: доставлен");

        //USC 8: добавление книги
        driver.findElement(By.linkText("Добавить новую книгу")).click();
        driver.findElement(By.name("title")).sendKeys("Крики");
        driver.findElement(By.name("author0")).sendKeys("Владимир Фролов");
        driver.findElement(By.name("genre")).sendKeys("триллер");
        driver.findElement(By.name("cover")).sendKeys("Твёрдая");
        driver.findElement(By.name("page_count")).sendKeys("378");
        driver.findElement(By.name("count_book")).sendKeys("8");
        driver.findElement(By.name("price")).sendKeys("450.0");
        driver.findElement(By.name("publishing_house")).sendKeys("АРТ");
        driver.findElement(By.name("publication_year")).sendKeys("2018");
        driver.findElement(By.id("edit")).click();


        // USC9: Изменение информации о книге (удаление книги)
        driver.findElement(By.linkText("Информация о книгах")).click();
        driver.findElement(By.name("title")).sendKeys("Крики");
        driver.findElement(By.id("search")).click();
        driver.findElement(By.className("watch")).click();
        Assert.assertEquals( driver.findElement(By.xpath("//h3[2]")).getText(), "Название: Крики");
        Assert.assertEquals( driver.findElement(By.xpath("//h3[9]")).getText(), "Стоимость: 450.0");
        driver.findElement(By.id("edit")).click();
        driver.findElement(By.name("price")).clear();
        driver.findElement(By.name("price")).sendKeys("530.0");
        driver.findElement(By.id("edit")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[9]")).getText(), "Стоимость: 530.0");

        driver.findElement(By.id("remove")).click();
        driver.findElement(By.name("title")).sendKeys("Крики");
        driver.findElement(By.id("search")).click();
        Assert.assertEquals(driver.findElement(By.className("null")).getText(), "Книг с такими параметрами не найдено");

        // USC10: удаление клиента
        driver.findElement(By.linkText("Информация о клиентах")).click();
        driver.findElement(By.className("watch")).click();
        driver.findElement(By.className("remove")).click();

        driver.findElement(By.linkText("Выход")).click();
        driver.quit();
    }
}
