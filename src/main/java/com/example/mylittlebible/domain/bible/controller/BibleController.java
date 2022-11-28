package com.example.mylittlebible.domain.bible.controller;

import com.example.mylittlebible.domain.bible.dto.BibleDto;
import com.example.mylittlebible.domain.bible.dto.SearchBookRequest;
import com.example.mylittlebible.domain.bible.dto.SearchBookResponse;
import com.example.mylittlebible.domain.bible.dto.SearchChapterRequest;
import com.example.mylittlebible.domain.bible.dto.SearchChapterResponse;
import com.example.mylittlebible.domain.bible.dto.SearchSectionRequest;
import com.example.mylittlebible.domain.bible.dto.SearchSectionResponse;
import com.example.mylittlebible.domain.bible.dto.SearchVerseRequest;
import com.example.mylittlebible.domain.bible.service.BibleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public ResponseEntity<SearchBookResponse> getTitle(
        @RequestBody SearchBookRequest titleRequest) {
        SearchBookResponse response = bibleService.getBook(titleRequest.getBook());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/chapter")
    public ResponseEntity<SearchChapterResponse> getChapter(
        @RequestBody SearchChapterRequest chapterRequest) {
        SearchChapterResponse response = bibleService
            .getChapter(chapterRequest.getBook(), chapterRequest.getChapter());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verse")
    public ResponseEntity<BibleDto> getVerse(@RequestBody SearchVerseRequest verseRequest) {
        BibleDto response = bibleService.getVerse(
            verseRequest.getBook(),
            verseRequest.getChapter(),
            verseRequest.getVerse());

        return ResponseEntity.ok(response);
    }

    //TODO: 구간 찾아오기 A~B
    @GetMapping("/section")
    public ResponseEntity<SearchSectionResponse> getSection(@RequestBody SearchSectionRequest sectionRequest){
        SearchSectionResponse section = bibleService.getSection(
            sectionRequest.getFrontBook(),
            sectionRequest.getFrontChapter(),
            sectionRequest.getFrontVerse(),
            sectionRequest.getBackBook(),
            sectionRequest.getBackChapter(),
            sectionRequest.getBackVerse()
        );

        return ResponseEntity.ok(section);
    }
}
