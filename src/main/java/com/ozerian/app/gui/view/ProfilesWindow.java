package com.ozerian.app.gui.view;

import com.ozerian.app.model.entity.Profile;
import com.ozerian.app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

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

    private void addProfile() {
        Profile profile = new Profile();
        profile.setEmail(emailTextField.getText());
        profile.setPassword(passwordTextField.getText());
        profile.setPort(portTextField.getText());
        profile.setAttachmentStorePath(pathTextField.getText());

        profileService.addProfile(profile);
    }

    private void showAllProfiles() {
        List<Profile> profiles = profileService.getAllProfiles();
        ProfilesTableModel model = new ProfilesTableModel(profiles);
        profilesTable.setModel(model);
    }

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public void init() {
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
