package com.ll.wisesaying.global.constant;

public class Message {

    // App UI
    public static final String APP_TITLE = "== 명언 앱 ==";
    public static final String INPUT_PROMPT = "명령) ";

    // 등록 관련
    public static final String INPUT_CONTENT = "명언 : ";
    public static final String INPUT_AUTHOR = "작가 : ";
    public static final String REGISTER_SUCCESS = "%d번 명언이 등록되었습니다.\n";

    // 목록 관련
    public static final String LIST_HEADER = "번호 / 작가 / 명언\n----------------------";
    public static final String LIST_FOOTER = "----------------------\n페이지 : %s\n";
    public static final String LIST_ROW_FORMAT = "%d / %s / %s\n";

    // 삭제 관련
    public static final String DELETE_SUCCESS = "%d번 명언이 삭제되었습니다.\n";
    public static final String DELETE_NOT_FOUND = "%d번 명언은 존재하지 않습니다.\n";

    // 수정 관련
    public static final String MODIFY_NOT_FOUND = "%d번 명언은 존재하지 않습니다.\n";
    public static final String BEFORE_CONTENT = "명언(기존) : %s\n";
    public static final String BEFORE_AUTHOR = "작가(기존) : %s\n";

}
