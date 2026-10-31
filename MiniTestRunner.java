import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run { }

class MyTests {
    @Run
    public void testAddition() {
        if (2 + 3 != 5) throw new AssertionError("math is broken");
    }

    @Run
    public void testFails() {
        throw new RuntimeException("boom");
    }

    @Run
    public void testStrings() {
        if (!"java".toUpperCase().equals("JAVA")) throw new AssertionError("upper-case failed");
    }

    public void helper() {
        System.out.println("helper() was called (this should never print)");
    }
}

public class MiniTestRunner {

    public static void runTests(Class<?> cls) throws Exception {
        Object instance = cls.getDeclaredConstructor().newInstance();

        Method[] methods = cls.getDeclaredMethods();
        Arrays.sort(methods, Comparator.comparing(Method::getName));

        int ran = 0, passed = 0, failed = 0;

        for (Method m : methods) {
            if (!m.isAnnotationPresent(Run.class)) continue;

            ran++;
            System.out.print("Running " + m.getName() + " ... ");
            try {
                m.invoke(instance);
                System.out.println("PASSED");
                passed++;
            } catch (InvocationTargetException e) {

                System.out.println("FAILED (" + e.getCause().getMessage() + ")");
                failed++;
            }
        }
        System.out.println("Ran " + ran + " test(s): " + passed + " passed, " + failed + " failed");
    }

    public static void main(String[] args) throws Exception {
        runTests(MyTests.class);
    }
}
