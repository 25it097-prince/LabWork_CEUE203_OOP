import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.nio.charset.*;
import java.util.*;
import java.util.stream.*;

public class FolderReport {
    public static void main(String[] args) throws IOException {

        Path root = Paths.get("myfolder");
        Files.createDirectories(root.resolve("subfolder"));

        Files.write(root.resolve("a.txt"),
                Arrays.asList("Hello Java"));

        Files.write(root.resolve("subfolder/b.txt"),
                Arrays.asList("Java NIO"));

        List<String> report = new ArrayList<>();

        report.add("FOLDER FILE REPORT");
        report.add("==================");

        List<Path> files;

        try (Stream<Path> paths = Files.walk(root)) {
            files = paths
                    .filter(Files::isRegularFile)
                    .sorted()
                    .collect(Collectors.toList());
        }

        for (Path file : files) {
            BasicFileAttributes attr =
                    Files.readAttributes(file, BasicFileAttributes.class);

            report.add("Name: " + root.relativize(file));
            report.add("Size: " + attr.size() + " bytes");
            report.add("Modified: " + attr.lastModifiedTime());
            report.add("------------------");
        }

        Path output = Paths.get("file_report.txt");
        Files.write(output, report, StandardCharsets.UTF_8);

        System.out.println("Report generated successfully!");
        System.out.println("Saved to: " + output.toAbsolutePath());
    }
}