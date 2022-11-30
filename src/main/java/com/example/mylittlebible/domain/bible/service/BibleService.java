package com.example.mylittlebible.domain.bible.service;

import com.example.mylittlebible.domain.bible.dto.BibleDto;
import com.example.mylittlebible.domain.bible.dto.SearchResponse;
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

    public SearchResponse searchBook(String book) {
        List<Bible> list = bibleRepository.findBibleByBook(book);
        return BibleConverter.toSearchResponse(list);
    }

    public SearchResponse searchChapter(String book, Long chapter) {
        List<Bible> list = bibleRepository.findBibleByBookAndChapter(book, chapter);
        return BibleConverter.toSearchResponse(list);
    }

    public BibleDto searchVerse(String book, Long chapter, Long verse) {
        Bible bible = bibleRepository
            .findBibleByBookAndChapterAndVerse(book, chapter, verse)
            .orElseThrow(RuntimeException::new);
        return BibleConverter.verseFromBible(bible.getBook(), bible.getChapter(), bible.getVerse(),
            bible.getContent());
    }

    public SearchResponse searchChapterSection(String book, Long frontChapter, Long backChapter){
        List<Bible> list = bibleRepository
            .findChapterSection(book, frontChapter, backChapter);

        return BibleConverter.toSearchResponse(list);
    }

    public SearchResponse searchVerseSection(String book, Long chapter, Long frontVerse, Long backVerse){
        List<Bible> list = bibleRepository
            .findVerseSection(book, chapter, frontVerse, backVerse);

        return BibleConverter.toSearchResponse(list);
    }
}
