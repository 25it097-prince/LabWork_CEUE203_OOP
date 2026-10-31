import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    int rollNo;
    String name;
    double marks;
    transient String password;

    Student(int rollNo, String name, double marks, String password) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
        this.password = password;
    }

    void display() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
        System.out.println("Password: " + password);
        System.out.println();
    }
}

public class StudentSerializationDemo {
    public static void main(String[] args) {
        Student[] students = {
                new Student(101, "Ravi", 85.5, "ravi123"),
                new Student(102, "Amit", 90.0, "amit456"),
                new Student(103, "Neha", 92.5, "neha789")
        };

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("students.dat"))) {

            out.writeObject(students);
            System.out.println("Objects saved successfully.\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream("students.dat"))) {

            Student[] loadedStudents = (Student[]) in.readObject();

            System.out.println("Objects loaded from file:\n");

            for (Student s : loadedStudents) {
                s.display();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}