class FileResource implements AutoCloseable {
    private String fileName;
    public FileResource(String fileName) {
        this.fileName = fileName;
        System.out.println("Resource opened: " + fileName);
    }
    public void readFile() {
        System.out.println("Reading file: " + fileName);
        throw new RuntimeException("Error while reading the file!");
    }
    @Override
    public void close() throws Exception {
        System.out.println("Resource closing: " + fileName);

        throw new Exception("Error while closing the resource!");
    }
}
public class AutoCloseDemo {
    public static void main(String[] args) {
        try (FileResource resource =
                new FileResource("data.txt")) {

            System.out.println("Inside try-with-resources");

            resource.readFile();

        } catch (Exception e) {
            System.out.println("\nException caught:");
            System.out.println("Original error: " + e.getMessage());
            System.out.println("\nSuppressed exceptions:");
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println(
                    "Suppressed: " + suppressed.getMessage()
                );
            }
        }
        System.out.println("\nProgram continues normally.");
    }
}