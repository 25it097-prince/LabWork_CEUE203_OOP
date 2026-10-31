public class Boundedstates {

    public static <N extends Number> double average(N[] values) {
        double sum = 0;
        for (N v : values) {
            sum += v.doubleValue();
        }
        return sum / values.length;
    }

    public static void main(String[] args) {
        Integer[] ints    = {1, 2, 3, 4, 5};
        Double[]  doubles = {1.5, 2.5, 3.5};
        Long[]    longs   = {10L, 20L, 30L};

        System.out.println("Integer avg: " + average(ints));
        System.out.println("Double avg:  " + average(doubles));
        System.out.println("Long avg:    " + average(longs));

        // String[] words = {"a", "b"};
        // average(words);   // COMPILE ERROR: String is not a Number
    }
}
