package com.example.mybatisspringbootstarter.repository;

import java.time.LocalDateTime;

public class UpdateBoard {
    private final String id;
    private final String title;
    private final String content;
    private final LocalDateTime updatedAt;

    private UpdateBoard(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public static UpdateBoard of(String id, String title, String content) {
        return new UpdateBoard(id, title, content);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
