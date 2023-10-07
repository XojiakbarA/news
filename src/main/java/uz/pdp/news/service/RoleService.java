package uz.pdp.news.service;

import uz.pdp.news.entity.Role;
import uz.pdp.news.enums.Authority;

public interface RoleService {
    Role findByName(String name);
    void create(String name, Authority ...authorities);
}
