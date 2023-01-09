package com.example.mylittlebible.domain.user.dto;

import com.example.mylittlebible.domain.user.entity.Favorite;
import com.example.mylittlebible.domain.user.entity.Gender;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MyPageResponse {
    private Long id;
    private String email;
    private String name;
    private Gender gender;
    private String birth;
    private List<Favorite> bookmarkList;
    //TODO: 최근 기록 추가 필요
}
