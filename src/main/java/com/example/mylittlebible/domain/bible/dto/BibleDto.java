package com.example.mylittlebible.domain.bible.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BibleDto {

    private String title;
    private Long chapter;
    private Long verse;
    private String content;
}
