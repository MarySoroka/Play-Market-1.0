import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class GZPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.task("gz").doLast(task -> gz());
    }

    private static void gz() {
        List<String> srcFiles = Arrays.asList("/home/maria/Desktop/play/gamesSer.spec",
                "/home/maria/Desktop/play/gamesSer.bin", "/home/maria/Desktop/play/gamesSer.xml");
        List<String> srcFile = Arrays.asList("/home/maria/Desktop/play/archive/gamesSerspec.gz",
                "/home/maria/Desktop/play/archive/gamesSerbin.gz", "/home/maria/Desktop/play/archive/gamesSerxml.gz");
        for (int j = 0; j < 3; j++) {
            try (
                    FileInputStream fis = new FileInputStream(srcFiles.get(j));
                    FileOutputStream fos = new FileOutputStream(srcFile.get(j));
                    GZIPOutputStream gzos = new GZIPOutputStream(fos)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    gzos.write(buffer, 0, length);
                }
                gzos.finish();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
