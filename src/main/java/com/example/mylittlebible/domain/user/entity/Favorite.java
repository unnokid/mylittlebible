package com.example.mylittlebible.domain.user.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
