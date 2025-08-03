package com.ll.wisesaying.domain.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import support.AppTest;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {

    @BeforeEach
    void beforeEach() {
        AppTest.clear();
    }

    @Test
    @DisplayName("명언 등록 테스트")
    void t1() {
        String out = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                종료
                """);

        assertThat(out)
                .contains("명언 :")
                .contains("작가 :")
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("명언 목록 테스트")
    void t2() {
        String output = AppTest.run("""
        등록
        과거에 집착하지 마라.
        작자미상
        등록
        현재와 자신을 사랑하라.
        작자미상
        목록
        종료
    """);

        assertThat(output)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("2 / 작자미상 / 현재와 자신을 사랑하라.")
                .contains("1 / 작자미상 / 과거에 집착하지 마라.")
                .contains("----------------------")
                .contains("페이지 : [1]");
    }

    @Test
    @DisplayName("명언 삭제 테스트")
    void t3() {
        String output = AppTest.run("""
            등록
            현재를 사랑하라.
            작자미상
            삭제?id=1
            종료
        """);

        assertThat(output)
                .contains("1번 명언이 삭제되었습니다.");
    }

    @Test
    @DisplayName("명언 삭제 예외 테스트")
    void t4() {
        String output = AppTest.run("""
            삭제?id=999
            종료
        """);

        assertThat(output)
                .contains("999번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("명언 수정")
    void t5() {
        String output = AppTest.run("""
        등록
        현재를 사랑하라.
        작자미상
        수정?id=1
        현재와 나를 사랑하라.
        나
        목록
        종료
    """);

        assertThat(output)
                .contains("1번 명언이 등록되었습니다.")
                .contains("명언(기존) : 현재를 사랑하라.")
                .contains("작가(기존) : 작자미상")
                .contains("1 / 나 / 현재와 나를 사랑하라.");
    }

}
