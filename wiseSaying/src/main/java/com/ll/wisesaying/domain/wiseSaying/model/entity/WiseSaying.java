package com.ll.wisesaying.domain.wiseSaying.model.entity;

public class WiseSaying {

    private long id;
    private String content;
    private String author;

    public WiseSaying(long id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public WiseSaying(String content, String author) {
        this(0, content, author);
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

}
