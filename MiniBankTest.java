import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MiniBankTest {
    @Test
    void addingAccountIncreasesCount() {
        BankService service = new BankService();
        assertEquals(0, service.count());
        service.addAccount(new Account("Raj", 1000));
        assertEquals(1, service.count());
    }
    @Test
    void addingTwoAccountsIncreasesCountTwice() {
        BankService service = new BankService();
        service.addAccount(new Account("Raj", 1000));
        service.addAccount(new Account("Krish", 1500));
        assertEquals(2, service.count());
    }
    @Test
    void invalidActionThrows() {
        BankService service = new BankService();
        assertThrows(IllegalArgumentException.class,
                () -> service.performAction("DELETE"));
    }
}
