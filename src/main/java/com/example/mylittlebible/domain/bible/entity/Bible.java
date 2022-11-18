package com.example.mylittlebible.domain.bible.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("bible")
@Getter
@Setter
public class Bible {

    private String _id;

    private String title;

    private Long chapter;

    private Long verse;

    private String content;

    public Bible(String title, Long chapter, Long verse, String content) {
        this.title = title;
        this.chapter = chapter;
        this.verse = verse;
        this.content = content;
    }
}
