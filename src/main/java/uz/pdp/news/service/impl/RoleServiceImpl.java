package uz.pdp.news.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uz.pdp.news.dto.request.AuthoritiesRequest;
import uz.pdp.news.dto.request.RoleRequest;
import uz.pdp.news.dto.view.RoleView;
import uz.pdp.news.entity.Role;
import uz.pdp.news.enums.Authority;
import uz.pdp.news.exception.ResourceExistsException;
import uz.pdp.news.exception.ResourceNotFoundException;
import uz.pdp.news.mapper.RoleMapper;
import uz.pdp.news.repository.RoleRepository;
import uz.pdp.news.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final String resourceName = Role.class.getSimpleName();

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    private Role save(Role role) {
        return roleRepository.save(role);
    }
    private Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "id", id)
        );
    }
    private void checkToExistsByName(String name) {
        if (roleRepository.existsByName(name)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private void checkToExistsByName(String name, Long id) {
        if (roleRepository.existsByNameAndIdNot(name, id)) {
            throw new ResourceExistsException(resourceName, "name", name);
        }
    }
    private Set<Authority> mapStringsToAuthorities(Set<String> strings) {
        return strings.stream().map(a -> Authority.valueOf(a.toUpperCase())).collect(Collectors.toSet());
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(
            () -> new ResourceNotFoundException(resourceName, "name", name)
        );
    }

    @Override
    public void create(String name, Authority... authorities) {
        checkToExistsByName(name);
        
        Role role = new Role();

        role.setName(name);
        role.setAuthorities(Set.of(authorities));
        
        save(role);
    }

    @Override
    public Page<RoleView> getAll(Pageable pageable) {
        return roleRepository.findAll(pageable).map(roleMapper::mapToView);
    }

    @Override
    public RoleView getById(Long id) {
        return roleMapper.mapToView(findById(id));
    }
    @Override
    public RoleView create(RoleRequest request) {
        checkToExistsByName(request.getName());

        Role role = new Role();
        
        roleMapper.mapToEntity(role, request);

        return roleMapper.mapToView(save(role));
    }
    @Override
    public RoleView update(RoleRequest request, Long id) {
        checkToExistsByName(request.getName(), id);

        Role role = findById(id);
        
        roleMapper.mapToEntity(role, request);

        return roleMapper.mapToView(save(role));
    }
    @Override
    public void deleteById(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException(resourceName, "id", id);
        }
        roleRepository.deleteById(id);
    }
    @Override
    public RoleView addAuthorities(AuthoritiesRequest request, Long id) {
        Role role = findById(id);

        Set<Authority> authorities = mapStringsToAuthorities(request.getAuthorities());

        role.getAuthorities().addAll(authorities);

        return roleMapper.mapToView(role);
    }
    @Override
    public RoleView removeAuthorities(AuthoritiesRequest request, Long id) {
        Role role = findById(id);

        Set<Authority> authorities = mapStringsToAuthorities(request.getAuthorities());

        role.getAuthorities().removeAll(authorities);

        return roleMapper.mapToView(role);
    }
}
