import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String value();
}

class Student {
    @Column("id")    private int id;
    @Column("name")  private String name;
    @Column("marks") private double marks;

    @Override
    public String toString() {
        return "Student{id=" + id + ", name=" + name + ", marks=" + marks + "}";
    }
}

class RowMapper {

    public static <T> T map(Class<T> cls, String[] header, String[] row, boolean strict)
            throws Exception {

        T obj = cls.getDeclaredConstructor().newInstance();

        Map<String, Integer> position = new HashMap<>();
        for (int i = 0; i < header.length; i++) {
            position.put(header[i].trim(), i);
        }

        for (Field field : cls.getDeclaredFields()) {
            Column col = field.getAnnotation(Column.class);
            if (col == null) continue;

            Integer i = position.get(col.value());
            if (i == null || i >= row.length) {
                String msg = "Column '" + col.value() + "' not found for field '" + field.getName() + "'";
                if (strict) throw new IllegalArgumentException(msg);
                System.out.println("Warning: " + msg + " -> using default value");
                continue;
            }

            field.setAccessible(true);
            String text = row[i].trim();

            if (field.getType() == int.class)         field.setInt(obj, Integer.parseInt(text));
            else if (field.getType() == double.class) field.setDouble(obj, Double.parseDouble(text));
            else                                      field.set(obj, text);
        }
        return obj;
    }
}

public class Task3 {
    public static void main(String[] args) throws Exception {
        String[] header1 = {"id", "name", "marks"};
        String[] row1    = {"1", "Asha", "88.5"};
        System.out.println(RowMapper.map(Student.class, header1, row1, true));

        String[] header2 = {"marks", "id", "name"};
        String[] row2    = {"72", "2", "Ravi"};
        System.out.println(RowMapper.map(Student.class, header2, row2, true));

        String[] header3 = {"id", "name"};
        String[] row3    = {"3", "Meera"};
        System.out.println(RowMapper.map(Student.class, header3, row3, false));

        try {
            RowMapper.map(Student.class, header3, row3, true);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
