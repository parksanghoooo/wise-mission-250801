package com.ll.wisesaying.domain.wiseSaying.controller;

import com.ll.wisesaying.domain.wiseSaying.model.entity.WiseSaying;
import com.ll.wisesaying.domain.wiseSaying.service.WiseSayingService;
import com.ll.wisesaying.global.constant.Message;

import java.util.List;
import java.util.Optional;

public class WiseSayingController {

    private final WiseSayingService service = new WiseSayingService();

    public void create(String content, String author) {
        WiseSaying response = service.create(content, author);
        System.out.printf(Message.REGISTER_SUCCESS, response.getId());
    }

    public Optional<WiseSaying> getWiseSaying(long id) {
        return service.findById(id);
    }

    public void getAllWiseSayings() {
        List<WiseSaying> allWiseSayings = service.findAll();

        System.out.println(Message.LIST_HEADER);
        for (WiseSaying wiseSaying : allWiseSayings) {
            System.out.printf(Message.LIST_ROW_FORMAT, wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    public void delete(WiseSaying wiseSaying) {
        service.delete(wiseSaying);
    }

    public void update(WiseSaying ws, String newContent, String newAuthor) {
        service.update(ws, newContent, newAuthor);
    }

}
