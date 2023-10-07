package uz.pdp.news.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uz.pdp.news.dto.request.PostRequest;
import uz.pdp.news.dto.view.PostView;
import uz.pdp.news.entity.Post;

@Component
public class PostMapper {
    @Autowired
    private UserAuditorMapper userAuditorMapper;

    public PostView mapToView(Post post) {
        if (post == null) return null;
        return PostView.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdatedAt())
                    .createdBy(userAuditorMapper.mapToAuditorView(post.getCreatedBy()))
                    .updatedBy(userAuditorMapper.mapToAuditorView(post.getUpdatedBy()))
                    .build();
    }

    public void mapToEntity(Post post, PostRequest request) {
        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            post.setTitle(request.getTitle());
        }
        if (request.getContent() != null && !request.getContent().isBlank()) {
            post.setContent(request.getContent());
        }
    }
}
