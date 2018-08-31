package com.zti.photoblog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Post entity
 */
@Entity
@ToString
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @ManyToOne
    @Getter
    @Setter
    private Account publishedBy;

    @Getter
    @Setter
    private byte[] content;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    @Getter
    private Date updatedOn;

    public Post(Account publishedBy, String title, String description, byte[] content) {
        this.publishedBy = publishedBy;
        this.content = content;
        this.title = title;
        this.description = description;
    }
}
