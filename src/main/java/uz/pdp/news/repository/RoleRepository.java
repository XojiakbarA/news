package uz.pdp.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.news.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
