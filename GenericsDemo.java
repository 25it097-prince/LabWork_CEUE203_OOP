class Box<T> {
    private T value;

    public void set(T value) { this.value = value; }
    public T get() { return value; }
}

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst()  { return first; }
    public B getSecond() { return second; }
}

public class GenericsDemo {

    public static <T> T firstElement(T[] arr) {
        return arr[0];
    }

    public static void main(String[] args) {
        Box<String> nameBox = new Box<>();
        nameBox.set("Hello");
        String s = nameBox.get();
        System.out.println("Box<String>: " + s);

        Box<Integer> numBox = new Box<>();
        numBox.set(42);
        int n = numBox.get();
        System.out.println("Box<Integer>: " + n);

        Pair<String, Integer> p = new Pair<>("Age", 20);
        System.out.println("Pair: " + p.getFirst() + " = " + p.getSecond());

        Integer[] ints = {10, 20, 30};
        String[] words = {"Java", "Generics"};
        System.out.println("First int: " + firstElement(ints));
        System.out.println("First word: " + firstElement(words));
    }
}
