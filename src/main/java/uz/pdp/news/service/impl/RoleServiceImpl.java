package uz.pdp.news.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.news.entity.Role;
import uz.pdp.news.enums.Authority;
import uz.pdp.news.exception.ResourceNotFoundException;
import uz.pdp.news.repository.RoleRepository;
import uz.pdp.news.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    private Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(
            () -> new ResourceNotFoundException(Role.class.getSimpleName(), "name", name)
        );
    }

    @Override
    public void create(String name, Authority... authorities) {
        Role role = new Role();

        role.setName(name);
        role.setAuthorities(Set.of(authorities));
        
        save(role);
    }
    
}
