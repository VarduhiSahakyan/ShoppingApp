package com.example.shoppingapplication.service;

import com.example.shoppingapplication.model.enums.Category;
import com.example.shoppingapplication.model.enums.Role;
import com.example.shoppingapplication.model.User;
import com.example.shoppingapplication.repository.UserRepository;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final String PASSWORD_EXPRESSION = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,15}$";

    public UserService(UserRepository userRepository ) {
        this.userRepository = userRepository;

    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void deleteByUsername(String username) {
        User user = findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        delete(user);
    }

    public void blockByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");

        user.setCategory(Category.BLOCKED);
        update(user);
    }

    public void unblockByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");

        user.setCategory(Category.UNBLOCKED);
        update(user);
    }

    public void singUp(String username, String password){
        User user = findByUsername(username);
        if (user != null) {
            throw new BadCredentialsException("Username already exists");
        }
        if (!password.matches(PASSWORD_EXPRESSION)) {
            throw new BadCredentialsException("Incorrect password password");
        }

        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.USER);
        user.setCategory(Category.UNBLOCKED);

        userRepository.save(user);
    }


}
