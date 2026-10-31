import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;
import java.util.stream.*;

public class LogAnalyzerDemo {
    public static void main(String[] args) throws IOException {

        Path logDir = Paths.get("logs");
        Files.createDirectories(logDir);

        Files.write(logDir.resolve("app1.txt"), Arrays.asList(
                "Application started",
                "ERROR: Unable to connect",
                "User logged in"
        ), StandardCharsets.UTF_8);

        Files.write(logDir.resolve("app2.txt"), Arrays.asList(
                "INFO: Request received",
                "WARNING: Slow response",
                "ERROR: Database timeout"
        ), StandardCharsets.UTF_8);

        Files.write(logDir.resolve("app3.txt"), Arrays.asList(
                "User logged out",
                "INFO: Shutdown complete",
                "ERROR: Service unavailable"
        ), StandardCharsets.UTF_8);

        String keyword = "ERROR";
        int totalLines = 0;
        int matchingLines = 0;

        try (Stream<Path> paths = Files.list(logDir)) {

            List<Path> files = paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(".txt"))
                    .sorted()
                    .collect(Collectors.toList());

            for (Path file : files) {

                BasicFileAttributes attrs =
                        Files.readAttributes(file, BasicFileAttributes.class);

                System.out.println(file.getFileName()
                        + " - Size: " + attrs.size() + " bytes");

                try (BufferedReader reader =
                             Files.newBufferedReader(file, StandardCharsets.UTF_8)) {

                    String line;

                    while ((line = reader.readLine()) != null) {
                        totalLines++;

                        if (line.toLowerCase(Locale.ROOT)
                                .contains(keyword.toLowerCase(Locale.ROOT))) {
                            matchingLines++;
                        }
                    }
                }
            }
        }

        System.out.println("\nTotal lines across all files: " + totalLines);
        System.out.println("Lines containing '" + keyword
                + "': " + matchingLines);
    }
}