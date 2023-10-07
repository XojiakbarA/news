package uz.pdp.news.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uz.pdp.news.dto.request.PostRequest;
import uz.pdp.news.dto.view.PostView;
import uz.pdp.news.entity.Post;
import uz.pdp.news.exception.ResourceNotFoundException;
import uz.pdp.news.mapper.PostMapper;
import uz.pdp.news.repository.PostRepository;
import uz.pdp.news.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    private final String resourceName = Post.class.getSimpleName();

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostMapper postMapper;

    private Post save(Post post) {
        return postRepository.save(post);
    }
    private Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }

    @Override
    public Page<PostView> getAll(Pageable pageable) {
        return postRepository.findAll(pageable).map(postMapper::mapToView);
    }

    @Override
    public PostView getById(Long id) {
        return postMapper.mapToView(findById(id));
    }

    @Override
    public PostView create(PostRequest request) {
        Post post = new Post();

        postMapper.mapToEntity(post, request);

        return postMapper.mapToView(save(post));
    }

    @Override
    public PostView update(PostRequest request, Long id) {
        Post post = findById(id);

        postMapper.mapToEntity(post, request);

        return postMapper.mapToView(save(post));
    }

    @Override
    public void deleteById(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        postRepository.deleteById(id);
    }
    
}
