package com.example.mylittlebible.domain.bible.util;

import com.example.mylittlebible.domain.bible.dto.BibleDto;
import com.example.mylittlebible.domain.bible.dto.SearchChapterResponse;
import com.example.mylittlebible.domain.bible.dto.SearchTitleResponse;
import com.example.mylittlebible.domain.bible.entity.Bible;
import java.util.List;

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
    return null;
  }

  public static SearchTitleResponse titleFromBible(List<Bible> list) {

    return null;
  }

}
