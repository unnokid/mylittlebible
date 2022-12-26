package com.example.mylittlebible.domain.user.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("User 클래스 생성 테스트")
class UserTest {

    @Nested
    @DisplayName("User 생성 테스트")
    class createTest{

        @Test
        @DisplayName("성공: User 생성")
        void success() {
            User user = new User("kim@naver.com","abcd1234!","김철수","1997-06-09",Gender.MALE);

            assertThat(user.getEmail()).isEqualTo("kim@naver.com");
            assertThat(user.getPassword()).isEqualTo("abcd1234!");
            assertThat(user.getName()).isEqualTo("김철수");
            assertThat(user.getBirth()).isEqualTo("1997-06-09");
            assertThat(user.getGender()).isEqualTo(Gender.MALE);
        }

        @Test
        @DisplayName("실패: 형식에 맞지않는 이메일")
        void failWrongExpressionEmail() {
            assertThatThrownBy(() -> new User("kimasdf","abcd1234!","김철수","1997-06-09",Gender.MALE))
                .isInstanceOf(RuntimeException.class);
        }

        @Test
        @DisplayName("실패: 형식에 맞지않는 비밀번호")
        void failWrongExpressionPassword() {
            assertThatThrownBy(() -> new User("kim@naver.com","abcd!","김철수","1997-06-09",Gender.MALE))
                .isInstanceOf(RuntimeException.class);
        }
    }

}