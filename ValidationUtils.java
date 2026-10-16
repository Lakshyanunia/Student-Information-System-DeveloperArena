public final class ValidationUtils {
    private ValidationUtils() {
        
    }

    /**
     * Validates the format of a student ID, such as STU-101.
     */
    public static boolean isValidStudentId(String studentId) {
        if (studentId == null) {
            return false;
        }
        return studentId.trim().matches("STU-\\d{3,}");
    }

    /**
     * Validates a student name.
     */
    public static boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        String cleanName = name.trim();
        return cleanName.length() >= 2 && cleanName.length() <= 50
                && cleanName.matches("[A-Za-z\\s'.-]+");
    }

    /**
     * Validates age between 1 and 120.
     */
    public static boolean isValidAge(int age) {
        return age >= 1 && age <= 120;
    }

    /**
     * Validates grade range from 0 to 100.
     */
    public static boolean isValidGrade(double grade) {
        return grade >= 0 && grade <= 100;
    }

    /**
     * Validates a phone number or email address.
     */
    public static boolean isValidContact(String contact) {
        if (contact == null) {
            return false;
        }
        String value = contact.trim();
        return !value.isEmpty() && (
                value.matches("\\+?[0-9\\s-]{7,15}")
                        || value.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        );
    }
}
