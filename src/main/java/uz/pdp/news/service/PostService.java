package uz.pdp.news.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uz.pdp.news.dto.request.PostRequest;
import uz.pdp.news.dto.view.PostView;

public interface PostService {

    Page<PostView> getAll(Pageable pageable);

    PostView getById(Long id);

    PostView create(PostRequest request);

    PostView update(PostRequest request, Long id);

    void deleteById(Long id);

}
