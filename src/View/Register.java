
package View;

public class Register extends javax.swing.JPanel {

    public Frame frame;
    
    public Register() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        registerBtn = new javax.swing.JButton();
        passwordFld = new javax.swing.JPasswordField();
        usernameFld = new javax.swing.JTextField();
        errormessageFld = new javax.swing.JLabel();
        confpassFld = new javax.swing.JPasswordField();
        errorDisplayFld = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();

        registerBtn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        registerBtn.setText("REGISTER");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });

        passwordFld.setBackground(new java.awt.Color(240, 240, 240));
        passwordFld.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordFld.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordFld.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "PASSWORD", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        usernameFld.setBackground(new java.awt.Color(240, 240, 240));
        usernameFld.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        usernameFld.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameFld.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "USERNAME", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        errormessageFld.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        errormessageFld.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errormessageFld.setText("SECURITY Svcs");
        errormessageFld.setToolTipText("");

        confpassFld.setBackground(new java.awt.Color(240, 240, 240));
        confpassFld.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        confpassFld.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        confpassFld.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "CONFIRM PASSWORD", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        errorDisplayFld.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        errorDisplayFld.setForeground(new java.awt.Color(255, 0, 0));
        errorDisplayFld.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorDisplayFld.setText("");

        backBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        backBtn.setText("<Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usernameFld)
                    .addComponent(errormessageFld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordFld, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confpassFld, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorDisplayFld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(200, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backBtn)
                .addGap(24, 24, 24)
                .addComponent(errormessageFld, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(usernameFld, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordFld, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confpassFld, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorDisplayFld, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed

        String username = usernameFld.getText();
        String password = passwordFld.getText();
        String confirmPassword = confpassFld.getText();
        
        // Clear previous error message
        errorDisplayFld.setText("");
        
        // Validate inputs
        String errorMessage = validateRegistration(username, password, confirmPassword);
        
        if (errorMessage != null) {
            // Display error message below confirm password field
            errorDisplayFld.setText(errorMessage);
            // Clear password fields but keep username
            passwordFld.setText("");
            confpassFld.setText("");
            return;
        }
        
        // Check if username already exists
        if (frame.main.sqlite.usernameExists(username)) {
            errorDisplayFld.setText("Username already exists");
            // Clear password fields but keep username
            passwordFld.setText("");
            confpassFld.setText("");
            return;
        }
        
        // Attempt registration
        boolean registrationSuccess = frame.main.sqlite.addUser(username, password);
        
        if (registrationSuccess) {
            
            frame.main.sqlite.addLogs(
            "REGISTER",
            username,
            "User registered successfully.",
            new java.sql.Timestamp(new java.util.Date().getTime()).toString()
            );
            
            // Clear fields
            usernameFld.setText("");
            passwordFld.setText("");
            confpassFld.setText("");
            errorDisplayFld.setText("");
            
            // Navigate to login after successful registration
            frame.loginNav();
        } else {
            errorDisplayFld.setText("Registration failed");
            
            frame.main.sqlite.addLogs(
                "REGISTER",
                username,
                "User registration failed",
                new java.sql.Timestamp(new java.util.Date().getTime()).toString()
            );
        }
    }                                           

    private String validateRegistration(String username, String password, String confirmPassword) {
        // Check if any field is empty
        if (username.trim().isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return "All fields are required";
        }
        
        // Validate username
        if (!frame.main.sqlite.isValidUsername(username)) {
            if (username.length() < 3) {
                 frame.main.sqlite.addLogs(
                "REGISTER",
                username,
                "User registration failed",
                new java.sql.Timestamp(new java.util.Date().getTime()).toString()
            );
                return "Username too short (min 3 chars)";
            } else if (username.length() > 50) {
                 frame.main.sqlite.addLogs(
                "REGISTER",
                username,
                "User registration failed",
                new java.sql.Timestamp(new java.util.Date().getTime()).toString()
            );
                return "Username too long (max 50 chars)";
            } else {
                 frame.main.sqlite.addLogs(
                "REGISTER",
                username,
                "User registration failed",
                new java.sql.Timestamp(new java.util.Date().getTime()).toString()
            );
                return "Username contains invalid characters";
            }
        }
        
        // Validate password
        if (!frame.main.sqlite.isValidPassword(password)) {
            if (password.length() < 8) {
                 frame.main.sqlite.addLogs(
                "REGISTER",
                username,
                "User registration failed",
                new java.sql.Timestamp(new java.util.Date().getTime()).toString()
            );
                return "Password too short (min 8 chars)";
            }
            
            boolean hasUpper = password.matches(".*[A-Z].*");
            boolean hasLower = password.matches(".*[a-z].*");
            boolean hasDigit = password.matches(".*[0-9].*");
            boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
            
            if (!hasUpper) {
                 frame.main.sqlite.addLogs(
                "REGISTER",
                username,
                "User registration failed",
                new java.sql.Timestamp(new java.util.Date().getTime()).toString()
            );
                return "Password needs uppercase letter";
                
            } else if (!hasLower) {
                 frame.main.sqlite.addLogs(
                "REGISTER",
                username,
                "User registration failed",
                new java.sql.Timestamp(new java.util.Date().getTime()).toString()
            );
                return "Password needs lowercase letter";
            } else if (!hasDigit) {
                 frame.main.sqlite.addLogs(
                "REGISTER",
                username,
                "User registration failed",
                new java.sql.Timestamp(new java.util.Date().getTime()).toString()
            );
                return "Password needs a number";
            } else if (!hasSpecial) {
                 frame.main.sqlite.addLogs(
                "REGISTER",
                username,
                "User registration failed",
                new java.sql.Timestamp(new java.util.Date().getTime()).toString()
            );
                return "Password needs special character";
            }
        }
        
        // Check if passwords match
        if (!password.equals(confirmPassword)) {
             frame.main.sqlite.addLogs(
                "REGISTER",
                username,
                "User registration failed",
                new java.sql.Timestamp(new java.util.Date().getTime()).toString()
            );
            return "Passwords do not match";
        }
        
        return null; // No errors
    }//GEN-LAST:event_registerBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        frame.loginNav();
    }//GEN-LAST:event_backBtnActionPerformed


    // Variables declaration - do not modify
    private javax.swing.JButton backBtn;
    private javax.swing.JPasswordField confpassFld;
    private javax.swing.JLabel errormessageFld;
    private javax.swing.JLabel errorDisplayFld;
    private javax.swing.JPasswordField passwordFld;
    private javax.swing.JButton registerBtn;
    private javax.swing.JTextField usernameFld;
    // End of variables declaration
}
