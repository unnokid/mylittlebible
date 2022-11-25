package com.example.mylittlebible.domain.bible.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchChapterRequest {

    private String book;
    private Long Chapter;
}
