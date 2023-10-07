package uz.pdp.news.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uz.pdp.news.dto.request.UserRequest;
import uz.pdp.news.dto.view.UserView;

public interface UserService {
    void create(String firstName, String lastName, String username, String password, String roleName);

    Page<UserView> getAll(Pageable pageable);

    UserView getById(Long id);

    UserView create(UserRequest request);

    UserView update(UserRequest request, Long id);

    void lock(Long id);

    void unlock(Long id);

    void deleteById(Long id);
}
