package uz.pdp.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.news.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
}
