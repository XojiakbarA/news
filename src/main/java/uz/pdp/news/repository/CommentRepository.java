package uz.pdp.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.news.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
