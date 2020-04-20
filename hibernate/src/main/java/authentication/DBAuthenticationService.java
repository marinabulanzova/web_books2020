package authentication;

import DAO.UserDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DBAuthenticationService implements UserDetailsService {
    @Autowired
    private SessionFactory factory;

    @Override
    public UserDetails loadUserByUsername(String e_mail) throws UsernameNotFoundException {
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        List<models.User> list = users.find(null, null, null, null, null, e_mail, null);
        if (list.size() == 0) {
            throw new UsernameNotFoundException("Пользователь с таким e_mail не найден");
        }
        models.User user = list.get(0);
        Boolean isAdmin = user.getAdmin();
        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority =
                new SimpleGrantedAuthority("ROLE_" + ((isAdmin) ? "ADMIN" : "USER"));
        grantList.add(authority);

        return new User(user.getE_mail(),
                user.getPassword_hash(),
                grantList);
    }
}
