package Controllers;


import DAO.*;
//import config.SecurityConfig;
import models.User;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {
    @Autowired
    SessionFactory factory;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String findAll(ModelMap model) {
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        model.addAttribute("UsersList", users.findAll());
        return "users";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
        public String hello() {
        return "index";
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.GET)
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

    /*@RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String user_add(ModelMap model) {
        return "users/add";
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    public String edit_user(@RequestParam Integer id,
                              ModelMap model) {
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        User user = users.getById(id);
        model.addAttribute("id", id);
        model.addAttribute("surname", user.getSurname());
        model.addAttribute("first_name", user.getFirst_name());
        model.addAttribute("patronymic", user.getPatronymic());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("phone_number", user.getPhone_number());
        model.addAttribute("e_mail", user.getE_mail());
        return "users/edit";
    }

    String generate_hash(String Password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(Password.getBytes());
        byte[] result = digest.digest();
        return DatatypeConverter.printBase64Binary(result);
    }

    @RequestMapping(value = "/users/edit_done", method = RequestMethod.POST)
    public String edit_done(Integer id,
                            @RequestParam String surname, @RequestParam String first_name,
                            @RequestParam String patronymic, @RequestParam String address,
                            @RequestParam String phone_number, @RequestParam String e_mail,
                            @RequestParam String password, ModelMap model) throws NoSuchAlgorithmException {
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        boolean number = false;
        boolean email = false;
        if (first_name.equals("") || phone_number.equals("") || e_mail.equals("")  || password.equals("") ||
                (number = users.find(null, null, null, null, phone_number, null, null).size() > 0 )||
                (email = users.find(null, null, null, null, null, e_mail, null).size() > 0 )
        ) {
            if(first_name.equals("") || phone_number.equals("") || e_mail.equals(""))
                model.addAttribute("error", true);
            if(number) {
                model.addAttribute("error_number", true);
            }
            if (email) {
                model.addAttribute("error_email", true);
            }
            model.addAttribute("surname", surname);
            model.addAttribute("first_name", first_name);
            model.addAttribute("patronymic", patronymic);
            model.addAttribute("address", address);
            model.addAttribute("phone_number", phone_number);
            model.addAttribute("e_mail", e_mail);

            if (id != null) {
                model.addAttribute("id", id);
                return "users/edit";
            } else {
                return "users/add";
            }
        }

        if (id != null) {
            session.getTransaction().begin();
            User user = users.getById(id);
            user.setSurname(surname);
            user.setFirst_name(first_name);
            user.setPatronymic(patronymic);
            user.setAddress(address);
            user.setPhone_number(phone_number);
            user.setE_mail(e_mail);
            users.update(user);
            session.getTransaction().commit();
        } else {
            String password_hash = generate_hash(password);
            User user = new User(surname, first_name, patronymic, address, phone_number, e_mail, password_hash, false);
            session.getTransaction().begin();
            Integer id_user = (Integer) session.save(user);
            session.getTransaction().commit();
        }

        model.addAttribute("UsersList", users.findAll());
        return "users";
    }*/

    @RequestMapping(value = "/users/rm", method = RequestMethod.POST)
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

    @RequestMapping(value = "/users/detailed", method = RequestMethod.GET)
    public String detailed_user(@RequestParam Integer id,
                                ModelMap model) {
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        User user = users.getById(id);
        model.addAttribute("id", id);
        model.addAttribute("surname", user.getSurname());
        model.addAttribute("first_name", user.getFirst_name());
        model.addAttribute("patronymic", user.getPatronymic());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("phone_number", user.getPhone_number());
        model.addAttribute("e_mail", user.getE_mail());
        model.addAttribute("OrdersList", user.getOrders());
        return "users/detailed";
    }

    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "users/login";
    }*/

    /*@RequestMapping(value = "/login/check", method = RequestMethod.GET)
    public String login_check(@RequestParam String e_mail, @RequestParam String password,
                        ModelMap model) throws NoSuchAlgorithmException {
        if (password.equals("") || e_mail.equals("")) {
            model.addAttribute("error", true);
            model.addAttribute("e_mail", e_mail);
            model.addAttribute("password", password);
            return "users/login";
        }
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        List<User> list = users.find(null, null, null, null, null, e_mail, null);
        if (list.size() == 0) {
            model.addAttribute("error_email", true);
            model.addAttribute("e_mail", e_mail);
            model.addAttribute("password", password);
            return "users/login";
        }
        if(list.get(0).getPassword_hash() != generate_hash(password)) {
            model.addAttribute("error_password", true);
            model.addAttribute("e_mail", e_mail);
            model.addAttribute("password", password);
            return "users/login";
        } else {
            model.addAttribute("id_user", list.get(0).getId_user());
            model.addAttribute("admin", list.get(0).getAdmin());
            return "books";
        }
    }*/

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register_get() {
        return "/register";
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
            return "/register";
        }
        //user.setPassword_hash(SecurityConfig.passwordEncoder().encode(user.getPassword_hash()));
        users.save(user);
        return "redirect:/login";
    }

    /*@RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error, ModelMap model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        return "login";
    }*/
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request, ModelMap model) {
        if (request.getUserPrincipal() != null) {
            return "redirect:/books";
        }
        return "login";
    }
}


