package com.example.mylittlebible.domain.bible.controller;

import com.example.mylittlebible.domain.bible.dto.BibleDto;
import com.example.mylittlebible.domain.bible.dto.SearchBookRequest;
import com.example.mylittlebible.domain.bible.dto.SearchChapterRequest;
import com.example.mylittlebible.domain.bible.dto.SearchChapterSectionRequest;
import com.example.mylittlebible.domain.bible.dto.SearchResponse;
import com.example.mylittlebible.domain.bible.dto.SearchVerseRequest;
import com.example.mylittlebible.domain.bible.dto.SearchVerseSectionRequest;
import com.example.mylittlebible.domain.bible.service.BibleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bible")
public class BibleController {

    private BibleService bibleService;

    public BibleController(BibleService bibleService) {
        this.bibleService = bibleService;
    }

    @GetMapping("/title")
    public ResponseEntity<SearchResponse> searchTitle(
        @RequestBody SearchBookRequest titleRequest) {
        SearchResponse response = bibleService.searchBook(titleRequest.getBook());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/chapter")
    public ResponseEntity<SearchResponse> searchChapter(
        @RequestBody SearchChapterRequest chapterRequest) {
        SearchResponse response = bibleService
            .searchChapter(chapterRequest.getBook(), chapterRequest.getChapter());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verse")
    public ResponseEntity<BibleDto> searchVerse(@RequestBody SearchVerseRequest verseRequest) {
        BibleDto response = bibleService.searchVerse(
            verseRequest.getBook(),
            verseRequest.getChapter(),
            verseRequest.getVerse());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/section/chapter")
    public ResponseEntity<SearchResponse> searchChapterSection(@RequestBody SearchChapterSectionRequest sectionRequest){
        SearchResponse section = bibleService.searchChapterSection(
            sectionRequest.getBook(),
            sectionRequest.getFrontChapter(),
            sectionRequest.getBackChapter()
        );

        return ResponseEntity.ok(section);
    }

    @GetMapping("/section/verse")
    public ResponseEntity<SearchResponse> searchVerseSection(@RequestBody SearchVerseSectionRequest sectionRequest){
        SearchResponse section = bibleService.searchVerseSection(
            sectionRequest.getBook(),
            sectionRequest.getChapter(),
            sectionRequest.getFrontVerse(),
            sectionRequest.getBackVerse()
        );

        return ResponseEntity.ok(section);
    }
}
