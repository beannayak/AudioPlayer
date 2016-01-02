package com.project.audioplayerproject.domain;

public class NewUser {
    private String userName;
    private String password;
    private String repeatPassword;
    private String invitationCode;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NewUser() {
    }

    public NewUser(String userName, String password, String repeatPassword, String invitationCode) {
        this.userName = userName;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.invitationCode = invitationCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
