package src.sms.model;

/**
 * Represents a Student entity in the system.
 * Contains id, name, department, and marks.
 */
public class Student {

    private int id; // Auto-incremented ID in DB
    private String name; // Student name
    private String department; // Department name
    private int marks; // Marks (0-100)

    // Constructor with ID (for fetching/updating existing student)
    public Student(int id, String name, String department, int marks) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    // Constructor without ID (for creating new student)
    public Student(String name, String department, int marks) {
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getMarks() {
        return marks;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    // Optional: Override toString() for easy printing
    @Override
    public String toString() {
        return id + " | " + name + " | " + department + " | " + marks;
    }
}
