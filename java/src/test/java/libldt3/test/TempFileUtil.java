package libldt3.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class TempFileUtil {

    public static Path getTempFile(String prefix) throws IOException {
        File test = File.createTempFile(prefix, ".ldt");
        test.deleteOnExit();
        return test.toPath();
    }

}
