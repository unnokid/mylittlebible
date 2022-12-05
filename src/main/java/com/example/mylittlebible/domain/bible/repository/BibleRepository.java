package com.example.mylittlebible.domain.bible.repository;

import com.example.mylittlebible.domain.bible.entity.Bible;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BibleRepository extends MongoRepository<Bible, String> {

    Optional<Bible> findBibleByBookAndChapterAndVerse(String book, Long chapter, Long verse);
    List<Bible> findBibleByBookAndChapter(String book, Long chapter);
    List<Bible> findBibleByBook(String book);

    @Query("{'book' : ?0 'chapter': {'$gte': ?1,'$lte':?2}}")
    List<Bible> findChapterSection(String book, Long frontChapter, Long backChapter);
    @Query("{'book' : ?0 'chapter': ?1 'verse': {'$gte': ?3,'$lte':?4}} ")
    List<Bible> findVerseSection(String book, Long chapter, Long frontVerse, Long backVerse);
}
