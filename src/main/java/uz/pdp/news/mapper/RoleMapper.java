package uz.pdp.news.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uz.pdp.news.dto.request.RoleRequest;
import uz.pdp.news.dto.view.RoleView;
import uz.pdp.news.entity.Role;

@Component
public class RoleMapper {
    @Autowired
    private UserMapper userMapper;

    public RoleView mapToView(Role role) {
        if (role == null) return null;
        return RoleView.builder()
                    .id(role.getId())
                    .name(role.getName())
                    .authorities(role.getAuthorities())
                    .createdAt(role.getCreatedAt())
                    .updatedAt(role.getUpdatedAt())
                    .createdBy(userMapper.mapToAuditorView(role.getCreatedBy()))
                    .updatedBy(userMapper.mapToAuditorView(role.getUpdatedBy()))
                    .build();
    }

    public void mapToEntity(Role role, RoleRequest request) {
        if (request.getName() != null && !request.getName().isBlank()) {
            role.setName(request.getName());
        }
    }
}
