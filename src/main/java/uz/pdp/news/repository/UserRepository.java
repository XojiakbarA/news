package uz.pdp.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.news.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}