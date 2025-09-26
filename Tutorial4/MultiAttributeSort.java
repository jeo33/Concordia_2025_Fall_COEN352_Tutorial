package Tutorial4;
import java.util.*;

class Student {
    String name;
    double gpa;
    int age;
    String major;

    public Student(String name, double gpa, int age, String major) {
        this.name = name;
        this.gpa = gpa;
        this.age = age;
        this.major = major;
    }

    @Override
    public String toString() {
        return String.format("%-7s GPA: %.2f | Age: %d | Major: %s",
                name, gpa, age, major);
    }
}

public class MultiAttributeSort{

    // Custom comparison: Name -> GPA -> Age
    public static int compareStudents(Student s1, Student s2) {
        int nameCmp = s1.name.compareTo(s2.name);
        if (nameCmp != 0) return nameCmp;

        if (s1.gpa != s2.gpa) {
            return (s1.gpa < s2.gpa) ? 1 : -1;  // Higher GPA first
        }

        return Integer.compare(s1.age, s2.age); // Younger first
    }

    public static void bubbleSort(List<Student> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareStudents(list.get(j), list.get(j + 1)) > 0) {
                    // Swap
                    Student temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 3.80, 21, "CS"));
        students.add(new Student("Alice", 3.90, 20, "Math"));
        students.add(new Student("Bob", 3.90, 22, "Math"));
        students.add(new Student("Charlie", 3.90, 20, "Physics"));
        students.add(new Student("David", 3.80, 21, "Math"));
        students.add(new Student("Eve", 3.90, 20, "CS"));
        students.add(new Student("Alice", 3.90, 22, "Physics"));

        System.out.println("=== Input Students ===");
        for (Student s : students) {
            System.out.println(s);
        }

        bubbleSort(students);

        System.out.println("\n=== Sorted Students ===");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
