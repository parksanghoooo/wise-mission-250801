package com.ll.wisesaying;

import com.ll.wisesaying.domain.wiseSaying.controller.WiseSayingController;
import com.ll.wisesaying.domain.wiseSaying.model.entity.WiseSaying;
import com.ll.wisesaying.domain.wiseSaying.service.WiseSayingService;
import com.ll.wisesaying.global.constant.Command;
import com.ll.wisesaying.global.constant.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    WiseSayingService service = new WiseSayingService();
    WiseSayingController controller = new WiseSayingController(service);

    public void run() throws IOException {
        System.out.println(Message.APP_TITLE);
        while (true) {
            System.out.print(Message.INPUT_PROMPT);
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
                controller.getAll();
            }
            // 삭제
            else if (cmd.startsWith(Command.DELETE)) {
                long id = extractIdFromCommand(cmd);
                controller.delete(id);
            }
            // 수정
            else if (cmd.startsWith(Command.MODIFY)) {
                long id = extractIdFromCommand(cmd);
                WiseSaying target = controller.get(id);

                if (target == null) {
                    System.out.printf(Message.MODIFY_NOT_FOUND, id);
                    return;
                }

                System.out.printf(Message.BEFORE_CONTENT, target.getContent());
                System.out.print(Message.INPUT_CONTENT);
                String newContent = br.readLine().trim();

                System.out.printf(Message.BEFORE_AUTHOR, target.getAuthor());
                System.out.print(Message.INPUT_AUTHOR);
                String newAuthor = br.readLine().trim();

                controller.update(id, newContent, newAuthor);
            }

        }
    }

    // 명령 내 쿼리 파라미터에서 id 추출 ("삭제?id=1", "수정?id=2")
    private long extractIdFromCommand(String cmd) {
        String[] parts = cmd.split("\\?");
        if (parts.length < 2) return -1;

        String[] keyValue = parts[1].split("=");
        if (keyValue.length < 2 || !keyValue[0].equals("id")) return -1;

        try {
            return Long.parseLong(keyValue[1]);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
