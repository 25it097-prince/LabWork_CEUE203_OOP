import java.util.*;

class InvalidQuantityException extends Exception {

    public InvalidQuantityException(String message) {
        super(message);
    }
}

class OutOfStockException extends Exception {

    private int shortfall;

    public OutOfStockException(String message, int shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class Warehouse {

    private Map<String, Integer> stock = new HashMap<>();

    public void addStock(String item, int quantity) {
        stock.put(item, stock.getOrDefault(item, 0) + quantity);
    }

    public void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException(
                "Quantity must be greater than 0."
            );
        }

        int available = stock.getOrDefault(item, 0);

        if (qty > available) {

            int shortfall = qty - available;

            throw new OutOfStockException(
                "Not enough stock for " + item +
                ". Available: " + available +
                ", Requested: " + qty,
                shortfall
            );
        }

        stock.put(item, available - qty);

        System.out.println(
            "Issued " + qty + " " + item +
            ". Remaining: " + (available - qty)
        );
    }
}
class Request {

    String item;
    int quantity;

    public Request(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
public class StockIssue {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        warehouse.addStock("Laptop", 50);
        warehouse.addStock("Mouse", 30);
        warehouse.addStock("Keyboard", 25);

        List<Request> requests = Arrays.asList(
            new Request("Laptop", 4),
            new Request("Mouse", 10),
            new Request("Keyboard", 8),
            new Request("Laptop", 0),
            new Request("Monitor", 3),
            new Request("Mouse", 5)
        );

        System.out.println("=== Warehouse Stock Issue ===");

        for (Request request : requests) {

            System.out.println(
                "\nRequest: " +
                request.item + " - " +
                request.quantity
            );

            try {

                warehouse.issue(
                    request.item,
                    request.quantity
                );

            } catch (OutOfStockException e) {

                System.out.println(
                    "Out of Stock: " + e.getMessage()
                );

                System.out.println(
                    "Shortfall: " +
                    e.getShortfall() +
                    " units"
                );

            } catch (InvalidQuantityException e) {

                System.out.println(
                    "Invalid Quantity: " +
                    e.getMessage()
                );
            }
        }

        System.out.println("\n=== All Requests Processed ===");
    }
}