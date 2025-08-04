package com.ll.wisesaying;

import com.ll.wisesaying.domain.wiseSaying.controller.WiseSayingController;
import com.ll.wisesaying.global.constant.Command;
import com.ll.wisesaying.global.constant.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final WiseSayingController controller = new WiseSayingController();

    public void run() throws IOException {

        System.out.println(Message.APP_TITLE);

        while (true) {

            System.out.print(Message.INPUT);
            String cmd = br.readLine().trim();

            // 종료
            if (cmd.equals(Command.QUIT)) break;
            // 등록
            else if (cmd.equals(Command.REGISTER)) {
                System.out.print(Message.INPUT_CONTENT);
                String content = br.readLine().trim();

                System.out.print(Message.INPUT_AUTHOR);
                String author = br.readLine().trim();

                controller.create(content, author);
            }
            // 목록
            else if (cmd.equals(Command.LIST)) {
                controller.getAllWiseSayings();
            }
        }

    }

}
