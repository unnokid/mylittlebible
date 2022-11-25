package com.example.mylittlebible.domain.bible.util;

import com.example.mylittlebible.domain.bible.dto.BibleDto;
import com.example.mylittlebible.domain.bible.dto.SearchBookResponse;
import com.example.mylittlebible.domain.bible.dto.SearchChapterResponse;
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
            .book(title)
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

    public static SearchBookResponse bookFromBible(List<Bible> list) {
        List<BibleDto> response = list.stream().map(BibleConverter::toBibleDto)
            .collect(Collectors.toList());
        return SearchBookResponse.builder()
            .list(response)
            .build();
    }

    private static BibleDto toBibleDto(Bible bible) {
        return BibleDto.builder()
            .book(bible.getBook())
            .chapter(bible.getChapter())
            .verse(bible.getVerse())
            .content(bible.getContent())
            .build();
    }

}
