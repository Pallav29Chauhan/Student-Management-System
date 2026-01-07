package src.sms.dao;

import src.sms.model.Student;
import src.sms.util.DBConnection;
import src.sms.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Add a new student
    public void addStudent(Student student) {
        String sql = "INSERT INTO students(name, department, marks) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getDepartment());
            ps.setInt(3, student.getMarks());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to add student.", e);
        }
    }

    // Delete student by ID
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // true if deleted, false if ID not found

        } catch (SQLException e) {
            throw new DataAccessException("Failed to delete student", e);
        }
    }

    // Get all students
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getInt("marks")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch students.", e);
        }

        return list;
    }

    // Get student by ID
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("department"),
                            rs.getInt("marks"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch student by ID.", e);
        }
        return null; // not found
    }

    // Update student
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, department = ?, marks = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getDepartment());
            ps.setInt(3, student.getMarks());
            ps.setInt(4, student.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch students", e);
        }
    }
}

// Dele
