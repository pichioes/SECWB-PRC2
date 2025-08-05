package Controller;

import Model.History;
import Model.Logs;
import Model.Product;
import Model.User;
import java.sql.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.security.MessageDigest;

public class SQLite {
    
    public int DEBUG_MODE = 0;
    String driverURL = "jdbc:sqlite:" + "database.db";
    
    // Connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(driverURL);
    }
    
    // Password hashing 
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
    public void createNewDatabase() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Database database.db created.");
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    // Creates the history table for purchase logs
    public void createHistoryTable() {
        String sql = "CREATE TABLE IF NOT EXISTS history (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " username TEXT NOT NULL,\n"
            + " name TEXT NOT NULL,\n"
            + " stock INTEGER DEFAULT 0,\n"
            + " timestamp TEXT NOT NULL\n"
            + ");";

        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table history in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    // Creates the log table for system events
    public void createLogsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS logs (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " event TEXT NOT NULL,\n"
            + " username TEXT NOT NULL,\n"
            + " desc TEXT NOT NULL,\n"
            + " timestamp TEXT NOT NULL\n"
            + ");";

        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table logs in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
     
    // Creates the products table, sample data already exists
    public void createProductTable() {
        String sql = "CREATE TABLE IF NOT EXISTS product (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " name TEXT NOT NULL UNIQUE,\n"
            + " stock INTEGER DEFAULT 0,\n"
            + " price REAL DEFAULT 0.00\n"
            + ");";

        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table product in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
     
    // Create users table, can be seen in the homepage some user roles
    public void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " username TEXT NOT NULL UNIQUE,\n"
            + " password TEXT NOT NULL,\n"
            + " role INTEGER DEFAULT 2,\n"
            + " locked INTEGER DEFAULT 0,\n"
            + " failed_attempts INTEGER DEFAULT 0,\n"
            + " last_failed_attempt TIMESTAMP\n"
            + ");";

        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table users in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    // Drop history table upon starting app (clean slate)
    public void dropHistoryTable() {
        String sql = "DROP TABLE IF EXISTS history;";

        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table history in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    // Drop logs table upon starting app (clean slate)
    public void dropLogsTable() {
        String sql = "DROP TABLE IF EXISTS logs;";

        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table logs in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    // Drop products table upon starting app (clean slate)
    public void dropProductTable() {
        String sql = "DROP TABLE IF EXISTS product;";

        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table product in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    // Remove all users upon starting app (clean slate)
    public void dropUserTable() {
        String sql = "DROP TABLE IF EXISTS users;";

        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table users in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    // Add logs into product purchase history
    public void addHistory(String username, String name, int stock, String timestamp) {
        String sql = "INSERT INTO history(username,name,stock,timestamp) VALUES(?,?,?,?)";
        
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            pstmt.setString(2, name);
            pstmt.setInt(3, stock);
            pstmt.setString(4, timestamp);
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    // Add system event/s logs
    public void addLogs(String event, String username, String desc, String timestamp) {
        String sql = "INSERT INTO logs(event,username,desc,timestamp) VALUES(?,?,?,?)";
        
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, event);
            pstmt.setString(2, username);
            pstmt.setString(3, desc);
            pstmt.setString(4, timestamp);
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    // Add products into the existing table & list
    public void addProduct(String name, int stock, double price) {
        String sql = "INSERT INTO product(name,stock,price) VALUES(?,?,?)";
        
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, name);
            pstmt.setInt(2, stock);
            pstmt.setDouble(3, price);
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    // User registration (mainly for input validation)
    public boolean addUser(String username, String password) {
        // Check if username or password is valid. If !valid, deny registration
        if (!isValidUsername(username) || !isValidPassword(password)) {
            return false;
        }
        
        String hashedPassword = hashPassword(password); // hash password upon input
        String sql = "INSERT INTO users(username, password) VALUES(?, ?)"; // insert user after hashing
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            // Error catching
            System.out.print("Registration failed: " + ex.getMessage());
            return false;
        }
    }
    
    // Fetch history and add data into history table
    public ArrayList<History> getHistory(){
        String sql = "SELECT id, username, name, stock, timestamp FROM history";
        ArrayList<History> histories = new ArrayList<History>();
        
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                histories.add(new History(rs.getInt("id"),
                                   rs.getString("username"),
                                   rs.getString("name"),
                                   rs.getInt("stock"),
                                   rs.getString("timestamp")));
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return histories;
    }
    
    // Fetch log contents
    public ArrayList<Logs> getLogs(){
        String sql = "SELECT id, event, username, desc, timestamp FROM logs";
        ArrayList<Logs> logs = new ArrayList<Logs>();
        
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                logs.add(new Logs(rs.getInt("id"),
                                   rs.getString("event"),
                                   rs.getString("username"),
                                   rs.getString("desc"),
                                   rs.getString("timestamp")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return logs;
    }
    
    // Fetch product list
    public ArrayList<Product> getProduct(){
        String sql = "SELECT id, name, stock, price FROM product";
        ArrayList<Product> products = new ArrayList<Product>();
        
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"),
                                   rs.getString("name"),
                                   rs.getInt("stock"),
                                   rs.getFloat("price")));
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return products;
    }
    
    // Fetch users in database (upon starting the app, only sample users are present)
    public ArrayList<User> getUsers(){
        String sql = "SELECT id, username, password, role, locked FROM users";
        ArrayList<User> users = new ArrayList<User>();
        
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                users.add(new User(rs.getInt("id"),
                                   rs.getString("username"),
                                   rs.getString("password"),
                                   rs.getInt("role"),
                                   rs.getInt("locked")));
            }
        } catch (Exception ex) {}
        return users;
    }
    
    // Adding of user into the database mismo. Default user role = 2 (client role)
    public void addUser(String username, String password, int role) {
        String sql = "INSERT INTO users(username,password,role) VALUES(?,?,?)";
        
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setInt(3, role);
            pstmt.executeUpdate();
            
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    // Deleting of user/s
    public void removeUser(String username) {
        String sql = "DELETE FROM users WHERE username=?";

        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            System.out.println("User " + username + " has been deleted.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    // Fetch product list
    public Product getProduct(String name){
        String sql = "SELECT name, stock, price FROM product WHERE name=?";
        Product product = null;
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                product = new Product(rs.getString("name"),
                                       rs.getInt("stock"),
                                       rs.getFloat("price"));
            }
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return product;
    }
    
    // User authentication in login page
    public User authenticateUser(String username, String password) {
        String sql = "SELECT id, username, password, role, locked, failed_attempts FROM users WHERE username = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Check if account is locked
                if (rs.getInt("locked") == 1) {
                    return null; // Account locked
                }
                
                String storedHash = rs.getString("password");
                String inputHash = hashPassword(password);
                
                if (storedHash.equals(inputHash)) {
                    resetFailedAttempts(username); // Reset failed attempts on successful login
                    return new User(rs.getInt("id"), rs.getString("username"), 
                                  "", rs.getInt("role"), rs.getInt("locked"));
                } else {
                    incrementFailedAttempts(username);
                    return null;
                }
            }
        } catch (SQLException ex) {
            System.out.print("Authentication error: " + ex.getMessage());
        }
        return null;
    }
    
    // Increment failed attempts & user lockout method
    private void incrementFailedAttempts(String username) {
        String sql = "UPDATE users SET " +
                    "failed_attempts = failed_attempts + 1, " +
                    "last_failed_attempt = datetime('now'), " +
                    "locked = CASE WHEN failed_attempts + 1 >= 3 THEN 1 ELSE locked END " +
                    "WHERE username = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Check if account was locked
                checkIfAccountLocked(username);
            }
            
        } catch (SQLException ex) {
            System.out.print("Error updating failed attempts: " + ex.getMessage());
        }
    }
    
    // Checker if account was locked. This is used to declare in system that the user is locked 
    private void checkIfAccountLocked(String username) {
        String sql = "SELECT locked FROM users WHERE username = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next() && rs.getInt("locked") == 1) {
                System.out.println("Account " + username + " has been locked due to too many failed attempts.");
            }
            
        } catch (SQLException ex) {
            System.out.print("Error checking account lock status: " + ex.getMessage());
        }
    }
    
    // Function called to reset failed attempts. Called when user successfully logs into account
    private void resetFailedAttempts(String username) {
        String sql = "UPDATE users SET failed_attempts = 0, last_failed_attempt = NULL WHERE username = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.print("Error resetting failed attempts: " + ex.getMessage());
        }
    }
    
    // Valid username checker. Must be >3 and <50 characters. Only letters, numbers, and "_" allowed
    public boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) return false;
        if (username.length() < 3 || username.length() > 50) return false;
        return username.matches("^[a-zA-Z0-9_]+$"); // Alphanumeric and underscore only
    }
    
    // Valid password checker. Must have at least 1 upper & lowercase letter, 1 number, & 1 special character
    // Min. of 8 characters to be valid
    public boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) return false;
        
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
        
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
    
    // Check if username already exists
    public boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt(1) > 0;
            
        } catch (SQLException ex) {
            System.out.print("Error checking username: " + ex.getMessage());
            return true; // Assume username exists for safety
        }
    }
}