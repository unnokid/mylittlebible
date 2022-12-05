package com.example.mylittlebible.domain.bible.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.mylittlebible.domain.bible.dto.BibleDto;
import com.example.mylittlebible.domain.bible.dto.SearchBookRequest;
import com.example.mylittlebible.domain.bible.dto.SearchChapterRequest;
import com.example.mylittlebible.domain.bible.dto.SearchChapterSectionRequest;
import com.example.mylittlebible.domain.bible.dto.SearchResponse;
import com.example.mylittlebible.domain.bible.dto.SearchVerseRequest;
import com.example.mylittlebible.domain.bible.dto.SearchVerseSectionRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@DisplayName("성경 서비스 테스트")
class BibleServiceTest {

    @Autowired
    private BibleService bibleService;

    @Nested
    @DisplayName("제목으로 성경 조회 테스트")
    class getBibleForTitle {

        @Test
        @DisplayName("성공: 제목으로 조회")
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
        @DisplayName("실패: 존재하지 않는 제목 조회")
        void failNotExistTitle() {
            SearchChapterRequest request = new SearchChapterRequest("요구르트",1L);
            SearchResponse bible = bibleService
                .searchChapter(request.getBook(), request.getChapter());

            assertThat(bible.getList().size()).isZero();
        }

        @Test
        @DisplayName("실패: 존재하지 않는 장 조회")
        void failNotExistChapter() {
            SearchChapterRequest request = new SearchChapterRequest("창세기",0L);
            SearchResponse bible = bibleService
                .searchChapter(request.getBook(), request.getChapter());

            assertThat(bible.getList().size()).isZero();
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

        @Test
        @DisplayName("실패: 존재하지 않는 제목으로 조회")
        void failNotExistTitle() {
            SearchVerseRequest request = new SearchVerseRequest("요구르트",1L,1L);

            assertThatThrownBy(()-> bibleService
                .searchVerse(request.getBook(), request.getChapter(), request.getVerse()))
                .isInstanceOf(RuntimeException.class);
        }

        @Test
        @DisplayName("실패: 존재하지 않는 장으로 조회")
        void failNotExistChapter() {
            SearchVerseRequest request = new SearchVerseRequest("창세기",100L,1L);

            assertThatThrownBy(()-> bibleService
                .searchVerse(request.getBook(), request.getChapter(), request.getVerse()))
                .isInstanceOf(RuntimeException.class);
        }

        @Test
        @DisplayName("실패: 존재하지 않는 절으로 조회")
        void failNotExistVerse() {
            SearchVerseRequest request = new SearchVerseRequest("창세기",1L,100L);

            assertThatThrownBy(()-> bibleService
                .searchVerse(request.getBook(), request.getChapter(), request.getVerse()))
                .isInstanceOf(RuntimeException.class);
        }

    }

    @Nested
    @DisplayName("장에 대한 구간 조회 테스트")
    class searchSectionForChapter {

        @Test
        @DisplayName("성공: 창세기 1~2장 조회")
        void success() {
            SearchChapterSectionRequest request = new SearchChapterSectionRequest("창세기",1L,2L);
            SearchResponse response = bibleService.searchChapterSection(
                request.getBook(),
                request.getFrontChapter(),
                request.getBackChapter()
            );

            for(BibleDto dto :response.getList()){
                log.info("제목: {}",dto.getBook());
                log.info("장: {}",dto.getChapter());
                log.info("절: {}",dto.getVerse());
                log.info("내용: {}",dto.getContent());
            }
        }

        @Test
        @DisplayName("실패: 존재하지 않는 구간 조회")
        void failNotExistSection() {
            SearchChapterSectionRequest request = new SearchChapterSectionRequest("창세기",-2L,-1L);
            SearchResponse response = bibleService.searchChapterSection(
                request.getBook(),
                request.getFrontChapter(),
                request.getBackChapter()
            );

            assertThat(response.getList().size()).isZero();
        }

        @Test
        @DisplayName("실패: 존재하지 장으로 구간 조회")
        void failNotExistTitle() {
            SearchChapterSectionRequest request = new SearchChapterSectionRequest("요구르트",1L,2L);
            SearchResponse response = bibleService.searchChapterSection(
                request.getBook(),
                request.getFrontChapter(),
                request.getBackChapter()
            );

            assertThat(response.getList().size()).isZero();
        }
    }

    @Nested
    @DisplayName("절에 대한 구간 조회 테스트")
    class searchSectionForVerse {

        @Test
        @DisplayName("성공: 창세기 1장 1~10절 조회")
        void success() {
            SearchVerseSectionRequest request = new SearchVerseSectionRequest("창세기",1L,1L,10L);
            SearchResponse response = bibleService.searchVerseSection(
                request.getBook(),
                request.getChapter(),
                request.getFrontVerse(),
                request.getBackVerse()
            );

            for(BibleDto dto :response.getList()){
                log.info("제목: {}",dto.getBook());
                log.info("장: {}",dto.getChapter());
                log.info("절: {}",dto.getVerse());
                log.info("내용: {}",dto.getContent());
            }
        }

        @Test
        @DisplayName("실패: 존재하지 않는 구간 조회")
        void failNotExistSection() {
            SearchVerseSectionRequest request = new SearchVerseSectionRequest("창세기",1L,-100L,-1L);
            SearchResponse response = bibleService.searchVerseSection(
                request.getBook(),
                request.getChapter(),
                request.getFrontVerse(),
                request.getBackVerse()
            );

            assertThat(response.getList().size()).isZero();
        }

        @Test
        @DisplayName("실패: 존재하지 장으로 구간 조회")
        void failNotExistTitle() {
            SearchVerseSectionRequest request = new SearchVerseSectionRequest("요구르트",1L,1L,10L);
            SearchResponse response = bibleService.searchVerseSection(
                request.getBook(),
                request.getChapter(),
                request.getFrontVerse(),
                request.getBackVerse()
            );

            assertThat(response.getList().size()).isZero();
        }
    }
}