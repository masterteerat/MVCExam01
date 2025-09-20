package ViewPack;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import ControllerPack.RegistrationController;
import ModelPack.RegisteredSubject;
import ModelPack.Subject;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryPanel extends JPanel {
    private MainView parent;
    private RegistrationController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private String studentId = "";

    public HistoryPanel(MainView parent, RegistrationController controller) {
        this.parent = parent;
        this.controller = controller;

        setLayout(new BorderLayout());
        String[] columns = {"Subject ID", "Subject Name"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        JButton goToReg = new JButton("Go to register page");
        goToReg.addActionListener(e -> {this.parent.showPanel("register");});
        add(goToReg, BorderLayout.SOUTH);

    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
        loadTableData();
    }

    //show history
    public void loadTableData() {
        tableModel.setRowCount(0);
        List<String> registeredIds = controller.getRegisteredSubjects().stream()
                .filter(r -> r.getStudentId().equals(studentId))
                .map(RegisteredSubject::getSubjectId)
                .collect(Collectors.toList());

        for (Subject s : controller.getAllSubjects()) {
            if (registeredIds.contains(s.getSubjectId())) {
                tableModel.addRow(new Object[]{s.getSubjectId(), s.getSubjectName()});
            }
        }
    }
}
