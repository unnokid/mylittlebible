package com.example.mylittlebible.domain.user.service;

import com.example.mylittlebible.domain.bible.entity.Bible;
import com.example.mylittlebible.domain.bible.repository.BibleRepository;
import com.example.mylittlebible.domain.user.dto.SignupRequest;
import com.example.mylittlebible.domain.user.dto.UserInfoResponse;
import com.example.mylittlebible.domain.user.entity.FavoriteId;
import com.example.mylittlebible.domain.user.entity.User;
import com.example.mylittlebible.domain.user.repository.UserRepository;
import com.example.mylittlebible.domain.user.util.UserConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private UserRepository userRepository;
    private BibleRepository bibleRepository;

    public UserService(UserRepository userRepository,
        BibleRepository bibleRepository) {
        this.userRepository = userRepository;
        this.bibleRepository = bibleRepository;
    }

    @Transactional
    public void save(SignupRequest request){
        validatePassword(request.getPassword(), request.getPasswordCheck());

        userRepository.save(UserConverter.toUser(request));
    }

    @Transactional
    public void validateEmail(String email){
        userRepository.findByEmail(email)
            .ifPresent(a ->{
                throw new RuntimeException();
            });
    }

    @Transactional
    public UserInfoResponse getUserInfo(Long userId){

        User user = userRepository.findById(userId)
            .orElseThrow(RuntimeException::new);
        FavoriteId recent = user.getRecent();

        Bible bible = bibleRepository.findBibleByBookAndChapterAndVerse(
            recent.getBook(),
            recent.getChapter(),
            recent.getVerse()
        ).orElseThrow(RuntimeException::new);

        return UserConverter.getInfoFromUser(user,bible.getContent());
    }

    @Transactional
    public void delete(Long userId){
        User user = userRepository.findById(userId)
            .orElseThrow(RuntimeException::new);

        userRepository.delete(user);
    }

    private void validatePassword(String password, String passwordCheck){
        //정규화 비밀번호 기준 추가해야함
        if(!password.equals(passwordCheck)){
            throw new RuntimeException();
        }
    }
}
