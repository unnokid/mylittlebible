package com.example.mylittlebible.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.mylittlebible.domain.user.aop.SessionManager;
import com.example.mylittlebible.domain.user.dto.SignupRequest;
import com.example.mylittlebible.domain.user.entity.Gender;
import com.example.mylittlebible.domain.user.entity.User;
import com.example.mylittlebible.domain.user.repository.UserRepository;
import com.example.mylittlebible.domain.user.util.PasswordEncryptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootTest
@DisplayName("유저 서비스 테스트")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Nested
    @DisplayName("계정 생성 테스트")
    class saveTest {

        @Test
        @DisplayName("성공: 계정 생성")
        void success() {
            SignupRequest request = new SignupRequest(
                "kim@naver.com",
                "abcd1234!",
                "abcd1234!",
                "김철수",
                "1997-06-09",
                Gender.MALE
            );
            userService.save(request);

            User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(RuntimeException::new);

            assertThat(user.getEmail()).isEqualTo("kim@naver.com");
            assertThat(PasswordEncryptor.isMatch("abcd1234!",user.getPassword())).isTrue();
            assertThat(user.getName()).isEqualTo("김철수");
            assertThat(user.getBirth()).isEqualTo("1997-06-09");
            assertThat(user.getGender()).isEqualTo(Gender.MALE);
        }

        @Test
        @DisplayName("실패: 존재하는 이메일")
        void failExistEmail() {
            SignupRequest request = new SignupRequest(
                "kim@naver.com",
                "abcd1234!",
                "abcd1234!",
                "김철수",
                "1997-06-09",
                Gender.MALE
            );
            userService.save(request);
            assertThatThrownBy(() -> userService.save(request))
                .isInstanceOf(RuntimeException.class);
        }

        @Test
        @DisplayName("실패: 입력받았던 비밀번호 서로 다름")
        void failNotMatchPassword() {
            SignupRequest request = new SignupRequest(
                "kim@naver.com",
                "abcd1234!",
                "abcd1234@",
                "김철수",
                "1997-06-09",
                Gender.MALE
            );
            assertThatThrownBy(() -> userService.save(request))
                .isInstanceOf(RuntimeException.class);
        }

        @Test
        @DisplayName("실패: 입력받은 이메일 형식이 잘못됨")
        void failWrongExpressionEmail() {
            SignupRequest request = new SignupRequest(
                "kimabcd",
                "abcd1234!",
                "abcd1234!",
                "김철수",
                "1997-06-09",
                Gender.MALE
            );
            assertThatThrownBy(() -> userService.save(request))
                .isInstanceOf(RuntimeException.class);
        }

        @Test
        @DisplayName("실패: 입력받은 비밀번호 형식이 잘못됨")
        void failWrongExpressionPassword() {
            SignupRequest request = new SignupRequest(
                "kimabcd",
                "abcd1234",
                "abcd1234",
                "김철수",
                "1997-06-09",
                Gender.MALE
            );
            assertThatThrownBy(() -> userService.save(request))
                .isInstanceOf(RuntimeException.class);
        }
    }

    @Nested
    @DisplayName("로그인 테스트")
    class loginTest {

        @Test
        @DisplayName("성공: 로그인 성공")
        void success() {
        }
    }

    @Nested
    @DisplayName("로그아웃 테스트")
    class logoutTest {

        @Test
        @DisplayName("성공: 로그아웃 성공")
        void success() {

        }
    }

    @Nested
    @DisplayName("이메일 중복 테스트")
    class validateEmailTest {

        @Test
        @DisplayName("성공: 중복되지 않음")
        void success() {

        }
    }

    @Nested
    @DisplayName("유저 정보 테스트")
    class getUserInfoTest {

        @Test
        @DisplayName("성공: 유저 정보 조회 성공")
        void success() {

        }
    }

    @Nested
    @DisplayName("회원 탈퇴 테스트")
    class deleteTest {

        @Test
        @DisplayName("성공: 탈퇴 성공")
        void success() {

        }
    }

    @Nested
    @DisplayName("내 정보 가져오기")
    class getMyInfoTest {

        @Test
        @DisplayName("성공: 내 정보 조회 성공")
        void success() {

        }
    }

    @Nested
    @DisplayName("현재 로그인 된 유저 테스트")
    class getLoginUser {

        @Test
        @DisplayName("성공: 로그인 유저 조회")
        void success() {

        }
    }

}