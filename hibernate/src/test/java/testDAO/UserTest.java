package testDAO;

import DAO.UserDAO;
import models.User;
import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static utils.HibernateSessionFactoryUtil.getSessionFactory;

@Test(singleThreaded=true)
public class UserTest {

    private SessionFactory sessionFactory = null;

    @BeforeMethod
    public void setUp() throws Exception {
        sessionFactory = getSessionFactory();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        sessionFactory.close();
    }

    @Test
    public void testSaveUpdateDelete() throws Exception {
        UserDAO users = new UserDAO(sessionFactory);
        List<User> l = users.findAll();
        Assert.assertEquals(l.size(), 7 );
        User user = new User(
                "Буланцова",
                "Марина",
                "Геннадьевна",
                "г.Москва, Ломомносовский проспект, д.27",
                "89779347100",
                "marina_b@mail.ru",
                "fsgvsgtsg",
                false);
        users.save(user);
        l = users.findAll();
        Assert.assertEquals(l.size(), 8 );
        Assert.assertEquals(l.get(0).getSurname(), "Буланцова" );
        Assert.assertEquals(l.get(0).getFirst_name(), "Марина" );
        Assert.assertEquals(l.get(0).getPatronymic(), "Геннадьевна" );
        Assert.assertEquals(l.get(0).getAddress(), "г.Москва, Ломомносовский проспект, д.27" );
        Assert.assertEquals(l.get(0).getPhone_number(), "89779347100" );
        Assert.assertEquals(l.get(0).getE_mail(), "marina_b@mail.ru" );
        Assert.assertEquals(l.get(0).getPassword_hash(), "fsgvsgtsg" );
        Assert.assertEquals((boolean)l.get(0).getAdmin(), false );

        users.delete(l.get(0));
        l = users.findAll();
        Assert.assertEquals(l.size(), 7 );

        Assert.assertEquals(l.get(3).getE_mail(), "matveev_d@mail.ru");
        Assert.assertEquals((int)l.get(3).getId_user(), 1);
        user = users.getById(1);
        user.setE_mail("matveev_d@gmail.ru");
        users.update(user);
        l = users.findAll();
        Assert.assertEquals((int)l.get(3).getId_user(), 1);
        Assert.assertEquals(l.get(3).getE_mail(), "matveev_d@gmail.ru");
        user = l.get(3);
        user.setE_mail("matveev_d@mail.ru");
        users.update(user);
    }

    public void testFind() throws Exception {
        UserDAO users = new UserDAO(sessionFactory);
        List<User> l =  users.find(null,
                "Валерия",
                null,
                null,
                null,
                null,
                null);
        Assert.assertEquals(l.size(), 2 );
        Assert.assertEquals(l.get(0).getFirst_name(), "Валерия");
        Assert.assertEquals(l.get(1).getFirst_name(), "Валерия");

        l =  users.find(null,
                null,
                null,
                "Ломоносовский проспект",
                null,
                null,
                null);
        Assert.assertEquals(l.size(), 2 );
        Assert.assertEquals(l.get(0).getAddress(), "г.Москва, Ломоносовский проспект, д.103, к.1");
        Assert.assertEquals(l.get(1).getAddress(), "г.Москва, Ломоносовский проспект, д.27, к.11");

        l =  users.find(null,
                null,
                null,
                null,
                null,
                null,
                true);
        Assert.assertEquals(l.size(), 1 );
        Assert.assertEquals((boolean)l.get(0).getAdmin(), true);

        l =  users.find(null,
                null,
                null,
                null,
                "89779347100",
                "marina_b@mail.ru",
                null);
        Assert.assertEquals(l.size(), 0 );

        l =  users.find("Костина",
                "Екатерина",
                "Андреевна",
                null,
                null,
                null,
                null);
        Assert.assertEquals(l.size(), 1);
        Assert.assertEquals(l.get(0).getPatronymic(), "Андреевна");
        Assert.assertEquals(l.get(0).getFirst_name(), "Екатерина");
        Assert.assertEquals(l.get(0).getSurname(), "Костина");
    }
}
