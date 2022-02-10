package com.neoAA.TinyBoard.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 30)
    private String title;
    @Size(min = 2, max = 30)
    private String subtitle;
    @Size(min = 2, max = 30)
    private String info;

    @Size(min = 15)
    private String content;
    @NotEmpty
    private String category;
    @NotEmpty
    private String year;
}
