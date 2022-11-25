package com.example.mylittlebible.domain.bible.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BibleTest {

    @Nested
    @DisplayName("Bible 생성 테스트")
    class createTest {
        @Test
        @DisplayName("성공: Bible 생성 성공")
        void success() {
            Bible bible = new Bible("창세기", 1L, 1L, "테스트 중입니다.");

            assertThat(bible.getBook()).isEqualTo("창세기");
            assertThat(bible.getChapter()).isEqualTo(1L);
            assertThat(bible.getVerse()).isEqualTo(1L);
            assertThat(bible.getContent()).isEqualTo("테스트 중입니다.");
        }

        @Test
        @DisplayName("실패: 제목에 빈칸 또는 null 경우")
        void failNotNullOrBlankTitle() {
            assertThatThrownBy(() -> new Bible("", 1L, 1L, "테스트 중입니다."))
                .isInstanceOf(RuntimeException.class);
            assertThatThrownBy(() -> new Bible(null, 1L, 1L, "테스트 중입니다."))
                .isInstanceOf(RuntimeException.class);
        }

        @Test
        @DisplayName("실패: 장에 음수 또는 0인 경우")
        void failNotMinusOrZeroChapter() {
            assertThatThrownBy(() -> new Bible("창세기", -1L, 1L, "테스트 중입니다."))
                .isInstanceOf(RuntimeException.class);
            assertThatThrownBy(() -> new Bible("창세기", 0L, 1L, "테스트 중입니다."))
                .isInstanceOf(RuntimeException.class);
        }

        @Test
        @DisplayName("실패: 절에 음수 또는 0인 경우")
        void failNotMinusOrZeroVerse() {
            assertThatThrownBy(() -> new Bible("창세기", 1L, -1L, "테스트 중입니다."))
                .isInstanceOf(RuntimeException.class);
            assertThatThrownBy(() -> new Bible("창세기", 1L, 0L, "테스트 중입니다."))
                .isInstanceOf(RuntimeException.class);
        }

        @Test
        @DisplayName("실패: 내용에 빈칸 또는 null 경우")
        void failNotNullOrBlankContent() {
            assertThatThrownBy(() -> new Bible("창세기", 1L, 1L, ""))
                .isInstanceOf(RuntimeException.class);
            assertThatThrownBy(() -> new Bible("창세기", 1L, 1L, null))
                .isInstanceOf(RuntimeException.class);
        }
    }
}
