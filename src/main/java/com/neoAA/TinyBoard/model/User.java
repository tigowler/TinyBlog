package com.neoAA.TinyBoard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Size(min = 2, max = 8, message = "❌ User name must be between {min} and {max} characters long :(")
    private String username;

//    @Size(min = 4, max = 11, message = "❌ Password must be between {min} and {max} characters long :(")
    private String password;
    private Boolean enabled;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> post = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<GuestBook> guestBooks = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "user_post",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> postsLoved = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_guest_book",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_book_id")
    )
    private List<GuestBook> guestBooksLoved = new ArrayList<>();
}
