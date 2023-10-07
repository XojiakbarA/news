package uz.pdp.news.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "comments")
public class Comment extends BaseEntity {
    @Column(nullable = false)
    private String content;

    @ManyToOne
    private Post post;
}
