package com.ll.wisesaying.domain.wiseSaying.service;

import com.ll.wisesaying.domain.wiseSaying.model.entity.WiseSaying;
import com.ll.wisesaying.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;
import java.util.Optional;

public class WiseSayingService {

    private final WiseSayingRepository repository = new WiseSayingRepository();

    public WiseSaying create(String content, String author) {
        return repository.save(content, author);
    }

    public Optional<WiseSaying> findById(long id) {
        return repository.findById(id);
    }

    public List<WiseSaying> findAll() {
        return repository.findAllDesc();
    }

    public void delete(WiseSaying wiseSaying) {
        repository.delete(wiseSaying);
    }

    public void update(WiseSaying wiseSaying, String newContent, String newAuthor) {
        repository.update(wiseSaying, newContent, newAuthor);
    }

}
