package uz.pdp.news.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uz.pdp.news.dto.request.AuthoritiesRequest;
import uz.pdp.news.dto.request.RoleRequest;
import uz.pdp.news.dto.view.RoleView;
import uz.pdp.news.entity.Role;
import uz.pdp.news.enums.Authority;

public interface RoleService {
    void create(String name, Authority ...authorities);

    Role findByName(String name);

    Role findById(Long id);

    Page<RoleView> getAll(Pageable pageable);

    RoleView getById(Long id);

    RoleView create(RoleRequest request);

    RoleView update(RoleRequest request, Long id);

    void deleteById(Long id);

    RoleView addAuthorities(AuthoritiesRequest request, Long id);
    
    RoleView removeAuthorities(AuthoritiesRequest request, Long id);
}
