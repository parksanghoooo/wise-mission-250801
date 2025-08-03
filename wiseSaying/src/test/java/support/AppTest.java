package support;

import com.ll.wisesaying.App;
import com.ll.wisesaying.domain.wiseSaying.repository.WiseSayingRepository;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AppTest {

    public static String run(String input) {
        BufferedReader br = TestUtil.generateReader(input);
        ByteArrayOutputStream out = TestUtil.setOutToByteArray();

        try {
            new App(br).run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            TestUtil.clearSetOutToByteArray(out);
        }

        return out.toString();
    }

    public static void clear() {
        // DB 초기화
        new WiseSayingRepository().deleteAll();
    }

}
