package uz.pdp.news.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uz.pdp.news.dto.request.CommentRequest;
import uz.pdp.news.dto.view.CommentView;
import uz.pdp.news.entity.Comment;
import uz.pdp.news.exception.ResourceNotFoundException;
import uz.pdp.news.mapper.CommentMapper;
import uz.pdp.news.repository.CommentRepository;
import uz.pdp.news.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private final String resourceName = Comment.class.getSimpleName();

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMapper commentMapper;

    private Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
    private Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }

    @Override
    public Page<CommentView> getAll(Pageable pageable) {
        return commentRepository.findAll(pageable).map(commentMapper::mapToView);
    }

    @Override
    public Page<CommentView> getAllByPostId(Long postId, Pageable pageable) {
        return commentRepository.findAllByPostId(postId, pageable).map(commentMapper::mapToView);
    }

    @Override
    public CommentView getById(Long id) {
        return commentMapper.mapToView(findById(id));
    }

    @Override
    public CommentView create(CommentRequest request) {
        Comment comment = new Comment();

        commentMapper.mapToEntity(comment, request);

        return commentMapper.mapToView(save(comment));
    }

    @Override
    public CommentView update(CommentRequest request, Long id) {
        Comment comment = findById(id);

        commentMapper.mapToEntity(comment, request);

        return commentMapper.mapToView(save(comment));
    }

    @Override
    public void deleteById(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        commentRepository.deleteById(id);
    }
}
