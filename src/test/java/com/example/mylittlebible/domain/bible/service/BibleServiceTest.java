package com.example.mylittlebible.domain.bible.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.example.mylittlebible.domain.bible.repository.BibleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("성경 서비스 테스트")
class BibleServiceTest {

  @Autowired
  private BibleService bibleService;

  @Autowired
  private BibleRepository bibleRepository;

  @Nested
  @DisplayName("제목으로 성경 조회 테스트")
  class getBibleTest{

    @Test
    @DisplayName("성공테스트 ")
    void success() {

    }
  }
}