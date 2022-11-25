package com.example.mylittlebible.domain.bible.service;

import com.example.mylittlebible.domain.bible.dto.BibleDto;
import com.example.mylittlebible.domain.bible.dto.SearchBookResponse;
import com.example.mylittlebible.domain.bible.dto.SearchChapterResponse;
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
    public SearchBookResponse getBook(String book) {
        List<Bible> list = bibleRepository.findBibleByBook(book);
        return BibleConverter.bookFromBible(list);
    }

    //장으로 찾기
    public SearchChapterResponse getChapter(String title, Long chapter) {
        List<Bible> list = bibleRepository.findBibleByBookAndChapter(title, chapter);
        return BibleConverter.chapterFromBible(list);
    }

    //특정 찾기
    public BibleDto getVerse(String title, Long chapter, Long verse) {
        Bible bible = bibleRepository
            .findBibleByBookAndChapterAndVerse(title, chapter, verse)
            .orElseThrow(RuntimeException::new);
        return BibleConverter.verseFromBible(bible.getBook(), bible.getChapter(), bible.getVerse(),
            bible.getContent());
    }

    //구간 찾기

}
