package com.example.mylittlebible.domain.board.entity;

import com.example.mylittlebible.domain.comment.entity.Comment;
import com.example.mylittlebible.domain.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.addUser(user);
    }

    public void addUser(User user) {
        if (Objects.isNull(user)) {
            throw new RuntimeException();
        }
        if (this.user != null) {
            this.user.getBoards().remove(this);
        }
        user.addBoard(this);
        this.user = user;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void changeTitle(String title){
        //빈칸인지 확인해야 함
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }




}
