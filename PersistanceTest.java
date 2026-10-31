import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class PersistenceTest {
    @Test
    void savesAndReloadsDataUnchanged() throws Exception {
        ArrayList<String> before = new ArrayList<>();
        before.add("Yug:1000");
        before.add("Riya:1500");
        File file = File.createTempFile("minibank", ".dat");
        try {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(before);
            }
            ArrayList<String> after;
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                after = (ArrayList<String>) in.readObject();
            }
            assertEquals(before, after);
        } finally { file.delete(); }
    }
}
