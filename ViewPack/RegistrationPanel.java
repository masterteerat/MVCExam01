package ViewPack;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import ControllerPack.RegistrationController;
import ModelPack.Subject;
import java.awt.*;

public class RegistrationPanel extends JPanel {
    private MainView parent;
    private RegistrationController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private String studentId = "";

    public RegistrationPanel(MainView parent, RegistrationController controller) {
        this.parent = parent;
        this.controller = controller;
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Name", "Credits", "Teacher", "Prerequisite", "Max", "Current"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JButton registerBtn = new JButton("Register Selected");
        registerBtn.addActionListener(e -> registerSelected());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(registerBtn, BorderLayout.SOUTH);
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
        loadTableData();
    }

    //read csv file
    private void loadTableData() {
        tableModel.setRowCount(0);
        for (Subject s : controller.getAvailableSubjects(studentId)) {
            tableModel.addRow(new Object[]{
                    s.getSubjectId(), s.getSubjectName(), s.getCredits(),
                    s.getTeacherName(), s.getPrerequisiteId(),
                    s.getMaxStudents(), s.getCurrentStudents()
            });
        }
    }

    private void registerSelected() {
        int row = table.getSelectedRow();
        if (row != -1) {
            String subjectId = (String) tableModel.getValueAt(row, 0);
            JOptionPane.showMessageDialog(this, "Registered successfully!");
            parent.showPanel("history");
            boolean success = controller.registerSubject(studentId, subjectId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Registered successfully!");
                loadTableData();
                parent.showPanel("history");
            } else {
                JOptionPane.showMessageDialog(this, "Cannot register (prerequisite or max reached)!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a subject!");
        }
    }


}