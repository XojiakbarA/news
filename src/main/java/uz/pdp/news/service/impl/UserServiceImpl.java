package uz.pdp.news.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uz.pdp.news.dto.request.UserRequest;
import uz.pdp.news.dto.view.UserView;
import uz.pdp.news.entity.User;
import uz.pdp.news.exception.ResourceExistsException;
import uz.pdp.news.exception.ResourceNotFoundException;
import uz.pdp.news.mapper.UserMapper;
import uz.pdp.news.repository.UserRepository;
import uz.pdp.news.service.RoleService;
import uz.pdp.news.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final String resourceName = User.class.getSimpleName();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserMapper userMapper;

    private User save(User user) {
        return userRepository.save(user);
    }
    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }

    @Override
    public void create(String firstName, String lastName, String username, String password, String roleName) {
        if (userRepository.existsByUsername(username)) {
            throw new ResourceExistsException(resourceName, "username", username);
        }
        
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleService.findByName(roleName));

        save(user);
    }

    @Override
    public Page<UserView> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::mapToView);
    }

    @Override
    public UserView getById(Long id) {
        return userMapper.mapToView(findById(id));
    }
    private void checkToExistsByUsername(String name) {
        if (userRepository.existsByUsername(name)) {
            throw new ResourceExistsException(resourceName, "username", name);
        }
    }
    private void checkToExistsByUsername(String name, Long id) {
        if (userRepository.existsByUsernameAndIdNot(name, id)) {
            throw new ResourceExistsException(resourceName, "username", name);
        }
    }

    @Override
    public UserView create(UserRequest request) {
        checkToExistsByUsername(request.getUsername());

        User user = new User();

        userMapper.mapToEntity(user, request);

        return userMapper.mapToView(save(user));
    }

    @Override
    public UserView update(UserRequest request, Long id) {
        checkToExistsByUsername(request.getUsername(), id);

        User user = findById(id);

        userMapper.mapToEntity(user, request);

        return userMapper.mapToView(save(user));
    }

    @Override
    public void lock(Long id) {
        User user = findById(id);

        user.setAccountNonLocked(false);

        save(user);
    }

    @Override
    public void unlock(Long id) {
        User user = findById(id);

        user.setAccountNonLocked(true);

        save(user);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        userRepository.deleteById(id);
    }
    
}
