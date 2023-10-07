package uz.pdp.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uz.pdp.news.dto.request.LoginRequest;
import uz.pdp.news.dto.request.RegisterRequest;
import uz.pdp.news.dto.response.AuthResponse;
import uz.pdp.news.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);

        return new AuthResponse(token, HttpStatus.OK.name());
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        String token = authService.register(request);

        return new AuthResponse(token, HttpStatus.OK.name());
    }
}
