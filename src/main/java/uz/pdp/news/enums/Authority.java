package uz.pdp.news.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    GET_USERS, GET_USER, CREATE_USER, UPDATE_USER, DELETE_USER,
    GET_POSTS, GET_POST, CREATE_POST, UPDATE_POST, DELETE_POST,
    GET_COMMENTS, GET_COMMENT, CREATE_COMMENT, UPDATE_COMMENT, DELETE_COMMENT;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
