package com.zti.photoblog.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Comment entity
 */
@Entity
@ToString
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @ManyToOne
    @Getter
    @Setter
    private Account publishedBy;

    @ManyToOne
    @Getter
    @Setter
    private Post on;

    @Getter
    @Setter
    @Lob
    private String text;

    @Getter
    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    @Getter
    private Date updatedOn;
}