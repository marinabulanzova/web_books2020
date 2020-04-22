package security;

import DAO.UserDAO;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private SessionFactory factory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String e_mail= authentication.getName();
        Session session = factory.openSession();
        UserDAO users = new UserDAO(session);
        List<User> list = users.find(null, null, null, null, null, e_mail, null);
        if (list.size() == 0) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = list.get(0);
        String password = authentication.getCredentials().toString();

        if (!passwordEncoder.matches(password, user.getPassword_hash())) {
            throw new BadCredentialsException("Bad credentials");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority =
                new SimpleGrantedAuthority((user.getAdmin()) ? "ADMIN" : "USER");
        authorities.add(authority);
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
