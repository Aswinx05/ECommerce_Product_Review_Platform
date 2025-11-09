// File: module1.java
package mini_project;

abstract class User {
    protected String username;
    public User(String username) { this.username = username; }
    public abstract void displayRole();
}

class Customer extends User {
    public Customer(String username) { super(username); }
    @Override
    public void displayRole() { System.out.println("Customer: " + username); }
}

class Admin extends User {
    public Admin(String username) { super(username); }
    @Override
    public void displayRole() { System.out.println("Admin: " + username); }
}

class Product {
    private String name;
    private double price;
    public Product(String name, double price) { this.name = name; this.price = price; }
    public void showProduct() { System.out.println(name + " - ₹" + price); }
}

public class module1 {
    public static void main(String[] args) {
        User u1 = new Customer("Aswin");
        User u2 = new Admin("Ananth");

        u1.displayRole();
        u2.displayRole();

        Product p1 = new Product("Laptop", 55000);
        Product p2 = new Product("Smartphone", 20000);

        p1.showProduct();
        p2.showProduct();
    }
}
