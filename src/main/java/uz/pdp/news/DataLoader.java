package uz.pdp.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import uz.pdp.news.enums.AuthorityType;
import uz.pdp.news.service.RoleService;
import uz.pdp.news.service.UserService;

import static uz.pdp.news.enums.AuthorityType.*;
import static uz.pdp.news.enums.RoleType.*;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        roleService.create(ADMIN.name(), AuthorityType.values());
        roleService.create(MODERATOR.name(), GET_POSTS, GET_POST, CREATE_POST, UPDATE_POST, GET_COMMENTS, GET_COMMENT, CREATE_COMMENT);
        roleService.create(USER.name(), GET_POSTS, GET_POST, GET_COMMENTS, GET_COMMENT, CREATE_COMMENT);

        userService.create("firstNameAdmin1", "lastNameAdmin1", "admin1", "123", ADMIN.name());
        userService.create("firstNameModerator1", "lastNameModerator1", "moderator1", "123", MODERATOR.name());
        userService.create("firstNameUser1", "lastNameUser1", "user1", "123", USER.name());
    }
    
}
