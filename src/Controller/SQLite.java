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
    
    // Initialize database connection with proper settings
    private Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(driverURL);
        // Set timeout to prevent long waits
        conn.createStatement().execute("PRAGMA busy_timeout = 3000;");
        // Enable WAL mode for better concurrency
        conn.createStatement().execute("PRAGMA journal_mode = WAL;");
        return conn;
    }
    
    // Simple password hashing (without salt)
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
    
    public void createHistoryTable() {
        String sql = "CREATE TABLE IF NOT EXISTS history (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " username TEXT NOT NULL,\n"
            + " name TEXT NOT NULL,\n"
            + " stock INTEGER DEFAULT 0,\n"
            + " timestamp TEXT NOT NULL\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table history in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void createLogsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS logs (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " event TEXT NOT NULL,\n"
            + " username TEXT NOT NULL,\n"
            + " desc TEXT NOT NULL,\n"
            + " timestamp TEXT NOT NULL\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table logs in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
     
    public void createProductTable() {
        String sql = "CREATE TABLE IF NOT EXISTS product (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " name TEXT NOT NULL UNIQUE,\n"
            + " stock INTEGER DEFAULT 0,\n"
            + " price REAL DEFAULT 0.00\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table product in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
     
    // Create user table without salt column
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

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table users in database.db created.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void dropHistoryTable() {
        String sql = "DROP TABLE IF EXISTS history;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table history in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void dropLogsTable() {
        String sql = "DROP TABLE IF EXISTS logs;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table logs in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void dropProductTable() {
        String sql = "DROP TABLE IF EXISTS product;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table product in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void dropUserTable() {
        String sql = "DROP TABLE IF EXISTS users;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table users in database.db dropped.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void addHistory(String username, String name, int stock, String timestamp) {
        String sql = "INSERT INTO history(username,name,stock,timestamp) VALUES('" + username + "','" + name + "','" + stock + "','" + timestamp + "')";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void addLogs(String event, String username, String desc, String timestamp) {
        String sql = "INSERT INTO logs(event,username,desc,timestamp) VALUES('" + event + "','" + username + "','" + desc + "','" + timestamp + "')";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void addProduct(String name, int stock, double price) {
        String sql = "INSERT INTO product(name,stock,price) VALUES('" + name + "','" + stock + "','" + price + "')";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    // User registration with input validation (no salt)
    public boolean addUser(String username, String password) {
        // Input validation
        if (!isValidUsername(username) || !isValidPassword(password)) {
            return false;
        }
        
        String hashedPassword = hashPassword(password);
        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.out.print("Registration failed: " + ex.getMessage());
            return false;
        }
    }
    
    
    public ArrayList<History> getHistory(){
        String sql = "SELECT id, username, name, stock, timestamp FROM history";
        ArrayList<History> histories = new ArrayList<History>();
        
        try (Connection conn = DriverManager.getConnection(driverURL);
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
    
    public ArrayList<Logs> getLogs(){
        String sql = "SELECT id, event, username, desc, timestamp FROM logs";
        ArrayList<Logs> logs = new ArrayList<Logs>();
        
        try (Connection conn = DriverManager.getConnection(driverURL);
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
    
    public ArrayList<Product> getProduct(){
        String sql = "SELECT id, name, stock, price FROM product";
        ArrayList<Product> products = new ArrayList<Product>();
        
        try (Connection conn = DriverManager.getConnection(driverURL);
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
    
    public ArrayList<User> getUsers(){
        String sql = "SELECT id, username, password, role, locked FROM users";
        ArrayList<User> users = new ArrayList<User>();
        
        try (Connection conn = DriverManager.getConnection(driverURL);
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
    
    public void addUser(String username, String password, int role) {
        String sql = "INSERT INTO users(username,password,role) VALUES('" + username + "','" + password + "','" + role + "')";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void removeUser(String username) {
        String sql = "DELETE FROM users WHERE username='" + username + "';";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("User " + username + " has been deleted.");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public Product getProduct(String name){
        String sql = "SELECT name, stock, price FROM product WHERE name='" + name + "';";
        Product product = null;
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            product = new Product(rs.getString("name"),
                                   rs.getInt("stock"),
                                   rs.getFloat("price"));
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return product;
    }
    
    // User authentication without salt - improved connection handling
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
                    // Reset failed attempts on successful login
                    resetFailedAttempts(username);
                    return new User(rs.getInt("id"), rs.getString("username"), 
                                  "", rs.getInt("role"), rs.getInt("locked"));
                } else {
                    // Increment failed attempts
                    incrementFailedAttempts(username);
                    return null;
                }
            }
        } catch (SQLException ex) {
            System.out.print("Authentication error: " + ex.getMessage());
        }
        return null;
    }
    
    // Account lockout mechanism with better connection handling
    private void incrementFailedAttempts(String username) {
        String updateSql = "UPDATE users SET failed_attempts = failed_attempts + 1, " +
                          "last_failed_attempt = datetime('now') WHERE username = ?";
        String lockSql = "UPDATE users SET locked = 1 WHERE username = ? AND failed_attempts >= 3";
        
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false); // Start transaction
            
            // Update failed attempts
            try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
                pstmt.setString(1, username);
                pstmt.executeUpdate();
            }
            
            // Lock account if needed (in same transaction)
            try (PreparedStatement pstmt = conn.prepareStatement(lockSql)) {
                pstmt.setString(1, username);
                pstmt.executeUpdate();
            }
            
            conn.commit(); // Commit transaction
            
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.print("Error rolling back transaction: " + rollbackEx.getMessage());
                }
            }
            System.out.print("Error updating failed attempts: " + ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Reset auto commit
                    conn.close();
                } catch (SQLException closeEx) {
                    System.out.print("Error closing connection: " + closeEx.getMessage());
                }
            }
        }
    }
    
    private void lockAccountIfNeeded(String username) {
        // This method is now handled within incrementFailedAttempts
        // Keeping it for backward compatibility but making it empty
    }
    
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
    
    // Input validation methods
    public boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) return false;
        if (username.length() < 3 || username.length() > 50) return false;
        return username.matches("^[a-zA-Z0-9_]+$"); // Alphanumeric and underscore only
    }
    
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
        
        try (Connection conn = DriverManager.getConnection(driverURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt(1) > 0;
            
        } catch (SQLException ex) {
            System.out.print("Error checking username: " + ex.getMessage());
            return true; // Assume exists to be safe
        }
    }
}