package com.example.mylittlebible.domain.bible.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchSectionRequest {

    private String frontBook;
    private String backBook;
    private Long frontChapter;
    private Long backChapter;
    private Long frontVerse;
    private Long backVerse;
}
