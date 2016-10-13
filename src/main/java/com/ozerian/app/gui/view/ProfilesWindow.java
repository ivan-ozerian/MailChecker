package com.ozerian.app.gui.view;

import com.ozerian.app.model.entity.Profile;
import com.ozerian.app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Main window with profile's table, and input fields for new profile creation.
 */
@Component
public class ProfilesWindow extends JFrame {
    private JTable profilesTable;
    private JTextField emailTextField;
    private JTextField passwordTextField;
    private JTextField portTextField;
    private JTextField pathTextField;
    private JButton addNewProfileButton;
    private JPanel rootPanel;
    private JScrollPane scroll;

    private ProfileService profileService;

    /**
     * Constructor for ProfileWindow (main menu) with init of
     * all panel's components.
     */
    public ProfilesWindow() {
        super("Profiles for email check");
        addNewProfileButton.addActionListener((e) -> {
            addProfile();
            showAllProfiles();
        });

        emailTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });

        passwordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });

        portTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });

        pathTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
    }

    /**
     * Method handle input data from text filds and create new profile
     * after click button.
     */
    private void addProfile() {
        Profile profile = new Profile();
        profile.setEmail(emailTextField.getText());
        profile.setPassword(passwordTextField.getText());
        profile.setPort(portTextField.getText());
        profile.setAttachmentStorePath(pathTextField.getText());

        profileService.saveProfile(profile);
    }

    /**
     * This method get all profile from database through ProfileService and
     * shows all profile on existing Jtable on main window.
     */
    private void showAllProfiles() {
        List<Profile> profiles = profileService.getAllProfiles();
        ProfilesTableModel model = new ProfilesTableModel(profiles);
        profilesTable.setModel(model);
    }

    /**
     * Init method for launch of main menu window.
     */
    public void init() {
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

}
