package com.example.mylittlebible.domain.bible.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchVerseSectionRequest {

    String book;
    Long chapter;
    Long frontVerse;
    Long backVerse;
}