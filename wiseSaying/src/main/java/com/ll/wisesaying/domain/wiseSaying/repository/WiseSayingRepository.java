package com.ll.wisesaying.domain.wiseSaying.repository;

import com.ll.wisesaying.domain.wiseSaying.model.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WiseSayingRepository {

    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private long lastId = 0;

    public WiseSaying save(String content, String author) {
        long id = ++lastId;
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);
        return wiseSaying;
    }

    public List<WiseSaying> findAllDesc() {
        List<WiseSaying> descWiseSayings = new ArrayList<>(wiseSayings); // 데이터 복제
        descWiseSayings.sort((a, b) -> Long.compare(b.getId(), a.getId()));
        return descWiseSayings;
    }

    public Optional<WiseSaying> findById(long id) {
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.getId() == id) {
                return Optional.of(wiseSaying);
            }
        }
        return Optional.empty();
    }

    public boolean deleteById(long id) {
        Optional<WiseSaying> optional = findById(id);
        if (optional.isPresent()) {
            wiseSayings.remove(optional.get());
            return true;
        }

        return false;
    }

}
