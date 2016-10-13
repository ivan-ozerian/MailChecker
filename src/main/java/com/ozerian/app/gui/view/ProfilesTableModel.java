package com.ozerian.app.gui.view;

import com.ozerian.app.model.entity.Profile;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Class extends AbstractTableModel for representation of existing profiles list
 * with appropriate override methods.
 */
public class ProfilesTableModel extends AbstractTableModel {

    private static final int EMAIL_COLUMN = 0;
    private static final int PORT = 1;
    private static final int ATTACHMENTS_FOLDER = 2;

    private String[] columnNames = {"Email", "PORT", "Folder for attachments"};

    private List<Profile> profiles;

    public ProfilesTableModel(List<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public int getRowCount() {
        return profiles.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Profile tempProfile = profiles.get(rowIndex);

        switch (columnIndex) {
            case EMAIL_COLUMN:
                return tempProfile.getEmail();
            case PORT:
                return tempProfile.getPort();
            case ATTACHMENTS_FOLDER:
                return tempProfile.getAttachmentStorePath();
            default:
                return tempProfile.getEmail();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
}
