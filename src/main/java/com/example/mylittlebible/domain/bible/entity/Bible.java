package com.example.mylittlebible.domain.bible.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Document("bible")
@Getter
public class Bible {

    private String _id;

    private String title;

    private Long chapter;

    private Long verse;

    private String content;

    public Bible(String title, Long chapter, Long verse, String content) {
        setTitle(title);
        setChapter(chapter);
        setVerse(verse);
        setContent(content);
    }

    private void setTitle(String title){
        checkBlank(title);
        this.title = title;
    }
    private void setChapter(Long chapter){
        checkNumber(chapter);
        this.chapter = chapter;
    }
    private void setVerse(Long verse){
        checkNumber(verse);
        this.verse = verse;
    }
    private void setContent(String content){
        checkBlank(content);
        this.content = content;
    }

    private void checkBlank(String s){
        if(isBlank(s)){
            throw new RuntimeException();
        }
    }
    private void checkNumber(Long number){
        if(number<=0){
            throw new RuntimeException();
        }
    }
}
