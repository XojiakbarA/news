package uz.pdp.news.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uz.pdp.news.dto.request.CommentRequest;
import uz.pdp.news.dto.view.CommentView;
import uz.pdp.news.entity.Comment;
import uz.pdp.news.service.PostService;

@Component
public class CommentMapper {
    @Autowired
    private UserAuditorMapper userAuditorMapper;
    @Autowired
    private PostService postService;

    public CommentView mapToView(Comment comment) {
        if (comment == null) return null;
        return CommentView.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt())
                    .updatedAt(comment.getUpdatedAt())
                    .createdBy(userAuditorMapper.mapToAuditorView(comment.getCreatedBy()))
                    .updatedBy(userAuditorMapper.mapToAuditorView(comment.getUpdatedBy()))
                    .build();
    }

    public void mapToEntity(Comment comment, CommentRequest request) {
        if (request.getPostId() != null) {
            comment.setPost(postService.findById(request.getPostId()));
        }
        if (request.getContent() != null && !request.getContent().isBlank()) {
            comment.setContent(request.getContent());
        }
    }
}
