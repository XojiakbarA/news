package uz.pdp.news.dto.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuditorView {
    private Long id;
    private String username;
    private String roleName;
}
