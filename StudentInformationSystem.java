import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Main application for managing student information.
 */
public class StudentInformationSystem {
    private static final StudentManager manager = new StudentManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        manager.addSampleData();
        boolean running = true;
        while (running) {
            System.out.println("\n=== STUDENT INFORMATION SYSTEM ===");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    addStudentFromInput();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using the Student Information System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Adds a student using values entered by the user.
     */
    private static void addStudentFromInput() {
        System.out.println("\n=== ADD NEW STUDENT ===");

        String studentId = readRequiredText("Enter Student ID (format STU-123): ");
        if (manager.findStudentById(studentId) != null) {
            System.out.println("A student with this ID already exists.");
            return;
        }

        String name = readRequiredText("Enter Name: ");
        int age = readInt("Enter Age: ");
        double grade = readDouble("Enter Grade: ");
        String contact = readRequiredText("Enter Contact: ");

        try {
            Student student = new Student(studentId, name, age, grade, contact);
            if (manager.addStudent(student)) {
                System.out.println("Student added successfully!");
            } else {
                System.out.println("Unable to add student.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Shows all students in a table-like format.
     */
    private static void viewAllStudents() {
        System.out.println("\n=== ALL STUDENTS ===");
        List<Student> students = manager.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.printf("%-15s %-20s %-8s %-8s %-15s%n",
                "Student ID", "Name", "Age", "Grade", "Contact");
        System.out.println(repeat('-', 70));

        for (Student student : students) {
            System.out.printf("%-15s %-20s %-8d %-8.2f %-15s%n",
                    student.getStudentId(),
                    student.getName(),
                    student.getAge(),
                    student.getGrade(),
                    student.getContact());
        }
    }

    /**
     * Searches for and prints a student by ID.
     */
    private static void searchStudent() {
        System.out.println("\n=== SEARCH STUDENT ===");
        String studentId = readRequiredText("Enter Student ID: ");
        Student student = manager.findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.displayInfo();
    }

    /**
     * Updates a student's information.
     */
    private static void updateStudent() {
        System.out.println("\n=== UPDATE STUDENT ===");
        String studentId = readRequiredText("Enter Student ID to update: ");
        Student existing = manager.findStudentById(studentId);

        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Enter new details. Press Enter to keep existing values.");

        String newName = readOptionalText("Enter new Name: ");
        int newAge = readIntWithDefault("Enter new Age: ", existing.getAge());
        double newGrade = readDoubleWithDefault("Enter new Grade: ", existing.getGrade());
        String newContact = readOptionalText("Enter new Contact: ");
        String newStudentId = readOptionalText("Enter new Student ID (format STU-123): ");

        try {
            Student updatedStudent = new Student(
                    newStudentId.isEmpty() ? existing.getStudentId() : newStudentId,
                    newName.isEmpty() ? existing.getName() : newName,
                    newAge,
                    newGrade,
                    newContact.isEmpty() ? existing.getContact() : newContact
            );

            if (!manager.updateStudent(existing.getStudentId(), updatedStudent)) {
                System.out.println("Update failed. The new student ID may already exist.");
                return;
            }

            if (!newStudentId.isEmpty() && !newStudentId.equalsIgnoreCase(existing.getStudentId())) {
                System.out.println("Student ID updated successfully.");
            }
            System.out.println("Student updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Deletes a student using their ID.
     */
    private static void deleteStudent() {
        System.out.println("\n=== DELETE STUDENT ===");
        String studentId = readRequiredText("Enter Student ID to delete: ");

        if (manager.deleteStudent(studentId)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static String readRequiredText(String prompt) {
        String value;
        do {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        } while (true);
    }

    private static String readOptionalText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String repeat(char ch, int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(ch);
        }
        return builder.toString();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid numeric grade.");
            }
        }
    }

    private static int readIntWithDefault(String prompt, int defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer. Keeping the previous value.");
            return defaultValue;
        }
    }

    private static double readDoubleWithDefault(String prompt, double defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Keeping the previous value.");
            return defaultValue;
        }
    }
}
