package ru.guwfa.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.guwfa.auction.Repository.UserRepository;
import ru.guwfa.auction.domain.Role;
import ru.guwfa.auction.domain.User;

import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ResourceBundle resourceBundle;


    public UserService() {
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public void saveUser(User User, Map<String, String> form, Long balance) {
        User.setBalance(balance);

        //помещаем в Set все существующие роли
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        //очистим список ролей пользователя
        User.getRoles().clear();

        //установим роли, которые были получены из формы
        for (String key : form.keySet())
            if (roles.contains(key)) {
                User.getRoles().add(Role.valueOf(key));
            }

        UserRepository.save(User);
    }
}