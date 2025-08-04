package com.ll.wisesaying.domain.wiseSaying.service;

import com.ll.wisesaying.domain.wiseSaying.model.entity.WiseSaying;
import com.ll.wisesaying.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {

    private final WiseSayingRepository repository = new WiseSayingRepository();

    public WiseSaying create(String content, String author) {
        return repository.save(content, author);
    }

    public List<WiseSaying> findAll() {
        return repository.findAllDesc();
    }

}
