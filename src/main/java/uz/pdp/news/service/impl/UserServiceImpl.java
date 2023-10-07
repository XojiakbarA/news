package uz.pdp.news.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uz.pdp.news.entity.User;
import uz.pdp.news.exception.ResourceExistsException;
import uz.pdp.news.repository.UserRepository;
import uz.pdp.news.service.RoleService;
import uz.pdp.news.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    private User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void create(String firstName, String lastName, String username, String password, String roleName) {
        if (userRepository.existsByUsername(username)) {
            throw new ResourceExistsException(User.class.getSimpleName(), "username", username);
        }
        
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleService.findByName(roleName));

        save(user);
    }
    
}
