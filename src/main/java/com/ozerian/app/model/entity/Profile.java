package com.ozerian.app.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 * Class for representation of Profile entity.
 */
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(name = "mail")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "port")
    private String port;

    @Column(name = "attachment_path")
    private String attachmentStorePath;

    public Profile() {
    }

    public Profile(String email, String port, String attachmentStorePath) {
        this.email = email;
        this.port = port;
        this.attachmentStorePath = attachmentStorePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (email != null ? !email.equals(profile.email) : profile.email != null) return false;
        if (port != null ? !port.equals(profile.port) : profile.port != null) return false;
        return attachmentStorePath != null ? attachmentStorePath.equals(profile.attachmentStorePath) : profile.attachmentStorePath == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (attachmentStorePath != null ? attachmentStorePath.hashCode() : 0);
        return result;
    }
}
