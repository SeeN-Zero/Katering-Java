package com.katering.kateringjava.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "articles")
public class Articles {

    private static final String NOT_EMPTY_MSG = "Tidak Boleh Kosong";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotNull(message = NOT_EMPTY_MSG)
    @NotBlank(message = NOT_EMPTY_MSG)
    @NotEmpty(message = NOT_EMPTY_MSG)
    @Column(nullable = false)
    private String title;

    @NotNull(message = NOT_EMPTY_MSG)
    @NotBlank(message = NOT_EMPTY_MSG)
    @NotEmpty(message = NOT_EMPTY_MSG)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String caption;

    @NotNull(message = NOT_EMPTY_MSG)
    @NotBlank(message = NOT_EMPTY_MSG)
    @NotEmpty(message = NOT_EMPTY_MSG)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @NotNull(message = NOT_EMPTY_MSG)
    @Column(nullable = false)
    private Date date;

    @NotNull(message = NOT_EMPTY_MSG)
    @Column(nullable = false)
    private String author;

    public Articles() {
        this.date = new Date(System.currentTimeMillis());
        this.author = "Username";
    }

    public Articles(Long id, String title, String caption, String content) {
        this.id = id;
        this.title = title;
        this.caption = caption;
        this.content = content;
        this.date = new Date(System.currentTimeMillis());
        this.author = "Username";
    }
}