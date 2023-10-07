package uz.pdp.news.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import uz.pdp.news.dto.request.LoginRequest;
import uz.pdp.news.dto.request.RegisterRequest;
import uz.pdp.news.enums.RoleType;
import uz.pdp.news.security.JWTProvider;
import uz.pdp.news.service.AuthService;
import uz.pdp.news.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private UserService userService;

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return jwtProvider.generateToken(Map.of("roles", userDetails.getAuthorities()), userDetails);
    }

    @Override
    public String register(RegisterRequest request) {
        userService.create(request.getFirstName(), request.getLastName(), request.getUsername(), request.getPassword(), RoleType.USER.name());

        LoginRequest loginRequest = new LoginRequest(request.getUsername(), request.getPassword());

        return login(loginRequest);
    }
    
}
