package com.damir.healthcare.auth;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class AuGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    @Column(name="email")
    private String email;
    @Column(name="role")
    private String authgroup;

    public AuGroup(String email, String authgroup) {
        this.email = email;
        this.authgroup = authgroup;
    }

    public AuGroup() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthgroup() {
        return authgroup;
    }

    public void setAuthgroup(String authgroup) {
        this.authgroup = authgroup;
    }
}
