package com.example.mylittlebible.domain.bible.repository;

import com.example.mylittlebible.domain.bible.entity.Bible;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BibleRepository extends MongoRepository<Bible, String> {
    Optional<Bible> findBibleByTitleAndChapterAndVerse(String title, Long chapter, Long verse);
    List<Bible> findAllByTitleAndChapter(String title, Long chapter);
    List<Bible> findAllByTitle(String title);
}
