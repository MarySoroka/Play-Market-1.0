import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class UngzPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.task("ungz").doLast(task -> ungz());
    }

    private static void ungz() {
        List<String> srcFiles = Arrays.asList("/home/maria/Desktop/play/game/gamesSer.spec",
                "/home/maria/Desktop/play/game/gamesSer.bin", "/home/maria/Desktop/play/game/gamesSer.xml");
        List<String> srcFile = Arrays.asList("/home/maria/Desktop/play/archive/gamesSerspec.gz",
                "/home/maria/Desktop/play/archive/gamesSerbin.gz", "/home/maria/Desktop/play/archive/gamesSerxml.gz");
        for (int i = 0; i < 3 ; i++) {
            try (
                    FileInputStream fis = new FileInputStream(srcFile.get(i));
                    GZIPInputStream gzis = new GZIPInputStream(fis);
                    FileOutputStream fos = new FileOutputStream(srcFiles.get(i))) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = gzis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

