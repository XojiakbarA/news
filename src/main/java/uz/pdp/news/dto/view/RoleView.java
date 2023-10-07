package uz.pdp.news.dto.view;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Builder;
import lombok.Data;
import uz.pdp.news.enums.Authority;

@Data
@Builder
public class RoleView {
    private Long id;
    private String name;
    private Set<Authority> authorities;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserAuditorView createdBy;
    private UserAuditorView updatedBy;
}
