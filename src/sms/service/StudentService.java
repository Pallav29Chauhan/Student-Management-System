package src.sms.service;

import src.sms.dao.StudentDAO;
import src.sms.model.Student;

import java.util.List;

public class StudentService {

    private final StudentDAO dao = new StudentDAO();

    // Create a new student
    public void createStudent(String name, String dept, int marks) {
        validateMarks(marks);
        dao.addStudent(new Student(name, dept, marks));
    }

    // Fetch all students
    public List<Student> fetchStudents() {
        return dao.getAllStudents();
    }

    // Fetch student by ID
    public Student fetchStudentById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid student ID.");
        }
        return dao.getStudentById(id);
    }

    // Update existing student
    public boolean updateStudent(int id, String name, String dept, int marks) {
        validateMarks(marks);
        if (id <= 0)
            return false;
        Student student = new Student(id, name, dept, marks);
        return dao.updateStudent(student);
    }

    // Delete student by ID
    public boolean deleteStudent(int id) {
        if (id <= 0)
            return false;
        return dao.deleteStudent(id);
    }

    // Validate marks input
    private void validateMarks(int marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
    }
}
