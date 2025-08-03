package com.ll.wisesaying;

import com.ll.wisesaying.domain.wiseSaying.model.entity.WiseSaying;
import com.ll.wisesaying.domain.wiseSaying.repository.WiseSayingRepository;
import com.ll.wisesaying.global.constant.Command;
import com.ll.wisesaying.global.constant.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    WiseSayingRepository repository = new WiseSayingRepository();

    public void run() throws IOException {
        System.out.println(Message.APP_TITLE);
        while (true) {
            System.out.println(Message.INPUT_PROMPT);
            String cmd = br.readLine().trim();

            // 종료
            if (cmd.equals(Command.QUIT)) break;
            // 등록
            else if (cmd.equals(Command.REGISTER)) {
                System.out.println(Message.REGISTER_CONTENT);
                String content = br.readLine().trim();

                System.out.println(Message.REGISTER_AUTHOR);
                String author = br.readLine().trim();

                WiseSaying wiseSaying = new WiseSaying(content, author);
                long id = repository.save(wiseSaying);

                System.out.printf(Message.REGISTER_SUCCESS, id);

            }

        }
    }
}
