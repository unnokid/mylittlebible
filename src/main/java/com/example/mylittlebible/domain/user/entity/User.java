package com.example.mylittlebible.domain.user.entity;

import com.example.mylittlebible.domain.board.entity.Board;
import com.example.mylittlebible.domain.comment.entity.Comment;
import com.example.mylittlebible.domain.user.util.PasswordEncryptor;
import com.example.mylittlebible.domain.user.util.Validation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Where(clause = "is_deleted = false")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    //즐겨찾기한 성경구절 모음
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> bookmark = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    //최근 마지막으로 본 구절
    private FavoriteId recent;

    public User(String email, String password, String name, String birth,
        Gender gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = LocalDate.parse(birth);
        this.gender = gender;
        bookmark = new ArrayList<>();
        recent = new FavoriteId("창세기",1L,1L);
    }

    private void changeRecent(String book, Long chapter, Long verse){
        recent = new FavoriteId(book,chapter,verse);
    }

    public void addBookmark(Favorite favorite) {
        this.bookmark.add(favorite);
    }

    public boolean matchPassword(String password) {
        return PasswordEncryptor.isMatch(password, this.password);
    }

    public void addBoard(Board board){
        this.boards.add(board);
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

}
