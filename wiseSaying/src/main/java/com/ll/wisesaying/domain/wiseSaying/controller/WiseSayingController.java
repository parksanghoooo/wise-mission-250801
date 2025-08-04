package com.ll.wisesaying.domain.wiseSaying.controller;

import com.ll.wisesaying.domain.wiseSaying.model.entity.WiseSaying;
import com.ll.wisesaying.domain.wiseSaying.service.WiseSayingService;
import com.ll.wisesaying.global.constant.Message;

import java.io.IOException;
import java.util.List;

public class WiseSayingController {

    // 페이지 크기 상수
    private static final int PAGE_SIZE = 5;

    private final WiseSayingService service;

    public WiseSayingController(WiseSayingService service) {
        this.service = service;
    }

    public void create(String content, String author) throws IOException {
        long id = service.create(content, author);
        System.out.printf(Message.REGISTER_SUCCESS, id);
    }

    public WiseSaying get(long id) {
        return service.find(id);
    }

    public void getAll(int pageNumber) {
        List<WiseSaying> paged = service.findAll(pageNumber, PAGE_SIZE);
        int totalCount = service.getTotalCount();
        int totalPage = (int) Math.ceil((double) totalCount / PAGE_SIZE);

        System.out.println(Message.LIST_HEADER);
        for (WiseSaying ws : paged) {
            System.out.printf(Message.LIST_ROW_FORMAT, ws.getId(), ws.getAuthor(), ws.getContent());
        }
        markCurrentPage(pageNumber, totalPage);
    }

    public void delete(long id) {
        boolean result = service.delete(id);

        if (result) {
            System.out.printf(Message.DELETE_SUCCESS, id);
        } else {
            System.out.printf(Message.DELETE_NOT_FOUND, id);
        }
    }

    public void update(long id, String newContent, String newAuthor) {
        WiseSaying updated = new WiseSaying(id, newContent, newAuthor);
        service.update(updated);
    }

    private void markCurrentPage(int current, int total) {
        StringBuilder sb = new StringBuilder();
        for (int idx = 1; idx <= total; idx++) {
            if (idx == current) sb.append("[").append(idx).append("]");
            else sb.append(idx);
            if (idx < total) sb.append(" / ");
        }
        System.out.printf(Message.LIST_FOOTER , sb);
    }

}
