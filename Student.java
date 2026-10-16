public class Student {
    private String studentId;
    private String name;
    private int age;
    private double grade;
    private String contact;

   
    public Student(String studentId, String name, int age, double grade, String contact) {
        setStudentId(studentId);
        setName(name);
        setAge(age);
        setGrade(grade);
        setContact(contact);
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        if (!ValidationUtils.isValidStudentId(studentId)) {
            throw new IllegalArgumentException("Student ID must follow the format STU-123.");
        }
        this.studentId = studentId.trim();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!ValidationUtils.isValidName(name)) {
            throw new IllegalArgumentException("Name must be between 2 and 50 characters and contain only valid letters.");
        }
        this.name = name.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (!ValidationUtils.isValidAge(age)) {
            throw new IllegalArgumentException("Age must be between 1 and 120.");
        }
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (!ValidationUtils.isValidGrade(grade)) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
        this.grade = grade;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        if (!ValidationUtils.isValidContact(contact)) {
            throw new IllegalArgumentException("Contact must be a valid phone number or email address.");
        }
        this.contact = contact.trim();
    }

    /**
     * Prints all student details in a readable format.
     */
    public void displayInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Grade: " + grade);
        System.out.println("Contact: " + contact);
        System.out.println(repeat('-', 30));
    }

    private static String repeat(char ch, int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(ch);
        }
        return builder.toString();
    }
}
