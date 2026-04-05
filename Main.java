import java.sql.*;
import java.util.Scanner;

public class Main {

    static final String url = "jdbc:mysql://localhost:3306/resource_monitoring";
    static final String user = "root";
    static final String password = "Root@1234";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\n==== RESOURCE MONITOR MENU ====");
            System.out.println("1. View Logs");
            System.out.println("2. Insert Log");
            System.out.println("3. Update CPU Usage");
            System.out.println("4. Delete Log");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch(choice) {
                case 1: viewLogs(); break;
                case 2: insertLog(sc); break;
                case 3: updateLog(sc); break;
                case 4: deleteLog(sc); break;
                case 5: System.exit(0);
                default: System.out.println("Invalid choice");
            }
        }
    }

    static void viewLogs() {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM resource_logs");

            System.out.println("\n--- LOG DATA ---");

            while(rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("log_id") +
                    " | CPU: " + rs.getInt("cpu_usage") +
                    " | Memory: " + rs.getInt("memory_usage") +
                    " | Disk: " + rs.getInt("disk_usage")
                );
            }

            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    static void insertLog(Scanner sc) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            System.out.print("Enter Log ID: ");
            int id = sc.nextInt();

            System.out.print("Enter System ID: ");
            int sys = sc.nextInt();

            System.out.print("CPU: ");
            int cpu = sc.nextInt();

            System.out.print("Memory: ");
            int mem = sc.nextInt();

            System.out.print("Disk: ");
            int disk = sc.nextInt();

            String query = "INSERT INTO resource_logs VALUES (?, ?, ?, ?, ?, NOW())";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, sys);
            ps.setInt(3, cpu);
            ps.setInt(4, mem);
            ps.setInt(5, disk);

            ps.executeUpdate();

            System.out.println("Inserted successfully!");

            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    static void updateLog(Scanner sc) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            System.out.print("Enter Log ID to update: ");
            int id = sc.nextInt();

            System.out.print("New CPU value: ");
            int cpu = sc.nextInt();

            String query = "UPDATE resource_logs SET cpu_usage = ? WHERE log_id = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, cpu);
            ps.setInt(2, id);

            ps.executeUpdate();

            System.out.println("Updated successfully!");

            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    static void deleteLog(Scanner sc) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            System.out.print("Enter Log ID to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM resource_logs WHERE log_id = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Deleted successfully!");

            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
