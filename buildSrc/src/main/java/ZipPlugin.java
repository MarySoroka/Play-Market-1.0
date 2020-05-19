
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.task("zip").doLast(task -> zip());
    }
     private static void zip() {
            try {
                List<String> srcFiles = Arrays.asList("/home/maria/Desktop/play/gamesSer.spec",
                        "/home/maria/Desktop/play/gamesSer.bin", "/home/maria/Desktop/play/gamesSer.xml");
                FileOutputStream fos = new FileOutputStream("/home/maria/Desktop/play/archive/game.zip");
                ZipOutputStream zipOut = new ZipOutputStream(fos);
                for (String srcFile : srcFiles) {
                    File fileToZip = new File(srcFile);
                    FileInputStream fis = new FileInputStream(fileToZip);
                    ZipEntry zipEntry = new ZipEntry(fileToZip.getName());

                    zipOut.putNextEntry(zipEntry);


                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zipOut.write(bytes, 0, length);
                    }
                    fis.close();
                }
                zipOut.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}

