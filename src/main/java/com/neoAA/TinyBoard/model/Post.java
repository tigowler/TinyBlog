package com.neoAA.TinyBoard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 50, message = "❌ The title must be between {min} and {max} characters long :(")
    private String title;
    @Size(min = 2, max = 50, message = "❌ The subtitle must be between {min} and {max} characters long :(")
    private String subtitle;

    @Size(min = 15, message = "❌ The content must be at least {min} characters long :(")
    private String content;
    @NotEmpty(message = "❌ Please select category!")
    private String category;
    @NotEmpty(message = "❌ Please select year!")
    private String year;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;
}
