package uz.pdp.news.mapper;

import org.springframework.stereotype.Component;

import uz.pdp.news.dto.view.UserAuditorView;
import uz.pdp.news.entity.User;

@Component
public class UserMapper {
    public UserAuditorView mapToAuditorView(User user) {
        if (user == null) return null;
        return UserAuditorView.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .roleName(user.getRole().getName())
                    .build();
    }
}
