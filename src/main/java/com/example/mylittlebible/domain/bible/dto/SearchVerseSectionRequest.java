package com.example.mylittlebible.domain.bible.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchVerseSectionRequest {

    private String book;
    private Long chapter;
    private Long frontVerse;
    private Long backVerse;
}
