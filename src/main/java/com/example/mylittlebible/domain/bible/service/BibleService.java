package com.example.mylittlebible.domain.bible.service;

import com.example.mylittlebible.domain.bible.dto.BibleDto;
import com.example.mylittlebible.domain.bible.dto.SearchChapterResponse;
import com.example.mylittlebible.domain.bible.dto.SearchTitleResponse;
import com.example.mylittlebible.domain.bible.entity.Bible;
import com.example.mylittlebible.domain.bible.repository.BibleRepository;
import com.example.mylittlebible.domain.bible.util.BibleConverter;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BibleService {
  private BibleRepository bibleRepository;

  public BibleService(BibleRepository bibleRepository) {
    this.bibleRepository = bibleRepository;
  }


  //타이틀 찾기
  public SearchTitleResponse getTitle(String title){
    List<Bible> list = bibleRepository.findAllByTitle(title);

    return BibleConverter.titleFromBible(list);
  }
  //장으로 찾기
  public SearchChapterResponse getChapter(String title, Long chapter){
    List<Bible> list = bibleRepository.findAllByTitleAndChapter(title, chapter);

    return BibleConverter.chapterFromBible(list);
  }

  //특정 찾기
  public BibleDto getVerse(String title, Long chapter, Long verse){
    Bible bible = bibleRepository
        .findBibleByTitleAndChapterAndVerse(title, chapter, verse)
        .orElseThrow(RuntimeException::new);

    return BibleConverter.verseFromBible(bible.getTitle(),bible.getChapter(),bible.getVerse(),bible.getContent());
  }

  //구간 찾기

}
