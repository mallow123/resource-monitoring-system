package dao;

import java.sql.*;
import model.ResourceLog;

public class ResourceDAO {

    public boolean systemExists(int sysId) throws Exception {
        Connection conn = DBConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM systems WHERE system_id = ?"
        );
        ps.setInt(1, sysId);

        ResultSet rs = ps.executeQuery();
        boolean exists = rs.next();

        conn.close();
        return exists;
    }

    public void insert(ResourceLog r) throws Exception {
        if(!systemExists(r.getSystemId())) {
            System.out.println("❌ Invalid System ID!");
            return;
        }

        Connection conn = DBConnection.getConnection();

        String query = "INSERT INTO resource_logs VALUES (?, ?, ?, ?, ?, NOW())";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, r.getLogId());
        ps.setInt(2, r.getSystemId());
        ps.setInt(3, r.getCpu());
        ps.setInt(4, r.getMemory());
        ps.setInt(5, r.getDisk());

        ps.executeUpdate();
        System.out.println("✅ Log Inserted!");

        if(r.getCpu() > 80) {
            System.out.println("⚠ ALERT: HIGH CPU USAGE!");
        }

        conn.close();
    }

    public void view() throws Exception {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM resource_logs");

        System.out.println("\n--------------------------------------------------");
        System.out.printf("%-5s %-10s %-10s %-10s\n", "ID", "CPU", "MEM", "DISK");
        System.out.println("--------------------------------------------------");

        while(rs.next()) {
            System.out.printf(
                "%-5d %-10d %-10d %-10d\n",
                rs.getInt("log_id"),
                rs.getInt("cpu_usage"),
                rs.getInt("memory_usage"),
                rs.getInt("disk_usage")
            );
        }

        System.out.println("--------------------------------------------------");

        conn.close();
    }

    public void viewDetailed() throws Exception {
        Connection conn = DBConnection.getConnection();

        String query =
        "SELECT s.system_name, rl.cpu_usage, rl.memory_usage, rl.disk_usage " +
        "FROM resource_logs rl JOIN systems s ON rl.system_id = s.system_id";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n================ SYSTEM RESOURCE TABLE ================");
        System.out.printf("%-15s %-10s %-10s %-10s\n", "SYSTEM", "CPU", "MEM", "DISK");
        System.out.println("------------------------------------------------------");

        while(rs.next()) {
            System.out.printf(
                "%-15s %-10d %-10d %-10d\n",
                rs.getString("system_name"),
                rs.getInt("cpu_usage"),
                rs.getInt("memory_usage"),
                rs.getInt("disk_usage")
            );
        }

        System.out.println("======================================================");

        conn.close();
    }

    public void showHighCPU() throws Exception {
        Connection conn = DBConnection.getConnection();

        String query =
        "SELECT system_id, cpu_usage FROM resource_logs WHERE cpu_usage > 80";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("\n🔥 HIGH CPU SYSTEMS 🔥");
        System.out.printf("%-10s %-10s\n", "SYSTEM", "CPU");
        System.out.println("----------------------");

        while(rs.next()) {
            System.out.printf(
                "%-10d %-10d\n",
                rs.getInt("system_id"),
                rs.getInt("cpu_usage")
            );
        }

        conn.close();
    }
}
