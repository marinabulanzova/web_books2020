package Controllers;


import DAO.*;
import config.SecurityConfig;
import form.UserForm;
import models.*;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;


@Controller
public class UserController {
    @Autowired
    SessionFactory factory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register_get() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register_post(@ModelAttribute /*@Valid  hibernate_validator*/User user, ModelMap model) {
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        boolean number = false;
        boolean email = false;
        if (user.getFirst_name().equals("") || user.getPhone_number().equals("") || user.getE_mail().equals("") || user.getPassword_hash().equals("") ||
                (number = users.find(null, null, null, null, user.getPhone_number(), null, null).size() > 0) ||
                (email = users.find(null, null, null, null, null, user.getE_mail(), null).size() > 0)
        ) {
            if (user.getFirst_name().equals("") || user.getPhone_number().equals("") || user.getE_mail().equals(""))
                model.addAttribute("error", true);
            if (number) {
                model.addAttribute("error_number", true);
            }
            if (email) {
                model.addAttribute("error_email", true);
            }
            model.addAttribute("surname", user.getSurname());
            model.addAttribute("first_name", user.getFirst_name());
            model.addAttribute("patronymic", user.getPatronymic());
            model.addAttribute("address", user.getAddress());
            model.addAttribute("phone_number", user.getPhone_number());
            model.addAttribute("e_mail", user.getE_mail());
            return "register";
        }
        user.setPassword_hash(SecurityConfig.passwordEncoder().encode(user.getPassword_hash()));
        //user.setPassword_hash(PasswordEncoder.encode(user.getPassword_hash()));
        user.setAdmin(false);
        users.save(user);
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error, ModelMap model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        return "login";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String findAll(ModelMap model) {
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        model.addAttribute("UsersList", users.find(null,null,null,
                null,null, null, false));
        return "users";
    }

    @RequestMapping(value = "/detailed_users", method = RequestMethod.GET)
    public String detailed_user(@RequestParam Integer id,
                                ModelMap model) {
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        User user = users.getById(id);
        model.addAttribute("user", user);
        return "users/detailed";
    }

    @RequestMapping(value = "/search_users", method = RequestMethod.GET)
    public String list(@RequestParam String surname, @RequestParam String first_name,
                       @RequestParam String patronymic, @RequestParam String address,
                       @RequestParam String phone_number, @RequestParam String e_mail, ModelMap model) {
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        if (surname.equals("")) surname = null;
        if (first_name.equals("")) first_name = null;
        if (patronymic.equals("")) patronymic = null;
        if (address.equals("")) address = null;
        if (e_mail.equals(""))  e_mail = null;
        if (phone_number.equals("")) phone_number = null;

        model.addAttribute("UsersList",
                users.find(surname, first_name, patronymic, address, phone_number, e_mail, false));
        model.addAttribute("surname", surname);
        model.addAttribute("first_name", first_name);
        model.addAttribute("patronymic", patronymic);
        model.addAttribute("address", address);
        model.addAttribute("e_mail", e_mail);
        model.addAttribute("phone_number", phone_number);
        model.addAttribute("admin", false);

        return "users/search_results";
    }

    @RequestMapping(value = "/rm_users", method = RequestMethod.POST)
    public String remove_user(@RequestParam Integer id,
                              ModelMap model) {
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);

        session.getTransaction().begin();
        users.delete(users.getById(id));
        session.getTransaction().commit();

        model.addAttribute("UsersList", users.findAll());
        return "users";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        models.User user = (models.User)auth.getPrincipal();
        model.addAttribute("user", user);
        return "users/account";
    }

    @RequestMapping(value = "/rm_account", method = RequestMethod.POST)
    public String remove_account(HttpServletRequest request, ModelMap model) {
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        models.User user = (models.User)auth.getPrincipal();

        session.getTransaction().begin();
        users.delete(user);
        session.getTransaction().commit();

        SecurityContextHolder.clearContext();
        HttpSession s = request.getSession(false);

        if (s != null) {
            s.invalidate();
        }
        return "redirect:/?accountDeleted=true";
        //return "/register";
    }

    @RequestMapping(value = "/edit_account", method = RequestMethod.GET)
    public String edit_user(@RequestParam Integer id,
                              ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        models.User user = (models.User)auth.getPrincipal();
        UserForm userForm = new UserForm(user.getSurname(), user.getFirst_name(), user.getPatronymic(),
                user.getAddress(), user.getPhone_number(), user.getE_mail());
        model.addAttribute("userForm", userForm);
        return "users/edit";
    }

    @RequestMapping(value = "/edit_account", method = RequestMethod.POST)
    public String edit_done(@ModelAttribute UserForm userForm, ModelMap model)  {
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        boolean number = false;
        boolean email = false;
        if (userForm.getFirst_name().equals("") || userForm.getE_mail().equals("") || userForm.getPhone_number().equals("") ||
                (number = users.find(null, null, null, null, userForm.getPhone_number(), null, null).size() > 0 )||
                (email = users.find(null, null, null, null, null, userForm.getE_mail(), null).size() > 0 )
        ) {
            if(userForm.getFirst_name().equals("") || userForm.getE_mail().equals("") ||  userForm.getPhone_number().equals(""))
                model.addAttribute("error", true);
            if(number) {
                model.addAttribute("error_number", true);
            }
            if (email) {
                model.addAttribute("error_email", true);
            }
            model.addAttribute("userForm", userForm);
            return "users/edit";
        }
        if (!userForm.getOld_password().equals("") || userForm.getNew_password().equals("")) {
            model.addAttribute("error_new_password", true);
            model.addAttribute("userForm", userForm);
            return "users/edit";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        models.User user = (models.User)auth.getPrincipal();
        if(!passwordEncoder.matches(userForm.getOld_password(), user.getPassword_hash())){
            model.addAttribute("error_new_password", true);
            model.addAttribute("userForm", userForm);
            return "users/edit";
        }
        user.setSurname(userForm.getSurname());
        user.setFirst_name(userForm.getFirst_name());
        user.setPatronymic(userForm.getPatronymic());
        user.setAddress(userForm.getAddress());
        user.setPhone_number(userForm.getPhone_number());
        user.setE_mail(userForm.getE_mail());
        session.getTransaction().begin();
        users.update(user);
        session.getTransaction().commit();
        return "users/account";
    }

    @RequestMapping(value = "/my_orders", method = RequestMethod.GET)
    public String my_orders(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        models.User user = (models.User)auth.getPrincipal();
        model.addAttribute("OrdersList", user.getOrders());
        return "users/my_orders";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        models.User user = (models.User)auth.getPrincipal();
        model.addAttribute("BooksList", user.getBasket_customerList());
        return "users/basket";
    }

    @RequestMapping(value = "/rm_book_basket", method = RequestMethod.POST)
    public String remove_book(@RequestParam Integer id,
                              ModelMap model) {
        Session session = factory.openSession();
        Basket_customerDAO basket_customers = new Basket_customerDAO(session);

        session.getTransaction().begin();
        basket_customers.delete(basket_customers.getById(id));
        session.getTransaction().commit();

        return "redirect:users/basket";
    }

    @RequestMapping(value = "/add_orders", method = RequestMethod.POST)
    public String add_order(@RequestParam String delivery_address, @RequestParam Boolean payment_card,
                            ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        models.User user = (models.User)auth.getPrincipal();
        Session session = factory.openSession();
        Basket_orderDAO basket_orders = new Basket_orderDAO(session);
        Basket_customerDAO basket_customers = new Basket_customerDAO(session);
        OrderDAO orders = new OrderDAO(session);
        Boolean payment = payment_card ? payment_card : false;
        Order order = new Order(user, delivery_address, payment,new Timestamp(System.currentTimeMillis()), null, "в обработке", null);
        session.getTransaction().begin();
        for(Basket_customer b_c : user.getBasket_customerList()) {
            Basket_order b_o = new Basket_order( b_c.getBook(), order,  b_c.getCount_book(), b_c.getBook().getPrice());
            basket_customers.delete(b_c);
            basket_orders.save(b_o);
        }
        orders.save(order);
        session.getTransaction().commit();
        return "redirect:/users/basket";
    }
}


