import java.sql.*;
import java.util.Scanner;

public class ConferenceAttendeeManager {
    private static final String URL = "jdbc:mysql://localhost:3306/Conf_att";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    // Establish a connection to the database
    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }
        return connection;
    }

    // Close the database connection
    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a new attendee to the database
    public static void addAttendee(String fullName, String email, String contactNumber, String country) {
        String query = "INSERT INTO attendees (full_name, email, contact_number, country) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, contactNumber);
            stmt.setString(4, country);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int attendeeId = rs.getInt(1);
                    System.out.println("Attendee registered successfully with ID: " + attendeeId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to add attendee!");
            e.printStackTrace();
        }
    }

    // Edit an existing attendee's information
    public static void editAttendee(int attendeeId, String email, String contactNumber) {
        String query = "UPDATE attendees SET email = ?, contact_number = ? WHERE attendee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, contactNumber);
            stmt.setInt(3, attendeeId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Attendee information updated successfully.");
            } else {
                System.out.println("Attendee not found.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to edit attendee!");
            e.printStackTrace();
        }
    }

    // Delete an attendee from the database
    public static void deleteAttendee(int attendeeId) {
        String query = "DELETE FROM attendees WHERE attendee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, attendeeId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Attendee deleted successfully.");
            } else {
                System.out.println("Attendee not found.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to delete attendee!");
            e.printStackTrace();
        }
    }

    // Search for an attendee based on ID, Name, or Country
    public static void searchAttendee(String searchValue) {
        String query = "SELECT * FROM attendees WHERE attendee_id = ? OR full_name LIKE ? OR country LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, searchValue);
            stmt.setString(2, "%" + searchValue + "%");
            stmt.setString(3, "%" + searchValue + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int attendeeId = rs.getInt("attendee_id");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                String contactNumber = rs.getString("contact_number");
                String country = rs.getString("country");
                System.out.println("ID: " + attendeeId + ", Name: " + fullName + ", Email: " + email + ", Contact: " + contactNumber + ", Country: " + country);
            }
        } catch (SQLException e) {
            System.out.println("Failed to search attendee!");
            e.printStackTrace();
        }
    }

    // Generate attendee statistics by country using a stored procedure
    public static void generateAttendeeStatistics() {
        String procedureCall = "{CALL GetAttendeeStatistics()}"; // Assumes the stored procedure name is 'GetAttendeeStatistics'
        try (CallableStatement stmt = connection.prepareCall(procedureCall)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String country = rs.getString("country");
                int count = rs.getInt("count");
                System.out.println("Country: " + country + " - Attendees: " + count);
            }
        } catch (SQLException e) {
            System.out.println("Failed to generate attendee statistics!");
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        connection = connect();
        Scanner scanner = new Scanner(System.in);

        // Sample operations
        System.out.println("1. Add Attendee\n2. Edit Attendee\n3. Delete Attendee\n4. Search Attendee\n5. Generate Attendee Statistics");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter Full Name: ");
                String fullName = scanner.nextLine();
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter Contact Number: ");
                String contactNumber = scanner.nextLine();
                System.out.print("Enter Country: ");
                String country = scanner.nextLine();
                addAttendee(fullName, email, contactNumber, country);
                break;
            case 2:
                System.out.print("Enter Attendee ID to edit: ");
                int editId = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                System.out.print("Enter new Email: ");
                String newEmail = scanner.nextLine();
                System.out.print("Enter new Contact Number: ");
                String newContact = scanner.nextLine();
                editAttendee(editId, newEmail, newContact);
                break;
            case 3:
                System.out.print("Enter Attendee ID to delete: ");
                int deleteId = scanner.nextInt();
                deleteAttendee(deleteId);
                break;
            case 4:
                System.out.print("Enter search value (ID, Name, or Country): ");
                String searchValue = scanner.nextLine();
                searchAttendee(searchValue);
                break;
            case 5:
                generateAttendeeStatistics();
                break;
            default:
                System.out.println("Invalid choice!");
        }

        disconnect();
    }
}
