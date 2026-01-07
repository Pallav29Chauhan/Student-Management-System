package src.sms.app;

import src.sms.model.Student;
import src.sms.service.StudentService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Welcome to Student Management System ===");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // clear invalid input
                continue;
            }

            switch (choice) {

                case 1: // Add Student
                    try {
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Department: ");
                        String dept = sc.nextLine();

                        System.out.print("Enter Marks (0-100): ");
                        int marks = sc.nextInt();
                        sc.nextLine(); // consume newline

                        service.createStudent(name, dept, marks);
                        System.out.println(" Student added successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(" Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println(" Unexpected error occurred.");
                        e.printStackTrace();
                    }
                    break;

                case 2: // View All Students
                    List<Student> students = service.fetchStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("ID | Name | Department | Marks");
                        System.out.println("-------------------------------");
                        for (Student s : students) {
                            System.out.println(
                                    s.getId() + " | " +
                                            s.getName() + " | " +
                                            s.getDepartment() + " | " +
                                            s.getMarks());
                        }
                    }
                    break;

                case 3: // Search by ID
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Student student = service.fetchStudentById(id);
                    if (student != null) {
                        System.out.println("Found: " +
                                student.getId() + " | " +
                                student.getName() + " | " +
                                student.getDepartment() + " | " +
                                student.getMarks());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4: // Update Student
                    System.out.print("Enter Student ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    try {
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();

                        System.out.print("Enter New Department: ");
                        String newDept = sc.nextLine();

                        System.out.print("Enter New Marks (0-100): ");
                        int newMarks = sc.nextInt();
                        sc.nextLine();

                        boolean updated = service.updateStudent(updateId, newName, newDept, newMarks);
                        if (updated) {
                            System.out.println(" Student updated successfully.");
                        } else {
                            System.out.println(" Student not found.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(" Error: " + e.getMessage());
                    }
                    break;

                case 5: // Delete Student
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();
                    boolean deleted = service.deleteStudent(deleteId);
                    if (deleted) {
                        System.out.println(" Student deleted successfully.");
                    } else {
                        System.out.println(" Student not found.");
                    }
                    break;

                case 6: // Exit
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Enter 1-6.");
            }
        }
    }
}
