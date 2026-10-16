# Student Information System

This Java application manages student records with a simple menu-driven interface.

## Features
- Add a new student
- View all students
- Search for a student by ID
- Update student information
- Delete a student
- Input validation for IDs, names, ages, grades, and contacts

## Project Structure
- `Student.java` - Student entity with encapsulated fields
- `ValidationUtils.java` - Validation helper methods
- `StudentManager.java` - Business logic for student management
- `StudentInformationSystem.java` - Console menu and user interactions

## Compile and Run
```bash
javac *.java
java StudentInformationSystem
```

## Sample Data
The app loads the following default records on startup:
- STU-101 - Alice Johnson - Age 18 - Grade 92.5
- STU-102 - Brian Smith - Age 19 - Grade 88.0
- STU-103 - Chloe Davis - Age 20 - Grade 95.7

## Test Cases
1. Add a valid student: `STU-104`, `Daniel Lee`, age `21`, grade `90`, contact `daniel@example.com`
2. Try duplicate ID: add `STU-101` again and confirm it is blocked
3. Invalid age: enter `0` or `-1` and verify validation message
4. Invalid grade: enter `101` and verify validation message
5. Search for `STU-102` and confirm the student appears
6. Update `STU-103` contact to a new value and confirm the update works
7. Delete `STU-104` and confirm the student is removed

## Notes
- Student IDs use the format `STU-123`
- Grade must be between `0` and `100`
- Contact can be a phone number or email address
