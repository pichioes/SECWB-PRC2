package View;

import Model.User;
import javax.swing.*;

public class Login extends javax.swing.JPanel {

    public Frame frame;

    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        usernameFld = new javax.swing.JTextField();
        passwordFld = new javax.swing.JPasswordField();
        registerBtn = new javax.swing.JButton();
        loginBtn = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SECURITY Svcs");
        jLabel1.setToolTipText("");

        usernameFld.setBackground(new java.awt.Color(240, 240, 240));
        usernameFld.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        usernameFld.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameFld.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "USERNAME", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));

        passwordFld.setBackground(new java.awt.Color(240, 240, 240));
        passwordFld.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordFld.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordFld.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "PASSWORD", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N);
        
        errorLabel.setForeground(java.awt.Color.RED);
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorLabel.setText("");

    registerBtn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    registerBtn.setText("REGISTER");
    registerBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            registerBtnActionPerformed(evt);
        }
    });

    loginBtn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    loginBtn.setText("LOGIN");
    loginBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            loginBtnActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(registerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(usernameFld)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordFld, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLabel, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(usernameFld, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordFld, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(126, Short.MAX_VALUE))
        );
    }// </editor-fold>
    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        String username = usernameFld.getText().trim();
        String password = new String(passwordFld.getPassword());
        
        // Clear previous error
        errorLabel.setText("");
        
        // Input validation
        if (username.isEmpty() || password.isEmpty()) {
            showError("Username and password cannot be empty");
            return;
        }
        
        try {
            // Try secure authentication first
            User user = frame.main.sqlite.authenticateUser(username, password);
            
            if (user != null) {
                // Set current user in frame and navigate to appropriate home
                frame.setCurrentUser(user);
                frame.mainNav();
                clearFields();
                
               frame.main.sqlite.addLogs(
               "LOGIN",
               username,
               "Successful Login",
               new java.sql.Timestamp(new java.util.Date().getTime()).toString()
               );
               
                return;
            }
            
            // If secure auth fails, try authentication for existing plain text passwords
            user = simpleAuthenticate(username, password);
            
            if (user != null) {
                // Set current user in frame and navigate to appropriate home
                frame.setCurrentUser(user);
                frame.mainNav();
                clearFields();
                
               frame.main.sqlite.addLogs(
               "LOGIN",
               username,
               "Successful Login",
               new java.sql.Timestamp(new java.util.Date().getTime()).toString()
               );

            } else {
             if (frame.main.sqlite.isAccountLocked(username)) {
                    showError("Account is locked, please wait for 15 minutes.");
                } else {
                    frame.main.sqlite.addLogs(
                    "LOGIN",
                    username,
                    "Invalid Details",
                    new java.sql.Timestamp(new java.util.Date().getTime()).toString()
                    );
                    showError("Invalid username or password");
                }
        }
            
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
            e.printStackTrace();
            showError("Login failed. Please try again.");
            
            frame.main.sqlite.addLogs(
            "LOGIN",
            username,
            "Failed Login",
            new java.sql.Timestamp(new java.util.Date().getTime()).toString()
            );
            
        }
        
        // Clear password field for security
        passwordFld.setText("");
    }//GEN-LAST:event_loginBtnActionPerformed

    private User simpleAuthenticate(String username, String password) {
        try {
            java.util.ArrayList<Model.User> users = frame.main.sqlite.getUsers();
            for (Model.User user : users) {
                if (user.getUsername().equals(username) && 
                    user.getPassword().equals(password) && 
                    user.getLocked() == 0) {
                    return user;
                }
            }
        } catch (Exception e) {
            System.out.println("Simple auth error: " + e.getMessage());
        }
        return null;
    }
    
    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        clearFields();
        frame.registerNav();
    }//GEN-LAST:event_registerBtnActionPerformed

    private void showError(String message) {
        errorLabel.setText(message);
        // Auto-clear error after 5 seconds
        Timer timer = new Timer(5000, e -> errorLabel.setText(""));
        timer.setRepeats(false);
        timer.start();
    }
    
    private void clearFields() {
        usernameFld.setText("");
        passwordFld.setText("");
        errorLabel.setText("");
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passwordFld;
    private javax.swing.JButton registerBtn;
    private javax.swing.JTextField usernameFld;
    private javax.swing.JLabel errorLabel;
    // End of variables declaration
}
