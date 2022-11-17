package com.example.mylittlebible.domain.bible.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchChapterResponse {
  List<BibleDto> list;
}
