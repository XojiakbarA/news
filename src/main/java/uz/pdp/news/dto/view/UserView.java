package uz.pdp.news.dto.view;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserView {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private RoleView role;
    private Boolean accountNonLocked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserAuditorView createdBy;
    private UserAuditorView updatedBy;
}
