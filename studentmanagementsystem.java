student.java

package studentmanagementsystem;
public class Student {
    private String name;
    private String rollnumber;
    private String grade;
    private long phonenumber;
    private int section;
    private String brach;
    private String gender;
    public Student(String name, String rollnumber, String grade,long phonenumber,int section,String brach,String gender) {
        this.name = name;
        this.rollnumber = rollnumber;
        this.grade = grade;
        this.phonenumber = phonenumber;
        this. section  = section ;
        this. brach =brach;
        this. gender=gender ;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getrollnumber() {
        return rollnumber;
    }

    public void setrollnumber(String rollnumber) {
        this.rollnumber = rollnumber;
    }

    public String getgrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    public long getphonenumber() {
        return phonenumber;
    }

    public void setphonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
    }
    
    public int getsection() {
        return section;
    }

    public void setsection(int section) {
        this.section = section;
    }
    public String getbrach() {
        return brach;
    }

    public void set(String brach ) {
        this.brach = brach ;
    }
    public String getgender() {
        return gender;
    }

    public void setgender(String gender) {
        this.gender = gender;
    }
   
    public String toString() {
        return "Student [name=" + name + ", rollnumber=" + rollnumber + ", grade=" + grade + ",phonrnumber=" +phonenumber+",section="+section+",brach="+brach+",gender="+gender+"]";
    } 
          
}





studentmanagementsystem.java

package studentmanagementsystem ;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;

public class StudentManagementSystem1 {
    private static final String rollnumber = "a";
	private Connection connection;
    private List<Student> student=new ArrayList<>();

    public StudentManagementSystem1() {
        try {
            // Establish connection to the database
            String url = "jdbc:mysql://localhost:3306/student_management_system"; // Database URL
            String username = "root";  // Replace with your DB username
            String password = "root";      // Replace with your DB password
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Connected to the database!");
            }
student = new ArrayList<>();
            loadStudentsFromDatabase(); // Load existing students from DB
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
    private void loadStudentsFromDatabase() {
        try {
            String query = "SELECT * FROM student";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("name");
                String rollnumber = rs.getString("rollnumber");
                String grade = rs.getString("grade");
                long phonenumber = rs.getLong("phonenumber");
                int section = rs.getInt("section");
                String brach = rs.getString("brach");
                String gender=  rs.getString("gender");
				student.add(new Student(name,rollnumber, grade,phonenumber,section,brach,gender ));
            }
        } catch (SQLException e) {
            System.out.println("Error loading student: " + e.getMessage());
        }
    }

    // Add a new student to the database and list
    private List<Student> studentList = new ArrayList<>();
    public void addStudent(Student newstudent) {
        try {
            String query = "INSERT INTO student (name, rollnumber, grade,phonenumber,section,brach,gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, newstudent.getName());
            stmt.setString(2, newstudent.getrollnumber());
            stmt.setString(3, newstudent.getgender());
            stmt.setLong(4, newstudent.getphonenumber());
            stmt.setInt(5, newstudent.getsection());
            stmt.setString(6, newstudent.getbrach());
            stmt.setString(7, newstudent.getgender());

            stmt.executeUpdate();

            // Add student to the list
            studentList.add(newstudent);
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    // Remove a student by roll number from the database and list
    public void removeStudent(String rollnumber) {
        try {
            String query = "DELETE FROM student WHERE rollnumber = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, rollnumber);
            stmt.executeUpdate();

            // Remove student from the list
            student.removeIf(s -> s.getrollnumber().equals(rollnumber));
            System.out.println("Student removed successfully.");
        } catch (SQLException e) {
            System.out.println("Error removing student: " + e.getMessage());
        }
    }

    // Search for a student by roll number
    public Student searchStudent(String rollnumber) {
        for (Student student : student) {
            if (student.getrollnumber().equals(rollnumber)) {
                return student;
            }
        }
        return null;
    }
    
    // Display all students
    public void displayStudents() {
        if (student.isEmpty()) {
            System.out.println("No student found.");
        } else {
            for (Student student : student) {
                System.out.println(student);
            }
        }
    }
}








package studentmanagementsystem;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        StudentManagementSystem1 system = new StudentManagementSystem1(); // This now connects to the database
        Scanner scanner = new Scanner(System.in);
        int choice;
       
        do {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter rollnumber: ");
                    String rollnumber = scanner.next();
                    System.out.print("Enter grade: ");
                    String grade = scanner.next();
                    System.out.print("Enter phonenumber: ");
                    long phonenumber = scanner.nextLong();
                    System.out.print("Enter section: ");
                    int section = scanner.nextInt();
                    System.out.print("Enter brach: ");
                    String brach = scanner.next();
                    System.out.print("Enter gender: ");
                    String gender = scanner.next();
                    system.addStudent(new Student(name,rollnumber, grade, phonenumber,section,brach,gender));
                    break;

                case 2:
                    System.out.print("Enter rollnumber to remove: ");
                    String removerollnumber = scanner.next();
                    system.removeStudent(removerollnumber);
                    break;

                case 3:
                	System.out.print("Enter rollnumber to search: ");
                	String searchrollnumber = scanner.next(); // Use next() for string input
                	Student foundStudent = system.searchStudent(searchrollnumber); // Pass the string to the search method
                	if (foundStudent != null) {
                	    System.out.println("Student found: " + foundStudent);
                	} else {
                	    System.out.println("Student not found.");
                	}

                    break;

                case 4:
                    system.displayStudents();
                    break;

                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}

