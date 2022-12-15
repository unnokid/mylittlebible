package com.example.mylittlebible.domain.user.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Favorite {

    @EmbeddedId
    private FavoriteId favoriteId;

    private String content;


    public Favorite(FavoriteId favoriteId, String content) {
        this.favoriteId = favoriteId;
        this.content = content;
    }
}
