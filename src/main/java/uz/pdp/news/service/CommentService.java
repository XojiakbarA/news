package uz.pdp.news.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uz.pdp.news.dto.request.CommentRequest;
import uz.pdp.news.dto.view.CommentView;

public interface CommentService {

    Page<CommentView> getAll(Pageable pageable);

    Page<CommentView> getAllByPostId(Long postId, Pageable pageable);

    CommentView getById(Long id);

    CommentView create(CommentRequest request);

    CommentView update(CommentRequest request, Long id);

    void deleteById(Long id);

}
