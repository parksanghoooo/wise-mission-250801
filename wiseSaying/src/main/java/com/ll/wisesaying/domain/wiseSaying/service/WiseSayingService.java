package com.ll.wisesaying.domain.wiseSaying.service;

import com.ll.wisesaying.domain.wiseSaying.model.entity.WiseSaying;
import com.ll.wisesaying.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {

    private final WiseSayingRepository repository = new WiseSayingRepository();

    public long create(String content, String author) {
        WiseSaying ws = new WiseSaying(content, author);
        return repository.save(ws);
    }

    public WiseSaying find(long id) {
        return repository.findById(id);
    }

    public List<WiseSaying> findAll() {
        return repository.findAllDesc();
    }

    public boolean delete(long id) {
        return repository.deleteById(id);
    }

    public void update(WiseSaying updated) {
        repository.update(updated);
    }

}
