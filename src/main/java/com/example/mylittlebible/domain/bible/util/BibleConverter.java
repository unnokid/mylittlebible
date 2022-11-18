package com.example.mylittlebible.domain.bible.util;

import com.example.mylittlebible.domain.bible.dto.BibleDto;
import com.example.mylittlebible.domain.bible.dto.SearchChapterResponse;
import com.example.mylittlebible.domain.bible.dto.SearchTitleResponse;
import com.example.mylittlebible.domain.bible.entity.Bible;
import java.util.List;
import java.util.stream.Collectors;

public class BibleConverter {

    public static BibleDto verseFromBible(
        String title,
        Long chapter,
        Long verse,
        String content
    ) {
        return BibleDto.builder()
            .title(title)
            .chapter(chapter)
            .verse(verse)
            .content(content)
            .build();
    }

    public static SearchChapterResponse chapterFromBible(List<Bible> list) {
        List<BibleDto> response = list.stream().map(BibleConverter::toBibleDto)
            .collect(Collectors.toList());
        return SearchChapterResponse.builder()
            .list(response)
            .build();
    }

    public static SearchTitleResponse titleFromBible(List<Bible> list) {
        List<BibleDto> response = list.stream().map(BibleConverter::toBibleDto)
            .collect(Collectors.toList());
        return SearchTitleResponse.builder()
            .list(response)
            .build();
    }

    private static BibleDto toBibleDto(Bible bible) {
        return BibleDto.builder()
            .title(bible.getTitle())
            .chapter(bible.getChapter())
            .verse(bible.getVerse())
            .content(bible.getContent())
            .build();
    }

}
