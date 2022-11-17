package com.example.mylittlebible.domain.bible.controller;

import com.example.mylittlebible.domain.bible.dto.BibleDto;
import com.example.mylittlebible.domain.bible.dto.SearchTitleRequest;
import com.example.mylittlebible.domain.bible.dto.SearchTitleResponse;
import com.example.mylittlebible.domain.bible.dto.SearchVerseRequest;
import com.example.mylittlebible.domain.bible.service.BibleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/bible")
public class BibleController {

  private BibleService bibleService;

  public BibleController(BibleService bibleService) {
    this.bibleService = bibleService;
  }

  //TODO: 파트 전체 가져오기
  @GetMapping("/title")
  public ResponseEntity<SearchTitleResponse> getTitle(@RequestBody SearchTitleRequest titleRequest){

    return ResponseEntity.ok().build();
  }
  //TODO: 한 장 찾기

  //TODO: 한 절 찾기
  @GetMapping("/verse")
  public ResponseEntity<BibleDto> getVerse(@RequestBody SearchVerseRequest verseRequest){
    BibleDto response = bibleService.getVerse(
        verseRequest.getTitle(),
        verseRequest.getChapter(),
        verseRequest.getVerse());

    return ResponseEntity.ok(response);
  }
  //TODO: 구간 찾아오기 A~B

}
