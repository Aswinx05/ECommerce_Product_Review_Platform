package mini_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class module5 extends JFrame {
    private Connection conn;

    public module5() {
        setTitle("E-Commerce Product Review Platform");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 15, 15));
        JLabel lblTitle = new JLabel("🛍️ E-Commerce Product Review Platform", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnViewProducts = new JButton("📦 View Products");
        JButton btnAddReview = new JButton("📝 Add Review");
        JButton btnViewReviews = new JButton("📄 View Reviews");

        panel.add(lblTitle);
        panel.add(btnViewProducts);
        panel.add(btnAddReview);
        panel.add(btnViewReviews);
        add(panel);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "1234");
            System.out.println("✅ Database Connected Successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ DB Connection Error: " + e.getMessage());
        }

        btnViewProducts.addActionListener(e -> showProducts());
        btnAddReview.addActionListener(e -> openAddReview());
        btnViewReviews.addActionListener(e -> showReviews());
    }

    private void showProducts() {
        JFrame frame = new JFrame("Product List");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(this);
        JTextArea area = new JTextArea();
        area.setEditable(false);
        frame.add(new JScrollPane(area));

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products");
            StringBuilder sb = new StringBuilder("🛒 Product List:\n\n");
            while (rs.next()) {
                sb.append(rs.getInt("id")).append(" | ")
                        .append(rs.getString("name")).append(" | ₹")
                        .append(rs.getDouble("price")).append("\n");
            }
            area.setText(sb.toString());
            stmt.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ DB Error: " + ex.getMessage());
        }

        frame.setVisible(true);
    }

    private void openAddReview() {
        JFrame frame = new JFrame("Add Product Review");
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(this);
        frame.setLayout(new GridLayout(6, 1, 10, 10));

        JTextField productField = new JTextField();
        JTextArea reviewArea = new JTextArea(3, 20);
        JComboBox<Integer> ratingBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        JButton submitBtn = new JButton("Submit Review");

        frame.add(new JLabel("Enter Product Name:"));
        frame.add(productField);
        frame.add(new JLabel("Enter Review:"));
        frame.add(new JScrollPane(reviewArea));
        frame.add(new JLabel("Select Star Rating:"));
        frame.add(ratingBox);
        frame.add(submitBtn);

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS reviews (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "product_name VARCHAR(100), " +
                "review_text VARCHAR(255), " +
                "rating INT)"
            );
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "❌ Table Creation Error: " + e.getMessage());
        }

        submitBtn.addActionListener(e -> {
            String product = productField.getText().trim();
            String review = reviewArea.getText().trim();
            int rating = (int) ratingBox.getSelectedItem();

            if (product.isEmpty() || review.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "⚠️ Please fill all fields.");
                return;
            }

            try {
                PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO reviews (product_name, review_text, rating) VALUES (?, ?, ?)"
                );
                pstmt.setString(1, product);
                pstmt.setString(2, review);
                pstmt.setInt(3, rating);
                pstmt.executeUpdate();
                pstmt.close();

                JOptionPane.showMessageDialog(frame, "✅ Review added successfully!");
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "❌ DB Error: " + ex.getMessage());
            }
        });

        frame.setVisible(true);
    }

    private void showReviews() {
        JFrame frame = new JFrame("View Reviews");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(this);
        JTextArea area = new JTextArea();
        area.setEditable(false);
        frame.add(new JScrollPane(area));

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reviews");
            StringBuilder sb = new StringBuilder("⭐ Product Reviews ⭐\n\n");
            while (rs.next()) {
                sb.append("Product: ").append(rs.getString("product_name")).append("\n");
                sb.append("Review : ").append(rs.getString("review_text")).append("\n");
                sb.append("Rating : ").append("⭐".repeat(rs.getInt("rating")))
                  .append(" (" + rs.getInt("rating") + ")\n");
                sb.append("------------------------------------\n");
            }
            area.setText(sb.toString());
            stmt.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "❌ DB Error: " + ex.getMessage());
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new module5().setVisible(true));
    }
}
