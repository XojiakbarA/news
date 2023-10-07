package uz.pdp.news.service;

import uz.pdp.news.dto.request.LoginRequest;
import uz.pdp.news.dto.request.RegisterRequest;

public interface AuthService {
    String login(LoginRequest request);

    String register(RegisterRequest request);
}
