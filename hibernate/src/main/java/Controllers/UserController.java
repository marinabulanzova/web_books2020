package Controllers;


import DAO.*;
import com.sun.istack.NotNull;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static utils.HibernateSessionFactoryUtil.getSessionFactory;

@Controller
public class UserController {
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String findAll(ModelMap model) {
        Session session = getSessionFactory().openSession();
        UserDAO users = new UserDAO(session);
        model.addAttribute("UsersList", users.findAll());
        return "users";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
        public String hello() {
        return "index";
    }

/*    // почему он post, потому что get уже есть?
    @RequestMapping(value = "/users/search_by_private_information", method = RequestMethod.GET)
    public String list(@RequestParam String surname, @RequestParam String first_name,
                       @RequestParam String patronymic, @RequestParam String address,
                       @RequestParam String phone_number, @RequestParam String e_mail,
                       @RequestParam(required = false) Boolean admin, ModelMap model) {
        Session session = getSessionFactory().openSession();
        UserDAO users = new UserDAO(session);
        if (surname.equals("")) surname = null;
        if (first_name.equals("")) first_name = null;
        if (patronymic.equals("")) patronymic = null;
        if (address.equals("")) address = null;
        if (e_mail.equals(""))  e_mail = null;
        if (phone_number.equals("")) phone_number = null;

        model.addAttribute("UsersList",
                users.find(surname, first_name, patronymic, address, phone_number, e_mail, admin));
        model.addAttribute("surname", surname);
        model.addAttribute("first_name", first_name);
        model.addAttribute("patronymic", patronymic);
        model.addAttribute("address", address);
        model.addAttribute("e_mail", e_mail);
        model.addAttribute("phone_number", surname);
        model.addAttribute("admin", admin);

        return "users/search_results";
    }
    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String save_user(ModelMap model) {
        return "users/add";
    }

    @RequestMapping(value = "/users/rm", method = RequestMethod.DELETE)
    public String remove_client(@RequestParam Integer id,
                                ModelMap model) {
        Session session = getSessionFactory().openSession();
        UserDAO users = new UserDAO(session);

        session.getTransaction().begin();
        users.delete(users.getById(id));
        session.getTransaction().commit();

        model.addAttribute("UsersList", users.findAll());
        return "users/rm";
    }*/


}
