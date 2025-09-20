package ViewPack;

import javax.swing.*;
import java.awt.*;
import ControllerPack.RegistrationController;

public class MainView extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private RegistrationController controller;

    public MainView() {
        setTitle("Student Registration System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new RegistrationController();

        

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);


        LoginPanel loginPanel = new LoginPanel(this, controller);
        RegistrationPanel registrationPanel = new RegistrationPanel(this, controller);
        HistoryPanel historyPanel = new HistoryPanel(this, controller);


        loginPanel.setPanels(registrationPanel, historyPanel);

        //initialise panel
        mainPanel.add(loginPanel, "login");
        mainPanel.add(registrationPanel, "register");
        mainPanel.add(historyPanel, "history");
        //update history page
        controller.setRegistrationListener(() -> {
            historyPanel.loadTableData();  
            showPanel("history");
        });

        add(mainPanel);
        showPanel("login");
        
        setVisible(true);
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }
}