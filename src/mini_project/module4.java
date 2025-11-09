package mini_project;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class module4 extends JFrame {
    private Connection conn;

    public module4() {
        setTitle("E-Commerce Product Setup");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea));

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "1234");
            textArea.append("✅ Database Connected Successfully!\n");

            // Create table if not exists
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS products (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "price DOUBLE)"
            );
            textArea.append("✅ Table 'products' ready.\n\n");

            // Insert products only if table empty
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM products");
            rs.next();
            int count = rs.getInt(1);
            if (count == 0) {
                stmt.executeUpdate("INSERT INTO products (name, price) VALUES ('Laptop', 65000.0)");
                stmt.executeUpdate("INSERT INTO products (name, price) VALUES ('Smartphone', 25000.0)");
                stmt.executeUpdate("INSERT INTO products (name, price) VALUES ('Headphones', 3000.0)");
                textArea.append("✅ Products inserted successfully!\n\n");
            }

            // Display all products
            textArea.append("🛒 Product List:\n");
            rs = stmt.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                textArea.append(rs.getInt("id") + " | " +
                        rs.getString("name") + " | ₹" +
                        rs.getDouble("price") + "\n");
            }

            stmt.close();
        } catch (Exception e) {
            textArea.append("❌ DB Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new module4().setVisible(true));
    }
}
