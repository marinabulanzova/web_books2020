package utils;


import DAO.UserDAO;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class UserValidator implements Validator {

    @Autowired
    private SessionFactory factory;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        List<User> list = users.find(null, null, null, null, null, user.getE_mail(), null);
        if (list.size() != 0) {
            errors.rejectValue(
                    "email", "", "This email is already in use"
            );
        }
    }

}
