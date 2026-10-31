class MyStack<T> {
    private Object[] data;
    private int size;

    public MyStack(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public boolean isEmpty() { return size == 0; }

    public void push(T item) {
        if (size == data.length) {
            System.out.println("Stack is full! Cannot push " + item);
            return;
        }
        data[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Nothing to pop.");
            return null;
        }
        T item = (T) data[--size];
        data[size] = null;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty! Nothing to peek.");
            return null;
        }
        return (T) data[size - 1];
    }
}

public class GenericsStack {
    public static void main(String[] args) {
        MyStack<Integer> intStack = new MyStack<>(3);
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);
        intStack.push(4);
        System.out.println("Popped: " + intStack.pop());
        System.out.println("Peek:   " + intStack.peek());

        MyStack<String> strStack = new MyStack<>(2);
        strStack.push("Java");
        strStack.push("Generics");
        System.out.println("Popped: " + strStack.pop());
        System.out.println("Popped: " + strStack.pop());
        strStack.pop();
    }
}
