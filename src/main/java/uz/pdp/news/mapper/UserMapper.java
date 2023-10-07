package uz.pdp.news.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import uz.pdp.news.dto.request.UserRequest;
import uz.pdp.news.dto.view.UserView;
import uz.pdp.news.entity.User;
import uz.pdp.news.service.RoleService;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserAuditorMapper userAuditorMapper;
    @Autowired
    private RoleService roleService;

    public UserView mapToView(User user) {
        if (user == null) return null;
        return UserView.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .username(user.getUsername())
                    .role(roleMapper.mapToView(user.getRole()))
                    .accountNonLocked(user.getAccountNonLocked())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .createdBy(userAuditorMapper.mapToAuditorView(user.getCreatedBy()))
                    .updatedBy(userAuditorMapper.mapToAuditorView(user.getUpdatedBy()))
                    .build();
    }

    public void mapToEntity(User user, UserRequest request) {
        if (request.getRoleId() != null) {
            user.setRole(roleService.findById(request.getRoleId()));
        }
        if (request.getFirstName() != null && !request.getFirstName().isBlank()) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null && !request.getLastName().isBlank()) {
            user.setLastName(request.getLastName());
        }
        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            user.setUsername(request.getUsername());
        }
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
    }
}
