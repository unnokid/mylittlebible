package com.example.mylittlebible.domain.user.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class FavoriteId implements Serializable {

    private String book;

    private Long chapter;

    private Long verse;


    public FavoriteId(String book, Long chapter, Long verse) {
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FavoriteId that = (FavoriteId) o;
        return Objects.equals(book, that.book) && Objects
            .equals(chapter, that.chapter) && Objects.equals(verse, that.verse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, chapter, verse);
    }
}
