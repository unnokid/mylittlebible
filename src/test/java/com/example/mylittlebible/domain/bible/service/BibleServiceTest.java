package com.example.mylittlebible.domain.bible.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.mylittlebible.domain.bible.dto.BibleDto;
import com.example.mylittlebible.domain.bible.dto.SearchBookRequest;
import com.example.mylittlebible.domain.bible.dto.SearchChapterRequest;
import com.example.mylittlebible.domain.bible.dto.SearchResponse;
import com.example.mylittlebible.domain.bible.dto.SearchVerseRequest;
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

    @Nested
    @DisplayName("제목으로 성경 조회 테스트")
    class getBibleForTitle {

        @Test
        @DisplayName("성공테스트")
        void success() {
            SearchBookRequest request = new SearchBookRequest("창세기");
            SearchResponse title = bibleService.searchBook(request.getBook());

            assertThat(title.getList().size()).isNotZero();
        }

        @Test
        @DisplayName("실패: 존재하지 않는 제목으로 조회 ")
        void failNotExistTitle() {
            SearchBookRequest request = new SearchBookRequest("요구르트");
            SearchResponse title = bibleService.searchBook(request.getBook());

            assertThat(title.getList().size()).isEqualTo(0);

        }

        @Test
        @DisplayName("실패: 빈 제목으로 조회 ")
        void failEmptyTitle() {
            SearchBookRequest request = new SearchBookRequest("");
            SearchResponse title = bibleService.searchBook(request.getBook());

            assertThat(title.getList().size()).isZero();
        }
    }

    @Nested
    @DisplayName("제목, 장 성경 조회 테스트")
    class getBibleForChapter {
        @Test
        @DisplayName("성공: 창세기 1장 조회")
        void success() {
            SearchChapterRequest request = new SearchChapterRequest("창세기",1L);
            SearchResponse bible = bibleService
                .searchChapter(request.getBook(), request.getChapter());

            assertThat(bible.getList().size()).isNotZero();
        }

        @Test
        @DisplayName("실패: 존재하지 않는 장 조회")
        void failNotExist() {
            SearchChapterRequest request = new SearchChapterRequest("창세기",0L);
            SearchResponse bible = bibleService
                .searchChapter(request.getBook(), request.getChapter());

            assertThat(bible.getList().size()).isNotZero();
        }
    }

    @Nested
    @DisplayName("제목, 장, 절 성경 조회 테스트")
    class getBibleForVerse {
        @Test
        @DisplayName("성공: 창세기 1장 1절 조회")
        void success() {
            SearchVerseRequest request = new SearchVerseRequest("창세기",1L,1L);
            BibleDto bible = bibleService
                .searchVerse(request.getBook(), request.getChapter(), request.getVerse());

            assertThat(bible.getBook()).isEqualTo("창세기");
            assertThat(bible.getChapter()).isEqualTo(1L);
            assertThat(bible.getVerse()).isEqualTo(1L);
            assertThat(bible.getContent()).isEqualTo("태초에 하나님이 천지를 창조하시니라");
        }

    }
}