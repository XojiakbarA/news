package uz.pdp.news.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uz.pdp.news.dto.request.PostRequest;
import uz.pdp.news.dto.view.PostView;
import uz.pdp.news.entity.Post;

public interface PostService {

    Page<PostView> getAll(Pageable pageable);

    PostView getById(Long id);

    PostView create(PostRequest request);

    PostView update(PostRequest request, Long id);

    Post findById(Long id);

    void deleteById(Long id);

}
