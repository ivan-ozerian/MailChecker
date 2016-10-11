package com.ozerian.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mail")
    private String mailAdress;

    @Column(name = "port")
    private String port;

    @Column(name = "attachment_path")
    private String attachmentStorePath;

    public Profile() {
    }

    public Profile(String mailAdress, String port, String attachmentStorePath) {
        this.mailAdress = mailAdress;
        this.port = port;
        this.attachmentStorePath = attachmentStorePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAttachmentStorePath() {
        return attachmentStorePath;
    }

    public void setAttachmentStorePath(String attachmentStorePath) {
        this.attachmentStorePath = attachmentStorePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;

        Profile profile = (Profile) o;

        if (id != null ? !id.equals(profile.id) : profile.id != null) return false;
        if (mailAdress != null ? !mailAdress.equals(profile.mailAdress) : profile.mailAdress != null) return false;
        if (port != null ? !port.equals(profile.port) : profile.port != null) return false;
        return attachmentStorePath != null ? attachmentStorePath.equals(profile.attachmentStorePath) : profile.attachmentStorePath == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mailAdress != null ? mailAdress.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (attachmentStorePath != null ? attachmentStorePath.hashCode() : 0);
        return result;
    }
}
