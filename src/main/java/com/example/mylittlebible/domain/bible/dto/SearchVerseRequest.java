package com.example.mylittlebible.domain.bible.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchVerseRequest {

    private String book;
    private Long chapter;
    private Long verse;
}
