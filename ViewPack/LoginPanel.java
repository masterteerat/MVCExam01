package ViewPack;

import javax.swing.*;
import java.awt.*;
import ControllerPack.RegistrationController;

public class LoginPanel extends JPanel {
    private MainView parent;
    private RegistrationController controller;
    private RegistrationPanel registerPanel;
    private HistoryPanel historyPanel;

    public LoginPanel(MainView parent, RegistrationController controller) {
        this.parent = parent;
        this.controller = controller;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Student ID:");
        JTextField studentField = new JTextField(15);
        JButton loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> {
            String studentId = studentField.getText().trim();
            boolean valid = this.controller.getAllStudents().stream()
                    .anyMatch(s -> s.getStudentId().equals(studentId));

            if (valid) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                if(registerPanel != null && historyPanel != null){
                    registerPanel.setStudentId(studentId);
                    historyPanel.setStudentId(studentId);
                }
                this.parent.showPanel("history");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Student ID!");
            }
        });

        gbc.gridx = 0; gbc.gridy = 0; add(label, gbc);
        gbc.gridx = 1; add(studentField, gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(loginBtn, gbc);
    }

    public void setPanels(RegistrationPanel registerPanel, HistoryPanel historyPanel) {
        this.registerPanel = registerPanel;
        this.historyPanel = historyPanel;
    }
}