package uz.pdp.news.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uz.pdp.news.dto.response.Response;
import uz.pdp.news.enums.AuthorityType;

@RestController
@RequestMapping("/authorities")
public class AuthorityController {
    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).GET_AUTHORITIES)")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll() {
        return new Response(HttpStatus.OK.name(), Set.of(AuthorityType.values()));
    }
}
