package uz.pdp.news.enums;

import org.springframework.security.core.GrantedAuthority;

public enum AuthorityType implements GrantedAuthority {
    GET_AUTHORITIES,
    GET_USERS, GET_USER, CREATE_USER, UPDATE_USER, DELETE_USER, LOCK_USER, UNLOCK_USER,
    GET_ROLES, GET_ROLE, CREATE_ROLE, UPDATE_ROLE, DELETE_ROLE, ADD_AUTHORITIES, REMOVE_AUTHORITIES,
    GET_POSTS, GET_POST, CREATE_POST, UPDATE_POST, DELETE_POST,
    GET_COMMENTS, GET_COMMENT, CREATE_COMMENT, UPDATE_COMMENT, DELETE_COMMENT;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
