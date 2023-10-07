package uz.pdp.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uz.pdp.news.dto.request.AuthoritiesRequest;
import uz.pdp.news.dto.request.RoleRequest;
import uz.pdp.news.dto.response.Response;
import uz.pdp.news.dto.view.RoleView;
import uz.pdp.news.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).GET_ROLES)")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<RoleView> roles = roleService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), roles);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).GET_ROLE)")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        RoleView role = roleService.getById(id);

        return new Response(HttpStatus.OK.name(), role);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).CREATE_ROLE)")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody RoleRequest request) {
        RoleView role = roleService.create(request);

        return new Response(HttpStatus.CREATED.name(), role);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).UPDATE_ROLE)")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody RoleRequest request, @PathVariable Long id) {
        RoleView role = roleService.update(request, id);

        return new Response(HttpStatus.OK.name(), role);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).ADD_AUTHORITIES)")
    @PutMapping("/{id}/authorities")
    @ResponseStatus(HttpStatus.OK)
    public Response addAuthorities(@Valid @RequestBody AuthoritiesRequest request, @PathVariable Long id) {
        RoleView role = roleService.addAuthorities(request, id);

        return new Response(HttpStatus.OK.name(), role);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).REMOVE_AUTHORITIES)")
    @DeleteMapping("/{id}/authorities")
    @ResponseStatus(HttpStatus.OK)
    public Response removeAuthorities(@Valid @RequestBody AuthoritiesRequest request, @PathVariable Long id) {
        RoleView role = roleService.removeAuthorities(request, id);

        return new Response(HttpStatus.OK.name(), role);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).DELETE_ROLE)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        roleService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
