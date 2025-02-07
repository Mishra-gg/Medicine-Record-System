import java.sql.*;
import java.util.*;

public class MedicineDAO {
    public void addMedicine(Medicine medicine) throws SQLException {
        String sql = "INSERT INTO Medicine (name, manufacturer, price, quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medicine.getName());
            stmt.setString(2, medicine.getManufacturer());
            stmt.setDouble(3, medicine.getPrice());
            stmt.setInt(4, medicine.getQuantity());
            stmt.executeUpdate();
        }
    }

    public List<Medicine> getAllMedicines() throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM Medicine";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Medicine medicine = new Medicine();
                medicine.setId(rs.getInt("id"));
                medicine.setName(rs.getString("name"));
                medicine.setManufacturer(rs.getString("manufacturer"));
                medicine.setPrice(rs.getDouble("price"));
                medicine.setQuantity(rs.getInt("quantity"));

                medicines.add(medicine);
            }
        }
        return medicines;
    }

    public void updateMedicine(Medicine medicine) throws SQLException {
        String sql = "UPDATE Medicine SET name = ?, manufacturer = ?, price = ?, quantity = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medicine.getName());
            stmt.setString(2, medicine.getManufacturer());
            stmt.setDouble(3, medicine.getPrice());
            stmt.setInt(4, medicine.getQuantity());
            stmt.setInt(5, medicine.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteMedicine(int id) throws SQLException {
        String sql = "DELETE FROM Medicine WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}