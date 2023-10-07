package uz.pdp.news.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import uz.pdp.news.enums.Authority;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "roles")
public class Role extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Set<Authority> authorities;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<User> users;
}
