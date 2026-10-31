import java.util.*;
import java.util.jar.*;
import java.io.*;
import java.nio.file.*;


public class DiscountEngine {
    @FunctionalInterface
    interface DiscountRule {
        double apply(double price);
    }

    static final class Engine {

        private final List<DiscountRule> rules;

        Engine(List<DiscountRule> rules) {
            this.rules = List.copyOf(rules);
        }

        List<Double> apply(List<Double> prices) {
            List<Double> result = new ArrayList<>();

            for (double price : prices) {
                double discounted = price;

                for (DiscountRule rule : rules) {
                    discounted = rule.apply(discounted);
                }

                result.add(round(discounted));
            }

            return result;
        }

        private static double round(double value) {
            return Math.round(value * 100.0) / 100.0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<DiscountRule> rules = new ArrayList<>();

        System.out.println("=== Discount Engine ===");
        System.out.println("Available rules:");
        System.out.println("1. 10% off");
        System.out.println("2. 20% off");
        System.out.println("3. Flat $5 off");
        System.out.println("4. 50% off");
        System.out.println("5. No discount");
        System.out.println();
        System.out.print("Choose rules separated by commas (e.g. 1,3): ");

        String input = scanner.nextLine();

        for (String choice : input.split(",")) {
            switch (choice.trim()) {
                case "1" -> rules.add(price -> price * 0.90);
                case "2" -> rules.add(price -> price * 0.80);
                case "3" -> rules.add(price -> Math.max(0, price - 5));
                case "4" -> rules.add(price -> price * 0.50);
                case "5" -> rules.add(price -> price);
                default -> System.out.println(
                        "Ignoring unknown rule: " + choice.trim()
                );
            }
        }

        System.out.print("Enter prices separated by commas (e.g. 100,50,25): ");

        String priceInput = scanner.nextLine();

        List<Double> prices = new ArrayList<>();

        for (String value : priceInput.split(",")) {
            try {
                prices.add(Double.parseDouble(value.trim()));
            } catch (NumberFormatException e) {
                System.out.println("Ignoring invalid price: " + value.trim());
            }
        }

        Engine engine = new Engine(rules);
        List<Double> discountedPrices = engine.apply(prices);

        System.out.println();
        System.out.println("Original prices:   " + prices);
        System.out.println("Discounted prices: " + discountedPrices);
    }
}
