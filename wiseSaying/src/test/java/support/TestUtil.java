package support;

import java.io.*;

public class TestUtil {

    public static BufferedReader generateReader(final String input) {
        final InputStream in = new ByteArrayInputStream(input.getBytes());
        return new BufferedReader(new InputStreamReader(in));
    }

    public static ByteArrayOutputStream setOutToByteArray() {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        return output;
    }

    public static void clearSetOutToByteArray(ByteArrayOutputStream output) {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        try {
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
