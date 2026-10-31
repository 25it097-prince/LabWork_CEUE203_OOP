import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {
    @NotBlank @MaxLength(10)
    private String username;

    @NotBlank
    private String email;

    @MaxLength(8)
    private String password;

    public SignupForm(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

class Validator {
    public static List<String> validate(Object obj) throws IllegalAccessException {
        List<String> errors = new ArrayList<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);

            if (field.isAnnotationPresent(NotBlank.class)) {
                if (value == null || value.toString().isBlank()) {
                    errors.add(field.getName() + " must not be blank");
                }
            }

            if (field.isAnnotationPresent(MaxLength.class)) {
                int max = field.getAnnotation(MaxLength.class).value();
                if (value != null && value.toString().length() > max) {
                    errors.add(field.getName() + " is too long (max " + max
                            + ", was " + value.toString().length() + ")");
                }
            }
        }
        return errors;
    }
}

public class FormValidator {
    public static void main(String[] args) throws Exception {
        SignupForm good = new SignupForm("asha", "asha@mail.com", "secret12");
        SignupForm bad  = new SignupForm("", "   ", "123456789012");

        System.out.println("Good form: " + Validator.validate(good));
        System.out.println("Bad form:");
        for (String error : Validator.validate(bad)) {
            System.out.println("  - " + error);
        }
    }
}
