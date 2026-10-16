import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages a list of students in memory.
 */
public class StudentManager {
    private final List<Student> students = new ArrayList<>();

    /**
     * Adds a new student if the student ID is unique.
     */
    public boolean addStudent(Student student) {
        if (student == null) {
            return false;
        }

        if (findStudentById(student.getStudentId()) != null) {
            return false;
        }

        return students.add(student);
    }

    /**
     * Returns the list of all students.
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Finds a student by student ID.
     */
    public Student findStudentById(String studentId) {
        if (studentId == null) {
            return null;
        }

        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId.trim())) {
                return student;
            }
        }
        return null;
    }

    /**
     * Updates an existing student with new details.
     */
    public boolean updateStudent(String studentId, Student updatedStudent) {
        Student existingStudent = findStudentById(studentId);
        if (existingStudent == null || updatedStudent == null) {
            return false;
        }

        if (!existingStudent.getStudentId().equalsIgnoreCase(updatedStudent.getStudentId())
                && findStudentById(updatedStudent.getStudentId()) != null) {
            return false;
        }

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setAge(updatedStudent.getAge());
        existingStudent.setGrade(updatedStudent.getGrade());
        existingStudent.setContact(updatedStudent.getContact());

        if (!existingStudent.getStudentId().equalsIgnoreCase(updatedStudent.getStudentId())) {
            existingStudent.setStudentId(updatedStudent.getStudentId());
        }
        return true;
    }

    /**
     * Deletes a student from the list by their ID.
     */
    public boolean deleteStudent(String studentId) {
        Student student = findStudentById(studentId);
        if (student == null) {
            return false;
        }
        return students.remove(student);
    }

    /**
     * Adds a few example student records for demonstration/testing.
     */
    public void addSampleData() {
        if (!students.isEmpty()) {
            return;
        }

        addStudent(new Student("STU-101", "Alice Johnson", 18, 92.5, "+1234567890"));
        addStudent(new Student("STU-102", "Brian Smith", 19, 88.0, "brian@gmail.com"));
        addStudent(new Student("STU-103", "Chloe Davis", 20, 95.7, "+1987654321"));
    }
}
