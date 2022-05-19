package ru.guwfa.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.guwfa.auction.Repository.UserRepository;
import ru.guwfa.auction.domain.Role;
import ru.guwfa.auction.domain.User;
import ru.guwfa.auction.domain.dataTransferObject.UserDataTransferObject;

import java.util.*;
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

    public void addUser(UserDataTransferObject UserDataTransferObject) {
        // создаем пользователя
        User User = new User();

        //перепишем значения с UserDataTransferObject
        User.setUsername(UserDataTransferObject.getUsername());
        User.setPassword(UserDataTransferObject.getPassword());
        User.setEmail(UserDataTransferObject.getEmail());

        User.setActive(false);
        User.setRoles(Collections.singleton(Role.User));
        User.setBalance(0L);
        User.setActivationCode(UUID.randomUUID().toString());

        //шифруем пароль
        User.setPassword(passwordEncoder.encode(User.getPassword()));
        UserRepository.save(User);

        // отправляем пользователю код для подтверждения аккаунта
        sendActivationCode(User);
    }
    private void sendActivationCode(User User) {
        String message = String.format(
                resourceBundle.getString("message.toNewUser"),
                User.getActivationCode()
        );

        String subject = resourceBundle.getString("subject.toNewUser");

        mailSender.send(User.getEmail(), subject, message);
    }

    public boolean activateUser(String code) {
        User User = UserRepository.findByActivationCode(code);

        //если пользователь не найден, то выводим false
        if (User == null)
            return false;

        //помечаем, что код активирован
        User.setActivationCode(null);
        User.setActive(true);

        UserRepository.save(User);
        return true;
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
    public void saveUser(User User) {
        UserRepository.save(User);
    }

    public void updateProfile(User User, UserDataTransferObject UserFromForm) {

        boolean isEmailChanged = UserFromForm.getNewEmail() != null && !UserFromForm.getNewEmail().equals(User.getEmail());

        if (isEmailChanged) {
            User.setEmail(UserFromForm.getNewEmail());
            User.setActivationCode(UUID.randomUUID().toString());

            User.setActive(false); // станет активен, когда подтвердит новую почту

            sendActivationCode(User);
        }

        boolean isPasswordChanged = UserFromForm.getNewPassword() != null && !UserFromForm.getNewPassword().equals(User.getPassword());

        if (isPasswordChanged)
            User.setPassword(passwordEncoder.encode(UserFromForm.getNewPassword()));

        if (isEmailChanged || isPasswordChanged)
            UserRepository.save(User);
    }

    public Optional<User> getById(Long id) {
        return UserRepository.findById(id);
    }

    public boolean isExistsByEmail(String UserEmail) {
        return UserRepository.findByEmail(UserEmail) != null;
    }

    public boolean isExistsByUsername(String Username) {
        return UserRepository.findByUsername(Username) != null;
    }

    public Page<User> getAll(Pageable pageable) {
        return UserRepository.findAll(pageable);
    }
}