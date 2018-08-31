package com.zti.photoblog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Account entity
 */
@Entity
@ToString
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    @Column(nullable = true)
    private String name;

    @Getter
    @Setter
    @Column(nullable = true)
    private String surname;

    @Getter
    @Setter
    @Column(nullable = true)
    private Character sex;

    @Getter
    @Setter
    @Column(nullable = true)
    private Date born;

    @Getter
    @Setter
    @Column(nullable = true)
    private String bio;

    @Getter
    @CreationTimestamp
    private Date joinedOn;

    @Getter
    @Setter
    private byte[] avatar;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @Getter
    @Setter
    @JsonIgnore
    private List<Role> roles;

    public Account(String username, String password, String name, String surname, Character sex, Date born, String bio, byte[] avatar, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.born = born;
        this.bio = bio;
        this.avatar = avatar;
        this.roles = roles;
    }
}
